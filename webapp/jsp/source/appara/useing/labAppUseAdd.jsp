<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
    function submitvalue(actionstr){
		var df = document.forms[0];
	    df.action = actionstr;
		df.submit();
    }
    function showApp(){
	  var url='<%=basePath%>/appara/drop/showApp4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'<s:property value="getText('sleapptus')"/>',
			maxBtn:false,
			rang: true,
			drag: true,
			resize: false,
			bgcolor:'#000',
			opacity:0.4,
			width:600, 
			iconTitle:false,
			btnBar:false,
			height:400,
			max:false,
			min:false,
			lock:true,
			autoPos:{left:'center',top:'center'}
		 });
	} 
	function back()
	{
	   window.location.href='<%=basePath%>/appara/useing/listLabAppUse.action';
	}
	</script>
		<style>
.tabtableboxtable td label {
	float: none;
}

.tabtableboxtable {
	border: #c1c1c1 0.5px solid;
	border-collapse: collapse;
}

.tabtableboxtable td {
	border: #c1c1c1 0.5px solid;
	border-collapse: collapse;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto;">
		<form action="" method="post">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<div class="myworkingbox">
							<div class="myworkingboxttitle">
								<h2>
									${funName }：
									<span><s:text name="admin.add"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="back();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabAppUse.action');return false;"><img height="20" width="20" src="<%=basePath%>img/xinjian.gif" /><b><s:text name="post.commit"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="app.use.rec"/></span>
								</div>
								<table id="tableId" class="FormtableCon">
									<tr>
										<td>
											<s:text name="app.name"/>：
										</td>
										<td>
											${labApparaUseVo.appName}
											<input type="text" id="appName" name="labApparaUseVo.appName" valType="required" msg="请选择仪器" value="${labApparaUseVo.appName}" onclick="showApp();" />
											<input type="hidden" id="appId" name="labApparaUseVo.appId" value="${labApparaUseVo.appId}" />
										</td>
										<td>
											<s:text name="app.no"/>：
										</td>
										<td>
											<input type="text" id="appNo" name="labApparaUseVo.appNo" value="${labApparaUseVo.appNo}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="start.time"/>：
										</td>
										<td>
											<input name="labApparaUseVo.startDate" id="startDate" value="${labApparaUseVo.startDate}"   type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});" />
										</td>
										<td>
											<s:text name="end.time"/>：
										</td>
										<td>
											<input name="labApparaUseVo.endDate" id="endDate" value="${labApparaUseVo.endDate}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});"/>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="sys.ma.sign"/>：
										</td>
										<td>
											<input name="labApparaUseVo.managerUser" value="${labApparaUseVo.managerUser}" type="text" />
										</td>
										<td>
											<s:text name="user.assign"/>：
										</td>
										<td>
											<input name="labApparaUseVo.useUser" value="${labApparaUseVo.useUser}" type="text" />
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="tempture"/>：
										</td>
										<td>
											<input  name="labApparaUseVo.wenDu" value="${labApparaUseVo.wenDu}" type="text" />
										</td>
										<td>
											<s:text name="shidu"/>：
										</td>
										<td>
											<input name="labApparaUseVo.shiDu" value="${labApparaUseVo.shiDu}" type="text" />
										</td>
									</tr>
									<tr>
										<td rowspan="4">
											<s:text name="working.state"/>：
										</td>
									</tr>
									<tr>
										<td>
											<input  name="labApparaUseVo.beforeUse" value="${labApparaUseVo.beforeUse}" type="text" />(<s:text name="before.using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<input  name="labApparaUseVo.nowUse" value="${labApparaUseVo.nowUse}" type="text" />(<s:text name="using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<input  name="labApparaUseVo.afterUse" value="${labApparaUseVo.afterUse}" type="text" />(<s:text name="after.using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="remark"/>：
										</td>
										<td>
											<textarea cols="40" rows="6" name="labApparaUseVo.remark" id="remark" size="80">${labApparaUseVo.remark}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
