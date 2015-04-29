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
            alert("请选择检定日期!");
			return false;
        }
        else {
            return true;
        }
    }
    function showApp(){
	  var url='<%=basePath%>/appara/drop/showApp4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'仪器选择：',
			maxBtn:false,
			rang: true,
			drag: true,
			resize: false,
			bgcolor:'#000',
			opacity:0.4,
			width:600, 
			iconTitle:false,
			btnBar:false,
			height:400,
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
			<input type="hidden" name="labApparaTestVo.id" value="${labApparaTestVo.id }" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<div class="myworkingbox">
							<div class="myworkingboxttitle">
								<h2>
									${funName }：
									<span><s:text name="lab.code.modify"/></span>
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
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApparaTest.action?labApparaTestVo.auditResult=0');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApparaTest.action?labApparaTestVo.auditResult=1');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
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
											<input name="labApparaTestVo.appName" value="${labApparaTestVo.appName}" type="text" onclick="showApp()" readonly="true"  valType="required" msg="请选择仪器" id="appName" size="30">
											<input type="hidden" value="${labApparaTestVo.appId}" name="labApparaTestVo.appId" id="appId">
										</td>
										<td class="w100">
											<s:text name="chhdate"/>：
										</td>
										<td colspan="2">
											<input id="testDate" name="labApparaTestVo.testDate" value="${labApparaTestVo.testDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'testDate\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
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
											<s:text name="cker"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaTestVo.reposPerson}" readonly="true"  name="labApparaTestVo.reposPerson" size="30">
										</td>
										<td class="w100">
											<s:text name="send.check"/>：
										</td>
										<td colspan="2">
											<input name="labApparaTestVo.ext01" value="${labApparaTestVo.ext01}" readonly="true"  type="text" id="labApparaTestVo.ext01">
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
