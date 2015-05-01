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
			function BASEisNotFloat(theFloat){
				//判断是否为浮点数
				len=theFloat.length;
				dotNum=0;
				if (len==0) return true;
				for(var i=0;i<len;i++){
					oneNum=theFloat.substring(i,i+1);
					if (oneNum==".") dotNum++;
					if (((oneNum<"0" || oneNum>"9") && oneNum!=".") || dotNum>1) return true;
				}
				if (len>1 && theFloat.substring(0,1)=="0"){
					if (theFloat.substring(1,2)!=".") return true;
				}
					return false;
			}
			
			function checkTotal(obj){
				if($(obj).val().length>0){
					if(BASEisNotFloat($(obj).val())) {
					    validate.tip('<s:property value="getText('input.num')"/>',$('#functionId'));
						$(obj).val("0");
						return false;
					}else{
						return true
					}
				}
			}
			
			function ajaxIsExistName(name,size){//验证编号重复
				var name=$('#name').val();
				var size=$('#size').val();
				if(value.length==0){
					return ;
				}
				$.ajax({
					url:'${basePath}/sam/labSam/ajaxIsExistName.action',
					type:'POST',
					data:{'name':name,'size':size},
					dataType:'text',
					success:function(data){
						if(data=='1'){
						   validate.tip('<s:property value="getText('sam.existed')"/>',$('#functionId'));
							$(obj).val("");
							$(obj).select();
						}
					},
					error:function(){
					    validate.tip('<s:property value="getText('network.error)"/>',$('#functionId'));
					}
				});
			}
			
	function uploadFile(busId,busType){
	var m=$("#sampleNum").val();
	if(m==""){
		alert('<s:property value="getText('input.sam.number')"/>');
		return;
	}
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
		 
	function ajax2UserList(index){
		var orgId=$("#orgId"+index).val();
		  	if(orgId==''){
				alert('<s:property value="getText('select.room')"/>');
				return false;
			}
		var url  = '${basePath}sam/labSam/showLabUser4Select.action?labUserVo.orgId='+orgId;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('user.list')"/>',
			opacity:0.4,
			min:false,
			max:false,
			width:800,
			height:400,
			lock:true
		 });
		}
		
	function deleteOne(obj){
		if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
			$(obj).parent().parent().remove();
			var key=$(obj).parent().parent().attr('key');
			var flag=$(obj).parent().parent().find('select').length;
			if(flag==1){
				$('#TwoEachDiv'+key).remove();
			}
		}
	};
	function forEacheSamp(){
		var m=$("#sampleNum").val();
		if(checkTotal($("#sampleNum"))){
			$("input ").each(function(){
				$(this).removeAttr('valType');
			});
			submitvalue('/sam/labSam/preAddLabSam.action');
		}
	}
	function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
   function copySampInfo(name,index){
    		var sampinfo=$('#'+name+''+index).val();
    		var len=$("#sampleNum").val();
	      	len=parseInt(len);
    		for(var i=(parseInt(index)+1);i<=len;i++){
			$('#'+name+''+i).val(sampinfo);
   		}
    } 
</script>
	</head>
	<body id="mainid">
		<form method="post" name="form" enctype="multipart/form-data" id="form">
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
												${funName}：
												<span><s:text name="lab.code.add"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="sam/labSam/addLabSam.action" value="lab.code.save" />
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
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="sam.no"/>：
														</label>
													</td>
													<td>
														<input name="labSamMainVo.sampNo" readonly="readonly" id="sampNo" type="text"  size="30" value="${labSamMainVo.sampNo}" valType="required,strLength" msg="任务编号不能为空！" strLength-msg="任务编号长度不能超过16位" />
													</td>
													<td>
														<label>
															<s:text name="sam.number"/>：
														</label>
													</td>
													<td>
														<input type="text" id="sampleNum" name="labSamMainVo.num" size="30" value="${labSamMainVo.num }" onblur="forEacheSamp();return false;" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="cust.name"/>：
														</label>
													</td>
													<td>
														<input name="labSamMainVo.customer" id="customer" type="text" size="30" value="${labSamMainVo.customer}" />
													</td>
													<td>
														<label>
															<s:text name="call.people"/>：
														</label>
													</td>
													<td>
														<input type="text" id="contacts" name="labSamMainVo.contacts" size="30"  value="${labSamMainVo.contacts }" id="contacts" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="called.info"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSamMainVo.contactPhone" size="30" value="${labSamMainVo.contactPhone }" id="contactPhone" />
													</td>
													<td>
														<label>
															<s:text name="sam.come.date"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labSamMainVo.sampDate" size="30" value="${labSamMainVo.sampDate}" id="sampDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});"/>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="3">
														<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labSamMainVo.uuid}','sam');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"></s:text></b> </a>
														<input value="${labSamMainVo.uuid}" name="labSamMainVo.uuid" type="hidden" />
														<span> <a href="${path }">${name } </a></span>
														<div id="upfiles"></div>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable oneEachDiv" >
										<div class="Formtabletitle">
											<span><s:text name="sam.info"/></span>
											<label style="float: right;padding-right: 10px;"></label>
										</div>
										<table class="FormtableCon" id="oneEach">
										<tr>
											<th><label><s:text name="origin.no"/></label></th>
											<th width="120"><label><s:text name="sam.name"/></label></th>
											<th width="85"><label><s:text name="regular"/></label></th>
											<th width="100"><label><s:text name="sam.classify"/></label></th>
											<th width="120"><label><s:text name="save.style"/></label></th>
											<th width="150"><label><s:text name="sam.site"/></label></th>
											<th width="120"><label><s:text name="sam.performance"/></label></th>
											<th width="120"><label><s:text name="sam.time"/></label></th>
											<th><label><s:text name="remark"/></label></th>
										</tr>
										<s:iterator value="new int[${labSamMainVo.num}]" status="st">
											<tr key="0">
												<td class="c"><input size="5" type="text" name="labSamMainVo.labSamVoList[${st.index }].oldNo" value="${oldNo}" /></td>
												<td class="c"><input size="10" type="text" name="labSamMainVo.labSamVoList[${st.index }].name" value="" id="name${st.index }"/>
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('name','${st.index}')"/>
												</td>
												<td class="c"><input size="5" type="text" name="labSamMainVo.labSamVoList[${st.index }].specifications" value="" id="specifications${st.index}"/>
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('specifications','${st.index}')"/></td>
												<td class="c"><s:select id="sampTypeId${st.index}" name="labSamMainVo.labSamVoList[${st.index }].samTypeId" list="#request.labSamTypeVoList" headerKey="0" headerValue="" listKey="id" listValue="name" theme="simple"></s:select>
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('sampTypeId','${st.index}')" /></td>
												</td>
												
												<td class="c"><input type="text" size="10" name="labSamMainVo.labSamVoList[${st.index }].saveMode"  id="saveMode${st.index}"/>
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('saveMode','${st.index}')"/>
												</td>
												<td class="c"><input type="text" size="15" name="labSamMainVo.labSamVoList[${st.index }].sampAddr" id="sampAddr${st.index }" />
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('sampAddr','${st.index}')"/>
												</td>
												<td class="c">
													<input type="text" size="10" name="labSamMainVo.labSamVoList[${st.index }].shape" id="shape${st.index}" />
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('shape','${st.index}')" />
												</td>
												<td>
													<input type="text" name="labSamMainVo.labSamVoList[${st.index }].sampDate" class="Wdate" size="10"
															onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" id="sampDate${st.index}"/>
													<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('sampDate','${st.index}')" />
												</td>
												<td class="c">
													<input type="text" size="10" name="labSamMainVo.labSamVoList[${st.index }].remark" />
												</td>
											
											</tr>
										</s:iterator>
										</table>
										</div>
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
			
		}
	</script>
</html>
