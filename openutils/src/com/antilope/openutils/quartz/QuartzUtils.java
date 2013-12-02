package com.antilope.openutils.quartz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;

public class QuartzUtils {
	
	@SuppressWarnings("deprecation")
	public static CronTrigger createCronTrigger(Date startTime, int cycleType,
			int valueOfCyc) {

		// Date startTime = tariffcyc.getStartDayOfCyc( );
		String minExp = "";
		String cronExp = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		CronTrigger trigger = null;
		try {
			String startSecond = String.valueOf(cal.get(Calendar.SECOND));
			String startMinute = String.valueOf(cal.get(Calendar.MINUTE));
			String startHour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
			String startDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String startMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String startYear = String.valueOf(cal.get(Calendar.YEAR));
			String weekDay = getWeek(cal.get(Calendar.DAY_OF_WEEK));

			minExp = startSecond + " " + startMinute + " " + startHour;

			// int cycleType = tariffcyc.getMethodOfCyc( );
			// 2009-11-18 17:53:34
			switch (cycleType) {
			case FinaceConstant.CYCLE_WEEKLY:// 周期 按周
				cronExp = minExp + " ? * " + weekDay;
				break;

			case FinaceConstant.CYCLE_MONTHLY:// 按月
				
				cronExp = minExp + " " + startDay + " * ?";
				if (valueOfCyc > 1) {
					cronExp = minExp + " " + startDay + " "+monthStr(cal.get(Calendar.MONTH)+1,valueOfCyc).replaceFirst(",", "")+ " ?";
				}
				break;

			case FinaceConstant.CYCLE_QUARTERLY:// 按季度
				cronExp = minExp + " " + startDay + " " + monthStr(cal.get(Calendar.MONTH)+1,valueOfCyc*3).replaceFirst(",", "") + " ?";
				break;

			case FinaceConstant.CYCLE_HALFYEAR:// 按半年
				cronExp = minExp + " " + startDay + " " + monthStr(cal.get(Calendar.MONTH)+1,valueOfCyc*6).replaceFirst(",", "") + " ?";
				break;

			case FinaceConstant.CYCLE_YEAR:// 按年
				cronExp = minExp + " " + startDay + " " + startMonth + " ? *";
				break;
			case FinaceConstant.CYCLE_TERM:// 按学期
				cronExp = minExp + " " + startDay + " " + monthStr(cal.get(Calendar.MONTH)+1,valueOfCyc*5).replaceFirst(",", "") + " ?";
				break;
			case FinaceConstant.CYCLE_DAYLY:
				// 0 15 10 ? * *
				cronExp = minExp + " ? * *";
//				cronExp = "0 0/1 * * * ?";
				
				break;
			default:
				break;
			}

			trigger = new CronTrigger();
			
			/**
			 * 开始时间设置为下一次要出账的时间
			 * ? 出账规则
			 */
			trigger.setStartTime(new Date(startTime.getTime()+1000));
			CronExpression exp;
//			cronExp = "0 0/20 12-15 * * ?";
//			System.out.println(" 任务调度时间表达式 cronExp :" + cronExp+"\t startTime :"+startTime);
			exp = new CronExpression(cronExp);
			trigger.setCronExpression(exp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trigger;

	}
	
	/**
	 * 
	 * getWeek
	 * 
	 * @param weekDay
	 * @return
	 */
	public static String getWeek(int weekDay) {
		String week = "";
		switch (weekDay) {
		case 1:
			week = "SUN";
			break;

		case 2:
			week = "MON";
			break;

		case 3:
			week = "TUE";
			break;

		case 4:
			week = "WED";
			break;

		case 5:
			week = "THU";
			break;

		case 6:
			week = "FRI";
			break;

		case 7:
			week = "SAT";
			break;

		default:
			break;
		}
		return week;

	}
	
	public static String monthStr(int startMonth,int cycCount){  
//		System.out.println(startMonth +":"+cycCount);
		List<Integer> months = getFrieMonths(startMonth, cycCount);
		String monthStr = "";
		for(int i =0;i<months.size();i++){
			monthStr +=","+months.get(i);
		}
//		System.out.println(monthStr);
		return monthStr;
	}
	
	public static List<Integer> getFrieMonths(int startMonth,int cycCount){
		  List<Integer> monthsList = new ArrayList<Integer>();
		  
		  for(int i = 0;i<12;i++){
			  if(startMonth+cycCount<=12){
				if(!monthsList.contains(startMonth+cycCount))
			    monthsList.add(startMonth+cycCount);
			    startMonth = startMonth+cycCount;
			  }else {
				  if(!monthsList.contains(startMonth+cycCount-12)){
				    monthsList.add(startMonth+cycCount-12);
				    startMonth = startMonth+cycCount-12;
				  }
				  else
					break; 
			  }
				  
		  }
		return monthsList;
	}
	

}
