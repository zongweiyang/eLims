<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	  function submitvalue(actionstr){
				var df = document.forms[0];
			 	df.action=actionstr;
			  	df.submit();
			}
	  function doUrl(url){
		   window.location.href=url;
	   }
	  function add(){
	  	var url='<%=basePath%>/appara/useing/listLabAppUse4Bespek.action';
		var df = document.forms[0];
			 	df.action=url;
			  	df.submit();
	  }
	  
	</script>
	</head>

	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="customeractivitiesForm" theme="simple">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="appusinglist"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="app.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" id="appName" name="labApparaUseVo.appName" value="${labApparaUseVo.appName}" />
																</td>
																<td>
																	<l:a uri="/appara/useing/listLabAppUse.action" value="fun.query" />
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="appara/useing/preAddLabAppUse.action" value="lab.code.add" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="add();return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="yuyue"/></b> </a>
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
														<s:text name="lab.sequence"/>
													</th>
													<th property="appName">
														<s:text name="app.name"/>
													</th>
													<th property="beStartDate">
														<s:text name="pre.starttime"/>
													</th>
													<th property="beEndDate">
														<s:text name="pre.endtime"/>
													</th>
													<th property="day">
														<s:text name="appstatus"/>
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
																<td class="c">
																	<s:property value="#st.index+1" />
																</td>
																<td>
																	<s:property value="appName" />
																</td>
																<td class="c">
																	<s:property value="beStartDate" />
																</td>
																<td class="c">
																	<s:property value="beEndDate" />
																</td>
																<td class="c">
																	<s:if test="${stauts == '0'}">
																		<s:text name="preapp"/>
																	</s:if>
																	<s:else>
																		<s:text name="usingapp"/>
																	</s:else>
																</td>
																<td class="c">
																	<s:if test="${stauts == '0'}">
																		<l:a href="#" uri="appara/useing/preUpdateLabAppUse.action?labApparaUseVo.id=${id}" value="using" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="appara/useing/preUpdateLabAppUse.action?labApparaUseVo.id=${id}" value="theme.modify" />
																	</s:else>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=customeractivitiesForm" flush="true" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>
