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
		<form action="${basePath}reference/labReference/addLabStandard.action"
			method="post" name="form" enctype="multipart/form-data">
			<input type="hidden" name="labReferenceVo.referenceTypeName"
				value="${labReferenceVo.referenceTypeName}" />
			<input type="hidden" name="labReferenceVo.referenceTypeId"
				value="${labReferenceVo.referenceTypeId}" />
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
												<span>[<font color="blue">${labReferenceVo.referenceTypeName}</font>]><s:text name="look.check"/></span>
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
												<span><s:text name="stdandard.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
												  <td>
														<label>
															<s:text name="stdandard.code"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.code" id="code"
															value="${labReferenceVo.code }"
															valType="required,strLength" max="32" disabled="true"
															strLength-msg="标准品编码长度不能超过32位" msg="标准品编码不能为空"
															type="text" size="40" />
													</td>
													<td>
														<label>
															<s:text name="stdandard.name"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.name" id="name"
															valType="required,strLength" max="128"
															strLength-msg="标准品名称长度不能超过128位" disabled="true" msg="标准品名称不能为空"
															type="text" size="40" value="${labReferenceVo.name}" />
														<input name="labReferenceVo.id" id="id" type="hidden"
															size="40" value="${labReferenceVo.id}" />
														<input name="labReferenceVo.isDel" id="isDel"
															type="hidden" size="40" value="${labReferenceVo.isDel}" />
													</td>
														</tr>
												<tr>
													<td>
														<label>
															<s:text name="stdandard.regular"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.size" id="size"
															valType="required,strLength" max="32"
															strLength-msg="标准品规格长度不能超过32位" msg="标准品规格不能为空"
															type="text" size="40" value="${labReferenceVo.size}" disabled="true"/>
													</td>
											
													<td>
														<label>
															<s:text name="pure"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.purity" valType="strLength"
															max="32" strLength-msg='<s:property value="getText('purelengnot32')"/>' id="purity"
															type="text" size="40" value="${labReferenceVo.purity}" disabled="true"/>
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
															name="labReferenceVo.dangerSize" headerKey=""
															headerValue="" theme="simple" listKey="name"
															listValue="name" disabled="true"></s:select>
													</td>
													<td>
														<label>
															<s:text name="alarm.number"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.safeAmount"
															value="${labReferenceVo.safeAmount}"
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
														<input name="labReferenceVo.amount"
															value="${labReferenceVo.amount}" valType="strLength"
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
															name="labReferenceVo.saveOrg" headerKey="" disabled="true"
															headerValue="" value="${labReferenceVo.saveOrg}"
															id="orgId" listValue="name" listKey="id"></s:select>
													</td>
														</tr>
												<tr>
													<td>
														<label>
															<s:text name="saver"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.saveUser" valType="strLength"
															max="32" strLength-msg='<s:property value="getText('savinglennotover')"/>'
															value="${labReferenceVo.saveUser}" id="saveUser"
															size="40" type="text" disabled="true"/>
													</td>
													<td>
														<label>
															<s:text name="valide.date"/>：
														</label>
													</td>
													<td>
														<input name="labReferenceVo.safeDate" valType="strLength"
															max="11" strLength-msg='<s:property value="getText('lennote11asfd')"/>'
															value="${labReferenceVo.safeDate}" id="safeDate"
															size="40" type="text" disabled="true"/>
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
															name="labReferenceVo.unit" headerKey=""
															headerValue="" value="%{labReferenceVo.unit}"
															id="unit" listValue="name" listKey="id" cssStyle="width:280px;" disabled="true"></s:select>
													</td>
													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td colspan="">
														<textarea rows="3" cols="36" name="labReferenceVo.remark"
															valType="strLength" max="128"
															strLength-msg='<s:property value="getText('remarknot128')"/>' id="remark" disabled="true">${labReferenceVo.remark}</textarea>
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
																<span>${name}<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}""><img src="<%=basePath %>/img/query.gif"/></a>
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
