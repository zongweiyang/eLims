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

.Formtable {
	border: none;
}

.FormtableCon textarea {
	width: 600px;
}

.FormtableCon select {
	width: 200px;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script language=javascript>
			var isStatus='0';
			function submitvalue(actionstr){
				$('form').attr('action','<%=basePath%>'+actionstr);
				if(confirm("您确认要提交吗？")){
					$('form').submit();
				}
			}
			function submit2Back(actionstr){
				var html = $('#auditMessage').html();
				html=html.replace(/(^\s*)|(\s*$)/g,'');
				if(html==''){
					alert('<s:property value="getText('inpputnnotcorr')"/>');
					return false;
				}
				$('form').attr('action','<%=basePath%>'+actionstr);
				if(confirm("您确认要提交吗？")){
					$('form').submit();
				}
			}
			function checkTrColor(obj){
				obj=$(obj);
				obj.closest('tr').find('td').each(function(){
					$(this).css("background-color","red");
				});
				obj.closest('tr').find('td').eq(1).find('input[type=checkbox]').attr('checked',true);
			}
			/*function agree(){
				isStatus='0';
				$('.myworkingboxttable tr:gt(0)').find('td').each(function(){
					$(this).css("background-color",'#f7f7f7');				
				});
				$('.myworkingboxttable tr:gt(0)').find('td').find('input[type=checkbox]').each(function(){
					if($(this).attr('checked')){
						$(this).attr('checked',false);
					}
				});
			}
			function noPass(){
				isStatus='1';
			}*/
	</script>
	<body>
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
								<span><s:text name="config.modify"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labSupplierForm">
							<input type="hidden" name="labSamTestVo.taskId" value="${labSamTestVo.taskId}" />
							<s:token></s:token>
							<div class="TabTable">
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
															<l:a uri="samtest/labSamTest/updateLabSamTest4Summary.action" onclick="submitvalue('samtest/labSamTest/updateLabSamTest4Summary.action?labSamTestVo.auditResult=1');" value="flow.pass" img="/img/icon/filesave.gif"/>
														</td>
														<td>
															<l:a uri="samtest/labSamTest/updateLabSamTest4Summary.action" onclick="submit2Back('samtest/labSamTest/updateLabSamTest4Summary.action?labSamTestVo.auditResult=-1');" value="flow.return" img="/img/icon/filesave.gif"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span style="cursor: hand;" onclick="$(this).parent().next().toggle();"><s:text name="xiaoyan.clu"/></span>
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon">
											<tr>
												<td>
													<label>
														<s:text name="total.clu"/>：
													</label>
												</td>
												<td colspan="2">
													<textarea rows="3" name="labSamTestVo.auditMessage" id="auditMessage" cols="20"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span onclick="javascript:$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="check.result"/></span>
										<label style="float: right; margin-right: 20px;" onclick="javascript:$(this).parent().next().toggle();">
											<font color="blue">[<s:text name="open.close"/></font>]
										</label>
									</div>
									<table class="myworkingboxttable">
										<tr>
											<th width="100">
												<img src="<%=basePath%>img/icon_drag.gif" />
											</th>
											<th>
												<s:text name="sam.id"/>
											</th>
											<th>
												<s:text name="sam.name"/>
											</th>
											<th>
												<s:text name="sam.type"/>
											</th>
											<s:iterator value="#request.listLabSamTestVo[0]"  id="st">
												<s:iterator value="#st.listTitle" status="sdd">
													<th>
														<span style="color: red;">${demo1}</span>
														<input type="checkbox" name="listLabSamTestVo[${sdd.index}].itemId" value="${demo}" />
													</th>
												</s:iterator>
											</s:iterator>
										</tr>
										<s:iterator value="#request.listLabSamTestVo" status="st1" id="vo">
											<tr>
												<td>
													${st1.index+1}
												</td>
												<td>
													${sampCode}
												</td>
												<td>
													${labSamName}
													<input type="hidden" name="listLabSamTestVo[${st1.index}].labSamId" value="${labSamId}" />
												</td>
												<td>
													${labSamTypeName}
												</td>
												<s:iterator value="#vo.listLabDemoVo" status="son">
													<td style="text-align: center;" title="${demo3}">
														<s:if test="${demo2==null}||${demo2==''}">
																			---
																		</s:if>
														<s:else>
																			${demo2}
																		</s:else>
													</td>
												</s:iterator>
											</tr>
										</s:iterator>
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
