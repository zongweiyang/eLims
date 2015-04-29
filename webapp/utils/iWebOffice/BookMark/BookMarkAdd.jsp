<%@ page contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<html>
	<head>
		<title>标签管理</title>
		<link rel='stylesheet' type='text/css' href='../test.css'>
	</head>
	<body bgcolor="#ffffff">
		<div align="center">
			<font size=4 color=ff0000>标签管理〖标签保存〗</font>
		</div>
		<hr size=1>
		<br>
		<%
			int mBookMarkID;
			String mBookMarkName = new String(request.getParameter("BookMarkName"));
			String mBookMarkDesc = new String(request.getParameter("BookMarkDesc"));
			String mBookMarkText = new String(request.getParameter("BookMarkText"));
			boolean mResult = false;

			DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
			if (null != DbaObj.Conn) {
				String mSql = "select BookMarkName from BookMarks where BookMarkName='" + mBookMarkName + "'";
				try {
					ResultSet result = DbaObj.ExecuteQuery(mSql);
					if (result.next()) {
						out.write("保存失败，数据库中已存在相同的标签〖" + mBookMarkName + "〗<input type='button' value='返 回' onclick='javascript:history.back();'");
						mResult = false;
					} else {
						java.sql.PreparedStatement prestmt = null;
						mSql = "Insert Into BookMarks (BookMarkName,BookMarkDesc,BookMarkText) values ('" + mBookMarkName + "','" + mBookMarkDesc + "','" + mBookMarkText + "')";
						prestmt = DbaObj.prepareStatement(mSql);
						;
						//DbaObj.Conn.setAutoCommit(true) ;
						prestmt.execute();
						//DbaObj.Conn.commit();
						prestmt.close();
						mResult = true;
					}
					result.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				} finally {
					DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
				}
			}

			if (mResult) {
				response.sendRedirect("BookMarkList.jsp");
			}
		%>
	</body>
</html>
