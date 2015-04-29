<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property value="#session.SessionContainer.orgUnit" /></title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.myworkingboxttable {
	width: 98%;
	border: 0px solid #CFCFCF;
	margin: 0 5px;
	border-collapse: collapse;
}
</style>
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
       	function goToNextAction(url){
       		var df = document.listform;
			df.action = url;
		  	df.submit();
       	}
       	function submitExcel(actionstr){
       		var df = document.listform;
            df.action = actionstr;
            df.submit();
       	}
       	function downExcel(){
       		
       	}
</script>
	</head>

	<body class="" id="mainid" style="overflow-y: auto;">
		<form action="" method="post" name="listform" target="workarea" theme="simple" enctype="multipart/form-data" >
			<table>
				<tr>
					<td>
						<input type="file" name="upload" id="file" valType="suffix" suffix=".xlsx,.xls" msg="请选择Excel格式文件" />
					</td>
				</tr>
				<tr>
					<td>
						<img height="20" width="20" src="<%=basePath%>img/icon_excel.gif"  />
						<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${labApparaVo.modPath}&fileName=${labApparaVo.modName}"><s:text name="app.list.tmp.down"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="labApparaVo.typeId" value="${labApparaVo.typeId}" />
						<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitExcel('<%=basePath%>appara/excel/importLabAppExcel.action');return false;">
							<img height="20" width="20" src="<%=basePath%>img/filesave.gif"  /><b><s:text name="post.commit"/></b>
						</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
