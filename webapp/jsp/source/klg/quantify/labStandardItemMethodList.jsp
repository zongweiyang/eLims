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
				$('#form').attr('action','${basePath}'+url);
				$('#form').submit();
			}
			function importStandardItemMethod(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>klg/labStandardItem/preImportLabStandardItemMethod4Excel.action',
					title:'标准量化导入',
					opacity:0.4,
					width:400, 
					height:300,
					lock:true,
					max:false,
					min:false,
					close:function(){
						thisFlush();
					}
				 });
			}
			function exportSubmit(url){
				window.location.href="<%=basePath %>"+url;
			}
			function thisFlush(){
		    	$('#form').attr('action','${basePath}'+'klg/labStandardItem/listLabStandardItemMethod.action');
				$('#form').submit();
		    }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labStandardItemMethodForm" id="form">
			<input type="hidden" name="labStandardVo.standTypeName" value="${labStandardVo.standTypeName}" />
			<input type="hidden" name="labStandardVo.standTypeId" value="${labStandardVo.standTypeId}" />
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
												${funName }：首页
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
																		标准名称：
																	</label>
																</td>
																<td>
																	<input type="text" name="labStandardVo.searchName" value="${labStandardVo.searchName}" />
																</td>
																<td>
																	<label>
																		标准编号：
																	</label>
																</td>
																<td>
																	<input type="text" name="labStandardVo.searchCode" value="${labStandardVo.searchCode}" />
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="submitAction();" value="fun.query" />
																</td>
																<td>
																	<l:a uri="klg/labStandardItem/preAddLabStandardItemMethod.action" onclick="importStandardItemMethod();return fasle;" value="import" />
																</td>
																<td>
																	<l:a uri="klg/labStandardItem/listLabStandardItemMethod.action" onclick="exportSubmit('klg/labStandardItem/exportLabStandardItemMethod4Excel.action');" value="export" />
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
												<th property="name">
													标准名称
												</th>
												<th property="code">
													标准编号
												</th>
												<th property="standIndex">
													<s:text name="regular.no"/>型号
												</th>
												<th>
													<s:text name="projected"/>
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
																<l:a href="#" uri="klg/labStandardItem/showLabStandard.action?id=${id}" value="${name}" />
															</td>
															<td class="l">
																${code }
															</td>
															<td class="l">
																${standIndex }
															</td>
															<td class="c">
																${fn:length(list)}
															</td>
															<td class="c">
																<l:a href="#" uri="klg/labStandardItem/preUpdateLabStandardItemMethod.action?id=${id}" value="maintain" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labStandardItemMethodForm" flush="true" /></td>
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
