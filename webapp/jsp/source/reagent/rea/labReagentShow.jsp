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
</style>
	</head>
	<body class="" id="mainid">
		<form action="${basePath}reagent/labReagent/addLabStandard.action"
			method="post" name="form" enctype="multipart/form-data">
			<input type="hidden" name="labReagentVo.reagentTypeName"
				value="${labReagentVo.reagentTypeName}" />
			<input type="hidden" name="labReagentVo.reagentTypeId"
				value="${labReagentVo.reagentTypeId}" />
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
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}：
												<span>[<font color="blue">${labReagentVo.reagentTypeName}</font>]><s:text name="look.check"/></span>
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
																	<l:a uri="back" value="msg.back" />
																</td>
																<!--  <td>
                                                      				<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/filesave.gif" btFunctionType="add" btFunctionUrl="" btFunctionValue="lab.code.save" btIsDeafultImg="false" btJsFunctions="onclick:submitforform();return false;"/>
                                                      			</td>-->
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()"
												style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
												<span><s:text name="reageinfo"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
												    <td>
														<label>
															<s:text name="reageid"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.code" id="code"
															valType="required,strLength" max="32" readonly="true"
															strLength-msg='<s:property value="getText('reacodenot32')"/>' msg='<s:property value="getText('reacodenotemoy')"/>' type="text"
															size="40" value="${labReagentVo.code}" disabled="true"/>
													</td>
													<td>
														<label>
															<s:text name="rea.name"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.name" id="name"
															valType="required,strLength" max="128"
															strLength-msg='<s:property value="getText('reagnamenot128')"/>' msg='<s:property value="getText('reannamenotemp')"/>' type="text"
															size="40" value="${labReagentVo.name}" disabled="true"/>
														<input name="labReagentVo.id" id="id" type="hidden"
															size="40" value="${labReagentVo.id}" />
														<input name="labReagentVo.isDel" id="isDel" type="hidden"
															size="40" value="${labReagentVo.isDel}" />
													</td>
													</tr>
												<tr>
													<td>
														<label>
															<s:text name="rearegular"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.size" id="size"
															valType="required,strLength" max="32"
															strLength-msg='<s:property value="getText('rearegunot32')"/>' msg='<s:property value="getText('rearegunot32')"/>' type="text"
															size="40" value="${labReagentVo.size}" disabled="true"/>
													</td>
												
													<td>
														<label>
															<s:text name="pure"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.purity" valType="strLength"
															max="32" strLength-msg='<s:property value="getText('purelengnot32')"/>' id="purity"
															type="text" size="40" value="${labReagentVo.purity}" disabled="true"/>
													</td>
													</tr>
												<tr>
													<td>
														<label>
															<s:text name="risk.level"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.codeList"
															name="labReagentVo.dangerSize" headerKey=""
															headerValue="" theme="simple" listKey="name"
															listValue="name" disabled="true"></s:select>
													</td>
												
													<td>
														<label>
															<s:text name="alarm.number"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeAmount"
															value="${labReagentVo.safeAmount}"
															valType="required,strLength" max="16"
															strLength-msg='<s:property value="getText('alarmlennot16')"/>' msg='<s:property value="getText('alarmnotem')"/>'
															id="releaseDate" size="40" type="text" disabled="true"/>
													</td>
													</tr>
												<tr>
													<td>
														<label>
															<s:text name="std.stock"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.amount"
															value="${labReagentVo.amount}" valType="strLength"
															max="16" strLength-msg='<s:property value="getText('stocklennot16')"/>' id="amount"
															size="40" type="text" disabled="true"/>
													</td>
											
													<td>
														<label>
															<s:text name="save.office"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.labOrgVoList" theme="simple"
															name="labReagentVo.saveOrg" headerKey=""
															headerValue="" value="${labReagentVo.saveOrg}"
															id="orgId" listValue="name" listKey="id" disabled="true"></s:select>
													</td>
														</tr>
												<tr>
													<td>
														<label>
															<s:text name="saver"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.saveUser" valType="strLength"
															max="32" strLength-msg='<s:property value="getText('savinglennotover')"/>'
															value="${labReagentVo.saveUser}" id="saveUser" size="40"
															type="text" disabled="true"/>
													</td>

													<td>
														<label>
															<s:text name="valide.date"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeDate" valType="strLength"
															max="11" strLength-msg="有限期长度不能超过11位"
															value="${labReagentVo.safeDate}" id="safeDate" size="40"
															type="text" disabled="true"/>
													</td>
														</tr>
												<tr>
												<td>
														<label>
															<s:text name="supplier"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.supplierList" theme="simple"
															name="labReagentVo.unit" headerKey=""
															headerValue="" value="%{labReagentVo.unit}"
															id="unit" listValue="name" listKey="id" disabled="true" cssStyle="width:280px;"></s:select>
													</td>
													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td colspan="">
														<textarea rows="3" cols="36" name="labReagentVo.remark"
															valType="strLength" max="128"
															strLength-msg="备注长度不能超过128位" id="remark" disabled="true">${labReagentVo.remark}</textarea>
													</td>
													</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="3">
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span> ${name} 
																		<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;
																	<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}"">
																	    <img src="<%=basePath %>/img/query.gif"/>
																	</a>
																	<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
																</s:if></span>			
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
