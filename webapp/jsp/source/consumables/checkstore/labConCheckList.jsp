<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
			function deleteEntity(url){
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goAction(url);
				}
				return ;
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('ids');
				if(checkbox==0){
					validate.tip('<s:property value="getText('selected.pls')"/>',$('#functionId'));
					return ;
				}
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goAction(url);
				}
				return ;
			}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labConCheckform">
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
												${funName}:
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="bill.no"/>
																	</label>
																</td>
																<td>
																	<input type="text" name="labConCheckMainVo.checkno"
																		value="${labConCheckMainVo.checkno}" id="checkno"
																		size="20" />
																</td>
																<td>
																	<label>
																		<s:text name="pansubject"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConCheckMainVo.name"
																		value="${labConCheckMainVo.name }" id="name" size="20" />
																</td>
																<td>
																	<label>
																		<s:text name="pandian"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConCheckMainVo.checker"
																		id="checker" value="${labConCheckMainVo.checker}" />
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
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label></label>
																</td>
																<td>
																	<l:a uri="consumables/labConCheckMain/preAddLabConCheckMain.action"
																		value="lab.code.add" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<tr>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="checkno">
													<s:text name="bill.no"/>
												</th>
												<th property="name">
													<s:text name="pansubject"/>
												</th>
												<th property="checktime">
													<s:text name="pandiantime"/>
												</th>
												<th property="checker">
													<s:text name="pandian"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${checkno}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="c">
																${fn:substring(checktime,0,10)}
															</td>
															<td class="l">
																${checker}
															</td>
															<td class="c">
																<l:a href="#"
																	uri="consumables/labConCheckMain/showLabConCheckMain.action?labConCheckMainVo.id=${id}"
																	value="look.check" />
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
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labConCheckform"
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
		</s:form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
