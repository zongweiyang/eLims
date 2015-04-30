<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		      	$('form').attr('action',actionstr);
				$('form').submit();
		  	}
		  	function doAction(page){
		  		var pageNum=0;
		  		if(page=='start'){
		  			pageNum=0;
		  		}else if(page=='end'){
		  			pageNum=parseInt($("#countPage").val()-1);
		  		}else{
			  		if((parseInt($("#pages").val())+parseInt(page))<0){
		  				pageNum=0;
			  		}else{
			  			pageNum=(parseInt($("#pages").val())+parseInt(page));
			  		}
			  		if((parseInt($("#pages").val())+parseInt(page))>(parseInt($("#countPage").val()-1))){
		  				pageNum=parseInt($("#countPage").val())-1;
			  		}
		  		}
		  		$("#pages").val(pageNum);
		  		submitvalue('ACTIONPATH');
		  	}
		  		function showValue(index){
		  			$("#diaLogIndex").val(index);
		  			var demo=$("#demo"+index).val();
					var url  = '${basePath}jsp/common/query/queryDialog4Select.jsp';
						$.dialog({
							id:'ids',
							content:'url:'+url,
							title:demo,
							opacity:0.4,
							width:800,
							height:600,
							lock:true,
							max:false,
							min:false
						 });
				}
				$(function(){
					$("#queryDiv").queryDiv();
				});
		</script>

		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			
			html {
				_overflow-x: hidden;
			}
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form theme="simple" action="" method="post" name="labQueryForm">
		<input type="hidden" value="${labQueryVo.id}" name="labQueryVo.id"/>
		<input type="hidden" value="" id="diaLogIndex"/>
		<input type="hidden" id="countPage" value="${labQueryVo.pageVo.countPages}" name="labQueryVo.pageVo.countPages"/>
		<input type="hidden" id="pages" value="${labQueryVo.pageVo.pages}" name="labQueryVo.pageVo.pages"/>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
		<tr><td class="TLimg"></td><td class="TMimg"></td><td class="TRimg"></td></tr>
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
												${funName}${labQueryVo.rowNum}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW" id="queryDiv" >
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<s:if test="labQueryVo.listQuery!=null">
																<s:set name="alllist" value="labQueryVo.listQuery" />
																	<tr>
																		<s:iterator status="st" value="#alllist" >
																			<s:if test="${st.index}%${labQueryVo.rowNum}==0&&${st.index}>0">
																				</tr>
																			</s:if>
																			<td><label>${demo}:</label></td>
																			<td>
																				<input type="hidden" value="${demo}" id="demo${st.index}" name="labQueryVo.listQuery[${st.index}].demo"  />
																				<input type="hidden" value="${name}" name="labQueryVo.listQuery[${st.index}].name"/>
																				<input type="hidden" value="${queryIndex}" name="labQueryVo.listQuery[${st.index}].queryIndex"/>
																				<s:if test="${showType}==0">
																						<input type="text" value="${value}" name="labQueryVo.listQuery[${st.index}].value"  />
																				</s:if>
																				<s:if test="${showType}==1">
																						<input type="text" name="labQueryVo.listQuery[${st.index}].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
																				</s:if>
																				<s:if test="${showType}==2">
																						<input type="text" name="labQueryVo.listQuery[${st.index}].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true});" />
																				</s:if>
																				<s:if test="${showType}==3">
																					<s:property value="comboxValue" escape="false"/>
																					<input type="hidden" name="labQueryVo.listQuery[${st.index}].comboxValue"  value="<s:property value="comboxValue" escape="true"/> "/>
																				</s:if>
																				<s:if test="${showType}==4">
																					<input type="text" onclick="showValue('${st.index}');" id="value${st.index}" name="labQueryVo.listQuery[${st.index}].value"  value="${value}" />
																					<input type="hidden" id="json${st.index}" name="labQueryVo.listQuery[${st.index}].dialogJson" value="<s:property value="dialogJson" escape="true"/>" />
																					<input type="hidden" id="colum${st.index}" name="labQueryVo.listQuery[${st.index}].diaLogColum" value="<s:property value="diaLogColum" escape="true"/>" />
																				</s:if>
																				<input type="hidden" value="${showType}" name="labQueryVo.listQuery[${st.index}].showType" />
																			</td>
																			<s:if test="${isVague}==1">
																				<td>
																					<s:select list="#{'LIKE':getText('mohu.query'),'=':getText('jingque.query')}" theme="simple" name="labQueryVo.listQuery[${st.index}].isVagueValue" ></s:select>
																					<input type="hidden" value="${isVague}" name="labQueryVo.listQuery[${st.index}].isVague" />
																				</td>
																				<s:if test="${isSort}==1">
																					<td>
																						<s:select list="#{'1':getText('plseaseselect'),'ASC':getText('up.order')},'DESC':getText('down.order')}" theme="simple"  value="'${isSortValue}'" name="labQueryVo.listQuery[${st.index}].isSortValue" ></s:select>
																						<input type="hidden" value="${isSort}" name="labQueryVo.listQuery[${st.index}].isSort" />
																					</td>
																				</s:if>	
																				<s:else>
																						<td></td>
																				</s:else>
																			</s:if>
																			<s:elseif test="${isSort}==1">
																					<td>
																						<s:select list="#{'1':getText('plseaseselect'),'ASC':getText('shunxu')},'DESC':getText('daoxu')}" theme="simple"  value="'${isSortValue}'" name="labQueryVo.listQuery[${st.index}].isSortValue" ></s:select>
																						<input type="hidden" value="${isSort}" name="labQueryVo.listQuery[${st.index}].isSort" />
																					</td>
																				<s:else>
																						<td></td>
																				</s:else>
																		</s:elseif>
																		<s:else>
																			<td></td>
																			<td></td>
																		</s:else>
																		</s:iterator>																	
																	</tr>
															</s:if>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                    <div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="query.list"/>：
																	</label>
																</td>
																<td>
																		<td><a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="submitvalue('ACTIONPATH'); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif"/><b><s:text name="fun.query"/></b></a></td>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											
											<s:if test="labQueryVo.listTitle!=null">
												<s:set name="alllist" value="labQueryVo.listTitle" />
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<s:iterator  value="#alllist" >
														<th>${name}</th>
													</s:iterator>
												</tr>
											</s:if>
											<s:if test="labQueryVo.listValue!=null">
												<s:set name="alllist" value="labQueryVo.listValue"/>
													<s:iterator id="one"  value="#alllist" status="st" >
														<tr>
															<td>
																	${st.index+1}
															</td>
															<s:iterator value="#one.listValue" id="two">
																<td>${two.name}</td>
															</s:iterator>
														</tr>
													</s:iterator>
											</s:if>
											</table>
										</div>
								</td>
							</tr>
							<tr>
					      <tr>
							<td align="center">
								<table border="0" cellPadding="2" align="center" cellSpacing="0" width="75%" bgcolor="#FFFFFF">
								<tr>
									<td align="center" class="tou" colspan="5">
										<div class="pagecontrol">
											<s:text name="page.current"/>${labQueryVo.pageVo.pages+1}<s:text name="page.page"/>|&nbsp;<s:text name="page.total"/>${labQueryVo.pageVo.countPages}<s:text name="page.page"/>&nbsp;
											<s:if test="${labQueryVo.pageVo.pages}==0">
												<s:text name="top.index"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('start');"><s:text name="top.index"/></a>
											</s:else>
											&nbsp;
											<s:if test="${labQueryVo.pageVo.pages}==0">
												<s:text name="page.before"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('-1');"><s:text name="page.before"/></a> 
											</s:else>
											&nbsp; 
											<s:if test="'${labQueryVo.pageVo.pages}'=='${labQueryVo.pageVo.countPages-1}'||'${labQueryVo.pageVo.countPages}'=='0'">
												<s:text name="page.next"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('1');"><s:text name="page.next"/></a>
											</s:else>
											 &nbsp;
											 <s:if test="'${labQueryVo.pageVo.pages}'=='${labQueryVo.pageVo.countPages-1}'||'${labQueryVo.pageVo.countPages}'=='0'">
											 	<s:text name="page.tail"/>
											 </s:if>
											 <s:else>
											 	<a href="javascript:;" onclick="doAction('end');"><s:text name="page.tail"/></a>
											 </s:else>
											 	
										</div>
									</td>
								</tr>
							</td>
					       </tr>
				          </tr>
						</table>
				    </td>
			 <td class="MRimg"></td>
				</tr>
				<tr>
				<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
        </tr>
				
			</table>
		</form>
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
</html>
