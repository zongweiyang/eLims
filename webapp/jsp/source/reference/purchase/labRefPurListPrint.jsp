<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
<script language="javascript" src="<%=basePath%>utils/Lodop/LodopFuncs.js"></script>
		<title>My JSP 'sampleReportInfo' starting page</title>
		<style>
.baobiaotitle {
	margin: 10px;
	text-align: center;
}

.baobiaotitle h1 {
	color: #000;
	font-weight: bold;
	font-size: 26px;
}

.baobiaotitle h2 {
	color: #000;
	font-weight: bold;
	font-size: 20px;
	margin: 10px 0;
}

.logobox {
	position: relative;
	width: 100%;
	margin: 10px 0;
}

.logobox .logoimg {
	position: absolute;
	left: 10%;
	top: 0;
}

.logobox h3 {
	color: #000;
	font-weight: bold;
	font-size: 30px;
	margin: 20px 0 10px;
	text-align: center;
}

.logobox h2 {
	color: #000;
	font-weight: bold;
	font-size: 20px;
	margin: 20px 0 10px;
	text-align: center;
}

.Formtable_baobiao {
	width: 98%;
	margin: 0 10px;
	background: #fff;
	border: 0;
}

.FormtableCon_baobiao {
	border-collapse: collapse;
	width: 100%;
	margin: 10px 0;
	font-size: 12px;
}

.FormtableCon_baobiao td {
	border: 1px solid #000;
	text-align: left;
	vertical-align: middle;
	padding: 5px;
}

.FormtableCon_baobiao td.c {
	text-align: center;
}

.FormtableCon_baobiao td.l {
	text-align: left;
}

.FormtableCon_baobiao td.r {
	text-align: right;
}

.linebox {
	width: 100%;
	border: 0;
	border-bottom: 1px dashed #000;
	height: 5px;
	line-height: 10px;
	overflow: hidden;
	margin-bottom: 5px;
}

.FormtableCon input {
	border: 1px solid #7F9DB9;
	vertical-align: middle;
	height: 18px;
	line-height: 18px;
	padding-left: 2px;
}

.FormtableCon textarea {
	font-size: 12px;
	padding: 2px;
}

.FormtableCon .td_textarealabel {
	vertical-align: top;
}

.FormtableCon {
	width: 100%;
	margin: 10px 0;
	font-size: 12px;
}

.FormtableCon td {
	text-align: left;
	vertical-align: middle;
	padding: 5px;
}

.FormtableCon td.c {
	text-align: center;
}

.FormtableCon td.l {
	text-align: left;
}

.FormtableCon td.r {
	text-align: right;
}
</style>
		<script language="javascript" type="text/javascript"> 
    var LODOP; //声明为全局变量 
	function myPreview4() {	
		CrefteOnePage();	
		//LODOP.SET_PREVIEW_WINDOW(0,0,0,760,540,"");
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",1);
		//LODOP.SET_PREVIEW_WINDOW(0,1,1,800,600,"自定义标题.开始打印");	
		LODOP.PREVIEW();	
	};	
	function CrefteOnePage(){
		var text1  = getTextInfo("page1");
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INIT('<s:property value="getText('printconrldemo')"/>');
		LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");
		LODOP.ADD_PRINT_HTM(63,38,684,10000,text1);
		LODOP.SET_PRINT_STYLEA(0,"FontSize",30);
	};	
	
	function prn1_setup() {		
		CrefteOneFormPage();
		document.getElementById('S1').value=LODOP.PRINT_SETUP();
		document.getElementById('id01').style.display="none";		
	};
	
	function prn2_design() {		
		CrefteOneFormPage();
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 		
		eval(document.getElementById('S1').value); 
		document.getElementById('S1').value=LODOP.PRINT_DESIGN();
	};
	
	function CrefteOneFormPage(){
		var text  = getTextInfo();
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 
		LODOP.PRINT_INIT('<s:property value="getText('printconfundemogetcode')"/>');
		LODOP.SET_PRINT_STYLE("FontName","黑体");
		LODOP.SET_PRINT_STYLE("FontSize",15);
		LODOP.ADD_PRINT_TEXT(12,46,175,30,"纯文本一");
		LODOP.ADD_PRINT_HTM(63,38,684,10000,text);
	};
	
	
	function getTextInfo(str){
		var cssText = '<style>.baobiaotitle {margin:10px;text-align:center;}'
			+'.baobiaotitle h1 {color:#000;font-weight:bold;font-size:26px;}'
			+'.baobiaotitle h2 {color:#000;font-weight:bold;font-size:20px;margin:10px 0;}'
			+'.logobox {position:relative;width:100%;margin:10px 0;}'
			+'.logobox .logoimg {position:absolute;left:10%;top:0;}'
			+'.logobox h3 {color:#000;font-weight:bold;font-size:30px;margin:20px 0 10px;text-align:center;}'
			+'.logobox h2 {color:#000;font-weight:bold;font-size:20px;margin:20px 0 10px;text-align:center;}'
			+'.Formtable_baobiao {width:98%;margin:0 10px;background:#fff;border:0;}'
			+'.FormtableCon_baobiao {border-collapse:collapse;width:100%;margin:10px 0;font-size:12px;}'
			+'.FormtableCon_baobiao td {border:1px solid #000;text-align:left;vertical-align:middle;padding:5px;}'
			+'.FormtableCon_baobiao td.c {text-align:center;}'
			+'.FormtableCon_baobiao td.l {text-align:left;}'
			+'.FormtableCon_baobiao td.r {text-align:right;}'
			+'.linebox {width:100%;border:0;border-bottom:1px dashed #000;height:5px;line-height:10px;overflow:hidden;margin-bottom:5px;}'
			+'.FormtableCon input {border:1px solid #7F9DB9;vertical-align:middle;height:18px;line-height:18px;padding-left:2px;}'
			+'.FormtableCon textarea {font-size:12px;padding:2px;}'
			+'.FormtableCon .td_textarealabel {vertical-align:top;}'
			+'.FormtableCon {width:100%;margin:10px 0;font-size:12px;}'
			+'.FormtableCon td {text-align:left;vertical-align:middle;padding:5px;}'
			+'.FormtableCon td.c {text-align:center;}'
			+'.FormtableCon td.l {text-align:left;}'
			+'.FormtableCon td.r {text-align:right;}'
			+'</style>';
			var text = document.getElementById(str).innerHTML;
			var strHTML='<body>'+text+'</body>';
			return cssText+strHTML;
	}
	
</script>

	</head>

	<body class="" id="mainid" style="overflow-y: auto;">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0"
						border="0">
						<tr>
							<td>
								<div class="myworkingbox">
									<div class="myworkingboxttitle">
										<h2>
											<s:text name="stgbuybill"/>
										</h2>
									</div>
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd"
													style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#"
																	onclick="myPreview4();return false;"><img
																		height="20" width="20" src="${basePath}img/dayin.gif" /><b><s:text name="print"/></b>
																</a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#"
																	onclick="javascript:history.back();return false;"><img
																		height="20" width="20" src="${basePath}img/fanhui.gif" /><b><s:text name="msg.back"/></b>
																</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<object id="LODOP"
										classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0
										height=0>
										<embed id="LODOP_EM" type="application/x-print-lodop" width=0
											height=0 pluginspage="install_lodop.exe"></embed>
									</object>
									<div id="cHtml" align="center">
										<div id="page1" style="width: 700px;">
											<div class="logobox c">
												<h3>
													<s:text name="stgbuybill"/>
												</h3>
											</div>
											<div class="Formtable_baobiao c">
												<table class="FormtableCon_baobiao c">
													<tr>
														<td class="r" width="15%">
															<s:text name="bill.no"/>：
														</td>
														<td class="c" width="25%">
															${labRefPurMainVo.receiptno}
														</td>
														<td class="r" width="15%">
															<s:text name="applier"/>：
														</td>
														<td class="c" colspan="2" width="25%">
															${labRefPurMainVo.applicant}
														</td>
													</tr>
													<tr>
														<td class="r">
															<s:text name="applytime"/>：
														</td>
														<td class="c" colspan="4">
															${labRefPurMainVo.createTime}
														</td>
													</tr>
													<tr>
														<td class="r">
															<s:text name="lab.remark"/>：
														</td>
														<td class="c" colspan="4">
															${labRefPurMainVo.remark}
														</td>
													</tr>

													<tr>
														<td class="c">
															<s:text name="lab.sequence"/>
														</td>
														<td class="c">
															<s:text name="stdandard.name"/>
														</td>
														<td class="c">
															<s:text name="stdandard.regular"/>
														</td>
														<td class="c">
															<s:text name="buyingnumber"/>
														</td>
														<td class="c">
															<s:text name="remark"/>
														</td>
													</tr>
													<s:iterator value="#request.resultList" status="st"
														id="first">
														<tr>
															<td class="c">
																${st.index+1}
															</td>
															<td>
																${referenceName }
															</td>
															<td>
																${size }
															</td>
															<td>
																${num }
															</td>
															<td>
																${remark }
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
										<div class="linebox"></div>
									</div>
									<div style="display: none">
										<form method="POST" action="--WEBBOT-SELF--">
											<p>
												<textarea rows="14" id="S1" cols="107"></textarea>
												<font size="2">
													<div id='id01' style="display: none">
														<input type="button" value='<s:property value="getText('codeproint')"/>' id="button02"
															onclick="prn2_Preview()">
														<input type="button" value='<s:property value="getText('savecodetolocal')"/>' id="button03"
															onclick="myWriteFile(document.getElementById('S1').value)">
														<input type="button" value='<s:property value="getText('procodeimport')"/>' id="button04"
															onclick="document.getElementById('S1').value=myRefdFile()">
														<input type="button" value='<s:property value="getText('thesecodeexe')"/>'
															id="button05" onclick="prn2_design()">
													</div> </font>
										</form>
									</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
