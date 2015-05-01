<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.2.js"></script>
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
<script type="text/javascript">
	function printlnTable(id){
    	var url='<%=basePath%>appara/drop/printApparaDrop.action?labApparaDropVo.id='+id;
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'<s:property value="getText('appdroplistprint')"/>',
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
																	<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/appara/drop/listLabApparaDrop4View.action?labApparaDropVo.id=${id}&labApparaDropVo.typeId=${labApparaDropVo.typeId}');return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" onclick="printlnTable('${labApparaDropVo.id}');"><img height="20" width="20" src="<%=basePath%>img/dayin.gif" /><b><s:text name="print"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
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
														<input disabled="disabled" value="${labApparaDropVo.appName}" id="appName" type="text" size="80" readonly="readonly" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="app.sys.no"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<input disabled="disabled" readonly="readonly" value="${labApparaDropVo.appNo}" id="appNo" type="text" size="80" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="droped.reason"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea disabled="disabled" cols="70" rows="6" name="labApparaDropVo.view1" id="view1" size="80">${labApparaDropVo.view1}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="reporter.man"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaDropVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaDropVo.date1}" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="sudited.sound"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="view2" size="80">${labApparaDropVo.view2}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.user2" disabled="disabled" value="${labApparaDropVo.user2}" id="user2" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaDropVo.date2" disabled="disabled" value="${labApparaDropVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="apply.opinion"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="view3" size="80">${labApparaDropVo.view3}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="apply.peo"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaDropVo.user3}" id="user3" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaDropVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable" style="margin-top: 0px;">
											<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
												<span><s:text name="biz.progress"/></span>
											</div>
											<div id="contentFrame">
												<iframe name="content" id="content" src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${labApparaDropVo.id}" onload="document.all.content.style.height=document.content.document.body.clientHeight" scrolling="yes" frameborder="0" width="100%" height="450">
												</iframe>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						</div>
					<td class="MRimg"></td>
				</tr>
			</table>
			<form>
	</body>
</html>
