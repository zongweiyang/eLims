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
			window.location.href='${basePath}number/labNumber/updateLabNumber4Del.action?labNumberVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labNumberVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('number/labNumber/updateLabNumber4Del.action');
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
		<form action="" method="post" name="labNumberFrom" id="labNumberFrom">
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
																	<label>
																		<s:text name="config.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labNumberVo.name" value="${labNumberVo.name}" />
																</td>
																<td>
																	<l:a uri="${pageResult.action}"  onclick="submitAction();" value="fun.query"/>
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
																	<l:a uri="number/labNumber/preAddLabNumber.action" value="admin.add" />
																</td>
																<td>
																	<l:a uri="number/labNumber/deleteLabNumber.action"
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
														<input type="checkbox" id="allCheckBox" key="labNumberVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name"><s:text name="code.rule.number"/></th>
													<th property="part"><s:text name="several.part"/></th>
													<th property="type"><s:text name="rule.no"/></th>
													<th><s:text name="instance"/></th>
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
																	<input type="checkbox" name="labNumberVo.ids" value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td><a href="<%=basePath%>number/labNumber/showLabNumber.action?labNumberVo.id=${id}">${name}</a></td>
																<td>${part}</td>
																<td>${type}</td>
																<td>${example}</td>
																<td class="c">
																	<l:a href="#"
																		uri="number/labNumber/preUpdateLabNumber.action?labNumberVo.id=${id}"
																		value="theme.modify" />
																	<l:a href="#"
																		uri="number/labNumber/deleteLabNumber.action?labNumberVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="config.del" />
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
										page="/jsp/include/page.jsp?formName=labNumberFrom" flush="true" /></td>
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
