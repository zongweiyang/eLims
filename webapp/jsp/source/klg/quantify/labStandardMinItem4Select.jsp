<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type='text/javascript' src='<%=basePath%>/js/autocomplete/jquery.autocomplete.min.js'></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/autocomplete/jquery.autocomplete.css" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			function closeMe(){
			  	api.close();
		 	}
			function submitvalue(){
				goAction('/klg/labStandardItem/addLabStandardMinItem.action');
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labItemform" id="form">
			<input name="labStandardItemVo.standardId" id="standardId" type="hidden" value="${labStandardItemVo.standardId}" />
			<input  type="hidden" name="labStandardItemVo.itemId" value="${labStandardItemVo.itemId}"/>
			<table id="bodyTable" class="bodytable_pop" width="99%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop">
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labItemVo.name" value="" />
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('showLabStandardMinItem4select.action');" value="fun.query" />
																</td>
																<td>
																	<l:a uri="/klg/labStandardItem/addLabStandardMinItem.action" onclick="submitvalue();" value="page.confirm" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th>
												</th>
												<th property="name">
													<s:text name="chekcitemname"/>
												</th>
												<th property="unit">
													<s:text name="chkitemnuit"/>
												</th>
												<th property="demo1">
													<s:text name="biaozhunfen"/>
												</th>
												<th property="demo2">
													精度
												</th>
												<th property="price">
													<s:text name="checking.fee"/>
												</th>
											</tr>
											<s:if test="pageResult.resultList!=null">
												<s:set name="alllist" value="pageResult.resultList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															${st.index+1}
														</td>
														<td class="c">
															<input type="checkbox"  value="${id}" name="labStandardItemVo.itemsIds"/>
														</td>
														<td class="l">
															${name}
														</td>
														<td class="l">
															${unit }
														</td>
														<td class="l">
															${demo1 }
														</td>
														<td class="l">
															${demo2 }
														</td>
														<td class="r">
															${price }
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td colspan="7" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labItemform" flush="true" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
