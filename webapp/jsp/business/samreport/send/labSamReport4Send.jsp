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
function submitvalue(actionstr){
	$('form').attr('action','<%=basePath%>'+actionstr);
	$('form').submit();
}
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
								<span><s:text name="flow.check"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post"
							name="labSamReportForm">
							<s:token></s:token>
							<input type="hidden" name="labSamReportVo.id" value="${labSamReportVo.id}" />
											<input type="hidden" name="labSamReportVo.busId"
								value="${labSamReportVo.busId}" />
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
														<td>
															<l:a uri="samreport/labSamReport/updateLabSamReport4Send.action"
																onclick="submitvalue('samreport/labSamReport/updateLabSamReport4Send.action');"
																value="lab.code.save" img="/img/icon/filesave.gif"/>
														</td>
														<td>
															<l:a uri="samreport/labSamReport/updateLabSamReport4Send.action"
																onclick="submitvalue('samreport/labSamReport/updateLabSamReport4Send.action?labSamReportVo.auditResult=1');"
																value="post.commit" img="/img/icon/filesave.gif"/>
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
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div style="display: none;">
										<table class="FormtableCon">
											<tr>
												<td width="100">
													<label>
														<s:text name="task.no"/>：
													</label>
												</td>
												<td width="300">
													${labSamReportVo.busNo}
												</td>
												<td width="100">
													<label>
														<s:text name="cust.name"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.labCustomerName}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="report.no"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.reportNo}
												</td>
												<td>
													<label>
														<s:text name="report.type"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.reportType}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="report.sum"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.reportNum}
												</td>
												<td>
													<label>
														<s:text name="get.report.style"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.reportPost}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="report.type"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.sampType}
												</td>
												<td>
													<label>
														<s:text name="comp.report.date"/>：
													</label>
												</td>
												<td>
													${labSamReportVo.finishDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="check.conclu"/>：
													</label>
												</td>
												<td colspan="3">
													 ${labSamReportVo.testResult} 
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="lab.remark"/>：
													</label>
												</td>
												<td colspan="3">
													 ${labSamReportVo.remark}
												</td>
											</tr>
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
															height="20" width="20" src="${basePath}/img/chakan.gif" /><b><s:text name="previewed"/></b>
													</a>
												</s:if>
												<s:else>
													<s:text name="ungenerate.report"/>
												</s:else>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="msg.send.msg"/></span>
									</div>
									<div>
										<table class="FormtableCon_sform">
											<tr>
												<td width="100"><label><s:text name="cust.name"/>：</label></td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.labCustomerName" value="${labSamReportVo.samReportEndVo.labCustomerName}" size="40"/>
												</td>
												<td width="100"><label><s:text name="rev.reporter"/>：</label></td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.toUser" value="${labSamReportVo.samReportEndVo.toUser}" size="40"/>
												</td>
											</tr>
											<tr>
												<td>
													<label><s:text name="fax.large"/>：</label>
												</td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.fax" value="${labSamReportVo.samReportEndVo.fax}" size="40"/>
												</td>
												<td>
													<label>Email：</label>
												</td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.email" value="${labSamReportVo.samReportEndVo.email}" size="40"/>
												</td>
											</tr>
											<tr>
												<td>
													<label><s:text name="theme.phone"/>：</label>
												</td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.telephone" value="${labSamReportVo.samReportEndVo.telephone}" size="40"/>
												</td>
												<td>
													<label><s:text name="address.large"/>：</label>
												</td>
												<td>
													<input type="text" name="labSamReportVo.samReportEndVo.address" value="${labSamReportVo.samReportEndVo.address}" size="40"/>
												</td>
											</tr>
											<tr>
												<td><label><s:text name="sender.style"/>：</label></td>
												<td id="reportPost">
													<s:checkboxlist theme="simple" list="{'自取','快递','传真','Email'}" name="labSamReportVo.samReportEndVo.reportPost"></s:checkboxlist>
												</td>
												<td><label><s:text name="msg.send.date"/>：</label></td>
												<td>
													<input size="40" type="text" name="labSamReportVo.samReportEndVo.sendDate" value="${labSamReportVo.samReportEndVo.sendDate}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
												</td>
											</tr>
											
											<tr>
												<td>
													<label><s:text name="post.com"/><br/><s:text name="name.no"/>：</label>
												</td>
												<td colspan="3">
													<textarea rows="2" cols="60" name="labSamReportVo.samReportEndVo.express" id="express">${labSamReportVo.samReportEndVo.express}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													<label><s:text name="lab.remark"/>：</label>
												</td>
												<td colspan="3">
													<textarea rows="4" cols="60" name="labSamReportVo.samReportEndVo.remark" id="">${labSamReportVo.samReportEndVo.remark}</textarea>
												</td>
											</tr>
										</table>
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
<script>
	$(function(){
		var reprotStr='${labSamReportVo.samReportEndVo.reportPost}';
		var postStr=reprotStr.split(',');
		$('#reportPost').find('input[type="checkbox"]').each(function(){ 
	         var checkVal=$(this).val(); 
	         for(var i=0;i<postStr.length;i++){
	         	var str=postStr[i].replace(' ','');
	         	if(str==checkVal){
	         		$(this).prop('checked',true);
	         	}
	         }
 	   	});  
	});
</script>
</html>
