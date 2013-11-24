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
 * ʱ�乤����
 *
 * @author         :    xushiheng
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public class DateUtils {
	
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	public static SimpleDateFormat formatNoSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat formatNoMinutes = new SimpleDateFormat("yyyy-MM-dd HH");
	public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
	public static SimpleDateFormat formatTimeNoSeconds = new SimpleDateFormat("HH:mm");

	
	/**
	 * 
	 *  �ƻ�����
	 */
	public static final int DAYLY = 1;
	public static final int WEEKLY = 2;
	public static final int MONTHLY = 3;
	public static final int YEARLY = 4;
	public static final int DAYLY_4 = 5;
	
	
	/**
	 *  �����Ч����ÿ��
	 * @param effectBeginTime
	 * @param effectEndTime
	 * @param startDate
	 * @param endDate
	 * @return 
	 * 
	 */
	public  List<Date[]> getDateListBetweenByDays(Timestamp effectBeginTime,Timestamp effectEndTime,Date startDate,Date endDate,int num){
		
		List<Date[]> dateList = new LinkedList();
		/**
		 *   ��������Чʱ�䷶Χ
		 *   ʧЧʱ�䴦��
		 */
		int i = 0;
		while(endDate.getTime() <= effectEndTime.getTime()){
			
//			System.out.println(endDate + " "+ effectEndTime);
			Date[] date = new Date[2];
			/**
			 * ����㷨
			 */
			if(i > 0){
				startDate = addDays(startDate,num); 
				endDate = addDays(endDate,num); 
			}
			
			/**
			 * ��������Ч�ڵ����ֵ �˳�
			 */
			if(endDate.getTime() > effectEndTime.getTime()) break;
			
			/**
			 * ֻ������Ч�ڷ�Χ�ڵ����ڲ����
			 */
			if(startDate.getTime() >= effectBeginTime.getTime()){
				date[0] = startDate;
				date[1] = endDate;
				dateList.add(date);
			}
			i++;
			if(i>365){
				break;
			}
		}
		
		return dateList;
		
	}
	
	/**
	 *  �����Ч����ÿ���¼���
	 *  һ�µ�ĳ��
	 * @param effectBeginTime
	 * @param effectEndTime
	 * @param startDate
	 * @param endDate
	 * @return 
	 * 
	 */
	public static List<Date[]> getDateListBetweenByMonths(Timestamp effectBeginTime,Timestamp effectEndTime,Date startDate,Date endDate,int num){
		
		
		List<Date[]> dateList = new LinkedList();
		/**
		 *   ��������Чʱ�䷶Χ
		 */
		int i = 0;
		while(endDate.getTime() <= effectEndTime.getTime()){
			
			
			Date[] date = new Date[2];
			
			if(i > 0){
			 startDate = addMonths(startDate,num); 
			 endDate = addMonths(endDate,num);
			}
			
			if(endDate.getTime() > effectEndTime.getTime()) break;
			
			if(startDate.getTime() >= effectBeginTime.getTime()){
			date[0] = startDate;
			date[1] = endDate;
			dateList.add(date);
			}
			
			i++;
			if(i > 12)break;
				
			}
			
		
		return dateList;
		
	}
	
	
	 /**
	  * ��ȡĳ�� ĳ�� �ڼ��� ���ڼ�������
	  * @param year          ���
	  * @param month         �·�
	  * @param weekOfMonth   ����µĵڼ���
	  * @param dayOfWeek     ���ڼ�
	  * @return
	 * @throws ParseException 
	  */
	 public static Date weekDatetoData(int year,int month,int weekOfMonth,int dayOfWeek) throws ParseException{
		 
		  Calendar c = Calendar.getInstance();
		  //����� x�� y�� 1�� �����ڼ�
		  c.set(year, month-1, 1);
		  
		  //���i_week_day =1 �Ļ� ʵ����������  
		  int i_week_day = c.get(Calendar.DAY_OF_WEEK);
		  
		  int sumDay = 0;
		  //dayOfWeek+1 �������ڼ��������� Ϊ 1��
		  if(i_week_day == 1){
		   sumDay = (weekOfMonth-1)*7 + dayOfWeek+1;
		  }else{
		   sumDay = 7-i_week_day+1 +  (weekOfMonth-1)*7 + dayOfWeek +1;
		  }
		  //��1�ŵĻ����ϼ�����Ӧ������
		  c.set(Calendar.DATE,  sumDay);
		  SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  
		  return sf2.parse(sf2.format(c.getTime()));
		  
	 } 
	 
	
	/**
	 * �������ڻ�ȡ�������Ǹ��µĵڼ��ܵ��ܼ�
	 * @param date
	 */
	
	public static int getWeekDay(Date date){
	
		return 0;
		
	}
	
	
	/**
	 * ��ȡ�¼����µĵڼ��ܵ��ܼ���ʱ��
	 * @param date   ��ʵ����
	 * @param monthNum ������
	 * @throws ParseException 
	 */
	public static Date getNextMonthsWeekDay(Date date,int monthNum) throws ParseException{ 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int week = cal.get(Calendar.WEEK_OF_MONTH)-1;//��ȡ�Ǳ��µĵڼ���
		int day = cal.get(Calendar.DAY_OF_WEEK)-1;//�����Ǳ��ܵĵڼ����, 1����������...7����������
		if(day == 0){
			day = 7;
		}
		
		Date nextMonth = addMonths(date, monthNum);
		cal.setTime(nextMonth);
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		
		/**
		 *  ��ȡ�¼����µĵڼ��ܵ��ܼ�������
		 */
		date = weekDatetoData(year, month, week, day);
		
		return date;
		
		
	}
	
	
	
	/**
	 * ��õ�ǰ���� �¼����µ��ܼ�������
	 * @param curMonth
	 * @param week
	 * @param weekday
	 * @param num
	 * @return
	 */
	public static Date getDate(int curMonth,int week,int weekday,int num){
		
		return null;
		
	}
	
	
	
	/**
	 *  �����Ч����ÿ��
	 *  һ�ܵ�ĳ��
	 * @param effectBeginTime
	 * @param effectEndTime
	 * @param startDate
	 * @param endDate
	 * @return 
	 * @throws ParseException 
	 * 
	 */
	public static List<Date[]> getDateListBetweenByMonthsWeek(Timestamp effectBeginTime,Timestamp effectEndTime,Date startDate,Date endDate,int num) throws ParseException{
		
		/**
		 *   ��ʼʱ��ת��Ϊ��ʼ�ܼ�
		 */
		
		
		List<Date[]> dateList = new LinkedList();
		/**
		 *   ��������Чʱ�䷶Χ
		 */
		while(endDate.getTime() <= effectEndTime.getTime()){
			
			Date[] date = new Date[2];
		
			startDate = getNextMonthsWeekDay(startDate,num); 
			endDate = getNextMonthsWeekDay(endDate,num); 
			
			if(endDate.getTime() > effectEndTime.getTime()) break;
			
			date[0] = startDate;
			date[1] = endDate;
			dateList.add(date);

		}
		
		return dateList;
		
	}
	
	/**
	 * ��ԭ������֮�����num��
	 * @param date
	 * @param num
	 * @return
	 */
	private static Date addMonths(Date date, int num) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
		
	}
	
	/**
	 * ��ԭ������֮�����num��
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addDays(Date date,int num){
		 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, num);
		return cal.getTime();
		
	}
	

	
	
	public static Date pareDateTime(String datestr){
		try {
			return format.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
	}
	
	
	
	public static String formatDate(Date date){
	
			return formatDate.format(date);
		
		
	}
	
	public static String formatDateTime(Date date){
		
		return format.format(date);
		
   }
	
	public static String formatDateTimes(Date date){
		
		return formats.format(date);
		
    }  

	
	
	public static Date pareDate(String datestr){
		try {
			return formatDate.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
	}
	
    public static Timestamp date2Timestamp(Date date){
		return new Timestamp(date.getTime());
    	
    }

    
    public static Date timestamp2Date(Timestamp t){
    	return new Date(t.getTime());
    	
    }
    
    
    /**
     * �������ڻ�ȡ���ڼ�
     * @param dt
     * @return
     */
    public   static   String   getWeekOfDate(Date  dt){
        String[]   weekDays={ "������ ", "����һ ", "���ڶ� ", "������ ", "������ ", "������ ", "������ "};
        Calendar   cal=Calendar.getInstance();
        cal.setTime(dt);
       
        int   w=cal.get(Calendar.DAY_OF_WEEK)-1;
        if(w <0)w=0;
        return   weekDays[w];
           
      } 
    
    
    /**
     * 
     * @param dt
     * @return
     */
    public   static   int   getWeekOfDateInt(Date  dt){
        String[]   weekDays={ "7", "1", "2", "3", "4", "5", "6"};
        Calendar   cal=Calendar.getInstance();
        cal.setTime(dt);
        
        int   w=cal.get(Calendar.DAY_OF_WEEK)-1;
        if(w <0)w=0;
        return   Integer.valueOf(weekDays[w]);
           
      } 
    
    
    /**
     * ���ݵ�ǰ���ڻ�ȡ���������������������
     * @param now   ��ǰʱ��
     * @param day   �ܼ� 1 - 7 
     * @return
     */
    public static Date getWeekDate(Date now,int day){
    	
    	Date weekDate = null;
    	int curDay = getWeekOfDateInt(now);
    	int ducDay = day - curDay ;
    	weekDate = addDays(now, ducDay);
    	
		return weekDate;
    	
    }
    
    
    public  static Date[] getStart2End(int periodOfTime,String type,Date input){
    	
    	
    	Date inputNew = null;
    	if(type.equals("day")){
    		inputNew = addDays(input, 1*periodOfTime);
		}else if(type.equals("week")){
			inputNew = addDays(input, 7*periodOfTime);
		}else if(type.equals("fourDay")){
			
		}else if(type.equals("month")){
			inputNew = addMonths(input, 1*periodOfTime);
		}
    	
		return getStart2End(type,inputNew);
    	
    }
    /**
	 *  ������������ʱ�� �������ͼ��㿪ʼ����ʱ��
	 *  �ƻ�����,day:���ռƻ���week:���ܼƻ���fourDay����4��ƻ���month:���¼ƻ�
	 */
		
    public  static Date[] getStart2End(String type,Date input){
		int dataType = 0;
		if(type.equals("day")){
			dataType = DAYLY;
		}else if(type.equals("week")){
			dataType = WEEKLY;
		}else if(type.equals("fourDay")){
			dataType = DAYLY_4;
		}else if(type.equals("month")){
			dataType = MONTHLY;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		
		Date[] date = new Date[2];
		
		Date startDate = input;
		Date endDate = input;
		
//		System.out.println(DateUtils.formatDateTime(startDate) +" "+DateUtils.formatDateTime(endDate));
		switch (dataType) {
		
		case DAYLY:
			//ʱ�䴦��
			 endDate = addDays(input, 1);
			break;
			
      case WEEKLY://�������������ܵĿ�ʼ��β
    	  startDate = getWeekDate(input, 1);
    	  endDate = getWeekDate(input,8);
		  break;
			
      case MONTHLY://�������������µĿ�ʼ��β
    	  startDate = getMonthStart2End(input,cal.get(Calendar.MONTH))[0];
    	  endDate = addDays(getMonthStart2End(input,cal.get(Calendar.MONTH))[1],1);
//    	  return date;
    	  break;
	
      case DAYLY_4://�������ں�����ʱ��
	      endDate = addDays(input, 5);
    	  break;
	
		
		}
		
		date[0] = startDate;
		date[1]= endDate;
		
		return date;
		
	}
	
	/**
	 *  ������������ʱ�� �������ͼ��㿪ʼ����ʱ��
	 */
	public  static Date[] getStart2End(int type,Date input){
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(input);
		
		Date[] date = new Date[2];
		
		Date startDate = input;
		Date endDate = input;
		
		switch (type) {
		
		case DAYLY:
			
			break;
			
      case WEEKLY://�������������ܵĿ�ʼ��β
    	  startDate = getWeekDate(input, 1);
    	  endDate = getWeekDate(input,7);
		  break;
			
      case MONTHLY://�������������µĿ�ʼ��β
    	  startDate = getMonthStart2End(input,cal.get(Calendar.MONTH))[0];
    	  endDate = getMonthStart2End(input,cal.get(Calendar.MONTH))[1];
    	  break;
	
      case DAYLY_4://�������ں�����ʱ��
	      endDate = addDays(input, 4);
    	  break;
	
		
		}
		
		date[0] = startDate;
		date[1]= endDate;
		
		return date;
		
	}
	
	
	public   static   Date[]   getMonthStart2End(Date input,int  month){
		
		Date[]  date = new Date[2];
		Calendar   cal   =   Calendar.getInstance();
		cal.setTime(input);
		cal.set(Calendar.MONTH,   month);
		cal.set(Calendar.DAY_OF_MONTH,   1);
//		System.out.println( "first   day: "   +   );
		date[0] = new   Date(cal.getTimeInMillis());

		cal.add(Calendar.MONTH,   1);
		cal.set(Calendar.DAY_OF_MONTH,   0);
//		System.out.println( "last   day: "   +   new   Date(cal.getTimeInMillis()));
		date[1] = new   Date(cal.getTimeInMillis());
		return date;
		
		}
	
	public void dateDiff(String startTime, String endTime, String format) {
	    //���մ���ĸ�ʽ����һ��simpledateformate����
		    SimpleDateFormat sd = new SimpleDateFormat(format);
		    long nd = 1000*24*60*60;//һ��ĺ�����
		    long nh = 1000*60*60;//һСʱ�ĺ�����
		    long nm = 1000*60;//һ���ӵĺ�����
		    long ns = 1000;//һ���ӵĺ�����
		    long diff;
		    try {
		    //�������ʱ��ĺ���ʱ�����
		    diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		    long day = diff/nd;//����������
		    long hour = diff%nd/nh;//��������Сʱ
		    long min = diff%nd%nh/nm;//�������ٷ���
		    long sec = diff%nd%nh%nm/ns;//����������
		    //������
		    System.out.println("ʱ����"+day+"��"+hour+"Сʱ"+min+"����"+sec+"�롣");
		    } catch (ParseException e) {
		    	e.printStackTrace();
	        }
	    }
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	
	public static double getHalfHoursBetween(Date start,Date end,int interver){
		
		//���մ���ĸ�ʽ����һ��simpledateformate����

	    long nd = 1000*24*60*60;//һ��ĺ�����
	    long nh = 1000*interver*60;//һСʱ�ĺ�����
	    long day = 0;
	    long hour = 0;
	    
	  
	    
	    long diff;
	    try {
	    	
	    	if(start != null){
	    		
	    	 Calendar cal = Calendar.getInstance();
	    	 cal.setTime(start);
	    	 if(cal.get(Calendar.MINUTE) < 30&&cal.get(Calendar.MINUTE)!=0)hour=1;
	    	 cal.setTime(end);
	    	 if(cal.get(Calendar.MINUTE) < 30&&cal.get(Calendar.MINUTE)!=0)hour=0;
//	    	 start = format.parse(format.format(start));
//	   	     end = format.parse(format.format(end));
//	    	}else{
//	    		end = formatTime.parse(formatTime.format(end));
//	    	}
	    	}
//	   	    System.out.println(start);
//	   	    System.out.println(end);
	   	    
	    //�������ʱ��ĺ���ʱ�����
	   	if(start != null)
	     diff = end.getTime() - start.getTime() ;
	   	else
	   	 diff =  end.getTime()+8*60*60*1000;
	   	
	     day = diff/nd;//����������
	     hour += (diff%nd)/nh;//��������Сʱ
	     
	    //������
//	    System.out.println("ʱ����"+day+"��"+hour+"Сʱ");
	    } catch (Exception e) {
	    	e.printStackTrace();
        }
	    
	   
	   
		return day*24+hour;
		
	}
	
	/**
	 * �ַ���ʱ�仯Ϊ��
	 * @param timeString
	 * @return
	 */
	public static long getTimeSeconds(String timeString){
		
		String zero = "00:00:00";
		String zeroDate = formatDate(new Date())+" "+zero;
		if(timeString.split(":").length==1)timeString+=":00:00";
		if(timeString.split(":").length==2)timeString+=":00";
		String transDate = formatDate(new Date())+" "+timeString;
		
		
		return (pareDateTime(transDate).getTime()-pareDateTime(zeroDate).getTime())/1000;
		
	}
	
	public static double getHoursBetween(Date start,Date end){
		
		//���մ���ĸ�ʽ����һ��simpledateformate����
	 
	    long nd = 1000*24*60*60;//һ��ĺ�����
	    long nh = 1000*60*60;//һСʱ�ĺ�����
	    long day = 0;
	    long hour = 0;
	    
	    long diff;
	    try {
	    	
	    	start = format.parse(format.format(start));
	   	    end = format.parse(format.format(end));
	   	    
//	   	    System.out.println(start);
//	   	    System.out.println(end);
	   	    
	    //�������ʱ��ĺ���ʱ�����
	     diff = end.getTime() - start.getTime() ;
	     day = diff/nd;//����������
	     hour = (diff%nd)/nh;//��������Сʱ
	     
	     
	    //������
//	    System.out.println("ʱ����"+day+"��"+hour+"Сʱ");
	    } catch (ParseException e) {
	    	e.printStackTrace();
        }
		return day*24+hour;
		
	}
	
	
	public static double getTimePercent(Date date,int interval){
		
		double percent = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int minute = cal.get(Calendar.MINUTE);
		if(minute >=30)minute-=30;
		percent = (double)minute/interval;
		BigDecimal   b   =   new   BigDecimal(percent);  
		percent   =   b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();  
		return percent;
		
	}
	
	
	
	public static int getLeftCols(String planType,Date fliterDate,Date startDate){
		
		
		int fliterDay = 0;
		int startDay = 0 ;
		
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fliterDate);
		fliterDay = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(startDate);
		startDay = cal.get(Calendar.DAY_OF_MONTH);
		
		int leftCols = 0;
		if(planType.equals("week")){
			leftCols = getWeekOfDateInt(startDate)-1;
		}else if(planType.equals("fourDay")){
			leftCols = startDay - fliterDay;
		}
		return leftCols;
		
	}
	
	public static String formatTime(Date date){
		return formatTime.format(date);
		
	}
	
	
	/**
	 * ������ǰ��ʱ��
	 * @param timevalue
	 * @param timeUnit
	 * @param date
	 * @return
	 */
	public static Date getFrontDate(int timevalue,int timeUnit,Date date){
		
		
		long dateMill = date.getTime();
		
		long millBefore = 0 ;
		
		switch (timeUnit) {
		
		case 1://��
			millBefore = timevalue*24*60*60*1000;
			break;

		case 2://Сʱ
			millBefore = timevalue*60*60*1000;
			break;
			
		case 3://����
			millBefore = timevalue*60*1000;
			break;
		
		case 4://��
			millBefore = timevalue*1000;
			break;
		
		}
		
		return new Date(dateMill - millBefore);
		
	}
    
	
	/**
	 * ��ȡһ�����м���
	 * @param date   
	 * @return
	 */
	public static int getWeekNumOfMonth(Date date){
         
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
	}
	
	public static void main(String[] args) {
//		System.out.println(format.format(addDays(new Date(),1)));
//		System.out.println(getWeekOfDate(new Date()));
		
		
		
//		System.out.println(DateUtils.formatDateTime(getStart2End(1, new Date())[0]));
//		System.out.println(DateUtils.formatDateTime(getStart2End(1, new Date())[1]));
//		System.out.println(getStart2End("month", new Date())[0]);
//		System.out.println(getStart2End("month", new Date())[1]);
//		System.out.println(getMonthStart2End(new Date(), 9)[0]);
//		System.out.println(getMonthStart2End(new Date(), 9)[1]);
		
//		System.out.println(getWeekDate(new Date(),7));
//		try {
//			System.out.println(format.format(getNextMonthsWeekDay(new Date(), 1)));
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(getTimePercent(new Date(),60));

//		System.out.println(getWeekNumOfMonth(new Date()));
//		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		System.out.println(cal.getActualMaximum(Calendar.WEEK_OF_MONTH));
		
		
		try {
//			System.out.println(formatTimeNoSeconds.parse("00:00"));
			System.out.println(getHalfHoursBetween(formatTime.parse("07:10:00"),formatTime.parse("08:00:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("07:10:00"),formatTime.parse("08:10:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("07:30:00"),formatTime.parse("08:00:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("07:30:00"),formatTime.parse("08:45:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("07:45:00"),formatTime.parse("08:40:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("07:45:00"),formatTime.parse("08:50:00"), 30)-1);
			System.out.println(getHalfHoursBetween(formatTime.parse("00:00:00"),formatTime.parse("05:30:00"), 30)-1);
			
			
		
//			System.out.println(getTimeSeconds("03"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
