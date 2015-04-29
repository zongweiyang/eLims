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
								<span><s:text name="look.check"></s:text></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="mouldForm">
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
										<span>${funName}<s:text name="information"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="theme.user"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3">
												${labUserCredVo.userName}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="card.name"></s:text>：
												</label>
											</td>
											<td>
												${labUserCredVo.name}
											</td>
											<td>
												<label>
													<s:text name="card.number"></s:text>：
												</label>
											</td>
											<td>
												${labUserCredVo.code}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="start.date"></s:text>：
												</label>
											</td>
											<td>
												${labUserCredVo.startDate}
											</td>
											<td>
												<label>
													<s:text name="end.date"></s:text>：
												</label>
											</td>
											<td>
												${labUserCredVo.endDate}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="theme.depart"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												${labUserCredVo.unit}
											</td>
											<td>
												<label>
													<s:text name="config.type"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												${labUserCredVo.type}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3" style="height: 56px;">
												${labUserCredVo.remark}
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				</td>
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
