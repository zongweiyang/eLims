<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<%@ include file="../../include/common.jsp"%>
		<script type="text/javascript" language="javascript">
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象

		function submit(){
				var df = D.labDocForm;	
		 		df.action="<%=basePath%>doc/labDoc/addLabDocFolder.action";
		  		df.submit();
        }
        
		function closeMe(){
		  	api.close();
	 	}
		function submitValue()	{
			if($('#fileName').val()==''){
				alert('<s:property value="getText('inputname')"/>');
				return false;
			}else{
				var df = document.labDocForm;	
		 		df.action="<%=basePath%>doc/labDoc/addLabDocFolder.action";
		  		df.submit();
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

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>

	</head>
	<body class="" id="mainid" style="height: 100%；">
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
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																	onclick="submitValue();return false;"><img
																		height="20" width="20"
																		src="<%=basePath%>img/accept.gif" /><b><s:text name="lab.code.save"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<td class="c">
													<label>
														<s:text name="config.name"/>
													</label>
												</td>
												<td colspan="2">
													<input name="labDocVo.fileName"  type="text" id="fileName"/>
													<input type=hidden id="docId" value="${labDocVo.docId }" />
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														<s:text name="remark"/>
													</label>
												</td>
												<td colspan="2">
													<textarea rows="4" cols="55" name="labDocVo.remark" valType="strLength"  max="512"  strLength-msg="备注长度不能超过512位"></textarea>
												</td>
											</tr>
											<tr>
												<td class="c" style="height: 80">
													<label>
														图标
													</label>
												</td>
												<td class="l" colspan="3">
													<input type="radio" value="folder_doc.png"
														name="labDocVo.docIcon"  checked="checked"/>
													<img src="<%=basePath%>img/folder_doc.png" height="70" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" value="folder_file.png"
														name="labDocVo.docIcon" />
													<img src="<%=basePath%>img/folder_file.png" height="70" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" value="folder_doc1.png"
														name="labDocVo.docIcon" />
													<img src="<%=basePath%>img/folder_doc1.png" height="70" />
												</td>
											</tr>
										</table>
									</div>
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
		</s:form>
		<script>
		var index = $("#docId").val();
		$(function(){
		if(index!=''){
		  		   W.flushThisPage();
		  		   closeMe()
		  		}
		
		})
		</script>
	</body>
</html>
