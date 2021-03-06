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
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			function deleteEntity(obj){
			    $(obj).parent().parent().remove();
			}
			function deleteEntityForBatch(url){
				var checkbox=validationCheckbox('ids');
				if(checkbox==0){
                    validate.tip('<s:property value="getText('select.one.m')"/>',$('#functionId'));
					return;
				}
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goToNextAction(url);
				}
				return ;
			};
			function addRow4Reagent(){
	            var url  = '/jsp/source/reagent/purchase/selectReagentTree.jsp';
				$.dialog({
					id:'userId',
					content:'url:'+url,
					title:'<s:property value="getText('applisted')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			}
			function goAction(action){
				if(confirm('<s:property value="getText('confirmconmmit')"/>')){
					$('form').attr('action','${basePath}'+action);
					var flag=$('input[name="labReaPurMainVo.auditResult"]:checked').val();
					if("-1"==flag){
						var auditMessage=$('#auditMessage').val();
						if(""==auditMessage){
							alert('<s:property value="getText('plseauditopinion')"/>');
							return;
						}
					}
					$('form').submit();
				}
			}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
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
												<span><s:text name="details.info"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																		onclick="goAction('reagent/labReaPurMain/updateLabReaPur4Audit.action');return false;"><img
																			height="20" width="20"
																			src="${basePath}img/xinjian.gif" /><b><s:text name="post.commit"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTable"
											style="padding-top: 0; margin: 0; background: none;">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="base.info"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>
															</label>
														</td>
														<td>
															${labReaPurMainVo.receiptno}
															<input type="hidden" value="${labReaPurMainVo.id }"
																name="labReaPurMainVo.id" />
														<td>
															<label>
																<s:text name="applier"/>：
															</label>
														</td>
														<td>
															${labReaPurMainVo.applicant}
														</td>
														<td>
															<label>
																<s:text name="applytime"/>：
															</label>
														</td>
														<td>
															${labReaPurMainVo.createTime}
														</td>
													</tr>
													<tr>
														<td>
															<label>
																<s:text name="remark"/>：
															</label>
														</td>
														<td rowspan="3" colspan="3">
															${labReaPurMainVo.remark}
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="buyinginfo"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="rea.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="storenumber"/>
														</th>
														<th>
															<s:text name="buyingnumber"/>
														</th>
														<th>
															<s:text name="remark"/>
														</th>
													</tr>
													<s:iterator value="reaPurDetailList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${reagentName}
															</td>
															<td class="c">
																${size }
															</td>
															<td class="c">
																${amount }
															</td>
															<td class="c">
																${num }
															</td>
															<td class="c">
																${remark }
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="flow.check"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="flow.pass"/>：
															</label>
														</td>
														<td>
															<input type="radio" name="labReaPurMainVo.auditResult"
																value="1" checked="checked" />
															<s:text name="lab.yes"/>
															<input type="radio" name="labReaPurMainVo.auditResult"
																value="-1" />
															<s:text name="lab.no"/>
														<td>
															<label>
																<s:text name="audit.opn"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" id="auditMessage" name="labReaPurMainVo.auditMessage"></textarea>
														</td>
													</tr>
												</table>
											</div>

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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
