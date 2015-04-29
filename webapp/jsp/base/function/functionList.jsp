<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
	function submitvalue(actionstr){
		$('#sysFunctionform').attr('action','<%=basePath%>'+actionstr).submit();
	}
	function checkDeleteBatchFunction(){
		if($('input[name="labFunctionVo.ids"]:checked').length == 0){
			alert('<s:property value="getText('select.one.item')"/>');
			return ;
		}
		if(confirm('<s:property value="getText('confirm.deleted')"/>')){	
			var ids=$('#parentId').val();
			parent.location.href='<%=basePath%>/function/labFunction/frameLabFunction.action?labFunctionVo.parentId='+ids;
			goAction('updateLabFunction4Del.action');
			
		}
	}
	function checkDeleteFunction(id){
		$.ajax({
			url:'<%=basePath%>function/labFunction/ajaxIsCouldDelete.action',
		  	data:{'labFunctionVo.id':id},
		  	type:'post',
			dataType:'text',
			success:function (data){
				if(data == 'true'){
					if(confirm('<s:property value="getText('confirm.deleted')"/>')){	
						parent.location.href='<%=basePath%>/function/labFunction/frameLabFunction.action?labFunctionVo.parentId='+$('#parentId').val();	
						submitvalue('function/labFunction/updateLabFunction4Del.action?labFunctionVo.ids='+id);
					}
				}else{
					alert('<s:property value="getText('drop.downlayer')"/>');
					return ;
				}
			}
		});
	}
	function goToNextAction(actionstr){
		window.location.href = '<%=basePath%>function/labFunction/'+actionstr;
	}
	function updateParent(actionstr){
		$.dialog({
			id:'parentFunction',
			content:'url:'+'<%=basePath%>function/labFunction/'+actionstr,
			title:'<s:property value="getText('fun.transiton')"/>',
			opacity:0.4,
			width:400, 
			height:400,
			max:false,
			min:false,
			lock:true
		 });
	}
	function refeshPage(){
		window.location.href = '<%=basePath%>function/labFunction/listLabFunction.action?labFunctionVo.parentId='+$('#parentId').val();
		parent.location.href='<%=basePath%>function/labFunction/frameLabFunction.action?labFunctionVo.parentId='+$('#parentId').val();
	}
</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="sysFunctionform" id="sysFunctionform">
			<input type="hidden" name="labFunctionVo.parentId" id="parentId" value="${labFunctionVo.parentId}" />
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="function.name"></s:text>：
																	</label>
																</td>
																<td>
																	<input name="labFunctionVo.name" id="name" type="text" value="${labFunctionVo.name}"/>
																</td>
																<td>
																	<l:a uri="function/labFunction/listLabFunction.action" onclick="submitvalue('function/labFunction/listLabFunction.action');" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:if test="${labFunctionVo.isOper=='N'}">
																		<l:a uri="function/labFunction/preAddLabFunction.action?labFunctionVo.parentId=${labFunctionVo.parentId}" value="lab.code.add"  />
																	</s:if>
																	<s:else>
																		<l:a uri="function/labFunction/preAddLabFunction.action?labFunctionVo.parentId=${labFunctionVo.parentId}" value="lab.code.add" />
																	</s:else>
																</td>
																<td>
																	<l:a uri="function/labFunction/updateLabFunction4Del.action" onclick="checkDeleteBatchFunction();" value="lab.code.deleteall" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox" key="labFunctionVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="function.name"/>
													</th>
													<th>
														<s:text name="config.number"/>
													</th>
													<th>
														<s:text name="icon.addr"/>
													</th>
													<th>
														<s:text name="link.addr"/>
													</th>
													<th>
														<s:text name="show.ornot"/>
													</th>
													<th>
														<s:text name="config.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<s:if test="${isHaveSub == 'Y'}">
																		<input type="checkbox" name="labFunctionVo.ids" value="${id}" disabled="disabled" />
																	</s:if>
																	<s:else>
																		<input type="checkbox" name="labFunctionVo.ids" value="${id}" />
																	</s:else>
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	${name}
																</td>
																<td class="l">
																	${wfcode}
																</td>
																<td class="l">
																	${imageUrl}
																</td>
																<td class="l">
																	${url}
																</td>
																<td class="c">
																	<s:if test="${isDisplay == 'N'}"><s:text name="lab.no"/></s:if>
																	<s:else><s:text name="lab.yes"/></s:else>
																</td>
																<td class="c">
																	<l:a href="#" uri="function/labFunction/preUpdateLabFunction.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}" onclick="goToNextAction('preUpdateLabFunction.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}');" value="lab.code.modify" />
																	<s:if test="${isHaveSub == 'Y'}">
																		<l:a href="#" uri="function/labFunction/preUpdateLabFunction.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}" onclick="checkDeleteFunction('${id}');" disabled="true" value="lab.code.del" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="function/labFunction/preUpdateLabFunction.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}" onclick="checkDeleteFunction('${id}');" value="lab.code.del" />
																	</s:else>
																	<l:a href="#" uri="function/labFunction/showFunction4Select.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}" onclick="updateParent('showFunction4Select.action?labFunctionVo.id=${id}&labFunctionVo.parentId=${labFunctionVo.parentId}');" value="fun.transfer" />
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
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=sysFunctionform" flush="true" /></td>
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
		<%@ include file="../../../jsp/include/foot.jsp"%>
	</body>
</html>