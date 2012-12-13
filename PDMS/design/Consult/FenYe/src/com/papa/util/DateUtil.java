package com.papa.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil
{

	/**
	 * 将字符串(格式为:yyyy-mm-dd)转化为java.util.Date格式
	 */
	public static Calendar parseDate(String simpleDateStr)
	{
		SimpleDateFormat parseTime=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=null;
		try
		{
			Date date=parseTime.parse(simpleDateStr);
			calendar=Calendar.getInstance();
			calendar.setTime(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return calendar;
	}

	private static DateFormat df=DateFormat.getDateInstance(2,
			Locale.SIMPLIFIED_CHINESE);

	public static String getNowDate()
	{
		GregorianCalendar gcNow=new GregorianCalendar();
		Date dNow=gcNow.getTime();
		return df.format(dNow);
	}

	public static String getNowDateTime()
	{
		GregorianCalendar gcNow=new GregorianCalendar();
		Date dNow=gcNow.getTime();
		DateFormat df=DateFormat.getDateTimeInstance(2,2,
				Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}
}
