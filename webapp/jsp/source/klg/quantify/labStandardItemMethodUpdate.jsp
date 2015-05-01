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
			function submitforform(){
				$('#standardItemMethod').submit();
			}
			function goToNextAction(url){
				$('#standardItemMethod').attr('action','${basePath}'+url);
				submitforform();
			}
			function chooseItem(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath%>/klg/labStandardItem/showLabStandardItem4select.action?labStandardItemVo.standardId=${labStandardVo.id}',
					title:'<s:property value="getText('relaitem')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false,
					close:function(){
						thisFlush();
					}
				 });
		    }
		    function chooseMinItem(itemId){
		    	$.dialog({
					id:'id',
					content:'url:'+'<%=basePath%>/klg/labStandardItem/showLabStandardMinItem4select.action?labStandardItemVo.standardId=${labStandardVo.id}&labStandardItemVo.itemId='+itemId,
					title:'<s:property value="getText('addalittle')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false,
					close:function(){
						thisFlush();
					}
				 });
		    }
		    function thisFlush(){
		    	$('#standardItemMethod').attr('action','${basePath}'+'klg/labStandardItem/preUpdateLabStandardItemMethod.action');
				$('#standardItemMethod').submit();
		    }
		    function linkValue(obj,index){
		    	var thisVal=$(obj).val();
		    	var thiskey=$(obj).prev().val();
		    	$('input[id*="value'+index+'"]').each(function(){
			    	 if($(this).val()==""){
			    	 	$(this).prev().val(thiskey);
			    	 	$(this).val(thisVal);
			    	 }
		    	});
		    }
		   function chooseMethod(itemId,methodIds){
		   		goToNextAction('/klg/labStandardItem/saveLabStandardItemMethod.action?labStandardItemMethodVo.standardId=${labStandardVo.id}&labStandardItemMethodVo.itemId='+itemId+'&labStandardItemMethodVo.methodIds='+methodIds+'&labStandardItemMethodVo.type=1');
				
		    }
		    function delrow(id,type){
		    	if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){	
		    		goToNextAction('/klg/labStandardItem/deleteLabStandardItem.action?labStandardItemVo.id='+id+'&labStandardItemVo.typex='+type);
		    	}
		    }
		    $(function(){
		    	var currentPage = '${pageResult.currentPage }';
		    	var pagerMethod = '${pageResult.pagerMethod }';
		    	var pageSize = '${pageResult.pageSize }';
		    	var type = '${labStandardItemMethodVo.type}';
		    	if(type == '1'){
		    			$.dialog({
							id:'id',
							content:'url:'+'<%=basePath%>/klg/labStandardItem/showLabMethod4select.action?labStandardItemMethodVo.standardId=${labStandardVo.id}&labStandardItemMethodVo.itemId=${labStandardItemMethodVo.itemId}&labStandardItemMethodVo.methodIds=${labStandardItemMethodVo.methodIds}&currentPages='+currentPage+'&pagerMethods='+pagerMethod+'&pageSizes='+pageSize,
							title:'<s:property value="getText('contrmethod')"/>',
							opacity:0.4,
							width:800, 
							height:500,
							lock:true,
							max:false,
							min:false,
							close:function(){
								thisFlush();
							}
				 		});
		    	}
		    
		    })
		    
		    function closeThis(){
		    	$('#tableMain').hide();
		    	$('#xs').show();
		    	$('#yc').hide();
		    }
		    function openThis(){
		    	$('#tableMain').show();
		    	$('#xs').hide();
		    	$('#yc').show();
		    }
		    
			function deleteRow(obj){
				$(obj).parent().parent().remove();
			}
		</script>
		
	</head>
	<body class="" id="mainid" >
		<form action="" method="post" name="standardItemMethodFrom" id="standardItemMethod">
			<input type="hidden" name="labStandardVo.standTypeName" value="${labStandardVo.standTypeName}" />
			<input type="hidden" name="labStandardVo.standTypeId" value="${labStandardVo.standTypeId}" />
			<input type="hidden" name="labStandardVo.id" value="${labStandardVo.id}" />
			<input type="hidden" name="id" value="${labStandardVo.id}" />
			<input type="hidden" name="labStandardItemVo.standardId" id="standId" value="${labStandardVo.id}" />
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
												<span><s:text name="maintain"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="/klg/labStandardItem/updateLabStandardItemMethod.action" img="/img/add.gif" onclick="goAction('updateLabStandardItemMethod.action');" value="lab.code.save"/>
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
																	<label>
																		<s:text name="sam.classify"/>：
																	</label>
																</td>
																<td>
																	<s:select headerKey="" headerValue="-全部-" list="#request.samTypeList" listKey="id" listValue="name"  name="labStandardItemVo.labSamTypeId"  theme="simple"></s:select>
																</td>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labItemVo.name" value="${labItemVo.name}" size="45"/>
																</td>
																<td>
																	<l:a uri="/klg/labStandardItem/updateLabStandardItemMethod.action" img="/img/query.gif" onclick="goAction('preUpdateLabStandardItemMethod.action');" value="fun.query"/>
																</td>
																<td style="vertical-align: middle;">
																	<font color="red"><s:text name="duhaofenge"/></font>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="stdinfo"/>[<font color="red">${labStandardVo.standTypeName}</font>]</span>
												<label id="yc"  style="float: right;display: none; color: #0088ee;cursor: pointer;margin-right: 10px;" title='<s:property value="getText('closeinfo')"/>' onclick="closeThis();" >[关闭]</label>
												<label id="xs" style="float: right;color: #0088ee;cursor: pointer;margin-right: 10px;" title='<s:property value="getText('showinforsds')"/>' onclick="openThis();">[显示]</label>
											</div>
											<table id="tableMain" style="display: none;" class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="std.name"/>：
														</label>
													</td>
													<td>
														${labStandardVo.name}
													</td>
													<td>
														<label>
															<s:text name="biaozcode"/>：
														</label>
													</td>
													<td>
														${labStandardVo.code}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="publish.date"/>：
														</label>
													</td>
													<td>
														${labStandardVo.releaseDate}
													</td>
													<td>
														<label>
															<s:text name="shishidate"/>：
														</label>
													</td>
													<td>
														${labStandardVo.materialDate}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="stdtypess"/>：
														</label>
													</td>
													<td>
														${labStandardVo.standIndex}
													</td>
												</tr>
												<tr>
													<td>
														<label>
															代替标准：
														</label>
													</td>
													<td colspan="3">
														<textarea disabled="true" cols="50" rows="3" name="labStandardVo.replaceName">${labStandardVo.replaceName}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea disabled="true" cols="50" rows="3" name="labStandardVo.remark">${labStandardVo.remark}</textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="itemandbiao"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td colspan="2" class="c" width="30">
														<s:text name="lab.sequence"/>
													</td>
													<td class="c">
														<s:text name="sam.classify"/>
													</td>
													<td class="c">
														<s:text name="itemperference"/>
													</td>
													<td class="c">
														<s:text name="chekstyle"/>
													</td>
													<td class="c">
														<s:text name="projected"/>
													</td>
													<td class="c">
														<s:text name="indexnumber"/>
													</td>
													<td class="c" width="48">
														数值精度
													</td>
													<td class="c" colspan="2">
														限量值
													</td>
													<td class="c">
														实验方法
													</td>
													<td class="c" width="100">
														<s:text name="lab.code.ops"/>
													</td>
												</tr>
												<s:if test="pageResult.resultList!=null">
													<s:iterator status="st1" id="std1" value="pageResult.resultList">
															<tr>
																<td class="c">
																	${st1.index+1}
																</td>
																<td></td>
																<td class="c"><s:select list="#request.samTypeList" listKey="id" listValue="name"  name="standardItemList[${st1.index}].labSamTypeId" value="'${std1.labSamTypeId}'"  theme="simple"></s:select></td>
																<td class="c"><s:select list="#{'0':getText('changguisxing'),'1':getText('guixisng'),'':getText('plseaseselect')}"     name="standardItemList[${st1.index}].itemType" value="'${std1.itemType}'"  theme="simple" ></s:select></td>
																<td class="c"><s:select list="#{'':getText('plsselect'),'1':getText('daycheckl'),'2':getText('weekcheck'),'3':getText('monthcheck'),'4':getText('halfyearch'),'5':getText('yearcheck')}"      name="standardItemList[${st1.index}].testType"  theme="simple"  value="'${std1.testType}'"></s:select></td> 
																<td class="c">
																	<input type="hidden" value="${std1.id}" name="standardItemList[${st1.index}].id"/>
																	<input type="hidden" id="itemId${st1.index}" name="standardItemList[${st1.index}].itemId" value="${std1.itemId}" />
																	<input type="text"  id="itemName${st1.index}" name="standardItemList[${st1.index}].itemName" value="${std1.itemName}" readonly="true"/>
																</td>
																<s:if test="${fn:length(std1.minSItemList)>0}">
																	<td></td>
																	<td></td>
																	<td></td>
																</s:if>
																<s:else>
																<td class="c"><input type="text" name="standardItemList[${st1.index}].scores"  value="${std1.scores }"  readonly="true"/></td>
																<td class="c" width="35">
																	<s:select list="#request.jingduList" headerKey="" headerValue="-请选择-" name="standardItemList[${st1.index}].decimalCount" theme="simple" listKey="name" listValue="name" value="'${std1.decimalCount}'" cssStyle="width:80px;"></s:select>
																</td>
																<td class="c">
																			<s:select  list="#{'':'','<':'<','<=':'<=','>':'>','>=':'>='}" value="'${std1.minKey}'"  name="standardItemList[${st1.index}].minKey"   theme="simple" ></s:select><br />
																			<s:select list="#{'':'','<':'<','<=':'<=','>':'>','>=':'>='}" value="'${std1.maxKey}'" name="standardItemList[${st1.index}].maxKey"  theme="simple"></s:select>
																</td>
																<td class="1">
																	<input style="width: 35px" type="text"  name="standardItemList[${st1.index}].minValue" value="${std1.minValue}" /><br>
																	<input style="width: 35px" type="text" name="standardItemList[${st1.index}].maxValue" value="${std1.maxValue}"/><br>
																</td>
																</s:else>
																<td class="l">
																	<textarea rows="2" cols="20" name="standardItemList[${st1.index}].methodNames"  readonly="readonly" onclick="chooseMethod('${std1.itemId}','${std1.methodIds}');">${std1.methodNames }</textarea>
																	<input type="hidden" name="standardItemList[${st1.index}].methodIds" id="methodIds${st1.index}" value="${std1.methodIds }" />
																</td>
																<td class="c">
																	<a href="javascript:;" onclick="delrow('${std1.id}','0')"><s:text name="lab.code.del"/></a>
																	&nbsp;&nbsp;
																	<a href="javascript:;" onclick="chooseMinItem('${std1.itemId}');">增加小项</a>
																</td>
															</tr>
															<s:if test="${fn:length(std1.minSItemList)>0}">
																<s:iterator status="st2" id="sonId1" value="minSItemList">
																	<tr>
																		<td></td>
																		<td class="c"></td>
																		<td class="c"><s:select list="#request.samTypeList" listKey="id" listValue="name"   name="standardItemList[${st1.index}].minSItemList[${st2.index}].labSamTypeId"  theme="simple"></s:select></td>
																        <td class="c"><s:select list="#{'0':getText('changguisxing'),'1':getText('guixisng')}"  value="${std1.itemType}"   name="standardItemList[${st1.index}].minSItemList[${st2.index}].itemType"  theme="simple" ></s:select></td>
																        <td class="c"><s:select list="#{'3':getText('monthcheck'),'2':getText('weekcheck'),'1':getText('daycheckl')}"  value="${std1.testType}"    name="standardItemList[${st1.index}].minSItemList[${st2.index}].testType"  theme="simple" ></s:select></td> 
																		<td class="r">
																			<input type="hidden" value="${sonId1.id}" name="standardItemList[${st1.index}].minSItemList[${st2.index}].id"/>
																			<input type="hidden"  name="standardItemList[${st1.index}].minSItemList[${st2.index}].minItemId" value="${sonId1.minItemId}" />
																			<input type="text"  name="standardItemList[${st1.index}].minSItemList[${st2.index}].minItemName" value="${sonId1.minItemName}" />
																		</td>
																		<td class="r">
																			<input type="text" name="standardItemList[${st1.index}].minSItemList[${st2.index}].scores" value="${sonId1.scores }" />
																		</td>
																		<td class="r">
																			<s:select list="#{'':'','<':'<','<=':'<=','>':'>','>=':'>='}" value="'${sonId1.minKey}'" name="standardItemList[${st1.index}].minSItemList[${st2.index}].minKey"  theme="simple" ></s:select><br />
																			<s:select list="#{'':'','<':'<','<=':'<=','>':'>','>=':'>='}" value="'${sonId1.minKey}'" name="standardItemList[${st1.index}].minSItemList[${st2.index}].maxKey"  theme="simple" ></s:select>
																		</td>
																		<td class="r">
																			<input style="width: 35px" type="text" value="${sonId1.minValue}"  name="standardItemList[${st1.index}].minSItemList[${st2.index}].minValue"  /><br>
																			<input style="width: 35px" type="text" value="${sonId1.maxValue}"  name="standardItemList[${st1.index}].minSItemList[${st2.index}].maxValue"/><br>
																		</td>
																		<td class="l">
																			<textarea rows="2" cols="20" name="standardItemList[${st1.index}].minSItemList[${st2.index}].methodNames" readonly="readonly" onclick="chooseMethod('${sonId1.minItemId}','${sonId1.methodIds}');">${sonId1.methodNames}</textarea>
																			<input type="tes" name="standardItemList[${st1.index}].minSItemList[${st2.index}].methodIds"  value="${sonId1.methodIds}" />
																		</td>
																		<td class="c">
																			<a href="javascript:;" onclick="delrow('${sonId1.minItemId}','1')"><s:text name="lab.code.del"/></a>
																		</td>
																	</tr>
																</s:iterator>
																
															</s:if>
														</s:iterator>	
													</s:if>
												<tr>
													<td class="r" colspan="6" style="padding-right: 20px;">
														<a href="#" onclick="chooseItem();"><s:text name="additem"/>...</a>
													</td>
												</tr>
												<tr>
													<td align="center" class="c" colspan="13"><jsp:include page="/jsp/include/page.jsp?actionparam=updateLabStandardItemMethod.action&formName=standardItemMethodFrom" flush="true" /></td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 结束 -->
									</div>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
	<script>
		
	</script>
</html>