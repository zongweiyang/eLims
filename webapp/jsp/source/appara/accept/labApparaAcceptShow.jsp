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
    function printlnTable(id){
    	var url='<%=basePath%>appara/accept/printApparaAccept.action?labApparaAcceptVo.id='+id;
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'仪器验收单打印',
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
			<input name="labApparaAcceptVo.id" value="${labApparaAcceptVo.id}" type="hidden" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
																	<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/appara/accept/listLabAppAccept4View.action?labApparaAcceptVo.typeId=${labApparaAcceptVo.typeId}&labApparaAcceptVo.appId=${labApparaAcceptVo.appId}');return false;" ><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" onclick="printlnTable('${labApparaAcceptVo.id}');return false;"><img height="20" width="20" src="<%=basePath%>img/dayin.gif" /><b><s:text name="print"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
												<span><s:text name="checking.info"/></span>
											</div>
											<table id="tableId" class="tabtableboxtable" width="80%" cellpadding="1" cellspacing="1">
												<tr>
													<td align="right">
														<label>
															<s:text name="app.sys.name"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.appId}" id="appId" type="hidden" />
														<input disabled="disabled" value="${labApparaAcceptVo.appName}" id="appName" type="text" size="30" />
													</td>
													<td align="right">
														<label>
															<s:text name="regular.sys"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.spec}" id="specification" type="text" size="30" />
														<!-- <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font> -->
													</td> 
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="app.no"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.appNo}" id="price" type="text" size="30" />
													</td>
													<td align="right">
														<label>
															<s:text name="price.number"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.price}" id="price" type="text" size="30" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="making.com"/>：
														</label>
													</td>
													<td align="left" colspan="4">
														<input disabled="disabled" value="${labApparaAcceptVo.manufact}" id="manufacturer" type="text" size="30" />
													</td>
												</tr>
												<tr>
													<td align="right" rowspan="2">
														<label>
															<s:text name="open.box"/>：
														</label>
													</td>
													<td colspan="3">
														<table width="100%">
															<tr>
																<td align="right">
																	<s:text name="random.doc"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp1}" disabled="disabled" />
																</td>
																<td align="right">
																	<s:text name="help.book"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp2}" disabled="disabled" />
																</td>
															</tr>
															<tr>
																<td align="right">
																	<s:text name="box.bill"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp3}" disabled="disabled" />
																</td>
																<td align="right">
																	<s:text name="hegezheng"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp4}" disabled="disabled" />
																</td>
															</tr>
															<tr>
																<td align="right">
																	<s:text name="checking.cert"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp5}" disabled="disabled" />
																</td>
																<td align="right">
																	<s:text name="other.doc"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp6}" disabled="disabled" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="exp7" size="80">${labApparaAcceptVo.exp7}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="checking.man"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.date1}" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="perform.check"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="view2" size="80">${labApparaAcceptVo.view2}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="checking.man"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.user2}" id="user2" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="move.exchange"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="view3" size="80">${labApparaAcceptVo.view3}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="prj.manager"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.user3}" id="user3" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="remark" size="80">${labApparaAcceptVo.remark}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.auditUser}" id="auditUser" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.auditDate}" id="auditDate" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="audit.opn"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="auditMessage" size="80">${labApparaAcceptVo.auditMessage}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="apply.peo"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.pauditUser}" id="auditUser" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input disabled="disabled" value="${labApparaAcceptVo.pauditDate}" id="auditDate" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="audit.opn"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" disabled="disabled" id="auditMessage" size="80">${labApparaAcceptVo.pauditMessage}</textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable" style="margin-top: 0px;">
											<div class="Formtabletitle" onclick="javascript:$(this).next().toggle();" title="点击隐藏/显示">
												<span><s:text name="biz.progress"/></span>
											</div>
											<div id="contentFrame">
												<iframe name="content" id="content" src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${labApparaAcceptVo.id}" onload="document.all.content.style.height=document.content.document.body.clientHeight" scrolling="yes" frameborder="0" width="100%" height="450">
												</iframe>
											</div>
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
