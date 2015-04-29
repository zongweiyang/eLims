<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
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
						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciFundsVo.type" value="2"
								id="type" />
							<input type="hidden" name="labSciFundsVo.labSciScienceId"
								value="${labSciFundsVo.labSciScienceId}" id="labSciScienceId" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
																<l:a uri="science/labSciFunds/listLabScience.action"
																		onclick="nextUri('science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciFundsVo.labSciScienceId}&labSciFundsVo.type=2');" value="msg.back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="charge.pre.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>

											<td class="r" width="150">
												<label>
													<s:text name="charge.name"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.name}
											</td>

											<td class="r" width="150">
												<label>
													<s:text name="budget.mount"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.money}<s:text name="thousand.yuan"/>
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="budgeter"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.user}
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td>
												<textarea rows="3" cols="40" name="labSciFundsVo.remark" disabled="true"
													valType="strLength" max="512" strLength-msg="备注长度不能超过512位">${labSciFundsVo.remark}</textarea>
											</td>
											</td>
										</tr>
									</table>
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
