r <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function deleteOne(id){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}report/labReportTag/deleteLabReportTag.action?labReportTagVo.ids='+id;
			}	
		}
		function deleteBath(){
			if(check('labReportTagVo.ids')){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
					submitvalue('report/labReportTag/deleteLabReportTag.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		function ajax4InitTags(id){
			$.ajax({
		   	  url:'<%=basePath%>report/labReportTag/ajaxLabReportTag4Init.action',
		   	  data:{'labReportTagVo.labReportId':id},
		   	  type:'post',
			  dataType:'text',
		   	  success:function (data){
		   	  	if(data=="true"){
	            	alert("标签初始化成功.");
	          	 }else{
	          	 	alert("标签初始化失败.");
	             }
	             flashThisPage();
		   	  },
		   	  error:function (data){
		   	  	alert('请求错误.');
		   	  }
		   });
		}
		function flashThisPage(){
			window.location.href=window.location.href;
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
		<form action="" method="post" name="labReportTagFrom" id="labReportTagFrom">
			<input type="hidden" name="labReportTagVo.labReportId" value="${labReportTagVo.labReportId}"/>
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
												<span>【<font color="red">${labReportTagVo.labReportTitle}</font>】<s:text name="tag.index"/></span>
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
																	<l:a uri="report/labReport/listReportModel.action" onclick="nextUri('report/labReport/listLabReport.action');return false" value="back.tmp" img="img/fanhui.gif"/>
																</td>
																<td>
																	<l:a uri="report/labReportTag/ajaxLabReportTag4Init.action" onclick="ajax4InitTags('${labReportTagVo.labReportId}');return false" value="init.tag" img="img/xinjian.gif"/>
																</td>
																<td>
																	<l:a uri="report/labReportTag/preAddLabReportTag.action?labReportTagVo.labReportId=${labReportTagVo.labReportId}&labReportTagVo.labReportTitle=${labReportTagVo.labReportTitle}" value="admin.add" />
																</td>
																<td>
																	<l:a uri="report/labReportTag/deleteLabReportTag.action"
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
														<input type="checkbox" id="allCheckBox" key="labReportTagVo.ids" />
													</th>
													<th class="w50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="title"><s:text name="tag"/></th>
													<th><s:text name="expresion"/></th>
													<th property="type"><s:text name="config.type"/></th>
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
																	<input type="checkbox" name="labReportTagVo.ids" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>${title}</td>
																<td>${code}</td>
																<td class="c">${type}</td>
																<td class="c">
																	<l:a href="#"
																		uri="report/labReportTag/preUpdateLabReportTag.action?labReportTagVo.id=${id}"
																		value="lab.code.modify" />
																	<l:a href="#"
																		uri="report/labReportTag/deleteLabReportTag.action?labReportTagVo.ids=${id}"
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
										page="/jsp/include/page.jsp?formName=labReportTagFrom" flush="true" /></td>
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
