<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property
				value="#session.SessionContainer.orgUnit" /></title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.myworkingboxttable {
	width: 95%;
	border: 0px solid #CFCFCF;
	margin: 0 5px;
	border-collapse: collapse;
	margin-left: 20px;
}
</style>
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   	var index = '${labSciScienceVo.index}';
	   	var messageInfo = '${labSciScienceVo.messageInfo}';
	    function closeMe(){
			api.close();
	    }
       	function submitx(){
       		closeMe();
       	}
       	$(function(){
       		if(messageInfo == 1){
			$('input[name*="userId"]',D).each(function(){
				$('input[value="'+$(this).val()+'"]').prop('checked',true);
			});
       		}
		});
       	
       	function checkUser(index,num){
       	if(num=='0'){
       		$('input[type="checkbox"]:checked').each(function(){
       		    var check = $(this);
       		    var masterSex = $(check).attr("key2");
       		    $('#masterId',D).val($(check).val());
       		    $('#masterName',D).val($(check).attr("key1"));
       		    $('#masterName2',D).val($(check).attr("key1"));
       		    $('input:radio[value='+masterSex+']',D).attr('checked','true');
       		   // $('#masterSex',D).attr("checked",masterSex);
       		    $('#masterDuty',D).val($(check).attr("key3"));
       		    $('#masterTechTitle',D).val($(check).attr("key4"));
       		    $('#masterTelephone',D).val($(check).attr("key5"));
       		    $('#masterEmail',D).val($(check).attr("key6"));
       		});
       		closeMe();
       		}
       	}
       	
       	function chooseOne(cb){  
         var obj = document.getElementsByName("id"); 
         for (i=0; i<obj.length; i++){   
             if (obj[i]!=cb){
              obj[i].checked = false;
             }else {
             	obj[i].checked = true;  
             } 
         }
     }
       	
       <%--	function selectUser()
       	{
       		var userIds = "";var techTitles = "";var userNames = "";var dutys = "";
       		$('input[type="checkbox"]:checked').each(function(){
       		    var check = $(this);
       		    var userId = $(check).val();
       		    var userName = $(check).attr("key1");
       		    var duty = $(check).attr("key3");
       		    var techTitle = $(check).attr("key4");
       		    userIds += userId + ",";
       		    userNames += userName + ",";
       		    dutys += duty + ",";
       		    techTitles += techTitle + ",";
       		});
       		if(userIds.split(",").length > 0){
       			userIds = userIds.substring(0,userIds.length-1);
       		}
       		if(userNames.split(",").length > 0){
       			userNames = userNames.substring(0,userNames.length-1);
       		}
       		if(dutys.split(",").length > 0){
       			dutys = dutys.substring(0,dutys.length-1);
       		}
       		if(techTitles.split(",").length > 0){
       			techTitles = techTitles.substring(0,techTitles.length-1);
       		}
       		$('#userId'+index,D).val(userIds);
       		$('#userName'+index,D).val(userNames);
       		$('#duty'+index,D).val(dutys);
       		$('#techTitle'+index,D).val(techTitles);
       		closeMe();
       	}--%>
       	
       	function selectUser()
       	{
   	        var learns = "";
   	        var remarks = "";
       		var userIds = "";
       		$('input[name*="userId"]',D).each(function(){
					userIds += $(this).val()+",";
					learns  += $(this).attr("learn")+",";
					remarks += $(this).attr("remark")+",";
				});
			if(userIds.split(",").length > 0){
       			userIds = userIds.substring(0,userIds.length-1);
       		}
       		if(learns.split(",").length > 0){
       			learns = learns.substring(0,learns.length-1);
       		}
       		if(remarks.split(",").length > 0){
       			remarks = remarks.substring(0,remarks.length-1);
       		}
       	    $("#person thead",D).eq(0).nextAll().remove();
       	    $('input[type="checkbox"]:checked').each(function(){
       	        var table=$('#person',D);
	            var index=table.find('tr').length-1;
	            var check = $(this);
       	        var id=$(check).val();
       		    var name=$(check).attr("key1");
       		    var duty=$(check).attr("key3");
       		    var techTitle=$(check).attr("key4");
       		    var learn = "";
   	        	var remark = "";
       		    var userIdArray = userIds.split(",");
       		    var learnArray = learns.split(",");
       		    var remarkArray = remarks.split(",");
       		    if(userIdArray.length > 0){
	   		    	for(var i = 0 ;i < userIdArray.length;i++){
	     		    	if(userIdArray[i] == id){
	     		    		learn = learnArray[i];
	     		    		remark = remarkArray[i];
	     		    	}
	   		    	}
       		    }
	            var trHtml='<tr>';
	            trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.authorList['+index+'].userName\" readonly=\"true\" maxlength=\"16\"  value=\"'+name+'\" id=\"userName'+index+'\" onclick="selectPerson(\'1\','+index+');return false;" /><input type=\"hidden\" name=\"labSciScienceVo.authorList['+index+'].userId\" maxlength=\"16\"  learn=\"'+learn+'\" remark=\"'+remark+'\" value=\"'+id+'\" id=\"userId'+index+'\"/><input type=\"hidden\" name=\"labSciScienceVo.authorList['+index+'].no\" maxlength=\"16\"  value=\"1\" id=\"no'+index+'\"/><span style=\"color:red;\">&nbsp;&nbsp;*</span></td>';
	            trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.authorList['+index+'].techTitle\" readonly=\"true\" maxlength=\"16\"  value=\"'+techTitle+'\" id=\"techTitle'+index+'\"/></td>';
	            trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.authorList['+index+'].duty\" readonly="true\" maxlength=\"16\"  value=\"'+duty+'\" id=\"duty'+index+'\"/></td>';
	            trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.authorList['+index+'].learn\" maxlength=\"16\"  value=\"'+learn+'\" id=\"learn'+index+'\"  onblur=\"writeHui(\'learn\','+index+');\" /></td>';
	            trHtml+='<td class=\"c\"><input type=\"text\" name=\"labSciScienceVo.authorList['+index+'].remark\" maxlength=\"16\"  value=\"'+remark+'\" id=\"remark'+index+'\"  onblur=\"writeHui(\'remark\','+index+');\"/></td>';
                trHtml+='<td class=\"c\"><a href="javascript:void(0);" onclick="deleteCheck(this,\'person\',\'rowNum1\');return false;"><s:text name="lab.code.del"/></a></td>';
                trHtml+='</tr>';
	            table.append(trHtml);
	            $('#rowNum1',D).attr('rowspan',index+2);
       		});
       		closeMe();
       	}
       
</script>
	</head>

	<body class="" id="mainid" style="overflow-y: auto;">
		<!-- pageResule query -->
		<form action="" method="post" name="listform" theme="simple">
			<input type="hidden" name="funId" value="${funId}" />
			<table id="bodyTable" class="bodytable_pop" width="96%"
				cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="myworkingbox_pop">
										<s:if test="${labSciScienceVo.messageInfo == 1}">
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd"
															style="padding: 6px 18px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		<a id="BtnEdit" class="zPushBtn"
																			href="javascript:void(0);"
																			onclick="selectUser();return false;"><img
																				height="20" width="20"
																				src="<%=basePath%>img/filesave.gif" /><b><s:text name="page.confirm"/></b> </a>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
										</s:if>
										<s:else>
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd"
															style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		&nbsp;&nbsp;
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
										</s:else>
										<table class="listTable_pop" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th class="c">
													</th>
													<th class="c" width="30">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th class="l">
													<s:text name="nike.name"/>
													</th>
													<th class="c">
												<s:text name="theme.sex"/>
													</th>
													<th class="l">
												<s:text name="work.duty"/>
													</th>
													<th class="l">
														<s:text name="theme.job.title"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:set name="alllist" value="#request.userList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<s:if test="${labSciScienceVo.messageInfo == 0}">
																<input type="checkbox" value="${id}" key1="${name}"
																	key2="${sex}" key3="${duty}" key4="${techTitle}"
																	key5="${telephone}" key6="${email}" name="keeperId"
																	id="id${st.index}"
																	onclick="checkUser('${st.index}','${labSciScienceVo.messageInfo}');" />
															</s:if>
															<s:if test="${labSciScienceVo.messageInfo == 1}">
																<input type="checkbox" value="${id}" key1="${name}"
																	key2="${sex}" key3="${duty}" key4="${techTitle}"
																	key5="${telephone}" key6="${email}" name="keeperId"
																	id="id" />
															</s:if>
														</td>
														<td class="c">
															<s:property value="#st.index+1" />
														</td>
														<td class="l">
															${name}
														</td>
														<td class="l">
															${sex}
														</td>
														<td class="l">
															${duty}
														</td>
														<td class="l">
															${techTitle}
														</td>
													</tr>
												</s:iterator>
											</tbody>
										</table>
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
