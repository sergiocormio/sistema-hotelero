package com.cdz.sh.dao;

import java.util.HashMap;
import java.util.Map;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.BankDaoImpl;
import com.cdz.sh.dao.impl.CleaningTypeDaoImpl;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.dao.impl.StateReservationFormDaoImpl;
import com.cdz.sh.model.Bank;
import com.cdz.sh.model.CleaningType;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.Language;
import com.cdz.sh.model.Region;
import com.cdz.sh.model.StateReservationForm;

/**
 * Creates all master data. Master data means classes that do not have a related CRUD UI.
 * 
 * @author fede
 *
 */
public class MasterDataFactory {
	
	private DocumentTypeDao documentTypeDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	private StateReservationFormDao stateReservationFormDao;
	private BankDao bankDao;
	private CleaningTypeDao cleanigTypeDao;
	private ExchangeRateDao exchangeRateDao;
	
	
	public MasterDataFactory() {
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.countryDao = new CountryDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		this.stateReservationFormDao = new StateReservationFormDaoImpl();
		this.bankDao = new BankDaoImpl();
		this.cleanigTypeDao = new CleaningTypeDaoImpl();
		this.exchangeRateDao = new ExchangeRateDaoImpl();
	}

	public void  createMasterData() {
		
		try {
			// Document types
			createDocumentTypes();
			
			// Countries
			Map<Long, Country> countries = createCountries();
			
			// Regions
			createRegions(countries);
			
			// Languages
			createLanguages();
						
			// States for Reservation forms
			createStateReservationForms();
			
			// Banks
			createBanks();
						
			// Cleaning Types 
			createCleaningTypes();
			
			// Exchange rates 
			createExchangeRates();
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		}
		
	}



	private void createCleaningTypes() throws DaoException {
		CleaningType typeBasic = this.cleanigTypeDao.getRecordById(1L);
		if(typeBasic == null){
			typeBasic = new CleaningType();
			typeBasic.setName("Basica");
			this.cleanigTypeDao.createRecord(typeBasic);
		}
		CleaningType typeGral = this.cleanigTypeDao.getRecordById(2L);
		if(typeGral == null){
			typeGral = new CleaningType();
			typeGral.setName("General");
			this.cleanigTypeDao.createRecord(typeGral);
		}
		CleaningType typeBedClothe = this.cleanigTypeDao.getRecordById(3L);
		if(typeBedClothe == null){
			typeBedClothe = new CleaningType();
			typeBedClothe.setName("Cambio de ropa de cama");
			this.cleanigTypeDao.createRecord(typeBedClothe);
		}
	}

	private void createExchangeRates() throws DaoException {
		ExchangeRate excUsd = this.exchangeRateDao.getRecordById("USD");
		if(excUsd == null){
			excUsd = new ExchangeRate();
			excUsd.setId("USD");
			excUsd.setCurrencySymbol("Us$");
			excUsd.setName("Dollar");
			excUsd.setValueAgainstReal(0.45);
			this.exchangeRateDao.createRecord(excUsd);
		}
		ExchangeRate excArs = this.exchangeRateDao.getRecordById("ARS");
		if(excArs == null){
			excArs = new ExchangeRate();
			excArs.setId("ARS");
			excArs.setCurrencySymbol("$");
			excArs.setName("Peso Argentino");
			excArs.setValueAgainstReal(0.45);
			this.exchangeRateDao.createRecord(excArs);
		}
		ExchangeRate excUyu = this.exchangeRateDao.getRecordById("UYU");
		if(excUyu == null){
			excUyu = new ExchangeRate();
			excUyu.setId("UYU");
			excUyu.setCurrencySymbol("$");
			excUyu.setName("Peso Uruguayo");
			excUyu.setValueAgainstReal(0.45);
			this.exchangeRateDao.createRecord(excUyu);
		}
	}

	private void createBanks() throws DaoException {
		Bank bankArg = this.bankDao.getRecordById(1L);
		if(bankArg == null){
			bankArg = new Bank();
			bankArg.setName("Banco Provincia - Argentina");
			this.bankDao.createRecord(bankArg);
		}
		Bank bankBr = this.bankDao.getRecordById(2L);
		if(bankBr == null){
			bankBr = new Bank();
			bankBr.setName("Banco Florianopolos - Brasil");
			this.bankDao.createRecord(bankBr);
		}
	}

	private void createStateReservationForms() throws DaoException {
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
	}

	private void createLanguages() throws DaoException {
		Language languageESP_ARG = this.languageDao.getRecordById(1L);
		if(languageESP_ARG == null){
			languageESP_ARG = new Language();
			languageESP_ARG.setName("Castellano");
			this.languageDao.createRecord(languageESP_ARG);
		}
		Language languagePor = this.languageDao.getRecordById(2L);
		if(languagePor == null){
			languagePor = new Language();
			languagePor.setName("Portugues");
			this.languageDao.createRecord(languagePor);
		}
		Language languageEng = this.languageDao.getRecordById(3L);
		if(languageEng == null){
			languageEng = new Language();
			languageEng.setName("Ingles");
			this.languageDao.createRecord(languageEng);
		}
	}

	private void createRegions(Map<Long,Country> countries)	throws DaoException {
		
		Region regionCentro = this.regionDao.getRecordById(1L);
		if(regionCentro == null){
			regionCentro = new Region();
			regionCentro.setName("Centro");
			regionCentro.setCountry(countries.get(1L));
			this.regionDao.createRecord(regionCentro);
		}
		Region regionInterior = this.regionDao.getRecordById(2L);
		if(regionInterior == null){
			regionInterior = new Region();
			regionInterior.setName("Interior");
			regionInterior.setCountry(countries.get(1L));
			this.regionDao.createRecord(regionInterior);
		}
	}
	

	private Map<Long, Country> createCountries() throws DaoException {
		
		Map<Long, Country> countries = new HashMap<Long, Country>();
		
		Country countryArg = this.countryDao.getRecordById(1L);
		if(countryArg == null){
			countryArg = new Country();
			countryArg.setName("Argentina");
			this.countryDao.createRecord(countryArg);
		}
		countries.put(1L, countryArg);
		
		Country countryBr = this.countryDao.getRecordById(2L);
		if(countryBr == null){
			countryBr = new Country();
			countryBr.setName("Brasil");
			this.countryDao.createRecord(countryBr);
		}
		countries.put(2L, countryBr);
		
		Country countryUSA = this.countryDao.getRecordById(3L);
		if(countryUSA == null){
			countryUSA = new Country();
			countryUSA.setName("Estados Unidos");
			this.countryDao.createRecord(countryUSA);
		}
		countries.put(3L, countryUSA);
		
		Country countryCo = this.countryDao.getRecordById(4L);
		if(countryCo == null){
			countryCo = new Country();
			countryCo.setName("Colombia");
			this.countryDao.createRecord(countryCo);
		}
		countries.put(4L, countryCo);
		/*
		 * create remaining countries
		 */
		return countries;
	}

	private void createDocumentTypes() throws DaoException {
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		if(docTypeDNI == null){
			docTypeDNI = new DocumentType();
			docTypeDNI.setName("DNI");
			docTypeDNI.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeDNI);
		}
		DocumentType docTypeRG = this.documentTypeDao.getRecordById(2L);
		if(docTypeRG == null){
			docTypeRG = new DocumentType();
			docTypeRG.setName("RG");
			docTypeRG.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeRG);
		}
		DocumentType docTypeSSN = this.documentTypeDao.getRecordById(3L);
		if(docTypeSSN == null){
			docTypeSSN = new DocumentType();
			docTypeSSN.setName("SSN");
			docTypeSSN.setRegExp("^[0-9]");
			
			this.documentTypeDao.createRecord(docTypeSSN);
		}
	}
	
}
