package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.dao.ConsumptionDao;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.ReservationFormDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ConsumptionDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.model.Consumption;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.model.export.ExportConsumption;
import com.cdz.sh.report.PDFReportManager;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.ConsumptionService;
import com.cdz.sh.service.exception.InvalidOperationException;
import com.cdz.sh.util.DateUtil;

/**
 * Implementation of ConsumptionService facade
 * 
 * @author fede
 *
 */
public class ConsumptionServiceImpl extends AbstractCrudService<Consumption, Long> implements ConsumptionService {

	private static final String TEMPLATE_FILE_TYPE = ".jrxml";
	private static final String CONSUMPTION_TEMPLATE = "consumption";
	
	private ConsumptionDao consumptionDao;
	private ReservationFormDao reservationFormDao;
	private OccupationDao occupationDao;
	
	@Override
	protected CrudDao<Consumption, Long> createDao() {
		this.occupationDao = new OccupationDaoImpl();
		this.reservationFormDao = new ReservationFormDaoImpl();
		this.consumptionDao = new ConsumptionDaoImpl();
		return consumptionDao;
	}
	
	
	

	@Override
	public Consumption createRecord(Consumption consumption) throws DaoException, InvalidOperationException {
		
		verifyConfirmedReservationForm(consumption);
		
		return super.createRecord(consumption);
	}




	private void verifyConfirmedReservationForm(Consumption consumption) throws DaoException {
		
		int confirmedReservationForms = 0;
		List<ReservationForm> reservationForms = this.reservationFormDao.retrieveReservationForms(consumption);
		
		if(reservationForms != null){
			for (ReservationForm resForm : reservationForms) {
				if(resForm.getState().equals(StateReservationForm.CONFIRMED)){
					confirmedReservationForms++;
				}
			}
		}
		
		if(confirmedReservationForms == 0){
			throw new DaoException(ExceptionErrorCodes.NO_CONFIRMED_RESERVATION_FORM, "The specified consumption, does not have a confirmed reservation form");
		}
		if(confirmedReservationForms > 1){
			throw new DaoException(ExceptionErrorCodes.INVALID_OPERATION, "The specified consumption you want to create or update, has more than one confirmed reservation form");
		}
	}




	@Override
	public void updateRecord(Consumption consumption) throws DaoException {

		verifyConfirmedReservationForm(consumption);
		
		super.updateRecord(consumption);
	}




	@Override
	public List<Consumption> retrieveConsumptions(Date dateFrom, Date dateTo, Room room) throws DaoException {
		
		return this.consumptionDao.retrieveConsumptions(dateFrom, dateTo, room);
	}
	
	
	@Override
	public List<Consumption> retrieveConsumptions(ReservationForm reservationForm) throws DaoException {
		
		List<Consumption> consumptions = new ArrayList<Consumption>();
		
		List<Occupation> occupations = this.occupationDao.retrieveOccupations(reservationForm);
		for (Occupation occupation : occupations) {
			
			consumptions.addAll(this.consumptionDao.retrieveConsumptions(occupation));
		}
		
		return consumptions;
	}
	
	

	@Override
	public byte[] exportData(List<Consumption> consumptions, String selectedLocale) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String filename = CONSUMPTION_TEMPLATE + "_" + selectedLocale + TEMPLATE_FILE_TYPE;
		
		PDFReportManager pdfReportManager = new PDFReportManager(filename, params);
		
		ExportConsumption exportConsumption = createExportConsumption(consumptions);
		
		Collection<ExportConsumption> collection = new ArrayList<ExportConsumption>();
		collection.add(exportConsumption);
		
		
		byte[] report = pdfReportManager.createReport(collection);
		
		return report;
	}


	private ExportConsumption createExportConsumption(List<Consumption> consumptions) {
		
		ExportConsumption exportConsumption = new ExportConsumption();
		
		exportConsumption.setDateFrom(DateUtil.getDateUTC(consumptions.get(0).getDate()));
		exportConsumption.setDateTo(DateUtil.getDateUTC(consumptions.get(consumptions.size()-1).getDate()));
		
		exportConsumption.setTotalPrice(calculateTotalPrice(consumptions));
		
		exportConsumption.setConsumptions(consumptions);
		
		return exportConsumption;
	}




	private double calculateTotalPrice(List<Consumption> consumptions) {
		
		double totalPrice = 0;
		for (Consumption consumption : consumptions) {
			
			totalPrice += consumption.getPrice();
		}
		return totalPrice;
	}

}
