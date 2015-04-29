<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
		function submitvalue(actionstr){
			var df=document.samForm;
			df.action = actionstr;
			df.submit();  
		}
		
		function check(name){
			var el =document.getElementsByTagName('input');     
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
				validate.tip('<s:property value="getText('selected.pls')"/>',$('#functionId'));
				return false;
			}else{
				return true;
			}
		}
		
		function deleteBatch(url){
			if(check('labSamVo.ids')){
				if(confirm('<s:property value="getText('confirm.deleted.select')"/>')){
					goAction(url);
				}
			}
		}
		function exportLabSam(url){
			if(check('labSamVo.ids')){
					goAction(url);
			}
		}	
		function ajax2TwoCode(){
			if(check('labSamMainVo.ids')){
				var ids=new Array();
				$(".reaCoder:checked").each(function(i){
					ids+=$(this).closest('tr').find('input[name*="ids"]').val()+",";
				});
				if(ids.length>32){
				ids=ids.substring(0,ids.length-1);
				}
				var url  = '${basePath}sam/labSam/showLabSam4printTwoCode.action?labSamMainVo.id='+ids;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.2d')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			 }	
		  }
		 function ajax2BarCode(){
			if(check('labSamMainVo.ids')){
				var ids=new Array();
				$(".reaCoder:checked").each(function(i){
					ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				if(ids.length>32){
				ids=ids.substring(0,ids.length-1);
				}
				var url  = '${basePath}sam/labSam/showLabSam4printBarCode.action?labSamMainVo.id='+ids;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.bar')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			  }	
		   }
		function getSamType(id){
	       var url  = '<%=basePath%>sam/labSam/preLabReaTypeTree.action?labSamVo.id='+id;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('sam.style.list')"/>',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
			    min:false,
				lock:true
			 });
			}
			
		function importSam(){
		var labSamType=$("#samTypeId").val();
			if(labSamType=='0'){
				alert('<s:property value="getText('sam.select.classify')"/>');
				return false;
			}
	       var url  = '<%=basePath%>sam/labSam/preImportLabSam.action?labSamVo.samTypeId='+labSamType;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('sam.select.classify')"/>',
				opacity:0.4,
				width:400,
				height:300,
				max:false,
				min:false,
				lock:true
			 });
		}
			
	function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>/template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			alert(data);
	   	  			window.location.href = '<%=basePath%>sam/labSam/'+actionstr+'?path='+data;
				}
			}
	   });
	}
	function importLabSam(){
	       var url  = '<%=basePath%>sam/labSam/preImportLabSam.action';
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('sam.imported')"/>',
				opacity:0.4,
				width:400,
				height:300,
				max:false,
				min:false,
				lock:true
			 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="samForm">
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
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="sam.no"/>：
																	</label>
																</td>
																<td>
																	<input id="sampNo" name="labSamMainVo.sampNo" value="${labSamMainVo.sampNo }" />
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
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>

																	</label>
																</td>
																<td>
																	<l:a uri="sam/labSam/preAddLabSam.action?labSamVo.samTypeId=${labSamVo.samTypeId}" value="lab.code.add" />
																</td>
																<td>
																	<l:a uri="sam/labSam/preAddLabSam.action" onclick="ajax2TwoCode();return false;" value="print.2d" />
																</td>
																<td>
																	<l:a uri="sam/labSam/preAddLabSam.action" onclick="ajax2BarCode();return false;" value="print.bar" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th width="40">
													<input type="checkbox" id="allCheckBox" key="labSamMainVo.ids" />
												</th>
												<th property="sampNo">
													<s:text name="sam.no"/>
												</th>
												<th>
													<s:text name="cust.name"/>
												</th>
												<th>
													<s:text name="sam.name"/>
												</th>
												<th>
													<s:text name="sam.number"/>
												</th>

												<th property="registDate">
													<s:text name="sam.send.date"/>
												</th>
												<th>
													<s:text name="config.ops"/>
												</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labSamMainVo.ids" value="${id}" class="reaCoder"/>
															</td>
															<td class="l">
																<a href="${basePath }sam/labSam/showLabSam.action?labSamMainVo.id=${id}">${sampNo}</a>
															</td>
															<td>
																${customer }
															</td>
															<td class="l" title="${sampName}">
																<s:if test="${fn:length(sampName)>20 }">
																  ${fn:substring(sampName,0,20)}
																</s:if>
																<s:else>
																${sampName}
																</s:else>
															</td>
															<td>
																${num }
															</td>

															<td class="c">
																${sampDate}
															</td>
															<td class="c">
																	<l:a href="javascript:;" uri="sam/labSam/preUpdateLabSam.action?labSamMainVo.id=${id}" value="lab.code.modify" />
																	<l:a href="javascript:;" uri="sam/labSam/deleteLabSam.action?labSamMainVo.ids=${id}" onclick="javascript:if(confirm('确定要删除吗?')){nextUrl('${basePath}sam/labSam/deleteLabSam.action?labSamMainVo.ids=${id}');}return false;" value="config.del" />
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=samForm" flush="true" /></td>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
