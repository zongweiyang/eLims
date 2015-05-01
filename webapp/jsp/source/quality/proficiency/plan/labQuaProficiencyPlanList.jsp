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
			function goToNextAction(url){
				$('#form').attr('action','${basePath}'+url);
				$('#form').submit();
			}
			function deleteOne(id){
			   	if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){	 
			       $('form').attr('action','<%=basePath%>quality/proficiencyPlan/deleteLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaProficiencyPlanVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/proficiencyPlan/deleteLabQuaProficiencyPlan.action');
						$('form').submit();	
					}	
				}		
			}
			function showProcess(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath%>/jsp/common/workflow/ins/processFrame.jsp?busId='+id,
					title:'<s:property value="getText('task.progress')"/>',
					opacity:0.4,
					width:1100, 
					height:500,
					lock:true,
					max:false,
					min:false
				});
			}
			function thisFlush(){
		    	$('#form').attr('action','${basePath}'+'quality/proficiencyPlan/listLabQuaProficiencyPlan.action');
				$('#form').submit();
		    }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyPlanForm" id="form">
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
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
									   	<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						 <td><label><s:text name="theme.depart"/>：</label></td>
																<td>
																	<s:select list="labOrgList" headerKey="" headerValue=""
																		name="labQuaProficiencyPlanVo.unitOrgSearch" id="unitId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																<td><label><s:text name="plannamed"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaProficiencyPlanVo.name" value="${labQuaProficiencyPlanVo.name}" />
					                      						</td>
																<td><label><s:text name="accpterman"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaProficiencyPlanVo.auditPeople" value="${labQuaProficiencyPlanVo.auditPeople}" />
					                      						</td>
					                      						 <td><label><s:text name="accidentinfo"/>：</label></td>
					                      						<td>
																	<s:select list="#{'0':getText('unureturnerro'),'1':getText('dealingwith'),'2':getText('deleswith')}" headerKey="" headerValue=""
																		name="labQuaProficiencyPlanVo.accStatus" id="accStatus" theme="simple" value="'${labQuaProficiencyPlanVo.accStatus}'" ></s:select>
																</td>
										                      	<td>
										                      		<l:a uri="${SessionContainer.lastUrl}" onclick="submitAction();" value="fun.query" />
										                      	</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                   <!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
                                                      				<l:a uri="quality/proficiencyPlan/preAddLabQuaProficiencyPlan.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/proficiencyPlan/deleteLabQuaProficiencyPlan.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<tr> 
												<th>
													<input type="checkbox"
														onclick="if(this.checked==true) { checkAll('labQuaProficiencyPlanVo.ids'); } else { clearAll('labQuaProficiencyPlanVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="org.name">
													<s:text name="theme.depart"/>
												</th>
												<th property="name">
													<s:text name="plannamed"/>
												</th>
												<th>
													<s:text name="duibiyanzheng"/>
												</th>
												<th property="planTime">
													<s:text name="jishuashishidate"/>
												</th>
												<th property="payMoney">
													<s:text name="fundbudget"/>
												</th>
												<th property="auditPeople">
													<s:text name="accpterman"/>
												</th>
												<th property="auditDate">
													<s:text name="acceptdate"/>
												</th>
												<th property="accStatus">
													<s:text name="accidentinfo"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<s:if test="${status =='00'}">
																<input type="checkbox"
																	name="labQuaProficiencyPlanVo.ids" id="ids${st.index+1 }" value="${id}" />
																</s:if>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${unitOrgName }
															</td>
															<td class="l">
																${name }
															</td>
															<td class="l">
																${contents }
															</td>
															<td class="c">
																${planTime }
															</td>
															<td  class="r">
																 ${payMoney }
															</td>
															<td  class="l">
																${auditPeople }
															</td>
															<td class="c">
																${auditDate }
															</td>
															<td  class="c">
																<s:if test="${accStatus == 1}">
																	<span style="color: red;"><s:text name="dealtingwith"/></span>
																</s:if>
																<s:elseif test="${accStatus == 2}">
																	<span style="color: red;"><s:text name="sam.dealed"/></span>
																</s:elseif>
																<s:else>
																	<span style="color: red;"><s:text name="untrunacci"/></span>
																</s:else>
															</td>
															<td class="c">
																<l:a href="#" uri="quality/proficiencyPlan/showLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.id=${id}" value="look.check" />
																<s:if test="${isTest == 'Y' }">
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/proficiencyPlan/preUpdateLabQuaProficiencyPlan.action" onclick="showProcess('${sampRegisterId}');" value="lookprogre" />
																</s:if>
																<s:if test="${status =='00'}">
																	&nbsp;&nbsp;
															  		<l:a href="#" uri="quality/proficiencyPlan/preUpdateLabQuaProficiencyPlan.action?labQuaProficiencyPlanVo.id=${id}" value="lab.code.modify" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/proficiencyPlan/deleteLabQuaProficiencyPlan.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
																</s:if>
																&nbsp;&nbsp;
															  	<l:a href="#" uri="quality/proficiencyPlan/preAddLabQuaProficiency.action?labQuaProficiencyVo.proficiencyPlanId=${id}&labQuaProficiencyVo.proficiencyPlanName=${name }&labQuaProficiencyVo.orgName=${unitOrgName }&labQuaProficiencyVo.orgId=${unitOrgId }" value="记录" />
																<s:if test="${accStatus == ''|| accStatus == null || accStatus=='0'}">
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/accident/preAddLabQuaAccident4Other.action"  onclick="selectAccident('${id}','ACC-NLYZ');" value="transferaccid"/>	
																</s:if>
															</td>
														</tr>
													</s:iterator>
												</s:if>
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
								</td>
							</tr>
					      	<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labQuaProficiencyPlanForm"
										flush="true" /></td>
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
	 <%@ include file="/jsp/include/foot.jsp"%> 
	 <script>
	 </script>
	</body>
</html>		
