<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/jsp/include/common.jsp"%>
<title>SaaS For LIMS - LabSoft</title>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<link  href="<%=basePath%>css/global.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>css/common.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/menu.js"></script>
</head>

<%
String username = request.getParameter("username")==null?"":request.getParameter("username");
String type = request.getParameter("type")==null?"1":request.getParameter("type");
%>
<style>

.bodytable .oRight {
	padding-left: 0;
}
.buttonbar {
height:21px;
padding:0 10px;
}
html {
	_overflow-x: hidden;
}
</style>
<script type="text/javascript">
	  function submitvalue(actionstr){
			var df = document.forms[0];
			var path = '${basePath}'
			df.action= path + actionstr;
			df.submit(); 	
	  }
	  
	  function doUrl(url){
	  	var runtimeAIid = $("#runtimeAIid").val();
	    var verificationAIid = $("#verificationAIid").val();
	    var aIid = $("#aIid").val();
	  	if(runtimeAIid==""){
	  		$("#runtimeAIid").val(aIid);
	  	}else{
	  		$("#aIid").val(runtimeAIid);
	  	}
	  	if(verificationAIid==""){
	  		$("#verificationAIid").val(aIid);
	  	}else{
	  		$("#aIid").val(verificationAIid);
	  	}
	  	var df = document.forms[0];
		df.action=url;
		df.submit(); 	
	  }
	   function exportExcel(){
	   		var data=document.getElementById('year').style.display;
	   		if(data!='none'){
	   			var value=$('#yearValue').val();
	   			if(value.length>0){
	   				window.location.href='<%=basePath%>apparatus/maintenance/exportApparatusUseing.action?apparatusUseingVo.infoId=${apparatusInfoVo.id}&apparatusUseingVo.year='+value;
	   			}else{
	   				$('#year').toggle();
	   			}
	   		}else{
	   			$('#year').toggle();
	   		}
   		}
</script>
</head>
	<body class="" id="mainid" style="overflow-y:auto;">
    <table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
      <tr>
        <td id="bodyCell" class="oRight">
          <table class="workingBody" cellspacing="0" cellpadding="0" border="0" >
              <tr><td>     
              <div class="myworkingbox">
                <div class="myworkingboxttitle">
                  <h2>
						${funName }：
					<span><s:text name="app.bill.list"/></span>
				  </h2>
                </div>			
                <div class="TabTable">
                  <div class="TabTableNAV">
                    <ul>
                      <li id="li01" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/listitmes/showApparatusInfo.action');"><span><s:text name="base.info"/></span></a></li>
					  <li id="li11" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/accept/getApparatusAccept.action');"><span><s:text name="checking.info"/></span></a></li>
					  <li id="li02" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/listitmes/getApparatusVerificationRecordPageList.action');"><span><s:text name="accid.recd"/></span></a></li>
					  <li id="li010" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/listitmes/getApparatusRunCheckRecordPageList.action');"><span><s:text name="wording.check"/></span></a></li>
					  <li id="li03" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/listitmes/getApparatusRuntimeRecordPageList.action');"><span><s:text name="between.check"/></span></a></li>
					    <li id="li12" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/test/record/getApparatusTestRecordPageListx.action');"><span><s:text name="check.recod"/></span></a></li>
					   <li id="li04" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/maintenance/getApparatusEditRecordPageListX.action');"><span><s:text name="save.repair"/></span></a></li>
					  <li id="li05" class="currenttab"><a href="#" onclick="doUrl('<%=basePath%>apparatus/listitmes/showApparatusListItemsUsesRecord.action');"><span><s:text name="use.record"/></span></a></li>
					   <li id="li06" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/open/getApparatusOpenList.action');"><span><s:text name="start.record"/></span></a></li>
					  <li id="li07" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/stop/getApparatusStopList.action');"><span><s:text name="stop.record"/></span></a></li>
					  <li id="li08" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/borrow/getApparatusBorrowList.action');"><span><s:text name="borrow.record"/></span></a></li>
					  <li id="li09" class=""><a href="#" onclick="doUrl('<%=basePath%>apparatus/drop/getApparatusDropList.action');"><span><s:text name="drop.record"/></span></a></li>
                    </ul>
                  </div>
               <div class="TabTableBOX01 b" id="Tab01">
               		<form action="" method="post">
               		<input type="hidden" id="" name="apparatusEditRecordVo.apparatusInfoId" value="${apparatusInfoVo.id}" />
					<input type="hidden" id="runtimeAIid" name="labApparaCheckVo.appId" value="${apparatusInfoVo.id}" />
					<input type="hidden" id="verificationAIid" name="apparatusVerificationRecordVo.apparatusInfoId" value="${apparatusInfoVo.id}" />
					<input type="hidden" id="aIid" name="apparatusInfoVo.id" value="${apparatusInfoVo.id }" />
					<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr><td class="TLimg"></td><td class="TMimg"></td><td class="TRimg"></td></tr>
			              <tr>
							<td class="MLimg"></td>
					           <td id="bodyCell" class="oRight">				    
									<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
										
										<tr>
											<td>
												
								                   <div class="tabtablebox">
														<table class="myworkingboxttable" cellspacing="0"
														cellpadding="0" id="thisTable">
														<thead>
															<tr>
																<th class="c" rowspan="2">
																	<s:text name="msg.date"/>
																</th>
																<th class="c" colspan="2">
																	<s:text name="env.state"/>
																</th>

																<th class="c" colspan="2">
																	<s:text name="useing.time"/>
																</th>

																<th class="c" colspan="3">
																	<s:text name="working.state"/>
																</th>
																<th class="c" rowspan="2">
																	<s:text name="user.man"/>
																</th>

																<th class="c" rowspan="2">
																	<s:text name="remark"/>
																</th>

															</tr>
															<tr>
																<th class="c">
																	<s:text name="tempture"/>（℃）
																</th>
																<th class="c">
																	<s:text name="shidu"/>（%）
																</th>
																<th class="c">
																	<s:text name="start"/>
																</th>
																<th class="c">
																	<s:text name="end"/>
																</th>
																<th class="c">
																	<s:text name="before.using"/>
																</th>
																<th class="c">
																	<s:text name="using"/>
																</th>
																<th class="c">
																	<s:text name="after.using"/>
																</th>
															</tr>
														</thead>
														<tbody>
															<s:if test="pageResult!=null">
																<s:if test="pageResult.resultList!=null">
																	<s:set name="alllist" value="pageResult.resultList" />
																	<s:iterator value="#alllist" status="st" id="first">
																		<td class="c" width="70">
																			<!--<s:if test="${fn:length(starttime)>=10}">
																				${fn:substring(starttime,0,10)}
																			</s:if>-->
																			${year}-${month}-${day}
																			
																		</td>
																		<td class="r" width="50">
																			${wenDu}
																		</td>
																		<td class="r" width="50">
																			${shiDu}
																		</td>
																		<td class="c">
																			${startDate}
																		</td>
																		<td class="c">
																			${endDate}
																		</td>
																		<td class="l">
																			${before}
																		</td>
																		<td class="l">
																			${now}
																		</td>
																		<td class="l">
																			${after}
																		</td>
																		<td class="c">
																			${useUser}
																		</td>
																		<td class="l">
																			${remark}
																		</td>
																		</tr>
																	</s:iterator>
																</s:if>
															</s:if>
															
														</tbody>
													</table>
													</div>
													
												
											</td>
										</tr>
										<tr>
						<td align="center"><jsp:include page="/jsp/include/page.jsp?actionparam=showApparatusListItemsUsesRecord.action&formName=customeractivitiesForm" flush="true" /></td>
					</tr>
									</table>
							 </div>
							 <td class="MRimg"></td>
							</tr>
							
							<tr>
							<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
			                </tr>
						</table>
					<form>
               </div>                  
               <div class="TabTableBOX02" id="Tab02"></div>
			   <div class="TabTableBOX03" id="Tab03"></div>
               <div class="TabTableBOX04" id="Tab04"></div>
               <div class="FUNCIONBARNEW">
	             <table>
	                   <tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
	                      <td><a id="BtnPreview" class="zPushBtn" href="#" onclick="window.location.href='<%=basePath %>apparatus/listitmes/getApparatusListItemsPageList.action'"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif"/><b><s:text name="msg.back"/></b></a></td>
	                      <td><a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="exportExcel();"><img height="20" width="20" src="<%=basePath%>img/xinjian.gif"/><b><s:text name="export.app.using.record"/></b></a></td>
	                      <td id="year" style="display: none;">
								<input id="yearValue" size="4" name="apparatusUseingVo.year" value="${apparatusUseingVo.year}"  class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy',alwaysUseStartDate:true});" readonly="readonly"/> 
						  </td>
	                  </tr></table></td></tr>
	             </table>
             	</div>
            </div>  
            </div>  
			</td>
			 </tr>
          </table>
        </td>
      </tr>
    </table>
</body>
</html>
