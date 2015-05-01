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
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			function closeMe(){
			  	api.close();
		 	}
			function goToNextAction(){
				 var id = "";
				 var name = "";
				 $('input[name*=id]:checked').each(function (linked){
	       			var check=$(this);
	       			id=$(check).val();
	       			name = $(check).attr("planName");
		       	});
				 if(id == "" || id.length == 0){
				 	alert('<s:property value="getText('internplan')"/>');
				 	return false;
				 }
				 $('#quaAuditPlanEleId',D).val(id);
				 $('#quaAuditPlanEleName',D).val(name);
				 closeMe();
			}
			function toAction(){
				var id = "";
				var name = "";
				 $('input[name*=id]:checked').each(function (linked){
	       			var check=$(this);
	       			id=$(check).val();
	       			name = $(check).attr("planName");
		       	});
				 if(id == "" || id.length == 0){
				 	alert('<s:property value="getText('internplan')"/>');
				 	return false;
				 }
				  closeMe();
				  D.location.href="<%=basePath%>quality/auditRecord/preAddLabQuaAuditRecord.action?labQuaAuditRecordVo.quaAuditPlanEleId="+id+"&labQuaAuditRecordVo.quaAuditPlanEleName="+name;
			}
		 	function chooseOne(cb){  
		         var obj = document.getElementsByName("id"); 
		         for (i=0; i<obj.length; i++){   
		             if (obj[i]!=cb){
		              obj[i].checked = false;
		             }else {
		             	obj[i].checked = true;  
		             } 
		         }
		     }
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaAuditPlanEleForm" id="form">
			<table id="bodyTable" class="bodytable_pop" width="100%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox_pop">
									   <!-- 按钮条 开始 -->
									   	<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						 <td><label><s:text name="theme.depart"/>：</label></td>
																<td>
																	<s:select list="labOrgVoList" headerKey="" headerValue="--全部--"
																		name="labQuaAuditPlanEleVo.orgIdSearch" id="unitId" theme="simple"
																		listKey="id" listValue="name" ></s:select>
																</td>
					                      						<td><label>编制人：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaAuditPlanEleVo.createPeople" value="${labQuaAuditPlanEleVo.createPeople}" />
					                      						</td>
										                      	<td>
										                      		<l:a uri="quality/proficiencyPlan/listLabQuaAuditPlanEle4select.action" onclick="goAction('showLabQuaAuditPlanEle4select.action');" value="fun.query" />
										                      	</td>
										                      	<td>    
                                                      				<l:a uri="quality/proficiencyPlan/preAddLabQuaProficiencyPlan.action" onclick="toAction();" value="page.confirm" />	                                              
                                                      			</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
										<table class="listTable_pop" cellspacing="0"
											cellpadding="0">
											<tr> 
												<th>
													
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th property="orgName">
													<s:text name="theme.depart"/>
												</th>
												<th property="purpose">
													审核目的
												</th>
												<th property="range">
													审核范围
												</th>
												<th property="implement">
													审核依据
												</th>
												<th property="createPeople">
													编制人
												</th>
												<th property="auditTime">
													批准日期
												</th>
											</tr>
												<s:if test="labQuaAuditPlanEleVoList!=null">
													<s:set name="alllist" value="labQuaAuditPlanEleVoList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox"
																	name="id" id="ids${st.index+1 }" key="${id }" planName="${purpose }" value="${id}" onclick="chooseOne(this);" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${orgName}
															</td>
															<td class="l">
																${purpose }
															</td>
															<td class="l">
																${range }
															</td>
															<td class="l">
																${implement }
															</td>
															<td  class="l">
																${createPeople }
															</td>
															<td  class="c">
																${auditTime }
															</td>
														</tr>
													</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td colspan="8" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</table>
									</div>
								</td>
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
