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
		<form action="${basePath}reagent/labReagent/updateLabReagent.action" method="post" name="form" enctype="multipart/form-data" id="form">
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
												<span>[<font color="blue">${labReagentVo.reagentTypeName}</font>]><s:text name="lab.code.modify"/></span>
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
																	<l:a uri="reagent/labReagent/updateLabReagent.action" img="/img/add.gif" value="lab.code.save" />
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
														<input name="labReagentVo.code" id="code" valType="required,strLength" max="32" readonly="true" strLength-msg='<s:property value="getText('reagcodelennot32')"/>' msg='<s:property value="getText('reacodenotemoy')"/>' type="text" size="40" value="${labReagentVo.code}" />
													</td>
													<td>
														<label>
															<s:text name="rea.name"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.name" id="name" valType="required,strLength" max="128" strLength-msg='<s:property value="getText('reagnamenot128')"/>' msg='<s:property value="getText('reannamenotemp')"/>' type="text" size="40" value="${labReagentVo.name}" />
														<input name="labReagentVo.id" id="id" type="hidden" size="40" value="${labReagentVo.id}" />
														<input name="labReagentVo.isDel" id="isDel" type="hidden" size="40" value="${labReagentVo.isDel}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="rearegular"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.size" id="size" valType="required,strLength" max="32" strLength-msg='<s:property value="getText('rearegunot32')"/>' msg='<s:property value="getText('rearegunot32')"/>' type="text" size="40" value="${labReagentVo.size}" />
													</td>

													<td>
														<label>
															<s:text name="pure"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.purity" valType="strLength" max="32" strLength-msg='<s:property value="getText('purelengnot32')"/>' id="purity" type="text" size="40" value="${labReagentVo.purity}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="risk.level"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.codeList" name="labReagentVo.dangerSize" headerKey="" headerValue="" theme="simple" listKey="name" listValue="name"></s:select>
													</td>

													<td>
														<label>
															<s:text name="alarm.number"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeAmount" value="${labReagentVo.safeAmount}" valType="required,strLength" max="16" strLength-msg='<s:property value="getText('alarmlennot16')"/>' msg='<s:property value="getText('alarmnotem')"/>' id="releaseDate" size="40" type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="std.stock"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.amount" value="${labReagentVo.amount}" valType="strLength" max="16" strLength-msg='<s:property value="getText('stocklennot16')"/>' id="amount" size="40" type="text" />
													</td>

													<td>
														<label>
															<s:text name="save.office"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.labOrgVoList" theme="simple" name="labReagentVo.saveOrg" headerKey="" headerValue="" value="${labReagentVo.saveOrg}" id="orgId" listValue="name" listKey="id"></s:select>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="saver"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.saveUser" valType="strLength" max="32" strLength-msg='<s:property value="getText('savinglennotover')"/>' onclick="selectLabUser();" value="${labReagentVo.saveUser}" id="saveUser" size="40" type="text" />
													</td>

													<td>
														<label>
															<s:text name="valide.date"/>：
														</label>
													</td>
													<td>
														<input name="labReagentVo.safeDate" valType="strLength" max="11" strLength-msg='<s:property value="getText('lennote11asfd')"/>' value="${labReagentVo.safeDate}" id="safeDate" size="40" type="text" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="supplier"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.supplierList" theme="simple" name="labReagentVo.unit" headerKey="" headerValue="" value="%{labReagentVo.unit}" id="unit" listValue="name" listKey="id" cssStyle="width:280px;"></s:select>
													</td>

													<td>
														<label>
															<s:text name="remark"/>：
														</label>
													</td>
													<td colspan="">
														<textarea rows="3" cols="36" name="labReagentVo.remark" valType="strLength" max="128" strLength-msg='<s:property value="getText('remarknot128')"/>' id="remark">${labReagentVo.remark}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="">
														<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labReagentVo.id}','lab-reagent');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>

														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span> <a href="${path }" id="fileId">${name } </a> <a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id}')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
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
	<script>
		var num=$('#indexTable tr').length;
	</script>
</html>
