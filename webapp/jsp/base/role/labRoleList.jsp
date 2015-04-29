<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>

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
			alert('<s:property value="getText('deleted.record')"/>');
			return false;
		}else{
			return true;
		}
	}

function submitvalue(actionstr){
	var df = document.labRoleForm;
	  df.action=actionstr;
	  df.submit();	
}
function deleteBatch(actionstr,name){
	  var ids1 = document.getElementsByTagName('input');   
	  var len = ids1.length;
	  var ids=""; 
	  for(var i=0; i<len; i++)     
	   {             
		  if((ids1[i].type=="checkbox")&&(ids1[i].name==name)){
		  	if(ids1[i].checked == true){
		  		ids+=ids1[i].value+",";
		  	}
		  }			
	   }
	   if(ids.length>0){
	   	ids=ids.substring(0,ids.length-1);
	   }
	   
	   $.ajax({
	   	  url:'<%=basePath%>role/labRole/isDeleteInIds.action',
	   	  data:{'ids':ids},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="1"){				
               alert();
             }
             else{
             	submitvalue(actionstr);
             }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('config.request.error')"/>');
	   	  }
	   });
}
function submitvalueforlist(actionstr,id){
	  $.ajax({
	   	  url:'<%=basePath%>role/labRole/isDeleteInIds.action',
	   	  data:{'ids':id},
	   	  type:'post',
		  dataType:'text',
	   	  success:function (data){
	   	  	if(data=="1"){				
               alert('<s:property value="getText('deleteall.denied')"/>');
             }
             else{
             	submitvalue(actionstr);
             }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('config.request.error')"/>');
	   	  }
	   });
}
		function deleteThis(){
		if(check('labRoleVo.ids')){
			if(confirm('<s:property value="getText('confirm.deleted')"/>'))
				{
					deleteBatch('<%=basePath%>role/labRole/deleteLabRole.action','labRoleVo.ids');
				}	
		}		
	}
			function deleteOne(id){
				if(confirm('<s:property value="getText('confirm.deleted')"/>'))
					{
						submitvalueforlist('<%=basePath %>role/labRole/deleteLabRole.action?labRoleVo.ids='+id,id);
					}		
				}			
		function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}		
</script>
<script language=javascript> 
	var nwin;      
	function openwindow(id)      
	{      
		var sTitle = '<s:property value="getText('data.authorize')"/>';
		var url = '<%=basePath %>role/labRole/listDataLabRole.action?labRoleVo.id='+id;
		if (nwin && !nwin.closed){      
	       nwin.close();      
	    }      
	  	sWidth  = 600;
	    sHeight = 300;
	    
		var l = ( screen.availWidth - sWidth ) / 2;
	    var t = ( screen.availHeight - sHeight ) / 2;      
	 	nwin = window.open(url,sTitle,'left=' + l +',top=' + t 
		+',width='+sWidth+',height='+sHeight+',scrollbars=yes,resizable=yes');      
	    nwin.focus();      
	}
	
	
	function roleFunTree(id,name){
		var url  = '${basePath}role/labRole/showLabRoleFun4Select.action?labRoleVo.id='+id;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('fun.auth')"/>'+name+'<s:property value="getText('funquan')"/>',
			opacity:0.4,
			width:500,
			height:300,
			lock:true,
			max:false,
			min:false
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
			#roletext{
				width:70px;
				overflow:hidden; 
				white-space:nowrap;
			    text-overflow:ellipsis;/* 支持 IE */
			}
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labRoleForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
              	<tr>
					<td class="MLimg"></td>
	        		<td id="bodyCell" class="oRight">
	        			
			          	<table class="workingBody" cellspacing="0" cellpadding="0" border="0" >
			              	<tr>
				              	<td>
				              		<div class="myworkingbox">
				                		<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="top.index"></s:text></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
									   	<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
															   <td><label><s:text name="role.name"></s:text>：</label></td>
					                      						<td>
					                      							<input type="text" name="labRoleVo.name" id="name" value="${labRoleVo.name }"  />
					                      						</td>
										                      	<td>
										                      		<l:a uri="${pageResult.action}" onclick="submitAction();" value="fun.query" />
										                      	</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                   <!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
	                                        <table>
	                                        	<tr>
	                                        		<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
	                                        			<table cellspacing="0" cellpadding="0" border="0">
	                                        				<tr>
				                     					    	<td>
				                     					    		<l:a uri="role/labRole/preAddLabRole.action" value="lab.code.add"/>
				                                                </td>
				                                                <td>
				                                                	<l:a uri="role/labRole/deleteLabRole.action" onclick="deleteThis();" value="lab.code.deleteall" />	
										             		  	</td>
	                                             			</tr>
	                                             		</table>
	                                             	</td>
	                                            </tr>
	                     					</table>
										</div>
									 	<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>  
													<th>
														<input type="checkbox" id="allCheckBox" key="labRoleVo.ids"/>
													</th>
													<th><img src="<%=basePath%>img/icon_drag.gif"/></th>
													<th property="name">
														<s:text name="role.name"></s:text>
													</th>
													<th>
														<s:text name="show"></s:text>
													</th>
													<th>
														<s:text name="own.role"></s:text>
													</th>
													<th>
														<s:text name="remark"></s:text>
													</th>	
													<th>
														<s:text name="lab.code.ops"></s:text>
													</th>										
											</thead>
											<tbody>
												<s:iterator value="pageResult.resultList" status="st">
													<s:if test="${isDefault=='Y'}">
														<tr>
															<td class="c">
																<input type="checkbox" name="labRoleVo.ids" id="ids" value="${id }"
																	disabled="disabled" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																<l:a href="#" uri="role/labRole/showLabRole.action?labRoleVo.id=${id}" value="${name }"/>
															</td>
															<td class="c">
																<s:if test="${show=='FRONT'}">
																	<s:text name="front"></s:text>
																</s:if>
																<s:if test="${show=='BACK'}">
																	<s:text name="backend"/>
																</s:if>
															</td>															
															<td class="1" title="${userNames}">
																<a id="roletext">
																	${userNames}
																</a>
															</td>
															<td class="l">
																${remark}
															</td>	
															<td class="c">
																<s:text name="lab.code.modify"></s:text>&nbsp;|&nbsp;
																<s:text name="lab.code.del"></s:text>&nbsp;|&nbsp;
																<s:text name="fuquan.role"></s:text>
															</td>							
														</tr>
													</s:if>
													<s:else>
														<tr>
															<td class="c">
																<input type="checkbox"
																	name="labRoleVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="1">
																<l:a href="#" uri="role/labRole/showLabRole.action?labRoleVo.id=${id}" value="${name }"/>
															</td>
															<td class="c">
																<s:if test="${show=='FRONT'}">
																	<s:text name="front"></s:text>
																</s:if>
																<s:if test="${show=='BACK'}">
																	<s:text name="backend"/>
																</s:if>
															</td>
															<td class="1" title="${userNames}">
																<a id="roletext">
																		${userNames }
																</a>
															</td>
															<td class="l">
																${remark}
															</td>
															<td class="c">
																<l:a href="#" uri="role/labRole/preUpdateLabRole.action?labRoleVo.id=${id}" value="lab.code.modify" />
																&nbsp;&nbsp;
																<l:a href="#" uri="role/labRole/deleteLabRole.action" onclick="deleteOne('${id}');" value="lab.code.del" />
																&nbsp;&nbsp;

																<l:a href="#" uri="role/labRole/showLabRoleFun4Select.action" onclick="roleFunTree('${id}','${name}');" value="fun.right" />

															</td>	
														</tr>
													</s:else>
												</s:iterator>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
									page="../../include/page.jsp?formName=labRoleForm"
									flush="true" />
								</td>
						    </tr>
						</table>
        			</td>
        			<td class="MRimg"></td>
        		</tr>
				<tr><td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td></tr>
			</table>
	</form>
	<%@ include file="../../include/foot.jsp"%>
</body>
</html>
