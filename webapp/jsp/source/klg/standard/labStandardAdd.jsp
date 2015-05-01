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
			function checkUpload(){
			/*
				var uploadTest=$("#upload").val();
				if(null!=uploadTest&&uploadTest.length>0){
					var index=uploadTest.lastIndexOf(".");
					var newLoad=uploadTest.substring(index+1,uploadTest.length);
					if(newLoad=="rar"){
						alert('<s:property value="getText('cannotuploadrad')"/>');
						return false;
					}
				}
				return true;
				*/
			}		
			
			function submitForm(){
			$('#form').submit();
			}
			
			
			function submitforform(){
				$('#form').submit();
			}
			function goToNextAction(url){
				$('#form').attr('action','${basePath}'+url);
				submitforform();
			}
			function ajaxIsExistName(obj){//验证编号重复
				var value=$(obj).val();
				if(value.length==0){
					return ;
				}
				$.ajax({
					url:'${basePath}/klg/labStandard/ajaxIsExistName.action',
					type:'POST',
					data:{'code':value},
					dataType:'text',
					success:function(data){
						if(data=='1'){
							alert('<s:property value="getText('stdcodeisexist')"/>');
							$(obj).val("");
							$(obj).select();
						}
					},
					error:function(){
						alert('<s:property value="getText('network.error')"/>');
					}
				});
			}
			var num=2;
			function copyrow(){
				var trStr='<tr>'+
								'<td class="c">'+num+'</td>'+
								'<td class="c"><input type="text" size="50" name="labStandardVo.indexList['+num+'].name" value="" id="indexName'+num+'"/></td>'+
								'<td class="c"><a href="javascript:;" onclick="delrow(this)"><s:text name="lab.code.del"/></a></td>'+
							'</tr>';
				$('#indexTable').append(trStr);			
				num++;	
			}
			function delrow(obj){
				$(obj).parent().parent().remove();
			}
			
			function uploadFile(busId,busType){
			   var fileTypes = '*.*;';
			   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
			   +fileTypes+'&busType='+busType;
			   $.dialog({
					id:'id',
					content:'url:'+url,
					title:'<s:property value="getText('stdfileupload')"/>',
					opacity:0.4,
					width:300, 
					height:80,
					lock:true,
					max:false,
					min:false,
					resize:false
				 });
			}//多附件labUpLoads.jsp height:300,
			function selectStandard(){
					var url  = '${basePath}klg/labStandard/listLabStandard4Select.action?labStandardVo.ids='+$("#replaceIds").val();
					$.dialog({
						id:'ids',
						content:'url:'+url,
						title:'<s:property value="getText('stdstdseelct')"/>',
						opacity:0.4,
						width:800,
						height:500,
						lock:true,
						max:false,
						min:false
					 });
			}
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
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labStandardForm" id="form">
			<input type="hidden" name="labStandardVo.standTypeName" value="${labStandardVo.standTypeName}" />
			<input type="hidden" name="labStandardVo.standTypeId" value="${labStandardVo.standTypeId}" />
			<input type="hidden" value="${labStandardVo.uuid}" name="labStandardVo.uuid" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="admin.add"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back"/> 
																</td>
																<td>
																	<l:a uri="klg/labStandard/addLabStandard.action" onclick="goAction('/klg/labStandard/addLabStandard.action');" value="lab.code.save"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
												<span><s:text name="stdinfo"/>[<font color="blue">${labStandardVo.standTypeName}</font>]</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="biaozcode"/>：
														</label>
													</td>
													<td>
														<input name="labStandardVo.code" valType="required"  msg='<s:property value="getText('stdcodenotempty')"/>' id="code" type="text" size="40" onblur="ajaxIsExistName(this);" value="${labStandardVo.code}" />
													</td>
													<td>
														<label>
															<s:text name="std.name"/>：
														</label>
													</td>
													<td>
														<input name="labStandardVo.name" valType="required" msg='<s:property value="getText('stdnamenotemp')"/>' id="name" type="text" size="40" value="${labStandardVo.name}" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="publish.date"/>：
														</label>
													</td>
													<td>
														<input readonly="readonly" name="labStandardVo.releaseDate" value="${labStandardVo.releaseDate}" id="releaseDate" class="Wdate" size="40" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
													</td>
													<td>
														<label>
															<s:text name="shishidate"/>：
														</label>
													</td>
													<td>
														<input readonly="readonly" name="labStandardVo.materialDate" value="${labStandardVo.materialDate}" id="materialDate" class="Wdate" size="40" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
													</td>
												</tr>
												<tr>
													<td><label><s:text name="biaolenave"/>：</label></td>
													<td>
														<s:select list="{getText('countstd'),getText('induststd'),getText('dizhiindustry')}" name="labStandardVo.standIndex" value="%{labStandardVo.standIndex}" theme="simple"></s:select>
													</td>
													<td>
														<label>
															<s:text name="lab.yes"/>否启用：
														</label>
													</td>
													<td>
														<input type="radio" style="border: 0px;" checked="checked" name="labStandardVo.isUsed" value="Y"/>启用&nbsp;&nbsp;&nbsp;
														<input type="radio" style="border: 0px;" name="labStandardVo.isUsed" value="N"/>不启用
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="stdfiles"/>：
														</label>
													</td>
													<td colspan="3">
														<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labStandardVo.uuid}','klg-standard');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
														<div id="upfiles">
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>
																	<a href="${path }">${name } </a>
																	<a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id }')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a>
																</span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
														</div>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															代替标准：
														</label>
													</td>
													<td colspan="3">
														<textarea cols="40" readonly="readonly" id="replaceName" rows="3" name="labStandardVo.replaceName" onclick="selectStandard()">${labStandardVo.replaceName}</textarea>
														<input type="hidden" name="labStandardVo.replaceIds" id="replaceIds"/>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															标准说明：
														</label>
													</td>
													<td colspan="3">
														<textarea valType="strLength" max="200" msg="备注不能大于200个字" cols="40" rows="3" name="labStandardVo.remark">${labStandardVo.remark}</textarea>
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
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			 this.a = new Array("upload", "请上传图章!",   new Function ("varName", " return this[varName];"));	
		}
	</script>
</html>
