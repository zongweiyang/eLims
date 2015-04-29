<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>

		<%@ include file="../../include/common.jsp"%>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet" type="text/css" id="theme" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
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
	<script language=javascript> 
	var eq;
	function checkRolename(id)
	{	
	  var rolename=document.getElementById("name").value;
	  var oldName='${labRoleVo.name}';
	  if(rolename==oldName){
	  	return eq="0";
	  }	  
	  $.ajax({
	   	  url:'<%=basePath%>role/labRole/isRequiredRoleName.action',
	   	  data:{'name':name},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="1"){
              alert('<s:property value="getText('role.exist.error')"/>');
              eq="1";
            }
            else{
                eq="0";
                }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('theme.request.error')"/>');
	   	  }
	   });
	  return eq;
	} 
function submitvalue(actionstr,id){
	var t=checkRolename(id);
	var df = document.labRoleForm;
   if(t!="1"){	
    df.action=actionstr;
    df.submit();
   }
}
</script>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName }：
								<span><s:text name="lab.code.modify"></s:text></span>
							</h2>
						</div>
						<form action="" method="post" name="labRoleForm">
							<input name="labRoleVo.id" id="id" type="hidden" size="20" value="${labRoleVo.id}" />
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back"/>
														</td>
														<td>
															<l:a uri="role/labRole/updateLabRole.action" onclick="goAction('updateLabRole.action');" img="/img/add.gif" value="lab.code.save"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="role.info"></s:text>：</span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="lab.sequence"></s:text>：
												</label>
											</td>
											<td>
												<input name="labRoleVo.sort" valType="required,strLength" max="11" strLength-msg="序号长度不能超过11位" id="sort" type="text" size="20" value="${labRoleVo.sort}" />
											</td>
											<td>
												<label>
													<s:text name="role.name"/>：
												</label>
											</td>
											<td>
												<input name="labRoleVo.name" valType="required,strLength" max="32" strLength-msg="角色名称长度不能超过32位" msg="角色名称不能为空" id="name" type="text" size="20" value="${labRoleVo.name}" onblur="checkRolename('${labRoleVo.id}')" />
											</td>
											<td>
												<label>
													<s:text name="show"></s:text>：
												</label>
											</td>
											<td>
												<input type="radio" name="labRoleVo.show" value="FRONT" <s:if test="${labRoleVo.show=='FRONT'}">checked="checked"</s:if>/>前端
												<input type="radio" name="labRoleVo.show" value="BACK" <s:if test="${labRoleVo.show=='BACK'}">checked="checked"</s:if>/>后端
											</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="remark"></s:text>：
											</label>
										</td>
										<td colspan="5">
												<textarea name="labRoleVo.remark" valType="strLength" max="256" strLength-msg="备注长度不能超过256位" cols="60" rows="3" label='<s:property value="getText('remark')"/>'>${labRoleVo.remark}</textarea>
										</tr>
										<tr>
										<td>
											<label>
												<s:text name="home.content"></s:text>：
											</label>
										<td colspan="3">
											<s:iterator value="#request.portletList" id="choosetype" status="st"> 
												<input type="checkbox" <s:if test="${fn:indexOf(labRoleVo.portlet,code)}>-1">checked="checked"</s:if> type="checkbox" style="border: 0" name="labRoleVo.portlet" value="${code }" >${name}</input>
											</s:iterator>		
										</td>
									</tr>
									</table>
								</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>

	<script language="javascript" type="text/javascript">
	function required() {
		this.a = new Array("name",'<s:property value="getText('inputrole.name')"/>',   new Function ("varName", " return this[varName];"));
	}
	function maxlength() {
	}
	function email() {
	}
	function DateTimeValidations() {

	}
	function FloatValidations() {
	}
	function IntegerValidations() {
	}
	function DateValidations() {

	}
</script>
</html>
