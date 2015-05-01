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
				function goBack(url){
						window.location.href='${basePath}'+url;
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
						<form theme="simple" action="" method="post"
							name="labSupEvaluateForm">
							<input type="hidden" name="labSupEvaluateVo.labSupplierId"
								id="labSupplierId" value="${labSupEvaluateVo.labSupplierId}" />
							<input type="hidden" name="labSupEvaluateVo.id"
								value="${labSupEvaluateVo.id}" />
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
																uri="supplier/labSupEvaluate/updateLabSupEvaluate.action"
																value="lab.code.save" />
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
													<s:text name="comment.time"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupEvaluateVo.evaluateDate"
													id="evaluateDate" value="${labSupEvaluateVo.evaluateDate}"
													valType="required" readOnly="true" />
											<td>
												<label>
													<s:text name="commenter"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupEvaluateVo.person"
													id="person" value="${labSupEvaluateVo.person}"
													valType="required" readOnly="true" />
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
													headerValue="" theme="simple" listKey="code"
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
													headerValue="" theme="simple" listKey="code"
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
													headerValue="" theme="simple" listKey="code"
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
													headerValue="" theme="simple" listKey="code"
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
