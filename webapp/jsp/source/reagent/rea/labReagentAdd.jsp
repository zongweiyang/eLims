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
			
			
			function ajaxIsExistName(name,size){//验证编号重复
				var name=$('#name').val();
				var size=$('#size').val();
				if(value.length==0){
					return ;
				}
				$.ajax({
					url:'${basePath}reagent/labReagent/ajaxIsExistName.action',
					type:'POST',
					data:{'name':name,'size':size},
					dataType:'text',
					success:function(data){
						if(data=='1'){
						   validate.tip('<s:property value="getText('reanoexist')"/>',$('#functionId'));
							$(obj).val("");
							$(obj).select();
						}
					},
					error:function(){
					    validate.tip('<s:property value="getText('theme.net.fail')"/>',$('#functionId'));
					}
				});
			}
			
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
							   url:"<%=basePath %>/LabUploadServlet?delFlag=Y&fileId="+id,
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
					content:'url:'+'<%=basePath %>utils/chooseuser/showLabUser4Select.jsp',
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
		<form action="${basePath}reagent/labReagent/addLabReagent.action"
			method="post" name="form" enctype="multipart/form-data" id="form">
			<input type="hidden" name="labReagentVo.reagentTypeName"
				value="${labReagentVo.reagentTypeName}" />
			<input type="hidden" id="type" name="labReagentVo.reagentTypeId"
				value="${labReagentVo.reagentTypeId}" />
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
												<span>[<font color="blue">${labReagentVo.reagentTypeName}</font>]><s:text name="admin.add"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="reagent/labReagent/addLabReagent.action" value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()"
												style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
												<span><s:text name="reageinfo"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
												   <td>
														<label>
															<s:text name="reageid"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.code" id="code"
															valType="required,strLength" max="32" readonly="true"
															strLength-msg='<s:property value="getText('reacodenot32')"/>' msg='<s:property value="getText('reacodenotemoy')"/>' type="text"
															size="40" value="${labReagentVo.code}" />
													</td>
													<td>
														<label>
															<s:text name="rea.name"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.name" id="name"
															valType="required,strLength" max="128"
															strLength-msg='<s:property value="getText('reagnamenot128')"/>' msg='<s:property value="getText('reannamenotemp')"/>' type="text"
															size="40" value="${labReagentVo.name}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="rearegular"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.size"
															value="${labReagentVo.size}" id="size"
															valType="required,strLength" max="32"
															strLength-msg='<s:property value="getText('rearegunot32')"/>' msg='<s:property value="getText('rearegunot32')"/>' type="text"
															size="40" />
													</td>
												
													<td>
														<label>
															<s:text name="pure"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.purity"
															value="${labReagentVo.purity}" id="purity"
															valType="strLength" max="64" strLength-msg="纯度的长度不能超过64位"
															type="text" />
													</td>
													</tr>
												<tr>
													<td>
														<label>
															<s:text name="risk.level"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.codeList"
															name="labReagentVo.dangerSize" headerKey=""
															headerValue="" theme="simple" listKey="name"
															listValue="name"></s:select>
													</td>
											
													<td>
														<label>
															<s:text name="alarm.number"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeAmount"
															value="${labReagentVo.safeAmount}"
															valType="required,strLength" max="16"
															strLength-msg='<s:property value="getText('alarmlennot16')"/>' msg='<s:property value="getText('alarmnotem')"/>'
															id="releaseDate" size="40" type="text" />
													</td>
														</tr>
												<tr>
													<td>
														<label>
															<s:text name="std.stock"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.amount"
															value="${labReagentVo.amount}" valType="strLength"
															max="16" strLength-msg='<s:property value="getText('stocklennot16')"/>' id="amount"
															size="40" type="text" />
														<input name="labReagentVo.uuid"
															value="${labReagentVo.uuid}" id="uuid" size="40"
															type="hidden" />
													</td>
												
													<td>
														<label>
															<s:text name="save.office"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.labOrgVoList" theme="simple"
															name="labReagentVo.saveOrg" headerKey=""
															headerValue="" value="${labReagentVo.saveOrg}"
															id="orgId" listValue="name" listKey="id"></s:select>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="saver"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.saveUser" onclick="selectLabUser();"
															value="${labReagentVo.saveUser}" valType="strLength"
															max="32" strLength-msg='<s:property value="getText('savinglennotover')"/>' id="saveUser"
															size="40" type="text" />
													</td>

													<td>
														<label>
															<s:text name="valide.date"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeDate"
															value="${labReagentVo.safeDate}" valType="strLength"
															max="11" strLength-msg='<s:property value="getText('lennote11asfd')"/>' id="safeDate"
															size="40" type="text" />
														<s:text name="dayday"/>
													</td>
													</tr>
												<tr>
												
												<td>
														<label>
															<s:text name="supplier"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.supplierList" theme="simple"
															name="labReagentVo.unit" headerKey=""
															headerValue="" value="${labReagentVo.unit}"
															id="unit" listValue="name" listKey="id" cssStyle="width:280px;"></s:select>
													</td>
													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td colspan="">
														<textarea rows="3" cols="36" name="labReagentVo.remark"
															valType="strLength" max="128"
															strLength-msg='<s:property value="getText('remarknot128')"/>' id="remark">${labReagentVo.remark}</textarea>
													</td>
														</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="">
														<a id="BtnEdit" class="zPushBtn"
															href="javascript:void(0);"
															onclick="uploadFile('${labReagentVo.uuid}','lab-reagent');">
															<img height="20" width="20"
																src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
														<div id="upfiles"></div>
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
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			
		}
	</script>
</html>
