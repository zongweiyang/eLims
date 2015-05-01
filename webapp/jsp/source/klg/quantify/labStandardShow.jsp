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
		<form action="" method="post" name="labStandardForm">
			<input type="hidden" value="${labStandardVo.id}" name="labStandardVo.id" id="id" />
			<input type="hidden" value="${labStandardVo.standTypeId}" name="labStandardVo.standTypeId" id="id" />
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
												<span><s:text name="stdinfo"/>[<font color="blue">${labStandardVo.standTypeName}</font>]</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td><label><s:text name="std.name"/>：</label></td>
													<td>
														${labStandardVo.name}
													</td>
													<td><label><s:text name="stdcodes"/>：</label></td>
													<td>
														${labStandardVo.code}
													</td>
												</tr>
												<tr>
													<td><label><s:text name="publish.date"/>：</label></td>
													<td>
														${labStandardVo.releaseDate}
													</td>
													<td><label><s:text name="shishidate"/>：</label></td>
													<td>
														${labStandardVo.materialDate}
													</td>
												</tr>
												<tr>
													<td><label>标准类型：</label></td>
													<td colspan="3">
														<s:if test="${labStandardVo.standIndex=='1'}">限量标准</s:if>
														<s:elseif test="${labStandardVo.standIndex=='2'}">方法标准</s:elseif>
														<s:elseif test="${labStandardVo.standIndex=='3'}">两者都有</s:elseif>
													</td>
												</tr>
												<tr>
													<td><label>替代标准：</label></td>
													<td colspan="3"><textarea disabled="true" cols="50" rows="3" name="labStandardVo.replaceName">${labStandardVo.replaceName}</textarea></td>
												</tr>
												<tr>
													<td><label><s:text name="lab.remark"/>：</label></td>
													<td colspan="3"><textarea disabled="true" cols="50" rows="3" name="labStandardVo.remark">${labStandardVo.remark}</textarea></td>
												</tr>
											</table>
										</div>
									<!--<div class="Formtable">
											<div class="Formtabletitle">
												<span>级别/种类</span>
											</div>
											<table class="FormtableCon" id="indexTable">
												<tr>
													<td class="c" width="50">序号</td>
													<td class="c"><s:text name="config.name"/></td>
												</tr>
												<s:iterator status="st" value="labStandardVo.indexList">
													<tr>
														<td class="c">
															${st.index+1}
														</td>
														<td class="c">
															${name}
														</td>
													</tr>
												</s:iterator>
											</table>
										</div>-->
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
