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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAuditPlanEleForm" id="form">
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
												<span><s:text name="admin.add"/></span>
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
																<td>
																	<l:a uri="quality/planEle/addLabQuaAuditPlanEle.action" onclick="goAction('addLabQuaAuditPlanEle.action');" value="lab.code.save"/>
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
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												 <tr>
													<td class="r" width="150">
														<label>
															<s:text name="theme.depart"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.orgList" onchange="checkLab(this)"  theme="simple" listKey="id+'|'+name" listValue="name" cssStyle="width:273px;"  headerKey="" headerValue=""/>
														<input type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaAuditPlanEleVo.orgId"  />
														<input type="hidden"  id="orgName" name="labQuaAuditPlanEleVo.orgName"  />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="audittarget"/>：
														</label>
													</td>
													<td>
													<input size="40" name="labQuaAuditPlanEleVo.purpose" id="purpose" type="text"
														value="" maxlength="50"  /><font color="red">*</font>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="auditfanwei"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditPlanEleVo.range" id="range"
															type="text" value=""
															maxlength="50" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="audireaseon"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditPlanEleVo.implement" id="implement"
															type="text" value=""
															maxlength="50" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="auditstyless"/>：
														</label>
													</td>
													<td >
													       <input size="40" name="labQuaAuditPlanEleVo.auditType" id="auditType"
															type="text" value="${labQuaAuditPlanEleVo.auditType }"/>
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="interndsite"/>：
														</label>
													</td>
													<td>
														<input size="40" valType="required" msg="<s:text name="interndsite"/>不能为空" name="labQuaAuditPlanEleVo.address" id="address"
															type="text"  value="${labQuaAuditPlanEleVo.address}"/>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="auditleader"/>：
														</label>
													</td>
													<td>
														<input size="40" valType="required" msg="内审组长不能为空" name="labQuaAuditPlanEleVo.checkHead"
															id="checkHead" type="text"  value="${labQuaAuditPlanEleVo.checkHead }" />
													</td>
													<td class="r" width="150">
													       <label>
															<s:text name="auditers"/>：
															</label>
													</td>
													<td>
															<input size="40" name="labQuaAuditPlanEleVo.groupMember" id="groupMember" type="text"  value="" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="coderepeople"/>：
														</label>
													</td>
													<td >
													       <input size="40" name="labQuaAuditPlanEleVo.createPeople" id="createPeople"
															type="text" value=""/>
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="codedates"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditPlanEleVo.createTime" id="createTime" readonly="readonly" 
															type="text"  value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
													       <label>
															<s:text name="pizhunren"/>：
															</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditPlanEleVo.auditPeople" id="auditPeople" type="text"
																 value="" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="acceptdate"/>：
														</label>
													</td>
													<td>
														<input size="40" name="labQuaAuditPlanEleVo.auditTime" id="auditTime" readonly="readonly" 
															type="text"  value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="yaosuinfo"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;">
												<table class="FormtableCon_sform" id="OneTable" >
													<thead>
															<th width="50" class="c">
																<s:text name="tiaokuan"/>
															</th>
															<th width="500" class="c">
																<s:text name="elements"/>
															</th>
															<th width="20" class="c">
																<s:text name="month1"/>
															</th>
															<th width="20" class="c">
																<s:text name="month2"/>
															</th>
															<th width="20" class="c">
																<s:text name="month3"/>
															</th>
															<th width="20" class="c">
																<s:text name="month4"/>
															</th>
															<th width="20" class="c">
																<s:text name="month5"/>
															</th>
															<th width="20" class="c">
																<s:text name="month6"/>
															</th>
															<th width="20" class="c">
																<s:text name="month7"/>
															</th>
															<th width="20" class="c">
																<s:text name="month8"/>
															</th>
															<th width="20" class="c">
																<s:text name="month9"/>
															</th>
															<th width="20" class="c">
																<s:text name="month10"/>
															</th>
															<th width="20" class="c">
																<s:text name="month11"/>
															</th>
															<th width="20" class="c">
																<s:text name="month12"/>
															</th>
															<th width="100" class="c">
																<s:text name="prj.manager"/>
															</th>
															<th width="100" class="c">
																<s:text name="helpman"/>
															</th>
													</thead>
												<tbody>
												<s:if test="labQuaInitAuditPlanVoList!=null">
													<s:set name="alllist" value="labQuaInitAuditPlanVoList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td align="center" width="20">
																	<input type="text" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].ruleNum" />
																</td>
																<td  align="left">
																	${name }
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].quaInitAuditPlanId" value="${id }" />
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
																	<input type="text" size="10" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].groupLeader"/>
																</td>
																<td align="center" width="10">
																	<input type="text" size="10" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].helpPeople"/>
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
				</tr>
			</table>
		</form>
	</body>
</html>
