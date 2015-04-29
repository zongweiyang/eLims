<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	function submitvalue(actionstr){
		$('#pageFileForm').attr('action','<%=basePath%>page/labPageEditor/'+actionstr).submit();
	}
	function submit4Add(actionstr){
		if($('input[name="labPageEditorVo.urls"]:checked').length == 0){
			alert('请至少选择一项');
			return ;
		}
		$('#pageFileForm').attr('action','<%=basePath%>page/labPageEditor/'+actionstr).submit();
	}
	function clickSelected(obj){
		if($(obj).prop('checked')){
			$('input[name="labPageEditorVo.urls"]').each(function(){
				$(this).prop('checked',true);
			});
		}else{
			$('input[name="labPageEditorVo.urls"]').each(function(){
				$(this).prop('checked',false);
			});
		}
	}
	function goToNextAction(actionstr){
		window.location.href = '<%=basePath%>page/labPageEditor/'+actionstr;
	}
	$(function(){
		if('${labPageEditorVo.param}' == 'success'){
			W.refeshThis();
			api.close();
		}
	})
</script>
		<style>
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="pageFileForm" id="pageFileForm">
			<input type="hidden" id="parentUrl" name="labPageEditorVo.parentUrl" value="${labPageEditorVo.parentUrl}" />
			<table id="bodyTable" class="bodytable" width="98%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox" style="min-height: 100px;">
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="file.name"/>：
																	</label>
																</td>
																<td>
																	<input id="fileName" name="labPageEditorVo.fileName" value="${labPageEditorVo.fileName}" />
																</td>
																<td>
																	<l:a uri="page/labPageEditor/listFilePage.action" onclick="submitvalue('listFilePage.action');" value="fun.query" />
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
																	<l:a uri="page/labPageEditor/addLabPage4Batch.action" onclick="submit4Add('addLabPage4Batch.action');" value="page.confirm" />
																</td>
																<td>
																	<s:if test="${labPageEditorVo.grandUrl != null} && ${labPageEditorVo.grandUrl != ''}">
																		<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="goToNextAction('listFilePage.action?labPageEditorVo.parentUrl=${labPageEditorVo.grandUrl}');"><img height="20" width="20" src="<%=basePath %>img/fanhui.gif"/><b><s:text name="upper.list"/></b></a>
																	</s:if>
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
														<input type="checkbox" id="allCheckBox" key="labPageEditorVo.ids" onclick="clickSelected(this);" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="file.name"/>
													</th>
													<th>
														<s:text name="file.path"/>
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
																<input type="checkbox" name="labPageEditorVo.urls" value="${url}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${fileName}
															</td>
															<td class="l" title="${showUrl}">
																<s:if test="${showUrl != null} && ${fn:length(showUrl) > 45}">
																	${fn:substring(showUrl, 0, 45)}...
																</s:if>
																<s:else>
																	${showUrl}
																</s:else>
															</td>
															<td class="c">
																<s:if test="${type == '1'}">
																	<hr:jhref hrAttribute="href:javascript" hrFunctionType="update" hrFunctionUrl="page/labPageEditor/listFilePage.action" hrFunctionValue="打开" hrJsFunctions="onclick:goToNextAction('listFilePage.action?labPageEditorVo.parentUrl=${url}');return false;" hrIsInterval="true" />
																</s:if>
																<s:if test="${type == '0'}">
																	-
																</s:if>
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
								<td align="center"><jsp:include page="../../../jsp/include/page.jsp?actionparam=listFilePage.action&formName=pageFileForm" flush="true" /></td>
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
		<%@ include file="../../../jsp/include/foot.jsp"%>
	</body>
</html>