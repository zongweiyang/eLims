<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet"
			type="text/css" id="theme" />
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>
		<script type="text/javascript" language="javascript">
	function goBack(){
	history.go(-1);
	location.reload();
	}
	function submitValue()	{
		var df = document.labDocForm;
		df.action="<%=basePath%>doc/labDoc/updateLabDocFile.action";
 		df.submit();
	} 
							
	function deleteThis(id){
		if(!confirm("确认删除文件吗？删除后不可恢复")) return false;
		$('#fileId').html('');
		$('#fileIcon').html('');

    } 
	
	function uploadFile(busId,busType){
	   var fileTypes = '*.*;';
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
			resize:false
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
</script>
	</head>

	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName }：
								<span>${path}&nbsp;&nbsp;[<font color="red">${labDocVo.fileName}-修改</font>]</span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labDocForm"
							id="form">
							<input type="hidden" name="labDocVo.id" value="${labDocVo.id }" />
							<input type="hidden" name="labDocVo.pid" value="${labDocVo.pid}" />
							<div class="TabTable"
								style="padding-top: 0; margin: 0; background: none;">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
														<l:a  uri="doc/labDoc/listLabDoc.action?labDocVo.id=${labDocVo.pid}" onclick="nextUrl('${basePath }doc/labDoc/listLabDoc.action?labDocVo.id=${labDocVo.pid}');"  value="msg.back"/>
														</td>
														<td>
														<l:a  uri="doc/labDoc/addLabDocFile.action" onclick="submitValue();return false;" value="lab.code.save"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="docinfo"/> </span>
									</div>
									<div style="margin: 10px 50px 10px 50px;">
										<table class="FormtableCon">
											<tr>
												<td class="c">
													<label>
														<s:text name="file.name"/>
													</label>
												</td>
												<td>
													<input name="labDocVo.fileName" type="text" size="20"
													alType="required,strLength"  max="64" msg="文件名不能为空"  strLength-msg="文件名长度不能超过64位"
														 value="${labDocVo.fileName }" />
												</td>
											</tr>
											<tr>
												<td class="c">
													<label>
														上传文件
													</label>
												</td>
												<td>
													<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="uploadFile('${labDocVo.id}','doc');" height="20" width="20"><img height="20" width="20" src="<%=basePath%>img/filesave.gif" /> <b>添加附件</b> </a>
													<div id="upfiles">
														<s:iterator value="#request.loadList" status="st">
															<span id="${id}">${name }<a href="javascript:;" onclick="deleteUploadFile(this,'${id }')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>&nbsp;&nbsp;&nbsp;
														</s:iterator>
													</div>
												</td>
											</tr>
											<tr>
												<td class="c" style="height: 80">
													<label>
														<s:text name="remark"/>
													</label>
												</td>
												<td style="border: 0">
													<textarea cols="50" rows="4" valType="strLength"  max="512"  strLength-msg="备注长度不能超过512位" name="labDocVo.remark">${labDocVo.remark }</textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
