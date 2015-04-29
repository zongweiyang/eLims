<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style type="text/css" media="screen">
#editor {
	position: absolute;
	margin-top: 10px;
	top: 80px;
	left: 0;
	bottom: 0;
	right: 0;
	width: 98%;
	border: 2px solid #999;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
	-moz-box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
	box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-box-flex: 1;
	-moz-box-flex: 1;
	-ms-box-flex: 1;
	box-flex: 1;
}

.myworkingbox {
	min-height: 480px
}

.bodytable {
	min-height: 400px
}
</style>

	</head>
	<script language=javascript>
	function submitvalue(actionstr){
		$('#labPageEditorVo_file').val(editor.getValue());
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
								<s:text name="file.edit"/>：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="pageForm" id="pageForm" theme="simple">
							<input type="hidden" name="labPageEditorVo.id" id="labPageEditorVo_id" value="${labPageEditorVo.id}" />
							<div id="editor">
							</div>
							<div style="display: none;">
								<textarea rows="" cols="" id="labPageEditorVo_file" name="labPageEditorVo.file">${labPageEditorVo.file}</textarea>
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
	<script src="/js/ace-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
	<script>
	    var editor = ace.edit("editor");
	    editor.setTheme("ace/theme/eclipse");
	    editor.getSession().setMode("ace/mode/javascript");
	    editor.setValue($('#labPageEditorVo_file').val());
	</script>
	<script language="javascript" type="text/javascript">
	function required() {
	}
	function maxlength() {
	}
	function email() {
	}
	function DateTimeValidations() {
	}
	function FloatValidations() {
	}
	function IntegerValidations() {
	}
	function DateValidations() {
	}
</script>
</html>