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
			
			#roletext {
				width: 70px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis; /* 支持 IE */
			}
			.myworkingboxttable th{
			background: #F5F5F5;
			color: #000000;
			border-top: #C0C0C0 1px solid;
			border-left: #C0C0C0 1px solid;
			border-right: #C0C0C0 1px solid;
			}
			.editType{
				visibility:hidden;
			}
			</style>
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
			function deleteOne(id){
				var planId=$('#id').val();
			   	if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){	 
			       $('form').attr('action','<%=basePath%>quality/managePlan/deleteLabQuaManageCheckRecord.action?labQuaManageCheckVo.ids='+id+'&labQuaManageCheckVo.quaManageCheckPlanId='+planId);
		      	   $('form').submit();
			    }
			}
			function showLabQuaManageCheckRecord(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/managePlan/showLabQuaManageCheckRecord.action?labQuaManageCheckVo.id='+id,
					title:'<s:property value="getText('details.info')"/>',
					opacity:0.4,
					width:900, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
		<input type="hidden" value="${labQuaManageCheckPlanVo.id}" name="labQuaManageCheckPlanVo.id" id="id" />
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
												<span><s:text name="plan.info"/>：</span>
											</div>
											<table class="FormtableCon">
												   <tr>
												<td class="r" width="150">
													<label>
														<s:text name="commtunit"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.orgName }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="auditplanname"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.name}
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="auditsite"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.address}
												</td>
											    <td class="r" width="150">
													<label>
														<s:text name="acctlescte"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.trackPeople}
												</td>
												
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="audittime"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.recTime}
												</td>
												<td class="r" width="150">
													<label>
																<s:text name="acceptreason"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.foundation}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="groupmaster"/>：
														</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.groupLeader}
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="grouppeople"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.groupMember}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
																<s:text name="plancodepeo"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.planUser}
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="planwirtedate"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.planTime}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
														<s:text name="pizhunren"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.checkPeople}
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="acceptdate"/>：
													</label>
												</td>
												<td>
													${labQuaManageCheckPlanVo.checkTime}
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
													<s:text name="penshenconte"/>：
														</label>
												</td>
												<td colspan="3">
													 <textarea readonly="readonly" rows="3" cols="50" name="labQuaManageCheckPlanVo.contents" id="contents" >${labQuaManageCheckPlanVo.contents}</textarea>
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="record.info"/></span>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;">
												<table class="FormtableCon_sform" id="OneTable" >
											<tr> 
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th>
													<s:text name="commtunit"/>
												</th>
												<th>
													<s:text name="manageauditlist"/>
												</th>
												<th property="address">
													<s:text name="auditsite"/>
												</th>
												<th property="recTime">
													<s:text name="audittime"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="labQuaManageCheckVoList!=null">
												<s:set name="alllist" value="labQuaManageCheckVoList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td class="">
															${orgName }
														</td>
														<td class="">
															${quaManageCheckPlanName }
														</td>
														<td class="">
															${address }
														</td>
														<td  class="c">
															${recTime }
														</td>
														<td class="c">
															<l:a href="#" uri="quality/managePlan/showLabQuaManageCheckRecord.action" onclick="showLabQuaManageCheckRecord('${id}');"  value="details.info" />
															&nbsp;&nbsp;
															<l:a href="#" uri="quality/managePlan/deleteLabQuaManageCheckRecord.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td colspan="7" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
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
