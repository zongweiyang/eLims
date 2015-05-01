<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			
			html {
				_overflow-x: hidden;
			}
			
			#roletext {
				width: 70px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis; /* 支持 IE */
			}
			.myworkingboxttable th{
			background: #F5F5F5;
			color: #000000;
			border-top: #C0C0C0 1px solid;
			border-left: #C0C0C0 1px solid;
			border-right: #C0C0C0 1px solid;
			}
			.editType{
				visibility:hidden;
			}
			</style>
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
       			url:'<%=basePath%>quality/control/ajaxLabQuaControl4LabOrg.action',
       			type:'POST',
       			data:{'labQuaControlVo.unitOrgId':oo[0]},
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
       				alert('<s:property value="getText('theme.net.fail')"/>');
       			}
       		});
       }
       function selectApp(){
				var appId = $('#appId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/control/showLabAppara4select.action?labQuaControlVo.appId='+appId,
					title:'<s:property value="getText('apprllist')"/>',
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
		<form action="" method="post" name="labQuaControlForm" id="form">
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
																	<l:a uri="quality/control/addLabQuaControl.action" onclick="goAction('addLabQuaControl.action');" value="lab.code.save"/>
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
												<span><s:text name="dubaseinfo"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon" id="mainId">
												  <tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													<s:select list="unitOrgVoList" headerKey="" headerValue="--请选择--" cssStyle="width:273px;"
													name="labQuaControlVo.unitOrgSearch" value="'${labQuaCusVisitVo.unitOrgId}|${labQuaCusVisitVo.unitOrgName }'" id="unitOrgId" theme="simple"
													listKey="id+'|'+name" listValue="name" onchange="selectChange(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaControlVo.unitOrgId" value="${labQuaControlVo.unitOrgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaControlVo.unitOrgName"  value="${labQuaControlVo.unitOrgName }" />
												</td>
												<td  class="r" width="150">
													<label>
														<s:text name="labroom"/>：
													</label>
												</td>
												<td>
													<s:select list="labOrgVoList" headerKey="" headerValue="--请选择--" cssStyle="width:273px;"
													name="labQuaControlVo.labOrgSearch" id="labOrgId" theme="simple"
													listKey="id+'|'+name" listValue="name" ></s:select>
													<input size="40" type="hidden"  id="labId" valType="required" msg="请选择实验室" name="labQuaControlVo.labOrgId" value="${labQuaControlVo.labOrgId }"  />
													<input size="40" type="hidden"  id="labName" name="labQuaControlVo.labOrgName"  value="${labQuaControlVo.labOrgName }" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaControlVo.place" id="place" type="text"
														value="${labQuaControlVo.place }" valType="required" msg="地区不能为空"  />
												</td>
											    <td class="r" width="150">
													<label>
												<s:text name="zhixingstg"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaControlVo.standardName" id="standardName"
														type="text" value="${labQuaControlVo.standardName }"
														valType="required" msg="执行标准不能为空"/>
												</td>
											</tr>

											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="app.style"/>：
													</label>
												</td>
												<td >
												       <input size="40" name="labQuaControlVo.appCode" id="appCode"
														type="text" value="${labQuaControlVo.appCode }"
														    valType="required" msg="仪器型号不能为空" onclick="selectApp();"/>
														 <input size="40" name="labQuaControlVo.appId" id="appId" type="hidden" value="${labQuaControlVo.appId }" />
												</td>
											    <td class="r" width="150">
												       <label>
														<s:text name="app.no"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaControlVo.appNo" id="appNo" type="text"
															 value="${labQuaControlVo.appNo }"  valType="required" msg="仪器编号不能为空" onclick="selectApp();" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												       <label>
														<s:text name="chouschare"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaControlVo.anaPeople" id="anaPeople" type="text"
															 value="" />
												</td>
												
												<td class="r" width="150">
													
													<label>
														<s:text name="chksdate"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaControlVo.testTime" id="testTime" readonly="readonly" 
														type="text"  value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
														<s:text name="jiandupeo"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaControlVo.conPeople" id="conPeople" type="text"
															 value="" />
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="jiandudate"/>：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaControlVo.conTime" id="conTime" readonly="readonly" 
														type="text"  value="" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="jianducontent"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;">
												<table class="myworkingboxttable" id="OneTable" >
													<thead>
														<tr>
															<th>
																<s:text name="jianducontent"/>
															</th>
															<th>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="statedesc"/>
															</th>
															<th>
																<s:text name="notreasonresultl"/>
															</th>
														</tr>
													</thead>
													<s:if test="labCodeVoList!=null">
														<s:set name="alllist" value="labCodeVoList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c" width="150">
																	${name }
																	<input type="hidden" name="labQuaControlVo.labQuaControlDetailVoList[${st.index}].comCodeId" value="${id }" />
																	<input type="hidden" name="labQuaControlVo.labQuaControlDetailVoList[${st.index}].comCodeName" value="${name }" />
																</td>
																<td class="c" width="150">
																	<input name="labQuaControlVo.labQuaControlDetailVoList[${st.index}].statusDesc" size="40" type="text"  value="" />
																</td>
																<td  class="c" width="150">
																	<input name="labQuaControlVo.labQuaControlDetailVoList[${st.index}].proResult"  size="40" type="text"  value="" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
													<s:else>
														<tr>
															<td colspan="3" class="c">
																<font color="red"><s:text name="lab.msg.none"/></font>
															</td>
														</tr>
													</s:else>
												</table>
											</div>
										</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="comment.info"/></span>
													<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
												</div>
												<table class="FormtableCon" id="twoTable">
													<tr>
														<td class="r" width="150">
														       <label>
																<s:text name="conheping"/>：
																</label>
														</td>
														<td colspan="3">
																<textarea name="labQuaControlVo.evaluation" cols="40" rows="3" id="evaluation"></textarea>
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
