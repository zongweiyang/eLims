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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAuditRecordForm" id="form">
			<input type="hidden" value="${labQuaAuditRecordVo.id}" name="labQuaAuditRecordVo.id" id="id" />
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
                                                      				<l:a uri="quality/auditRecord/updateLabQuaAuditRecord.action" onclick="goAction('updateLabQuaAuditRecord.action?labQuaAuditRecordVo.auditResult=0');" value="lab.code.save"/>
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="quality/auditRecord/updateLabQuaAuditRecord.action" onclick="goAction('updateLabQuaAuditRecord.action?labQuaAuditRecordVo.auditResult=1');" value="save.commit"/>
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
													<input size="40" type="hidden" name="labQuaAuditRecordVo.auditPart" value="${labQuaAuditRecordVo.auditPart}" id="lab" readonly="readonly" style="background-color:#e8e8e8;"/>
												</td>
												<td class="r" width="150">
													<label>
														内审计划：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.quaAuditPlanEleName}
													<input size="40" type="hidden" name="labQuaAuditRecordVo.quaAuditPlanEleName" value="${labQuaAuditRecordVo.quaAuditPlanEleName}" id="quaAuditPlanEleName" onclick="showLabQuaAuditPlanEle4select();"/>
													<input size="40" type="hidden" name="labQuaAuditRecordVo.quaAuditPlanEleId" value="${labQuaAuditRecordVo.quaAuditPlanEleId}" id="quaAuditPlanEleId" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														被审<s:text name="msg.depart"/>：
													</label>
												</td>
												<td>
													<s:select list="labOrgVoList" headerKey="" cssStyle="width:150px;" headerValue="--请选择--"  value="'${labQuaAuditRecordVo.labOrgId}|${labQuaAuditRecordVo.labOrgName }'" id="unitOrgId" theme="simple"
													listKey="id+'|'+name" listValue="name" onchange="selectChange(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择被审核部门" name="labQuaAuditRecordVo.labOrgId" value="${labQuaAuditRecordVo.labOrgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaAuditRecordVo.labOrgName"  value="${labQuaAuditRecordVo.labOrgName }" />
												</td>
												<td class="r" width="150">
													<label>
														内审地点：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAuditRecordVo.address" id="address"
														type="text"  value="${labQuaAuditRecordVo.address}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														内审组长：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAuditRecordVo.checkHead"
														id="checkHead" type="text"  value="${labQuaAuditRecordVo.checkHead }" />
												</td>
												<td class="r" width="150">
													<label>
														内&nbsp;&nbsp;审&nbsp;&nbsp;员：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAuditRecordVo.auditPeople" id="auditPeople"
														type="text" value="${labQuaAuditRecordVo.auditPeople }" maxlength="32" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														审核报告发放<s:text name="msg.depart"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text"  name="labQuaAuditRecordVo.reportUnit" id="reportUnit" value="${labQuaAuditRecordVo.reportUnit }"/>
												</td>
												<td class="r" width="150">
													<label>
														内审月份：
													</label>
												</td>
												<td>
													<s:select name="labQuaAuditRecordVo.month" id="month"
														list="labCodeVoList" theme="simple" listKey="code" listValue="name"  onchange="getList();" cssStyle="width:150px;" headerKey="" headerValue="-请选择-"></s:select>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="coderepeople"/>员：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAuditRecordVo.writeMember"
														id="checkMember" type="text"  value="${labQuaAuditRecordVo.writeMember }" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAuditRecordVo.writeTime" id="writeTime"
														readonly="readonly" type="text"  value="${labQuaAuditRecordVo.writeTime }"
														class="Wdate"
														onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="auditor"/>：
													</label>
												</td>
												<td width="380">
													<input size="40" type="text"  name="labQuaAuditRecordVo.auditPerson" id="auditPerson" value="${labQuaAuditRecordVo.auditPerson }"/>
												</td>
												<td class="r" width="150">
													<label>
														审核日期：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labQuaAuditRecordVo.auditDate" id="auditDate" value="${labQuaAuditRecordVo.auditDate }" class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														审核情况概述：
													</label>
												</td>
												<td colspan="3">
													<textarea rows="3" cols="50" name="labQuaAuditRecordVo.auditCondition" id="auditCondition">${labQuaAuditRecordVo.auditCondition }</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														内审结论：
													</label>
												</td>
												<td colspan="3">
													<textarea rows="3" cols="50" name="labQuaAuditRecordVo.auditRecord" id="auditRecord">${labQuaAuditRecordVo.auditRecord }</textarea>
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>评审内容</span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;">
												<table class="myworkingboxttable" id="OneTable" >
													<thead>
															<th width="50" class="c">
																<s:text name="page.record"/>   款
															</th>
															<th width="" align="center">
																检查内容
															</th>
															<th width="205">
																检查方法
															</th>
															<th width="30">
																符合
															</th>
															<th width="30">
																基本符合
															</th>
															<th width="30">
																不符合
															</th>
															<th width="155">
																存在问题
															</th>
															<th width="340">
																整改情况(跟踪人、时间)
															</th>
														</thead>
														<tbody>
															<s:if test="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList!=null">
																<s:set name="alllist" value="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList" />
																<s:iterator value="#alllist" status="st" id="first">
																	<tr>
																		<td align="left" colspan="8">
																			${initAuditPlanName }
																		</td>
																	</tr>
																	<s:if test="labQuaAuditRecordDetailVoList!=null">
																		<s:set name="alllist" value="labQuaAuditRecordDetailVoList" />
																		<s:iterator value="#alllist" status="st1" id="sec">
																			<tr>
																				<td class="c" width="20">
																					${st.index+1}.${st1.index+1}
																				</td>
																				<td class="">
																					${initAuditPlanContentName }
																				</td>
																				<td colspan="6">
																					<table style="width: 100%; margin: 0px; border-bottom: 0px;border-left: 0px;border-top: 0px;border-right: 0px;" class="myworkingboxttable">
																						<s:if test="labQuaAuditRecordDetailVoList!=null">
																							<s:set name="alllist" value="labQuaAuditRecordDetailVoList" />
																							<s:iterator value="#alllist" status="st2" id="threed">
																								<tr>
																									<td width="205" style="border-left: 0; border-top: 0;border-bottom: 0">
																										${initAuditPlanKeyName }
																										<input type="hidden" size="3" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].initAuditPlanId" value="${first.initAuditPlanId }" />
																										<input type="hidden" size="3" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].initAuditPlanContentId" value="${sec.initAuditPlanContentId }" />
																										<input type="hidden" size="3" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].initAuditPlanKeyId" value="${threed.initAuditPlanKeyId }" />
																										<input type="hidden" size="3" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].id" value="${threed.id }" />
																										<input type="hidden"  class="checkedVal" value="${meetStatus }"/>
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																										<input onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="0" />
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																										<input onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="1" />
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																										<input onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="2" />
																									</td>
																									<td class="c" width="150" style="border-top: 0;border-bottom: 0">
																										<textarea name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].problem" cols="30" rows="1" style="width: 150px; height: 30px;">${problem }</textarea>
																									</td>
																									<td class="c" width="340" style="border-top: 0; border-right: 0;border-bottom: 0">
																										<input name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].trackPeople" id="trackPeople" type="text"  value="${trackPeople }" />
																										<input name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].recTime" id="recTime" readonly="readonly" type="text"  value="${recTime }" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
																									</td>
																								</tr>
																							</s:iterator>
																						</s:if>
																					</table>

																				</td>

																			</tr>
																		</s:iterator>
																	</s:if>
																</s:iterator>
															</s:if>
															<s:else>
																<tr>
																	<td colspan="8" align="center" valign="middle">
																		<font color="red"><s:text name="lab.msg.none"/></font>
																	</td>
																</tr>
															</s:else>
														</tbody>
												</table>
											</div>
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
