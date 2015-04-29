<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
	    <%@ include file="../../include/common.jsp"%>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet"
			type="text/css" id="theme" />
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
#roletext{
	width:70px;
	overflow:hidden; 
	white-space:nowrap;
    text-overflow:ellipsis;/* 支持 IE */
}
</style>
		<script language="JavaScript">
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
			alert('<s:property value="getText('deleted.record')"/>');
			return false;
		}else{
			return true;
		}
	}
	function submitvalue(actionstr){	
		var df = document.labUserForm;		
		  df.action=actionstr;
		  df.submit();	
	}
	function deleteOne(id){
	   	if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){	 
	       $('form').attr('action','<%=basePath%>user/labUser/deleteLabUser.action?labUserVo.ids='+id);
      	   $('form').submit();
	    }
	
	}
	function checkDeleteBatchUser(){
	    $('form').attr('action','<%=basePath%>user/labUser/deleteLabUser.action');
		$('form').submit();	
	}	
	function deleteThis(){
		if(check('labUserVo.ids')){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
			{
				checkDeleteBatchUser()
			}	
		}		
	}
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}		
</script>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form theme="simple" action="" method="post" name="labUserForm">
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
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
									   	<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
															   	<td><label><s:text name="nike.name"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labUserVo.name" id="username"  value="${labUserVo.name}" />
					                      						</td>
										                      	<td>
										                      		<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query"/>
										                      	</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                   
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="msg.username"/>
													</th>
													<th>
														<s:text name="theme.duty"/>
													</th>
													<th property="sex">
														<s:text name="theme.sex"/>
													</th>
													<th>
														<s:text name="theme.tel"/>
													</th>
													<th>
														email
													</th>
													<th property="isUsed">
														<s:text name="using.ornot"/>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	<a href="javascript:;" onclick="window.location.href='${basePath}user/labUser/showLabUser4Org.action?labUserVo.id=${id}'">${name}</a>
																</td>
																<td class="c">
																	${duty}
																</td>
																<td class="c">
																	${sex}
																</td>
																<td class="l">
																	${mobile}
																</td>
																<td class="l">
																	${email}
																</td>
																<td class="c">
																	<s:if test="${isUsed=='Y'}"><s:text name="lab.yes"/></s:if>
																	<s:else><s:text name="lab.no"/></s:else>
																</td>
																<td class="c">
																	<l:a href="#" uri="user/labUser/preUpdateLabUser4Org.action?labUserVo.id=${id}" value="lab.code.edit" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>

											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
							<tr>
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=labUserForm"
										flush="true" />
								</td>
							</tr>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
