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
        var flage = $("input[name=labApparaStopVo.auditResult]:checked").val();
	    if("-1"==flage){
	    	if(''==$("#view3").val()){
		    	alert('<s:property value="getText('plseinputopinion')"/>');
	    		return false;
	    	}
	    }
        df.action = actionstr;
	  	df.submit();
    }
    function showApp(){
	  var url='<%=basePath%>/appara/drop/showApp4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'<s:property value="getText('sleapptus')"/>',
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
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }:
												<span><s:text name="appstoped"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW" width="100%" align="left">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateAppStopPaudit.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="appstop.bill"/></span>
											</div>
											<table id="tableId" class="FormtableCon">
												<tr>
													<td align="right">
														<label>
															<s:text name="app.style.name"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input name="labApparaStopVo.appId" value="${labApparaStopVo.appId}" id="appId" type="hidden" size="80" />
														<input name="labApparaStopVo.id" value="${labApparaStopVo.id}" id="id" type="hidden" size="80" />
														<input name="labApparaStopVo.appName" readonly="readonly" value="${labApparaStopVo.appName}" id="appName" type="text" size="80" onclick="showApp();return false;" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="app.sys.no"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input name="labApparaStopVo.appNo" readonly="readonly" value="${labApparaStopVo.appNo}" id="appNo" type="text" size="80" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="stoped.rea"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaStopVo.view1" id="view1" size="80" readonly="readonly">${labApparaStopVo.view1}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="reporter.man"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.user1" readonly="readonly" value="${labApparaStopVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.date1" readonly="readonly" value="${labApparaStopVo.date1}" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="sudited.sound"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaStopVo.view2" id="view2" size="80" readonly="true">${labApparaStopVo.view2}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.user2" value="${labApparaStopVo.user2}" readonly="true" id="user2" type="text" size="20" readonly="readonly" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.date2" value="${labApparaStopVo.date2}" readonly="true" id="date2" type="text" size="20" readonly="readonly" class="Wdate" onfocus="WdatePicker({})" />
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
														<input type="radio" name="labApparaStopVo.auditResult" value="1" checked="checked" />
														<s:text name="lab.yes"/>
														<input type="radio" name="labApparaStopVo.auditResult" value="-1" />
														<s:text name="lab.no"/>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="apply.opinion"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaStopVo.view3" id="view3" size="80">${labApparaStopVo.view3}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="apply.peo"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.user3" value="${labApparaStopVo.user3}" readonly="true" id="user3" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaStopVo.date3" value="${labApparaStopVo.date3}" readonly="true" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<form>
	</body>
</html>
