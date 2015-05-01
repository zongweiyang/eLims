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
		</style>
		<script>
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function showTable(obj){
				var test=$('#isTest').val();
				if(test=='Y'){
					$(obj).html('[&nbsp;<font color="blue">生成任务</font>&nbsp;]');
					$('#isTest').val('N');
				}else{
					$(obj).html('[&nbsp;<font color="blue">取消任务</font>&nbsp;]');
					$('#isTest').val('Y');
				}
				$(obj).parent().next().toggle();
			}
			function showLabItem4Select(index){
				var typeId=$('#samTypeId'+index).val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>sample/labSampRegister/showLabItem4Select.action?labItemVo.categoryIds='+typeId+'&labItemVo.index='+index,
					title:'<s:property value="getText('cont.item.info')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
			function copySampInfo(name,index){
				var nameVal=$('#'+name+index).val();
				var len=$('#sampRegisterNum').val();
			   	len=parseInt(len);
				for(var i=(parseInt(index)+1);i<len;i++){
					if(name=='itemName'){
						var idVal=$('#itemId'+index).val();
						$('#itemId'+i).val(idVal);
					}else if(name=='samTypeId'){
						var newVal=$('#samTypeId'+index).val();
						var oldVal=$('#samTypeId'+i).val();
						if(oldVal!=newVal){
							clearValue(i);
						}
					}
					$('#'+name+i).val(nameVal);
				}
			 }
			 function copySampInfo4Select(name,index){
				var nameVal=$('#'+name+index).val();
				var len=$('#sampRegisterNum').val();
			   	len=parseInt(len);
				for(var i=(parseInt(index)+1);i<=len;i++){
					$('#'+name+i).find('option[value="'+nameVal+'"]').attr('selected','selected');
				}
			 }
			 function clearValue(index){
				$('#itemName'+index).val("");
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyPlanForm" id="form">
			<input type="hidden" value="${labQuaProficiencyPlanVo.id}" name="labQuaProficiencyPlanVo.id" id="id" />
			<input type="hidden" value="${labQuaProficiencyPlanVo.isTest}" name="labQuaProficiencyPlanVo.isTest" id="isTest"/>
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
              	<tr>
					<td class="MLimg"></td>
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
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
																	<l:a uri="back" value="msg.back"/> 
																</td>
                                                      			<td>
                                                      				<l:a uri="quality/proficiencyPlan/updateLabQuaProficiencyPlan.action" onclick="goAction('updateLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.auditResult=1');" value="lab.code.save"/>
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
												<span>年度比对和验证实验计划表</span>
											</div>
											<table class="FormtableCon">
										 		<tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													<s:select headerKey="" headerValue="请选择" list="labOrgList" theme="simple" listKey="id+'|'+name"  value="'${labQuaProficiencyPlanVo.unitOrgId}|${labQuaProficiencyPlanVo.unitOrgName}'" listValue="name" cssStyle="width:273px;" onchange="checkLab(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaProficiencyPlanVo.unitOrgId" value="${labQuaProficiencyPlanVo.unitOrgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaProficiencyPlanVo.unitOrgName"  value="${labQuaProficiencyPlanVo.unitOrgName }" />
												</td>
												<td class="r" width="150">
													<label>
														计划名称：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaProficiencyPlanVo.name" id="name" valType="required" msg="计划名称不能为空"
														type="text" value="${labQuaProficiencyPlanVo.name}"
														/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														比对/验证类型：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaProficiencyPlanVo.proficiencyType" id="proficiencyType" valType="required" msg="比对/验证类型不能为空"
														type="text" value="${labQuaProficiencyPlanVo.proficiencyType}"
														/>
												</td>
												<td class="r">
													<label>
														计划实施日期：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaProficiencyPlanVo.planTime" id="planTime"
														type="text" value="${labQuaProficiencyPlanVo.planTime}" valType="required" msg="计划实施日期不能为空"
														class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
												</td>
											</tr>
											<tr>
												<td class="r">
													<label>
														经费预算：
													</label>
												</td>
												<td colspan="3">
												       <input size="40" name="labQuaProficiencyPlanVo.payMoney" id="payMoney"
														type="text" value="${labQuaProficiencyPlanVo.payMoney}"
														valType="required"  msg="经费预算不能为空"  onblur="checkNum(this);"/>
												</td>
												<!-- <td class="r">
													<label>
														生成检测任务：
													</label>
												</td>
												<td>
												      <s:radio list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" theme="simple" name="labQuaProficiencyPlanVo.isTest"></s:radio>
												</td> -->
											</tr>
											<tr>
												<td class="r">
													<label>
														计&nbsp;&nbsp;划&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
													<input size="40" name="labQuaProficiencyPlanVo.contPeople" id="contPeople"
														type="text" value="${labQuaProficiencyPlanVo.contPeople}"
														/>
												</td>
												<td class="r">
													
													<label>
														计划日期：
													</label>
												</td>
												<td>
													    <input size="40" name="labQuaProficiencyPlanVo.contDate" id="contDate" type="text"
															 value="${labQuaProficiencyPlanVo.contDate}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"  />
												</td>
											</tr>
											<tr>
												<td class="r">
												    <label>
													   批准人：
													</label>
												</td>
												<td>
														<input size="40" name="labQuaProficiencyPlanVo.auditPeople" id="auditPeople" type="text"
															 value="${labQuaProficiencyPlanVo.auditPeople}" />
												</td>
												<td class="r">
													
													<label>
														批准日期：
													</label>
												</td>
												<td>
													    <input size="40" name="labQuaProficiencyPlanVo.auditDate" id="auditDate" type="text"
															 value="${labQuaProficiencyPlanVo.auditDate}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"  />
												</td>
											</tr>
											<tr>
												<td class="r">
												       <label>
													   比对/验证内容及要求：
														</label>
												</td>
												<td colspan="3">
													<textarea rows="3" cols="40" name="labQuaProficiencyPlanVo.contents" id="contents"  valType="required" msg="比对/验证类型不能为空">${labQuaProficiencyPlanVo.contents}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r">
												    <label>
													     <s:text name="lab.remark"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea name="labQuaProficiencyPlanVo.remark" cols="40" rows="3" id="remark">${labQuaProficiencyPlanVo.remark }</textarea>
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>检测任务</span>
												<label style="padding-left: 10px;" onclick="showTable(this)">
													[&nbsp;
													<font color="blue">生成任务</font>&nbsp;]
												</label>
											</div>
											<s:if test="${labQuaProficiencyPlanVo.isTest=='Y'}">
												<div>
											</s:if>
											<s:else>
												<div style="display: none;">
											</s:else>
												<table class="FormtableCon">
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.item"/>：
															</label>
														</td>
														<td>
															<input size="40"
																name="labQuaProficiencyPlanVo.sampRegisterTitle"
																id="sampRegisterTitle" type="text"
																value="${labQuaProficiencyPlanVo.sampRegisterTitle}" />
																<input name="labQuaProficiencyPlanVo.sampRegisterId"
																id="sampRegisterId" type="hidden" value="${labQuaProficiencyPlanVo.sampRegisterId}" />
														</td>
														<td class="r" width="150">
															<label>
																任务编号：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterNo"
																value="${labQuaProficiencyPlanVo.sampRegisterNo}" id="sampRegisterNo" readonly="readonly" style="background-color: #eee"/>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="call.people"/>：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterUser"
																id="sampRegisterUser" value="${labQuaProficiencyPlanVo.sampRegisterUser}"/>
														</td>
														<td class="r" width="150">
															<label>
																联系电话：
															</label>
														</td>
														<td>
															<input size="40" name="labQuaProficiencyPlanVo.sampRegisterTel"
																id="sampRegisterTel" type="text"  valType="phone"  phone-msg="电话格式不准确"
																value="${labQuaProficiencyPlanVo.sampRegisterTel}" />
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																样品数量：
															</label>
														</td>
														<td colspan="3">
															<input size="40"  valType="number" msg="样品数量输入不正确！" name="labQuaProficiencyPlanVo.sampRegisterNum"
																id="sampRegisterNum" type="text"
																value="${labQuaProficiencyPlanVo.sampRegisterNum}" />&nbsp;&nbsp;
															&nbsp;&nbsp;
															<l:a href="#" uri="quality/proficiencyPlan/updateLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.auditResult=0" value="generateconten" />
														</td>
													</tr>
												</table>
											</div>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
		                  				<s:if test="${fn:length(sampList) > 0 }">
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>检测内容</span>
												<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<table class="FormtableCon_sform" cellspacing="1" cellpadding="0">
													<thead>
														<tr>
															<th width="100">
																批内编号
															</th>
															<th width="100">
																原编号
															</th>
															<th width="190">
																样品名称
															</th>
															<th width="110">
																样品类型
															</th>
															<th>
																<s:text name="checking.item"/>
															</th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="sampList" status="st">
															<tr>
																<td width="100">
																	<input type="text" name="sampList[${st.index}].sampCode" value="${sampCode}" size="10" readonly="readonly" style="background-color: #DDDDDD"/>
																</td>
																<td width="100">
																	<input type="text" name="sampList[${st.index}].oldNo" value="${oldNo}" size="10"/>
																</td>
																<td>
																	<input type="text" name="sampList[${st.index}].name" id="sampName${st.index}" value="${name}"/>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('sampName','${st.index}')"/>
																</td>
																<td>
																	<s:select list="#request.samTypeList" listKey="id" listValue="name" theme="simple" name="sampList[${st.index}].samTypeId" id="samTypeId${st.index}" onchange="clearValue('${st.index}');"></s:select>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('samTypeId','${st.index}')"/>
																</td>
																<td>
																	<textarea style="width: 90%;overflow:hidden;" name="sampList[${st.index}].itemName" id="itemName${st.index}" onclick="showLabItem4Select('${st.index}');" readonly="readonly">${itemName}</textarea>
																	<input type="hidden" name="sampList[${st.index}].itemId" value="${itemId}" id="itemId${st.index}"/>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('itemName','${st.index}')"/>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
										</div>
									</s:if>
									</div>
								</td>
							</tr>
						</table>
				    </td>
			 		<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
        		</tr>
			</table>
		</form>
	</body>
</html>		