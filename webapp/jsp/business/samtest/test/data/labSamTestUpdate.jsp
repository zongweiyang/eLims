<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script>
	function copyValue(obj){
		obj=$(obj);
		var rIndex=obj.closest('tr').index();
		var cIndex=obj.closest('td').index();
		var value=obj.val();
		$(".myworkingboxttable tr:gt("+rIndex+")").each(function(){
			$(this).find('td').eq(cIndex).find('input').val(value);
		});
	}
	function submitValue(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
</script>
	<body class="" id="mainid">
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
											<span><s:text name="admin.add"/></span>
										</h2>
									</div>
									<form action="" method="post" name="studentForm">
										<input type="hidden" value="${labSamTestBeatchVo.id}" name="labSamTestBeatchVo.id" />
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="samtest/labSamTest/updateLabSamTest.action" value="lab.code.save" img="/img/icon/filesave.gif"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="currenttab">
														<a href="javascript:;"><span><s:text name="check.item.info"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest4Appara.action?tabNum=1');"><span><s:text name="app.use"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reagent.action?tabNum=1');"><span><s:text name="rea.use"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reference.action?tabNum=1');"><span><s:text name="std.use"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Ambient.action?tabNum=1');"><span><s:text name="env.info"/></span>
														</a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Data.action?tabNum=1');"><span><s:text name="data.in"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span><s:text name="check.item.info"/></span>
														<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
															[&nbsp;
															<font color="blue"><s:text name="open.close"/></font>&nbsp;]
														</label>
													</div>
													<div>
														<table class="myworkingboxttable">
															<tr>
																<th width="20px">
																	<img src="<%=basePath%>img/icon_drag.gif" />
																</th>
																<th>
																	<s:text name="checking.item"/>
																</th>
																<th>
																	<s:text name="std.name"/>
																</th>
																<th>
																	<s:text name="quan.index"/>
																</th>
																<th style="width: 60px;">
																	<s:text name="tempture"/>
																</th>
																<th style="width: 60px;">
																	<s:text name="shidu"/>
																</th>
																<th>
																	<s:text name="chk.method"/>
																</th>
															</tr>
															<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
																<tr>
																	<td>
																		${st.index+1}
																	</td>
																	<td>
																		${itemName}
																		<input type="hidden" value="${itemId}" name="listLabSamTestVo[${st.index}].itemId" />
																	</td>
																	<td class="c">
																		${standardName}
																		<input type="hidden" value="${describeWri}" name="listLabSamTestVo[${st.index}].describeWri" />
																		<input type="hidden" value="${describeFormula}" name="listLabSamTestVo[${st.index}].describeFormula" />
																	</td>
																	<td>
																		${describeFormula}
																	</td>
																	<td class="c">
																		<input type="text" style="width: 80%;"  onchange="copyValue(this)" name="listLabSamTestVo[${st.index}].temperature" value="${temperature}" />
																	</td>
																	<td class="c">
																		<input type="text" style="width: 80%;" onchange="copyValue(this)" name="listLabSamTestVo[${st.index}].humidity" value="${humidity}" />
																	</td>
																	<td class="c">
																		${methodName}
																	</td>
																</tr>
															</s:iterator>
														</table>
													</div>
												</div>
											</div>
										</div>
									</form>
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
	</body>
</html>
