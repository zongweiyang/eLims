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
									<span><s:text name="use.chg"/></span>
								</h2>
							</div>
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('/appara/useing/listLabAppUse4View.action?labApparaUseVo.id=${id}&labApparaUseVo.typeId=${labApparaUseVo.typeId}');return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
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
											<input type="text" disabled="disabled" id="appName" name="labApparaUseVo.appName" valType="required" msg="请选择仪器" value="${labApparaUseVo.appName}" onclick="showApp();" />
											<input type="hidden" id="appId" name="labApparaUseVo.appId" value="${labApparaUseVo.appId}" />
											<input type="hidden" id="id" name="labApparaUseVo.id" value="${labApparaUseVo.id}" />
										</td>
										<td>
											<s:text name="app.no"/>：
										</td>
										<td>
											<input type="text" disabled="disabled" id="appNo" name="labApparaUseVo.appNo" value="${labApparaUseVo.appNo}" />
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="pre.starttime"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.beStartDate" readonly="readonly" value="${labApparaUseVo.beStartDate}" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
										</td>
										<td>
											<s:text name="pre.endtime"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.beEndDate" readonly="readonly" value="${labApparaUseVo.beEndDate}" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="start.time"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.startDate" value="${labApparaUseVo.startDate}" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
										</td>
										<td>
											<s:text name="end.time"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.endDate" value="${labApparaUseVo.endDate}" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="sys.ma.sign"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.managerUser" value="${labApparaUseVo.managerUser}" type="text" />
										</td>
										<td>
											<s:text name="user.assign"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.useUser" value="${labApparaUseVo.useUser}" type="text" />
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="tempture"/>：
										</td>
										<td>
											<input  disabled="disabled" name="labApparaUseVo.wenDu" value="${labApparaUseVo.wenDu}" type="text" />
										</td>
										<td>
											<s:text name="shidu"/>：
										</td>
										<td>
											<input disabled="disabled" name="labApparaUseVo.shiDu" value="${labApparaUseVo.shiDu}" type="text" />
										</td>
									</tr>
									<tr>
										<td rowspan="4">
											<s:text name="working.state"/>：
										</td>
									</tr>
									<tr>
										<td>
											<input  disabled="disabled" name="labApparaUseVo.beforeUse" value="${labApparaUseVo.beforeUse}" type="text" />(<s:text name="before.using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<input disabled="disabled"  name="labApparaUseVo.nowUse" value="${labApparaUseVo.nowUse}" type="text" />(<s:text name="using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<input  disabled="disabled" name="labApparaUseVo.afterUse" value="${labApparaUseVo.afterUse}" type="text" />(<s:text name="after.using"/>)
										</td>
									</tr>
									<tr>
										<td>
											<s:text name="remark"/>：
										</td>
										<td>
											<textarea disabled="disabled" cols="40" rows="6" name="labApparaUseVo.remark" id="remark" size="80">${labApparaUseVo.remark}</textarea>
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
