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
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#orgId').val(oo[0]);
				$('#orgName').val(oo[1]);
			}
			function showLabQuaAuditPlanEle4select(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/auditRecord/showLabQuaAuditPlanEle4select.action',
					title:'<s:property value="getText('internchecklist')"/>',
					opacity:0.4,
					width:900, 
					height:400,
					lock:true,
					max:false,
					min:false
				 });
			}
			function changeOnly(obj,name){
				var key=$(obj).attr('checked');
				$('input[name*="'+name+'"]').each(function (){
					$(this).removeAttr('checked');
				});
				if(key){
					$(obj).attr('checked','checked');
					$(obj).attr('checked',true);
				}
			}
			function showReport(flag){
				var tempId=$('#tempId').val();
				var auditCondition = $('#auditCondition').val();
				var auditRecord = $('#auditRecord').val();
				var reportUnit = $('#reportUnit').val();
				var auditPerson = $('#auditPerson').val();
				var auditTime = $('#auditTime').val();
				if(tempId==''){
					alert('<s:property value="getText('modleseletconfig')"/>');
					return false;
				}
				var height = window.screen.height-250;
				var url  = '${basePath}quality/auditRecord/addLabReport4Bus.action?labReportVo.editType=1&labReportVo.path=${labQuaAuditRecordVo.reportPath}&labReportVo.busInsId=${labQuaAuditRecordVo.id}&labReportVo.id='+tempId+'&labReportVo.isNew='+flag+'&labQuaAuditRecordVo.auditCondition='+auditCondition+'&labQuaAuditRecordVo.auditRecord='+auditRecord+'&labQuaAuditRecordVo.reportUnit='+reportUnit+'&labQuaAuditRecordVo.auditPerson='+auditPerson+'&labQuaAuditRecordVo.auditTime='+auditTime;
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
		<form action="" method="post" name="labQuaAuditRecordForm" id="form">
			<input type="hidden" value="${labQuaAuditRecordVo.id}" name="labQuaAuditRecordVo.id" id="id" />
			<input type="hidden" value="${labQuaAuditRecordVo.month}" name="labQuaAuditRecordVo.month" id="month" />
			<input type="hidden" name="labQuaAuditRecordVo.reportPath" id="reportPath" value="${labQuaAuditRecordVo.reportPath }" />
			<input type="hidden"  name="labQuaAuditRecordVo.reportUnit" id="reportUnit" value="${labQuaAuditRecordVo.reportUnit }"/>
			<input type="hidden" value="${labQuaAuditRecordVo.reportNo}" name="labQuaAuditRecordVo.reportNo" id="reportNo" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="lab.code.modify"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
																	<l:a uri="back" value="msg.back"/> 
																</td>
                                                      			<td>
                                                      				<l:a uri="quality/auditRecord/updateLabQuaAuditReport.action" onclick="goAction('updateLabQuaAuditReport.action?labQuaAuditRecordVo.auditResult=0');" value="lab.code.save"/>
                                                      			</td>
                                                      			<!-- <td>
                                                      				<l:a uri="quality/auditRecord/updateLabQuaAuditReport.action" onclick="goAction('updateLabQuaAuditReport.action?labQuaAuditRecordVo.auditResult=1');" value="save.commit"/>
                                                      			</td> -->
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
										 		<tr>
													<td class="r" width="150">
														<label>
															内审要素：
														</label>
													</td>
													<td>
														<s:if test="${labQuaAuditRecordVo.auditPart == 'null' }">
															<label></label>
											 			</s:if>
														<s:else>
															${labQuaAuditRecordVo.auditPart }
														</s:else>
														<input type="hidden" name="labQuaAuditRecordVo.auditPart" value="${labQuaAuditRecordVo.auditPart}" id="lab" readonly="readonly" style="background-color:#e8e8e8;"/>
													</td>
													<td class="r" width="150">
														<label>
															内审计划：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.quaAuditPlanEleName}
														<input type="hidden" name="labQuaAuditRecordVo.quaAuditPlanEleName" value="${labQuaAuditRecordVo.quaAuditPlanEleName}" id="quaAuditPlanEleName" onclick="showLabQuaAuditPlanEle4select();"/>
														<input type="hidden" name="labQuaAuditRecordVo.quaAuditPlanEleId" value="${labQuaAuditRecordVo.quaAuditPlanEleId}" id="quaAuditPlanEleId" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															被审<s:text name="msg.depart"/>：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.labOrgName }
														<input type="hidden"  id="orgId" name="labQuaAuditRecordVo.labOrgId" value="${labQuaAuditRecordVo.labOrgId }"  />
														<input type="hidden"  id="orgName" name="labQuaAuditRecordVo.labOrgName"  value="${labQuaAuditRecordVo.labOrgName }" />
													</td>
													<td class="r" width="150">
													<label>
														内审地点：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.address}
														<input name="labQuaAuditRecordVo.address" id="address"
														type="hidden"  value="${labQuaAuditRecordVo.address}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														内审组长：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.checkHead }
													<input name="labQuaAuditRecordVo.checkHead"
														id="checkHead" type="hidden"  value="${labQuaAuditRecordVo.checkHead }" />
												</td>
												<td class="r" width="150">
													<label>
														审&nbsp;&nbsp;核&nbsp;&nbsp;员：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.auditPeople }
													<input name="labQuaAuditRecordVo.auditPeople" id="auditPeople"
														type="hidden" value="${labQuaAuditRecordVo.auditPeople }" maxlength="32" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
													<label>
														编&nbsp;&nbsp;制&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.writeMember}
													<input name="labQuaAuditRecordVo.writeMember" id="writeMember" type="hidden"
														 value="${labQuaAuditRecordVo.writeMember}" />
												</td>
												<td class="r" width="300">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.writeTime}
													<input name="labQuaAuditRecordVo.writeTime" id="writeTime" type="hidden"
														 value="${labQuaAuditRecordVo.writeTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="auditor"/>：
													</label>
												</td>
												<td width="380">
													${labQuaAuditRecordVo.auditPerson }
													<input type="hidden"  name="labQuaAuditRecordVo.auditPerson" id="auditPerson" value="${labQuaAuditRecordVo.auditPerson }"/>
												</td>
												<td class="r" width="150">
													<label>
														审核日期：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.auditDate }
													<input type="hidden"  name="labQuaAuditRecordVo.auditDate" id="auditDate" value="${labQuaAuditRecordVo.auditDate }"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														审核情况概述：
													</label>
												</td>
												<td>
													<textarea rows="2" readonly="readonly" cols="40" name="labQuaAuditRecordVo.auditCondition" id="auditCondition">${labQuaAuditRecordVo.auditCondition }</textarea>
												</td>
												<td class="r" width="150">
													<label>
														内审结论：
													</label>
												</td>
												<td>
													<textarea rows="2" readonly="readonly" cols="40" name="labQuaAuditRecordVo.auditRecord" id="auditRecord">${labQuaAuditRecordVo.auditRecord }</textarea>
												</td>
											</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
		                  				<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="report.preview"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="report.info"/>：
														</label>
													</td>
													<td width="100">
														<s:select list="#request.tempList" id="tempId"
															listKey="id" listValue="title" theme="simple"
															name="labQuaAuditRecordVo.reportTempId"></s:select>
													</td>
													<td>
														<a id="reportPatha" class="zPushBtn"
															href="javascript:void(0);"
															onclick="showReport('');"><img
																height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="reportprint"/></b>
														</a>
														<s:if test="${fn:length(labQuaAuditRecordVo.reportPath)>0}">
															<a class="zPushBtn"
																href="javascript:void(0);"
																onclick="if(confirm('<s:property value="getText('regeneteatesd')"/>')){showReport('1');}"><img
																	height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="re.generate"/></b>
															</a>
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="archiinfo"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												<tr>
												    <td class="r" width="150">
													       <label>
																<s:text name="lab.yes"/>否存档：
															</label>
													</td>
													<td colspan="3" > 
														<s:if test="${labQuaAuditRecordVo.isFile == '' || labQuaAuditRecordVo.isFile == null }">
															<s:radio cssStyle="border:0 " theme="simple" list="#{'0':getText('lab.yes'),'1':getText('lab.no')}" value="'1'" name="labQuaAuditRecordVo.isFile"></s:radio>
														</s:if>
														<s:else>
															<s:radio cssStyle="border:0 " theme="simple" list="#{'0':getText('lab.yes'),'1':getText('lab.no')}" value="'${labQuaAuditRecordVo.isFile}'" name="labQuaAuditRecordVo.isFile"></s:radio>
														</s:else>
													</td>
												</tr>
												<tr>
												    <td class="r" width="150">
													       <label>
														存档说明：
															</label>
													</td>
													<td colspan="3">
														 <textarea rows="3" cols="40" name="labQuaAuditRecordVo.remark" id="remark" >${labQuaAuditRecordVo.remark}</textarea>
													</td>
												</tr>
												<tr>
												    <td class="r" width="150">
														<label>
															归档人：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditRecordVo.filePeople" id="filePeople" type="text"
															 value="${labQuaAuditRecordVo.filePeople}" />
													</td>
													<td class="r" width="240">
														<label>
															归档日期：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditRecordVo.fileTime" id="fileTime" type="text"
															 value="${labQuaAuditRecordVo.fileTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
											</table>
									</div>
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
			$('#OneTable tbody tr').each(function(){
				var _val = $(this).find('.checkedVal').val();
				if(_val!=''&&_val!=undefined){
					$(this).find('input[type="checkbox"]:eq('+parseInt(_val)+')').attr({'checked':'checked'});
				}
			});
		});
	</script>	
</html>		
