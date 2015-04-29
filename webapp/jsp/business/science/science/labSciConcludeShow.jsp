<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>

		<script language=javascript> 
			  	function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
				}
	</script>
	</head>
	<body class="" id="mainid">
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="over.item"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
							<input type="hidden" name="labSciScienceVo.processInsId"
								value="${labSciScienceVo.processInsId}" />
							<input type="hidden" name="messageInfo" value="6" />
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
							<div class="TabTable">
								<div class="TabTableNAV">
									<ul>
										<li id="li01" class="currenttab">
											<a href="javascript:;"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="javascript:;"
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab2');"><span><s:text name="charge.details"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab3');"><span><s:text name="prj.result"/></span>
											</a>
										</li>
									</ul>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="prj.base.info"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon" id="content">
												<tr>
													<td rowspan="4">
														<s:text name="prj.base.info"/>
													</td>
													<td>
														<label>
															<s:text name="item.name"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.name"
															maxlength="16" value="${labSciScienceVo.name}" id="name"
															disabled="true" />
													</td>
													<td>
														<label>
															<s:text name="prj.manager"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.masterName"
															maxlength="16" value="${labSciScienceVo.masterName}"
															id="masterName" onclick="selectPerson('0');return false;"
															disabled="true" />
														<input type="hidden" name="labSciScienceVo.masterId"
															maxlength="16" value="${labSciScienceVo.masterId}"
															id="masterId" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															起止时间：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.startDate"
															style="width: 80px;" value="${labSciScienceVo.startDate}"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
															valType="required" strLength-msg="起止时间长度不能超过32位"
															disabled="true" />
														<s:text name="to"/>
														<input type="text" name="labSciScienceVo.endDate"
															style="width: 80px;" value="${labSciScienceVo.endDate}"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
															valType="required" strLength-msg="起止时间长度不能超过32位"
															disabled="true" />
													</td>
													<td>
														<label>
															实际结束时间：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.trueEndDate"
															style="width: 80px;" value="${labSciScienceVo.trueEndDate}"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"
															valType="required" strLength-msg="起止时间长度不能超过32位"
															disabled="true" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="approval.fee"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.ratifyFunds"
															maxlength="32" value="${labSciScienceVo.ratifyFunds}"
															id="ratifyFunds" max="32"
															disabled="true" />
													</td>
													<td>
														<label>
															<s:text name="come.fee"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSciScienceVo.inFunds"
															maxlength="32" value="${labSciScienceVo.inFunds}"
															id="inFunds" valType="strLength" max="32"
															disabled="true" />
													</td>
												</tr>

											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemcanyuinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table  id="person" class="FormtableCon" id="person">
														<tr align="center">
															  <td rowspan="${1+fn:length(labSciScienceVo.authorList)}" width="10%" id="rowNum1">参与人信息</td>
															</td>
															<td width="18%" class="l">
															<s:text name="nike.name"/>
															</td>
															<td width="18%" class="l">
																职责
															</td>
															<td width="18%" class="l">
																贡献率
															</td>
														</tr>
													 	<s:if test="labSciScienceVo.authorList!=null">
														<s:set name="authorList" value="labSciScienceVo.authorList" />
														<s:iterator value="#authorList" status="st">
                                               <tr>
                                                    <td>
                                                       <input type="text" name="labSciScienceVo.authorList[${st.index}].userName" maxlength="32"  valType="required,strLength" max="32" strLength-msg="参与人名称长度不能超过32位" msg="参与人名称不能为空" readonly="true" value="${userName}" id="userName[${st.index}]" onclick="selectPerson('1');return false;" disabled="true" />
                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].id" maxlength="16"  value="${id}" id="id[${st.index}]"/>
                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].no" maxlength="16"  value="1" id="no[${st.index}]"/>
                                                    </td>
                                                    <td><input type="text" name="labSciScienceVo.authorList[${st.index}].duty" maxlength="16"  readonly="true" value="${duty}" id="duty[${st.index}]" disabled="true" /></td>
                                                     <td><input type="text" name="labSciScienceVo.authorList[${st.index}].contributionRate" maxlength="16" value="${contributionRate}" id="contributionRate[${st.index}]" disabled="true" /></td>
                                               </tr>
                                               </s:iterator>
												</s:if>
													</table>
										</div>
									</div>
									
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="prj.result"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon">
												<tr align="center">
													<td rowspan="20" width="10%">
														<s:text name="resultinfo"/>
													</td>
													<td>
														创新之处：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.discovery"
															value="${labSciScienceVo.discovery}" valType="strLength"
															max="512" strLength-msg="创新之处不能超过512位" disabled="true" >${labSciScienceVo.discovery}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														达到的目标：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.quota"
															value="${labSciScienceVo.quota}"
															valType="strLength" max="512"
															strLength-msg="达到的目标不能超过512位" disabled="true" >${labSciScienceVo.quota}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														意义：
													</td>
													<td>
														<textarea rows="4" cols="60" name="labSciScienceVo.meaning"
															value="${labSciScienceVo.meaning}" valType="strLength"
															max="512" strLength-msg="意义不能超过512位" disabled="true" >${labSciScienceVo.meaning}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														应用前景：
													</td>
													<td>
														<textarea rows="4" cols="60"
															name="labSciScienceVo.prospect"
															value="${labSciScienceVo.prospect}" valType="strLength"
															max="512" strLength-msg="应用前景不能超过512位" disabled="true" >${labSciScienceVo.prospect}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														存在的问题：
													</td>
													<td>
														<textarea rows="4" cols="60" name="labSciScienceVo.problem"
															value="${labSciScienceVo.problem}" valType="strLength"
															max="512" strLength-msg="存在的问题不能超过512位" disabled="true">${labSciScienceVo.problem}</textarea>
													</td>
												</tr>
											</table>
										</div>
									</div>

									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemfeeinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div>
											<table class="FormtableCon" id="funds">
												<tr>
													<td
														rowspan="${2+fn:length(labSciScienceVo.labSciFundsList)}"
														width="10%" id="rownum2">
														<s:text name="charge.out.info"/>
													</td>
													</td>
													<td class="r">
														总支出：
													</td>
													<td>
														<input type="text" name="labSciScienceVo.outFunds"
															maxlength="16" value="${labSciScienceVo.outFunds}"
															id="outFunds" readonly="true" disabled="true" />
														万元
													</td>
													<td class="r">
														剩余经费：
													</td>
													<td>
														<input type="text" name="labSciScienceVo.paperFunds"
															maxlength="16" value="${labSciScienceVo.paperFunds}"
															id="paperFunds" readonly="true" disabled="true" />
														万元
													</td>
												</tr>
												<tr align="center">
													<td width="18%" class="c">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支出名目
													</td>
													<td width="18%" class="c">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额
													</td>
													<td width="18%" class="c">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经手人
													</td>
													<td width="18%" class="c">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
												</tr>

												<s:if test="labSciScienceVo.labSciFundsList!=null">
													<s:set name="labSciFundsList"
														value="labSciScienceVo.labSciFundsList" />
													<s:iterator value="#labSciFundsList" status="ind">
														<tr id="tr0">
															<td class="c">
																${name}
															</td>
															<td class="c">
																${money}<s:text name="thousand.yuan"/>
															</td>
															<td class="c">
																${user}
															</td>
															<td class="c">
																${remark}
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</table>
										</div>
									</div>
								</div>
						</form>
					</div>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
