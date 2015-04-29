<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script>
		function validateVal(obj,status){
			obj=$(obj);
			var samNum=parseFloat(obj.val());
			var key=true;
			var validate=obj.next().val().split(',');
			for(var i=0;i<validate.length;i++){
				for(var j=0;j<validate[i].split(':').length;j++){
					var sign=validate[i].split(':')[j];
					var num=parseFloat(validate[i].split(':')[j+1]);
					if(sign=='<'){
						if(samNum-num>=0){
							key=false;
							break;
						}
					}else if(sign=='<='){
						if(samNum-num>0){
							key=false;
							break;
						}
					}else if(sign=='>'){
						if(samNum-num>0){
						}else{
							key=false;break;
						}
					}else if(sign=='>='){
						if((samNum-num<0)){
							key=false;
							break;
						}
					}
					j++;
				}
				if(key==false){
					break;
				}
				
			}
			var color='';
			if(!key){
				color='red';
			}else{
				color='green';
				if(status=='1'){
					copyValue(obj);
				}
			}
			obj.parent('td').css('background-color',color);
		}
		function copyValue(obj){
			var cIndex=obj.closest('td').index();
			$('.myworkingboxttable tr:gt('+obj.closest('tr').index()+')').each(function(){
				var value=$(this).find('td').eq(cIndex).find('input[name*=value]').val();
				if(value==''){
					$(this).find('td').eq(cIndex).find('input[name*=value]').val(obj.val());
					$(this).find('td').eq(cIndex).css('background-color','green');
				}
			});
		}
	function submitForm(url){
		
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
		function submitvalueforlist(){
			 var file = $('#file').val();
		    if(file == ''){
		    	alert("请选择文件！");
		    	return false;
		    }
		    var stuff=file.match(/^(.*)(\.)(.{1,8})$/)[3]; 
	        if(stuff!='xls')
	        {
	           alert('文件类型不正确，请选择.xls文件');
	           return false;
	        }
			submitValue('samtest/labSamTest/updateLabSamTest4Validate.action');
		}
		function uploadFile(busId,busType){
	   	fileTypes = '*.*';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='+fileTypes+'&busType='+busType+'&urls=/samtest/labSamTest/preUpdateSamTest4Data.action';
		   $.dialog({
				id:'id',
				content:'url:'+url,
				title:'原始记录单上传',
				opacity:0.4,
				width:300, 
				height:300,
				lock:true,
				max:false,
				resize:false
			 });
	}
	function uploadFile1(busId,busType){
	   	fileTypes = '*.*';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='+fileTypes+'&busType='+busType+'&num=1&urls=/samtest/labSamTest/preUpdateSamTest4Data.action';
		   $.dialog({
				id:'id',
				content:'url:'+url,
				title:'附件上传',
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
   	$(function(){
   		if('${message}'!=null&&'${message}'!=''){
   			validate.tip('${message}');
   		}
   	});
   function submitValue(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
  function 	preview(){
 	 $.dialog({
			id:'parentShowInfo',
			content:'url:'+'<%=basePath%>samtest/labSamTest/showLabSamTestInfo.action?labSamTestBeatchVo.id='+$("#labSamTestBeatchId").val(),
			title:'数据录入信息预览：',
			opacity:0.4,
			width:800, 
			height:600,
			lock:true,
			max:false,
			min:false
		});
  	
  	}
  	function flushThisPage(){
  		var url='samtest/labSamTest/updateLabSamTest4Date.action?labSamTestBeatchVo.auditResult=1';
  		$('form').attr('action','${basePath}'+url);
		$('form').submit();
  	}
	
	</script>
	<body class="" id="mainid">
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
											<span><s:text name="admin.add"/></span>
										</h2>
									</div>
									<form action="" method="post" enctype="multipart/form-data">
										<input type="hidden" id="labSamTestBeatchId" value="${labSamTestBeatchVo.id}" name="labSamTestBeatchVo.id" />
										<input type="hidden" name="labSamTestBeatchVo.itemNum" value="<s:property value="#request.listLabSamTestVo[0].listTitle.size" /> " />
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
																	<l:a uri="samtest/labSamTest/updateLabSamTest4Date.action" value="lab.code.save" img="/img/icon/filesave.gif"/>
																</td>
																<td>
																	<l:a uri="samtest/labSamTest/updateLabSamTest4Date.action?labSamTestBeatchVo.auditResult=1" onclick="preview();" value="save.commit" img="/img/icon/filesave.gif"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" >
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest.action?tabNum=6')"><span><s:text name="check.item.info"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest4Appara.action?tabNum=6');"><span><s:text name="app.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reagent.action?tabNum=6');"><span><s:text name="rea.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reference.action?tabNum=6');"><span><s:text name="std.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Ambient.action?tabNum=6');"><span><s:text name="env.info"/></span></a>
													</li>
													<li id="li04" class="currenttab">
														<a href="javascript:;"><span><s:text name="data.in"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle">
														<span style="cursor: hand;" onclick="$(this).parent().next().toggle();"><s:text name="check.item.info"/></span>
														<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
															[&nbsp;
															<font color="blue"><s:text name="open.close"/></font>&nbsp;]
														</label>
													</div>
													<div>
														<table class="myworkingboxttable">
															<tr>
																<th width="100">
																	<img src="<%=basePath%>img/icon_drag.gif" />
																</th>
																<th>
																	<s:text name="sam.id"/>
																</th>
																<th>
																	<s:text name="sam.name"/>
																</th>
																<th>
																	<s:text name="sam.type"/>
																</th>
																<s:iterator value="#request.listLabSamTestVo[0]" id="st">
																	<s:iterator value="#st.listTitle">
																		<th>
																			<span style="color: red;">${demo1}</span>
																			<span style="color: blue; cursor: hand;" title="检验人">（${demo2}）</span>
																		</th>
																	</s:iterator>
																</s:iterator>
															</tr>
															<s:iterator value="#request.listLabSamTestVo" status="st1" id="vo">
																<tr>
																	<td>
																		${st1.index+1}
																	</td>
																	<td>
																		${sampCode}
																	</td>
																	<td>
																		${labSamName}
																		<input type="hidden" name="listLabSamTestVo[${st1.index}].labSamId" value="${labSamId}" />
																	</td>
																	<td>
																		${labSamTypeName}
																	</td>
																	<s:iterator value="#vo.listLabDemoVo" status="son">
																		<s:if test="${demo=='N'}">
																			<td style="text-align: center;cursor: hand;" title="${demo3}">
																				<input type="hidden" name="listLabSamTestVo[${st1.index}].listLabSamTest[${son.index}].itemId" value="${demo1}" />
																				<input type="text" style="width: 40px;cursor: hand;" value="${demo2}" name="listLabSamTestVo[${st1.index}].listLabSamTest[${son.index}].value" onchange="validateVal(this,'1')" title="${demo3}" />
																				<input type="hidden" id="demo4${st1.index}" value="${demo4}" />
																		</s:if>
																		<s:else>
																			<td style="text-align: center;">
																				---
																		</s:else>
																		</td>
																	</s:iterator>
																</tr>
															</s:iterator>
														</table>
													</div>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span style="cursor: hand;" onclick="$(this).parent().next().toggle();"><s:text name="check.result.in"/></span>
														<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
															[&nbsp;
															<font color="blue"><s:text name="open.close"/></font>&nbsp;]
														</label>
													</div>
													<div style="display: none;">
														<table class="FormtableCon">
															<tr>
																<td>
																	<label>
																		<s:text name="imported.excel"/>:
																	</label>
																</td>
																<td colspan="2">
																	<input type="file" style="float: left;" name="upload" id="file" size="30" />
																	<a id="BtnPreview" class="zPushBtn" href="<%=basePath%>samtest/labSamTest/exportLabSamTest.action?labSamTestBeatchVo.id=${labSamTestBeatchVo.id}"><img height="20" width="20" src="<%=basePath%>img/icon_excel.gif" /><b><s:text name="check.bill.download"/></b> </a><span style="color: red; font-size: 12px;"><strong>（<s:text name="tip.down"></s:text>）</strong> </span>
																	<l:a uri="samtest/labSamTest/updateLabSamTest4Validate.action" onclick="submitvalueforlist();" value="checking.value.upload" />
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="checking.item"/>：
																	</label>
																</td>
																<td>
																	<s:iterator value="#request.listLabSamTestVo[0]" id="st">
																		<s:iterator value="#st.listTitle" status="stt">
																					${demo1}&nbsp;&nbsp;&nbsp;&nbsp;
																		</s:iterator>
																	</s:iterator>
																</td>
																<td>

																</td>
																<td>

																</td>
															</tr>

														</table>
													</div>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle">
														<span style="cursor: hand;" onclick="$(this).parent().next().toggle();"><s:text name="origin.upload="/></span>
														<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
															[&nbsp;
															<font color="blue"><s:text name="open.close"/></font>&nbsp;]
														</label>
													</div>
													<div style="display: none;">
														<table class="FormtableCon">
															<tr>
																<td>
																	<label>
																		<s:text name="checking.item"/>：
																	</label>
																</td>
																<td colspan="2">
																	<s:iterator value="#request.listLabSamTestVo[0]" id="st">
																		<s:iterator value="#st.listTitle" status="stt">
																					${demo1}&nbsp;&nbsp;&nbsp;&nbsp;
																		</s:iterator>
																	</s:iterator>
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="origin.bill"/>：
																	</label>
																</td>
																<td colspan="2">
																	<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labSamTestBeatchVo.id}','lab_testDate');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="uploaded"></s:text></b> </a>
																	<s:if test="${fn:length(loadList)>0}">
																		<s:iterator status="st2" value="#request.loadList" id="">
																			<span> <a href="${path }" id="fileId">${name } </a> <a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id}')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
																		</s:iterator>&nbsp;&nbsp;&nbsp;
																		</s:if>
																	<div id="upfiles"></div>
																</td>
															</tr>
															<tr>
																<td>
																	<label>
																		<s:text name="attachment.upload"/>：
																	</label>
																</td>
																<td colspan="2">
																	<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile1('${labSamTestBeatchVo.id}','lab_testDate_fj');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="uploaded"></s:text></b> </a>
																	<s:if test="${fn:length(loadList1)>0}">
																		<s:iterator status="st2" value="#request.loadList1" id="">
																			<span> <a href="${path }" id="fileId">${name } </a> <a href="javascript:;" id="fileIcon" onclick="deleteUploadFile(this,'${id}')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
																		</s:iterator>&nbsp;&nbsp;&nbsp;
																		</s:if>
																	<div id="upfiles1"></div>
																</td>
															</tr>
														</table>
													</div>
												</div>
											</div>
										</div>
									</form>
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
	</body>
	<script>
			$(function(){
				$('.myworkingboxttable tr:gt(0)').each(function(){
					if($(this).find('input[name*=value]').val()!=''){
						validateVal($(this).find('input[name*=value]'),'0');
					}
				});
			});
		</script>

</html>
