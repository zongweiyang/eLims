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
		function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			window.location.href = '<%=basePath%>reagent/labReaOutMain/'+actionstr+'&path='+data;
				}
			}
	   });
    	}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labReaOutform">
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
												${funName}：
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
																	<input type="text" name="labReaOutMainVo.receiptno"
																		id="receiptno" value="${labReaOutMainVo.receiptno}" />
																</td>
																<td>
																	<label>
																		<s:text name="out.time"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaOutMainVo.startDate" id="startDate"
																		value="${labReaOutMainVo.startDate}" class="Wdate"
																		size="15"
																		onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	<s:text name="to"/>
																	<input type="text" name="labReaOutMainVo.endDate" id="endDate"
																		value="${labReaOutMainVo.endDate}" class="Wdate"
																		size="15"
																		onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
																</td>
																<td>
																	<label>
																		<s:text name="out.savinger"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaOutMainVo.outstorer"
																		id="name" value="${labReaOutMainVo.outstorer}" />
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
																	<l:a uri="reagent/labReaOutMain/preAddLabReaOutMain.action"
																		value="admin.add" />
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
												<th property="receiptno">
													<s:text name="bill.no"/>
												</th>
												<th property="outstorer">
													<s:text name="out.savinger"/>
												</th>
												<th property="outstoreDate">
													<s:text name="out.time"/>
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
																${receiptno}
															</td>
															<td class="l">
																${outstorer}
															</td>
															<td class="c">
																${fn:substring(outstoreDate,0,10)}
															</td>
															<td class="c">
																<l:a href="#"
																	uri="reagent/labReaOutMain/showLabReaOutMain.action?labReaOutMainVo.id=${id}"
																	value="look.check" />
																&nbsp&nbsp&nbsp
																<a href="#"
																	onclick="ajaxVerification('exportLabReaOutMain.action?labReaOutMainVo.id=${id}');return false;"><s:text name="outbill"/></a>
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
										page="/jsp/include/page.jsp?formName=labReaOutform"
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
