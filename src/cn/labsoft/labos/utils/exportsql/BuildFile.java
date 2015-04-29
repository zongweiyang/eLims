package cn.labsoft.labos.utils.exportsql;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.JDBCTools;

public class BuildFile {
	
	public static boolean isFirst = true;
	private static Connection conn = null;
	/** SQL 分页 */
	private static final int limit = 100000;
	/** 文件字节数 */
	private static final int fileTotleLength = 50 * 1024 * 1024;
	/** 数据库内所有表信息 */
	private static List<String> tableListInfo;

	// private static int startRow=0;
	// private static int startF=0;
	// private static int total=0;

	private static int index = 1;

	public static void closeConnection() throws GlobalException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unused")
	private static void getTableInfo() throws GlobalException {
		Statement st = null;
		ResultSet rs = null;
		// 可以的到所有的表信息。
		try {
			conn = JDBCTools.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("show   tables");
			if (null == rs)
				return;
			while (rs.next()) {
				Object obj = rs.getObject(1);
				if (null != obj) {
					tableListInfo.add(String.valueOf(obj));
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	private static void buildFile(String sql, String buildFilePath) throws GlobalException {
		if (StringUtils.isBlank(sql)) {
			return;
		}

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCTools.getConnection();
			String new_sql = "";
			if (sql.indexOf("*") > -1) {
				new_sql = sql.replace("*", " COUNT(*) ");
			}
			ps = conn.prepareStatement(new_sql);
			rs = ps.executeQuery();
			Object counts = null;
			while (rs.next()) {
				counts = rs.getObject(1);
				break;
			}
			if (null != counts) {
				Integer count = Integer.valueOf(String.valueOf(counts));
				if (count > 100000) {
					if (count > 500000) {
						count = 500000;
					}
					//String lareSql = "";
					Integer newCount = count / limit;
					String tempSQL = sql;
					int result = 0;
					for (int f = 0; f < newCount; f++) {
						if (f == newCount - 1) {
							result = limit * (f + 1);
						}
						tempSQL = sql + " LIMIT " + f * limit + " , " + limit
								* (f + 1) + " ";
						System.out.println(tempSQL);
						buildFile_each(tempSQL, buildFilePath);
					}
					if (result != count) {
						tempSQL = sql + " LIMIT " + result + " , " + count
								+ " ";
						System.out.println(tempSQL);
						buildFile_each(tempSQL, buildFilePath);
					}
				} else {
					buildFile_temp(sql, buildFilePath);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			JDBCTools.closeRs(rs);
			JDBCTools.closePstmt(ps);
		}
	}

	@SuppressWarnings("unchecked")
	private static void buildFile_each(String sql, String buildFilePath) throws GlobalException {
		com.mysql.jdbc.Statement st = null;
		ResultSet rs = null;
		try {
			st = (com.mysql.jdbc.Statement) conn.createStatement();
			st.setFetchSize(1);
			//Date d1 = new Date();
			rs = st.executeQuery(sql);
			if (rs == null)
				return;
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalCol = rsmd.getColumnCount();
			String tableName = rsmd.getTableName(1);
			if (JDBCTools.sqlModel.indexOf("?") == -1) {
				throw new RuntimeException("改程序是以?号作为分割的,所以模板中必须有?号");
			}
			String baseString = JDBCTools.sqlModel.substring(0, JDBCTools.sqlModel.indexOf("?"))
					.replaceAll("#tableName#", tableName);
			String endString = JDBCTools.sqlModel.substring(JDBCTools.sqlModel.indexOf("?") + 1);
			List typeList = new ArrayList();
			for (int i = 0; i < totalCol; ++i) {
				typeList.add(Integer.valueOf(rsmd.getColumnType(i + 1)));
			}
			StringBuffer insertSql = new StringBuffer();
			int sortIndex = 1;
			while (rs.next()) {
				insertSql.append(baseString);
				for (int i = 0; i < totalCol; ++i) {
					Object value = rs.getObject(i + 1);
					if (value != null) {
						int type = ((Integer) typeList.get(i)).intValue();
						if (type == 91)
							value = JDBCTools.dateFormat.format(value);
						else if (type == 93) {
							value = JDBCTools.timeStampFormat.format(value);
						}
					}

					String modelStr = JDBCTools.TYPESTRMAP.get(typeList.get(i));
					if ((modelStr != null) && (value != null)) {
						value = modelStr.replaceAll("\\?", value.toString());
					}
					if (null != value)
						value = "'" + value + "'";
					insertSql.append(value);
					if (i != totalCol - 1) {
						insertSql.append(",");
					}
				}
				insertSql.append(endString);
				if (insertSql.length() >= JDBCTools.flushSize) {
					writeToFile(insertSql, tableName, buildFilePath);
					insertSql = new StringBuffer();
				}
				if (sortIndex % 10 == 0) {
					writeToFile(new StringBuffer("\r\n"), tableName,
							buildFilePath);
				}
				sortIndex++;
			}
			writeToFile(insertSql, tableName, buildFilePath);
			if (StringUtils.isNotBlank(insertSql.toString())) {
				writeToFile(new StringBuffer("\r\n"), tableName, buildFilePath);
				writeToFile(new StringBuffer("\r\n"), tableName, buildFilePath);
			}
		} catch (Exception e) {
			// //e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (null != st)
				try {
					st.close();
				} catch (SQLException e) {
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
				JDBCTools.closeRs(rs);
		}
	}

	@SuppressWarnings("unchecked")
	private static void buildFile_temp(String sql, String buildFilePath) throws GlobalException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			sql.split(" ");
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			int totalCol = rsmd.getColumnCount();
			String tableName = rsmd.getTableName(1);

			if (JDBCTools.sqlModel.indexOf("?") == -1) {
				throw new RuntimeException("改程序是以?号作为分割的,所以模板中必须有?号");
			}
			String baseString = JDBCTools.sqlModel.substring(0, JDBCTools.sqlModel.indexOf("?"))
					.replaceAll("#tableName#", tableName);
			String endString = JDBCTools.sqlModel.substring(JDBCTools.sqlModel.indexOf("?") + 1);
			List typeList = new ArrayList();
			for (int i = 0; i < totalCol; ++i) {
				typeList.add(Integer.valueOf(rsmd.getColumnType(i + 1)));
			}
			StringBuffer insertSql = new StringBuffer();
			int sortIndex = 1;
			while (rs.next()) {
				insertSql.append(baseString);
				for (int i = 0; i < totalCol; ++i) {
					Object value = rs.getObject(i + 1);
					if (value != null) {
						int type = ((Integer) typeList.get(i)).intValue();
						if (type == 91)
							value = JDBCTools.dateFormat.format(value);
						else if (type == 93) {
							value = JDBCTools.timeStampFormat.format(value);
						}
					}

					String modelStr = JDBCTools.TYPESTRMAP.get(typeList.get(i));
					if ((modelStr != null) && (value != null)) {
						value = modelStr.replaceAll("\\?", value.toString());
					}
					if (null != value)
						value = "'" + value.toString().replace("'", "\\'")
								+ "'";
					insertSql.append(value);
					if (i != totalCol - 1) {
						insertSql.append(",");
					}
				}
				insertSql.append(endString);
				if (insertSql.length() >= JDBCTools.flushSize) {
					writeToFile(insertSql, tableName, buildFilePath);
					insertSql = new StringBuffer();
				}
				if (sortIndex % 10 == 0) {
					writeToFile(new StringBuffer("\r\n"), tableName,
							buildFilePath);
				}
				sortIndex++;
			}
			writeToFile(insertSql, tableName, buildFilePath);
			if (StringUtils.isNotBlank(insertSql.toString())) {
				writeToFile(new StringBuffer("\r\n"), tableName, buildFilePath);
				writeToFile(new StringBuffer("\r\n"), tableName, buildFilePath);
			}
			// writeToFile(new StringBuffer("\r\n"), tableName);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			JDBCTools.closeRs(rs);
			JDBCTools.closePstmt(ps);
			// DataBaseHelper.closeConn(conn);
		}
	}

	private static void writeToFile(StringBuffer sql, String tableName,
			String buildFilePath) throws GlobalException {
		tableName = "export";
		FileOutputStream fos = null;
		try {
			File file = new File(buildFilePath + "/" + tableName + "-" + index
					+ ".sql");
			if (isFirst) {
				if (file.exists()) {
					file.delete();
				}
				isFirst = false;
			}
			long length = file.length();
			// 小文件不分割
			if (length > fileTotleLength) {
				file = new File(buildFilePath + "/" + tableName + "-" + index
						+ ".sql");
				length = file.length();
				if (length > fileTotleLength) {
					index++;
					// System.out.println("index:"+index);
					file = new File(buildFilePath + "/" + tableName + "-"
							+ index + ".sql");
					if (file.exists()) {
						file.delete();
					}
				}
			}
			fos = new FileOutputStream(file, true);
			fos.write(sql.toString().getBytes());
			fos.flush();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			CommonUtils.closeStream(fos);
		}
	}

	public static void createSQLFile(String sql, String buildFilePath) throws GlobalException {
		Date d1 = new Date();
		BuildFile.index = 1;
		System.out.println("请输入要执行的sql");
		System.out.println("用法举例:select * from user");
		try {
			System.out.println("Starting...............");
			if (StringUtils.isNotBlank(sql)) {
				buildFile(sql, buildFilePath);
			} else {
				// 获取表信息
				String[] allTableNameStrArray = ReorgAllTable
						.getSortedTableNameForInsert();
				for (String tableInfo : allTableNameStrArray) {
					buildFile("select * from " + tableInfo, buildFilePath);
				}
			}
			System.out.println("Success!");
		} catch (Exception e) {
			System.out.println("Error!");
			throw new GlobalException("" + e.getMessage());
		}
		System.out.println("End...............");
		Date d2 = new Date();
		long t = (d2.getTime() - d1.getTime()) / (1000);
		System.out.println("总共用时：" + t + "m");
	}

	public static void main(String[] args) throws GlobalException {
		createSQLFile("", "d:\\sql");
		/*
		 * String str = "<img src='topnav_icon_index.gif' />";
		 * System.out.println(str.replace("'", "\\'"));
		 */
	}

}
