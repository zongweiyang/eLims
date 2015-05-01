<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<title><s:text name="page.info"/></title>
<style>
.myworkingboxttable {width:100%;*width:97%;margin:0;}
</style>
</head>
<body> 
<table class="myworkingboxttable" cellspacing="0" cellpadding="0" >
							<thead>
								<tr>
									<th class="c" width="20"><img src="<%=basePath%>img/icon_drag.gif"/></th>
									<th class="c" width="40"><s:text name="submit.people"/></th>
									<th class="c" width="80"><s:text name="submit.time"/></th>
									<th class="c"><s:text name="msg.subject"/></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="c">1</td>
									<td class="c">admin</td>
									<td class="c">2010-5-19</td>
									<td>[<s:text name="sam.id"/>:20100519001]<s:text name="waistcheck"/></td>
								</tr>
								<tr>
									<td class="c">2</td>
									<td class="c">test</td>
									<td class="c">2010-5-23</td>
									<td>[<s:text name="sam.id"/>:20100519001]<s:text name="waistcheck"/></td>
								</tr>
								<tr>
									<td class="c">3</td>
									<td class="c">test3</td>
									<td class="c">2010-6-11</td>
									<td>[<s:text name="sam.id"/>:20100611001]<s:text name="waistcheck"/></td>
								</tr>
							</tbody>
						</table>
</body>
						</html>