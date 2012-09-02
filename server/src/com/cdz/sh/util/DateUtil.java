package com.cdz.sh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static String dateToStringYYYYmmDD(Date date){
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		String strDate = calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);	
		return strDate;
	}

	public static boolean sameDate(Date date, Date anotherDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		GregorianCalendar anotherCalendar = new GregorianCalendar();
		anotherCalendar.setTime(anotherDate);
		
		if(calendar.get(Calendar.DAY_OF_MONTH) == anotherCalendar.get(Calendar.DAY_OF_MONTH) && 
		   calendar.get(Calendar.MONTH) == anotherCalendar.get(Calendar.MONTH) &&
		   calendar.get(Calendar.YEAR) == anotherCalendar.get(Calendar.YEAR)){
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
