<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<%@ include file="/jsp/include/common.jsp"%>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			
			var index = '${labSamTypeVo.index}';
			function closeMe(){
			  	api.close();
		 	}
		 	function ok(){
		 		var ids="";
		 		var names="";
		 		var funIds="";
		 		$('input[name="labSamTypeVo.ids"]:checked').each(function(m){
		 			ids+=$(this).val()+",";
		 			names+=$(this).attr('key')+",";
		 		});
		 		if(ids.length>1){
		 			ids=ids.substring(0,ids.length-1);
		 			names=names.substring(0,names.length-1);
		 		}
		 		$('#samTypeId'+index,D).val(ids);
		 		$('#samTypeName'+index,D).val(names);
		 		closeMe();
		 	}
		 	
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#samTypetext {
	width: 70px;
    overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>

	</head>
	<body class="" id="mainid" style="height: 100%">
		<form theme="simple" action="" method="post" name="labSamTypeForm">
			<input type="hidden" name="sysSamTypeId" value="0" />
			<table id="bodyTable" class="bodytable" width="98%" cellspacing="0" cellpadding="0" border="0" style="min-height: 100px;">
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
									<div class="">
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="sam/labSam/addLabSam.action" onclick="ok();" value="page.confirm"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th width="40">
														
													</th>
													<th width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th width="100">
														<s:text name="sam.name"/>
													</th>
													
											</thead>
											<tbody>
												<s:iterator value="#request.samTypeVolist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labSamTypeVo.ids"
																	id="ids${st.index+1}" value="${id}" key="${name}"/>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c" title="${where }">
																${name}
															</td>
														
														</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script>
		$(function(){
			var samTypeIds=$('#samTypeId'+index,D).val();
			if(samTypeIds.length>0){
				var samTypes=samTypeIds.split(',');
				for(var i=0;i<samTypes.length;i++){
					var rid=samTypes[i].replace(' ','');
					$('input[value="'+rid+'"]').attr('checked','true');
				}
			}
			$('.nameLength').each(function(){
				var str=$(this).html();
				if(str.length>30){
					$(this).parent().parent().attr('title',str);
					$(this).html(str.substring(0,30)+"...");
				}
			});
		});
	</script>
</html>
