<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
</style>
	</head>
	<script language=javascript> 
	function submitvalue(actionstr){
		$('#templateForm').attr('action','<%=basePath%>'+actionstr).submit();
	}
	function backList(actionstr){
		window.location.href = '<%=basePath%>'+actionstr;
	}
</script>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
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
								${funName}：
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="" method="post" id="templateForm" name="templateForm" theme="simple">
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="template/labTemplate/listLabTemplate.action" onclick="backList('template/labTemplate/listLabTemplate.action');" value="lab.code.return" />
														</td>
														<td>
															<l:a uri="template/labTemplate/addLabTemplate.action" onclick="submitvalue('template/labTemplate/addLabTemplate.action');" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="tmp.info"/></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="tmp.name"/>：
											</label>
										</td>
										<td>
											<input type="text" name="labTemplateVo.name" valType="required,strLength" max="32" msg="名称不能为空" strLength-msg="长度不能超过32位" id="title" value="${labTemplateVo.name}" size="30" />
										</td>
										<td>
											<label>
												<s:text name="tmp.type"/>：
											</label>
										</td>
										<td>
											<s:select theme="simple" cssStyle="width:214px" list="#{'excel':'Excel模板'}" id="type" name="labTemplateVo.type" value="%{labTemplateVo.type}"></s:select>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="tmp.biz"/>：
											</label>
										</td>
										<td>
											<s:select list="listLabFunction" listKey="id" listValue="name" name="labTemplateVo.busId" id="busId" value="%{labTemplateVo.busId}" headerKey="" headerValue="请选择..." theme="simple" cssStyle="width:214px;"></s:select>
										</td>
										<td>
											<label>
												<s:text name="object.path"/>：
											</label>
										</td>
										<td>
											<input type="text" name="labTemplateVo.objUrl" valType="required,strLength" max="128" msg="对象路径不能为空" strLength-msg="长度不能超过128位" id="objUrl" value="${labTemplateVo.objUrl}" size="30" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="lab.remark"/>：
											</label>
										</td>
										<td colspan="3">
											<textarea rows="2" cols="60" name="labTemplateVo.remark">${labTemplateVo.remark}</textarea>
										</td>
									</tr>
								</table>
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