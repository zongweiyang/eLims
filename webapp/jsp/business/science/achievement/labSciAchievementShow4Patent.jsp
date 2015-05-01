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
			<input type="hidden" name="labSciAchievementVo.type" value="PATENT"
				id="type" />
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
												<span><s:text name="look.check"/></span>
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
																		<l:a
																			uri="science/labSciAchievement/listLabScience.action"
																			onclick="nextUri('science/labSciAchievement/listLabSciAchievement.action?labSciAchievementVo.type=PATENT');"
																			value="msg.back" />
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
																<s:text name="pat.type"/>：
															</label>
														</td>
														<td>
															${labSciAchievementVo.achievementType}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="pat.name"/>：
															</label>
														</td>
														<td>
															${labSciAchievementVo.name}
														</td>

													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="pat.author"/>：
															</label>
														</td>
														
														<td>
															${labSciAchievementVo.otherAuthorName}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="publish.date"/>：
															</label>
														</td>
														<td>
															${labSciAchievementVo.date}
														</td>
														
													</tr>

													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="own.item"/>：
															</label>
														</td>
														<td>
															${labSciAchievementVo.labSciScienceName}
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="pat.no"/>：
															</label>
														</td>
														<td>
															${labSciAchievementVo.achievementNum}
														</td>

													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="study.lec"/>：
															</label>
														</td>
														<td colspan="3">
															${labSciAchievementVo.researchContents}
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="study.content"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="40"
																name="labSciAchievementVo.contents"
																valType="required,strLength" max="512"
																strLength-msg="研究内容长度不能超过512位" msg="研究内容不能为空"
																id="contents" value="${labSciAchievementVo.contents}"
																size="15">${labSciAchievementVo.contents}</textarea>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="pat.preface"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="40"
																name="labSciAchievementVo.tabloid" valType="strLength"
																max="512" strLength-msg="专利摘要长度不能超过512位">${labSciAchievementVo.tabloid}</textarea>
														</td>
													</tr>
													<tr>
														<td class="r" width="150">
															<label>
																<s:text name="lab.remark"/>：
															</label>
														</td>
														<td>
															<textarea readonly="readonly" rows="3" cols="40"
																name="labSciAchievementVo.remark" valType="strLength"
																max="512" strLength-msg="备注长度不能超过512位">${labSciAchievementVo.remark}</textarea>
														</td>
														<td class="r" width="150">
															<label>
																<s:text name="upload.attachment"/>：
															</label>
														</td>
														<td colspan="">
															<s:if test="${fn:length(uplodeList)>0}">
															<s:iterator status="st2" value="#request.uplodeList" id="">
																<span> <a href="${path }" id="fileId">${name
																		} </a></span>
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
