package com.cdz.sh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	@SuppressWarnings("deprecation")
	public static String dateToStringYYYYmmDD(Date date){
		
		String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();	
		return strDate;
	}

	public static boolean sameDate(Date date, Date anotherDate) {
		
		if(date.getDate() == anotherDate.getDate() && date.getMonth() == anotherDate.getMonth() &&
		   date.getYear() == anotherDate.getYear()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static Date getNextDay(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	
}
