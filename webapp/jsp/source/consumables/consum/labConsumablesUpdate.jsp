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
			function uploadFile(busId,busType){
	   fileTypes = '*.*;';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType;
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('msg.add.attachment')"/>',
			opacity:0.4,
			width:300, 
			height:300,
			lock:true,
			max:false,
			resize:true
		 });
	}
	function deleteUploadFile(obj,id){
       if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
	       	$.ajax({
				   type: "POST",
				   url:"<%=basePath%>/LabUploadServlet?delFlag=Y&fileId="+id,
				   data:"",
				   async: false,
				   success: function(data){
					   if(data==true||data=="true")
					   {
						   alert('<s:property value="getText('msg.del.success')"/>');
						   $(obj).parent().remove();
					   }
				   }
			});
       }
   	}
   		var config = {
			itmes: [],
			display: true,
			callback:callback,
			init:""
		};
		var userWin = null;
		function selectLabUser(){
			if (userWin != null) {
				userWin.show();
			} else {
				userWin = $.dialog({
					id:'id',
					content:'url:'+'<%=basePath%>utils/chooseuser/showLabUser4Select.jsp',
					title:'<s:property value="getText('selected.people')"/>',
					opacity:0.4,
					width:840,
					lock:true,
					max:false,
					min:false,
					close:function(){
						this.hide();
						return false;
					}
				 });
			}
			$.dialog.data("config",config);
		}
		function callback(data) {
			$("#saveUser").val(data.names);
		}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="${basePath}consumables/labConsumables/updateLabConsumables.action" method="post" name="form" enctype="multipart/form-data" id="form">
			<input type="hidden" name="labConsumablesVo.consumablesTypeName" value="${labConsumablesVo.consumablesTypeName}" />
			<input type="hidden" name="labConsumablesVo.consumablesTypeId" value="${labConsumablesVo.consumablesTypeId}" />
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
												<span>[<font color="blue">${labConsumablesVo.consumablesTypeName}</font>]><s:text name="lab.code.modify"/></span>
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
																	<l:a uri="consumables/labConsumables/updateLabConsumables.action" img="/img/add.gif" value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
												<span><s:text name="coninfo"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="conno"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.code" value="${labConsumablesVo.code }" id="code" valType="required,strLength" max="32" readonly="true" strLength-msg="耗材编码不能超过32位" msg="耗材编码不能为空" type="text" size="40" />
													</td>
													<td>
														<label>
															<s:text name="conname"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.name" id="name" valType="required,strLength" max="128" strLength-msg="耗材名称长度不能超过128位" msg="耗材名称不能为空" type="text" size="40" value="${labConsumablesVo.name}" />
														<input name="labConsumablesVo.id" id="id" type="hidden" size="40" value="${labConsumablesVo.id}" />
														<input name="labConsumablesVo.isDel" id="isDel" type="hidden" size="40" value="${labConsumablesVo.isDel}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="conregular"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.size" id="size" valType="required,strLength" max="32" strLength-msg="耗材规格长度不能超过32位" msg="耗材规格不能为空" type="text" size="40" value="${labConsumablesVo.size}" />
													</td>
													<td>
														<label>
															<s:text name="risk.level"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.codeList" name="labConsumablesVo.dangerSize" headerKey="" headerValue="" theme="simple" listKey="name" listValue="name"></s:select>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="alarm.number"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.safeAmount" value="${labConsumablesVo.safeAmount}" valType="required,strLength" max="16" strLength-msg="警戒数量长度不能超过16位" msg="警戒数量不能为空" id="releaseDate" size="40" type="text" />
													</td>
													<td>
														<label>
															<s:text name="std.stock"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.amount" value="${labConsumablesVo.amount}" valType="strLength" max="16" strLength-msg="库存长度不能超过16位" id="amount" size="40" type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="save.office"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.labOrgVoList" theme="simple" name="labConsumablesVo.saveOrg" headerKey="" headerValue="" value="${labConsumablesVo.saveOrg}" id="orgId" listValue="name" listKey="id"></s:select>
													</td>
													<td>
														<label>
															<s:text name="saver"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.saveUser" onclick="selectLabUser();" valType="strLength" max="32" strLength-msg="保管人长度不能超过32位" value="${labConsumablesVo.saveUser}" id="saveUser" size="40" type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
																	<s:text name="valide.date"/>：
														</label>
													</td>
													<td>
														<input name="labConsumablesVo.safeDate" valType="strLength" max="11" strLength-msg="有限期长度不能超过11位" value="${labConsumablesVo.safeDate}" id="safeDate" size="40" type="text" />
													</td>

													<td>
														<label>
															<s:text name="supplier"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.supplierList" theme="simple" name="labConsumablesVo.unit" headerKey="" headerValue="" value="%{labConsumablesVo.unit}" id="unit" listValue="name" listKey="id" cssStyle="width:280px;"></s:select>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td>
														<textarea rows="3" cols="36" name="labConsumablesVo.remark" valType="strLength" max="128" strLength-msg="备注长度不能超过128位" id="remark">${labConsumablesVo.remark}</textarea>
													</td>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="">
														<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labConsumablesVo.id}','lab-consumables');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span> <a href="${path }" id="fileId">${name } </a> <a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id}')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>
															<div id="upfiles"></div>
														</s:if>
													</td>
												</tr>
											</table>
										</div>

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
		var num=$('#indexTable tr').length;
	</script>
</html>
