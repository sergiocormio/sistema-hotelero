package com.cdz.sh.trigger;

import com.cdz.sh.dao.exception.DaoException;

public interface Trigger {
	
	
	public void executeAction() throws DaoException;

}
