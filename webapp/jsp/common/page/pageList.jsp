<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
	function submitvalue(actionstr){
		$('#pageForm').attr('action','<%=basePath%>page/labPageEditor/'+actionstr).submit();
	}
	function goToNextAction(actionstr){
		window.location.href = '<%=basePath%>page/labPageEditor/'+actionstr;
	}
	function deletePage(actionstr){
		if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
			window.location.href = '<%=basePath%>page/labPageEditor/'+actionstr;
		}
	}
	function preEdit(id){
		$.ajax({
			url:'<%=basePath%>page/labPageEditor/ajaxIsFileExist.action',
			data:{'labPageEditorVo.id':id},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data=="false"){
					alert('<s:property value="getText('filenotexist')"/>');
					return ;
				}else{
					window.location.href = '<%=basePath%>page/labPageEditor/preUpdateLabPage4Edit.action?labPageEditorVo.id='+id;
				}
			}
	   });
	}
	function preAddBatch(actionstr){
		$.dialog({
			id:'filePage',
			content:'url:'+'<%=basePath%>/page/labPageEditor/'+actionstr,
			title:'<s:property value="getText('pagelistedsdf')"/>',
			opacity:0.4,
			width:800, 
			height:500,
			max:false,
			min:false,
			lock:true
		});
	}
	function refeshThis(){
		window.location.href = '<%=basePath%>page/labPageEditor/listLabPage.action';
	}
</script>
		<style>
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="pageForm" id="pageForm">
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
												<s:text name="page.manage"/>：
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
																		<s:text name="cn.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labPageEditorVo.name" value="${labPageEditorVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="file.name"/>：
																	</label>
																</td>
																<td>
																	<input id="fileName" name="labPageEditorVo.fileName" value="${labPageEditorVo.fileName}" />
																</td>
																<td>
																	<label>
																		<s:text name="remark"/>：
																	</label>
																</td>
																<td>
																	<input id="remark" name="labPageEditorVo.remark" value="${labPageEditorVo.remark}" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('listLabPage.action');return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b></a>
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
																	<l:a uri="page/labPageEditor/preAddLabPage.action" value="admin.add" />
																</td>
																<td>
																	<l:a uri="page/labPageEditor/updateLabPage.action" onclick="preAddBatch('listFilePage.action');" value="batch.add" />
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
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="cn.name"/>
													</th>
													<th>
														<s:text name="file.name"/>
													</th>
													<th>
														<s:text name="remark"/>
													</th>
													<th>
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
																${name}
															</td>
															<td class="l">
																${fileName}
															</td>
															<td>
																<s:if test="${remark != null} && ${fn:length(remark) > 45}">
																	${fn:substring(remark, 0, 45)}...
																</s:if>
																<s:else>
																	${remark}
																</s:else>
															</td>
															<td class="c">
																<l:a href="#" uri="page/labPageEditor/preUpdateLabPage.action" onclick="goToNextAction('preUpdateLabPage.action?labPageEditorVo.id=${id}');" value="lab.code.modify" />
																<l:a href="#" uri="page/labPageEditor/" onclick="preEdit('${id}');" value="lab.code.edit" />
																<l:a href="#" uri="page/labPageEditor/updateLabPage4Del.action" onclick="deletePage('updateLabPage4Del.action?labPageEditorVo.ids=${id}');" value="lab.code.del" />
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="8" align="center" valign="middle">
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
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=pageForm" flush="true" />
								</td>
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