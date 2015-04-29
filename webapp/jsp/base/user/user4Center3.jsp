<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
			<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<script language="javascript" type="text/javascript"> 
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
								<div class="myworkingbox">
									<div class="myworkingboxttitle">
										<h2>
											<s:text name="ops.time"/>：
											<span><s:text name="update.now"/></span>
										</h2>
									</div>
									<form action="" method="post" name="labUserForm" id="form">
										<s:token></s:token>
										<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/preUpdateLabUser4Center.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="base.info"/></span></a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center1.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="auth.info"/></span> </a>
													</li>
													<li id="li04" class="currenttab">
														<a href="javascript:;"><span><s:text name="other.info"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="vert.info"/></span>
													</div>
													<table class="FormtableCon_sform">
														<tr>
															<th width="50">
																<img src="<%=basePath%>img/icon_drag.gif" />
															</td>
															<th>
																<s:text name="config.name"/>
															</th>
															<th>
																<s:text name="code.number"/>
															</th>
															<th>
																<s:text name="config.type"/>
															</th>
															<th>
																<s:text name="valide.date"/>
															</th>
														</tr>
														<s:iterator value="#request.userCredList" status="st">
															<tr>
																<td width="50" class="c">
																	${st.index+1}
																</td>
																<td>
																	${name}
																</td>
																<td>
																	${code}
																</td>
																<td>
																	${type}
																</td>
																<td>
																	${startDate}~${endDate}
																</td>
															</tr>
														</s:iterator>
													</table>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="tec.info"/></span>
													</div>
													<table class="FormtableCon_sform">
														<tr>
															<th width="50">
																<img src="<%=basePath%>img/icon_drag.gif" />
															</td>
															<th>
																<s:text name="msg.subject"/>
															</th>
															<th>
																<s:text name="training.time"/>
															</th>
															<th>
																<s:text name="training.site"/>
															</th>
															<th>
																<s:text name="training.content"/>
															</th>
															<th>
																<s:text name="tec.result"/>
															</th>
														</tr>
														<s:iterator value="#request.trainRecordList" status="st">
															<tr>
																<td width="50" class="c">
																	${st.index+1}
																</td>
																<td>
																	${title}
																</td>
																<td>
																	${tdate}
																</td>
																<td>
																	${address}
																</td>
																<td>
																	${content}
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
						</tr>
					</table>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
	<script></script>
</html>
