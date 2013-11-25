package com.antilope.openutils.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间工具类
 * 
 * @author : xshwlx
 * @date :
 * @version : 1.0 20131124
 * @since : 1.0 20131124
 * 
 */
public class DateUtils {

	public static SimpleDateFormat formatNomal = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formatMilli = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss:SSS");
	public static SimpleDateFormat formatNoSeconds = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static SimpleDateFormat formatNoMinutes = new SimpleDateFormat(
			"yyyy-MM-dd HH");
	public static SimpleDateFormat formatDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
	
	public static SimpleDateFormat formatTimeNoSeconds = new SimpleDateFormat(
			"HH:mm");
	
	public String getNomalDate(){
		return formatNomal.format(new Date());
	}
	
	
	
}
