<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
	</head>
	<script>
			$(function(){
				var accType = '${labQuaAccidentVo.accType}';
				if(accType == '数据管理'){
					$('#TR').show();
				}else{
					$('#otherName').val('');
					$('#otherId').val('');
					$('#TR').hide();
				}
			})
		</script>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAccidentForm" id="form">
			<input name="labQuaAccidentVo.id" type="hidden" value="${labQuaAccidentVo.id }" />
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
												<span><s:text name="registinfo"/></span>
                                                <label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												 <tr>
												<td class="r" width="100">
													<label>
														<s:text name="accidunit"/>：
													</label>
												</td>
												<td>
													${labQuaAccidentVo.accUnit }
												</td>
												<td class="r" width="100">
													<label>
														<s:text name="accidtype"/>：
													</label>
												</td>
												<td>
													${labQuaAccidentVo.other }
												</td>
											</tr>
											<tr id="TR" style="display: none;">
												<td class="r" width="160">
													<label>
														<s:text name="snedcheck"/>：
													</label>
												</td>
												<td colspan="3">
													${labQuaAccidentVo.otherName }
												</td>
											</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="chekacichap"/>
															原因、经过：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaAccidentVo.accDecs" cols="40"
															rows="5" id="accDecs">${labQuaAccidentVo.accDecs }</textarea>
													</td>
												</tr>
												<tr>
													<td class="r">
														<label>
															<s:text name="accidresult"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaAccidentVo.procOpin" cols="40" rows="5" id="procOpin">${labQuaAccidentVo.procOpin }</textarea>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="sam.deal.info"/></span>
												 <label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r">
														<label>
															<s:text name="accidresult"/>及防范措施：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaAccidentVo.procOpin" cols="40" rows="3" id="procOpin">${labQuaAccidentVo.procOpin }</textarea>
													</td>
												</tr>
												<tr>
													<td class="w150">
														<label style="padding-left: 90px;">
															<s:text name="accdelapel"/>：
														</label>
													</td>
													<td>
														${labQuaAccidentVo.resPeople }
													</td>
													<td class="w150">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td>
														${labQuaAccidentVo.procTime }
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
