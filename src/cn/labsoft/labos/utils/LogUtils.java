package cn.labsoft.labos.utils;

import java.net.URL;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class LogUtils {
	

	private Logger logger;
	private static org.apache.log4j.Logger rootLogger = LogManager
			.getRootLogger();
	/**
	 * log4j的属性文�?
	 */
	private static final String configFile = "resources/log4j.properties";

	static {
		_init();
	}

	/**
	 * 初始化日志配�?
	 */
	private static void _init() {
		/* 查找制定的log4j.properties配置文件 */
		URL url = Thread.currentThread().getContextClassLoader().getResource(
				configFile);
		if (url != null) { // 获取到了log4j.properties配置文件的url路径
			PropertyConfigurator.configureAndWatch(configFile, 1000);
		} else {
			// ("url is null");
			Appender app = new ConsoleAppender(new PatternLayout(
					PatternLayout.TTCC_CONVERSION_PATTERN));
			app.setName("console");
			rootLogger.addAppender(app);
		}
		rootLogger.info(LogUtils.class.getName() + " initialized.");
	}

	/**
	 * 
	 * @param className
	 *            String
	 */
	private LogUtils(String className) {
		logger = Logger.getLogger(className);
	}

	@SuppressWarnings("unchecked")
	private LogUtils(Class cls) {
		logger = Logger.getLogger(cls);
	}

	public static LogUtils getLogger(String className) {
		return new LogUtils(className);
	}

	@SuppressWarnings("unchecked")
	public static LogUtils getLogger(Class cls) {
		return new LogUtils(cls);
	}

	public void loggerReset() {
		LogManager.resetConfiguration();
		_init();
	}

	public void debug(Object message) {
		logger.log(Level.DEBUG, message);
	}

	public void debug(Object message, Throwable t) {
		logger.log(Level.DEBUG, message, t);
	}

	public void info(Object message) {
		logger.log(Level.INFO, message);
	}

	public void info(Object message, Throwable t) {
		logger.log(Level.INFO, message, t);
	}

	public void warn(Object message) {
		logger.log(Level.WARN, message);
	}

	public void warn(Object message, Throwable t) {
		logger.log(Level.WARN, message, t);
	}

	public void error(Object message) {
		logger.log(Level.ERROR, message);
	}

	public void error(Object message, Throwable t) {
		logger.log(Level.ERROR, message, t);
	}

	public void fatal(Object message) {
		logger.log(Level.FATAL, message);
	}

	public void fatal(Object message, Throwable t) {
		logger.log(Level.FATAL, message, t);
	}

}
