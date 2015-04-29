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
			function deleteEntity(url){
				if(confirm('与该方法的标准量化信息会删除，确定删除？')){
					goToNextAction(url);
				}
				return ;
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('labMethodVo.ids');
				if(checkbox==0){
					validate.tip('请选择要删除的条目.',$('labItemVo.ids'));
					return ;
				}
				if(confirm('与该方法的标准量化信息会删除，确定删除？')){
					goToNextAction(url);
				}
				return ;
			}
			
			function deleteOne(id){
				if(confirm('确定要删除吗?')){	 
			      window.location.href='<%=basePath%>klg/labMethod/deleteLabMethod.action?labMethodVo.ids='+id;
			    }
			}
		</script>
		
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labMethodForm" id="form">
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
					                      							<label>方法名称：</label>
					                      						</td>
					                      						<td>
					                      							<input type="text" name="labMethodVo.name" id="name" value="${labMethodVo.name }"/>
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
                                                      				<l:a uri="klg/labMethod/preAddLabMethod.action" value="lab.code.add"/>
                                                      			</td>
                                                      			<td>          
                                                      				<l:a uri="klg/labMethod/deleteLabMethod.action" onclick="deleteEntityForBatch('klg/labMethod/deleteLabMethod.action');" value="lab.code.deleteall" />	                                        
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
												<th width="40">
													<input type="checkbox"
														onclick="if(this.checked==true) { checkAll('labMethodVo.ids'); } else { clearAll('labMethodVo.ids'); }" />
												</th>
												<th width="40">
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="name">
													方法名称
												</th>
												<th  property="code">
													方法编号
												</th>
												<th>
													<s:text name="usingapp"/>
												</th>
												<th  property="price">
													费用
												</th>
												<th  property="context">
													内容
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
																	name="labMethodVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																<l:a href="#" uri="klg/labMethod/showLabMethod.action?id=${id}" value="${name}"/>
															</td>
															<td class="l">
																${code}
															</td>
															<td>
																${labApparaName}
															</td>
															<td class="l">
																${price}
															</td>
															<td class="l">
																<s:if test="${fn:length(context)>20}">
																	${fn:substring(context,0,20)}..
																</s:if>
																<s:else>
																	${context}
																</s:else>
															</td>
															<td class="c">
																<l:a href="#" uri="klg/labMethod/preUpdateLabMethod.action?id=${id}" value="theme.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="klg/labMethod/deleteLabMethod.action?labMethodVo.ids=${id}" onclick="deleteOne('${id }');return false;" value="lab.code.del" />
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
										page="/jsp/include/page.jsp?actionparam=listLabMethod.action&formName=labMethodForm"
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
	</body>
</html>		
