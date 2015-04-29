<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
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
				if(confirm('<s:property value="getText('confirm.deleted.select')"/>')){
					goToNextAction(url);
				}
				return ;
			}
			function deleteEntity4Batch(url){
				var checkbox=validationCheckbox('labSamTypeVo.ids');
				if(checkbox==0){
					alert('<s:property value="getText('select.one.m')"/>');
					return ;
				}
				deleteEntity(url);
			}
			function deleteObject(url){
				if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
					window.location.href=url;
				}
			}
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="form">
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
																		<s:text name="sam.classify.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSamTypeVo.searchName" value="${labSamTypeVo.searchName}"/>
																</td>
																<td>
																<l:a uri="${pageResult.action}"  onclick="submitAction();" value="fun.query"/>
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
																<l:a uri="sam/labSam/deleteLabSamType4Batch.action" onclick="deleteEntity4Batch('/sam/labSam/deleteLabSamType4Batch.action');return false;" value="lab.code.deleteall"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<tr>
												<th>
													<input type="checkbox" onclick="if(this.checked==true) { checkAll('labSamTypeVo.ids'); } else { clearAll('labSamTypeVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th>
													<s:text name="sam.classify.name"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:iterator value="pageResult.resultList" status="st">
												<tr title="${where}">
													<td class="c">
														<s:if test="${isDefault=='N'}">
															<input type="checkbox" name="labSamTypeVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" />
														</s:if>
														<s:else>
															&nbsp;
														</s:else>
													</td>
													<td class="c">
														${pageSizex* currenPagex+st.index+1}
													</td>
													<td class="l">
														<a href="${basePath }sam/labSam/showLabSamType.action?labSamTypeVo.id=${id}"> ${name}</a>
													</td>
													<td class="c">
														<s:if test="${isDefault=='N'}">
														<a href="javascript:;" onclick="editEntity('${id}->${pid}->${name}');return false;" ><s:text name="lab.code.modify"/></a>
														<a href="javascript:;" onclick="deleteObject('${basePath }sam/labSam/deleteLabSamType.action?labSamTypeVo.id=${id}')" ><s:text name="lab.code.del"/></a>	
														<a href="${basePath }sam/labSam/preAddLabSamType.action?labSamTypeVo.id=${id}" ><s:text name="relaitem"/></a>	
														</s:if>
														<s:else>
															<s:text name="config.modify"/>
															&nbsp;
															<s:text name="config.del"/>
														</s:else>
													</td>
												</tr>
											</s:iterator>
										</table>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr style="border: 0">
												<td>
													<label>
														<s:text name="up.level.name"/>
													</label>
													<s:select id="parentId" name="labSamTypeVo.pid" list="labSamTypeVo.labSamTypeVoList" headerKey="0" headerValue="样品分类" listKey="id" listValue="name"></s:select>
												</td>
												<td>
													<label>
														<s:text name="classify.name"/>
													</label>
													<input type="text" name="labSamTypeVo.name"  id="name" size="20" />
													<input type="hidden" name="labSamTypeVo.id"  id="id" size="20" />
												</td>
												<td>
												<l:a uri="sam/labSam/addLabSamType.action"  onclick="goToNextAction('sam/labSam/addLabSamType.action');return false;" key="saveButton" value="lab.code.save"/>
												<l:a uri="sam/labSam/addLabSamType.action"  onclick="goToNextAction('sam/labSam/updateLabSamType.action');return false;" key="updateButton" value="lab.code.save"/>
												</td>
											</tr>
										</table>
										<script>
											$(function (){
												$('a[key="updateButton"]').hide();
											})
											function editEntity(str){
												var id,parentId,name;
												$('#id').val(str.split('->')[0]);
												$('#name').val(str.split('->')[2]);
												$('#parentId').val(str.split('->')[1]);
												$('a[key="saveButton"]').hide();
												$('a[key="updateButton"]').show();
											}
										</script>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=form" flush="true" /></td>
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
