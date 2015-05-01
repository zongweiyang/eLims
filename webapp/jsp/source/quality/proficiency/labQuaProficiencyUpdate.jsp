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
			function checkLab(obj){
				var thisVal = $(obj).val();
				var name=$(obj).val();;
				var oo=name.split('|');
				$('#OrgId').val(oo[0]);
				$('#OrgName').val(oo[1]);
			}
			function showLabQuaProficiencyPlan4select(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/proficiency/showLabQuaProficiencyPlan4select.action',
					title:'<s:property value="getText('duibichecklist')"/>',
					opacity:0.4,
					width:900, 
					height:400,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaProficiencyPlanForm" id="form">
			<input type="hidden" value="${labQuaProficiencyPlanVo.id}" name="labQuaProficiencyPlanVo.id" id="id" />
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
												<span><s:text name="lab.code.modify"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
																	<l:a uri="back" value="msg.back"/> 
																</td>
                                                      			<td>
                                                      				<l:a uri="quality/proficiency/updateLabQuaProficiency.action" onclick="goAction('updateLabQuaProficiency.action');" value="lab.code.save"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>年度比对和验证实验记录表</span>
											</div>
											<table class="FormtableCon">
										 		<tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													<s:select headerKey="" headerValue="请选择" list="labOrgVoList" theme="simple" listKey="id+'|'+name" value="'${labQuaProficiencyVo.orgId}|${labQuaProficiencyVo.orgName}'" listValue="name" cssStyle="width:150px;" onchange="checkLab(this);"></s:select>
													<input size="40" type="hidden"  id="orgId" valType="required" msg="请选择单位" name="labQuaProficiencyVo.orgId" value="${labQuaProficiencyVo.orgId }"  />
													<input size="40" type="hidden"  id="orgName" name="labQuaProficiencyVo.orgName"  value="${labQuaProficiencyVo.orgName }" />
												</td>
												<td class="r" width="150">
													<label>
														比对计划：
													</label>
												</td>
												<td>
													<input size="40" onclick="showLabQuaProficiencyPlan4select();" name="labQuaProficiencyVo.proficiencyPlanName" id="proficiencyPlanName" type="text" value="${labQuaProficiencyVo.proficiencyPlanName}"/>
													<input size="40" name="labQuaProficiencyVo.proficiencyPlanId" valType="required" msg="比对计划不能为空"  id="proficiencyPlanId" type="hidden" value="${labQuaProficiencyVo.proficiencyPlanId}"/>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														参&nbsp;&nbsp;加&nbsp;&nbsp;人：
													</label>
												</td>
												<td >
												       <input size="40" name="labQuaProficiencyVo.joinPeople" id="joinPeople"
														type="text" value="${labQuaProficiencyVo.joinPeople }"
														  />
												</td>
												<td class="r" width="150">
												       <label>
													   实施日期：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaProficiencyVo.planTime" id="planTime" readonly="readonly" 
														type="text"  value="${labQuaProficiencyVo.planTime }" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
												</td>
											</tr>
												<tr>
												<td class="r" width="150"">
													<label>
														经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：
													</label>
												</td>
												<td >
												       <input size="40" name="labQuaProficiencyVo.payMoney" id="payMoney"
														type="text" value="${labQuaProficiencyVo.payMoney}"
														onblur="checkNum(this);"/>
												</td>
												<td class="r" width="150">
												       <label>
													   <s:text name="duibichkitem"/>：
														</label>
												</td>
												<td>
														<input size="40" name="labQuaProficiencyVo.ratioItem" id="ratioItem" 
														type="text"  value="${labQuaProficiencyVo.ratioItem}" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												    <label>
													     比对/验证内容：
													</label>
												</td>
												<td colspan="3">
													<textarea name="labQuaProficiencyVo.contents" cols="40" rows="3" id="contents">${labQuaProficiencyVo.contents}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												    <label>
													     主任意见：
													</label>
												</td>
												<td colspan="3">
													<textarea name="labQuaProficiencyVo.directorViews" cols="40" rows="3" id="directorViews">${labQuaProficiencyVo.directorViews}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												    <label>
													     <s:text name="lab.remark"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea name="labQuaProficiencyVo.remark" cols="40" rows="3" id="remark">${labQuaProficiencyVo.remark }</textarea>
												</td>
											</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
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
	</body>
</html>		
