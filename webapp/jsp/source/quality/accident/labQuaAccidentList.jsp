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
			       $('form').attr('action','<%=basePath%>quality/accident/deleteLabQuaAccident.action?labQuaAccidentVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaAccidentVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/accident/deleteLabQuaAccident.action');
						$('form').submit();	
					}	
				}		
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="LabQuaAccidentForm" id="form">
		<input type="hidden" name="labQuaAccidentVo.accStatus" value="0"/>
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
					                      						<td><label><s:text name="accidunit"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaAccidentVo.accUnitSearch" value="${labQuaAccidentVo.accUnitSearch}" />
					                      						</td>
					                      						<td><label><s:text name="accidtype"/>：</label></td>
					                      						<td>
					                      							<s:select list="labCodeVoList" listKey="code" listValue="name"  headerKey="" headerValue="" name="labQuaAccidentVo.accType" id="accType" value="'${labQuaAccidentVo.accType }'" theme="simple" ></s:select>
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
                                                      				<l:a uri="quality/accident/preAddLabQuaAccident.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/accident/deleteLabQuaAccident.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaAccidentVo.ids'); } else { clearAll('labQuaAccidentVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="labCustomer.name">
													<s:text name="accidunit"/>
												</th>
												<th property="accType">
												  	<s:text name="accidtype"/>
												</th>
												<th>
													<s:text name="accihapreas"/>
												</th>
												<th property="createTime">
												  	 <s:text name="sam.register"/>
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
																	name="labQuaAccidentVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td  class="">
																${accUnit }
															</td>
															<td class="">
																${other }
															</td>
															<td  class="">
																${accDecs }
															</td>
															<td class="c">
																${createTime }
															</td>
															<td class="c">
																<l:a href="#" uri="quality/accident/showLabQuaAccident.action?labQuaAccidentVo.id=${id}" value="look.check" />
																&nbsp;&nbsp;
														  		<l:a href="#" uri="quality/accident/preUpdateLabQuaAccident.action?labQuaAccidentVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/accident/deleteLabQuaAccident.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />	
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
										page="/jsp/include/page.jsp?formName=LabQuaAccidentForm"
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
