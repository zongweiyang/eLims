<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

.TabTable .TabTableBOX01 {
	display: none;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}

.TabTable .TabTableBOX01.b {
	display: block;
	_height: 220px;
	min-height: 220px;
	width: 96.5%;
	background: #ECFCFF;
	border: 1px solid #499EB3;
	margin-left: 10px;
	margin-bottom: 10px;;
	padding: 10px 10px 0 10px
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<Script>
	function goToNextAction(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	function submitforform(){
		if($('#processName').val()==0){
			validate.tip('流程名称不能为空.',$('#processName'));
			return false;
		}
		if($('#functionId').val()==0){
			validate.tip('请选择业务.',$('#functionId'));
			return false;
		}
		var isSubWf=$('input[name="labWfProcessVo.isSubWf"]:checked').val();
		if(isSubWf=='Y'){
			var isAll=true;
			$('select[id*="subProcessId"]').each(function(){
				if($(this).val()==''||$(this).val()=='null'||$(this).val()==null){
					validate.tip('请选择子流程.',$(this));
					isAll=false;
					return ;
				}
			});
			if(!isAll){
				return false;
			}
		}
		$('form').submit();
	}
	function addRows(){
		var i=$('#LCBL').find('tr').length;
		i=parseInt($('#LCBL').find('tr').eq(i-1).attr('key'))+1;
		if(isNaN(i)){
			i=0;
		}
		var trhtml="<tr key='"+i+"'>"+
						"<td>"+
							"<input type='text' name='labWfProcessVo.varList["+i+"].name' value='' id='name"+i+"' size='40' /><font color='red'>*</font>"+
						"</td>"+
						"<td>"+
							"<input type='text' name='labWfProcessVo.varList["+i+"].value' value='' id='value"+i+"' size='20' />"+
						"</td>"+
						"<td>"+
							"<input type='text' name='labWfProcessVo.varList["+i+"].comment' value='' id='comment"+i+"' size='40' />&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='deleteOne(this)'><s:text name="lab.code.del"/></a>"+
						"</td>"+
					"</tr>";
		
		$('#LCBL').append(trhtml);
	}
	function deleteOne(obj){
		$(obj).parent().parent().remove();
	}
	function ajaxToStep(obj){
		var funId=$(obj).val();
		if(funId.length>0){
			$.ajax({
				url:'${basePath}/workflow/process/ajaxLabWfStep4Select.action',
				type:'POST',
				data:{'labWfProcessVo.funId':funId},
				dataType:'text',
				success:function(date){
					var result=eval('('+date+')');
					$('#bzhtsh').remove();
					$('#bzhxx').html("");
					$('#bzhxx').append(
    				     $('<tr>').append($('<td>').addClass('c').append('节点名称'))
     								 .append($('<td>').addClass('c').append('节点编号'))
       				);
       				for(var i=0;i<result.length;i++){
       					$('#bzhxx').append(
       						$('<tr>').append($('<td>').append('<input type="hidden" name="labWfProcessVo.funStepList['+i+'].stepId" value="'+result[i].id+'" id="stepId'+i+'" />'+
       														  '<input readonly="readonly" type="text" name="labWfProcessVo.funStepList['+i+'].stepName" value="'+result[i].name+'" id="stepName'+i+'" size="40"  style="background-color: #DDDDDD;"/>'))
       								 .append($('<td>').append('<input type="text" name="labWfProcessVo.funStepList['+i+'].stepCode" value="'+result[i].wfcode+'" id="stepCode'+i+'" size="40" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="deleteOne(this)"><s:text name="lab.code.del"/></a>'))
       					);
       				}
				},
				error:function(){
					alert('<s:property value="getText('network.error')"/>');
				}
			});
		}else{
			var htmlstr='<tr>'+
							'<td class="c">'+
									'节点名称'+
							'</td>'+
							'<td class="c">'+
									'节点编号'+
							'</td>'+
						'</tr>'+
						'<tr id="bzhtsh">'+
							'<td colspan="2">'+
								'<font color="red">请先选择流程源．．．</font>'+
							'</td>'+
						'</tr>';
			$('#bzhxx').html(htmlstr);
		}
	}
	function isSubWf(obj){
		var checked=$(obj).val();
		if(checked=='Y'){
			$('.subWf').removeAttr('style');
		}else{
			$('.subWf').attr('style','display:none');
		}
		
	}
	function addSubProcessRows(){
		var i=$('#subProcess').find('tr').length;
		i=parseInt($('#subProcess').find('tr').eq(i-1).attr('key'))+1;
		if(isNaN(i)){
			i=0;
		}
		var optionStr="";
		<s:iterator value="#request.processList" status="st">
			var flag=true;
			var thisId='${id}';
			$('select[id*="subProcessId"]').each(function(){
				var selVal=$(this).val();
				if(selVal==thisId){
					flag=false;
					return false;
				}
			});
			if(flag){
				optionStr +='<option value="${id}">${name}</option>'; 
			}
		</s:iterator>
		var trhtml='<tr key="'+i+'">'+
						'<td>'+
							'<select name="labWfProcessVo.subProcessList['+i+'].id" id="subProcessId'+i+'" >'+optionStr+'</select>'+
						'</td>'+
						'<td>'+
							'<input type="radio" name="labWfProcessVo.subProcessList['+i+'].isStop" value="N" />正常流转'+
							'<input type="radio" name="labWfProcessVo.subProcessList['+i+'].isStop" value="Y" checked="true" />骤停等待'+
						'</td>'+
						'<td class="c">'+
							'<a href="javascript:;" onclick="deleteOne(this);"><s:text name="lab.code.del"/></a>'+
						'</td>'+
					'</tr>';
		$('#subProcess').append(trhtml);
	}
</script>
	<body class="" id="mainid">
		<form method="post" name="form" action="addLabWfProcess.action">
			<s:token></s:token>
			<input type="hidden" name="labWfProcessVo.id" value="${labWfProcessVo.id}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												<s:text name="prc.manage"/>
												<span><s:text name="prc.def"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<l:a uri="workflow/process/addLabWfProcess.action" onclick="submitforform();" value="lab.code.save" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="prf.info"/></span>
											</div>
											<table class="FormtableCon_line">
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="prc.name"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labWfProcessVo.name" value="${labWfProcessVo.name}" valType="required,strLength" max="32" msg="名称不能为空" strLength-msg="长度不能超过32位" id="processName" size="30" />
													</td>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="prc.no"/>：
														</label>
													</td>
													<td>
														<input type="text" name="labWfProcessVo.code" value="${labWfProcessVo.code}" id="code" size="30" valType="strLength" max="32" strLength-msg="长度不能超过32位" />
													</td>
												</tr>
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="biz.selected"/>：
														</label>
													</td>
													<td>
														<s:select list="#request.funcList" listKey="id" listValue="name+'('+parentName+')'" name="labWfProcessVo.funId" id="functionId" headerKey="" headerValue="" theme="simple" onchange="ajaxToStep(this);"></s:select>
														<font color="red">*</font>
													</td>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="rel.child.flow"/>：
														</label>
													</td>
													<td>
														<input type="radio" name="labWfProcessVo.isSubWf" value="Y" onclick="isSubWf(this);" <s:if test="${labWfProcessVo.isSubWf=='Y'}">checked="true"</s:if>/><s:text name="lab.yes"/>
														<input type="radio" name="labWfProcessVo.isSubWf" value="N" onclick="isSubWf(this);" <s:if test="${labWfProcessVo.isSubWf=='N'}">checked="true"</s:if>/><s:text name="lab.no"/>
													</td>
												</tr>
												<tr>
													<td style="width: 100px" class="r">
														<label>
															<s:text name="lab.remark"/>：
														</label>
													</td>
													<td colspan="3">
														<textarea rows="2" cols="60" name="labWfProcessVo.comment">${labWfProcessVo.comment}</textarea>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable subWf" style="display: none;">
											<div class="Formtabletitle">
												<span><s:text name="child.flow"/></span>
												<a href="javascript:;" onclick="addSubProcessRows();"><font color="red">&nbsp;+&nbsp;<s:text name="added.a.row"/></font>
												</a>
											</div>
											<table class="FormtableCon_line" id="subProcess">
												<tr>
													<td class="c">
														<s:text name="processed"/>
													</td>
													<td class="c">
														<s:text name="dist.sex"/>
													</td>
													<td class="c">
														<s:text name="lab.code.ops"/>
													</td>
												</tr>
												<tr key="0">
													<td>
														<s:select list="#request.processList" listKey="id" listValue="name" name="labWfProcessVo.subProcessList[0].id" id="subProcessId${st.index}" theme="simple"></s:select>
													</td>
													<td>
														<input type="radio" name="labWfProcessVo.subProcessList[0].isStop" value="N" />正常流转
														<input type="radio" name="labWfProcessVo.subProcessList[0].isStop" value="Y" checked="true" />骤停等待
													</td>
													<td class="c">
														<a href="javascript:;" onclick="deleteOne(this);"><s:text name="lab.code.del"/></a>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="node.info"/></span>
											</div>
											<table class="FormtableCon_line" id="bzhxx">
												<tr>
													<td class="c">
												<span><s:text name="node.name"/></span>
													</td>
													<td class="c">
														<s:text name="node.id"/>
													</td>
												</tr>
												<tr id="bzhtsh">
													<td colspan="2">
														<font color="red">请先选择流程源．．．</font>
													</td>
												</tr>
											</table>
											<div style="padding-left: 10px;">
												<font size="2"><s:text name="updatefunlist"/></font>
											</div>
										</div>
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="path.var"/></span>
												<a href="javascript:;" onclick="addRows();"><font color="red">&nbsp;+&nbsp;<s:text name="added.a.row"/></font>
												</a>
											</div>
											<table class="FormtableCon_line" id="LCBL">
												<tr>
													<td class="c" width="40%">
														<s:text name="变量名"/>
													</td>
													<td class="c" width="20%">
														<s:text name="var.value"/>
													</td>
													<td class="c" width="40%">
														<s:text name="remark"/>
													</td>
												</tr>
												<s:iterator id="" status="st" value="labWfProcessVo.varList">
													<tr key="${st.index}">
														<td>
															<input type="text" name="labWfProcessVo.varList[${st.index}].name" value="${name}" id="name${st.index}" size="40" />
															<font color="red">*</font>
														</td>
														<td>
															<input type="text" name="labWfProcessVo.varList[${st.index}].value" value="${value}" id="value${st.index}" size="20" />
														</td>
														<td>
															<input type="text" name="labWfProcessVo.varList[${st.index}].comment" value="${comment}" id="comment${st.index}" size="40" />
															&nbsp;&nbsp;&nbsp;&nbsp;
															<a href='javascript:;' onclick='deleteOne(this)'><s:text name="lab.code.del"/></a>
														</td>
													</tr>
												</s:iterator>
											</table>
										</div>
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