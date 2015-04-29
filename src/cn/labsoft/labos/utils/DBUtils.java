package cn.labsoft.labos.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageBean;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;

public class DBUtils {
	private static String driver = PropertiesTools.getPropertiesValueByFileAndKey("resources"+File.separator+"jdbc.properties", "datasource.driverClassName");
	private static Connection conn;
	private static PreparedStatement pst;
	private static Statement stmt;
	public static int count = 0;
	public static DataSource dataSource = (DataSource) SystemInstance.getInstance().getBean("dataSource");
	public static Connection getConnect() throws GlobalException  {
		boolean key=true;
		do{
			try {
				if (null != dataSource) {
					conn =DataSourceUtils.getConnection(dataSource);
				}
			} catch (Exception e) {
				key=false;
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
			
		}while(!key);
		return conn;
	}

	static void close() throws GlobalException {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static boolean executSql(String sql) throws GlobalException {
		boolean flag = false;
		conn = getConnect();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			flag = pst.execute();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return flag;
	}

	public static boolean executSqlBatch(String sql) throws GlobalException {
		boolean flag = false;
		conn = getConnect();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.executeBatch();
			flag = true;
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return flag;
	}

	public static List<Object[]> executSqlQuery(String sql) throws GlobalException {
		List<Object[]> objectArrayList = new ArrayList<Object[]>();
		conn = getConnect();
		ResultSet rs = null;
		ResultSetMetaData metaDate = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (null != rs) {
				metaDate = rs.getMetaData();
				while (rs.next()) {
					Object[] objectArray = new Object[metaDate.getColumnCount()];
					for (int i = 1; i <= metaDate.getColumnCount(); i++) {
						objectArray[i - 1] = rs.getObject(i);
					}
					objectArrayList.add(objectArray);
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return objectArrayList;
	}

	public static List<String> executSqlQueryFXC(String sql) throws GlobalException {
		List<String> strList = new ArrayList<String>();
		conn = getConnect();
		ResultSet rs = null;
		ResultSetMetaData metaDate = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (null != rs) {
				metaDate = rs.getMetaData();
				int cols = metaDate.getColumnCount();
				while (rs.next()) {
					StringBuilder sb=new StringBuilder();
					for (int i = 1; i <= cols; i++) {
						sb.append(rs.getString(i));
						sb.append(",");
					}
				  String value=sb.toString().substring(0,sb.toString().length()-1);
				  strList.add(value);
				}

			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
//			if (null != rs) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					//e.printStackTrace();
//				}
//			}
//			close();
		}
		return strList;
	}

	public static PageResult getPageResultBySQL(String sql, int currentPage, String pagerMethod, int pageSize) throws GlobalException {
		PageBean pager = null;
		ResultSet rs = null;
		ResultSetMetaData metaDate = null;
		List<Object[]> resultList = new ArrayList<Object[]>();
		List<String> columnList = new ArrayList<String>();
		int totalRows = 0;
		pager = new PageBean(totalRows, pageSize);

		if (pagerMethod != null && !"".equals(pagerMethod) && !"null".equals(pagerMethod)) {
			pager.refresh(currentPage);
			if (pagerMethod.equals("first")) {
				pager.first();
			} else if (pagerMethod.equals("previous")) {
				pager.previous();
			} else if (pagerMethod.equals("next")) {
				pager.next();
			} else if (pagerMethod.equals("last")) {
				pager.last();
			}
		}
		int i = 0, rowCount = 0, colCount = 0;
		int startRow = pager.getStartRow();
		int endRow = currentPage * pageSize;
		if (startRow < 1)
			startRow = 1;
		if (endRow < startRow)
			endRow = startRow;
		try {
			conn = getConnect();
			if (conn != null) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (null != rs) {
					metaDate = rs.getMetaData();
					colCount = metaDate.getColumnCount();
					for (i = 1; i <= colCount; i++) {
						columnList.add(metaDate.getColumnName(i));
					}
					while (rs.next()) {
						rowCount++;
						if (rowCount < startRow)
							continue;
						if (rowCount > endRow)
							break;
						Object[] objectArray = new Object[colCount];
						for (i = 1; i <= colCount; i++) {
							objectArray[i - 1] = rs.getObject(i);
						}
						resultList.add(objectArray);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
			close();
		}
		PageResult pagerResult = new PageResult();
		pagerResult.setPageBean(pager);
		pagerResult.setResultList(resultList);
		pagerResult.setColumnList(columnList);
		return pagerResult;
	}

	public static void main(String[] args) throws GlobalException {
		long startTime=new Date().getTime();
		try {
			List<String> list=DBUtils.executSqlQueryFXC("select * from IPHONE_MX");
			FileUtils.writeLines(new File("D:\\test\\2.csv"), list);
			long endTime=new Date().getTime();
			System.out.println("时间："+(endTime-startTime));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
//
//		for (int i = 0; i < 1000; i++) {
//			if (i % 10 == 0) {
//				try {
//					System.out.println("休眠开始....." + (i % 10));
//					Thread.sleep(4000);
//					System.out.println("休眠结束....." + (i % 10));
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					//e.printStackTrace();
//				}
//			} else {
//				System.out.println("............");
//			}
//		}
		/*
		 * Thread thread = new Thread(new SelectTable("", new ArrayList<String>()));
		 * thread.start(); while (true) {
		 * 
		 * if("TERMINATED".equals(thread.getState().toString())){
		 * System.out.println(thread.getState().toString()); break; }else{
		 * System.out.println(thread.getState().toString()); } }
		 */}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		DBUtils.count = count;
	}
	
}

