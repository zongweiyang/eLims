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
			
			#roletext {
				width: 70px;
				overflow: hidden;
				white-space: nowrap;
				text-overflow: ellipsis; /* 支持 IE */
			}
			.editType{
				visibility:hidden;
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
								<span><s:text name="look.check"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
							<input type="hidden" name="labSciScienceVo.processInsId"
								value="${labSciScienceVo.processInsId}" />
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
							<!--<div class="TabTable">
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
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab3');"><span><s:text name="xueshueex"/></span>
											</a>
										</li>
										<li id="li04" class="">
											<a href="javascript:;"
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab4');"><span><s:text name="prj.result"/></span>
											</a>
										</li>
										<li id="li05" class="">
											<a href="javascript:;"
												onclick="submitValue('/sample/labSampRegister/addLabSampRegister4Tab1.action?labSampRegisterVo.status=tab5');"><span><s:text name="itemtest"/></span>
											</a>
										</li>
									</ul>
								</div> -->	
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
													<td class="r" width="150">
														<label>
															<s:text name="item.name"/>：
														</label>
													</td>
													<td  class="l" width="430">
														${labSciScienceVo.name}
														<input size="40" type="hidden" name="labSciScienceVo.name"
															maxlength="16" value="${labSciScienceVo.name}" id="name"
															/>
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="study.lec"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.topic}
														<input size="40" type="hidden" name="labSciScienceVo.topic"
															maxlength="16" value="${labSciScienceVo.topic}"
															id="topic" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="appleypeol"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.masterName}
														<input size="40" type="hidden" name="labSciScienceVo.masterName"
															maxlength="16" value="${labSciScienceVo.masterName}"
															id="masterName" onclick="selectPerson('0','null');return false;"
															readonly="true"/>
														<input type="hidden" name="labSciScienceVo.masterId"
															maxlength="16" value="${labSciScienceVo.masterId}"
															id="masterId" />
													</td>
													<td class="r" width="150">
														<label>
																	<s:text name="start.time"/>：
														</label>
													</td>
													<td>
														<input type="hidden" name="labSciScienceVo.startDate"
															value="${labSciScienceVo.startDate}" id="startDate"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});"
															/>
														${labSciScienceVo.startDate}  &nbsp;&nbsp;  至  &nbsp;&nbsp;  ${labSciScienceVo.endDate}  
														<input type="hidden" name="labSciScienceVo.endDate"
															value="${labSciScienceVo.endDate}" id="endDate"
															class="Wdate" size="15"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});" 
															/>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="itemsource"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.source}
														<input size="40" type="hidden" name="labSciScienceVo.source"
															maxlength="32" value="${labSciScienceVo.source}"
															id="source" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="yilailab"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.labOrgName}
														<input size="40" type="hidden" name="labSciScienceVo.labOrgName"
															maxlength="32" value="${labSciScienceVo.labOrgName}"
															id="labOrgName" />
															<input size="40" type="hidden" name="labSciScienceVo.labOrgId"
															maxlength="32" value="${labSciScienceVo.labOrgId}"
															id="labOrgId" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
													<s:text name="owndaches"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.subject}
														<input size="40" type="hidden" name="labSciScienceVo.subject"
															maxlength="32" value="${labSciScienceVo.subject}"
															id="subject" valType="strLength" max="32" msg="所属学科不能为空" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="xuekelingyu"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.domain}
														<input size="40" type="hidden" name="labSciScienceVo.domain"
															maxlength="32" value="${labSciScienceVo.domain}"
															id="domain" valType="strLength" max="32" msg="学科领域不能为空" />
													</td>
												</tr>

											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="applierinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="nike.name"/>：
														</label>
													</td>
													<td class="l" width="430" >
														${labSciScienceVo.masterName}
														<input size="40" type="hidden" maxlength="16"
															value="${labSciScienceVo.masterName}" id="masterName2"
															readonly="true" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="theme.sex"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.sex }
														<input size="40" type="hidden" name="labSciScienceVo.sex"
															maxlength="16" value="${labSciScienceVo.sex}"
															id="sex" readonly="true" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：
														</label>
													</td>
													<td>
														${labSciScienceVo.duty}
														<input size="40" type="hidden" name="labSciScienceVo.duty"
															maxlength="16" value="${labSciScienceVo.duty}"
															id="masterDuty" readonly="true" />
													</td>
													<td class="r" width="150">
														<label>
															职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
														</label>
													</td>
													<td>
														${labSciScienceVo.techTitle}
														<input size="40" type="hidden" name="labSciScienceVo.techTitle"
															maxlength="16" value="${labSciScienceVo.techTitle}"
															id="masterTechTitle" readonly="true" />
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="theme.phone"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.telephone}
														<input size="40" type="hidden" name="labSciScienceVo.telephone"
															maxlength="16" value="${labSciScienceVo.telephone}"
															id="masterTelephone" readonly="true" />
													</td>
													<td class="r" width="150">
														<label>
															<s:text name="email"/>：
														</label>
													</td>
													<td>
														${labSciScienceVo.email}
														<input size="40" type="hidden" name="labSciScienceVo.email"
															maxlength="16" value="${labSciScienceVo.email}"
															id="masterEmail" readonly="true" />
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemcanyuinfo"/></span>
											<!-- <label style="padding-left: 885px;" >
											[&nbsp;<a style="align: right; font-color: green;"
											href="javascript:void(0);" onclick="addRow1(this);return false;"><s:text name="added.a.row"/></a>&nbsp;]
											</label> -->
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon_sform" id="person">
												<thead>
													<th width="18%" class="c">
													<s:text name="nike.name"/>
													</th>
													<th width="18%" class="c">
														<s:text name="theme.job.title"/>
													</th>
													<th width="18%" class="c">
												<s:text name="work.duty"/>
													</th>
													<th width="18%" class="c">
														研究领域
													</th>
													<th width="18%" class="c">
														职责&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													</th>
												</thead>
											 	<s:if test="labSciScienceVo.authorList!=null">
													<s:set name="authorList" value="labSciScienceVo.authorList" />
													<s:iterator value="#authorList" status="st">
	                                               <tr>
	                                                    <td class="c">
	                                                    	${userName}
	                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].userName" maxlength="32" max="32" readonly="true" value="${userName}" id="userName${st.index}" onclick="selectPerson('1',${st.index });return false;"/>
	                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].userId"  maxlength="16" learn="${learn }" remark="${remark }" value="${userId}" id="userId${st.index}" />
	                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].no" maxlength="16"  value="1" id="no${st.index}" value="${no}"/>
	                                                       <input type="hidden" name="labSciScienceVo.authorList[${st.index}].id" id="id${st.index}" value="${id}"/>
	                                                    </td>
	                                                    <td class="c">${techTitle}<input type="hidden" name="labSciScienceVo.authorList[${st.index}].techTitle" maxlength="16"  readonly="true" value="${techTitle}" id="techTitle${st.index}"/></td>
	                                                    <td class="c">${duty}<input type="hidden" name="labSciScienceVo.authorList[${st.index}].duty" maxlength="16"  readonly="true" value="${duty}" id="duty${st.index}"/></td>
	                                                    <td class="c">${learn}<input type="hidden" name="labSciScienceVo.authorList[${st.index}].learn" maxlength="16" onblur="writeHui('learn',${st.index });"  value="${learn}" id="learn${st.index}"/></td>
	                                                    <td class="c">${remark}<input type="hidden" name="labSciScienceVo.authorList[${st.index}].remark" maxlength="16"  onblur="writeHui('remark',${st.index });" value="${remark}" id="remark${st.index}"/></td>
	                                               </tr>
	                                               </s:iterator>
												</s:if>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemprefeeinfo"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon_sform" id="funds">
												<thead>
													<th width="18%" class="c">
														<s:text name="paying.out.name"/>
													</th>
													<th width="18%" class="c">
														金额
													</th>
													<th width="18%" class="c">
														<s:text name="remark"/>
													</th>
												</thead>
												<s:if test="labSciScienceVo.labSciFundsList!=null">
												<s:set name="labSciFundsList" value="labSciScienceVo.labSciFundsList" />
												<s:iterator value="#labSciFundsList" status="ind">
												<tr id="tr0">
													 <td class="c">${name}<input type="hidden" name="labSciScienceVo.labSciFundsList[${ind.index}].name" maxlength="16"  value="${name}" id="name" /></td>
                                                    <td class="c">${money}<input type="hidden" name="labSciScienceVo.labSciFundsList[${ind.index}].money" maxlength="16"  value="${money}" id="money" onblur="getPrice();return false;" max="32" strLength-msg="支出金额长度不能超过32位" number-msg="支出金额必须为数字" msg="支出金额不能为空"/>&nbsp;&nbsp;万元</td>
                                                    <td class="c">${remark}<input type="hidden" name="labSciScienceVo.labSciFundsList[${ind.index}].remark" maxlength="16"  value="${remark}" id="remark"/></td>
												</tr>
												</s:iterator>
												</s:if>
												<tr>
													<td width="370" class="c">
														<font color="red">总计：</font>
													</td>
													<td  colspan="2">
														<label id="count" style="margin-left: 150px;color: red;">${labSciScienceVo.applyFunds}</label><font color="red">&nbsp;&nbsp;万元</font>
														<input type="hidden" name="labSciScienceVo.applyFunds" maxlength="16" value="${labSciScienceVo.applyFunds}" id="applyFunds" />
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="itemdetails"/></span>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div style="display: none;">
											<table class="FormtableCon">
												<tr>
													<td class="r" width="150">
														研究基础：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40"
															name="labSciScienceVo.foundation"
															valType="strLength" max="512"
															strLength-msg="研究基础不能超过512位">${labSciScienceVo.foundation}</textarea>
													</td>
													<td class="r" width="150">
														研究方案：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40" name="labSciScienceVo.plan"
															valType="strLength"
															max="512" strLength-msg="研究方案不能超过512位">${labSciScienceVo.plan}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														风险评估：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40" name="labSciScienceVo.risk"
															 valType="strLength"
															max="512" strLength-msg="风险评估不能超过512位">${labSciScienceVo.risk}</textarea>
													</td>
													<td class="r" width="150">
												<s:text name="kexingxingfen"/>：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40"
															name="labSciScienceVo.feasibility"
															valType="strLength" max="512"
															strLength-msg="可行性分析不能超过512位">${labSciScienceVo.feasibility}</textarea>
													</td>
												</tr>
												<tr align="center">
													<td class="r" width="150">
														<s:text name="itembacksource"/>：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40"
															name="labSciScienceVo.backdrop"
															valType="strLength"
															max="512" strLength-msg="<s:text name="itembacksource"/>不能超过512位">${labSciScienceVo.backdrop}</textarea>
													</td>
													<td class="r" width="150">
														研究目标及内容：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40" name="labSciScienceVo.goal"
															valType="strLength"
															max="512" strLength-msg="研究目标及内容不能超过512位">${labSciScienceVo.goal}</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														研究成果及应用：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40"
															name="labSciScienceVo.achievement"
															valType="strLength" max="512"
															strLength-msg="研究成果及应用不能超过512位">${labSciScienceVo.achievement}</textarea>
													</td>
													<td class="r" width="150">
														<s:text name="lab.remark"/>：
													</td>
													<td>
														<textarea readonly="readonly" rows="2" cols="40"
															name="labSciScienceVo.remark" valType="strLength"
															max="512" strLength-msg="长度不能超过512位">${labSciScienceVo.remark }</textarea>
													</td>
												</tr>
												<tr>
													<td class="r" width="150">
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="3">
															<s:if test="${fn:length(uplodeList)>0}">
															<s:iterator status="st2" value="#request.uplodeList" id="">
																<span> ${name} 
																		<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;
																	<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}"">
																	    <img src="<%=basePath %>/img/query.gif"/>
																	</a>
																	<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
																</s:if></span>		
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
														<div id="upfiles"></div>
													</td>
												</tr>
											</table>
										</div>
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
