<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			
			var index = '${labRoleVo.index}';
			function closeMe(){
			  	api.close();
		 	}
		 	function ok(){
		 		var ids="";
		 		var names="";
		 		$('input[name="labRoleVo.ids"]:checked').each(function(){
		 			ids+=$(this).val()+",";
		 			names+=$(this).attr('key')+",";
		 		});
		 		if(ids.length>1){
		 			ids=ids.substring(0,ids.length-1);
		 			names=names.substring(0,names.length-1);
		 		}
		 		$('#roleId'+index,D).val(ids);
		 		$('#roleName'+index,D).val(names);
		 		closeMe();
		 	}
		</script>
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

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labRoleForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="">
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<!-- <td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																	onclick="closeMe();return false;"><img
																		height="20" width="20"
																		src="<%=basePath%>img/zhongzhi.gif" /><b><s:text name="closednow"/></b> </a>
																</td> -->
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																	onclick="ok();return false;"><img
																		height="20" width="20"
																		src="<%=basePath%>img/accept.gif" /><b><s:text name="page.confirm"></s:text></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th width="40">
														<input type="checkbox" id="allCheckBox"
															key="labRoleVo.ids" />
													</th>
													<th width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th width="150">
														<s:text name="role.name"></s:text>
													</th>
													<th width="150">
														<s:text name="remark"></s:text>
													</th>
													<th>
														<s:text name="fun.authorise"></s:text>
													</th>
											</thead>
											<tbody>
												<s:iterator value="#request.roleList" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labRoleVo.ids"
																	id="ids<s:property value="#st.index+1" />"
																	value="${id}" key="${name}"/>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${name}
															</td>
															<td class="l">
																${remark}
															</td>
															<td class="1">
																${funNames}
															</td>
														</tr>
												</s:iterator>
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
	<script>
		$(function(){
			var roleIds=$('#roleId'+index,D).val();
			if(roleIds.length>0){
				var roles=roleIds.split(',');
				for(var i=0;i<roles.length;i++){
					var rid=roles[i].replace(' ','');
					$('input[value="'+rid+'"]').attr('checked','true');
				}
			}
		});
	</script>
</html>
