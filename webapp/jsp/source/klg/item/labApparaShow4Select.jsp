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
				var appIds="";
	       		var appNames="";
	       		$('input[name*=labApparaVo.ids]:checked').each(function (linked){
	       			var check=$(this);
	       			var appId=$(check).val();
	       			var appName=$(check).attr('appName');
	       			appIds+=appId+",";
	       			appNames+=appName+",";
	       		});
				if(appNames.length>0){
					appNames=appNames.substring(0,appNames.length-1);
				} 
				if(appIds.length>0){
					appIds=appIds.substring(0,appIds.length-1);
				}       		
				$('#appId',D).val(appIds);
				$('#appName',D).val(appNames);
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
																		<s:text name="app.name"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaVo.name" value="${labApparaVo.name}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="app.no"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaVo.no" value="${labApparaVo.no}" type="text" />
																</td>
                                                     			<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('showLabAppara4select.action');" value="fun.query" />
																</td>
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
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="app.name"/>
													</th>
													<th property="no">
														<s:text name="app.no"/>
													</th>
													<th property="code">
														<s:text name="outer.id"/>
													</th>
													<th property="specification">
														<s:text name="regular.sys"/>
													</th>
													<th>
														<s:text name="sam.state"/>
													</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input key="${id}" style="border: 1px;" index="${st.index+1}"  appName="${name}" type="checkbox" name="labApparaVo.ids" value="${id}" id="ids${st.index+1}"/>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${name }
															</td>
															<td class="l">
																${no }
															</td>
															<td class="l">
																${code }
															</td>
															<td class="l">
																${spec }
															</td>
															<td class="c">
																<s:if test="${status==0 }"><s:text name="common"/></s:if>
																<s:if test="${status==1 }"><s:text name="report.repair"/></s:if>
																<s:if test="${status==2 }"><s:text name="stoped"/></s:if>
																<s:if test="${status==7 }"><s:text name="dropped"/></s:if>
															</td>
														</tr>
													</s:iterator>
												</s:if>
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
			var appIds=$("#appId",D).val();
			if(appIds.length>0){
				var appIdArray=appIds.split(",");
				for(var i=0;i<appIdArray.length;i++){
					var appId=appIdArray[i];
					$('input[key="'+appId+'"]').attr('checked','checked');
				}			
			}
		});
	</script>
</html>		
