<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function selectCustomer(){
				var accUnitId = $('#accUnitId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>/quality/accident/showLabCustomer4select.action?labQuaAccidentVo.accUnitId='+accUnitId,
					title:'<s:property value="getText('departlist')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
			function showLabSampRegister(){
				var otherId = $('#otherId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>/quality/accident/showLabSampRegister4select.action?labQuaAccidentVo.otherId='+otherId,
					title:'<s:property value="getText('sendchecklist')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
			function changeValue(obj){
				obj = $(obj).val();
				if(obj == '数据管理'){
					$('#TR').show();
				}else{
					$('#otherName').val('');
					$('#otherId').val('');
					$('#TR').hide();
				}
			}
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
		<input type="hidden" name="labQuaAccidentVo.otherId" value="${labQuaAccidentVo.otherId }" id="otherId" />
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
																	<l:a uri="quality/accident/updateLabQuaAccident.action" onclick="goAction('updateLabQuaAccident.action?labQuaAccidentVo.accStatus=0');" img="/img/add.gif" value="lab.code.save"/>
																</td>
																<td>
																	<l:a uri="quality/accident/updateLabQuaAccident.action" onclick="goAction('updateLabQuaAccident.action?labQuaAccidentVo.accStatus=1');" img="/img/add.gif" value="save.commit"/>
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
												<span><s:text name="chekaccisheetrec"/></span>
											</div>
											<table class="FormtableCon">
												 <tr>
												<td class="r">
													<label>
														<s:text name="accidunit"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.accUnit" id="accUnit"  valType="required" msg="事故单位不能为空"
														type="text" style="width: 270px;" value="${labQuaAccidentVo.accUnit }" onclick="selectCustomer();"/>
													<input size="40" name="labQuaAccidentVo.accUnitId" id="accUnitId" type="hidden" value="${labQuaAccidentVo.accUnitId }" />
												</td>
												<td class="r">
													<label>
														<s:text name="accidtype"/>：
													</label>
												</td>
												<td>
													<s:select list="labCodeVoList" listKey="name" listValue="name"  headerKey="" headerValue="--请选择--" name="labQuaAccidentVo.accType" cssStyle="width:273px;" id="accType" value="'${labQuaAccidentVo.accType }'" theme="simple"  onchange="changeValue(this);" ></s:select>
												</td>
											</tr>
											<tr id="TR" style="display: none;">
												<td class="r" width="160">
													<label>
														<s:text name="snedcheck"/>：
													</label>
												</td>
												<td colspan="3">
													<input size="40" type="text" name="labQuaAccidentVo.otherName" value="${labQuaAccidentVo.otherName }" id="otherName" onclick="showLabSampRegister();" />
												</td>
											</tr>
											<!-- <tr>
												<td class="r">
													<label>
														<s:text name="accifuze"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.accPeople" id="accPeople"
														type="text" style="width: 270px;" value="${labQuaAccidentVo.accPeople }" />
												</td>
												<td class="r">
													<label>
														<s:text name="accidtime"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.accTime" id="accTime"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" 
														type="text" style="width: 270px;" value="${labQuaAccidentVo.accTime }" />
												</td>
											</tr>
											<tr>
											    <td class="r">
												       <label>
														<s:text name="labmasterman"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaAccidentVo.repPeople" id="repPeople" type="text"  style="width: 270px;" value="${labQuaAccidentVo.repPeople }" />
												</td>
												<td class="r">
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.repTime" id="repTime" readonly="readonly" 
														type="text"  style="width: 270px;" value="${labQuaAccidentVo.repTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r">
												       <label>
														 <s:text name="qosmaind"/>：
														</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.chePeople" id="chePeople" type="text"  style="width: 270px;" value="${labQuaAccidentVo.chePeople }" />
												</td>
												<td class="r">
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.cheTime" id="cheTime" readonly="readonly" 
														type="text"  style="width: 270px;" value="${labQuaAccidentVo.cheTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr> -->
											<tr>
												<td class="r">
													<label>
														<s:text name="chekacichap"/>
														原因、经过：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaAccidentVo.accDecs" cols="40" rows="3" id="accDecs">${labQuaAccidentVo.accDecs }</textarea>
												</td>
											</tr>
											<tr>
											   <td class="r">
													<label>
														<s:text name="accireason"/>
														以及处理意见：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaAccidentVo.accAna" cols="40" rows="3" id="accAna">${labQuaAccidentVo.accAna }</textarea>
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
