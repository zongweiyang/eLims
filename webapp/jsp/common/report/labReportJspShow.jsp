<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="l" uri="/WEB-INF/tld/labsoft/lab.tld"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<link rel=stylesheet href="<%=basePath%>/js/skins/default.css" media="all" type="text/css" />
		<link rel=stylesheet href="<%=basePath%>skin/${session.SessionContainer.styleName}/css/common.css" type="text/css">
		<link rel=stylesheet href="<%=basePath%>style/global.css" type="text/css">
	
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<script language="javascript" src="<%=basePath%>/js/print/LodopFuncs.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/areport/areport.js"></script>

		<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<style>
.myworkingbox {
	min-height: 550px;
}

.bodytable {
	min-height: 400px
}
</style>

	</head>
	<script language=javascript>
	
	 var LODOP; //声明为全局变量 
		function myPrint() {	
			CreateOnePage();	
			LODOP.PREVIEW();	
		};	
		function CreateOnePage(){
			LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
			LODOP.PRINT_INIT('<s:property value="getText('printconrldemo')"/>');
			LODOP.SET_PRINT_PAGESIZE(1,0,0,"A4");
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD",1);
			
			var cssStr="<style> hr{display:none;}</style>";
			
			var startHtml = window.frames["frameContent"].document.getElementById("startHtml")
			var startDiv = "";
			if(startHtml!=undefined&&startHtml!="undefined"){
				startDiv = startHtml.innerHTML;
			}
			var tableObj =window.frames["frameContent"].document.getElementById("printTable");
			var table = "";
			if(tableObj!=undefined&&tableObj!="undefined"){
				table = tableObj.innerHTML;
			}
			var endObj =window.frames["frameContent"].document.getElementById("endDiv");
			var endDiv = "";
			if(endObj!=undefined&&endObj!="undefined"){
				endDiv = endObj.innerHTML;
			}
			LODOP.ADD_PRINT_HTM(0,0,800,1024,cssStr+'<body>'+startDiv+'</body>');
			LODOP.ADD_PRINT_TABLE(10,0,800,1024,cssStr+'<body>'+table+'</body>');
			LODOP.SET_PRINT_STYLEA(0,"Top2Offset",0);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",0);
			LODOP.SET_PRINT_STYLEA(0,"LinkedItem",-1);
			LODOP.ADD_PRINT_HTM(0,0,800,1024,cssStr+'<body>'+endDiv+'</body>');
			LODOP.SET_PRINT_STYLEA(0,"ItemType",0);
			LODOP.SET_PRINT_STYLEA(0,"LinkedItem",-1);
		};
	//模版编辑保存时调用的方法
	function submitvalue(url){
		changeTitle();
		var htmlContent="";
		$('.are-page-editor').each(function(){
			htmlContent+=$(this).html();
		});
		if(document.all) {
			htmlContent = htmlContent.replace(/\b([A-Za-z_]\w*)\s*=\s*([^\b\s>"']+)/gi,"$1=\"$2\"");
		}
			htmlContent='\<\%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="true" \%\>'
					+'<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">'
					+'<html><body>'
					+htmlContent
					+'</body></html>';
		$('#labReportVo_file').val(htmlContent);
		var df = document.pageForm;
		df.action=url;
		df.submit();	
	}
	function changeTitle(){
		$('#are-content').find('td[class="ElStr"]').each(function(){
			var elStr=$(this).html();
			$(this).attr('title',elStr);
			elStr=elStr.replace('$','');
			$(this).attr('lang',elStr);
		});
	};
	//业务模块调用的方法
	function submitvalue4Bus(url){
		changeTitle4Bus();
		var htmlContent=$('#frameContent').contents().find('html').html();
		if(document.all) {
			htmlContent = htmlContent.replace(/\b([A-Za-z_]\w*)\s*=\s*([^\b\s>"']+)/gi,"$1=\"$2\"");
		}
			htmlContent='\<\%@ page language="java" import="java.util.*" pageEncoding="UTF-8"\%\>'
					+'<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">'
					+'<html>'
					+htmlContent
					+'</html>';
		$('#labReportVo_file').val(htmlContent);
		var df = document.pageForm;
		df.action=url;
		df.submit();	
	}
	function changeTitle4Bus(){
		$('#frameContent').contents().find('td[class="ElStr"]').each(function(){
			var elStr=$(this).attr('lang');
			$(this).removeAttr('title');
			$(this).attr('title','$'+elStr);
			$(this).html('');
		});
	};
	function getValue(obj){
		$('#valueStr').val($(obj).val());
	}
	function nextUrl(url){
		window.location.href="<%=basePath%>"+url;
	}
</script>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<input type="hidden" id="reportId" value="${labReportVo.id}">
		<div id="page-data"  style="display: none;">
			<jsp:include page="${labReportVo.path}"></jsp:include>
		</div>
	</body>
<SCRIPT>
$(function(){
	$('td .ElStr').each(function(){
		var valStr=$(this).attr('lang');
		if(valStr.indexOf('{')==0){
			$(this).html('$'+valStr);
		}
	});
	$('body').areport();
});
</SCRIPT>
</html>