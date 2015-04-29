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
			
			function checkTotal(obj,i){
				if($(obj).val().length>0){
					if(BASEisNotFloat($(obj).val())) {
					    validate.tip('<s:property value="getText('theme.input.number')"/>',$('#functionId'));
						$(obj).val("0");
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
						   validate.tip('<s:property value="getText('sam.no.existed')"/>',$('#functionId'));
							$(obj).val("");
							$(obj).select();
						}
					},
					error:function(){
					    validate.tip('<s:property value="getText('network.error')"/>',$('#functionId'));
					}
				});
			}
			
	function uploadFile(busId,busType){
	   fileTypes = '*.*;';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType;
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('msg.confirm.del')"/>',
			opacity:0.4,
			width:300, 
			height:300,
			lock:true,
			max:false,
			resize:false
		 });
	}
	function deleteUploadFile(obj,id){
       if(confirm('<s:property value="getText('msg.del.success')"/>')){
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
		 
		 function showLabSamType4Select(){
		var url  = '${basePath}sam/labSam/showLabSamType4Select.action';
		$.dialog({
			id:'samTypeId',
			content:'url:'+url,
			title:'<s:property value="getText('sam.cla.list')"/>',
			opacity:0.4,
			max: false,                  
	        min: false,   
			width:600,
			height:400,
			lock:true
		 });
		}
		function ajax2UserList(){
		var orgId=$("#orgId").val();
			if(orgId==''){
				alert('<s:property value="getText('select.room')"/>');
				return false;
			}
		var url  = '${basePath}sam/labSam/showLabUser4Select.action?labUserVo.orgId='+orgId;
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.user.list')"/>',
			opacity:0.4,
			min:false,
			max:false,
			width:800,
			height:400,
			lock:true
		 });
		}
	
	function addOneEachRows(obj){
			var tempIndex=$(obj).attr('key');
			var num=$(obj).parent().parent().parent().find('tr').length;
			var newIndex=parseInt($(obj).parent().parent().parent().find('tr').eq(num-1).attr('key'));
			var i=$(obj).closest('tr').find('input[id*="sampnum"]').val();
			if(isNaN(i)||i==''){
				return false;
			}
			if(isNaN(newIndex)){
				return false;
			}
			for(var j=0;j<i;j++){
				newIndex++;
				var tempTr=$(obj).closest('tr').clone();
				tempTr.find('input[name^="labSamMainVo.labSamVoList"]').each(function(n){
				    var name = $(this).attr('name').replace('labSamVoList['+tempIndex+']','labSamVoList['+newIndex +']');
					$(this).attr('name',name);
					$(this).attr('id',tempIndex+j);
				});
				tempTr.find('select[name^="labSamMainVo.labSamVoList"]').each(function(){
				 var name = $(this).attr('name').replace('labSamVoList['+tempIndex+']','labSamVoList['+newIndex+']');
					$(this).attr('name',name);
				});
				tempTr.find('input[name$="sampCode"]').each(function(n){
					if(i<10){
						$(this).val($(this).val()+"-0"+j);
						$(this).prev().html($(this).val());
					}else{
						$(this).val($(this).val()+"-"+j);
						$(this).prev().html($(this).val());
					}
				});
				var str=tempTr.find('td').eq(8);
				tempTr.find('td').eq(8).html('<input type="hidden" name="labSamMainVo.labSamVoList['+newIndex+'].isDiv" value="Y" /><a href="javascript:;" onclick="deleteOne(this);return false;"><s:text name="lab.code.del"/></a>');
				tempTr.attr('key',newIndex);
				$('#oneEach').append(tempTr);
			}
			$(obj).closest('tr').remove();
		}
		function deleteOne(obj){
			if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
				$(obj).parent().parent().remove();
			}
		};
		//第一层循环数据序号改变事件
	function eachToChangeIndex(obj){
		var index=$(obj).val();
		var oldIndex=$(obj).attr('key');
		
		var key=$(obj).parent().parent().attr('key');
		//判断序号是否存在，若存在不修改
		var isHas=false;
		$(obj).parent().parent().parent().find('input[name*=".index"]').each(function(){
			if($(this).attr('key')==index){
				isHas=true;
				return false;
			}	
		})
		if(isHas){
			validate.tip('<s:property value="getText('seq.existed')"/>',$(obj));
			$(obj).val(key);
			return false;
		}
		var trDiv=$('#TwoEachDiv'+key);
		trDiv.find('.TwoEachIndex').html(index);
		$(obj).attr('key',index);
	}
		</script>
	</head>
	<body id="mainid">
		<form method="post" name="form" enctype="multipart/form-data" id="form">
			<input name="labSamMainVo.id" value="${labSamMainVo.id }" type="hidden" />
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
												<span><s:text name="lab.code.modify"/></span>
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
																	<l:a uri="sam/labSam/updateLabSam.action" img="/img/add.gif" value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title="点击此处，隐藏/显示该信息">
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
														<input name="labSamMainVo.sampNo" id="sampNo" type="text" size="30" value="${labSamMainVo.sampNo}" valType="required,strLength" msg="任务编号不能为空！" strLength-msg="任务编号长度不能超过16位" />
													</td>
													<td>
														<label>
															<s:text name="sam.number"/>：
														</label>
													</td>
													<td>
														<input type="text" id="sampleNum" name="labSamMainVo.num" value="${labSamMainVo.num }" size="30" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="cust.name"/>：
														</label>
													</td>
													<td>
														<input name="labSamMainVo.customer" id="customer" type="text" size="30" value="${labSamMainVo.customer}" valType="required" msg="客户名称不能为空！" />
													</td>
													<td>
														<label>
															<s:text name="call.people"/>：
														</label>
													</td>
													<td>
														<input type="text" id="contacts" name="labSamMainVo.contacts" size="30" value="${labSamMainVo.contacts }" id="contacts" />
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
														<input type="text" name="labSamMainVo.sampDate" size="30" value="${labSamMainVo.sampDate}" id="sampDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="save.style"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea rows="2" cols="80" name="labSamMainVo.saveMode">${labSamMainVo.saveMode}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="sam.site"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea rows="2" cols="80" name="labSamMainVo.sampSource">${labSamMainVo.sampSource}</textarea>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="3">
														<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labSamMainVo.id}','sam');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>${name }<a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id}')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
														<div id="upfiles"></div>
													</td>
												</tr>
											</table>
										</div>
										<s:if test="labSamMainVo.labSamVoList!=null&&labSamMainVo.labSamVoList.size()>0">
											<div class="Formtable oneEachDiv">
												<div class="Formtabletitle">
													<span><s:text name="sam.info"/></span>
													<label style="float: right; padding-right: 10px;"></label>
												</div>
												<table class="FormtableCon" id="oneEach">
													<tr>
														<th>
															<label>
																<s:text name="batch.no"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="origin.no"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="sam.name"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="regular"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="sam.classify"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="sam.performance"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="sam.time"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="using.type"/>
															</label>
														</th>
														<th>
															<label>
																<s:text name="sam.divied"/>
															</label>
														</th>
													</tr>
													<s:iterator value="labSamMainVo.labSamVoList" status="st">
														<tr key="${st.index}">
															<td class="c">
																<font>${sampCode}</font>
																<input type="hidden" name="labSamMainVo.labSamVoList[${st.index }].sampCode" value="${sampCode}" />
															</td>
															<td class="c" width="70">
																<input style="width: 100%;" type="text" name="labSamMainVo.labSamVoList[${st.index }].oldNo" value="${oldNo}" />
															</td>
															<td class="c">
																<input style="width: 90%;" type="text" name="labSamMainVo.labSamVoList[${st.index }].name" value="${name}" valType="required,strLength" msg="样品名称不能为空！" strLength-msg="样品名称长度不能超过16位" />
															</td>
															<td class="c" width="50">
																<input style="width: 100%;" type="text" name="labSamMainVo.labSamVoList[${st.index }].specifications" value="${specifications }" id="labReportTagCode" />
															</td>
															<td class="c">
																<s:select name="labSamMainVo.labSamVoList[${st.index }].samTypeId" list="#request.labSamTypeVoList" headerKey="${samTypeId}" headerValue="${samTypeName}" listKey="id" listValue="name" theme="simple"></s:select>
															</td>
															<td class="c" width="100">
																<input style="width: 100%;" type="text" name="labSamMainVo.labSamVoList[${st.index }].shape" value="${shape }" />
															</td>
															<td width="70">
																<input style="width: 100%;" type="text" name="labSamMainVo.labSamVoList[${st.index }].sampDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" value="${sampDate }" />
															</td>
															<td class="c">
																<s:select list="#{'N':'检测','Y':'留样'}"  name="labSamMainVo.labSamVoList[${st.index }].isDestory" id="isDestory" cssStyle="width: 80px;" theme="simple"></s:select>
															</td>
															<td class="c" width="50">
																<s:if test="${isDiv=='Y'}">
																	<s:text name="sam.divieded"/>
																	<input type="hidden" name="labSamMainVo.labSamVoList[${st.index}].isDiv" value="Y" />
																</s:if>
																<s:else>
																<input style="width: 100%;" type="text" id="sampnum${st.index}" key="${st.index}" onblur="addOneEachRows(this);return false;" />
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
										</s:if>
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
