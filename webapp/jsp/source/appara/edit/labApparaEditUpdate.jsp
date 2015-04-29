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
        if (actionstr=='addApparatusEditRecord.action'){
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
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApparaEdit.action?labApparaEditVo.auditResult=0');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApparaEdit.action?labApparaEditVo.auditResult=1');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable" style="margin-top: 0px;">
								<div class="Formtabletitle">
									<span><s:text name="app.protect.rcd"/></span>
								</div>
								<table id="tableId" class="FormtableCon" >
									<tr>
										<td class="w100">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											<input type="text" id="appName" value="${labApparaEditVo.appName}" name="labApparaEditVo.appName" size="30" onclick="showApp()" valType="required" msg="请选择仪器" readonly="true">
											<input type="hidden" id="id" value="${labApparaEditVo.id}" name="labApparaEditVo.id">
											<input type="hidden" id="appId" value="${labApparaEditVo.appId}" name="labApparaEditVo.appId">
											<input type="hidden" id="specification" value="${labApparaEditVo.appSpec}" name="labApparaEditVo.appSpec">
										</td>
										<td class="w100">
											<s:text name="msg.date"/>：
										</td>
										<td colspan="2">
											<input id="verificationTime" name="labApparaEditVo.verDate" value="${labApparaEditVo.verDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'verificationTime\')||\'2020-10-01\'}'})" />
											<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaEditVo.auditName}" name="labApparaEditVo.auditName" size="30">
										</td>
										<td class="w100">
											<s:text name="saved.repair.man"/>：
										</td>
										<td colspan="2">
											<input name="labApparaEditVo.checkName" value="${labApparaEditVo.checkName}" type="text" id="labApparaEditVo.checkName" size="30" >
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="saving.re.method"/>：
										</td>
										<td colspan="5">
											<input id="ext05" name="labApparaEditVo.ext05" value="${labApparaEditVo.ext05 }" type="text" maxlength="64" size="30" />
										</td>
									</tr>
									<tr>
										<td class="w100">
											<s:text name="saving.re.cont"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaEditVo.verInfo" rows="5" cols="100">${labApparaEditVo.verInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="saving.re.rec"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaEditVo.verCon" rows="5" cols="100">${labApparaEditVo.verCon}</textarea>
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
