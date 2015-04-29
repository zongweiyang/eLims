<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript"
			src="<%=basePath%>js/powerFloat/jquery-powerFloat.js"></script>

		<link href="<%=basePath%>js/powerFloat/css/powerFloat.css" media="all"
			rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/headerfooter.css" media="all"
			rel="stylesheet" type="text/css" />
		<script>
		
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	    function closeMe(){
			api.close();
	    }
</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

.myworkingbox {
	height: auto;
	min-height: auto;
	border: 0;
}

html {
	_overflow-x: hidden;
}
</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form action="" method="post" name="reaReagenttypeForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">

						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox" style="padding-top: 20px;">
										<div class="buttonbar2">
											<label>
												特殊字符展示：
											</label>
										</div>
										<table id="tableId" class="myworkingboxttable1" cellspacing="0"
											cellpadding="0">
											<tr>
												<td style="">
													演示：H<sub>2</sub>O 代码：<input size="15" style="border: 0" type="" value="H<sub>2</sub>O" /> &nbsp;&nbsp;复制：<input style="border: 0; background-color: green;" type="" value="<sub></sub>" />
												</td>
											</tr>
											<tr>
												<td style="">
													演示：I<sup>2</sup> 代码：<input size="15" style="border: 0" type="" value="I<sup>2</sup>" /> &nbsp;&nbsp;复制：<input style="border: 0; background-color: green;" type="" value="<sup></sup>" />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
