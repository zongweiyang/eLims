<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		    	$('form').attr('action',actionstr);
				$('form').submit();
		  	}
			function deleteBatch(){
				if(check('labQueryVo.ids')){
					if(confirm('<s:property value="getText('confirbatchdel')"/>'))
					{
						submitvalue('<%=basePath%>query/labQuery/deleteBatchLabQuery.action');				
					}	
				}		
			}
			function deleteOne(id){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					submitvalue('<%=basePath%>query/labQuery/deleteBatchLabQuery.action?labQueryVo.ids='+id);
				}		
				return false;	
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
		<s:form theme="simple" action="" method="post" name="labQueryForm">

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
																		<s:text name="function.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labQueryVo.funName" value="${labQueryVo.funName}" />
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
																   	<l:a uri="query/labQuery/preAddLabQuery.action" value="admin.add"/>
	                                                         	</td>
		                                                         <td>
		                                                         	<l:a uri="query/labQuery/deleteBatchLabQuery.action" onclick="deleteBatch();" value="lab.code.deleteall" />	
		                                                         </td>
															</tr>
														</table>
													</td>
													</tr>      					
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox"
															key="labQueryVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="function.name"/>
													</th>
													<th>
														<s:text name="def.sql"/>
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
																	<input type="checkbox" name="labQueryVo.ids"
																		value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td style="width: 180px">
																	${funName}
																</td>
																<td title="${name}">
																		<s:if test="${fn:length(name)}>30">
																			${fn:substring(name,0,60)}...
																		</s:if>
																		<s:else>
																			${name}
																		</s:else>
																		
																	</a>
																</td>
																<td align="center" style="width: 150px">
																	<l:a href="#" uri="query/labQuery/preUpdateLabQuery.action?labQueryVo.id=${id}" value="lab.code.modify" />
																		&nbsp;&nbsp;
																		<l:a href="#" uri="query/labQuery/deleteLabQuery.action" onclick="deleteOne('${id}');return false;" value="lab.code.del" />
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
										</table>
									</div>
								</td>
							</tr>
							<tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labQueryForm"
										flush="true" /></td>
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
		</s:form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
