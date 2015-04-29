<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="UTF-8"%>
<%@ page
	import="cn.labsoft.labos.common.encoder.vo.LabEncoderVo,cn.labsoft.labos.common.encoder.vo.BarCodeContent,cn.labsoft.labos.common.encoder.vo.BarCode"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="javascript"
			src="<%=basePath%>js/print/LodopFuncs.js"></script>
		<object id="LODOP"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0></embed>
		</object>
		<script language="JavaScript" src="<%=basePath%>js/commonutil.js"></script>
		<script>
		var basePath='<%=basePath%>';
		<%
		StringBuffer idstr = new StringBuffer();
		StringBuffer keystr=new StringBuffer();
		StringBuffer valuestr=new StringBuffer();
		LabEncoderVo encoderVo=(LabEncoderVo)request.getAttribute("labEncoderVo");
		List<BarCode> barCodeList = encoderVo.getBarCodeList();
		int size=barCodeList.size();
		int length=0;
		for(BarCode barCode:barCodeList){
			length=barCode.getBccArray().length;
			idstr.append(barCode.getId()+",");
			for(BarCodeContent o:barCode.getBccArray()){
				keystr.append(o.getKey()+",");
				valuestr.append(o.getValue()+"$");
			}
		}
		idstr=new StringBuffer(idstr.substring(0,idstr.length()-1));
		keystr=new StringBuffer(keystr.substring(0,keystr.length()-1));
		valuestr=new StringBuffer(valuestr.substring(0,valuestr.length()-1));
		%>
		var idstr='<%=idstr.toString()%>';
		var keystr='<%=keystr.toString()%>';
		var valuestr='<%=valuestr.toString()%>';
		var size=parseInt('<%=size%>');
		var lengths=parseInt('<%=length%>');
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>

	</head>

	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="reaReagenttypeForm">
			<input type="hidden" name="labReagentVo.reagentTypeId"
				value="${labReagentVo.reagentTypeId }" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="2d.print"/></span>
											</h2>
										</div>
										<div class="buttonbar2">
											<label>

											</label>
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<a id="BtnEdit" class="zPushBtn"
																href="javascript:void('print');"
																onclick="printlnTable();return false;"><img
																	height="20" width="20"
																	src="<%=basePath%>img/xinjian.gif" /><b><s:text name="print.preview"/></b> </a>
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div id="printDiv">
											<table class="myworkingboxttable" cellspacing="1"
												cellpadding="0">
												<s:if test="labEncoderVo.barCodeList!=null">
													<%
													for(BarCode barCode:barCodeList){
														for(BarCodeContent bcc:barCode.getBccArray()){
															String key=bcc.getKey();
															String value=bcc.getValue();
													%>
													<tr>
														<td>
															<%=key %>:<%=value %>
														</td>
													</tr>
													<%
															 }
														%>
													<tr id="">
														<td class="ewm">
															<img
																src="<%=basePath%>uploadDoc/barcode/<%=barCode.getId() %>.png"
																alt="二维码"
																style="margin-top: 6px; margin-bottom: 6px; margin-left: 30px" />
														</td>
													</tr>
													<%
													}
														%>
												</s:if>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
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
		
		LODOP.SET_PRINT_PAGESIZE(1,"90mm","65mm","CreateCustomPage"); 
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		var header="";
		var i = 0;
		var j = 0;
		for(i=0;i<size;i++){
			var html ='<div style="border:1px solid;height:170px;width:290px;"><table cellspacing="0" cellpadding="0" style="margin:0;padding:0;">';
			for(j=0;j<lengths;j++){
				html +='<tr><td align="center" style="height:20px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;width:130px;valign-align:middle;">'+keystr.split(",")[i*lengths+j]+'：</td><td style="height:20px;font-size:12px;width:230px;line-height:8px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;">'+valuestr.split("$")[i*lengths+j]+'</td></tr>';
			}
			html +='<tr><td align="center" style="height:30px;line-height:10px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;"></td><td style="height:47px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;"><img width="70px" src="<%=basePath%>/uploadDoc/barcode/'+idstr.split(",")[i]+'.png" /></td></tr>';
			html +='</table></div>';
			LODOP.ADD_PRINT_HTM("30px","20px","400px","208px",html);
			LODOP.NEWPAGE();
		}
	};	

</script>