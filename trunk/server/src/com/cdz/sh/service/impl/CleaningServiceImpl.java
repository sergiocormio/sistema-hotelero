package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.cdz.sh.dao.CleaningDao;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CleaningDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.model.Cleaning;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CleaningService;
import com.cdz.sh.util.DateUtil;

/**
 * Implementation of CleaningService facade
 * 
 * @author fede
 *
 */
public class CleaningServiceImpl extends AbstractCrudService<Cleaning, Long> implements CleaningService {

	private CleaningDao cleaningDao;
	private OccupationDao occupationDao;
	
	@Override
	protected CrudDao<Cleaning, Long> createDao() {
		
		this.occupationDao = new OccupationDaoImpl();
		this.cleaningDao = new CleaningDaoImpl();
				
		return cleaningDao;
	}
	
	
//	@Override
//	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException {
//		
//		this.cleaningDao.retrieveRoomsToClean(date);
//		
//		
//		return null;
//	}
	
	
	@Override
	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException {
		
		List<Cleaning> cleanings = new ArrayList<Cleaning>();
		
		List<Occupation> confirmedOccupations = this.occupationDao.retrieveConfirmedOccupations(date);
		
		for (Occupation occupation : confirmedOccupations) {
			
			// check general cleaning type (first day)
			if(DateUtil.sameDate(date, occupation.getId().getReservationForm().getDateFrom())){
				Cleaning cleaning = new Cleaning();
				cleaning.setOccupation(occupation);
				cleaning.setCleaningType(CleaningType.GENERAL);
			}
			
			// check basic cleaning type (every other day except from Sunday)
			boolean needBasicCleaningType = needBasicCleaningType(date, occupation);
			if(needBasicCleaningType){
				Cleaning cleaning = new Cleaning();
				cleaning.setOccupation(occupation);
				cleaning.setCleaningType(CleaningType.BASIC);
			}

			// check bed clothe change cleaning type  
			boolean needBedClotheChangeCleaningType = needBedClotheChangeCleaningType(date, occupation);
			if(needBedClotheChangeCleaningType){
				Cleaning cleaning = new Cleaning();
				cleaning.setOccupation(occupation);
				cleaning.setCleaningType(CleaningType.BED_CLOTHE_CHANGE);
			}
		}
		return cleanings;
	}

	


	private boolean needBasicCleaningType(Date date, Occupation occupation) {
		Date dateFrom = occupation.getId().getReservationForm().getDateFrom();
		
		int daysDifference = DateUtil.getDaysDifference(dateFrom, date);
		
		int remainder = daysDifference % 2;
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		// every other day except from Sunday
		if(remainder == 0 && dayOfWeek != Calendar.SUNDAY){
			return true;
		}
		else{
			return false;
		}
	}


	private boolean needBedClotheChangeCleaningType(Date date, Occupation occupation) {

		boolean needBedClotheChangeCleaningType = false;
		/**
		 *    a las cuarta noche si son 7 dias
			  a la 5 noche si son 8 dias
			  a la 5 noche si son 9,dias
			  10 y 11 idem, para 8 y 9
			  a partir de 12 noches, falta definir
		 */
		Date dateFrom = occupation.getId().getReservationForm().getDateFrom();
		int nightsQuantity = occupation.getId().getReservationForm().getNightsQuantity();
		
		int daysDifference = DateUtil.getDaysDifference(dateFrom, date);
		int nightNumber = daysDifference++; //add the first night
		
		if(nightsQuantity == 7 && nightNumber == 4){
			needBedClotheChangeCleaningType = true;
		}
		
		if((nightsQuantity >= 8 && nightsQuantity <= 11)  && nightNumber == 5){
			needBedClotheChangeCleaningType = true;
		}
		
		return needBedClotheChangeCleaningType;
	}
	
	

	@Override
	public String exportData(List<Cleaning> cleanings) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	

}
