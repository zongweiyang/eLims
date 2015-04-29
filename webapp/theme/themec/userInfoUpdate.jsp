<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ include file="../jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<script language="JavaScript" src="<%=basePath%>js/portal/commonutil.js"></script>
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
function submitvalueforlist(actionstr){
    var password = document.getElementById('password').value;
	var rpassword = document.getElementById('rpassword').value;
	if(password != rpassword){
		alert('<s:property value="getText('theme.two.pass')"/>');
		return false;
	}
	
	var df = document.sysUserform;
	if(validateForm(df)){
	  df.action=actionstr;
	  df.submit();
	}
}			
</script>
	<body><%try{ %>						    
		<div class="myworkingbox">
			<div class="myworkingboxttitle">
				<h2>
					<s:text name="theme.own.info"></s:text>：
					<span><s:text name="theme.modify"></s:text></span>
				</h2>
			</div>
			<s:form  method="post" name="sysUserform" theme="simple">
				<input type="hidden" name="labUserVo.id" id="id" value="${labUserVo.id}"/>
				<input type="hidden" name="labUserVo.sysOrgId" id="sysOrgId" value="${labUserVo.sysOrgId}"/>
				<input type="hidden" name="labUserVo.sysDutyId" id="sysDutyId" value="${labUserVo.sysDutyId}"/>
				<input type="hidden" name="labUserVo.isused" id="isused" value="${labUserVo.isused}"/>
				<div >
					<div class="FUNCIONBARNEW">
                        <table>
                        	<tr>
                        		<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                        			<table cellspacing="0" cellpadding="0" border="0">
                        			<tr>
        					    	<td>
                                    	<td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalueforlist('updateProfileForUser.action');return false;"><img height="20" width="20" src="<%=basePath%>img/filesave.gif"/><b><s:text name="theme.save"></s:text></b></a></td>
                                    </td>
                           			</tr>
                           			</table>
                             	</td>
                            </tr>
     					</table>
					</div>
					<div class="Formtable">
						<div class="Formtabletitle">
							<span><s:text name="theme.people.info"></s:text></span>
						</div>
						<table class="FormtableCon">
								<tr>
									<td>
										<label>
											<s:text name="theme.depart"></s:text>：
										</label>									</td>
									<td>
										<input name="labUserVo.sysOrgName" class="disab" class="disab" id="sysOrgName" type="text" size="20" value="${labUserVo.sysOrgName }" readonly="true"/>									</td>

									<td>
									<label><s:text name="theme.user.name"></s:text>：</label>									</td>
									<td><input name="labUserVo.username"  id="username" type="text" size="20" value="${labUserVo.username }"/></td>
								</tr>
								<tr>
									<td>
										<label><s:text name="theme.login.name"></s:text>：										</label>									</td>
									<td>
									    <input name="labUserVo.loginname" class="disab" id="loginname" type="text" size="20" value="${labUserVo.loginname }" readonly="true"/>		    </td>

									<td>
										<label><s:text name="theme.sex"></s:text>：										</label>									</td>
									<td class="radiolabel">
									    <s:radio name="labUserVo.sex" id="sex" value="${labUserVo.sex}" list="#{'0':'#{getText('theme.male')}','1':'#{getText('theme.female')}'}"/>
  								    </td>
								</tr>
								<tr>
									<td>
									<label><s:text name="theme.pass"></s:text>：										</label>
									</td>
									<td><input name="labUserVo.password"  id="password" type="password" size="20" value="${labUserVo.password }"  maxlength="20"/>	</td>

									<td>
										<label><s:text name="theme.pass.repeat"></s:text>：										</label>									</td>
									<td>
										<input name="labUserVo.rpassword"  id="rpassword" type="password" size="20" value="${labUserVo.rpassword }"  maxlength="20"/></td>
								</tr>
								
								<tr>
									<td>
										<label><s:text name="theme.phone"></s:text>：										</label>									</td>
									<td>
										<input name="labUserVo.contact"  id="contact" type="text" size="20" value="${labUserVo.contact }"  maxlength="32"/>									</td>

									<td><label>Email：										</label></td>
									<td><input name="labUserVo.email"  id="email" type="text" size="20" value="${labUserVo.email }"  maxlength="32"/>	</td>
								</tr>
							</table>
						</div>
				</div>

				</td>
				</tr>
				</table>
				</td>
				</tr>
				</table>
		
		</div>
					</s:form>
					<%}catch(Exception e){ e.printStackTrace();} %>
	</body>
<script language="javascript" type="text/javascript">
function required ()  
{

}
function maxlength(){

}
function DateTimeValidations(){

}
function email(){
	this.c = new Array("email",  '<s:property value="getText('theme.right.email')"/>',   new Function ("varName", " return this[varName];"));	
}
function FloatValidations(){
}
function IntegerValidations(){
}
function DateValidations () {
}
</script>
</html>
