<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
function selectLabSciScience()
		{
		  if($('#type').val()=='')
             {
                 alert('请选择统计类型');
                 return false;
             }
		  if($('#type').val()!='2'&&$('#type').val()!='3'){
       	   var i=0;
       	    $('input[type="checkbox"]:checked').each(function(){
	            i++;
       		});
             if(i>1)
             {
                 alert('只能选择一个项目');
                 return false;
             }else if(i==0)
             {
                 alert('请选择一个项目');
                 return false;
             }
             }
             else if($('#type').val()=='2'&&$('#userId').val()=='')
             {
                 alert('请选择人');
                 return false;
             }
             return true;
		}
		function submitvalue1(){
		   if(selectLabSciScience())
		   {
		      var ids='';
		      $('input[type="checkbox"]:checked').each(function(){
		        ids+=$(this).val();
       		   });
			$.dialog({
				id:'id',
				content:'url:'+'<%=basePath%>science/labScience/showQueryLabScience.action?type='+$('#type').val()+'&labSciScienceVo.id='+ids+'&userId='+$('#userId').val(),
				title:'统计图',
				opacity:0.4,
				width:1100, 
				height:500,
				lock:true,
				max:false,
				min:false
			});
			}
		}
		
		function showUser()
		{
		    if($('#type').val()=='2')
             {
                document.getElementById("user1").style.display= "block";
             }else
             {
              document.getElementById("user1").style.display= "none";
             }
		}
			</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labSciFundsFrom"
			id="labSciFundsFrom">
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
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}：
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
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
																	<input type="text" name="labSciScienceVo.code"
																		id="code" value="${labSciScienceVo.code}" />
																</td>
																<td>
																	<label>
																		<s:text name="item.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labSciScienceVo.name"
																		id="name" value="${labSciScienceVo.name}" />
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
																<input type="hidden" id="treeNid" name="treeNid"
																	value="8" />
																<td>
																	<l:a uri="${SessionContainer.lastUrl}"
																		onclick="submitAction();" value="fun.query" />
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
																<td>
																	<s:select
																		list="#{0:'按经费支出类型',1:'按科研成果类型',2:'按人员的科研成果',3:'按照每个人论文类型'}"
																		headerKey="" headerValue="-全部-"
																		name="type" id="type"
																		theme="simple" onchange="showUser();return false;"
																		></s:select>
																</td>
																<td id="user1" style="display: none">
																	<s:select
																		list="#request.userList" listKey="id"
																		listValue="name"
																		headerKey="" headerValue="-全部-"
																		name="userId" id="userId"
																		theme="simple"
																		></s:select>
																</td>
																<td>
																	<l:a uri="science/labScience/showQueryLabScience.action"
																		onclick="submitvalue1();" value="统计分析" img="/img/query.gif"/>
																</td>
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
														<input type="checkbox" id="allCheckBox"
															key="labSciScienceVo.id" />
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
																	<input type="checkbox" name="labSciScienceVo.id"
																		value="${id}" />
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
																	${startDate}~${endDate}
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
										page="/jsp/include/page.jsp?formName=labSciFundsFrom"
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

