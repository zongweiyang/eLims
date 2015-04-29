<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	<script language=javascript>
	    function ajaxIsExistName(checkStr,flag)
		{
			var msg="";
		   var url = '<%=basePath%>code/labType/ajaxIsExsitLabType.action'; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'flag':flag,'checkStr':checkStr},
				dataType:'text',
				success:function(data){
					if(data=='1'){
						validate.tip('<s:property value="getText('lab.common.repeatname')"/>',$('#functionId'));
						$('#name').val("");
					}
				}
	   		});	
		}
		function ajaxIsExistCode(checkStr,flag)
		{
			var msg="";
		   var url = '<%=basePath%>code/labType/ajaxIsExsitLabType.action'; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'flag':flag,'checkStr':checkStr},
				dataType:'text',
				success:function(data){
					if(data=='1'){
						validate.tip('<s:property value="getText('lab.common.repeatid')"/>',$('#functionId'));
						$('#code').val("");
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
								<span><s:text name="lab.code.add"></s:text></span>
							</h2>
						</div>
						<form action="" method="post" name="commtypeform" target="workarea">
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<l:a uri="back" value="lab.code.return"></l:a>
													</td>
													<td>
														<l:a uri="code/labType/addLabType4Code.action" value="lab.code.save"/>
													</td>

												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>

							<!-- 表单型表格（用于新增/修改页面） 开始 -->
							<div class="Formtable">
								<div class="Formtabletitle" onclick="$(this).next().toggle()"
									style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
									<span><s:text name="lab.commoncode.info"/></span>
								</div>
								<div class="TabTable"
									style="padding-top: 0; margin: 0; background: none;">
									<table class="tabtableboxtable">
										<tr class="w150">
											<td>
												<label>
													<s:text name="lab.common.module"/>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.type" id="type" type="text" size="20"
													value="${labTypeVo.type }" />
											</td>
										<tr>
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
											</td>

											<td class="w150">
												<label>
													<s:text name="lab.type.id"/>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.code" valType="required,strLength"
													max="32" msg='<s:property value="getText('lab.msg.number')"/>' strLength-msg='<s:property value="getText('lab.strlength.number')"/>' id="code"
													type="text" size="20" value="${labTypeVo.code }" onblur="ajaxIsExistCode($('#code').val(),'1');"/>
											</td>
											<s:if test="${sessionScope.SessionContainer.type!='FRONT'}">
										</tr>
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.front.show"></s:text>：
												</label>
											</td>
											<td>
												<input style="border: 0px;" type="radio"
													name="labTypeVo.showType" value="Y" />
												<span style="width: 100px;"><s:text name="lab.yes"/></span>
												<input style="border: 0px;" type="radio"
													name="labTypeVo.showType" value="N" checked="checked" />
												<span style="width: 100px;"><s:text name="lab.no"></s:text></span>
											</td>
											</s:if>
											<td class="w150">
												<label>
													<s:text name="lab.sequence"></s:text>：
												</label>
											</td>
											<td>
												<input name="labTypeVo.sort" 
													valType="required,strLength" max="11"
													strLength-msg='<s:property value="getText('lab.strlength.seq')"/>' msg='<s:property value="getText('lab.msg.seq')"/>' id="sort"
													type="text" size="20" value="${labTypeVo.sort}" />
											</td>
										</tr>
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.remark"/>
												</label>
											</td>
											<td colspan="3">
												<textarea rows="3" cols="60" name="labTypeVo.remark"
													valType="strLength" max="128" strLength-msg='<s:property value="getText('lab.length.over')"/>' value="${labTypeVo.remark}"></textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				</td>
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