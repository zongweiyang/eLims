package cn.labsoft.labos.framework.common.servicefactory;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * <strong>Title : SpringContext </strong>. <br>
 * <strong>Description : 向service层以外提供service业务接口的工厂</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author <br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class SystemInstance {

	private static SystemInstance instance = null;

	private ApplicationContext appContext;

	private static boolean isMutiTenant;

	public SystemInstance() {
	}

	public static boolean isMutiTenant() {
		return isMutiTenant;
	}

	public static void setMutiTenant(boolean isMutiTenant) {
		SystemInstance.isMutiTenant = isMutiTenant;
	}

	public static SystemInstance getInstance() {
		if (instance == null) {
			instance = new SystemInstance();
		}
		return instance;
	}

	public ApplicationContext getAppContext() {
		if(appContext==null){
			String[] location = new String[] {
					"classpath:applicationContext.xml",
					 };
			appContext = new FileSystemXmlApplicationContext(location);
			return appContext;
		}
		else return appContext;
	}


	public Object getBean(String beanName) {
		return getAppContext().getBean(beanName);
	}

	/**
	 * 
	 * @Title:
	 * @Description: TODO
	 * @param
	 * @param clazz
	 *            要获取的Bean对象的类实例
	 * @param
	 * @return
	 * @return 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Object getBean(Class clazz) {
		String beanNameStr = clazz.toString();
		String beanName = beanNameStr.substring(
				beanNameStr.lastIndexOf(".") + 1, beanNameStr.length());
		beanName = (beanName.substring(1, 2).toLowerCase())
				+ beanName.substring(2, beanName.length());
		return getAppContext().getBean(beanName);
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return appContext.getMessage(key, null, Locale.getDefault());
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param args
	 * @return
	 */
	public String getMessage(String key, Object[] args) {
		return appContext.getMessage(key, args, Locale.getDefault());
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Locale locale) {
		return appContext.getMessage(key, null, locale);
	}

	/**
	 * get message by specific key
	 * 
	 * @param key
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Object[] args, Locale locale) {
		return appContext.getMessage(key, args, locale);
	}
}