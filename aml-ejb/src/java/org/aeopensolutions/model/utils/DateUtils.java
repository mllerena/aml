package org.aeopensolutions.model.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
	
	public static Date convertToDateStartDate(Date dateStart) {
		if( dateStart ==null){
			return null;
		}
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dateStart);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    
	    
	    
	    Date date = cal.getTime();
		TimeZone tz = cal.getTimeZone();


		//Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT 
		long msFromEpochGmt = date.getTime();

		//gives you the current offset in ms from GMT at the current date
		int offsetFromUTC = tz.getOffset(msFromEpochGmt);

		//create a new calendar in GMT timezone, set to this date and add the offset
		Calendar gmtCal = Calendar.getInstance(TimeZone.getDefault());
		gmtCal.setTime(date);
		gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);
	    
	    return gmtCal.getTime();
	}

	public  static Date convertToDateEndDate(Date dateEnd) {
		if( dateEnd ==null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dateEnd);
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    
	    Date date = cal.getTime();
		TimeZone tz = cal.getTimeZone();


		//Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT 
		long msFromEpochGmt = date.getTime();

		//gives you the current offset in ms from GMT at the current date
		int offsetFromUTC = tz.getOffset(msFromEpochGmt);

		//create a new calendar in GMT timezone, set to this date and add the offset
		Calendar gmtCal = Calendar.getInstance(TimeZone.getDefault());
		gmtCal.setTime(date);
		gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);
	    
	    
	    return gmtCal.getTime();
	}

}
