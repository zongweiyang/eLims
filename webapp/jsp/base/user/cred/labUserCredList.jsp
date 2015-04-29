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
			if(confirm('<s:property value="getText('confirm.deleted')"/>')){
				window.location.href='${basePath}user/labUserCred/deleteLabUserCred.action?labUserCredVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labUserCredVo.ids')){
				if(confirm('<s:property value="getText('confirm.deleted')"/>')){
					submitvalue('user/labUserCred/deleteLabUserCred.action');
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
		<form action="" method="post" name="labUserCredFrom" id="labUserCredFrom">
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
												<span><s:text name="top.index"></s:text></span>
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
																		<s:text name="config.name"></s:text>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labUserCredVo.name" id="name" value="${labUserCredVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="code.number"></s:text>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labUserCredVo.code" id="code" value="${labUserCredVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="nike.name"></s:text>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labUserCredVo.userName" id="userName" value="${labUserCredVo.userName}" />
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
																	<l:a uri="user/labUserCred/preAddLabUserCred.action" value="lab.code.add" />
																</td>
																<td>
																	<l:a uri="user/labUserCred/deleteLabUserCred.action" onclick="deleteBath();" value="lab.code.deleteall" />
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
														<input type="checkbox" id="allCheckBox" key="labUserCredVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="userName">
														<s:text name="nike.name"></s:text>
													</th>
													<th>
														<s:text name="config.name"></s:text>
													</th>
													<th property="code">
														<s:text name="code.number"></s:text>
													</th>
													<th property="unit">
														<s:text name="theme.depart"></s:text>
													</th>
													<th property="type">
														<s:text name="config.type"></s:text>
													</th>
													<th>
														<s:text name="lab.code.ops"></s:text>
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
																	<input type="checkbox" name="labUserCredVo.ids" value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	${userName}
																</td>
																<td>
																	<a href="<%=basePath%>user/labUserCred/showLabUserCred.action?labUserCredVo.id=${id}">${name}</a>
																</td>
																<td>
																	${code}
																</td>
																<td>
																	${unit}
																</td>
																<td class="c">
																	${type}
																</td>
																<td class="c">
																	<l:a href="#" uri="user/labUserCred/preUpdateLabUserCred.action?labUserCredVo.id=${id}" value="lab.code.modify" />
																	<l:a href="#" uri="user/labUserCred/deleteLabUserCred.action?labUserCredVo.ids=${id}" onclick="deleteOne('${id}');" value="lab.code.del" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="12" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"></s:text></font>
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labUserCredFrom" flush="true" /></td>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
