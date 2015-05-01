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
			function showLabSciFunds(id)
		  	 	{
			   		var url='<%=basePath%>/science/labScience/showLabSciFunds4Select.action?labSciFundsVo.id='+id;
				   $.dialog({
							id:'processId',
							content:'url:'+url,
							title:'<s:property value="getText('fundinfo')"/>',
							opacity:0.4,
							width:900,
							height:400,
							max:false,
							min:false,
							lock:true
				    });
		   	}
	</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labScienceFrom">
		<input type="hidden" name="labSciScienceVo.id"
			value="${labSciScienceVo.id}" />
		<input type="hidden" name="labSciScienceVo.code"
			value="${labSciScienceVo.code}" />
		<input type="hidden" name="labSciScienceVo.processInsId"
			value="${labSciScienceVo.processInsId}" />
			<input type="hidden" name="messageInfo"
			value="${messageInfo }" />
			<input type="hidden" name="labSciScienceVo.isKnot"
					value="${labSciScienceVo.isKnot}" />
			<input type="hidden" name="labSciScienceVo.isSeized"
			value="${labSciScienceVo.isSeized}" />
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
												${funName}：
												<span><s:text name="top.index"/></span>
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
																<td>
																	<l:a
																		uri="science/labScience/updateLabScience.action"
																		value="post.commit" />
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
													<li id="li01" class="">
														<a href="javascript:;" onclick="submitvalue('/science/labScience/preUpdateLabScience.action?labSciScienceVo.auditResult=0');""><span><s:text name="base.info"/></span> </a>
													</li>
													<li id="li02" class="currenttab">
														<a href="javascript:;" ><span><s:text name="charge.details"/></span>
														</a>
													</li>
													<li id="li03" class="">
														<a href="javascript:;"
															onclick="submitvalue('/science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab3');"><span><s:text name="prj.result"/></span>
														</a>
													</li>
													<li id="li03" class="">
														<a href="javascript:;"
															onclick="goAction('science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab4');"><span><s:text name="procedure.detail"/></span>
														</a>
													</li>
												</ul>
											</div>
										<div class="TabTableBOX01 b" id="Tab01">
											<div class="FUNCIONBARNEW">
													<table>
														<tr>
															<td class="blockTd"
																style="padding: 6px 10px; vertical-align: center;">
																<table cellspacing="0" cellpadding="0" border="0">
																	<tr>
																		<td>
																			<label>
																				<s:text name="funtypele"/>：
																			</label>
																		</td>
																		<td>
																			<s:select list="#request.codeList" listKey="code"
																				listValue="name" headerKey="" headerValue="-全部-"
																				name="labSciFundsVo.type" id="type" theme="simple"
																				>
																			</s:select>
																		</td>
																		<td>
																			<l:a uri="${SessionContainer.lastUrl}"
																				onclick="goAction('listLabSciFunds4Select.action');" value="fun.query" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
												<table class="myworkingboxttable" cellspacing="1"
													cellpadding="0">
													<thead>
														<tr>
															<th class="w50">
																<img src="<%=basePath%>img/icon_drag.gif" />
															</th>
															<th width="18%" class="c"  property="name">
																<s:text name="config.name"/>
															</th>
															<th width="18%" class="c" property="money">
																金额
															</th>
															<th width="18%" class="c" property="type">
																<s:text name="funtypele"/>
															</th>
															<th width="18%" class="c" property="remark">
																<s:text name="remark"/>
															</th>
															<th width="18%" class="c">
																<s:text name="lab.code.ops"/>
															</th>
														</tr>
													</thead>
													<tbody>
														<s:if test="pageResult!=null">
															<s:if test="pageResult.resultList!=null">
																<s:set name="alllist" value="pageResult.resultList" />
																<s:iterator value="#alllist" status="st">
																	<tr>
																		<td class="c">
																			${pageSizex* currenPagex+st.index+1}
																		</td>
																		<td class="c">${name}</td>
					                                                    <td class="c">${money}&nbsp;&nbsp;万元</td>
					                                                    <td class="c">
					                                                    	<s:if test="${type == '0' }">
					                                                    		<s:text name="infundeed"/>    
					                                                    	</s:if>
					                                                    	<s:if test="${type == '1' }">
					                                                    		<s:text name="out.fee"/>
					                                                    	</s:if>
					                                                    	<s:if test="${type == '2' }">
					                                                    		<s:text name="budgetfunsdfs"/>
					                                                    	</s:if>
					                                                    </td>
					                                                    <td class="c">${remark}</td>
																		<td class="c">
																			<l:a href="#" uri="science/labScience/showLabSciFunds4Select.action" onclick="showLabSciFunds('${id }');" value="details.info"/>	
																		</td>
																	</tr>
																</s:iterator>
															</s:if>
														</s:if>
														<s:else>
															<tr>
																<td colspan="24" align="center" valign="middle">
																	<font color="red"><s:text name="lab.msg.none"/></font>
																</td>
															</tr>
														</s:else>
													</tbody>
												</table>
												<table style="padding-top: 230px;" width="100%" >
													<tr>
														<td align="center" width="300px;"><jsp:include
																page="/jsp/include/page.jsp?formName=labScienceFrom"
																flush="true" /></td>
													</tr>
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
		<div class="clear"></div>
	</body>
</html>
