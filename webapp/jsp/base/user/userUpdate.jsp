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
	   	  url:'<%=basePath%>user/labUser/isExistLoginName.action',
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
	   	  	alert('<s:property value="getText('theme.request.error')"/>');
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
			 .append('<td>组织：</td>')
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
		var type=$('input[name="labUserVo.userType"]:checked').val();
		var url  = '${basePath}user/labUser/showLabRole4Select.action?labRoleVo.index='+index+'&labRoleVo.show='+type;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.role.list')"/>',
			opacity:0.4,
			width:800,
			max: false,                  
	        min: false, 
			height:400,
			lock:true,
			resize:false
		 });
	}
	function alertMsg(){
		alert('<s:property value="getText('orgchangeallright')"/>');
	}
	function uploadFile(busId,busType){
	   fileTypes = '*.jpg;*.gif;*.png;';
	   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&showType=show&saveType=data';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('image.upload')"/>',
			opacity:0.4,
			width:300, 
			height:80,
			lock:true,
			max: false,                  
	        min: false,
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
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labUserForm" id="form">
							<s:token></s:token>
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
															<td>
																<l:a uri="user/labUser/updateLabUser.action" img="/img/add.gif" value="lab.code.save" />
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
													<input name="labUserVo.name" readonly="readonly" value="${labUserVo.name}" onmouseover="this.title='长度不能超过8位'" valType="required,strLength" max="32" msg="用户名未能为空" strLength-msg="用户名长度不能超过32位" value="${labUserVo.name}" id="username" type="text" size="20" maxlength="8" />
												</td>
												<td>
													<label>
														<s:text name="login.nickname"/>	：
													</label>
												</td>
												<td>
													<input name="labUserVo.loginName" value="${labUserVo.loginName}" onmouseover="this.title='长度不能超过12位'" valType="required,strLength" max="32" msg="登录名未能为空" strLength-msg="长度不能超过32位" id="loginname" type="text" size="20" maxlength="12" style="background-color: #e9e9e9;" />
												</td>
												<td rowspan="5" align="center" width="170">
													<div id="upfiles"  style="width: 170px; height: 200px">
													<s:if test="${fn:length(labUserVo.logo)>0}">
														<img src="<%=basePath%>${labUserVo.logo}" style="width: 170px; height: 200px" />
													</s:if>
													<s:else>
														<img src="<%=basePath%>/img/user_logo.jpg" style="width: 170px; height: 200px" />
													</s:else>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.birthday"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.birthday" value="${labUserVo.birthday}" id="birthday" type="text" size="20" class="Wdate" onfocus="WdatePicker()" />
												</td>
												<td>
													<label>
														<s:text name="theme.sex"/>：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.sex" value='<s:property value="getText('theme.male')"/>' <s:if test="${labUserVo.sex=='男'}">checked="checked" </s:if> />
													<s:text name="theme.male"/>
													<input type="radio" name="labUserVo.sex" value='<s:property value="getText('theme.female')"/>' <s:if test="${labUserVo.sex=='女'}">checked="checked" </s:if> />
													<s:text name="theme.female"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="using"/>：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.isUsed" value="Y" <s:if test="${labUserVo.isUsed=='Y'}">checked="checked"</s:if> />
													<s:text name="lab.yes"/>
													<input type="radio" name="labUserVo.isUsed" value="N" <s:if test="${labUserVo.isUsed=='N'}">checked="checked"</s:if> />
													<s:text name="lab.no"/>
												</td>
												<td>
													<label>
														<s:text name="login.pass"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.pwd" value="" valType="strLength" min="6" max="32" strLength-msg="密码未能为空且长度在6-32之间" id="password" type="password" size="20" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.duty"/>：
													</label>
												</td>
												<td>
													<s:select list="#request.listLabCode" cssStyle="width:153px" name="labUserVo.duty" theme="simple" listValue="name" listKey="name"></s:select>
												</td>
												<td>
													<label>
														<s:text name="config.type"/>：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.userType" <s:if test="${labUserVo.userType == 'FRONT'}"> checked="checked"</s:if> value="FRONT" id="typeFRONT" />
													<s:text name="front.user"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="remark"/>：
													</label>
												</td>
												<td colspan="4">
													<textarea name="labUserVo.remark" valType="strLength" max="512" strLength-msg="备注长度不能超过512位" cols="50" rows="2" label="备注">${labUserVo.remark}</textarea>
												</td>
											</tr>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td class="c">
													<a href="javascript:;" onclick="uploadFile('${labUserVo.id}','userLogo');"><img src="<%=basePath %>/img/lefttorightselect/arrow_up.gif" /><s:text name="local.view"/></a>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="org.info"/></span>
										<a href="javascript:;" onclick="addRow4Org()" style="color: red;">  + <s:text name="add.org"/></a>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon" id="orgTable">
											<s:if test="${fn:length(labUserVo.userOrgList)>0}">
												<s:iterator value="labUserVo.userOrgList" status="st">
													<tr key="${st.index}">
														<td>
															<s:text name="orgnized"/>：
														</td>
														<td>
															<s:select list="#request.orgList" theme="simple" name="labUserVo.userOrgList[${st.index}].orgId" id="orgId${st.index}" listValue="name" listKey="id" onchange="alertMsg();"></s:select>
														</td>
														<td>
															<s:text name="theme.role"/>：
														</td>
														<td>
															<input readonly="readonly" size="50" type="text" name="labUserVo.userOrgList[${st.index}].roleNames" valType="required" msg='<s:property value="getText('rolenotemp')"/>' id="roleName${st.index}" value="${roleNames}" />
															<input type="hidden" name="labUserVo.userOrgList[${st.index}].roleIds" id="roleId${st.index}" value="${roleIds}" />
															 
															<a href="javascript:;" onclick="ajax2RoleList('${st.index}')" name="xuanze"><s:text name="selected"></s:text></a>
															<s:if test="${st.index!=0}">
																<a href="javascript:;" onclick="deleteOneOrg(this)"><font color="red"><s:text name="lab.code.del"></s:text></font> </a>
															</s:if>
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr key="0">
													<td>
														<s:text name="orgnized"/>：
													</td>
													<td>
														<s:select list="#request.orgList" theme="simple" name="labUserVo.userOrgList[0].orgId" id="orgId0" listValue="name" listKey="id"></s:select>
													</td>
													<td>
														<s:text name="theme.role"/>：
													</td>
													<td>
														<input readonly="readonly" size="50" type="text" name="labUserVo.userOrgList[0].roleNames" valType="required" msg='<s:property value="getText('rolenotemp')"/>' id="roleName0" value="" />
														<input type="hidden" name="labUserVo.userOrgList[0].roleIds" id="roleId0" value="" />
														<a href="javascript:;" onclick="ajax2RoleList('0')" name="xuanze"><s:text name="selected"/></a>
													</td>
												</tr>
											</s:else>
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