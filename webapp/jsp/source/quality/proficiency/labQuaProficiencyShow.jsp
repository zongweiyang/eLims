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
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>年度比对和验证实验<s:text name="record.info"/></span>
											</div>
											<table class="FormtableCon">
										 		<tr>
													<td class="r" width="150">
														<label>
															单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
														</label>
													</td>
													<td>
														${labQuaProficiencyVo.orgName }
													</td>
													<td class="r" width="150">
													       <label>
														   <s:text name="duibichkitem"/>：
															</label>
													</td>
													<td>
															${labQuaProficiencyVo.ratioItem}
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															参&nbsp;&nbsp;加&nbsp;&nbsp;人：
														</label>
													</td>
													<td >
													       ${labQuaProficiencyVo.joinPeople}
													</td>
													<td class="r" width="150">
													       <label>
														   实施日期：
															</label>
													</td>
													<td>
															${labQuaProficiencyVo.planTime}
													</td>
												</tr>
													<tr>
													<td class="r" width="150"">
														<label>
															经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：
														</label>
													</td>
													<td >
													       ${labQuaProficiencyVo.payMoney}
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
													    <label>
														     比对/验证内容：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaProficiencyVo.contents" cols="40" rows="3" id="contents">${labQuaProficiencyVo.contents}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
													    <label>
														     主任意见：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaProficiencyVo.directorViews" cols="40" rows="3" id="directorViews">${labQuaProficiencyVo.directorViews}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
													    <label>
														     <s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea readonly="readonly" name="labQuaProficiencyVo.remark" cols="40" rows="3" id="remark">${labQuaProficiencyVo.remark}</textarea>
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
