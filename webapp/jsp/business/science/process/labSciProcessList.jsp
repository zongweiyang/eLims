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
				submitvalue('science/labSciProcess/deleteLabSciProcess.action?labSciProcessVo.ids='+id);
			}	
		}
		function deleteBath(){
			if(check('labSciProcessVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('science/labSciProcess/deleteLabSciProcess.action');
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
		<form action="" method="post" name="labSciProcessFrom" id="labSciProcessFrom">
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
												<span>过程记录列表</span>
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
																		<s:text name="code.number"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciProcessVo.no"
																    id="no" value="${labSciProcessVo.no}" />
																</td>
																<td>
																	<label>
																		登记时间：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciProcessVo.startTime"
																    id="startTime" value="${labSciProcessVo.startTime}" size="12" class="Wdate"
																   	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
																   ~
																   <input type="text" name="labSciProcessVo.endTime"
																    id="endTime" value="${labSciProcessVo.endTime}" size="12" class="Wdate"
																  	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});" />
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
																	<l:a uri="science/labSciProcess/listLabScience4Process.action" onclick="goAction('listLabScience4Process.action');" value="msg.back" />
																</td>
																<td>
																	<l:a uri="science/labSciProcess/preAddLabSciProcess.action" value="admin.add" />
																</td>
																<td>
																	<l:a uri="science/labSciProcess/deleteLabSciProcess.action"
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
														<input type="checkbox" id="allCheckBox" key="labSciProcessVo.ids" />
													</th>
													<th class="w50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="no"><s:text name="code.number"/></th>
													<th property="name">名称</th>
													<th property="type ">类型</th>
													<th property="contents">内容</th>
													<th property="createTime" >登记时间</th>
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
																	<input type="checkbox" name="labSciProcessVo.ids" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c"><l:a href="#"
																		uri="science/labSciProcess/showLabSciProcess.action?labSciProcessVo.id=${id}"
																		value="${no}" /></td>
																<td>${name}</td>
																<td>${type }</td>
																<td>${contents}</td>
																<td class="c">${createTime }</td>
																<td class="c">
																	<l:a href="#"
																		uri="science/labSciProcess/preUpdateLabSciProcess.action?labSciProcessVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="science/labSciProcess/deleteLabSciProcess.action?labSciProcessVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="lab.code.del" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9" align="center" valign="middle">
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
										page="/jsp/include/page.jsp?formName=labSciProcessFrom" flush="true" /></td>
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
