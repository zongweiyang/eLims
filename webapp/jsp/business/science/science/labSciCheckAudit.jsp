<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>

		<script language=javascript> 
			  	function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
				}
	</script>
	</head>
	<body class="" id="mainid">
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="flow.check"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
							<input type="hidden" name="labSciScienceVo.processInsId"
								value="${labSciScienceVo.processInsId}" />
							<input type="hidden" name="labSciScienceVo.isKnot"
							value="${labSciScienceVo.isKnot}" />
							<input type="hidden" name="labSciScienceVo.isSeized"
							value="${labSciScienceVo.isSeized}" />
							<input type="hidden" name="messageInfo" value="5" />
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<l:a uri="back" value="msg.back" />
													</td>
													<td>
														<l:a
															uri="science/labScience/updateLabScience.action"
															value="post.commit" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="TabTable">
								<div class="TabTableNAV">
									<ul>
										<li id="li01" class="currenttab">
											<a href="javascript:;"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/updateLabScience.action?formName=tab2');"><span><s:text name="charge.details"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/updateLabScience.action?formName=tab3');"><span><s:text name="prj.result"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/updateLabScience.action?formName=tab4');"><span><s:text name="procedure.detail"/></span>
											</a>
										</li>
									</ul>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="prj.base.info"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon" id="content">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="item.name"/>：
														</label>
													</td>
													<td  class="l" width="407">
														${labSciScienceVo.name}
														<input size="40" type="hidden" name="labSciScienceVo.name"
															maxlength="16" value="${labSciScienceVo.name}" id="name"
															/>
													</td>
													<td class="r" width="150">
														<label>
															申&nbsp;&nbsp;请&nbsp;&nbsp;人：
														</label>
													</td>
													<td>
														${labSciScienceVo.masterName}
														<input size="40" type="hidden" name="labSciScienceVo.masterName"
															maxlength="16" value="${labSciScienceVo.masterName}"
															id="masterName" onclick="selectPerson('0','null');return false;"
															readonly="true"/>
														<input type="hidden" name="labSciScienceVo.masterId"
															maxlength="16" value="${labSciScienceVo.masterId}"
															id="masterId" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															起止时间：
														</label>
													</td>
													<td>
														<input type="hidden" name="labSciScienceVo.startDate"
															value="${labSciScienceVo.startDate}" id="startDate"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});"
															/>
														${labSciScienceVo.startDate}  &nbsp;&nbsp;  至  &nbsp;&nbsp;  ${labSciScienceVo.endDate}  
														<input type="hidden" name="labSciScienceVo.endDate"
															value="${labSciScienceVo.endDate}" id="endDate"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" 
															/>
													</td>
													<td class="r" width="150">
														<label>
															依托实验室：
														</label>
													</td>
													<td>
														${labSciScienceVo.labOrgName}
														<input size="40" type="hidden" name="labSciScienceVo.labOrgName"
															maxlength="32" value="${labSciScienceVo.labOrgName}"
															id="labOrgName" />
															<input size="40" type="hidden" name="labSciScienceVo.labOrgId"
															maxlength="32" value="${labSciScienceVo.labOrgId}"
															id="labOrgId" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="approval.fee"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.ratifyFunds}
														<input type="hidden" name="labSciScienceVo.ratifyFunds"
															maxlength="32" value="${labSciScienceVo.ratifyFunds}"
															id="ratifyFunds" max="32"
															disabled="true" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="yiboyinfei"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.inFunds}
														<input type="hidden" name="labSciScienceVo.inFunds"
															maxlength="32" value="${labSciScienceVo.inFunds}"
															id="inFunds" valType="strLength" max="32"
															disabled="true" />
													</td>
												</tr>

											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemfeeinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon_sform">
												<thead>
													<th width="18%" class="c">
														<s:text name="paying.out.name"/>
													</th>
													<th width="18%" class="c">
														金额
													</th>
													<th width="18%" class="c">
														经手人
													</th>
													<th width="18%" class="c">
														<s:text name="remark"/>
													</th>
												</thead>

												<s:if test="labSciScienceVo.labSciFundsList!=null">
													<s:set name="labSciFundsList"
														value="labSciScienceVo.labSciFundsList" />
													<s:iterator value="#labSciFundsList" status="ind">
														<tr id="tr0">
															<td class="c">
																${name}
															</td>
															<td class="r">
																${money}&nbsp;&nbsp;万元
															</td>
															<td class="c">
																${user}
															</td>
															<td class="c">
																${remark}
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="3" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
												<tr>
													<td width="275" class="c">
														<font color="red">总计：</font>
													</td>
													<td  colspan="3">
														<label id="count" style="margin-left: 205px;color: red;">${labSciScienceVo.outFunds}</label><font color="red">&nbsp;&nbsp;万元</font>
														<input type="hidden" name="labSciScienceVo.outFunds"
																	maxlength="16" value="${labSciScienceVo.outFunds}"
																	id="applyFunds" readonly="true" />
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemchk"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														研究进度：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.schedule"
															value="${labSciScienceVo.schedule}"
															valType="strLength" max="512"
															strLength-msg="研究进度不能超过512位">${labSciScienceVo.schedule}</textarea>
													</td>
													<td class="r" width="150">
														取得成果：
													</td>
													<td>
														<textarea rows="2" cols="40" name="labSciScienceVo.achieved"
															value="${labSciScienceVo.achieved}" valType="strLength"
															max="512" strLength-msg="取得成果不能超过512位">${labSciScienceVo.achieved}</textarea>
													</td>
												</tr>
												<tr>
													<td  class="r" width="150">
														计划完成情况：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.isPlan"
															value="${labSciScienceVo.isPlan}" valType="strLength"
															max="512" strLength-msg="计划完成情况不能超过512位">${labSciScienceVo.isPlan}</textarea>
													</td>
													<td class="r" width="150">
														存在问题和改进措施：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.question"
															value="${labSciScienceVo.question}" valType="strLength"
															max="512" strLength-msg="存在问题和改进措施不能超过512位">${labSciScienceVo.question}</textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemchkauditinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon">
												<tr>
													<td width="150" class="r">
														<label>
															<s:text name="yes.no"/><s:text name="flow.pass"/>：
														</label>
													</td>
													<td class="l" width="407">
														<input style="border: 0" type="radio" name="labSciScienceVo.auditResult"
															value="3" checked="checked" />
														<s:text name="lab.yes"/>
														<input style="border: 0" type="radio" name="labSciScienceVo.auditResult"
															value="-2" />
														<s:text name="lab.no"/>
													</td>
													<td width="150" class="r">
														<label>
															<s:text name="sudited.sound"/>：
														</label>
													</td>
													<td>
														<textarea rows="3" cols="40"
															name="labSciScienceVo.auditMessage"></textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
						</form>
					</div>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
