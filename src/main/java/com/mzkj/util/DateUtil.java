package com.mzkj.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理
 *
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonths = new SimpleDateFormat("yyyy-MM");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    public static void main(String[] args) {
    	System.out.println(getDiffYearMonths("2019-12","2020-12"));
    }

	public static String getDateAfterMonths(String dateMoth, int i) {
		Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try{   
            date = sdf.parse(dateMoth);//初始日期   
        }catch(Exception e){

        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,i-1);
        String strDate = sdf.format(c.getTime());
        return strDate;
	}
	
	public static String getDateBeforeMonths(String dateMoth, int i) {
		Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try{   
            date = sdf.parse(dateMoth);//初始日期   
        }catch(Exception e){

        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,-i+1);
        String strDate = sdf.format(c.getTime());
        return strDate;
	}

	public static String getYearMonths() {
		return sdfMonths.format(new Date());
		
	}
	
	public static String[] get25YearMonths() {
		String now = sdfMonths.format(new Date());
		String s = getDateAfterMonths(now,-11);
		String months24[] = new String[25];
		for(int i=1;i<=25;i++) {
			String month = getDateAfterMonths(s,i);
			months24[i-1] = month;
		}
		return months24;
	}

	public static Integer getDiffMonths(String date1, String date2) {
		Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        int abs = 0;
        try {
			bef.setTime(sdfMonths.parse(date1));
			aft.setTime(sdfMonths.parse(date2));
			int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
	        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12 + 1;
	        abs = Math.abs(month + result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        
		return abs;
	}
	
	public static Integer getDiffYearMonths(String date1, String date2) {
		Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        int abs = 0;
        try {
			bef.setTime(sdfMonths.parse(date1));
			aft.setTime(sdfMonths.parse(date2));
			int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
	        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
	        abs = month + result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        
		return abs;
	}
	
	/**
     * 格式化几种日期类型
     * 2015年9月-2016年8月 或 2015-9/2016-8 -》 2015-09,2016-08
     * @param yearMonths
     * @return
     */
    public static String formatDate(String yearMonths) {
    	if(StringUtils.isEmpty(yearMonths)) {
    		return null;
    	}
    	if(yearMonths.contains("年")) {
    		yearMonths = yearMonths.replace("-", ",").replace("年", "-").replace("月", "");
    	}else if(yearMonths.contains("/")) {
    		yearMonths = yearMonths.replace("/", ",");
    	}else {
    		yearMonths = null;
    	}
        return yearMonths;
    }
    
    /**
	 * 获取月份
	 * @return
	 */
	public static String findMonthData(int day){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int maxDay=aCalendar.getActualMaximum(Calendar.DATE);
		int nowday =aCalendar.get(Calendar.DAY_OF_MONTH);
		if(day + nowday <= maxDay){
			aCalendar.add(Calendar.MONTH,-1);
		}
		String dateString = sdfMonths.format(aCalendar.getTime());
//		aCalendar.
//		int nowMonth=aCalendar.get(Calendar.MONTH);
		return dateString;
	}
	
}
