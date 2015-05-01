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
			       $('form').attr('action','<%=basePath%>quality/complain/deleteLabQuaComplain.action?labQuaComplainVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaComplainVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/complain/deleteLabQuaComplain.action');
						$('form').submit();	
					}	
				}		
			}
			function thisFlush(){
		    	$('#form').attr('action','${basePath}'+'quality/complain/listLabQuaComplain.action');
				$('#form').submit();
		    }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="LabQuaComplainForm" id="form">
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
					                      						<td><label>投诉单号：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaComplainVo.no" value="${labQuaComplainVo.no}" />
					                      						</td>
					                      						<td><label><s:text name="opinionunit"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaComplainVo.unitSearch" value="${labQuaComplainVo.unitSearch}" />
					                      						</td>
					                      						<td><label>投诉人：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaComplainVo.nameSearch" value="${labQuaComplainVo.nameSearch}" />
					                      						</td>
					                      						 <td><label>事故情况：</label></td>
																<td>
																	<s:select list="#{'0':getText('unureturnerro'),'1':getText('dealingwith'),'2':getText('deleswith')}" headerKey="" headerValue="--全部--"
																		name="labQuaComplainVo.accStatus" id="accStatus" theme="simple" value="'${labQuaComplainVo.accStatus}'" ></s:select>
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
                                                      				<l:a uri="quality/complain/preAddLabQuaComplain.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/complain/deleteLabQuaComplain.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaComplainVo.ids'); } else { clearAll('labQuaComplainVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="no">
													投诉单号
												</th>
												<th property="unit">
													<s:text name="opinionunit"/>：
												</th>
												<th property="name">
													投诉人
												</th>
												<th property="skillPerson">
													实验室负责人
												</th>
												<th property="qualityPerson">
													质量负责人
												</th>
												<th property="appTime">
													投诉日期
												</th>
												<th property="accStatus">
													事故情况
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
																	name="labQuaComplainVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																<l:a href="#" uri="quality/complain/showLabQuaComplain.action?labQuaComplainVo.id=${id}" value="${no }" />
															</td>
															<td class="l">
																${unit }
															</td>
															<td class="l">
																${name }
															</td>
															<td  class="l">
																${skillPerson }
															</td>
															<td  class="l">
																${qualityPerson }
															</td>
															<td  class="c">
																${appTime }
															</td>
															<td  class="c">
																<s:if test="${accStatus == 1}">
																	<span style="color: red;">正在处理</span>
																</s:if>
																<s:elseif test="${accStatus == 2}">
																	<span style="color: red;"><s:text name="sam.dealed"/></span>
																</s:elseif>
																<s:else>
																	<span style="color: red;">未转事故</span>
																</s:else>
															</td>
															<td class="c">
														  		<l:a href="#" uri="quality/complain/preUpdateLabQuaComplain.action?labQuaComplainVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/complain/deleteLabQuaComplain.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />	
																<s:if test="${accStatus == ''|| accStatus == null || accStatus=='0'}">
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/accident/preAddLabQuaAccident4Other.action"  onclick="selectAccident('${id}','ACC-YWTS');" value="transferaccid"/>	
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
										page="/jsp/include/page.jsp?formName=LabQuaComplainForm"
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
