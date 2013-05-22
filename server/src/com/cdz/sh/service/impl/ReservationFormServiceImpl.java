package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.ReservationFormDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.exception.InvalidParameterException;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.model.Alternative;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.model.Transfer;
import com.cdz.sh.report.PDFReportManager;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ReservationFormService;
import com.cdz.sh.service.exception.InvalidOperationException;
import com.cdz.sh.trigger.CheckReservationFormsExpirationTrigger;
import com.cdz.sh.trigger.Trigger;
import com.cdz.sh.util.DateUtil;

/**
 * Implementation of ReservationFormService facade
 * 
 * @author fede
 *
 */
public class ReservationFormServiceImpl extends AbstractCrudService<ReservationForm, Long> implements ReservationFormService {

	private Trigger checkReservationFormsExpirationTrigger;
	
	private static final String RESERVATION_FORM_TEMPLATE = "reservationForm";
	private static final String TEMPLATE_FILE_TYPE = ".jrxml";
	
	private ReservationFormDao reservationFormDao ;
	private OccupationDao occupationDao;
	
	public ReservationFormServiceImpl() {
		this.checkReservationFormsExpirationTrigger = new CheckReservationFormsExpirationTrigger();
		
		this.occupationDao = new OccupationDaoImpl();
	}
	
	@Override
	protected CrudDao<ReservationForm, Long> createDao() {
		//to to able to handle specific ReservationFormDao operations
		this.reservationFormDao = new ReservationFormDaoImpl(); 
		
		return this.reservationFormDao;
	}
	

	@Override
	public List<ReservationForm> retrieveReservationForms(Date dateFrom, Date dateTo, Customer customer, StateReservationForm state) throws InvalidParameterException, DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, dateTo, customer, state);
		return reservationForms;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationForm> retrieveActiveReservationFormsByDateAndRoom(Date date, Room room) throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveActiveReservationForms(date, room);
		
		Collections.sort(reservationForms);
		
		return reservationForms;
	}

	

	@Override
	public ReservationForm book(Alternative chosenAlternative, ReservationForm reservationForm) throws DaoException, InvalidParameterException {
						
		if(!reservationForm.getState().equals(StateReservationForm.PRE_BOOKING)){ 
			throw new InvalidParameterException(ExceptionErrorCodes.INVALID_RESERVATION_FORM_STATE, "Invalid reservation form state: " + reservationForm.getState().toString());
		}
		ReservationForm createdReservationForm = this.crudDao.createRecord(reservationForm);
		
		for (Occupation occupation : chosenAlternative.getOccupations()) {
			occupation.getId().setReservationForm(reservationForm);
			this.occupationDao.createRecord(occupation);
		}
		return createdReservationForm;
	}
	
	

	@Override
	public ReservationForm createRecord(ReservationForm e) throws DaoException, InvalidOperationException {
		throw new InvalidOperationException(ExceptionErrorCodes.INVALID_OPERATION, "Invalid operation. You should use the 'book' method");
	}

	@Override
	public void updateRecord(ReservationForm reservationForm) throws DaoException {
		if(reservationForm.getState().equals(StateReservationForm.CONFIRMED) ||
			reservationForm.getState().equals(StateReservationForm.PRE_BOOKING)){			
			
			List<Occupation> occupations = this.occupationDao.retrieveOccupations(reservationForm);
			for (Occupation occupation : occupations) {
				List<Occupation> occupationsForTheSameDate = this.occupationDao.retrieveOverlapedOccupations(occupation, reservationForm);
				if(occupationsForTheSameDate != null){
					for (Occupation occupationForTheSameDate : occupationsForTheSameDate) {
						ReservationForm overlapedReservationForm = occupationForTheSameDate.getId().getReservationForm();
						overlapedReservationForm.setState(StateReservationForm.CANCELLED);
						this.reservationFormDao.updateRecord(overlapedReservationForm);
					}
				}
			}
		}
		super.updateRecord(reservationForm);
	}

	@Override
	public byte[] exportData(ReservationForm reservationForm, String selectedLocale) throws DaoException {
			
		Map<String, Object> params = new HashMap<String, Object>();
		
		String filename = RESERVATION_FORM_TEMPLATE + "_" + selectedLocale + TEMPLATE_FILE_TYPE;
		
		PDFReportManager pdfReportManager = new PDFReportManager(filename, params);
		
		reservationForm.setCreationDate(DateUtil.getDateUTC(reservationForm.getCreationDate()));
		reservationForm.setDateFrom(DateUtil.getDateUTC(reservationForm.getDateFrom()));
		reservationForm.setDateTo(DateUtil.getDateUTC(reservationForm.getDateTo()));
		
		
		List<Transfer> transfers = new TransferServiceImpl().retrieveTransfers(reservationForm);
		if(!transfers.isEmpty()){
			reservationForm.setTransfers(transfers);
		}
		
		Collection<ReservationForm> collection = new ArrayList<ReservationForm>();
		collection.add(reservationForm);
		
		byte[] report = pdfReportManager.createReport(collection);
		
		return report;
	}

	@Override
	public Collection<ReservationForm> retrieveAll() throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		return super.retrieveAll();
	}

	
	@Override
	public List<ReservationForm> retrieveActiveReservationForms() throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		return this.reservationFormDao.retrieveActiveReservationForms();
	}

	@Override
	public List<ReservationForm> retrieveActiveReservationFormsByCustomer(Customer customer) throws DaoException {
		
		this.checkReservationFormsExpirationTrigger.executeAction();
		
		return this.reservationFormDao.retrieveActiveReservationForms(customer);
	}
	
	
	
	

}
