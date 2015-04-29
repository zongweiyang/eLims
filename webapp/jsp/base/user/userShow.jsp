<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/autocomplete/jquery.autocomplete.css" />
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
	var t=checkLoginname();
	var df = document.labUserForm;	
	   if(t!="1"){	  	 
		  df.action=actionstr;
		  df.submit();
		 }
}
 var eq;
 function checkLoginname()
	{
	  var loginname=document.getElementById("loginname").value;	 
	  $.ajax({
	   	  url:'<%=basePath%>user/labUser/isRequiredName.action',
	   	  data:{'name':loginname},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="true"){
             alert('<s:property value="getText('exist.loginname')"/>');
              eq="1";
           	  }else{
                eq="0";
              }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('config.request.error')"/>');
	   	  }
	   });	 
    	return eq;
	} 
	function addRow4Org(){
		var table=$('#orgTable');
		var index=table.find('tr').length;
		index=parseInt(table.find('tr').eq(index-1).attr('key'))+1;
		var optionStr="";
		<s:iterator value="#request.orgList" status="st">
			var flag=true;
			var thisId='${id}';
			$('select[id*="orgId"]').each(function(){
				var selVal=$(this).val();
				if(selVal==thisId){
					flag=false;
					return false;
				}
			});
			if(flag){
				optionStr +='<option value="${id}">${name}</option>'; 
			}
		</s:iterator>
		
		table.append($('<tr key="'+index+'">')
			 .append('<td><s:text name="msg.depart"/>：</td>')
			 .append('<td><select name="labUserVo.userOrgList['+index+'].orgId" id="orgId'+index+'" >'+optionStr+'</select></td>')
			 .append('<td>角色：</td>')
			 .append('<td><input readonly="readonly" size="50" type="text" name="labUserVo.userOrgList['+index+'].roleNames" id="roleName'+index+'" value="" />'
						+'<input type="hidden" name="labUserVo.userOrgList['+index+'].roleIds" id="roleId'+index+'" value=""/>'
						+'   <a href="javascript:;" onclick="ajax2RoleList('+index+')" name="xuanze"><s:text name="selected"/></a>'
						+'  <a href="javascript:;" onclick="deleteOneOrg(this)"><font color="red">删除</font></a></td>'));
	}
	function deleteOneOrg(obj){
		$(obj).parent().parent().remove();
	}
	function ajax2RoleList(index){
		var url  = '${basePath}user/labUser/showLabRole4Select.action?labRoleVo.index='+index;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.role.list')"/>',
			opacity:0.4,
			width:800,
			height:400,
			lock:true
		 });
	}
	function alertMsg(){
		alert('<s:property value="getText('depart.change')"/>');
	}
	function uploadFile(busId,busType){
	   fileTypes = '*.jpg;*.gif;*.png;';
	   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&urls=/user/labUser/preUpdateLabUser.action';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('logo.upload')"/>',
			opacity:0.4,
			width:300, 
			height:80,
			lock:true,
			max:false,
			resize:false
		 });
	}//多附件labUpLoads.jsp height:300,
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="details.info"/></span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labUserForm" id="form">
							<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
							<div class="FUNCIONBARNEW">
								<table>
									<tbody>
										<tr>
											<td style="padding: 6px 10px; vertical-align: center;" class="blockTd">
												<table cellspacing="0" cellpadding="0" border="0">
													<tbody>
														<tr>
															<td>
																<l:a uri="back" value="msg.back" />
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="TabTable" style="padding-top: 0; margin: 0; background: none;">
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="user.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<td>
													<label>
														<s:text name="msg.username"/>：
													</label>
												</td>
												<td>
													${labUserVo.name}
												</td>
												<td>
													<label>
														<s:text name="login.nickname"/>：
													</label>
												</td>
												<td>
													${labUserVo.loginName}
												</td>
												<td rowspan="5" align="center" width="170">
													<s:if test="${fn:length(labUserVo.logo)>0}">
														<img src="<%=basePath%>${labUserVo.logo}" style="width: 170px; height: 200px" />
													</s:if>
													<s:else>
														<img src="<%=basePath%>/img/user_logo.jpg" style="width: 170px; height: 200px" />
													</s:else>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.birthday"/>：
													</label>
												</td>
												<td>
													${labUserVo.birthday}
												</td>
												<td>
													<label>
														<s:text name="theme.sex"/>：
													</label>
												</td>
												<td>
													${labUserVo.sex}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="using"/>：
													</label>
												</td>
												<td>
													<s:if test="${labUserVo.isUsed=='Y'}"><s:text name="lab.yes"/></s:if>

													<s:if test="${labUserVo.isUsed=='N'}"><s:text name="lab.no"/></s:if>

												</td>
												<td>
													<label>
														<s:text name="login.pass"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.pwd" value="${labUserVo.pwd }" type="password" size="20" disabled="disabled" style="border: 0px" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.duty"/>：
													</label>
												</td>
												<td colspan="3">
													${labUserVo.duty }
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="remark"/>：
													</label>
												</td>
												<td colspan="4">
													${labUserVo.remark}
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name=depart.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon" id="orgTable">
											<s:if test="${fn:length(labUserVo.userOrgList)>0}">
												<s:iterator value="labUserVo.userOrgList" status="st">
													<tr key="${st.index}">
														<td width="160">
															<s:text name="msg.depart"/>：
														</td>
														<td width="170">
															${orgName }
														</td>
														<td width="170">
															<s:text name="theme.role"/>：
														</td>
														<td width="500">
															${roleNames}
														</td>
													</tr>
												</s:iterator>
											</s:if>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</td>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
