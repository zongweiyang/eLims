<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	</head>
	<script type='text/javascript'>
	
function submitthis(action)
{
	document.processForm.action='${basePath}'+action;
	document.processForm.submit();
}
 function deleteOne(url){
 	if(confirm("确认删除？")){
 		document.processForm.action='${basePath}'+url;
	    document.processForm.submit();
 	}
 }
 function doSubmit(url){
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
 }
/*function checkFunId4Open(funId) {
  var eq="";
  $.ajax({
   	  url:'<%=basePath%>workflow/process/ajax4hasOpenedProcess.action',
   	  data:{'funId':funId},
   	  type:'post',
	  dataType:'text',
	  async:false,
   	  success:function (data){
   	  	if(data=="true"){
           	eq="此次操作将会替换该业务正启用的流程，确认开启？";
         }
   	  },
   	  error:function (data){
   	  	alert('请求错误.');
   	  }
   });	 
    return eq;
}*/
</script>

	</head>
	<body class="" id="mainid">
		<form action="listWfFlow.action" method="post" name="processForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		业务：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funcList" listKey="id" listValue="name" name="labWfProcessVo.funId" headerKey="" headerValue="-全部-" theme="simple"></s:select>
																</td>
																<td>
																	<label>
																		<s:text name="config.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labWfProcessVo.name" value="${labWfProcessVo.name}" />
																</td>
																<td>
																	<label>
																		编码：
																	</label>
																</td>
																<td>
																	<input type="text" name="labWfProcessVo.code" value="${labWfProcessVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#{'0':'建模中','1':'启用中','2':'已关闭'}" name="labWfProcessVo.status" headerKey="" headerValue="-全部-" theme="simple"></s:select>
																</td>
																<td>
																	<l:a uri="${pageResult.action}" value="fun.query" />
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
																	<l:a uri="workflow/process/preAddLabWfProcess.action" value="lab.code.add" />
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
													<th class="c" width="50">
														<s:text name="lab.sequence"/>
													</th>
													<th class="c" width="100" property="funName">
														业务
													</th>
													<th class="c" width="200" >
														<s:text name="config.name"/>
													</th>
													<th class="c" width="150" property="code">
														编码
													</th>
													<th class="c" width="90" property="userName">
														创建者
													</th>
													<th class="c" width="100" property="createDate">
														创建日期
													</th>
													<th class="c" >
														<s:text name="remark"/>
													</th>
													<th class="c" width="70" property="status">
														<s:text name="sam.state"/>
													</th>
													<th class="c" width="150">
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${funName}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="l">
																<a href="#" onclick="submitthis('/workflow/process/getLabWfProcess.action?labWfProcessVo.id=${id}');"> ${code}</a>
															</td>
															<td class="c">
																${userName}
															</td>
															<td class="c">
																${createDate}
															</td>
															<td class="l">
																<s:if test="${fn:length(parProcessName)>0}">父流程：${parProcessName}；</s:if>${comment}
															</td>
															<td class="c">
																<s:if test="${status=='1'}">
																	启用中
																</s:if>
																<s:elseif test="${status=='2'}">
																	已关闭
																</s:elseif>
																<s:else>
																	建模中
																</s:else>
															</td>
															<td class="c">
																<s:if test="${status=='0'&&fn:length(parProcessId)<=0}">
																		<l:a href="#" uri="workflow/process/preUpdateLabWfProcess.action" onclick="submitthis('workflow/process/preUpdateLabWfProcess.action?labWfProcessVo.id=${id}');" value="lab.code.modify" />
																		<l:a href="#" uri="workflow/process/preUpdateLabWfProcess4Content.action" onclick="submitthis('workflow/process/preUpdateLabWfProcess4Content.action?labWfProcessVo.id=${id}');" value="modeling" />
																		<l:a href="#" uri="workflow/process/updateLabWfProcess2Open.action" onclick="doSubmit('workflow/process/updateLabWfProcess2Open.action?labWfProcessVo.id=${id}');" value="start" />
																		<l:a href="#" uri="workflow/process/deleteLabWfProcess.action" onclick="deleteOne('workflow/process/deleteLabWfProcess.action?labWfProcessVo.id=${id}');" value="lab.code.del" />
																</s:if>
																<s:elseif test="${fn:length(parProcessId)>0}">
																	<l:a href="#" uri="workflow/process/showLabWfProgress.action" onclick="submitthis('workflow/process/showLabWfProgress.action?labWfProcessVo.id=${parProcessId}');" value="look.check" />
																</s:elseif>
																<s:else>
																	<l:a href="#" uri="workflow/process/showLabWfProgress.action" onclick="submitthis('workflow/process/showLabWfProgress.action?labWfProcessVo.id=${id}');" value="look.check" />
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td class="c" colspan="8">
															<s:text name="lab.msg.none"/>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=processForm" flush="true" />
								</td>
							</tr>
						</table>
					</td>
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