<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp" %>
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
			
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			function submitvalue(){
				var otherIds="";
	       		var otherNames="";
	       		$('input[name*=labSampRegisterVo.ids]:checked').each(function (linked){
	       			var check=$(this);
	       			var otherId=$(check).val();
	       			var otherName=$(check).attr('otherName');
	       			otherIds+=otherId+",";
	       			otherNames+=otherName+",";
	       		});
				if(otherIds.length>0){
					otherIds=otherIds.substring(0,otherIds.length-1);
				}       		
				if(otherNames.length>0){
					otherNames=otherNames.substring(0,otherNames.length-1);
				}
				$('#otherId',D).val(otherIds);
				$('#otherName',D).val(otherNames);
				closeMe();
			}
		</script>
	</head>
	<body>
		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable_pop" width="96%" cellspacing="0" cellpadding="0" border="0">
              	<tr>
		           	<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
								border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop">
										<div class="FUNCIONBARNEW">
                                           <table>
                                               <tr>
                                                	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                 		<table cellspacing="0" cellpadding="0" border="0">
                                                  			<tr>
																<td>
																	<label>
																		任务编号：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.no"
																		 value="${labSampRegisterVo.no}" />
																</td>
																<td>
																	<label>
																		课题&nbsp;/&nbsp;客户名称：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSampRegisterVo.labSampCustomerVo.labCustomerName"
																		 value="${labSampRegisterVo.labSampCustomerVo.labCustomerName}" />
																</td>
																<td>
																	<l:a uri="quality/accident/listLabSampRegister4select.action" onclick="goAction('quality/accident/showLabSampRegister4select.action');" value="fun.query" />
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
                                                     				<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue();" >
                                                     				<img height="20" width="20" src="<%=basePath%>img/xinjian.gif"/><b>确定选择</b></a>
                                                     			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>			
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<tr>
												<th>
												
												</th>
												<th property="no" width="150">任务编号</th>
												<th property="labSampCustomerVo.labCustomerName">课题&nbsp;/&nbsp;客户名称</th>
												<th width="70">样品数量</th>
												<th property="taskType" width="100">检测类别</th>
												<th property="reportType" width="100">报告性质</th>
												<th property="createTime" width="150">登记日期</th>
											</tr>
											<s:if test="labSampRegisterVoList!=null">
												<s:set name="alllist" value="labSampRegisterVoList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<input key="${id}" style="border: 1px;" index="${st.index+1}"  otherName="${no}" type="radio" name="labSampRegisterVo.ids" value="${id}" id="ids${st.index+1}"/>
														</td>
														<td class="c">
															${no}
														</td>
														<td>${labSampCustomerVo.labCustomerName}</td>
														<td>${sampNum}</td>
														<td class="c">${taskType}</td>
														<td class="c">${reportType}</td>
														<td class="c">${createTime}</td>
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
				</tr>
			</table>
		</form>
	</body>
	<script>
		$(function(){
			var otherIds=$("#otherId",D).val();
			if(otherIds.length>0){
				var otherIdArray=otherIds.split(",");
				for(var i=0;i<otherIdArray.length;i++){
					var otherId=otherIdArray[i];
					$('input[key="'+otherId+'"]').attr('checked','checked');
				}			
			}
		});
	</script>
</html>		
