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

.redtd td {
	background: #FFD6D6;
}
</style>
		<script>
		function submitvalue(actionstr){
			var minSize = $("#minSize").val();
			var maxSize = $("#maxSize").val();
			if(minSize>maxSize){
				alert("后面的数量应该大于等于前面的数量！");
				return false;
			}
			var df=document.regentForm;
			df.action = actionstr;
			df.submit();  
		}
		function deleteOne(type,id){
			if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
				window.location.href='${basePath}reagent/labReagent/deleteLabReagent.action?labReagentVo.reagentTypeId='+type+'&labReagentVo.ids='+id;
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
			if(check('labReagentVo.ids')){
				if(confirm('确认删除选中信息吗?')){
					goAction(url);
				}
			}
		}
			
		function printTwoDimension(){
			if(check('labReagentVo.ids')){
			submitvalue('<%=basePath%>reagent/labReagent/showLabReagent4printTwoCode.action');
			}		
		}
		function printBarCode(){
			if(check('labReagentVo.ids')){
			submitvalue('<%=basePath%>reagent/labReagent/showLabReagent4printBarCode.action');
			}		
		}
		function ajax2TwoCode(){
			if(check('labReagentVo.ids')){
			var ids=new Array();
				$(".reaCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}reagent/labReagent/showLabReagent4printTwoCode.action?labReagentVo.id='+ids;
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
			if(check('labReagentVo.ids')){
				var ids=new Array();
				$(".reaCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}reagent/labReagent/showLabReagent4printBarCode.action?labReagentVo.id='+ids;
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
		function getReagentType(id){
	       var url  = '<%=basePath%>reagent/labReagent/preLabReaTypeTree.action?labReagentVo.id='+id;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'试剂类型列表',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
			    min:false,
				lock:true
			 });
			}
			
			function importReagent(reagentTypeId){
	       var url  = '<%=basePath%>reagent/labReagent/preImportLabReagent.action?labReagentVo.reagentTypeId='+reagentTypeId;
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'试剂类型列表',
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
			url:'<%=basePath%>template/labTemplate/ajaxVerification4Export.action',
			data:{'labTemplateVo.busId':'${funId}'},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data != "false"){
	   	  			window.location.href = '<%=basePath%>reagent/labReagent/'+actionstr+'?path='+data;
				}
			}
	   });
	}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="regentForm">
			<input type="hidden" name="labReagentVo.reagentTypeName" value="${labReagentVo.reagentTypeName}" />
			<input type="hidden" name="labReagentVo.reagentTypeId" value="${labReagentVo.reagentTypeId}" />
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
												<span>[<font color="blue">${labReagentVo.reagentTypeName}</font>]><s:text name="top.index"/></span>
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
																		<s:text name="reageid"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReagentVo.code" value="${labReagentVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="rea.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReagentVo.name" value="${labReagentVo.name}" />
																</td>
																<td>
																	<label>
																		<s:text name="rearegular"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReagentVo.size" value="${labReagentVo.size}" />
																</td>
																<td>
																	<label>
																		<s:text name="reagstock"/>：
																	</label>
																</td>
																<td>
																	<input type="text" id="minSize" name="labReagentVo.minSize" valType="number" style="width: 50px;" value="${labReagentVo.minSize}" />
																</td>
																<td>
																	<s:text name="to"/>
																	<input type="text" id="maxSize" name="labReagentVo.maxSize" valType="number" style="width: 50px;" value="${labReagentVo.maxSize}" />
																</td>
																<td>
																	<l:a uri="reagent/labReagent/listLabReagent.action" onclick="submitvalue('listLabReagent.action');return false;" value="fun.query" />
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
																	<l:a uri="reagent/labReagent/preAddLabReagent.action?labReagentVo.reagentTypeId=${labReagentVo.reagentTypeId}" value="admin.add" />
																</td>
																<td>
																	<l:a uri="reagent/labReagent/deleteLabReagent.action" onclick="deleteBatch('deleteLabReagent.action');return false;" value="lab.code.deleteall" />
																</td>
																<td>
																	<!--<l:a uri="reagent/labReagent/preAddLabReagent.action"
																		onclick="ajaxVerification('exportLabReagent.action');return false;"
																		value="导出试剂清单" />-->
																	<l:export params="labReagentVo,pageResult" type="excel" source="${labReagentVo.filePath}" target="${funName}-${now}.xls" value="导出${funName}" />
																</td>
																<td>
																	<l:a uri="reagent/labReagent/preAddLabReagent.action" onclick="importReagent('${labReagentVo.reagentTypeId}');return false;" value="导入试剂清单" img="/img/daoru.gif"/>
																</td>
																<td>
																	<l:a uri="reagent/labReagent/listLabReagent4printTwoCode.action" onclick="ajax2TwoCode();return false;" value="打印二维码" />
																</td>
																<td>
																	<l:a uri="reagent/labReagent/listLabReagent4printBarCode.action" onclick="ajax2BarCode();return false;" value="打印条形码" />
																</td>
																<!-- <td>
																	<l:a
																		uri="reagent/labReagent/updateLabReagentList.action"
																		value="批量更新编码" /> -->
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<tr>
												<th>
													<input type="checkbox" id="ids" onclick="if(this.checked==true) { checkAll('labReagentVo.ids'); } else { clearAll('labReagentVo.ids'); }" />
												</th>
												<th>
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="code">
													<s:text name="reageid"/>
												</th>
												<th property="name">
													<s:text name="rea.name"/>
												</th>
												<th property="size">
													<s:text name="rearegular"/>
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
														<s:if test="${amount<=safeAmount}">
															<tr class="redtd">
														</s:if>
														<s:else>
															<tr>
														</s:else>
														<td class="c">
															<input type="checkbox" name="labReagentVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" class="reaCoder" />
														</td>
														<td class="c">
															${pageSizex* currenPagex+st.index+1}
														</td>
														<td class="c">
															${code }
														</td>
														<td class="l">
															<l:a href="#" uri="reagent/labReagent/showLabReagent.action?labReagentVo.id=${id}" value="${name}" />
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
															<l:a href="#" uri="reagent/labReagent/preUpdateLabReagent.action?labReagentVo.reagentTypeId=${labReagentVo.reagentTypeId}&labReagentVo.id=${id}" value="lab.code.modify" />
															<l:a href="#" uri="reagent/labReagent/deleteLabReagent.action?labReagentVo.reagentTypeId=${labReagentVo.reagentTypeId}&labReagentVo.ids=${id}" onclick="deleteOne('${labReagentVo.reagentTypeId}','${id}');" value="lab.code.del" />
															<a id="BtnPreview" href="javascript:;" onclick="getReagentType('${id}');return false;"><s:text name="fun.transfer"/></a>
														</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:if>
											<s:else>
												<th>
												<td colspan="7" align="center" valign="middle">
													<font color="red"><s:text name="lab.msg.none"/></font>
												</td>
												</th>
											</s:else>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=regentForm" flush="true" /></td>
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
