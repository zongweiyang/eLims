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
<script>
function submitValue(url){
	var reprotStr="";
	$('input[name="labSampRegisterVo.reportPost"]').each(function(){
		if($(this).prop('checked')&&$(this).val()=="快递"&&($('#zipCode').val()==""||$('#address').html()=="")){
			reprotStr="1";
		}else if($(this).prop('checked')&&$(this).val()=="传真"&&$('#fax').val()==""){
			reprotStr="2";
		}else if($(this).prop('checked')&&$(this).val()=="Email"&&$('#email').val()==""){
			reprotStr="3";
		}
	});
	if(reprotStr =="1"){
		alert("取报告方式含有快递，请务必填写邮编和地址.");
		return false;
	}else if(reprotStr =="2"){
		alert("取报告方式含有传真类型，请务必填写传真号.");
		return false;
	}else if(reprotStr =="3"){
		alert("取报告方式含有email类型，请务必填写email.");
		return false;
	}
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
}
function submitValuex(url){
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
}
function ajax4labSampItem(){
	var msg='';
	var id='${labSampRegisterVo.id}';
	if(id==''){
		msg='请填写检测内容后再进行保存.';
		return msg;
	}
	var uri='sample/labSampRegister/ajaxLabSampItem4Exsit.action';
	$.ajax({
   	  url:'<%=basePath%>'+uri,
   	  data:{'labSampRegisterVo.id':id},
   	  type:'post',
	  dataType:'text',
	  async:false,
   	  success:function (data){
   	  	if(data!="true"){
           msg='请填写检测内容后再进行保存.';
        }
   	  },
   	  error:function (data){
   	  	alert('请求错误.');
   	  }
   });
	return msg;
}
function showLabCustomer4Select(){
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath %>sample/labSampRegister/showLabCustomer4Select.action',
		title:'客户列表',
		opacity:0.4,
		width:800, 
		height:500,
		lock:true,
		max:false,
		min:false
	 });
}
function showRegister4Confirm(){
	var reprotStr="";
	$('input[name="labSampRegisterVo.reportPost"]').each(function(){
		if($(this).prop('checked')&&$(this).val()=="快递"&&($('#zipCode').val()==""||$('#address').html()=="")){
			reprotStr="1";
		}else if($(this).prop('checked')&&$(this).val()=="传真"&&$('#fax').val()==""){
			reprotStr="2";
		}else if($(this).prop('checked')&&$(this).val()=="Email"&&$('#email').val()==""){
			reprotStr="3";
		}
	});
	if(reprotStr =="1"){
		alert("取报告方式含有快递，请务必填写邮编和地址.");
		return false;
	}else if(reprotStr =="2"){
		alert("取报告方式含有传真类型，请务必填写传真号.");
		return false;
	}else if(reprotStr =="3"){
		alert("取报告方式含有email类型，请务必填写email.");
		return false;
	}
	var msg=ajax4labSampItem();
	if(msg!=''){
		validate.tip(msg,$('#labCustomerName'));
		return false;
	}
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath %>sample/labSampRegister/showLabSampRegister4Confirm.action?labSampRegisterVo.id=${labSampRegisterVo.id}',
		title:'登记单信息',
		opacity:0.4,
		width:1000, 
		height:600,
		lock:true,
		max:false,
		min:false
	 });
}
function checkContact(obj){
	var vals=$(obj).val();
	var vs=vals.split('|');
	if(vs.length==9){
		$('#contractId').val(vs[0]);
		$('#labCustomerId').val(vs[1]);
		$('#labCustomerName').val(vs[2]);
		$('#user').val(vs[3]);
		$('#telephone').val(vs[4]);
		$('#email').val(vs[5]);
		$('#fax').val(vs[6]);
		$('#zipCode').val(vs[7]);
		$('#address').val(vs[8]);
	}else{
		$('#contractId').val('');
		$('#labCustomerId').val('');
		$('#labCustomerName').val('');
		$('#user').val('');
		$('#telephone').val('');
		$('#email').val('');
		$('#fax').val('');
		$('#zipCode').val('');
		$('#address').val('');
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
									<form action="" method="post" name="studentForm">
										<input type="hidden" name="labSampRegisterVo.uuid" value="${labSampRegisterVo.uuid}"/>
										<input type="hidden" name="labSampRegisterVo.id" value="${labSampRegisterVo.id}"/>
										<input type="hidden" name="labSampRegisterVo.otherType" value="${labSampRegisterVo.otherType}"/>
										<input type="hidden" name="labSampRegisterVo.labSampCustomerVo.id" value="${labSampRegisterVo.labSampCustomerVo.id}"/>
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
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab1.action" onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.auditResult=tab1');" value="lab.code.save"  img="/img/add.gif"/>
																</td>
																<td>
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab1.action" onclick="showRegister4Confirm();return false;" value="save.commit"  img="/img/add.gif"/>
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
													<li id="li01" class="currenttab">
														<a href="javascript:;" ><span><s:text name="base.info"/></span> </a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.auditResult=tab2');"><span><s:text name="checking.content"></s:text></span> </a>
													</li>
													<li id="li03" class="">
														<a href="javascript:;" onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.auditResult=tab3');"><span><s:text name="checking.fee"></s:text></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="base.info"/></span>
														<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
													</div>
													<div>
															<table class="FormtableCon">
																<tr>
																	<td width="100">
																		<label>
																			<s:if test="${fn:length(labSampRegisterVo.otherId)>0}">
																			<s:text name="lecture.item "/>：
																			</s:if>
																			<s:else>
																			<s:text name="cust.name"/>：
																			</s:else>
																		</label>
																	</td>
																	<td width="400">
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.labCustomerName" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.labCustomerName}" id="labCustomerName" size="40" valType="required" msg="客户名称不能为空！"/>
																		<input type="hidden" name="labSampRegisterVo.labSampCustomerVo.labCustomerId" value="${labSampRegisterVo.labSampCustomerVo.labCustomerId}" id="labCustomerId" />
																		<a href="javascript:;" onclick="showLabCustomer4Select()"><s:text name="selected"></s:text></a>
																	</td>
																	<td width="100">
																		<label>
																			<s:text name="org.people"/>：
																		</label>
																	</td>
																	<td>
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.user" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.user}" id="user" size="40" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<label>
																			<s:text name="theme.phone"/>：
																		</label>
																	</td>
																	<td>
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.telephone" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.telephone}" id="telephone" size="40"  valType="phone"  phone-msg="电话格式不准确"/>
																	</td>
																	<td>
																		<label>
																			Email：
																		</label>
																	</td>
																	<td>
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.email" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.email}" id="email" size="40" valType="email" email-msg="邮箱格式不准确，例<s:text name="ifed"/>：xxx@xx.xxx"/>
																	</td>
																</tr>
																<tr>
																	<td>
																		<label>
																			<s:text name="fax.large"/>：
																		</label>
																	</td>
																	<td>
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.fax" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.fax}" id="fax" size="40"  valType="fax" fax-msg="传真格式不准确，例<s:text name="ifed"/>：xxx-xxxxxxxx"/>
																	</td>
																	<td>
																		<label>
																			<s:text name="postcode.large"/>：
																		</label>
																	</td>
																	<td>
																		<input type="text" name="labSampRegisterVo.labSampCustomerVo.zipCode" readonly="readonly" value="${labSampRegisterVo.labSampCustomerVo.zipCode}" id="zipCode" size="40" valType="zipCode" msg="邮编格式不正确！" />
																	</td>
																</tr>
																<tr>
																	<td>
																		<label>
																			<s:text name="address.large"/>：
																		</label>
																	</td>
																	<td colspan="3">
																		<textarea rows="2" cols="80" name="labSampRegisterVo.labSampCustomerVo.address" readonly="readonly" id="address">${labSampRegisterVo.labSampCustomerVo.address}</textarea>
																	</td>
																</tr>
															</table>
													</div>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="sam.info"/></span>
														<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
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
																	<input type="text" name="labSampRegisterVo.sampNo" value="${labSampRegisterVo.sampNo}" id="sampNo" size="40" readonly="readonly" style="background-color: #DDDDDD"/>
																</td>
																<td>
																	<label>
																		<s:text name="sam.number"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.sampNum" value="${labSampRegisterVo.sampNum}" id="sampNum" size="40"  valType="required,number" msg="样品数量为空或输入不正确！" />
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="sam.source"/>：
																	</label>
																</td>
																<td>
																	<s:radio theme="simple" list="{'自送样','现场采样'}" value="'自送样'" name="labSampRegisterVo.sampSource"></s:radio>
																</td>
																<td>
																	<label>
																		<s:text name="package.style"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.BZFSlist" listKey="name" listValue="name" theme="simple"  cssStyle="width:270px;" name="labSampRegisterVo.sampPack" value="'${labSampRegisterVo.sampPack}'"></s:select>
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="send.sam.people"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.sampUser" value="${labSampRegisterVo.sampUser}" id="sampUser" size="40" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.send.date"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.sampDate" value="${labSampRegisterVo.sampDate}" id="sampDate" size="40" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
																</td>
															</tr>
															<tr>
																<td><label><s:text name="saved.site"/>：</label></td>
																<td><input type="text" name="labSampRegisterVo.saveOrg" value="${labSampRegisterVo.saveOrg}" id="saveOrg" size="40"  /></td>
																<td><label><s:text name="saved.people"/>：</label></td>
																<td><input type="text" name="labSampRegisterVo.saveUser" value="${labSampRegisterVo.saveUser}" id="saveUser" size="40"  /></td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="sam.desc"/>：
																	</label>
																</td>
																<td colspan="3">
																	<textarea rows="2" cols="80" name="labSampRegisterVo.sampDesc" id="sampDesc">${labSampRegisterVo.sampDesc}</textarea>
																</td>
															</tr>
															<tr>
																<td><label><s:text name="received.people"/>：</label></td>
																<td>
																	<input type="text" name="labSampRegisterVo.acceptUser" value="${labSampRegisterVo.acceptUser}" id="acceptUser" size="40"/>
																</td>
																<td><label><s:text name="accpet.date"/>：</label></td>
																<td><input type="text" name="labSampRegisterVo.createDate" value="${labSampRegisterVo.createDate}" id="createDate" size="40" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
															</tr>
															<tr>
																<td><label><s:text name="checking.type"/>：</label></td>
																<td><s:select list="#request.JCLBlist" listKey="name" listValue="name" theme="simple"  cssStyle="width:270px;" name="labSampRegisterVo.taskType"></s:select></td>
																<td><label><s:text name="report.performance"/>：</label></td>
																<td><s:radio list="{'检测','检验'}" theme="simple" name="labSampRegisterVo.reportType" /></td>
															</tr>
															<tr>
																<td><label><s:text name="report.sum"/>：</label></td>
																<td><input type="text" name="labSampRegisterVo.reportNum" value="${labSampRegisterVo.reportNum}" id="reportNum" size="40" valType="number" msg="数字格式不正确！"/></td>
																<td><label><s:text name="comp.report.date"/>：</label></td>
																<td><input type="text" name="labSampRegisterVo.finishDate" id="finishDate" value="${labSampRegisterVo.finishDate}" size="40" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/></td>
																
															</tr>
															<tr>	
																<td><label><s:text name="out.report.style"/>：</label></td>
																<td>
																	<s:radio theme="simple" list="#{'1':getText('by.task'),'2':getText('by.sam')}" name="labSampRegisterVo.reportMake" value="${labSampRegisterVo.reportMake}"></s:radio>
																</td>
																<td><label><s:text name="get.report.style"/>：</label></td>
																<td id="reportPost">
																	<s:checkboxlist theme="simple" list="{'自取','快递','传真','Email'}" name="labSampRegisterVo.reportPost"></s:checkboxlist>
																</td>
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
		var reprotStr='${labSampRegisterVo.reportPost}';
		var postStr=reprotStr.split(',');
		$('#reportPost').find('input[type="checkbox"]').each(function(){ 
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
