<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
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
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		function showDetail4User(month,userId,userName){
			var url  = '${basePath}/attendance/labAttendance/showLabAttendance4User.action?labAttendanceVo.userId='+userId+'&labAttendanceVo.workMonth='+month;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:month+userName+'考勤记录',
				opacity:0.4,
				max: false,                  
		        min: false,
				width:800,
				height:500,
				lock:true
			 });
		}
		</script>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labAttendanceFrom" id="labAttendanceFrom">
			<input name="labAttendanceVo.userId" value="${labAttendanceVo.userId}" type="hidden" />
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
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:text name="msg.depart"/>：
																	<s:select list="#request.orgList" listKey="id" listValue="name" name="labAttendanceVo.orgId" theme="simple"  onchange="submitvalue('attendance/labAttendance/listLabAttendance4All.action')"></s:select>
																</td>
																<td>
																	<s:text name="month"/>份：
																	<input type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM',alwaysUseStartDate:true})" name="labAttendanceVo.workMonth" value="${labAttendanceVo.workMonth}" onchange="submitvalue('attendance/labAttendance/listLabAttendance4All.action')"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTableBOX01 b" id="Tab01">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="atd.info"/></span>
												</div>
												<table class="FormtableCon_sform">
													<tr>
														<th>
															应出勤（<s:text name="dayday"/>）
														</th>
														<th>
															<s:text name="lated"/>（<s:text name="ciren"/>）
														</th>
														<th>
															<s:text name="be.out"/>（<s:text name="ciren"/>）
														</th>
														<th>
															<s:text name="save.atd"/>（<s:text name="ciren"/>）
														</th>
														<th>
															<s:text name="lat.card"/>（<s:text name="ciren"/>）
														</th>
													</tr>
													<tr>
														<td class="c">
															<font color="blue">${countList[0]}</font>
														</td>
														<td class="c">
															<s:if test="${countList[1]>0}">
																<font color="red">${countList[1]}</font>
															</s:if>
															<s:else>
																${countList[1]}
															</s:else>
														</td>
														<td class="c">
															<s:if test="${countList[2]>0}">
																<font color="red">${countList[2]}</font>
															</s:if>
															<s:else>
																${countList[2]}
															</s:else>
														</td>
														<td class="c">
															<s:if test="${countList[3]>0}">
																<font color="red">${countList[3]}</font>
															</s:if>
															<s:else>
																${countList[3]}
															</s:else>
														</td>
														<td class="c">
															<s:if test="${countList[4]>0}">
																<font color="red">${countList[4]}</font>
															</s:if>
															<s:else>
																${countList[4]}
															</s:else>
														</td>
													</tr>
												</table>
												<table class="FormtableCon_sform">
													<tr>
														<th>
														<s:text name="nike.name"/>
														</th>
														<th>
													<s:text name="msg.depart"/>
														</th>
														<th>
															<s:text name="act.atd"/>（<s:text name="dayday"/>）
														</th>
														<th>
															<s:text name="lated"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="be.out"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="save.atd"/>（<s:text name="times"/>）
														</th>
														<th>
															<s:text name="lat.card"/>（<s:text name="times"/>）
														</th>
													</tr>
													<s:iterator value="countAllList" status="st" id="all">
														<tr>
															<td class="l">
																<a href="javascript:;" onclick="showDetail4User('${labAttendanceVo.workMonth}','${all[0]}','${all[1]}');">${all[1]}</a>
															</td>
															<td class="l">
																${all[2]}
															</td>
															<td class="c">
																<font color="blue">${all[3]}</font>
															</td>
															<td class="c">
																<s:if test="${all[4]>0}">
																	<font color="red">${all[4]}</font>
																</s:if>
																<s:else>
																	${all[4]}
																</s:else>
															</td>
															<td class="c">
																<s:if test="${all[5]>0}">
																	<font color="red">${all[5]}</font>
																</s:if>
																<s:else>
																	${all[5]}
																</s:else>
															</td>
															<td class="c">
																<s:if test="${all[6]>0}">
																	<font color="red">${all[6]}</font>
																</s:if>
																<s:else>
																	${all[6]}
																</s:else>
															</td>
															<td class="c">
																<s:if test="${all[7]>0}">
																	<font color="red">${all[7]}</font>
																</s:if>
																<s:else>
																	${all[7]}
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</div>
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
</html>
