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
			serviceTypeBreakfast.setModality(ServiceTypeModality.porHabitacion);
			serviceTypeBreakfast.setPrice(10d);
			serviceTypeBreakfast.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBreakfast);
		}
		ServiceType serviceTypeParking = this.serviceTypeDao.getRecordById(2L);
		if (serviceTypeParking == null){
			serviceTypeParking = new ServiceType();
			serviceTypeParking.setName("Estacionamiento");
			serviceTypeParking.setModality(ServiceTypeModality.porNoche);
			serviceTypeParking.setPrice(15d);
			serviceTypeParking.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeParking);
		}
		
		ServiceType serviceTypeAeroIda = this.serviceTypeDao.getRecordById(3L);
		if (serviceTypeAeroIda == null){
			serviceTypeAeroIda = new ServiceType();
			serviceTypeAeroIda.setName("Ida al Aeropuerto");
			serviceTypeAeroIda.setModality(ServiceTypeModality.porVez);
			serviceTypeAeroIda.setPrice(90d);
			serviceTypeAeroIda.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeAeroIda);
		}
		ServiceType serviceTypeAreoBoth = this.serviceTypeDao.getRecordById(4L);
		if (serviceTypeAreoBoth == null){
			serviceTypeAreoBoth = new ServiceType();
			serviceTypeAreoBoth.setName("Aeropuerto ida y vuelta");
			serviceTypeAreoBoth.setModality(ServiceTypeModality.porVez);
			serviceTypeAreoBoth.setPrice(170d);
			serviceTypeAreoBoth.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeAreoBoth);
		}
		ServiceType serviceTypeBusIda = this.serviceTypeDao.getRecordById(5L);
		if (serviceTypeBusIda == null){
			serviceTypeBusIda = new ServiceType();
			serviceTypeBusIda.setName("Bus Ida");
			serviceTypeBusIda.setModality(ServiceTypeModality.porVez);
			serviceTypeBusIda.setPrice(80d);
			serviceTypeBusIda.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBusIda);
		}
		ServiceType serviceTypeBusBoth = this.serviceTypeDao.getRecordById(6L);
		if (serviceTypeBusBoth == null){
			serviceTypeBusBoth = new ServiceType();
			serviceTypeBusBoth.setName("Bus ida y vuelta");
			serviceTypeBusBoth.setModality(ServiceTypeModality.porVez);
			serviceTypeBusBoth.setPrice(150d);
			serviceTypeBusBoth.setAdditionalFixed(false);
			this.serviceTypeDao.createRecord(serviceTypeBusBoth);
		}
		ServiceType serviceTypeTowels = this.serviceTypeDao.getRecordById(7L);
		if (serviceTypeTowels == null){
			serviceTypeTowels = new ServiceType();
			serviceTypeTowels.setName("Toallas");
			serviceTypeTowels.setModality(ServiceTypeModality.porVez);
			serviceTypeTowels.setPrice(10d);
			serviceTypeTowels.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeTowels);
		}
		ServiceType serviceTypeBedClothe = this.serviceTypeDao.getRecordById(8L);
		if (serviceTypeBedClothe == null){
			serviceTypeBedClothe = new ServiceType();
			serviceTypeBedClothe.setName("Ropa de cama");
			serviceTypeBedClothe.setModality(ServiceTypeModality.porVez);
			serviceTypeBedClothe.setPrice(10d);
			serviceTypeBedClothe.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeBedClothe);
		}
		ServiceType serviceTypeExtraCleaning = this.serviceTypeDao.getRecordById(9L);
		if (serviceTypeExtraCleaning == null){
			serviceTypeExtraCleaning = new ServiceType();
			serviceTypeExtraCleaning.setName("Limpieza Extra");
			serviceTypeExtraCleaning.setModality(ServiceTypeModality.porVez);
			serviceTypeExtraCleaning.setPrice(15d);
			serviceTypeExtraCleaning.setAdditionalFixed(true);
			this.serviceTypeDao.createRecord(serviceTypeExtraCleaning);
		}
	}
	

}
