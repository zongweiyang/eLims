<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<%@ include file="/jsp/include/common.jsp"%>
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
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('labStandardVo.ids');
				if(checkbox==0){
					validate.tip('请选择要删除的条目.',$('labStandardVo.ids'));
					return ;
				}
				if(confirm('与该标准的标准量化信息会删除，确定删除？')){
					goAction(url);
				}
				return ;
			}
			function deleteOne(id){
				if(confirm('确定要删除吗?')){	 
			      window.location.href='<%=basePath%>klg/labStandard/deleteLabStandard.action?labStandardVo.ids='+id;
			    }
			}
			function impotExcel(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>klg/labStandard/preImportLabStandard4Excel.action?labStandardVo.standTypeId=${labStandardVo.standTypeId}&labStandardVo.standTypeName=${labStandardVo.standTypeName}',
					title:'<s:property value="getText('stdimport')"/>',
					opacity:0.4,
					width:400, 
					height:200,
					lock:true,
					max:false,
					min:false,
					close:function(){
						thisFlush();
					}
				 });
			}
			function thisFlush(){
		    	$('#labStanardForm').attr('action','${basePath}'+'klg/labStandard/listLabStandard.action');
				$('#labStanardForm').submit();
		    }
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" id="labStanardForm" name="labStanardForm" id="form">
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
												${funName }：
												<s:if test="${labStandardVo.standTypeName == '' }">
													<span><s:text name="top.index"/></span>
												</s:if>
												<s:else>
													<span>[<font color="blue">${labStandardVo.standTypeName }</font>] </span>
												</s:else>
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
																		<s:text name="std.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labStandardVo.searchName" value="${labStandardVo.searchName}" />
																</td>
																<td>
																	<label>
																		<s:text name="biaozcode"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labStandardVo.searchCode" value="${labStandardVo.searchCode}" />
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
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="klg/labStandard/preAddLabStandard.action" onclick="goAction('/klg/labStandard/preAddLabStandard.action');"  value="admin.add"/>
																</td>
																<td>
																	<l:a uri="klg/labStandard/deleteLabStandard.action" onclick="deleteEntityForBatch('/klg/labStandard/deleteLabStandard.action');" value="lab.code.deleteall" />	
																</td>
																<td>
																	<l:a uri="klg/labStandard/preAddLabStandard.action" onclick="impotExcel();return false;"  value="inbiaozhun"/>
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
													<input type="checkbox" onclick="if(this.checked==true) { checkAll('labStandardVo.ids'); } else { clearAll('labStandardVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="name">
													<s:text name="std.name"/>
												</th>
												<th property="code">
													<s:text name="biaozcode"/>
												</th>
												<th property="standIndex">
													<s:text name="biaolenave"/>
												</th>
												<th property=standStatus>
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
																<input type="checkbox" name="labStandardVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																<l:a href="#" uri="klg/labStandard/showLabStandard.action?id=${id}" value="${name }"/>
															</td>
															<td class="l">
																${code }
															</td>
															<td class="l">
																${standIndex }
															</td>
															<td class="c">
																<s:if test="${isUsed == 'N' }">
																	被替换
																</s:if>
																<s:else>
																	使用中
																</s:else>
															</td>
															<td class="c">
																<l:a href="#" uri="klg/labStandard/preUpdateLabStandard.action?id=${id}" value="lab.code.modify"/>
																&nbsp;&nbsp;
																<l:a href="#" uri="klg/labStandard/deleteLabStandard.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labStanardForm" flush="true" /></td>
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
