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
        var flage = $("input[name=labApparaAcceptVo.auditResult]:checked").val();
	    if("-1"==flage){
	    	if(''==$("#pauditMessage").val()){
		    	alert("请填写审批意见");
	    		return false;
	    	}
	    }
        df.action = actionstr;
	  	df.submit();
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
												<span><s:text name="app.checking"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW" width="100%" align="left">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabAppAcceptPaudit.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="save.commit"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
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
														<input name="labApparaAcceptVo.appId" value="${labApparaAcceptVo.appId}" id="appId" type="hidden" />
														<input name="labApparaAcceptVo.appName" value="${labApparaAcceptVo.appName}" id="appName" type="text" size="30" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
													<td align="right">
														<label>
															<s:text name="regular.sys"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.spec" value="${labApparaAcceptVo.spec}" id="specification" type="text" size="30" />
														<!-- <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>  -->
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="app.no"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.appNo" value="${labApparaAcceptVo.appNo}" id="price" type="text" size="30" />
													</td>
													<td align="right">
														<label>
															<s:text name="price.number"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.price" value="${labApparaAcceptVo.price}" id="price" type="text" size="30" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="making.com"/>：
														</label>
													</td>
													<td align="left" colspan="4">
														<input name="labApparaAcceptVo.manufact" value="${labApparaAcceptVo.manufact}" id="manufacturer" type="text" size="30" />
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
																	<input type="text" value="${labApparaAcceptVo.exp1}" name="labApparaAcceptVo.exp1" />
																</td>
																<td align="right">
																	<s:text name="help.book"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp2}" name="labApparaAcceptVo.exp2" />
																</td>
															</tr>
															<tr>
																<td align="right">
																	<s:text name="box.bill"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp3}" name="labApparaAcceptVo.exp3" />
																</td>
																<td align="right">
																	<s:text name="hegezheng"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp4}" name="labApparaAcceptVo.exp4" />
																</td>
															</tr>
															<tr>
																<td align="right">
																	<s:text name="checking.cert"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp5}" name="labApparaAcceptVo.exp5" />
																</td>
																<td align="right">
																	<s:text name="other.doc"/>：
																</td>
																<td align="left">
																	<input type="text" value="${labApparaAcceptVo.exp6}" name="labApparaAcceptVo.exp6" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.exp7" id="exp7" size="80">${labApparaAcceptVo.exp7}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="checking.man"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.user1" value="${labApparaAcceptVo.user1}" id="user1" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.date1" value="${labApparaAcceptVo.date1}" id="date1" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="perform.check"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.view2" id="view2" size="80">${labApparaAcceptVo.view2}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="checking.man"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.user2" value="${labApparaAcceptVo.user2}" id="user2" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.date2" value="${labApparaAcceptVo.date2}" id="date2" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="move.exchange"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.view3" id="view3" size="80">${labApparaAcceptVo.view3}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="prj.manager"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.user3" value="${labApparaAcceptVo.user3}" id="user3" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.date3" value="${labApparaAcceptVo.date3}" id="date3" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.remark" id="remark" size="80">${labApparaAcceptVo.remark}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.auditUser" value="${labApparaAcceptVo.auditUser}" id="auditUser" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.auditDate" value="${labApparaAcceptVo.auditDate}" id="auditDate" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="audit.opn"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.auditMessage" id="auditMessage" size="80">${labApparaAcceptVo.auditMessage}</textarea>
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
															<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="flow.pass"/>：
														</label>
													</td>
													<td>
														<input type="radio" name="labApparaAcceptVo.auditResult" value="1" checked="checked" />
														<s:text name="lab.yes"/>
														<input type="radio" name="labApparaAcceptVo.auditResult" value="-1" />
														<s:text name="lab.no"/>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="audit.opn"/>：
														</label>
													</td>
													<td align="left" colspan="3">
														<textarea cols="70" rows="6" name="labApparaAcceptVo.pauditMessage" id="pauditMessage" size="80">${labApparaAcceptVo.pauditMessage}</textarea>
													</td>
												</tr>
												<tr>
													<td align="right">
														<label>
															<s:text name="auditor"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.pauditUser" value="${labApparaAcceptVo.pauditUser}" id="pauditUser" type="text" size="20" />
													</td>
													<td align="right">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td align="left">
														<input name="labApparaAcceptVo.pauditDate" value="${labApparaAcceptVo.pauditDate}" id="pauditDate" type="text" size="20" class="Wdate" onfocus="WdatePicker({})" />
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
