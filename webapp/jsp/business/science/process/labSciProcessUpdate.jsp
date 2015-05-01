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
</style>



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
   	function showLabItem4Select(index){
				var typeId=$('#samTypeId'+index).val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>sample/labSampRegister/showLabItem4Select.action?labItemVo.categoryIds='+typeId+'&labItemVo.index='+index,
					title:'<s:property value="getText('cont.item.info')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
			function copySampInfo(name,index){
				var nameVal=$('#'+name+index).val();
				var len=$('#sampRegisterNum').val();
			   	len=parseInt(len);
				for(var i=(parseInt(index)+1);i<len;i++){
					if(name=='itemName'){
						var idVal=$('#itemId'+index).val();
						$('#itemId'+i).val(idVal);
					}else if(name=='samTypeId'){
						var newVal=$('#samTypeId'+index).val();
						var oldVal=$('#samTypeId'+i).val();
						if(oldVal!=newVal){
							clearValue(i);
						}
					}
					$('#'+name+i).val(nameVal);
				}
			 }
			 function copySampInfo4Select(name,index){
				var nameVal=$('#'+name+index).val();
				var len=$('#sampRegisterNum').val();
			   	len=parseInt(len);
				for(var i=(parseInt(index)+1);i<=len;i++){
					$('#'+name+i).find('option[value="'+nameVal+'"]').attr('selected','selected');
				}
			 }
			 function clearValue(index){
				$('#itemName'+index).val("");
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
						<form theme="simple" action="" method="post"
							name="labSciProcessForm">
							<input type="hidden" name="labSciProcessVo.labSciScienceId"
								value="${labSciProcessVo.labSciScienceId }" id="labSciScienceId" />
							<input type="hidden" name="labSciProcessVo.id"
								value="${labSciProcessVo.id}" />
							<input type="hidden" name="labSciProcessVo.no"
								value="${labSciProcessVo.no }" id="no" />
							<input name="labSciProcessVo.sampRegisterId" id="sampRegisterId" type="hidden" value="${labSciProcessVo.sampRegisterId}" />
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
															<l:a
																uri="science/labSciProcess/updateLabSciProcess.action?labSciProcessVo.auditResult=1"
																value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="stone">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td class="r" width="150">
												<label>
													名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
												</label>
											</td>
											<td>
												<input size="40" type="text" name="labSciProcessVo.name"
													id="name" valType="required" msg="名称不能为空"
													value="${labSciProcessVo.name }" />
											</td>
											<td class="r" width="150">
												<label>
													填&nbsp;&nbsp;写&nbsp;&nbsp;人：
												</label>
											</td>
											<td>
												<input size="40" type="text"
													name="labSciProcessVo.writeUser" id="writeUser"
													valType="required" msg="填写人不能为空"
													value="${labSciProcessVo.writeUser }" />
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													开始时间：
												</label>
											</td>
											<td>
												<input size="40" type="text" class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});"
													name="labSciProcessVo.startTime"
													value="${labSciProcessVo.startTime }" id="startTime" />
											</td>
											<td class="r" width="150">
												<label>
													<s:text name="end.time"/>：
												</label>
											</td>
											<td>
												<input size="40" type="text" class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});"
													name="labSciProcessVo.endTime"
													value="${labSciProcessVo.endTime }" id="endTime" />
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：
												</label>
											</td>
											<td id="TD3">
												<s:select cssStyle="width:231px;"
													list="#request.labCodeVoList" theme="simple" listKey="name" value="'${labSciProcessVo.type }'"
													listValue="name" id="types" onchange="selectType(this);"></s:select>
												<input valType="required" msg="类型不能为空" type="hidden"
													name="labSciProcessVo.type"
													value="${labSciProcessVo.type }" id="type" />
											</td>
											<td id="TD1" class="r" width="150">
												<label>
													样品数量：
												</label>
											</td>
											<td id="TD2">
												<input size="40" type="text"
													name="labSciProcessVo.sampRegisterNum" id="sampRegisterNum"
													value="${labSciProcessVo.sampRegisterNum }" />
												&nbsp;&nbsp;
												<l:a href="#"
													uri="science/labSciProcess/updateLabSciProcess.action?labSciProcessVo.auditResult=0"
													value="生成检测内容" />
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="contentdesd"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labSciProcessVo.contents" cols="40" rows="3"
													id="contents">${labSciProcessVo.contents }</textarea>
											</td>
										</tr>
										<tr>
											<td class="r" width="150">
												<label>
													<s:text name="lab.remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labSciProcessVo.remark" cols="40" rows="3"
													id="remark">${labSciProcessVo.remark }</textarea>
											</td>
										</tr>
										<tr>
											<td class="r">
												<label>
													<s:text name="upload.attachment"/>：
												</label>
											</td>
											<td colspan="3">
												<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);"
													onclick="uploadFile('${labSciLearnVo.id}','lab-sciProcess');">
													<img height="20" width="20"
														src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
												<s:if test="${fn:length(uplodeList)>0}">
													<s:iterator status="st2" value="#request.uplodeList" id="">
														<span> <a href="${path }" id="fileId">${name }
														</a> <a href="javascript:;" id="fileIcon"
															onclick="deleteUploadFile(this,'${id}')"><img
																	src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
													</s:iterator>&nbsp;&nbsp;&nbsp;
											</s:if>
												<div id="upfiles"></div>
											</td>
										</tr>
									</table>
								</div>
								<!-- 表单型表格（用于新增/修改页面）结束 -->
								<s:if test="${fn:length(sampList) > 0 }">
									<div class="Formtable">
										<div class="Formtabletitle">
													<s:text name="checking.content"/>
											<label style="float: right; padding-right: 10px;"
												onclick="$(this).parent().next().toggle();">
												[&nbsp;
												<font color="blue"><s:text name="open.close"/></font>&nbsp;]
											</label>
										</div>
										<div id="Tab01">
											<table class="FormtableCon_sform" cellspacing="1"
												cellpadding="0">
												<thead>
													<tr>
														<th width="100">
															<s:text name="batch.no"/>
														</th>
														<th width="100">
															<s:text name="origin.number"/>
														</th>
														<th width="190">
															样品名称
														</th>
														<th width="110">
															样品类型
														</th>
														<th>
															<s:text name="checking.item"/>
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="sampList" status="st">
														<tr>
															<td width="100">
																<input type="text" name="sampList[${st.index}].sampCode"
																	value="${sampCode}" size="10" readonly="readonly"
																	style="background-color: #DDDDDD" />
															</td>
															<td width="100">
																<input type="text" name="sampList[${st.index}].oldNo"
																	value="${oldNo}" size="10" />
															</td>
															<td>
																<input type="text" name="sampList[${st.index}].name"
																	id="sampName${st.index}" value="${name}" />
																<img src="/img/lefttorightselect/arrow_dwn.gif"
																	onclick="copySampInfo('sampName','${st.index}')" />
															</td>
															<td>
																<s:select list="#request.samTypeList" listKey="id"
																	listValue="name" theme="simple"
																	name="sampList[${st.index}].samTypeId"
																	id="samTypeId${st.index}"
																	onchange="clearValue('${st.index}');"></s:select>
																<img src="/img/lefttorightselect/arrow_dwn.gif"
																	onclick="copySampInfo('samTypeId','${st.index}')" />
															</td>
															<td>
																<textarea style="width: 90%; overflow: hidden;"
																	name="sampList[${st.index}].itemName"
																	id="itemName${st.index}"
																	onclick="showLabItem4Select('${st.index}');"
																	readonly="readonly">${itemName}</textarea>
																<input type="hidden" name="sampList[${st.index}].itemId"
																	value="${itemId}" id="itemId${st.index}" />
																<img src="/img/lefttorightselect/arrow_dwn.gif"
																	onclick="copySampInfo('itemName','${st.index}')" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</div>
									</div>
								</s:if>
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
			var type = '${labSciProcessVo.type }';
				if(type != '科研实验'){
					$('#TD1').hide();
	  				$('#TD2').hide();
	  				$('#TD3').attr('colspan','3');
				}else{
					$('#TD1').show();
	  				$('#TD2').show();
	  				$('#TD3').removeAttr('colspan');
				}
			})
		</script>
	</body>
</html>
