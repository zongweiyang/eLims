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
.myworkingboxttable th{
background: #F5F5F5;
color: #000000;
border-top: #C0C0C0 1px solid;
border-left: #C0C0C0 1px solid;
border-right: #C0C0C0 1px solid;
}
.editType{
	visibility:hidden;
}
</style>
		<script type="text/javascript" language="javascript">
function ajax4AddFolder(pid){
		var url  = '${basePath}doc/labDoc/showLabDocFolder4Select.action?labDocVo.pid='+pid;
		$.dialog({
			pid:'pid',
			content:'url:'+url,
			title:'<s:property value="getText('folderadded')"/>',
			opacity:0.4,
			width:600,
			height:400,
			async:false,
			lock:true,
			max:false,
			min:false
		 });
	}
	
	
	function goBack(){
	history.go(-1);
	location.reload();
	}
	function submitValue(actioner){
			var df = document.labDocForm;	
		 	df.action=actioner;
		  	df.submit();
			} 
	function flushThisPage(){
		window.location.reload();
	}
	function deleteOne(id,pid){
		 $.ajax({
			url: "<%=basePath%>doc/labDoc/ajaxCheckLabDocApply.action",
			data: {"ID":id},
			type: "POST",
			dataType: "text",
			cache: false,
			success: function(data) {
				if(data=='1'){
					if(confirm("您要删除的文件已经被其他人申请,删除该文件后所有申请信息也被删除！")){
					window.location.href='<%=basePath%>doc/labDoc/deleteLabDocFile.action?labDocVo.id='+id+'&labDocVo.pid='+pid;
					}
				}else{
				 if(confirm('<s:property value="getText('lab.confirm.delete')"/>'))
				{
					window.location.href='<%=basePath%>doc/labDoc/deleteLabDocFile.action?labDocVo.id='+id+'&labDocVo.pid='+pid;
				}	
				}
				
			}
		});
              
	}
		
	function ajax4ShowFile(id){
	var url  = '${basePath}doc/labDoc/showLabDocFile4Select.action?labDocVo.id='+id;
	$.dialog({
		pid:'pid',
		content:'url:'+url,
		title:'<s:property value="getText('filedestails')"/>',
		opacity:0.4,
		width:500,
		height:300,
		async:false,
		max:false,
		min:false,
		lock:true
	 });
    }
    function deleteFolder(id,pid){
		if(confirm("确认删除该文件夹吗？")){
		window.location.href='${basePath}doc/labDoc/deleteLabDocFolder.action?labDocVo.id='+id+'&labDocVo.pid='+pid;
		}
	}
	function ajax4UpdateFolder(id){
		var url  = '${basePath}doc/labDoc/showLabDocFolder4Update.action?labDocVo.id='+id+'&labDocVo.ext01=<%=new Date().getTime()%>' ;
		$.dialog({
			pid:'pid',
			content:'url:'+url,
			title:'<s:property value="getText('filemodified')"/>',
			opacity:0.4,
			width:600,
			height:400,
			lock:true,
			max:false,
			min:false
		 });
	}
	$(function(){
		$('.folder').mouseover(function(){    
            $(this).find('.editType').removeClass("editType").addClass("cur"); 
        }).mouseout(function(){    
            $(this).find('.cur').removeClass("cur").addClass("editType"); 
        });
	
	});		
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
								<span>${path}</span>
							</h2>
						</div>
						<form action="addLabUser" method="post" name="labDocForm">
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
														<l:a  uri="doc/labDoc/listLabDoc.action?labDocVo.id=${labDocVo.pid}" onclick="goAction('listLabDoc.action?labDocVo.id=${labDocVo.pid}');" value="msg.back"/>
														</td>
														<td>
														<l:a  uri="doc/labDoc/preAddLabDocFolder.action" onclick="ajax4AddFolder('${labDocVo.id}');return false;" value="add.folder"/>
														</td>
														<td>
														<l:a  uri="doc/labDoc/preAddLabDocFile.action?labDocVo.pid=${labDocVo.id}" value="new.file"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>我的文档 </span>
									</div>
									<div style="margin: 10px 50px 10px 50px;">
										<table border="0" width="100%">
											<tr>
												<s:iterator value="#request.labDocVoList" status="st">
													<td>
														<div  class="folder" style="width: 150px; text-align: center;">
															<a href="<%=basePath%>doc/labDoc/listLabDoc.action?&labDocVo.id=${id}">
																<img src="<%=basePath%>img/${docIcon }"  title="${remark}"/>
																<p style="margin-left: 20px;">
																	<a href="<%=basePath%>doc/labDoc/listLabDoc.action?labDocVo.id=${id}" title="${remark}">	
																		${fileName }(${num })
																	</a>
																	<a class="editType" href="javascript:;" onclick="ajax4UpdateFolder('${id}');"><img src="<%=basePath %>/img/plugin.gif"/></a>
																	<s:if test="${num<=0}">
																		<a class="editType" href="javascript:;" onclick="deleteFolder('${id}','${labDocVo.id}');"><img src="<%=basePath %>/img/cancel.png"/></a>
																	</s:if>
																</p>
															</a>
														</div>
													</td>
													<s:if test="${(st.index+1)%6==0}">
													</tr>
													<tr>
												</s:if>
												</s:iterator>
											</tr>
										</table>
									<s:if test="#request.labDocVoFileList.size()>0">
									<div style="margin: 10px -50px 10px -50px;">
												<hr />
									</div>
									<div style="margin: 10px -40px 10px -40px">
										<table class="myworkingboxttable" id="orgTable">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="file.name"/>
													</th>
													<th>
														类型
													</th>
													<th>
														创建日期
													</th>
													<th>
														文件说明
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
												<s:iterator value="#request.labDocVoFileList" status="st">
													<tr>
														<td class="c">
															${st.index+1 }
														</td>
														<td class="l">
															<a href="javascript:;" onclick="ajax4ShowFile('${id }');return false;">${fileName }</a>
														</td>
														<td class="l">
															${docType }
														</td>
														<td class="c">
															${createTime }
														</td>
														<td class="l">
															${remark }
														</td>
														<td class="c">
															<a href="<%=basePath %>doc/labDoc/preUpdateLabDocFile.action?labDocVo.id=${id }&labDocVo.pid=${labDocVo.pid}" >修改</a>
															&nbsp;&nbsp;
															<a onclick="deleteOne('${id }','${labDocVo.id}')" href="javascript:;"><s:text name="lab.code.del"/></a>
														</td>
													</tr>
												</s:iterator>
											</thead>
										</table>
									</div>
									</s:if>
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
