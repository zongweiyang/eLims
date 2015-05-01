<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			function closeMe(){
			  	api.close();
		 	}
			
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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAccidentForm" id="form">
			<input type="hidden" name="labQuaAccidentVo.otherId" value="${labQuaAccidentVo.otherId }" id="otherId"/>
			<input type="hidden" name="labQuaAccidentVo.accType" value="${labQuaAccidentVo.accType }" id="type"/>
			<input type="hidden" name="demo1" value="${demo1 }" id="demo1"/>
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="quality/accident/addLabQuaAccident4Other.action" value="page.confirm"/>
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
												<td class="r" width="160">
													<label>
														<s:text name="accidunit"/>：
													</label>
												</td>
												<td width="300">
													<input size="40" name="labQuaAccidentVo.accUnit" id="accUnit"  valType="required" msg="事故单位不能为空"
														type="text" style="width: 270px;" value="" onclick="selectCustomer();"/>
													<input name="labQuaAccidentVo.accUnitId" id="accUnitId" type="hidden" value="${labQuaAccidentVo.accUnitId }" />
												</td>
												<td class="r" width="160">
													<label>
														<s:text name="accidtype"/>：
													</label>
												</td>
												<td>
													<s:select list="labCodeVoList" headerKey="" headerValue="" name="labQuaAccidentVo.accType" cssStyle="width:273px;" id="accType" value="'${labQuaAccidentVo.accType }'" listKey="code" listValue="name" disabled="true" theme="simple" ></s:select>
												</td>
											</tr>
											<!--<tr>
												<td class="r" width="160">
													<label>
														<s:text name="accifuze"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.accPeople" id="accPeople"
														type="text"  style="width: 270px;" value="" />
												</td>
												<td class="r" width="160">
													<label>
														<s:text name="accidtime"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.accTime" id="accTime" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" 
														type="text"  style="width: 270px;" value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
												</td>
											</tr>
											<tr>
											    <td class="r" width="160">
												       <label>
														<s:text name="labmasterman"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaAccidentVo.repPeople"  style="width: 270px;"  id="repPeople" type="text" value="" />
												</td>
												<td width="100" class="r">
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.repTime" id="repTime" readonly="readonly" 
														type="text"   style="width: 270px;" value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="160">
												       <label>
														 <s:text name="qosmaind"/>：
														</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.chePeople" id="chePeople" type="text"  style="width: 270px;" value="" />
												</td>
												<td class="r" width="160">
													<label>
														<s:text name="msg.date"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaAccidentVo.cheTime" id="cheTime" readonly="readonly" 
														type="text"   style="width: 270px;"value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>-->
											<tr>
												<td class="r" width="160">
													<label>
														<s:text name="chekacichap"/>
														原因、经过：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaAccidentVo.accDecs" cols="40" rows="3" id="accDecs"></textarea>
												</td>
											</tr>
											<tr>
											   <td class="r" width="170">
													<label>
														<s:text name="accireason"/>
														以及处理意见：
													</label>
												</td>
												<td colspan="3">
												       <textarea name="labQuaAccidentVo.accAna" cols="40" rows="3" id="accAna"></textarea>
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
		<script>
		$(function(){
			var demo1 = '${demo1}';
			if(demo1 == 'true'){
				closeMe();
			}
		})
		</script>
	</body>
</html>
