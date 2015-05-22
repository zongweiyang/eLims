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
.redtd td {background:#FFD6D6;}
</style>
		<script>
		function submitvalue(actionstr){
			var minSize = $("#minSize").val();
			var maxSize = $("#maxSize").val();
			if(minSize>maxSize){
				alert('<s:property value="getText('tagerrornumber')"/>');
				return false;
			}
			var df=document.regentForm;
			df.action = actionstr;
			df.submit();  
		}
		function deleteOne(type,id){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}reference/labReference/deleteLabReference.action?labReferenceVo.referenceTypeId='+type+'&labReferenceVo.ids='+id;
			}	
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
				validate.tip("请选择！",$('#functionId'));
				return false;
			}else{
				return true;
			}
		}
		
		function deleteBatch(url){
			if(check('labReferenceVo.ids')){
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goAction(url);
				}
			}
		}
			
		function printTwoDimension(){
			if(check('labReferenceVo.ids')){
			submitvalue('<%=basePath%>reference/labReference/showTwoDimension.action');
			}		
		}
		function printBarCode(){
			if(check('labReferenceVo.ids')){
			submitvalue('<%=basePath%>reference/labReference/showBarCode.action');
			}		
		}
		
		function getReferenceType(id){
	       var url  = '<%=basePath%>reference/labReference/preLabRefTypeTree.action?labReferenceVo.id='+id;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('stdtypelsit')"/>',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
				min:false,
				lock:true
			 });
			}
			
			function importReference(referenceTypeId){
	       var url  = '<%=basePath%>reference/labReference/preImportLabReference.action?labReferenceVo.referenceTypeId='+referenceTypeId;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('stdtypelsit')"/>',
				opacity:0.4,
				width:400,
				height:300,
				max:false,
				min:false,
				lock:true
			 });
			}
			
			function ajax2TwoCode(){
			if(check('labReferenceVo.ids')){
			var ids=new Array();
				$(".refCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}reference/labReference/showLabReference4printTwoCode.action?labReferenceVo.id='+ids;
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
			if(check('labReferenceVo.ids')){
				var ids=new Array();
				$(".refCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}reference/labReference/showLabReference4printBarCode.action?labReferenceVo.id='+ids;
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
	function ajaxVerification(actionstr){
		$.ajax({
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			window.location.href = '<%=basePath%>reference/labReference/'+actionstr+'?path='+data;
				}
			}
	   });
	}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="regentForm">
			<input type="hidden" name="labReferenceVo.referenceTypeName"
				value="${labReferenceVo.referenceTypeName}" />
			<input type="hidden" name="labReferenceVo.referenceTypeId"
				value="${labReferenceVo.referenceTypeId}" />
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
												<span>[<font color="blue">${labReferenceVo.referenceTypeName}</font>]
													><s:text name="top.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
															<td>
																	<label>
																		<s:text name="stdandard.code"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReferenceVo.code"
																		value="${labReferenceVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="stdandard.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReferenceVo.name"
																		value="${labReferenceVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="stdandard.regular"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReferenceVo.size"
																		value="${labReferenceVo.size}" />
																</td>
																<td>
																	<label>
																		<s:text name="stgstockk"/>：
																	</label>
																</td>
																<td>
																	<input type="text" id="minSize" name="labReferenceVo.minSize"
																		valType="number" style="width: 50px;"
																		value="${labReferenceVo.minSize}" />
																</td>
																<td>
																	<s:text name="to"/>
																	<input type="text" id="maxSize" name="labReferenceVo.maxSize"
																		valType="number" style="width: 50px;"
																		value="${labReferenceVo.maxSize}" />
																</td>
															
																<!--  <td><label>标准品类型：</label></td>
					                      						<td>
					                      							<input type="text" name="labReferenceVo.referenceTypeName" value="${labReferenceVo.referenceTypeName}" />
					                      						</td>-->
																<td>
																	<l:a uri="reference/labReference/listLabReference.action"
																		onclick="submitvalue('listLabReference.action');return false;"
																		value="fun.query" />
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
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>

																	</label>
																</td>
																<td>
																	<l:a
																		uri="reference/labReference/preAddLabReference.action?labReferenceVo.referenceTypeId=${labReferenceVo.referenceTypeId}"
																		value="admin.add" />
																</td>
																<td>
																	<l:a uri="reference/labReference/deleteLabReference.action"
																		onclick="deleteBatch('deleteLabReference.action');return false;"
																		value="lab.code.deleteall" />
																</td>
																<td>
																	<!--<l:a uri="reference/labReference/preAddLabReference.action"
																		onclick="ajaxVerification('exportLabReference.action');return false;"
																		value="#export#标准品清单" />-->
																		<l:export params="labReferenceVo,pageResult" type="excel" source="${labReferenceVo.filePath}" target="${funName}-${now}.xls" value="#export#${funName}"/>
																</td>
																<td>
																	<l:a uri="reference/labReference/preAddLabReference.action"
																		onclick="importReference('${labReferenceVo.referenceTypeId}');return false;"
																		value="importstdlist" />
																</td>
																<td>
																	<l:a uri="reference/labReference/preAddLabReference.action"
																		onclick="ajax2TwoCode();return false;" value="print.2d" />
																</td>
																<td>
																	<l:a uri="reference/labReference/preAddLabReference.action"
																		onclick="ajax2BarCode();return false;" value="print.bar" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<tr>
												<th>
													<input type="checkbox" id="ids"
														onclick="if(this.checked==true) { checkAll('labReferenceVo.ids'); } else { clearAll('labReferenceVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="code">
													<s:text name="stdandard.code"/>
												</th>
												<th property="name">
													<s:text name="stdandard.name"/>
												</th>
												<th property="size">
													<s:text name="stdandard.regular"/>
												</th>
												<th property="amount">
													<s:text name="std.stock"/>
												</th>
												<th property="purity">
													<s:text name="pure"/>
												</th>
												<th property="dangerSize">
													<s:text name="risk.level"/>
												</th>
												<th property="saveOrg">
													<s:text name="save.office"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<s:if test="${amount<=safeAmount}"><tr class="redtd"></s:if>
                                                        <s:else><tr></s:else>
															<td class="c">
																<input type="checkbox" name="labReferenceVo.ids"
																	id="ids<s:property value="#st.index+1" />"
																	value="${id}" class="refCoder" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${code }
															</td>
															<td class="l">
																<l:a href="#"
																	uri="reference/labReference/showLabReference.action?labReferenceVo.id=${id}"
																	value="${name}" />
															</td>
															
															<td class="l">
																${size }
															</td>
															<td class="l">
																${amount }
															</td>
															<td class="l">
																${purity }
															</td>
															<td class="c">
																${dangerSize}
															</td>
															<td class="l">
																${saveOrgName}
															</td>
															<td class="c">
																<l:a href="#"
																	uri="reference/labReference/preUpdateLabReference.action?labReferenceVo.referenceTypeId=${labReferenceVo.referenceTypeId}&labReferenceVo.id=${id}"
																	value="lab.code.modify" />
																<l:a href="#"
																	uri="reference/labReference/deleteLabReference.action?labReferenceVo.referenceTypeId=${labReferenceVo.referenceTypeId}&labReferenceVo.ids=${id}"
																	onclick="deleteOne('${labReferenceVo.referenceTypeId}','${id}');" value="lab.code.del" />
																<a id="BtnPreview" href="javascript:;"
																	onclick="getReferenceType('${id}');return false;"><s:text name="fun.transfer"/></a>
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
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=regentForm" flush="true" /></td>
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
