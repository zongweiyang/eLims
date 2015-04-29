/**
 * 
 */
package cn.labsoft.labos.framework.common.mutidatasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * <strong>Title : MultiDataSource </strong>. <br>
 * <strong>Description : 多数据源加载对象</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM  </strong>. <br>
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
public class MultiDataSource implements DataSource,ApplicationContextAware {

	private static final Log log = LogFactory.getLog(MultiDataSource.class);
	private ApplicationContext applicationContext = null;
	private DataSource dataSource = null;
	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		return getDataSource().getConnection(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException {
		return getDataSource().getLogWriter();
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException {
		return getDataSource().getLoginTimeout();
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		getDataSource().setLogWriter(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int arg0) throws SQLException {
		getDataSource().setLoginTimeout(arg0);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public DataSource getDataSource(String dataSourceName){
		log.debug("dataSourceName:"+dataSourceName);
		try{
			if(dataSourceName==null||dataSourceName.equals("")){
				return this.dataSource;
			}
			return (DataSource)this.applicationContext.getBean(dataSourceName);
		}catch(NoSuchBeanDefinitionException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource(){
		String sp = SpObserver.getSp();
		return getDataSource(sp);
	}


	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
