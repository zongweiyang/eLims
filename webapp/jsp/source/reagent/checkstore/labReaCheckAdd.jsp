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

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
		<script>
			function deleteEntity(obj){
			    $(obj).parent().parent().remove();
			}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" name="labReaCheckMainVo.id"
				value="${labReaCheckMainVo.id}" />
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
												<span><s:text name="admin.add"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
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
																	<l:a uri="reagent/labReaCheckMain/addLabReaCheckMain.action"
																		value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTable"
											style="padding-top: 0; margin: 0; background: none;">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="base.info"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>
															</label>
														</td>
														<td>
															<input type="text" name="labReaCheckMainVo.checkno"
																valType="required,strLength" max="64"
																strLength-msg="单据号长度不能超过64位" msg="单据号不能为空"
																value="${labReaCheckMainVo.checkno}" />
														</td>
														<td>
															<label>
																<s:text name="pandian"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaCheckMainVo.checker"
																valType="required,strLength" max="32"
																strLength-msg="盘点人长度不能超过32位" msg="盘点人不能为空"
																value="${labReaCheckMainVo.checker}" />
														</td>
														<td>
															<label>
																<s:text name="pandiantime"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaCheckMainVo.checktime"
																value="${labReaCheckMainVo.checktime}" class="Wdate"
																size="20"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
														<td>
															<label>
																<s:text name="pansubject"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaCheckMainVo.name"
																valType="required,strLength" max="128"
																strLength-msg="盘点主题长度不能超过128位" msg="盘点主题不能为空"
																value="${labReaCheckMainVo.name}" />
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="paninfo"/></span>
												</div>
												<table class="myworkingboxttable" cellspacing="0"
													cellpadding="0">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="rea.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="pure"/>
														</th>

														<th>
															<s:text name="preconnum"/>
														</th>
														<th>
															<s:text name="thistimein"/>
														</th>
														<th>
															<s:text name="thistimeout"/>
														</th>
														<th>
															<s:text name="std.stock"/>
														</th>
														<th>
															<s:text name="panstore"/>
														</th>
														<th>
															<s:text name="remark"/>
														</th>
														<th>
															<s:text name="lab.code.ops"/>
														</th>
													</tr>
													<s:iterator
														value="labReaCheckMainVo.labReaCheckDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${labReagentName }
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].labReagentId"
																	value="${labReagentId }" />
															</td>
															<td class="l">
																${reagentSize}
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].reagentSize"
																	value="${reagentSize}" />
															</td>
															<td class="cl">
																${reagentpurity}
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].reagentpurity"
																	value="${reagentpurity}" />
															</td>
															<td class="l">
																${lastAmount }
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].lastAmount"
																	value="${lastAmount}" />
															</td>
															<td class="l">
																${thisInAmount}
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].thisInAmount"
																	value="${thisInAmount}" />
															</td>
															<td class="l">
																${thisOutAmount }
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].thisOutAmount"
																	value="${thisOutAmount}" />
															</td>
															<td class="l">
																${thisAmount }
																<input type="hidden"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].thisAmount"
																	value="${thisAmount}" />
															</td>
															<td class="c">
																<input type="text" valType="required,strLength" max="16"
																	strLength-msg="盘点库存长度不能超过16位" msg="盘点库存不能为空"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].amount"
																	value="${amount}" />
															</td>
															<td class="c">
																<input type="text"
																	name="labReaCheckMainVo.labReaCheckDetailVoList[${st.index}].remark"
																	style="width: 200px;" value="${remark}" />
															</td>
															<td class="c">
																<hr:jhref hrAttribute="href:javascript"
																	hrFunctionType="delete" hrFunctionUrl=""
																	hrFunctionValue="lab.code.del"
																	hrJsFunctions="onclick:deleteEntity(this);return false;"
																	hrIsInterval="false" />
															</td>
														</tr>
													</s:iterator>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
