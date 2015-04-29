package cn.labsoft.labos.utils;

import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {

	private C3P0Util() {
	}

	private static ComboPooledDataSource ds = null;

	static {
		try {
			String driver = PropertiesTools.getPropertiesValueByFileAndKey(
					"resources"+File.separator+"jdbc.properties", "datasource.driverClassName");
			String url = PropertiesTools.getPropertiesValueByFileAndKey(
					"resources"+File.separator+"jdbc.properties", "datasource.url");
			String username = PropertiesTools.getPropertiesValueByFileAndKey(
					"resources"+File.separator+"jdbc.properties", "datasource.username");
			String password = PropertiesTools.getPropertiesValueByFileAndKey(
					"resources"+File.separator+"jdbc.properties", "datasource.password");
			String minPoolSize = PropertiesTools
					.getPropertiesValueByFileAndKey("resources"+File.separator+"jdbc.properties",
							"c3p0.minPoolSize");
			String MaxPoolSize = PropertiesTools
					.getPropertiesValueByFileAndKey("resources"+File.separator+"jdbc.properties",
							"c3p0.maxPoolSize");
			String maxIdleTime = PropertiesTools
			.getPropertiesValueByFileAndKey("resources"+File.separator+"jdbc.properties",
					"c3p0.maxIdleTime");
			int minPoolSizeTemp = 2;
			int maxPoolSizeTemp = 50;
			int maxIdleTimeTemp = 600;
			try {
				minPoolSizeTemp = Integer.valueOf(minPoolSize);
				maxPoolSizeTemp = Integer.valueOf(MaxPoolSize);
				maxIdleTimeTemp = Integer.valueOf(maxIdleTime);
			} catch (Exception e) {
				//e.printStackTrace();
				
			}
			ds = new ComboPooledDataSource();
			ds.setDriverClass(driver);
			ds.setJdbcUrl(url);
			ds.setUser(username);
			ds.setPassword(password);
			ds.setMaxPoolSize(maxPoolSizeTemp);
			ds.setMinPoolSize(minPoolSizeTemp);
			ds.setMaxIdleTime(maxIdleTimeTemp);
		} catch (PropertyVetoException e) {
			//e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return con;
	}
}
