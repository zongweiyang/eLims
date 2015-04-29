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
				submitvalue('attendance/labAttendConfig/deleteLabAttendConfig.action?labAttendConfigVo.ids='+id);
			}	
		}
		function deleteBath(){
			if(check('labAttendConfigVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('attendance/labAttendConfig/deleteLabAttendConfig.action');
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
		<form action="" method="post" name="labAttendConfigFrom" id="labAttendConfigFrom">
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="attendance/labAttendConfig/preAddLabAttendConfig.action" value="lab.code.add" />
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
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="title"><s:text name="subtitle"/></th>
													<th property="startTime"><s:text name="workingdate"/></th>
													<th property="endTime"><s:text name="afterword"/></th>
													<th property="startDay"><s:text name="begindatedo"/></th>
													<th property="endDay"><s:text name="enddodate"/></th>
													<th property="workDay"><s:text name="workdayday"/></th>
													<th property="remark"><s:text name="remark"/></th>
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
																<td><a href="<%=basePath%>attendance/labAttendConfig/showLabAttendConfig.action?labAttendConfigVo.id=${id}">${title}</a></td>
																
																<td class="c">${startTime}</td>
																<td class="c">${endTime}</td>
																<td class="c">${startDay}</td>
																<td class="c">${endDay}</td>
																<td>${workDay}</td>
																<td>${remark}</td>
																<td class="c">
																	<l:a href="#"
																		uri="attendance/labAttendConfig/preUpdateLabAttendConfig.action?labAttendConfigVo.id=${id}"
																		value="theme.modify" />
																	<l:a href="#"
																		uri="attendance/labAttendConfig/deleteLabAttendConfig.action?labAttendConfigVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="10" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=labAttendConfigFrom" flush="true" /></td>
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
