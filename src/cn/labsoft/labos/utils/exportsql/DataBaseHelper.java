package cn.labsoft.labos.utils.exportsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class DataBaseHelper
{
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

  public static void closeConn(Connection conn) throws GlobalException {
    try {
      if (conn != null)
        conn.close();
    }
    catch (Exception e) {
      //e.printStackTrace(); 
      throw new GlobalException("" + e.getMessage());}
  }

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
