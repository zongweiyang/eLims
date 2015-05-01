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
								<span><s:text name="look.check"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
							<input type="hidden" name="labSciScienceVo.processInsId"
								value="${labSciScienceVo.processInsId}" />
							<input type="hidden" name="messageInfo" value="4" />
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
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab2');"><span><s:text name="charge.details"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab3');"><span><s:text name="prj.result"/></span>
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
													<td rowspan="4">
														<s:text name="prj.base.info"/>
													</td>
													<td>
														<label>
															<s:text name="item.name"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.name"
															maxlength="16" value="${labSciScienceVo.name}" id="name"
															disabled="true" />
													</td>
													<td>
														<label>
															<s:text name="prj.manager"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.masterName"
															maxlength="16" value="${labSciScienceVo.masterName}"
															id="masterName" onclick="selectPerson('0');return false;"
															disabled="true" />
														<input type="hidden" name="labSciScienceVo.masterId"
															maxlength="16" value="${labSciScienceVo.masterId}"
															id="masterId" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
																	<s:text name="start.time"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.startDate"
															style="width: 80px;" value="${labSciScienceVo.startDate}"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
															valType="required" strLength-msg="		<s:text name="start.time"/>长度不能超过32位"
															disabled="true" />
														<s:text name="to"/>
														<input type="text" name="labSciScienceVo.endDate"
															style="width: 80px;" value="${labSciScienceVo.endDate}"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
															valType="required" strLength-msg="		<s:text name="start.time"/>长度不能超过32位"
															disabled="true" />
													</td>
													<td>
														<label>
															<s:text name="yilailab"/>：
														</label>
													</td>
													<td>
														<s:select theme="simple" list="#request.orgList"
															listKey="id" listValue="name" headerKey=""
															headerValue="-请选择-" name="labSciScienceVo.labOrgId"
															value="${labSciScienceVo.labOrgId}" id="labOrgId"
															disabled="true" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="approval.fee"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.ratifyFunds"
															maxlength="32" value="${labSciScienceVo.ratifyFunds}"
															id="ratifyFunds" max="32"
															disabled="true" />
													</td>
													<td>
														<label>
															<s:text name="yiboyinfei"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.inFunds"
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
											<span><s:text name="itemchk"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon">
												<tr align="center">
													<td rowspan="20" width="10%">
														<s:text name="chkinfoed"/>
													</td>
													<td>
														<s:text name="ifplanedbegin"/>：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.isPlan"
															value="${labSciScienceVo.isPlan}" valType="strLength"
															max="512" strLength-msg="是否按计划进行不能超过512位" disabled="true">${labSciScienceVo.isPlan}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="studyprogress"/>：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.schedule"
															value="${labSciScienceVo.schedule}"
															valType="strLength" max="512"
															strLength-msg="<s:text name="studyprogress"/>不能超过512位" disabled="true">${labSciScienceVo.schedule}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														取得成果：
													</td>
													<td>
														<textarea rows="4" cols="60" name="labSciScienceVo.achieved"
															value="${labSciScienceVo.achieved}" valType="strLength"
															max="512" strLength-msg="取得成果不能超过512位" disabled="true">${labSciScienceVo.achieved}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														存在问题和改进措施：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.question"
															value="${labSciScienceVo.question}" valType="strLength"
															max="512" strLength-msg="存在问题和改进措施不能超过512位" disabled="true">${labSciScienceVo.question}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="lovwnplan"/>：
													</td>
													<td>
														<textarea rows="4" cols="60" name="labSciScienceVo.nextPlan"
															value="${labSciScienceVo.nextPlan}" valType="strLength"
															max="512" strLength-msg="下面计划不能超过512位" disabled="true">${labSciScienceVo.nextPlan}</textarea>
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
										<div>
											<table class="FormtableCon" id="funds">
												<tr>
													<td
														rowspan="${2+fn:length(labSciScienceVo.labSciFundsList)}"
														width="10%" id="rownum2">
														<s:text name="charge.out.info"/>
													</td>
													</td>
													<td class="r">
														总计：
													</td>
													<td colspan="3">
														<input type="text" name="labSciScienceVo.outFunds"
															maxlength="16" value="${labSciScienceVo.outFunds}"
															id="outFunds" readonly="true" disabled="true" />
														万元
													</td>
												</tr>
												<tr align="center">
													<td width="18%" class="l">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支出名目
													</td>
													<td width="18%" class="l">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额
													</td>
													<td width="18%" class="l">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="jingshouren"/>
													</td>
													<td width="18%" class="l">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>

												<s:if test="labSciScienceVo.labSciFundsList!=null">
													<s:set name="labSciFundsList"
														value="labSciScienceVo.labSciFundsList" />
													<s:iterator value="#labSciFundsList" status="ind">
														<tr id="tr0">
															<td>
																${name}
															</td>
															<td>
																${money}<s:text name="thousand.yuan"/>
															</td>
															<td>
																${user}
															</td>
															<td>
																${remark}
															</td>
														</tr>
													</s:iterator>
												</s:if>
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
