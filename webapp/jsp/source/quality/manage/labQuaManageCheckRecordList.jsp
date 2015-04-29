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
			   	if(confirm('确定要删除吗?')){	 
			       $('form').attr('action','<%=basePath%>quality/manage/deleteLabQuaManageCheckRecord.action?labQuaManageCheckVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaManageCheckVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/manage/deleteLabQuaManageCheckRecord.action');
						$('form').submit();	
					}	
				}		
			}
			function showLabQuaManageCheckPlan4select(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/manage/showLabQuaManageCheckPlan4select.action',
					title:'管理评审计划列表',
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
		<form action="" method="post" name="labQuaManageCheckRecordForm" id="form">
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
					                      						<td><label><s:text name="commtunit"/>：</label></td>
																<td>
																	<s:select list="labOrgList" headerKey="" headerValue="--全部--"
																		name="labQuaManageCheckVo.orgSearch" id="orgSearch" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																<td><label>管理评审计划：</label></td>
																<td>
																	<s:select list="labQuaManageCheckPlanVoList" headerKey="" headerValue="--全部--"
																		name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" theme="simple"
																		listKey="id" listValue="name"></s:select>
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
                                                      				<l:a uri="quality/manage/preAddLabQuaManageCheckRecord.action" onclick="showLabQuaManageCheckPlan4select();" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/manage/deleteLabQuaManageCheckRecord.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaManageCheckVo.ids'); } else { clearAll('labQuaManageCheckVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th>
													<s:text name="commtunit"/>
												</th>
												<th>
													管理评审计划
												</th>
												<th property="address">
													评审地点
												</th>
												<th property="recTime">
													评审时间
												</th>
												<th>
													进度
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
																	name="labQuaManageCheckVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="">
																${orgName }
															</td>
															<td class="">
																${quaManageCheckPlanName }
															</td>
															<td class="">
																${address }
															</td>
															<td  class="c">
																${recTime }
															</td>
															<td  class="c">
																${status }
															</td>
															<td class="c">
																<s:if test="${status == '管理评审报告'}">
																	<l:a href="#" uri="quality/manage/showLabQuaManageCheckReport.action?labQuaManageCheckVo.id=${id}"  value="look.check" />
																</s:if>
																<s:elseif test="${status == '归档' || status == '已完结' }">
																	<l:a href="#" uri="quality/manage/showLabQuaManageCheckFile.action?labQuaManageCheckVo.id=${id}"  value="look.check" />
																</s:elseif>
																<s:else>
																	<l:a href="#" uri="quality/manage/showLabQuaManageCheckRecord.action?labQuaManageCheckVo.id=${id}"  value="look.check" />
																</s:else>
																<s:if test="${isOper=='Y'}"> 
															  		&nbsp;&nbsp;
															  		<l:a href="#" uri="quality/manage/preUpdateLabQuaManageCheckRecord.action?labQuaManageCheckVo.id=${id}" value="lab.code.modify" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/manage/deleteLabQuaManageCheckRecord.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
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
										page="/jsp/include/page.jsp?formName=labQuaManageCheckRecordForm"
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
