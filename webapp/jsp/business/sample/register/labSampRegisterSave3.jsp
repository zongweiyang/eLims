<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
<Script>
function showRegister4Confirm(){
	var msg=ajax4labSampItem();
	if(msg!=''){
		validate.tip(msg,$('#labCustomerName'));
		return false;
	}
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath %>sample/labSampRegister/showLabSampRegister4Confirm.action?labSampRegisterVo.id=${labSampRegisterVo.id}',
		title:'<s:property value="getText('reglistbill')"/>',
		opacity:0.4,
		width:1000, 
		height:600,
		lock:true,
		max:false,
		min:false
	 });
}
function submitValuex(url){
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
}
function ajax4labSampItem(){
	var msg='';
	var id='${labSampRegisterVo.id}';
	if(id==''){
		msg='请填写并保存检测内容后再进行提交.';
		return msg;
	}
	var uri='sample/labSampRegister/ajaxLabSampItem4Exsit.action';
	$.ajax({
   	  url:'<%=basePath%>'+uri,
   	  data:{'labSampRegisterVo.id':'${labSampRegisterVo.id}'},
   	  type:'post',
	  dataType:'text',
	  async:false,
   	  success:function (data){
   	  	if(data!="true"){
           msg='请填写检测内容后再进行保存.';
        }
   	  },
   	  error:function (data){
   	  	alert('<s:property value="getText('config.request.error')"/>');
   	  }
   });
	return msg;
}
function chargeTypes(obj){
	var vs=$(obj).val();
	window.location.href='${basePath}/sample/labSampRegister/preAddLabSampRegister4Tab3.action?labSampRegisterVo.id=${labSampRegisterVo.id}&labSampRegisterVo.chargeType='+vs;
}
function countRowMoney(obj,index){
	var thisVal=parseFloat($(obj).val());
	if(isNaN(thisVal)){
		alert('<s:property value="getText('formatisnot')"/>');
		$(obj).val(0);
		return false;
	}
	var price=parseFloat($('input[name="labChargeVo.chargeList['+index+'].price"]').val());
	var num=parseInt($('input[name="labChargeVo.chargeList['+index+'].num"]').val());
	$('input[name="labChargeVo.chargeList['+index+'].payMoney"]').val(price*num);
	var totals=0;
	$('#itemTable input[id*="payMoney"]').each(function(){
		totals+=parseFloat($(this).val());
	});
	$('#itemTotal').val(totals);
	countTotalx();
}
function countRowMoneyx(obj){
	var vs=parseFloat($(obj).val());
	if(isNaN(vs)){
		alert('<s:property value="getText('moneyformatieoor')"/>');
		$(obj).val(0.0);
		return false;
	}
	var totals=0;
	$('#otherTable input[id*="price"]').each(function(){
		totals+=parseFloat($(this).val());
	});
	$('#otherTotal').val(totals);
	countTotalx();
}
function countTotal(obj){
	var vs=parseFloat($(obj).val());
	if(isNaN(vs)){
		alert('<s:property value="getText('moneyformatieoor')"/>');
		$(obj).val(0.0);
		return false;
	}
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
	$('#totalMoney').val(iv+ov);
	$('#payMoney').val((iv+ov)*zk/100-yh);
}
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
	$('#totalMoney').val(iv+ov);
	$('#payMoney').val((iv+ov)*zk/100-yh);
}
function changeThisValue(obj){
	if($(obj).prop('checked')){
		$(obj).val('Y');
	}else{
		$(obj).val('N');
	}
}
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td>
								<div class="myworkingbox">
									<div class="myworkingboxttitle">
										<h2>
											${funName}：
											<span><s:text name="lab.code.add"/></span>
										</h2>
									</div>
									<form action="" method="post" name="registerForm">
										<input type="hidden" name="labSampRegisterVo.id" value="${labSampRegisterVo.id}"/>
										<input type="hidden" name="labChargeVo.code" value="${labChargeVo.code}"/>
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
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab3.action" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab3.action?labSampRegisterVo.auditResult=tab3');" value="lab.code.save"  img="/img/icon/filesave.gif"/>
																</td>
																<td>
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab3.action" onclick="showRegister4Confirm();return false;" value="save.commit"  img="/img/icon/filesave.gif"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="">
														<a href="javascript:;" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab3.action?labSampRegisterVo.auditResult=tab1');"><span><s:text name="base.info"/></span> </a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab3.action?labSampRegisterVo.auditResult=tab2');"><span><s:text name="checking.content"/></span> </a>
													</li>
													<li id="li03" class="currenttab">
														<a href="javascript:;"><span><s:text name="checking.fee"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="charging.content"/></span>
														&nbsp;&nbsp;
														<label><input type="checkbox" name="labSampRegisterVo.isCharge" value="${labSampRegisterVo.isCharge}" <s:if test="${labSampRegisterVo.isCharge=='Y'}">checked="checked"</s:if> onclick="changeThisValue(this);"/>&nbsp;<s:text name="generate.fee"></s:text>： <font color="red">${labChargeVo.code}</font></label>
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
																	<s:radio list="#{'1':getText('bytemi'),'2':getText('by.sam'),'3':getText('bymethod')}" name="labSampRegisterVo.chargeType" theme="simple" onclick="chargeTypes(this);"></s:radio>
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
																				<s:if test="${labSampRegisterVo.chargeType=='2'}">
																					<s:text name="sam.name"/>
																				</s:if>
																				<s:elseif test="${labSampRegisterVo.chargeType=='3'}">
																					<s:text name="method.name"/>
																				</s:elseif>
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
																					<input type="text" name="labChargeVo.chargeList[${st.index}].price" value="${price}" onblur="countRowMoney(this,'${st.index}');" style="text-align: right;"/>
																				</td>
																				<td class="r">
																					 <input type="text" name="labChargeVo.chargeList[${st.index}].num" value="${num}"  onblur="countRowMoney(this,'${st.index}');"/>
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
																				<s:text name="charge.con"/>
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
																					 <input type="hidden" name="labChargeVo.qtList[${count}].payName" value=" ${payName}"/>
																				</td>
																				<td class="r">
																					<input type="text" name="labChargeVo.qtList[${count}].price" id="price${count}" value="${price}" size="10" style="width: 100%;text-align: right;" onblur="countRowMoneyx(this);"/>
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
														</table>
													</div>
													<div class="Formtabletitle">
														<span><s:text name="fee.details"/></span>
													</div>
													<div>
														<table class="FormtableCon">
															<tr>
																<td width="100">
																	<label><s:text name="total.fee"/>：</label>
																</td>
																<td><input type="text" name="labChargeVo.totalMoney" id="totalMoney" value="" readonly="readonly" style="text-align: right;background-color: #DDDDDD;"/>(元)</td>
																<td width="100">
																	<label><s:text name="discount"/>：</label>
																</td>
																<td><input type="text" name="labChargeVo.discount" id="discount" value="${labChargeVo.discount}" style="text-align: right;" onblur="countTotal(this)"/>%</td>
															</tr>
															<tr>
																<td>
																	<label><s:text name="discount.fee"/>：</label>
																</td>
																<td><input type="text" name="labChargeVo.preferential" id="preferential" value="${labChargeVo.preferential}" style="text-align: right;" onblur="countTotal(this)"/>(元)</td>
																<td>
																	<label><s:text name="total.payed"/>：</label>
																</td>
																<td><input type="text" name="labChargeVo.payMoney" id="payMoney" value="" readonly="readonly" style="text-align: right;background-color: #DDDDDD;"/>(元)</td>
															</tr>
														</table>
													</div>
												</div>
											</div>
										</div>
									</form>
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
	</body>
	<script>
		$(function(){
			var totals=0;
			$('#otherTable input[id*="price"]').each(function(){
				totals+=parseFloat($(this).val());
			});
			$('#otherTotal').val(totals);
			
			countTotalx();
		});
	</script>
</html>
