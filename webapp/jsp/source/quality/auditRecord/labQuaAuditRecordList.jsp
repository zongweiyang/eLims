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
			       $('form').attr('action','<%=basePath%>quality/auditRecord/deleteLabQuaAuditRecord.action?labQuaAuditRecordVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaAuditRecordVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/auditRecord/deleteLabQuaAuditRecord.action');
						$('form').submit();	
					}	
				}		
			}
			function showLabQuaAuditPlanEle4select(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/auditRecord/showLabQuaAuditPlanEle4select.action',
					title:'<s:property value="getText('internchecklist')"/>',
					opacity:0.4,
					width:900, 
					height:400,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAuditRecordForm" id="form">
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
					                      						<td>
																	<label>
																	被审<s:text name="msg.depart"/>：
																	</label>
																</td>
																<td>
																	<s:select list="labOrgVoList" headerKey="" headerValue=""
																		name="labQuaAuditRecordVo.labOrgId" id="labOrgId"
																		theme="simple" listKey="id" listValue="name"></s:select>
																</td>
																<td>
																	<label>
																		<s:text name="intendsdsplan"/>：
																	</label>
																</td>
																<td>
																	<s:select id="planId" theme="simple"
																		name="labQuaAuditRecordVo.quaAuditPlanEleId"
																		list="labQuaAuditPlanEleVoList" headerKey=""
																		headerValue="" listKey="id" listValue="purpose"></s:select>
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
                                                      				<l:a uri="quality/auditRecord/preAddLabQuaAuditRecord.action" onclick="showLabQuaAuditPlanEle4select();" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/auditRecord/deleteLabQuaAuditRecord.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaAuditRecordVo.ids'); } else { clearAll('labQuaAuditRecordVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="labQuaAuditPlanEle.purpose">
													<s:text name="intendsdsplan"/>
												</th>
												<th property="org.name">
													<s:text name="checkeddepart"/>
												</th>
												<th property="auditPart">
													<s:text name="internaeles"/>
												</th>
												<th property="month">
													<s:text name="internchkmonth"/>
												</th>
												<th property="checkHead">
													<s:text name="auditleader"/>
												</th>
												<th property="writeMember">
													<s:text name="coderepeople"/>
												</th>
												<th property="writeTime">
													<s:text name="codetimes"/>
												</th>
												<th>
													<s:text name="progressing"/>
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
																<input type="checkbox"
																	name="labQuaAuditRecordVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${quaAuditPlanEleName }
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
																${status }
															</td>
															<td class="c">
																<s:if test="${status == '归档' || status == '已完结' } ">
																	<l:a href="#" uri="quality/auditRecord/showLabQuaAuditFile.action?labQuaAuditRecordVo.id=${id}" value="look.check" />
																</s:if>
																<s:elseif test="${status == '内审报告'}">
																	<l:a href="#" uri="quality/auditRecord/showLabQuaAuditReport.action?labQuaAuditRecordVo.id=${id}"  value="look.check" />
																</s:elseif>
																<s:else>
																	<l:a href="#" uri="quality/auditRecord/showLabQuaAuditRecord.action?labQuaAuditRecordVo.id=${id}"  value="look.check" />
																</s:else>
																<s:if test="${isOper=='Y'}"> 
																	&nbsp;&nbsp;
															  		<l:a href="#" uri="quality/auditRecord/preUpdateLabQuaAuditRecord.action?labQuaAuditRecordVo.id=${id}" value="lab.code.modify" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/auditRecord/deleteLabQuaAuditRecord.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
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
										page="/jsp/include/page.jsp?formName=labQuaAuditRecordForm"
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
	</body>
</html>		
