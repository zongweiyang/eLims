<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labSciFundsFrom"
			id="labSciFundsFrom">
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
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="prj.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.code"
																		id="code" value="${labSciScienceVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.name"
																		id="name" value="${labSciScienceVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="prj.people"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.masterName"
																		id="masterName" value="${labSciScienceVo.masterName}" />
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}"
																		onclick="submitAction();" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:export params="labSciFundsVo,pageResult" type="excel" source="${labSciFundsVo.filePath}" target="${funName}-${now}.xls" value="导出${funName}"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox"
															key="labSciFundsVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="code">
														<s:text name="prj.no"/>
													</th>
													<th property="name">
														<s:text name="item.name"/>
													</th>
													<th property="master">
														<s:text name="prj.manager"/>
													</th>
													<th property="ratifyFunds">
														<s:text name="approval.fee"/>(<s:text name="thousand.yuan"/>)
													</th>
													<th property="inFunds">
														<s:text name="come.fee"/>(<s:text name="thousand.yuan"/>)
													</th>
													<th property="paperFunds">
														<s:text name="bill.fee"/>(<s:text name="thousand.yuan"/>)
													</th>
													<th property="outFunds">
														<s:text name="out.fee"/>(<s:text name="thousand.yuan"/>)
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="td_cb">
																	<input type="checkbox" name="labSciFundsVo.ids"
																		value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	${code}
																</td>
																<td class="l">
																	${name}
																</td>
																<td class="l">
																	${masterName}
																</td>
																<td class="r">
																	${ratifyFunds}
																</td>
																<td class="r">
																	${inFunds}
																</td>
																	<td class="r">
																	${paperFunds}
																</td>
																	<td class="r">
																	${outFunds}
																</td>
																<td class="c">
																    <l:a href="#"
																		uri="science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=2"
																		onclick="nextUri('science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=2');" value="prj.budget" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a href="#"
																		uri="science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=0"
																		onclick="nextUri('science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=0');" value="prj.in.record" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a href="#"
																		uri="science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=1"
																		onclick="nextUri('science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${id}&labSciFundsVo.type=1');" value="prj.out.record" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="6" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labSciFundsFrom"
										flush="true" /></td>
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
