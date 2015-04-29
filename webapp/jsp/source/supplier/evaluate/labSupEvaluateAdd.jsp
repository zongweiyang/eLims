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
			  	function goBack(url){
						window.location.href='${basePath}'+url;
				}
				function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
				}
	</script>
	</head>
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
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSupEvaluateVo.labSupplierId"
								id="labSupplierId" value="${labSupEvaluateVo.labSupplierId}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<a id="BtnPreview" class="zPushBtn"
															href="javascript:void();"
															onclick="goBack('supplier/labSupEvaluate/listLabSupEvaluate.action?labSupEvaluateVo.labSupplierId=${labSupEvaluateVo.labSupplierId }');return false;"><img
																height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b>
														</a>
														</td>
														<td>
															<l:a
																uri="supplier/labSupEvaluate/addLabSupEvaluate.action"
																value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="comment.time"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate"
													onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
													name="labSupEvaluateVo.evaluateDate" id="evaluateDate"
													valType="required"
													value="${labSupEvaluateVo.evaluateDate }" readOnly="true" />
											</td>
											<td>
												<label>
													<s:text name="commenter"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupEvaluateVo.person"
													id="person" valType="required"
													value="${labSupEvaluateVo.person}" readOnly="true" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="priced"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.priceList"
													name="labSupEvaluateVo.price" headerKey=""
													headerValue="---请选择---" theme="simple" listKey="code"
													listValue="name" cssStyle="width:200px;"></s:select>
											</td>
											<td>
												<label>
													<s:text name="gived.stock"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.timeList"
													name="labSupEvaluateVo.delivery" headerKey=""
													headerValue="---请选择---" theme="simple" listKey="code"
													listValue="name" cssStyle="width:200px;"></s:select>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="prd.quant"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.qualityList"
													name="labSupEvaluateVo.quality" headerKey=""
													headerValue="---请选择---" theme="simple" listKey="code"
													listValue="name" cssStyle="width:200px;"></s:select>
											</td>
											<td>
												<label>
													<s:text name="service.quant"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.serverList"
													name="labSupEvaluateVo.server" headerKey=""
													headerValue="---请选择---" theme="simple" listKey="code"
													listValue="name" cssStyle="width:200px;"></s:select>
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
