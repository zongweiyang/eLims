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
															<l:a uri="customer/labCustomer/addLabCustomer.action"
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
											<td class="r">
												<label>
													<s:text name="custno"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.num"
													value="${labCustomerVo.num}" readonly="true" id="num"
													size="40" valType="required,strLength" max="16"
													strLength-msg="客户编码长度不能超过16位" msg="客户编码不能为空" />
											</td>
											<td class="r">
												<label>
													<s:text name="ownindustry"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.industry"
													id="industry" size="40" valType="strLength" max="30"
													strLength-msg="所处行业长度不能超过30位" />
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="cust.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.name" id="name"
													size="40" valType="required,strLength" max="30"
													strLength-msg="客户名称长度不能超过30位" msg="客户名称不能为空" />
											</td>
											<td class="r">
												<label>
													<s:text name="custtype"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.typeList" name="labCustomerVo.type"
													theme="simple" listKey="name" listValue="name"></s:select>
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="custlevel"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.levelList"
													name="labCustomerVo.level" theme="simple" listKey="name"
													listValue="name"></s:select>
											</td>
											<td class="r">
												<label>
													<s:text name="buildtime"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.buildDate"
													id="buildDate" size="40" valType="required" msg="建立时间不能为空"
													class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
											</td>
										</tr>
										<tr>
											<!-- <td class="r"><label>短&nbsp信&nbsp通&nbsp知：</label></td>
												<td><s:radio list='#{1:"是",0:"否"}' value="1" name="labCustomerVo.inform" theme="simple"></s:radio></td>
												 -->
											<td class="r">
												<label>
													<s:text name="custtel"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.phone" id="phone"
													size="40" valType="required,strLength,phone" max="30"
													strLength-msg="客户电话长度不能超过30位" msg="客户电话不能为空" phone-msg="客户电话不准确"/>
											</td>
											<td class="r">
												<label>
															<s:text name="email"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.email" id="email"
													size="40" valType="required,strLength,email" max="30"
													strLength-msg="客户邮箱长度不能超过30位" msg="客户邮箱不能为空" email-msg="邮箱不准确"/>
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="fax.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.fox" id="fox"
													size="40" valType="strLength,fax" max="30"
													strLength-msg="客户传真长度不能超过30位" fax-msg="传真不准确"/>
											</td>
											<td class="r">
												<label>
													<s:text name="postcode.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.zipCode" id="zipCode"
													size="40" valType="strLength,zipCode" max="30"
													strLength-msg="客户邮编长度不能超过30位"  zipCode-msg="邮编不准确"/>
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="call.people"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.firPerson"
													id="firPerson" size="40" valType="required,strLength"
													max="30" strLength-msg="联系人长度不能超过30位" msg="联系人不能为空" />
											</td>
											<td class="r">
												<label>
													<s:text name="address.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.address" id="address"
													size="40" valType="required,strLength" max="64"
													strLength-msg="客户地址长度不能超过64位" msg="客户地址不能为空" />
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea rows="4" cols="60" name="labCustomerVo.remark"
													valType="strLength" max="512" strLength-msg="长度不能超过512位"></textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="custbizinfo"/></span>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg">

				</td>
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
