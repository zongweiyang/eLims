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
			function showReport(){
				var height = window.screen.height-250;
				var url  = '${basePath}quality/auditRecord/showLabReport4Bus.action?labReportVo.path=${labQuaAuditRecordVo.reportPath}&labReportVo.id=${labQuaAuditRecordVo.reportTempId}&labReportVo.busInsId=${labQuaAuditRecordVo.id}';
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
			<input type="hidden" name="labQuaAuditRecordVo.auditCondition" id="auditCondition" value="${labQuaAuditRecordVo.auditCondition }"/>
			<input type="hidden" name="labQuaAuditRecordVo.auditRecord" id="auditRecord" value="${labQuaAuditRecordVo.auditRecord }"/>
			<input type="hidden" size="57" name="labQuaAuditRecordVo.reportUnit" id="reportUnit" value="${labQuaAuditRecordVo.reportUnit }"/>
			<input type="hidden" size="57" name="labQuaAuditRecordVo.auditPerson" id="auditPerson" value="${labQuaAuditRecordVo.auditPerson }"/>
			<input type="hidden" name="labQuaAuditRecordVo.auditTime" id="auditTime" value="${labQuaAuditRecordVo.auditTime }" />
			<input type="hidden" name="labQuaAuditRecordVo.reportPath" id="reportPath" value="${labQuaAuditRecordVo.reportPath }" />
			<input type="hidden" name="labQuaAuditRecordVo.reportTempId" id="reportTempId" value="${labQuaAuditRecordVo.reportTempId }" />
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
                                                      				<l:a uri="quality/auditRecord/updateLabQuaFile.action" onclick="goAction('updateLabQuaFile.action?labQuaAuditRecordVo.auditResult=0');" value="lab.code.save"/>
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="quality/auditRecord/updateLabQuaFile.action" onclick="goAction('updateLabQuaFile.action?labQuaAuditRecordVo.auditResult=1');" value="save.commit"/>
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
												<span><s:text name="base.info"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon">
										 		<tr>
													<td class="r" width="150">
														<label>
															<s:text name="internaeles"/>：
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
															<s:text name="intendsdsplan"/>：
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
															<s:text name="auditdeparted"/>：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.labOrgName }
														<input type="hidden"  id="orgId" name="labQuaAuditRecordVo.labOrgId" value="${labQuaAuditRecordVo.labOrgId }"  />
														<input type="hidden"  id="orgName" name="labQuaAuditRecordVo.labOrgName"  value="${labQuaAuditRecordVo.labOrgName }" />
													</td>
													<td class="r" width="150">
													<label>
														<s:text name="interndsite"/>：
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
														<s:text name="auditleader"/>：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.checkHead }
													<input name="labQuaAuditRecordVo.checkHead"
														id="checkHead" type="hidden"  value="${labQuaAuditRecordVo.checkHead }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="innealauditedd"/>：
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
														<s:text name="coderepeople"/>：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.writeMember }
													<input name="labQuaAuditRecordVo.writeMember"
														id="checkMember" type="hidden"  value="${labQuaAuditRecordVo.writeMember }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.writeTime }
													<input name="labQuaAuditRecordVo.writeTime" id="writeTime"
														readonly="readonly" type="hidden"  value="${labQuaAuditRecordVo.writeTime }" />
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
														<s:text name="auditdatess"/>：
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
														<s:text name="auditinfoasfd"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea rows="3" readonly="readonly" cols="50" name="labQuaAuditRecordVo.auditCondition" id="auditCondition">${labQuaAuditRecordVo.auditCondition }</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="internlcloudi"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea rows="3" readonly="readonly" cols="50" name="labQuaAuditRecordVo.auditRecord" id="auditRecord">${labQuaAuditRecordVo.auditRecord }</textarea>
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
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
												<td colspan="3">
													<s:if test="${fn:length(labQuaAuditRecordVo.reportPath)>0}">
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
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="internreportachi"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
										 		<tr>
												    <td class="r" width="150">
													       <label>
														<s:text name="docdesc"/>：
															</label>
													</td>
													<td colspan="3">
														 <textarea rows="5" cols="50" name="labQuaAuditRecordVo.remark" id="remark" >${labQuaAuditRecordVo.remark}</textarea>
													</td>
												</tr>
												<tr>
												    <td class="r" width="150">
														<label>
															<s:text name="coderman"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditRecordVo.filePeople" id="filePeople" type="text"
															 value="${labQuaAuditRecordVo.filePeople}" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="docerdate"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditRecordVo.fileTime" id="fileTime" type="text"
															 value="${labQuaAuditRecordVo.fileTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
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
