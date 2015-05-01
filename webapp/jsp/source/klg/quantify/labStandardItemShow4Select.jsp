<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type='text/javascript' src='<%=basePath%>/js/autocomplete/jquery.autocomplete.min.js'></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/autocomplete/jquery.autocomplete.css" />
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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labItemform" id="labItemform" >
			<input name="labStandardItemVo.standardId" id="standardId" type="hidden" value="${labStandardItemVo.standardId}" />
			<table id="bodyTable" class="bodytable_pop" width="99%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop">
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labItemVo.name" value="${labItemVo.name }" size="45"/>
																</td>
																<td>
																	<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('showLabStandardItem4select.action');" value="fun.query" />
																</td>
																<td>
																	<l:a uri="klg/labStandardItem/addLabStandardItem.action" onclick="goAction('addLabStandardItem.action');" value="added" />
																	
																</td>
																<td style="vertical-align: middle;">
																	<font color="red"><s:text name="duhaofenge"/></font>
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
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th>
												</th>
												<th property="name">
													<s:text name="item.name"/>
												</th>
												<th property="unit">
													<s:text name="theme.depart"/>
												</th>
												<th property="demo1">
													<s:text name="indexnumber"/>
												</th>
												<th property="demo2">
													<s:text name="unithoure"/>
												</th>
												<th property="price">
													<s:text name="price.number"/>
												</th>
											</tr>
											<s:if test="#request.labItemVoList!=null">
												<s:set name="alllist" value="#request.labItemVoList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															${st.index+1}
														</td>
														<td class="c">
															<input type="checkbox"  value="${id}" name="labStandardItemVo.itemsIds"/>
														</td>
														<td class="l">
															${name}
														</td>
														<td class="l">
															${unit }
														</td>
														<td class="l">
															${demo1 }
														</td>
														<td class="l">
															${demo2 }
														</td>
														<td class="r">
															${price }
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
			var itemIds='${itemId }';
			if(itemIds.length>0){
				var itemIdArray=itemIds.split(",");
				for(var i=0;i<itemIdArray.length;i++){
					var itemId=itemIdArray[i];
					$('input[key="'+itemId+'"]').attr('checked','checked');
				}			
			}
		});
	</script>
</html>
