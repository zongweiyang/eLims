<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<%@ include file="/jsp/include/jquery.jsp"%>
<body>
<html>
	<head>
		<title><s:text name="errorlass"/></title>
		<meta http-equiv=content-type content="text/html; charset=gb2312">
		<meta content="mshtml 6.00.2800.1458" name="generator">
		<link type=text/css rel=stylesheet>
		<style type=text/css>
table {
	border:1px;
	font-size: 9pt;
	color: #842b00;
	line-height: 16pt;
	font-family: "tahoma", "宋体";
	text-decoration: none
}

td {
	font-size: 9pt;
	color: #842b00;
	line-height: 16pt;
	font-family: "tahoma", "宋体";
	text-decoration: none
}
body {
	font-size: 9pt;
	color: #842b00;
	line-height: 16pt;
	font-family: "tahoma", "宋体";
	text-decoration: none
	scrollbar-highlight-color: buttonface;
	scrollbar-shadow-color: buttonface;
	scrollbar-3dlight-color: buttonhighlight;
	scrollbar-track-color: #eeeeee;
	background-color: #ffffff
}

a {
	font-size: 9pt;
	color: #842b00;
	line-height: 16pt;
	font-family: "tahoma", "宋体";
	text-decoration: none
}

a:hover {
	font-size: 9pt;
	color: #0188d2;
	line-height: 16pt;
	font-family: "tahoma", "宋体";
	text-decoration: underline
}

h1 {
	font-size: 9pt;
	font-family: "tahoma", "宋体"
}
</style>
	</head>
	<body>
		<table cellspacing="0" width="100%" align="center" border="0" cepadding="0" >
			<tbody>
				<tr>
					<td width="270">
						<img src="<%=basePath %>jsp/error/error.gif"/>
					</td>
					<td>
						<h1>
							<font color="#666666">无法找到该页</font>
						</h1>
						<font color="#666666"> 当前页面暂时不可用。
						</font>
						<hr noshade size=0>
						<p>
							<font color="#666666">☉ 请尝试以下操作：</font>
						</p>
						<ul>
							<li>
								<font color="#666666">确保浏览器的地址栏中显示的网站地址的拼写和格式正确无误。 </font>
							</li>	
							<li>
								<font color="#666666">如果通过单击链接而到达了该网页，请与网站管理员联系，通知他们该链接的格式不正确。</font>
							</li>	
							<li>
								<font color="#666666">单击</font><a
									href="javascript:history.back(1)"><font color=#0000ff>后退</font>
								</a><font color="#666666">按钮尝试另一个链接。 </font>
							</li>	
							</li>
						</ul>
						<hr noshade size=0>
						<p>
							<font color="#666666">☉<a href="javascript:;" onclick="$('#errorInfo').toggle();"> <s:text name="showexpinfo"/>：</a></font>
							<ul style="display: none" id="errorInfo">
								<li>${exception}</li>
								<li>${exception.stackTrace[0] }</li>
							</ul>	
						</p>
						<p>
							<font color="#666666">☉
								如果您对本站有任何疑问、意见、建议、咨询，请联系管理员<br>
							</font>
							<br>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
