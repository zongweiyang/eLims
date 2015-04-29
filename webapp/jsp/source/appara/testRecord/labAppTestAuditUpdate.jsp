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
		var flage = $("input[name=labApparaTestVo.auditResult]:checked").val();
	    if("-1"==flage){
	    	if(''==$("#auditMessage").val()){
		    	alert("请填写审核意见");
	    		return false;
	    	}
	    }
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
			<input type="hidden" name="labApparaTestVo.id" value="${labApparaTestVo.id }" />
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
									<span><s:text name="flow.check"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApparaTestAudit.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" style="margin-top: 0px;">
								<div class="Formtabletitle">
									<span><s:text name="app.ss.cc.l"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td class="w100">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											${labApparaTestVo.appName}
											<input type="hidden" value="${labApparaTestVo.appName}" name="labApparaTestVo.appName">
											<input type="hidden" value="${labApparaTestVo.appId}" name="labApparaTestVo.appId">
										</td>
										<td class="w100">
											<s:text name="chhdate"/>：
										</td>
										<td colspan="2">
											<input id="testDate" name="labApparaTestVo.testDate" value="${labApparaTestVo.testDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'testDate\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
										</td>
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="ck.method"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaTestVo.testFun" rows="5" cols="100">${labApparaTestVo.testFun}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="ck.res"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaTestVo.testResult" rows="5" cols="100">${labApparaTestVo.testResult}</textarea>
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaTestVo.reposPerson}" name="labApparaTestVo.reposPerson" size="30">
										</td>
										<td class="w100">
											<s:text name="send.check"/>：
										</td>
										<td colspan="2">
											<input name="labApparaTestVo.ext01" value="${labApparaTestVo.ext01}" type="text" id="labApparaTestVo.ext01">
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="flow.check"/></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="yes.no"/><s:text name="flow.pass"/>：
											</label>
										</td>
										<td>
											<input type="radio" name="labApparaTestVo.auditResult" value="1" checked="checked" />
											<s:text name="lab.yes"/>
											<input type="radio" name="labApparaTestVo.auditResult" value="-1" />
											<s:text name="lab.no"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="sudited.sound"/>：
											</label>
										</td>
										<td align="left" colspan="3">
											<textarea cols="70" rows="6" name="labApparaTestVo.auditMessage" id="auditMessage" size="80">${labApparaTestVo.auditMessage}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="auditor"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaTestVo.user2" value="${labApparaTestVo.user2}" id="user2" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaTestVo.date2" value="${labApparaTestVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
						</div>
						</div>
					</td>
					<td class="MRimg"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
