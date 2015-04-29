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
				window.location.href='${basePath}science/labSciAchievement/deleteLabSciAchievement.action?labSciAchievementVo.type=PAPER&labSciAchievementVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labSciAchievementVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('science/labSciAchievement/deleteLabSciAchievement.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
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
		<form action="" method="post" name="labSciAchievementFrom"
			id="labSciAchievementFrom">
			<input type="hidden" name="labSciAchievementVo.type" value="PAPER" id="type" />
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
																		<s:text name="thesis.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciAchievementVo.name"
																		id="name" value="${labSciAchievementVo.name}" />
																</td>
																<td>
															       <label>
																         <s:text name="thesis.type"/>：
															      </label>
														        </td>
														        <td>
																	<s:select list="#request.typeList" listKey="name"
																		listValue="name" headerKey="" headerValue="--请选择--"
																		name="labSciAchievementVo.achievementType"
																		id="achievementType" theme="simple"></s:select>
																</td>
														         <td>
															       <label>
																          <s:text name="first.writer"/>：
															      </label>
														        </td>
														        <td>
															        <input type="text" name="labSciAchievementVo.fristAuthorName"
																		id="fristAuthorName" value="${labSciAchievementVo.fristAuthorName}" />
														         </td>
																 <td>
																	<label>
																		<s:text name="publish.date"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciAchievementVo.startDate"
																    id="startDate" value="${labSciAchievementVo.startDate}" size="12" class="Wdate"
																   	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																   ~
																   <input type="text" name="labSciAchievementVo.endDate"
																    id="endDate" value="${labSciAchievementVo.endDate}" size="12" class="Wdate"
																  	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
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
																	<l:a uri="science/labSciAchievement/preAddLabSciAchievement.action?labSciAchievementVo.type=PAPER"
																		value="admin.add" />
																</td>
																<td>
																	<l:a uri="science/labSciAchievement/deleteLabSciAchievement.action"
																		onclick="deleteBath();" value="lab.code.deleteall" />
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
														<input type="checkbox" id="allCheckBox"
															key="labSciAchievementVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="achievementType">
														<s:text name="thesis.type"/>
													</th>
													<th property="name">
														<s:text name="thesis.name"/>
													</th>
													<th property="fristAuthorName">
														<s:text name="first.writer"/>
													</th>
													<th property="date">
														<s:text name="publish.date"/>
													</th>
													<th property="organization">
														<s:text name="publish.journal"/>
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
																<td class="td_cb">
																	<input type="checkbox" name="labSciAchievementVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	${achievementType}
																</td>
																<td class="l">
																	<a href="<%=basePath%>science/labSciAchievement/showLabSciAchievement.action?labSciAchievementVo.type=PAPER&labSciAchievementVo.id=${id}">${name}</a>
																</td>
																<td class="l">
																	${fristAuthorName}
																</td>
																<td class="c">
																	${date }
																</td>
																<td class="l">
																	${organization}
																</td>
																<td class="c">
																    <l:a href="#"
																		uri="science/labSciAchievement/preUpdateLabSciAchievement.action?labSciAchievementVo.type=PAPER&labSciAchievementVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="science/labSciAchievement/deleteLabSciAchievement.action?labSciAchievementVo.type=PAPER&labSciAchievementVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="6" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=labSciAchievementFrom"
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
