<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page
	import="cn.labsoft.labos.source.consumables.vo.LabConsumablesVo"%>
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
	StringBuffer name=new StringBuffer();
	StringBuffer guige=new StringBuffer();
	StringBuffer saveOrgName=new StringBuffer();
	StringBuffer address=new StringBuffer();
	java.util.List<LabConsumablesVo> oldNoList=(java.util.List<LabConsumablesVo>)request.getAttribute("labConsumablesVoList");
	int size=oldNoList.size();
	int index=0;
	for(LabConsumablesVo vo:oldNoList){
		name.append(vo.getName()+",");
		guige.append(vo.getSize()+"$");
		saveOrgName.append(vo.getSaveOrgName()+"|");
		address.append(vo.getId()+"*");
	}
	name=new StringBuffer(name.substring(0,name.length()-1));
	guige=new StringBuffer(guige.substring(0,guige.length()-1));
	saveOrgName=new StringBuffer(saveOrgName.substring(0,saveOrgName.length()-1));
	address=new StringBuffer(address.substring(0,address.length()-1));
%>
var name='<%=name.toString()%>';
var guige='<%=guige.toString()%>';
var saveOrgName='<%=saveOrgName.toString()%>';
var address='<%=address.toString()%>';
var size = parseInt('<%=size%>');

		function submitValue(actionstr)
    {
		document.conConsumablestypeForm.action = actionstr;
		document.conConsumablestypeForm.submit();  
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
		<form action="" method="post" name="conConsumablestypeForm">
			<input type="hidden" name="labConsumablesVo.consumablesTypeId"
				value="${labConsumablesVo.consumablesTypeId }" />
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
												<s:if test="labConsumablesVoList!=null">
													<s:set name="alllist" value="labConsumablesVoList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td colspan="2">
																<span style="margin-left: 25px; margin-top: 25px;">
																	<s:text name="config.name"/>：${name} </span>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<span style="margin-left: 25px; margin-top: 25px">
																	<s:text name="regular.no"/>：${size} </span>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<span style="margin-left: 25px; margin-top: 25px">
																	<s:text name="save.office"/>：${saveOrgName } </span>
															</td>
														</tr>

														<tr>
															<td>
																<img
																	src="<%=basePath%>gensvg?type=code39&msg=${id }&height=2cm&hrp=bottom&fmt=png&res=200&gray=true&hrsize=5mm"
																	style="margin-top: 6px; margin-bottom: 6px; height: 80px; width: 280px;" />
															</td>
														</tr>
													</s:iterator>
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
		CcontePrintPage();
	  	LODOP.PREVIEW();	
	};	
	
	function CcontePrintPage() {
		LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM')); 
		
		LODOP.SET_PRINT_PAGESIZE(1,"80mm","60mm","CconteCustomPage"); 
		LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		var header="";
		var i = 0;
		for(i=0;i<size;i++){
			var html = '<div style="border:1px solid;height:173px;width:290px;"><table cellspacing="0" cellpadding="0" style="margin:0;padding:0;"><tr><td align="center" style="height:20px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;width:100px;valign-align:middle;">耗材名称：</td><td style="height:20px;font-size:12px;width:230px;line-height:8px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;">'+name.split(",")[i]+'</td></tr>';
				html +='<tr><td align="center" style="height:30px;line-height:10px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;">规格：</td><td  style="height:30px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;">'+guige.split("$")[i]+'</td></tr>';
				html +='<tr><td align="center" style="height:30px;line-height:10px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;">保管科室：</td><td style="height:30px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;">'+saveOrgName.split("|")[i]+'</td></tr>';
				html+='<tr><td align="center" style="height:30px;line-height:10px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;"></td><td style="height:47px;line-height:8px;font-size:12px;font-weight:bold;font-family:仿宋_GB2312;vertical-align:middle;border-bottom:1px dashed #ccc;"><img width="200px" height="70px" src="<%=basePath%>gensvg?type=code128&msg='+address.split("*")[i]+'&height=2cm&hrp=bottom&fmt=jpeg&res=300&gray=true&hrsize=3mm" /></td></tr>';
				html+='</table></div>';
			LODOP.ADD_PRINT_HTM("10px","-3px","500px","180px",html);
			LODOP.NEWPAGE();
		}
	};	

</script>