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
			max:false,
			min:false,
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
												<span><s:text name="app.borrow"/></span>
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
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabApparaBor.action?labApparaBorVo.auditResult=0');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b></a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabApparaBor.action?labApparaBorVo.auditResult=1');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable" style="margin-top: 0px;">
											<div class="Formtabletitle">
												<span><s:text name="bor.outer.audit"/></span>
											</div>
											<table id="tableId" class="FormtableCon" >
												<tr>
													<td align="right">
														<label>
															<s:text name="app.style.name"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input name="labApparaBorVo.appId" value="${labApparaBorVo.appId}" id="appId" type="hidden" size="80" />
														<input name="labApparaBorVo.appName" value="${labApparaBorVo.appName}" id="appName" type="text" size="80" onclick="showApp();return false;" valType="required" msg="请选择仪器" readonly="true"/>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="app.sys.no"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input name="labApparaBorVo.appNo" value="${labApparaBorVo.appNo}" id="appNo" type="text" size="80"  readonly="true" valType="required" msg="请填写编号!"/>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="borrow.reason"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaBorVo.view1" id="view1" size="80">${labApparaBorVo.view1}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="reporter.man"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaBorVo.user1" value="${labApparaBorVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaBorVo.date1" value="${labApparaBorVo.date1}" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
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
		</form>
	</body>
</html>
