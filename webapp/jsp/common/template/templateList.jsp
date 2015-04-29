<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
	function submitvalue(actionstr){
		$('#labTemplateForm').attr('action','<%=basePath%>'+actionstr).submit();
	}
	function checkDelete(actionstr){
		if(confirm("是否删除？")){
			window.location.href = '<%=basePath%>'+actionstr;
		}
	}
	function selectCheck(actionstr) {
		if($('input[name="labTemplateVo.ids"]:checked').length == 0){
			validate.tip('请只少选择一项',null,1);
			return ;
		}
		if(confirm("是否删除？")){
			submitvalue(actionstr);
		}
	}
	function selectChange(obj){
		if($(obj).prop('checked')){
			$('input[name="labTemplateVo.ids"]').each(function(){
				$(this).prop('checked',true);
			});
		}else{
			$('input[name="labTemplateVo.ids"]').each(function(){
				$(this).prop('checked',false);
			});
		}
	}
	function addTemplate(url){
		var height = window.screen.height-250;
		var url  = '<%=basePath%>' + url;
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'模板定义',
			opacity:0.4,
			width:1050,
			height:height,
			max:false,
			min:false,
			lock:true
		 });
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
		<form action="" method="post" name="labTemplateForm" id="labTemplateForm">
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
																		<s:text name="tmp.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labTemplateVo.name" value="${labTemplateVo.name}" id="name" />
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="template/labTemplate/preAddLabTemplate.action" value="lab.code.add" />
																</td>
																<td>
																	<l:a uri="template/labTemplate/preAddLabTemplate.action" onclick="selectCheck('template/labTemplate/updateLabTemplate4Del.action');" value="lab.code.deleteall" />
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
														<input type="checkbox" onclick="selectChange(this);" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th  property="name">
														<s:text name="tmp.name"/>
													</th>
													<th  property="type">
														<s:text name="tmp.type"/>
													</th>
													<th  property="busId">
														<s:text name="tmp.biz"/>
													</th>
													<th>
														<s:text name="remark"/>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult.resultList != null && pageResult.resultList.size > 0">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labTemplateVo.ids" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="l">
																<s:if test="${type == 'excel'}">
																	<s:text name="excel.tmp"/>
																</s:if>
																<s:if test="${type == 'word'}">
																	<s:text name="word.tmp"/>
																</s:if>
															</td>
															<td class="l">
																${busName}
															</td>
															<td class="l">
																${remark}
															</td>
															<td class="c">
																<l:a href="#" uri="template/labTemplate/preUpdateLabTemplate4Edit.action?labTemplateVo.id=${id}" onclick="addTemplate('template/labTemplate/preUpdateLabTemplate4Edit.action?labTemplateVo.id=${id}');" value="def.model" />
																<l:a href="#" uri="template/labTemplate/preUpdateLabTemplate.action?labTemplateVo.id=${id}" value="lab.code.modify" />
																<l:a href="#" uri="template/labTemplate/updateLabTemplate4Del.action?labTemplateVo.ids=${id}" onclick="checkDelete('template/labTemplate/updateLabTemplate4Del.action?labTemplateVo.ids=${id}');" value="lab.code.del" />
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="6" align="center" valign="middle">
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
							<tr>
								<td align="center"><jsp:include page="../../../jsp/include/page.jsp?formName=labTemplateForm" flush="true" /></td>
							</tr>
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