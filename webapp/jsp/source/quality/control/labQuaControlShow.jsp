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
			
			#roletext {
				width: 70px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis; /* 支持 IE */
			}
			.myworkingboxttable th{
			background: #F5F5F5;
			color: #000000;
			border-top: #C0C0C0 1px solid;
			border-left: #C0C0C0 1px solid;
			border-right: #C0C0C0 1px solid;
			}
			.editType{
				visibility:hidden;
			}
			</style>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaCusVisitForm" id="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
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
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
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
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="quanjianduinfo"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<table class="FormtableCon">
												  <tr>
												<td class="r" width="150">
													<label>
														<s:text name="theme.depart"/>：
													</label>
												</td>
												<td>
													${labQuaControlVo.unitOrgName }
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="labroom"/>：
													</label>
												</td>
												<td>
													${labQuaControlVo.labOrgName }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：
													</label>
												</td>
												<td>
													${labQuaControlVo.place }
												</td>
											    <td class="r" width="150">
													<label>
												<s:text name="zhixingstg"/>：
													</label>
												</td>
												<td>
													${labQuaControlVo.standardName }
												</td>
											</tr>

											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="app.style"/>：
													</label>
												</td>
												<td >
												      ${labQuaControlVo.appCode }
												</td>
											    <td class="r" width="150">
												       <label>
														<s:text name="app.no"/>：
														</label>
												</td>
												<td>
														${labQuaControlVo.appNo }
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
												       <label>
														<s:text name="chouschare"/>：
														</label>
												</td>
												<td>
														${labQuaControlVo.anaPeople }
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="chksdate"/>：
													</label>
												</td>
												<td>
													${labQuaControlVo.testTime }
												</td>
											</tr>
											<tr>
											    <td class="r" width="150">
												       <label>
														<s:text name="jiandupeo"/>：
														</label>
												</td>
												<td>
														${labQuaControlVo.conPeople }
												</td>
												<td class="r" width="150">
													
													<label>
														<s:text name="jiandudate"/>：
													</label>
												</td>
												<td>
													${labQuaControlVo.conTime }
												</td>
											</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="jianducontent"/></span>
												<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
											</div>
											<div style="margin-top: 10px; margin-bottom: 10px;display: none;">
												<table class="myworkingboxttable" id="OneTable">
													<thead>
														<tr>
															<th>
																<s:text name="jianducontent"/>
															</th>
															<th>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="statedesc"/>
															</th>
															<th>
																<s:text name="notreasonresultl"/>
															</th>
														</tr>
													</thead>
													<s:if test="labQuaControlVo.labQuaControlDetailVoList!=null">
														<s:set name="alllist" value="labQuaControlVo.labQuaControlDetailVoList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c" width="150">
																	${comCodeName }
																</td>
																<td  class="c">
																	${statusDesc }
																</td>
																<td  class="c">
																	${proResult }
																</td>
															</tr>
														</s:iterator>
													</s:if>
													<s:else>
														<tr>
															<td colspan="3" align="center" valign="middle">
																<font color="red"><s:text name="lab.msg.none"/></font>
															</td>
														</tr>
													</s:else>
												</table>
											</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="comment.info"/></span>
											<label style="float: right;margin-right: 20px;cursor: pointer;" onclick="javascript:$(this).parent().next().toggle();">[<font color="red"><s:text name="open.close"/></font>]</label>
										</div>
										<table class="FormtableCon" id="twoTable" style="display: none;">
											<tr>
												<td class="r" width="150">
												       <label>
														<s:text name="conheping"/>：
														</label>
												</td>
												<td>
														<textarea readonly="readonly" name="labQuaControlVo.evaluation" cols="40" rows="3" id="evaluation">${labQuaControlVo.evaluation }</textarea>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
