<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function deleteOne(id){
			if(confirm('<s:property value="getText('deleted.confirm')"/>')){
				submitvalue('user/labUserTrain/deleteLabUserTrain.action?labUserTrainVo.ids='+id);
			}	
		}
		function deleteBath(){
			if(check('labUserTrainVo.ids')){
				if(confirm('<s:property value="getText('deleted.confirm')"/>')){
					submitvalue('user/labUserTrain/deleteLabUserTrain.action');
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
		<form action="" method="post" name="labUserTrainFrom" id="labUserTrainFrom">
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
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																	<s:text name="msg.subject"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labUserTrainVo.title" id="title" value="${labUserTrainVo.title}" />
																</td>
																<td>
																	<label>
																		<s:text name="training.place"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labUserTrainVo.station" id="station" value="${labUserTrainVo.station}" />
																</td>
																<td>
																	<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query" />
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="user/labUserTrain/preAddLabUserTrain.action" value="lab.code.add" />
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
													<!-- <th>
														<input type="checkbox" id="allCheckBox" key="labUserTrainVo.ids" />
													</th> -->
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="orgName">
														<s:text name="msg.depart"/>
													</th>
													<th>
														<s:text name="msg.subject"/>
													</th>
													<th>
														<s:text name="teacher"/>
													</th>
													<th property="station">
														<s:text name="training.place"/>
													</th>
													<th property="tdate">
														<s:text name="training.time"/>
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
																<td>
																	${orgName}
																</td>
																<td>
																	<a href="javascript:;" onclick="nextUri('user/labUserTrain/showLabUserTrain.action?labUserTrainVo.id=${id}')">${title}</a>
																</td>
																<td>
																	${teacher}
																</td>
																<td>
																	${station}
																</td>
																<td>
																	${tdate}
																</td>
																<td class="c">
																	<s:if test="${status=='0'}">
																		<l:a href="#" uri="user/labUserTrain/preUpdateLabUserTrain.action?labUserTrainVo.id=${id}" value="lab.code.modify" />
																		<l:a href="#" uri="user/labUserTrain/deleteLabUserTrain.action?labUserTrainVo.ids=${id}" onclick="deleteOne('${id}');" value="lab.code.del" />
																	</s:if>
																	<l:a href="#" uri="user/labUserTrain/preUpdateLabUserTrain4Record.action?labUserTrainVo.id=${id}" value="teach.record" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="13" align="center" valign="middle">
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labUserTrainFrom" flush="true" /></td>
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
