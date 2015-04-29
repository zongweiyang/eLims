<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
	    <%@ include file="../../include/common.jsp"%>
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
		<form action="" method="post" name="labUserForm">
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
												<span><s:text name="top.index"></s:text></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
									   	<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						<td><label><s:text name="msg.depart"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labUserVo.orgName" value="${labUserVo.orgName}" id="orgName"   />
					                      						</td>
															   	<td><label><s:text name="theme.user.name"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labUserVo.name"  value="${labUserVo.name}" id="username"   />
					                      						</td>
					                      						<td><label><s:text name="theme.role"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labUserVo.roleName" value="${labUserVo.roleName}" id="roleName"   />
					                      						</td>
										                      	<td>
										                      		<l:a uri="${pageResult.action}"  onclick="submitAction();" value="fun.query"/>
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
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="user/labUser/preAddLabUser.action" value="lab.code.add"/>
																</td>
																<td>
																	<l:a uri="user/labUser/deleteLabUser.action" onclick="deleteThis();" value="lab.code.deleteall" />	
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
													<th width="40">
														<input type="checkbox" id="allCheckBox"
															key="labUserVo.ids" />
													</th>
													<th width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th width="150"><s:text name="msg.depart"/></th>
													<th property="name" width="70"><s:text name="msg.username"/></th>
													<th property="loginName" width="80"><s:text name="login.nickname"/></th>
													<th  width="70"><s:text name="theme.duty"/></th>
													<th ><s:text name="user.role"/></th>
													<th property="isUsed"  width="70"><s:text name="using.ornot"/></th>
													<th width="150"><s:text name="config.ops"/></th>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<input type="checkbox" name="labUserVo.ids"
																		id="ids<s:property value="#st.index+1" />"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	${orgName}
																</td>
																<td class="l">
																	<a href="<%=basePath%>user/labUser/showLabUser.action?labUserVo.id=${id}">${name}</a>
																</td>
																<td class="l">
																	${loginName}
																</td>
																<td class="c">
																	${duty}
																</td>
																<td class="l">
																	${roleName}
																</td>
																<td class="c">
																	<s:if test="${isUsed=='Y'}"><s:text name="lab.yes"/></s:if>
																	<s:else><s:text name="lab.no"/></s:else>
																</td>
																<td class="c">
																	<l:a href="#" uri="user/labUser/preUpdateLabUser.action?labUserVo.id=${id}" value="lab.code.modify" />
																	&nbsp;&nbsp;
																	<s:if test="${loginName=='suadmin'}">
																		<l:a href="#" uri="user/labUser/deleteLabUser.action" onclick="deleteOne('${id}');return false;" disabled="true" value="lab.code.del" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="user/labUser/deleteLabUser.action" onclick="deleteOne('${id}');return false;" value="lab.code.del" />
																	</s:else>
																	&nbsp;&nbsp;
																	<l:a href="#" uri="user/labUser/preUpdateLabUser4fun.action?labUserVo.id=${id}" value="rights" />
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
									<jsp:include page="/jsp/include/page.jsp?formName=labUserForm" flush="true" />
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
