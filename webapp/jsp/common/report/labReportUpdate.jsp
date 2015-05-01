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
					var busId=$('#busId').val();
				  		if(busId==''){
				  			validate.tip("请选择业务.",$('#busId'));
				  			return false;
				  		}
					var df = document.labReportForm;
					 	df.action=actionstr;
					  	df.submit();
				}
				function getWfProcessCode(obj){
					var val=$(obj).val();
					var vv=val.split("^");
					if(vv.length==2&&vv[0].length>0){
				   	  	$('#busCode').val(vv[1]);
				   	  	$('#busId').val(vv[0]);
				   }else{
				   		alert('<s:property value="getText('dataexception')"/>');
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
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="labReportForm">
							<s:token></s:token>
							<input type="hidden" name="labReportVo.id" value="${labReportVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="report/labReport/listLabReport.action" onclick="nextUri('report/labReport/listLabReport.action');" img="img/fanhui.gif" value="msg.back" />
														</td>
														<td>
															<l:a uri="report/labReport/updateLabReport.action" onclick="submitvalue('report/labReport/updateLabReport.action');return false;" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="report.tmp.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="tmp.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labReportVo.title" id="title" value="${labReportVo.title}" size="30" valType="required,strLength" max="32" msg="名称不能为空" strLength-msg="长度不能超过32位" />
											</td>
											<td>
												<label>
													<s:text name="type.large"/>：
												</label>
											</td>
											<td>
												<s:select list="#{'1':getText('html.tmp'),'2':getText('excel.tmp')}" name="labReportVo.type" id="type" headerKey="" theme="simple" cssStyle="width:176px;"></s:select>
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="biz.large"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.funcList" listKey="id+'^'+wfcode" listValue="name" id="funSelect" theme="simple" cssStyle="width:176px;" headerKey="" headerValue="-请选择-" onchange="getWfProcessCode(this);"></s:select>
												<input type="hidden" name="labReportVo.busId" id="busId" value="${labReportVo.busId}" />
												<font color="red">*</font>
											</td>
											<td>
												<label>
													<s:text name="biz.code"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labReportVo.busCode" id="busCode" value="${labReportVo.busCode}" size="30"  readonly="readonly" style="background-color: #eee" valType="required"  msg="编号未取或配置出错."/>
											</td>
											
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="biz.type"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labReportVo.busType" id="busType" value="${labReportVo.busType}" size="30"  valType="strLength" max="32"  strLength-msg="长度不能超过32位"/>
											</td>
											<td>
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td >
												<textarea rows="2" cols="40" name="labReportVo.remark">${labReportVo.remark}</textarea>
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
		var busId='${labReportVo.busId}';
		$('#funSelect').find('option[value*="'+busId+'^"]').attr("selected","selected");
	});
</script>
</html>
