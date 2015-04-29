package cn.labsoft.labos.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class JDBCCon{
	private  Connection conn = null;

	public  Connection getConnection(String  driver, String url, String username, String password) throws GlobalException {
		if (null == conn) {
			try {
				Class.forName(driver); // 加载mysq驱动
				conn = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载错误！");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (SQLException e) {
				System.out.println("连接建立失败！");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (Exception e){
				System.out.println("读取连接信息失败！");
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		return conn;
	}
}