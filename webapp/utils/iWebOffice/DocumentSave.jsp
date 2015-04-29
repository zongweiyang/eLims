<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%@page import="cn.labsoft.labos.utils.SysOut"%>
<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%
	String mRecordID = request.getParameter("RecordID");
	if (mRecordID == null)
		mRecordID = "";
	String mTemplate = new String(request.getParameter("Template"));
	String mSubject = new String(request.getParameter("Subject"));
	String mAuthor = new String(request.getParameter("Author"));
	String mFileDate = new String(request.getParameter("FileDate"));
	String mFileType = new String(request.getParameter("FileType"));
	String mHTMLPath = new String(request.getParameter("HTMLPath"));
	String mStatus = "READ";

	DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
	if (null != DbaObj.Conn) {
		String mysql = "SELECT RecordID from  Document Where RecordID='" + mRecordID + "'";
		try {
			ResultSet result = DbaObj.ExecuteQuery(mysql);
			if (result.next()) {
				mysql = "update Document set RecordID=?,Template=?,Subject=?,Author=?,FileDate=?,FileType=?,HtmlPath=?,Status=? where RecordID='" + mRecordID + "'";
			} else {
				mysql = "insert into Document (RecordID,Template,Subject,Author,FileDate,FileType,HtmlPath,Status) values (?,?,?,?,?,?,?,?)";
			}
			result.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		java.sql.PreparedStatement prestmt = null;
		try {
			prestmt = DbaObj.prepareStatement(mysql);
			prestmt.setString(1, mRecordID);
			prestmt.setString(2, mTemplate);
			prestmt.setString(3, mSubject);
			prestmt.setString(4, mAuthor);
			prestmt.setString(5, mFileDate);
			prestmt.setString(6, mFileType);
			prestmt.setString(7, mHTMLPath);
			prestmt.setString(8, "READ");

			//DbaObj.Conn.setAutoCommit(true) ;
			prestmt.executeUpdate();
			//DbaObj.Conn.commit();

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			prestmt.close();
			DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
		}
	} else {
		out.println("OpenDatabase Error");
	}
	//response.sendRedirect("DocumentList.jsp");
	//request.setAttribute("para",request.getParameter("para").replace("_","&"));
	//request.setAttribute("nextPage",request.getParameter("nextPage"));
	response.sendRedirect("next.jsp?mRecordID=" + mRecordID);
%>