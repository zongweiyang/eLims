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
		var isPass='0';
		function submitvalue(actionstr){
			var key=true;
			$("#numTab").find('input[name*=demo]').each(function(){
				validate.tip('<s:property value="getText('havenochecksam')"/>',$(this));
				key=false;
				return false;
			});
			if(key==true){
				$('form').attr('action','<%=basePath%>'+actionstr);
				if(confirm('<s:property value="getText('confirmconmmit')"/>')){
					$('form').submit();
				}
			}
			
		}
		function submit2Back(actionstr){
			var html = $('#auditMessage').html();
			html=html.replace(/(^\s*)|(\s*$)/g,'');
			if(html==''){
				alert('<s:property value="getText('input.audited')"/>');
				return false;
			}
			$('form').attr('action','<%=basePath%>'+actionstr);
			if(confirm('<s:property value="getText('confirmconmmit')"/>')){
				$('form').submit();
			}
		}
		function checkTd(obj){
			obj=$(obj);
			if(obj.css('background-color')=='red'){
				obj.css('background-color','#eaf7ff');
				obj.find('input[name*=isBack]').val('N');
			}else{
				obj.css('background-color','red');
				obj.find('input[name*=isBack]').val('Y');
			}
		}
		/*function agree(){
			isPass='0';
			$('#guidelines').css('display','none');
			$('.myworkingboxttable tr:gt(0)').find('td').each(function(){
					$(this).css('background-color','#eaf7ff');
			});
		}
		function noPass(){
			isPass='1';
			$('#guidelines').css('display','inline');
		}*/
		function copyTh(obj){
			obj=$(obj);
			var cIndex=obj.closest('th').index();
			$('.myworkingboxttable tr:gt(0)').each(function(){
				$(this).find('td').eq(cIndex).css('background-color','red');
			});
		}
		function checkAll(){
			
		}
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
							 <input type="hidden" value="${labSamTestBeatchVo.id}" name="labSamTestVo.beatchId"/>
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
															<l:a uri="samtest/labSamTest/updateLabSamTest4Check.action" onclick="submitvalue('samtest/labSamTest/updateLabSamTest4Check.action?labSamTestVo.auditResult=1');" value="flow.pass" img="/img/icon/filesave.gif"/>
														</td>
														<td>
															<l:a uri="samtest/labSamTest/updateLabSamTest4Check.action" onclick="submit2Back('samtest/labSamTest/updateLabSamTest4Check.action?labSamTestVo.auditResult=-1');" value="msg.back" img="/img/icon/filesave.gif"/>
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
														<s:text name="xiaoyan.opinion"/>：
													</label>
												</td>
												<td colspan="2">
													<textarea rows="3" name="labSamTestVo.auditMessage" cols="20" id="auditMessage"></textarea>
												</td>
											</tr>
											<tr>

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
									<table class="myworkingboxttable" id="numTab">
										<thead>
											<tr>
												<th width="100">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th style="width: 110px;">
													<s:text name="sam.no"/>
												</th>
												<th>
													<s:text name="sam.name"/>
												</th>
												<th>
													<s:text name="sam.type"/>
												</th>
												<s:iterator value="#request.listLabSamTestVo[0]" id="st">
													<s:iterator value="#st.listTitle">
														<th onclick="copyTh(this)">
															${demo1}
														</th>
													</s:iterator>
												</s:iterator>
											</tr>
										</thead>
										<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
											<tr>
												<td>
													${st.index+1}
												</td>
												<td>
													${sampCode}
												</td>
												<td>
													${labSamName}
												</td>
												<td>
													${labSamTypeName}
												</td>
												<s:iterator value="#vo.listLabDemoVo" status="stt">
													<s:if test="${demo!='Y'&&demo3=='Y'}">
														<td style="text-align: center;cursor: hand;" onclick="checkTd(this)">
															<s:if test="${demo4=='Y'}">
																<span style="color: red;" title='<s:property value="getText('fucessd')"/>'>${demo1}</span>
															</s:if>
															<s:else>
																${demo1}
															</s:else>
															<input type="hidden" name="labSamTestVo.listLabSamTest[${demo5}].id" value="${demo2}" />
															<input type="hidden" name="labSamTestVo.listLabSamTest[${demo5}].isBack" value="" />
														</td>
													</s:if>
													<s:elseif test="${demo!='Y'&&demo3!='Y'}">
														<td style="text-align: center;">
														<span style="color: red;"><s:text name="testing"/></span>
														<input type="hidden" name="demo" value="${demo}" />
														</td>
													</s:elseif>
													<s:else>
														<td style="text-align: center;">
															--
														</td>
													</s:else>
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
