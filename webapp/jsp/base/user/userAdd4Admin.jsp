<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
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
	   	  data:{'LoginName':loginname},
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
			 .append('<td>组织：</td>')
			 .append('<td><select name="labUserVo.userOrgList['+index+'].orgId" id="orgId'+index+'" >'+optionStr+'</select></td>')
			 .append('<td>角色：</td>')
			 .append('<td><input readonly="readonly" size="50" type="text" name="labUserVo.userOrgList['+index+'].roleNames" id="roleName'+index+'" value="" />'
						+'<input type="hidden" name="labUserVo.userOrgList['+index+'].roleIds" id="roleId'+index+'" value=""/>'
						+'&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="ajax2RoleList('+index+')" name="xuanze"><s:text name="selected"/></a>'
						+'&nbsp;&nbsp;<a href="javascript:;" onclick="deleteOneOrg(this)"><font color="red">删除</font></a></td>'));
	}
	function deleteOneOrg(obj){
		if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
			$(obj).parent().parent().remove();
		}
	}
	function ajax2RoleList(index){
		var type=$('input[name="labUserVo.userType"]:checked').val();
		var url  = '${basePath}user/labUser/showLabRole4Select.action?labRoleVo.index='+index+'&labRoleVo.show='+type;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.role.list')"/>',
			opacity:0.4,
			max: false,                  
	        min: false,   
			width:600,
			height:400,
			lock:true
		 });
	}
	function uploadFile(busId,busType){
	   fileTypes = '*.jpg;*.gif;*.png;';
	   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&showType=show';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('image.upload')"/>',
			opacity:0.4,
			width:300, 
			height:80,
			max: false,                  
	        min: false,             
			lock:true,
			max:false,
			resize:true
		 });
	}//多附件labUpLoads.jsp height:300,
	$(function(){
		if($('input[name="labUserVo.userType"]:checked').val()  == 'FRONT'){
		
			var optionStr="";
			<s:iterator value="#request.orgList" status="st">
				optionStr +='<option value="${id}">${name}</option>';
			</s:iterator>
			
			$('#addRow4Org').prop('disabled',false);
		
			$('#orgTable').append(
				$('<tr>').attr({
					'key':0
				}).append(
					$('<td>').html('<s:property value="getText('orgnized')"/>: ')
				).append(
					$('<td>').append(
						$('<select>').attr({
							'name':'labUserVo.userOrgList[0].orgId',
							'id':'orgId0'
						}).append(optionStr)
					)
				).append(
					$('<td>').html('<s:property value="getText('theme.role')"/>: ')
				).append(
					$('<td>').append(
						$('<input type="text">').attr({
							'readonly':'readonly',
							'valType':'required',
							'msg':'角色未能为空',
							'name':'labUserVo.userOrgList[0].roleNames',
							'id':'roleName0',
							'size':50
						}).val('${labUserVo.userOrgList[0].roleNames}')
					).append(
						$('<input type="hidden">').attr({
							'name':'labUserVo.userOrgList[0].roleIds',
							'id':'roleId0'
						})
					).append(
						'&nbsp;'
					).append(
						'<a href="javascript:;" onclick="ajax2RoleList(\'0\')" name="xuanze"><s:text name="selected"/></a>'
					)
				)
			)
		}else{
			$('#addRow4Org').prop('disabled',true);
			
			var optionStr="";
			<s:iterator value="#request.orgList4Back" status="st">
				optionStr +='<option value="${id}">${name}</option>';
			</s:iterator>
			
			$('#orgTable').append(
				$('<tr>').attr({
					'key':0
				}).append(
					$('<td>').html('<s:property value="getText('orgnized')"/>: ')
				).append(
					$('<td>').append(
						$('<select>').attr({
							'name':'labUserVo.userOrgList[0].orgId',
							'id':'orgId0'
						}).append(optionStr)
					)
				).append(
					$('<td>').html('<s:property value="getText('theme.role')"/>: ')
				).append(
					$('<td>').append(
						$('<input type="text">').attr({
							'readonly':'readonly',
							'valType':'required',
							'msg':'角色未能为空',
							'name':'labUserVo.userOrgList[0].roleNames',
							'id':'roleName0',
							'size':50
						}).val('${labUserVo.userOrgList[0].roleNames}')
					).append(
						$('<input type="hidden">').attr({
							'name':'labUserVo.userOrgList[0].roleIds',
							'id':'roleId0'
						})
					).append(
						'&nbsp;'
					).append(
						'<a href="javascript:;" onclick="ajax2RoleList(\'0\')" name="xuanze"><s:text name="selected"/></a>'
					)
				)
			)
		}
	});
	
	function clickType(obj){
		$('#orgTable').html('');
		
		if($(obj).val()  == 'FRONT' && $(obj).prop('checked')){
			var optionStr="";
			<s:iterator value="#request.orgList" status="st">
				optionStr +='<option value="${id}">${name}</option>'; 
			</s:iterator>
			
			$('#addRow4Org').prop('disabled',false);
			
			$('#orgTable').append(
				$('<tr>').attr({
					'key':0
				}).append(
					$('<td>').html('<s:property value="getText('orgnized')"/>: ')
				).append(
					$('<td>').append(
						$('<select>').attr({
							'name':'labUserVo.userOrgList[0].orgId',
							'id':'orgId0'
						}).append(optionStr)
					)
				).append(
					$('<td>').html('<s:property value="getText('theme.role')"/>: ')
				).append(
					$('<td>').append(
						$('<input type="text">').attr({
							'readonly':'readonly',
							'valType':'required',
							'msg':'角色未能为空',
							'name':'labUserVo.userOrgList[0].roleNames',
							'id':'roleName0',
							'size':50
						}).val('${labUserVo.userOrgList[0].roleNames}')
					).append(
						$('<input type="hidden">').attr({
							'name':'labUserVo.userOrgList[0].roleIds',
							'id':'roleId0'
						})
					).append(
						'&nbsp;'
					).append(
						'<a href="javascript:;" onclick="ajax2RoleList(\'0\')" name="xuanze"><s:text name="selected"/></a>'
					)
				)
			)
		}else if($(obj).val()  == 'BACK' && $(obj).prop('checked')){
			$('#addRow4Org').prop('disabled',true);
		
			var optionStr="";
			<s:iterator value="#request.orgList4Back" status="st">
				optionStr +='<option value="${id}">${name}</option>';
			</s:iterator>
			
			$('#orgTable').append(
				$('<tr>').attr({
					'key':0
				}).append(
					$('<td>').html('<s:property value="getText('orgnized')"/>: ')
				).append(
					$('<td>').append(
						$('<select>').attr({
							'name':'labUserVo.userOrgList[0].orgId',
							'id':'orgId0'
						}).append(optionStr)
					)
				).append(
					$('<td>').html('<s:property value="getText('theme.role')"/>: ')
				).append(
					$('<td>').append(
						$('<input type="text">').attr({
							'readonly':'readonly',
							'valType':'required',
							'msg':'角色未能为空',
							'name':'labUserVo.userOrgList[0].roleNames',
							'id':'roleName0',
							'size':50
						}).val('${labUserVo.userOrgList[0].roleNames}')
					).append(
						$('<input type="hidden">').attr({
							'name':'labUserVo.userOrgList[0].roleIds',
							'id':'roleId0'
						})
					).append(
						'&nbsp;'
					).append(
						'<a href="javascript:;" onclick="ajax2RoleList(\'0\')" name="xuanze"><s:text name="selected"/></a>'
					)
				)
			)
		}
	}
</script>
	</head>

	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="lab.code.add"/><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labUserForm" id="form">
							<s:token></s:token>
							<input type="hidden" value="${labUserVo.uuid}" name="labUserVo.uuid" />
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
																<l:a uri="user/labUser/addLabUser.action" value="lab.code.save" />
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
										<span><s:text name="user.info"></s:text></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<td>
													<label>
														<s:text name="msg.username"></s:text>：
													</label>
												</td>
												<td>
													<input name="labUserVo.name" onmouseover="this.title='长度不能超过8位'" value="${labUserVo.name}" valType="required,strLength" max="8" msg='<s:property value="getText('usennept')"/>' strLength-msg="长度不能超过8位" id="username" type="text" size="20" maxlength="8" />
												</td>
												<td>
													<label>
														<s:text name="login.nickname"></s:text>：
													</label>
												</td>
												<td>
													<input name="labUserVo.loginName" value="${labUserVo.loginName}" valType="required,strLength" onmouseover="this.title='长度不能超过12位'" max="32" msg="登录名不能为空" strLength-msg="长度不能超过32位" id="loginname" type="text" size="20" onblur="checkLoginname()" maxlength="12" />
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
														<s:text name="theme.birthday"></s:text>：
													</label>
												</td>
												<td>
													<input name="labUserVo.birthday" value="${labUserVo.birthday}" id="birthday" type="text" size="20" class="Wdate" onfocus="WdatePicker()" />
												</td>
												<td>
													<label>
														<s:text name="theme.sex"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.sex" value='<s:property value="getText('theme.male')"/>' checked="checked" />
													<s:text name="theme.male"/>
													<input type="radio" name="labUserVo.sex" value='<s:property value="getText('theme.female')"/>' />
													<s:text name="theme.female"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="using"/>&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.isUsed" value="Y" checked="checked" />
													<s:text name="lab.yes"/>
													<input type="radio" name="labUserVo.isUsed" value="N" />
													<s:text name="lab.no"/>
												</td>
												<td>
													<label>
														<s:text name="login.pass"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.pwd" value="${labUserVo.pwd }" valType="strLength" max="32" strLength-msg="密码长度不能超过32位" id="password" type="password" size="20" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.duty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<s:select list="#request.listLabCode" value="labUserVo.duty" cssStyle="width:153px" name="labUserVo.duty" theme="simple" listValue="name" listKey="name"></s:select>
												</td>
												<td>
													<label>
														<s:text name="config.type"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input type="radio" onclick="clickType(this);" name="labUserVo.userType" <s:if test="${labUserVo.userType == 'FRONT'}"> checked="checked"</s:if> value="FRONT" id="typeFRONT" />
													<s:text name="front.user"/>
													<input type="radio" onclick="clickType(this);" name="labUserVo.userType" <s:if test="${labUserVo.userType == 'BACK'}"> checked="checked"</s:if> value="BACK" id="typeBACK" />
													<s:text name="back.user"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="remark"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td colspan="4">
													<textarea name="labUserVo.remark" valType="strLength" max="512" strLength-msg="备注长度不能超过32位" cols="50" rows="2" label="备注">${labUserVo.remark}</textarea>
												</td>
											</tr>
											<tr>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td>
												</td>
												<td class="c" style="width: 80px; height: 100px">
													<a href="javascript:;" onclick="uploadFile('${labUserVo.uuid}','userLogo');"><img src="/img/lefttorightselect/arrow_up.gif" /><s:text name="local.view"></s:text></a> (170×190px)
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="org.info"/></span>
										<a href="javascript:;" id="addRow4Org" onclick="addRow4Org()" style="color: red;">&nbsp;&nbsp;+&nbsp;<s:text name="add.org"></s:text></a>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon" id="orgTable">
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
