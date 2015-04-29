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
		function check(obj){
			obj=$(obj);
			if(obj.val().indexOf('|')>-1){
				var values=obj.val().split('|');
				$("#unit").val(values[1]);
				$("#labAmbientId").val(values[0]);
			}else{
				$("#unit").val("");
			}
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
																	<l:a uri="samtest/labSamTest/updateSamTest4Ambient.action" value="lab.code.save" img="/img/icon/filesave.gif"/>
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
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest.action?tabNum=5')"><span><s:text name="check.item.info"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest4Appara.action?tabNum=5');"><span><s:text name="app.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reagent.action?tabNum=5');"><span><s:text name="rea.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reference.action?tabNum=5');"><span><s:text name="std.use"/></span> </a>
													</li>
													<li  class="currenttab">
														<a href="javascript:;" ><span><s:text name="env.info"></s:text></span></a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Data.action?tabNum=5');"><span><s:text name="data.in"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="check.item.info"></s:text></span>
														<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
															[&nbsp;
															<font color="blue"><s:text name="open.close"/></font>&nbsp;]
														</label>
													</div>
													<table class="FormtableCon">
														<tr>
															<td class="c">
																<label>
																	<s:text name="param.name"/>
																</label>
															</td>
															<td>
																<s:select list="#request.listLabAmbientVo" cssStyle="width:153px" onchange="check(this)" headerValue="------请选择------" headerKey="0" theme="simple" value="'${labAmbientInfoVo.labAmbientId}'+'|'+'${labAmbientInfoVo.labAmbientUnit}'" listValue="name" listKey="id+'|'+unit"></s:select>
															</td>

															<td class="c">
																<label>
																	<s:text name="param.unit"/>：
																</label>
															</td>
															<td>
																<input type="text" readonly="readonly" value="${labAmbientInfoVo.labAmbientUnit}"  id="unit" />
																<input type="hidden" readonly="readonly" valType="required" msg="请选择参数" value="${labAmbientInfoVo.labAmbientId}" name="labAmbientInfoVo.labAmbientId" id="labAmbientId" />
															</td>
														</tr>
														<tr>
															<td class="c">
																<label>
																	<s:text name="param.value"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labAmbientInfoVo.value" value="${labAmbientInfoVo.value}" id="value" valType="required,numBetween" msg="请正确输入参数值" min="0" />
															<td class="c">
																<label>
																	<s:text name="address"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labAmbientInfoVo.address" value="${labAmbientInfoVo.address}" id="address" />
															</td>
														</tr>
														<tr>
															<td class="c">
																<label>
																	<s:text name="room"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labAmbientInfoVo.room" value="${labAmbientInfoVo.room}" id="room" />
															</td>

															<td class="c">
																<label>
																	<s:text name="env.time"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labAmbientInfoVo.time" value="${labAmbientInfoVo.time}" id="time" class="Wdate" size="20" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true});" />
															</td>
														</tr>
													</table>
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
