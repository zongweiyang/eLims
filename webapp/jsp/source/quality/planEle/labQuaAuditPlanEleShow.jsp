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
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function deleteOne(id){
				var planId=$('#id').val();
			   	if(confirm('确定要删除吗?')){	 
			       $('form').attr('action','<%=basePath%>quality/planEle/deleteLabQuaAuditRecord.action?labQuaAuditRecordVo.ids='+id+'&labQuaAuditRecordVo.quaAuditPlanEleId='+planId);
		      	   $('form').submit();
			    }
			
			}
			function showLabQuaAuditRecord(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/auditRecord/showLabQuaAuditRecord.action?labQuaAuditRecordVo.id='+id,
					title:'<s:property value="getText('reclistinfo')"/>',
					opacity:0.4,
					width:1100, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labChargeForm" id="form">
			<input type="hidden" value="${labQuaAuditPlanEleVo.id}" name="labQuaAuditPlanEleVo.id" id="id" />
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
												<span><s:text name="look.check"/></span>
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
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable" id="Tab01">
											<div class="Formtabletitle">
												<span><s:text name="plan.info"/>：</span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
										<table class="FormtableCon">
											<tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													${labQuaAuditPlanEleVo.orgName }
												</td>
												<td class="r" width="150">
													<label>
														审核目的：
													</label>
												</td>
												<td>
													${labQuaAuditPlanEleVo.purpose }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														审核范围：
													</label>
												</td>
												<td>
													${labQuaAuditPlanEleVo.range }
												</td>
												<td class="r" width="150">
													<label>
														审核依据：
													</label>
												</td>
												<td>
													${labQuaAuditPlanEleVo.implement }
												</td>
											</tr>
											<tr>
													<td class="r" width="150">
														<label>
															审核方式：
														</label>
													</td>
													<td >
													       ${labQuaAuditPlanEleVo.auditType }
													</td>
													<td class="r" width="150">
														<label>
															内审地点：
														</label>
													</td>
													<td>
														${labQuaAuditPlanEleVo.address}
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															内审组长：
														</label>
													</td>
													<td>
														${labQuaAuditPlanEleVo.checkHead }
													</td>
													<td class="r" width="150">
													       <label>
															审核员：
															</label>
													</td>
													<td>
															${labQuaAuditPlanEleVo.groupMember }
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															编&nbsp;&nbsp;制&nbsp;&nbsp;人：
														</label>
													</td>
													<td >
													       ${labQuaAuditPlanEleVo.createPeople }
													</td>
													<td class="r" width="150">
														
														<label>
															编制日期：
														</label>
													</td>
													<td>
														${labQuaAuditPlanEleVo.createTime }
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
												       <label>
														批&nbsp;&nbsp;准&nbsp;&nbsp;人：
														</label>
													</td>
													<td>
														${labQuaAuditPlanEleVo.auditPeople }
													</td>
													<td class="r" width="150">
														
														<label>
															批准日期：
														</label>
													</td>
													<td>
														${labQuaAuditPlanEleVo.auditTime }
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
		                  				<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="yaosuinfo"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div id="Tab01" style="margin-top: 10px; margin-bottom: 10px;">
												<table class="FormtableCon_sform" id="OneTable" >
													<thead>
															<th width="50" class="c">
																条款
															</th>
															<th width="500" class="c">
																要素
															</th>
															<th width="20" class="c">
																一月
															</th>
															<th width="20" class="c">
																二月
															</th>
															<th width="20" class="c">
																三月
															</th>
															<th width="20" class="c">
																四月
															</th>
															<th width="20" class="c">
																五月
															</th>
															<th width="20" class="c">
																六月
															</th>
															<th width="20" class="c">
																七月
															</th>
															<th width="20" class="c">
																八月
															</th>
															<th width="20" class="c">
																九月
															</th>
															<th width="20" class="c">
																十月
															</th>
															<th width="20" class="c">
																十一月
															</th>
															<th width="20" class="c">
																十二月
															</th>
															<th width="100" class="c">
																<s:text name="prj.manager"/>
															</th>
															<th width="100" class="c">
																协助人
															</th>
													</thead>
												<tbody>
												<s:if test="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList!=null">
													<s:set name="alllist" value="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td align="center" width="20">
																	${ruleNum }
																</td>
																<td  align="left">
																	${quaInitAuditPlanName }
																	<input type="hidden"  class="month" value="${month }"/>
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].quaAuditPlanEleId" value="${quaAuditPlanEleId }" />
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].id" value="${id }" />
																	<input type="hidden" size="3" name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].quaInitAuditPlanId" value="${quaInitAuditPlanId }" />
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="1"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="2"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="3"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="4"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="5"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="6"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="7"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="8"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="9"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="10"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="11"/>
																</td>
																<td  class="c">
																	<input name="labQuaAuditPlanEleVo.labQuaAuditPlanEleDetailVoList[${st.index}].month" disabled="disabled" type="checkbox" id="" value="12"/>
																</td>
																<td align="center" width="10">
																	${groupLeader }
																</td>
																<td align="center" width="10">
																	${helpPeople }
																</td>
															</tr>
															</s:iterator>
														</s:if>
														<s:else>
															<tr>
																<td colspan="14" align="center" valign="middle">
																	<font color="red"><s:text name="lab.msg.none"/></font>
																</td>
															</tr>
														</s:else>
													</tbody>
												</table>  
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="record.info"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div id="Tab01" style="margin-top: 10px; margin-bottom: 10px;">
											<table class="FormtableCon_sform" cellspacing="0"
											cellpadding="0">
											<tr> 
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th>
													被审部门
												</th>
												<th property="auditPart">
													内审要素
												</th>
												<th property="month">
													内审月份
												</th>
												<th property="checkHead">
													内审组长
												</th>
												<th property="writeMember">
													编制人
												</th>
												<th property="writeTime">
													编制时间
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="labQuaAuditRecordVoList!=null">
												<s:set name="alllist" value="labQuaAuditRecordVoList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td class="l">
															${labOrgName }
														</td>
														<td class="l">
															${auditPart }
														</td>
														<td class="c">
															${month }月
														</td>
														<td  class="l">
															${checkHead }
														</td>
														<td  class="l">
															${writeMember }
														</td>
														<td class="c">
															${writeTime }
														</td>
														<td class="c">
															<l:a href="#" uri="quality/auditRecord/showLabQuaAuditRecord.action" onclick="showLabQuaAuditRecord('${id}');"  value="details.info" />
															&nbsp;&nbsp;
															<l:a href="#" uri="quality/planEle/deleteLabQuaAuditRecord.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td colspan="7" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</table>
										</div>
										</div>
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
	<script>
		$(function(){
			$('#OneTable tbody tr').each(function(){
				var _val = $(this).find('.month').val().split(', ');
				for(var i=0;i<_val.length;i++){
					if(_val[i] != ''){
						$(this).find('input[type="checkbox"]:eq('+(parseInt(_val[i])-1)+')').attr({'checked':'checked'});
					}
				}
			});
		});
	</script>
</html>		
