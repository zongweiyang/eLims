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

.Formtable {
	border: none;
}

.FormtableCon input {
	width: 200px;
}

.FormtableCon textarea {
	width: 600px;
}

.FormtableCon select {
	width: 200px;
}
</style>
	</head>
	<script language=javascript> 
		  	 	function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
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
							name="labSupplierForm">
							<input type="hidden" name="labSupplierVo.id"
								value="${labSupplierVo.id}" />
							<div class="TabTable">
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
														<td>
															<l:a uri="supplier/labSupplier/updateLabSupplier.action" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}</span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td width="150">
												<label>
													<s:text name="supplier.code"/>：
												</label>
											</td>
											<td width="450">
												<input type="text" name="labSupplierVo.code" id="code"
													value="${labSupplierVo.code}" readonly="true"
													valType="required" msg="供应商编号不能为空"/>
											</td>
											<td width="150">
												<label>
													<s:text name="supplier.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.name" id="name"
													value="${labSupplierVo.name }" valType="required"  msg="供应商名称不能为空"//>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="company.type"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.companyList"
													name="labSupplierVo.companyType" headerKey=""
													headerValue="" theme="simple" listKey="name"
													listValue="name"></s:select>
											</td>
											<td>
												<label>
													<s:text name="build.time"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate"
													onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
													name="labSupplierVo.bulidDate"
													value="${labSupplierVo.bulidDate}" id="bulidDate" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="supply.prd.type"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.typeList"
													name="labSupplierVo.goodsType" headerKey=""
													headerValue="" theme="simple" listKey="name"
													listValue="name"></s:select>
											</td>
											<td>
												<label>
													<s:text name="build.rel.time"/>：
												</label>
											</td>
											<td>
												<input type="text" class="Wdate"
													onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"
													name="labSupplierVo.startDate"
													value="${labSupplierVo.startDate }" id="startDate"
													valType="required" msg="关系建立时间不能为空"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="contract.peo.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.person"
													value="${labSupplierVo.person }" id="person"
													valType="required" msg="联系人不能为空"/>
											</td>
											<td>
												<label>
													<s:text name="contract.tel"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.phone"
													value="${labSupplierVo.phone }" id="phone"
													valType="required,phone"  phone-msg="联系电话不准确" msg="联系电话不能为空" />
											</td>
										</tr>

										<tr>
											<td>
												<label>
													<s:text name="email.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.email"
													value="${labSupplierVo.email }" id="email"
													valType="required,email" email-msg="邮箱不准确" msg="邮箱不能为空"/>
											</td>
											<td>
												<label>
													<s:text name="fax.expend"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.fax"
													value="${labSupplierVo.fax }" id="fax" valType="fax"  fax-msg="传真不准确"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="postcode.expend"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.zipCode"
													value="${labSupplierVo.zipCode }" id="zipCode" valType="required,zipCode" zipCode-msg="邮编不准确" msg="邮编不能为空"/>
											</td>
											<td>
												<label>
													<s:text name="com.expend"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.address"
													value="${labSupplierVo.address }" id="address" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark.next"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labSupplierVo.remark" cols="60" rows="3"
													id="remark">${labSupplierVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="act.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td width="150">
												<label>
													<s:text name="open.bank"/>：
												</label>
											</td>
											<td width="450">
												<s:select list="#request.bankList"
													name="labSupplierVo.account" headerKey=""
													headerValue="" theme="simple" listKey="name"
													listValue="name"></s:select>
											</td>

											<td width="150">
												<label>
													<s:text name="act.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSupplierVo.accountName"
													id="accountName" value="${labSupplierVo.accountName }"
													valType="required"  msg="账户名称不能为空"/>
											</td>


										</tr>
										<tr>
											<td>
												<label>
													<s:text name="account"/>：
												</label>
											</td>
											<td colspan="3">
												<input type="text" name="labSupplierVo.accountCode"
													value="${labSupplierVo.accountCode }" id="accountCode" />
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="supply.info"/></span>
									</div>

									<table class="FormtableCon">
										<tr>
											<td width="150">
												<label>
													<s:text name="supply.prd.detail"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labSupplierVo.goods" cols="60" rows="3"
													id="goods">${labSupplierVo.goods}</textarea>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="comment.info"/></span>
									</div>

									<table class="FormtableCon">
										<tr>
											<td width="150">
												<label>
													<s:text name="gived.stock"/>：
												</label>
											</td>
											<td width="450">
												<input type="text"
													value="<s:set name="alllist" value="#request.timeList" /><s:iterator value="#alllist" status="st">${name};</s:iterator>"
													id="accountCode" disabled="true" />
											</td>
											<td width="150">
												<label>
													<s:text name="priced"/>：
												</label>
											</td>
											<td>
												<input type="text"
													value="<s:set name="alllist" value="#request.priceList" /><s:iterator value="#alllist" status="st">${name};</s:iterator>"
													id="accountCode" disabled="true" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="prd.quant"/>：
												</label>
											</td>
											<td>
												<input type="text"
													value="<s:set name="alllist" value="#request.qualityList" /><s:iterator value="#alllist" status="st">${name};</s:iterator>"
													id="accountCode" disabled="true" />
											</td>
											<td>
												<label>
													<s:text name="service.quant"/>：
												</label>
											</td>
											<td>
												<input type="text"
													value="<s:set name="alllist" value="#request.serverList" /><s:iterator value="#alllist" status="st">${name};</s:iterator>"
													id="accountCode" disabled="true" />
											</td>
										</tr>
									</table>

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
