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
	margin: 0 auto;
	top: 170px;
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
	-moz-box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
	box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
	box-shadow: 0 0 80px rgba(0, 0, 0, 0.30) inset 0 0 5px rgba(0, 0, 0, 0.60);
}
</style>

	</head>
	<script language=javascript>
	function submitvalue(actionstr){
		$('#labPageEditorVo_file').val(editor.getValue());
		$('#pageForm').attr('action','<%=basePath%>page/labPageEditor/'+actionstr).submit();
	}
	$(function(){
		if(null != $('#objName').val() && null != $('#objUrl').val()){
			$.ajax({
				url:'<%=basePath%>page/labPageEditor/ajaxGetParameter.action',
				data:{'labPageEditorVo.objName':$('#objName').val(),'labPageEditorVo.objUrl':$('#objUrl').val()},
				type:'post',
				dataType:'text',
				success:function (data){
					if(null != data && data != ''){
						var result = eval('('+ data +')');
						$('#paramSelect').append(
							$('<option>').attr({'value':''}).html('请选择')
						)
						if(null != result && result.length > 0){
							for(var i = 0; i < result.length; i++){
								$('#paramSelect').append(
									$('<option>').attr({'value':'\${' + result[i].id + '}'}).html(result[i].name)
								)
							}
						}
					}else{
						$('#paramSelect').append(
							$('<option>').attr({'value':''}).html('请选择')
						)
					}
				}
			});
		}
	});
	function selectThis(obj){
		$('#paramInput').val($('#paramSelect').val());
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
								<span><s:text name="lab.code.edit"/></span>
							</h2>
						</div>
						<form action="" method="post" name="pageForm" id="pageForm" theme="simple">
							<input type="hidden" name="labPageEditorVo.id" id="labPageEditorVo_id" value="${labPageEditorVo.id}" />
							<input type="hidden" name="labPageEditorVo.objName" id="objName" value="${labPageEditorVo.objName}" />
							<input type="hidden" name="labPageEditorVo.objUrl" id="objUrl" value="${labPageEditorVo.objUrl}" />
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="page/labPageEditor/listLabPageEdit.action" onclick="backList();" value="msg.back" />
														</td>
														<td>
															<l:a uri="page/labPageEditor/updateLabPage4Edit.action" onclick="submitvalue('updateLabPage4Edit.action');" value="lab.code.save" />
														</td>
														<td>
															<label>
																<s:text name="can.used"/>：
															</label>
														</td>
														<td>
															<select id="paramSelect" onchange="selectThis(this);"></select>
															<input id="paramInput" size="60" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
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
										</table>
									</div>
								</div>
							</div>
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
</html>