package cn.labsoft.labos.utils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.exportsql.BuildFile;
import cn.labsoft.labos.utils.exportsql.CommonUtils;

public class JDBCTools {
	public static Properties properties = new Properties();
	public static String driver;
	public static String url;
	public static String user;
	public static String password;
	public static Map<String, String> TYPESTRMAP = new HashMap<String, String>();
	public static SimpleDateFormat timeStampFormat;
	public static SimpleDateFormat dateFormat;
	public static String sqlModel = "";
	public static int flushSize = 0;
	public static String buildFilePath;

	private static Connection conn = null;

	static {
		try {
			initProperty();
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		initTypeMap();
	}

	private static void initTypeMap() {
		try {
			Class.forName(driver);
			Field[] fs = Types.class.getDeclaredFields();
			for (int i = 0; i < fs.length; ++i) {
				Field f = fs[i];
				String modelStr = properties.getProperty(f.getName());
				if (modelStr != null) {
					int typeId = f.getInt(Types.class);
					TYPESTRMAP.put(String.valueOf(typeId), modelStr);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("初始化信息异常", e);
		}
	}

	private static void initProperty() throws GlobalException {
		InputStream is = null;
		try {
			is = BuildFile.class.getClassLoader().getResourceAsStream(
					"resources"+File.separator+"jdbc.properties");
			properties.load(is);
			driver = properties.getProperty("datasource.driverClassName");
			url = properties.getProperty("datasource.url");
			user = properties.getProperty("datasource.username");
			password = properties.getProperty("datasource.password");
			timeStampFormat = new SimpleDateFormat(properties
					.getProperty("TimeStampFormat"));
			dateFormat = new SimpleDateFormat(properties
					.getProperty("DateFormat"));
			sqlModel = properties.getProperty("SqlModel");
			flushSize = Integer.parseInt(properties.getProperty("FlushSize"));
			buildFilePath = properties.getProperty("BuildFilePath");
		} catch (Exception e) {
			throw new RuntimeException("初始化信息异常", e);
		} finally {
			CommonUtils.closeStream(is);
		}
	}

	public static Connection getConnection() throws GlobalException {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return conn;
	}
	
	public static String addPageForOracle(String sql, int start, int limit)
	  {
	    int end = start + limit;
	    sql = "select * from (select t.*,rownum rn from (" + sql + ") t where rownum <= " + end + ") t1 where t1.rn>" + start;
	    return sql;
	  }

	  public static String addPageForMySql(String sql, int start, int limit)
	  {
	    sql = sql + " limit " + start + "," + limit;
	    return sql; }

	  public String addPageForTeraData(String sql, int start, int limit) {
	    sql = sql + " QUALIFY sum(1) over (rows unbounded preceding) between (" + start + ") and (" + limit + ")";
	    return sql; }

	  public static void closePstmt(PreparedStatement pstmt) throws GlobalException {
	    try {
	      if (pstmt != null)
	        pstmt.close();
	    }
	    catch (Exception e) {
	      //e.printStackTrace(); 
	      throw new GlobalException("" + e.getMessage());}
	  }

	  public static void closeRs(ResultSet rs) throws GlobalException {
	    try {
	      if (rs != null)
	        rs.close();
	    }
	    catch (Exception e) {
	      //e.printStackTrace();
	      throw new GlobalException("" + e.getMessage());
	    }
	  }
}
