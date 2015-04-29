<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			var api = frameElement.api, W = api.opener, D = W.document;
			function submitforform(){
				$('form').submit();
			}
			/*function ok(){
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
		 	}*/
		 	function ok(id,name,user,phone,email,fox,zipCode,ad){
		 		$('#labCustomerName',D).val(name);
		 		$('#labCustomerId',D).val(id);
		 		$('#user',D).val(user);
		 		$('#telephone',D).val(phone);
		 		$('#email',D).val(email);
		 		$('#fax',D).val(fox);
		 		$('#zipCode',D).val(zipCode);
		 		$('#address',D).val(ad);
		 		closeMe();
		 	}
		 	function closeMe(){
			  	api.close();
		 	}
		 	$(function(){
		 	   var ids=$('#itemId',D).val();
		 	   if(ids!=undefined){
		 	   		alert(ids);
			 	   var attIds = ids.split(',');
					for(var i=0;i<attIds.length;i++){
						$('input[name="labItemVo.ids"]').each(function(){
							if($(this).val() == attIds[i]){
								$(this).prop('checked',true);
							}
						});
				   }
			   }
			})
		 	
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="selectForm">
			<table id="bodyTable" class="bodytable_pop" width="98%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox_pop">
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                    		</tr>
                                                    			<tr>
                                                      			<td class="c">  
                                                      				<s:text name="cust.name"/>：<input type="text" value="${labCustomerVo.name}" name="labCustomerVo.name" />
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="sample/labSampRegister/listLabSampRegister.action" onclick="submitforform('${basePath}sample/labSampRegister/showLabCustomer4Select.action');return false;" value="fun.query"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
											<table class="listTable_pop" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="code.number"/>
													</th>
													<th>
														<s:text name="config.name"/>
													</th>
													<th>
														<s:text name="industry"/>
													</th>
													<th>
														<s:text name="telphone"/>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
											</thead>
											<tbody>
												<s:iterator value="#request.customerList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${num}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="l">
																${industry}
															</td>
															<td class="c">
																${phone}
															</td>
															<td class="c">
																<a href="javascript:;" onclick="ok('${id}','${name}','${firPerson}','${phone}','${email}','${fox}','${zipCode}','${address}');"><s:text name="selected"/></a>
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
