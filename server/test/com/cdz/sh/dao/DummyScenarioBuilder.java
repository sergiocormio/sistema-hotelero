package com.cdz.sh.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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
import com.cdz.sh.model.RoomType;
import com.cdz.sh.model.Season;
import com.cdz.sh.model.StateReservationForm;

/**
 * 
 * Para armar un escenario inicial que cubra todos los tests.
	
		los 5 roomTypes : Costao, Praia, Sol, Alpinas y Lua
		
		1 room por cada roomType
		
		los 7 serviceTypes: 
			transfer aeropuerto ida
			transfer aeropuerto ida y vuelta
			transfer bus ida
			transfer bus ida y vuelta
			toallas
			ropa de cama
			limpieza extra
		
		2 clientes
			Fede
			Sergio
		
		4 reservationForms
			a) de 3 dias, sin cambio de habitacion (lease, a distinto tipo), pre-reserva, cliente Fede
			b) de 6 dias con un cambio de habitacion(lease, a distinto tipo), confirmada, cliente Fede
			c) de 10 dias con 2 cambios de habitacion(lease, a distinto tipo), vencida, cliente Sergio
			d) de dos dias, unica habitacion, cancelada, cliente Sergio
	
		Todas las ocupaciones asociadas a las reservas, son del 1 al 19 de Agosto de 2012
			a) 1 al 3
			b) 1 al 6
			c) 10 al 19
			d) no hay, porque esta vencida (simulo que ya se borro de la tabla "Occupations")
		
		1 temporada (season) del 1 de agosto al 31 del mismo mes, 2012
		
		5 tarifas
			a) season 1 , roomType 1, $150
			a) season 1 , roomType 2, $180
			c) season 1 , roomType 3, $210
			d) season 1 , roomType 4, $240
			e) season 1 , roomType 5, $270
			
 * 
 * 
 * @author fede
 *
 */
public class DummyScenarioBuilder {
	
	private RoomTypeDao roomTypeDao;
	private RoomDao roomDao;
	
	private CustomerDao customerDao;
	
	//these three daos are needed to create customers
	private DocumentTypeDao documentTypeDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	
	private ReservationFormDao reservationFormDao;
	
	private CustomerPK customerPKFede;
	private CustomerPK customerPKSergio;
	
	private OccupationDao occupationDao;
	
	private SeasonDao seasonDao;
	
	private RateDao rateDao;
	
		
	public DummyScenarioBuilder() throws DaoException {
		
		this.roomTypeDao = new RoomTypeDaoImpl();
		this.roomDao = new RoomDaoImpl();
		
		ServiceTypesBuilder roomsAndServiceTypesBuilder = new ServiceTypesBuilder();
		roomsAndServiceTypesBuilder.buildServiceTypes();
		
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		
		this.customerDao = new CustomerDaoImpl();
		
		this.reservationFormDao = new ReservationFormDaoImpl();
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		
		this.customerPKFede = new CustomerPK();
		this.customerPKFede.setDocType(docTypeDNI);
		this.customerPKFede.setIdNumber("33103189");
		
		this.customerPKSergio = new CustomerPK();
		this.customerPKSergio.setDocType(docTypeDNI);
		this.customerPKSergio.setIdNumber("32******");
		
		this.occupationDao = new OccupationDaoImpl();
		
		this.seasonDao = new SeasonDaoImpl();
		this.rateDao = new RateDaoImpl();
	}

	
		
	
	
	public void  createDummyScenario() {
		
		try {
			// Room Types
			Map<Long, RoomType> roomTypes = createRoomTypes();
			
			// Rooms
			createRooms(roomTypes);
	
			//Customers
			createCustomers();
			
			//ReservationForms
			createReservationForms();

			//seasons
			createSeasons();
				
			//rates
			createRates();
			
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		}
		
	}

	
	private void createRooms(Map<Long, RoomType> roomTypes) throws DaoException {
		Room roomOfTypeCostao = this.roomDao.getRecordById(1L);
		if (roomOfTypeCostao == null){
			roomOfTypeCostao = new Room();
			roomOfTypeCostao.setRoomType(roomTypes.get(1L));
			roomOfTypeCostao.setNumber(1);
			this.roomDao.createRecord(roomOfTypeCostao);
		}
		Room roomOfTypePraia = this.roomDao.getRecordById(2L);
		if (roomOfTypePraia == null){
			roomOfTypePraia = new Room();
			roomOfTypePraia.setRoomType(roomTypes.get(2L));
			roomOfTypePraia.setNumber(2);
			this.roomDao.createRecord(roomOfTypePraia);
		}
		Room roomOfTypeSol = this.roomDao.getRecordById(3L);
		if (roomOfTypeSol == null){
			roomOfTypeSol = new Room();
			roomOfTypeSol.setRoomType(roomTypes.get(3L));
			roomOfTypeSol.setNumber(3);
			this.roomDao.createRecord(roomOfTypeSol);
		}
		Room roomOfTypeAlpina = this.roomDao.getRecordById(4L);
		if (roomOfTypeAlpina == null){
			roomOfTypeAlpina = new Room();
			roomOfTypeAlpina.setRoomType(roomTypes.get(4L));
			roomOfTypeAlpina.setNumber(4);
			this.roomDao.createRecord(roomOfTypeAlpina);
		}
		Room roomOfTypeLua = this.roomDao.getRecordById(5L);
		if (roomOfTypeLua == null){
			roomOfTypeLua = new Room();
			roomOfTypeLua.setRoomType(roomTypes.get(5L));
			roomOfTypeLua.setNumber(5);
			this.roomDao.createRecord(roomOfTypeLua);
		}
	}

	private Map<Long, RoomType> createRoomTypes() throws DaoException {
		Map<Long, RoomType> roomTypes = new HashMap<Long, RoomType>();
		
		RoomType roomTypeCostao = this.roomTypeDao.getRecordById(1L);
		if (roomTypeCostao == null){
			roomTypeCostao = new RoomType();
			roomTypeCostao.setName("Costão");
			roomTypeCostao.setDescription(" Térreo, ideal para 4 pessoas ou família.﻿"
										 + "﻿﻿﻿ Sala / cozinha conjugada com sofa cama, quarto com cama casal, patio privativo com churrasqueira."
									 	 + " Oferece café da manhã e roupas de cama. ");
			this.roomTypeDao.createRecord(roomTypeCostao);
		}
		roomTypes.put(1L, roomTypeCostao);
		
		RoomType roomTypePraia = this.roomTypeDao.getRecordById(2L);
		if (roomTypePraia == null){
			roomTypePraia = new RoomType();
			roomTypePraia.setName("Praia");
			roomTypePraia.setDescription("Primeiro andar ideal para 2/3 pessoas, equipado com cama casal ou solteiro, fogão geladeira, utensílios, tv a cabo, ventilador de teto e  cofre."
										 +" Oferece café da manhã e roupas de cama.");
			this.roomTypeDao.createRecord(roomTypePraia);
		}
		roomTypes.put(2L, roomTypePraia);
		
		RoomType roomTypeSol = this.roomTypeDao.getRecordById(3L);
		if (roomTypeSol == null){
			roomTypeSol = new RoomType();
			roomTypeSol.setName("Sol");
			roomTypeSol.setDescription("Primeiro andar ideal para 2 pessoas, equipado com cama casal ou solteiro, fogão geladeira,utensílios, tv a cabo, ventilador de teto e  cofre."
									   +" Oferece café da manhã e roupas de cama.");
			this.roomTypeDao.createRecord(roomTypeSol);
		}
		roomTypes.put(3L, roomTypeSol);
		
		RoomType roomTypeAlpinas = this.roomTypeDao.getRecordById(4L);
		if (roomTypeAlpinas == null){
			roomTypeAlpinas = new RoomType();
			roomTypeAlpinas.setName("Alpinas");
			roomTypeAlpinas.setDescription(" Segundo andar, ideal para 2/3 pessoas."
											+" Com cama casal ou solteiro, forno microondas,"
											+" tv a cabo, ventilador de teto e cofre.﻿"
											+" Oferece roupas de cama e café da manhã. ");
			this.roomTypeDao.createRecord(roomTypeAlpinas);
		}
		roomTypes.put(4L, roomTypeAlpinas);
		
		RoomType roomTypeLua = this.roomTypeDao.getRecordById(5L);
		if (roomTypeLua == null){
			roomTypeLua = new RoomType();
			roomTypeLua.setName("Lua");
			roomTypeLua.setDescription(" Primeiro andar ideal para 2 pessoas (casal), forno microondas utensílios,"
										+" tv a cabo, ventilador de teto e  cofre."
										+" Oferece roupas de cama e café da manhã. ");
			this.roomTypeDao.createRecord(roomTypeLua);
		}
		roomTypes.put(5L, roomTypeLua);
				
		return roomTypes;
	}
	
	
	private void createRates() throws DaoException {
		
		Double price = new Double(150);
		long i = 1;
		
		RatePK ratePK = new RatePK();
		ratePK.setSeason(this.seasonDao.getRecordById(1L));
					
		for(i = 1; i <= 5; i++){
			
			ratePK.setRoomType(this.roomTypeDao.getRecordById(i));
			Rate rate = this.rateDao.getRecordById(ratePK);
			if(rate == null){
				rate = new Rate();
				rate.setId(ratePK);
				rate.setPrice(price);
				this.rateDao.createRecord(rate);
			}
			price += 30;
		}
	}


	private void createSeasons() throws DaoException {

		Season season1 = this.seasonDao.getRecordById(1L);
		if(season1 == null){
			season1 = new Season();
			season1.setDateFrom(new GregorianCalendar(2012, 7, 1).getTime());
			season1.setDateTo(new GregorianCalendar(2012, 7, 31).getTime());
			season1.setName("Agosto 2012");
			this.seasonDao.createRecord(season1);
		}
		
	}





	private void createReservationForms() throws DaoException {
		// del 1 al 3
		ReservationForm reservationForm1 = this.reservationFormDao.getRecordById(1L);
		if(reservationForm1 == null){
			reservationForm1 = new ReservationForm();
			
			GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
			reservationForm1.setDateFrom(calendar.getTime());
			
			calendar.add(GregorianCalendar.DATE, 2);
			
			reservationForm1.setDateTo(calendar.getTime());
	
			reservationForm1.setCustomer(this.customerDao.getRecordById(this.customerPKFede));
			reservationForm1.setState(StateReservationForm.pre_reserva);
			
			this.reservationFormDao.createRecord(reservationForm1);
		}
		
		createOccupationWithoutRoomChange(reservationForm1);
		
		
		// del 1 al 6
		ReservationForm reservationForm2 = this.reservationFormDao.getRecordById(2L);
		if(reservationForm2 == null){
			reservationForm2 = new ReservationForm();
			
			GregorianCalendar calendar = new GregorianCalendar(2012, 7, 1);
			reservationForm2.setDateFrom(calendar.getTime());
			
			calendar.add(GregorianCalendar.DATE, 5);
			
			reservationForm2.setDateTo(calendar.getTime());
	
			reservationForm2.setCustomer(this.customerDao.getRecordById(this.customerPKFede));
			reservationForm2.setState(StateReservationForm.confirmada);
			
			this.reservationFormDao.createRecord(reservationForm2);
		}
		
		createOccupationWithOneRoomChange(reservationForm2);
		
		// del 10 al 19
		ReservationForm reservationForm3 = this.reservationFormDao.getRecordById(3L);
		if(reservationForm3 == null){
			reservationForm3 = new ReservationForm();
			
			GregorianCalendar calendar = new GregorianCalendar(2012, 7, 10);
			reservationForm3.setDateFrom(calendar.getTime());
			
			calendar.add(GregorianCalendar.DATE, 9);
			
			reservationForm3.setDateTo(calendar.getTime());
	
			reservationForm3.setCustomer(this.customerDao.getRecordById(this.customerPKSergio));
			reservationForm3.setState(StateReservationForm.vencida);
			
			this.reservationFormDao.createRecord(reservationForm3);
		}
		
		createOccupationWithTwoRoomChanges(reservationForm3);
		
		// del 20 al 21 
		ReservationForm reservationForm4 = this.reservationFormDao.getRecordById(4L);
		if(reservationForm4 == null){
			reservationForm4 = new ReservationForm();
			
			GregorianCalendar calendar = new GregorianCalendar(2012, 7, 20);
			reservationForm4.setDateFrom(calendar.getTime());
			
			calendar.add(GregorianCalendar.DATE, 1);
			
			reservationForm4.setDateTo(calendar.getTime());
	
			reservationForm4.setCustomer(this.customerDao.getRecordById(this.customerPKSergio));
			reservationForm4.setState(StateReservationForm.cancelada);
			
			this.reservationFormDao.createRecord(reservationForm4);
		}
		
	}

	private void createOccupationWithTwoRoomChanges(ReservationForm reservationForm3) throws DaoException {

		Date date = reservationForm3.getDateFrom();
		// first 3 days on roomtype 3
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(3L));
			occupationPK.setReservationForm(reservationForm3);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		//second 3 days on roomtype 4
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(4L));
			occupationPK.setReservationForm(reservationForm3);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		// remaining 4 days on roomtype 5
		while(!date.after(reservationForm3.getDateTo())){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(5L));
			occupationPK.setReservationForm(reservationForm3);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		
	}
	
	private void createOccupationWithOneRoomChange(ReservationForm reservationForm2) throws DaoException {

		Date date = reservationForm2.getDateFrom();
		// first 3 days on roomtype 2
		for(int i = 0; i < 3; i++){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(2L));
			occupationPK.setReservationForm(reservationForm2);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		// remaining 3 days on roomtype 3
		while(!date.after(reservationForm2.getDateTo())){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(3L));
			occupationPK.setReservationForm(reservationForm2);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
		
	}





	private void createOccupationWithoutRoomChange(ReservationForm reservationForm1) throws DaoException {
		
		Date date = reservationForm1.getDateFrom();
		while(!date.after(reservationForm1.getDateTo())){
			OccupationPK occupationPK = new OccupationPK();
			occupationPK.setDate(date);
			occupationPK.setRoom(this.roomDao.getRecordById(1L));
			occupationPK.setReservationForm(reservationForm1);
			
			Occupation occupation1 = this.occupationDao.getRecordById(occupationPK);
			if(occupation1 == null){
				occupation1 = new Occupation();
				occupation1.setId(occupationPK);
				this.occupationDao.createRecord(occupation1);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, 1);
			date = calendar.getTime();
		}
	}





	private void createCustomers() throws DaoException {
		
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
		
		Customer customerSergio = this.customerDao.getRecordById(customerPKSergio);
		if(customerSergio == null){
			customerSergio = new Customer();
			customerSergio.setId(customerPKSergio);
			customerSergio.setFirstName("Sergio");
			customerSergio.setLastName("Cormio");
			customerSergio.setDateOfBirth(new GregorianCalendar(1986, 5, 27).getTime());
			customerSergio.setRegion(region);
			customerSergio.setLanguage(language);
			this.customerDao.createRecord(customerSergio);
		}
		
	}

			
}
