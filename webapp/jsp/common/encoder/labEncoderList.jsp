<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.labEncoderForm;
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
				if(check('labEncoderVo.ids')){
					if(confirm('<s:property value="getText('confirbatchdel')"/>'))
					{
						submitvalue('<%=basePath%>encoder/labEncoder/deleteBatchLabEncoder.action');				
					}	
				}		
			}
			function deleteOne(id){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					submitvalue('<%=basePath%>encoder/labEncoder/deleteLabEncoder.action?labEncoderVo.id='+id);
				}		
				return false;	
			}
		function printBarCode(){
	
		if(check('labEncoderVo.ids')){
	
			submitvalue('<%=basePath%>/encoder/labEncoder/printBarCode.action');
		}		
		};
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
		<s:form theme="simple" action="" method="post" name="labEncoderForm">

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
												${funName }：
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
																<s:select list="#request.funcList" listKey="id" listValue="name" name="labEncoderVo.busId" id="busId" headerKey="" headerValue="全部" ></s:select>
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:encoder/labEncoder/preAddLabEncoder.action" btImgSrc="img/xinjian.gif" btFunctionType="add" btFunctionUrl="encoder/labEncoder/preAddLabEncoder.action" btFunctionValue="admin.add" btIsDeafultImg="false" btJsFunctions="" />
																</td>
																<td>
																	<bt:jbutton btAttribute="id:BtnPreview,class:zPushBtn,href:javascript" btImgSrc="img/shanchu.gif" btFunctionType="delete" btFunctionUrl="encoder/labEncoder/deleteBatchLabEncoder.action" btFunctionValue="lab.code.deleteall" btIsDeafultImg="false" btJsFunctions="onclick:deleteBatch();return false;" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox" key="labEncoderVo.ids" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="tmp.biz"/><s:text name="config.name"/>
													</th>
													<th>
														<s:text name="bar.type"/>
													</th>
													<th>
														<s:text name="bar.info"/>
													</th>
													<th>
														<s:text name="2d.info"/>
													</th>
													<th>
														<s:text name="remark"/>
													</th>
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
																	<input type="checkbox" name="labEncoderVo.ids" value="${id}" />
																</td>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>
																	${busName}
																</td>
																<td name="busType">
																	${busType}
																</td>
																<td title="${codeInfo4One }">
																	<s:if test="%{null!= codeInfo4One&&codeInfo4One.length()>20}">
																		<s:property value="%{codeInfo4One.substring(0, 20)}" />…
           														</s:if>
																	<s:else>
																		<s:property value="%{codeInfo4One}" default="-" />
																	</s:else>
																</td>
																<td title="${codeInfo4Two }">
																	<s:if test="%{null!= codeInfo4Two&&codeInfo4Two.length()>20}">
																		<s:property value="%{codeInfo4Two.substring(0, 20)}" />…
           														   </s:if>
																	<s:else>
																		<s:property value="%{codeInfo4Two}" default="-" />
																	</s:else>
																</td>
																<td>
																	${remark}
																</td>
																<td align="center">
																	<hr:jhref hrAttribute="href:encoder/labEncoder/preUpdateLabEncoder.action?labEncoderVo.id=${id}" hrFunctionType="update" hrFunctionUrl="encoder/labEncoder/preUpdateLabEncoder.action" hrFunctionValue="lab.code.modify" hrJsFunctions="" hrIsInterval="true" />
																	<hr:jhref hrAttribute="href:javascript" hrFunctionType="delete" hrFunctionUrl="encoder/labEncoder/deleteLabEncoder.action" hrFunctionValue="lab.code.del" hrJsFunctions="onclick:deleteOne('${id}');return false;" hrIsInterval="false" />
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="11" align="center" valign="middle">
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=labEncoderForm" flush="true" /></td>
							</tr>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
