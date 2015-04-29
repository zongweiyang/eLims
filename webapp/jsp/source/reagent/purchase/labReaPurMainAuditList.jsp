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
		    function submitvalue(actionstr){
			var df=document.labReaPurform;
			df.action = actionstr;
			df.submit();  
		   }  
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			
			function deleteEntity(url){
				if(confirm('确认删除选中信息吗?')){
					goToNextAction(url);
				}
				return ;
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('ids');
				if(checkbox==0){
				    validate.tip("请至少选中一项.",$('#functionId'));
					return ;
				}
				if(confirm('确认删除选中信息吗?')){
					goToNextAction(url);
				}
				return ;
			}
			function showProcess(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath%>/jsp/common/workflow/ins/processFrame.jsp?busId='+id,
					title:'<s:property value="getText('task.progress')"/>',
					opacity:0.4,
					width:1100, 
					height:500,
					lock:true,
					max:false,
					min:false
				});
			}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labReaPurform">
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
												${funName }：
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
																	<input type="text" name="labReaPurMainVo.receiptno"
																		id="receiptno" value="${labReaPurMainVo.receiptno}" />
																</td>
																<td>
																	<label>
																		<s:text name="applytime"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaPurMainVo.startDate" id="startDate"
																		value="${labReaPurMainVo.startDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	<s:text name="to"/>
																	<input type="text" name="labReaPurMainVo.endDate" id="endDate"
																		value="${labReaPurMainVo.endDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
																</td>
																<td>
																	<label>
																		<s:text name="applier"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReaPurMainVo.applicant"
																		id="name" value="${labReaPurMainVo.applicant}" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funStepList" listKey="stepId"
																		listValue="stepName" headerKey="" headerValue="-全部-"
																		name="labReaPurMainVo.status" id="status"
																		onchange="submitvalue('listLabReaPurApprove.action');">
																	</s:select>
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
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<tr>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="receiptno">
													<s:text name="bill.no"/>
												</th>
												<th property="applicant">
													<s:text name="applier"/>
												</th>
												<th property="createTime">
													<s:text name="applytime"/>
												</th>
												<th property="status">
													<s:text name="sam.state"/>
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
																${applicant}
															</td>
															<td class="c">
																${createTime}
															</td>
															<td class="c">
																<a href="javascript:;" onclick="showProcess('${id}');return false">
																	${status}
																</a>
															</td>
															<td class="c">
																<s:if test="${isOper=='Y'}">
																	<l:a href="#"
																		uri="reagent/labReaPurMain/showLabReaPur4Audit.action?labReaPurMainVo.id=${id}"
																		value="审核" />
																</s:if>
																<l:a href="#"
																	uri="reagent/labReaPurMain/showLabReaPur.action?labReaPurMainVo.id=${id}"
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
										page="/jsp/include/page.jsp?formName=labReaPurform"
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
