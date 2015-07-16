<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title></title>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript" src="<%=basePath%>js/menu.js"></script>
		<script language="JavaScript" src="<%=basePath%>utils/common/commonutil.js"></script>

	</head>
	<script>
	$(function(){
		$('#left',parent.document).find("iframe").attr('src','<%=basePath%>coreextend/extend/leftframe.action');
	});
	function checkToDelete(){
		
		if(check('labMsgMainVo.ids'))
		{
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
			{
				deleteMessage('deleteLabMsg.action');
			}
			return false;
		}
	}
	function checkTodeleteAll(){
		if(check('labMsgMainVo.ids')){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				deleteMessage('realDeleteLabMsgReceive.action');
			}
			return false;
		}
	}
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
     function checkName(name){
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
			alert('<s:property value="getText('msg.record.mark')"/>');
			return false;
		}else{
			return true;
		}
	}
	function submitvalue(actionstr) {
	if(checkName('labMsgMainVo.ids')){
		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
		$('#left',parent.document).find("iframe").attr('src','<%=basePath%>coreextend/extend/leftframe.action');
		}
	}
	
	function deleteMessage(actionstr) {
		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
		$('#left',parent.document).find("iframe").attr('src','<%=basePath%>coreextend/extend/leftframe.action');
	}
	
	function ajax2RoleList(index){
		var url  = '${basePath}role/labRole/ajax4ListLabRole.action?labRoleVo.index='+index;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.role.list')"/>',
			opacity:0.4,
			width:800,
			height:600,
			lock:true
		 });
	}
	function showLabMsgByFlag(flag){
		window.location.href='<%=basePath%>message/messageMain/listLabMsg4Recive.action?labMsgMainVo.flag='+flag;
	}
	function showLabMsgDetail(actioner){
		window.location.href=actioner;
	}
</script>
	<script type="text/javascript" language="JavaScript">
</script>
	<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.yidu td a {
	font-weight: normal;
	color: #808080;
}
</style>
	<style type="text/css">
.bold a {
	font-weight: bold;
	font-size: 14px;
}
</style>


	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="messageForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												<s:text name="msg.msg.manage"></s:text>：
												<span> <s:text name="msg.receive.finish"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
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
															   	<td><label><s:text name="msg.short.sender"></s:text>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labMsgMainVo.senderName"  value="${labMsgMainVo.senderName}" id="senderName"   />
					                      						</td>
					                      						<td><label><s:text name="msg.send.date"></s:text>：</label></td>
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
					                   <!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<!-- <td>
																	<l:a uri="message/messageMain/update" onclick="submitvalue('updateLabMsgAll.action');" value="msg.trans" />
																</td> -->
																<td>
																	<l:a uri="message/messageMain/update" onclick="submitvalue('showLabMsgAll.action?labMsgMainVo.flag=1');" value="msg.mark.read" />
																</td>
																<td>
																	<l:a uri="message/messageMain/update" onclick="submitvalue('showLabMsgAll.action?labMsgMainVo.flag=0');" value="msg.mark.unread" />
																</td>
																<td>
																	<l:a uri="message/messageMain/delete" onclick="checkToDelete();" value="lab.code.del" />
																</td>
																<td>
																	<l:a uri="message/messageMain/delete" onclick="checkToDeleteALl();" value="msg.forever.del" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick=window.location.reload();returnfalse;><img height="20" width="20" src="<%=basePath%>img/shuaxin.gif" /><b><s:text name="msg.refresh"/></b> </a>
																</td>
																<td>
																	<select onchange="showLabMsgByFlag(this.value);return false">
																		<option value="" <s:if test="${labMsgMainVo.flag==''}">selected="selected"</s:if>>
																			<s:text name="msg.all"></s:text>
																		</option>
																		<option value="0" <s:if test="${labMsgMainVo.flag=='0'}">selected="selected"</s:if>>
																			<s:text name="msg.unread"></s:text>
																		</option>
																		<option value="1" <s:if test="${labMsgMainVo.flag=='1'}">selected="selected"</s:if>>
																			<s:text name="msg.readed"></s:text>
																		</option>
																	</select>
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
													<th width="40" class="c">
														<input type="checkbox" id="allCheckBox" key="labMsgMainVo.ids" />

													</th>
													<th class=" c" width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th class="c" width="100">
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
														<s:set name="currentPage" value="pageResult.pageBean.currentPage" />
														<s:iterator value="#alllist" status="st">
															<s:if test="${'0'== flag}">
																<tr class="bold">
															</s:if>
															<s:else>
																<tr class="yidu">
															</s:else>
															<td class="c">
																<input type="checkbox" name="labMsgMainVo.ids" id="ids${pageSizex* currenPagex+st.index+1}" value="${demo1}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${senderName}
															</td>
															<td class="l">
																<s:if test="${flag=='0'}">
																	<a href="javascript:;" onclick="showLabMsgDetail('<%=basePath%>message/messageMain/updateLabMsg4Readed.action?labMsgDetailVo.id=${demo1}&labMsgDetailVo.mainMsgID=${id}');">${subject}</a>
																</s:if>
																<s:else>
																	<a href="<%=basePath%>message/messageMain/showLabMsg.action?labMsgDetailVo.id=${demo1}&labMsgDetailVo.mainMsgID=${id}"> ${subject} </a>
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=messageForm" flush="true" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>