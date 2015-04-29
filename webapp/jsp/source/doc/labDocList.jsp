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
	function flushThisPage(){
		window.location.reload();
	}
	function showAllLabDoc(){
		var url  = '${basePath}doc/labDoc/showLabDoc4Select.action';
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'文件库',
			opacity:0.4,
			width:800,
			height:500,
			lock:true,
			max:false,
			min:false
		 });
	}
	function showOtherApplyLabDoc(){
		var url  = '${basePath}doc/labDoc/showLabDocApply4Select.action';
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'我的审核',
			opacity:0.4,
			width:800,
			height:500,
			lock:true,
			max:false,
			min:false
		 });
	}
	function submitValue(actioner)	{
				var df = document.labDocForm;	
		 		df.action=actioner;
		  		df.submit();
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
			title:'文件详情',
			opacity:0.4,
			width:500,
			height:300,
			async:false,
			min:false,
			max:false,
			lock:true
		 });
	}
	$(function(){
		$('.folder').mouseover(function(){    
            $(this).find('.editType').removeClass("editType").addClass("cur"); 
        }).mouseout(function(){    
            $(this).find('.cur').removeClass("cur").addClass("editType"); 
        });
	
	});
	function deleteFolder(id,pid){
		if(confirm("确认删除该文件夹吗？")){
		window.location.href='${basePath}doc/labDoc/deleteLabDocFolder.action?labDocVo.id='+id+'&labDocVo.pid='+pid;
		}
	}
	function ajax4UpdateFolder(id){
		var url  = '${basePath}doc/labDoc/showLabDocFolder4Update.action?labDocVo.id='+id+'&labDocVo.ext01=<%=new Date().getTime()%>';
		$.dialog({
			pid:'pid',
			content:'url:'+url,
			title:'文件夹现修改',
			opacity:0.4,
			width:600,
			height:400,
			lock:true,
			max:false,
			min:false
		 });
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
								<span><s:text name="top.index"/></span>
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
										<span><s:text name="my.doc"/></span>
									</div>
									<div style="margin: 10px 50px 10px 50px;">
										<table border="0" width="100%">
											<tr>
												<s:iterator value="#request.labDocVoList" status="st">
													<td>
														<div class="folder" style="width: 150px; text-align: center;" >
															<a href="<%=basePath%>doc/labDoc/listLabDoc.action?labDocVo.id=${id}" title="${remark }">
																<img src="<%=basePath%>img/${docIcon}" />
															</a>
															<p style="margin-left: 20px;">
																<a href="<%=basePath%>doc/labDoc/listLabDoc.action?labDocVo.id=${id}" title="${remark }">	
																	${fileName }(${num })
																</a>
																<a class="editType" href="javascript:;" onclick="ajax4UpdateFolder('${id}');"><img src="<%=basePath %>/img/plugin.gif"/></a>
																<s:if test="${num<=0}">
																	<a class="editType" href="javascript:;" onclick="deleteFolder('${id}','${labDocVo.id}');"><img src="<%=basePath %>/img/cancel.png"/></a>
																</s:if>
															</p> 
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
											<div style="margin: 10px -40px 10px -40px;">
												<table class="myworkingboxttable" id="orgTable">
													<thead>
														<tr>
															<th width="100">
																<img src="<%=basePath%>img/icon_drag.gif" />
															</th>
															<th width="270" >
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
														</thead>
														<s:iterator value="#request.labDocVoFileList" status="st">
															<s:if test="${st.index<5}">
																<tr>
																	<td class="c" >
																		${st.index+1 }
																	</td>
																	<td>
																		<a href="javascript:;" onclick="ajax4ShowFile('${id }');return false;">${fileName }</a>
																	</td>
																	<td class="l">
																		${docType }
																	</td>
																	<td class="c">
																		${createTime }
																	</td>
																	<td>
																		${remark }
																	</td>
																	<td class="c" width="120">
																		<a href="<%=basePath%>doc/labDoc/preUpdateLabDocFile.action?labDocVo.id=${id}&labDocVo.pid=${labDocVo.id }">修改</a>
																		&nbsp;&nbsp;
																		<a href="javascript:;" onclick="deleteOne('${id}','${labDocVo.id }');"><s:text name="lab.code.del"/></a>
																	</td>
																</tr>
															</s:if>
														</s:iterator>
												</table>
											</div>
										</s:if>
									</div>
								</div>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span>我的申请</span>
									<label style="float: right;">
										<a href="javascript:;" onclick="showAllLabDoc()" >&nbsp;&nbsp;+&nbsp;查看全部&nbsp;&nbsp;&nbsp;&nbsp;</a>
									</label>
								</div>
								<div style="margin-top: 10px; margin-bottom: 10px;">
									<table class="myworkingboxttable" id="orgTable">
										<thead>
											<tr>
												<th width="100">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th width="270">
													<s:text name="file.name"/>
												</th>
												<th width="180">
													<s:text name="author"/>
												</th>
												<th>
													科室
												</th>
												<th>
													申请日期
												</th>
												<th>
													文件说明
												</th>
												<th>
													<s:text name="sam.state"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
										</thead>
										<s:iterator value="#request.labDocApplyVoList" status="st">
											<s:if test="${st.index<5}">
												<tr>
													<td class="c">
														${st.index+1 }
													</td>
													<td>
														<s:if test="${status==2&&ext1!=null}">
															<a href="javascript:;" onclick="ajax4ShowFile('${labDocVo.id }');return false;"> ${labDocVo.fileName }</a>	
														</s:if>
															<s:else> ${labDocVo.fileName } </s:else>
													</td>
													<td>
														${labDocVo.creatorName }
													</td>
													<td class="l">
														${labDocVo.orgName }
													</td>
													<td>
														${applyTime }
													</td>
													<td>
														${labDocVo.remark }
													</td>
													<td class="c">
														<s:if test="${status==1 }">审核中</s:if>
														<s:elseif test="${status==2}">已赋权</s:elseif>
														<s:elseif test="${status==3}">已拒绝</s:elseif>
														<s:elseif test="${status==4}">无权限</s:elseif>
													</td>
													<td class="c" width="120">
														<s:if test="${status>0&&status<3||status==4}">
															<a
																href="<%=basePath%>doc/labDoc/updateLabDocApply4cancel.action?labDocApplyVo.id=${id }">取消</a>
														</s:if>
														<s:elseif test="${status==3}">
															<a
																href="<%=basePath%>doc/labDoc/updateLabDocApply.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=1">申请</a>&nbsp;&nbsp;
											        <a
																href="<%=basePath%>doc/labDoc/updateLabDocApply4cancel.action?labDocApplyVo.id=${id }">取消</a>
														</s:elseif>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</table>
								</div>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span>我的审核</span>
									<label style="float: right;">
										<a href="javascript:;" onclick="showOtherApplyLabDoc()"
											>&nbsp;&nbsp;+&nbsp;查看全部&nbsp;&nbsp;&nbsp;&nbsp;</a>
									</label>
								</div>
								<div style="margin-top: 10px; margin-bottom: 10px;">
									<table class="myworkingboxttable" id="orgTable">
										<thead>
											<tr>
												<th width="100px"> 
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th width="270">
													<s:text name="file.name"/>
												</th>
												<th width="180">
													申请人
												</th>
												<th>
													科室
												</th>
												<th>
													申请日期
												</th>
												<th>
													文件说明
												</th>
												<th>
													<s:text name="sam.state"/>
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
										</thead>
										<s:iterator value="#request.labDocApplyVoOtherList"
											status="st">
											<s:if test="${st.index<5}">
												<tr>
													<td class="c">
													${st.index+1 }
													</td>
													<td>
													<a href="javascript:;" onclick="ajax4ShowFile('${labDocVo.id }');return false;">
														${labDocVo.fileName }
													</a>
													</td>
													<td>
														${applyName }
													</td>
													<td class="l">
														${labDocVo.orgName }
													</td>
													<td class="c">
														${applyTime }
													</td>
													<td>
														${labDocVo.remark }
													</td>
													<td class="c">
														<s:if test="${status==4 }">无权限</s:if>
														<s:elseif test="${status==1}">审核中</s:elseif>
														<s:elseif test="${status==2}">已赋权</s:elseif>
														<s:elseif test="${status==3}">已拒绝</s:elseif>
													</td>
													<td class="c" width="120">
														<s:if test="${status==2}">
															<a
																href="<%=basePath%>doc/labDoc/updateLabDocApplyfByFlag.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=4">取消</a>
														</s:if>
														<s:elseif test="${status==1}">
															<a
																href="<%=basePath%>doc/labDoc/updateLabDocApplyfByFlag.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=2">通过</a>&nbsp;&nbsp;
											        <a
																href="<%=basePath%>doc/labDoc/updateLabDocApplyfByFlag.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=3">拒绝</a>
														</s:elseif>
														<s:elseif test="${status==3||status==4}">
															<a
																href="<%=basePath%>doc/labDoc/updateLabDocApplyfByFlag.action?labDocApplyVo.id=${id }&labDocApplyVo.flag=2">通过</a>&nbsp;&nbsp;
											        </s:elseif>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</table>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
		</table>
	</body>
</html>
