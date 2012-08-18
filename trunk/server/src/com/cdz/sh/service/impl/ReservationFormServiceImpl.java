package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdz.sh.constants.MasterDataConstants;
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

/**
 * Implementation of ReservationFormService facade
 * 
 * @author fede
 *
 */
public class ReservationFormServiceImpl extends AbstractCrudService<ReservationForm, Long> implements ReservationFormService {

		
	private static final String RESERVATION_FORM_TEMPLATE = "resources/report/reservationForm.jrxml";
	
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
	public ReservationForm book(Alternative chosenAlternative, ReservationForm reservationForm) throws DaoException {
						
		ReservationForm createdReservationForm = this.crudDao.createRecord(reservationForm);
		
		for (Occupation occupation : chosenAlternative.getOccupations()) {
			occupation.setReservationForm(reservationForm);
			this.occupationDao.createRecord(occupation);
		}
		return createdReservationForm;
	}

	@Override
	public String exportData(ReservationForm reservationForm, String absolutePath) throws DaoException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put(MasterDataConstants.IMAGE_LOGO_URL, MasterDataConstants.IMAGE_LOGO_PATH);
	    
		params.put("P_TITULO", "Ficha de Reserva");
	    params.put("P_SUBTITULO", "");
	    
	    params.put("P_ID", reservationForm.getId().toString());
	    params.put("P_CREATION_DATE", reservationForm.getCreationDate());
	    
//	    params.put("P_CUSTOMER_FULL_NAME", reservationForm.getCustomer().getFullName());
//	    params.put("P_CUSTOMER_EMAIL", reservationForm.getCustomer().getEmail());
//	    
//	    List<Occupation> occupations = this.occupationDao.retrieveOccupations(reservationForm);
//	    
//	    params.put("P_DAYS", occupations.size()+1);
//	    params.put("P_NIGHTS", occupations.size());
//	    
//	    /**
//	     * FIXME REVIEW THIS
//	     */
//	    String strRoomTypes = "";
//	    RoomType roomType = null;
//	    for (Occupation occupation : occupations) {
//	    	RoomType nextRoomType = occupation.getId().getRoom().getRoomType();
//	    	if(!nextRoomType.equals(roomType)){
//	    		strRoomTypes = strRoomTypes.concat(nextRoomType.getName() + " - ");
//	    		roomType = nextRoomType;
//	    	}
//		}
//	    if(strRoomTypes.endsWith(" - ")){
//	    	strRoomTypes.subSequence(0, strRoomTypes.length() - 3);
//	    }
//	    
//	    params.put("P_ROOM_TYPES", strRoomTypes);
//	    
//	    params.put("P_DATE_FROM", reservationForm.getDateFrom());
//	    params.put("P_DATE_TO", reservationForm.getDateTo());
//	    
//	    params.put("P_STATE", reservationForm.getState().getState());
//	    
//	    params.put("P_ADULTS", reservationForm.getAdultsQuantity());
//	    params.put("P_CHILDREN", reservationForm.getChildrenQuantity());
//	    
//	    /**
//	     * FIXME REVIEW THIS
//	     */
//	    params.put("P_PRICE_PER_DAY", reservationForm.getPricePerDay());
//	    params.put("P_TOTAL_PRICE", "precio total");
//	    params.put("P_MONETARY_RESERVE", reservationForm.getMonetaryReserve());
//	    
//	    params.put("P_BANK_NAME", reservationForm.getBank().getName());
//	    params.put("P_BANK_DOCUMENT_NUMBER", reservationForm.getBankDocumentNumber());
//	    
		
		PDFReportManager pdfReportManager = new PDFReportManager(RESERVATION_FORM_TEMPLATE, absolutePath, params);
		
		Collection<ReservationForm> collection = new ArrayList<ReservationForm>();
		pdfReportManager.createReport(collection);
		
		return absolutePath;
	}


	
	
	

}
