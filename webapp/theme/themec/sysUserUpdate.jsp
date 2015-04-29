<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ include file="../../../jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>


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

		<s:head />
	</head>
	<script>
	function submitvalue(actionstr){	
	var df = document.sysUserForm;		
	  df.action=actionstr;
	  shandow($('body'));
	  df.submit();	
}
	function checkshenfenzheng(obj){
		var pattern=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		var num=$(obj).val();
		if(!pattern.exec(num)){
		   	alert('<s:property value="getText('theme.ic.format')"/>');
	        $('#cardNum').val('');
	        return false;
		 }else{
		 }
		 return true;
	}
	function checkdianhua(obj){
		var pattern=/^(\d{11}$|^\d{3,4}[- ]?\d{0,8}[- ]?\d{0,8})$/;
		var num=$(obj).val();
		if(!pattern.exec(num)){
			   	alert('<s:property value="getText('theme.tel.format')"/>');
		        $(obj).val('');
		        return false;
		}else{
		}
		return true;
	}
	function checkages(obj){
		var ages=$(obj).val();
		var length=$(obj).val().length;
		if(length>3){
			alert('<s:property value="getText('theme.age.error')"/>');
			$(obj).val('');
			return false;
		}else if(/[^\d]/.test(ages)){
			alert('<s:property value="getText('theme.input.number')"/>')
			$(obj).val('');
			return false;
		}
		return true;
	}
</script>
	<body class="" id="mainid" style="overflow-y: auto;" >
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<!--<s:fielderror cssStyle="color: red"></s:fielderror>
					<s:if test="hasFieldErrors()">
						<s:iterator value="fieldErrors">
							<s:iterator value="value" status="statu">
								               //关键代码 
								<s:set name="msg"
									value="((#msg==null || #msg=='')?'':#msg+'\\\n')" />
								<s:set name="msg"
									value="#msg+value.get(#statu.getIndex()).toString()" />
							</s:iterator>
						</s:iterator>
					</s:if>
					<s:if test="#msg.length()>0">
						<script language="JavaScript">      
				            alert("<s:property escape="false" value="#msg"/>")      
				        </script>
					</s:if>
					-->
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								<s:text name="theme.people.dangan"/>：
								<span><s:text name="theme.modify"></s:text></span>
							</h2>
						</div>
						<s:form theme="simple" action="" method="post" name="sysUserForm">
							<input name="userAddendumMainVo.id" id="id" type="hidden"
								value="${userAddendumMainVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<a id="BtnPreview" class="zPushBtn" href="#"
																onclick="submitvalue('updateSysUserForSelf.action');return false;"><img
																	height="20" width="20"
																	src="<%=basePath%>img/chakan.gif" /><b><s:text name="theme.save"></s:text></b>
															</a>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
									<div class="tabtablebox">
										<div class="tabtableboxtitle">
											<span><s:text name="theme.people.info"></s:text></span>
										</div>
										<table class="tabtableboxtable">
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.user.name"></s:text>：
													</label>
												</td>
												<td>
													${userAddendumMainVo.userName}
													<input type="hidden" name="userAddendumMainVo.userid"
														id="userid" value="${userAddendumMainVo.userid}" />
													<input type="hidden" name="userAddendumMainVo.userName"
														id="userName" value="${userAddendumMainVo.userName}" />
													
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.login.name"></s:text>：
													</label>
												</td>
												<td>
													${labUserVo.loginname}
													<input type="hidden" name="userAddendumMainVo.loginname"
														id="loginname" value="${userAddendumMainVo.loginname}" />
													
												</td>

											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.sex"/>：
													</label>
												</td>
												<td class="w150">
													<s:if test="${userAddendumMainVo.sexs==0}">
													<s:text name="theme.male"></s:text>
													</s:if>
													<s:else>
													<s:text name="theme.female"></s:text>
													</s:else>
													<input type="hidden"  name="userAddendumMainVo.sexs" value="${userAddendumMainVo.sexs}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.birthday"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.birthday}
													<input name="userAddendumMainVo.birthday" id="birthday"
														type="hidden" size="20" class="Wdate"
														onfocus="WdatePicker()" value="${userAddendumMainVo.birthday}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.politic.status"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.politicStatus}
													<input type="hidden"  name="userAddendumMainVo.politicStatus" value="${userAddendumMainVo.politicStatus}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.nation"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.nations}
													<input name="userAddendumMainVo.nations" id="nations" type="hidden"
														size="20" value="${userAddendumMainVo.nations}" />
												</td>

											</tr>
												<tr>
												<td class="w150">
													<label>
														<s:text name="theme.id.number"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.cardNum}
													<input name="userAddendumMainVo.cardNum" onblur="checkshenfenzheng(this);"
														id="cardNum" type="hidden" size="20" value="${userAddendumMainVo.cardNum}"/>
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.age"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.ages}
													<input name="userAddendumMainVo.ages" id="ages" type="hidden"  onblur="checkages(this);"
														size="20" value="${userAddendumMainVo.ages}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.phone"></s:text>：
													</label>
												</td>
												<td>
													<input name="userAddendumMainVo.telephones" id="telephones" type="text"
														size="20" value="${userAddendumMainVo.telephones}"  onblur="checkdianhua(this);"/>
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.tel"/>：
													</label>
												</td>
												<td>
													<input name="userAddendumMainVo.mobiles" id="mobiles" type="text"
														size="20" value="${userAddendumMainVo.mobiles}"  onblur="checkdianhua(this);"/>
												</td>
											</tr>
																			<tr>
												<td class="w150">
													<label>
														e-mail：
													</label>
												</td>
												<td>
													<input name="userAddendumMainVo.emails" id="emails" type="text"
														size="20" value="${userAddendumMainVo.emails}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.addr"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.adress}
													<input name="userAddendumMainVo.adress" id="adress" type="hidden"
														size="20" value="${userAddendumMainVo.adress}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.knowledge"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.wenhua}
													<input name="userAddendumMainVo.wenhua" id="wenhua" type="hidden"
														size="20" value="${userAddendumMainVo.wenhua}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.degree"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.xuewei}<input name="userAddendumMainVo.xuewei" id="xuewei" type="hidden"
														size="20" value="${userAddendumMainVo.xuewei}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.graduate"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.biyeyuanxiao}
													<input name="userAddendumMainVo.biyeyuanxiao"
														id="biyeyuanxiao" type="hidden" size="20"
														value="${userAddendumMainVo.biyeyuanxiao}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.focus.master"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.zhuanye}
													<input name="userAddendumMainVo.zhuanye" id="zhuanye"
														type="hidden" size="20" value="${userAddendumMainVo.zhuanye}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.graduate.date"/>：
													</label>
												</td>
												<td>${userAddendumMainVo.graduationDate}
													<input name="userAddendumMainVo.graduationDate"
														id="graduationDate" type="hidden" size="20" class="Wdate"
														onfocus="WdatePicker()"
														value="${userAddendumMainVo.graduationDate}" />
												</td>
												
											</tr>
											<tr><td colspan="4">&nbsp;</td></tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.depart"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.orgUnitName}
													<input name="userAddendumMainVo.orgUnitName"
														id="orgUnitName" type="hidden" size="20"
														value="${userAddendumMainVo.orgUnitName}" />
													<input name="userAddendumMainVo.orgUnitId"
														id="orgUnitId" type="hidden" size="20"
														value="${userAddendumMainVo.orgUnitId}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.office"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.sysOrgName}
													<input name="userAddendumMainVo.sysOrgName"
														id="sysOrgName" type="hidden" size="20"
														value="${userAddendumMainVo.sysOrgName}" />
													<input name="userAddendumMainVo.sysOrgId"
														id="sysOrgId" type="hidden" size="20"
														value="${userAddendumMainVo.sysOrgId}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.emplyee.date"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.offDate}
													<input name="userAddendumMainVo.offDate"
														id="offDate" type="hidden" size="20" class="Wdate"
														onfocus="WdatePicker()"
														value="${userAddendumMainVo.offDate}" />
												</td>
												<td class="w150">
													<label>
														<s:text name="theme.addin.time"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.addDate}
													<input name="userAddendumMainVo.addDate"
														id="addDate" type="hidden" size="20" class="Wdate"
														onfocus="WdatePicker()"
														value="${userAddendumMainVo.addDate}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.addschool.time"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.comeDateY}
													<input name="userAddendumMainVo.comeDateY"
														id="comeDateY" type="hidden" size="20" class="Wdate"
														onfocus="WdatePicker()"
														value="${userAddendumMainVo.comeDateY}" />
												</td>
											
												<td class="w150">
													<label>
														<s:text name="theme.comeoffice.time"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.comeDateS}
													<input name="userAddendumMainVo.comeDateS" id="comeDateS" type="hidden"
														size="20" class="Wdate" onfocus="WdatePicker()"
														value="${userAddendumMainVo.comeDateS}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.job"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.zhiwu}
													<input type="hidden" name="userAddendumMainVo.zhiwu" value="${userAddendumMainVo.zhiwu}"/>
												</td>
											
												<td class="w150">
													<label>
														<s:text name="theme.job.title"/>：
													</label>
												</td>
												<td>${userAddendumMainVo.techTitle}
													<input type="hidden" name="userAddendumMainVo.techTitle" value="${userAddendumMainVo.techTitle}"/>
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.work.site"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.workAdress}
													<input name="userAddendumMainVo.workAdress" id="workAdress"
														type="hidden" size="20"  value="${userAddendumMainVo.workAdress}" />
												</td>
											
												<td class="w150">
													<label>
														<s:text name="theme.worker.use"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.workType}
													<input name="userAddendumMainVo.workType" id="workAdress" type="hidden" size="20"  value="${userAddendumMainVo.workType}" />
												</td>
											</tr>
											<tr>
												<td class="w150">
													<label>
														<s:text name="theme.operation"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.operCard}
													<input type="hidden" name="userAddendumMainVo.operCard"
														value="${userAddendumMainVo.operCard}" id="operCard"/>
												</td>
											
												<td class="w150">
													<label>
														<s:text name="theme.checking"></s:text>：
													</label>
												</td>
												<td>${userAddendumMainVo.verifyCard}
													<input type="hidden" name="userAddendumMainVo.verifyCard"
														value="${userAddendumMainVo.verifyCard}" id="verifyCard"/>
												</td>
												
											</tr>
										</table>
									</div>
						</s:form>
					</div>
				</td>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<s:fielderror cssStyle="color: red"></s:fielderror>
		<div class="clear"></div>
	</body>
</html>
