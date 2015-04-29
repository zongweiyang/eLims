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
				window.location.href='${basePath}science/labSciLearn/deleteLabSciLearn.action?labSciLearnVo.type=2&labSciLearnVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labSciLearnVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('science/labSciLearn/deleteLabSciLearn.action');
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
		<form action="" method="post" name="labSciLearnFrom"
			id="labSciLearnFrom">
			<input type="hidden" name="labSciLearnVo.type" value="2" id="type" />
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
																		<s:text name="lecture.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciLearnVo.name"
																		id="name" value="${labSciLearnVo.name}" />
																</td>
																<td>
															       <label>
																          讲座类型：
															      </label>
														      </td>
														     <td>
															     <s:select list="#request.typeList" listKey="name"
															      listValue="name" headerKey="" headerValue="--请选择--"
															       name="labSciLearnVo.learnType" id="learnType" theme="simple"></s:select>
														      </td>
														      <td>
															       <label>
																          主讲人：
															      </label>
														      </td>
														     <td>
															     <input type="text" name="labSciLearnVo.speaker"
																		id="speaker" value="${labSciLearnVo.speaker}" />
														      </td>
																<td>
																	<label>
																		<s:text name="time"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciLearnVo.startTime"
																    id="startTime" value="${labSciAchievementVo.startDate}" size="12" class="Wdate"
																   	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
																   ~
																   <input type="text" name="labSciLearnVo.endTime"
																    id="endTime" value="${labSciAchievementVo.endDate}" size="12" class="Wdate"
																  	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});" />
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
																	<l:a uri="science/labSciLearn/preAddLabSciLearn.action?labSciLearnVo.type=2"
																		value="admin.add" />
																</td>
																<td>
																	<l:a uri="science/labSciLearn/deleteLabSciLearn.action"
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
															key="labSciLearnVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="learnType">
														讲座类型
													</th>
													<th property="name">
														讲座名称
													</th>
													<th property="speaker">
														主讲人
													</th>
													<th property="startTime">
														讲座时间
													</th>
													<th property="place">
														讲座地点
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
																	<input type="checkbox" name="labSciLearnVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	${learnType}
																</td>
																<td class="l">
																	<a href="<%=basePath%>science/labSciLearn/showLabSciLearn.action?labSciLearnVo.type=2&labSciLearnVo.id=${id}">${name}</a>
																</td>
																<td class="l">
																	${speaker}
																</td>
																<td class="c">
																	${startTime}--${endTime}
																</td>
																<td class="l">
																	${place}
																</td>
																<td class="c">
																    <l:a href="#"
																		uri="science/labSciLearn/preUpdateLabSciLearn.action?labSciLearnVo.type=2&labSciLearnVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="science/labSciLearn/deleteLabSciLearn.action?labSciLearnVo.type=2&labSciLearnVo.ids=${id}"
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
										page="/jsp/include/page.jsp?formName=labSciLearnFrom"
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
