<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<!-- dwr -->
		<script type="text/javascript" src="<%=basePath%>utils/dwr/engine.js"></script>
		<script type="text/javascript" src="<%=basePath%>utils/dwr/util.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>dwr/interface/LabCodeService.js"></script>
		
		<script language="JavaScript"
			src="<%=basePath%>utils/common/commonutil.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/validate.js"></script>
		<script>
	function submitvalue(actionstr)
    {
		document.commcodeform.action = actionstr;
		document.commcodeform.submit();  
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
	function copyrslab(name){
		var el = document.getElementsByTagName('input');     
		var len = el.length; 
		var m = 0;
		var n = 0;    
		for(var i=0; i<len; i++)     
		{         
			if((el[i].type=="checkbox") && (el[i].name==name))         
			{             
				if(el[i].checked == true){
			    	m = m + 1;
			    	n = i;
			 	}      
			}     
		}  
		if(m<1){
			alert('<s:property value="getText('lab.selectcopy')"/>');
			return false;
		}else{
			if(m>1){
				alert('<s:property value="getText('lab.selectonly.one')"/>');
				return false;
			}else{
				var url="<%=basePath%>/code/labCode/preCopyLabCode.action?labCodeVo.id="+el[n].value;;
				submitvalue(url);
				return true;				
			}
			
		}
	}
	var checkFlag = true;
	function checkNameIsExist(checkStr,flag)
		{
		/*
			DWREngine.setAsync(false);
			ComCodeService.isExsitLabCodeByCode(checkStr,document.getElementById("typeid").value,flag,function getDate(retrueStr){
				if(retrueStr=="1"){
					if(flag=="0"){
						alert('<s:property value="getText('lab.codeinputname.repeat')"/>');
						document.getElementById("name").focus();
						checkFlag = false;
						return false;
					}else{
						checkFlag = false;
						return true;
					}
					
				}else{
					checkFlag = true;
					checkCodeIsExist(document.getElementById("code").value, document.getElementById("typeid").value,"1");
					return true;
				}
				
			});
			DWREngine.setAsync(true);
					*/
		}
		function checkCodeIsExist(checkStr,typeid,flag)
		{
		/*
			DWREngine.setAsync(false);
			LabCodeService.isExsitLabCodeByCode(checkStr,typeid,flag,function getDate(retrueStr){
				if(retrueStr=="1"){
					if(flag=="1"){
						//alert("输入的公共代码编码重复!");
						//document.getElementById("code").focus();
						//checkFlag = false;
						//return true;
					}else{
						checkFlag = false;
						return true;
					}
					
				}else{
					
					checkFlag = true;
					
					return true;
				}
				
			});
			DWREngine.setAsync(true);
					*/
		}
	function submitvalueforlist(actionstr){
				var name = document.getElementById('name').value;
				if(name==''){
					alert('<s:property value="getText('lab.inputcode.name')"/>');
					return false;
				}
				var sort = document.getElementById('sort').value;
				var code = document.getElementById('code').value;
				var remark = document.getElementById('remark').value;
				var typeid = document.getElementById('typeid').value;
				checkNameIsExist(name,"0");
				if(checkFlag){
				location.href="<%=basePath%>/code/labCode/addLabCode4Code.action?labCodeVo.typeid="+typeid+"&labCodeVo.remark="+remark+"&labCodeVo.code="+code+"&labCodeVo.sort="+sort+"&labCodeVo.name="+name;
				}
	}
	function submitUpdateLabCode(actionstr){
				var name = document.getElementById('name').value;
				if(name==''){
					alert('<s:property value="getText('lab.inputcode.name')"/>');
					return false;
				}
				var sort = document.getElementById('sort').value;
				var code = document.getElementById('code').value;
				var remark = document.getElementById('remark').value;
				var typeid = document.getElementById('typeid').value;
				var id = document.getElementById('id').value;
				
				if(document.getElementById("name").value!=document.getElementById("namex").value){
					checkNameIsExist(name,"0");
				}
				if(checkFlag){
				var url="labCodeVo.id="+id+"&labCodeVo.typeid="+typeid+"&labCodeVo.remark="+remark+"&labCodeVo.code="+code+"&labCodeVo.sort="+sort+"&labCodeVo.name="+name;
				url = url.split("%").join("~"); 
				location.href="<%=basePath%>/code/labCode/updateLabCode4Code.action?"+url;
				}
				
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
	<body class="" id="mainid">
		<s:form action="" method="post" name="commcodeform">
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
												<s:text name="lab.common.code"/>：
												<span><s:text name="lab.index"/></span>
											</h2>
										</div>

										<div class="buttonbar2">
											<label>
												<s:text name="lab.commoncode.list"></s:text>：
											</label>
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd"
															style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>

										</div>
										
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="lab.commoncode.name"></s:text>
													</th>
													<th>
														<s:text name="lab.commoncode.id"/>
													</th>
													<th>
														<s:text name="lab.desc"></s:text>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="listResult!=null">
														<s:set name="alllist" value="listResult" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="c">
																	<s:property value="name" />
																</td>
																<td class="c">
																	<s:property value="code" />
																</td>
																<td class="c">
																	<s:property value="remark" />
																</td>
																<td class="c">

																	<a href="#"
																		onclick="submitvalue('<%=basePath%>code/labCode/preUpdateLabCode4Code.action?labCodeVo.id=${id }');"
																		class="edit"><img height="20" width="20" src="<%=basePath%>img/bianji.gif" title='<s:property value="getText('lab.edit.modify')"/>'/></a>
																	
																	&nbsp;&nbsp;
																	
																	<a
																		href="<%=basePath%>/code/labCode/delLabCode4Code.action?labCodeVo.id=${id }"
																		onclick="javascript:if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){return true;}return false;"
																		class="delete"><img height="20" width="20" src="<%=basePath%>img/shanchu.gif" title='<s:property value="getText('lab.delete.data')"/>'/></a>
																	  
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												
												<tr>
																	<td class="1">
																		<s:if test="${labCodeVo.name==null}">
																			<input name="labCodeVo.sort" disabled="disabled"  id="sort" type="hidden"
																			 style="width: 10px"	 value="${maxSort}"  />
																			 ${maxSort+1}
																		</s:if>
																		<s:else>
																			<input name="labCodeVo.sort" disabled="disabled"  id="sort" type="hidden"
																			 style="width: 10px"	 value="${labCodeVo.sort}"  />
																			 ${labCodeVo.sort+1}
																		</s:else>	 
																	</td>
																	<td class="c">
																		<input name="labCodeVo.name" id="name" type="text"
																		  style="width: 80px"	  value="${labCodeVo.name }" />
																		<input name="labCodeVo.name" id="namex" type="hidden"
																				size="20" value="${labCodeVo.name }" />
																	</td>
																	<td class="c">
																		<input name="labCodeVo.code" id="code" type="text"
																			 value="${labCodeVo.code }" />
																	</td>
																	<td class="c">
																		<input name="labCodeVo.remark" id="remark" type="text"
																		 	 value="${labCodeVo.remark}" />
																	</td>
																	
																		<input type="hidden" name="labCodeVo.typeid" id="typeid" value="${labCodeVo.typeid }" />
																	
																	<td class="c">
																		<s:if test="${labCodeVo.name==null}">
																			 <a id="BtnPreview" class="zPushBtn" href="javascript:void();" onclick="submitvalueforlist('addLabCode4Code.action');return false;"><img height="20" width="20" src="<%=basePath%>img/xinjian.gif"/><b><s:text name="lab.code.add"></s:text></b></a>
																		</s:if>
																		<s:else>
																			<input type="hidden" id="id"  value="${labCodeVo.id }" />
																			<a id="BtnPreview" class="zPushBtn" href="javascript:void();" onclick="submitUpdateLabCode('updateLabCode4Code.action');return false;"><img height="20" width="20" src="<%=basePath%>img/bianji.gif"/><b><s:text name="lab.code.modify"></s:text></b></a>
																		</s:else>
																	</td>
												</tr>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
								</td>
							</tr>
							<tr>
							<tr>
							</tr>
							</tr>
					
											
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
		
		<%@ include file="/jsp/include/page.jsp"%>
	</body>
</html>