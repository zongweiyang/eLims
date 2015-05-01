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
				window.location.href='${basePath}supplier/labSupplier/deleteLabSupplier.action?labSupplierVo.ids='+id;
			}	
		}
		function getEvaluateList(id)
		{
				window.location.href='${basePath}supplier/labSupEvaluate/listLabSupEvaluate.action?labSupEvaluateVo.labSupplierId='+id;
		}
		function deleteBath(){
			if(check('labSupplierVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('supplier/labSupplier/deleteLabSupplier.action');
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
		<form action="" method="post" name="labSupplierFrom"
			id="labSupplierFrom">
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
																		<s:text name="supplier.code"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSupplierVo.code" id="code"
																		value="${labSupplierVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="supplier.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSupplierVo.name" id="name"
																		value="${labSupplierVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="supply.prd.type"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.typeList"
																		name="labSupplierVo.goodsType" headerKey=""
																		headerValue="" theme="simple"
																		listKey="name" listValue="name"></s:select>
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
																	<l:a
																		uri="supplier/labSupplier/preAddLabSupplier.action"
																		value="admin.add" />
																</td>
																<td>
																	<l:a
																		uri="supplier/labSupplier/deleteLabSupplier.action"
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
													<th class="w50">
														<input type="checkbox" id="allCheckBox"
															key="labSupplierVo.ids" />
													</th>
													<th class="w50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="code">
														<s:text name="supplier.code"/>
													</th>
													<th property="name">
														<s:text name="supplier.name"/>
													</th>
													<th property="goodsType">
														<s:text name="supply.prd.type"/>
													</th>
													<th property="person">
														<s:text name="org.people"/>
													</th>
													<th property="phone">
														<s:text name="theme.phone"/>
													</th>
													<th property="email">
														<s:text name="email"/>
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
																	<input type="checkbox" name="labSupplierVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>
																	<a
																		href="<%=basePath%>supplier/labSupplier/showLabSupplier.action?labSupplierVo.id=${id}">${code}</a>
																</td>
																<td>
																	${name}
																</td>
																<td>
																	${goodsType}
																</td>
																<td>
																	${person}
																</td>
																<td>
																	${phone}
																</td>
																<td>
																	${email}
																</td>
																<td class="c">
																	<l:a href="#"
																		uri="supplier/labSupplier/preUpdateLabSupplier.action?labSupplierVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="supplier/labSupplier/deleteLabSupplier.action?labSupplierVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																	<l:a href="#"
																		uri="supplier/labSupEvaluate/listLabSupEvaluate.action?labSupplierVo.id=${id}"
																		onclick="getEvaluateList('${id}');" value="comment.manage" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="24" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=labSupplierFrom"
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
