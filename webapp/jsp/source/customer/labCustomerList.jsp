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
				window.location.href='${basePath}customer/labCustomer/deleteLabCustomer.action?labCustomerVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labCustomerVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('customer/labCustomer/deleteLabCustomer.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		
	function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			window.location.href = '<%=basePath%>'+actionstr+'?path='+data;
				}
			}
	   });
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
		<form action="" method="post" name="labCustomerFrom"
			id="labCustomerFrom">
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
																		<s:text name="custno"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labCustomerVo.num" id="num"
																		value="${labCustomerVo.num}" />
																</td>
																<td>
																	<label>
																		<s:text name="cust.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labCustomerVo.name" id="name"
																		value="${labCustomerVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="custclass"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.levelList"
																		name="labCustomerVo.level" headerKey=""
																		headerValue="" theme="simple"
																		listKey="name" listValue="name"></s:select>
																</td>
																<td>
																	<label>
																		<s:text name="custtype"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.typeList"
																		name="labCustomerVo.type" headerKey=""
																		headerValue="---------全部-------" theme="simple"
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
																		uri="customer/labCustomer/preAddLabCustomer.action"
																		value="lab.code.add" />
																</td>
																<td>
																	<l:a
																		uri="customer/labCustomer/deleteLabCustomer.action"
																		onclick="deleteBath();" value="lab.code.deleteall" />
																</td>
																<td>
																	<!--<l:a
																		uri="customer/labCustomer/preAddLabCustomer.action"
																		onclick="ajaxVerification('customer/labCustomer/exportLabCustomer.action');"
																		value="导出客户单" />-->
																		<l:export params="labCustomerVo,pageResult" type="excel" source="${labCustomerVo.filePath}" target="${funName}-${now}.xls" value="导出${funName}"/>
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
															key="labCustomerVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="num">
														<s:text name="custno"/>
													</th>
													<th property="name">
														<s:text name="cust.name"/>
													</th>
													<th property="industry">
														<s:text name="ownindustry"/>
													</th>
													<th property="type">
														<s:text name="custtype"/>
													</th>
													<th property="level">
														<s:text name="custclass"/>
													</th>
													<th property="buildDate">
														<s:text name="buildtime"/>
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
																	<input type="checkbox" name="labCustomerVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	<a
																		href="<%=basePath%>customer/labCustomer/showLabCustomer.action?labCustomerVo.id=${id}">${num}</a>
																</td>
																<td>
																	${name}
																</td>
																<td>
																	${industry}
																</td>
																<td class="l">
																	${type}
																</td>
																<td class="l">
																	${level}
																</td>
																<td class="c">
																	${buildDate}
																</td>
																<td class="c">
																	<l:a href="#"
																		uri="customer/labCustomer/preUpdateLabCustomer.action?labCustomerVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="customer/labCustomer/deleteLabCustomer.action?labCustomerVo.ids=${id}"
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
										page="/jsp/include/page.jsp?formName=labCustomerFrom"
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
