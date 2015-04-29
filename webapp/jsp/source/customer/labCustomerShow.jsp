<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		 <script type="text/javascript" src="<%=basePath%>/js/highcharts/1.7.1-jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/highcharts/highcharts.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript">
        $(function(){
        $('#container').highcharts({
            title: {
                text: '客户业务统计',
                x: -20 //center
            },
            subtitle: {
                text: '客户业务信息',
                x: -20
            },
            xAxis: {
                categories: ['1月', '2月', '3月', '4月', '5月', '6月',
                    '7月', '8月', '9月', '10月', '11月', '12月']
            },
            yAxis: {
                title: {
                    text: '业务(个)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '项'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: '${labCustomerVo.name}',
                data: [${amountList[0]},${amountList[1]}, ${amountList[2]}, ${amountList[3]}, ${amountList[4]}, ${amountList[5]},${amountList[6]},${amountList[7]},${amountList[8]},${amountList[9]},${amountList[10]},${amountList[11]}]
            }]
        });
    });
		</script>
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
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="mouldForm">
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
													value="${labCustomerVo.num}" id="num" size="40"
													disabled="true" />
											</td>
											<td class="r">
												<label>
													<s:text name="ownindustry"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.industry"
													value="${labCustomerVo.industry}" id="industry"
													disabled="disabled" />
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="cust.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.name"
													value="${labCustomerVo.name}" id="name" size="40"
													disabled="disabled" />
											</td>
											<td class="r">
												<label>
													<s:text name="custtype"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.typeList" name="labCustomerVo.type"
													value="#{labCustomerVo.type }" theme="simple"
													listKey="name" listValue="name" disabled="true"></s:select>
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
													name="labCustomerVo.level" value="#{labCustomerVo.level }"
													theme="simple" listKey="name" listValue="name"
													disabled="true"></s:select>
											</td>
											<td class="r">
												<label>
													<s:text name="buildtime"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.buildDate"
													value="${labCustomerVo.buildDate}" id="buildDate" size="40"
													　class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
													disabled="true" />
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="custtel"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.phone"
													value="${labCustomerVo.phone}" id="phone" size="40"
													disabled="true" />
											</td>
											<td class="r">
												<label>
															<s:text name="email"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.email"
													value="${labCustomerVo.email}" id="email" size="40"
													disabled="true" />
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="fax.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.fox"
													value="${labCustomerVo.fox}" id="fox" size="40"
													disabled="true" />
											</td>
											<td class="r">
												<label>
													<s:text name="postcode.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.zipCode"
													value="${labCustomerVo.zipCode}" id="zipCode" size="40"
													disabled="true" />
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
													value="${labCustomerVo.firPerson}" id="firPerson" size="40"
													disabled="true" />
											</td>
											<td class="r">
												<label>
													<s:text name="address.large"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labCustomerVo.address"
													value="${labCustomerVo.address}" id="address" size="40"
													disabled="true" />
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
													disabled="true">${labCustomerVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
							
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="custbizinfo"/></span>
								</div>
								<div id="container" style="min-width:700px;height:400px"></div>
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
