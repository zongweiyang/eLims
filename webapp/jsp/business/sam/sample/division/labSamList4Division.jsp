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
			var df=document.samForm;
			df.action = actionstr;
			df.submit();  
		}
	function ajax2TwoCode(id){
				var url  = '${basePath}sam/labSam/showLabSam4printTwoCode.action?labSamMainVo.id='+id;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.2d')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			 }	
	function ajax2BarCode(id){
				var url  = '${basePath}sam/labSam/showLabSam4printBarCode.action?labSamMainVo.id='+id;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.bar')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			  }			
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="samForm">
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
												${funName}：
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
																	<input id="taskCode" name="labSamMainVo.taskCode" value="${labSamMainVo.taskCode }"/>
																</td>
																<td>
																	<label>
																		<s:text name="sam.register"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamMainVo.startDate" value="${labSamMainVo.startDate}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																	~
																	<input type="text" name="labSamMainVo.endDate" value="${labSamMainVo.endDate}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																</td>
																<td>
																	<label>
																		<s:text name="sam.state"/>：
																	</label>
																</td>
																<td>
																	<s:select list="#{'N':'未分样','Y':'已分样'}" headerKey="" headerValue="全部" listKey="key" listValue="value" theme="simple" name="labSamMainVo.isDiv"></s:select>
																</td>
																<td>
																	<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query" />
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
													<s:text name="sam.register"/>
												</th>
												<th>
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
															<td class="l">
																${sampNo}
															</td>
															<td>${sampName}</td>
															<td>${num}</td>
															<td class="l">${customer}</td>
															<td class="c">
																${registDate }
															</td>
															<td class="c">
																<s:if test="${isDiv=='Y'}">
																	<s:text name="sam.divieded"/>
																</s:if>
																<s:else>
																	<s:text name="sam.undivied"/>
																</s:else>
															</td>
															<td class="c">
																<s:if test="${isDiv=='Y'}">
																	<l:a uri="sam/labSam/preAddLabSam.action" href="javascript:;" onclick="ajax2TwoCode('${id}');return false;" value="print.2d" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a uri="sam/labSam/preAddLabSam.action" href="javascript:;" onclick="ajax2BarCode('${id}');return false;" value="print.bar" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<a href="${basePath }sam/labSam/showLabSam4Division.action?labSamMainVo.id=${id}"><s:text name="look.check"></s:text></a>
																</s:if>
																<s:else>
																	<l:a uri="sam/labSam/preAddLabSam.action" href="javascript:;" onclick="ajax2TwoCode('${id}');return false;" value="print.2d" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a uri="sam/labSam/preAddLabSam.action" href="javascript:;" onclick="ajax2BarCode('${id}');return false;" value="print.bar" />&nbsp;&nbsp;&nbsp;&nbsp;
																	<l:a href="javascript:;" uri="sam/labSam/preUpdateLabSam4Division.action?labSamMainVo.id=${id}" value="sam.divied" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=samForm" flush="true" /></td>
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
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
