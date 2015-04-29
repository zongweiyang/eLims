<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	    function closeMe(){
			api.close();
	    }
		function submitvalue(actionstr){
			$('form').attr('action',actionstr);
			$('form').submit();
		}
		function goToNextAction(actionstr){
			window.location.href = '<%=basePath%>page/labPageEditor/'+actionstr;
		}
		function deletePage(actionstr){
			if(confirm('确认要删除吗？')){
				window.location.href = '<%=basePath%>page/labPageEditor/'+actionstr;
			}
		}
	function select(url,id){
		$.ajax({
			url:'<%=basePath%>page/labPageEditor/ajaxIsFileExist.action',
			data:{'labPageEditorVo.id':id},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data=="false"){
					alert("此文件不存在");
					return ;
				}else{
					$("#jspUrl",D).val(url);
					$("#jspUrlId",D).val(id);
					$("#showFont",D).show();
					closeMe();
				}
			}
	   });
		
	}
</script>
		<style>
.myworkingbox {
	min-height: 300px;
}

#mainid {
	width: 98.5%;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labPageForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0" style="min-height: 100px;">
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
												<s:text name="formula.edit"/>：
												<span><s:text name="file.list"/></span>
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
																		<s:text name="cn.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labPageEditorVo.name" value="${labPageEditorVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="file.name"/>：
																	</label>
																</td>
																<td>
																	<input id="fileName" name="labPageEditorVo.fileName" value="${labPageEditorVo.fileName}" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="submitvalue('showPageFormula4Select.action'); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b> </a>
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
																	<label>
																		<s:text name="file.info.list"/>：
																	</label>
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
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="cn.name"/>
													</th>
													<th property="url">
														<s:text name="file.name"/>
													</th>
													<th property="url">
														<s:text name="file.path"/>
													</th>
													<th width="100">
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="l">
																${fileName}
															</td>
															<td class="l">
																${url}
															</td>
															<td class="c">
																<a href="#" onclick="select('${url}','${id}')"><s:text name="selected"/></a>
															</td>
														</tr>
													</s:iterator>
												</s:if>
												<s:else>
													<tr>
														<td colspan="8" align="center" valign="middle">
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
								<td align="center"><jsp:include
								page="/jsp/include/page.jsp?formName=labPageForm"
								flush="true" /></td>
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