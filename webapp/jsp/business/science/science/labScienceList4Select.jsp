<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	   
	    function closeMe(){
			api.close();
	    }
		function getEvaluateList(id)
		{
			submitvalue('science/labSupEvaluate/listLabSupEvaluate.action?labSupEvaluateVo.labScienceId='+id);
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		
		function selectLabSciScience()
		{
		   var name='';
       	   var id='';
       	   var i=0;
       	    $('input[type="checkbox"]:checked').each(function(){
	            id=$(this).val();
	            name+=$(this).attr('key1');
	            i++;
       		});
             if(i>1)
             {
                 alert('<s:property value="getText('onlyoneitem')"/>');
                 return;
             }else{
       		 $('#labSciScienceId',D).val(id);
       		 $('#labSciScienceName',D).val(name);}
       		closeMe();
		}
		
		$(function(){
       	    var id;
       	   if(${treeNid}=='1'){
               id=$('#labSciScienceId',D).val(); 
               $('input[value="'+id+'"]').prop('checked',true);
       	   }else{
       	       id=$('#labSciScienceId',D).val().split(','); 
       	       if(id.length>0)
            {
               for(var i=0;i<id.length;i++)
               {
                 $('input[key1="'+id[i]+'"]').prop('checked',true);
               }
            }     
       	   }  
		});
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 80%">
		<form action="" method="post" name="labScienceFrom"
			id="labScienceFrom">
			<input type="hidden" name="treeNid" value="${treeNid}" id="treeNid" />
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
						<table class="workingBody" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td>
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd"
													style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="prj.no"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labSciScienceVo.code" id="code"
																	value="${labSciScienceVo.code}" />
															</td>
															<td>
																<label>
																	<s:text name="item.name"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labSciScienceVo.name" id="name"
																	value="${labSciScienceVo.name}" />
															</td>
															<td>
																<label>
																	<s:text name="prj.people"/>：
																</label>
															</td>
															<td>
																<input type="text" name="labSciScienceVo.masterName"
																	id="masterName" value="${labSciScienceVo.masterName}" />
															</td>
															<td>
																<label>
																	<s:text name="prj.status"/>：
																</label>
															</td>
															<td>
																<s:select list="#request.funStepList" listKey="stepId"
																	listValue="stepName" headerKey="" headerValue=""
																	name="labSciScienceVo.status" id="status"
																	theme="simple"
																	onchange="submitvalue('/science/labScience/listLabScience.action');">
																</s:select>
															</td>
															<td>
																<l:a uri="${SessionContainer.lastUrl}"
																	onclick="submitvalue('science/labScience/showLabSciScience4Select.action?treeNid=${treeNid}');"
																	value="fun.query" />
															</td>
															<td>
																<a id="BtnEdit" class="zPushBtn"
																	href="javascript:void(0);"
																	onclick="selectLabSciScience();return false;"><img
																		height="20" width="20"
																		src="<%=basePath%>img/filesave.gif" /><b><s:text name="page.confirm"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd"
													style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<table class="myworkingboxttable" cellspacing="1"
										cellpadding="0">
										<thead>
											<tr>
												<th class="w50">
												</th>
												<th class="w50">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th property="code">
													<s:text name="prj.no"/>
												</th>
												<th property="name">
													<s:text name="item.name"/>
												</th>
												<th property="master">
													<s:text name="prj.people"/>
												</th>
												<th property="startDate">
													<s:text name="prj.time"/>
												</th>
												<th>
													<s:text name="prj.status"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="pageResult!=null">
												<s:if test="pageResult.resultList!=null">
													<s:set name="alllist" value="pageResult.resultList" />
													<s:iterator value="#alllist" status="st">
														<tr>
															<td class="c">
																<input type="checkbox" name="labSciScienceVo.ids"
																	value="${id}" key1="${name}"/>
															</td>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${code}
															</td>
															<td class="l">
																${name}
															</td>
															<td class="c">
																${masterName}
															</td>
															<td class="c">
																${startDate}-${endDate}
															</td>
															<td class="c">
																${status}
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:if>
											<s:else>
												<tr>
													<td colspan="24" align="center" valign="middle">
														<font color="red"><s:text name="lab.msg.none"/></font>
													</td>
												</tr>
											</s:else>
										</tbody>
									</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labScienceFrom"
										flush="true" /></td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
