<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function checkUnit(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#labId').val(oo[0]);
				$('#labName').val(oo[1]);
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
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
												<span><s:text name="look.check"/></span>
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
																	<l:a uri="back" value="msg.back"/>  
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
												<span><s:text name="custvisitinfo"/></span>
											</div>
											<table class="FormtableCon">
												  <tr>
												<td class="r" width="150">
													<label>
														<s:text name="theme.depart"/>：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.unitOrgName }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="labroom"/>：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.labOrgName }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="custunit"/>：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.cusUnit }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="nike.name"/>：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.name }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													
													<label>
														<s:text name="custtel"/>：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.phone }
												</td>
												<td class="r" width="150">
												    <label>
													     <s:text name="visitetime"/>：：
													</label>
												</td>
												<td>
													${labQuaCusVisitVo.visitTime }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="custadvopin"/>：
													</label>
												</td>
												<td colspan="3">
														<textarea readonly="readonly" rows="3" cols="50" valType="required" msg="客户意见及建议不能为空" name="labQuaCusVisitVo.suggestion"  id="suggestion">${labQuaCusVisitVo.suggestion }</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												       <label>
													   意见处理：
														</label>
												</td>
												<td colspan="3">
														<textarea readonly="readonly" rows="3" cols="50" name="labQuaCusVisitVo.handle"  id="handle">${labQuaCusVisitVo.handle }</textarea>
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
