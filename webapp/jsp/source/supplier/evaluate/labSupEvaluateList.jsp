<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function deleteOne(id){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}supplier/labSupEvaluate/deleteLabSupEvaluate.action?labSupEvaluateVo.labSupplierId=${labSupEvaluateVo.labSupplierId}&labSupEvaluateVo.ids='+id;
			}	
		}
		function backList(url){
				submitvalue(url);
		}
		function deleteBath(){
			if(check('labSupEvaluateVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('supplier/labSupEvaluate/deleteLabSupEvaluate.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
			function goBack(url){
						window.location.href='${basePath}'+url;
				}
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labSupEvaluateFrom"
			id="labSupEvaluateFrom">
			<input type="hidden" name="labSupEvaluateVo.labSupplierId"
				id="labSupplierId" value="${labSupEvaluateVo.labSupplierId}" />
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
												<span><s:text name="top.index"/></span>
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
																	<label>
																		<s:text name="commenter"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSupEvaluateVo.person"
																		id="person" value="${labSupEvaluateVo.person}" />
																</td>
																<td>
																	<label>
																		<s:text name="time"/>：
																	</label>
																</td>
																<td>
																	<input type="text" class="Wdate"
																		onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
																		name="labSupEvaluateVo.startDate" id="startDate"
																		value="${labSupEvaluateVo.startDate }" />
																	<s:text name="to"/>
																	<input type="text" class="Wdate"
																		onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
																		name="labSupEvaluateVo.endDate" id="endDate"
																		value="${labSupEvaluateVo.endDate }" />
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}"
																		onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn"
																		href="javascript:void();"
																		onclick="goBack('supplier/labSupplier/listLabSupplier.action');return false;"><img
																			height="20" width="20"
																			src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
																</td>
																<td>
																	<l:a
																		uri="supplier/labSupEvaluate/preAddLabSupEvaluate.action?labSupEvaluateVo.labSupplierId=${labSupEvaluateVo.labSupplierId }"
																		value="admin.add" />
																</td>
																<td>
																	<l:a
																		uri="supplier/labSupEvaluate/deleteLabSupEvaluate.action"
																		onclick="deleteBath();" value="lab.code.deleteall" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1"
											cellpadding="0">
											<thead>
												<tr>
													<th class="w50">
														<input type="checkbox" id="allCheckBox"
															key="labSupEvaluateVo.ids" />
													</th>
													<th class="w50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="evaluateDate">
														<s:text name="comment.time"/>
													</th>
													<th property="person">
														<s:text name="commenter"/>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<input type="checkbox" name="labSupEvaluateVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	<a
																		href="<%=basePath%>supplier/labSupEvaluate/showLabSupEvaluate.action?labSupEvaluateVo.id=${id}">${evaluateDate}</a>
																</td>
																<td class="l">
																	${person}
																</td>
																<td class="c">
																	<l:a href="#"
																		uri="supplier/labSupEvaluate/preUpdateLabSupEvaluate.action?labSupEvaluateVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="supplier/labSupEvaluate/deleteLabSupEvaluate.action?labSupEvaluateVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labSupEvaluateFrom"
										flush="true" /></td>
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
