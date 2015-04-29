<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=basePath%>utils/kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="<%=basePath%>utils/kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/plugins/code/prettify.js"></script>
		<title>SaaS For LIMS - LabSoft</title>

		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<script language=javascript>
		function submitvalue(actionstr) {

			var df = document.messageForm;
			df.action = actionstr;
			df.submit();
		} 
		KindEditor.ready(function(K) {
			var editor = K.create('#content', {
					readonlyMode : true,
					autoHeightMode : true,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
					afterCreate : function() {
						this.loadPlugin('autoheight');
					}
				});
		});
		function submitvalue(actionstr) {
			var df = document.messageForm;
			df.action = actionstr;
			df.submit();
		} 
</script>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								<s:text name="msg.msg.manage"></s:text>：
								<span><s:text name="msg.show.msg"></s:text></span>
							</h2>
						</div>
						<form action="update1.action" method="post" name="messageForm">
							<div>
								<!-- 按钮条 开始 -->
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="TabTable" style="padding-top: 0; margin: 0; background: none;">
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="msg.detail"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td class="w100">
													<label>
														<s:text name="msg.sender"></s:text>：
													</label>
												</td>
												<td class="w300">
													${labMsgDetailVo.senderName}
												</td>
											</tr>
											<tr>
												<td class="w100">
													<label>
														<s:text name="msg.rev.er"></s:text>：
													</label>
												</td>
												<td class="w300">
													${labMsgDetailVo.receiveName}
												</td>
											</tr>
											<tr>
												<td class="w100">
													<label>
														<s:text name="msg.subjuct.blank"/>：
													</label>
												</td>
												<td>
													${labMsgDetailVo.subject}
												</td>
											</tr>
											<tr>
												<td class="w100">
													<label>
														<s:text name="msg.attachment.blank"/>：
													</label>
												</td>
												<td>
													<s:if test="#request.loadList!=null">
														<s:iterator value="#request.loadList" status="st">
															<span>
																${name}
																<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}""><img src="<%=basePath %>/img/query.gif"/></a>
																</s:if>
																&nbsp;&nbsp;<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
															</span>
														</s:iterator>
													</s:if>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<textarea name="labMsgMainVo.content" id="content" style="width:100%;height:300px;visibility:hidden;">${labMsgDetailVo.content}</textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</td>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>