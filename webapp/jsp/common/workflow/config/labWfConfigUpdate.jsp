<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}

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

	<script language=javascript> 
		  	function submitvalue(actionstr){
		  		var funId=$('#funId').val();
		  		if(funId==''){
		  			validate.tip("请选择业务.",$('#funId'));
		  			return false;
		  		}
		  		var processId=$('#processId').val();
		  		if(processId==''||processId=='null'||processId==null){
		  			validate.tip("请选择使用流程.",$('#processId'));
		  			return false;
		  		}
				$('form').attr('action','<%=basePath%>'+actionstr);
				$('form').submit();
			}
			/*function ajax2ValidateCode(obj,id){
				var val=$(obj).val();
				$.ajax({
			   	  url:'<%=basePath%>/workflow/labWfConfig/isExist4Code.action',
			   	  data:{'labWfConfigVo.id':id,'labWfConfigVo.code':val},
			   	  type:'post',
				  dataType:'text',
			   	  success:function (data){
			   	  	if(data=="true"){
		            	validate.tip("编号重复.",$('#code'));
		              	$(obj).val("");;
		          	 }
			   	  },
			   	  error:function (data){
			   	  	alert('请求错误.');
			   	  }
			   });
			}*/
			function ajaxLabWfProcess4Select(obj){
				var val=$(obj).val();
				var vv=val.split("^");
				if(vv.length==2&&vv[0].length>0){
					$.ajax({
				   	  url:'<%=basePath%>/workflow/labWfConfig/ajaxLabWfProcess4Select.action',
				   	  data:{'labWfProcessVo.funId':vv[0]},
				   	  type:'post',
					  dataType:'text',
				   	  success:function (data){
				   	  	var datas=eval('('+data+')');
				   	  	var optionStr='';
				   	  	$('#processId').html('');
				   	  	for(var i=0;i<datas.length;i++){
				   	  		optionStr+='<option value="'+datas[i].id+'">'+datas[i].name+'</option>';
				   	  	}
				   	  	$('#processId').append(optionStr);
				   	  	$('#code').val(vv[1]);
				   	  	$('#funId').val(vv[0]);
				   	  },
				   	  error:function (data){
				   	  	alert('请求错误.');
				   	  }
				   });
				}
				
			}
	</script>
	</head>
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
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labWfConfigVo.id" value="${labWfConfigVo.id}"/>
							<s:token></s:token>
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="workflow/labWfConfig/updateLabWfConfig.action" onclick="submitvalue('workflow/labWfConfig/updateLabWfConfig.action');return false;" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
											<tr>
												<td><label><s:text name="tmp.biz"/><s:text name="config.name"/>：</label></td>
												<td>
													<input type="text" name="labWfConfigVo.funName" id="funName" value="${labWfConfigVo.funName}" readonly="readonly"/>
													<input type="hidden" name="labWfConfigVo.funId" id="funId" value="${labWfConfigVo.funId}" />
												</td>
												<td><label><s:text name="config.number"/>：</label></td>
												<td><input type="text" name="labWfConfigVo.code" id="code" value="${labWfConfigVo.code}" readonly="readonly" style="background-color: #eee" valType="required"  msg="编号未取代或配置出错."/></td>
												<td><label><<s:text name="use.flow"/>：</label></td>
												<td>
													<s:select list="#request.processList" listKey="id" listValue="name" name="labWfConfigVo.processId" id="processId" theme="simple"></s:select>
													<font color="red"> *</font>
												</td>
											</tr>
											<tr>
												<td><label><s:text name="remark"/>：</label></td>
												<td colspan="5">
													<textarea rows="4" cols="60" name="labWfConfigVo.remark" id="remark">${labWfConfigVo.remark}</textarea>
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
<script>
	$(function(){
		var funId='${labWfConfigVo.funId}';
		$('#funSelect').find('option[value*="'+funId+'^"]').attr("selected","selected");
	});
</script>
</html>
