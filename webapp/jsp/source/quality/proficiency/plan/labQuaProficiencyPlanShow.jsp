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
			
			#roletext {
				width: 70px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis; /* 支持 IE */
			}
			.myworkingboxttable th{
			background: #F5F5F5;
			color: #000000;
			border-top: #C0C0C0 1px solid;
			border-left: #C0C0C0 1px solid;
			border-right: #C0C0C0 1px solid;
			}
			.editType{
				visibility:hidden;
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
			function deleteOne(id){
				var planId = $('#id').val();
			   	if(confirm('确定要删除吗?')){	 
			       $('form').attr('action','<%=basePath%>quality/proficiencyPlan/deleteLabQuaProficiency.action?labQuaProficiencyVo.ids='+id+'&labQuaProficiencyVo.proficiencyPlanId='+planId);
		      	   $('form').submit();
			    }
			
			}
			function showLabQuaProficiency(id){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>quality/proficiency/showLabQuaProficiency.action?labQuaProficiencyVo.id='+id,
					title:'详情',
					opacity:0.4,
					width:900, 
					height:500,
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
												<span><s:text name="look.check"/></span>
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
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="plan.info"/>：</span>
											</div>
											<table class="FormtableCon">
										 		<tr>
												<td class="r" width="150">
													<label>
														单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
													</label>
												</td>
												<td>
													${labQuaProficiencyPlanVo.unitOrgName }
												</td>
												<td class="r" width="150">
													<label>
														计划名称：
													</label>
												</td>
												<td>
													${labQuaProficiencyPlanVo.name}
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														比对/验证类型：
													</label>
												</td>
												<td>
													${labQuaProficiencyPlanVo.proficiencyType}
												</td>
												<td class="r">
													<label>
														计划实施日期：
													</label>
												</td>
												<td>
													${labQuaProficiencyPlanVo.planTime}
												</td>
											</tr>
											<tr>
												<td class="r">
													<label>
														经费预算：
													</label>
												</td>
												<td colspan="3">
												      ${labQuaProficiencyPlanVo.payMoney}
												</td>
											</tr>
											<tr>
												<td class="r">
													<label>
														计&nbsp;&nbsp;划&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
													${labQuaProficiencyPlanVo.contPeople}
												</td>
												<td class="r">
													
													<label>
														计划日期：
													</label>
												</td>
												<td>
													    ${labQuaProficiencyPlanVo.contDate}
												</td>
											</tr>
											<tr>
												<td class="r">
												    <label>
													   批&nbsp;&nbsp;准&nbsp;&nbsp;人：
													</label>
												</td>
												<td>
														${labQuaProficiencyPlanVo.auditPeople}
												</td>
												<td class="r">
													
													<label>
														批准日期：
													</label>
												</td>
												<td>
													    ${labQuaProficiencyPlanVo.auditDate}
												</td>
											</tr>
											<tr>
												<td class="r">
												       <label>
													   比对/验证内容及要求：
														</label>
												</td>
												<td colspan="3">
													<textarea readonly="readonly" rows="3" cols="40" name="labQuaProficiencyPlanVo.contents" id="contents"  valType="required" msg="比对/验证类型不能为空">${labQuaProficiencyPlanVo.contents}</textarea>
												</td>
											</tr>
											<tr>
												<td class="r">
												    <label>
													     <s:text name="lab.remark"/>：
													</label>
												</td>
												<td colspan="3">
													<textarea readonly="readonly" name="labQuaProficiencyPlanVo.remark" cols="40" rows="3" id="remark">${labQuaProficiencyPlanVo.remark}</textarea>
												</td>
											</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
		                  				<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="record.info"/></span>
												<label style="float: right;margin-right: 20px;;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div id="Tab01" style="margin-top: 10px; margin-bottom: 10px;">
											<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
												<tr> 
													<th>
														<img src="<%=basePath%>img/icon_drag.gif"/>
													</th>
													<th>
														<s:text name="theme.depart"/>
													</th>
													<th>
														比对/验证内容
													</th>
													<th property="ratioItem">
														<s:text name="duibichkitem"/>
													</th>
													<th property="payMoney">
														经费预算
													</th>
													<th property="joinPeople">
														参加人
													</th>
													<th property="planTime">
														实施日期
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
												<s:if test="labQuaProficiencyVoList!=null">
													<s:set name="alllist" value="labQuaProficiencyVoList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${orgName }
															</td>
															<td class="l">
																${contents }
															</td>
															<td class="l">
																${ratioItem }
															</td>
															<td  class="r">
																${payMoney }
															</td>
															<td  class="l">
																${joinPeople }
															</td>
															<td class="c">
																${planTime }
															</td>
															<td class="c">
														  		<l:a href="#" uri="quality/proficiency/showLabQuaProficiency.action" onclick="showLabQuaProficiency('${id}');" value="详情" />
																&nbsp;&nbsp;
																<l:a href="#" uri="quality/proficiencyPlan/deleteLabQuaProficiency.action" onclick="deleteOne('${id}');return false;"  value="lab.code.del" />
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
