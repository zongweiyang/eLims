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
			min:false,
			max:false,
			opacity:0.4,
			width:700, 
			height:500,
			iconTitle:false,
			btnBar:false,
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
									<span><s:text name="admin.add"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabApparaProv.action?labApparaProvVo.auditResult=0');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabApparaProv.action?labApparaProvVo.auditResult=1');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="app.stock.rec"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td  width="150">
											<s:text name="app.name"/>：
										</td>
										<td colspan="2">
											<input type="text" id="appName" name="labApparaProvVo.labAppName" onclick="showApp();return false;" size="30"  valType="required" msg="请选择仪器" readonly="true">
											<input type="hidden" id="appId" name="labApparaProvVo.labAppId">
											<input type="hidden" id="spec" name="labApparaProvVo.spec">
											<input type="hidden" id="appNo" name="labApparaProvVo.labAppNo">
										</td>
										<td  width="150">
											<s:text name="msg.date"/>：
										</td>
										<td colspan="2">
											<input id="verificationTime" name="labApparaProvVo.verDate" value="${labApparaProvVo.verDate}" size="30" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'verificationTime\')||\'2020-10-01\'}'})" valType="required" msg="请填写故障日期!" />
										</td>
									</tr>
									<tr>
										<td  width="150">
											<s:text name="main.man"/>：
										</td>
										<td colspan="2">
											<input type="text" value="${labApparaProvVo.auditName}" name="labApparaProvVo.auditName" size="30">
										</td>
										<td  width="150">
											<s:text name="send.check"/>：
										</td>
										<td colspan="2">
											<input name="labApparaProvVo.checkName" size="30" value="${labApparaProvVo.checkName}" type="text" id="labApparaProvVo.checkName">
										</td>
									</tr>
									<tr>
										<td width="150">
											<s:text name="fault.occ.rea"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaProvVo.verInfo" rows="5" cols="100">${labApparaProvVo.verInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td  width="150">
											<s:text name="fault.occ.deal"/>：
										</td>
										<td colspan="5">
											<textarea name="labApparaProvVo.verCon" rows="5" cols="100">${labApparaProvVo.verCon}</textarea>
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
