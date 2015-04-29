<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title></title>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript" src="<%=basePath%>utils/common/commonutil.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/menu.js"></script>
	</head>

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

		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
	}
	function deleteMessage(actionstr) {
		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
	}
	function deleteThis(url){
		if(validationCheckbox('labMsgMainVo.ids')){
			if(confirm('<s:property value="getText('msg.confirm.del')"/>'))
			{
				var df = document.messageForm;
				df.action = url;
				df.submit();;
			}	
		}		
	}
</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="messageForm">
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
												<span> <s:text name="msg.draft"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						<td><label><s:text name="msg.subject"></s:text>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labMsgMainVo.subject" value="${labMsgMainVo.subject}" id="subject"   />
					                      						</td>
					                      						<td><label><s:text name="msg.send.date"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labMsgMainVo.startDate"
																		value="${labMsgMainVo.startDate}"
																		 class="Wdate" size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																	~<input type="text" name="labMsgMainVo.endDate"
																		value="${labMsgMainVo.endDate}"
																		 class="Wdate" size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
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
																	<l:a uri="message/messageMain/deleteLabMsg4CaoGao.action" onclick="deleteThis('deleteLabMsg4CaoGao.action');return false;" value="lab.code.del" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick=window.location.reload();returnfalse;><img height="20" width="20" src="<%=basePath%>img/shuaxin.gif" /><b><s:text name="msg.refresh"/></b> </a>
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
													<th class="c" width="40">
														<input type="checkbox" id="allCheckBox" key="labMsgMainVo.ids" />
													</th>
													<th class=" c" width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th class="c">
														<s:text name="msg.subject"/>
													</th>
													<th class=" c">
														<s:text name="msg.receiver"/>
													</th>
													<th class=" c" width="280">
														<s:text name="msg.date"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:set name="currentPage" value="pageResult.pageBean.currentPage" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<input type="checkbox" name="labMsgMainVo.ids" id="ids${pageSizex* currenPagex+st.index+1}" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																	<input type="hidden" name="labMsgMainVo.id" value="${id}">
																</td>

																<td class="l">
																	<a href="<%=basePath%>message/messageMain/preUpdateLabMsg.action?labMsgMainVo.id=${id}"> ${subject} </a>
																</td>
																<td class="l">
																	<s:if test="${fn:length(receiverNames)>15}">
																		${fn:substring(receiverNames,0,15)}..
           															</s:if>
																	<s:else>
					        							  				  	${receiverNames}
					      											</s:else>
																</td>
																<td class="c">
																	<s:if test="${fn:length(createTime)>19}">
																	     ${fn:substring(createTime,0,19) }
																	</s:if>
																	<s:else>
																		${createTime}
																	</s:else>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="msg.nomsg"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>

								</td>
							</tr>
							<tr>
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=messageForm" flush="true" />
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