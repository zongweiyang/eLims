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
			function submitvalue(actionstr){
			var df=document.labConPutInsform;
			df.action = actionstr;
			df.submit();  
		}
			function deleteOne(url){
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
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
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goToNextAction(url);
				}
				return ;
			}
			
		function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			window.location.href = '<%=basePath%>consumables/labConPurMain/'+actionstr+'&path='+data;
				}
			}
	   });
    	}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labConPutInsform">
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
																	<input type="text" name="labConPurMainVo.receiptno"
																		id="receiptno" value="${labConPurMainVo.receiptno}" />
																</td>
																<td>
																	<label>
																		<s:text name="inboudtime"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConPurMainVo.startDate" id="startDate"
																		value="${labConPurMainVo.startDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
																	<s:text name="to"/>
																	<input type="text" name="labConPurMainVo.endDate" id="endDate"
																		value="${labConPurMainVo.endDate}" class="Wdate"
																		size="15"
																		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#request.funStepList" listKey="stepId"
																		listValue="stepName" headerKey="" headerValue="-全部-"
																		name="labConPurMainVo.status" id="status"
																		onchange="submitvalue('listLabConPurIns.action');">
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
																	<l:a uri="consumables/labConPurMain/preAddLabConPurIns.action"
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
												<th property="receiptno">
													单据号
												</th>
												<th property="applicant">
													<s:text name="applier"/>
												</th>
												<th property="createTime">
													申请时间
												</th>
												<th property="inTime">
													<s:text name="inboudtime"/>
												</th>
												<th property="status">
													<s:text name="sam.state"/>
												</th>
												<th property="type">
													<s:text name="config.type"/>
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
																${inTime}
															</td>
															<td class="c">
																${status}
															</td>
															<td class="c">
																<s:if test="${type=='1'}">
																	<s:text name="indoundbill"/>
																</s:if>
																<s:else>
																	采购单
																</s:else>
															</td>
															<td class="c">
																<s:if test="${isOper=='Y'&& type=='1'}">
																	<l:a href="#"
																		uri="consumables/labConPurMain/preUpdateLabConPurIns.action?labConPurMainVo.id=${id}"
																		value="theme.modify" />
																	&nbsp;&nbsp;
																		 <l:a href="#"
																		uri="consumables/labConPurMain/deleteLabConPurIns.action?labConPurMainVo.id=${id}"
																		onclick="deleteOne('consumables/labConPurMain/deleteLabConPurIns.action?labConPurMainVo.id=${id}');" value="lab.code.del" />
																</s:if>
																<s:elseif test="${isOper=='Y'&& type=='0'}">
																	<l:a href="#"
																		uri="consumables/labConPurMain/showLabConPur4Ins.action?labConPurMainVo.id=${id}"
																		value="inbound.con" />
																</s:elseif>
																<s:else>
																	<l:a href="#"
																		uri="consumables/labConPurMain/showLabConPur.action?labConPurMainVo.id=${id}"
																		value="look.check" />
																	<a href="#"
																		onclick="ajaxVerification('exportLabConPur.action?labConPurMainVo.id=${id}');return false;"><s:text name="indoundbill"/></a>
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
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labConPutInsform"
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
