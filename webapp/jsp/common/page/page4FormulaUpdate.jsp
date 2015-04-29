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
		if(null == $('#js').val() || '' == $('#js').val()){
			alert('请输入js信息');
			return ;
		}
		var df = document.pageForm;
		df.action=actionstr;
		df.submit();
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
								<s:text name="formula.edit"/>：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="pageForm" theme="simple">
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
															<l:a uri="page/labPageEditor/updateLabPage4Formula.action" onclick="submitvalue('updateLabPage4Formula.action');" value="lab.code.modify" />
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
															<s:text name="page.name"/>：
														</label>
													</td>
													<td>
														${labPageEditorVo.name}
													</td>
													<td>
														<label>
															<s:text name="page.path"/>：
														</label>
													</td>
													<td>
														${labPageEditorVo.url}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="obj.name"/>：
														</label>
													</td>
													<td>
														${labPageEditorVo.objName}
													</td>
													<td>
														<label>
															<s:text name="object.path"/>：
														</label>
													</td>
													<td>
														${labPageEditorVo.objUrl}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															JAVA_SCRIPT：
														</label>
													</td>
													<td>
														<textarea rows="5" cols="60" id="js" name="labPageEditorVo.js">${labPageEditorVo.js}</textarea>
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