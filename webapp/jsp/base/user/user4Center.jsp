<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
			<style>
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
	var newPwd = $('#rePwd').val();
	var oldPwd = $('#password').val();
	if(newPwd != oldPwd){
		validate.tip('<s:property value="getText('twopass.same')"/>',$('#password'));
		return false;
	}
	var df = document.labUserForm;	
	  df.action=actionstr;
	  df.submit();
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
			max:false,                 
	        min:false, 
			resize:false
		 });
	}//多附件labUpLoads.jsp height:300,
	function checkPassword(){
		var newPwd = $('#rePwd').val();
		var oldPwd = $('#password').val();
		if(oldPwd.length>0&&newPwd.length>0&&newPwd != oldPwd){
			validate.tip('<s:property value="getText('twopass.same')"/>',$('#password'));
			return false;
		}
	}
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
								<div class="myworkingbox">
									<div class="myworkingboxttitle">
										<h2>
											<s:text name="person.center"/>：
											<span><s:text name="update.now"/></span>
										</h2>
									</div>
									<form action="" method="post" name="labUserForm" id="form">
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
																			<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabUser4Center.action');return false;"><img height="20" width="20" src="/img/filesave.gif" /><b><s:text name="lab.code.save"/></b>
																			</a>
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="currenttab">
														<a href="javascript:;"><span><s:text name="base.info"/></span> </a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center1.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="auth.info"/></span> </a>
													</li>
													<li id="li04" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center3.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="other.info"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span></span>
													</div>
													<table class="FormtableCon">
														<tr>
															<td width="100">
																<label>
																	<s:text name="msg.username"/>：
																</label>
															</td>
															<td width="300">
																<input size="30" name="labUserVo.name"  value="${labUserVo.name}" id="username" type="text" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
															<td width="100">
																<label>
																	<s:text name="login.nickname"/>：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.loginName" value="${labUserVo.loginName}"  id="loginname" type="text" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
															<td rowspan="5" align="center" width="170">
																<div id="upfiles" style="width: 170px; height: 200px">
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
																<input size="30" name="labUserVo.birthday" value="${labUserVo.birthday}" id="birthday" type="text" class="Wdate" onfocus="WdatePicker()" />
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
																	<s:text name="telphone"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.telephone" value="${labUserVo.telephone}" valType="phone" id="telephone" type="text" msg="电话输入不正确"/>
															</td>
															<td>
																<label>
																	email：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.email" value="${labUserVo.email}" id="email" type="text" valType="email" msg="邮箱格式不正确"/>
															</td>
														</tr>
														<tr>
															<td>
																<label>
																	<s:text name="newed.pass"/>&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<input size="31" name="labUserVo.pwd" value="" id="password" type="password" onchange="checkPassword();" />
															</td>
															<td>
																<label>
																	<s:text name="confirm.pass"/>：
																</label>
															</td>
															<td>
																<input size="31" name="labUserVo.rePwd" value="" id="rePwd" type="password" onchange="checkPassword();" />
															</td>
														</tr>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td>
																&nbsp;
															</td>
														</tr>
														<tr>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td class="c">
																<a href="javascript:;" onclick="uploadFile('${labUserVo.id}','userLogo');"><img src="/img/lefttorightselect/arrow_up.gif" /><s:text name="local.view"/></a>
															</td>
														</tr>
													</table>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="detail.info"/></span>
													</div>
													<table class="FormtableCon">
														<tr>
															<td width="100">
																<label>
																	<s:text name="address"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td width="300">
																<input size="30" name="labUserVo.address" value="${labUserVo.address}" id="address" type="text" />
															</td>
															<td width="100">
																<label>
																	<s:text name="study.his"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<s:select cssStyle="width:214px;" theme="simple" list="#{'小学':getText('xiaoxue'),'初中':getText('chuzhong'),'高中':getText('gaozhong'),'中专':getText('zhongzhuan'),'专科':getText('zhuanke'),'本科':getText('benke'),'研究生':getText('yanjiusheng'),'博士':getText('boshi')}"   name="labUserVo.education" id="education" ></s:select>
															</td>
														</tr>
														<tr>
															<td>
																<label>
																	<s:text name="major"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.profession" value="${labUserVo.profession}" id="profession" type="text" maxlength="8" />
															</td>
															<td>
																<label>
																	<s:text name="hunyin.state"/>：
																</label>
															</td>
															<td>
																<s:select cssStyle="width:214px;" theme="simple" list="#{'已婚':getText('yihun'),'未婚':getText('weihun'),'离异':getText('liyi')}"   name="labUserVo.maritalStatus" id="maritalStatus" ></s:select>
															</td>
														</tr>
														<tr>
															<td>
																<label>
																	<s:text name="person.xiguan"/>：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.personalHabit" value="${labUserVo.personalHabit}" id="personalHabit" type="text" maxlength="12" />
															</td>
															<td>
																<label>
																	<s:text name="instrest"/>：
																</label>
															</td>
															<td colspan="3">
																<textarea name="labUserVo.remark" cols="30" rows="2" id="remark" type="text">${labUserVo.remark}</textarea>
															</td>
														</tr>
														<tr>
															<td width="100">
																<label>
																	<s:text name="work.number"/>：
																</label>
															</td>
															<td width="300">
																<input size="30" name="labUserVo.no" value="${labUserVo.no}" id="no" type="text" size="20" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
															<td width="100">
																<label>
																	<s:text name="work.time"/>：
																</label>
															</td>
															<td colspan="3">
																<input size="30" name="labUserVo.workDate" value="${labUserVo.workDate}" id="workDate" type="text" maxlength="8" class="Wdate" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
														</tr>
														<tr>
															<td>
																<label>
																<s:text name="theme.job"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.duty" value="${labUserVo.duty}" id="duty" type="text" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
															<td>
																<label>
																	<s:text name="theme.job.title"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
																</label>
															</td>
															<td>
																<input size="30" name="labUserVo.techTitle" value="${labUserVo.techTitle}" id="techTitle" type="text" readonly="readonly" style="background-color: #e9e9e9;" />
															</td>
														</tr>
													</table>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="perference.info"/></span>
													</div>
													<table class="FormtableCon">
														<tr>
															<td width="100">
																<label>
																	<s:text name="menu.style"/>：
																</label>
															</td>
															<td colspan="3">
																<s:select list="#request.listLabCode4CDFG" cssStyle="width:150px" value="%{labUserVo.menuType}" name="labUserVo.menuType" theme="simple" listValue="name" listKey="code"></s:select>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</div>
									</form>
								</div>
							</td>
						</tr>
					</table>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
	<script></script>
</html>
