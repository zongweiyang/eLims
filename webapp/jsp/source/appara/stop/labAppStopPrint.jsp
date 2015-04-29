<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script language="javascript" src="<%=basePath%>/js/print/LodopFuncs.js"></script>
		<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop.exe"></embed>
		</object>
		<title><s:property value="#session.SessionContainer.orgUnit" /></title>
		<style>
#printDiv0 {
	text-align: center;
}

#bodytable {
	text-align: left;
}

#td {
	height: 30xp;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.tabtableboxtable {
	background: #999;
}

.tabtableboxtable td {
	background: #fff;
}

.myworkingboxttable12 {
	width: 98%;
	border: 1px solid #000;
	margin: 0 10px;
	border-collapse: collapse;
}

.myworkingboxttable12 th {
	background: url(../images/hc_indexworktable_bg.gif) repeat-x left top;
	height: 18px;
	font-weight: normal;
	font-size: 16px;
	color: #000;
}

.myworkingboxttable12 td {
	border: 1px solid #000;
	height: 28px;
	font-size: 12px;
	padding: 0 5px;
	vertical-align: middle;
	color: #000;
}

.myworkingboxttable12 table {
	border-collapse: collapse;
}

.myworkingboxttable11 {
	width: 98%;
	border: 0;
	margin: 0 5px;
	border-collapse: collapse;
}

.myworkingboxttable11 th {
	background: url(../images/hc_indexworktable_bg.gif) repeat-x left top;
	height: 18px;
	font-weight: normal;
	font-size: 16px;
	color: #000;
}

.myworkingboxttable11 td {
	height: 18px;
	font-size: 12px;
	padding: 0 5px;
	vertical-align: middle;
	color: #000;
}

.myworkingboxttable11 table {
	border-collapse: collapse;
}

.print .commontitle {
	margin: 0;
	color: #000;
}

.print .commontitle h2 {
	margin-top: 10px;
	font-size: 26px;
	color: #000;
}

.gsbg_label01 {
	width: 85px;
}

.gsbg_label02 {
	width: 50px;
}

"
.gsbg_label03 {
	width: 115px;
}

.gsbg_label04 {
	width: 75px;
}

.myworkingboxttable13 {
	width: 94%;
	border: 0px solid #000;
	margin: 0 10px;
	border-collapse: collapse;
}

.myworkingboxttable13 th {
	background: url(../images/hc_indexworktable_bg.gif) repeat-x left top;
	height: 18px;
	font-weight: bold;
	font-size: 16px;
	color: #000;
}

.myworkingboxttable13 td {
	border: 0px solid #000;
	font-size: 14px;
	padding: 0 5px;
	vertical-align: middle;
}

.myworkingboxttable13 td sub {
	font-size: 8px;
	vertical-align: middle;
	color: #000;
}

.myworkingboxttable13 table {
	border-collapse: collapse;
}

.myworkingboxttable13 .formatLeng {
	width: 50px;
}
</style>
	</head>

	<script> 
		function goToBackAction(actionstr){
			var df = document.sampleregisterform;
			df.action = actionstr;
	  		df.submit();
		}
	</script>
	<body>
		<form id="bbbb" action="" method="post" name="sampleregisterform">
			<input type="hidden" name="funId" value="${funId}" />
			<input type="hidden" name="sampleInfoVo.id" value="${sampleInfoVo.id}" />
			<input type="hidden" name="id" value="${sampleInfoVo.id}" />
		</form>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="">
						<div class="TabTable" style="padding-top: 0;" align="center">
							<div style="width: 786px;">
								<div class="">
									<div class=" " id="Tab01">
										<div class="tabtablebox">
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="printlnTable();return false;"><img src="${basePath}/img/filesavenew.gif" /><b><s:text name="print.preview"/></b> </a>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
											<div id="topFiles2">
											</div>
											<div id="printDiv" style="overflow: hidden;">
												<div id="printDiv0">
													<center>
														<table>
															<tr>
																<td style="font-size: 20px; font-weight: 800;">
																	<s:text name="apps.bill"/>
																</td>
															</tr>
														</table>
														<table id="tableId" class="myworkingboxttable12" cellpadding="0" cellspacing="0">
															<tr>
																<td align="right" width="20%">
																	<label>
																		<s:text name="app.style.name"/>：
																	</label>
																</td>
																<td align="left" colspan="3">
																	${labApparaStopVo.appName}
																</td>
															</tr>
															<tr>
																<td align="right">
																	<label>
																		<s:text name="app.sys.no"/>：
																	</label>
																</td>
																<td align="left" colspan="3">
																	${labApparaStopVo.appNo}
																</td>
															</tr>
															<tr>
																<td align="right" style="height: 100px;">
																	<label>
																		<s:text name="stoped.rea"/>：
																	</label>
																</td>
																<td align="left" colspan="3">
																	${labApparaStopVo.view1}
																</td>
															</tr>
															<tr>
																<td align="right">
																	<label>
																		<s:text name="reporter.man"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.user1}
																</td>
																<td align="right">
																	<label>
																		<s:text name="msg.date"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.date1}
																</td>
															</tr>
															<tr>
																<td align="right" style="height: 100px;">
																	<label>
																		<s:text name="sudited.sound"/>：
																	</label>
																</td>
																<td align="left" colspan="3">
																	${labApparaStopVo.view2}
																</td>
															</tr>
															<tr>
																<td align="right">
																	<label>
																		<s:text name="auditor"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.user2}
																</td>
																<td align="right">
																	<label>
																		<s:text name="msg.date"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.date2}
																</td>
															</tr>
															<tr>
																<td align="right" style="height: 100px;">
																	<label>
																		<s:text name="apply.opinion"/>：
																	</label>
																</td>
																<td align="left" colspan="3">
																	${labApparaStopVo.view3}
																</td>
															</tr>
															<tr>
																<td align="right">
																	<label>
																		<s:text name="apply.peo"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.user3}
																</td>
																<td align="right">
																	<label>
																		<s:text name="msg.date"/>：
																	</label>
																</td>
																<td align="left">
																	${labApparaStopVo.date3}
																</td>
															</tr>
														</table>
													</center>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
<script>
	var LODOP; //声明为全局变量
	function printlnTable() {		
		CreatePrintPage();
	  	LODOP.PREVIEW();
	};	
	function CreatePrintPage() {
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INITA(10,10,754,453,"打印控件功能演示_Lodop功能_多页文档");
		//LODOP.SET_PRINT_PAGESIZE(1,"1980","2850","A4");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		
		var strBodyStyle="<style>"
						+"#printDiv0{text-align: center;}"
						+".myworkingboxttable13 {width:96%;border:0px solid #000;margin:0 10px;border-collapse: collapse;}"
						+".myworkingboxttable13 th {background:url(../images/hc_indexworktable_bg.gif) repeat-x left top;height:18px;font-weight:bold;font-size:16px;color:#000;}"
						+".myworkingboxttable13 td {border:0px solid #000;font-size:14px;padding:0 5px;vertical-align:middle;}"
						+".myworkingboxttable13 td sub {font-size:8px;vertical-align:middle;color:#000;}"
						+".myworkingboxttable13 table {border-collapse: collapse;}"
						+".myworkingboxttable12 {width:96%;border:1px solid #000;margin:0 10px;border-collapse: collapse;}"
						+".myworkingboxttable12 th {background:url(../images/hc_indexworktable_bg.gif) repeat-x left top;height:18px;font-weight:normal;font-size:14px;color:#000;}"
						+".myworkingboxttable12 td {border:1px solid #000;height:28px;font-size:14px;padding:0 5px;vertical-align:middle;color:#000;}"
						+".myworkingboxttable12 table {border-collapse: collapse;}"
						+".myworkingboxttable12 td sub {font-size:8px;vertical-align:middle;color:#000;}"
						+".myworkingboxttable11 {width:98%;border:0;margin:0 5px;border-collapse: collapse;}"
						+".myworkingboxttable11 th {background:url(../images/hc_indexworktable_bg.gif) repeat-x left top;height:18px;font-weight:normal;font-size:14px;color:#000;}"
						+".myworkingboxttable11 td {height:18px;font-size:10px;padding:0 5px;vertical-align:middle;color:#000;}"
						+".myworkingboxttable11 table {border-collapse: collapse;}"
						+".myworkingboxttable13 .formatLeng{width:140px;}"
						+".c {text-align:center;}"
						+".l {text-align:left;}"
						+".r {text-align:right;}"
						+".font-sizeClass{font-size:16px;}"
						+".gsbg_label01{width:85px;}"
						+".gsbg_label02{width:50px;}"
						+".gsbg_label03{width:115px;}"
						+".gsbg_label04{width:75px;}"
						+"</style>";
		var reportTitle='';
		
			LODOP.ADD_PRINT_HTM(60,40,684,1024,strBodyStyle+'<body>'+reportTitle+document.getElementById("printDiv0").innerHTML+'</body>');
			LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",0);
			LODOP.SET_PRINT_STYLEA(0,"Horient",3);
			LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
		
	};	
</script>