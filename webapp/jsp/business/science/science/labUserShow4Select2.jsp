<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property value="#session.SessionContainer.orgUnit" />
		</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
.myworkingboxttable {
	width: 98%;
	border: 0px solid #CFCFCF;
	margin: 0 5px;
	border-collapse: collapse;
}
</style>
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
       	function submitx(){
       		closeMe();
       	}
       	$(function(){
			$('input[name*="userId"]',D).each(function(){
				$('input[value="'+$(this).val()+'"]').prop('checked',true);
			});
		});
       	
       	function checkUser(index,num){
       	if(num=='0'){
       		$('input[type="checkbox"]:checked').each(function(){
       		    var check = $(this);
       		    $('#masterId',D).val($(check).val());
       		    $('#masterName',D).val($(check).attr("key1"));
       		    $('#masterName2',D).val($(check).attr("key1"));
       		    $('#masterSex',D).val($(check).attr("key2"));
       		    $('#masterDuty',D).val($(check).attr("key3"));
       		    $('#masterTechTitle',D).val($(check).attr("key4"));
       		    $('#masterTelephone',D).val($(check).attr("key5"));
       		    $('#masterEmail',D).val($(check).attr("key6"));
       		});
       		closeMe();
       		}
       	}
       	
       	function selectUser()
       	{
       	    $("#person>tbody>tr",D).eq(0).nextAll().remove();
       	    $('input[type="checkbox"]:checked').each(function(){
       	        var table=$('#person',D);
	            var index=table.find('tr').length-1;
	            var check = $(this);
       	        var id=$(check).val();
       		    var name=$(check).attr("key1");
       		    var duty=$(check).attr("key3");
       		    var techTitle=$(check).attr("key4");
	            var trHtml='<tr>';
	            trHtml+='<td><input type="text" name="labSciScienceVo.authorList['+index+'].userName" readonly="true" maxlength="16"  value="'+name+'" id="userName['+index+']"/><input type="hidden" name="labSciScienceVo.authorList['+index+'].userId" maxlength="16"  value="'+id+'" id="userId['+index+']"/><input type="hidden" name="labSciScienceVo.authorList['+index+'].no" maxlength="16"  value="1" id="no['+index+']"/></td>';
	            trHtml+='<td><input type="text" name="labSciScienceVo.authorList['+index+'].techTitle" readonly="true" maxlength="16"  value="'+techTitle+'" id="techTitle['+index+']"/></td>';
	            trHtml+='<td><input type="text" name="labSciScienceVo.authorList['+index+'].duty" readonly="true" maxlength="16"  value="'+duty+'" id="duty['+index+']"/></td>';
	            trHtml+='<td><input type="text" name="labSciScienceVo.authorList['+index+'].learn" maxlength="16"  value="" id="learn['+index+']"/></td>';
	            trHtml+='<td><input type="text" name="labSciScienceVo.authorList['+index+'].remark" maxlength="16"  value="" id="remark['+index+']"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0);" onclick="deleteEntity(this,\'person\',\'rowNum1\');return false;"><s:text name="lab.code.del"/></a></td>';
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
			<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
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
						<th class="l" >
						    职务
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
								<input type="checkbox" value="${id}"  key1="${name}" key2="${sex}" key3="${duty}" key4="${techTitle}" key5="${telephone}" key6="${email}" name="keeperId" id="id${st.index}" onclick="checkUser('${st.index}','${messageInfo}');" />
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
			<div class="FUNCIONBARNEW">
				<table>
					<tr>
						<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
							<table cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td>
										<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="selectUser();return false;"><img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="page.confirm"/></b> </a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</form>

	</body>
</html>
