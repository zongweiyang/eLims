<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page import="cn.labsoft.labos.utils.SysOut"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
.TabTable {
	padding-top: 0;margin:0;background:none;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
.Formtabletitle {cursor:pointer;}
</style>
<script>
	function addComType(actionstr)
    {
		var url  = actionstr;
		$.dialog({
			id:'selectTaskType',
			content:'url:'+url,
			title:'<s:property value="getText('lab.edit.type')"/>',
			opacity:0.4,
			width:700,
			height:400,
			lock:true
		 });
	}
	function addType(type){
	  	var url  = '<%=basePath%>/code/labType/preAddLabType4Code.action?labTypeVo.type='+encodeURIComponent(type);
		$.dialog({
			id:'selectTaskType',
			content:'url:'+url,
			title:'<s:property value="getText('lab.edit.type')"/>',
			opacity:0.4,
			width:700,
			height:400,
			lock:true
		 });
	}
	function updateCode(id){
	  	var url  = '<%=basePath%>/code/labCode/showListLabCode.action?labCodeVo.typeid='+id;
		$.dialog({
			id:'selectTaskType',
			content:'url:'+url,
			title:'<s:property value="getText('lab.edit.commoncode')"/>',
			opacity:0.4,
			width:700,
			height:400,
			lock:true
		 });
	}
	
	function toggle(obj){
		$(obj).next().toggle();
	}
	
	function toogleAll(){
		$('.Formtabletitle').next().toggle();
	}
</script>
	</head>
	<body class="" id="mainid" >
	   <table id="bodytable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
      		<tr>
					<td class="MLimg" ></td>
       				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								<s:text name="lab.common.code"/>:
								<span><s:text name="lab.code.edit"/></span>
							</h2>
						</div>
						<table class="pb_list" cellspacing="0" cellpadding="0" border="0" style="width:99%">
						<s:set name = "col" value="${fn:length(typeList)}" />
							<tr>
							<td colspan="${col}">
								<a style="margin-left: 40px;margin-top: 5px" id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="toogleAll()"><img height="20" width="20" src="${basePath}/img/shuaxin.gif"/><b><s:text name="lab.onekey.change"></s:text></b></a>
								
								<s:if test="${sessionScope.SessionContainer.type!='FRONT'}">
								<a style="margin-left: 40px;margin-top: 5px" id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="addComType('<%=basePath%>/code/labType/preAddLabType4Code.action')"><img height="20" width="20" src="${basePath}/img/shuaxin.gif"/><b><s:text name="lab.newadd.code"></s:text></b></a>							
								</s:if>
							</td>
						<tr>
						<tr>
						<s:iterator value="typeList" status="s" id="sy">
							<td style="width:${100/col}%">
							<s:iterator value="#sy.list" status="st" id="s1">
							<div class="Formtable">
								<div class="Formtabletitle" onclick="toggle(this);" title='<s:property value="getText('lab.click.here')"/>'>
									<span>${s1.type} ${fn:length(list)}</span>
								</div>
								<div style="display: none" class="toogle_div">
									<table class="FormtableCon" cellspacing="0" cellpadding="0">
										
												<s:iterator value="#s1.list" status="st2" id="s2">
													<tr> 
														<td class="c">${st2.index+1 }</td>
														<td>${s2.name }</td>
														<td>${s2.code }</td>
														<td class="c">${fn:length(s2.codeList)}</td>
														<td class="c"><a href="javascript:;" onclick="updateCode('${id}')"><s:text name="lab.setting"></s:text></a></td>
													</tr>
												</s:iterator>
											
											<tr>
												<td colspan="5" class="c" style="text-align:center;">
														<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="addType('${type}')"><img height="20" width="20" src="${basePath}/img/tianjia.gif"/><b><s:text name="lab.code.add"></s:text></b></a>
	                     						 </td>
											</tr>
									 </table>
								</div>
							</div>
						</s:iterator>
						</td>
						</s:iterator>
				</tr>
			</table>
			<td class="MRimg"></td>
		</tr>
  </table>
</body>
</html>
