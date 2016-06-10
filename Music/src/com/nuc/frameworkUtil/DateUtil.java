package com.nuc.frameworkUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * 日期工具类
 * @author zhaoPC
 *
 */
public class DateUtil {
	
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_DATE_MILLI_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	/**
	 * 按指定格式将字符串转换为日期
	 * @param dateStr 日期串
	 * @param pattern 格式
	 * @return 日期
	 * @throws Exception 异常
	 */
	public static Date str2Date(String dateStr, String pattern) throws Exception{
		if(null==dateStr) {
			return null;
		}
		if(null==pattern) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		return format.parse(dateStr);
	}
	/**
	 * 按指定格式将字符串转换为日期时间
	 * @param dateStr 日期串
	 * @param pattern 格式
	 * @return 日期时间
	 * @throws ParseException 解析异常
	 */
	public static Date str2DateTime(String dateStr, String pattern) throws ParseException{
		if(null==dateStr) {
			return null;
		}
		if(null==pattern) {
			pattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		return format.parse(dateStr);
	}
	/**
	 * 将日期格式化为字符串
	 * @param date 日期
	 * @param pattern 格式
	 * @return 格式化后的日期串
	 */
	public static String date2Str(Date date, String pattern){
		if(null==date) {
			return null;
		}
		if(null==pattern) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		return format.format(date);
	}
	/**
	 * 将日期时间格式化为字符串
	 * @param date 日期
	 * @param pattern 格式
	 * @return 格式化后的日期时间串
	 */
	public static String dateTime2Str(Date date, String pattern){
		if(null==date) {
			return null;
		}
		if(null==pattern) {
			pattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		return format.format(date);
	}
	/**
	 * 取得当前时间戳
	 * @return 当前时间戳
	 */
	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	/**
	 * 取得当前日期
	 * @return 当前日期
	 */
	public static String thisDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar.getTime());
	}
	/**
	 * 对当前日期延后一年
	 * @return 当前日期
	 */
	public static String nextYearDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar.getTime());
	}
	
	/**
	 * 取得当前时间
	 * @return 当前时间
	 */
	public static String thisTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(calendar.getTime());
	}
	/**
	 * 取得当前完整日期时间
	 * @return 当前完整日期时间
	 */
	public static String thisDateTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(calendar.getTime());
	}
	
	/**
	 * 获取某月最后一天的日期数字
	 * @param firstDate 日期
	 * @return 某月最后一天的日期数字
	 * @throws Exception 异常
	 */
	@SuppressWarnings("deprecation")
	public static int getLastDayOfMonth(Date firstDate) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, firstDate.getYear());
		cal.set(Calendar.MONTH, firstDate.getMonth());
		return cal.getActualMaximum(Calendar.DATE);
	}
	/**
	 * 取得今天的最小时间
	 * @return 今天的最小时间
	 */
	public static Date getTodayMin(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	/**
	 * 取得明天的最小时间
	 * @return 明天的最小时间
	 */
	public static Date getTomorrowMin(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		
		return cal.getTime();
	}
	/**
	 * 由指定时间、时间域、数额，计算时间值
	 * @param standard 指定时间
	 * @param type 时间域
	 * @param amount 数额
	 * @return 时间值
	 */
	public static Date genDiffDate(Date standard, int type, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(standard);
		cal.add(type, amount);
		
		return cal.getTime();
	}
	/**
	 * 生成指定时间所在的小时段（清空：分钟、秒、毫秒）
	 * @param standard 指定时间
	 * @return 指定时间所在的小时段
	 */
	public static Date genHourStart(Date standard){
		Calendar cal = Calendar.getInstance();
		cal.setTime(standard);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

    /**
	 * 取得当前天后，减去指定天数后的最小时间
     * @param date 当前日期
     * @param beforeDay 天数
	 * @return 当前天后，减去指定天数后的最小时间
	 */
	public static Date getBeforeDayMin(Date date,int beforeDay){

         return getDayMin(date,-beforeDay);
	}

      /**
	 * 取得当前天后，减去指定天数后的最大时间
     * @param date 当前日期
     * @param beforeDay 天数
	 * @return 当前天后，减去指定天数后的最大时间
	 */
	public static Date getBeforeDayMax(Date date,int beforeDay){

        return getDayMax(date,-beforeDay);
	}

    /**
	 * 取得当前天后，加上指定天数后的最小时间
     * @param date 当前日期
     * @param afterDay 天数
	 * @return 当前天后，加上指定天数后的最小时间
	 */
	public static Date getAfterDayMin(Date date,int afterDay){

		return getDayMin(date,afterDay);
	}

      /**
	 * 取得当前天后，加上指定天数后的最大时间
     * @param date 当前日期
     * @param afterDay 天数
	 * @return 当前天后，加上指定天数后的最大时间
	 */
	public static Date getAfterDayMax(Date date,int afterDay){

		return getDayMax(date,afterDay);
	}
     /**
	 * 取得当前天后，加上指定天数后的最小时间
     * @param date 当前日期
     * @param addDay 天数
	 * @return 当前天后，加上指定天数后的最小时间
	 */
	public static Date getDayMin(Date date,int addDay){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, addDay);

		return cal.getTime();
	}

      /**
	 * 取得当前天 ,加上指定天数后的最大时间
     * @param date 当前日期
     * @param addDay 天数
	 * @return 当前天 ,加上指定天数后的最大时间
	 */
	public static Date getDayMax(Date date,int addDay){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, addDay);

		return cal.getTime();
	}

    /**
    * 取得当前天 ,加上指定月份数后的时间
    * @param date 当前日期
    * @param months 月份数
    * @return 当前天 ,加上指定月份数后的时间
    */
   public static Date addMonths(Date date,int months){
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.MONTH, months);
       return cal.getTime();
   }
   /**
   * 取得当前天 ,加上指定周数后的时间
   * @param date 当前日期
   * @param weeks 周数
   * @return 当前天 ,加上指定周数后的时间
   */
  public static Date addWeeks(Date date,int weeks){
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.WEEK_OF_YEAR, weeks);
      return cal.getTime();
  }
  /**
  * 取得当前天 ,加上指定年数后的时间
  * @param date 当前日期
  * @param years 年数
  * @return 当前天 ,加上指定年数后的时间
  */
 public static Date addYears(Date date,int years){
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     cal.add(Calendar.YEAR, years);
     return cal.getTime();
 }
   
   /**
    * 日期差天数(按照时间比较,如果不足一天会自动补足)
    * @param date1 开始日期
    * @param date2 结束日期
    * @return 两日期差天数
    * @throws Exception 
   */
   public static int diff(Date date1, Date date2) throws Exception {
	   long day = 24L * 60L * 60L * 1000L;
	   String str1=DateUtil.date2Str(date1, "yyyy-MM-dd");
	   date1=DateUtil.str2Date(str1, "yyyy-MM-dd");
	   String str2=DateUtil.date2Str(date2, "yyyy-MM-dd");
	   date2=DateUtil.str2Date(str2, "yyyy-MM-dd");
   	   return (int) (((date2.getTime() - date1.getTime()) /day));
//   		return (int) Math.ceil((((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000d))));
   	}
       
    /**
    * 日期差天数(和当前时间比)
    * @param date 比较日期
    * @return 和当前日期差天数
     * @throws Exception 
    */
   	public static int diff(Date date) throws Exception {
   		return diff(new Date(), date);
   	}
	   	
   	/**
   	 * 按固定格式比较两个日期
   	 * @param date1 日期
   	 * @param date2 日期2
   	 * @param pattern 格式 	默认：yyyy-MM-dd
   	 * @return 比较结果
   	 */
   	public static int compareDate(Date date1, Date date2, String pattern) {
		String d1 = date2Str(date1, pattern);
		String d2 = date2Str(date2, pattern);
		return d1.compareTo(d2);
   	}
	   	
	/**
	 * 按固定格式比较两个日期+时间
	 * @param time1 日期时间
	 * @param time2 日期时间2
	 * @param pattern 格式	默认： yyyy-MM-dd HH:mm:ss
	 * @return 比较结果
	 */
	public static int compareDateTime(Date time1, Date time2, String pattern) {
		String d1 = dateTime2Str(time1, pattern);
		String d2 = dateTime2Str(time2, pattern);
		return d1.compareTo(d2);
	}
	   	
	/**
	 * 将Date类转换为XMLGregorianCalendar
	 * @param date 
	 * @throws DatatypeConfigurationException 转换异常
	 * @return XMLGregorianCalendar 
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date) throws DatatypeConfigurationException{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DatatypeFactory dtf = null;
		dtf = DatatypeFactory.newInstance();	
		XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
		dateType.setYear(cal.get(Calendar.YEAR));
		//由于Calendar.MONTH取值范围为0~11,需要加1
		dateType.setMonth(cal.get(Calendar.MONTH)+1);
		dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
		dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
		dateType.setMinute(cal.get(Calendar.MINUTE));
		dateType.setSecond(cal.get(Calendar.SECOND));
		return dateType;
	} 

	/**
	 * 将XMLGregorianCalendar转换为Date
	 * @param cal 
	 * @return date 
	 */
	public static Date xmlDate2Date(XMLGregorianCalendar cal){
		return cal.toGregorianCalendar().getTime();
	}
	

  	/**
  	 * 判断是否是闰年
  	 * @param date 日期
  	 * @return boolean
  	 */
  	public static boolean isLeapyear(Date date) {
  		GregorianCalendar gregorianCalendar = new GregorianCalendar();
  		gregorianCalendar.setTime(date);
  		return gregorianCalendar.isLeapYear(gregorianCalendar.get(Calendar.YEAR));
  	}
  	/**
  	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
  	 * @param date 传入日期
  	 * @return date 月末日期
  	 * @author sy 
  	 */
  	public static Date getLastDateOfMonth(Date date){
  		Calendar c=Calendar.getInstance();
  		c.setTime(date);
  		return getLastDateOfMonth(c);
  	}
  	/**
  	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
  	 * @param calendar 传入日期
  	 * @return date 月末日期
  	 * @author sy
  	 */
  	public static Date getLastDateOfMonth(Calendar calendar){
  		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
  		return calendar.getTime();
  	}
  	/**
  	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
  	 * @param date 传入日期
  	 * @return boolean true为是
  	 * @author sy 
  	 */
  	public static boolean isLastDateOfMonth(Date date){
  		Calendar c=Calendar.getInstance();
  		c.setTime(date);
  		return isLastDateOfMonth(c);
  	}
 	/**
  	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
  	 * @param date 传入日期
  	 * @return boolean false为不是
  	 * @author sy 
  	 */
  	public static boolean isLastDateOfMonth(Calendar date){
  		if(date.getActualMaximum(Calendar.DAY_OF_MONTH)==date.get(Calendar.DAY_OF_MONTH)){
  			return true;
  		}
  		return false;
  	}
  	
  	/**
  	 * 根据日期得到年份
  	 * @param date 日期
  	 * @return 年份
  	 */
  	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
  	
  	/**
  	 * 根据日期得到月份
  	 * @param date 日期
  	 * @return 月份
  	 */
  	public static int getMonth(Date date) {
  		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
  	}
  	
  	/**
  	 * 根据日期得到日
  	 * @param date 日期
  	 * @return 日
  	 */
  	public static int getDay(Date date) {
  		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
  	}
  	/**
  	 * 
  	 * Description: 判断两个日期是否同月
  	 *
  	 * @param beginDate 开始日期
  	 * @param endDate 结束日期
  	 * @return boolean true:同月,false:不同月
  	 * @Author yaoxin
  	 * Create Date: 2013-12-9 下午6:14:37
  	 */
  	public static boolean sameMonth(Date beginDate,Date endDate){
  		boolean result = false;
  		int calBegin = getCalendarMonth(beginDate);
  		int calEnd = getCalendarMonth(endDate);
  		if(calBegin == calEnd){
  			result = true;
  		}
  		return result;
  	}
  	
  	/**
  	 * 
  	 * Description: 判断两个日期是否同年
  	 *
  	 * @param beginDate 开始日期
  	 * @param endDate 结束日期
  	 * @return boolean true:同月,false:不同月
  	 * @Author yaoxin
  	 * Create Date: 2013-12-9 下午6:14:37
  	 */
  	public static boolean sameYear(Date beginDate,Date endDate){
  		boolean result = false;
  		int calBegin = getCalendarYear(beginDate);
  		int calEnd = getCalendarYear(endDate);
  		if(calBegin == calEnd){
  			result = true;
  		}
  		return result;
  	}
  	
  	/**
  	 * 
  	 * Description: 获得日期的月份
  	 *
  	 * @param date 日期
  	 * @return int 月份
  	 * @Author yaoxin
  	 * Create Date: 2013-12-9 下午6:14:02
  	 */
  	public static int getCalendarMonth(Date date){
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		return cal.get(Calendar.MONTH);
  	}
  	
  	/**
  	 * 
  	 * Description: 获得日期的月份
  	 *
  	 * @param date 日期
  	 * @return int 月份
  	 * @Author yaoxin
  	 * Create Date: 2013-12-9 下午6:14:02
  	 */
  	public static int getCalendarYear(Date date){
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		return cal.get(Calendar.YEAR);
  	}
  	
  	/**
  	 * 
  	 * Description: 参数paramDate的上月最后一天
  	 * 思路：月加1，日设为1，日再减1，即使跨年也可以。
  	 *
  	 * @param paramDate 日期参数
  	 * @return Date 上月最后一天
  	 * @Author yaoxin
  	 * Create Date: 2013-12-10 上午10:22:30
  	 */
  	public static Date getLastDateOfLastMonth(Date paramDate){
  		Calendar c = Calendar.getInstance();
  		c.setTime(paramDate);
  		c.set(Calendar.DATE, 1);
  		c.add(Calendar.DATE, -1);
  		return c.getTime();
  	}
  	
  	/**
  	 * 
  	 * Description:获得当月最后一天 
  	 *
  	 * @param paramDate 日期参数
  	 * @return Date 当月最后一天
  	 * @Author yaoxin
  	 * Create Date: 2015-1-28 下午8:35:06
  	 */
  	public static Date getLastDateOfCurMonth(Date paramDate){
  		Calendar c = Calendar.getInstance();
  		c.setTime(paramDate);
  		c.set(Calendar.DATE, 1);
  		c.add(Calendar.MONTH, 1);
  		c.add(Calendar.DATE, -1);
  		return c.getTime();
  	}
  	
//  	/**
//  	 * 
//  	 * Description: 判断参数是否为导入风险金数据要求的月份:4,7,10,1
//  	 *
//  	 * @param 
//  	 * @return boolean
//  	 * @throws 
//  	 * @Author haolingfeng
//  	 * Create Date: 2014-2-24 下午3:09:48
//  	 */
//  	public static boolean isRightImportRiskFundMonth(int month){
//  		if(month == PhenixConst.FIRST_QUARTER_IMPORT_MONTH || month == PhenixConst.SECOND_QUARTER_IMPORT_MONTH || 
//  				month == PhenixConst.THIRD_QUARTER_IMPORT_MONTH || month == PhenixConst.FOURTH_QUARTER_IMPORT_MONTH){
//  			return true;
//  		}
//  		return false;
//  	}
//  	
//  	/**
//  	 * 
//  	 * Description: 获得披露开始日期以及结束日期
//  	 * 起始日期：上一季度最后月份的26日
//	    终止日期：本季度最后月份的25日
//  	 * @param 
//  	 * @return String
//  	 * @throws 
//  	 * @Author haolingfeng
//  	 * Create Date: 2014-2-24 下午3:37:39
//  	 */
//  	public static String getPublishDate(Date date){
//  		Calendar cal = Calendar.getInstance();
//  		cal.setTime(date);
//  		//披露结束日期：本季度的最后月份的25日
//  		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
//  		cal.set(Calendar.DATE,PhenixConst.PUBLISH_END_DATE);
//  		String endDate = DateUtil.date2Str(cal.getTime(), null);
//  		
//  	   //披露开始日期：上一季度的最后月份的26日
//  		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-3);
//  		cal.set(Calendar.DATE, PhenixConst.PUBLISH_START_DATE);
//  		String startDate = DateUtil.date2Str(cal.getTime(),null);
//  		return startDate+";"+endDate;
//  	}
//  	/**
//  	 * 
//  	 * Description: 获得报告日期15日
//  	 *
//  	 * @param 
//  	 * @return Date
//  	 * @throws 
//  	 * @Author haolingfeng
//  	 * Create Date: 2014-3-18 上午11:11:05
//  	 */
//  	public static Date getReportDate(Calendar cal){
//  		cal.set(Calendar.DATE, Integer.parseInt(PhenixConst.RISKFUND_15));
//  		return cal.getTime();
//  	}
//  	
//  	/**
//  	 * 
//  	 * Description: 各年份季度
//  	 *
//  	 * @param yearSelect 年份
//  	 * @param seasonSelect 季度
//  	 * @return String
//  	 * @throws 
//  	 * @Author lihuiwei
//  	 * Create Date: 2014-3-31 下午2:58:08
//  	 */
//  	public static String yearSeason(String yearSelect, String seasonSelect){
//  		
//  		String yearSeasons = "";
//  		String yearMonth = "";
//  		String yearSeasonOne = yearSelect+"-"+PhenixConst.FIRST_QUARTER_IMPORT_MONTH+"-"+PhenixConst.RISKFUND_15;//第一季度--4月份
//  		String yearSeasonTwo = yearSelect+"-"+PhenixConst.SECOND_QUARTER_IMPORT_MONTH+"-"+PhenixConst.RISKFUND_15;//第二季度--7月份
//  		String yearSeasonThree = yearSelect+"-"+PhenixConst.THIRD_QUARTER_IMPORT_MONTH+"-"+PhenixConst.RISKFUND_15;//第三季度--11月份
//  		String yearSeasonFour = (Long.parseLong(yearSelect)+1)+"-"+PhenixConst.FOURTH_QUARTER_IMPORT_MONTH+"-"
//  								+PhenixConst.RISKFUND_15;//第四季度--1月份
//  		
//  		//季度为空，取得全年首末季度
//  		if(seasonSelect == null || ("").equals(seasonSelect)){
//  			yearSeasons += yearSeasonOne;
//  			yearSeasons += ","+yearSeasonFour;
//  			return yearSeasons;
//  		}
//		//根据季度确定生成报告月份
//		if((PhenixConst.FIRST_QUARTER).equals(seasonSelect)){
//			yearMonth = yearSeasonOne;
//		}
//		if((PhenixConst.SECOND_QUARTER).equals(seasonSelect)){
//			yearMonth = yearSeasonTwo;
//		}
//		if((PhenixConst.THIRD_QUARTER).equals(seasonSelect)){
//			yearMonth = yearSeasonThree;
//		}
//		if((PhenixConst.FOURTH_QUARTER).equals(seasonSelect)){
//			yearMonth = yearSeasonFour;
//		}
//		return yearMonth;
//  	}
  	
  	/**
  	 * 
  	 * Description: 获得全年首末季度报告日期
  	 *
  	 * @param yearSelect 年份
  	 * @param seasonSelect 季度
  	 * @return String
  	 * @throws 
  	 * @Author lihuiwei
  	 * Create Date: 2014-3-31 下午2:56:32
  	 */
//  	public static String getYearReportDate(String yearSelect, String seasonSelect){
//  		String yearSeasons = yearSeason(yearSelect,seasonSelect);
//  		return yearSeasons;
//  	}
  	
  	/**
  	 * 
  	 * Description: 获得报告日期--年份季度
  	 *
  	 * @param yearSelect 年份
  	 * @param seasonSelect 季度
  	 * @return Date
  	 * @throws 
  	 * @Author lihuiwei
  	 * Create Date: 2014-3-31 下午2:55:33
  	 */
//  	public static Date getReportDate(String yearSelect, String seasonSelect){
//  		String yearMonth = yearSeason(yearSelect,seasonSelect);
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		Date date;
//		try {
//			date = sdf.parse(yearMonth);
//			calendar.setTime(date);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//  		return calendar.getTime();
//  	}
  	
}