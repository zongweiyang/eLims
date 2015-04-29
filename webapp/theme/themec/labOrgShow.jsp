<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			function logOut(){
			  	W.logOut();
			  	api.close();
		 	}
			function ok(){
				W.location.href="<%=basePath%>/coreextend/extend/loginSystem4Org.action?labUserVo.id=${labUserVo.id}&labUserVo.orgId="+$('#orgId').val();
				api.close();
			}
			$(document).ready(function(){   
      			$(document).bind("contextmenu",function(e){   
		            return false;   
		        });   
		    });   
			function getRoleName(obj)
			{
			  $.ajax({
			   	  url:'<%=basePath%>coreextend/extend/ajaxLabRole4User2Org.action',
			   	  data:{'labUserVo.id':'${labUserVo.id}','labUserVo.orgId':$(obj).val()},
			   	  type:'post',
				  dataType:'text',
			   	  success:function (data){
			   	  	if(data!=""){
		            	$('#roleTd').html(data);
		          	 }
			   	  },
			   	  error:function (data){
			   	  	alert('<s:property value="getText('theme.request.error')"/>');
			   	  }
			   });	 
			}
		</script>

	</head>
	<body>

		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable_pop" cellspacing="0" cellpadding="0" border="0" style="min-height: 50px; width: 95%">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop" style="min-height: 50px; width: 100%; padding-top: 10px;">
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<label>
														<s:text name="theme.curr.user"></s:text>：
													</label>
												</td>
												<td>
													${labUserVo.name}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.login.office"></s:text>：
													</label>
												</td>
												<td>
													<s:select onchange="getRoleName(this);" list="#request.orgList" listKey="id" listValue="name" name="labUserVo.orgId" id="orgId" theme="simple"></s:select>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.role"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td id="roleTd">
													${labUserVo.roleName}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.date"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.currentDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.host.ip"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${SessionContainer.ip}
												</td>
											</tr>
										</table>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding-top: 10px; vertical-align: center; padding-left: 10px;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="ok();"> <img height="20" width="20" src="<%=basePath%>img/xinjian.gif" /><b><s:text name="theme.confirm.login"></s:text></b> </a>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="logOut();"> <img height="20" width="20" src="<%=basePath%>img/xinjian.gif" /><b><s:text name="theme.login.cancel"></s:text></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
