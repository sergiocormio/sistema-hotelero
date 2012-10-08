package com.cdz.sh.dao;

import java.util.Date;
import java.util.List;

import com.cdz.sh.dao.crud.CrudDao;
import com.cdz.sh.dao.exception.DaoException;
import com.cdz.sh.model.Occupation;
import com.cdz.sh.model.OccupationPK;
import com.cdz.sh.model.ReservationForm;
import com.cdz.sh.model.Room;
import com.cdz.sh.model.request.CheckAvailabilityRequest;

/**
 * Declares specific functionality for customers access data, in addition to the CRUD methods.
 * 
 * @author fede
 *
 */
public interface OccupationDao extends CrudDao<Occupation, OccupationPK> {

	
	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo) throws DaoException;
	
	public List<Occupation> retrieveConfirmedOccupations(CheckAvailabilityRequest request) throws DaoException;

	public List<Occupation> retrieveOccupations(ReservationForm reservationForm) throws DaoException;

	public List<Occupation> retrieveOverlapedOccupations(Occupation occupation, ReservationForm reservationForm) throws DaoException;

	public List<Occupation> retrieveOccupations(Date dateFrom, Date dateTo, Room room) throws DaoException;


}
