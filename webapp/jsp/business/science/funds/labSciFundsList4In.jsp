<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function deleteOne(id,labSciScienceId){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}science/labSciFunds/deleteLabSciFunds.action?labSciFundsVo.labSciScienceId='+labSciScienceId+'&labSciFundsVo.ids='+id+'&labSciFundsVo.type=0';
			}	
		}
		function deleteBath(){
			if(check('labSciFundsVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('science/labSciFunds/deleteLabSciFunds.action');
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
		<form action="" method="post" name="labSciFundsFrom"
			id="labSciFundsFrom">
			<input type="hidden" name="labSciFundsVo.type" value="0" id="type" />
							<input type="hidden" name="labSciFundsVo.labSciScienceId"
								value="${labSciFundsVo.labSciScienceId}" id="labSciScienceId" />
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
																		<s:text name="fee.source"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciFundsVo.user"
																		id="user" value="${labSciFundsVo.user}" />
																</td>
																<td>
																	<label>
																		<s:text name="charge.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciFundsVo.name"
																		id="name" value="${labSciFundsVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="come.acc.time"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciFundsVo.startDate"
																		id="startDate" value="${labSciFundsVo.startDate}" class="Wdate"
													                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"/><s:text name="to"/>
													                  <input type="text" name="labSciFundsVo.endDate"
																		id="endDate" value="${labSciFundsVo.endDate}" class="Wdate"
													                  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"/>
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
																	<l:a uri="science/labSciFunds/listLabScience.action"
																		onclick="nextUri('science/labSciFunds/listLabScience.action');" value="msg.back" />
																</td>
															    <td>
																	<l:a uri="science/labSciFunds/preAddLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciFundsVo.labSciScienceId}&labSciFundsVo.type=0"
																		value="admin.add" />
																</td>
																<td>
																	<l:a uri="science/labSciFunds/deleteLabSciFunds.action"
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
													<th>
														<input type="checkbox" id="allCheckBox"
															key="labSciFundsVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="acct.name"/>
													</th>
													<th property="user">
														<s:text name="source.depart"/>
													</th>
													<th property="money">
														<s:text name="come.amount"/>(<s:text name="thousand.yuan"/>)
													</th>
													<th property="useDate">
														<s:text name="come.acc.time"/>
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
																	<input type="checkbox" name="labSciFundsVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	${name}
																</td>
																<td class="l">
																	${user}
																</td>
																<td class="r">
																	${money}
																</td>
																<td class="c">
																	${useDate}
																</td>
																<td class="c">
																	<l:a href="#"
																		uri="science/labSciFunds/updateLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciScienceId}&labSciFundsVo.id=${id}&labSciFundsVo.type=0"
																		onclick="nextUri('science/labSciFunds/preUpdateLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciScienceId}&labSciFundsVo.id=${id}&labSciFundsVo.type=0');" value="lab.code.modify" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a href="#"
																		uri="science/labSciFunds/deleteLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciScienceId}&labSciFundsVo.ids=${id}&labSciFundsVo.type=0"
																		onclick="deleteOne('${id}','${labSciScienceId}');" value="lab.code.del" />
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
										page="/jsp/include/page.jsp?formName=labSciFundsFrom"
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
