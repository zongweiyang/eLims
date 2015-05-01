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
			url:'<%=basePath%>/org/labOrg/isRequiredName.action',
			data:{'name':name},
			type:'post',
			dataType:'text',
			success:function (data){
				if(data=="1"){
					validate.tip('<s:property value="getText('org.input.error')" />',$("#name"));
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
								<span><s:text name=""></s:text></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="sysOrgForm" target="workarea">
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
															<l:a uri="back" value="msg.back"/>
														</td>
														<td>
															<l:a uri="org/labOrg/updateLabOrg.action" onclick="submitvalue('updateLabOrg.action');return false;" img="/img/add.gif" value="lab.code.save"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="org.info"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="config.name"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.name"  valType="required,strLength"  max="10" msg='<s:property value="getText('input10word')"/>'  id="name" type="text" size="20" value="${labOrgVo.name }" onblur="checkOrgname(this);" />
											</td>
											<td>
												<label>
													<s:text name="org.people"></s:text>&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.linkMan" valType="strLength"  max="10" msg='<s:property value="getText('input10word')"/>' id="linkman" type="text" size="20" value="${labOrgVo.linkMan}" maxlength="8" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="lab.sequence"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.sort" valType="required,numBetween" min="0" id="sort" type="text" value="${labOrgVo.sort}"/>
											</td>
											<td>
												<label>
													<s:text name="org.type"></s:text>：
												</label>
											</td>
											<td>
												<s:select list="#request.listLabCode" cssStyle="width:153px" headerKey="0" headerValue="------请选择-----" onchange="checkLab(this)"  value="'${labOrgVo.typeId}|${labOrgVo.type}'"   theme="simple" listValue="name" listKey="id+'|'+name" ></s:select>
												<input type="hidden" id="typeId" name="labOrgVo.typeId" valType="required" msg='<s:property value="getText('selectorgtype')"/>' value="${labOrgVo.typeId}"  />
												<input type="hidden" id="typeName"  name="labOrgVo.type"  value="${labOrgVo.type}"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="address"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.address" id="address" type="text"  value="${labOrgVo.address }" />
											</td>
											<td>
												<label>
													<s:text name="postcode"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.post" id="post" type="text"  valType="zipCode"  value="${labOrgVo.post }" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="telphone"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.telephone" id="telephone" valType="phone" min="0" type="text"  value="${labOrgVo.telephone}" />
											</td>
											<td>
												<label>
													<s:text name="fax"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.fax" id="fax" type="text" valType="fax"   value="${labOrgVo.fax }" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="network"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.website" id="website" valType="url" type="text"  value="${labOrgVo.website }" />
											</td>
											<td>
												<label>
													<s:text name="email"></s:text>：
												</label>
											</td>
											<td colspan="3">
												<input name="labOrgVo.email" id="email" type="text" valType="email"  value="${labOrgVo.email }" />
												<label>
													&nbsp;
												</label>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="can.use"></s:text>：
												</label>
											</td>
											<td class="radiollabel">
												
												<input type="radio" name="labOrgVo.isUsed" <s:if test="${labOrgVo.isUsed=='Y'}">checked="checked"</s:if> value="Y" />
													<s:text name="lab.yes"></s:text>
													<input type="radio" name="labOrgVo.isUsed" <s:if test="${labOrgVo.isUsed=='N'}">checked="checked"</s:if> value="N"  />
													<s:text name="lab.no"></s:text>
											</td>
											<td>
												<label>
												<s:text name="org.level"></s:text>：
												</label>
											</td>
											<td>
												${labOrgVo.rank}
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labOrgVo.remark" id="remark" cols="50" rows="3"
														label="备注">${labOrgVo.remark}</textarea>
											</td>
											<td>
											</td>
											<td></td>
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
