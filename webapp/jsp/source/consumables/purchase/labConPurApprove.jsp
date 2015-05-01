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
					validate.tip("请至少选中一项.",$('#functionId'));
					return ;
				}
				if(confirm('确认删除选中信息吗?')){
					goToNextAction(url);
				}
				return ;
			};
			function addRow4Consumables(){
	            var url  = '/jsp/source/consumables/purchase/selectConsumablesTree.jsp';
				$.dialog({
					id:'userId',
					content:'url:'+url,
					title:'<s:property value="getText('consulist')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			}
			function submitAction(action){
				if(confirm("确认要提交吗?")){
					$('form').attr('action','${basePath}'+action);
					var flag=$('input[name="labConPurMainVo.auditResult"]:checked').val();
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
												<span>详情</span>
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
																		onclick="submitAction('consumables/labConPurMain/updateLabConPur4Approve.action');return false;"><img
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
															${labConPurMainVo.receiptno}
															<input type="hidden" value="${labConPurMainVo.id }"
																name="labConPurMainVo.id" />
														<td>
															<label>
																申请人：
															</label>
														</td>
														<td>
															${labConPurMainVo.applicant}
														</td>
														<td>
															<label>
																申请时间：
															</label>
														</td>
														<td>
															${labConPurMainVo.createTime}
														</td>
													</tr>
													<tr>
														<td>
															<label>
																<s:text name="remark"/>：
															</label>
														</td>
														<td rowspan="3" colspan="3">
															${labConPurMainVo.remark}
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
															<s:text name="conname"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="storenumber"/>
														</th>
														<th>
															采购数量
														</th>
														<th>
															<s:text name="remark"/>
														</th>
													</tr>
													<s:iterator value="conPurDetailList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${consumablesName}
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
													<span><s:text name="flow.audit"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="yes.no"/><s:text name="flow.pass"/>：
															</label>
														</td>
														<td>
															<input type="radio" name="labConPurMainVo.auditResult"
																value="1" checked="checked" />
															<s:text name="lab.yes"/>
															<input type="radio" name="labConPurMainVo.auditResult"
																value="-1" />
															<s:text name="lab.no"/>
														<td>
															<label>
																<s:text name="apply.opinion"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" id="auditMessage" name="labConPurMainVo.auditMessage"></textarea>
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
