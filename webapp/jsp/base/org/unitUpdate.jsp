<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
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
	<script language=javascript> 
	$(function(){
		window.validate.validateTypes.url = function($obj){
		str_url = $obj.val();
		var strRegex = new RegExp("^((https|http|ftp|rtsp|mms://)?)" + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|" + "([0-9a-z_!~*'()-]+\\.)*" + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." + "[a-z]{2,6})" + "(:[0-9]{1,4})?" + "((/?)|" + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
		//re.test()
		if (strRegex.test(str_url)){
			return true;
		}else{
			var sMessage = validate.getMessage($obj,"url","url");
			validate.tip(sMessage,$obj);
			return false;
		}
									
	}
	})
	
	function checkOrgname(){
		var name=$("#name").val();
		if(name==''){
			return ;
		}
		var oldName='${labOrgVo.name}';
			if(name==oldName){
			return ;
		}
		$.ajax({
			url:'<%=basePath%>org/labOrg/isRequiredName.action',
			data:{'name':name},
			type:'post',
			dataType:'text',
			success:function (data){
				if(data=="1"){
					alert('<s:property value="getText('org.input.error')"/>');
				}
		   	  }
		  });
	}
	function checkLab(obj){
			var thisVal = $(obj).val();
			var name=$(obj).val();;
			var oo=name.split('|');
			$('#typeId').val(oo[0]);
			$('#typeName').val(oo[1]);
	}
	function submitvalue(actionstr){
		var df = document.sysOrgForm;
		df.action=actionstr;
		df.submit();
	}
	function uploadFile(busId,busType){
	  fileTypes = '*.jpg;*.gif;*.png;';
	   var url='<%=basePath%>utils/upload/labUpLoad.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&showType=show';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('logo.upload')"/>',
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
							   alert('<s:property value="getText('deleted.well')"/>');
							   $(obj).parent().remove();
						   }
					   }
				});	
		   }
		</script>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="sysOrgForm" id="form" target="workarea">
							<input type="hidden" value="${labOrgVo.id}" name="labOrgVo.id" />
							<input type="hidden" name="labOrgVo.parentId" id="parentId" value="${labOrgVo.parentId}" />
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="org/labOrg/updateLabUnit.action" onclick="goAction('org/labOrg/updateLabUnit.action');return false;" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="dep.info"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="dep.name"></s:text>：
												</label>
											</td>
											<td>
												<input name="labOrgVo.name" valType="required,strLength" max="10" msg='<s:property value="getText('input10word')"/>' style="width: 300px;" id="name" type="text" size="20" value="${labOrgVo.name }" onblur="checkOrgname(this);" />
											</td>
											<td>
												<label>
													<s:text name="dep.faren"></s:text>：
												</label>
											</td>
											<td>
												<input name="labOrgVo.linkMan" valType="strLength" max="10" msg='<s:property value="getText('input10word')"/>' style="width: 300px;" id="linkman" type="text" size="20" value="${labOrgVo.linkMan}" maxlength="8" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="dep.code"></s:text>：
												</label>
											</td>
											<td>
												<input name="labOrgVo.code" valType="strLength" min="0" max="9" msg="长度必须小于或等于9" id="sort" style="width: 300px;" type="text" value="${labOrgVo.code}" />
											</td>
											<td>
												<label>
													<s:text name="dep.type"></s:text>：
												</label>
											</td>
											<td>
												<s:select list="#request.listLabCode" cssStyle="width:300px" headerKey="" headerValue="------请选择-----" onchange="checkLab(this)" value="'${labOrgVo.typeId}|${labOrgVo.type}'" theme="simple" listValue="name" listKey="id+'|'+name"></s:select>
												<input type="hidden" id="typeId" name="labOrgVo.typeId" valType="required" msg="请选择公司类型" value="${labOrgVo.typeId}" />
												<input type="hidden" id="typeName" name="labOrgVo.type" value="${labOrgVo.type}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="dep.addr"></s:text>：
												</label>
											</td>
											<td>
												<input name="labOrgVo.address" id="address" type="text" style="width: 300px;" value="${labOrgVo.address }" />
											</td>
											<td>
												<label>
													<s:text name="postcode"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.post" id="post" type="text" style="width: 300px;" valType="zipCode" value="${labOrgVo.post }" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="telphone"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.telephone" id="telephone" style="width: 300px;" valType="phone" min="0" type="text" value="${labOrgVo.telephone}" />
											</td>
											<td>
												<label>
													<s:text name="fax"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.fax" id="fax" type="text" valType="fax" style="width: 300px;" value="${labOrgVo.fax }" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="network"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.website" id="website" valType="url" msg="请填写正确格式网址" style="width: 300px;" type="text" value="${labOrgVo.website }" />
											</td>
											<td>
												<label>
													<s:text name="email"></s:text>：
												</label>
											</td>
											<td colspan="3">
												<input name="labOrgVo.email" id="email" type="text" style="width: 300px;" valType="email" value="${labOrgVo.email }" />
												<label>
													&nbsp;
												</label>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="logo"></s:text>：
												</label>
											</td>
											<td>
												<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labOrgVo.id}','sysLogo');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
												<input name="labOrgVo.logo" id="logo" type="hidden" value="${labOrgVo.logo}" />
											</td>
											<td>
												<label>
													<s:text name="remark"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<textarea name="labOrgVo.remark" id="remark" cols="46" rows="3" label="备注">${labOrgVo.remark}</textarea>
											</td>
										</tr>
										<tr>
											<td></td>
											<td colspan="3">
												<div id="upfiles" style="width:300px; height:80px;"><span> <img src="<%=basePath%>${labOrgVo.logo}"   width="100%" height="100%" /> </span></div>
											</td>
										</tr> 	
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="config.index"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="inface.style"></s:text>：
												</label>
											</td>
											<td class="radiollabel">
												<input style="border: 0px;" type="radio" name="labOrgVo.manner" value="Y" disabled="true" />
												<span style="width: 100px;"><s:text name="blue.style"></s:text></span>
												<input style="border: 0px;" type="radio" name="labOrgVo.manner" checked="checked" value="N" disabled="true" />
												<span style="width: 100px;"><s:text name="green.style"></s:text></span>
											</td>
											<td>
												<label>
												</label>
											</td>
											<td>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
