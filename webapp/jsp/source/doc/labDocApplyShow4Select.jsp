<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<%@ include file="../../include/common.jsp"%>
		<script type="text/javascript" language="javascript">
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象

		function closeMe(){
		  	api.close();
	 	}
		 	
		function submitValue(actioner)	{
				var df = document.labDocForm;	
		 		df.action=actioner;
		  		df.submit();
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
			alert('<s:property value="getText('plseaupplyredoc')"/>');
			return false;
		}else{
			return true;
		}
		alert(m);
	   }
	
		function checkLabDoc(url,name){
			var el = document.getElementsByTagName('input');     
			var len = el.length; 
			var m = 0;    
			var n = 0;
			for(var i=0; i<len; i++)     
			{       
				if((el[i].type=="checkbox") && (el[i].name==name))         
				{         
					if(el[i].checked == true){
					m = m + 1; 
						var param={'ID':el[i].value};
						$.ajax({
						   type: "POST",
						   url: url,
						   data: param,
						   async: false,
						   success: function(data){
							    if(data=='1'){
							    	 if(n>0){
							         return false;
							        }
							        n=n+1;	
							        if(url=='<%=basePath%>doc/labDoc/ajaxCheckPass.action'){
							         alert('<s:property value="getText('docexistseleother')"/>');   
							         }else{
							          alert('<s:property value="getText('codeixsiscancel')"/>'); 
							         }    
							        return false;
								}
						   }
						});	
				 	}      
			}     
		}  
			if(m<1){
			if(url=='<%=basePath%>doc/labDoc/ajaxCheckPass.action'){
				alert('<s:property value="getText('plseselectdoc')"/>');
			}else{
			    alert('<s:property value="getText('plsecanceldoc')"/>');
			}
				return false;
			}else{
			   if(n==0){
			   return true;
			   }
			}
	}	
	
	function applylabDocBatch(){
		if(checkLabDoc('<%=basePath%>doc/labDoc/ajaxCheckPass.action','labDocApplyVo.ids')){
		submitValue('<%=basePath%>doc/labDoc/updateLabDocOtherApplyBatch.action?labDocApplyVo.flag=2')
		
		}
	}
	
	function cancellabDocBatch(){
		if(checkLabDoc('<%=basePath%>doc/labDoc/ajaxCheckRefuse.action','labDocApplyVo.ids')){
		submitValue('<%=basePath%>doc/labDoc/updateLabDocOtherApplyBatch.action?labDocApplyVo.flag=4')
		}
		}
		function getLabDocApplyList(obj){
		submitValue('<%=basePath%>doc/labDoc/showLabDocApply4Select.action?labDocVo.flag='+obj);
		}
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form theme="simple" action="" method="post" name="labDocForm">
			<input type="hidden" name="labDocVo.pid" value="${labDocId }" />
			<input type="hidden" name="labDocVo.id" value="${labDocVo.id}" />
			<table id="bodyTable" class="bodytable" width="99%" cellspacing="0" cellpadding="0" border="0" style="min-height: 200px">
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
									<div class="">
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="theme.office"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.orgList" theme="simple" name="labDocVo.orgId" id="orgId0" listValue="name" listKey="id" headerValue="全部" headerKey="-1"></s:select>
																</td>
																<td>
																	<label>
																		<s:text name="applier"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labDocVo.applyName" id="creatorName" value="${labDocVo.applyName }"/>
																</td>
																<td>
																	<label>
																		<s:text name="file.name"/>
																	</label>
																</td>
																<td>
																	<input type="text" name="labDocVo.fileName" id="fileName" value="${labDocVo.fileName}"/>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="submitValue('showLabDocApply4Select.action'); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b> </a>
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

																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="applylabDocBatch();return false;"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="batchpassd"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="cancellabDocBatch();return false;"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="abtchcancel"/></b> </a>
																</td>
																<td style="float: right;">
																	<s:select list="#{'-1':getText('page.all'),'2':getText('passed'),'1':getText('unupassed')}" headerValue="--请选择--" headerKey="" listKey="key" listValue="value" onchange="getLabDocApplyList(this.value)"></s:select>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>

										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th>
													<input type="checkbox" id="allCheckBox" key="labDocApplyVo.ids" onclick="if(this.checked==true) { checkAll('labDocApplyVo.ids'); } else { clearAll('labDocApplyVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th>
													<s:text name="file.name"/>
												</th>
												<th>
													<s:text name="applier"/>
												</th>
												<th>
													<s:text name="theme.office"/>
												</th>
												<th>
													<s:text name="apply.date"/>
												</th>
												<th>
													<s:text name="filedoc"/>
												</th>
												<th>
													<s:text name="sam.state"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:iterator value="#request.labDocApplyVoList" status="st">
												<tr>
													<td class="c">
														<input type="checkbox" name="labDocApplyVo.ids" id="ids${st.index+1}" value="${id}" key="${name}" />
													</td>
													<td class="c">
														${pageSizex* currenPagex+st.index+1}
													</td>
													<td class="l">
														${labDocVo.fileName }
													</td>
													<td class="l">
														${applyName }
													</td>
													<td class="l">
														${labDocVo.orgName }
													</td>
													<td class="c">
														${applyTime }
													</td>
													<td class="l">
														${labDocVo.remark }
													</td>
													<td class="c">
														<s:if test="${status==4}"><s:text name="noprivi"/></s:if>
														<s:elseif test="${status==1}"><s:text name="flow.check"/>中</s:elseif>
														<s:elseif test="${status==2}"><s:text name="fuquaned"/></s:elseif>
														<s:elseif test="${status==3}"><s:text name="denied"/></s:elseif>
													</td>
													<td class="c">
														<s:if test="${status==1}">
															<a href="<%=basePath%>doc/labDoc/updateLabDocApplyById.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=2">		<s:text name="flow.pass"/>
															<a href="<%=basePath%>doc/labDoc/updateLabDocApplyById.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=3"><s:text name="jujuesd"/></a>
														</s:if>
														<s:elseif test="${status==2}">
															<a href="<%=basePath%>doc/labDoc/updateLabDocApplyById.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=4"> <s:text name="config.cancel"/>&nbsp;&nbsp;
														</s:elseif>
														<s:elseif test="${status==3||status==4}">
															<a href="<%=basePath%>doc/labDoc/updateLabDocApplyById.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=2"> 		<s:text name="flow.pass"/></a>&nbsp;&nbsp;
														</s:elseif>
													</td>
												</tr>
											</s:iterator>

										</table>
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
		</s:form>
	</body>
</html>
