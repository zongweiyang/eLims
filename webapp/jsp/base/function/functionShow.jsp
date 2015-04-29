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
								<span></span>
							</h2>
						</div>
						<form action="addLabFunction.action" method="post" name="stFunctionform" theme="simple">
							<input type="hidden" name="labFunctionVo.parentId" id="parentId" value="${labFunctionVo.parentId}" />
							<input type="hidden" name="labFunctionVo.id" id="labFunctionVo_id" value="${labFunctionVo.id}" />
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
											${labFunctionVo.name}
										</td>
										<td>
											<label>
												<s:text name="f.code"/>：
											</label>
										</td>
										<td>
											${labFunctionVo.wfcode}
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="f.type"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.type=='0'}"><s:text name="preface"></s:text></s:if>
											<s:else><s:text name="function"/></s:else>
										</td>
										<td>
											<label>
												<s:text name="fun.link"/>：
											</label>
										</td>
										<td>
											${labFunctionVo.url}
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="menu.show"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isDisplay=='N'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
										</td>
										<td>
											<label>
												<s:text name="site.show"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isFront=='Y'}"><s:text name="front.show"/></s:if>
											<s:if test="${labFunctionVo.isBack=='Y'}"><s:text name="back.show"/></s:if>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="icon.link"/>：
											</label>
										</td>
										<td>
											${labFunctionVo.imageUrl}
										</td>
										<td>
											<label>
												<s:text name="lab.sequence"/>：
											</label>
										</td>
										<td>
											${labFunctionVo.sort}
										</td>
									</tr>
									<tr>
										<td>
											<label id="SCLC">
												<s:text name="make.flow"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isProcess=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
										</td>
										<td>
											<label>
												<s:text name="export.module"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isTemplate=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="relate.report"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isReport=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
										</td>
										<td>
											<label>
												<s:text name="bar.back"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isBarCode=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/><s:text name="lab.no"/></s:else>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="def.query"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isQuery=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
										</td>
										<td>
											<label>
												<s:text name="quick.fun"/>：
											</label>
										</td>
										<td>
											<s:if test="${labFunctionVo.isQuickFun=='Y'}"><s:text name="lab.yes"/></s:if>
											<s:else><s:text name="lab.no"/></s:else>
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