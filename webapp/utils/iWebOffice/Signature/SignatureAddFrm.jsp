<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<html>
<head>
<title>ǩ�¹���</title>
<link rel='stylesheet' type='text/css' href='../test.css'>
<script language=javascript>
function Check(theForm){
	if (theForm.MarkName.value == ""){
		alert("������ӡǩ��.");
		theForm.MarkName.focus();
		return (false);
	}
	return (true);
}
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName = String.valueOf(session.getAttribute("suserName"));
String userId = String.valueOf(session.getAttribute("suserId"));
String orgId = String.valueOf(session.getAttribute("sorgId"));
String info = String.valueOf(session.getAttribute("info"));
session.removeAttribute("info");
if("null".equals(info)) info = "";
%>
</Script>
</head>
<body bgcolor="#ffffff">
<br/>
<br/>
<div align="center"><font size=4 color="ff0000">ǩ�¹�������ǩ�¡�</font></div>
<div align="center"><font size=3 color="ff0000"><%= info%></font></div>
<hr size=1>
<br>
<form name="webform" method="post" enctype="multipart/form-data" action="<%=basePath%>servlet/SignatureServer" onsubmit="return Check(this)">
<input type="hidden" name="UserId" size="50" maxlength="32" class="IptStyle" value="<%=userId%>">
<input type="hidden" name="OrgId" size="50" maxlength="32" class="IptStyle" value="<%=orgId%>">
<table border=0  cellspacing='0' cellpadding='0' width=100% align=center class=TBStyle>
<tr>
  <td nowrap align=center class="TDTitleStyle" width=64>�û�����</td>
  <td class="TDStyle" width="90%"><input type="text" name="UserName" size="50" maxlength="32" class="IptStyle" value="<%=userName%>"></td>
</tr>
<tr>
  <td nowrap align=center class="TDTitleStyle" width=64>�û�����</td>
  <td class="TDStyle" width="90%"><input type="password" name="PassWord" size="50" maxlength="32" class="IptStyle" value=""></td>
</tr>
<tr>
  <td nowrap align=center class="TDTitleStyle" width=64>ǩ������</td>
  <td class="TDStyle" width="90%"><input type="text" name="MarkName" size="50" maxlength="32" class="IptStyle" value=""></td>
</tr>
<tr>
  <td nowrap align=center class="TDTitleStyle" width=64>ǩ���ļ�</td>
  <td class="TDStyle"><input type="file" name="MarkFile" size="50" maxlength="60" class="IptStyle"></td>
</tr>
<tr>
  <td colspan=2 class="TDTitleStyle" nowrap>
    <input type=submit name="Save" value="�� ��">
    <input type=reset name="Reset" value="�� ��">
    <input type=button name="Return" value="�� ��"  onclick="javascript:history.back();">
  </td>
</tr>
</table>
</form>
</body>
</html>
