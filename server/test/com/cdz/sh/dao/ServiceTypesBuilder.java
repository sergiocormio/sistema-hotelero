package com.cdz.sh.dao;

import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.dao.impl.ServiceTypeDaoImpl;
import com.cdz.sh.model.ServiceType;
import com.cdz.sh.model.ServiceTypeModality;

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
			serviceTypeBreakfast.setModality(ServiceTypeModality.PER_PERSON);
			serviceTypeBreakfast.setPrice(5d);
			serviceTypeBreakfast.setIncludedInBudget(true);
			serviceTypeBreakfast.setIncludedInBasePrice(true);
			this.serviceTypeDao.createRecord(serviceTypeBreakfast);
		}
		ServiceType serviceTypeParking = this.serviceTypeDao.getRecordById(2L);
		if (serviceTypeParking == null){
			serviceTypeParking = new ServiceType();
			serviceTypeParking.setName("Estacionamiento");
			serviceTypeParking.setModality(ServiceTypeModality.PER_NIGHT);
			serviceTypeParking.setPrice(10d);
			serviceTypeParking.setIncludedInBudget(true);
			serviceTypeParking.setIncludedInBasePrice(true);
			this.serviceTypeDao.createRecord(serviceTypeParking);
		}
		
		ServiceType serviceTypeAeroIda = this.serviceTypeDao.getRecordById(3L);
		if (serviceTypeAeroIda == null){
			serviceTypeAeroIda = new ServiceType();
			serviceTypeAeroIda.setTransfer(true);
			serviceTypeAeroIda.setName("Ida al Aeropuerto");
			serviceTypeAeroIda.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeAeroIda.setPrice(90d);
			serviceTypeAeroIda.setIncludedInBudget(true);
			serviceTypeAeroIda.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeAeroIda);
		}
		ServiceType serviceTypeAeroBoth = this.serviceTypeDao.getRecordById(4L);
		if (serviceTypeAeroBoth == null){
			serviceTypeAeroBoth = new ServiceType();
			serviceTypeAeroBoth.setTransfer(true);
			serviceTypeAeroBoth.setName("Aeropuerto ida y vuelta");
			serviceTypeAeroBoth.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeAeroBoth.setPrice(170d);
			serviceTypeAeroBoth.setIncludedInBudget(true);
			serviceTypeAeroBoth.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeAeroBoth);
		}
		ServiceType serviceTypeBusIda = this.serviceTypeDao.getRecordById(5L);
		if (serviceTypeBusIda == null){
			serviceTypeBusIda = new ServiceType();
			serviceTypeBusIda.setTransfer(true);
			serviceTypeBusIda.setName("Bus Ida");
			serviceTypeBusIda.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeBusIda.setPrice(80d);
			serviceTypeBusIda.setIncludedInBudget(true);
			serviceTypeBusIda.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeBusIda);
		}
		ServiceType serviceTypeBusBoth = this.serviceTypeDao.getRecordById(6L);
		if (serviceTypeBusBoth == null){
			serviceTypeBusBoth = new ServiceType();
			serviceTypeBusBoth.setTransfer(true);
			serviceTypeBusBoth.setName("Bus ida y vuelta");
			serviceTypeBusBoth.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeBusBoth.setPrice(150d);
			serviceTypeBusBoth.setIncludedInBudget(true);
			serviceTypeBusBoth.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeBusBoth);
		}
		ServiceType serviceTypeTowels = this.serviceTypeDao.getRecordById(7L);
		if (serviceTypeTowels == null){
			serviceTypeTowels = new ServiceType();
			serviceTypeTowels.setName("Toallas");
			serviceTypeTowels.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeTowels.setPrice(10d);
			serviceTypeTowels.setIncludedInBudget(true);
			serviceTypeTowels.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeTowels);
		}
		ServiceType serviceTypeBedClothe = this.serviceTypeDao.getRecordById(8L);
		if (serviceTypeBedClothe == null){
			serviceTypeBedClothe = new ServiceType();
			serviceTypeBedClothe.setName("Ropa de cama");
			serviceTypeBedClothe.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeBedClothe.setPrice(10d);
			serviceTypeBedClothe.setIncludedInBudget(true);
			serviceTypeBedClothe.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeBedClothe);
		}
		ServiceType serviceTypeExtraCleaning = this.serviceTypeDao.getRecordById(9L);
		if (serviceTypeExtraCleaning == null){
			serviceTypeExtraCleaning = new ServiceType();
			serviceTypeExtraCleaning.setName("Limpieza Extra");
			serviceTypeExtraCleaning.setModality(ServiceTypeModality.PER_TIME);
			serviceTypeExtraCleaning.setPrice(15d);
			serviceTypeExtraCleaning.setIncludedInBudget(true);
			serviceTypeExtraCleaning.setIncludedInBasePrice(false);
			this.serviceTypeDao.createRecord(serviceTypeExtraCleaning);
		}
	}
	

}
