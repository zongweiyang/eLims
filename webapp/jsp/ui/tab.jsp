<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<Script>
	function shadowdiv(id){
		var tem = eval(id); 
		if(tem == 1){
			 document.getElementById('li01').className = "currenttab";
			 document.getElementById('li02').className = "";
			 document.getElementById('li03').className = "";
			 document.getElementById('li04').className = "";
			 document.getElementById('li05').className = "";
		}else if(tem == 2){
			 document.getElementById('li01').className = "";
			 document.getElementById('li02').className = "currenttab";
			 document.getElementById('li03').className = "";
			 document.getElementById('li04').className = "";
			 document.getElementById('li05').className = "";
		}else if(tem == 3){
			 document.getElementById('li01').className = "";
			 document.getElementById('li02').className = "";
			 document.getElementById('li03').className = "currenttab";
			 document.getElementById('li04').className = "";
			  document.getElementById('li05').className = "";
		}else if(tem == 4){
			 document.getElementById('li01').className = "";
			 document.getElementById('li02').className = "";
			 document.getElementById('li03').className = "";
			 document.getElementById('li04').className = "currenttab";
			 document.getElementById('li05').className = "";
		}else if(tem == 5){
			 document.getElementById('li01').className = "";
			 document.getElementById('li02').className = "";
			 document.getElementById('li03').className = "";
			 document.getElementById('li04').className = "";
			 document.getElementById('li05').className = "currenttab";
		}
	}
</script>
	<script> 
function SetCwinHeight(){
  var bobo=document.getElementById("bobo"); //iframe id
  if (document.getElementById){
   if (bobo && !window.opera){
    if (bobo.contentDocument && bobo.contentDocument.body.offsetHeight){
     bobo.height = bobo.contentDocument.body.offsetHeight+20;
    }else if(bobo.Document && bobo.Document.body.scrollHeight){
     bobo.height = bobo.Document.body.scrollHeight+20;
    }
   }
  }
 }
 
</script>

	<body class="" id="mainid">
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
											<s:text name="reportmange"/>：
											<span><s:text name="tjinfo"/></span>
										</h2>
									</div>
									<!-- 模板内容页面 开始 -->
									<!--  标签组件 开始  -->
									<div class="TabTable">
										<!--  页签导航 开始  -->
										<div class="TabTableNAV">
											<ul>
												<li id="li01" class="currenttab">
													<a href="${basePath}/jsp/ui/tabContent.jsp" ; target="userInfoFrame" onclick="shadowdiv('01');"><span><s:text name="shunyibiao"/></span>
													</a>
												</li>
												<li id="li02" class="">
													<a href="${basePath}/statistics/report/getFinanceReport.action" target="userInfoFrame" onclick="shadowdiv('02');"><span><s:text name="yingshouyinffu"/></span>
													</a>
												</li>
												<li id="li03" class="">
													<a href="${basePath}/statistics/report/getCashReport.action" target="userInfoFrame" onclick="shadowdiv('03');"><span><s:text name="xianjinliu"/></span>
													</a>
												</li>
												<li id="li04" class="">
													<a href="${basePath}/statistics/report/getBusinessReport.action" target="userInfoFrame" onclick="shadowdiv('04');"><span><s:text name="zhufuye"/></span>
													</a>
												</li>
												<li id="li05" class="">
													<a href="${basePath}/statistics/report/getProjectReport.action" target="userInfoFrame" onclick="shadowdiv('05');"><span><s:text name="itemmoneymore"/></span>
													</a>
												</li>
											</ul>
										</div>
										<!--  页签导航 结束  -->
										<!--  页签内容 开始  -->
										<div class="TabTableBOX01 b" id="Tab01">
											<iframe width="100%" id="bobo" frameborder="no" allowtransparency="true" style="" scrolling="auto" height="400" border="0″  src=" ${basePath}/jsp/ui/tabContent.jsp" name="userInfoFrame"></iframe>
										</div>
										<!--  页签内容 结束  -->
									</div>
									<!--  标签组件 结束  -->
									<!-- 模板内容页面 结束 -->
								</div>
							</td>
						</tr>
						<!-- 分页 开始 -->
						<tr>
							<td align="center"><jsp:include page="../include/page.jsp?actionparam=listComType.action&formName=commtypeform" flush="true" /></td>
						</tr>
						<!-- 分页 结束 -->
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

	</body>
</html>
