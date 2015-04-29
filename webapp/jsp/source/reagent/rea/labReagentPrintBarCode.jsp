<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="UTF-8"%>
<%@ page
	import="cn.labsoft.labos.common.encoder.vo.LabEncoderVo,cn.labsoft.labos.common.encoder.vo.BarCodeContent"%>
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
StringBuffer keystr=new StringBuffer();
StringBuffer valuestr=new StringBuffer();
LabEncoderVo encoderVo=(LabEncoderVo)request.getAttribute("labEncoderVo");
List<BarCodeContent[]> barCodeContentArrayList=	encoderVo.getBarCodeContentArrayList();
int size=barCodeContentArrayList.size();
int length=0;
for(BarCodeContent[] object:barCodeContentArrayList){
	length=object.length;
	for(BarCodeContent o:object){
		keystr.append(o.getKey()+",");
		valuestr.append(o.getValue()+"$");
	}
}
keystr=new StringBuffer(keystr.substring(0,keystr.length()-1));
valuestr=new StringBuffer(valuestr.substring(0,valuestr.length()-1));
%>
var keystr='<%=keystr.toString()%>';
var valuestr='<%=valuestr.toString()%>';
var size=parseInt('<%=size%>');
var lengths=parseInt('<%=length%>');
		function submitValue(actionstr)
    {
		document.reaReagenttypeForm.action = actionstr;
		document.reaReagenttypeForm.submit();  
	}
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
												<span><s:text name="bar.print"/></span>
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
												<s:if test="labEncoderVo.barCodeContentArrayList!=null">
													<%
														for (BarCodeContent[] object : barCodeContentArrayList) {
															for (BarCodeContent o : object) {
																String key = o.getKey();
																String value = o.getValue();
													%>
													<tr>
														<td>
															<%=key%>：<%=value%>
														</td>
													</tr>
													<%
														}
													%>
													<tr id="">
														<td class="ewm">
															<img
																src="<%=basePath%>gensvg?type=code128&msg=402881fa4486e512014486e85fd40001&height=2cm&hrp=bottom&fmt=png&res=200&gray=true&hrsize=4mm"
																style="margin-top: 6px; margin-bottom: 6px; height: 80px; width: 280px;" />
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
		
		LODOP.SET_PRINT_PAGESIZE(1,"100mm","80mm","CreateCustomPage"); 
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		var header="";
		var i = 0;
		for(i=0;i<size;i++){
			var html = '<div style="border:1px solid;height:170px;width:300px;"><table cellspacing="0" cellpadding="0" style="margin:0;padding:0;">';
				for(j=0;j<lengths;j++){
					html +='<tr><td align="center" style="height:20px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;width:100px;valign-align:middle;">'+keystr.split(",")[i*lengths+j]+'：</td><td style="height:20px;font-size:12px;width:240px;line-height:8px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;">'+valuestr.split("$")[i*lengths+j]+'</td></tr>';
				}
				html+='<tr><td align="center" style="height:47px;line-height:10px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;"></td><td style="height:47px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;"><img width="220px" height="80px" src="<%=basePath%>gensvg?type=code128&msg='+valuestr.split("$")[i*lengths]+'&height=3cm&hrp=bottom&fmt=png&res=200&gray=true&hrsize=3mm" /></td></tr>';
				html+='</table></div>';
			LODOP.ADD_PRINT_HTM("30px","30px","100px","218px",html);
			LODOP.NEWPAGE();
		}
	};	

</script>