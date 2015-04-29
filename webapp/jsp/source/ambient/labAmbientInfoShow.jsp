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
							<s:form theme="simple" action="" method="post" name="labAmbientInfoForm">
								<input type="hidden" name="labAmbientInfoVo.id" value="${labAmbientInfoVo.id}"/>
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
				                      <table><tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
				                      <td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif"/><b><s:text name="msg.back"/></b></a></td>
				                      </tr></table></td></tr>
				                      </table>
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="env.manage.info"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td class="c"><label><s:text name="param.name"/></label></td>
												<td>
													${labAmbientInfoVo.labAmbientName}
												</td>
												<td class="c"><label><s:text name="param.unit"/>：</label></td>
												<td>
													${labAmbientInfoVo.labAmbientUnit}
												</td>
											</tr>
											<tr>
												<td class="c"><label><s:text name="param.valu"/>：</label></td>
												<td>${labAmbientInfoVo.value}</td>
												<td class="c"><label><s:text name="address"/>：</label></td>
												<td>${labAmbientInfoVo.address}</td>
											</tr>
											<tr>
												<td class="c"><label><s:text name="room"/>：</label></td>
												<td>${labAmbientInfoVo.room}</td>
												<td class="c"><label><s:text name="env.time"/>：</label></td>
												<td>${labAmbientInfoVo.time}</td>
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
				this.a1 = new Array("value", "请填写参数值!", new Function("varName"," return this[varName];"));
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
