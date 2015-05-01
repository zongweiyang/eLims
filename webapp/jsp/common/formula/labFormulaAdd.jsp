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
					var df = document.labFormulaForm;
					if($("#editor").val()==''){
						alert('<s:property value="getText('plsesaveeditgorm')"/>');
						return false;
					}
				 	df.action=actionstr;
				  	df.submit();
				}
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
									${funName }：
									<span><s:text name="admin.add"/></span>
								</h2>
							</div>
							<s:form theme="simple" action="" method="post" name="labFormulaForm">
			
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
				                      <table><tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
				                      <td><l:a uri="back" value="msg.back"/></td>
				                      <td>
				                      		<l:a uri="formula/labFormula/addLabFormula.action" onclick="submitvalue('addLabFormula.action');return false;" value="lab.code.save"/>
		                      		  </td>
				                      </tr></table></td></tr>
				                      </table>
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="formula.edit.info"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td><label><s:text name="formula.name"/>：</label></td>
												<td><input type="text" name="labFormulaVo.name" id="name"  valType="required" msg="公式名不能为空" /></td>
												<td><label><s:text name="formula.def"/>：</label></td>
												<td>
													<input type="hidden" name="labFormulaVo.editor" id="editor" />
													<input type="hidden" name="labFormulaVo.showHtml" id="showLabFormula"/>
													<input type="text" name="labFormulaVo.defineUser" id="defineUser" value="${labFormulaVo.defineUser}" />
												</td>
												
											</tr>
											<tr>
												<td colspan="6" style="width:100%;height:500px;">
													<iframe id="iframe" style="width:100%;height:100%;border:0px;" src="<%=basePath%>utils/mathedit/matheditc/mathedit.html">
													
													</iframe>
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
			function required() {
				this.a1 = new Array("name", "请填写公式名!", new Function("varName"," return this[varName];"));
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
