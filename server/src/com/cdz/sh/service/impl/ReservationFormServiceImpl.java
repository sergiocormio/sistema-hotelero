package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
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
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.report.PDFReportManager;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ReservationFormService;
import com.cdz.sh.service.exception.InvalidOperationException;

/**
 * Implementation of ReservationFormService facade
 * 
 * @author fede
 *
 */
public class ReservationFormServiceImpl extends AbstractCrudService<ReservationForm, Long> implements ReservationFormService {

		
	private static final String RESERVATION_FORM_TEMPLATE = "resources/report/reservationForm_ES.jrxml";
	
	private ReservationFormDao reservationFormDao ;
	private OccupationDao occupationDao;
	
	public ReservationFormServiceImpl() {
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
		
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(dateFrom, dateTo, customer, state);
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
		if(reservationForm.getState().equals(StateReservationForm.CONFIRMED)){			
			
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
	public String exportData(ReservationForm reservationForm, String absolutePath) throws DaoException {
						
		Map<String, Object> params = new HashMap<String, Object>();
		PDFReportManager pdfReportManager = new PDFReportManager(RESERVATION_FORM_TEMPLATE, absolutePath, params);
		
		Collection<ReservationForm> collection = new ArrayList<ReservationForm>();
		collection.add(reservationForm);
		
		pdfReportManager.createReport(collection);
		
		return absolutePath;
	}


	
	
	

}
