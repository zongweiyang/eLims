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
            if (actionstr == 'updateApparatusInfo.action') {
                if (validateForm(df)) {
                    df.submit();
                }
            }
            else {
                df.submit();
            }
        }
        
		function isDecimal2(obj){
			var value=$(obj).val();
			var myRegExp =/^\d{1,}(\.\d{1,2})?$/;
			var message = "";
			if(value!=""){
				if (!myRegExp.test(value)) {
					alert('<s:property value="getText('correctprice')"/>');
					$(obj).val("");
				}
			}
		}
		
        function isNoNatureNum(obj){
       		var value=$(obj).val();
            var strReg = /^\d{1,}$/;
            var msg="";
			if (value != "") {
                if (!strReg.test(value)) {
                    alert('<s:property value="getText('plseiputnumber')"/>');
                    $(obj).val("");
                }
			}
        }
		
        function check(){
			var msg="";
            if ($("#purchaseTime").val() == '' || $("#purchaseTime").val() == null) {
                msg+="请选择购买日期"+'\n';
            }
            if ($("#verificationPeriod").val() == '' || $("#verificationPeriod").val() == null) {
                msg+="请填写检定周期"+'\n';
            }
			if ($("#effectiveDate").val() == '' || $("#effectiveDate").val() == null) {
                msg+="请填写有效日期"+'\n';
            }
			if ($("#verificationDate").val() == '' || $("#verificationDate").val() == null) {
                msg+="请填写检定日期"+'\n';
            }
			//msg+=isDecimal2($("#price").val());
			//msg+=isNoNatureNum($("#verificationPeriod").val());
			if(msg.length>0){
				alert(msg);
				return false;
			}else{
				return true;
			}
        }
	function uploadFile(busId,busType){
	   fileTypes = '*.*';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&urls=/appara/preUpdateLabApp.action';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('appattupload')"/>',
			opacity:0.4,
			width:300, 
			height:300,
			lock:true,
			max:false,
			resize:false
		 });
	}//多附件labUpLoads.jsp height:300,
	function deleteThis(obj,id){
		if(!confirm('<s:property value="getText('confirmnotreplse')"/>')) return false;
		var url = '<%=basePath%>appara/delLabAppFile.action'; 
		$.ajax({
				url:url,
				type:'POST',
				data:{'fileId':id},
				dataType:'text',
				success:function(data){
					if('true' == data){
						alert('<s:property value="getText('delete.success')"/>');
						$('#'+id).html('');
						  $(obj).parent().remove();
					}
				},
				error:function(){
					alert('<s:property value="getText('theme.net.fail')"/>');
				}
			});	
	}
	function showUser(){
	  var url='<%=basePath%>/appara/showUser/showUser4Select.action';
		$.dialog({
			id:'power',
			content:'url:'+url,
			title:'<s:property value="getText('apprsaver')"/>',
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
	  </script>
		<style>
.ww100 {
	width: 120px;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto;">
		<form action="" method="post" id="form">
			<input type="hidden" name="labApparaVo.id" value="${labApparaVo.id}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="app.repair.list"/></span>
											</h2>
										</div>
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
																	<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalue('updateLabApp.action');return false;"><img height="20" width="20" src="<%=basePath%>img/add.gif" /><b><s:text name="lab.code.save"/></b> </a>
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
														<input name="labApparaVo.no" value="${labApparaVo.no}" id="no" type="text" size="20" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
													<td>
														<label>
															<s:text name="build.no"/>：
														</label>
													</td>
													<td>
														<input name="labApparaVo.code" value="${labApparaVo.code}" id="code" type="text" size="20" />
														<!-- <font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font> -->
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="app.name"/>：
														</label>
													</td>
													<td>
														<input name="labApparaVo.name" value="${labApparaVo.name}" id="name" type="text" size="20" />
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
													<td>
														<label>
															<s:text name="supplier"/>：
														</label>
													</td>
													<td>
														<input name="labApparaVo.supplier" value="${labApparaVo.supplier}" id="code" type="text" size="20" />
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
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
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
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
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
														<font color="red">&nbsp;&nbsp;&nbsp;&nbsp;*</font>
													</td>
													<td>
														<label>
															<s:text name="price.number"/>（<s:text name="theme.yuan"/>）：
														</label>
													</td>
													<td>
														<input name="labApparaVo.price" value="${labApparaVo.price}" type="text" size="20" onchange="isDecimal2(this);" />
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="sam.state"/>：
														</label>
													</td>
													<td>
														<s:select theme="simple" cssStyle="width:152px" name="labApparaVo.status" id="status" list="#{'0':'正常','1':'报修','2':'停用','7':'报废'}" value="%{labApparaVo.status}" />
														&nbsp;&nbsp;&nbsp;
														<span style="color: red">*</span>
													</td>
													<td>
														<label>
															<s:text name="use.price"/>：
														</label>
													</td>
													<td>
														<input name="labApparaVo.cost" value="${labApparaVo.cost}" type="text" size="20" onchange="isDecimal2(this);" />
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
														<input name="labApparaVo.keeper" value="${labApparaVo.keeper}" id="keeper" type="text" size="20" onclick="selectLabUser();" />
														<input name="labApparaVo.keeperId" value="${labApparaVo.keeperId}" id="keeperId" type="hidden" />
													</td>
													<td>
														<label>
															<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="finished.check"/>：
														</label>
													</td>
													<td>
													<s:if test="${labApparaVo.isCheck=='5'}">
														<input type="radio" value="1" disabled="disabled" name="labApparaVo.isCheck">
														<s:text name="lab.yes"/>
														<input type="radio" value="5" disabled="disabled" name="labApparaVo.isCheck" checked="checked">
														<s:text name="lab.no"/>
													</s:if>
													<s:else>
														<input type="radio" value="1" disabled="disabled" checked="checked" name="labApparaVo.isCheck">
														<s:text name="lab.yes"/>
														<input type="radio" value="5" disabled="disabled" name="labApparaVo.isCheck" >
														<s:text name="lab.no"/>
													</s:else>
													</td>
												</tr>
												<tr>
													<td colspan="5">
														<a id="BtnEdit" class="zPushBtn"
															href="javascript:void(0);"
															onclick="uploadFile('${labReagentVo.id}','lab-app');">
															<img height="20" width="20"
																src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
														
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span> <a href="${path }" id="fileId">${name
																		} </a> <a href="javascript:;" id="fileIcon"
																	onclick="deleteUploadFile(this,'${id}')"><img
																			src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
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
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
