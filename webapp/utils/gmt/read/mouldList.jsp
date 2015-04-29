<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function deleteOne(id){
			if(confirm('确定删除吗?')){
				submitvalue('pageName/mould/deleteMould.action?mouldVo.ids='+id);
			}	
		}
		function deleteBath(){
			if(check('mouldVo.ids')){
				if(confirm('确定删除吗?')){
					submitvalue('pageName/mould/deleteMould.action');
				}	
			}		
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
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
		<form action="" method="post" name="mouldFrom" id="mouldFrom">
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
												<span>首页</span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="pageName/mould/preAddMould.action" value="新增" />
																</td>
																<td>
																	<l:a uri="pageName/mould/deleteMould.action"
																		onclick="deleteBath();" value="批量删除" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1"
											cellpadding="0">
											<thead>
												<tr>
													<th class="w50">
														<input type="checkbox" id="allCheckBox" key="mouldVo.ids" />
													</th>
													<th class="w50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<!-- Header Insert Position -->
													<th>
														操作
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
																	<input type="checkbox" name="mouldVo.ids" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<!-- Body Insert Position -->
																<td class="c">
																	<l:a href="#"
																		uri="pageName/mould/preUpdateMould.action?mouldVo.id=${id}"
																		value="修改" />
																	<l:a href="#"
																		uri="pageName/mould/deleteMould.action?mouldVo.ids=${id}"
																		onclick="deleteOne('${id}');" value="删除" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="9999" align="center" valign="middle">
															<font color="red">暂无信息</font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=mouldFrom" flush="true" /></td>
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