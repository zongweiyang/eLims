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
				var cusUnitIds="";
	       		var cusUnits="";
	       		var firPersons= "";
	       		var phones = "";
	       		$('input[name*=labCustomerVo.ids]:checked').each(function (linked){
	       			var check=$(this);
	       			var cusUnitId=$(check).val();
	       			var cusUnit=$(check).attr('cusUnit');
	       			var firPerson=$(check).attr('cusName');
	       			var phone=$(check).attr('phone');
	       			cusUnitIds+=cusUnitId+",";
	       			cusUnits+=cusUnit+",";
	       			firPersons+=firPerson+",";
	       			phones+=phone+",";
	       		});
				if(cusUnitIds.length>0){
					cusUnitIds=cusUnitIds.substring(0,cusUnitIds.length-1);
				}       		
				if(cusUnits.length>0){
					cusUnits=cusUnits.substring(0,cusUnits.length-1);
				}
				if(firPersons.length>0){
					firPersons=firPersons.substring(0,firPersons.length-1);
				}
				if(phones.length>0){
					phones=phones.substring(0,phones.length-1);
				}
				$('#cusUnitId',D).val(cusUnitIds);
				$('#cusUnit',D).val(cusUnits);
				$('#name',D).val(firPersons);
				$('#phone',D).val(phones);
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
												<th property="level">
													<s:text name="org.people"/>
												</th>
												<th property="level">
													<s:text name="custtel"/>
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
															<input key="${id}" style="border: 1px;" index="${st.index+1}"  cusUnit="${name}" cusName="${firPerson }" phone="${phone }" type="radio" name="labCustomerVo.ids" value="${id}" id="ids${st.index+1}"/>
														</td>
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td>
															${name}
														</td>
														<td class="l">
															${firPerson}
														</td>
														<td class="l">
															${phone}
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
			var cusUnitIds=$("#cusUnitId",D).val();
			if(cusUnitIds.length>0){
				var cusUnitIdArray=cusUnitIds.split(",");
				for(var i=0;i<cusUnitIdArray.length;i++){
					var cusUnitId=cusUnitIdArray[i];
					$('input[key="'+cusUnitId+'"]').attr('checked','checked');
				}			
			}
		});
	</script>
</html>		
