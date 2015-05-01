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
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAuditRecordForm" id="form">
			<input type="hidden" value="${labQuaAuditRecordVo.id}" name="labQuaAuditRecordVo.id" id="id" />
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
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>   
											</div>
											<table class="FormtableCon" style="display: none;">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="theme.depart"/>：
														</label>
													</td>
													<td class="l" width="407">
														${labQuaAuditRecordVo.labOrgName }
													</td>
													<td class="r" width="150">
													<label>
														<s:text name="interndsite"/>：
													</label>
													</td>
													<td>
														${labQuaAuditRecordVo.address}
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
												</td>
												<td class="r" width="150">
													<label>
														内&nbsp;&nbsp;审&nbsp;&nbsp;员：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.auditPeople }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														制&nbsp;&nbsp;表&nbsp;&nbsp;人：
													</label>
												</td>
												<td >
														${labQuaAuditPlanEleVo.createPeople }
												</td>
												<td class="r" width="150">
													<label>
														制表日期：
													</label>
												</td>
												<td>
													${labQuaAuditPlanEleVo.createTime }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="pizhunren"/>：
													</label>
												</td>
												<td width="380">
													${labQuaAuditRecordVo.auditPerson }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="acceptdate"/>：
													</label>
												</td>
												<td>
													${labQuaAuditRecordVo.auditDate }
												</td>
											</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="record.info"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="internchkmonth"/>：
														</label>
													</td>
													<td class="l" width="407">
														${labQuaAuditRecordVo.month }
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="internaeles"/>：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.auditPart }
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="coderepeople"/>员：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.writeMember }
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="codedates"/>：
														</label>
													</td>
													<td>
														${labQuaAuditRecordVo.writeTime }
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															审核报告发放<s:text name="msg.depart"/>：
														</label>
													</td>
													<td colspan="3">
														${labQuaAuditRecordVo.reportUnit }
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															审核情况概述：
														</label>
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40" name="labQuaAuditRecordVo.auditCondition" id="auditCondition">${labQuaAuditRecordVo.auditCondition }</textarea>
													</td>
													<td class="r" width="150">
														<label>
															内审结论：
														</label>
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40" name="labQuaAuditRecordVo.auditRecord" id="auditRecord">${labQuaAuditRecordVo.auditRecord }</textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="penshenconte"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;" >
												<table class="FormtableCon_sform" id="OneTable" >
													<thead>
															<th width="50" class="c">
																<s:text name="page.record"/>   款
															</th>
															<th width="205" align="center">
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
																				<td class="" width="205">
																					${initAuditPlanContentName }
																				</td>
																				<td colspan="6">
																					<table style="width: 100%; margin: 0px; border-bottom: 0px;border-left: 0px;border-top: 0px;border-right: 0px;" class="FormtableCon_sform">
																						<s:if test="labQuaAuditRecordDetailVoList!=null">
																							<s:set name="alllist" value="labQuaAuditRecordDetailVoList" />
																							<s:iterator value="#alllist" status="st2" id="threed">
																								<tr>
																									<td width="205" style="border-left: 0; border-top: 0;border-bottom: 0">
																										${initAuditPlanKeyName }
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																									<input type="hidden"  class="checkedVal" value="${meetStatus }"/>
																										<input disabled="disabled" onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="0" />
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																										<input disabled="disabled" onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="1" />
																									</td>
																									<td class="c" width="20" style="border-top: 0;border-bottom: 0">
																										<input disabled="disabled" onclick="changeOnly(this,'${st.index}${st1.index}${st2.index}');" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].meetStatus" type="checkbox" value="2" />
																									</td>
																									<td class="c" width="150" style="border-top: 0;border-bottom: 0">
																										<textarea disabled="disabled" name="labQuaAuditRecordVo.labQuaAuditRecordDetailVoList[${st.index}${st1.index}${st2.index}].problem" cols="30" rows="1" style="width: 150px; height: 30px;">${problem }</textarea>
																									</td>
																									<td class="c" width="340" style="border-top: 0; border-right: 0;border-bottom: 0">
																										${trackPeople }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																										${recTime }
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
