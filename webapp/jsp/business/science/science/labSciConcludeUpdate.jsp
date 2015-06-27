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
								<span><s:text name="over.item"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
							<input type="hidden" name="labSciScienceVo.processInsId"
								value="${labSciScienceVo.processInsId}" />
							<input type="hidden" name="messageInfo" value="6" />
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
												onclick="goAction('science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab2');"><span><s:text name="charge.details"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab3');"><span><s:text name="prj.result"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab4');"><span><s:text name="procedure.detail"/></span>
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
													<td class="l" width="407">
														${labSciScienceVo.name}
														<input size="40" type="hidden" name="labSciScienceVo.name"
															maxlength="16" value="${labSciScienceVo.name}" id="name"
															/>
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="appleypeol"/>：
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
																	<s:text name="start.time"/>：
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
															<s:text name="yilailab"/>：
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
														<s:text name="jingshouren"/>
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
												<tr>
													<td width="275" class="c">
														<font color="red"><s:text name="reaminfund"/>：</font>
													</td>
													<td colspan="3">
														<label id="count" style="margin-left: 205px;color: red;">${labSciScienceVo.paperFunds}</label><font color="red">&nbsp;&nbsp;万元</font>
														<input type="hidden" name="labSciScienceVo.paperFunds"
															maxlength="16" value="${labSciScienceVo.paperFunds}"
															id="paperFunds" readonly="true" disabled="true" />
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="prj.result"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<s:text name="lab.yes"/>否over.item：
													</td>
													<td colspan="3">
														<input type="hidden" name="labSciScienceVo.isSeized" value="${labSciScienceVo.isSeized}" />
															<s:radio id="isKnot" name="labSciScienceVo.isKnot" value="'${labSciScienceVo.isKnot}'" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" theme="simple" cssStyle="border: 0" ></s:radio> 
													</td>
												</tr> 
												<tr align="center">
													<td class="r" width="150">
														创新之处：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.discovery"
															value="${labSciScienceVo.discovery}" valType="strLength"
															max="512" strLength-msg="创新之处不能超过512位">${labSciScienceVo.discovery}</textarea>
													</td>
													<td class="r" width="150">
														达到的目标：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.quota"
															value="${labSciScienceVo.quota}"
															valType="strLength" max="512"
															strLength-msg="达到的目标不能超过512位">${labSciScienceVo.quota}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														意&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;义：
													</td>
													<td>
														<textarea rows="2" cols="40" name="labSciScienceVo.meaning"
															value="${labSciScienceVo.meaning}" valType="strLength"
															max="512" strLength-msg="意义不能超过512位">${labSciScienceVo.meaning}</textarea>
													</td>
													<td class="r" width="150">
														应用前景：
													</td>
													<td>
														<textarea rows="2" cols="40"
															name="labSciScienceVo.prospect"
															value="${labSciScienceVo.prospect}" valType="strLength"
															max="512" strLength-msg="应用前景不能超过512位">${labSciScienceVo.prospect}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														存在的问题：
													</td>
													<td colspan="3">
														<textarea rows="2" cols="40" name="labSciScienceVo.problem"
															value="${labSciScienceVo.problem}" valType="strLength"
															max="512" strLength-msg="存在的问题不能超过512位" >${labSciScienceVo.problem}</textarea>
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
