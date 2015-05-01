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
										<span>${funName}<s:text name="information"/></span>
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
											<td class="r" width="150">
												<label>
													类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：
												</label>
											</td>
											<td id="TD3">
												${labSciProcessVo.type }
											</td>
											<td id="TD1" class="r" width="150">
												<label>
													<s:text name="sam.number"/>：
												</label>
											</td>
											<td id="TD2">
												${labSciProcessVo.sampRegisterNum }                               
											</td>
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
								<!-- 表单型表格（用于新增/修改页面）结束 -->
								<s:if test="${fn:length(sampList) > 0 }">
									<div class="Formtable">
										<div class="Formtabletitle">
													<s:text name="checking.content"/>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div id="Tab01">
											<table class="FormtableCon_sform" cellspacing="1"
												cellpadding="0">
												<thead>
													<tr>
														<th width="100">
															<s:text name="batch.no"/>
														</th>
														<th width="100">
															<s:text name="origin.number"/>
														</th>
														<th width="190">
															<s:text name="sam.name"/>
														</th>
														<th width="110">
															<s:text name="sam.type"/>
														</th>
														<th>
															<s:text name="checking.item"/>
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="sampList" status="st">
														<tr>
															<td width="100">
																${sampCode}
															</td>
															<td width="100">
																${oldNo}
															</td>
															<td>
																${name}
															</td>
															<td>
																${SamTypeName}
															</td>
															<td>
																<textarea style="width: 60%; overflow: hidden;"
																	name="sampList[${st.index}].itemName"
																	id="itemName${st.index}"
																	onclick="showLabItem4Select('${st.index}');"
																	readonly="readonly">${itemName}</textarea>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
								</s:if>
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
