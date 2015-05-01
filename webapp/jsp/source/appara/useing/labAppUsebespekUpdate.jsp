<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}

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

	<script language=javascript> 
			  	function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
				}
				function submitvalue1(actionstr){
						if(!confirm('<s:property value="getText('confirmcaceltime')"/>'))return false;
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
				}
	</script>
	</head>
	<body>
		<form action="" method="post" target="workarea" name="studentForm">
			<div class="TabTable">
				<div class="FUNCIONBARNEW">
					<table>
						<tr>
							<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
								<table cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td>
											<l:a onclick="submitvalue1('appara/useing/deleteLabAppUse4bespek.action');return false;" uri="appara/useing/deleteLabAppUse4bespek.action" value="cancel.dating" />
										</td>
										<td>
											<l:a uri="appara/useing/updateLabAppUse4bespek.action" img="/img/add.gif" value="theme.save" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
					<input type="hidden" value="${labApparaUseVo.id}" name="labApparaUseVo.id" />
					<input type="hidden" value="${labApparaUseVo.uuid}" name="labApparaUseVo.uuid" />
					<tr>
						<td>
							<label>
								<s:text name="app.name"/>：
							</label>
						</td>
						<td>
							<s:select cssStyle="width:130px;" headerKey="${labApparaUseVo.appId}" headerValue="${labApparaUseVo.appName}" name="labApparaUseVo.appId"  list="#request.labApparaList" listKey="id" listValue="name" theme="simple" />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<s:text name="using.peo"/>：
							</label>
						</td>
						<td>
							<s:select cssStyle="width:130px;" headerKey="${labApparaUseVo.useUserId}" headerValue="${labApparaUseVo.useUser}" name="labApparaUseVo.useUserId"  list="#request.labUserList" listKey="id" listValue="name" theme="simple"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<s:text name="start.time"/>：
							</label>
						</td>
						<td>
							<input type="text" class="Wdate" onFocus="WdatePicker({WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'bespeakEndDate\')}'});" value="${labApparaUseVo.beStartDate}" name="labApparaUseVo.beStartDate" id="bespeakStartDate" />
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<s:text name="end.time"/>：
							</label>
						</td>
						<td>
							<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'bespeakStartDate\')}'});" name="labApparaUseVo.beEndDate" id="bespeakEndDate" value="${labApparaUseVo.beEndDate }"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="clear"></div>
	</body>
</html>
