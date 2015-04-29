<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
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
</style>
	</head>
<Script>
function showRegister4Confirm(){
	var msg=ajax4labSampItem();
	if(msg!=''){
		validate.tip(msg,$('#labCustomerName'));
		return false;
	}
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath %>sample/labSampRegister/showLabSampRegister4Confirm.action?labSampRegisterVo.id=${labSampRegisterVo.id}',
		title:'登记单信息',
		opacity:0.4,
		width:1000, 
		height:600,
		lock:true,
		max:false,
		min:false
	 });
}
function submitValuex(url){
	$('form').attr('action','${basePath}'+url);
	$('form').submit();
}
function ajax4labSampItem(){
	var msg='';
	var uri='sample/labSampRegister/ajaxLabSampItem4Exsit.action';
	$.ajax({
   	  url:'<%=basePath%>'+uri,
   	  data:{'labSampRegisterVo.id':'${labSampRegisterVo.id}'},
   	  type:'post',
	  dataType:'text',
	  async:false,
   	  success:function (data){
   	  	if(data!="true"){
           msg='请填写并保存检测内容后再进行提交.';
        }
   	  },
   	  error:function (data){
   	  	alert('请求错误.');
   	  }
   });
	return msg;
}
function copySampInfo(name,index){
	var nameVal=$('#'+name+index).val();
	var len=$('#sampNum').val();
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
	var len=$('#sampNum').val();
   	len=parseInt(len);
	for(var i=(parseInt(index)+1);i<=len;i++){
		$('#'+name+i).find('option[value="'+nameVal+'"]').attr('selected','selected');
	}
 }
 function showLabItem4Select(index){
	var typeId=$('#samTypeId'+index).val();
	$.dialog({
		id:'id',
		content:'url:'+'<%=basePath %>sample/labSampRegister/showLabItem4Select.action?labItemVo.categoryIds='+typeId+'&labItemVo.index='+index,
		title:'<s:property value="getText('chkiteminfo')"/>',
		opacity:0.4,
		width:800, 
		height:500,
		lock:true,
		max:false,
		min:false
	 });
}
function clearValue(index){
	$('#itemName'+index).val("");
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
											<span><s:text name="lab.code.add"/></span>
										</h2>
									</div>
									<form action="" method="post" name="studentForm">
										<input type="hidden" name="labSampRegisterVo.id" value="${labSampRegisterVo.id}"/>
										<input type="hidden" name="labSampRegisterVo.sampNum" id="sampNum" value="${labSampRegisterVo.sampNum}"/>
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
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab2.action" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab2.action?labSampRegisterVo.auditResult=tab2');" value="lab.code.save"  img="/img/icon/filesave.gif"/>
																</td>
																<td>
																	<l:a uri="sample/labSampRegister/addLabSampRegister4Tab2.action" onclick="showRegister4Confirm();return false;" value="save.commit"  img="/img/icon/filesave.gif"/>
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
													<li id="li01" class="">
														<a href="javascript:;" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab2.action?labSampRegisterVo.auditResult=tab1');"><span><s:text name="base.info"></s:text></span> </a>
													</li>
													<li id="li02" class="currenttab">
														<a href="javascript:;" ><span><s:text name="checking.content"></s:text></span> </a>
													</li>
													<li id="li03" class="">
														<a href="javascript:;" onclick="submitValuex('/sample/labSampRegister/addLabSampRegister4Tab2.action?labSampRegisterVo.auditResult=tab3');"><span><s:text name="checking.fee"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<table class="FormtableCon_sform" cellspacing="1" cellpadding="0">
													<thead>
														<tr>
															<th width="100">
																<s:text name="batch.no"/>
															</th>
															<th width="100">
																<s:text name="origin.number"/>
															</th>
															<th width="190">
																<s:text name="sam.name"/>
															</th>
															<th width="110">
																<s:text name="sam.style"/>
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
																	<input type="text" name="sampList[${st.index}].sampCode" value="${sampCode}" size="10" readonly="readonly" style="background-color: #DDDDDD"/>
																</td>
																<td width="100">
																	<input type="text" name="sampList[${st.index}].oldNo" value="${oldNo}" size="10"/>
																</td>
																<td>
																	<input type="text" name="sampList[${st.index}].name" id="sampName${st.index}" value="${name}"/>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('sampName','${st.index}')"/>
																</td>
																<td>
																	<s:select list="#request.samTypeList" listKey="id" listValue="name" theme="simple" name="sampList[${st.index}].samTypeId" id="samTypeId${st.index}" onchange="clearValue('${st.index}');"></s:select>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('samTypeId','${st.index}')"/>
																</td>
																<td>
																	<textarea style="width: 90%;overflow:hidden;" name="sampList[${st.index}].itemName" id="itemName${st.index}" onclick="showLabItem4Select('${st.index}');" readonly="readonly">${itemName}</textarea>
																	<input type="hidden" name="sampList[${st.index}].itemId" value="${itemId}" id="itemId${st.index}"/>
																	<img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copySampInfo('itemName','${st.index}')"/>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
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
</html>
