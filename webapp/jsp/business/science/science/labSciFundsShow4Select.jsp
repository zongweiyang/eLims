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
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<s:if test="${labSciFundsVo.type == '2'}">
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
												${labSciFundsVo.money}
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
													valType="strLength" max="512" strLength-msg='<s:property value="getText('remakrnot512')"/>'>${labSciFundsVo.remark}</textarea>
											</td>
											</td>
										</tr>
									</table>
									</s:if>
									<s:if test="${labSciFundsVo.type == '1'}">
									<div class="Formtabletitle">
										<span><s:text name="charge.out.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="outer.peo"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.user}
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="outer.name"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.name}
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="outer.money"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.money}
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="outer.time"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.useDate}
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td>
												<textarea rows="3" cols="40" name="labSciFundsVo.remark"
													valType="strLength" max="512" strLength-msg='<s:property value="getText('remakrnot512')"/>' disabled="true">${labSciFundsVo.remark}</textarea>
											</td>
											<td class="r" width="150">
													<label>
														<s:text name="upload.attachment"/>：
													</label>
											</td>
											<td colspan="">
												<s:if test="${fn:length(uplodeList)>0}">
													<s:iterator status="st2" value="#request.uplodeList" id="">
														<span> <a href="${path }" id="fileId">${name
																} </a></span>
													</s:iterator>&nbsp;&nbsp;&nbsp;
												</s:if>
												<div id="upfiles"></div>
											</td>
										</tr>
									</table>
									</s:if>
									<s:if test="${labSciFundsVo.type == '0'}">
									<div class="Formtabletitle">
										<span><s:text name="charge.in.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="fee.source"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.user}
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="charge.name"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.name}
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="fee.amount"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.money}
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="come.acc.time"/>：
												</label>
											</td>
											<td>
												${labSciFundsVo.useDate}
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="remark"/>：
												</label>
											</td>
											<td>
												<textarea rows="3" cols="40" name="labSciFundsVo.remark" disabled="true"
													valType="strLength" max="512" strLength-msg='<s:property value="getText('remakrnot512')"/>'>${labSciFundsVo.remark}</textarea>
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="upload.attachment"/>：
												</label>
											</td>
											<td colspan="">
												<s:if test="${fn:length(uplodeList)>0}">
													<s:iterator status="st2" value="#request.uplodeList" id="">
														<span> ${name} 
																		<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;
																	<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}"">
																	    <img src="<%=basePath %>/img/query.gif"/>
																	</a>
																	<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
																</s:if></span>	
													</s:iterator>&nbsp;&nbsp;&nbsp;
												</s:if>
												<div id="upfiles"></div>
											</td>
										</tr>
									</table>
									</s:if>
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
