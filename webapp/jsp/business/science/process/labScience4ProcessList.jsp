<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
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
		<form action="" method="post" name="labScienceFrom"
			id="labScienceFrom">
			<input type="hidden" name="messageInfo"
								value="1" />
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
																		<s:text name="prj.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.code"
																		id="code" value="${labSciScienceVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.name"
																		id="name" value="${labSciScienceVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="prj.people"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.masterName"
																		id="masterName" value="${labSciScienceVo.masterName}" />
																</td>
																<td>
																	<label>
																		<s:text name="prj.status"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funStepList" listKey="stepId"
																		listValue="stepName" headerKey="" headerValue=""
																		name="labSciScienceVo.status" id="status" theme="simple"
																		onchange="submitvalue('/science/labSciProcess/listLabScience4Process.action');">
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
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
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="code">
														<s:text name="prj.no"/>
													</th>
													<th property="name">
														<s:text name="item.name"/>
													</th>
													<th property="master">
														<s:text name="prj.people"/>
													</th>
													<th property="startDate">
														<s:text name="prj.time"/>
													</th>
													<th property="startDate">
														<s:text name="prj.status"/>
													</th>
													<th property="isSeized">
														<s:text name="prj.check.status"/>
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
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	${code}
																</td>
																<td class="l">
																	${name}
																</td>
																<td class="c">
																	${masterName}
																</td>
																<td class="c">
																	${startDate}~${endDate}
																</td>
																<td class="c">
																	<a href="javascript:;" onclick="showProcess('${id}');return false">${status}
																	</a>
																</td>
																<td class="c">
																	<s:if test="${isSeized == 'Y'}">
																		<font color="red"><s:text name="to.check"/></font>
																	</s:if>
																	<s:else>
																		<font color="red"><s:text name="no.check"/></font>
																	</s:else>
																	<s:if test="${isKnot != '' && isKnot != null }">
																		<s:if test="${isKnot == 'Y'}">
																			<font color="red">/<s:text name="to.item"/></font>
																		</s:if>
																		<s:else>
																			<font color="red">/<s:text name="no.item"/></font>
																		</s:else>
																	</s:if>
																</td>
																<td class="c">
																	<a href="<%=basePath%>science/labSciProcess/showLabScience4Process.action?labSciScienceVo.id=${id}"><s:text name="look.check"/></a>
																	&nbsp;&nbsp;
																	<a href="<%=basePath%>science/labSciProcess/preAddLabSciProcess.action?labSciProcessVo.labSciScienceId=${id}"><s:text name="writed"/></a>
																	<s:if test="${isSeized == 'N'}" >
																		&nbsp;&nbsp;
																		<a href="<%=basePath%>science/labScience/preUpdateLabScience.action?labSciScienceVo.id=${id }&messageInfo=4"><s:text name="apply.check"/></a>
																	</s:if>
																	<s:if test="${isSeized == 'Y' && isKnot == 'N'}">
																		&nbsp;&nbsp;
																		<a href="<%=basePath%>science/labScience/preUpdateLabScience.action?labSciScienceVo.id=${id }&messageInfo=6"><s:text name="apply.over"/></a>
																	</s:if>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="24" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=labScienceFrom"
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
