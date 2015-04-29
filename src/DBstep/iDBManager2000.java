package DBstep;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;

public class iDBManager2000 {

	public Connection Conn;
	public Statement Stmt;
	public DataSource dataSource = (DataSource) SystemInstance.getInstance().getBean("dataSource");

	public iDBManager2000() {
		if (null != dataSource) {
			Conn = DataSourceUtils.getConnection(dataSource);
		}
	}

	public String GetDateTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mDateTime = formatter.format(cal.getTime());
		return (mDateTime);
	}

	public java.sql.Date GetDate() {
		java.sql.Date mDate;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(cal.getTime());
		return (java.sql.Date.valueOf(mDateTime));
	}

	public int GetMaxID(String vTableName, String vFieldName) throws GlobalException {
		int mResult = 0;
		String mSql = new String();
		mSql = "select max(" + vFieldName + ")+1 as MaxID from " + vTableName;
		if (null != Conn) {
			try {
				mSql = editTableName(mSql);
				ResultSet result = ExecuteQuery(mSql);
				if (result.next()) {
					mResult = result.getInt("MaxID");
				}
				result.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return (mResult);
	}

	public ResultSet ExecuteQuery(String SqlString) throws GlobalException {
		ResultSet result = null;
		try {
			if (null != Conn) {
				SqlString = editTableName(SqlString);
				Stmt = Conn.createStatement();
				result = Stmt.executeQuery(SqlString);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new GlobalException("" + e.getMessage());
		}
		return (result);
	}

	public ResultSet ExecuteQuery1(String SqlString) throws GlobalException {
		ResultSet result = null;
		try {
			if (null != Conn) {
				Stmt = Conn.createStatement();
				result = Stmt.executeQuery(SqlString);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new GlobalException("" + e.getMessage());
		}
		return (result);
	}

	public int ExecuteUpdate(String SqlString) throws GlobalException {
		int result = 0;
		try {
			SqlString = editTableName(SqlString);
			if (null != Conn) {
				Stmt = Conn.createStatement();
				result = Stmt.executeUpdate(SqlString);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			throw new GlobalException("" + e.getMessage());
		}
		return (result);
	}

	public PreparedStatement prepareStatement(String SqlString) throws GlobalException {
		try {
			SqlString = editTableName(SqlString);
			return Conn.prepareStatement(SqlString);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
	}

	public String editTableName(String sql) {
		String uppSql = sql.toUpperCase();
		String regex = "FROM";
		if (uppSql.startsWith("UPDATE")) {
			regex = "UPDATE";
		} else if (uppSql.startsWith("INSERT")) {
			regex = "INTO";
		}
		String tableName = uppSql.split(regex)[1].trim().split(" ")[0];
		if (!tableName.startsWith("OFFICE_")) {
			if (uppSql.endsWith(tableName)) {
				uppSql = uppSql.replace(" " + tableName, "  OFFICE_" + tableName + "  ");
			} else {
				uppSql = uppSql.replace(" " + tableName + " ", "  OFFICE_" + tableName + "  ");
			}
		}
		return uppSql;
	}
}