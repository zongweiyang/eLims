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
			if(confirm('<s:property value="getText('confirm.submit')"/>')){
				$('form').submit();
			}
		}
		function submit2Back(actionstr){
			var html = $('#auditMessage').html();
			html=html.replace(/(^\s*)|(\s*$)/g,'');
			if(html==''){
				alert('<s:property value="getText('input.audited')"/>');
				return false;
			}
			submitvalue(actionstr);
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
								<span><s:text name="flow.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="mouldForm">
							<input name="labSampRegisterVo.id" value="${labSampRegisterVo.id}" type="hidden"/>
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
															<l:a uri="sample/labSampRegister/updateLabSampRegister4Approve.action" onclick="submitvalue('sample/labSampRegister/updateLabSampRegister4Approve.action?labSampRegisterVo.auditResult=1');return false;" value="flow.pass" img="/img/icon/filesave.gif"/>
														</td>
														<td>
															<l:a uri="sample/labSampRegister/updateLabSampRegister4Approve.action" onclick="submit2Back('sample/labSampRegister/updateLabSampRegister4Approve.action?labSampRegisterVo.auditResult=-1');return false;" value="flow.return" img="/img/icon/filesave.gif"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="base.info"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon">
											<tr>
												<td width="100">
													<label>
														<s:if test="${fn:length(labSampRegisterVo.otherId)>0}">
														<s:text name="lecture.item"/>：
														</s:if>
														<s:else>
														<s:text name="cust.name"/>：
														</s:else>
													</label>
												</td>
												<td width="400">
													${labSampRegisterVo.labSampCustomerVo.labCustomerName}
												</td>
												<td width="100">
													<label>
														<s:text name="org.people"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.labSampCustomerVo.user}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="theme.phone"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.labSampCustomerVo.telephone}
												</td>
												<td>
													<label>
														Email：
													</label>
												</td>
												<td>
													${labSampRegisterVo.labSampCustomerVo.email}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="fax.large"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.labSampCustomerVo.fax}
												</td>
												<td>
													<label>
														<s:text name="postcode.large"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.labSampCustomerVo.zipCode}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="address.large"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea disabled="disabled" rows="2" cols="80" style="overflow: hidden;" name="labSampRegisterVo.labSampCustomerVo.address" id="address">${labSampRegisterVo.labSampCustomerVo.address}</textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="sam.info.checking"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div style="display: none;">
										<table class="FormtableCon">
											<tr>
												<td width="100">
													<label>
														<s:text name="sam.no"/>：
													</label>
												</td>
												<td width="400">
													${labSampRegisterVo.sampNo}
												</td>
												<td width="100">
													<label>
														<s:text name="sam.number"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.sampNum}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="sam.source"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.sampSource}
												</td>
												<td>
													<label>
														<s:text name="package.style"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.sampPack}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="send.sam.people"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.sampUser}
												</td>
												<td>
													<label>
														<s:text name="send.sam.date"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.sampDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="saved.site"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.saveOrg}
												</td>
												<td>
													<label>
														<s:text name="saved.people"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.saveUser}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="sam.desc"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea disabled="disabled" style="overflow: hidden;" rows="2" cols="80" name="labSampRegisterVo.sampDesc" id="sampDesc">${labSampRegisterVo.sampDesc}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="received.people"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.acceptUser}
												</td>
												<td>
													<label>
														<s:text name="accpet.date"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.createDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="checking.type"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.taskType}
												</td>
												<td>
													<label>
														<s:text name="report.performance"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.reportType}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="report.sum"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.reportNum}
												</td>
												<td>
													<label>
														<s:text name="comp.report.date"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.finishDate}
												</td>
											</tr>
											<tr>
												<td>
													<label>
														<s:text name="get.report.style"/>：
													</label>
												</td>
												<td >
													<s:if test="${labSampRegisterVo.reportMake=='1'}">
													<s:text name="by.task"/>
													</s:if>
													<s:else>
													<s:text name="by.sam"/>
													</s:else>
												</td>
												<td>
													<label>
														<s:text name="get.report.style"/>：
													</label>
												</td>
												<td>
													${labSampRegisterVo.reportPost}
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
												<s:text name="checking.content"/>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div style="display: none;">
										<table class="FormtableCon_sform">
											<tr>
												<th width="100">
													<s:text name="batch.no"/>
												</th>
												<th width="100">
													<s:text name="origin.number"/>
												</th>
												<th width="190">
													<s:text name="sam.name"/>
												</th>
												<th width="110">
													<s:text name="sam.type"/>
												</th>
												<th>
													<s:text name="checking.item"/>
												</th>
											</tr>
											<s:iterator value="sampList" status="st">
												<tr>
													<td class="c">
														${sampCode}
													</td>
													<td class="c">
														${oldNo}
													</td>
													<td class="c">
														${name}
													</td>
													<td class="c">
														${samTypeName}
													</td>
													<td class="c">
														${itemName}
													</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="charging.content"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div style="display: none;">
										<table class="FormtableCon">
											<tr>
												<td width="">
													<label>
														<s:text name="checking.fee"/>：
													</label>
												</td>
												<td>
													<s:radio disabled="true" list="#{'1':getText('buitemcount'),'2':getText('bytemi'),'3':getText('by.sam')}" name="labSampRegisterVo.chargeType" theme="simple" ></s:radio>
												</td>
												<td >
													<label>
														<s:text name="other.fee"/>：
													</label>
												</td>
												<td >
												</td>
											</tr>
											<tr>
												<s:set name="count" value="0"/>
												<td colspan="2" style=" vertical-align:text-top;">
													<table class="FormtableCon_sform" id="itemTable">
														<tr>
															<td class="c">
																<s:if test="${labSampRegisterVo.chargeType=='3'}">
																	<s:text name="sam.name"/>
																</s:if>
																<s:else>
																	<s:text name="item.name"/>
																</s:else>
															</td>
															<td class="c" width="100">
																<s:text name="unit.yuan"/>
															</td>
															<td class="c" width="100">
																<s:text name="amount"/>
															</td>
															<td class="c" width="100">
																<s:text name="total"/>
															</td>
														</tr>
														<s:set name="count1" value="0"/>
														<s:iterator value="labChargeVo.chargeList" id="" status="st">
															<s:set name="count" value="${st.index+1}"/>
															<tr>
																<td class="l">
																	 ${payName}
																	 <input type="hidden" name="labChargeVo.chargeList[${st.index}].payName" value="${payName}"/>
																</td>
																<td class="r">
																	${price}
																	<input type="hidden" name="labChargeVo.chargeList[${st.index}].price" value="${price}" style="text-align: right;background-color: #DDDDDD;" readonly="readonly"/>
																</td>
																<td class="r">
																	${num}
																	 <input type="hidden" name="labChargeVo.chargeList[${st.index}].num" value="${num}" readonly="readonly" style="text-align: right;background-color: #DDDDDD;"/>
																</td>
																<td class="r">
																 	<input type="text" name="labChargeVo.chargeList[${st.index}].payMoney" id="payMoney${st.index}" value="${num*price}" readonly="readonly" style="text-align: right;background-color: #DDDDDD;"/>
																	<s:set name="count1" value="#count1+${num*price}"/>
																</td>
															</tr>
														</s:iterator>
														<tr>
															<td colspan="3" class="r"><s:text name="totaled"/>：
															</td>
															<td>
																<input type="text" id="itemTotal" value="${count1}" style="width: 100%;text-align: right;background-color: #DDDDDD;" readonly="readonly"/>
															</td>
														</tr>
													</table>
												</td>
												<td colspan="2" style=" vertical-align:text-top;">
													<table class="FormtableCon_sform" id="otherTable">
														<tr>
															<td class="c">
																<s:text name="charging.content"/>
															</td>
															<td class="c" width="100">
															<s:text name="unit.yuan"/>
															</td>
														</tr>
														<s:iterator value="labChargeVo.qtList" id="" status="st">
															<s:set name="count" value="#count+${st.index}"/>
															<tr>
																<td class="l">
																	 ${payName}
																	 <input type="hidden" name="labChargeVo.chargeList[${count}].payName" value=" ${payName}"/>
																</td>
																<td class="r">
																	${price}
																	<input type="hidden" name="labChargeVo.chargeList[${count}].price" id="price${count}" value="${price}" size="10" style="width: 100%;text-align: right;" onblur="countRowMoneyx(this);"/>
																</td>
															</tr>
														</s:iterator>
														<tr>
															<td class="r"><s:text name="totaled"/>：
															</td>
															<td>
																<input type="text" id="otherTotal" style="width: 100%;text-align: right;background-color: #DDDDDD;" readonly="readonly"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<table class="FormtableCon_sform">
														<tr>
															<td width="100">
																<label><s:text name="total.fee"/>：</label>
															</td>
															<td class="r">${labChargeVo.totalMoney}(元)</td>
															<td width="100">
																<label><s:text name="discount"/>：</label>
															</td>
															<td class="r">
																${labChargeVo.discount}%</td>
															<td>
																<label><s:text name="discount.fee"/>：</label>
															</td>
															<td class="r">
																${labChargeVo.preferential}(<s:text name="theme.yuan"/>)
															</td>
															<td>
																<label><s:text name="total.payed"/>：</label>
															</td>
															<td class="r">
																${labChargeVo.payMoney}(<s:text name="theme.yuan"/>)
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="audited.info"/></span>
									</div>
									<div>
										<table class="FormtableCon_sform">
											<tr>
												<td>
													<label><s:text name="sudited.sound"/>：</label>
												</td>
												<td>
													<textarea rows="4" cols="60" name="labSampRegisterVo.auditMessage" id="auditMessage">${labSampRegisterVo.auditMessage}</textarea>
												</td>
											</tr>
										</table>
									</div>
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
			function countTotalx(){
				var iv=parseFloat($('#itemTotal').val());
				if(isNaN(iv)){
					iv=0;
				}
				var ov=parseFloat($('#otherTotal').val());
				if(isNaN(ov)){
					ov=0;
				}
				var zk=parseFloat($('#discount').val());
				if(isNaN(zk)){
					zk=100;
				}
				var yh=parseFloat($('#preferential').val());
				if(isNaN(yh)){
					yh=0;
				}
				$('#totalMoney').html(iv+ov);
				$('#payMoney').val((iv+ov)*zk/100-yh);
			}
			
			var totals=0;
			$('#otherTable input[id*="price"]').each(function(){
				totals+=parseFloat($(this).val());
			});
			$('#otherTotal').val(totals);
			
			countTotalx();
		});
	</script>
</html>
