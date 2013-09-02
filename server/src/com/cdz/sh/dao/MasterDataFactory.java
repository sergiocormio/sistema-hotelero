package com.cdz.sh.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.BankDaoImpl;
import com.cdz.sh.dao.impl.CountryDaoImpl;
import com.cdz.sh.dao.impl.DocumentTypeDaoImpl;
import com.cdz.sh.dao.impl.EmailTemplateDaoImpl;
import com.cdz.sh.dao.impl.ExchangeRateDaoImpl;
import com.cdz.sh.dao.impl.LanguageDaoImpl;
import com.cdz.sh.dao.impl.MasterDataFlagDaoImpl;
import com.cdz.sh.dao.impl.RegionDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormDaoImpl;
import com.cdz.sh.dao.impl.ReservationFormExpirationDaysDaoImpl;
import com.cdz.sh.model.Bank;
import com.cdz.sh.model.Country;
import com.cdz.sh.model.Customer;
import com.cdz.sh.model.DocumentType;
import com.cdz.sh.model.EmailTemplate;
import com.cdz.sh.model.EmailTemplatePK;
import com.cdz.sh.model.ExchangeRate;
import com.cdz.sh.model.Language;
import com.cdz.sh.model.MasterDataFlag;
import com.cdz.sh.model.Region;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.ReservationFormExpirationDays;
import com.cdz.sh.service.exception.InvalidOperationException;
import com.cdz.sh.service.impl.CustomerServiceImpl;

/**
 * Creates all master data. 
 * 
 * @author fede
 *
 */
public class MasterDataFactory {
	
	private static final int DAYS_TO_EXPIRE = 2;
	
	private MasterDataFlagDao masterDataFlagDao;
	private DocumentTypeDao documentTypeDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private LanguageDao languageDao;
	private BankDao bankDao;
	private ExchangeRateDao exchangeRateDao;
	private ReservationFormExpirationDaysDao expirationDaysDao;
	private EmailTemplateDao emailTemplateDao;
	
	public MasterDataFactory() {
	
		this.masterDataFlagDao = new MasterDataFlagDaoImpl();
		this.documentTypeDao = new DocumentTypeDaoImpl();
		this.countryDao = new CountryDaoImpl();
		this.regionDao = new RegionDaoImpl();
		this.languageDao = new LanguageDaoImpl();
		this.bankDao = new BankDaoImpl();
		this.exchangeRateDao = new ExchangeRateDaoImpl();
		this.expirationDaysDao = new ReservationFormExpirationDaysDaoImpl();
		this.emailTemplateDao = new EmailTemplateDaoImpl();
	}

	public void  createMasterData() {
		
		try {
			
			if(!materDataCreated()){
				
				createData();
				
				//only for testing purposes
				//createDummyData();
				
				setMasterDataCreated();
			}
		}
		catch (DaoException daoException) {
			daoException.printStackTrace();
		} 
//		catch (InvalidOperationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	private void createDummyData() throws DaoException, InvalidOperationException {
		
		for(int i = 1; i <= 5000; i++){
			String iStr = Integer.toString(i);
			String email = iStr + "@" + iStr + ".com";
			
			Customer ci = new Customer();
			ci.setEmail(email);
			
			new CustomerServiceImpl().createRecord(ci);
		}
		for(int i = 1; i <= 5000; i++){
			
			ReservationForm form = new ReservationForm();
			form.setDateFrom(new Date());
			form.setDateTo(new Date());
			form.setCustomer(new CustomerServiceImpl().getRecordById(new Integer(i).longValue()));
			
			new ReservationFormDaoImpl().createRecord(form);
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
		
		// create record for checking reservation forms expiration
		createReserrvationFormExpirationRecord();
		
		createEmailTemplates();
		
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

	private void createEmailTemplates() throws DaoException {

		EmailTemplatePK emailTemplatePK = new EmailTemplatePK();
		emailTemplatePK.setTemplateId(EmailTemplatePK.BUDGET_RESPONSE);
		emailTemplatePK.setLocale("es_AR");
		
		EmailTemplate budgetResponse_esAR = emailTemplateDao.getRecordById(emailTemplatePK);
		if(budgetResponse_esAR == null){
			budgetResponse_esAR = new EmailTemplate();
			
			budgetResponse_esAR.setId(emailTemplatePK);
			//Generic header and footer
			budgetResponse_esAR.setHeader("<HTML><BODY><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Hola,<BR/>gracias por la consulta.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P></BODY></HTML>");
			budgetResponse_esAR.setFooter("<HTML><BODY><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Muchas gracias, esperamos su reserva.</FONT></P></BODY></HTML>");
			//Dell'Osky header and footer
//			budgetResponse_esAR.setHeader("<HTML><BODY><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Hola gracias por la consulta.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><FONT COLOR=\"#0000ff\"><A HREF=\"http://www.dellosky.com/\" TARGET=\"_self\"><U>La posada Dell'Osky</U></A></FONT> está localizada en el balneario GAIVOTAS, barrio playa de INGLESES al norte de la isla distante 150 mtr del mar y próximo a supermercados, farmacia y lavandería. A 600 mtr de la posada encontramos el centro comercial y nocturno de Ingleses donde las opciones de gastronomía y diversión están a tu disposición. La posada ofrece a sus visitantes una linda área social con piscina, parrilla común, lounge, WI-FI y estacionamiento sin costo adicional.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P></BODY></HTML>");
//			budgetResponse_esAR.setFooter("<HTML><BODY><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Para realizar su reserva lea atentamente nuestras disposiciones generales: (Lea con atención para no ocurrir engaños) Las reservas son confirmadas únicamente con un depósito (valor a combinar). Para Argentina el mismo será realizado vía pagamento on line o con tarjeta de credito. para otros países será por transferencia vía WESTER UNION a nuestra cuenta del Banco do Brasil. El saldo deberá ser pagado en la posada por anticipado (sin excepción) para recibir las llaves de la unidad reservada. Caso ocurra algún imprevisto con menos de 30 días antes del check-in y tenga que cancelar la reserva o, teniendo que retirarse antes del final de la estadía por motivos personales.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B>NO efectuamos devolución de valores ya pagos, estos pueden ser acreditados para una futura visita.</B></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Muchas gracias por la consulta, estamos a disposición para otros esclarecimientos.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Recuerde siempre continuar nuestra comunicación sobre este cuerpo de mail.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Gracias y cordiales saludos.</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><FONT COLOR=\"#0000ff\"><A HREF=\"http://www.dellosky.com/#!mapa/c8xe\" TARGET=\"_self\"><U>IMPRIMA mapa de localizaçaõ da POUSADA na Praia de INGLESES</U></A></FONT></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B>----------------------------------------------------------------------------------------------------</B></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B>Entrada de las 14hs a las 20hs - Salida hasta las 11h</B></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B><U>Por favor para INGRESOS fuera de ESTE HORARIO consulte !!</U></B></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Atenciosamente,</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B><U>Dell´Osky Pousada</U></B></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Travessa do Marisco 188</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">(esquina de la Rua do marisco N° 800 entrar a la derecha)</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Florianópolis - Ingleses</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">Telefono :048-3369-0733</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><FONT COLOR=\"#0000ff\"><A HREF=\"http://www.facebook.com/pages/DellOsky-Pousada-FLorianopolis/187620471311951\" TARGET=\"_self\"><U>Facebook</U></A></FONT></FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">GPS-48.39928150177002/-27.43093727472</FONT></P><P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">ESQUINA RUA DO MARISCO 800, ENTRAR A LA DERECHA 80 METROS</FONT></P></BODY></HTML>");
			
			emailTemplateDao.createRecord(budgetResponse_esAR);
		}
		
		emailTemplatePK.setLocale("en_US");
		EmailTemplate budgetResponse_enUS = emailTemplateDao.getRecordById(emailTemplatePK);
		if(budgetResponse_enUS == null){
			budgetResponse_enUS = new EmailTemplate();
			
			budgetResponse_enUS.setId(emailTemplatePK);
			budgetResponse_enUS.setHeader("");
			budgetResponse_enUS.setFooter("");
			
			emailTemplateDao.createRecord(budgetResponse_enUS);
		}
		
		emailTemplatePK.setLocale("pt_BR");
		EmailTemplate budgetResponse_ptBR = emailTemplateDao.getRecordById(emailTemplatePK);
		if(budgetResponse_ptBR == null){
			budgetResponse_ptBR = new EmailTemplate();
			
			budgetResponse_ptBR.setId(emailTemplatePK);
			budgetResponse_ptBR.setHeader("");
			budgetResponse_ptBR.setFooter("");
			
			emailTemplateDao.createRecord(budgetResponse_ptBR);
		}
		
	}

	private void createReserrvationFormExpirationRecord() throws DaoException {
		
		ReservationFormExpirationDays expirationDaysRecord = this.expirationDaysDao.getRecordById(1l);
		if(expirationDaysRecord == null){
			expirationDaysRecord = new ReservationFormExpirationDays(); 
			expirationDaysRecord.setDaysToExpire(DAYS_TO_EXPIRE);
			
			this.expirationDaysDao.createRecord(expirationDaysRecord);
		}
	}

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
		DocumentType docTypePassport = this.documentTypeDao.getRecordById(4L);
		if(docTypePassport == null){
			docTypePassport = new DocumentType();
			docTypePassport.setName("Pasaporte");
			docTypePassport.setRegExp("^[0-9][a-z][A-Z]{9}");
			
			this.documentTypeDao.createRecord(docTypePassport);
		}
		
		
	}
	
}
