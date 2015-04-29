<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="../../../jsp/include/common.jsp"%>
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
</style>



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
			  	function submitvalue(actionstr){
					var df = document.labFormulaApplyForm;
					 	df.action=actionstr;
					  	df.submit();
				}
				$(function(){
				  var paramer='${labFormulaApplyVo.parameter}';
				  var paramerId='${labFormulaApplyVo.parameterId}';
				  if(paramer!=''){
				  	for(var i=0;i<paramer.split(',').length;i++){
				  		if(paramer.split(',')[i]!=''){
					  		var row="<tr><td><label>参数：</label></td><td><input readonly=\"readonly\" value=\""+paramer.split(',')[i]+"\" type=\"text\"/></td>";
					  			row+="<td><label>对应页面参数ID：</label></td><td><input name=\"labFormulaApplyVo.names\"  value=\""+paramerId.split(',')[i]+"\" type=\"text\"/></td>";
					  			row+="</tr> ";
				  		 	$("#parameter").append(row);
			  		 	}
				  	}
				 
				  }
				  
				});
				function checkName(obj){
					var obj=$(obj);
					$("#formula").html(obj.val().split('|')[1]);
					$("#labFormulaId").val(obj.val().split('|')[0]);
					if(obj.val()=='0'){
						$("#parameter").hide();
						$("#formula").html('');
						return false;
					}
					var url="${basePath}/formula/labFormula/ajaxLabFormulaParameter.action?labFormulaVo.id="+obj.val().split('|')[0];
						$.ajax({
							url:url,
							type:'POST',
							dataType:'text',
							success:function(data){
								$("#parameter tbody").html("");
								if(data!=''){
									addParameter(data);
								}else{
									$("#parameter").hide();
									alert('请检查此公式是否正确');
								}
							},
							error:function(){
								alert('网络不通.');
							}
						});	
				}
				function addParameter(data){
					$("#parameter").show();
					var tabObj=$("#parameter");
					var dataList=data.split(',');
					for(var i=0;i<dataList.length;i++){
						if(dataList[i]!=null&&dataList[i]!=''){
							var html="<tr><td><label>参数：</label></td><td><input value=\""+dataList[i]+"\"/></td>";
							html+="<td><label>对应页面参数ID：</label></td><td><input type=\"text\" name=\"labFormulaApplyVo.names\"></td>";
							html+="</tr>";
							tabObj.append(html);
						}
					}
				
				}
				function selectJspUrl(){
					$.dialog({
						id:'parentFunction',
						content:'url:'+'<%=basePath%>/page/labPageEditor/showPageFormula4Select.action',
						title:'页面选择：',
						opacity:0.4,
						width:800, 
						height:500,
						lock:true,
						max:false,
						min:false
					 });
				
				}
				function editOpen(){
					$.dialog({
						id:'parentFunction',
						content:'url:'+'<%=basePath%>/page/labPageEditor/preUpdatePageEdit4Formula.action?labPageEditorVo.id='+$("#jspUrlId").val(),
						title:'编辑页面：',
						opacity:0.4,
						width:800, 
						height:500,
						lock:true,
						min:false,
						max:false
					 });
				}
				function showFormula(){
					$.dialog({
						id:'parentFunction',
						content:'url:'+'<%=basePath%>/formula/labFormula/showFormula.action?labFormulaVo.id='+$("#labFormulaId").val(),
						title:'公式显示：',
						opacity:0.4,
						width:800, 
						height:500,
						lock:true,
						max:false,
						min:false
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
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<s:form theme="simple" action="" method="post"
							name="labFormulaApplyForm">
							<input type="hidden" name="labFormulaApplyVo.id"
								value="${labFormulaApplyVo.id}" />
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
															<l:a uri="formula/labFormulaApply/updateLabFormula.action" onclick="submitvalue('updateLabFormulaApply.action');return false;" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="page.use.manage"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="formula.content"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.listLabFormula" headerKey="0"
													headerValue="---请选择公式---" theme="simple" listValue="name"
													listKey="id+'|'+editor" cssStyle="width:120px"
													value="'${labFormulaApplyVo.labFormulaId}|${labFormulaApplyVo.labFormulaEditor}'"
													onchange="checkName(this)"></s:select>
												<font color="red">&nbsp;&nbsp;*</font>
												<span><a href="#" onclick="showFormula()"><s:text name="show.formula"/></a>
												</span>
												<input type="hidden" name="labFormulaApplyVo.labFormulaId" valType="required" msg="请选择公式" id="labFormulaId" value="${labFormulaApplyVo.labFormulaId}" />
											</td>
											<td>
												<label>
													<s:text name="page.show.id"/>:
												</label>
											</td>
											<td>
												<input type="text" name="labFormulaApplyVo.valueId"
													value="${labFormulaApplyVo.valueId}" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="use.page.path"/>：
												</label>
											</td>
											<td colspan="3">
												<input type="text" size="40" id="jspUrl"
													onclick="selectJspUrl()"
													value="${labFormulaApplyVo.jspUrl }" />
												<a href="#" id="showFont" onclick="editOpen()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lab.code.edit"/></a>
												<input type="hidden" id="jspUrlId"
													name="labFormulaApplyVo.labPageEditorId"
													value="${labFormulaApplyVo.labPageEditorId}" />
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="parameterDiv">
									<div class="Formtabletitle">
										<span><s:text name="formula.param"/><lable><font color="red"
											id="formula"></font>
										</lable>
										</span>
									</div>
									<table class="FormtableCon" id="parameter">
										<tr>
											<td>
												<label></label>
											</td>
										</tr>
									</table>
								</div>
							</div>
					</div>
					<s:fielderror cssStyle="color: red"></s:fielderror>
					</div>
					</s:form>
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
	<script language="javascript" type="text/javascript">
			function required() {
				this.a1 = new Array("name", "请填写公式内容!", new Function("varName"," return this[varName];"));
			}
			function maxlength() {
		
			}
			function DateTimeValidations() {
		
			}
			function email() {
		
			}
			function FloatValidations() {
		
			}
			function IntegerValidations() {
			}
			function DateValidations() {
		
			}
		</script>
</html>
