<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		function showMsg(url){
			var url  = '${basePath}'+url;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('todayatten')"/>',
				opacity:0.4,
				max: false,                  
		        min: false,
				width:300,
				height:200,
				lock:true
			 });
		}
		</script>
	<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#dayShow td {
	height: 56px;
	width: 100px;
	padding-top: 0px;
}
#dayShow td p {
	text-align: center;
}
#dayShow td .remark{
	text-align:left;
	vertical-align:bottom;
}
#dayShow td .day{
	font-weight: bold;
	font-size: 18px;
	text-align:left;
	vertical-align:top;
	margin-top: opx;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labAttendanceFrom" id="labAttendanceFrom">
			<input name="labAttendanceVo.userId" value="${labAttendanceVo.userId}" type="hidden" />
			<input name="labAttendanceVo.orgId" value="${labAttendanceVo.orgId}" type="hidden" />
			<table id="bodyTable" class="bodytable_pop" width="100%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox_pop">
										<div class="TabTableBOX01 b" id="Tab01">
											<div class="Formtable">
												<table class="FormtableCon_sform">
													<tr>
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
													<tr>
														<td class="c">
															<font color="#009933">${countList[0]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[1]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[2]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[3]}</font>
														</td>
														<td class="c">
															<font color="red">${countList[4]}</font>
														</td>
													</tr>
												</table>
												<table class="FormtableCon_sform" id="dayShow">
													<tr>
														<th>
															<s:text name="monday"/>
														</th>
														<th>
															<s:text name="quesday"/>
														</th>
														<th>
															<s:text name="thirday"/>
														</th>
														<th>
															<s:text name="fourday"/>
														</th>
														<th>
															<s:text name="firday"/>
														</th>
														<th>
															<s:text name="satarday"/>
														</th>
														<th>
															<s:text name="sunday"/>
														</th>
													</tr>
													<tr>
													<s:iterator value="attendList" status="st">
														<s:if test="${isGood=='Y'}">
																<td style="background-color: green;" title="加班">
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:if>
															<s:elseif test="${week==6||week==7}">
																<td style="background-color: #E0E0E0;">
																	<s:if test="${day!=''}">
																		<div class="day">${day}</div>
																		<p>&nbsp;</p>
																		<div class="remark">&nbsp;</div>
																	</s:if>
																</td>
															</s:elseif>
															<s:elseif test="${noWork=='Y'}">
																<td style="background-color: red;" title="旷工">
																	<div class="day">${day}</div>
																	<p>&nbsp;</p>
																	<div class="remark">&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${startFlag=='Y'||endFlag=='Y'}">
																<td style="background-color: yellow;" title="迟到早退">
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${afterFlag=='Y'}">
																<td style="background-color: #99FF99;" title="补签">
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:elseif test="${isOk=='Y'}">
																<td style="background-color:#00CC00;"  title="正常">
																	<div class="day">${day}</div>
																	<p>${startTime} ~ ${endTime}&nbsp;</p>
																	<div class="remark">${remark}&nbsp;</div>
																</td>
															</s:elseif>
															<s:else>
																<td>
																	<s:if test="${day>0}">
																		<div class="day">${day}</div>
																		<p>&nbsp;</p>
																	<div class="remark">&nbsp;</div>
																	</s:if>
																</td>
															</s:else>
														<s:if test="${(st.index+1)%7==0}">
															</tr><tr>
														</s:if>
													</s:iterator>
													</tr>
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
