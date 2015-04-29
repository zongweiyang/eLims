<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body id="mainid">
		<form method="post" name="form" enctype="multipart/form-data" id="form">
		<input name="labSamMainVo.id" value="${labSamMainVo.id }" type="hidden" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
												${funName}：
												<span><s:text name="details.info"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="sam/labSam/updateLabSam4Receive.action" value="post.commit" onclick="javascript:if(confirm('确认要提交吗?')){goAction('updateLabSam4Receive.action');}return false;" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title="点击此处，隐藏/显示该信息">
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="sam.no"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.sampNo}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="attachment.large"/>：
														</label>
													</td>
													<td colspan="">
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span> <a href="<%=basePath%>${path}" id="fileId">${name } </a> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable oneEachDiv" style="display: black;">
										<div class="Formtabletitle">
											<span><s:text name="sam.info"/></span>
											<label style="float: right;padding-right: 10px;"></label>
										</div>
										<table class="FormtableCon" id="oneEach">
										<s:iterator status="st" value="labSamMainVo.labSamVoList">
											<tr key="0">
												<td><label><s:text name="sam.no"/>：</label></td>
												<td>${seqNum }</td>
												<td><label><s:text name="sam.name"/>：</label></td>
												<td>${name }</td>
												<td><label><s:text name="regular.no"/>：</label></td>
												<td>${specifications }</td>
												<td><label><s:text name="sam.number"/>：</label></td>
												<td>${total }</td>
												<td><label><s:text name="save.style"/>：</label></td>
												<td>${saveMode }</td>
												<td><label><s:text name="sam.site"/>：</label></td>
												<td>${sampAddr }</td>
												<td><label><s:text name="sam.classify"/>：</label></td>
												<td>${samTypeName}</td>
											</tr>
											</s:iterator>
										</table>
										</div>
										<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="rev.info"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="received.if"/>：
															</label>
														</td>
														<td>
															<input type="radio" name="labSamMainVo.auditResult" value="1" checked="checked"/> <s:text name="lab.yes"/>
															<input type="radio" name="labSamMainVo.auditResult" value="-1"/>  <s:text name="lab.no"/>
														<td>
															<label>
																<s:text name="remark"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSamMainVo.auditMessage"></textarea>
														</td>
													</tr>
												</table>
											</div>
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
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			
		}
	</script>
</html>
