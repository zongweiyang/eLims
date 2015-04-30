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
            	alert('<s:property value="getText('delete.success')"/>');
            	window.location.href=window.location.href;
          	 }else{
               alert('<s:property value="getText('delete.fail')"/>');
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
				.append('<td class="c"><input type="checkbox" name="labUserVo.userFunList['+index+'].isAdd" checked="checked" value="Y"/> '+'<s:property value="getText('add')"/>'+
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isDelete" checked="checked" value="Y"/>'+'<s:property value="getText('drop')"/>'+
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isUpdate" checked="checked" value="Y"/>'+'<s:property value="getText('modify')"/>'+
				+'<input type="checkbox" name="labUserVo.userFunList['+index+'].isShow" checked="checked" value="Y"/>'+'<s:property value="getText('search')"/>'+ '</td>')
				.append('<td class="c"><input type="radio" name="labUserVo.userFunList['+index+'].tenantStr" value=""/>'+'<s:property value="getText('sys.level')"/>'+ '</td>')
				                     +'<input type="radio" name="labUserVo.userFunList['+index+'].tenantStr" value="'+orgId+'" checked="checked"/>'+'<s:property value="getText('add')"/>'+'</td>')
				.append('<td class="c"><a href="javascript:;" onclick="deleteOneFun(this)">'+'<s:property value="getText('lab.code.del')"/>'+'</a></td>'));
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
function checkDataStr(index,oldVal){
	var vv=$('input[name="funlist['+index+'].dataStr"]:checked').val();
	//组织级别的select
	var orgHtml='<select id="orgStr'+index+'" name="funlist['+index+'].valStr">'
	<s:iterator value="#request.orgLeveList" status="st" id="orgVal">
		var thisId='${orgVal}';
		if(thisId==oldVal){
			orgHtml +='<option value="${orgVal}" selected="selected">${orgVal}</option>'; 
		}else{
			orgHtml +='<option value="${orgVal}">${orgVal}</option>'; 
		}
	</s:iterator>
	orgHtml+='</select>';
	//角色的select
	var roleHtml='<select id="roleStr'+index+'" name="funlist['+index+'].valStr">'
	<s:iterator value="#request.roleList" status="st">
		var thisId='${id}';
		if(thisId==oldVal){
			roleHtml +='<option value="${id}" selected="selected">${name}</option>'; 
		}else{
			roleHtml +='<option value="${id}">${name}</option>'; 
		}
	</s:iterator>
	roleHtml+='</select>';
	
	if(vv=='role'){
		$('#valStr'+index).html(roleHtml);
	}else if(vv=='org'){
		$('#valStr'+index).html(orgHtml);
	}else{
		$('#valStr'+index).html('');
	}
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
								<span><s:text name="rights.set"/></span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labUserForm">
							<s:token></s:token>
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
																<l:a uri="function/labFunction/updateLabFunction4Data.action" img="/img/add.gif" value="theme.save" />
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
											<th width="40">
												<img src="<%=basePath%>img/icon_drag.gif" />
											</th>
											<th>
												<s:text name="function.name"></s:text>
											</th>
											<th>
												<s:text name="data.auth"></s:text>
											</th>
											<th width="40">
												<img src="<%=basePath%>img/icon_drag.gif" />
											</th>
											<th>
												<s:text name="function.name"></s:text>
											</th>
											<th>
												<s:text name="data.auth"></s:text>
											</th>
											<th width="40">
												<img src="<%=basePath%>img/icon_drag.gif" />
											</th>
											<th>
												<s:text name="function.name"></s:text>
											</th>
											<th>
												<s:text name="data.auth"></s:text>
											</th>
										</tr>
										<tr>
											<s:iterator value="funlist" status="st">
												<td class="c">
													${st.index+1}
												</td>
												<td>
													${name}
													<input type="hidden" name="funlist[${st.index}].id" value="${id}" />
												</td>
												<td class="l">
													<s:radio list="#{'org':getText('msg.depart'),'user':getText('theme.user')}" name="funlist[${st.index}].dataStr" theme="simple" onclick="checkDataStr('${st.index}','${valStr}');"></s:radio>
													<span id="valStr${st.index}">
														<s:if test="${dataStr=='role'}">
															<s:select id="roleStr${st.index}" list="#request.roleList" listKey="id" listValue="name" name="funlist[${st.index}].valStr" theme="simple"></s:select>
														</s:if>
														<s:elseif test="${dataStr=='org'}">
															<s:select id="orgStr${st.index}" list="#request.orgLeveList" name="funlist[${st.index}].valStr" theme="simple"></s:select>
														</s:elseif>
													</span>
												</td>
												<s:if test="${(st.index+1)%3==0}">
													</tr><tr>
												</s:if>
											</s:iterator>
										</tr>
									</table>
								</div>
							</div>
						</form>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
