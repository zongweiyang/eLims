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
	<body>
		<form action="" method="post" name="labMethodFrom">
			<input name="labStandardItemMethodVo.standardId" id="standardId" type="hidden" value="${labStandardItemMethodVo.standardId}" />
			<input name="labStandardItemMethodVo.itemId" id="itemId" type="hidden" value="${labStandardItemMethodVo.itemId}" />
			<input type="hidden" id="methodIds" name="labStandardItemMethodVo.methodIds" value="${labStandardItemMethodVo.methodIds}" />
			<input type="hidden" id="currentPage" name="currentPages" value="${request.currentPages}" />
			<input type="hidden" id="pageSize" name="pageSizes" value="${request.pageSizes}" />
			<input type="hidden" id="labSamTypeId" name="labStandardItemVo.labSamTypeId" value="${labStandardItemVo.labSamTypeId}" />
			<input type="hidden" id="name" name="labItemVo.name" value="${labItemVo.name}" />
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
					                      							<label><s:text name="method.name"/>：</label>
					                      						</td>
					                      						<td>
					                      							<input type="text" name="labMethodVo.name" id="name" value="${labMethodVo.name }"/>
					                      						</td>
					                      						
										                      	<td>
										                      		<l:a uri="${SessionContainer.lastUrl}" onclick="goAction('showLabMethod4select.action');" value="fun.query" />
										                      	</td>
										                      	<td>
	                                                     			<l:a uri="/klg/labStandardItem/addLabStandardItemMethod.action" onclick="goAction('/klg/labStandardItem/addLabStandardItemMethod.action');" value="msg.confirm" />
	                                                     		</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th width="30">
													<img src="<%=basePath%>img/icon_drag.gif"/>
												</th>
												<th width="30">
													<!-- <input  type="checkbox" onclick="if(this.checked==true) { checkAll('ids'); } else { clearAll('ids'); }" />
													 -->
													<input type="checkbox" id="ids"
														onclick="if(this.checked==true) { checkAll('labStandardItemMethodVo.ids'); } else { clearAll('labStandardItemMethodVo.ids'); }" />
												</th>
												<th property="name">
													<s:text name="method.name"/>
												</th>
												<th property="code">
													<s:text name="methodno"/>
												</th>
											</tr>
											<s:if test="pageResult.resultList!=null">
												<s:set name="alllist" value="pageResult.resultList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c" width="30">
																${st.index+1}
														</td>
														<td width="30" class="c">
															<input type="checkbox" value="${id}"  name="labStandardItemMethodVo.ids"/>
														</td>
														<td width="200">
															${name}
														</td>
														<td width="150">
															${code}
														</td>
													</tr>
												</s:iterator>
											</s:if>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labMethodFrom" flush="true" /></td>
							</tr>
						</table>
				    </td>
				</tr>
			</table>
		</form>
	</body>
	<script>
		$(function(){
			var methodIds=$('#methodIds').val();
			$('input[name="labStandardItemMethodVo.ids"]').each(function(){
				if(methodIds.indexOf($(this).val())!=-1){
					$(this).attr('checked','checked');
				}
			});
		});
	</script>
</html>		
