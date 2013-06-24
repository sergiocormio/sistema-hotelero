package com.cdz.sh.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cdz.sh.dao.CleaningDao;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CleaningDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.model.Cleaning;
import com.cdz.sh.model.CleaningPK;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.export.ExportCleaning;
import com.cdz.sh.report.PDFReportManager;
import com.cdz.sh.service.AbstractCrudService;
import com.cdz.sh.service.CleaningService;
import com.cdz.sh.util.DateUtil;

import flex.messaging.io.ArrayCollection;

/**
 * Implementation of CleaningService facade
 * 
 * @author fede
 *
 */
public class CleaningServiceImpl extends AbstractCrudService<Cleaning, CleaningPK> implements CleaningService {

	private static final String TEMPLATE_FILE_TYPE = ".jrxml";
	private static final String CLEANING_TEMPLATE = "cleaning";
	
	private CleaningDao cleaningDao;
	private OccupationDao occupationDao;
	private RoomDao roomDao;
	
	@Override
	protected CrudDao<Cleaning, CleaningPK> createDao() {
		
		this.occupationDao = new OccupationDaoImpl();
		this.cleaningDao = new CleaningDaoImpl();
		this.roomDao = new RoomDaoImpl();		
		return cleaningDao;
	}
	
	
	@Override
	public List<Cleaning> retrieveRoomsToClean(Date date) throws DaoException {
		//first retrieves saved cleaning objects
		List<Cleaning> cleanings = this.cleaningDao.retrieveRoomsToClean(date);
		Collection<Room> rooms = this.roomDao.retrieveAll();
		if(cleanings == null || cleanings.size()==0){
			cleanings = generateCleanings(date,rooms);
		}else{
			//verifies if exists one cleaning object per existing room
			if(cleanings.size() < rooms.size()){
				//generate missing cleaning objects
				List<Room> missingRooms = new ArrayCollection();
				for(Room r : rooms){
					boolean isMissing = true;
					for(Cleaning c :cleanings){
						if(c.getId().getRoom().equals(r)){
							isMissing = false;
							break;
						}
					}
					if(isMissing){
						missingRooms.add(r);
					}
				}
				cleanings.addAll(generateCleanings(date,missingRooms));
			}
		}
		
		return cleanings;
	}
	
	
	private List<Cleaning> generateCleanings(Date date,Collection<Room> rooms) throws DaoException {
		Map<Room, Cleaning> roomToCleaningMap = new HashMap<Room, Cleaning>();
		Cleaning cleaning = null;
		//first of all, creates one Cleaning object per Room
		for(Room room : rooms){
			cleaning = new Cleaning(date,room);
			roomToCleaningMap.put(room, cleaning);
		}
		
		List<Occupation> confirmedOccupations = this.occupationDao.retrieveConfirmedOccupations(date);
		
		for (Occupation occupation : confirmedOccupations) {
			
			// check general cleaning type (first day)
			if(DateUtil.sameDate(date, occupation.getId().getReservationForm().getDateFrom())){
				cleaning = roomToCleaningMap.get(occupation.getId().getRoom());
				if(cleaning!=null){
					cleaning.addCleaningType(CleaningType.GENERAL);
				}
			}else{
				// check basic cleaning type (every other day except from Sunday)
				boolean needBasicCleaningType = needBasicCleaningType(occupation);
				if(needBasicCleaningType){
					cleaning = roomToCleaningMap.get(occupation.getId().getRoom());
					if(cleaning!=null){
						cleaning.addCleaningType(CleaningType.BASIC);
					}
				}
				
				// check bed clothe change cleaning type  
				boolean needBedClotheChangeCleaningType = needBedClotheChangeCleaningType(occupation);
				if(needBedClotheChangeCleaningType){
					cleaning = roomToCleaningMap.get(occupation.getId().getRoom());
					if(cleaning!=null){
						cleaning.addCleaningType(CleaningType.BED_CLOTHE_CHANGE);
					}
				}
			}
		}
		
		//Converts Collection to List and persists every object
		List<Cleaning> cleanings = new ArrayList<Cleaning>();
		for(Cleaning c : roomToCleaningMap.values()){
			cleanings.add(c);
			//TODO save the cleaning object
//			cleaningDao.createRecord(c);
		}
		
		return cleanings;
	}

	


	private boolean needBasicCleaningType(Occupation occupation) {
		Date dateFrom = occupation.getId().getReservationForm().getDateFrom();
		
		int daysDifference = DateUtil.getDaysDifference(dateFrom, occupation.getId().getDate());
		
		if(daysDifference == 0){
			return false; //as it is the first day, it will need only "General"
		}
		int remainder = daysDifference % 2;
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(occupation.getId().getDate());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		// every other day except from Sunday
		if(remainder == 0 && dayOfWeek != Calendar.SUNDAY){
			return true;
		}
		else{
			return false;
		}
	}


	private boolean needBedClotheChangeCleaningType(Occupation occupation) {

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
		
		int daysDifference = DateUtil.getDaysDifference(dateFrom, occupation.getId().getDate());
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
	public byte[] exportData(List<Cleaning> cleanings, String selectedLocale) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String filename = CLEANING_TEMPLATE + "_" + selectedLocale + TEMPLATE_FILE_TYPE;
		
		PDFReportManager pdfReportManager = new PDFReportManager(filename, params);
		
		ExportCleaning exportCleaning = createExportCleaning(cleanings);
		
		Collection<ExportCleaning> collection = new ArrayList<ExportCleaning>();
		collection.add(exportCleaning);
		
		
		byte[] report = pdfReportManager.createReport(collection);
		
		return report;
	}


	private ExportCleaning createExportCleaning(List<Cleaning> cleanings) {
		
		ExportCleaning exportCleaning = new ExportCleaning();
		
		Date date = cleanings.get(0).getId().getDate();
		Date utcDate = DateUtil.getDateUTC(date);
		
		exportCleaning.setDate(utcDate);
		
		exportCleaning.setCleanings(cleanings);
		
		return exportCleaning;
	}


	@Override
	public List<Cleaning> regenerateRoomsToClean(Date date) throws DaoException {
		Collection<Room> rooms = this.roomDao.retrieveAll();
		List<Cleaning> cleanings = generateCleanings(date,rooms);
		return cleanings;
	}

	

	
	
	

}
