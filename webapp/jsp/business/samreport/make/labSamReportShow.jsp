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
<script> 

function showReport(){
	var height = window.screen.height-250;
	var url  = '${basePath}/samreport/labSamReport/showLabReport4Bus.action?labReportVo.path=${labSamReportVo.reportPath}&labReportVo.id=${labSamReportVo.reportTempId}&labReportVo.busInsId=${labSamReportVo.id}';
	$.dialog({
		id:'id',
		content:'url:'+url,
		title:'<s:property value="getText('pageprint')"/>',
		opacity:0.4,
		width:1050,
		height:height,
		max:false,
		min:false,
		lock:true
	 });
}
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
								<span><s:text name="look.check
								"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labSamReportForm">
							<s:token></s:token>
							<input type="hidden" name="labSamReportVo.id" value="${labSamReportVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="base.info"/></span>
										<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
									</div>
									<div>
										<table class="FormtableCon">
											<tr>
												<td width="100"><label><s:text name="task.no"/>：</label></td>
												<td width="300">
													${labSamReportVo.busNo}
												</td>
												<td width="100"><label><s:text name="cust.name"/>：</label></td>
												<td>
													${labSamReportVo.labCustomerName}
												</td>
											</tr>
											<tr>
												<td><label><s:text name="report.no"/>：</label></td>
												<td>
													${labSamReportVo.reportNo}
												</td>
												<td><label><s:text name="report.type"/>：</label></td>
												<td>
													${labSamReportVo.reportType}
												</td>
											</tr>
											<tr>
												<td><label><s:text name="report.sum"/>：</label></td>
												<td>
													${labSamReportVo.reportNum}
												</td>
												<td><label><s:text name="get.report.style"/>：</label></td>
												<td>
													${labSamReportVo.reportPost}
												</td>
											</tr>
											<tr>
												<td><label><s:text name="report.type"/>：</label></td>
												<td>
													${labSamReportVo.sampType}
												</td>
												<td><label><s:text name="comp.report.date"/>：</label></td>
												<td>
													${labSamReportVo.finishDate}
												</td>
											</tr>
											<tr>
												<td><label><s:text name="check.conclu"/>：</label></td>
												<td colspan="3">
													${labSamReportVo.testResult}
												</td>
											</tr>
											<tr>
												<td><label><s:text name="lab.remark"/>：</label></td>
												<td colspan="3">
													${labSamReportVo.remark}
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="check.result"/></span>
										<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
									</div>
									<div>
										<table class="FormtableCon_sform">
											<thead>
												<th width="100"><s:text name="sam.no"/></th>
												<th width="100"><s:text name="sam.name"/></th>
												<th width="100"><s:text name="check.value"/></th>
												<th><s:text name="chk.method"/></th>
												<s:if test="${labSamReportVo.reportType=='检验'}">
													<th><s:text name="result"/></th>
												</s:if>
												<th width="100"><s:text name="checker"/></th>
												<th width="100"><s:text name="chk.date"/></th>
											</thead>
											<s:iterator value="labSamReportVo.reportDataList" status="st">
												<tr>
													<td>${sampCode}</td>
													<td>${labSamName}</td>
													<td>${testResult}</td>
													<td>${methodName}</td>
													<s:if test="${labSamReportVo.reportType=='检验'}">
														<td>${result}</td>
													</s:if>
													<td>${testUserName}</td>
													<td>${testTime}</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="report.preview"/></span>
									</div>
									<table class="FormtableCon">
												<tr>
													<td width="100">
														<label>
															<s:text name="report.info"/>：
														</label>
													</td>
													<td>
														<s:if test="${fn:length(labSamReportVo.reportPath)>0}">
															<a id="reportPatha" class="zPushBtn"
																href="javascript:void(0);"
																onclick="showReport();"><img
																	height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="previewed"/></b>
															</a>
														</s:if>
														<s:else>
															<s:text name="ungenerate.report"/>
														</s:else>
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
