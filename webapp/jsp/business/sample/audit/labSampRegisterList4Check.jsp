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
				submitvalue('sample/labSampRegister/deleteLabSampRegister.action?labSampRegisterVo.ids='+id);
			}	
		}
		function deleteBath(){
			if(check('labSampRegisterVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('sample/labSampRegister/deleteLabSampRegister.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
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
		<form action="" method="post" name="sampRegisterFrom" id="sampRegisterFrom">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">

															<tr>
																<td>
																	<label>
																		<s:text name="task.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.no" value="${labSampRegisterVo.no}" />
																</td>
																<td>
																	<label>
																		<s:text name="lec.cust.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.labSampCustomerVo.labCustomerName" value="${labSampRegisterVo.labSampCustomerVo.labCustomerName}" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select theme="simple" list="#request.funStepList" listKey="stepId" listValue="stepName" headerKey="" headerValue="" name="labSampRegisterVo.status" id="status" onchange="submitvalue('sample/labSampRegister/listLabSampRegister4Check.action');">
																	</s:select>
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th property="no" width="150">
														<s:text name="task.no"/>
													</th>
													<th>
														<s:text name="lec.cust.name"/>
													</th>
													<th width="70">
														<s:text name="sam.number"/>
													</th>
													<th width="100">
														<s:text name="checking.type"/>
													</th>
													<th width="100">
														<s:text name="report.performance"/>
													</th>
													<th property="createTime" width="100">
														<s:text name="sam.register"/>
													</th>
													<th>
														<s:text name="charge.fee"/>
													</th>
													<th>
														<s:text name="progressing"/>
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
																	<a href="<%=basePath%>sample/labSampRegister/showLabSampRegister.action?labSampRegisterVo.id=${id}">${no}</a>
																</td>
																<td>
																	${labSampCustomerVo.labCustomerName}
																</td>
																<td>
																	${sampNum}
																</td>
																<td class="c">
																	${taskType}
																</td>
																<td class="c">
																	${reportType}
																</td>
																<td class="c">
																	${fn:substring(createTime, 0, 10)}
																</td>
																<td class="r">
																	<fmt:formatNumber value="${charge}" pattern="￥#,##0.0" />
																</td>
																<td class="c">
																	<a href="javascript:;" onclick="showProcess('${id}');return false">${status}</a>
																</td>
																<td class="c">
																	<s:if test="${isOper=='Y'}">
																		<l:a href="#" uri="sample/labSampRegister/showLabSampRegister4Check.action?labSampRegisterVo.id=${id}" value="flow.review" />
																	</s:if>
																	<s:else>
																		<font><s:text name="flow.review"/></font>
																	</s:else>
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=sampRegisterFrom" flush="true" /></td>
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
