<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>js/autocomplete/jquery.autocomplete.css" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<script language="javascript" type="text/javascript"> 
function submitvalue(actionstr){
	var df = document.labUserForm;	
	  df.action=actionstr;
	  df.submit();
}
function deleteOneFun(id){
      if(confirm('<s:property value="getText('exist.auth.drop')"/>')){
         $.ajax({
	   	  url:'<%=basePath%>user/labUser/deleteLabUserFun.action',
	   	  data:{'labUserFunVo.userId':'${labUserVo.id}','labUserFunVo.functionId':id},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="true"){
            	alert('<s:property value="getText('delete.fail')"/>');
            	window.location.href=window.location.href;
          	 }else{
               alert();
             }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('config.request.error')"/>');
	   	  }
	   });	
	  }
}
function addRole4fun(funIds,orgId,orgName){
	var ftable=$('#funTable');
	var index=$('#funTable').find('tr').length;
	$('#funTable').find('input[value="'+orgId+'"]').each(function(){
		$(this).parent().parent().remove();
	});
	if(funIds.length>0){
		var funIdStr=funIds.split(",");
		for(var i=0;i<funIdStr.length;i++){
			if(funIdStr[i].length>0){
				var str=funIdStr[i].split("*");
				ftable.append($('<tr>')
				.append('<td class="c">'+str[1]+'<input type="hidden" name="labUserVo.userFunList['+index+'].functionId" id="functionId'+index+'" value="'+str[0]+'"/></td>')
				.append('<td class="c"><font>'+orgName+'</font><input type="hidden" name="labUserVo.userFunList['+index+'].orgId" id="orgId'+index+'" value="'+orgId+'"/></td>')
				.append('<td class="c">'+str[3]+'<input type="hidden" name="labUserVo.userFunList['+index+'].roleId" id="roleId'+index+'" value="'+str[2]+'"/></td>')
				.append('<td class="c"><input type="checkbox" name="labUserVo.userFunList['+index+'].isAdd" checked="checked" value="Y"/>增 '
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isDelete" checked="checked" value="Y"/>删 '
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isUpdate" checked="checked" value="Y"/>改 '
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isShow" checked="checked" value="Y"/>查 </td>')
				.append('<td class="c"><input type="radio" name="labUserVo.userFunList['+index+'].tenantStr" value=""/>系统级'
				                     +'<input type="radio" name="labUserVo.userFunList['+index+'].tenantStr" value="'+orgId+'" checked="checked"/>部门级</td>')
				.append('<td class="c"><a href="javascript:;" onclick="deleteOneFun(this)"><s:text name="lab.code.del"/></a></td>'));
				index++;
			}
		}
	}
}
function addRow4fun(){
	var url  = '${basePath}user/labUser/preTreeLabFunction.action?labUserVo.id=${labUserVo.id}';
	$.dialog({
		id:'userId',
		content:'url:'+url,
		title:'<s:property value="getText('fun.empower.list')"/>',
		opacity:0.4,
		width:600,
		height:400,
		lock:true
	 });
}
function flushPage(){
	window.location.href=window.location.href;
}
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="auth.modify"></s:text>【<font color="red">${labUserVo.name}</font>】</span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labUserForm">
							<s:token></s:token>
							<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
							<div class="FUNCIONBARNEW">
								<table>
									<tbody>
										<tr>
											<td style="padding: 6px 10px; vertical-align: center;"
												class="blockTd">
												<table cellspacing="0" cellpadding="0" border="0">
													<tbody>
														<tr>
															<td>
																<l:a uri="back" value="msg.back" />
															</td>
															<td>
																<l:a uri="user/labUser/updateLabUser4fun.action"
																	value="lab.code.save" />
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="TabTable"
								style="padding-top: 0; margin: 0; background: none;">
								<div class="Formtable">
									<table class="FormtableCon_sform" id="funTable">
										<tr>
											<th>
												<s:text name="function.name"/>
											</th>
											<th>
												<s:text name="own.depart"/>
											</th>
											<th>
												<s:text name="own.role"/>
											</th>
											<th>
												<s:text name="ops.auth"/>
											</th>
											<th>
												<s:text name="data.auth"/>
											</th>
											<th>
												<s:text name="lab.code.ops"/>
											</th>
										</tr>
										<s:iterator value="labUserVo.userFunList" status="st" id="uf">
											<tr>
												<td class="l">
													${functionName}
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].functionId"
														value="${functionId}" />
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].functionName"
														value="${functionName}" />
													<input type="hidden" name="parentFunId"
														value="${parentFunId}" id="${functionId}" />
												</td>
												<td class="l">
													<font>${orgName}</font>
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].orgId"
														id="orgId${st.index}" value="${orgId}" />
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].orgName"
														id="orgName${st.index}" value="${orgName}" />
												</td>
												<td class="l">
													${roleName}
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].roleId"
														value="${roleId}" />
													<input type="hidden"
														name="labUserVo.userFunList[${st.index}].roleName"
														value="${roleName}" />
												</td>
												<s:if test="${funType=='0'}">
													<td class="c">——</td>
													<td class="c">——</td>
												</s:if>
												<s:else>
													<td class="l">
														<input type="checkbox"
															name="labUserVo.userFunList[${st.index}].isAdd"
															<s:if test="${isAdd=='Y'}">checked="checked"</s:if>
															value="Y" />
														<s:text name="add"/>
														<input type="checkbox"
															name="labUserVo.userFunList[${st.index}].isDelete"
															<s:if test="${isDelete=='Y'}">checked="checked"</s:if>
															value="Y" />
														<s:text name="drop"/>
														<input type="checkbox"
															name="labUserVo.userFunList[${st.index}].isUpdate"
															<s:if test="${isUpdate=='Y'}">checked="checked"</s:if>
															value="Y" />
														<s:text name="modify"/>
														<input type="checkbox"
															name="labUserVo.userFunList[${st.index}].isShow"
															<s:if test="${isShow=='Y'}">checked="checked"</s:if>
															value="Y" />
														<s:text name="search"/>
													</td>
													<td class="c">
														<s:if test="${dataStr=='org'}">
															<s:select list="#uf.orgDataList" listKey="tenantId" listValue="name" name="labUserVo.userFunList[${st.index}].tenantStr" theme="simple"></s:select>
														</s:if>
														<s:elseif test="${dataStr=='user'}">
															<s:text name="theme.user"/>
															<input type="hidden" name="labUserVo.userFunList[${st.index}].tenantStr" value="${tenantStr}"/>
														</s:elseif>
														<s:else>
															${dataStr}
															<input type="hidden" name="labUserVo.userFunList[${st.index}].tenantStr" value="${tenantStr}"/>
														</s:else>
													</td>
												</s:else>
												<td class="c">
													<a href="javascript:;"
														onclick="deleteOneFun('${functionId}')"><s:text name="lab.code.del"/></a>
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
							</div>
						</form>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
