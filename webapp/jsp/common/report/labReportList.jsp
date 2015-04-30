<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.labReportForm;
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
					alert("请选择要删除的记录！");
					return false;
				}else{
					return true;
				}
			}
			
			function deleteBatch(){
				if(check('labReportVo.ids')){
					if(confirm('确定批量删除吗?'))
					{
						submitvalue('<%=basePath%>report/labReport/deleteBatchLabReport.action');				
					}	
				}		
			}
			function deleteOne(id){
				if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					submitvalue('<%=basePath%>report/labReport/deleteLabReport.action?labReportVo.id='+id);
				}		
				return false;	
			}
			function makeReport(id){
				var height = window.screen.height-250;
				var url  = '${basePath}report/labReport/preUpdateReportModel.action?labReportVo.id='+id;
				$.dialog({
					id:'id',
					content:'url:'+url,
					title:'报告设计',
					opacity:0.4,
					width:1050,
					height:height,
					lock:true,
					max: false,                  
	        		min: false
				 });
			}
			function submit4Copy(){
				var idLength=$('input[name="labReportVo.ids"]:checked').length;
		    	if(idLength!=1){
		    		alert("请选择一条数据进行复制.");
		    		return false;
		    	}else{
		    		var id=$('input[name="labReportVo.ids"]:checked').val();
		    		var df = document.labReportForm;
			 		df.action='<%=basePath%>report/labReport/addLabReport4Copy.action?labReportVo.id='+id;
			  		df.submit();
		    	}
		  	}
			
		</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labReportForm">
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
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
											<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						<td><label><s:text name="config.type"/>：</label></td>
					                      						<td>
					                      							<s:select list="#{'':getText('alldata'),'1':getText('html.tmp'),'2':getText('excel.tmp')}" name="labReportVo.type" theme="simple"></s:select>
					                      						</td>
															   	<td><label><s:text name="config.name"/>：</label></td>
					                      						<td>
					                      							<input type="text"  name="labReportVo.title" value="${labReportVo.title}" id="title"   />
					                      						</td>
					                      						<td><label><s:text name="tmp.biz"/>：</label></td>
					                      						<td>
					                      							<s:select list="#request.funcList" listKey="id" listValue="name" name="labReportVo.busId" headerKey="" headerValue="-全部-" theme="simple" ></s:select>
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
																	<l:a uri="report/labReport/preAddLabReport.action" value="admin.add" />
																</td>
																<td>
																	<l:a uri="report/labReport/addLabReport4Copy.action" onclick="submit4Copy();" value="copy" />
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
													<th width="40">
														<input type="checkbox" id="allCheckBox" key="labReportVo.ids" />
													</th>
													<th width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th width="100">
														<s:text name="tmp.biz"/><s:text name="config.name"/>
													</th>
													<th property="title" width="200">
														<s:text name="tmp.name"/>
													</th>
													<th property="busCode" width="100">
														<s:text name="biz.code"/>
													</th>
													<th>
														<s:text name="remark"/>
													</th>
													<th property="type" width="100">
														<s:text name="tmp.type"/>
													</th>
													<th width="150">
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
																	<input type="checkbox" name="labReportVo.ids" value="${id}" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>
																	${busName}
																</td>
																<td>
																	${title}
																</td>
																<td>
																	${busCode}
																</td>
																<td>
																	${remark}
																</td>
																<td>
																	<s:if test="${type=='1'}">
																		<s:text name="html.tmp"/>
																	</s:if>
																	<s:else>
																		<s:text name="excel.tmp"/>
																	</s:else>
																</td>
																<td align="center">
																	<a href="<%=basePath %>report/labReportTag/listLabReportTag.action?labReportTagVo.labReportId=${id}"><s:text name="tag"/></a>
																	<l:a href="#" uri="report/labReport/preUpdateReportModel.action" onclick="makeReport('${id}');return false;" value="module.design" />
																	<l:a href="#" uri="report/labReport/preUpdateLabReport.action?labReportVo.id=${id}" value="lab.code.modify" />
																	<l:a href="#" uri="report/labReport/deleteLabReport.action" onclick="deleteOne('${id}');return false;" value="lab.code.del" />
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
								<td align="center">
									<jsp:include page="/jsp/include/page.jsp?formName=labReportForm" flush="true" />
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
