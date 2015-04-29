<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%
	String mBookMarkID = new String(request.getParameter("BookMarkID"));
	boolean mResult = false;

	DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
	if (null != DbaObj.Conn) {
		try {
			java.sql.PreparedStatement prestmt = null;
			String mSql = "Delete from BookMarks where BookMarkID = " + mBookMarkID;
			prestmt = DbaObj.prepareStatement(mSql);
			;
			//DbaObj.Conn.setAutoCommit(true) ;
			prestmt.execute();
			//DbaObj.Conn.commit();
			prestmt.close();
			mResult = true;
		} catch (Exception e) {
		} finally {
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
	} else {
		mResult = false;
	}

	if (mResult) {
		response.sendRedirect("BookMarkList.jsp");
	}
%>
