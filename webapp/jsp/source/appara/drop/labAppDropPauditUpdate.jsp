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
        var flage = $("input[name=labApparaDropVo.auditResult]:checked").val();
	    if("-1"==flage){
	    	if(''==$("#view3").val()){
		    	alert("请填写审批意见");
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
												<span><s:text name="flow.audit"/></span>
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
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('upDateLabApparaDrop.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="droped.list"/></span>
											</div>
											<table id="tableId" class="FormtableCon">
												<tr>
													<td align="right">
														<label>
															<s:text name="app.style.name"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input name="labApparaDropVo.appId" id="appId" value="${labApparaDropVo.appId}" type="hidden" size="80" />
														<input name="labApparaDropVo.id" id="id" type="hidden" value="${labApparaDropVo.id}" size="80" />
														<input name="labApparaDropVo.appName" value="${labApparaDropVo.appName}" id="appName" type="text" size="80" readonly="readonly" />
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
														<input name="labApparaDropVo.appNo" readonly="readonly" value="${labApparaDropVo.appNo}" id="appNo" type="text" size="80" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="droped.reason"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea readonly="readonly" cols="70" rows="6" name="labApparaDropVo.view1" id="view1" size="80">${labApparaDropVo.view1}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="reporter.man"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.user1" readonly="readonly" value="${labApparaDropVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.date1" value="${labApparaDropVo.date1}" readonly="readonly" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="sudited.sound"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaDropVo.view2" id="view2" size="80" readonly="readonly">${labApparaDropVo.view2}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.user2" readonly="readonly" value="${labApparaDropVo.user2}" id="user2" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.date2" readonly="readonly" value="${labApparaDropVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
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
											<input type="radio" name="labApparaDropVo.auditResult" value="1" checked="checked" />
											<s:text name="lab.yes"/>
											<input type="radio" name="labApparaDropVo.auditResult" value="-1" />
											<s:text name="lab.no"/>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="apply.opinion"/>：
											</label>
										</td>
										<td align="left" colspan="3">
											<textarea cols="70" rows="6" name="labApparaDropVo.view3" id="view3" size="80">${labApparaDropVo.view3}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											<label>
												<s:text name="apply.peo"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaDropVo.user3" value="${labApparaDropVo.user3}" id="user3" type="text" size="20" />
										</td>
										<td align="right">
											<label>
												<s:text name="msg.date"/>：
											</label>
										</td>
										<td align="left">
											<input name="labApparaDropVo.date3" value="${labApparaDropVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
										</td>
									</tr>
								</table>
							</div>
						</table>
					</td>
				</tr>
			</table>
			<form>
	</body>
</html>
