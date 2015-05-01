<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="mouldForm">
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
											[&nbsp; <font color="blue"><s:text name="open.close"/></font>&nbsp;]
											
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
													${labSampRegisterVo.labSampCustomerVo.address}
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
									<div>
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
													<s:radio theme="simple" list="{getText('zisongyang'),getText('xiancaiynag')}" value="'自送样'" name="labSampRegisterVo.sampSource"></s:radio>
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
														<s:text name="sam.send.date"/>：
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
													${labSampRegisterVo.sampDesc}
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
														<s:text name="report.type"/>：
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
										<span><s:text name="checking.content"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
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
									<div>
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
																</td>
																<td class="r">
																	${price}
																</td>
																<td class="r">
																	${num}
																</td>
																<td class="r">
																 	${num*price}
																	<s:set name="count1" value="#count1+${num*price}"/>
																</td>
															</tr>
														</s:iterator>
														<tr>
															<td colspan="3" class="r"><s:text name="totaled"/>：
															</td>
															<td class="r">${count1}
															</td>
														</tr>
													</table>
												</td>
												<td colspan="2" style=" vertical-align:text-top;">
													<table class="FormtableCon_sform" id="otherTable">
														<tr>
															<td class="c">
																<s:text name="charge.con"/>
															</td>
															<td class="c" width="100">
																<s:text name="unit.yuan"/>
															</td>
														</tr>
														<s:set name="count1" value="0"/>
														<s:iterator value="labChargeVo.qtList" id="" status="st">
															<s:set name="count" value="#count+${st.index}"/>
															<tr>
																<td class="l">
																	 ${payName}
																</td>
																<td class="r">
																	${price}
																	<s:set name="count1" value="#count1+${price}"/>
																</td>
															</tr>
														</s:iterator>
														<tr>
															<td class="r"><s:text name="totaled"/>：
															</td>
															<td class="r">${count1}
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
															<td class="r">
																<fmt:formatNumber value="${labChargeVo.totalMoney}" pattern="￥#,##0.0"/>
															</td>
															<td width="100">
																<label><s:text name="discount"/>：</label>
															</td>
															<td class="r">
																${labChargeVo.discount}%</td>
															<td>
																<label><s:text name="discount.fee"/>：</label>
															</td>
															<td class="r">
																<fmt:formatNumber value="${labChargeVo.preferential}" pattern="￥#,##0.0"/>
															</td>
															<td>
																<label><s:text name="total.payed"/>：</label>
															</td>
															<td class="r">
																<fmt:formatNumber value="${labChargeVo.payMoney}" pattern="￥#,##0.0"/>
															</td>
														</tr>
													</table>
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
</html>
