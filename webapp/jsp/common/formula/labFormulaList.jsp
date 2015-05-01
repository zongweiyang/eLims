<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.labFormulaForm;
		 		df.action=actionstr;
		  		df.submit();
		  	}
		  
			function check(name){
				var el = document.getElementsByTagName('input');     
				var len = el.length; 
				var m = 0;    
				for(var i=0; i<len; i++)     
				{         
					if((el[i].type=="checkbox") && (el[i].name==name))         
					{             
						if(el[i].checked == true){
					    	m = m + 1;
					 	}      
					}     
				}  
				if(m<1){
					alert('<s:property value="getText('lab.selectdelete')"/>');
					return false;
				}else{
					return true;
				}
			}
			
			function deleteBatch(){
				if(check('labFormulaVo.ids')){
					if(confirm('<s:property value="getText('confirbatchdel')"/>'))
					{
						submitvalue('<%=basePath%>formula/labFormula/deleteBatchLabFormula.action');				
					}	
				}		
			}
			function deleteOne(id){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					submitvalue('<%=basePath%>formula/labFormula/deleteLabFormula.action?labFormulaVo.id='+id);
				}		
				return false;	
			}
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
		<s:form theme="simple" action="" method="post" name="labFormulaForm">
		
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
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="formula.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labFormulaVo.name" value="${labFormulaVo.name}" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('listLabFormula.action');return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
	                                        <table>
	                                        	<tr>
	                                        		<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
	                                        			<table cellspacing="0" cellpadding="0" border="0">
	                                        				<tr>
				                     					    	<td>
		                                                         	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:formula/labFormula/preAddLabFormula.action" btImgSrc="img/xinjian.gif" btFunctionType="add" btFunctionUrl="formula/labFormula/preAddLabFormula.action" btFunctionValue="admin.add" btIsDeafultImg="false" btJsFunctions=""/>
		                                                         </td>
		                                                         <td>
		                                                         	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/shanchu.gif" btFunctionType="delete" btFunctionUrl="formula/labFormula/deleteBatchLabFormula.action" btFunctionValue="lab.code.deleteall" btIsDeafultImg="false" btJsFunctions="onclick:deleteBatch();return false;"/>
		                                                         </td>
	                                             			</tr>
	                                             		</table>
	                                             	</td>
	                                           </table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>  
													<th>
														<input type="checkbox" id="allCheckBox" key="labFormulaVo.ids"/>
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif"/>
													</th>
													<th property="name"><s:text name="formula.name"/></th>
													<th property="defineUser"><s:text name="formula.def"/></th>
													<th property="editor"><s:text name="formula"/></th>
													<th>
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
																<td class="td_cb">
																		<input type="checkbox" name="labFormulaVo.ids"
																		value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td><a href="<%=basePath%>formula/labFormula/showLabFormula.action?labFormulaVo.id=${id}">${name}</a></td>
																<td>${defineUser}</td>
																<td>${editor}</td>
																<td align="center">
																	<hr:jhref hrAttribute="href:formula/labFormula/preUpdateLabFormula.action?labFormulaVo.id=${id}"  hrFunctionType="update" hrFunctionUrl="formula/labFormula/preUpdateLabFormula.action" hrFunctionValue="lab.code.modify"  hrJsFunctions="" hrIsInterval="true"/>
																	<hr:jhref hrAttribute="href:javascript"  hrFunctionType="delete" hrFunctionUrl="formula/labFormula/deleteLabFormula.action" hrFunctionValue="lab.code.del"  hrJsFunctions="onclick:deleteOne('${id}');return false;" hrIsInterval="false"/>
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="6" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</table>
										</div>
								</td>
							</tr>
							<tr>
					      <tr>
						<td align="center"><jsp:include
								page="/jsp/include/page.jsp?formName=labFormulaForm"
								flush="true" /></td>
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
		</s:form>
	 <%@ include file="../../../jsp/include/foot.jsp"%> 
	</body>
</html>
