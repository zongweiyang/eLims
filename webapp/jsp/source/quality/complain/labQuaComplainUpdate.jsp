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
					title:'<s:property value="getText('departlist')"/>',
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
		<input name="labQuaComplainVo.id" type="hidden" value="${labQuaComplainVo.id }" />
		<input name="labQuaComplainVo.no" type="hidden" value="${labQuaComplainVo.no }" />
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
																	<l:a uri="quality/complain/updateLabQuaComplain.action" onclick="goAction('updateLabQuaComplain.action');" value="lab.code.save"/>
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
												<span><s:text name="touregsheet"/></span>
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
														<s:text name="sam.name"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaComplainVo.sampName" id="sampName"
														type="text" valType="required" msg="样品名称不能为空!"  value="${labQuaComplainVo.sampName}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="tousuren"/>：
													</label>
												</td>
												<td>
												    <input size="40" name="labQuaComplainVo.name" id="name"
														type="text"  value="${labQuaComplainVo.name }"/>
												</td>
												<td class="r" width="150">
												    <label>
														<s:text name="apoinrdate"/>：
													</label>
												</td>
												<td>
														<input size="40" name="labQuaComplainVo.appTime" id="appTime" readonly="readonly" 
														type="text"   value="${labQuaComplainVo.appTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											   <td class="r" width="150">
											       <label>
													<s:text name="qosmaind"/>：
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
														<s:text name="labmasterman"/>：
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
														<s:text name="compncontent"/>：
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
														<s:text name="opinionunit"/>：
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
