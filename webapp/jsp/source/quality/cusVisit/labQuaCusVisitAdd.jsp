<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
			function selectChange(obj){
       		var value=$(obj).val();
       		if(value.length==0){
       			$('#labOrgId').append($('<option>').attr({'value':'','selected':'selected'}).html("--请选择--"));
       			return ;
       		}
			var oo=value.split('|');
			$('#orgId').val(oo[0]);
			$('#orgName').val(oo[1]);
       		$.ajax({
       			url:'<%=basePath%>quality/cusVisit/ajaxLabQuaCusVisit4LabOrg.action',
       			type:'POST',
       			data:{'labQuaCusVisitVo.unitOrgId':oo[0]},
       			dataType:'text',
       			success:function (data){
       				var result=eval('('+data+')');
       				$('#labOrgId').html('--请选择--');
       				for(var i=0;i<result.length;i++){
       					$('#labOrgId').append(
       						$('<option>').attr({'value':result[i].id+'|'+result[i].name}).html(result[i].name)
       					);
       					$('#labId').val(result[i].id);
						$('#labName').val(result[i].name);
       				}
       			},
       			error:function (){
       				alert('<s:property value="getText('network.error')"/>');
       			}
       		});
       }
			function selectCustomer(){
				var cusUnitId = $('#cusUnitId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/cusVisit/showLabCustomer4select.action?labQuaCusVisitVo.cusUnitId='+cusUnitId,
					title:'<s:property value="getText('custlist')"/>',
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
																	<l:a uri="quality/cusVisit/addLabQuaCusVisit.action" onclick="goAction('addLabQuaCusVisit.action');" value="lab.code.save"/>
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
													<s:select list="unitOrgList" headerKey="" headerValue="--请选择--" cssStyle="width:273px;"
													name="labQuaCusVisitVo.unitOrgIdSearch" id="unitOrgId" theme="simple"
													listKey="id+'|'+name" listValue="name" onchange="selectChange(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaCusVisitVo.unitOrgId" value="${labQuaCusVisitVo.unitOrgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaCusVisitVo.unitOrgName"  value="${labQuaCusVisitVo.unitOrgName }" />
												</td>
												<td  class="r" width="150">
													<label>
														<s:text name="labroom"/>：
													</label>
												</td>
												<td>
													<s:select list="labOrgList" headerKey="" headerValue="--请选择--" cssStyle="width:273px;"
													name="labQuaCusVisitVo.labOrgIdSearch" id="labOrgId" theme="simple"
													listKey="id+'|'+name" listValue="name" ></s:select>
													<input size="40" type="hidden"  id="labId" valType="required" msg="请选择实验室" name="labQuaCusVisitVo.labOrgId" value="${labQuaCusVisitVo.labOrgId }"  />
													<input size="40" type="hidden"  id="labName" name="labQuaCusVisitVo.labOrgName"  value="${labQuaCusVisitVo.labOrgName }" />
												</td>
											</tr>
											<tr>
												<td  class="r" width="150">
													<label>
														<s:text name="custunit"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaCusVisitVo.cusUnit" valType="required" msg="客户单位不能为空" id="cusUnit" type="text"
														 value="${labQuaCusVisitVo.cusUnit }"  onclick="selectCustomer();"  />
														 <input size="40" name="labQuaCusVisitVo.cusUnitId"id="cusUnitId" type="hidden"
														 value="${labQuaCusVisitVo.cusUnitId }"   />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="nike.name"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaCusVisitVo.name" id="name"
														type="text"  value="${labQuaCusVisitVo.name }" valType="required" msg="姓名不能为空"
														 />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													
													<label>
														<s:text name="custtel"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaCusVisitVo.phone" id="phone" type="text"
															  value="${labQuaCusVisitVo.phone }" valType="phone" />
												</td>
												<td class="r" width="150">
												    <label>
													     <s:text name="visitetime"/>：：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaCusVisitVo.visitTime" id="visitTime" readonly="readonly" 
													type="text"   value="${labQuaCusVisitVo.visitTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="custadvopin"/>：
													</label>
												</td>
												<td colspan="3">
														<textarea rows="3" cols="50" valType="required" msg="客户意见及建议不能为空" name="labQuaCusVisitVo.suggestion"  id="suggestion">${labQuaCusVisitVo.suggestion }</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												       <label>
													   意见处理：
														</label>
												</td>
												<td colspan="3">
														<textarea rows="3" cols="50" name="labQuaCusVisitVo.handle"  id="handle">${labQuaCusVisitVo.handle }</textarea>
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
