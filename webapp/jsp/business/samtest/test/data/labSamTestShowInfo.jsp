<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script>
		var api = frameElement.api, W = api.opener, D = W.document;
		function saveSubmit(){
			if(confirm('确认提交吗')){
				W.flushThisPage();
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
											<s:text name="record.in"/>：
											<span><s:text name="previewed"/></span>
										</h2>
									</div>
									<form action="" method="post" name="studentForm">
										<input type="hidden" value="${labSamTestBeatchVo.id}" name="labSamTestBeatchVo.id" />
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="samtest/labSamTest/updateLabSamTest.action" onclick="saveSubmit();" value="confirm.submit" img="/img/icon/filesave.gif" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="check.item.info"></s:text></span>
												<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
													[&nbsp;
													<font color="blue"><s:text name="open.close"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="myworkingboxttable" style="margin-left: 0px; width: 100%;">
													<tr>
														<th width="20px">
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="checking.item"/>
														</th>
														<th>
															<s:text name="std.name"/>
														</th>
														<th>
															<s:text name="quan.index"/>
														</th>
														<th style="width: 60px;">
															<s:text name="tempture"/>
														</th>
														<th style="width: 60px;">
															<s:text name="shidu"/>
														</th>
														<th>
															<s:text name="chk.method"/>
														</th>
													</tr>
													<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
														<tr>
															<td>
																${st.index+1}
															</td>
															<td>
																${itemName}
															</td>
															<td class="c">
																${standardName}
															</td>
															<td>
																${describeFormula}
															</td>
															<td class="c">
																${temperature}
															</td>
															<td class="c">
																${humidity}
															</td>
															<td class="c">
																${methodName}
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="app.use.info"/></span>
												<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
													[&nbsp;
													<font color="blue"><s:text name="open.close"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="myworkingboxttable" style="margin-left: 0px; width: 100%;">
													<tr>
														<th width="20px">
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="app.name"/>
														</th>
														<th>
															<s:text name="tempture"/>
														</th>
														<th>
															<s:text name="shidu"/>
														</th>
														<th>
															<s:text name="start.time"/>
														</th>
														<th>
															<s:text name="end.time"/>
														</th>
													</tr>
													<s:iterator value="#request.listLabApparaUseVo" status="st" id="vo">
														<tr>
															<td>
																${st.index+1}
															</td>
															<td>
																${appName}
															</td>
															<td class="c">
																${wenDu}
															</td>
															<td>
																${shiDu}
															</td>
															<td class="c">
																${beStartDate}
															</td>
															<td class="c">
																${beEndDate}
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="env.info"/></span>
												<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
													[&nbsp;
													<font color="blue"><s:text name="open.close"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="FormtableCon">
													<tr>
														<td class="c">
															<label>
																<s:text name="param.name"/>
															</label>
														</td>
														<td>
															${labAmbientInfoVo.labAmbientUnit}
														</td>
														<td class="c">
															<label>
																<s:text name="param.unit"/>：
															</label>
														</td>
														<td>
															${labAmbientInfoVo.labAmbientUnit}
														</td>
													</tr>
													<tr>
														<td class="c">
															<label>
																<s:text name="param.value"/>：
															</label>
														</td>
														<td>
															${labAmbientInfoVo.value}
														<td class="c">
															<label>
																<s:text name="address"/>：
															</label>
														</td>
														<td>
															${labAmbientInfoVo.address}
														</td>
													</tr>
													<tr>
														<td class="c">
															<label>
																<s:text name="room"/>：
															</label>
														</td>
														<td>
															${labAmbientInfoVo.room}
														</td>

														<td class="c">
															<label>
																<s:text name="env.time"/>：
															</label>
														</td>
														<td>
															${labAmbientInfoVo.time}
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="app.out.time"></s:text></span>
												<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
													[&nbsp;
													<font color="blue"><s:text name="open.close"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>：
															</label>
														</td>
														<td>
															${labReaOutMainVo.receiptno}
														</td>
														<td>
															<label>
																<s:text name="out.savinger"/>：
															</label>
														</td>
														<td>
															${labReaOutMainVo.outstorer}
														</td>
														<td>
															<label>
																<s:text name="out.time"/>：
															</label>
														</td>
														<td>
															${labReaOutMainVo.outstoreDate}
														</td>
													</tr>
												</table>
												<table class="myworkingboxttable" style="margin-left: 0px; width: 100%;" cellspacing="0" cellpadding="0">
													<tr>
														<th width="20px">
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="rea.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="pure"/>
														</th>
														<th>
															<s:text name="out.number"/>
														</th>

														<th>
															<s:text name="out.info"/>
														</th>
													</tr>
													<s:iterator value="labReaOutMainVo.labReaOutDetailVoList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${reagentName }
															</td>
															<td class="l">
																${size}
															</td>
															<td class="l">
																${purity}
															</td>
															<td class="l">
																${amount}
															</td>

															<td class="l">
																${remark}
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="std.out.info"></s:text></span>
												<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
													[&nbsp;
													<font color="blue"><s:text name="open.close"/></font>&nbsp;]
												</label>
											</div>
											<div style="display: none;">
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>：
															</label>
														</td>
														<td>
															${labRefOutMainVo.receiptno}
														</td>
														<td>
															<label>
																<s:text name="out.savinger"/>：
															</label>
														</td>
														<td>
															${labRefOutMainVo.outstorer}
														</td>
														<td>
															<label>
																<s:text name="out.time"/>：
															</label>
														</td>
														<td>
															${labRefOutMainVo.outstoreDate}
														</td>
													</tr>
												</table>
												<table class="myworkingboxttable" style="margin-left: 0px; width: 100%;" cellspacing="0" cellpadding="0">
													<tr>
														<th width="20">
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="std.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="pure"/>
														</th>
														<th>
															<s:text name="out.number"/>
														</th>

														<th>
															<s:text name="out.info"/>
														</th>
													</tr>
													<s:iterator value="labRefOutMainVo.labRefOutDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${referenceName }
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].referenceId"
																	value="${referenceId }" />
																<input type="hidden" name="id" value="${id}" />
															</td>
															<td class="l">
																${size}
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].size"
																	value="${size}" />
															</td>
															<td class="l">
																${purity}
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].purity"
																	value="${purity}" />
															</td>
															<td class="l">
																${amount}
															</td>

															<td class="l">
																${remark}
															</td>
														</tr>
													</s:iterator>
												</table>
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
</html>
