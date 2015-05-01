<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.labAmbientForm;
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
				if(check('labAmbientVo.ids')){
					if(confirm('确定批量删除吗?'))
					{
						submitvalue('<%=basePath%>ambient/labAmbient/deleteBatchLabAmbient.action');				
					}	
				}		
			}
			function deleteOne(id){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					submitvalue('<%=basePath%>ambient/labAmbient/deleteLabAmbient.action?labAmbientVo.id='+id);
				}		
				return false;	
			}
			function checkId(ids){
				$.ajax({
					url:'<%=basePath%>ambient/labAmbient/isDeleteLabAmbient.action',
					data:{'labAmbientVo.ids':ids},
					type:'post',
					dataType:'text',
					success:function (data){
						if(data=="1"){
							alert('<s:property value="getText('recordnotdele')"/>');
							return false;
						}
				   	  }
				  });
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
		<s:form theme="simple" action="" method="post" name="labAmbientForm">
		
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
																		<s:text name="param.name"/>：
																	</label>
																</td>
																<td>
																	<input id="name" name="labAmbientVo.name" value="${labAmbientVo.name}" />
																</td>
																<td>
																	<l:a uri="${pageResult.action}"  onclick="submitAction();" value="fun.query"/>
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
		                                                         	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:ambient/labAmbient/preAddLabAmbient.action" btImgSrc="img/xinjian.gif" btFunctionType="add" btFunctionUrl="ambient/labAmbient/preAddLabAmbient.action" btFunctionValue="admin.add" btIsDeafultImg="false" btJsFunctions=""/>
		                                                         </td>
		                                                         <td>
		                                                         	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/shanchu.gif" btFunctionType="delete" btFunctionUrl="ambient/labAmbient/deleteBatchLabAmbient.action" btFunctionValue="lab.code.deleteall" btIsDeafultImg="false" btJsFunctions="onclick:deleteBatch();return false;"/>
		                                                         </td>
	                                             			</tr>
	                                             		</table>
	                                             	</td>
	                                            </tr></table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>  
													<th>
														<input type="checkbox" id="allCheckBox" key="labAmbientVo.ids"/>
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif"/>
													</th>
													<th property="name"><s:text name="param.name"/></th>
													<th property="upValue"><s:text name="upper.value"/></th>
													<th property="downValue"><s:text name="lower.value"/></th>
													<th property="unit"><s:text name="theme.depart"/></th>
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
																		<input type="checkbox" name="labAmbientVo.ids"
																		value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td><a href="<%=basePath%>ambient/labAmbient/showLabAmbient.action?labAmbientVo.id=${id}">${name}</a></td>
																<td>${upValue}</td>
																<td>${downValue}</td>
																<td >${unit}</td>
																<td align="center">
																	<hr:jhref hrAttribute="href:ambient/labAmbient/preUpdateLabAmbient.action?labAmbientVo.id=${id}"  hrFunctionType="update" hrFunctionUrl="ambient/labAmbient/preUpdateLabAmbient.action" hrFunctionValue="lab.code.modify"  hrJsFunctions="" hrIsInterval="true"/>
																	<hr:jhref hrAttribute="href:javascript"  hrFunctionType="delete" hrFunctionUrl="ambient/labAmbient/deleteLabAmbient.action" hrFunctionValue="lab.code.del"  hrJsFunctions="onclick:deleteOne('${id}');return false;" hrIsInterval="false"/>
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
											</table>
										</div>
								</td>
							</tr>
							<tr>
					      <tr>
						<td align="center"><jsp:include
								page="/jsp/include/page.jsp?formName=labAmbientForm"
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
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
</html>
