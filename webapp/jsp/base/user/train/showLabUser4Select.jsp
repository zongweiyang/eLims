<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			
			function closeMe(){
			  	api.close();
		 	}
		 	function ok(){
		 		
		 		var userId="";
		 		var userName="";
		 		$('input[name="labUserVo.ids"]').each(function(){
		 			if($(this).prop('checked')==true){
		 				userId+=$(this).val()+",";
		 				userName+=$(this).attr('key')+",";
		 			}
		 		});
		 		if(userId.length>1){
		 			userId=userId.substring(0,userId.length-1);
		 			userName=userName.substring(0,userName.length-1);
		 		}
		 		$('#studentIds',D).val(userId);
		 		$('#studentNames',D).val(userName);
		 		closeMe();
		 	};
		 
			function doSubmit(url){
				$('form').attr('action',url);
				$('form').submit();
			}
		</script>


	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labRoleForm">
			<table id="bodyTable" class="bodytable_pop" width="97.2%" cellspacing="0" cellpadding="0" border="0" style="min-height: 200px; margin: 10px 5px 10px 5px">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop">
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:text name="msg.depart"/>：
																</td>
																<td>
																	<input type="text" name="labUserVo.orgName" value="${labUserVo.orgName}" />
																</td>
																<td>
																	<s:text name="theme.user"/>：
																</td>
																<td>
																	<input type="text" name="labUserVo.name" value="${labUserVo.name}" />
																</td>
																<td>
																	<l:a uri="/user/labUserTrain/showLabUser4Select.action" img="img/chakan.gif" onclick="doSubmit('/user/labUserTrain/showLabUser4Select.action');" value="fun.query" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="ok();return false;"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="page.confirm"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="listTable_pop" cellspacing="0" cellpadding="0" width="800">
											<thead>
												<tr>
													<th>
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="msg.depart"/>
													</th>
													<th>
														<s:text name="msg.username"/>
													</th>
													<th>
														&nbsp;
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="msg.depart"/>
													</th>
													<th>
														<s:text name="msg.username"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<s:iterator value="#request.userList" status="st">
														<td class="c">
															<input type="checkbox" name="labUserVo.ids" id="ids${st.index+1}" value="${id}" key="${name }"  />
														</td>
														<td class="c">
															${st.index+1}
														</td>
														<td>
															${orgName}
														</td>
														<td class="1">
															${name}
														</td>
														<s:if test="${(st.index+1)%2==0}">
												</tr>
												<tr>
												</s:if>
												</s:iterator>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
	</body>
<script type="text/javascript">
$(function(){
	var userId = $('#studentIds',D).val();
	$('input[name="labUserVo.ids"]').each(function(){
		if(userId.indexOf($(this).val())!=-1){
			$(this).attr('checked',true);
		}
	});
});
</script>
</html>
