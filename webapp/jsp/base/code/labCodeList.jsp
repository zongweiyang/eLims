<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />

		<script language="javascript" type="text/javascript">
	function submitvalue(actionstr) {
    		$('form').attr('action',actionstr);
			$('form').submit();
	}
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
      			return false;
		}else{
			return true;
		}
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
	<body class="" id="mainid">
		<form action="" method="post" name="commcodeform">
			<input type="hidden" name="labCodeVo.typeid" id="typeid"
				value="${labCodeVo.typeid }" />
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
												<span><s:text name="lab.msgmaintain.index"></s:text></span>
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
																	<l:a uri="back" value="lab.code.return" />                    
																</td>
																<td>
																	<l:a uri="code/labCode/preAddLabCode.action?labCodeVo.typeid=${labCodeVo.typeid}" value="lab.code.add" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn"
																		href="javascript:void();"
																		onclick="javascript:if(check('labCodeVo.ids')){if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){submitvalue('<%=basePath%>code/labCode/deleteLabCode.action');}} return false;"><img
																			height="20" width="20"
																			src="<%=basePath%>img/shanchu.gif" /><b><s:text name="lab.code.deleteall"/></b> </a>
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

													<th>
														<input type="checkbox" id="ids"
															onclick="if(this.checked==true) { checkAll('labCodeVo.ids'); } else { clearAll('labCodeVo.ids'); }" />
													</th>

													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="lab.code.name"></s:text>
													</th>
													<th>
														<s:text name="lab.code.number"></s:text>
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
																	<s:if test="${remark!='1'}">
																		<input type="checkbox" name="labCodeVo.ids"
																			id="ids<s:property value="#st.index+1" />"
																			value="${id}" class="reaCoder" />
																	</s:if>
																	<s:else>
																		<input type="checkbox" name="labCodeVo.ids"
																			value="${id}"/>
																	</s:else>
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	<s:property value="name" />
																</td>
																<td class="l">
																	<s:property value="code" />
																</td>
																<td class="c">
																	<l:a href="#"
																		uri="code/labCode/preUpdateLabCode.action?labCodeVo.typeid=${labCodeVo.typeid}&labCodeVo.id=${id }"
																		value="lab.code.modify" />
																	<a href="<%=basePath%>code/labCode/deleteLabCode.action?labCodeVo.typeid=${labCodeVo.typeid}&labCodeVo.ids=${id }"
																		onclick="javascript:if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){return true;}return false;"
																		class="delete"><s:text name="lab.code.del"></s:text></a>
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
								<td align="center">
									<jsp:include
										page="/jsp/include/page.jsp?actionparam=listLabCode.action&formName=commcodeform"
										flush="true" />
								</td>
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
	</body>
</html>