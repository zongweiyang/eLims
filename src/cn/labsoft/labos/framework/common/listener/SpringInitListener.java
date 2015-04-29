package cn.labsoft.labos.framework.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;

/**
 * 
 * <strong>Title : SpringInitListener </strong>. <br>
 * <strong>Description : Spring初始化监听</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
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
public class SpringInitListener implements ServletContextListener {
	/**
	 * Spring loader class
	 */
	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private static String OS = System.getProperty("os.name");
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * init
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		if (log.isInfoEnabled()) {
			log
					.info("spring loader bean..begin*****************************************************");
		}
		try {
			  String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			  if(OS.contains("Windows")){
				  path = path.replaceAll("file:/", "");  
			  }
			  path = path.replaceAll("WEB-INF/classes/", "solr");
			  System.setProperty("solr.solr.home", path);
			boolean isMutiTenant = false;
			try{
				//isMutiTenant = Boolean.valueOf(PropertiesTools.getPropertiesValueByFileAndKey("resource.properties", "isMutiTenant"));
				isMutiTenant = false;
			}catch(Exception e){
				isMutiTenant = false;
				throw new GlobalException("" + e.getMessage());
			}
			//System.setProperty(SessionContainer.Session_Container,"cn.labsoft.labos.framework.common.sesseionutils.SessionContainer");
			SystemInstance.setMutiTenant(isMutiTenant);
			//System.setProperty("isMutiTenant",String.valueOf(isMutiTenant));
		} catch (Exception ex) {
			log.error("Load ApplicationContext.xml error " + ex.getMessage(),
					ex);
			
		}
		if (log.isInfoEnabled()) {
			log
					.info("spring loader bean..end******************************************************");
		}
	}

}
