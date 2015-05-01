<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
			</script>
	<body>
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="mouldForm">
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="science/labSciProcess/listLabSciProcess.action" onclick="goAction('listLabSciProcess.action');" value="msg.back" />
														</td>													
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
											<tr>
												<td class="r" width="150"><label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label></td>
												<td colspan="3">${labSciProcessVo.type }</td>
											</tr>
											<tr>
												<td class="r" width="150"><label>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label></td>
												<td>${labSciProcessVo.name }</td>
												<td class="r" width="150"><label>录&nbsp;&nbsp;入&nbsp;&nbsp;人：</label></td>
												<td>${labSciProcessVo.writeUser }</td>
											</tr>
											<tr>
												<td class="r" width="150"><label>开始时间：</label></td>
												<td>${labSciProcessVo.startTime }</td>
												<td class="r" width="150"><label>结束时间：</label></td>
												<td>${labSciProcessVo.endTime }</td>
											</tr>
											<!-- <tr>
												<td class="r" width="150"><label>原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</label></td>
												<td>${labSciProcessVo.original }</td>
												<td class="r" width="150"><label>复&nbsp;&nbsp;印&nbsp;&nbsp;件：</label></td>
												<td>${labSciProcessVo.hardCopy }</td>
											</tr> -->
											<tr>
												<td class="r" width="150"><label><s:text name="contentdesd"/>：</label></td>
												<td colspan="3"><textarea readonly="readonly" valType="required" msg='<s:property value="getText('connotempty')"/>'  name="labSciProcessVo.contents" cols="40" rows="3" id="contents" >${labSciProcessVo.contents }</textarea></td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="lab.remark"/>：</label></td>
												<td colspan="3"><textarea readonly="readonly" name="labSciProcessVo.remark" cols="40" rows="3" id="remark" >${labSciProcessVo.remark }</textarea></td>
											</tr>
											<tr>
												<td class="r">
													<label>
														<s:text name="upload.attachment"/>：
													</label>
												</td>
												<td colspan="3">
														<s:if test="${fn:length(uplodeList)>0}">
													<s:iterator status="st2" value="#request.uplodeList" id="">
														<span> <a href="${path }" id="fileId">${name
																} </a></span>
													</s:iterator>&nbsp;&nbsp;&nbsp;
												</s:if>
												<div id="upfiles"></div>
												</td>
											</tr>
									</table>
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
