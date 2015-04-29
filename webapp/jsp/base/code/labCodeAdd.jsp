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
	<script language=javascript> 
   
		function ajaxIsExistName(checkStr,flag,typeid)
		{
     	   var msg="";
		    var url = '<%=basePath%>code/labCode/ajaxIsExsitLabCode.action?checkStr='+checkStr+'&typeid='+typeid+'&flag='+flag; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'fileId':flag},
				dataType:'text',
				success:function(data){
					if(data=='1'){
						validate.tip('<s:property value="getText('lab.codeinputname.repeat')"/>',$('#functionId'));
						$('#name').val("");
					}
				}
	   		});	
		}
		
		function ajaxIsExistCode(checkStr,typeid,flag)
		{
	      var msg="";
		   var url = '<%=basePath%>code/labCode/ajaxIsExsitLabCode.action?checkStr='+checkStr+'&typeid='+typeid+'&flag='+flag; 
		   $.ajax({
				url:url,
				type:'POST',
				async:false,
				data:{'fileId':flag},
				dataType:'text',
				success:function(data){
					if(data=='1'){
						validate.tip('<s:property value="getText('lab.codeinputid.repeat')"/>',$('#functionId'));
						$('#code').val("");
					}
				}
	      	});	
		}
		
		function fanhui(url){
		   var id=$('#typeid').val();
			window.location.href='<%=basePath%>'+url+id;
		}			
		function save(actionstr){
			$('form').attr('action',actionstr);
			$('form').submit();
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
								<span><s:text name="lab.codemaintain.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="commcodeform">
							<input type="hidden" name="labCodeVo.typeid" id="typeid"
								value="${labCodeVo.typeid }" />

							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn"
															href="javascript:void();"
															onclick="fanhui('code/labCode/listLabCode.action?labCodeVo.typeid=');return false;"><img
																height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="lab.code.return"></s:text></b>
														</a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn"
															href="javascript:void();"
															onclick="save('addLabCode.action');return false;"><img
																height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"></s:text></b>
														</a>
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
									<span><s:text name="lab.code.info"/></span>
								</div>
								<div class="TabTable"
									style="padding-top: 0; margin: 0; background: none;">
									<table class="tabtableboxtable">
										<tr>
											<td class="w150">
												<label>
													<s:text name="lab.code.name"></s:text>：
												</label>
											</td>
											<td>
												<input name="labCodeVo.name" valType="required,strLength"
													max="32" strLength-msg='<s:property value="getText('lab.strlength.msg')"/>' msg='<s:property value="getText('lab.msg.name')"/>' id="name"
													type="text" size="20" value="${labCodeVo.name }" onblur="ajaxIsExistName($('#name').val(),'0','${labCodeVo.typeid }');"/>
											</td>

											<td class="w150">
												<label>
													<s:text name="lab.code.number"></s:text>：
												</label>
											</td>
											<td>
												<input name="labCodeVo.code" valType="required,strLength" 
												msg='<s:property value="getText('lab.msg.number')"/>' id="code" strLength-msg='<s:property value="getText('lab.strlength.number')"/>'
													type="text" size="20" value="${labCodeVo.code }" onblur="ajaxIsExistCode($('#code').val(),'${labCodeVo.typeid }','1');"/>
											</td>
											<td class="w150">
												<label>
													<s:text name="lab.sequence"></s:text>：
												</label>
											</td>
											<td>
												<input name="labCodeVo.sort" valType="required,strLength"
													max="11" strLength-msg='<s:property value="getText('lab.strlength.seq')"/>' msg='<s:property value="getText('lab.mag.seq')"/>' id="sort"
													type="text" size="20" value="${labCodeVo.sort}" />
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