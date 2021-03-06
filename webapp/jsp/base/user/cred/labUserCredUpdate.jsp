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
</style>



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
		$('form').attr('action','<%=basePath%>'+actionstr);
		$('form').submit();
	}
	function ajax2UserList(){
		var url  = '${basePath}user/labUserCred/showLabUser4Select.action';
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('user.list')"/>',
			opacity:0.4,
			width:800,
			height:400,
			max: false,                  
	        min: false,
			lock:true
		 });
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
								${funName}：
								<span><s:text name="config.modify"></s:text></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labUserCredForm">
							<input type="hidden" name="labUserCredVo.id" value="${labUserCredVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="user/labUserCred/updateLabUserCred.action" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="stone">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="theme.user"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3">
												<input readonly="readonly" size="30" type="text" name="labUserCredVo.userName" value="${labUserCredVo.userName}" id="userName" valType="required" onclick="ajax2UserList();"/>
												<input type="hidden" name="labUserCredVo.userId" value="${labUserCredVo.userId}" id="userId" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="card.name"></s:text>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.name" id="name" value="${labUserCredVo.name}" valType="required" />
											</td>
											<td>
												<label>
													<s:text name="card.number"></s:text>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.code" id="code" value="${labUserCredVo.code}" valType="required" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="start.date"></s:text>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.startDate" id="startDate" value="${labUserCredVo.startDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});"/>
											</td>
											<td>
												<label>
													<s:text name="end.date"></s:text>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.endDate" id="endDate" value="${labUserCredVo.endDate}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="theme.depart"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.unit" id="unit" value="${labUserCredVo.unit}" />
											</td>
											<td>
												<label>
													<s:text name="config.type"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserCredVo.type" id="type" value="${labUserCredVo.type}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3">
												<textarea rows="2" cols="60" name="labUserCredVo.remark" id="remark">${labUserCredVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
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
		<div class="clear"></div>
	</body>
</html>
