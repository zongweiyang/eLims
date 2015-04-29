<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:property
				value="#session.SessionContainer.orgUnit" />
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
       	function selectUser()
       	{
       	   var name='';
       	   var id='';
       	     $('input[type="checkbox"]:checked').each(function(){
	            name+=$(this).val()+',';
	            id+=$(this).attr("id")+",";
       		});
       		name=name.substr(0,name.length-1);
       		id=id.substr(0,id.length-1);
       		$('#participant',D).val(name);
       		$('#participantId',D).val(id);
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
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
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
										<table class="listTable_pop" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													<th class="c">
													</th>
													<th class="c" width="30">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th class="c">
													<s:text name="nike.name"/>
													</th>
													<th class="c">
												<s:text name="theme.sex"/>
													</th>
													<th class="c">
												<s:text name="work.duty"/>
													</th>
													<th class="c">
														<s:text name="theme.job.title"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:set name="alllist" value="#request.userList" />
												<s:iterator value="#alllist" status="st">
													<tr>
														<td class="c">
															<input type="checkbox" key="${id }" value="${name}"
																id="${id }" name="keeperId" id="id${st.index}" />
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
	<script>
			$(function(){
			var participantId=$('#participantId',D).val();
			var participantIdArry=participantId.split(",");
            if(participantIdArry.length>0)
            {
               for(var i=0;i<participantIdArry.length;i++)
               {
                 $('input[key="'+participantIdArry[i]+'"]').attr('checked','checked');
               }
            }
		});
	</script>
</html>
