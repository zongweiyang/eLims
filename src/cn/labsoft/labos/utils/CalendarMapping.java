package cn.labsoft.labos.utils;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <strong>Title : CmViewAction </strong>. <br>
 * <strong>Description :日期映射的工具类</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:42:47 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class CalendarMapping {


 
    public   static  String begin = "" ;
    public   static  String end = "" ;
    public   static  String now = new  java.sql.Date( new  Date().getTime()).toString();
    
    public static void main(String[] args) {
        // 今天 
        calcToday(begin,end,now, new  GregorianCalendar());
         // 昨天 
        calcYesterday(begin,end,now, new  GregorianCalendar());
         // 本周 
        calcThisWeek(begin,end,now, new  GregorianCalendar());
         // 上周 
        calcLastWeek(begin,end,now, new  GregorianCalendar());
         // 本月 
        calcThisMonth(begin,end,now, new  GregorianCalendar());
         // 上月 
        calcLastMonth(begin,end,now, new  GregorianCalendar());
	}
 
    public   static   void  calcToday(String begin,String end,String now,GregorianCalendar calendar)  {
       
    	CalendarMapping.begin = now;
    	CalendarMapping.end = now;
  
   } 
   
    public   static   void  calcYesterday(String begin,String end,String now,GregorianCalendar calendar)  {

   
       calendar.add(Calendar.DATE,  - 1 );
       begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
       end = begin;
       CalendarMapping.begin=begin;
       CalendarMapping.end=end;
      
   } 
   
    public   static   void  calcThisWeek(String begin,String end,String now,GregorianCalendar calendar)  {
       end = now;
        int  minus = calendar.get(Calendar.DAY_OF_WEEK) - 2 ;
        if (minus < 0 )  {
           System.out.println( " 本周还没有开始，请查询上周 " );
          
       } else  {
   
       calendar.add(Calendar.DATE,  - minus);
       begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
       CalendarMapping.begin=begin;
       CalendarMapping.end=end;

       } 
   } 
   
    public   static   void  calcLastWeek(String begin,String end,String now,GregorianCalendar calendar)  {
        
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
    	int  minus = calendar.get(Calendar.DAY_OF_WEEK) ;
        System.out.println("minus="+minus);
       
       calendar.add(Calendar.DATE, - minus+1);
       end = new  java.sql.Date(calendar.getTime().getTime()).toString();
       calendar.add(Calendar.DATE, - 6 );
       begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
       CalendarMapping.begin=begin;
       CalendarMapping.end=end;
      
   } 

    
    public   static   void  calcThisMonth(String begin,String end,String now,GregorianCalendar calendar) {
       end = now;
        int  dayOfMonth = calendar.get(Calendar.DATE);
       calendar.add(Calendar.DATE,  - dayOfMonth + 1 );
       begin = new  java.sql.Date(calendar.getTime().getTime()).toString();
       CalendarMapping.begin=begin;
       CalendarMapping.end=end;

   } 
    public   static   void  calcLastMonth(String begin,String end,String now,GregorianCalendar calendar)  {
       
       calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), 1 );
       calendar.add(Calendar.DATE,  - 1 );
       end = new  java.sql.Date(calendar.getTime().getTime()).toString();
       
        int  month = calendar.get(Calendar.MONTH) + 1 ;
       begin = calendar.get(Calendar.YEAR) + "-" + month + "-01" ;
       CalendarMapping.begin=begin;
       CalendarMapping.end=end;        

   } 
} 