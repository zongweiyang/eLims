<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
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
			var api = frameElement.api, W = api.opener, D = W.document;
			function submitforform(){
				$('form').submit();
			}
			function ok(){
		 		var ids="";
		 		var names="";
		 		$('input[name="labItemVo.ids"]:checked').each(function(m){
		 			ids+=$(this).val()+",";
		 			names+=$(this).attr('key')+",";
		 		});
		 		if(ids.length>1){
		 			ids=ids.substring(0,ids.length-1);
		 			names=names.substring(0,names.length-1);
		 		}
		 		$('#itemId',D).val(ids);
		 		$('#itemName',D).val(names);
		 		closeMe();
		 	}
		 	function closeMe(){
			  	api.close();
		 	}
		 	$(function(){
		 	  var ids=$('#itemId',D).val();
		 	  var attIds = ids.split(',');
				for(var i=0;i<attIds.length;i++){
			$('input[name="labItemVo.ids"]').each(function(){
				if($(this).val() == attIds[i]){
					$(this).prop('checked',true);
				}
				});
			}
			})
		 	
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable" width="98%" cellspacing="0" cellpadding="0" border="0">
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
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                    		</tr>
                                                    			<tr>
                                                      			<td class="c">  
                                                      			<s:text name="checking.name"/>：<input type="text" value="${labItemVo.name}" name="labItemVo.name" />
                                                      			</td>
                                                      			<td>
                                                      			<l:a uri="sam/labSam/listLabSam.action" onclick="submitforform('${basePath}sam/labSam/showLabItem4Select.action');return false;" value="fun.query"/>
                                                      			</td>
                                                      			<td><l:a uri="sam/labSam/addLabSam.action" onclick="ok();" value="page.confirm"/></td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
											<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th width="40">
														<input type="checkbox" id="allCheckBox"
															key="labItemVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="item.name"/>
													</th>
											</thead>
											<tbody>
												<s:iterator value="#request.labItemList" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labItemVo.ids"
																	id="ids${st.index+1}" value="${id}" key="${name}"/>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${name}
															</td>
														</tr>
												</s:iterator>
											</tbody>
										</table>
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
