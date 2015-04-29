<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,DBstep.iDBManager2000.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%@page import="DBstep.Signature"%>
<html>
	<head>
		<title>签章管理</title>
		<link rel='stylesheet' type='text/css' href='../test.css'>
		<%@ include file="../../jsp/include/common.jsp"%>
		<script language="javascript">
function ConfirmDel(FileUrl){
	if (confirm('是否确定删除该印签！')){
		location.href=FileUrl;
	}
}
<%
String userName = request.getParameter("userName");
String userId = request.getParameter("userId");
String orgId = request.getParameter("orgId");
if(null!=userName){
	session.setAttribute("suserName",userName);
}
if(null!=userId){
	session.setAttribute("suserId",userId);
}
if(null!=orgId){
	session.setAttribute("sorgId",orgId);
}
%>
</script>
	</head>
	<body bgcolor="#ffffff">
		<div align="center">
			<font size=4 color="ff0000">签章管理</font>
		</div>
		<hr size=1>
		<br>
		<table border=0 cellspacing='0' cellpadding='0' width=100% align=center class=TBStyle>
			<tr>
				<td colspan=2 class="TDTitleStyle" nowrap>
					<input type=button name="Add" value="增加签章" onclick="javascript:location.href='SignatureAddFrm.jsp'">
					<input type=button name="Return" value="返 回" onclick="javascript:location.href='<%=basePath%>admin/sysUser/listSysUser.action?sysUserVo.sysOrgId=<%=orgId%>';">
				</td>
				<td colspan=3 class="TDTitleStyle">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td nowrap align=center class="TDTitleStyle" height="26">
					编号
				</td>
				<td nowrap align=center class="TDTitleStyle">
					用户名称
				</td>
				<td nowrap align=center class="TDTitleStyle">
					签章名称
				</td>
				<td nowrap align=center class="TDTitleStyle">
					签章
				</td>
				<td nowrap align=center class="TDTitleStyle">
					操作
				</td>
			</tr>
			<%
				DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
				if (null != DbaObj.Conn) {
					try {
						userId = String.valueOf(session.getAttribute("suserId"));
						ResultSet result = DbaObj.ExecuteQuery("select SignatureID,UserName,MarkName,MarkType,MarkBody from Signature  where UserId = '" + userId + "' order by SignatureID desc");
						while (result.next()) {
							String mSignatureID = result.getString("SignatureID");
							String imgUrl = new Signature().getPhoto(result.getBlob("MarkBody").getBinaryStream(), result.getString("SignatureID"));
			%>
			<tr>
				<td class="TDStyle"><%=result.getString("SignatureID")%>&nbsp;
				</td>
				<td class="TDStyle"><%=result.getString("UserName")%>&nbsp;
				</td>
				<td class="TDStyle"><%=result.getString("MarkName")%>&nbsp;
				</td>
				<td class="TDStyle">
					<img src="<%=basePath%><%=imgUrl%>" alt="<%=result.getString("UserName")%>">
					&nbsp;
				</td>
				<td class="TDStyle" width=148 nowrap>
					<!-- 
			<input type=button onclick="javascript:location.href='SignatureEditFrm.jsp?SignatureID=<%=mSignatureID%>';" name="Edit" value=" 修 改 ">
			 -->
					<input type=button onclick="javascript:ConfirmDel('SignatureDel.jsp?SignatureID=<%=mSignatureID%>');" name="Del" value=" 删 除 ">
				</td>
			</tr>
			<%
				}
						result.close();
					} catch (Exception e) {
						System.out.println(e.toString());
					}finally {
						DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
					}
				} else {
					out.println("OpenDatabase Error");
				}
			%>
		</table>
	</body>
</html>
