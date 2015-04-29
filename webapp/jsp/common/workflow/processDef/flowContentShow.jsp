<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/include/common.jsp"%>
<title>SaaS For LIMS - LabSoft</title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<script type="text/javascript" src="<%=basePath%>jsp/sample/register/showSampInfo.js"></script>
<style>

.bodytable .oRight {
	padding-left: 0;
}
.TabTable .TabTableBOX01 {display:none;_height:220px;min-height:220px;width:96.5%;background:#ECFCFF;border:1px solid #499EB3;margin-left:10px;margin-bottom:10px;;padding:10px 10px 0 10px}
.TabTable .TabTableBOX01.b {display:block;_height:220px;min-height:220px;width:96.5%;background:#ECFCFF;border:1px solid #499EB3;margin-left:10px;margin-bottom:10px;;padding:10px 10px 0 10px}

html {
	_overflow-x: hidden;
}
</style>
</head>
<Script>
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	
	function submitforform(){
		document.content.save();
		$('form').submit();
	}
</script>
<body class="" id="mainid">	
		<form method="post" name="form" action="saveOrUpdateProcessDef.action">
		<input type="hidden" name="labWfProcessVo.uuId" value="${labWfProcessVo.uuId}"/>
		<input type="hidden" name="labWfProcessVo.id" value="${labWfProcessVo.id}"/>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
		</tr>
         <tr>
				<td class="MLimg"></td>
        		<td id="bodyCell" class="oRight">
          			<table class="workingBody" cellspacing="0" cellpadding="0" border="0" >
              			<tr>
              				<td>
             			 		<div class="myworkingbox">
					                <div class="myworkingboxttitle">
					                  <h2><s:text name="prc.manage"/>：<span><s:text name="modeling.look"/></span></h2>
					                </div>
					                <div class="FUNCIONBARNEW">
						               <table>
						                   <tr>
						                    	<td class="blockTd" style="padding: 0px 10px;vertical-align:center;">
						                     		<table cellspacing="0" cellpadding="0" border="0">
						                      			<tr>
						                         			<td>                                                  
						                         				<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/fanhui.gif" btFunctionType="back" btFunctionUrl="" btFunctionValue="返回" btIsDeafultImg="false" btJsFunctions="onclick:goToNextAction('workflow/process/listLabWfProcess.action');return false;"/>
						                         			</td>
						                       		</tr>
						                   		</table>
						                		</td>
						                	</tr>
						                </table>
						           </div>	
						           
									<div class="Formtable">
									   <span style="background-color:#ccc;"><font color="red"><s:text name="use.ie8"/></font></span>	
										<div id="contentFrame">
											<iframe name="content" id="content" src="<%=basePath%>workflow/process/showLabWfProcess4Flow.action?labWfProcessVo.id=${labWfProcessVo.id}"  
											  onload="document.all.content.style.height=document.content.document.body.clientHeight" scrolling="no"   frameborder="0" width="100%" height="500" >
											</iframe> 
										</div>
									</div>
								</div>
								
				</td>
			</tr>		
          </table>
        </td>
		<td class="MRimg"></td></tr>
				<tr><td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td></tr>
			</table>
	</form>	
</body>
</html>
