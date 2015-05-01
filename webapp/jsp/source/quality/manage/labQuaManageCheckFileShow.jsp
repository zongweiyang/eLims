<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function checkUnit(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#labId').val(oo[0]);
				$('#labName').val(oo[1]);
			}
			function showReport(){
				var height = window.screen.height-250;
				var url  = '${basePath}quality/manage/showLabReport4Bus.action?labReportVo.path=${labQuaManageCheckVo.reportPath}&labReportVo.id=${labQuaManageCheckVo.reportTempId}&labReportVo.busInsId=${labQuaManageCheckVo.id}';
				$.dialog({
					id:'id',
					content:'url:'+url,
					title:'<s:property value="getText('pageprint')"/>',
					opacity:0.4,
					width:1050,
					height:height,
					max:false,
					min:false,
					lock:true
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back"/>  
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="manageauditlist"/>：</span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												   <tr>
												<td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.orgName }
													<input type="hidden"  id="orgId" name="labQuaManageCheckVo.orgId" value="${labQuaManageCheckVo.orgId }"  />
													<input type="hidden"  id="orgName" name="labQuaManageCheckVo.orgName"  value="${labQuaManageCheckVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="auditplanded"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.quaManageCheckPlanName}
													<input name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanName}" />
														<input name="labQuaManageCheckVo.quaManageCheckPlanId" id="quaManageCheckPlanId" type="hidden"
														value="${labQuaManageCheckVo.quaManageCheckPlanId}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="audittime"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.recTime}
													<input name="labQuaManageCheckVo.recTime" id="recTime" type="hidden"
														value="${labQuaManageCheckVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											    <td class="r" width="150">
													<label>
														<s:text name="auditsite"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.address}
													<input name="labQuaManageCheckVo.address" id="address"
														type="hidden" value="${labQuaManageCheckVo.address}"/>
												</td>
												
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="groupmaster"/>：
														</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupLeader}
													<input name="labQuaManageCheckVo.groupLeader" id="groupLeader" type="hidden"
														value="${labQuaManageCheckVo.groupLeader}" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="grouppeople"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckVo.groupMember}
													<input name="labQuaManageCheckVo.groupMember" id="groupMember" type="hidden"
														value="${labQuaManageCheckVo.groupMember}"/>
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="managereporinfo"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon" style="display: none;">
											<tr>
											    <td class="r" width="150">
											       <label>
														<s:text name="report.info"/>：
													</label>
												</td>
												<td colspan="3">
													<s:if test="${fn:length(labQuaManageCheckVo.reportPath)>0}">
													<a id="reportPatha" class="zPushBtn"
														href="javascript:void(0);"
														onclick="showReport();"><img
															height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="lookreport"/></b>
													</a>
												</s:if>
												<s:else>
													<s:text name="ungenerate.report"/>
												</s:else>
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable" style="margin-top: 0px;">
												<div class="Formtabletitle" >
													<span><s:text name="biz.progress"/></span>
													<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
												</div>
												<div id="contentFrame">
													<span style="background-color: #ccc;"><font
														color="red"><s:text name="use.ie8"/></font> </span>
													<iframe name="content" id="content"
														src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${labQuaManageCheckVo.id}"
														onload="document.all.content.style.height=document.content.document.body.clientHeight"
														scrolling="yes" frameborder="0" width="100%" height="460">
													</iframe>
												</div>
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
