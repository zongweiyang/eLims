<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%
	String mRecordID = request.getParameter("RecordID");

	DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
	if (null != DbaObj.Conn) {
		try {
			java.sql.PreparedStatement prestmt = null;
			String mSql = "Delete from Template_File where RecordID = '" + mRecordID + "'";
			prestmt = DbaObj.prepareStatement(mSql);
			//DbaObj.Conn.setAutoCommit(true) ;
			prestmt.execute();
			//DbaObj.Conn.commit();
			prestmt.close();
		} catch (Exception e) {
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
	}
	response.sendRedirect("TemplateList.jsp");
%>

