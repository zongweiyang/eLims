<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	  function submitvalue(actionstr){
	  			alert(1);
				var df = document.customeractivitiesForm;
			 	df.action=actionstr;
			  	df.submit();
			}
	   function doUrl(url){
		   window.location.href=url;
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
		<form action="" method="post" name="labApparaEditFrom" theme="simple">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="app.sys.sav.list"/></span>
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
																	<input id="appName" name="labApparaEditVo.appName" value="${labApparaEditVo.appName}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="app.style"/>：
																	</label>
																</td>
																<td>
																	<input id="appSpec" name="labApparaEditVo.appSpec" value="${labApparaEditVo.appSpec}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="re.saving.date"/>：
																	</label>
																</td>
																<td>
																	<input id="startDate" name="labApparaEditVo.startDate" class="Wdate" type="text" value="${labApparaEditVo.startDate}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" readonly="readonly" />
																</td>
																<td>
																	<label>
																		<s:text name="to"/>
																	</label><input id="endDate" name="labApparaEditVo.endDate" class="Wdate" type="text" value="${labApparaEditVo.endDate}" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" readonly="readonly" />
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="main.man"/>：
																	</label>
																</td>
																<td>
																	<input id="checkName" name="labApparaEditVo.checkName" value="${labApparaEditVo.checkName}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funStepList" listKey="stepId"
																		listValue="stepName" headerKey="" headerValue=""
																		name="labApparaEditVo.status" id="status"
																		theme="simple">
																	</s:select>
																</td>
																<td>
																	<l:a uri="appara/edit/listLabApparaEdit.action" value="fun.query" />
																</td>
															</tr>
															<tr>
																<td>
																	<l:a uri="appara/edit/preAddLabApparaEdit.action" value="lab.code.add" />
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
													<th property="appName">
														<s:text name="app.name"/>
													</th>
													<th property="appSpec">
														<s:text name="app.style"/>
													</th>
													<th property="auditName">
														<s:text name="main.man"/>
													</th>
													<th property="checkName">
														<s:text name="saved.repair.man"/>
													</th>
													<th property="verDate">
														<s:text name="re.saving.date"/>
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

															<tr>
																<td class="c">
																	<s:property value="#st.index+1" />
																</td>
																<td class="c">
																	<s:property value="appName" />
																</td>
																<td class="l">
																	<s:property value="appSpec" />
																</td>
																<td class="c">
																	<s:property value="auditName" />
																</td>
																<td class="c">
																	<s:property value="checkName" />
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
																	<s:if test="${isOper == 'N'}">
																		<l:a href="#" uri="appara/edit/showLabApparaEdit.action?labApparaEditVo.id=${id}" value="look.check" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="appara/edit/showLabApparaEdit.action?labApparaEditVo.id=${id}" value="look.check" />
																		<l:a href="#" uri="appara/edit/preUpdateLabApparaEdit.action?labApparaEditVo.id=${id}" value="theme.modify" />
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
								</div>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labApparaEditFrom" flush="true" /></td>
				</tr>
			</table>
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
