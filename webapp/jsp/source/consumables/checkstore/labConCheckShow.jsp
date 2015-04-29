<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
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


	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" name="labConCheckMainVo.id"
				value="${labConCheckMainVo.id}" />
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
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}:
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
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
										<div class="TabTable"
											style="padding-top: 0; margin: 0; background: none;">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="base.info"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>
															</label>
														</td>
														<td>
															${labConCheckMainVo.checkno}
														</td>
														<td>
															<label>
																<s:text name="pandian"/>：
															</label>
														</td>
														<td>
															${labConCheckMainVo.checker}
														</td>
														<td>
															<label>
																<s:text name="pandiantime"/>：
															</label>
														</td>
														<td>
															${labConCheckMainVo.checktime}
														</td>
														<td>
															<label>
																<s:text name="pansubject"/>：
															</label>
														</td>
														<td>
															${labConCheckMainVo.name}
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="paninfo"/></span>
												</div>
												<table class="myworkingboxttable" cellspacing="0"
													cellpadding="0">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="conname"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														
														<th>
															<s:text name="preconnum"/>
														</th>
														<th>
															<s:text name="thistimein"/>
														</th>
														<th>
															<s:text name="thistimeout"/>
														</th>
														<th>
															<s:text name="panstore"/>
														</th>
														<th>
															<s:text name="remark"/>
														</th>
													</tr>
													<s:iterator
														value="labConCheckMainVo.labConCheckDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${labConsumablesName }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].labConsumablesId"
																	value="${labConsumablesId }" />
															</td>
															<td class="l">
																${consumablesSize}
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].consumablesSize"
																	value="${consumablesSize}" />
															</td>
															<td class="l">
																${lastAmount }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].lastAmount"
																	value="${lastAmount}" />
															</td>
															<td class="l">
																${thisInAmount}
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].thisInAmount"
																	value="${thisInAmount}" />
															</td>
															<td class="l">
																${thisOutAmount }
																<input type="hidden"
																	name="labConCheckMainVo.labConCheckDetailVoList[${st.index}].thisOutAmount"
																	value="${thisOutAmount}" />
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
