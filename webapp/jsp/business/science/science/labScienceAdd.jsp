<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}

.editType {
	visibility: hidden;
}
</style>

		<script language=javascript> 
			  	function submitvalue(actionstr){
						$('form').attr('action','<%=basePath%>'+actionstr);
						$('form').submit();
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
   	
   	function addRow1(obj)
   	{
   	    var table=$('#person');
	    var index=table.find('tr').length-1;
	    var trHtml='<tr>';
	    trHtml+='<td class=\"c\"><input type="text" name="labSciScienceVo.authorList['+index+'].userName" readonly="true" maxlength="16"  value="${labSciScienceVo.authorList['+index+'].userName}" id="userName'+index+'" onclick="selectPerson(\'1\','+index+');return false;"/><input type="hidden" name="labSciScienceVo.authorList['+index+'].userId" maxlength="16"  id="userId'+index+'"/><input type="hidden" name="labSciScienceVo.authorList['+index+'].no" maxlength="16"  value="1" id="no'+index+'"/><span style=\"color:red;\">&nbsp;&nbsp;*</span></td>';
	    trHtml+='<td class=\"c\"><input type="text" name="labSciScienceVo.authorList['+index+'].techTitle" readonly="true" maxlength="16"  id="techTitle'+index+'"/></td>';
	    trHtml+='<td class=\"c\"><input type="text" name="labSciScienceVo.authorList['+index+'].duty" readonly="true" maxlength="16"   id="duty'+index+'"/></td>';
	    trHtml+='<td class=\"c\"><input type="text" name="labSciScienceVo.authorList['+index+'].learn" maxlength="16"   id="learn'+index+'"/></td>';
	    trHtml+='<td class=\"c\"><input type="text" name="labSciScienceVo.authorList['+index+'].remark" maxlength="16"   id="remark'+index+'"/></td>';
        trHtml+='<td class=\"c\"><a href="javascript:void(0);" onclick="deleteCheck(this,\'person\',\'rowNum1\');return false;"><s:text name="lab.code.del"/></a></td>';
        trHtml+='</tr>';
	    table.append(trHtml);
	    $('#rowNum1').attr('rowspan',index+2);
   	}
   	function deleteCheck(obj,id,id2){
   		var table=$('#'+id);
	    var index=table.find('tr').length;
	    if(index <= 2){
	    	alert('<s:property value="getText('cannotonerow')"/>');
	    }else{
	    	deleteEntity(obj,id,id2);
	    }
   	}
    function deleteEntity(obj,id,id2){
		$(obj).parent().parent().remove();
		var table=$('#'+id);
        var index=table.find('tr').length;
        $('#'+id2).attr('rowspan',index); 
	}
	
	function writeHui(name,index){
   		var learn=$('#'+name+index).val();
   	   var remark=$('#'+name+index).val();
   	   $('#userId'+index).attr(name,learn);
   	   $('#userId'+index).attr(name,remark);
   	}
   	
   		function selectPerson(msg,index)
   	{
   	   var ids=$('#ids').val();
	   var url='/science/labScience/getAllUser.action?labSciScienceVo.messageInfo='+msg+'&labSciScienceVo.index='+index;
	   $.dialog({
				id:'userId',
				content:'url:'+url,
				title:'<s:property value="getText('msg.user.list')"/>',
				opacity:0.4,
				width:800,
				height:400,
				max:false,
				min:false,
				lock:true
	    });
   	}
   	
   	function addRow2(obj)
   	{
   	    var table=$('#funds'); 
	    var index=table.find('tr').length-2;
	    var trHtml='<tr id="tr'+index+'">';
	    trHtml+='<td class=\"c\"><input type=\"text\"  name=\"labSciScienceVo.labSciFundsList['+index+'].name\" maxlength=\"16\" id=\"name'+index+'\" /><span style=\"color:red;\">&nbsp;&nbsp;*</span></td>';
	    trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.labSciFundsList['+index+'].money\" maxlength=\"16\" onblur=\"getPrice(this);return false;\" id=\"money'+index+'\"/><span style=\"color:red;\">&nbsp;&nbsp;*</span>&nbsp;&nbsp;万元</td>';
	    trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.labSciFundsList['+index+'].remark\" maxlength=\"16\" id=\"remark'+index+'\"/></td>';
        trHtml+='<td class=\"c\"><a href="javascript:void(0);" onclick="deleteCheck(this,\'funds\',\'rownum2\');return false;"><s:text name="lab.code.del"/></a></td>';
        trHtml+='</tr>';
	    table.find('tr').eq(index-1).after(trHtml);
	    $('#rownum2').attr('rowspan',index+3);
   	}
   function getPrice(obj)
   	{
	   	var money = 0;
		var val=$(obj).val();
		if(isNaN(val)){
			alert('<s:property value="getText('pleseinpunumber')"/>');
			$(obj).val("0.0");
			 $('#count').html("0.0");
		  	$('#applyFunds').val("0.0");
		}else{
			$('input[id*=money]').each(function(){
				var al = $(this).val();
				if(al == ''){
					al = 0.0;
				}
				money +=  parseFloat(al);
				
			});
		   $('#count').html(money);
		  $('#applyFunds').val(money);
		}
   	}
	</script>
	</head>
	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
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
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>

						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labSciScienceVo.code"
								value="${labSciScienceVo.code}" />
								<input type="hidden" name="labSciScienceVo.uuid"
								value="${labSciScienceVo.uuid}" />
							<input type="hidden" name="messageInfo" value="1" />
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<l:a uri="back" value="msg.back" />
													</td>
													<td>
														<l:a
															uri="science/labScience/addLabScience.action?labSciScienceVo.auditResult=0"
															value="lab.code.save" />
													</td>
													<td>
														<l:a
															uri="science/labScience/addLabScience.action?labSciScienceVo.auditResult=1"
															value="save.commit" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<!-- <div class="TabTable">
								<div class="TabTableNAV">
									<ul>
										<li id="li01" class="currenttab">
											<a href="javascript:;"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/addLabScience.action?labSciScienceVo.auditResult=tab2');"><span><s:text name="charge.details"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/addLabScience.action?labSciScienceVo.auditResult=tab3');"><span><s:text name="prj.result"/></span>
											</a>
										</li>
										<li id="li03" class="">
											<a href="javascript:;"
												onclick="goAction('science/labScience/addLabScience.action?labSciScienceVo.auditResult=tab4');"><span><s:text name="procedure.detail"/></span>
											</a>
										</li>
									</ul>
								</div> -->
							<div class="TabTableBOX01 b" id="Tab01">
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="prj.base.info"/></span>
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon" id="content">
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="item.name"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.name"
														maxlength="16" value="${labSciScienceVo.name}" id="name"
														valType="required,strLength" max="64"
														strLength-msg="项目名称长度不能超过64位" msg="项目名称不能为空" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="study.lec"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.topic"
														maxlength="16" value="${labSciScienceVo.topic}" id="topic"
														valType="required,strLength" max="64"
														strLength-msg="研究课题长度不能超过64位" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="appleypeol"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text"
														name="labSciScienceVo.masterName" maxlength="16"
														value="${labSciScienceVo.masterName}" id="masterName"
														readonly="true" valType="required" msg='<s:property value="getText('appnamenotempty')"/>' />
													<input type="hidden" name="labSciScienceVo.masterId"
														maxlength="16" value="${labSciScienceVo.masterId}"
														id="masterId" />
												</td>
												<td class="r" width="150">
													<label>
																<s:text name="start.time"/>：
													</label>
												</td>
												<td>
													<input type="text" name="labSciScienceVo.startDate"
														value="${labSciScienceVo.startDate}" id="startDate"
														class="Wdate" size="15"
														onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endDate\')}'});"
														valType="required" strLength-msg="		<s:text name="start.time"/>长度不能超过32位" />
													<s:text name="to"/>
													<input type="text" name="labSciScienceVo.endDate"
														value="${labSciScienceVo.endDate}" id="endDate"
														class="Wdate" size="15"
														onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startDate\')}'});"
														valType="required" strLength-msg="		<s:text name="start.time"/>长度不能超过32位" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="itemsource"/>：
													</label>
												</td>
												<td>
													<s:select cssStyle="width:273px" theme="simple"
														list="#request.sourceList" listKey="name" listValue="name"
														headerKey="" headerValue=""
														name="labSciScienceVo.source"
														value="${labSciScienceVo.source}" id="source" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="yilailab"/>：
													</label>
												</td>
												<td>
													<s:select cssStyle="width:273px" theme="simple"
														list="#request.orgList" listKey="id" listValue="name"
														headerKey="" headerValue=""
														name="labSciScienceVo.labOrgId"
														value="${labSciScienceVo.labOrgId}" id="labOrgId" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
												<s:text name="owndaches"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.subject"
														maxlength="32" value="${labSciScienceVo.subject}"
														id="subject" valType="strLength" max="32" msg="所属学科不能为空" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="xuekelingyu"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.domain"
														maxlength="32" value="${labSciScienceVo.domain}"
														id="domain" valType="strLength" max="32" msg="学科领域不能为空" />
												</td>
											</tr>

										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="applierinfo"/></span>
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon">
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="nike.name"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" maxlength="16"
														value="${labSciScienceVo.masterName}" id="masterName2"
														readonly="true" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="theme.sex"/>：
													</label>
												</td>
												<td>
													<s:radio id="masterSex" name="labSciScienceVo.sex"
														value="'男'" list="#{'男':getText('theme.male'),'女':getText('theme.female')}" theme="simple"
														cssStyle="border: 0"></s:radio>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.duty"
														maxlength="16" value="${labSciScienceVo.duty}"
														id="masterDuty" readonly="true" />
												</td>
												<td class="r" width="150">
													<label>
														职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：
													</label>
												</td>
												<td>
													<input size="40" type="text"
														name="labSciScienceVo.techTitle" maxlength="16"
														value="${labSciScienceVo.techTitle}" id="masterTechTitle"
														readonly="true" />
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="theme.phone"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text"
														name="labSciScienceVo.telephone" maxlength="16"
														value="${labSciScienceVo.telephone}" id="masterTelephone"
														readonly="true" />
												</td>
												<td class="r" width="150">
													<label>
														<s:text name="email"/>：
													</label>
												</td>
												<td>
													<input size="40" type="text" name="labSciScienceVo.email"
														maxlength="16" value="${labSciScienceVo.email}"
														id="masterEmail" readonly="true" />
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="itemcanyuinfo"/></span>
										<!-- <label style="padding-left: 885px;" >
											[&nbsp;<a style="align: right; font-color: green;"
											href="javascript:void(0);" onclick="addRow1(this);return false;"><s:text name="added.a.row"/></a>&nbsp;]
											</label> -->
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon_sform" id="person">
											<thead>
												<th width="18%" class="c">
												<s:text name="nike.name"/>
												</th>
												<th width="18%" class="c">
													<s:text name="theme.job.title"/>
												</th>
												<th width="18%" class="c">
											<s:text name="work.duty"/>
												</th>
												<th width="18%" class="c">
													研究领域
												</th>
												<th width="18%" class="c">
													职责&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</th>
												<th width="18%" class="c">
													<s:text name="lab.code.ops"/>
												</th>
											</thead>
											<tr>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.authorList[0].userName"
														maxlength="32" max="32" strLength-msg="参与人名称长度不能超过32位"
														readonly="true" id="userName0"
														onclick="selectPerson('1','0');return false;" />
													<input type="hidden"
														name="labSciScienceVo.authorList[0].userId" maxlength="16"
														id="userId0" valType="required" msg="姓名不能为空" />
													<input type="hidden"
														name="labSciScienceVo.authorList[0].no" maxlength="16"
														value="1" id="no0" />
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.authorList[0].techTitle"
														maxlength="16" readonly="true" id="techTitle0" />
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.authorList[0].duty" maxlength="16"
														readonly="true" id="duty0" />
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.authorList[0].learn" maxlength="16"
														id="learn0" />
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.authorList[0].remark" maxlength="16"
														id="remark0" />
												</td>
												<td class="c">
													<l:a href="#" uri=""
														onclick="deleteCheck(this,'person','rowNum1');" value="lab.code.del" />
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="itemprefeeinfo"/></span>
										<label style="padding-left: 920px;">
											[&nbsp;
											<a style="align: right; font-color: green;"
												href="javascript:void(0);"
												onclick="addRow2(this);return false;"><s:text name="added.a.row"/></a>&nbsp;]
										</label>
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon_sform" id="funds">
											<thead>
												<th width="18%" class="c">
													<s:text name="paying.out.name"/>
												</th>
												<th width="18%" class="c">
													金额
												</th>
												<th width="18%" class="c">
													<s:text name="remark"/>
												</th>
												<th width="18%" class="c">
													<s:text name="lab.code.ops"/>
												</th>
											</thead>
											<tr id="tr0">
												<td class="c">
													<input type="text"
														name="labSciScienceVo.labSciFundsList[0].name"
														maxlength="16"
														value="${labSciScienceVo.labSciFundsList[0].name}"
														id="name0" max="32" strLength-msg="支出名目长度不能超过32位"
														valType="required" msg="支出名目不能为空" />
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.labSciFundsList[0].money"
														maxlength="16"
														value="${labSciScienceVo.labSciFundsList[0].money}"
														id="money0" valType="required" msg="金额不能为空"
														onblur="getPrice(this);return false;" max="32" />
													&nbsp;&nbsp;万元
												</td>
												<td class="c">
													<input type="text"
														name="labSciScienceVo.labSciFundsList[0].remark"
														maxlength="16"
														value="${labSciScienceVo.labSciFundsList[0].remark}"
														id="remark0" />
												</td>
												<td class="c">
													<l:a href="#" uri=""
														onclick="deleteCheck(this,'funds','rownum2');" value="lab.code.del" />
												</td>
											</tr>
											<tr>
												<td width="275" class="c">
													<font color="red">总计：</font>
												</td>
												<td colspan="3">
													<label id="count" style="margin-left: 43px; color: red;">
														0.0
													</label>
													<font color="red">&nbsp;&nbsp;万元</font>
													<input style="margin-left: 43px" type="hidden"
														name="labSciScienceVo.applyFunds" maxlength="16"
														id="applyFunds" value="${labSciScienceVo.applyFunds}" />
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="itemdetails"/></span>
										<label style="float: right; padding-right: 10px;"
											onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
									</div>
									<div>
										<table class="FormtableCon">
											<tr>
												<td class="r" width="150">
													研究基础：
												</td>
												<td>
													<textarea rows="2" cols="40"
														name="labSciScienceVo.foundation"
														value="${labSciScienceVo.foundation}" valType="strLength"
														max="512" strLength-msg="研究基础不能超过512位"></textarea>
												</td>
												<td class="r" width="150">
													研究方案：
												</td>
												<td>
													<textarea rows="2" cols="40" name="labSciScienceVo.plan"
														value="${labSciScienceVo.plan}" valType="strLength"
														max="512" strLength-msg="研究方案不能超过512位"></textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													风险评估：
												</td>
												<td>
													<textarea rows="2" cols="40" name="labSciScienceVo.risk"
														value="${labSciScienceVo.risk}" valType="strLength"
														max="512" strLength-msg="风险评估不能超过512位"></textarea>
												</td>
												<td class="r" width="150">
											<s:text name="kexingxingfen"/>：
												</td>
												<td>
													<textarea rows="2" cols="40"
														name="labSciScienceVo.feasibility"
														value="${labSciScienceVo.feasibility}" valType="strLength"
														max="512" strLength-msg="可行性分析不能超过512位"></textarea>
												</td>
											</tr>
											<tr align="center">
												<td class="r" width="150">
													<s:text name="itembacksource"/>：
												</td>
												<td>
													<textarea rows="2" cols="40"
														name="labSciScienceVo.backdrop"
														value="${labSciScienceVo.backdrop}" valType="strLength"
														max="512" strLength-msg="<s:text name="itembacksource"/>不能超过512位"></textarea>
												</td>
												<td class="r" width="150">
													研究目标及内容：
												</td>
												<td>
													<textarea rows="2" cols="40" name="labSciScienceVo.goal"
														value="${labSciScienceVo.goal}" valType="strLength"
														max="512" strLength-msg="研究目标及内容不能超过512位"></textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													研究成果及应用：
												</td>
												<td>
													<textarea rows="2" cols="40"
														name="labSciScienceVo.achievement"
														value="${labSciScienceVo.achievement}" valType="strLength"
														max="512" strLength-msg="研究成果及应用不能超过512位">${labSciScienceVo.achievement}</textarea>
												</td>
												<td class="r" width="150">
													<s:text name="lab.remark"/>：
												</td>
												<td>
													<textarea rows="2" cols="40" name="labSciScienceVo.remark"
														valType="strLength" max="512" strLength-msg="长度不能超过512位"></textarea>
												</td>
											</tr>
											<tr>
												<td class="r" width="150">
													<label>
														<s:text name="upload.attachment"/>：
													</label>
												</td>
												<td colspan="3">
													<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);"
														onclick="uploadFile('${labSciScienceVo.uuid}','lab-science');">
														<img height="20" width="20"
															src="<%=basePath%>img/filesave.gif" /><b><s:text name="up.file"/></b> </a>
															<s:if test="${fn:length(uplodeList)>0}">
															<s:iterator status="st2" value="#request.uplodeList" id="">
																<span> <a href="${path }" id="fileId">${name
																		} </a> <a href="javascript:;" id="fileIcon"
																	onclick="deleteUploadFile(this,'${id}')"><img
																			src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													<div id="upfiles"></div>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
					</div>
					</form>
					</div>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
