<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labChargeForm" id="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="admin.add"/></span>
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
																	<l:a uri="back" value="msg.back"/> 
																</td>
																<td>
																	<l:a uri="charge/labCharge/addLabCharge.action" onclick="goAction('addLabCharge.action');" value="lab.code.save"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<s:text name="chargeoffice"/>：
													</td>
													<td colspan="3">
														<input name="labChargeVo.paymentUnit" valType="required"
															size="50"  msg="付款单位不能为空" type="text" value="${labChargeVo.paymentUnit }" />
													</td>
													<td>
														<s:text name="moneyno"/>：
													</td>
													<td colspan="3">
														<input name="labChargeVo.invoiceNo" valType="required" msg="发票号不能为空"
															size="50" type="text" value="${labChargeVo.invoiceNo }" />
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="expname"/>：
													</td>
													<td colspan="3">
														<input name="labChargeVo.fastName" size="50" type="text"
															value="${labChargeVo.fastName }" />
													</td>
													<td>
														<s:text name="sendtime"/>：
													</td>
													<td colspan="3">
														<input readonly="readonly" type="text"
															name="labChargeVo.sendTime" id="sendTime"
															value="${labChargeVo.sendTime}" class="Wdate" size="50"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,alwaysUseStartDate:true});" />
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
														<input type="text" name="labChargeVo.payName"  valType="required" msg="收费项目不能为空"
															value="${labChargeVo.payName}" id="payName"
															size="50" />
													</td>
													<td>
														<s:text name="totalmonty"/>：
													</td>
													<td>
														<input name="labChargeVo.totalMoney" id="totalMoney"
															value="${labChargeVo.totalMoney }"
															 valType="number" size="50" type="text" />
														（<s:text name="theme.yuan"/>）
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="discounted"/>：
													</td>
													<td>
														<input type="text" name="labChargeVo.discount" value="100"
															 valType="number" id="discount" size="50" />
														（%）
													</td>
													<td>
														<s:text name="youhiu"/>：
													</td>
													<td>
														<input name="labChargeVo.preferential" id="preferential"
															 valType="number" value="${labChargeVo.preferential }" size="50"
															type="text" />
															（<s:text name="theme.yuan"/>）
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="actmoney"/>：
													</td>
													<td>
														<input name="labChargeVo.payMoney" id="payMoney" value="${labChargeVo.payMoney }" valType="number"  size="50" type="text" />（<s:text name="theme.yuan"/>）
													</td>
													<td>
														<s:text name="paymoney"/>：
													</td>
													<td>
														<input name="labChargeVo.yiMoney" id="yiMoney" valType="number"
															value="${labChargeVo.yiMoney }" size="50" type="text" />（<s:text name="theme.yuan"/>）
													</td>
													
												</tr>
												<tr>
													<td>
														<s:text name="taxratio"/>：
													</td>
													<td>
														<input type="text" name="labChargeVo.tax"
														 valType="number" value="${labChargeVo.tax }" id="tax" size="50" />（%）
													</td>
													<td>
														<s:text name="paytaxtotal"/>：
													</td>
													<td>
														<input type="text" name="labChargeVo.taxMoney" valType="number"
															value="${labChargeVo.taxMoney }" id="taxMoney" size="50" />（<s:text name="theme.yuan"/>）
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="paytype"/>：
													</td>
													<td>
														<s:select cssStyle="border: 0;width:200px;"
															name="labChargeVo.payType" list="#request.payTypeList"
															value="%{labChargeVo.payType}" theme="simple"
															listKey="name" listValue="name" />
													</td>
													<td>
														<s:text name="moneycontent"/>：
													</td>
													<td>
														<textarea name="labChargeVo.payInfo" valType="strLength"
															max="512" strLength-msg='<s:property value="getText('remakrnot512')"/>' cols="30" rows="3"
															label="备注">${labChargeVo.payInfo}</textarea>
													</td>
												</tr>
												<tr>
													
												</tr>
												<tr>
													<td>
														<s:text name="chargunit"/>：
													</td>
													<td colspan="3">
														<input name="labChargeVo.collectionUnit"
															value="${labChargeVo.collectionUnit }" size="50"
															type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="peomain"/>：
													</td>
													<td>
														<input name="labChargeVo.collectionName"
															value="${labChargeVo.collectionName}" size="50"
															type="text" />
													</td>
													<td>
														<s:text name="chargedate"/>：
													</td>
													<td>
														<input readonly="readonly" type="text"
															name="labChargeVo.collectionTime" size="50"
															id="collectionTime" value="${labChargeVo.collectionTime}"
															class="Wdate"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,alwaysUseStartDate:true});" />
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 结束 -->
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script>
	$(function(){$('#taxMoney').click(function(){formula('4028812646b7d0050146b8039655002f');});});
			$(function(){$('#payMoney').click(function(){formula('4028812646b7d0050146b8020497002d');});});
			
	</script>
</html>
