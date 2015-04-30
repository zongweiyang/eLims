<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
			</script>
	<body>
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labAttendConfigForm">
							<s:token></s:token>
							<input type="hidden" name="labAttendConfigVo.id" value="${labAttendConfigVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="attendance/labAttendConfig/updateLabAttendConfig.action" img="/img/add.gif" value="lab.code.save" />
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
											<td width="100">
												<label>
													<s:text name="titleed"/>：
												</label>
											</td>
											<td width="300">
												<input type="text" name="labAttendConfigVo.title" id="title" value="${labAttendConfigVo.title}" />
											</td>
											<td width="100">
												<label>
													<s:text name="weekday"/>：
												</label>
											</td>
											<td id="workDay">
												<s:checkboxlist theme="simple" list="#{'1':getText('monday'),'2':getText('quesday'),'3':getText('thirday'),'4':getText('fourday'),'5':getText('firday'),'6':getText('satarday'),'7':getText('sunday')}" name="labAttendConfigVo.workDay"></s:checkboxlist>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="wordday.time"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" name="labAttendConfigVo.startTime" value="${labAttendConfigVo.startTime}" id="startTime" />
											</td>
											<td>
												<label>
													<s:text name="after.time"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'})" name="labAttendConfigVo.endTime" value="${labAttendConfigVo.endTime}" id="endTime" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="begindatedo"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDay\')}',minDate:'%y-%M-%d'})" name="labAttendConfigVo.startDay" value="${labAttendConfigVo.startDay}" id="startDay" />
											</td>
											<td>
												<label>
													<s:text name="enddodate"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDay\')}'})" name="labAttendConfigVo.endDay" value="${labAttendConfigVo.endDay}" id="endDay" />
											</td>

										</tr>
										<tr>
											<td>
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labAttendConfigVo.remark" cols="60" rows="3" id="remark">${labAttendConfigVo.remark}</textarea>
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
<script>
	$(function(){
		var reprotStr='${labAttendConfigVo.workDay}';
		var postStr=reprotStr.split(',');
		$('#workDay').find('input[type="checkbox"]').each(function(){ 
	         var checkVal=$(this).val(); 
	         for(var i=0;i<postStr.length;i++){
	         	var str=postStr[i].replace(' ','');
	         	if(str==checkVal){
	         		$(this).prop('checked',true);
	         	}
	         }
 	   	});  
	});
</script>
</html>
