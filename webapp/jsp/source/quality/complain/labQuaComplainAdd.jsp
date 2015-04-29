<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function selectCustomer(){
				var unitId = $('#unitId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/complain/showLabCustomer4select.action?labQuaComplainVo.unitId='+unitId,
					title:'单位列表',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaComplainForm" id="form">
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
												<span><s:text name="admin.add"/></span>
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
																	<l:a uri="quality/complain/addLabQuaComplain.action" onclick="goAction('addLabQuaComplain.action');" value="lab.code.save"/>
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
												<span>投诉登记与处理表</span>
											</div>
											<table class="FormtableCon">
												<tr>
												<td class="r" width="150">
													<label>
														<s:text name="opinionunit"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaComplainVo.unit" id="unit"
														type="text"  valType="required" msg="投诉单位不能为空!" value="${labQuaComplainVo.unit}" onclick="selectCustomer();"/>
														<input size="40" type="hidden" name="labQuaComplainVo.unitId" id="unitId" value="${labQuaComplainVo.unitId}"/>
												</td>
												<td class="r" width="150">
													<label>
														样品名称：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaComplainVo.sampName" id="sampName"
														type="text" valType="required" msg="样品名称不能为空!"   value="${labQuaComplainVo.sampName}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														投&nbsp;&nbsp;诉&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
												    <input size="40" name="labQuaComplainVo.name" id="name"
														type="text"  value=""/>
												</td>
												<td class="r" width="150">
												    <label>
														投诉日期：
													</label>
												</td>
												<td>
														<input size="40" name="labQuaComplainVo.appTime" id="appTime" readonly="readonly" 
														type="text"   value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											   <td class="r" width="150">
											       <label>
													质量负责人：
													</label>
												</td>
												<td>
														 <input size="40" name="labQuaComplainVo.qualityPerson" id="qualityPerson"
														type="text"  value="${labQuaComplainVo.qualityPerson}"  />
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaComplainVo.qualityTime" id="qualityTime" readonly="readonly" 
														type="text"   value="${labQuaComplainVo.qualityTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											   <td class="r" width="150">
												       <label>
														实验室负责人：
														</label>
												</td>
												<td>
														 <input size="40" name="labQuaComplainVo.skillPerson" id="skillPerson"
														type="text"  value="${labQuaComplainVo.skillPerson}"  />
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaComplainVo.skillTime" id="skillTime" readonly="readonly" 
														type="text"   value="${labQuaComplainVo.skillTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="checking.item"/>：
													</label>
												</td>
												<td colspan="3">
												    <input size="40" name="labQuaComplainVo.itemName" id="itemName"
														type="text" value="${labQuaComplainVo.itemName}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														投诉内容：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaComplainVo.content" cols="50" rows="3" id="content">${labQuaComplainVo.content}</textarea>
												</td>
											</tr>
												<tr>
												<td class="r" width="150">
													<label>
														处理措施：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaComplainVo.measures" cols="50" rows="3" id="measures">${labQuaComplainVo.measures}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														处理结果：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaComplainVo.result" cols="50" rows="3" id="result">${labQuaComplainVo.result}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="opinionunit"/>：(人)意见：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaComplainVo.suggestion" cols="50" rows="3" id="suggestion">${labQuaComplainVo.suggestion}</textarea>
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
