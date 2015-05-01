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
			       $('form').attr('action','<%=basePath%>quality/cusVisit/deleteLabQuaCusVisit.action?labQuaCusVisitVo.ids='+id);
		      	   $('form').submit();
			    }
			
			}
			function deleteThis(){
				if(check('labQuaCusVisitVo.ids')){
					if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
					{
						$('form').attr('action','<%=basePath%>quality/cusVisit/deleteLabQuaCusVisit.action');
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
       			url:'<%=basePath%>quality/cusVisit/ajaxLabQuaCusVisit4LabOrg.action',
       			type:'POST',
       			data:{'labQuaCusVisitVo.unitOrgId':value},
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
	    	$('#form').attr('action','${basePath}'+'quality/cusVisit/listLabQuaCusVisit.action');
			$('#form').submit();
	    }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
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
																	<s:select list="unitOrgList" headerKey="" headerValue="--全部--" cssStyle="width:150px;" onchange="selectChange(this);"
																		name="labQuaCusVisitVo.unitOrgIdSearch" id="unitOrgId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																 <td><label>实验室：</label></td>
																<td>
																	<s:select list="labOrgList" headerKey="" headerValue="--全部--" cssStyle="width:150px;"
																		name="labQuaCusVisitVo.labOrgIdSearch" id="labOrgId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
					                      						<td><label><s:text name="custunit"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaCusVisitVo.cusUnitSearch" value="${labQuaCusVisitVo.cusUnitSearch}" />
					                      						 </td>
																<td><label>姓  名：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaCusVisitVo.nameSearch" value="${labQuaCusVisitVo.nameSearch}" />
					                      						</td>
					                      						<td><label>事故情况：</label></td>
																<td>
																	<s:select list="#{'0':getText('unureturnerro'),'1':getText('dealingwith'),'2':getText('deleswith')}" headerKey="" headerValue="--全部--"
																		name="labQuaCusVisitVo.accStatus" id="accStatus" theme="simple" value="'${labQuaCusVisitVo.accStatus}'" ></s:select>
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
                                                      				<l:a uri="quality/cusVisit/preAddLabQuaCusVisit.action" value="admin.add"/>
                                                      			</td>
                                                      			<td>    
                                                      				<l:a uri="quality/cusVisit/deleteLabQuaCusVisit.action" onclick="deleteThis();" value="lab.code.deleteall" />	                                              
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
														onclick="if(this.checked==true) { checkAll('labQuaCusVisitVo.ids'); } else { clearAll('labQuaCusVisitVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th  property="no">
													登记编号
												</th>
												<th property="unitOrg.name">
													<s:text name="theme.depart"/>
												</th>
												<th property="labOrg.name">
													实验室
												</th>
												<th property="labCustomer.name">
													<s:text name="custunit"/>
												</th>
												<th property="name">
													姓  名
												</th>
												<th property="phone">
													<s:text name="custtel"/>
												</th>
												<th property="visitTime">
													回访时间
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
																	name="labQuaCusVisitVo.ids" id="ids${st.index+1 }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																<l:a href="#" uri="quality/cusVisit/showLabQuaCusVisit.action?labQuaCusVisitVo.id=${id}" value="${no }" />
															</td>
															<td class="">
																${unitOrgName }
															</td>
															<td class="">
																${labOrgName }
															</td>
															<td class="">
																${cusUnit }
															</td>
															<td  class="">
																${name }
															</td>
															<td  class="c">
																${phone }
															</td>
															<td class="c">
																${visitTime }
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
														  		<l:a href="#" uri="quality/cusVisit/preUpdateLabQuaCusVisit.action?labQuaCusVisitVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/cusVisit/deleteLabQuaCusVisit.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />	  
																<s:if test="${accStatus == ''|| accStatus == null || accStatus=='0'}">
																	&nbsp;&nbsp;
																	<l:a href="#" uri="quality/accident/preAddLabQuaAccident4Other.action"  onclick="selectAccident('${id}','ACC-KHHFJL');" value="transferaccid"/>	
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
										page="/jsp/include/page.jsp?formName=labQuaCusVisitForm"
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
