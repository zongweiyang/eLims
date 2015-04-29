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
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			function deleteEntity(url){
				if(confirm('<s:property value="getText('deleted.info')"/>')){
					goToNextAction(url);
				}
				return ;
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('ids');
				if(checkbox==0){
				    validate.tip('<s:property value="getText('select.one.m')"/>',$('#functionId'));
					return ;
				}
				if(confirm('<s:property value="getText('confirm.deleted.select')"/>')){
					goToNextAction(url);
				}
				return ;
			}
			function submitvalue(actionstr){
			var df=document.labSamDestoryform;
			df.action = actionstr;
			df.submit();  
		}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labSamDestoryform">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="sam.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamMainVo.taskCode" id="taskCode" value="${labSamMainVo.taskCode}" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.register"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamMainVo.startDate" value="${labSamMainVo.startDate}" id="startDate" class="Wdate" size="15" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	~
																	<input type="text" name="labSamMainVo.endDate" value="${labSamMainVo.endDate}" id="endDate" class="Wdate" size="15" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});"/>
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="submitAction();" value="fun.query" />
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
												<th property="sampNo">
													<s:text name="sam.no"/>
												</th>
												<th>
													<s:text name="sam.name"/>
												</th>
												<th>
													<s:text name="sam.number"/>
												</th>
												<th>
													<s:text name="cust.name"/>
												</th>
												<th property="registDate">
													<s:text name="reg.time"/>
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
															<td class="l">
																${sampNo}
															</td>
															<td>
																${sampName }
															</td>
															<td>
																${num }
															</td>
															<td class="l">
																${customer }
															</td>
															<td class="c">
																${registDate}
															</td>
															<td class="c">
																<s:if test="${status=='Y'}">
																	<l:a href="javascript:;" uri="sam/labSam/preUpdateLabSam4Out.action?labSamMainVo.id=${id}" value="get.lingqu" />
																</s:if>
																<l:a href="javascript:;" uri="sam/labSam/showLabSam4Out.action?labSamMainVo.id=${id}" value="liuzhuan.dan" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labSamDestoryform" flush="true" /></td>
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
