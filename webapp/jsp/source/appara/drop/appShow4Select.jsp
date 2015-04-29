<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property
				value="#session.SessionContainer.orgUnit" /></title>
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
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
       	function submitx(){
       		var appId=$('#appId').val();
       		var appName=$('#appName').val();
       		var appNo=$('#appNo').val();
       		var specification=$('#specification').val();
       		$('#appId',D).val(appId);
       		$('#appName',D).val(appName);
       		$('#appNo',D).val(appNo);
       		closeMe();
       	}
       	function checkUser(){
       		var ids="";
       		var names="";
       		var nos="";
       		var specs="";
       		$('input[type="radio"]:checked').each(function(){
       			ids+=$(this).val()+",";
       			nos+=$(this).parent().prev().prev().prev().html()+",";
       			names+=$(this).parent().prev().prev().html()+",";
       			specs+=$(this).parent().prev().html()+",";
       		});
       		if(ids.length>1){
       			ids=ids.substring(0,ids.length-1);
       		}
       		if(names.length>1){
       			names=names.substring(0,names.length-1);
       		}
       		if(nos.length>1){
       			nos=nos.substring(0,nos.length-1);
       		}
       		if(specs.length>1){
       			spec=specs.substring(0,specs.length-1);
       		}
       		$('#appId').val(ids);
       		$('#appName').val(names);
       		$('#appNo').val(nos);
       		$('#spec').val(specs);
       	}
       	$(document).ready(function(){
       		var id=$("#appId",D).val();
       		$('input[name="appId"]').each(function(){
       		if($(this).val()==id){
       		$('input[value="'+id+'"]').prop('checked',true);
       		$('#appId').val($("#appId",D).val());
       		$('#appName').val($("#appName",D).val());
       		$('#appNo').val($("#appNo",D).val());
       		$('#spec').val($("#spec",D).val());
       		}
       		})
       	});
</script>
	</head>

	<body class="" id="mainid" style="overflow-y: auto;">
		<!-- pageResule query -->
		<form action="" method="post" name="listform" theme="simple">
			<input type="hidden" name="funId" value="${funId}" />
			<table id="bodyTable" class="bodytable_pop" width="98%"
				cellspacing="0" cellpadding="0" border="0">
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
													<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnEdit" class="zPushBtn"
																		href="javascript:void(0);" onclick="submitx()"><img
																			height="20" width="20"
																			src="<%=basePath%>img/filesave.gif" /><b><s:text name="page.confirm"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="listTable_pop" cellspacing="0"
											cellpadding="0">
											<tr>
												<th class="c" width="30">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="no">
													<s:text name="app.no"/>
												</th>
												<th property="name">
													<s:text name="app.name"/>
												</th>
												<th property="spec">
													<s:text name="regular.sys"/>
												</th>
												<th>
													<s:text name="selected"/>
												</th>
											</tr>
											<s:if test="pageResult.resultList!=null">
												<s:set name="alllist" value="pageResult.resultList" />
												<s:set name="isurgent" value="0" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<s:property value="#st.index+1" />
														</td>
														<td class="l">
															${no}
														</td>
														<td class="l">
															${name}
														</td>
														<td class="l">
															${spec}
														</td>
														<td class="c">
															<input type="radio" value="${id}" name="appId"
																id="id${st.index}" onclick="checkUser();" />
														</td>
													<tr>
												</s:iterator>
												<tr>
													<td class="c" colspan="5">
														<input type="hidden" value="" id="appName" />
														<input type="hidden" value="" id="appId" />
														<input type="hidden" value="" id="appNo" />
														<input type="hidden" value="" id="spec" />
													</td>
												</tr>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=listform" flush="true" /></td>
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
