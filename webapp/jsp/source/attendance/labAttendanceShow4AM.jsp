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
			   	  	alert('<s:property value="getText('config.request.error')"/>');
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
														<s:text name="theme.curr.user"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.userName}
												</td>
												<td>
													<label>
														<s:text name="carddate"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.workDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="wordday.time"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.standTimeAM}
												</td>
												<td>
													<label>
														<s:text name="cark.time"/>：
													</label>
												</td>
												<td>
													${labAttendanceVo.startTime}
												</td>
											</tr>
										</table>
										<div align="center" style="margin-left: 10px;">
											<s:if test="${labAttendanceVo.startFlag=='Y'}">
												<img src="/img/alert/chidao.png" width="280" height="120"/>
											</s:if>
											<s:else>
												<img src="/img/alert/zhengchang.png" width="280" height="120"/>
											</s:else>
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
