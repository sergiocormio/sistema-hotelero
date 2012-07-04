package com.cdz.sh.util;

import java.util.Date;

public class DateUtil {

	@SuppressWarnings("deprecation")
	public static String dateToStringYYYYmmDD(Date date){
		
		String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();	
		return strDate;
	}
	
}
