package cn.labsoft.labos.utils.dataconvert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.JDBCCon;

public class MainApp {

	/**
	 * @param args
	 * @throws GlobalException 
	 */
	public static void main(String[] args) throws GlobalException {
		TableConvert  table = new TableConvert("fromtable","totable");
		Connection fromConn = new JDBCCon().getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/fromdb?useUnicode=true&characterEncoding=UTF-8", "root", "labsoftmysql");
		Connection toConn = new JDBCCon().getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/todb?useUnicode=true&characterEncoding=UTF-8", "root", "labsoftmysql");
		ColumnConvert Columns = new ColumnConvert(getFromColumns(table,fromConn),getToColumns(table,toConn));
		List<String>  list = getData(fromConn,table,Columns);
		saveData(toConn,table,Columns,list);
	}
	
	public static String getFromColumns(TableConvert  table, Connection conn) throws GlobalException{
		String sql = "select * from "+table.getFromTable();
		return  getColumns(conn,sql);
	}
	
	public static String getToColumns(TableConvert  table, Connection conn) throws GlobalException{
		String sql = "select * from "+table.getToTable();
		return  getColumns(conn,sql);
	}

	public static String getColumns(Connection conn, String sql) throws GlobalException{
		String columns="";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			int columnCount = 0;
			if(null!=resultSet){
				columnCount = resultSet.getMetaData().getColumnCount();
			}
	    	for(int i=2; i<=columnCount; i++){
	    		 String column = resultSet.getMetaData().getColumnName(i);
	    		 columns += column;
	    		 if(i<columnCount){
	    			 columns+=",";
	    		 }
	    	}
	        if(columns.equals("")){
	        	columns = "*";
	        }
		} catch (SQLException e) {
			System.out.println("查询数据失败!");  
			throw new GlobalException("" + e.getMessage());
		}
		return columns;
		
	}
	
	public static List<String> getData(Connection conn, TableConvert  table,ColumnConvert Columns) throws GlobalException{
		String sql = "select "+Columns.getFromColumn()+ " from "+table.getFromTable();
		Statement statement = null;
		ResultSet resultSet = null;
		String columnValues="";
		List<String>  list = new ArrayList<String>();
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if(null!=resultSet){
				int columnCount = resultSet.getMetaData().getColumnCount();
				while(resultSet.next()){
					for(int i=1; i<=columnCount; i++){
						String value = resultSet.getString(i);
						columnValues +="'"+value+"'";
						 if(i<columnCount){
							 columnValues+=",";
			    		 }
					}
					list.add(columnValues);
					columnValues="";
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("查询数据失败!");  
			throw new GlobalException("" + e.getMessage());
		}finally{
			try {
				if(null!=resultSet)
				   resultSet.close();
				if(null!=statement)
		            statement.close();
				if(null!=conn)
				    conn.close(); 
			} catch (SQLException e) {
				System.out.println("数据库关闭异常!");
				throw new GlobalException("" + e.getMessage());
			}
		}
		return list;
	}
	
	public static void saveData(Connection conn, TableConvert  table,ColumnConvert Columns, List<String> list) throws GlobalException{
		if(null!=list&&list.size()>0){
			Statement statement = null;
			try {
				statement = conn.createStatement();
				for(int i=0; i<list.size(); i++){
					String sql = "insert into "+table.getToTable()+"("+Columns.getToColumn()+")"+" values("+list.get(i)+")";
					System.out.println(sql);
					boolean key = statement.execute(sql);
					if(key){
						System.out.println("插入数据成功!");
					}
			     }
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("插入数据失败!");  
				throw new GlobalException("" + e.getMessage());
			}finally{
				try {
					if(null!=statement)
			            statement.close();
					if(null!=conn)
					    conn.close(); 
				} catch (SQLException e) {
					System.out.println("数据库关闭异常!");
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
	}
	
}
