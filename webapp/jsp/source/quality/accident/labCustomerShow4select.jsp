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
				var accUnitIds="";
	       		var accUnits="";
	       		$('input[name*=labCustomerVo.ids]:checked').each(function (linked){
	       			var check=$(this);
	       			var accUnitId=$(check).val();
	       			var accUnit=$(check).attr('accUnit');
	       			accUnitIds+=accUnitId+",";
	       			accUnits+=accUnit+",";
	       		});
				if(accUnitIds.length>0){
					accUnitIds=accUnitIds.substring(0,accUnitIds.length-1);
				}       		
				if(accUnits.length>0){
					accUnits=accUnits.substring(0,accUnits.length-1);
				}
				$('#accUnitId',D).val(accUnitIds);
				$('#accUnit',D).val(accUnits);
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
																		<s:text name="cust.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labCustomerVo.name" id="name"
																		value="${labCustomerVo.name}" />
																</td>
																<td>
																	<l:a uri="quality/accident/listLabCustomer4select.action" onclick="goAction('quality/accident/showLabCustomer4select.action');" value="fun.query" />
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
                                                     				<img height="20" width="20" src="<%=basePath%>img/xinjian.gif"/><b><s:text name="confirmseelet"/></b></a>
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
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="name">
													<s:text name="cust.name"/>
												</th>
												<th property="type">
													<s:text name="custtype"/>
												</th>
												<th property="level">
													<s:text name="custclass"/>
												</th>
												<th property="buildDate">
													<s:text name="buildtime"/>
												</th>
											</tr>
											<s:if test="labCustomerVoList!=null">
												<s:set name="alllist" value="labCustomerVoList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<input key="${id}" style="border: 1px;" index="${st.index+1}"  accUnit="${name}" type="radio" name="labCustomerVo.ids" value="${id}" id="ids${st.index+1}"/>
														</td>
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td>
															${name}
														</td>
														<td class="l">
															${type}
														</td>
														<td class="l">
															${level}
														</td>
														<td class="c">
															${buildDate}
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
				</tr>
			</table>
		</form>
	</body>
	<script>
		$(function(){
			var accUnitIds=$("#accUnitId",D).val();
			if(accUnitIds.length>0){
				var accUnitIdArray=accUnitIds.split(",");
				for(var i=0;i<accUnitIdArray.length;i++){
					var accUnitId=accUnitIdArray[i];
					$('input[key="'+accUnitId+'"]').attr('checked','checked');
				}			
			}
		});
	</script>
</html>		
