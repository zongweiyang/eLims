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
       	function submitx(){
       		var keeperId=$('#keeperId').val();
       		var userName=$('#userName').val();
       		$('#keeperId',D).val(keeperId);
       		$('#keeper',D).val(userName);
       		closeMe();
       	}
       	function goToNextAction(url){
       		var df = document.listform;
			df.action = url;
		  	df.submit();
       	}
       	function checkUser(){
       		var ids="";
       		var names="";
       		$('input[type="checkbox"]:checked').each(function(){
       			ids+=$(this).val()+",";
       			names+=$(this).parent().prev().html()+",";
       		});
       		if(ids.length>1){
       			ids=ids.substring(0,ids.length-1);
       		}
       		if(names.length>1){
       			names=names.substring(0,names.length-1);
       		}
       		$('#keeperId').val(ids);
       		$('#userName').val(names);
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
							<s:text name="nike.name"/>
						</th>
						<th class="c">
						</th>
						<th class="c" width="30">
							<img src="<%=basePath%>img/icon_drag.gif" />
						</th>
						<th class="c">
							<s:text name="nike.name"/>
						</th>
						<th class="c">
						</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="labUserList!=null">
						<s:set name="alllist" value="#request.labUserList" />
						<s:set name="isurgent" value="0" />
						<s:if test="isurgent==1">
							<tr style="background-color: #ffdee0">
						</s:if>
						<s:else>
							<tr>
						</s:else>
						<s:iterator value="#alllist" status="st">
							<td class="c">
								<s:property value="#st.index+1" />
							</td>
							<td class="l">
								${name}
							</td>
							<td class="c">
								<input type="checkbox" value="${id}" name="keeperId" id="id${st.index}" onclick="checkUser();" />
							</td>
							<s:if test="${(st.index+1)%2==0}">
								</tr>
								<tr>
							</s:if>
						</s:iterator>
						<tr>
							<td class="c" colspan="8">
								<textarea cols="50" rows="6" id="userName"></textarea>
								<input type="hidden" value="" id="keeperId" />
							</td>
						</tr>
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
