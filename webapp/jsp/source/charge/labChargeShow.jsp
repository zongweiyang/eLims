<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
<script>
function goToNextAction(url){
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
}

function showReport(flag){
	var tempId=$('#tempId').val();
	if(tempId==''){
		alert("模版为选中或未配置.");
		return false;
	}
	var height = window.screen.height-250;
	var url  = '${basePath}/charge/labCharge/addLabReport4Bus.action?labReportVo.editType=1&labReportVo.path=${labChargeVo.reportPath}&labReportVo.busInsId=${labChargeVo.id}&labReportVo.id='+tempId+'&labReportVo.isNew='+flag;
	$.dialog({
		id:'id',
		content:'url:'+url,
		title:'打印页面',
		opacity:0.4,
		width:1050,
		height:height,
		max:false,
		min:false,
		lock:true
	 });
}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" value="${labChargeVo.id}" name="labChargeVo.id"
				id="id" />
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
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
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
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（显示） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
												<label style="color: red;">
													<font>（<s:text name="feeunit"/>：：${labChargeVo.code }）</font>
												</label>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<s:text name="chargeoffice"/>：
													</td>
													<td colspan="3">
														${labChargeVo.paymentUnit }
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="moneytax"/>：
													</td>
													<td colspan="3">
														${labChargeVo.invoiceNo }
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="expname"/>：
													</td>
													<td colspan="3">
														${labChargeVo.fastName }
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="sendtime"/>：
													</td>
													<td colspan="3">
														${labChargeVo.sendTime}
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="feeinfo"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<s:text name="chargeitem"/>：
													</td>
													<td>
														${labChargeVo.payName }
													</td>
													<td>
														<s:text name="totalmoneyed"/>：
													</td>
													<td>
														${labChargeVo.totalMoney }（<s:text name="theme.yuan"/>）
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="dised"/>：
													</td>
													<td>
														${labChargeVo.discount }（%）
													</td>
													<td>
														<s:text name="youhuied"/>：
													</td>
													<td>
														${labChargeVo.preferential }
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="taxed"/>：
													</td>
													<td>
														${labChargeVo.tax }
													</td>
													<td>
														<s:text name="taxbiz"/>：
													</td>
													<td>
														${labChargeVo.taxMoney }（<s:text name="theme.yuan"/>）
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="actmoney"/>：
													</td>
													<td>
														${labChargeVo.payMoney }（<s:text name="theme.yuan"/>）
													</td>
													<td>
														<s:text name="paymoney"/>：
													</td>
													<td>
														${labChargeVo.yiMoney }（<s:text name="theme.yuan"/>）
													</td>

												</tr>
												<tr>
													<td>
														<s:text name="paytype"/>：
													</td>
													<td>
														${labChargeVo.payType}
													</td>
													<td>
														<s:text name="moneycontent"/>：
													</td>
													<td>
														<textarea name="labChargeVo.payInfo" readonly="readonly"
															rows="3" cols="30">${labChargeVo.payInfo }</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="chargunit"/>：
													</td>
													<td colspan="3">
														${labChargeVo.collectionUnit }
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="prj.manager"/>：
													</td>
													<td>
														${labChargeVo.collectionName}
													</td>
													<td>
														<s:text name="chargedate"/>：
													</td>
													<td>
														${labChargeVo.collectionTime}
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable" id="stone">
											<div class="Formtabletitle">
												<span><s:text name="report.preview"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td width="100">
														<label>
															<s:text name="report.info"/>：
														</label>
													</td>
													<td width="100">
														<s:select list="#request.tempList" id="tempId"
															listKey="id" listValue="title" theme="simple"
															name="labChargeVo.reportTempId"></s:select>
													</td>
													<td>
														<a id="reportPatha" class="zPushBtn"
															href="javascript:void(0);"
															onclick="showReport('');"><img
																height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="reportprint"/></b>
														</a>
														<s:if test="${fn:length(labChargeVo.reportPath)>0}">
															<a class="zPushBtn"
																href="javascript:void(0);"
																onclick="if(confirm('重新生成将会重置所有样式，确认？')){showReport('1');}"><img
																	height="20" width="20" src="${basePath}/img/dayin.gif" /><b><s:text name="re.generate"/></b>
															</a>
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（显示） 结束 -->
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
	</body>
	<script></script>
</html>
