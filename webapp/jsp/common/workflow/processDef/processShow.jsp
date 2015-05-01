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

.TabTable .TabTableBOX01 {
	display: none;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}

.TabTable .TabTableBOX01.b {
	display: block;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<Script>
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	
	function submitforform(){
		document.content.save();
		$('form').submit();
	}
</script>
	<body class="" id="mainid">
		<form method="post" name="form" action="saveOrUpdateProcessDef.action">
			<input type="hidden" name="labWfProcessVo.uuId" value="${labWfProcessVo.uuId}" />
			<input type="hidden" name="labWfProcessVo.id" value="${labWfProcessVo.id}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
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
												<s:text name="prc.manage"/>
												<span><s:text name="initinfo"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="prf.info"/></span>
											</div>
											<table class="FormtableCon_line">
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="prc.name"/>：
														</label>
													</td>
													<td>
														${labWfProcessVo.name}
													</td>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="prc.no"/>：
														</label>
													</td>
													<td>
														${labWfProcessVo.code}
													</td>
												</tr>
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="processed"/>选择：
														</label>
													</td>
													<td>
														${labWfProcessVo.funName}
													</td>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="rel.child.flow"/>：
														</label>
													</td>
													<td>
														<s:if test="${labWfProcessVo.isSubWf=='Y'}">是</s:if>
														<s:else>否</s:else>
													</td>
												</tr>
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="5">
														<textarea disabled="true" rows="2" cols="60" name="labWfProcessVo.comment">${labWfProcessVo.comment}</textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable subWf" <s:if test="${labWfProcessVo.isSubWf=='N'}">style="display: none;"</s:if>>
											<div class="Formtabletitle">
												<span><s:text name="child.flow"/></span>
											</div>
											<table class="FormtableCon_line" id="subProcess">
												<tr>
													<td class="c">
														<s:text name="processed"/>
													</td>
													<td class="c">
														<s:text name="dist.sex"/>
													</td>
													<td class="c">
														<s:text name="lab.code.ops"/>
													</td>
												</tr>
												<s:iterator id="" status="st" value="labWfProcessVo.subProcessList">
													<tr>
														<td>
															${name}
														</td>
														<td>
															<s:if test="${isStop=='N'}">正常流转</s:if>
															<s:else>骤停等待</s:else>
														</td>
														<td class="c">
															<a href="#" onclick="nextUri('/workflow/process/getLabWfProcess.action?labWfProcessVo.id=${id}');"><s:text name="look.check"/></a>
														</td>
													</tr>
												</s:iterator>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="node.info"/></span>
											</div>
											<table class="FormtableCon_line" id="bzhxx">
												<tr>
													<td class="c">
												<span><s:text name="node.name"/></span>
													</td>
													<td class="c">
														<s:text name="node.id"/>
													</td>
													<td class="c">
														<s:text name="nodetype"/>
													</td>
												</tr>
												<s:iterator id="" status="st" value="labWfProcessVo.funStepList">
													<tr>
														<td>
															${stepName}
														</td>
														<td>
															${stepCode}
														</td>
														<td>
															<s:if test="${stepType=='Node'}">节点</s:if>
															<s:else>子流程</s:else>
														</td>
													</tr>
												</s:iterator>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="path.var"/></span>
											</div>
											<table class="FormtableCon_line">
												<tr>
													<td class="c" width="40%">
														<s:text name="变量名"/>
													</td>
													<td class="c" width="20%">
														<s:text name="var.value"/>
													</td>
													<td class="c" width="40%">
														<s:text name="remark"/>
													</td>
												</tr>
												<s:iterator id="" status="st" value="labWfProcessVo.varList">
													<tr>
														<td>
															${name}
														</td>
														<td>
															${value}
														</td>
														<td>
															${comment}
														</td>
													</tr>
												</s:iterator>
											</table>
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
