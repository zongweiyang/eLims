<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.2.js"></script>
		<script type="text/javascript">
    function submitvalue(actionstr){
		var df = document.forms[0];
	    df.action = actionstr;
		df.submit();
    }
    function check(){
        if ($("#verificationTime").val()=='' || $("#verificationTime").val()== null) {
            alert('<s:property value="getText('selectcheckdate')"/>');
			return false;
        }
        else {
            return true;
        }
    }
    function printlnTable(id){
    	var url='<%=basePath%>appara/check/printLabApparaCheck.action?labApparaCheckVo.id='+id;
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'<s:property value="getText('apperrorprint')"/>',
			maxBtn:false,
			rang: true,
			drag: true,
			resize: false,
			bgcolor:'#000',
			min:false,
			max:false,
			opacity:0.4,
			width:810, 
			iconTitle:false,
			btnBar:false,
			height:600,
			lock:true,
			autoPos:{left:'center',top:'center'}
		 });
    }
	</script>
		<style>
.tabtableboxtable td label {
	float: none;
}

.tabtableboxtable {
	border: #c1c1c1 0.5px solid;
	border-collapse: collapse;
}

.tabtableboxtable td {
	border: #c1c1c1 0.5px solid;
	border-collapse: collapse;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto;">
		<form action="" method="post">
			<input type="hidden" name="labApparaCheckVo.id" value="${labApparaCheckVo.id }" />
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
									${funName }：
									<span><s:text name="look.check"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW" width="100%" align="left">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /> <b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="printlnTable('${labApparaCheckVo.id}');return false;"><img height="20" width="20" src="<%=basePath%>img/dayin.gif" /><b><s:text name="print"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" style="margin-top: 0px;">
								<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
									<span><s:text name="record.list.bet"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td class="w100">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											<input type="text" disabled="disabled" value="${labApparaCheckVo.appName}" />
										</td>
										<td class="w100">
											<s:text name="check.datetime"/>：
										</td>
										<td colspan="2">
											<input id="verTime" disabled="disabled" value="${labApparaCheckVo.verDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'verificationTime\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="check.methods"/>：
										</td>
										<td colspan="2">
											<input id="ext05" disabled="disabled" value="${labApparaCheckVo.ext05 }" type="text" maxlength="64" size="30" />
										</td>
										<td class="w100">
											<s:text name="checker"/>：
										</td>
										<td colspan="2">
											<input id="checkName" disabled="disabled" value="${labApparaCheckVo.checkName}" type="text" maxlength="64" size="30" />
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaCheckVo.auditName}" disabled="disabled" size="30">
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="chk.rcd"/>：
										</td>
										<td colspan="5">
											<textarea disabled="disabled" rows="5" cols="100">${labApparaCheckVo.verInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="chk.ret"/>：
										</td>
										<td colspan="5">
											<textarea disabled="disabled" rows="5" cols="100">${labApparaCheckVo.verCon }</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="sudited.sound"/>：
											</label>
										</td>
										<td align="left" colspan="3">
											<textarea cols="70" rows="6" disabled="disabled" id="auditMessage" size="80">${labApparaCheckVo.auditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td align="left">
											<input disabled="disabled" value="${labApparaCheckVo.user2}" id="user2" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input disabled="disabled" value="${labApparaCheckVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="apply.opinion"/>：
											</label>
										</td>
										<td align="left" colspan="3">
											<textarea cols="70" rows="6" disabled="disabled" id="pauditMessage" size="80">${labApparaCheckVo.pauditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="apply.peo"/>：
											</label>
										</td>
										<td align="left">
											<input disabled="disabled" value="${labApparaCheckVo.user3}" id="user3" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input disabled="disabled" value="${labApparaCheckVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" style="margin-top: 0px;">
								<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
									<span><s:text name="biz.progress"/></span>
								</div>
								<div id="contentFrame">
									<iframe name="content" id="content" src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${labApparaCheckVo.id}" onload="document.all.content.style.height=document.content.document.body.clientHeight" scrolling="yes" frameborder="0" width="100%" height="450">
									</iframe>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
