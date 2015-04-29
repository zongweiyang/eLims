<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			$(function(){
				var accType = '${labQuaAccidentVo.accType}';
				if(accType == '数据管理'){
					$('#TR').show();
				}else{
					$('#otherName').val('');
					$('#TR').hide();
				}
			})
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAccidentForm" id="form">
		<input name="labQuaAccidentVo.id" type="hidden" value="${labQuaAccidentVo.id }" />
		<input size="40" type="hidden" name="labQuaAccidentVo.otherId" value="${labQuaAccidentVo.otherId }" id="otherId" />
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
												<span><s:text name="lab.code.modify"/></span>
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
																<td>
																	<l:a uri="quality/accident/updateLabQuaAccident4Handle.action" onclick="goAction('updateLabQuaAccident4Handle.action?labQuaAccidentVo.accStatus=1');" value="lab.code.save"/>
																</td>
																<td>
																	<l:a uri="quality/accident/updateLabQuaAccident4Handle.action" onclick="goAction('updateLabQuaAccident4Handle.action?labQuaAccidentVo.accStatus=2');" value="save.commit"/>
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
												<td class="r" width="180">
													<label>
														<s:text name="accidunit"/>：
													</label>
												</td>
												<td>
													${labQuaAccidentVo.accUnit }
													<input name="labQuaAccidentVo.accUnit" id="Unit" type="hidden" value="${labQuaAccidentVo.accUnit }" />
												</td>
												<td class="r" width="180">
													<label>
														事故类别：
													</label>
												</td>
												<td>
													${labQuaAccidentVo.other  }
													<input name="labQuaAccidentVo.accType" id="accType" type="hidden"  value="${labQuaAccidentVo.accType }" />
												</td>
											</tr>
											<tr id="TR" style="display: none;">
												<td class="r" width="160">
													<label>
														送检单：
													</label>
												</td>
												<td colspan="3">
													${labQuaAccidentVo.otherName }
													<input size="40" type="hidden" name="labQuaAccidentVo.otherName" value="${labQuaAccidentVo.otherName }" id="otherName" />
												</td>
											</tr>
											<tr>
												<td class="r" width="180">
													<label>
														检验事故发生
														原因、经过：
													</label>
												</td>
												<td colspan="3">
												       <textarea readonly="readonly" name="labQuaAccidentVo.accDecs" cols="40" rows="3" id="accDecs">${labQuaAccidentVo.accDecs }</textarea>
												</td>
											</tr>
											<tr>
											   <td class="r" width="180">
													<label>
														事故原因分析以及处理意见：
													</label>
												</td>
												<td colspan="3">
												       <textarea readonly="readonly" name="labQuaAccidentVo.accAna" cols="40" rows="3" id="accAna">${labQuaAccidentVo.accAna }</textarea>
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
													<td class="r" width="180">
														<label>
															事故处理结果及防范措施：
														</label>
													</td>
													<td colspan="3">
														<textarea name="labQuaAccidentVo.procOpin" cols="40" rows="3" id="procOpin">${labQuaAccidentVo.procOpin }</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="180">
														<label>
															事故处理人：
														</label>
													</td>
													<td>
														<input size="40" type="text" name="labQuaAccidentVo.resPeople"   id="resPeople" value="${labQuaAccidentVo.resPeople }"/>
													</td>
													<td class="r" width="220">
														<label>
															<s:text name="msg.date"/>：
														</label>
													</td>
													<td>
														<input size="40" type="text" name="labQuaAccidentVo.procTime"   class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" id="procTime" value="${labQuaAccidentVo.procTime }"/>
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
