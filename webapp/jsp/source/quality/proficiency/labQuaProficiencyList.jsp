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
			       $('form').attr('action','<%=basePath%>quality/proficiency/deleteLabQuaProficiency.action?labQuaProficiencyVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaProficiencyVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/proficiency/deleteLabQuaProficiency.action');
						$('form').submit();	
					}	
				}		
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyForm" id="form">
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
																	<s:select list="labOrgVoList" headerKey="" headerValue=""  cssStyle="width:150px;"
																		name="labQuaProficiencyVo.orgSearch" id="unitId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																<td><label><s:text name="duibiplan"/>：</label></td>
					                      						<td>
					                      							<s:select list="labQuaProficiencyPlanVoList" id="planId" listKey="id"
																		listValue="name" name="labQuaProficiencyVo.proficiencyPlanId"
																		theme="simple" cssStyle="width:150px;" headerKey="" headerValue=""></s:select>
					                      						</td>
																<td><label><s:text name="paticipangenral"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaProficiencyVo.joinPeople" value="${labQuaProficiencyVo.joinPeople}" />
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
                                                      				<l:a uri="quality/proficiency/preAddLabQuaProficiency.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/proficiency/deleteLabQuaProficiency.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaProficiencyVo.ids'); } else { clearAll('labQuaProficiencyVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th>
													<s:text name="theme.depart"/>
												</th>
												<th>
													<s:text name="duibiplan"/>
												</th>
												<th>
													<s:text name="duibiyanzheng"/>
												</th>
												<th property="ratioItem">
													<s:text name="duibichkitem"/>
												</th>
												<th property="payMoney">
													<s:text name="fundbudget"/>
												</th>
												<th property="joinPeople">
													<s:text name="paticipangenral"/>
												</th>
												<th property="planTime">
													<s:text name="shishidate"/>
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
																	name="labQuaProficiencyVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${orgName }
															</td>
															<td class="l">
																${proficiencyPlanName }
															</td>
															<td class="l">
																${contents }
															</td>
															<td class="l">
																${ratioItem }
															</td>
															<td  class="r">
																${payMoney }
															</td>
															<td  class="l">
																${joinPeople }
															</td>
															<td class="c">
																${planTime }
															</td>
															<td class="c">
														  		<l:a href="#" uri="quality/proficiency/preUpdateLabQuaProficiency.action?labQuaProficiencyVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/proficiency/deleteLabQuaProficiency.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
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
										page="/jsp/include/page.jsp?formName=labQuaProficiencyForm"
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
