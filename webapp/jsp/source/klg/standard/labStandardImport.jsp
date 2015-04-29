<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<script language=javascript> 
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
		function closeMe(){
		  	api.close();
	 	}
	 	var basePath = '${basePath}';
		function submitvalueforlist(){
			 var file = $('#file').val();
		    if(file == ''){
		    	alert("请选择文件！");
		    	return false;
		    }
		    var stuff=file.match(/^(.*)(\.)(.{1,8})$/)[3]; 
	        if(stuff!='xls')
	        {
	           alert('文件类型不正确，请选择.xls文件');
	           return false;
	        }
		    var df=document.listForm;
			df.action=basePath+'/klg/labStandard/importLabStandard4Excel.action?labStandardVo.standTypeId=${labStandardVo.standTypeId}&type=1';
		    df.submit();
		}
	 	
		
</script>
	<body class="" id="mainid" style="overflow-y: auto;">
		<div id="popup">
			<form name="listForm" action="#" method="post" enctype="multipart/form-data">
			<input type="hidden" name="labStandardVo.standTypeName" value="${labStandardVo.standTypeName}" />
			<input type="hidden" name="labStandardVo.standTypeId" value="${labStandardVo.standTypeId}" />
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
												<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalueforlist();"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="import"/></b> </a>
											</td>
											<td>
												<a id="BtnPreview" class="zPushBtn"  href="<%=basePath%>${fileUrl}" ><img height="20" width="20" src="<%=basePath%>img/icon_excel.gif" /><b><s:text name="module.download"/></b> </a><span style="color: red;font-size: 12px;"><strong>（<s:text name="tipdownload"/>）</strong></span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<table class="FormtableCon">
						<tr>
							<td>
								<label>请选择Excel文件：</label>
							</td>
							<td>
								<input type="file" name="upload" id="file" valType="suffix" suffix=".xlsx,.xls" msg="请选择Excel格式文件"   />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
	<script>
		$(function(){
			var type = '${type}';
			if(type== "1"){
				closeMe();
			}
		});
	</script>
</html>
