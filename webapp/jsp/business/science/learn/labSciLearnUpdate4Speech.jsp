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
			title:'<s:property value="getText('selecdeprart')"/>',
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
			alert('<s:property value="getText('selectrooleto')"/>');
			return false;
		}
		$.dialog({
			id:'userFunction',
			content:'url:'+'<%=basePath%>science/labSciLearn/showLabUser4Select.action?labSciLearnVo.labOrgId='+orgId,
			title:'<s:property value="getText('selepeo')"/>',
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
			title:'<s:property value="getText('seleitemown')"/>',
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
			<input type="hidden" name="labSciLearnVo.type" value="2" id="type" />
			<input type="hidden" name="labSciLearnVo.id" value="${labSciLearnVo.id}" id="id" />
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
												<span><s:text name="lab.code.modify"/></span>
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
																			onclick="nextUri('science/labSciLearn/listLabSciLearn.action?labSciLearnVo.type=2');"
																			value="msg.back" />
																	</td>
																	<td>
																		<l:a uri="science/labSciLearn/updateLabSciLearn.action"
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
																<s:text name="lecture.name"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciLearnVo.name"
																value="${labSciLearnVo.name}" id="name" size="40"
																valType="required,strLength" max="64"
																strLength-msg="讲座名称长度不能超过64位" msg="讲座名称不能为空" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.type"/>：
															</label>
														</td>
														<td>
															<s:select cssStyle="width:273px;" list="#request.typeList" listKey="name"
															listValue="name" headerKey="" headerValue="--请选择--"
															name="labSciLearnVo.learnType" value="${labSciLearnVo.learnType}" id="learnType" theme="simple"></s:select>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.time"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciLearnVo.startTime"
																    id="startTime" value="${labSciLearnVo.startTime}" size="15" class="Wdate"
																   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'endTime\')}'});" valType="required,strLength" max="32"
																   strLength-msg="讲座时间不能超过32位" msg="讲座时间不能为空"/><s:text name="to"/>
																   <input type="text" name="labSciLearnVo.endTime"
																    id="endTime" value="${labSciLearnVo.endTime}" size="15" class="Wdate"
																   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});"  valType="required,strLength" max="32"
																   strLength-msg="讲座时间不能超过32位" msg="讲座时间不能为空" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="lecture.site"/>：
															</label>
														</td>
														<td>
															<s:select  cssStyle="width:273px;" list="#request.roomList" listKey="name"
															listValue="name" headerKey="" headerValue="--请选择--"
															name="labSciLearnVo.place" value="#{labSciLearnVo.place}" id="place" theme="simple"></s:select>
														</td>
													</tr>
													
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="teacher"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciLearnVo.speaker"
																value="${labSciLearnVo.speaker}" id="speaker" size="40"
																valType="required,strLength" max="32"
																strLength-msg="主讲人长度不能超过32位" msg="主讲人不能为空" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td>
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
														<td class="r" width="150">
															<label>
																<s:text name="host.office"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="36" name="labSciLearnVo.labOrgName" id="labOrgName"
																value="${labSciLearnVo.labOrgName}" size="40"
																valType="required,strLength" max="11"
																strLength-msg="主办科室不能超过64位" msg="主办科室不能为空"  onclick="getOrg();return false;" readonly="true"
																>${labSciLearnVo.labOrgName}</textarea>
																<input type="hidden" name="labSciLearnVo.labOrgId" id="labOrgId"
																value="${labSciLearnVo.labOrgId}"/>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="attend.people"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="36" name="labSciLearnVo.participant" onclick="getUser();return false;" readonly="true"
																id="participant" value="${labSciLearnVo.participant}" size="40"
																valType="required,strLength" max="128" msg="参加人员不能为空" strLength-msg="参加人员长度不能超过128位">${labSciLearnVo.participant}</textarea>
														<input type="hidden"  id="participantId" name="labSciLearnVo.participantId" value="${labSciLearnVo.participantId }"/>
														</td>
													</tr>
												<tr>
													<td class="r" width="150">
															<label>
																<s:text name="lecture.intr"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="36" name="labSciLearnVo.summary"
																valType="strLength" max="512"
																strLength-msg="讲座简介长度不能超过512位">${labSciLearnVo.summary}</textarea>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="teacher.intr"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="36" name="labSciLearnVo.speakerIntroduced"
																valType="strLength" max="512"
																strLength-msg="主讲人简介长度不能超过512位">${labSciLearnVo.speakerIntroduced}</textarea>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="36" name="labSciLearnVo.remark"
																valType="strLength" max="512"
																strLength-msg="备注长度不能超过512位">${labSciLearnVo.remark}</textarea>
														</td>
														<td class="r">
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="">
																<a id="BtnEdit" class="zPushBtn"
																	href="javascript:void(0);"
																	onclick="uploadFile('${labSciLearnVo.id}','lab-sciLearn');">
																	<img height="20" width="20"
																		src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
																<s:if test="${fn:length(uplodeList)>0}">
															<s:iterator status="st2" value="#request.uplodeList" id="">
																<span> <a href="${path }" id="fileId">${name
																		} </a> <a href="javascript:;" id="fileIcon"
																	onclick="deleteUploadFile(this,'${id}')"><img
																			src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
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
