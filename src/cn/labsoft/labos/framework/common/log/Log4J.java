package cn.labsoft.labos.framework.common.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * 
 * <strong>Title : Log4J </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 2, 2014 6:58:45 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class Log4J {
	static final String LOG4J_PATH = "resources/log4j.properties";
	static Logger logger =  Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
	static Properties properties;
	 static {
		InputStream in = LogThrowingAdvice.class.getClassLoader().getResourceAsStream(LOG4J_PATH);
		properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			Log4J.info("加载 "+LOG4J_PATH+" 出错",e);
			
		}
		
	}
	public static void debug(Object message) {
		logger.log(Level.DEBUG, message);
	}

	public static void debug(Object message, Throwable t) {
		logger.log(Level.DEBUG, message, t);
	}

	public static void info(Object message) {
		logger.log(Level.INFO, message);
	}

	public static void info(Object message, Throwable t) {
		logger.log(Level.INFO, message, t);
	}

	public static void warn(Object message) {
		logger.log(Level.WARN, message);
	}

	public static void warn(Object message, Throwable t) {
		logger.log(Level.WARN, message, t);
	}

	public static void error(Object message) {
		logger.log(Level.ERROR, message);
	}

	public static void error(Object message, Throwable t) {
		logger.log(Level.ERROR, message, t);
	}
	
	public static void err(Object object) {
		if(getValue("log4j.path")) {
			object = new Exception().getStackTrace()[1]+":"+ object;
		}
		if(getValue("log4j.err")) 
			System.err.println(object);
	}
	
	public static void out(Object object) {
		if(getValue("log4j.path")) {
			object = new Exception().getStackTrace()[1]+":"+ object;
		}
		if(getValue("log4j.out")) 
			System.out.println(object);
	}
	
	public static boolean getValue(String key) {
		boolean bool = false;
		if(null!=properties){
			if("TRUE".equalsIgnoreCase(String.valueOf(properties.get(key)))){
				bool = true;
			}
		}
		return bool;
	}
	
}
