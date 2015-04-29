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
				.oRight{
					min-height: 400px;
				}
				.myworkingbox{
					margin-top: 0px;
				}
			</style>
		</head>
			<script language=javascript> 
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
									<span><s:text name="look.check"/></span>
								</h2>
							</div>
							<s:form theme="simple" action="" method="post" name="labFormulaForm">
								<input type="hidden" name="labFormulaVo.id" value="${labFormulaVo.id}"/>
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="formula.edit.info"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td><label><s:text name="formula.name"/>：</label></td>
												<td><input type="text" readonly="readonly" name="labFormulaVo.name" id="name" value="${labFormulaVo.name}"/><font color="red">&nbsp;&nbsp;*</font></td>
												<td><label><s:text name="formula.def"/>：</label></td>
												<td>
													<input type="hidden" name="labFormulaVo.showHtml" value="${labFormulaVo.showHtml}" id="showLabFormula"/>
													<input type="text" readonly="readonly" name="labFormulaVo.defineUser" id="defineUser" value="${labFormulaVo.defineUser}"/></td>
												</td>	
											</tr>
											<tr>
												<td colspan="6" style="width:100%;height:300px;">
													<iframe style="width:100%;height:100%;border:0px;" src="<%=basePath%>utils/mathedit/matheditc/mathedit.html">
														
													</iframe>
												</td>
											</tr>
										</table>
									</div>
								</div>
								</div>
							</s:form>
						</div>
					<td class="MRimg"></td></tr>
			        			</table>
						<div class="clear"></div>
		</body>
		<script language="javascript" type="text/javascript">
		</script>
</html>
