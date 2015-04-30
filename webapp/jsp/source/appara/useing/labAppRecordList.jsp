<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<script type="text/javascript">
 	function submitvalue(actionstr){
		var df = document.forms[0];
	 	df.action=actionstr;
	  	df.submit();
	}
	function deleteEntity(url){
		if(confirm('确认删除选中信息吗?')){
			goToNextAction(url);
		}
		return ;
	}
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	
	function deleteBath(url){
		var strTemp = '';
		$('input[name="labApparaVo.ids"]').each(function (){
			if($(this).attr('checked') == true || $(this).attr('checked') == 'checked'){
				strTemp += $(this).val()+",";
			}
		});
		if(strTemp.length <= 0){
			alert("请选择要删除的仪器");
			return ;
		}else{
			if(confirm('确认删除选中信息吗?')){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
		}
	}
	function selectChange(obj){
		if($(obj).attr('checked') == true || $(obj).attr('checked') == 'checked'){
			$('input[name="labApparaVo.ids"]').each(function (){
				$(this).attr('checked',true);
			});
		}else{
			$('input[name="labApparaVo.ids"]').each(function (){
				$(this).attr('checked',false);
			});
		}
	}
</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form action="" method="post" name="customeractivitiesForm"
			theme="simple">
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
													${funName }：
												<span><s:text name="appinfo.list"/></span>
											</h2>
										</div>

										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td style="vertical-align: center;">
																	<label>
																		<s:text name="app.name"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaVo.name"
																		value="${labApparaVo.name}" type="text" />
																</td>
																<td style="vertical-align: center;">
																	<label>
																		<s:text name="appcurr.status"/>：
																	</label>
																</td>
																<td>
																	<s:select name="labApparaVo.status" headerKey=""
																		headerValue="全部"
																		list="#{'0':getText('common'),'1':getText('report.repair'),'2':getText('stoped'),'7':getText('dropped')}"
																		value="${labApparaVo.status}" />
																</td>
																<td>
																	&nbsp;
																</td>
																<td class="">
																	<label>
																		<s:text name="show"/>
																	</label>
																	<s:select name="pageSize" value="%{pageSize}"
																		list="#{'10':'10','15':'15','20':'20','30':'30'}"
																		onchange="submitvalue('<%=basePath %>apparatus/maintenance/getApparatusInfoPageList.action');" />
																	<label>
																		<s:text name="tiao.rec"/>
																	</label>
																</td>
																<td>
																	<bt:jbutton
																		btAttribute="id:BtnPreview,class:zPushBtn,href:javascript"
																		btImgSrc="img/chakan.gif" btFunctionType="show"
																		btFunctionUrl="apparatus/maintenance/getApparatusInfoPageList.action"
																		btFunctionValue="查询" btIsDeafultImg="false"
																		btJsFunctions="onclick:submitvalue('getApparatusInfoPageList.action');return false;" />
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
													<th class=" c">
														<s:text name="lab.sequence"/>
													</th>
													<th>
														<input type="checkbox" onclick="selectChange(this);" /> 
													</th>
													<th class=" c">
														<s:text name="app.name"/>
													</th>
													<th class=" c">
														<s:text name="app.no"/>
													</th>
													<th class=" c">
														<s:text name="outer.id"/>
													</th>
													<th class=" c">
														<s:text name="regular.sys"/>
													</th>
													<th class=" c">
														<s:text name="sam.state"/>
													</th>
													<th class=" c">
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<s:if test="alarm==1">
																<tr style="background-color: #ffdee0">
															</s:if>

															<tr>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	<input type="checkbox" id="" name="ids" value="${id}" />
																</td>
																<td class="l">
																	<s:property value="name" />
																</td>
																<td class="l">
																	<s:property value="no" />
																</td>
																<td class="l">
																	<s:property value="code" />
																</td>
																<td class="l">
																	<s:property value="spec" />
																</td>

																<td class="c">
																	<s:if test="status==0"><s:text name="common"/></s:if>
																	<s:if test="status==1"><s:text name="report.repair"/></s:if>
																	<s:if test="status==2"><s:text name="stoped"/></s:if>
																	<s:if test="status==7"><s:text name="dropped"/></s:if>
																</td>
																<td class="c">
																		<a href="#"
																			onclick="submitvalue('<%=basePath%>appara/useing/getLabAppUsePR.action?labApparaUseVo.infoId=${id}');return false;"
																			class="edit"><s:text name="use.record"/></a>&nbsp;&nbsp;
																		&nbsp;&nbsp;
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?actionparam=getLabApparaPR.action&formName=customeractivitiesForm"
										flush="true" /></td>
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
		</s:form>
	</body>
</html>
