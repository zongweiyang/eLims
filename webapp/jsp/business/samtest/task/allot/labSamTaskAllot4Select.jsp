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

.FormtableCon input {
	width: 200px;
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
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
       	function submitx(){
       		closeMe();
       	}
      function submitvalue(actionstr){
      		if($("#samNo").val()==''){
      			validate.tip('请输入批次号.！',$('#samNo'));
      			return false;
      		}
    		if(confirm('<s:property value="getText('confirmconmmit')"/>')){
				$('form').attr('action','<%=basePath%>'+actionstr);
				$('form').submit();
			}
		}
		$(function(){
			$('#samNo').focus();
		});
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
								<s:text name="assign.info"/>：
								<span><s:text name="display"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labSamTestAllot">
							<input type="hidden" name="labSamTestBeaVo.taskIds" value="${labSamTestVo.taskId}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="samtest/labSamTask/updateLabSamTask4Allot.action" onclick="submitvalue('samtest/labSamTask/updateLabSamTask4Allot.action');" img="img/accept.gif"  value="page.confirm"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="item.info"/></span>
										<label style="float: right; margin-right: 20px;" onclick="javascript:$(this).parent().next().toggle();">
											<font color="red">[<s:text name="open.close"/></font>]
										</label>
									</div>
										<table class="FormtableCon" id="orgTable">
												<tr>
													<td>
														<label><s:text name="batched.id"/>：</label>
													</td>
													<td>
														<input id="samNo" name="labSamTestBeaVo.name"   type="text"/> <font color="red">*</font>
													</td>
												</tr>
										</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="item.info"/></span>
										<label style="float: right; margin-right: 20px;" onclick="javascript:$(this).parent().next().toggle();">
											<font color="red">[<s:text name="open.close"/></font>]
										</label>
									</div>
										<table class="myworkingboxttable" id="orgTable">
											<thead>
												<tr>
													<th width="30px;">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="task.no"/>
													</th>
													<th>
														<s:text name="checking.item"/>
													</th>
													<th>
														<s:text name="assign.number"/>
													</th>
													<th>
														<s:text name="checker"/>
													</th>
													<th>
														<s:text name="tester"/>
													</th>
												</tr>
											</thead>
											<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
												<tr>
													<td class="c" rowspan="${spanRow}">
														${st.index+1}
													</td>
													<td class="c" rowspan="${spanRow}">
														${taskNo}
													</td>
													<s:iterator value="#vo.listLabSamTest" status="stt" >
															<td>
																${itemName}
																<input type="hidden" name="listLabSamTestVo[${sort}].itemId" value="${itemId}" />
																<input type="hidden" name="listLabSamTestVo[${sort}].taskId" value="${taskId}" />
															</td>
															<td>
																${sampNum}
																 <input type="hidden" value="${sampNum}" name="listLabSamTestVo[${sort}].sampNum" /> 
															</td>
															<td class="c">
																${testManName}
																<input type="hidden" value="${testManId}" name="listLabSamTestVo[${sort}].testManId"  />
																<input type="hidden" value="${testManName}" name="listLabSamTestVo[${sort}].testManName"  />
															</td>
															<td class="c">
																${checkManName}
																<input type="hidden" value="${checkManId}" name="listLabSamTestVo[${sort}].checkManId" />
																<input type="hidden" value="${checkManName}" name="listLabSamTestVo[${sort}].checkManName" />
															</td>
														</tr>
													</s:iterator>
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
