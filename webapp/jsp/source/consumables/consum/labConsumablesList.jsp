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
				window.location.href='${basePath}consumables/labConsumables/deleteLabConsumables.action?labConsumablesVo.consumablesTypeId='+type+'&labConsumablesVo.ids='+id;
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
			if(check('labConsumablesVo.ids')){
				if(confirm('<s:property value="getText('confirmselectdel')"/>')){
					goAction(url);
				}
			}
		}
			
		function printTwoDimension(){
			if(check('labConsumablesVo.ids')){
			submitvalue('<%=basePath%>consumables/labConsumables/showTwoDimension.action');
			}		
		}
		function printBarCode(){
			if(check('labConsumablesVo.ids')){
			submitvalue('<%=basePath%>consumables/labConsumables/showBarCode.action');
			}		
		}
		
		function getConsumablesType(id){
	       var url  = '<%=basePath%>consumables/labConsumables/preLabConTypeTree.action?labConsumablesVo.id='+id;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('acontypelist')"/>',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
				min:false,
				lock:true
			 });
			}
			
		function importConsumables(consumablesTypeId){
	       var url  = '<%=basePath%>consumables/labConsumables/preImportLabConsumables.action?labConsumablesVo.consumablesTypeId='+consumablesTypeId;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'<s:property value="getText('acontypelist')"/>',
				opacity:0.4,
				width:400,
				height:300,
				max:false,
				min:false,
				lock:true
			 });
			}
			
		function ajax2TwoCode(){
		   if(check('labConsumablesVo.ids')){
			var ids=new Array();
				$(".comCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}consumables/labConsumables/showLabConsumables4printTwoCode.action?labConsumablesVo.id='+ids;
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
			if(check('labConsumablesVo.ids')){
				var ids=new Array();
				$(".comCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}consumables/labConsumables/showLabConsumables4printBarCode.action?labConsumablesVo.id='+ids;
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
	   	  			window.location.href = '<%=basePath%>consumables/labConsumables/'+actionstr+'?path='+data;
				}
			}
	   });
	}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="regentForm">
			<input type="hidden" name="labConsumablesVo.consumablesTypeName"
				value="${labConsumablesVo.consumablesTypeName}" />
			<input type="hidden" name="labConsumablesVo.consumablesTypeId"
				value="${labConsumablesVo.consumablesTypeId}" />
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
												<span>[<font color="blue">${labConsumablesVo.consumablesTypeName}</font>]
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
																		<s:text name="conno"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConsumablesVo.code"
																		value="${labConsumablesVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="conname"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConsumablesVo.name"
																		value="${labConsumablesVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="regular.no"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labConsumablesVo.size"
																		value="${labConsumablesVo.size}" />
																</td>
																<td>
																	<label>
																		<s:text name="std.stock"/>：
																	</label>
																</td>
																<td>
																	<input id="minSize" type="text" name="labConsumablesVo.minSize"
																		valType="number" style="width: 50px;"
																		value="${labConsumablesVo.minSize}" />
																</td>
																<td>
																	<s:text name="to"/>
																	<input id="maxSize" type="text" name="labConsumablesVo.maxSize"
																		valType="number" style="width: 50px;"
																		value="${labConsumablesVo.maxSize}" />
																</td>
																<!--  <td><label>耗材类型：</label></td>
					                      						<td>
					                      							<input type="text" name="labConsumablesVo.consumablesTypeName" value="${labConsumablesVo.consumablesTypeName}" />
					                      						</td>-->
																<td>
																	<l:a uri="consumables/labConsumables/listLabConsumables.action"
																		onclick="submitvalue('listLabConsumables.action');return false;"
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
																		uri="consumables/labConsumables/preAddLabConsumables.action?labConsumablesVo.consumablesTypeId=${labConsumablesVo.consumablesTypeId}"
																		value="lab.code.add" />
																</td>
																<td>
																	<l:a uri="consumables/labConsumables/deleteLabConsumables.action"
																		onclick="deleteBatch('deleteLabConsumables.action');return false;"
																		value="lab.code.deleteall" />
																</td>
																<td>
																	<!--<l:a uri="consumables/labConsumables/preAddLabConsumables.action"
																		onclick="ajaxVerification('exportLabConsumables.action');return false;"
																		value="导出耗材清单" />-->
																	 <l:export params="labConsumablesVo,pageResult" type="excel" source="${labConsumablesVo.filePath}" target="${funName}-${now}.xls" value="导出${funName}"/>
																</td>
																<td>
																	<l:a uri="consumables/labConsumables/preAddLabConsumables.action"
																		onclick="importConsumables('${labConsumablesVo.consumablesTypeId}');return false;"
																		value="import.consumables" />
																</td>
																<td>
																	<l:a uri="consumables/labConsumables/preAddLabConsumables.action"
																		onclick="ajax2TwoCode();return false;" value="print.2d" />
																</td>
																<td>
																	<l:a uri="consumables/labConsumables/preAddLabConsumables.action"
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
														onclick="if(this.checked==true) { checkAll('labConsumablesVo.ids'); } else { clearAll('labConsumablesVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="code">
													<s:text name="conno"/>
												</th>
												<th property="name">
													<s:text name="conname"/>
												</th>
												<th property="size">
													<s:text name="regular.no"/>
												</th>
												<th property="amount">
													<s:text name="std.stock"/>
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
																<input type="checkbox" name="labConsumablesVo.ids"
																	id="ids<s:property value="#st.index+1" />"
																	value="${id}" class="comCoder" />
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${code }
															</td>
															<td class="l">
																<l:a href="#"
																	uri="consumables/labConsumables/showLabConsumables.action?labConsumablesVo.id=${id}"
																	value="${name}" />
															</td>
															<td class="l">
																${size }
															</td>
															<td class="l">
																${amount }
															</td>
															<td class="c">
																${dangerSize}
															</td>
															<td class="l">
																${saveOrgName}
															</td>
															<td class="c">
																<l:a href="#"
																	uri="consumables/labConsumables/preUpdateLabConsumables.action?labConsumablesVo.consumablesTypeId=${labConsumablesVo.consumablesTypeId}&labConsumablesVo.id=${id}"
																	value="theme.modify" />
																	<l:a href="#"
																	uri="consumables/labConsumables/deleteLabConsumables.action?labConsumablesVo.consumablesTypeId=${labConsumablesVo.consumablesTypeId}&labConsumablesVo.ids=${id}"
																		onclick="deleteOne('${labConsumablesVo.consumablesTypeId}','${id}');" value="lab.code.del" />
																<a id="BtnPreview" href="javascript:;"
																	onclick="getConsumablesType('${id}');return false;"><s:text name="fun.transfer"/></a>
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
