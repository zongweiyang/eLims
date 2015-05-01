<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
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
		var url  = '${basePath}/user/labUserTrain/showLabUser4Select.action';
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
		var config = {
			itmes: [],
			display: true,
			callback:callback,
			init:""
		};
		var userWin = null;
		function selectLabUser(){
			if (userWin != null) {
				userWin.show();
			} else {
				userWin = $.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>utils/chooseuser/showLabUser4Select.jsp',
					title:'<s:property value="getText('select.people')"/>',
					opacity:0.4,
					width:840,
					lock:true,
					max:false,
					min:false,
					close:function(){
						this.hide();
						return false;
					}
				 });
			}
			$.dialog.data("config",config);
		}
		function callback(data) {
			$("#studentNames").val(data.names);
			$("#studentIds").val(data.ids);
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
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labUserTrainForm">
							<s:token></s:token>
							<input type="hidden" name="labUserTrainVo.id" value="${labUserTrainVo.id}" />
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
															<l:a uri="user/labUserTrain/updateLabUserTrain.action" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="stone">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="msg.depart"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<s:select theme="simple" list="#request.orgList" listKey="id" listValue="name" name="labUserTrainVo.orgId" id="orgId"  headerKey="" headerValue="请选择..."></s:select>
											</td>
											<td>
												<label>
													<s:text name="msg.subject"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserTrainVo.title" id="title" value="${labUserTrainVo.title}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="teacher"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserTrainVo.teacher" id="teacher" value="${labUserTrainVo.teacher}" />
											</td>
											<td>
												<label>
													<s:text name="training.place"/>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserTrainVo.station" id="station" value="${labUserTrainVo.station}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="training.time"/>：
												</label>
											</td>
											<td>
												<input size="30" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" name="labUserTrainVo.tdate" value="${labUserTrainVo.tdate}" id="tdate"   valType="required"  msg="时间不能为空"/>
											</td>
											<td>
												<label>
													<s:text name="training.site"/>：
												</label>
											</td>
											<td>
												<input size="30" type="text" name="labUserTrainVo.address" id="address" value="${labUserTrainVo.address}"  valType="required"  msg="地点不能为空"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="trained.man"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labUserTrainVo.studentNames" cols="60" rows="3" id="studentNames"  onclick="selectLabUser();return false;">${labUserTrainVo.studentNames}</textarea>
												<input type="hidden" name="labUserTrainVo.studentIds" value="${labUserTrainVo.studentIds}" id="studentIds" />
												&nbsp;&nbsp;<input type="checkbox" name="labUserTrainVo.isMsg" value="Y" <s:if test="${labUserTrainVo.isMsg=='Y'}">checked="checked"</s:if>/>&nbsp;<s:text name="msg.notify"></s:text>
												&nbsp;&nbsp;<input type="checkbox" name="labUserTrainVo.isSms" value="Y" <s:if test="${labUserTrainVo.isSms=='Y'}">checked="checked"</s:if>/>&nbsp;<s:text name="sms.notify"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="training.content"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labUserTrainVo.content" cols="60" rows="3" id="content" valType="required"  msg='<s:property value="getText('connotempty')"/>'>${labUserTrainVo.content}</textarea>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labUserTrainVo.remark" cols="60" rows="3" id="remark">${labUserTrainVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
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
