<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
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
			function deleteEntity(url){
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goToNextAction(url);
				}
				return ;
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('labChargeVo.ids');
				if(checkbox==0){
					validate.tip('请选择要删除的条目.',$('labChargeVo.ids'));
					return ;
				}
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goToNextAction(url);
				}
				return ;
			}

		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labChargeForm" id="form">
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
					                      						<td><label><s:text name="feeunit"/>：：</label></td>
					                      						<td>
					                      							<input type="text" name="labChargeVo.code" id="code" value="${labChargeVo.code }" />
					                      						</td>
					                      						<td><label><s:text name="payunit"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labChargeVo.paymentUnit" id="paymentUnit"  value="${labChargeVo.paymentUnit }" />
					                      						</td>
					                      						<td>
					                      							<select name="labChargeVo.isEnd" style="width: 80px;" onchange="submitAction();">
					                      								<option value="" <s:if test="${labChargeVo.isEnd == '' }"> selected="selected"</s:if> ><s:text name="totalmoney"/></option>
					                      								<option value="N" <s:if test="${labChargeVo.isEnd == 'N' }"> selected="selected"</s:if>><s:text name="uncomed"/></option>
					                      								<option value="Y" <s:if test="${labChargeVo.isEnd == 'Y' }"> selected="selected"</s:if>><s:text name="alreadyed"/></option>
					                      							</select>
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
                                                      				<l:a uri="charge/labCharge/preAddLabCharge.action" value="lab.code.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="charge/labCharge/deleteLabCharge.action" onclick="deleteEntityForBatch('charge/labCharge/deleteLabCharge.action');" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labChargeVo.ids'); } else { clearAll('labChargeVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="code">
													<s:text name="feeunit"/>：
												</th>
												<th property="paymentUnit">
													<s:text name="chargeoffice"/>
												</th>
												<th class="c" property="payMoney">
													<s:text name="theme.charge.money"/>
												</th>
												<th class="c" property="yiMoney">
													<s:text name="theme.charged.moneyy"/>
												</th>
												<th class="c" property="payMoney-yiMoney">
													<s:text name="lastmoney"/>
												</th>
												<th property="collectionName">
													<s:text name="doman"/>
												</th>
												<th property="isEnd">
													<s:text name="ifovered"/>
												</th>
												<th property="createTime">
													<s:text name="chargedate"/>
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
																	name="labChargeVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																<l:a href="#" uri="charge/labCharge/showLabCharge.action?labChargeVo.id=${id}" value="${code}"/>
															</td>
															<td class="l">
																${paymentUnit }
															</td>
															<td class="r">
																<fmt:formatNumber value="${payMoney}" pattern="￥#,##0.0"/>
															</td>
															<td class="r">
																<fmt:formatNumber value="${yiMoney}" pattern="￥#,##0.0"/>
															</td>
															<td class="r">
																<fmt:formatNumber value="${payMoney-yiMoney}" pattern="￥#,##0.0"/>
															</td>
															<td class="l">
																${collectionName }
															</td>
															<td class="c">
															  <s:if test="${isEnd == 'Y' }">
																<span style="color: red;"><s:text name="alreadyed"/></span>
															  </s:if>
															  <s:else>
															  	<s:text name="uncomed"/>
															  </s:else>	
															</td>
															<td class="c">
																${fn:substring(createTime, 0, 10)}
															</td>
															<td class="c">
																<s:if test="${isEnd == 'Y' }">
																	<a style="color: gray;"><s:text name="charge.fee"/>&nbsp;&nbsp;&nbsp;
															  	</s:if>
															  	<s:else>
															  		<l:a href="#" uri="charge/labCharge/preUpdateLabCharge.action?labChargeVo.id=${id}" value="charge.fee" />
																	&nbsp;&nbsp;
																	<l:a href="#" uri="charge/labCharge/deleteLabCharge.action?labChargeVo.ids=${id}" value="lab.code.del" />	  
																</s:else>
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
										page="../../include/page.jsp?formName=labChargeForm"
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
	 <%@ include file="../../include/foot.jsp"%> 
	</body>
</html>		
