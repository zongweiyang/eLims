<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript">
	function goToNextAction(url){
		window.location.href='<%=basePath%>'+url;
	}
	function doRed(url){
		$('form').attr('action','<%=basePath%>'+url);
		$('form').submit();
	}
	/*function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			doRed(actionstr+'?path='+data);
	   	  			//try{
					//	$('body').shadow();
					//}catch(e){
					//	shandow('body');
					//}
				}
			}
	   });
	}*/
</script>

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form id="LogsForm" action="" name="LogsForm" method="post">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												<s:text name="log.index"/>
												<span></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="ops.people"/>：
																		<label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.operator" value="${labLogRecordVo.operator}" />
																</td>
																<td>
																	<label>
																		<s:text name="log.content"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.content" value="${labLogRecordVo.content}" />
																</td>
																<td>
																	<label>
																		<s:text name="module"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.module" value="${labLogRecordVo.module}" />
																</td>
																<td>
																	<label>
																		IP：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.ip" value="${labLogRecordVo.ip}" />
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="ops.time"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.startTime" value="${labLogRecordVo.startTime}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
																</td>
																<td>
																	<label>
																		<s:text name="to"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.endTime" value="${labLogRecordVo.endTime}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
																</td>
																<td>
																	<label>
																		<s:text name="data.table"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.workTable" value="${labLogRecordVo.workTable}" />
																</td>
																<td>
																	<label>
																		<s:text name="lab.code.ops"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labLogRecordVo.method" value="${labLogRecordVo.method}" />
																</td>
																<td>
																	<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query" />
																</td>
																<td>
																     <l:export params="labLogRecordVo,pageResult" type="excel" source="${labLogRecordVo.filePath}" target="${funName}-${now}.xls" value="export"/>
																	 <l:export params="labLogRecordVo,pageResult" type="word" source="${labLogRecordVo.filePath}" target="${funName}-${now}.xls" value="export"/>
																	 <!-- 
																	 <l:export params="pageResult.resultList" type="csv" property="XXX:method,XXX:module,XXX:workTable,XXX:ip,XXX:dateTime" target="${funName}-${now}.csv" value="导出csv${funName}"/>
																	 <l:export params="pageResult.resultList" type="csv" property="method,module,workTable,ip,dateTime" target="${funName}-${now}.csv" value="导出csv${funName}"/>
																	 -->
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th width="80">
														<s:text name="ops.people"/>
													</th>
													<th property="method" width="50">
														<s:text name="lab.code.ops"/>
													</th>
													<th property="module" width="80">
														<s:text name="lab.common.module"/>
													</th>
													<th property="workTable" width="100">
														<s:text name="data.table"/>
													</th>
													<th class="c">
														<s:text name="log.content"/>
													</th>
													<th property="ip">
														<s:text name="ip.addr"/>
													</th>
													<th property="dateTime">
														<s:text name="ops.time"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="pageResult.resultList" status="st" id="i">
													<tr title="<s:date name="dateTime" format="yyyy年MM月dd日" />，${operator }在客户端IP为：${ip }的终端机上在${workTable }中${method }数据，其内容为：&nbsp;&nbsp;${content }">
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td class="l">
															${operator}
														</td>
														<td class="c">
															${method }
														</td>
														<td class="c">
															${module }
														</td>
														<td class="c">
															${workTable }
														</td>
														<td>
															<s:if test="%{null!= content&&content.length()>50}">
																<s:property value="%{content.substring(0, 50)}" />…
           													</s:if>
															<s:else>
																<s:property value="%{content}" default="-" />
															</s:else>
														</td>
														<td class="c">
															${ip }
														</td>
														<td class="c">
															<s:date name="dateTime" format="yyyy-MM-dd HH:mm:ss" />
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=LogsForm" flush="true" />
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
</html>
