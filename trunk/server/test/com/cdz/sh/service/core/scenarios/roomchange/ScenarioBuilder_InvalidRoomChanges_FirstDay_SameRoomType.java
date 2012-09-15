package com.cdz.sh.service.core.scenarios.roomchange;

import java.util.Date;
import java.util.GregorianCalendar;

import com.cdz.sh.dao.CustomerDao;
import com.cdz.sh.dao.DocumentTypeDao;
import com.cdz.sh.dao.LanguageDao;
import com.cdz.sh.dao.MasterDataFactory;
import com.cdz.sh.dao.OccupationDao;
import com.cdz.sh.dao.RateDao;
import com.cdz.sh.dao.RegionDao;
import com.cdz.sh.dao.ReservationFormDao;
import com.cdz.sh.dao.RoomDao;
import com.cdz.sh.dao.RoomTypeDao;
import com.cdz.sh.dao.SeasonDao;
import com.cdz.sh.dao.ServiceTypesBuilder;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.CustomerDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.dao.impl.OccupationDaoImpl;
import com.cdz.sh.dao.impl.RateDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.dao.impl.RoomDaoImpl;
import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.dao.impl.SeasonDaoImpl;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.CustomerPK;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.Language;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.Rate;
import com.cdz.sh.model.RatePK;
import com.cdz.sh.model.Region;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.Season;
import com.cdz.sh.model.StateReservationForm;
import com.cdz.sh.service.core.roombuilder.SameRoomTypeBuilder;
import com.cdz.sh.util.DateUtil;





/**
 * All room 2, last day on room 1
 * All rooms of same type
 * 
 * 
 * Original State:
 * 
 * 			    1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10
 * 
 * 	Room 1: 	XXX
 *  Room 2:        XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX                      
 *  Room 3:     XXX
 * 
 * Result:
 * 
 * 	NoAvailableAlternativesException: No available alternatives for date: 5-7-2012
 * 
 * 	cause: invalidRoomChanges for rooms 1 and 3 (are available for more than 3 days)
 * 
 * 
 * @author fede
 *
 */
public class ScenarioBuilder_InvalidRoomChanges_FirstDay_SameRoomType {

	
	private RoomTypeDao roomTypeDao;
	private RoomDao roomDao;
	
	private CustomerDao customerDao;
	
	//these three daos are needed to create customers
	private DocumentTypeDao documentTypeDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	
	private CustomerPK customerPKFede;
	
	private SeasonDao seasonDao;
	
	private RateDao rateDao;
	
	private OccupationDao occupationDao;
	private ReservationFormDao reservationFormDao;
	
		
	public ScenarioBuilder_InvalidRoomChanges_FirstDay_SameRoomType() throws DaoException {
		
		MasterDataFactory masterDataFactory = new MasterDataFactory();
		masterDataFactory.createMasterData();
		
		SameRoomTypeBuilder sameRoomTypeBuilder = new SameRoomTypeBuilder();
		sameRoomTypeBuilder.buildRooms();
		
		ServiceTypesBuilder serviceTypesBuilder = new ServiceTypesBuilder();
		serviceTypesBuilder.buildServiceTypes();
		
		this.roomTypeDao = new RoomTypeDaoImpl();
		this.roomDao = new RoomDaoImpl();
		
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		
		this.customerDao = new CustomerDaoImpl();
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		this.customerPKFede = new CustomerPK();
		this.customerPKFede.setDocType(docTypeDNI);
		this.customerPKFede.setIdNumber("33103189");
			
		this.seasonDao = new SeasonDaoImpl();
		this.rateDao = new RateDaoImpl();
		
		this.reservationFormDao = new ReservationFormDaoImpl();
		this.occupationDao = new OccupationDaoImpl();
	}

	
		
	
	
	public void createDummyScenario() {
		
		try {
			//Customers
			createCustomer();
			
			//seasons
			createSeason();
				
			//rates
			createRate();
			
			buildReservationForms();
			fillOccupationsForAllRooms();
			
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		}
	}

	
	private void buildReservationForms() throws DaoException{
		
		for(int i = 0; i < 3; i++){
			
			ReservationForm reservationForm = new ReservationForm();
			
			GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
			reservationForm.setDateFrom(calendar.getTime());
			
			calendar.add(GregorianCalendar.DATE, 9);
			
			reservationForm.setDateTo(calendar.getTime());
			
			DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
			
			CustomerPK customerPKFede = new CustomerPK();
			customerPKFede.setDocType(docTypeDNI);
			customerPKFede.setIdNumber("33103189");
			
			reservationForm.setCustomer(this.customerDao.getRecordById(customerPKFede));
			reservationForm.setState(StateReservationForm.confirmada);
			
			this.reservationFormDao.createRecord(reservationForm);
						
		}
	}
	
	private void fillOccupationsForAllRooms() throws DaoException {
		
		Date dateFrom = new GregorianCalendar(2012, 7, 1).getTime();
		Date dateTo = new GregorianCalendar(2012, 7, 1).getTime();
		long roomId = 1l;
			
		Room room = this.roomDao.getRecordById(roomId);
		
		fillOccupations(dateFrom, dateTo, roomId, room);
		
		roomId++;
		room = this.roomDao.getRecordById(roomId);
		dateFrom = new GregorianCalendar(2012, 7, 2).getTime();
		dateTo = new GregorianCalendar(2012, 7, 10).getTime();

		fillOccupations(dateFrom, dateTo, roomId, room);
		
		roomId++;
		room = this.roomDao.getRecordById(roomId);
		dateFrom = new GregorianCalendar(2012, 7, 1).getTime();
		dateTo = new GregorianCalendar(2012, 7, 1).getTime();

		fillOccupations(dateFrom, dateTo, roomId, room);
		
	}





	private void fillOccupations(Date dateFrom, Date dateTo, long roomId, Room room) throws DaoException {
		Date date = dateFrom;
		while(!date.after(dateTo)){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(room);
			long formId = roomId;
			occupationPK.setReservationForm(this.reservationFormDao.getRecordById(formId));
			
			Occupation 	occupation = new Occupation();
			occupation.setId(occupationPK);
			
			this.occupationDao.createRecord(occupation);
			
			date = DateUtil.getNextDay(date);
		
		}
	}





	private void createRate() throws DaoException {
		
		Double price = new Double(150);
		
		RatePK ratePK = new RatePK();
		ratePK.setSeason(this.seasonDao.getRecordById(1L));
					
		ratePK.setRoomType(this.roomTypeDao.getRecordById(1L));
		Rate rate = this.rateDao.getRecordById(ratePK);
		if(rate == null){
			rate = new Rate();
			rate.setId(ratePK);
			rate.setPrice(price);
			this.rateDao.createRecord(rate);
		}
		
	}


	private void createSeason() throws DaoException {

		Season season1 = this.seasonDao.getRecordById(1L);
		if(season1 == null){
			season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2012, 7, 1).getTime());
			season1.setDateTo(new GregorianCalendar(2012, 7, 31).getTime());
			season1.setName("Agosto 2012");
			this.seasonDao.createRecord(season1);
		}
		
	}



	private void createCustomer() throws DaoException {
		
		Region region = this.regionDao.getRecordById(1L);
		Language language = this.languageDao.getRecordById(1L);
					
		Customer customerFede = this.customerDao.getRecordById(customerPKFede);
		if(customerFede == null){
			customerFede = new Customer();
			customerFede.setId(customerPKFede);
			customerFede.setFirstName("Federico");
			customerFede.setLastName("De Seta");
			customerFede.setDateOfBirth(new GregorianCalendar(1987, 5, 6).getTime());
			customerFede.setRegion(region);
			customerFede.setLanguage(language);
			this.customerDao.createRecord(customerFede);
		}
		
	}
}
