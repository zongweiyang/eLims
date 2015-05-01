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
	function getUser(num){
		$.dialog({
			id:'userFunction',
			content:'url:'+'<%=basePath%>science/labSciAchievement/showLabUser4Select.action?treeNid='+num,
			title:'<s:property value="getText('selepeo')"/>',
			opacity:0.4,
			width:600, 
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
			<input type="hidden" name="labSciAchievementVo.type" value="PAPER" id="type" />
				<input type="hidden" name="labSciAchievementVo.id" value="${labSciAchievementVo.id}"
				id="type" />
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
																		<l:a uri="back" value="msg.back"/> 
																	</td>
																	<td>
																		<l:a
																			uri="science/labSciAchievement/updateLabSciAchievement.action"
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
																<s:text name="thesis.type"/>：
															</label>
														</td>
														<td>
															<s:select list="#request.typeList" listKey="name"
																cssStyle="width:273px;" listValue="name" headerKey=""
																headerValue=""
																name="labSciAchievementVo.achievementType"
																id="achievementType" theme="simple"></s:select>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="thesis.name"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciAchievementVo.name"
																value="${labSciAchievementVo.name}" id="name" size="40"
																valType="required,strLength" max="64"
																strLength-msg="论文名称长度不能超过64位" msg="论文名称不能为空" />
														</td>

													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="first.writer"/>：
															</label>
														</td>
														<td>
															<input type="text"
																name="labSciAchievementVo.fristAuthorName"
																id="fristAuthorName" size="40"
																value="${labSciAchievementVo.fristAuthorName}"
																valType="required,strLength" max="32"
																strLength-msg="第一作者长度不能超过32位" msg="第一作者不能为空"
																onclick="getUser('1');return false;" readonly="true"/>
															<input type="hidden"
																name="labSciAchievementVo.fristAuthorId"
																id="fristAuthorId"
																value="${labSciAchievementVo.fristAuthorId}" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="other.writer"/>：
															</label>
														</td>
														<td>
															<input type="text"
																name="labSciAchievementVo.otherAuthorName"
																id="otherAuthorName" size="40"
																value="${labSciAchievementVo.otherAuthorName}"
																valType="strLength" max="128"
																strLength-msg="其他作者长度不能超过128位"
																onclick="getUser('2');return false;" readonly="true"/>
															<input type="hidden"
																name="labSciAchievementVo.otherAuthorId"
																id="otherAuthorId"
																value="${labSciAchievementVo.otherAuthorId}" />
														</td>
													</tr>

													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="publish.date"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciAchievementVo.date"
																id="date" value="${labSciAchievementVo.date}" size="40"
																size="15" class="Wdate" valType="required,strLength"
																max="32" strLength-msg="发布日期长度不能超过32位" msg="发布日期不能为空"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="publish.journal"/>：
															</label>
														</td>
														<td>
															<s:select list="#request.qkList" listKey="name"
																cssStyle="width:273px;" listValue="name" headerKey=""
																headerValue=""
																name="labSciAchievementVo.organization"
																value="#{labSciAchievementVo.organization}"
																id="organization" theme="simple"></s:select>
														</td>
													</tr>

													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td>
															<input type="text"
																name="labSciAchievementVo.labSciScienceName"
																id="labSciScienceName" size="40"
																value="${labSciAchievementVo.labSciScienceName}" readonly="true" onclick="selectLabSciScience();return false;"/>
															<input type="hidden"
																name="labSciAchievementVo.labSciScienceId"
																id="labSciScienceId"
																value="${labSciAchievementVo.labSciScienceId}" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="thesis.word.number"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciAchievementVo.wordNumber"
																value="${labSciAchievementVo.wordNumber}"
																id="wordNumber" size="40" valType="number,strLength"
																max="11" strLength-msg="论文字数的长度不能超过11位"
																number-msg="论文字数必须为数字" />
														</td>

													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="issn.no"/>：
															</label>
														</td>
														<td>
															<input type="text" size="40"
																name="labSciAchievementVo.issnNo" id="issnNo"
																valType="strLength" max="32"
																strLength-msg="ISSN号的长度不能超过32位"
																value="${labSciAchievementVo.issnNo}" />
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="key.word"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labSciAchievementVo.keyWords"
																value="${labSciAchievementVo.keyWords}" id="keyWords"
																size="40" valType="strLength" max="32"
																strLength-msg="关键字的长度不能超过32位" />
														</td>

													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="study.lec"/>：
															</label>
														</td>
														<td colspan="3">
															<s:select list="#request.searchList" listKey="name"
																cssStyle="width:273px;" listValue="name" headerKey=""
																headerValue=""
																name="labSciAchievementVo.researchContents"
																value="#{labSciAchievementVo.researchContents}"
																id="researchContents" theme="simple"></s:select>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="thesis.preface"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40"
																name="labSciAchievementVo.tabloid" valType="strLength"
																max="512" strLength-msg="论文摘要长度不能超过512位">${labSciAchievementVo.tabloid}</textarea>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="study.content"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40"
																name="labSciAchievementVo.contents"
																valType="required,strLength" max="512"
																strLength-msg="研究内容长度不能超过512位" msg="研究内容不能为空"
																id="contents" value="${labSciAchievementVo.contents}"
																size="15">${labSciAchievementVo.contents}</textarea>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea rows="3" cols="40"
																name="labSciAchievementVo.remark" valType="strLength"
																max="512" strLength-msg="备注长度不能超过512位">${labSciAchievementVo.remark}</textarea>
														</td>
														<td class="r">
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="">
															<a id="BtnEdit" class="zPushBtn"
																href="javascript:void(0);"
																onclick="uploadFile('${labSciAchievementVo.id}','lab-sciAchievement');">
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
