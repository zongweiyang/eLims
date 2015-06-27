<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}

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

	<script> 
		  	function submitvalue(actionstr){
					$('form').attr('action','<%=basePath%>'+actionstr);
					$('form').submit();
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
   	function goBackAction(url){
   		window.location.href='<%=basePath%>'+url;
   	}
   	function selectType(obj){
   		obj = $(obj).val();
   		$('#type').val(obj);
   		if(obj == '科研实验'){
   			$('#TD1').show();
   			$('#TD2').show();
   			$('#TD3').removeAttr('colspan');
   		}else{
   			$('#TD1').hide();
   			$('#TD2').hide();
   			$('#TD3').attr('colspan','3');
   		}
   	}
	</script>
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
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
						<input type="hidden" name="labSciProcessVo.labSciScienceId" value="${labSciProcessVo.labSciScienceId }" id="labSciScienceId"/>
						<input type="hidden" name="labSciProcessVo.uuid" value="${labSciProcessVo.uuid }" id="uuid"/>
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="science/labSciProcess/listLabScience4Process.action" onclick="goBackAction('science/labSciProcess/listLabScience4Process.action');" value="msg.back" />
														</td>
														<td>
															<l:a uri="science/labSciProcess/addLabSciProcess.action?labSciProcessVo.auditResult=1" value="lab.code.save" />
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
												<td class="r" width="150"><label><s:text name="namelarge"/>：：</label></td>
												<td><input size="40" type="text" name="labSciProcessVo.name" id="name"  valType="required" msg='<s:property value="getText('lab.msg.name')"/>'  /></td>
												<td class="r" width="150"><label>录&nbsp;&nbsp;入&nbsp;&nbsp;人：</label></td>
												<td><input size="40" type="text" name="labSciProcessVo.writeUser" id="writeUser" value="${labSciProcessVo.writeUser }"  valType="required" msg="填写人不能为空" /></td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="start.time"/>：</label></td>
												<td><input size="40" type="text" class="Wdate"  valType="required" msg="开始时间不能为空" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});" name="labSciProcessVo.startTime" id="startTime"  /></td>
												<td class="r" width="150"><label><s:text name="end.time"/>：</label></td>
												<td><input size="40" type="text" class="Wdate"  valType="required" msg="结束时间不能为空" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});" name="labSciProcessVo.endTime" id="endTime"  /></td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="type.large"/>：：</label></td>
												<td id="TD3"><s:select cssStyle="width:231px;" list="#request.labCodeVoList"  theme="simple" listKey="name" listValue="name" id="types" onchange="selectType(this);"></s:select> <input valType="required" msg="类型不能为空" type="hidden" name="labSciProcessVo.type" value="${request.labCodeVoList[0].name }" id="type"/></td>
												<td id="TD1" class="r" width="150"><label><s:text name="sam.number"/>：</label></td>
												<td id="TD2"><input size="40" type="text" name="labSciProcessVo.sampRegisterNum" id="sampRegisterNum" value="${labSciProcessVo.sampRegisterNum }" />&nbsp;&nbsp;<l:a href="#" uri="science/labSciProcess/addLabSciProcess.action?labSciProcessVo.auditResult=0" value="generateconten" /></td>
											</tr>
											<!-- <tr>
												<td class="r" width="150"><label>原&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件：</label></td>
												<td><input size="40" type="text" name="labSciProcessVo.original" id="original"  /></td>
												<td class="r" width="150"><label>复&nbsp;&nbsp;印&nbsp;&nbsp;件：</label></td>
												<td><input size="40" type="text" name="labSciProcessVo.hardCopy" id="hardCopy" /></td>
											</tr> -->
											<tr>
												<td class="r" width="150"><label><s:text name="contentdesd"/>：</label></td>
												<td colspan="3"><textarea  name="labSciProcessVo.contents" cols="40" rows="3" id="contents" ></textarea></td>
											</tr>
											<tr>
												<td class="r" width="150"><label><s:text name="lab.remark"/>：</label></td>
												<td colspan="3"><textarea  name="labSciProcessVo.remark" cols="40" rows="3" id="remark" ></textarea></td>
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
														onclick="uploadFile('${labSciProcessVo.uuid}','lab-sciProcess');">
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
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
		<script>
			$(function(){
				$('#TD1').hide();
  				$('#TD2').hide();
  				$('#TD3').attr('colspan','3');
			})
		</script>
	</body>
</html>
