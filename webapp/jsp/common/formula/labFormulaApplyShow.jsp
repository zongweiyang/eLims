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
			function editOpen(){
					$.dialog({
						id:'parentFunction',
						content:'url:'+'<%=basePath%>/page/labPageEditor/preUpdatePageEdit4Formula.action?labPageEditorVo.id='+$("#jspUrlId").val(),
						title:'编辑页面：',
						opacity:0.4,
						width:800, 
						height:500,
						max:false,
						min:false,
						lock:true
					 });
				}
				$(function(){
				  var paramer='${labFormulaApplyVo.parameter}';
				  var paramerId='${labFormulaApplyVo.parameterId}';
				  if(paramer!=''){
				  	for(var i=0;i<paramer.split(',').length;i++){
				  		if(paramer.split(',')[i]!=''){
					  		var row="<tr><td><label>参数：</label></td><td><input readonly=\"readonly\" value=\""+paramer.split(',')[i]+"\" type=\"text\"/></td>";
					  			row+="<td><label>对应页面参数ID：</label></td><td><input readonly=\"readonly\" value=\""+paramerId.split(',')[i]+"\" type=\"text\"/></td>";
					  			row+="</tr> ";
				  		 	$("#parameter").append(row);
			  		 	}
				  	}
				 
				  }
				  
				});
			</script>
		<body>
			 <table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
						cellpadding="0" border="0">
						  <tr><td class="TLimg"></td><td class="TMimg"></td><td class="TRimg"></td></tr>
			      <tr>
							<td class="MLimg"></td>
			        <td id="bodyCell" class="oRight">
						<div class="myworkingbox">
							<div class="myworkingboxttitle">
								<h2>
									${funName}：
									<span><s:text name="look.check"/></span>
								</h2>
							</div>
							<s:form theme="simple" action="" method="post" name="labFormulaApplyForm">
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
				                      <table><tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
				                      <td><l:a uri="back" value="msg.back"/></td>
				                      </tr></table></td></tr>
				                      </table>
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="page.use.manage"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td><label><s:text name="formula.content"/>：</label></td>
												<td>
													<input type="text" readonly="readonly" value="${labFormulaApplyVo.labFormulaEditor}" name="labFormulaApplyVo.labFormulaId" id="labFormulaId"/>
												</td>
												<td>
													<label><s:text name="page.show.id"/></label>
												</td>
												<td>
													<input readonly="readonly" type="text" value="${labFormulaApplyVo.valueId}" name="labFormulaApplyVo.valueId" />
												</td>
											</tr>
											<tr>
												<td><label><s:text name="use.page.path"/>：</label></td>
												<td colspan="3">
													<input type="text" readonly="readonly" size="40" value="${labFormulaApplyVo.jspUrl}"   id="jspUrl"  />
													<a href="#"  id="showFont" onclick="editOpen()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="lab.code.edit"/></a>
													<input type="hidden" id="jspUrlId" value="${labFormulaApplyVo.labPageEditorId }" name="labFormulaApplyVo.labPageEditorId"/>
												</td>
											</tr>
										</table>
									</div>
									<div class="Formtable" id="parameterDiv" >
										<div class="Formtabletitle">
											<span><s:text name="formula.param"/><lable ><font color="red" id="formula"></font></lable></span>
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
								<s:fielderror cssStyle="color: red"></s:fielderror>
								</div>
							</s:form>
						</div>
					<td class="MRimg"></td></tr>
							<tr>
							<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
			        </tr>
			        			</table>
						<div class="clear"></div>
		</body>
		<script language="javascript" type="text/javascript">
		</script>
</html>
