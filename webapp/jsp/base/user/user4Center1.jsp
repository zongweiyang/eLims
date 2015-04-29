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
											<s:text name="person.center"/>：
											<span><s:text name="update.now"></s:text></span>
										</h2>
									</div>
									<form action="" method="post" name="labUserForm" id="form">
										<s:token></s:token>
										<input type="hidden" value="${labUserVo.id}" name="labUserVo.id" />
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/preUpdateLabUser4Center.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="base.info"></s:text></span>
														</a>
													</li>
													<li id="li02" class="currenttab">
														<a href="javascript:;"><span><s:text name="auth.info"></s:text></span> </a>
													</li>
													<li id="li04" class="">
														<a href="javascript:;" onclick="nextUri('user/labUser/showLabUser4Center3.action?labUserVo.id=${labUserVo.id}');"><span><s:text name="other.info"></s:text></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<table class="FormtableCon_sform" id="funTable">
														<tr>
															<th>
																<s:text name="msg.depart"/>
															</th>
															<th>
																<s:text name="fun.authorise"></s:text>
															</th>
															<th>
																<s:text name="source"></s:text>
															</th>
															<th>
																<s:text name="ops.auth"/>
															</th>
															<th>
																<s:text name="data.auth"></s:text>
															</th>
															<th>
																<s:text name="lab.code.ops"></s:text>
															</th>
														</tr>
														<s:iterator value="labUserVo.userFunList" status="st" id="uf">
															<tr>
																<td class="l">
																	${orgName}
																</td>
																<td class="l">
																	${functionName}
																</td>
																<td class="l">
																	${roleName}
																</td>
																<s:if test="${funType=='0'}">
																	<td class="c">
																		——
																	</td>
																	<td class="c">
																		——
																	</td>
																</s:if>
																<s:else>
																	<td class="l">
																		<s:if test="${isAdd=='Y'}"><s:text name="add"/></s:if>
																		<s:if test="${isDelete=='Y'}"><s:text name="drop"/></s:if>
																		<s:if test="${isUpdate=='Y'}"><s:text name="modify"/></s:if>
																		<s:if test="${isShow=='Y'}"><s:text name="search"/></s:if>
																	</td>
																	<td class="l">
																		<s:if test="${dataStr=='org'}">
																			<s:select disabled="true" list="#uf.orgDataList" listKey="tenantId" listValue="name" name="labUserVo.userFunList[${st.index}].tenantStr" theme="simple"></s:select>
																		</s:if>
																		<s:elseif test="${dataStr=='user'}">
																			<s:text name="theme.user"></s:text>
																		</s:elseif>
																		<s:else>
																			${dataStr}
																		</s:else>
																	</td>
																</s:else>
																<td class="c">
																	<a><s:text name="data.share"></s:text></a>
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
