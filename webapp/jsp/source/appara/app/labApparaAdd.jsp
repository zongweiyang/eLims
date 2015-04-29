<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript" src="<%=basePath%>utils/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.3.2.js"></script>
		<script type="text/javascript">
        function submitvalue(actionstr){
            var df = document.forms[0];
            df.action = actionstr;
            df.submit();
        }
        
	function showUser(){
	  var url='<%=basePath%>/appara/showUser/showUser4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'仪器保管人：',
			maxBtn:false,
			rang: true,
			drag: true,
			resize: false,
			max:false,
			min:false,
			bgcolor:'#000',
			opacity:0.4,
			width:600, 
			iconTitle:false,
			btnBar:false,
			height:400,
			lock:true,
			autoPos:{left:'center',top:'center'}
		 });
	}
	var config = {
			itmes: [],
			display: true,
			callback:callback,
			init:""
		};
		var userWin = null;
		function selectLabUser(){
			if (userWin != null) {
				userWin.show();
			} else {
				userWin = $.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>utils/chooseuser/showLabUser4Select.jsp',
					title:'<s:property value="getText('selected.people')"/>',
					opacity:0.4,
					width:840,
					lock:true,
					max:false,
					min:false,
					close:function(){
						this.hide();
						return false;
					}
				 });
			}
			$.dialog.data("config",config);
		}
		function callback(data) {
			$("#keeper").val(data.names);
			$("#keeperId").val(data.ids);
		}
	function uploadFile(busId,busType){
				   fileTypes = '*.*;';
				   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='
				   +fileTypes+'&busType='+busType;
				   $.dialog({
						id:'id',
						content:'url:'+url,
						title:'<s:property value="getText('msg.add.attachment')"/>',
						opacity:0.4,
						width:300, 
						height:300,
						lock:true,
						max:false,
						resize:true
					 });
		}
	function deleteUploadFile(obj,id){
       if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
	       	$.ajax({
				   type: "POST",
				   url:"<%=basePath%>/LabUploadServlet?delFlag=Y&fileId="+id,
				   data:"",
				   async: false,
				   success: function(data){
					   if(data==true||data=="true")
					   {
						   alert('<s:property value="getText('msg.del.success')"/>');
						   $(obj).parent().remove();
					   }
				   }
			});
      	 }
       }
   	function select4Supplier(){
   		var url='<%=basePath%>/appara/showSupplier/showSupplier4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'仪器保管人：',
			maxBtn:false,
			rang: true,
			drag: true,
			resize: false,
			max:false,
			min:false,
			bgcolor:'#000',
			opacity:0.4,
			width:600, 
			iconTitle:false,
			btnBar:false,
			height:400,
			lock:true,
			autoPos:{left:'center',top:'center'}
		 });
   	}
	  </script>
		<style>
.ww100 {
	width: 120px;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
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
								${funName }：
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="appForm" id="form">
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="backList();return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('addLabAppara.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="app.info"/></span>
								</div>
								<table class="FormtableCon">
									<tr>
										<td>
											<label>
												<s:text name="app.no"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.typeId" id="typeId" value="${labApparaVo.typeId}" type="hidden" />
											<input name="labApparaVo.no" value="${labApparaVo.no}" id="no" type="text" valType="required" msg="请输入仪器编号" size="20" />
										</td>
										<td>
											<label>
												<s:text name="build.no"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.code" value="${labApparaVo.code}" id="code" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="app.name"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.name" value="${labApparaVo.name}" valType="required" msg="请输入仪器名称" id="name" type="text" size="20" />
										</td>
										<td>
											<label>
												<s:text name="supplier"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.supplier" value="${labApparaVo.supplier}" onclick="select4Supplier();" id="supplier" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="making.com"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.producer" type="text" value="${labApparaVo.producer}" size="20" id="producer" />
										</td>
										<td>
											<label>
												<s:text name="checking.config"/>：
											</label>
										</td>
										<td>
											<input value="${labApparaVo.testProperty}" name="labApparaVo.testProperty" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="regular.sys"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.spec" value="${labApparaVo.spec}" id="specification" type="text" size="20" />
										</td>
										<td>
											<label>
												<s:text name="place.site"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.ext01" id="ext01" value="${labApparaVo.ext01 }" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="bug.date"/>：
											</label>
										</td>
										<td>
											<input id="effectiveDate" name="labApparaVo.purTime" class="Wdate" type="text" value="${labApparaVo.purTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" readonly="readonly" />
										</td>
										<td>
											<label>
												<s:text name="price.number"/>（<s:text name="theme.yuan"/>）：
											</label>
										</td>
										<td>
											<input name="labApparaVo.price" value="${labApparaVo.price}" msg="请输入数字" valType="number" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="sam.state"/>：
											</label>
										</td>
										<td>
											<s:select theme="simple" cssStyle="width:152px" name="labApparaVo.status" id="status" list="#{'0':'正常','2':'报修','3':'停用','4':'报废'}" value="%{labApparaVo.status}" />
										</td>
										<td>
											<label>
												<s:text name="use.price"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.cost" valType="number" msg="请输入数字" value="${labApparaVo.cost}" type="text" size="20" />
											<s:text name="yuan.hour"/>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="check.cir"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.verPeriod" value="${labApparaVo.verPeriod}" id="verificationPeriod" type="text" size="20" onchange="isNoNatureNum(this);" />
											<s:select name="labApparaVo.verPeriodStr" value="%{labApparaVo.verPeriodStr}" headerKey="年" headerValue="年" list="#{'月':'月','天':'天'}" theme="simple" />
										</td>
										<td>
											<label>
												<s:text name="make.country"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.mcountry" value="${labApparaVo.mcountry}" id="mcountry" type="text" size="20" />
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="app.saver"/>：
											</label>
										</td>
										<td>
											<input name="labApparaVo.keeper" value="${labApparaVo.keeper}" id="keeper" type="text" size="20" onclick="selectLabUser()" />
											<input name="labApparaVo.keeperId" value="${labApparaVo.keeperId}" id="keeperId" type="hidden" />
										</td>
										<td>
											<label>
												<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="finished.check"/>：
											</label>
										</td>
										<td>
											<input type="radio" value="1" name="labApparaVo.isCheck">
											<s:text name="lab.yes"/>
											<input type="radio" value="5" name="labApparaVo.isCheck" checked="checked">
											<s:text name="lab.no"/>
										</td>
									</tr>
									<tr>
										<td>
											<label>
											<s:text name="msg.attachment"/>：：
											</label>
										</td>
										<td colspan="">
											<input name="labApparaVo.uuid" value="${labApparaVo.uuid}" id="uuid" size="40" type="hidden" />
											<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="uploadFile('${labApparaVo.uuid}','lab-app');"> <img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
											<div id="upfiles"></div>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<s:text name="remark"/>：
											</label>
										</td>
										<!-- 新增 字段用检定日期的代替-->
										<td colspan="3">
											<textarea rows="4" cols="60" name="labApparaVo.comment">${labApparaVo.comment}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
