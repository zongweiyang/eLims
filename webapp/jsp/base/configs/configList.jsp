<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
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
<script>
function addOneRow(obj){
	var table=$('#tableContent');
	var index=table.find('tr').length;
	if(index > 1){
		index = parseInt(index);
	}else{
		index=0;
	}
	if(isNaN(index)){
		index=0;
	}
	var trHtml='<tr>';
	trHtml+='<td class="c">'+index+'</td>';
	trHtml+='<td><input name="labConfigVo.name"  id="configName" value="" size="25" type="text"></td>';
	trHtml+='<td><input name="labConfigVo.code" id="configCode" value="" size="20" type="text"  onblur="hasThisCode(this)"></td>';
	trHtml+='<td><select name="labConfigVo.category" id="configCategory"  ><option value="0">'+'<s:property value="getText('config.parameters')"/>'+'</option><option value="1">'+'<s:property value="getText('config.preference')"/>'+'</option></select></td>';
	trHtml+='<td><input name="labConfigVo.initValue" id="configInitValue" value="" size="25" type="text"></td>';
	trHtml+='<td><input name="labConfigVo.value" id="configValue" value="" size="25" type="text"></td>';
	trHtml+='<td><input name="labConfigVo.desc" value="" size="30" type="text"></td>';
	trHtml+='<td><a href="javascript:;" onclick="saveOne();return false;">'+'<s:property value="getText('config.save')"/>'+'</a>'+
	'&nbsp;&nbsp;<a href="javascript:;" onclick="flushThisPage();return false;">'+'<s:property value="getText('config.cancel')"/>'+'</a></td>';
	trHtml+='</tr>';
	table.append(trHtml);
	$(obj).attr('disabled','true');
}
function deleteOne(id){
	if(confirm('<s:property value="getText('config.deleteisrist')"/>')){
		$('form').attr('action','${basePath}/configs/labConfig/delLabConfig.action?id='+id);
		$('form').submit();
	}
}
function flushThisPage(){
	window.location.href=window.location.href;
}
function saveOne(){
	var id=$('input[name="labConfigVo.id"]').val();
	var configName=$('#configName').val();
	if(configName.length<=0){
		alert('<s:property value="getText('config.name.empty')"/>');
		return false;
	}
	if(configName.length>=64){
		alert('<s:property value="getText('config.name.over')"/>');
		return false;
	}
	var configCode=$('#configCode').val();
	if(configCode.length<=0){
		alert('<s:property value="getText('config.code.empty')"/>');
		return false;
	}
	if(configCode.length>64){
		alert('<s:property value="getText('config.code.over')"/>');
		return false;
	}
	var msg=ajaxLabConfig4Code(id,configCode);
	if(msg.length>0){
		alert(msg);
		return false;
	}
	var configValue=$('#configValue').val();
	if(configValue.length<=0){
		alert('<s:property value="getText('config.value.empty')"/>');
		return false;
	}
	if(configValue.length>64){
		alert('<s:property value="getText('config.value.over')"/>');
		return false;
	}
	$('form').attr('action','${basePath}/configs/labConfig/addLabConfig.action');
	$('form').submit();
}
function initSysterm(){
	if(confirm('<s:property value="getText('config.firstinit.ops')"/>')){
		$.ajax({
		   	  url:'<%=basePath%>/configs/labConfig/initLabConfig4Value.action',
		   	  data:{'id':'id'},
		   	  type:'post',
			  dataType:'text',
		   	  success:function (data){
		   	  	if(data.indexOf("true")!=-1){
	            	alert('<s:property value="getText('config.system.initsuccess')"/>');
	          	 }else{
	             	alert('<s:property value="getText('config.system.initfail')"/>');
	             }
		   	  },
		   	  error:function (data){
		   	  	alert('<s:property value="getText('config.request.error')"/>');
		   	  }
		});
	}
}
function hasThisCode(obj){
	var id=$('input[name="labConfigVo.id"]').val();
	var code=$(obj).val();
	var msg=ajaxLabConfig4Code(id,code);
	if(msg.length>0){
		alert(msg);
		$(obj).val('');
		return false;
	}
}
function ajaxLabConfig4Code(id,code){
	var msg="";
	$.ajax({
	   	  url:'<%=basePath%>/configs/labConfig/ajaxLabConfig4Code.action',
	   	  data:{'code':code,'id':id},
	   	  type:'post',
		  dataType:'text',
		  async:false,
	   	  success:function (data){
	   	  	if(data=="true"||data==true){
            	msg='<s:property value="getText('config.code.repeat')"/>';
          	 }
	   	  },
	   	  error:function (data){
	   	  	alert('<s:property value="getText('config.request.error')"/>');
	   	  }
	});
	return msg;
}
function updateOne(){
	var id=$('input[name="labConfigVo.id"]').val();
	
	var configValue=$('#configValue').val();
	if(configValue.length<=0){
		alert('<s:property value="getText('config.value.empty')"/>');
		return false;
	}
	if(configValue.length>64){
		alert('<s:property value="getText('config.value.over')"/>');
		return false;
	}
	$('form').attr('action','${basePath}/configs/labConfig/updateLabConfig.action');
	$('form').submit();
}
function preUpdate(index,obj,id){
	var idValue=$('input[name="labConfigVo.id"]').val();
	if(idValue!=undefined&&idValue!='undefined'&&idValue!=''){
		alert('<s:property value="getText('config.savepre.modify')"/>');
		return false;
	}
	var name=$(obj).parent().prev().prev().prev().prev().prev().prev().html();
	var code=$(obj).parent().prev().prev().prev().prev().prev().html();
	var category=$(obj).parent().prev().prev().prev().prev().html();
	var initValue=$(obj).parent().prev().prev().html();
	var value=$(obj).parent().prev().prev().html();
	var desc=$(obj).parent().prev().html();
	var trHtml='';
	trHtml+='<td class="c">'+index+'<input name="labConfigVo.id"  value="'+id+'" type="hidden"></td>';
	trHtml+='<td>'+name+'</td>';
	trHtml+='<td>'+code+'</td>';
	trHtml+='<td>'+category+'</td>';
	trHtml+='<td>'+initValue+'</td>';
	trHtml+='<td><input name="labConfigVo.value" id="configValue" value="'+value+'" size="25" type="text"></td>';
	trHtml+='<td><input name="labConfigVo.desc" value="'+desc+'" size="30" type="text"></td>';
	trHtml+='<td><a href="javascript:;" onclick="updateOne();return false;">'+'<s:property value="getText('config.save')"/>'+'</a>'+
	'&nbsp;&nbsp;<a href="javascript:;" onclick="flushThisPage();return false;">'+'<s:property value="getText('config.cancel')"/>'+'</a></td>';
	$(obj).parent().parent().html(trHtml)
}
</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form theme="simple" action="" method="post" name="labRoleForm">
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

						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}：
												<span><s:text name="config.index"/></span>
											</h2>
										</div>
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript" onclick="initSysterm();return false;"><img src="<%=basePath %>img/xinjian.gif"/><b><s:text name="config.system.init"/></b></a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript" onclick="addOneRow(this);return false;"><img src="<%=basePath %>img/xinjian.gif"/><b><s:text name="config.add.row"></s:text></b></a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0" cellpadding="0" id="tableContent">
											<thead>
												<tr>
													<th width="50">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="config.name"></s:text>
													</th>
													<th>
														<s:text name="config.number"/>
													</th>
													<th>
														<s:text name="config.type"></s:text>
													</th>
													
													<th>
														<s:text name="config.init.value"/>
													</th>
													<th>
														<s:text name="config.value"></s:text>
													</th>
													<th>
														<s:text name="config.desc"/>
													</th>
													<th width="70">
														<s:text name="config.ops"/>
													</th>
											</thead>
											<tbody>
												<s:iterator value="configList" status="st">
													<tr>
														<td class="c">
															${st.index+1}
														</td>
														<td class="1">
															${name}
														</td>
														<td class="l">
															${code}
														</td>
														<td class="l">
														<s:if test="${category=='0'}">
														<s:text name="config.parameters"/>
														</s:if>
														<s:elseif test="${category=='1'}">
														<s:text name="config.preference"/>
														</s:elseif>
														</td>
														<td class="l">
															${initValue}
														</td>
														<td class="l">
															${value}
														</td>
														<td class="1" >
															${desc}
														</td>
														<td class="c">
															<a href="javascript:;" onclick="preUpdate('${st.index+1}',this,'${id}');return false;"><s:text name="config.modify"></s:text></a>
															<a href="javascript:;" onclick="deleteOne('${id}');return false;"><s:text name="config.del"/></a>
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
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
</html>
