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
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('labItemVo.ids');
				if(checkbox==0){
					validate.tip('请选择要删除的条目.',$('labItemVo.ids'));
					return ;
				}
				if(confirm('与该项目的标准量化信息会删除，确定删除？')){
					goAction(url);
				}
				return ;
			}
			function importItem(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>klg/labItem/preImportLabItem4Excel.action?labItemVo.type=${labItemVo.type}',
					title:'项目导入',
					opacity:0.4,
					width:400, 
					height:200,
					lock:true,
					max:false,
					min:false,
					close:function(){
						thisFlush();
					}
				 });
			}
			function thisFlush(){
		    	$('#form').attr('action','${basePath}'+'klg/labItem/listLabItem.action');
				$('#form').submit();
		    }
		    
		    function deleteOne(id){
				if(confirm('确定要删除吗?')){	 
			      window.location.href='<%=basePath%>klg/labItem/deleteLabItem.action?labItemVo.ids='+id;
			    }
			}
		    
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labItemform" id="form">

			<input type="hidden" name="labItemVo.type" value="${labItemVo.type}" />
			<input type="hidden" name="labStandardVo.standTypeName" value="${labStandardVo.standTypeName}" />
			<input type="hidden" name="labStandardVo.standTypeId" value="${labStandardVo.standTypeId}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labItemVo.name" value="${labItemVo.name }" />
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
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
																	<l:a uri="klg/labItem/preAddLabItem.action" onclick="goAction('/klg/labItem/preAddLabItem.action');" value="lab.code.add"/>
																</td>
																<td>
																	<l:a uri="klg/labItem/deleteLabItem.action" onclick="deleteEntityForBatch('/klg/labItem/deleteLabItem.action');" value="lab.code.deleteall" />	
																</td>
																<td>
																	<l:a uri="klg/labItem/preAddLabItem.action" onclick="importItem();" value="import.project"/>
																</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th width="40">
													<input type="checkbox" onclick="if(this.checked==true) { checkAll('labItemVo.ids'); } else { clearAll('labItemVo.ids'); }" />
												</th>
												<th width="40">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th  property="name">
													<s:text name="item.name"/>
												</th>
												<th  property="unit">
													<s:text name="theme.depart"/>
												</th>
												<th  property="demo1">
													标准分值
												</th>
												<th  property="demo2">
													工时
												</th>
												<th property="price">
													价格
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
																<input type="checkbox" name="labItemVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																<l:a href="#" uri="klg/labItem/showLabItem.action?id=${id}" value="${name}" />
															</td>
															<td class="l">
																${unit }
															</td>
															<td class="l">
																${demo1 }
															</td>
															<td class="l">
																${demo2 }
															</td>
															<td class="r">
																${demo5 }
															</td>
															<td class="c">
																<l:a href="#" uri="klg/labItem/preUpdateLabItem.action?id=${id}" value="theme.modify"/>
																&nbsp;&nbsp;
																<l:a href="#" uri="klg/labItem/deleteLabItem.action?labItemVo.ids=${id}" onclick="deleteOne('${id }');return false;" value="lab.code.del" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labItemform" flush="true" /></td>
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
