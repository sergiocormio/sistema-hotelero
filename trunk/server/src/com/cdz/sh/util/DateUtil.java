package com.cdz.sh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.cdz.sh.model.Alternative;

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

	public static Date getDateUTC(Date date){
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, c.getTimeZone().getRawOffset() * -1);
		c.setTimeZone(TimeZone.getTimeZone("GMT"));
		return c.getTime();
	}
	
	
	public static Date getDateAboutToFinish(Date date) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
	}

	public static int getDaysDifference(Date dateFrom, Date dateTo) {
		int daysQuantity = 0;
		if(dateFrom == null || dateTo == null){
			return daysQuantity;
		}
		else{
			Date date = dateFrom;
			while(!date.after(dateTo)){
				date = DateUtil.getNextDay(date);
				daysQuantity++;
			}
			return --daysQuantity;
		}
	}
	
	public static int getDaysQuantity(Date dateFrom, Date dateTo) {

		return getDaysDifference(dateFrom, dateTo) + 1; //add last day inclusive
	}

	
	public static Date getMaxDateTo(List<Alternative> alternatives) {
	
		Date maxDateTo = null;
		for (Alternative alternative : alternatives) {
			if(maxDateTo == null){
				maxDateTo = alternative.getDateTo();
			}
			else{
				if(alternative.getDateTo().after(maxDateTo)){
					maxDateTo = alternative.getDateTo();
				}
			}
		}
		return maxDateTo;
	}
	
	public static Date getMinDateFrom(List<Alternative> alternatives) {
		
		Date minDateFrom = null;
		for (Alternative alternative : alternatives) {
			if(minDateFrom == null){
				minDateFrom = alternative.getDateFrom();
			}
			else{
				if(alternative.getDateFrom().before(minDateFrom)){
					minDateFrom = alternative.getDateFrom();
				}
			}
		}
		return minDateFrom;
	}
	
}
