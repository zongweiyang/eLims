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
					$('form').attr('action',actionstr);
					$('form').submit();
				}
				function check(obj){
					obj=$(obj);
					if(obj.val().indexOf('|')>-1){
						var values=obj.val().split('|');
						$("#unit").val(values[1]);
						$("#labAmbientId").val(values[0]);
					}else{
						$("#unit").val("");
					}
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
							<s:form theme="simple" action="" method="post" name="labAmbientInfoForm">
			
								<div class="TabTable">
								 	<div class="FUNCIONBARNEW">
				                      <table><tr><td class="blockTd" style="padding: 6px 10px;vertical-align:center;"><table cellspacing="0" cellpadding="0" border="0"><tr>
				                      <td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif"/><b><s:text name="msg.back"/></b></a></td>
				                       <td><a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('addLabAmbientInfo.action');return false;"><img height="20" width="20" src="<%=basePath%>img/filesave.gif"/><b><s:text name="lab.code.save"/></b></a></td>
				                      </tr></table></td></tr>
				                      </table>
			                    	</div>
									<div class="Formtable">
										<div class="Formtabletitle">
											<span><s:text name="env.manage.info"/></span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td><label><s:text name="param.name"/>：</label></td>
												<td>
													<s:select list="#request.listLabAmbientVo" cssStyle="width:130px" onchange="check(this)"  headerValue="------请选择------" headerKey="0"   theme="simple" listValue="name" listKey="id+'|'+unit" ></s:select>
												</td>
											
												<td><label><s:text name="param.unit"/>：</label></td>
												<td>
													<input type="text" readonly="readonly"  id="unit" />
													<input type="hidden" readonly="readonly" valType="required" msg='<s:property value="getText('selparm')"/>' name="labAmbientInfoVo.labAmbientId" id="labAmbientId" />
												</td>
											</tr>
											<tr>
												<td ><label><s:text name="param.value"/>：</label></td>
												<td><input type="text" name="labAmbientInfoVo.value" id="value" valType="required,numBetween" msg='<s:property value="getText('inputcorrvalue')"/>'  min="0" />
											
												<td><label><s:text name="address"/>：</label></td>
												<td><input type="text" name="labAmbientInfoVo.address" id="address" /></td>
											</tr>
											<tr>
												<td><label><s:text name="room"/>：</label></td>
												<td><input type="text" name="labAmbientInfoVo.room" id="room" /></td>
											
												<td><label><s:text name="env.time"/>：</label></td>
												<td><input type="text" name="labAmbientInfoVo.time" id="time" class="Wdate" size="20"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true});" /></td>
											</tr>
											<tr>
												<td>
													<label><s:text name="lab.remark"/>：</label>
												</td>
												<td colspan="3">
													<textarea name="labAmbientInfoVo.demo" rows="2" cols="35"></textarea>
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
				this.a1 = new Array("value", '<s:property value="getText('inputpavlaue')"/>', new Function("varName"," return this[varName];"));
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
