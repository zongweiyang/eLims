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
.workingBody{
	width: 99%;
}
</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	       	function submitvalue(actionUrl){
	       			var ids="";
	       			var name="";
	       			$('input[name=labStandardVo.ids]:checked').each(function (){
		       			var check=$(this);
		       			if(check.val()!='on'){
			       			name+=$(this).parent().next().next().children().eq(0).html();
			       			ids+=$(check).val();
			       			ids+=",";
			       			name+=",";
	       				}
	       			});	
	       			if(name.length>0){
	       				name=name.substring(0,name.length-1);
	       			}
	       			if(ids.length>0){
	       				ids=ids.substring(0,ids.length-1);
	       			}
	       			$("#replaceName",D).val(name);
	       			$("#replaceIds",D).val(ids);
	       			api.close();
	       	}	
	       	$(function(){
	       		var ids=$("#replaceIds",D).val();
	       		$('input[type=checkbox]').each(function (){
		       			if(ids.indexOf($(this).val())>-1){
		       				$(this).attr("checked","checked");
		       			}
	       			});	
	       	});	
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="labStanardForms" id="form">
			<table id="bodyTable" class="bodytable_pop" width="98%" cellspacing="0"
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
									<div class="myworkingbox_pop">
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
																<td>
																	<l:a uri="klg/labStandard/updateLabStandard4Status.action" onclick="submitvalue('updateLabStandard4Status.action');" value="确定选择"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<tr>
												<th>
													<input type="checkbox"
														onclick="if(this.checked==true) { checkAll('labStandardVo.ids'); } else { clearAll('labStandardVo.ids'); }" />
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
													类别
												</th>
											</tr>
											<s:if test="listLabStandardVo!=null">
												<s:set name="alllist" value="listLabStandardVo" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<input type="checkbox" name="labStandardVo.ids"
																id="ids<s:property value="#st.index+1" />"
																value="${id}" />
														</td>
														<td class="c">
															${st.index+1}
														</td>
														<td class="l">
															<l:a href="#"
																uri="klg/labStandard/showLabStandard.action?id=${id}"
																value="${name }" />
														</td>
														<td class="l">
															${code }
														</td>
														<td class="l">
															${standIndex }
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td colspan="7" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
