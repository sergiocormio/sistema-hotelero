package com.cdz.sh.dao;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.BankDaoImpl;
import com.cdz.sh.dao.impl.CleaningTypeDaoImpl;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.dao.impl.RoomTypeDaoImpl;
import com.cdz.sh.dao.impl.StateReservationFormDaoImpl;
import com.cdz.sh.model.Bank;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.Region;
import com.cdz.sh.model.RoomType;
import com.cdz.sh.model.StateReservationForm;

public class MasterDataFactory {
	
	private DocumentTypeDao documentTypeDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private RoomTypeDao roomTypeDao;
	private StateReservationFormDao stateReservationFormDao;
	private BankDao bankDao;
	private CleaningTypeDao cleanigTypeDao;
	private ExchangeRateDao exchangeRateDao;
	
	
	public MasterDataFactory() {
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.countryDao = new CountryDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.roomTypeDao = new RoomTypeDaoImpl();
		this.stateReservationFormDao = new StateReservationFormDaoImpl();
		this.bankDao = new BankDaoImpl();
		this.cleanigTypeDao = new CleaningTypeDaoImpl();
		this.exchangeRateDao = new ExchangeRateDaoImpl();
	}

	public void  createMasterData() {
		
		try {
			/**
			 * Document types
			 */
			DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
			if(docTypeDNI == null){
				docTypeDNI= new DocumentType();
				docTypeDNI.setName("DNI");
				docTypeDNI.setRegExp("^[0-9]{9}$");
				
				this.documentTypeDao.createRecord(docTypeDNI);
			}
			
			/**
			 * Countries
			 */
			Country country = this.countryDao.getRecordById(1L);
			if(country == null){
				country = new Country();
				country.setName("Argentina");
				this.countryDao.createRecord(country);
			}
			
			/**
			 * Regions
			 */
			Region region = this.regionDao.getRecordById(1L);
			if(region == null){
				region = new Region();
				region.setName("Centro");
				region.setCountry(country);
				this.regionDao.createRecord(region);
			}
			
			/**
			 * Room Types
			 */
			RoomType roomType = this.roomTypeDao.getRecordById(1L);
			if (roomType == null){
				roomType = new RoomType();
				roomType.setName("SOL");
				roomType.setDescription("Esta es el tipo de habitacion SOL");
				this.roomTypeDao.createRecord(roomType);
			}
			
			/**
			 * States for Reservation forms
			 */
			StateReservationForm statePre = this.stateReservationFormDao.getRecordById(1L);
			if(statePre == null){
				statePre = new StateReservationForm();
				statePre.setState("Pre-reserva");
				this.stateReservationFormDao.createRecord(statePre);
			}
			StateReservationForm stateConf = this.stateReservationFormDao.getRecordById(2L);
			if(stateConf == null){
				stateConf = new StateReservationForm();
				stateConf.setState("Confirmada");
				this.stateReservationFormDao.createRecord(stateConf);
			}
			StateReservationForm stateVenc = this.stateReservationFormDao.getRecordById(3L);
			if(stateVenc == null){
				stateVenc = new StateReservationForm();
				stateVenc.setState("Vencida");
				this.stateReservationFormDao.createRecord(stateVenc);
			}
			StateReservationForm stateCanc = this.stateReservationFormDao.getRecordById(4L);
			if(stateCanc == null){
				stateCanc = new StateReservationForm();
				stateCanc.setState("Cancelada");
				this.stateReservationFormDao.createRecord(stateCanc);
			}
			
			/**
			 * Banks
			 */
			Bank bankArg = this.bankDao.getRecordById(1L);
			if(bankArg == null){
				bankArg = new Bank();
				bankArg.setName("Banco Provincia - Argentina");
				this.bankDao.createRecord(bankArg);
			}
			
			/**
			 * Cleaning Types 
			 */
			CleaningType typeBasic = this.cleanigTypeDao.getRecordById(1L);
			if(typeBasic == null){
				typeBasic = new CleaningType();
				typeBasic.setName("Basica");
				this.cleanigTypeDao.createRecord(typeBasic);
			}
			CleaningType typeGral = this.cleanigTypeDao.getRecordById(2L);
			if(typeGral == null){
				typeGral = new CleaningType();
				typeGral.setName("Genral");
				this.cleanigTypeDao.createRecord(typeGral);
			}
			CleaningType typeBedClothe = this.cleanigTypeDao.getRecordById(3L);
			if(typeBedClothe == null){
				typeBedClothe = new CleaningType();
				typeBedClothe.setName("Cambio de ropa de cama");
				this.cleanigTypeDao.createRecord(typeBedClothe);
			}
			
			/**
			 * Exchange rates 
			 */
			ExchangeRate excUsd = this.exchangeRateDao.getRecordById("USD");
			if(excUsd == null){
				excUsd = new ExchangeRate();
				excUsd.setCurrencyCode("USD");
				excUsd.setCurrencySymbol("Us$");
				excUsd.setName("Dollar");
				excUsd.setValueAgainstReal((float)0.45);
				this.exchangeRateDao.createRecord(excUsd);
			}
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		}
		
	}
	
}
