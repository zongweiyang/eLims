<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>

		<%@ include file="../../include/common.jsp"%>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet" type="text/css" id="theme" />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>

		<link href="<%=basePath%>css/global.css" media="all" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/common.css" media="all" rel="stylesheet" type="text/css" />
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
function submitvalue(actionstr){
	var df = document.labRoleForm;	
	  df.action=actionstr;
	  df.submit();
}
			
</script>
	<body>



		<div class="myworkingbox">
			<div class="myworkingboxttitle">
				<h2>
					${funName }：
					<span><s:text name="look.check"></s:text></span>
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
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div class="Formtable">
						<div class="Formtabletitle">
							${funName }
							<span><s:text name="look.check"></s:text></span>
						</div>
						<table class="FormtableCon">
							<tr>
								<td class="r">
									<label>
										<s:text name="lab.sequence"></s:text>：
									</label>
								</td>
								<td>
									${labRoleVo.sort}
								</td>
								<td class="r">
									<label>
										<s:text name="role.name"></s:text>：
									</label>
								</td>
								<td class="l">
									${labRoleVo.name}
								</td>
								<td>
									<label>
										<s:text name="show"></s:text>：
									</label>
								</td>
								<td>
									<input disabled="true" type="radio" name="labRoleVo.show" value="FRONT" <s:if test="${labRoleVo.show=='FRONT'}">checked="checked"</s:if>/><s:text name="front"></s:text>
									<input disabled="true" type="radio" name="labRoleVo.show" value="BACK" <s:if test="${labRoleVo.show=='BACK'}">checked="checked"</s:if>/><s:text name="backend"></s:text>
								</td>
							</tr>
							<tr>
								<td class="r">
									<label>
										<s:text name="remark"></s:text>：
									</label>
								</td>
								<td class="l" colspan="5">
									<textarea style="text-align: left;" readonly="readonly" name="labRoleVo.remark" valType="strLength" max="256" strLength-msg='<s:property value="getText('remark.toolong')"/>' cols="30" rows="3" label='<s:property value="getText('remark')"/>'>${labRoleVo.remark}</textarea>
								</td>
							</tr>
						</table>
					</div>
			</form>
		</div>
	</body>
	<script></script>
</html>
