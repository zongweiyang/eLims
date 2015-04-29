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
	    var flage = $("input[name=labApparaProvVo.auditResult]:checked").val();
	    if("-1"==flage){
	    	if(''==$("#pauditMessage").val()){
		    	alert("请填写审批意见");
	    		return false;
	    	}
	    }
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
            alert("请选择检定日期!");
			return false;
        }
        else {
            return true;
        }
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
									<span><s:text name="flow.audit"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="backList();return fasle;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabAppProvPaudit.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
									<span><s:text name="app.stock.rec"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td class="w100">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											<input type="text" readonly="readonly" value="${labApparaProvVo.labAppName}" name="labApparaProvVo.labAppName">
											<input type="hidden" value="${labApparaProvVo.labAppId}" name="labApparaProvVo.labAppId">
											<input type="hidden" value="${labApparaProvVo.labAppNo}" name="labApparaProvVo.labAppNo">
											<input type="hidden" value="${labApparaProvVo.spec}" name="labApparaProvVo.spec">
										</td>
										<td class="w100">
											<s:text name="msg.date"/>：
										</td>
										<td colspan="2">
											<input id="verificationTime" readonly="readonly" name="labApparaProvVo.verDate" value="${labApparaProvVo.verDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'verificationTime\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
										</td>
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" readonly="readonly" value="${labApparaProvVo.auditName}" name="labApparaProvVo.auditName" size="30">
										</td>
										<td class="w100">
											<s:text name="send.check"/>：
										</td>
										<td colspan="2">
											<input name="labApparaProvVo.checkName" readonly="readonly" value="${labApparaProvVo.checkName}" type="text" id="labApparaProvVo.checkName">
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="fault.occ.rea"/>：
										</td>
										<td colspan="5">
											<textarea readonly="readonly" name="labApparaProvVo.verInfo" rows="5" cols="100">${labApparaProvVo.verInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="fault.occ.deal"/>：
										</td>
										<td colspan="5">
											<textarea readonly="readonly" name="labApparaProvVo.verCon" rows="5" cols="100">${labApparaProvVo.verCon}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="sudited.sound"/>：
											</label>
										</td>
										<td>
											<textarea rows="3" cols="40" readonly="readonly" name="labApparaProvVo.auditMessage">${labApparaProvVo.auditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td align="left">
											<input readonly="readonly" name="labApparaProvVo.user2" value="${labApparaProvVo.user2}" id="user2" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input readonly="readonly" name="labApparaProvVo.date2" value="${labApparaProvVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="flow.audit"/></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="yes.no"/><s:text name="flow.pass"/>：
											</label>
										</td>
										<td>
											<input type="radio" name="labApparaProvVo.auditResult" value="1" checked="checked" />
											<s:text name="lab.yes"/>
											<input type="radio" name="labApparaProvVo.auditResult" value="-1" />
											<s:text name="lab.no"/>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="apply.opinion"/>：
											</label>
										</td>
										<td>
											<textarea rows="3" cols="40" id="pauditMessage" name="labApparaProvVo.pauditMessage"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaProvVo.user3" value="${labApparaProvVo.user3}" readonly="true" id="user3" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaProvVo.date3" value="${labApparaProvVo.date3}" readonly="true" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
