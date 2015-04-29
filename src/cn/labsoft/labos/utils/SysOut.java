package cn.labsoft.labos.utils;

import java.util.Properties;

import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * <strong>Title : SysOut </strong>. <br>
 * <strong>Description : 
	  解决系统中的 System.out.println 问题，
	  在系统部署时可以通过 log4j 配置文件的 
 	  log4j.system.out
  	  log4j.err.out
  	  log4j.system.mothedname
      的值来控制打印语句
	�</strong> <br>
 * <strong>Create on : Sep 23, 2011 10:33:38 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author Carson Liu<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class SysOut{
	/*
	 * 输出普通打印语句（黑色）
	 */
	public static void printOutln(Object obj) throws GlobalException {
		if(true==getIsPrint("log4j.system.out")){
			String str = "";
			if(true==getIsPrint("log4j.system.url")){
				str = new Exception().getStackTrace()[1]+":";
			}
			System.out.println(str+obj);
		}
	}
	/*
	 * 输出ERR打印语句（红色）
	 */
	public static void printErrln(Object obj) throws GlobalException {
		if(true==getIsPrint("log4j.system.err")){
			String str = "";
			if(true==getIsPrint("log4j.system.url")){
				str = new Exception().getStackTrace()[1]+":";
			}
			System.err.println(str+obj);
		}
	}
	private static boolean getIsPrint(String propertiesKey) throws GlobalException{
		boolean key =true;
		String str = null;
		try {
			Properties p = new PropertiesTools().read("log4j.properties");
			str = p.getProperty(propertiesKey) ;
		} catch (Exception e) {
			System.out.println("读取log4j 配置文件有误");
			throw new GlobalException("" + e.getMessage());
		}
		if(null==str){
			System.out.println("未获取到"+propertiesKey+"属性,默认输出 print 语句");
		}else if("FALSE".equals(str.toUpperCase())){
			key = false;
		}
		return key;
	}

}
