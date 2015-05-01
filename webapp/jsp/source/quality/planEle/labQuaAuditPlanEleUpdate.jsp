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
		<form action="" method="post" name="labChargeForm" id="form">
			<input type="hidden" value="${labQuaAuditPlanEleVo.id}" name="labQuaAuditPlanEleVo.id" id="id" />
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
                                                      				<l:a uri="quality/planEle/updateLabQuaAuditPlanEle.action" onclick="goAction('updateLabQuaAuditPlanEle.action');" value="lab.code.save"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable" id="Tab01">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
										<table class="FormtableCon">
											<tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													<s:select list="#request.orgList"  theme="simple"   name="labQuaAuditPlanEleVo.orgId" value="${labQuaAuditPlanEleVo.orgId}" listKey="id" listValue="name" cssStyle="width:273px;"/>
												</td>
												<td class="r" width="150">
													<label>
														审核目的：
													</label>
												</td>
												<td>
												<input size="40"  name="labQuaAuditPlanEleVo.purpose" id="purpose" type="text"
													valType="required" msg="审核目的不能为空"  value="${labQuaAuditPlanEleVo.purpose }" maxlength="50"  />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														审核范围：
													</label>
												</td>
												<td>
													<input size="40"  name="labQuaAuditPlanEleVo.range" id="range"
														type="text" value="${labQuaAuditPlanEleVo.range }"
														maxlength="50" />
												</td>
												<td class="r" width="150">
													<label>
														审核依据：
													</label>
												</td>
												<td>
													<input size="40"  name="labQuaAuditPlanEleVo.implement" id="implement"
														type="text" value="${labQuaAuditPlanEleVo.implement }"
														maxlength="50" />
												</td>
											</tr>
											<tr>
													<td class="r" width="150">
														<label>
															审核方式：
														</label>
													</td>
													<td >
													       <input size="40" name="labQuaAuditPlanEleVo.auditType" id="auditType"
															type="text" value="${labQuaAuditPlanEleVo.auditType }"/>
													</td>
													<td class="r" width="150">
														<label>
															内审地点：
														</label>
													</td>
													<td>
														<input size="40" valType="required" msg="内审地点不能为空" name="labQuaAuditPlanEleVo.address" id="address"
															type="text"  value="${labQuaAuditPlanEleVo.address}"/>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															内审组长：
														</label>
													</td>
													<td>
														<input size="40" valType="required" msg="内审组长不能为空" name="labQuaAuditPlanEleVo.checkHead"
															id="checkHead" type="text"  value="${labQuaAuditPlanEleVo.checkHead }" />
													</td>
													<td class="r" width="150">
													       <label>
															审核员：
															</label>
													</td>
													<td>
															<input size="40" name="labQuaAuditPlanEleVo.groupMember" id="groupMember" type="text"  value="${labQuaAuditPlanEleVo.groupMember }" />
													</td>
												</tr>
											<tr>
												<td class="r" width="150">
													<label>
														编&nbsp;&nbsp;制&nbsp;&nbsp;人：
													</label>
												</td>
												<td >
												       <input size="40"  name="labQuaAuditPlanEleVo.createPeople" id="createPeople"
														type="text" value="${labQuaAuditPlanEleVo.createPeople }"/>
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="codedates"/>：
													</label>
												</td>
												<td>
													<input size="40"  name="labQuaAuditPlanEleVo.createTime" id="createTime" readonly="readonly" 
														type="text"  value="${labQuaAuditPlanEleVo.createTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
												<tr>
													<td class="r" width="150">
													       <label>
															批准人：
															</label>
													</td>
													<td>
														<input size="40"  name="labQuaAuditPlanEleVo.auditPeople" id="auditPeople" type="text"
																 value="${labQuaAuditPlanEleVo.auditPeople }" />
													</td>
													<td class="r" width="150">
														
														<label>
															批准日期：
														</label>
													</td>
													<td>
														<input size="40"  name="labQuaAuditPlanEleVo.auditTime" id="auditTime" readonly="readonly" 
															type="text"  value="${labQuaAuditPlanEleVo.auditTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
		                  				<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="yaosuinfo"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div id="Tab01" style="margin-top: 10px; margin-bottom: 10px;">
												<table class="FormtableCon_sform" id="OneTable" >
													<thead>
															<th width="50" class="c">
																<s:text name="page.record"/>款
															</th>
															<th width="500" class="c">
																要素
															</th>
															<th width="20" class="c">
																一月
															</th>
															<th width="20" class="c">
																二月
															</th>
															<th width="20" class="c">
																三月
															</th>
															<th width="20" class="c">
																四月
															</th>
															<th width="20" class="c">
																五月
															</th>
															<th width="20" class="c">
																六月
															</th>
															<th width="20" class="c">
																七月
															</th>
															<th width="20" class="c">
																八月
															</th>
															<th width="20" class="c">
																九月
															</th>
															<th width="20" class="c">
																十月
															</th>
															<th width="20" class="c">
																十一月
															</th>
															<th width="20" class="c">
																十二月
															</th>
															<th width="100" class="c">
																<s:text name="prj.manager"/>
															</th>
															<th width="100" class="c">
																协助人
															</th>
													</thead>
												<tbody>
												<s:if test="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList!=null">
													<s:set name="alllist" value="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td align="center" width="20">
																	<input type="text" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].ruleNum" value="${ruleNum }" />
																</td>
																<td  align="left">
																	${quaInitAuditPlanName }
																	<input type="hidden"  class="month" value="${month }"/>
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].quaAuditPlanEleId" value="${quaAuditPlanEleId }" />
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].id" value="${id }" />
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].quaInitAuditPlanId" value="${quaInitAuditPlanId }" />
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="01"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="02"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="03"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="04"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="05"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="06"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="07"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="08"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="09"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="10"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="11"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" type="checkbox" id="" value="12"/>
																</td>
																<td align="center" width="10">
																	<input type="text" size="10" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].groupLeader" value="${groupLeader }"/>
																</td>
																<td align="center" width="10">
																	<input type="text" size="10" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].helpPeople" value="${helpPeople }"/>
																</td>
															</tr>
															</s:iterator>
														</s:if>
														<s:else>
															<tr>
																<td colspan="14" align="center" valign="middle">
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
				var _val = $(this).find('.month').val().split(', ');
				for(var i=0;i<_val.length;i++){
					if(_val[i] != ''){
						$(this).find('input[type="checkbox"]:eq('+(parseInt(_val[i])-1)+')').attr({'checked':'checked'});
					}
				}
			});
		});
	</script>
</html>		
