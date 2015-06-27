<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
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
	<script>
    var eq;
	function checkRolename()
	{
	  var name=document.getElementById("name").value;
	  $.ajax({
	   	  url:'<%=basePath%>role/labRole/isRequiredRoleName.action',
	   	  data:{'name':name},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="1"){
             alert('<s:property value="getText('role.exist.error')"/>');
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
    function submitvalue(actionstr){
    var t = checkRolename();
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
								<span><s:text name="lab.code.add"></s:text></span>
							</h2>
						</div>
						<form action="" method="post" name="labRoleForm">
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
														<l:a uri="role/labRole/addLabRole.action" value="lab.code.save"/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="role.info"></s:text></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="lab.sequence"></s:text>：
											</label>
										</td>
										<td>
											<input name="labRoleVo.sort" id="sort" valType="required,strLength" max="11" strLength-msg='<s:property value="getText('seqcannotover11')"/>' type="text" size="20" value="${labRoleVo.sort}" />
										</td>
										<td>
											<label>
												<s:text name="role.name"></s:text>：
											</label>
										</td>
										<td>
											<input name="labRoleVo.name" id="name" valType="required,strLength" max="32" strLength-msg='<s:property value="getText('rolenameconat32')"/>' msg='<s:property value="getText('rolenameempty')"/>' type="text" size="20" onblur="checkRolename()" />
										</td>
										<td>
											<label>
												<s:text name="show"></s:text>：
											</label>
										</td>
										<td>
											<input type="radio" name="labRoleVo.show" value="FRONT" checked="checked"/><s:text name="front"></s:text>
											<input type="radio" name="labRoleVo.show" value="BACK" /><s:text name="backend"></s:text>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="remark"></s:text>：
											</label>
										</td>
										<td colspan="5">
											<textarea name="labRoleVo.remark" valType="strLength" max="256" strLength-msg='<s:property value="getText('remark.toolong')"/>' cols="60" rows="3" label="备注"></textarea>
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
</html>
