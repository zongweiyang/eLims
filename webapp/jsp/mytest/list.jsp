<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ include file="/jsp/include/common.jsp"%>
<html>
  <head>
    <title>My JSP 'MyJsp.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<table border="1" style="width: 80%">
  		<tr>
  			<td>序号</td>
  			<td>1</td>
  			<td>2</td>
  			<td>3</td>
  			<td>4</td>
  			<td>5</td>
		</tr>
		<s:iterator value="pageResult.resultList" status="st">
		<tr>
			<td>${st.index }</td>
			<td>${haha }</td>
			<td>${createTime }</td>
			<td>${createUserId }</td>
			<td>${id }</td>
			<td>${isDel }</td>
			<td><a href="delete.action?myTestVo.id=${id}"><s:text name="lab.code.del"></s:text></a>||<a href="update2del.action?myTestVo.id=${id}"><s:text name="fake.del"></s:text></a></td>
		</tr>
		</s:iterator>
		<tr>
			<td colspan="4"><input name="myTestVo.haha"/></td>
			<td></td>
			<td></td>
			<td><a href="add.action"><s:text name="added"></s:text></a></td>
		</tr>
  	</table>
  </body>
</html>
