<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			var api = frameElement.api, W = api.opener, D = W.document;
			var index='${labItemVo.index}';
			function submitforform(){
				$('form').submit();
			}
			function ok(){
		 		var ids="";
		 		var names="";
		 		$('input[name="labItemVo.ids"]:checked').each(function(m){
		 			ids+=$(this).val()+"*"+$(this).parent().parent().find('select[id*="stand"]').val()+"*"+$(this).parent().parent().find('select[id*="method').val()+",";
		 			names+=$(this).attr('key')+",";
		 		});
		 		if(ids.length>1){
		 			ids=ids.substring(0,ids.length-1);
		 			names=names.substring(0,names.length-1);
		 		}
		 		$('#itemId'+index,D).val(ids);
		 		$('#itemName'+index,D).val(names);
		 		closeMe();
		 	}
		 	function closeMe(){
			  	api.close();
		 	}
		 	$(function(){
				var ids=$('#itemId'+index,D).val();
				var attIds = ids.split(',');
				for(var i=0;i<attIds.length;i++){
				  var idstr = attIds[i];
				  var idx = idstr.split('*');
				  if(idx.length==3){
				  	$('input[value="'+idx[0]+'"]').prop('checked',true);
				  	var stand=$('input[value="'+idx[0]+'"]').parent().parent().find('select[id*="stand"]');
				  	stand.val(idx[1]);
				  	methodOption(stand,idx[0],stand.attr('name'));
				  	$('input[value="'+idx[0]+'"]').parent().parent().find('select[id*="method"]').val(idx[2]);
				  }
				}
			})
			
		 	function onchangex(obj,index){
		 		if($(obj).val()==''){
		 			$(obj).parent().parent().find('input[id="ids'+index+'"]').prop('checked',false);
		 		}else{
		 			$(obj).parent().parent().find('input[id="ids'+index+'"]').prop('checked',true);
		 		}
		 	}
		 	function onchangex4Method(obj,index){
		 		if($(obj).val()==''){
		 			$(obj).parent().parent().find('input[id="ids'+index+'"]').prop('checked',false);
		 		}else{
		 			var stand=$(obj).parent().parent().find('select[id="stand'+index+'"]');
		 			if(stand.val()==''){
		 				stand.find('option').eq(1).attr('selected','selected');
		 			}
		 			$(obj).parent().parent().find('input[id="ids'+index+'"]').prop('checked',true);
		 		}
		 	}
		 	function onclickx(obj,index){
		 		if($(obj).prop('checked')==false){
		 			$(obj).parent().parent().find('select[id="stand'+index+'"]').val('');
		 			$(obj).parent().parent().find('select[id="method'+index+'"]').val('');
		 		}
		 	}
		 	function methodOption(obj,itemId,index){
		 		$.ajax({
					url:'<%=basePath%>/sample/labSampRegister/ajaxLabMethodList4Select.action',
					data:{'labItemVo.standId':$(obj).val(),'labItemVo.id':itemId},
					type:'post',
					dataType:'text',
					async: false,
					success:function (data){
						var datas=eval('('+data+')');
				 		$('#method'+index).html('');
				 		var optionStr='';
						for(var i=0;i<datas.length;i++){
							optionStr+='<option value="'+datas[i].id+'">'+datas[i].name+'</option>';
				  		}
				  		if(optionStr==''){
				  			optionStr+='<option value="">-请选择-</option>';
				  		}
				   	 	$('#method'+index).append(optionStr);
				   	 },
				   	 error:function (data){
				   	 	alert('<s:property value="getText('config.request.error')"/>');
				   	 }
				});
		 	}
		 	function ajax2MethodOption(obj,itemId,index){
		 		$.ajax({
					url:'<%=basePath%>/sample/labSampRegister/ajaxLabMethodList4Select.action',
					data:{'labItemVo.standId':$(obj).val(),'labItemVo.id':itemId},
					type:'post',
					dataType:'text',
					async: false,
					success:function (data){
						var datas=eval('('+data+')');
				 		$('#method'+index).html('');
				 		var optionStr='';
						for(var i=0;i<datas.length;i++){
							optionStr+='<option value="'+datas[i].id+'">'+datas[i].name+'</option>';
				  		}
				  		if(optionStr==''){
				  			optionStr+='<option value="">-请选择-</option>';
				  		}
				   	 	$('#method'+index).append(optionStr);
				   	 	onchangex(obj,index);
				   	 },
				   	 error:function (data){
				   	 	alert('<s:property value="getText('config.request.error')"/>');
				   	 }
				});
		 	}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable_pop" width="98%"
				cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox_pop">
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															</tr>
															<tr>
																<td class="c">
																	<s:text name="checking.item"/>：
																	<input type="text" value="${labItemVo.name}"
																		name="labItemVo.name" />
																</td>
																<td>
																	<l:a
																		uri="sample/labSampRegister/listLabSampRegister.action"
																		onclick="submitforform('${basePath}sample/labSampRegister/showLabItem4Select.action');return false;"
																		value="fun.query" />
																</td>
																<td>
																	<l:a
																		uri="sample/labSampRegister/listLabSampRegister.action"
																		onclick="ok();" value="page.confirm" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<tr>
												<th width="40">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th>
													<s:text name="checking.item"/>
												</th>
												<th>
													<s:text name="chk.standard"/>
												</th>
												<th>
													<s:text name="chk.method"/>
												</th>
												<th width="40">
													<input type="checkbox" id="allCheckBox" key="labItemVo.ids" />
												</th>
											</tr>
											<s:iterator value="#request.labItemList" status="st" id="ls">
												<tr>
													<td class="c">
														${st.index+1}
													</td>
													<td class="l">
														${name}
													</td>
													<td class="l">
														<s:select onchange="ajax2MethodOption(this,'${id}','${st.index}');" list="#ls.standardList" id="stand${st.index}" listKey="id" listValue="name" name="${st.index}" headerKey="" headerValue="" theme="simple" cssStyle="min-width:150px;"></s:select>
													</td>
													<td class="l">
														<s:select onchange="onchangex4Method(this,'${st.index}');" list="#ls.methodList" id="method${st.index}" listKey="id" listValue="name" name="${st.index}" headerKey="" headerValue="" theme="simple" cssStyle="min-width:150px;"></s:select>
													</td>
													<td class="c">
														<input type="checkbox" name="labItemVo.ids"
															id="ids${st.index}" value="${id}" key="${name}" onclick="onclickx(this,'${st.index}');"/>
													</td>
												</tr>
											</s:iterator>
										</table>
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
</html>
