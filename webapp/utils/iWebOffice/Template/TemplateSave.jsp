<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%@page import="cn.labsoft.labos.utils.SysOut"%>
<%
	request.setCharacterEncoding("UTF-8");
	String mRecordID = request.getParameter("RecordID");
	String mFileName = new String(request.getParameter("FileName"));
	String mDescript = new String(request.getParameter("Descript"));
	String mTempType = new String(request.getParameter("Type"));
	DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
	if (null != DbaObj.Conn) {
		try {
			java.sql.PreparedStatement prestmt = null;
			String mSql = "Update Template_File Set TempType = '" + mTempType + "',FileName = '" + mFileName + "',Descript = '" + mDescript + "' Where RecordID='" + mRecordID + "'";
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
	DbaObj.CloseConnection();

	response.sendRedirect("TemplateList.jsp");
%>
