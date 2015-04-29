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
			$(function(){$('#taxMoney').click(function(){formula('4028812646b7d0050146b8039655002f');});});
			$(function(){$('#payMoney').click(function(){formula('4028812646b7d0050146b8020497002d');});});
			function submitforform(){
				$('#form').submit();
			}
			function goToNextAction(url){
				$('#form').attr('action','${basePath}'+url);
				$('#form').submit();
			}
			var object = "${labChargeVo.chargeType}";
			function typeOne (obj){
				object = obj.value;
			}
			function chooseCharge(){
				var url = '<%=basePath %>/jsp/source/charge/chargeChoose.jsp?';
				$.dialog({
					id:'id',
					content:'url:'+url,
					title:object+'信息',
					opacity:0.4,
					width:600, 
					height:400,
					lock:true,
					max:false,
					resize:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labChargeForm" id="form">
			<input type="hidden" value="${labChargeVo.id}" name="labChargeVo.id" id="id" />
			<input type="hidden" value="${labChargeVo.code}" name="labChargeVo.code" id="code" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
												<span><s:text name="charge.fee"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>                     
                                                      				<l:a uri="back" value="msg.back"/> 
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="charge/labCharge/updateLabCharge.action" img="/img/add.gif" onclick="goAction('updateLabCharge.action');" value="lab.code.save"/>
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
												<span><s:text name="base.info"/></span><label style="color: red;"><font>（<s:text name="feeunit"/>：：${labChargeVo.code }）</font></label>
											</div>
											<table class="FormtableCon">
													<tr>
														<td><s:text name="payunit"/>：</td>
														<td colspan="3">
															<input name="labChargeVo.paymentUnit" valType="required" msg="付款单位不能为空"  size="50" type="text" value="${labChargeVo.paymentUnit }" />
														</td>
														<td><s:text name="moneyno"/>：</td>
														<td colspan="3">
															<input name="labChargeVo.invoiceNo" valType="required"  msg="发票号不能为空" size="50" type="text" value="${labChargeVo.invoiceNo }" />
														</td>
													</tr>
													<tr>
													<td><s:text name="expname"/>：</td>
														<td colspan="3">
															<input name="labChargeVo.fastName"  size="50" type="text" value="${labChargeVo.fastName }" />
														</td>
														<td><s:text name="sendtime"/>：</td>
														<td colspan="3">
															<input readonly="readonly" type="text" name="labChargeVo.sendTime" id="sendTime" 
																value="${labChargeVo.sendTime}" class="Wdate"  size="50" 
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,alwaysUseStartDate:true});"
															/>
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
													<td><s:text name="chargeitem"/>：</td>
													<td>
														<input type="text"  name="labChargeVo.payName" value="${labChargeVo.payName }"  valType="required" msg="收费项目不能为空"  id="payName" size="50"/>
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
													<td><s:text name="discounted"/>：</td>
													<td>
														<s:if test="${labChargeVo.discount == 0.0 }">
															<input type="text" valType="number" name="labChargeVo.discount" value="100"  id="discount" size="50"/>（%）
														</s:if>
														<s:else>
															<input type="text" valType="number"  name="labChargeVo.discount" value="${labChargeVo.discount }"  id="discount" size="50"/>（%）
														</s:else>
													</td>
													<td><s:text name="youhiu"/>：</td>
													<td>
														<input valType="number" name="labChargeVo.preferential" id="preferential" value="${labChargeVo.preferential }" size="50"  type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<s:text name="actmoney"/>：
													</td>
													<td>
														<input valType="number" name="labChargeVo.payMoney" id="payMoney"
															value="${labChargeVo.payMoney }"
															 size="50" type="text" />（<s:text name="theme.yuan"/>）
													</td>
													<td>
														<s:text name="paymoney"/>：
													</td>
													<td>
														<input valType="number" name="labChargeVo.yiMoney" id="yiMoney"
															value="${labChargeVo.yiMoney }"
															 size="50" type="text" />（<s:text name="theme.yuan"/>）
													</td>
													
												</tr>
												<tr>
													<td>
														<s:text name="taxratio"/>：
													</td>
													<td>
														<input valType="number" type="text" name="labChargeVo.tax"
															value="${labChargeVo.tax }" id="tax" size="50" />
													</td>
													<td>
														<s:text name="paytaxtotal"/>：
													</td>
													<td>
														<input valType="number" type="text" name="labChargeVo.taxMoney"
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
													<td><s:text name="moneycontent"/>：</td>
													<td>
														<textarea name="labChargeVo.payInfo"  rows="3" cols="30" >${labChargeVo.payInfo }</textarea>
													</td>
												</tr>
												<tr>
													<td><s:text name="chargunit"/>：</td>
													<td colspan="3">
														<input name="labChargeVo.collectionUnit" value="${labChargeVo.collectionUnit }" size="50" type="text" />
													</td>
												</tr>
												<tr>
													<td><s:text name="peomain"/>：</td>
													<td>
														<input name="labChargeVo.collectionName" value="${labChargeVo.collectionName}" size="50"   type="text" />
													</td>
													<td><s:text name="chargedate"/>：</td>
													<td>
														<input readonly="readonly" type="text" name="labChargeVo.collectionTime" size="50"  id="collectionTime" 
															value="${labChargeVo.collectionTime}" class="Wdate" 
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,alwaysUseStartDate:true});"
														/>
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
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
</html>		
