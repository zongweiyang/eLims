<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>js/autocomplete/jquery.autocomplete.css" />
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
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
						<form action="addLabUser" method="post" name="labUserForm" id="form">
							<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
							<div class="FUNCIONBARNEW">
								<table>
									<tbody>
										<tr>
											<td style="padding: 6px 10px; vertical-align: center;" class="blockTd">
												<table cellspacing="0" cellpadding="0" border="0">
													<tbody>
														<tr>
															<td>
																<l:a uri="back" value="msg.back" />
															</td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="TabTable" style="padding-top: 0; margin: 0; background: none;">
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="user.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<td class="w100 c">
													<label>
														<s:text name="msg.username"/>：
													</label>
												</td>
												<td>
													${labUserVo.name}
												</td>
												<td class="w100 c">
													<label>
														<s:text name="login.nickname"/>：
													</label>
												</td>
												<td>
													${labUserVo.loginName}
												</td>
												<td rowspan="7" align="center" width="170">
													<s:if test="${fn:length(labUserVo.logo)>0}">
														<img src="<%=basePath%>${labUserVo.logo}" style="width: 170px; height: 200px" />
													</s:if>
													<s:else>
														<img src="<%=basePath%>/img/user_logo.jpg" style="width: 170px; height: 200px" />
													</s:else>
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="theme.birthday"/>：
													</label>
												</td>
												<td>
													${labUserVo.birthday}
												</td>
												<td class="c">
													<label>
														<s:text name="theme.sex"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.sex}
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="theme.phone"/>：
													</label>
												</td>
												<td>
													${labUserVo.telephone}
												</td>
												<td class="c">
													<label>
														<s:text name="theme.tel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.mobile}
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="theme.nation"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.nation}
												</td>
												<td class="c">
													<label>
														email：
													</label>
												</td>
												<td>
													${labUserVo.email}
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="address"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td colspan="3">
													${labUserVo.address}
												</td>
												<td class="c">
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="work.number"/>：
													</label>
												</td>
												<td>
													${labUserVo.no}
												</td>
												<td class="c">
													<label>
														<s:text name="come.working.date"/>：
													</label>
												</td>
												<td>
													${labUserVo.workDate}
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="theme.duty"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.duty}
												</td>
												<td class="c">
													<label>
														<s:text name="theme.job.title"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td>
													${labUserVo.techTitle}
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="remakr"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
													</label>
												</td>
												<td colspan="4">
													${labUserVo.remark}
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="vert.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<th width="30">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</td>
												<th>
													<s:text name="config.name"/>
												</th>
												<th>
													<s:text name="code.number"/>
												</th>
												<th>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</th>
											</tr>
											<s:iterator value="#request.userCredList" status="st">
												<tr>
													<td width="30" class="c">
														${pageSizex* currenPagex+st.index+1}
													</td>
													<td>
														${name}
													</td>
													<td>
														${code}
													</td>
													<td>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="tec.info"/></span>
									</div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<table class="FormtableCon">
											<tr>
												<th width="60">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</td>
												<th >
													<s:text name="config.name"/>
												</th>
												<th>
													<s:text name="msg.date"/>
												</th>
												<th>
													<s:text name="result"/>
												</th>
											</tr>
											<s:iterator value="#request.trainRecordList" status="st">
												<tr>
													<td width="60" class="c">
														${pageSizex* currenPagex+st.index+1}
													</td>
													<td >
														${title}
													</td>
													<td>
														${tdate}
													</td>
													<td>
														${result}
													</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</td>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
