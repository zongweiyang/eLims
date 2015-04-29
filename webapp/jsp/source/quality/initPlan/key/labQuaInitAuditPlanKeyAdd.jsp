<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaInitAuditPlanForm" id="form">
		<input name="labQuaInitAuditPlanVo.parentId"  type="hidden" value="${labQuaInitAuditPlanVo.parentId}" />
		<input name="labQuaInitAuditPlanVo.key"  type="hidden" value="${labQuaInitAuditPlanVo.key}" />
		<input type="hidden" name="labQuaInitAuditPlanVo.rank" value="${labQuaInitAuditPlanVo.rank }" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span>检查内容>>检查重点&nbsp;&nbsp;[<font color="red"><s:text name="admin.add"/></font>]</span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
                                                      				<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="window.location.href='<%=basePath%>quality/initPlan/listLabQuaInitAuditPlan4Child.action?labQuaInitAuditPlanVo.rank=3&labQuaInitAuditPlanVo.parentId=${labQuaInitAuditPlanVo.parentId }&labQuaInitAuditPlanVo.key=${labQuaInitAuditPlanVo.key }';"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>                    
																</td>
																<td>
																	<l:a uri="quality/initPlan/addLabQuaInitAuditPlan4Child.action" onclick="goAction('addLabQuaInitAuditPlan4Child.action');" value="lab.code.save"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>初始化内部要素</span>
											</div>
											<table class="FormtableCon">
												<tr>
										 			<td class="r" width="120"><label>检查重点：</label></td>
										 			<td>
										 				<textarea cols="50" rows="4" valType="required" msg="检查重点不能为空" name="labQuaInitAuditPlanVo.name" id="name"></textarea>
										 			</td>
										 		</tr>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
