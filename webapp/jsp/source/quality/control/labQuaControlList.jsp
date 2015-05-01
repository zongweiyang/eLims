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
			       $('form').attr('action','<%=basePath%>quality/control/deleteLabQuaControl.action?labQuaControlVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaControlVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/control/deleteLabQuaControl.action');
						$('form').submit();	
					}	
				}		
			}
			function selectChange(obj){
       		var value=$(obj).val();
       		if(value.length==0){
       			$('#labOrgId').append($('<option>').attr({'value':'','selected':'selected'}).html("--全部--"));
       			return ;
       		}
       		$.ajax({
       			url:'<%=basePath%>quality/control/ajaxLabQuaControl4LabOrg.action',
       			type:'POST',
       			data:{'labQuaControlVo.unitOrgId':value},
       			dataType:'text',
       			success:function (data){
       				var result=eval('('+data+')');
       				$('#labOrgId').html('--全部--');
       				for(var i=0;i<result.length;i++){
       					$('#labOrgId').append(
       						$('<option>').attr({'value':result[i].id+'|'+result[i].name}).html(result[i].name)
       					);
       				}
       			},
       			error:function (){
       				alert('<s:property value="getText('network.error')"/>');
       			}
       		});
       		
       }
       function thisFlush(){
	    	$('#form').attr('action','${basePath}'+'quality/control/listLabQuaControl.action');
			$('#form').submit();
	    }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaControlForm" id="form">
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
																	<s:select list="unitOrgVoList" headerKey="" headerValue="" onchange="selectChange(this);"
																		name="labQuaControlVo.unitOrgSearch" id="unitOrgId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																 <td><label>实验室：</label></td>
																<td>
																	<s:select list="labOrgVoList" headerKey="" headerValue=""
																		name="labQuaControlVo.labOrgSearch" id="labOrgId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
					                      						<td><label><s:text name="chouchabiao"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaControlVo.anaNoSearch" value="${labQuaControlVo.anaNoSearch}" />
					                      						</td>
																<td><label>仪器型号：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaControlVo.appCodeSearch" value="${labQuaControlVo.appCodeSearch}" />
					                      						</td>
					                      						<td><label><s:text name="accidentinfo"/>：</label></td>
																<td>
																	<s:select list="#{'0':getText('unureturnerro'),'1':getText('dealingwith'),'2':getText('deleswith')}" headerKey="" headerValue=""
																		name="labQuaControlVo.accStatus" id="accStatus" theme="simple" value="'${labQuaControlVo.accStatus}'" ></s:select>
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
                                                      				<l:a uri="quality/control/preAddLabQuaControl.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/control/deleteLabQuaControl.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaControlVo.ids'); } else { clearAll('labQuaControlVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="anaNo">
													<s:text name="chouchabiao"/>
												</th>
												<th property="unitOrg.name">
													<s:text name="theme.depart"/>
												</th>
												<th property="labOrg.name">
													实验室
												</th>
												<th property="place">
											<s:text name="areamap"/>
												</th>
												<th property="standardName">
											<s:text name="zhixingstg"/>
												</th>
												<th property="appCode">
													<s:text name="app.style"/>
												</th>
												<th property="appNo">
													<s:text name="app.no"/>
												</th>
												<th property="conTime">
													<s:text name="jiandudate"/>
												</th>
												<th property="accStatus">
													<s:text name="accidentinfo"/>
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
																	name="labQuaControlVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																<l:a href="#" uri="quality/control/showLabQuaControl.action?labQuaControlVo.id=${id}" value="11" />
															</td>
															<td class="">
																${unitOrgName }
															</td>
															<td class="">
																${labOrgName }
															</td>
															<td  class="c">
																${place }
															</td>
															<td class="">
																${standardName }
															</td>
															<td  class="">
																${appCode }
															</td>
															<td  class="c">
																${appNo }
															</td>
															<td class="c">
																${conTime }
															</td>
															<td  class="c">
																<s:if test="${accStatus == 1}">
																	<span style="color: red;"><s:text name="dealtingwith"/></span>
																</s:if>
																<s:elseif test="${accStatus == 2}">
																	<span style="color: red;"><s:text name="sam.dealed"/></span>
																</s:elseif>
																<s:else>
																	<span style="color: red;"><s:text name="untrunacci"/></span>
																</s:else>
															</td>
															<td class="c">
														  		<l:a href="#" uri="quality/control/preUpdateLabQuaControl.action?labQuaControlVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/control/deleteLabQuaControl.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />	  
																<s:if test="${accStatus == ''|| accStatus == null || accStatus=='0'}">
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/accident/preAddLabQuaAccident4Other.action"  onclick="selectAccident('${id}','ACC-JDCC');" value="transferaccid"/>	
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
										page="/jsp/include/page.jsp?formName=labQuaControlForm"
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
