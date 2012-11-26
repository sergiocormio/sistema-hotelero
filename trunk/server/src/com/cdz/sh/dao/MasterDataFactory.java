package com.cdz.sh.dao;

import java.util.HashMap;
import java.util.Map;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.BankDaoImpl;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.dao.impl.MasterDataFlagDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.model.Bank;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.Language;
import com.cdz.sh.model.MasterDataFlag;
import com.cdz.sh.model.Region;

/**
 * Creates all master data. 
 * 
 * @author fede
 *
 */
public class MasterDataFactory {
	
	private MasterDataFlagDao masterDataFlagDao;
	private DocumentTypeDao documentTypeDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	private BankDao bankDao;
	private ExchangeRateDao exchangeRateDao;
	
	public MasterDataFactory() {
	
		this.masterDataFlagDao = new MasterDataFlagDaoImpl();
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.countryDao = new CountryDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		this.bankDao = new BankDaoImpl();
		this.exchangeRateDao = new ExchangeRateDaoImpl();
		
	}

	public void  createMasterData() {
		
		try {
			
			if(!materDataCreated()){
				
				createData();
				
				setMasterDataCreated();
			}
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		}
		
	}

	private void setMasterDataCreated() throws DaoException {
		
		MasterDataFlag masterDataFlag = new MasterDataFlag();
		masterDataFlag.setName("MasterData already created");
		
		this.masterDataFlagDao.createRecord(masterDataFlag);
	}
	

	private boolean materDataCreated() throws DaoException {
		
		if(this.masterDataFlagDao.retrieveAll().isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

	private void createData() throws DaoException {
		// Document types
		createDocumentTypes();
		
		// Countries
		Map<Long, Country> countries = createCountries();
		
		// Regions
		createRegions(countries);
		
		// Languages
		createLanguages();
		
		// Banks
		createBanks();
		
		// Exchange rates 
		createExchangeRates();
	}



//	private void createCleaningTypes() throws DaoException {
//		CleaningType typeBasic = this.cleanigTypeDao.getRecordById(1L);
//		if(typeBasic == null){
//			typeBasic = new CleaningType();
//			typeBasic.setName("Basica");
//			this.cleanigTypeDao.createRecord(typeBasic);
//		}
//		CleaningType typeGral = this.cleanigTypeDao.getRecordById(2L);
//		if(typeGral == null){
//			typeGral = new CleaningType();
//			typeGral.setName("General");
//			this.cleanigTypeDao.createRecord(typeGral);
//		}
//		CleaningType typeBedClothe = this.cleanigTypeDao.getRecordById(3L);
//		if(typeBedClothe == null){
//			typeBedClothe = new CleaningType();
//			typeBedClothe.setName("Cambio de ropa de cama");
//			this.cleanigTypeDao.createRecord(typeBedClothe);
//		}
//	}

	private void createExchangeRates() throws DaoException {
		ExchangeRate excUsd = this.exchangeRateDao.getRecordById("USD");
		if(excUsd == null){
			excUsd = new ExchangeRate();
			excUsd.setId("USD");
			excUsd.setCurrencySymbol("Us$");
			excUsd.setName("Dollar");
			excUsd.setValueAgainstReal(0.47);
			this.exchangeRateDao.createRecord(excUsd);
		}
		ExchangeRate excArs = this.exchangeRateDao.getRecordById("ARS");
		if(excArs == null){
			excArs = new ExchangeRate();
			excArs.setId("ARS");
			excArs.setCurrencySymbol("$");
			excArs.setName("Peso Argentino");
			excArs.setValueAgainstReal(2.35);
			this.exchangeRateDao.createRecord(excArs);
		}
		ExchangeRate excUyu = this.exchangeRateDao.getRecordById("UYU");
		if(excUyu == null){
			excUyu = new ExchangeRate();
			excUyu.setId("UYU");
			excUyu.setCurrencySymbol("$");
			excUyu.setName("Peso Uruguayo");
			excUyu.setValueAgainstReal(9.00);
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
			languagePor.setName("Portugués");
			this.languageDao.createRecord(languagePor);
		}
		Language languageEng = this.languageDao.getRecordById(3L);
		if(languageEng == null){
			languageEng = new Language();
			languageEng.setName("Inglés");
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
			regionInterior.setName("Códoba, Santa Fe, Rosario y Paraná");
			regionInterior.setCountry(countries.get(1L));
			this.regionDao.createRecord(regionInterior);
		}
		
		Region regionSur = this.regionDao.getRecordById(3L);
		if(regionSur == null){
			regionSur = new Region();
			regionSur.setName("Sur");
			regionSur.setCountry(countries.get(1L));
			this.regionDao.createRecord(regionSur);
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
		
		Country countryBo = this.countryDao.getRecordById(2L);
		if(countryBo == null){
			countryBo = new Country();
			countryBo.setName("Bolivia");
			this.countryDao.createRecord(countryBo);
		}
		countries.put(2L, countryBo);
		
		Country countryBr = this.countryDao.getRecordById(3L);
		if(countryBr == null){
			countryBr = new Country();
			countryBr.setName("Brasil");
			this.countryDao.createRecord(countryBr);
		}
		countries.put(3L, countryBr);
		
		Country countryCh = this.countryDao.getRecordById(4L);
		if(countryCh == null){
			countryCh = new Country();
			countryCh.setName("Chile");
			this.countryDao.createRecord(countryCh);
		}
		countries.put(4L, countryCh);
		
		Country countryCo = this.countryDao.getRecordById(5L);
		if(countryCo == null){
			countryCo = new Country();
			countryCo.setName("Colombia");
			this.countryDao.createRecord(countryCo);
		}
		countries.put(5L, countryCo);
		
		Country countryEs = this.countryDao.getRecordById(6L);
		if(countryEs == null){
			countryEs = new Country();
			countryEs.setName("España");
			this.countryDao.createRecord(countryEs);
		}
		countries.put(6L, countryEs);
		
		Country countryUSA = this.countryDao.getRecordById(7L);
		if(countryUSA == null){
			countryUSA = new Country();
			countryUSA.setName("Estados Unidos");
			this.countryDao.createRecord(countryUSA);
		}
		countries.put(7L, countryUSA);
		
		Country countryFr = this.countryDao.getRecordById(8L);
		if(countryFr == null){
			countryFr = new Country();
			countryFr.setName("Francia");
			this.countryDao.createRecord(countryFr);
		}
		countries.put(8L, countryFr);
		
		Country countryIn = this.countryDao.getRecordById(9L);
		if(countryIn == null){
			countryIn = new Country();
			countryIn.setName("Inglaterra");
			this.countryDao.createRecord(countryIn);
		}
		countries.put(9L, countryIn);
		
		Country countryIt = this.countryDao.getRecordById(10L);
		if(countryIt == null){
			countryIt = new Country();
			countryIt.setName("Italia");
			this.countryDao.createRecord(countryIt);
		}
		countries.put(10L, countryIt);
		
		Country countryMe = this.countryDao.getRecordById(11L);
		if(countryMe == null){
			countryMe = new Country();
			countryMe.setName("Mexico");
			this.countryDao.createRecord(countryMe);
		}
		countries.put(11L, countryMe);
		
		Country Pa = this.countryDao.getRecordById(12L);
		if(Pa == null){
			Pa = new Country();
			Pa.setName("Paraguay");
			this.countryDao.createRecord(Pa);
		}
		countries.put(12L, Pa);
		
		Country countryUr = this.countryDao.getRecordById(13L);
		if(countryUr == null){
			countryUr = new Country();
			countryUr.setName("Uruguay");
			this.countryDao.createRecord(countryUr);
		}
		countries.put(13L, countryUr);
		
		Country countryVe = this.countryDao.getRecordById(14L);
		if(countryVe == null){
			countryVe = new Country();
			countryVe.setName("Venezuela");
			this.countryDao.createRecord(countryVe);
		}
		countries.put(14L, countryVe);
		
		return countries;
	}

	private void createDocumentTypes() throws DaoException {
		
		DocumentType docTypeDNI = this.documentTypeDao.getRecordById(1L);
		if(docTypeDNI == null){
			docTypeDNI = new DocumentType();
			docTypeDNI.setName("DNI");
			docTypeDNI.setRegExp("^[0-9]{8}");
			
			this.documentTypeDao.createRecord(docTypeDNI);
		}
		DocumentType docTypeRG = this.documentTypeDao.getRecordById(2L);
		if(docTypeRG == null){
			docTypeRG = new DocumentType();
			docTypeRG.setName("RG");
			docTypeRG.setRegExp("^[0-9][a-z][A-Z]{9}");
			
			this.documentTypeDao.createRecord(docTypeRG);
		}
		DocumentType docTypeSSN = this.documentTypeDao.getRecordById(3L);
		if(docTypeSSN == null){
			docTypeSSN = new DocumentType();
			docTypeSSN.setName("SSN");
			docTypeSSN.setRegExp("^[0-9]{9}");
			
			this.documentTypeDao.createRecord(docTypeSSN);
		}
		
		
	}
	
}
