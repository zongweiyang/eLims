<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.labsoft.labos.utils.SysOut"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
String busId=request.getParameter("busId");
request.setAttribute("busId",busId);
%>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}
html {
	_overflow-x: hidden;
}
.Formtable {width:99%;margin:0px;background:#fff;border:1px solid #ccc;}

.Formtabletitle {padding:0;margin:0;background:#99CCCC;}
.Formtabletitle span {background:url(/theme/skin/default/images/lims_point_icon1.png) no-repeat 8px 4px;padding-left:22px;color:#fff;font-size:12px;font-weight:bold;}

.FormtableCon {width:100%;font-size:12px;border-collapse:collapse;}
.FormtableCon th {text-align:center;vertical-align:middle;font-weight:bold;padding:0 5px;height:16px;line-height:16px;background:#E5E5E5}
.FormtableCon td {text-align:left;vertical-align:middle;padding:5px;}
.FormtableCon td.c {text-align:center;}
.FormtableCon td.l {text-align:left;}
.FormtableCon td.r {text-align:right;}
</style>
</head>
<script language=javascript> 
$(function(){
	var busId='${busId}';
	$.ajax({
		type:'POST',
		url:'<%=basePath %>/workflow/process/ajaxLabWfStepLogs2show.action',
		data:{'labWfProcessInsVo.busId':busId},
		type:'post',
		dataType:'text',
		success: function(data){
			if(data.length>2){
				var result=eval('('+data+')');
				for(var i=0;i<result.length;i++){
					$('#logsTable').append($('<tr>')
					.append('<td class="c">'+result[i].stepName+'</td>')
					.append('<td class="c">'+result[i].userName+'</td>')
					.append('<td class="c">'+result[i].auditTime+'</td>')
					.append('<td class="c">'+result[i].useTime+'</td>')
					.append('<td class="c">'+result[i].auditResult+'</td>')
					.append('<td>'+result[i].auditMessage+'</td>'));
				}
			}
		},
		error: function(data){
			alert("error");
		}
	});
});
function iFrameHeight(id){
	var ifm= document.getElementById(id);
	var subWeb = document.frames?document.frames[id].document:ifm.contentDocument;
	if(ifm != null && subWeb != null){
		ifm.height = subWeb.body.scrollHeight;
	}
}   
</script>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<form action="" method="post" name="mouldForm">
							<div class="TabTable">
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="progress.ui"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<iframe name="content" id="content" src="<%=basePath%>/workflow/process/showLabWfProgressIns.action?labWfProcessInsVo.busId=${busId}" scrolling="no" frameborder="0" width="100%" onLoad="iFrameHeight('content')">
										</iframe>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="prg.log"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon" id="logsTable">
											<tr>
												<td width="100" class="c"><s:text name="prg.jie"/></td>
												<td width="100" class="c"><s:text name="ops.people"/></td>
												<td width="100" class="c"><s:text name="ops.date"/></td>
												<td width="100" class="c"><s:text name="consume.time"/></td>
												<td width="100" class="c"><s:text name="deal.result"/></td>
												<td class="c"><s:text name="remark"/></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
