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
			function toAction(){
				var id = "";
				var name = "";
				var recTime = "";
				var address = "";
				var groupLeader = "";
				var groupMember = "";
				var orgName = "";
				var orgId = "";
				 $('input[name*=labQuaManageCheckPlanVo.ids]:checked').each(function (linked){
	       			var check=$(this);
	       			id=$(check).val();
	       			name = $(check).attr("planName");
	       			recTime = $(check).attr("recTime");
	       			address = $(check).attr("address");
	       			groupLeader = $(check).attr("groupLeader");
	       			groupMember = $(check).attr("groupMember");
	       			orgId = $(check).attr("orgId");
	       			orgName = $(check).attr("orgName");
		       	});
				 if(id == "" || id.length == 0){
				 	alert('<s:property value="getText('manageplanpingshen')"/>');
				 	return false;
				 }
				  closeMe();
				  D.location.href="<%=basePath%>/quality/manage/preAddLabQuaManageCheckRecord.action?labQuaManageCheckVo.quaManageCheckPlanId="+id+"&labQuaManageCheckVo.quaManageCheckPlanName="+name+"&labQuaManageCheckVo.recTime="+recTime+"&labQuaManageCheckVo.address="+address+"&labQuaManageCheckVo.groupLeader="+groupLeader+"&labQuaManageCheckVo.groupMember="+groupMember+"&labQuaManageCheckVo.orgId="+orgId+"&labQuaManageCheckVo.orgName="+orgName;
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
		<form action="" method="post" name="labQuaManageCheckPlanForm" id="form">
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
					                      						 <td><label><s:text name="commtunit"/>：</label></td>
																<td>
																	<s:select list="labOrgList" headerKey="" headerValue="--全部--"
																		name="labQuaManageCheckPlanVo.orgSearch" id="orgSearch" theme="simple"
																		listKey="id" listValue="name"></s:select>
																</td>
					                      						<td><label><s:text name="audittime"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labQuaManageCheckPlanVo.recTime" value="${labQuaManageCheckPlanVo.recTime}" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
					                      						 </td>
										                      	<td>
										                      		<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('showLabQuaManageCheckPlan4select.action');" value="fun.query" />
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
													<s:text name="commtunit"/>
												</th>
												<th property="name">
													<s:text name="plannamed"/>
												</th>
												<th property="address">
													<s:text name="auditsite"/>
												</th>
												<th property="foundation">
															<s:text name="acceptreason"/>
												</th>
												<th property="groupLeader">
													<s:text name="parandmaster"/>
												</th>
												<th property="checkPeople">
													<s:text name="accpterman"/>
												</th>
												<th property="checkTime">
													<s:text name="acceptdate"/>
												</th>
												<th property="recTime">
													<s:text name="audittime"/>
												</th>
											</tr>
												<s:if test="labQuaManageCheckPlanVoList!=null">
													<s:set name="alllist" value="labQuaManageCheckPlanVoList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox"
																	name="labQuaManageCheckPlanVo.ids" orgName="${orgName }" orgId="${orgId }" groupMember="${groupMember }" groupLeader="${groupLeader }" address="${address }" recTime="${recTime }" key="${id }" planName="${name }" id="ids${st.index+1 }" value="${id}" onclick="chooseOne(this);" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="">
																${orgName }
															</td>
															<td class="">
																${name }
															</td>
															<td  class="">
																${address }
															</td>
															<td class="">
																${foundation }
															</td>
															<td class="">
																${groupLeader }
															</td>
															<td  class="">
																${checkPeople }
															</td>
															<td  class="c">
																${checkTime }
															</td>
															<td class="c">
																${recTime }
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
