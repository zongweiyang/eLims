<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<link rel="stylesheet" href="<%=basePath %>/themes/default/default.css" />
		<link rel="stylesheet" href="<%=basePath %>/plugins/code/prettify.css" />
		<script charset="utf-8" src="<%=basePath %>/js/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath %>/lang/zh_CN.js"></script>
		<script charset="utf-8" src="<%=basePath %>/plugins/code/prettify.js"></script>
		<script language="javascript" src="<%=basePath%>/js/print/LodopFuncs.js"></script>
		<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<style>
				* {
					font-size: 9pt;
					line-height: 20px
				}
				
				.title {
					font-size: 16px;
					color: #0000FF;
					font-weight: bold
				}
				
				.con {
					margin-left: 15px;
				}
				
				.STYLE1 {
					color: #FF0000
				}
				
				.STYLE2 {
					color: #0000FF
				}
				
				.STYLE3 {
					color: #009900
				}
				</style>
				
				
				
							<style>
				.TabTable {
					padding-top: 0;
				}
				
				.bodytable .oRight {
					padding-left: 0;
				}
				
				html {
					_overflow-x: hidden;
				}
			.FormNei tr td{
				    padding-left: 10px;
				    padding-top: 10px;
				    text-align: left;
				    border-color: teal;
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
			
			</style>
		<script>
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			
			KindEditor.ready(function(K) {
				
					K.create('#questionName', {
							minHeight: 180,
							minWidth: 600,
							pasteType:1,
							items: ['undo', 'redo',  'cut', 'copy', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', '|','bold','italic', 'underline','image', 'multiimage','advtable','|','source','fullscreen', ],
						    cssPath : '../plugins/code/prettify.css',
					        uploadJson : '<%=basePath %>/servlet/Upload',
					        fileManagerJson : '<%=basePath %>/jsp/file_manager_json.jsp',
							allowFileManager : true,
							autoSetDataMode: true,
							afterChange : function() {$('#questionName').val(this.html());}
	
					});
					K.create('#question', {
							minHeight: 180,
							minWidth: 600,
							pasteType:1,
							items: ['undo', 'redo',  'cut', 'copy', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', '|','bold','italic', 'underline','image', 'multiimage','advtable','|','source','fullscreen', ],
						    cssPath : '../plugins/code/prettify.css',
					        uploadJson : '<%=basePath %>/servlet/Upload',
					        fileManagerJson : '<%=basePath %>/jsp/file_manager_json.jsp',
							allowFileManager : true,
							autoSetDataMode: true,
							afterChange : function() {$('#question').val(this.html());}
	
					});
			});
		</script>
	</head>
	<body>
		<form action="" method="post" name="form">
			<input type="hidden" value="${labChargeVo.id}" name="labMethodVo.id" id="id" />
			<table id="bodyTable" cellspacing="0" cellpadding="0" border="0">
              	<tr>
		           	<td style="height: 300px;">
						<table class="workingBody" cellspacing="0" cellpadding="0"
								border="0">
							<tr>
								<td>
									<div style="height: 300px;">
										<div class="FUNCIONBARNEW">
                                            <table style="padding-top: 10px;padding-left: 260px;">
                                            	<tr>
													<td class="c">
														<a id="BtnPreview" class="zPushBtn" href="javascript:;"
														onclick="printlnTable();return false;"><img
															src="${basePath}/img/filesavenew.gif" /><b><s:text name="print"/></b>
														</a>
													</td>
							                     	<td class="c">
								                      <a id="BtnEdit" class="zPushBtn"
														href="javascript:void(0);"
														onclick="window.close();return false;"><img
															height="20" width="20"
														src="<%=basePath%>img/fanhui.gif" /><b><s:text name="closednow"/></b> </a>
												  	</td>
				                      			</tr>
				                     	 	</table>
                                        </div>		
										<!-- 表单型表格（显示） 开始 -->
										<div id="topFiles2" style="width: 640px;">
											<table width="100%">
												<tr>
													<td colspan="3" class="c">
														<font size="5"><strong><s:text name="billchart"/></strong> </font>
													</td>
												</tr>
												<tr>
													<td class="l" style="padding-left: 30px;">
														<s:text name="expno"/>：${labChargeVo.code }
													</td>
													<td colspan="2" class="r">
														<s:text name="msg.date"/>：${labChargeVo.collectionTime }
													</td>
												</tr>
												<tr>
													<td colspan="3" class="r">
													</td>
												</tr>
											</table>
											<table class="myworkingboxttable12" width="90%" cellpadding="0" cellspacing="0">
												<tr>
													<td align="center"> 
														<s:text name="chargeoffice"/>：
													</td>
													<td align="left">
														${labChargeVo.paymentUnit }
													</td>
													<td align="center">
														<s:text name="chargunit"/>：
													</td>
													<td align="left">
														${labChargeVo.collectionUnit }
													</td>
												</tr>
												<tr>
													<td align="center"> 
														<s:text name="chargeitem"/>：
													</td>
													<td align="left">
														${labChargeVo.chargeType }
													</td>
													<td align="center">
														<s:text name="theme.charge.money"/>：
													</td>
													<td align="left">
														${labChargeVo.payMoney }
													</td>
												</tr>
												<tr>
													<td align="center"> 
														<s:text name="theme.charged.moneyy"/>：
													</td>
													<td align="left">
														${labChargeVo.yiMoney }
													</td>
													<td align="center">
														<s:text name="paytype"/>：
													</td>
													<td align="left">
														${labChargeVo.payType }
													</td>
												</tr>
												<tr>
													<td align="center"> 
														<s:text name="moneycontent"/>：
													</td>
													<td style="height: 80px;" align="left" colspan="3">
														${labChargeVo.payInfo }
													</td>
												</tr>
											</table>
											<table width="100%" >
												<tr>
													<td class="r" style="padding-right: 30px;padding-top: 10px;">
														<s:text name="prj.manager"/>：${labChargeVo.collectionName }
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（显示） 结束 -->
									</div>
							    </td>
							</tr>
						</table>
					</form>
				 <%@ include file="../../include/foot.jsp"%> 
				</body>
		<script>
	var LODOP; //声明为全局变量
	function printlnTable() {		
		CreatePrintPage();
	  	LODOP.PREVIEW();
	};	
	function CreatePrintPage() {
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
		LODOP.PRINT_INITA(10,10,754,300,"打印控件功能演示_Lodop功能_多页文档");
		LODOP.SET_PRINT_PAGESIZE(1,"754","300","A4");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		LODOP.SET_PRINT_PAGESIZE(1,0, 0,"A4");
		LODOP.SET_PREVIEW_WINDOW(1,0,0,0,0,"西安瑞铂软件");	//打印预览模式	
		var strBodyStyle="<style>"
						+".myworkingboxttable12 {width:96%;border:1px solid #000;margin:0 30px;border-collapse: collapse;}"
						+".myworkingboxttable12 th {background:url(../images/hc_indexworktable_bg.gif) repeat-x left top;height:18px;font-weight:normal;font-size:14px;color:#000;}"
						+".myworkingboxttable12 td {border:1px solid #000;height:30px;font-size:16px;padding:5px;vertical-align:middle;color:#000;}"
						+".myworkingboxttable12 table {border-collapse: collapse;border:0;}"
						+".myworkingboxttable12 td sub {font-size:8px;vertical-align:middle;color:#000;}"
                        +".myworkingboxttable12 .bb0 td {border-bottom:0;}"
                        +".myworkingboxttable12 .bb1 td {border-top:0;padding:5px;}"
						+".c {text-align:center;}"
						+".l {text-align:left;}"
						+".r {text-align:right;}"
						+".font-sizeClass{font-size:16px;}"
						+".gsbg_label01{width:85px;}"
						+".gsbg_label02{width:50px;}"
						+".gsbg_label03{width:115px;}"
						+".gsbg_label04{width:75px;}"
						+"</style>";
		var tableTitle2 = document.getElementById("topFiles2").innerHTML;
		LODOP.ADD_PRINT_HTM(40,0,684,1024,strBodyStyle+'<body>'+document.getElementById("topFiles2").innerHTML+'</body>');
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",0);
		LODOP.SET_PRINT_STYLEA(0,"Horient",3);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
		
	};	
</script>
</html>		
