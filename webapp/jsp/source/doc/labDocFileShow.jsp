<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet"
			type="text/css" id="theme" />
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>
<script>
var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	function closeMe(){
		api.close();
	}
</script>
	</head>
	<body class="" id="mainid" style="height: 100%;">
		<s:form theme="simple" action="" method="post" name="labDocForm">
			<input type="hidden" name="labDocVo.pid" value="${labDocVo.pid}" />
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
										<!-- 按钮条 结束-->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																<span>${path}&nbsp;&nbsp;[<font color="red">${labDocVo.fileName}-<s:text name="details.info"/></font>]</span>
																<!--<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																	onclick="closeMe();return false;"><img
																		height="20" width="20"
																		src="<%=basePath%>img/zhongzhi.gif" /><b><s:text name="closednow"/></b> </a>
																--></td>
															</tr>
															<tr>
															<td>&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div> 
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<td class="r">
													<label>
														<s:text name="file.name"/>
													</label>
												</td>
												<td>
													${labDocVo.fileName }
												</td>
											</tr>
											<tr>
												<td class="r">
													<label>
														<s:text name="filetypess"/>
													</label>
												</td>
												<td>
													${labDocVo.docType }
												</td>
											</tr><tr>
												<td class="r">
													<label>
														<s:text name="createdate"/>
													</label>
												</td>
												<td>
													${labDocVo.createTime }
												</td>
											</tr>
											<tr>
												<td class="r">
													<label>
														附件
													</label>
												</td>
												<td>
													<s:if test="#request.loadList!=null">
														<s:iterator value="#request.loadList" status="st">
															<span>
																<a href="${path }" id="fileId">${name } </a>
															<a href="javascript:;"
																id="fileIcon" onclick="deleteUploadFile(this,'${id }')"></a>
															</span>
														</s:iterator>
													</s:if>
												</td>
											</tr>
											<tr>
												<td class="r" style="height: 80">
													<label>
														<s:text name="filedoc"/>
													</label>
												</td>
												<td>
													${labDocVo.remark }
												</td>
											</tr>	
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
