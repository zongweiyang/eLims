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
		<form action="" method="post" name="form">
			<input type="hidden" value="${labMethodVo.id}" name="labMethodVo.id" id="id" />
			<input type="hidden" value="${labMethodVo.id}" name="id" id="id" />
			<input type="hidden" value="${labMethodVo.uuid}" name="labMethodVo.uuid" id="uuid" />
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
												<span>检测${funName }</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td class="w100">
														<label>
															方法名称：
														</label>
													</td>
													<td>
														${labMethodVo.name}
													</td>
													<td class="w100">
														<label>
															方法编号：
														</label>
													</td>
													<td>
														${labMethodVo.code}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用：
														</label>
													</td>
													<td>
														${labMethodVo.price}
														（<s:text name="theme.yuan"/>）
													</td>
													<td><label>方法文件：</label></td>
													<td>
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>
																	<a href="${path }" id="fileId">${name } </a>
																</span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													</td>
												</tr>
												<tr>
													<td>
														<label>使用仪器：</label>
													</td>
													<td colspan="3">
														<textarea cols="60" id="appName" rows="3" name="labMethodVo.labApparaName" onclick="showApp()" ></textarea>
														<input type="hidden" id="appId" name="labMethodVo.labApparaId" value="${labMethodVo.labApparaId}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="60" rows="3" name="labMethodVo.context" readonly="readonly">${labMethodVo.context}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="60" rows="3" name="labMethodVo.remark" readonly="readonly">${labMethodVo.remark}</textarea>
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
