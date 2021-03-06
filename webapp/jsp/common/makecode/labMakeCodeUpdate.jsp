<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



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
			  	function submitvalue(actionstr){
					var df = document.labMakeCodeForm;
					 	df.action=actionstr;
					  	df.submit();
				}
			</script>
	<body>
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
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="labMakeCodeForm">
							<input type="hidden" name="labMakeCodeVo.id" value="${labMakeCodeVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('updateLabMakeCode.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"></s:text></s:tabbedPanel></b> </a>
														</td>
														<td>
															<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="location.href= 'listLabMakeCode.action';return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"></s:text></b> </a>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="build.code.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="module.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labMakeCodeVo.name" id="name" value="${labMakeCodeVo.name}" />
												<font color="red">&nbsp;&nbsp;*</font>
											</td>
											<td>
												<label>
													<s:text name="module.cn.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labMakeCodeVo.nameChin" id="nameChin" value="${labMakeCodeVo.nameChin}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="class.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labMakeCodeVo.className" id="className" value="${labMakeCodeVo.className}" />
											</td>
											<td>
												<label>
													<s:text name="class.cn.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labMakeCodeVo.classNameChin" id="classNameChin" value="${labMakeCodeVo.classNameChin}" />
											</td>
										</tr>
									</table>
								</div>
						</form>
					</div>
				</td>
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
