<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style type="text/css"></style>
	</head>
	<script language=javascript>
	function submitvalue(actionstr){
		$('#pageForm').attr('action','<%=basePath%>page/labPageEditor/'+actionstr).submit();
	}
</script>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								<s:text name="page.manage"/>：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="pageForm" id="pageForm" theme="simple">
							<input type="hidden" id="labPageEditorVo_id" value="${labPageEditorVo.id}" name="labPageEditorVo.id" />
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="page/labPageEditor/updateLabPage.action" onclick="submitvalue('updateLabPage.action');" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="TabTable" style="padding-top: 0; margin: 0; background: none;">
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="page.information"/></span>
										</div>
										<div style="margin-top: 10px; margin-bottom: 10px;">
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="cn.name"/>：
														</label>
													</td>
													<td>
														<input id="name" value="${labPageEditorVo.name}" valType="required,strLength" max="64" msg="中文名称不能为空" strLength-msg="长度不能超过64位" maxlength="64" name="labPageEditorVo.name" title='<s:property value="getText('funcnesaddpage')"/>' />
													</td>
													<td>
														<label>
															<s:text name="page.path"/>：
														</label>
													</td>
													<td>
														<input id="url" value="${labPageEditorVo.url}" valType="required,strLength" max="128" msg="页面路径不能为空" strLength-msg="长度不能超过128位" maxlength="128" name="labPageEditorVo.url" title="例：/jsp/base/function/functionAdd.jsp" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="obj.name"/>：
														</label>
													</td>
													<td>
														<input id="objName" value="${labPageEditorVo.objName}" valType="required,strLength" max="64" msg="对象名称不能为空" strLength-msg="长度不能超过64位" maxlength="64" name="labPageEditorVo.objName" title="例：labFunctionVo" />
													</td>
													<td>
														<label>
															<s:text name="object.path"/>：
														</label>
													</td>
													<td>
														<input id="objUrl" value="${labPageEditorVo.objUrl}" valType="required,strLength" max="128" msg="对象路径不能为空" strLength-msg="长度不能超过128位" maxlength="128" name="labPageEditorVo.objUrl" title="例：cn.labsoft.base.function.vo.LabFunctionVo" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="40" rows="3" id="remark" name="labPageEditorVo.remark">${labPageEditorVo.remark}</textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>