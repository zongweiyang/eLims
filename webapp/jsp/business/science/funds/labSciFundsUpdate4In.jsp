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
	<body>
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciFundsVo.type" value="0"
								id="type" />
								<input type="hidden" name="labSciFundsVo.oldMoney" value="${labSciFundsVo.oldMoney}"
								id=oldMoney />
							<input type="hidden" name="labSciFundsVo.labSciScienceId"
								value="${labSciFundsVo.labSciScienceId}" id="labSciScienceId" />
								<input type="hidden" name="labSciFundsVo.id"
								value="${labSciFundsVo.id}" id="id" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
																<l:a uri="science/labSciFunds/listLabScience.action"
																		onclick="nextUri('science/labSciFunds/listLabSciFunds.action?labSciFundsVo.labSciScienceId=${labSciFundsVo.labSciScienceId}&labSciFundsVo.type=0');" value="msg.back" />
														</td>
														<td>
															<l:a uri="science/labSciFunds/updateLabSciFunds.action"
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
										<span><s:text name="charge.in.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="fee.source"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSciFundsVo.user"
													value="${labSciFundsVo.user}" id="user" size="40"
													valType="required,strLength" max="32"
													strLength-msg="经费来源长度不能超过32位" msg="经费来源不能为空" />
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="charge.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSciFundsVo.name"
													value="${labSciFundsVo.name}" id="name" size="40"
													valType="required,strLength" max="64"
													strLength-msg="经费名称长度不能超过64位" msg="经费名称不能为空" />
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="fee.amount"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSciFundsVo.money" id="money"
													value="${labSciFundsVo.money}" size="40"
													valType="required,strLength,number" max="11"
													strLength-msg="经费金额长度不能超过11位" msg="经费金额不能为空"
													number-msg="经费金额必须为数字" /><s:text name="thousand.yuan"/>
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="come.acc.time"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labSciFundsVo.useDate" id="useDate"
													value="${labSciFundsVo.useDate}" size="40"
													valType="strLength" max="30" strLength-msg="到账时间长度不能超过30位 "  class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"/>
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td>
												<textarea rows="2" cols="35" name="labSciFundsVo.remark"
													valType="strLength" max="512" strLength-msg='<s:property value="getText('remakrnot512')"/>'>${labSciFundsVo.remark}</textarea>
											</td>
											<td class="r" width="150">
																<label>
																	<s:text name="upload.attachment"/>：
																</label>
															</td>
															<td colspan="">
																<a id="BtnEdit" class="zPushBtn"
																	href="javascript:void(0);"
																	onclick="uploadFile('${labSciFundsVo.id}','lab-scifunds');">
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
