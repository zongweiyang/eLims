<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
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
			function submitvalue(){
				var yiMoney = $('#hj').html();
				var payMoney = $('#yhj').html();
				$('#payMoney',D).val(payMoney);
				$('#yiMoney',D).val(yiMoney);
				closeMe();
			}
			
			function checkNumber(obj){
				var value=$(obj).val();
				if(value.length>0){
					var key=BASEisNotFloat(value);
					if(key){
						alert("请输入正确的费用.");
						$(obj).val("0.0");
						return false;
					}
				}
			}
			
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			function total(){
				var total = parseFloat($("#sa1").val())+parseFloat($("#sa2").val())+parseFloat($("#sa3").val())+parseFloat($("#sa4").val());
				$("#hj").html(parseFloat(total));
			}
			
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labChargeForm" id="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
                                                   			<tr>
                                                      			<td>
                                                      				<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/filesave.gif" btFunctionType="add" btFunctionUrl="" btFunctionValue="lab.code.save" btIsDeafultImg="false" btJsFunctions="onclick:submitvalue();"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="information"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label><s:text name="payunit"/>：</label>
													</td>
													<td>
														<input type="text" name="" value="" id=""/>
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面） 结束 -->
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
	 <%@ include file="../../include/foot.jsp"%> 
	</body>
</html>		
