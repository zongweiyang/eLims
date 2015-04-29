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
			function ajaxIsExistName(obj){
				var value=$(obj).val();
				if(value.length==0||value=='${labMethodVo.name}'){
					return ;
				}
				$.ajax({
					url:'${basePath}/klg/labMethod/ajaxMethodIsExistName.action',
					type:'POST',
					data:{'id':'${labMethodVo.id}','name':value},
					dataType:'text',
					success:function(data){
						if(data=='1'){
							alert('检测方法名称已经存在.');
							$(obj).select();
						}
					},
					error:function(){
						alert('网络不通.');
					}
				});
			}
			function uploadFile(busId,busType){
			  	fileTypes = '*.*;';
			   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
			   +fileTypes+'&busType='+busType+'&urls=/klg/labMethod/preUpdateLabMethod.action';
			   $.dialog({
					id:'id',
					content:'url:'+url,
					title:'标准文件上传',
					opacity:0.4,
					width:300, 
					height:80,
					lock:true,
					max:false,
					min:false,
					resize:false
				 });
			}//多附件labUpLoads.jsp height:300,、
			
			function deleteUploadFile(obj,id){
		        $.ajax({
					   type: "POST",
					   url: '<%=basePath%>doc/labDoc/deleteUploadFile.action?labDocVo.fileId='+id,
					   data: "",
					   async: false,
					   success: function(data){
						   if(data=='1')
						   {
							   alert('<s:property value="getText('msg.del.success')"/>');
							   $(obj).parent().remove();
						   }
					   }
				});	
		   }
		    function showApp(){
			 var url='<%=basePath%>/klg/labItem/showLabAppara4select.action';
				var url='<%=basePath%>/klg/labItem/showLabAppara4select.action';
				$.dialog({
					id:'power',
					content:'url:'+url,
					title:'仪器选择：',
					maxBtn:false,
					rang: true,
					drag: true,
					resize: false,
					bgcolor:'#000',
					opacity:0.4,
					width:700, 
					iconTitle:false,
					btnBar:false,
					height:400,
					max:false,
					min:false,
					lock:true,
					autoPos:{left:'center',top:'center'}
				 });
		} 
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labMethodForm" id="form">
			<input type="hidden" value="${labMethodVo.id}" name="labMethodVo.id" id="id" />
			<input type="hidden" value="${labMethodVo.uuid}" name="labMethodVo.uuid" id="uuid" />
			<input type="hidden" value="${labMethodVo.id}" name="id" id="id" />
			<input type="hidden" value="${pagerMethod}" name="pagerMethod" id="pagerMethod" />
			<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
			<input type="hidden" value="${currentPage}" name="currentPage" id="currentPage" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
												${funName }：
												<span><s:text name="lab.code.modify"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>       
                                                      				<l:a uri="back" value="msg.back"/>                             
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="klg/labMethod/updateLabMethod.action" img="/img/add.gif" onclick="goAction('/klg/labMethod/updateLabMethod.action');" value="lab.code.save"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>检测${funName }</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															方法名称：
														</label>
													</td>
													<td>
														<input flag="on"  onblur="ajaxIsExistName(this);" msg="检测方法名称不能为空"  valType="required" name="labMethodVo.name" id="name" type="text" size="20" value="${labMethodVo.name}" />
													</td>
													<td>
														<label>
															方法编号：
														</label>
													</td>
													<td>
														<input name="labMethodVo.code" valType="required"  msg="检测方法编号不能为空"  id="code" type="text" size="20" value="${labMethodVo.code}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用：
														</label>
													</td>
													<td>
														<input valType="number" name="labMethodVo.price" id="price" type="text" size="30" value="${labMethodVo.price}" />
														（<s:text name="theme.yuan"/>）
													</td>
													<td><label>方法文件：</label></td>
													<td colspan="">
														 <a id="BtnEdit" class="zPushBtn"
															href="javascript:void(0);"
															onclick="uploadFile('${labMethodVo.id}','klg-method');">
														 	<img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b>
														</a>
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>
																	<a href="${path }" id="fileId">${name } </a>
																	<a href="javascript:;"
																		id="fileIcon" onclick="deleteUploadFile(this,'${id }')"><img
																			src="<%=basePath%>img/zhongzhi.gif" /> </a>
																</span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													</td>
												</tr>
												<tr>
													<td>
														<label>使用仪器：</label>
													</td>
													<td colspan="3">
														<textarea cols="60" id="appName" rows="3" name="labMethodVo.labApparaName" onclick="showApp()" >${labMethodVo.labApparaName }</textarea>
														<input type="hidden" id="appId" name="labMethodVo.labApparaId" value="${labMethodVo.labApparaId}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="60" rows="3" name="labMethodVo.context">${labMethodVo.context}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="60" rows="3" name="labMethodVo.remark">${labMethodVo.remark}</textarea>
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面） 结束 -->
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
	</body>
<script></script>
</html>		
