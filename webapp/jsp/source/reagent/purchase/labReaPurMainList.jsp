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
		<script>
function submitvalue(actionstr){
	var df=document.labReaPurform;
	df.action = actionstr;
	df.submit();  
}
function showReport(busId){
	var funId='${session.SessionContainer.funId}';
	var height = window.screen.height-250;
	var url  = '${basePath}reagent/labReaPurMain/showLabReport4Bus.action?funId='+funId+'&busId='+busId;
	$.dialog({
		id:'id',
		content:'url:'+url,
		title:'<s:property value="getText('pageprint')"/>',
		opacity:0.4,
		width:1050,
		height:height,
		lock:true
	 });
}
   function deleteOne(id){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}reagent/labReaPurMain/deleteLabReaPurMain.action?labReaPurMainVo.id='+id;
			}	
		}
function showProcess(id){
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath%>/jsp/common/workflow/ins/processFrame.jsp?busId='+id,
		title:'<s:property value="getText('task.progress')"/>',
		opacity:0.4,
		width:1100, 
		height:500,
		lock:true,
		max:false,
		min:false
	});
}
</script>
	</head>
	<body class="" id="mainid">
		<form theme="simple" action="" method="post" name="labReaPurform">
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
												${funName }：
												<span><s:text name="top.index"/></span>
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
																	<label>
																		<s:text name="bill.no"/>
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaPurMainVo.receiptno"
																		id="receiptno" value="${labReaPurMainVo.receiptno}" />
																</td>
																<td>
																	<label>
																		<s:text name="applytime"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaPurMainVo.startDate" id="startDate"
																		value="${labReaPurMainVo.startDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	<s:text name="to"/>
																	<input type="text" name="labReaPurMainVo.endDate" id="endDate"
																		value="${labReaPurMainVo.endDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
																</td>
																<td>
																	<label>
																		<s:text name="applier"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaPurMainVo.applicant"
																		id="name" value="${labReaPurMainVo.applicant}" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select theme="simple" list="#request.funStepList"
																		listKey="stepId" listValue="stepName" headerKey=""
																		headerValue="" name="labReaPurMainVo.status"
																		id="status"
																		onchange="submitvalue('listLabReaPurMain.action');">
																	</s:select>
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
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																	</label>
																</td>
																<td>
																	<l:a uri="reagent/labReaPurMain/preAddLabReaPur.action"
																		value="admin.add" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<tr>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="receiptno">
													<s:text name="bill.no"/>
												</th>
												<th property="applicant">
													<s:text name="applier"/>
												</th>
												<th property="createTime">
													<s:text name="applytime"/>
												</th>
												<th property="status">
													<s:text name="sam.state"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${receiptno}
															</td>
															<td class="l">
																${applicant}
															</td>
															<td class="c">
																${createTime}
															</td>
															<td class="c">
																<a href="javascript:;" onclick="showProcess('${id}');return false">
																	${status}
																</a>
															</td>

															<td class="c">
																<l:a href="#"
																	uri="reagent/labReaPurMain/showLabReaPur.action?labReaPurMainVo.id=${id}"
																	value="look.check" />
																&nbsp;&nbsp;
																<s:if test="${isOper=='Y'}">
																	<l:a href="#"
																		uri="reagent/labReaPurMain/preUpdateLabReaPur.action?labReaPurMainVo.id=${id}"
																		value="lab.code.modify" />
																&nbsp;&nbsp;
																		<l:a href="#"
																		uri="reagent/labReaPurMain/deleteLabReaPurMain.action?labReaPurMainVo.id=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																</s:if>
																<s:if
																	test="${isOper!='Y' && (status=='采购审核'|| status=='采购审批')}">
																	<l:a href="#"
																		uri="reagent/labReaPurMain/showAllLabReaPurMainExcel.action?labReaPurMainVo.id=${id}"
																		value="打印" />
																	<l:a href="#" uri="" onclick="showReport('${id}');"
																		value="打印1" />
																</s:if>

															</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:if>
											<s:else>
												<tr>
													<td colspan="7" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labReaPurform"
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
	</body>
</html>
