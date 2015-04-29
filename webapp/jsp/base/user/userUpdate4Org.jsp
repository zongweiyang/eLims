<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
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
	var df = document.labUserForm;	
	  df.action=actionstr;
	  df.submit();
}
	function uploadFile(busId,busType){
	   fileTypes = '*.jpg;*.gif;*.png;';
	   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&showType=show';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'logo上传',
			opacity:0.4,
			width:300, 
			height:80,
			lock:true,
			max: false,                  
	        min: false, 
			resize:true
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
								<span><s:text name="lab.code.modify"></s:text></span>
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
																<l:a uri="user/labUser/updateLabUser4Org.action" img="/img/add.gif" value="lab.code.save" />
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
										<span><s:text name="base.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<td class="w100">
													<label>
														<s:text name="msg.username"/>：
													</label>
												</td>
												<td class="w300">
													<input name="labUserVo.name" valType="required,strLength" max="32" msg="用户名不能为空" strLength-msg="用户名长度不能超过32位" value="${labUserVo.name}" id="username" type="text" size="20" readonly="readonly" style="background-color: #e9e9e9;" />
												</td>
												<td class="w100">
													<label>
														<s:text name="login.nickname"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.loginName" onmouseover="this.title='长度不能超过12位'" valType="required,strLength" max="32" msg="登录名未能为空" strLength-msg="长度不能超过32位" value="${labUserVo.loginName}" id="loginname" type="text" size="20" maxlength="12" readonly="readonly" style="background-color: #e9e9e9;" />
												</td>
												<td rowspan="4" align="center" width="170">
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
														<s:text name="theme.sex"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input type="radio" name="labUserVo.sex" value="男" <s:if test="${labUserVo.sex=='男'}">checked="checked" </s:if> />
													<s:text name="theme.male"/>
													<input type="radio" name="labUserVo.sex" value="女" <s:if test="${labUserVo.sex=='女'}">checked="checked" </s:if> />
													<s:text name="theme.female"/>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.phone"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.telephone" valType="strLength,number" max="32" strLength-msg="联系电话长度不能超过32位" value="${labUserVo.telephone}" id="telephone" type="text" size="20" />
												</td>
												<td>
													<label>
														<s:text name="theme.tel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input name="labUserVo.mobile" value="${labUserVo.mobile}" valType="strLength,number" max="11" min="11" strLength-msg="手机号长度为11位" id="mobile" type="text" size="20" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.nation"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input name="labUserVo.nation" value="${labUserVo.nation}" valType="strLength" max="32" strLength-msg="民族长度不能超过32位" id="nation" type="text" size="20" />
												</td>
												<td>
													<label>
														email：
													</label>
												</td>
												<td>
													<input name="labUserVo.email" value="${labUserVo.email}" valType="strLength" max="128" strLength-msg="邮箱长度不能超过128位" id="email" type="text" size="20" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="address"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td colspan="3">
													<input name="labUserVo.address" value="${labUserVo.address}" valType="strLength" max="128" strLength-msg="地址长度不能超过128位" id="address" type="text" size="60" />
												</td>
												<td class="c">
													<a href="javascript:;" onclick="uploadFile('${labUserVo.id}','userLogo');"><img src="/img/lefttorightselect/arrow_up.gif" /><s:text name="local.view"></s:text></a>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="work.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<td class="w100">
													<label>
														<s:text name="work.number"/>：
													</label>
												</td>
												<td class="w300">
													<input name="labUserVo.no" value="${labUserVo.no}" id="no" valType="strLength" max="32" strLength-msg="工号长度不能超过32位" type="text" size="20" />
												</td>
												<td class="w100">
													<label>
														<s:text name="come.working.date"/>：
													</label>
												</td>
												<td>
													<input name="labUserVo.workDate" value="${labUserVo.workDate}" id="workdate" type="text" size="20" class="Wdate" onfocus="WdatePicker()" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.duty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<s:select list="#request.listLabCode" cssStyle="width:153px" name="labUserVo.duty" theme="simple" listValue="name" listKey="name"></s:select>
												</td>
												<td>
													<label>
														<s:text name="theme.job"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													<input type="text" name="labUserVo.techTitle" valType="strLength" max="64" strLength-msg="职称长度不能超过64位" value="${labUserVo.techTitle}" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="remark"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td colspan="4">
													<textarea name="labUserVo.remark" cols="50" rows="2" valType="strLength" max="512" strLength-msg="备注长度不能超过512位" label="备注">${labUserVo.remark}</textarea>
												</td>
											</tr>
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
