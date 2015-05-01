<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



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
			</script>
	<body>
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
					<div class="myworkingbox">
						<form action="" method="post" name="mouldForm">
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="info111"/></span>
									</div>
									<table class="FormtableCon">
											<tr>
												<td class="r" width="150"><label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label></td>
												<td colspan="3">${labSciProcessVo.type }</td>
											</tr>
											<tr>
												<td class="r" width="150"><label>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label></td>
												<td>${labSciProcessVo.name }</td>
												<td class="r" width="150"><label>填&nbsp;&nbsp;写&nbsp;&nbsp;人：</label></td>
												<td>${labSciProcessVo.writeUser }</td>
											</tr>
											<tr>
												<td class="r" width="150"><label>开始时间：</label></td>
												<td>${labSciProcessVo.startTime }</td>
												<td class="r" width="150"><label>结束时间：</label></td>
												<td>${labSciProcessVo.endTime }</td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="contentdesd"/>：</label></td>
												<td colspan="3"><textarea readonly="readonly" valType="required" msg='<s:property value="getText('connotempty')"/>'  name="labSciProcessVo.contents" cols="40" rows="3" id="contents" >${labSciProcessVo.contents }</textarea></td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="lab.remark"/>：</label></td>
												<td colspan="3"><textarea readonly="readonly" name="labSciProcessVo.remark" cols="40" rows="3" id="remark" >${labSciProcessVo.remark }</textarea></td>
											</tr>
											<tr>
												<td class="r">
													<label>
														<s:text name="upload.attachment"/>：
													</label>
												</td>
												<td colspan="3">
														<s:if test="${fn:length(uplodeList)>0}">
													<s:iterator status="st2" value="#request.uplodeList" id="">
														<span> ${name} 
																		<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;
																	<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}"">
																	    <img src="<%=basePath %>/img/query.gif"/>
																	</a>
																	<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
																</s:if></span>	
													</s:iterator>&nbsp;&nbsp;&nbsp;
												</s:if>
												<div id="upfiles"></div>
												</td>
											</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
