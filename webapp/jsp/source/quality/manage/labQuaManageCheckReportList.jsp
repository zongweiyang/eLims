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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaManageCheckReportForm" id="form">
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
																<td>
																	<label>
																		<s:text name="docstatus"/>：
																	</label>
																</td>
																<td>
																<s:select theme="simple" list="#{'':getText('alldata'),'0':getText('isartich'),'1':getText('unarchtided')}"  name="labQuaManageCheckVo.isFile"></s:select>
																</td>
																<td><label><s:text name="manageauditlist"/>：</label></td>
																<td>
																	<input name="labQuaManageCheckVo.quaManageCheckPlanName" id="quaManageCheckPlanName" value="${labQuaManageCheckVo.quaManageCheckPlanName }">
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
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="org.name">
													<s:text name="commtunit"/>
												</th>
												<th property="labQuaManageCheckPlan.name">
													<s:text name="manageauditlist"/>
												</th>
												<th property="address">
													<s:text name="auditsite"/>
												</th>
												<th property="labQuaManageCheckPlan.recTime">
													<s:text name="audittime"/>
												</th>
												<th property="isFile">
													<s:text name="lab.yes"/>否存档
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
															<td class="c">
																<s:if test="${isFile == 1 || isFile == '' || isFile == null  }">
																	<font color="red">未存档</font>
																</s:if>
																<s:if test="${isFile == 0 }">
																	<font color="red"><s:text name="aucechied"/></font>
																</s:if>
															</td>
															<td class="c">
																	<l:a href="#" uri="quality/manage/showLabQuaManageCheckReport.action?labQuaManageCheckVo.id=${id}"  value="look.check" />
																<s:if test="${isFile == '' || isFile == null || isFile== 1}"> 
															  		&nbsp;&nbsp;
															  		<l:a href="#" uri="quality/manage/preUpdateLabQuaManageCheckReport.action?labQuaManageCheckVo.id=${id}" value="填写报告" />
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
										page="/jsp/include/page.jsp?formName=labQuaManageCheckReportForm"
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
