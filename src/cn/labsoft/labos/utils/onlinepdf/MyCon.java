package cn.labsoft.labos.utils.onlinepdf;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class MyCon {

    private Connection connect;

    public String checkConnection(String host, String userName,String password, String dbName) throws GlobalException{
		String url = "jdbc:mysql://";
		host = host.trim();
		host = host.replace("http://", "");
		host = host.replace("//", "");
		if(host.length() == 0)
			host = "localhost";
		if(!host.contains(":"))
		host = host + ":3306";
		url += host;
        try{
        	Class.forName("com.mysql.jdbc.Driver").newInstance ();
        }catch(Exception e){
        	return "no_mysql_extensions";
        	
        }
		try{
			connect = DriverManager.getConnection(url);
			if(connect.isClosed())
				return "no_connect";
		} catch(Exception e){
        	return "no_connect";
        	
        } finally {
        	close();
        }
		dbName = dbName.trim();
		if(dbName.length() == 0)
			return "no_db";
		url += "/" + dbName;
		try{
			connect = DriverManager.getConnection(url, userName, password.trim());
			if(connect.isClosed())
				return "no_db";
		} catch(Exception e){
        	return "no_db";
        } finally{
        	close();
		}
        return "1";
    }

    public void close() throws GlobalException{
    	try {
			if(connect != null)
				connect.close();
		}catch(SQLException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
    }

    public Connection getConnection(String host, String userName,String password, String dbName) throws GlobalException{
        try{
			String url = "jdbc:mysql://";
			host = host.trim();
			host = host.replace("http://", "");
			host = host.replace("//", "");
			if(host.length() == 0)
				host = "localhost";
			if(!host.contains(":"))
			host = host + ":3306";
			url += host;
			url += "/" + dbName.trim();
			Class.forName("com.mysql.jdbc.Driver").newInstance ();
			connect=DriverManager.getConnection( url, userName.trim(), password.trim() );
		}catch(Exception ex){
			connect = null;
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
        return connect;
    }

    public boolean executes(String sql){
    	try {
			Statement stmt = connect.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
    	return true;
    }

	public ResultSet executeQuery(String sql){
		ResultSet rs = null;
		try{
			Statement stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return rs;
	}

	public int rowCount(String sql){
		int rowcount = 0;
		try{
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				rowcount = rs.getInt(1);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return rowcount;
	}

	public void echo(Object o){
		System.out.println(o);
	}

    public static void main(String args[]) {
    }

}