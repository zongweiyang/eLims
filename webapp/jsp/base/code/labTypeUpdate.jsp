<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
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
	<script> 
    function ajaxIsExistName(checkStr,flag)
		{
			var msg="";
			var name1=$('#namex').val();
		   var url = '<%=basePath%>code/labType/ajaxIsExsitLabType.action'; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'flag':flag,'checkStr':checkStr},
				dataType:'text',
				success:function(data){
					if(data=='1'&& checkStr!=name1){
						validate.tip('<s:property value="getText('lab.common.repeatname')"/>',$('#functionId'));
						$('#name').val(name1);
					}
				}
	   		});	
		}
		function ajaxIsExistCode(checkStr,flag)
		{
			var msg="";
			var code1=$('#codex').val();
		   var url = '<%=basePath%>code/labType/ajaxIsExsitLabType.action'; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'flag':flag,'checkStr':checkStr},
				dataType:'text',
				success:function(data){
					if(data=='1'&& checkStr!=code1){
						validate.tip('<s:property value="getText('lab.common.repeatid')"/>',$('#functionId'));
						$('#code').val(code1);
					}
				}
	      	});	
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
								<span><s:text name="lab.code.modify"></s:text></span>
							</h2>
						</div>
						<form action="" method="post" name="commtypeform">
							<input type="hidden" name="labTypeVo.id" id="id" size="20"
								value="${labTypeVo.id }" />

							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<l:a uri="back" value="lab.code.return" />
													</td>
													<td>
														<l:a uri="code/labType/updateLabType.action" img="/img/add.gif" value="lab.code.save" />
													</td>

												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle" onclick="$(this).next().toggle()"
									style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
									<span><s:text name="lab.commoncode.info"/></span>
								</div>
								<div class="TabTable"
									style="padding-top: 0; margin: 0; background: none;">
									<table class="tabtableboxtable">
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.type.name"/>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.name" valType="required,strLength"
													max="32" strLength-msg='<s:property value="getText('lab.strlength.msg')"/>' msg='<s:property value="getText('lab.msg.name')"/>' id="name"
													type="text" size="20" value="${labTypeVo.name }" onblur="ajaxIsExistName($('#name').val(),'0');"/>
												<input  id="namex" type="hidden"
													size="20" value="${labTypeVo.name}" />
											</td>

											<td class="w150">
												<label>
													<s:text name="lab.type.id"/>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.code" valType="required,strLength"
													max="32" strLength-msg='<s:property value="getText('lab.strlength.number')"/>' msg='<s:property value="getText('lab.msg.number')"/>' id="code"
													type="text" size="20" value="${labTypeVo.code}" onblur="ajaxIsExistCode($('#code').val(),'1');"/>
												<input  id="codex" type="hidden"
													size="20" value="${labTypeVo.code}" />
											</td>
											<s:if test="${sessionScope.SessionContainer.type!='FRONT'}">
										</tr>
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.front.show"/>：
												</label>
											</td>
											<td>
												<input style="border: 0px;" type="radio"
													name="labTypeVo.showType"
													<s:if test="${labTypeVo.showType=='Y'}">checked="checked"</s:if>
													value="Y" />
												<span style="width: 100px;"><s:text name="lab.yes"/></span>
												<input style="border: 0px;" type="radio"
													name="labTypeVo.showType"
													<s:if test="${labTypeVo.showType=='N'}">checked="checked"</s:if>
													value="N" />
												<span style="width: 100px;"><s:text name="lab.no"/></span>
											</td>
											</s:if>
											<td class="w150">
												<label>
													<s:text name="lab.sequence"/>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.sort" valType="required,strLength"
													max="11" strLength-msg='<s:property value="getText('lab.strlength.seq')"/>' msg='<s:property value="getText('lab.msg.seq')"/>' id="sort"
													type="text" size="20" value="${labTypeVo.sort}" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.remark"></s:text>：
												</label>
											</td>
											<td colspan="3">
												<textarea rows="3" cols="60" name="labTypeVo.remark"
													valType="strLength" max="128" strLength-msg='<s:property value="getText('lab.length.over')"/>'>${labTypeVo.remark}</textarea>
											</td>
										</tr>
									</table>
								</div>
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
	</body>
</html>