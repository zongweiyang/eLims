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
	function checkOrgname(obj){
		var name=$(obj).val();
		if(name==''){
			return ;
		}
		$.ajax({
			url:'<%=basePath%>org/labOrg/isRequiredName.action',
			data:{'labOrgVo.name':name,'labOrgVo.parentId':$("#parentId").val()},
			type:'post',
			dataType:'text',
			success:function (data){
				if(data=="1"){
					validate.tip('<s:property value="getText('org.input.error')"/>',$(obj));
					$(obj).val('');
					return ;
				}
		   	  }
		  });
	}
	function checkLab(obj){
			var thisVal = $(obj).val();
			var name=$(obj).val();;
			if(name=='0'){
				return false;
			}
			var oo=name.split('|');
			$('#typeId').val(oo[0]);
			$('#typeName').val(oo[1]);
	}
	function submitvalue(actionstr){
		$('form').attr('action',actionstr);
		$('form').submit();
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
								<span><s:text name="org.add"></s:text></span>
							</h2>
						</div>
						<form  method="post" name="LabOrgForm" target="workarea">
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
															<l:a uri="org/labOrg/addLabOrg.action" onclick="submitvalue('addLabOrg.action');return false;" value="lab.code.save"/>
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
												<input name="labOrgVo.name" valType="required,strLength"  max="10" msg="名称不可为空并最多输入10个汉字"   id="name" type="text" size="20" onblur="checkOrgname(this);" />

											</td>
											<td>
												<label>
													<s:text name="org.people"/>&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.linkMan" valType="strLength"  max="10" msg='<s:property value="getText('input10word')"/>'  id="linkMan" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="lab.sequence"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.sort" valType="required,numBetween" min="0" id="sort" type="text" value="${labOrgVo.sort}" size="20" />
											</td>
											<td>
												<label>
													<s:text name="org.type"></s:text>：
												</label>
											</td>
											<td>
												<s:select list="#request.listLabCode" cssStyle="width:153px" headerKey="0" headerValue="------请选择-----" onchange="checkLab(this)"   theme="simple" listValue="name" listKey="id+'|'+name" ></s:select>
												<input type="hidden"  id="typeId" valType="required" msg='<s:property value="getText('selectorgtype')"/>' name="labOrgVo.typeId"  />
												<input type="hidden" id="typeName"  name="labOrgVo.type"  />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="address"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.address" id="address" type="text" />
											</td>
											<td>
												<label>
													<s:text name="postcode"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.post" id="post" type="text" size="20" valType="zipCode"  />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="telphone"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.telephone" valType="phone" id="telephone" type="text" />
											</td>
											<td>
												<label>
													<s:text name="fax"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.fax" id="fax" valType="fax" type="text" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="network"></s:text>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												<input name="labOrgVo.website" id="website" type="text" valType="url" msg="请输入正确的网址" />
											</td>
											<td>
												<label>
													<s:text name="email"/>：
												</label>
											</td>
											<td colspan="3">
												<input name="labOrgVo.email" id="email" type="text" valType="email"  />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="can.use"/>：
												</label>
											</td>
											<td class="radiollabel">
													<input type="radio" name="labOrgVo.isUsed" value="Y"
														checked="checked" />
													<s:text name="lab.yes"></s:text>
													<input type="radio" name="labOrgVo.isUsed" value="N" />
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
													<s:text name="remark"></s:text>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labOrgVo.remark" id="remark" cols="50" rows="3"
														label="备注"></textarea>
											</td>
											<td></td>
											<td></td>
										</tr>
										
										<tr>
											<td>
												<s:text name="select.sample1"></s:text>：
											</td>
											<td colspan="5">
												<l:combox name="labOrgVo.typeId" data="/code/labCode/ajaxLabCodeList.action?labCodeVo.code=KIND"  value="ban" id="id" cssStyle="width:153px"/>
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
