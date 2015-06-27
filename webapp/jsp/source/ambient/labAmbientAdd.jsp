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
			  		var upValue=parseFloat($("#upValue").val());
					var downValue=parseFloat($("#downValue").val());
					if(downValue>=upValue){	
						var message='<s:property value="getText('lowervaluecantuper')"/>';
						validate.tip(message,$("#upValue"))
						return;
					}
					$('form').attr('action',actionstr);
					$('form').submit();
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
									${funName}：
									<span><s:text name="admin.add"/></span>
								</h2>
							</div>
							<form theme="simple" action="" method="post" name="labAmbientForm">
			
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
				                      <table><tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
				                      <td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif"/><b><s:text name="msg.back"/></b></a></td>
				                      <td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('addLabAmbient.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif"/><b><s:text name="lab.code.save"/></b></a></td>
				                      
				                      </tr></table></td></tr>
				                      </table>
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="env.manage"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td class="c"><label><s:text name="param.name"/>：</label></td>
												<td><input type="text" name="labAmbientVo.name" id="name" valType="required" msg='<s:property value="getText('paramnamecannotempty')"/>' />
										
												<td class="c"><label><s:text name="upper.value"/>：</label></td>
												<td><input type="text" name="labAmbientVo.upValue" valType="required,number" msg='<s:property value="getText('pleaseinputcor')"/>' min="0" id="upValue" /></td>
											</tr>
											<tr>
												<td class="c"><label><s:text name="lower.value"/>：</label></td>
												<td><input type="text" name="labAmbientVo.downValue" valType="required,number" msg='<s:property value="getText('pleaseinputlow')"/>'  min="0" id="downValue" /></td>
											
												<td class="c"><label><s:text name="theme.depart"/></label></td>
												<td>
													<s:select list="#request.listLabCode" cssStyle="width:130px" name="labAmbientVo.unit"   theme="simple" listValue="name" listKey="name" ></s:select>
												</td>
											</tr>
										</table>
									</div>
								</div>
								</div>
							</form>
						</div>
					<td class="MRimg"></td></tr>
							<tr>
							<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
			        </tr>
			        			</table>
						<div class="clear"></div>
		</body>
</html>
