<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<script language=javascript> 
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
   	
   	function getOrg(){
		$.dialog({
			id:'parentFunction',
			content:'url:'+'<%=basePath%>science/labSciLearn/showLabOrg4Select.action?labOrgVo.id='+$("#labOrgId").val(),
			title:'选择<s:text name="msg.depart"/>：',
			opacity:0.4,
			width:500, 
			height:300,
			lock:true,
			max:false,
			min:false
		 });
	}
	function getUser(){
		var orgId=$('#labOrgId').val();
		if(orgId.length == 0){
			alert("请选择参加科室!");
			return false;
		}
		$.dialog({
			id:'userFunction',
			content:'url:'+'<%=basePath%>science/labSciLearn/showLabUser4Select.action?labSciLearnVo.labOrgId='+orgId,
			title:'选择人员：',
			opacity:0.4,
			width:700, 
			height:400,
			lock:true,
			max:false,
			min:false
		 });
	}
	function selectLabSciScience(){
		$.dialog({
			id:'LabSciScienceFunction',
			content:'url:'+'<%=basePath%>science/labScience/showLabSciScience4Select.action?treeNid=1',
			title:'选择所属的项目：',
			opacity:0.4,
			width:1000, 
			height:400,
			lock:true,
			max:false,
			min:false
		 });
	}
			</script>
		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="studentForm">
			<input type="hidden" name="labSciLearnVo.type" value="1" id="type" />
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
												<span><s:text name="admin.add"/></span>
											</h2>
										</div>
										<div class="TabTable">
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd"
															style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		<l:a uri="science/labSciLearn/listLabScience.action"
																			onclick="nextUri('science/labSciLearn/listLabSciLearn.action?labSciLearnVo.type=1');"
																			value="msg.back" />
																	</td>
																	<td>
																		<l:a uri="science/labSciLearn/addLabSciLearn.action"
																			value="lab.code.save" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span>${funName}<s:text name="information"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="conference.name"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciLearnVo.name"
																value="${labSciLearnVo.name}" id="name" size="40"
																valType="required,strLength" max="64"
																strLength-msg="会议名称长度不能超过64位" msg="会议名称不能为空" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="conference.type"/>：
															</label>
														</td>
														<td>
															<s:select list="#request.typeList" listKey="name" cssStyle="width:273px"
															listValue="name" headerKey="" headerValue="--请选择--"
															name="labSciLearnVo.learnType" value="${labSciLearnVo.learnType}" id="learnType" theme="simple"></s:select>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="conference.time"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciLearnVo.startTime"
														    id="startTime" value="${labSciLearnVo.startTime}" size="15" class="Wdate"
														  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});" valType="required,strLength" max="32"
														   strLength-msg="召开时间不能超过32位" msg="召开时间不能为空"/><s:text name="to"/>
														   <input type="text" name="labSciLearnVo.endTime"
														    id="endTime" value="${labSciLearnVo.endTime}" size="15" class="Wdate"
														   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});"  />
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="conference.site"/>：
															</label>
														</td>
														<td>
															<s:select list="#request.roomList" listKey="name" cssStyle="width:273px"
															listValue="name" headerKey="" headerValue="--请选择--"
															name="labSciLearnVo.place" value="#{labSciLearnVo.place}" id="place" theme="simple"></s:select>
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td colspan="3">
															<input type="text" name="labSciLearnVo.labSciScienceName"
																value="${labSciLearnVo.labSciScienceName}" id="labSciScienceName" size="40"
																onclick="selectLabSciScience();return false;"
															/>
															<input type="hidden"
																name="labSciLearnVo.labSciScienceId"
																id="labSciScienceId"
																value="${labSciLearnVo.labSciScienceId}" />
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="attend.office"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.labOrgName" id="labOrgName"
																value="${labSciLearnVo.labOrgName}" size="40"
																valType="required,strLength" max="11"
																strLength-msg="参加科室不能超过64位" msg="参加科室不能为空" onclick="getOrg();return false;"
																readonly="true">${labSciLearnVo.labOrgName}</textarea>
																<input type="hidden" name="labSciLearnVo.labOrgId" id="labOrgId"
																value="${labSciLearnVo.labOrgId}"/>
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="attend.people"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.participant" readonly="true" onclick="getUser();return false;"
																id="participant" value="${labSciLearnVo.participant}" size="40"
																valType="required,strLength" max="128" msg="参加人员不能为空" strLength-msg="参加人员长度不能超过128位">${labSciLearnVo.participant}</textarea>
															<input type="hidden"  id="participantId" name="labSciLearnVo.participantId" value="${labSciLearnVo.participantId }"/>
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="conference.jianjie"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.summary"
																valType="strLength" max="512"
																strLength-msg="会议简介长度不能超过512位">${labSciLearnVo.summary}</textarea>
														</td>
														<td  class="r" width="150" >
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40" name="labSciLearnVo.remark"
																valType="strLength" max="512"
																strLength-msg="备注长度不能超过512位">${labSciLearnVo.remark}</textarea>
														</td>
													</tr>
													<tr>
														<td  class="r" width="150" >
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="3">
															<a id="BtnEdit" class="zPushBtn"
																href="javascript:void(0);"
																onclick="uploadFile('${labSciLearnVo.uuid}','lab-sciLearn');">
																<img height="20" width="20"
																	src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
															<div id="upfiles"></div>
														</td>
													</tr>
												</table>
											</div>
										</div>
										</form>
									</div>
								<td class="MRimg">

								</td>
							</tr>
							<tr>
								<td class="FLimg"></td>
								<td class="FMimg"></td>
								<td class="FRimg"></td>
							</tr>
						</table>
						<div class="clear"></div>
	</body>
</html>
