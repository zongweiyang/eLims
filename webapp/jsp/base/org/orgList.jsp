<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<%@ include file="/jsp/include/common.jsp"%>
		<%@ taglib prefix="l" uri="/WEB-INF/tld/labsoft/lab.tld"%>
		<script>
			function check(name){
				var el = document.getElementsByTagName('input');     
				var len = el.length; 
				var m = 0;    
				for(var i=0; i<len; i++)
				{         
					if((el[i].type=="checkbox") && (el[i].name==name))         
					{             
						if(el[i].checked == true){
					    	m = m + 1;
					 	}      
					}     
				}  
				if(m<1){
					alert('<s:property value="getText('deleted.record')"/>');
					return false;
				}else{
					return true;
				}
			}
	function submitvalue(actionstr){
		if(actionstr.indexOf('Update')>-1||actionstr.indexOf('Add')>-1){
			$("#form").attr("target","");
		}else{
			$("#form").attr("target","workarea");
		}
		$('form').attr('action',actionstr);
		$('form').submit();
	}

	function deleteThis(){
		if(check('labOrgVo.ids')){
			if(confirm('<s:property value="getText('confirm.deleted')"/>')){
				submitvalue('<%=basePath%>org/labOrg/deleteBatchLabOrg.action');
			}	
		}		
	}
	function deleteOne(id){
		if(confirm('<s:property value="getText('confirm.deleted')"/>')){
			submitvalue('<%=basePath%>org/labOrg/deleteLabOrg.action?labOrgVo.id='+id);
		}		
	}
	function move(id){
		$.dialog({
			id:'parentFunction',
			content:'url:'+'<%=basePath%>org/labOrg/preRadioTreeLabOrg.action?labOrgVo.id='+id+'&labOrgVo.parentId='+$("#parentId").val(),
			title:'<s:property value="getText('org.transition')"/>',
			opacity:0.4,
			width:500, 
			height:300,
			lock:true,
			max:false,
			min:false
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
		<form id="form" theme="simple" action="" method="post" name="OrgForm">
			<input type="hidden" name="labOrgVo.parentId" id="parentId" value="${labOrgVo.parentId}" />
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
												<span><s:text name="top.index"></s:text></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:if test="${labOrgVo.isOper=='N'}">
																		<l:a uri="org/labOrg/preAddLabOrg.action?labOrgVo.parentId=${labOrgVo.parentId}" value="lab.code.add" onclick="javascript:alert('超出最大限制.');return false;"/>
																	</s:if>
																	<s:else>
																		<l:a uri="org/labOrg/preAddLabOrg.action?labOrgVo.parentId=${labOrgVo.parentId}" value="lab.code.add" />
																	</s:else>
																</td>
																<td>
																	<l:a uri="org/labOrg/deleteBatchLabOrg.action" onclick="deleteThis();" value="lab.code.deleteall" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													</th>
													<th class="td_cb">
														<input type="checkbox" id="allCheckBox" key="labOrgVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>

													<th property="name">
														<s:text name="config.name"></s:text>
													</th>
													<th property="linkMan">
														<s:text name="org.people"></s:text>
													</th>
													<th property="type">
														<s:text name="org.type"></s:text>
													</th>
													<th property="isUsed">
														<s:text name="can.use"></s:text>
													</th>
													<th>
														<s:text name="remark"></s:text>
													</th>
													<th>
														<s:text name="lab.code.ops"></s:text>
													</th>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">

															<tr>

																<td class="c">
																	<input type="checkbox" name="labOrgVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>
																	<a href="<%=basePath%>/org/labOrg/showLabOrg.action?labOrgVo.id=${id}"><s:property value="name" /> </a>
																</td>
																<td class="c">
																	${linkMan}
																</td>
																<td class="c">
																	${type}
																</td>
																<td class="c">
																	<s:if test="${isUsed=='Y'}"><s:text name="lab.yes"></s:text></s:if>
																	<s:else><s:text name="lab.no"></s:text></s:else>
																</td>
																<td>
																	${remark}
																</td>
																<td class="c">
																	<l:a href="#" uri="org/labOrg/preUpdateLabOrg.action?labOrgVo.id=${id}" value="lab.code.modify" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="org/labOrg/deleteLabOrg.action" onclick="deleteOne('${id}');return false;" value="lab.code.del" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="org/labOrg/preRadioTreeLabOrg.action" onclick="move('${id}');return false;" value="fun.transfer" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"></s:text></font>
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
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=OrgForm" flush="true" />
								</td>
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
