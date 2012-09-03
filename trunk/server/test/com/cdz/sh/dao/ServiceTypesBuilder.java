package com.cdz.sh.dao;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.ServiceType;

/**
 * 
 *		
		los 7 serviceTypes: 
			transfer aeropuerto ida
			transfer aeropuerto ida y vuelta
			transfer bus ida
			transfer bus ida y vuelta
			toallas
			ropa de cama
			limpieza extra
*/
public class ServiceTypesBuilder {
	
	private ServiceTypeDao serviceTypeDao;
	
	public ServiceTypesBuilder() {
		
		this.serviceTypeDao = new ServiceTypeDaoImpl();
	}
	
	public void buildServiceTypes(){
		try {
	
			// Service Types 
			createServiceTypes();
						
		}
		catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	
	private void createServiceTypes() throws DaoException {
		
		ServiceType serviceTypeBreakfast = this.serviceTypeDao.getRecordById(1L);
		if (serviceTypeBreakfast == null){
			serviceTypeBreakfast = new ServiceType();
			serviceTypeBreakfast.setName("Desayuno");
			serviceTypeBreakfast.setPrice(10);
			serviceTypeBreakfast.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBreakfast);
		}
		ServiceType serviceTypeParking = this.serviceTypeDao.getRecordById(2L);
		if (serviceTypeParking == null){
			serviceTypeParking = new ServiceType();
			serviceTypeParking.setName("Estacionamiento");
			serviceTypeParking.setPrice(15);
			serviceTypeParking.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeParking);
		}
		
		ServiceType serviceTypeAeroIda = this.serviceTypeDao.getRecordById(3L);
		if (serviceTypeAeroIda == null){
			serviceTypeAeroIda = new ServiceType();
			serviceTypeAeroIda.setName("Ida al Aeropuerto");
			serviceTypeAeroIda.setPrice(90);
			serviceTypeAeroIda.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeAeroIda);
		}
		ServiceType serviceTypeAreoBoth = this.serviceTypeDao.getRecordById(4L);
		if (serviceTypeAreoBoth == null){
			serviceTypeAreoBoth = new ServiceType();
			serviceTypeAreoBoth.setName("Aeropuerto ida y vuelta");
			serviceTypeAreoBoth.setPrice(170);
			serviceTypeAreoBoth.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeAreoBoth);
		}
		ServiceType serviceTypeBusIda = this.serviceTypeDao.getRecordById(5L);
		if (serviceTypeBusIda == null){
			serviceTypeBusIda = new ServiceType();
			serviceTypeBusIda.setName("Bus Ida");
			serviceTypeBusIda.setPrice(80);
			serviceTypeBusIda.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBusIda);
		}
		ServiceType serviceTypeBusBoth = this.serviceTypeDao.getRecordById(6L);
		if (serviceTypeBusBoth == null){
			serviceTypeBusBoth = new ServiceType();
			serviceTypeBusBoth.setName("Bus ida y vuelta");
			serviceTypeBusBoth.setPrice(150);
			serviceTypeBusBoth.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBusBoth);
		}
		ServiceType serviceTypeTowels = this.serviceTypeDao.getRecordById(7L);
		if (serviceTypeTowels == null){
			serviceTypeTowels = new ServiceType();
			serviceTypeTowels.setName("Toallas");
			serviceTypeTowels.setPrice(10);
			serviceTypeTowels.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeTowels);
		}
		ServiceType serviceTypeBedClothe = this.serviceTypeDao.getRecordById(8L);
		if (serviceTypeBedClothe == null){
			serviceTypeBedClothe = new ServiceType();
			serviceTypeBedClothe.setName("Ropa de cama");
			serviceTypeBedClothe.setPrice(10);
			serviceTypeBedClothe.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeBedClothe);
		}
		ServiceType serviceTypeExtraCleaning = this.serviceTypeDao.getRecordById(9L);
		if (serviceTypeExtraCleaning == null){
			serviceTypeExtraCleaning = new ServiceType();
			serviceTypeExtraCleaning.setName("Limpieza Extra");
			serviceTypeExtraCleaning.setPrice(15);
			serviceTypeExtraCleaning.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeExtraCleaning);
		}
	}
	

}
