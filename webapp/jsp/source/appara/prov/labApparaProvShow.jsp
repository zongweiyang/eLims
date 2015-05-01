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
        if (actionstr=='addApparatusVerificationRecord.action'){
			if(check()){
	            df.submit();
			}
        }else{
			 df.submit();
		}
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
    	var url='<%=basePath%>appara/prov/printApparaProv.action?labApparaProvVo.id='+id;
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
			<input type="hidden" name="labApparaProvVo.id" value="${labApparaProvVo.id }" />
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
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/appara/prov/listApparaProv4View.action?labApparaProvVo.id=${id}&labApparaProvVo.typeId=${labApparaProvVo.typeId}');return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="printlnTable('${labApparaProvVo.id}');return false;"><img height="20" width="20" src="<%=basePath%>img/dayin.gif" /><b><s:text name="print"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" id="printDiv">
								<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
									<span><s:text name="app.stock.rec"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td width="150">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											<input type="text" disabled="disabled" size="30" value="${labApparaProvVo.labAppName}" name="labApparaProvVo.labAppName">
										</td>
										<td width="150">
											<s:text name="msg.date"/>：
										</td>
										<td colspan="2">
											<input id="verificationTime" disabled="disabled" value="${labApparaProvVo.verDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'verificationTime\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
										</td>
										</td>
									</tr>
									<tr>
										<td width="150">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaProvVo.auditName}" disabled="disabled" size="30">
										</td>
										<td width="150">
											<s:text name="send.check"/>：
										</td>
										<td colspan="2">
											<input disabled="disabled" size="30" value="${labApparaProvVo.checkName}" type="text" id="labApparaProvVo.checkName">
										</td>
									</tr>
									<tr>
										<td width="150">
											<s:text name="fault.occ.rea"/>：
										</td>
										<td colspan="5">
											<textarea disabled="disabled" rows="5" cols="80">${labApparaProvVo.verInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td width="150">
											<s:text name="fault.occ.deal"/>：
										</td>
										<td colspan="5">
											<textarea disabled="disabled" rows="5" cols="80">${labApparaProvVo.verCon}</textarea>
										</td>
									</tr>
									<tr>
										<td >
											<label>
												<s:text name="sudited.sound"/>：
											</label>
										</td>
										<td colspan="5">
											<textarea rows="3" cols="40" disabled="disabled">${labApparaProvVo.auditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td width="150">
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td colspan="2">
											<input disabled="disabled" size="30" value="${labApparaProvVo.user2}" id="user2" type="text" size="20" />
										</td>
										<td width="150">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td colspan="2">
											<input disabled="disabled" size="30" value="${labApparaProvVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="apply.opinion"/>：
											</label>
										</td>
										<td colspan="5">
											<textarea rows="3" cols="40" disabled="disabled" name="labApparaProvVo.pauditMessage">${labApparaProvVo.pauditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td colspan="2">
											<input disabled="disabled" size="30" value="${labApparaProvVo.user3}" id="user3" type="text" size="20" />
										</td>
										<td>
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td colspan="2">
											<input disabled="disabled" size="30" value="${labApparaProvVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" style="margin-top: 0px;">
								<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
									<span><s:text name="biz.progress"/></span>
								</div>
								<div id="contentFrame">
									<iframe name="content" id="content" src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${labApparaProvVo.id}" onload="document.all.content.style.height=document.content.document.body.clientHeight" scrolling="yes" frameborder="0" width="100%" height="450">
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
