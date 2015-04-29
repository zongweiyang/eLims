<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	</head>
	<script type='text/javascript'>
function submitthis(action)
{
	document.processForm1.action=action;
	document.processForm1.submit();
}
</script>

	</head>
	<body class="" id="mainid" >
		<form action="listWfFlow.action" method="post" name="processForm1">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">

										<div class="myworkingboxttitle">
											<h2>
												<s:text name="prc.manage"/>：
												<span><s:text name="prc.biz.info"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td><label><s:text name="tmp.biz"/>：</label></td>
					                      						<td>
					                      							<s:select list="#request.funcList" listKey="id" listValue="name" name="labWfProcessVo.funId" headerKey="" headerValue="-全部-" 
														              theme="simple"></s:select>
					                      						</td>
					                      						<td><label><s:text name="config.name"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labWfProcessVo.name" value="${labWfProcessVo.name}" />
					                      						</td>
					                      						<td><label><s:text name="config.number"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labWfProcessVo.code" value="${labWfProcessVo.code}" />
					                      						</td>
																<td>
																	<l:a uri="${pageResult.action}" value="fun.query"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1"
											cellpadding="0">
											<thead>
												<tr>
													<th class="c" width="50">
														<s:text name="lab.sequence"/>
													</th>
													<th class="c" width="90" property="funName">
														<s:text name="tmp.biz"/>
													</th>
													<th class="c" width="" property="name">
														<s:text name="config.name"/>
													</th>
													<th class="c" width="150" property="code">
														<s:text name="config.number"/>
													</th>
													<th class="c" width="100" property="startDate">
														<s:text name="begined.timeing"/>
													</th>
													<th class="c" width="100">
														<s:text name="deadline"/>
													</th>
													<th class="c" width="50">
														<s:text name="completed"/>
													</th>
													<th class="c" width="50">
														<s:text name="uncompleted"/>
													</th>
													<th class="c" width="50">
														<s:text name="sum.up.total"/>
													</th>
													<th class="c" width="70">
														<s:text name="sam.state"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${funName}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="l">
																${code}
															</td>
															<td class="c">
																<s:if test="${fn:length(startDate)>10}">
																	${fn:substring(startDate,0,10)}
																</s:if>
																<s:else>
																	${startDate}
																</s:else>
															</td>
															<td class="c">
																<s:if test="${fn:length(endDate)>10}">
																	${fn:substring(endDate,0,10)}
																</s:if>
																<s:else>
																	${endDate}
																</s:else>
															</td>
															<td class="r">
																${county}
															</td>
															<td class="r">
																${countn}
															</td>
															<td class="r">
																${count}
															</td>
															<td class="c">
																<s:if test="${status=='2'}">
																	<s:text name="closed"/>
																</s:if>
																<s:else>
																	<s:text name="using"/>
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td class="c" colspan="8">
															<s:text name="lab.msg.none"/>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center">
									<jsp:include
										page="/jsp/include/page.jsp?formName=processForm1"
										flush="true" />
								</td>
							</tr>
						</table>
					</td>
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