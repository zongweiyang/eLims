<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
</style>
	</head>
	<script language=javascript> 
	function submitvalue(actionstr){
		if(null != $('#name').val() && $('#name').val() == ''){
			validate.tip('<s:property value="getText('input.fun.name')"/>',$('#name'));
			return ;
		}
		var df = document.stFunctionform;
		df.target="workarea";
		df.action=actionstr;
		df.submit();
		df.target="docTreeMain";
	}

	function submitvalueforback(actionstr){
		var df = document.stFunctionform;
	  	df.action=actionstr;
	  	df.submit();
	}
	function checkFunNname(obj,formerName){
		if($(obj).val() == formerName){
			return ;
		}
		$.ajax({
			url:'<%=basePath%>function/labFunction/ajaxIsSameFunName.action',
			data:{'labFunctionVo.name':$(obj).val(),'labFunctionVo.parentId':$('#parentId').val()},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data=="true"){
					alert('<s:property value="getText('name.exist')"/>');
					$(obj).val('');
				}
			}
	   });
	} 
	function checkFunCode(obj,code,id){
		if($(obj).val() == code){
			return ;
		}
		$.ajax({
			url:'<%=basePath%>function/labFunction/ajax2IsExistFunCode.action',
			data:{'labFunctionVo.wfcode':$(obj).val(),'labFunctionVo.id':id},
			type:'post',
			dataType:'text',
	  		success:function (data){
	   	  		if(data=="true"){
					alert('<s:property value="getText('code.exist')"/>');
					$(obj).val('');
				}
			}
	   });
	} 
	function changeType(){
		if($('input[name="labFunctionVo.type"]:checked').val() == '1'){
			$('#url').val("/");
			$('#SCLC').html('<s:property value="getText('make.process')"/>');
		}else{
			$('#url').val("/");
			$('#SCLC').html('<s:property value="getText('make.flow')"/>');
		}
	}
	$(function(){
		if($('input[name="labFunctionVo.type"]:checked').val() == '1'){
			$('#SCLC').html('<s:property value="getText('make.process')"/>');
		}else{
			$('#SCLC').html('<s:property value="getText('make.flow')"/>');
		}
	});
	function goToBack(url){
		window.location.href = '<%=basePath%>'+url;
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
								<span><s:text name="lab.code.modify"></s:text></span>
							</h2>
						</div>
						<form action="addLabFunction.action" method="post" name="stFunctionform" theme="simple">
							<input type="hidden" name="labFunctionVo.parentId" id="parentId" value="${labFunctionVo.parentId}" />
							<input type="hidden" name="labFunctionVo.id" id="labFunctionVo_id" value="${labFunctionVo.id}" />
							<div>
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="function/labFunction/listLabFunction.action" onclick="goToBack('function/labFunction/listLabFunction.action?labFunctionVo.parentId=${labFunctionVo.parentId}');" value="lab.code.return" />
														</td>
														<td>
															<l:a uri="function/labFunction/updateLabFunction.action" onclick="submitvalue('updateLabFunction.action');" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="f.info"/></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="function.name"/>：
											</label>
										</td>
										<td>
											<input name="labFunctionVo.name" valType="required,strLength" max="32" strLength-msg="功能名称长度不能超过32位" msg='<s:property value="getText('funnameempty')"/>' id="name" type="text" size="30" value="${labFunctionVo.name}" onblur="checkFunNname(this,'${labFunctionVo.name}');" />
										</td>
										<td>
											<label>
												<s:text name="f.code"/>：
											</label>
										</td>
										<td>
											<input name="labFunctionVo.wfcode" id="wfcode" type="text" size="30" value="${labFunctionVo.wfcode}"  valType="required,strLength"  max="32" strLength-msg='<s:property value="getText('codelength32')"/>' msg='<s:property value="getText('config.code.empty')"/>' onblur="checkFunCode(this,'${labFunctionVo.wfcode}','${labFunctionVo.id}');" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="f.type"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'0':getText('preface'),'1':getText('function')}" name="labFunctionVo.type" onclick="changeType();"></s:radio>
										</td>
										<td>
											<label>
												<s:text name="fun.link"/>：
											</label>
										</td>
										<td>
											<input name="labFunctionVo.url" id="url" type="text" size="30" value="${labFunctionVo.url}" valType="required,url" msg="url为空或格式不正确."/>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="menu.show"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isDisplay == 'N'}">
												<input type="radio" name="labFunctionVo.isDisplay" value="N" checked="checked" id="isDisplay0" /><s:text name="lab.no"/>
												<input type="radio" name="labFunctionVo.isDisplay" value="Y" id="isDisplay1" /><s:text name="lab.yes"/>
											</s:if>
											<s:else>
												<input type="radio" name="labFunctionVo.isDisplay" value="N" id="isDisplay0" /><s:text name="lab.no"/>
												<input type="radio" name="labFunctionVo.isDisplay" value="Y" checked="checked" id="isDisplay1" /><s:text name="lab.yes"/>
											</s:else>
										</td>
										<td>
											<label>
												<s:text name="site.show"/>：
											</label>
										</td>
										<td>
											<input type="checkbox" name="labFunctionVo.isFront" <s:if test="${labFunctionVo.isFront == 'Y'}"> checked="checked"</s:if> value="Y" id="isFrontY" />
											<s:text name="front.show"/>
											<input type="checkbox" name="labFunctionVo.isBack" <s:if test="${labFunctionVo.isBack == 'Y'}"> checked="checked"</s:if> value="Y" id="isBackY" />
											<s:text name="back.show"/>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="icon.link"/>：
											</label>
										</td>
										<td>
											<input name="labFunctionVo.imageUrl" id="imageUrl" type="text" size="30" value="${labFunctionVo.imageUrl}" />
										</td>
										<td>
											<label>
												<s:text name="lab.sequence"/>：
											</label>
										</td>
										<td>
											<input name="labFunctionVo.sort" id="sort" type="text" size="30" value="${labFunctionVo.sort}" />
										</td>
									</tr>
									<tr>
										<td>
											<label id="SCLC">
												<s:text name="make.flow"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isProcess" ></s:radio>
										</td>
										<td>
											<label>
												<s:text name="export.module"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isTemplate" ></s:radio>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="relate.report"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isReport" ></s:radio>
										</td>
										<td>
											<label>
												<s:text name="bar.back"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isBarCode" ></s:radio>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="def.query"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isQuery" ></s:radio>
										</td>
										<td>
											<label>
												<s:text name="quick.fun"/>：
											</label>
										</td>
										<td>
											<s:radio theme="simple" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}" name="labFunctionVo.isQuickFun" ></s:radio>
										</td>
									</tr>
								</table>
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