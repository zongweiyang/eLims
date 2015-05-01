<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			html {
				_overflow-x: hidden;
			}
		</style>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" value="${labQuaInitAuditPlanEleVo.id}" name="labQuaInitAuditPlanEleVo.id" id="id" />
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
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>  
                                                      				<l:a uri="back" value="msg.back"/>                                
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（显示） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="initineterele"/></span>
											</div>
											<table class="FormtableCon">
										 			<td><label><s:text name="elements"/></label></td>
										 			<td>
										 				<textarea cols="20" readonly="readonly" rows="10" name="labQuaAuditPlanVo.name" id="name">${labQuaAuditPlanVo.name}</textarea>
										 			</td>
										 		</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（显示） 结束 -->
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
	</body>
	<script></script>
</html>		
