<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title></title>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript" src="<%=basePath%>utils/common/commonutil.js"></script>
	</head>
	<script>
	$(function(){
		$('#left',parent.document).find("iframe").attr('src','<%=basePath%>coreextend/extend/leftframe.action');
	});
	function check(name){
		var el = document.getElementsByTagName('input');     
		var len = el.length; 
		var m = 0;    
		for(var i=0; i<len; i++)     
		{         
			if((el[i].type=="checkbox") && (el[i].name==name))         
			{             
				if(el[i].checked == true){
			    	m = m + 1;
			 	}      
			}     
		}  
		if(m<1){
			alert('<s:property value="getText('msg.del.record')"/>');
			return false;
		}else{
			return true;
		}
	}

	function submitvalue(actionstr) {
		var df = document.labMsgForm;
		df.action = actionstr;
		df.submit();
	}
	function deleteMessage(actionstr) {
		var df = document.labMsgForm;
		df.action = actionstr;
		df.submit();
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
	<style type="text/css">
.bold {
	font-weight: bold;
	font-size: 25px;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labMsgForm">
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
												<s:text name="msg.msg.manage"/>：
												<span><s:text name="msg.sended"></s:text> </span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="msg.subject"></s:text>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labMsgMainVo.subject" value="${labMsgMainVo.subject}" id="subject" />
																</td>
																<td>
																	<label>
																		<s:text name="msg.send.date"></s:text>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labMsgMainVo.startDate" value="${labMsgMainVo.startDate}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																	~
																	<input type="text" name="labMsgMainVo.endDate" value="${labMsgMainVo.endDate}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="message/messageMain/deleteLabMsg4sender" onclick="javascript:if(check('labMsgMainVo.ids')){if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){deleteMessage('deleteLabMsg4sender.action');}return false;}" value="lab.code.del" />
																</td>
																<td>
																	<l:a uri="message/messageMain/delete" onclick="javascript:if(check('labMsgMainVo.ids')){if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){deleteMessage('realDeleteLabMsgSend.action');}return false;}" value="msg.forever.del" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick=window.location.reload();> <img height="20" width="20" src="<%=basePath%>img/shuaxin.gif"><b><s:text name="msg.refresh"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th class="c" width="40px">
														<input type="checkbox" id="allCheckBox" key="labMsgMainVo.ids" />

													</th>
													<th class=" c" width="40px">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th class="c">
														<s:text name="msg.short.sender"/>
													</th>
													<th class="c">
														<s:text name="msg.subject"></s:text>
													</th>
													<th class="c" width="280">
														<s:text name="msg.date"/>
													</th>
												</tr>
											</thead>

											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<input type="checkbox" name="labMsgMainVo.ids" id="ids${pageSizex* currenPagex+st.index+1}" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	<s:if test="${fn:length(receiverNames)>20}">
																		${fn:substring(receiverNames,0,20)}..
           															</s:if>
																	<s:else>
					        							  				  	${receiverNames}
					      											</s:else>
																</td>
																<td class="l">
																	<a onclick="" href="<%=basePath%>message/messageMain/showLabMsgMain.action?labMsgMainVo.id=${id}">${subject} </a>
																</td>
																<td class="c">
																	<s:if test="${fn:length(createTime)>19}">${fn:substring(createTime,0,19)} </s:if>
																	<s:else>
																	${createTime }
																	</s:else>

																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" valign="middle" align="center">
															<font color="red"><s:text name="msg.nomsg"></s:text></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labMsgForm" flush="true" /></td>
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
			<form>
	</body>
</html>