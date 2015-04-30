<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
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
		<form action="" method="post" name="form" id="form">
			<input type="hidden" value="${labItemVo.id}" name="labItemVo.id" id="id" />
			<input type="hidden" value="${pagerMethod}" name="pagerMethod" id="pagerMethod" />
			<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
			<input type="hidden" value="${currentPage}" name="currentPage" id="currentPage" />
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
												<span>${funName }[<font color="blue">${labItemVo.name}</font>]</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150"><label><s:text name="item.name"/>：</label></td>
													<td>
														${labItemVo.name}
													</td>
													<td class="r" width="150"><label><s:text name="itemunit"/>：</label></td>
													<td class="l">
														${labItemVo.unit}
													</td>
												</tr>
												<tr>
													<td class="r"><label>所用仪器：</label></td>
													<td class="l">
														${labItemVo.appName}
													</td>
													<td class="r"><label>标准分值：</label></td>
													<td class="l">
														${labItemVo.demo1}
													</td>
												</tr>
												<tr>
													<td class="r"><label><s:text name="unithoure"/>：</label></td>
													<td class="l">
														${labItemVo.demo2}
													</td>
													<td class="r"><label>价格：</label></td>
													<td class="l">
														${labItemVo.demo5}
													</td>
												</tr>
												<tr>
													<td><label>收费方式：</label></td>
													<td>
														<s:radio disabled="true" list="#{'0':getText('bytemi'),'1':getText('bynumber')}" value="${labItemVo.priceType}" name="labItemVo.priceType" theme="simple"></s:radio>
													</td>
												</tr>
												<tr>
													<td class="r"><label><s:text name="lab.remark"/>：</label></td>
													<td colspan="3" class="l">
														<textarea readonly="readonly" cols="50" rows="3" name="labItemVo.remark">${labItemVo.remark}</textarea>
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
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
</html>		
