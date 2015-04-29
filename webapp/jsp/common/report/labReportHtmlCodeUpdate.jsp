<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style type="text/css" media="screen">
#editor {
	position: absolute;
	margin-top: 20px;
	margin-bottom: 0px;
	top: 20px;
	left: 0PX;
	bottom: 0PX;
	right: 0PX;
	width: 1050px;
	border: 2px solid #999;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px
		rgba(0, 0, 0, 0.60);
	-moz-box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px
		rgba(0, 0, 0, 0.60);
	box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px
		rgba(0, 0, 0, 0.60);
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-box-flex: 1;
	-moz-box-flex: 1;
	-ms-box-flex: 1;
	box-flex: 1;
}
</style>

	</head>
	<script>
	function submitvalue(actionstr){
		var conStr=editor.getValue();
		$('#labReportVo_file').val(conStr);
		var df = document.pageForm;
		df.action=actionstr;
		df.submit();
	}
</script>
	<body id="mainid" style="overflow-y: auto;">
		<table id="bodyTable" class="bodytable" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td id="bodyCell" class="oRight">
					<form action="" method="post" name="pageForm">
						<s:token></s:token>
						<input type="hidden" name="labReportVo.id" id="labReportVo_id" value="${labReportVo.id}" />
						<div class="FUNCIONBARNEW">
							<table>
								<tr>
									<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
										<table cellspacing="0" cellpadding="0" border="0">
											<tr>
												<td>
													<l:a uri="report/labReport/updateReportModel.action" onclick="submitvalue('updateReportModel.action');" img="/img/chakan.gif" value="tmp.preview" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div id="editor">
						</div>
						<div style="display: none;">
							<textarea rows="" cols="" id="labReportVo_file" name="labReportVo.file">${labReportVo.file}</textarea>
						</div>
					</form>
				</td>
			</tr>
		</table>
	</body>
	<script src="/js/ace-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
	<script>
	    var editor = ace.edit("editor");
	    editor.setTheme("ace/theme/eclipse");
	    editor.getSession().setMode("ace/mode/javascript");
	    editor.setValue($('#labReportVo_file').val());
	</script>
</html>