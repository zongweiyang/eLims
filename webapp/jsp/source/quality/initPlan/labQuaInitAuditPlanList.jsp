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
			       $('form').attr('action','<%=basePath%>quality/initPlan/deleteLabQuaInitAuditPlan.action?labQuaInitAuditPlanVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaInitAuditPlanVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/initPlan/deleteLabQuaInitAuditPlan.action');
						$('form').submit();	
					}	
				}		
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaInitAuditPlanForm" id="form">
			<input name="labQuaInitAuditPlanVo.parentId"  type="hidden" value="${labQuaInitAuditPlanVo.parentId}" />
			<input type="hidden" name="labQuaInitAuditPlanVo.rank" value="${labQuaInitAuditPlanVo.rank }" />
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
					                      						 <td><label>要素：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaInitAuditPlanVo.searchName" value="${labQuaInitAuditPlanVo.searchName}" />
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
                                                      				<l:a uri="quality/initPlan/preAddLabQuaInitAuditPlan.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/initPlan/deleteLabQuaInitAuditPlan.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaInitAuditPlanVo.ids'); } else { clearAll('labQuaInitAuditPlanVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="name">
													要素
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
																	name="labQuaInitAuditPlanVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="c">
														  		<l:a href="#" uri="quality/initPlan/preUpdateLabQuaInitAuditPlan.action?labQuaInitAuditPlanVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/initPlan/deleteLabQuaInitAuditPlan.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
																&nbsp;&nbsp;
                                                      			<a id="BtnEdit" href="javascript:void(0);" onclick="window.location.href='<%=basePath%>quality/initPlan/listLabQuaInitAuditPlan4Child.action?labQuaInitAuditPlanVo.rank=2&labQuaInitAuditPlanVo.parentId=${id}';">检查内容</a>                    
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
										page="/jsp/include/page.jsp?formName=labQuaInitAuditPlanForm"
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
