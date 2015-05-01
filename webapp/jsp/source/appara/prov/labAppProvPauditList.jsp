<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<script type="text/javascript">
 	function submitvalue(actionstr){
		var df = document.forms[0];
	 	df.action='<%=basePath%>/appara/prov/'+actionstr;
	  	df.submit();
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
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="customeractivitiesForm" theme="simple">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="audit.listed"/></span>
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
																		<s:text name="app.name"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaProvVo.labAppName" id="name" value="${labApparaProvVo.labAppName}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="falut.date"/>：
																	</label>
																</td>
																<td>
																	<input id="startDate" name="labApparaProvVo.startDate" class="Wdate" type="text" value="${labApparaProvVo.startDate}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" readonly="readonly" />
																</td>
																<td>
																	<label>
																		<s:text name="to"/>
																	</label><input id="endDate" name="labApparaProvVo.endDate" class="Wdate" type="text" value="${labApparaProvVo.endDate}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" readonly="readonly" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funStepList" listKey="stepId"
																		listValue="stepName" headerKey="" headerValue=""
																		name="labApparaProvVo.status" id="status"
																		theme="simple">
																	</s:select>
																</td>
																<td>
																	<l:a uri="/appara/listApparaProvPaudit.action" value="fun.query" />
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
													<th>
														<s:text name="lab.sequence"/>
													</th>
													<th property="labAppName">
														<s:text name="app.name"/>
													</th>
													<th property="labAppNo">
														<s:text name="app.no"/>
													</th>
													<th property="spec">
														<s:text name="regular.sys"/>
													</th>
													<th property="verDate">
														<s:text name="falut.occ.date"/>
													</th>
													<th property="status">
														<s:text name="sam.state"/>
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
															<s:if test="alarm==1">
																<tr style="background-color: #ffdee0">
															</s:if>

															<tr>
																<td class="c">
																	<s:property value="#st.index+1" />
																</td>
																<td class="c">
																	<s:property value="labAppName" />
																</td>
																<td class="c">
																	<s:property value="labAppNo" />
																</td>
																<td class="c">
																	<s:property value="spec" />
																</td>
																<td class="c">
																	<s:property value="verDate" />
																</td>
																<td class="c">
																	<a href="javascript:;" onclick="showProcess('${id}');return false">
																	<s:property value="status" />
																	</a>
																</td>
																<td class="c">
																	<s:if test="${isOper == 'Y'}">
																		<l:a href="#" uri="appara/prov/preUpdateApparaProv4Paudit.action?labApparaProvVo.id=${id}" value="flow.check" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="appara/prov/showLabApparaProv.action?labApparaProvVo.id=${id}" value="look.check" />
																		
																	</s:else>
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

											</tbody>
										</table>
									</div>

								</td>
							</tr>
							<tr>
							<tr>
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=customeractivitiesForm" flush="true" /></td>
							</tr>
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
