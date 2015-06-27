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
			<input type="hidden" name="labConCheckMainVo.id"
				value="${labConCheckMainVo.id}" />
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
																	<l:a uri="consumables/labConCheckMain/addLabConCheckMain.action"
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
															<input type="text" name="labConCheckMainVo.checkno"
																valType="required,strLength" max="64"
																strLength-msg='<s:property value="getText('danjunot64')"/>' msg='<s:property value="getText('danjuidnotem')"/>'
																value="${labConCheckMainVo.checkno}" />
														</td>
														<td>
															<label>
																<s:text name="pandian"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConCheckMainVo.checker"
																valType="required,strLength" max="32"
																strLength-msg='<s:property value="getText('inventorynot32')"/>' msg='<s:property value="getText('inventorynotem')"/>'
																value="${labConCheckMainVo.checker}" />
														</td>
														<td>
															<label>
																<s:text name="pandiantime"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConCheckMainVo.checktime"
																value="${labConCheckMainVo.checktime}" class="Wdate"
																size="20"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
														<td>
															<label>
																<s:text name="pansubject"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConCheckMainVo.name"
																valType="required,strLength" max="128"
																strLength-msg='<s:property value="getText('inventsubjenot128')"/>' msg='<s:property value="getText('inventsubnotem')"/>'
																value="${labConCheckMainVo.name}" />
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
															<s:text name="conname"/>
														</th>
														<th>
															<s:text name="regular.no"/>
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
														value="labConCheckMainVo.labConCheckDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${labConsumablesName }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].labConsumablesId"
																	value="${labConsumablesId }" />
															</td>
															<td class="l">
																${consumablesSize}
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].consumablesSize"
																	value="${consumablesSize}" />
															</td>
															
															<td class="l">
																${lastAmount }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].lastAmount"
																	value="${lastAmount}" />
															</td>
															<td class="l">
																${thisInAmount}
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].thisInAmount"
																	value="${thisInAmount}" />
															</td>
															<td class="l">
																${thisOutAmount }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].thisOutAmount"
																	value="${thisOutAmount}" />
															</td>
															<td class="l">
																${thisAmount }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].thisAmount"
																	value="${thisAmount}" />
															</td>
															<td class="c">
																<input type="text" valType="required,strLength" max="16"
																	strLength-msg='<s:property value="getText('pandiannot16')"/>' msg='<s:property value="getText('invnestocknot')"/>'
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].amount"
																	value="${amount}" />
															</td>
															<td class="c">
																<input type="text"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].remark"
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
