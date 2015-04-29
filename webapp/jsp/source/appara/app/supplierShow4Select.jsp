<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property value="#session.SessionContainer.orgUnit" />
		</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
.myworkingboxttable {
	width: 98%;
	border: 0px solid #CFCFCF;
	margin: 0 5px;
	border-collapse: collapse;
}
</style>
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
	    function checkUser(){
       		var name="";
       		$('input[type="radio"]:checked').each(function(){
       			$('#supName').val($(this).val());
       		});
       		
       	}
       	function submitx(){
       		var name=$('#supName').val();
       		$('#supplier',D).val(name);
       		closeMe();
       	}
       	$(document).ready(function(){
       		//将来做回填用
       	});
</script>
	</head>

	<body class="" id="mainid" style="overflow-y: auto;">
		<!-- pageResule query -->
		<form action="" method="post" name="listform" theme="simple">
			<input type="hidden" name="funId" value="${funId}" />
			<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th class="c" width="30">
							<img src="<%=basePath%>img/icon_drag.gif" />
						</th>
						<th class="c">
							<s:text name="supplier"/>
						</th>
						<th class="c">
							<s:text name="theme.phone"/>
						</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<s:if test="pageResult.resultList!=null">
						<s:set name="alllist" value="pageResult.resultList" />
						<s:set name="isurgent" value="0" />
						<tr>
						<s:iterator value="#alllist" status="st">
							<td class="c">
								<s:property value="#st.index+1" />
							</td>
							<td class="l">
								${name}
							</td>
							<td class="l">
								${phone}
							</td>
							<td class="c">
								<input type="radio"  value="${name}" name="name" id="id${st.index}" onclick="checkUser()"  />
							</td>
						</s:iterator>
						</s:if>
				</tbody>
			</table>
			<div class="FUNCIONBARNEW">
				<table>
					<tr>
						<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
							<table cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td>
										<input type="hidden" name="supName" id="supName" />
										<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="submitx()"><img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="page.confirm"/></b> </a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
