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
				 	alert('<s:property value="getText('selectchkplan')"/>');
				 	return false;
				 }
				 $('#proficiencyPlanId',D).val(id);
				 $('#proficiencyPlanName',D).val(name);
				 closeMe();
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
		   function onLoadData(){
       		var proficiencyPlanIds=$('#proficiencyPlanId',D).val();
			if(proficiencyPlanIds.length>0){
				var proficiencyPlanIdArray=proficiencyPlanIds.split(",");
				for(var i=0;i<proficiencyPlanIdArray.length;i++){
					var proficiencyPlanId=proficiencyPlanIdArray[i];
					$('input[key="'+proficiencyPlanId+'"]').attr('checked','checked');
				}			
			}
       	}
       	$(function (){
       		onLoadData();
       	});
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyPlanForm" id="form">
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
																	<s:select list="labOrgVoList" headerKey="" headerValue="请选择"
																		name="labQuaProficiencyPlanVo.unitOrgSearch" id="unitId" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
																<td><label><s:text name="config.name"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaProficiencyPlanVo.name" value="${labQuaProficiencyPlanVo.name}" />
					                      						</td>
																<td><label><s:text name="accpterman"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaProficiencyPlanVo.auditPeople" value="${labQuaProficiencyPlanVo.auditPeople}" />
					                      						</td>
										                      	<td>
										                      		<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('quality/proficiency/showLabQuaProficiencyPlan4select.action');" value="fun.query" />
										                      	</td>
										                      	<td>    
                                                      				<l:a uri="quality/proficiencyPlan/preAddLabQuaProficiencyPlan.action" onclick="goToNextAction();" value="page.confirm" />	                                              
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
												<th property="unitOrgName">
													<s:text name="theme.depart"/>
												</th>
												<th>
													<s:text name="plannamed"/>
												</th>
												<th>
													<s:text name="duibiyanzheng"/>
												</th>
												<th property="planTime">
													<s:text name="jishuashishidate"/>
												</th>
												<th property="payMoney">
													<s:text name="fundbudget"/>
												</th>
												<th property="auditPeople">
													<s:text name="accpterman"/>
												</th>
												<th property="auditDate">
													<s:text name="acceptdate"/>
												</th>
											</tr>
												<s:if test="labQuaProficiencyPlanVoList!=null">
													<s:set name="alllist" value="labQuaProficiencyPlanVoList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="radio"
																	name="id" id="ids${st.index+1 }" key="${id }" planName="${name }" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${name }
															</td>
															<td class="l">
																${unitOrgName }
															</td>
															<td class="l">
																${contents }
															</td>
															<td class="c">
																${planTime }
															</td>
															<td  class="r">
																 ${payMoney }
															</td>
															<td  class="l">
																${auditPeople }
															</td>
															<td class="c">
																${auditDate }
															</td>
														</tr>
													</s:iterator>
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
