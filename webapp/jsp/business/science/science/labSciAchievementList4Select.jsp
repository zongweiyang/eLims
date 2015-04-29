<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script>
		function getOne(id){
				window.location.href='${basePath}science/labScience/preUpdateLabScience.action?labSciScienceVo.id='+id+'&messageInfo=7';
		}
		function submitvalue(actionstr){
			$('form').attr('action','<%=basePath%>'+actionstr);
			$('form').submit();
		}
		function showLabSciAchievement(id)
  	 	{
	   		var url='<%=basePath%>/science/labScience/showLabSciAchievement4Select.action?labSciAchievementVo.id='+id;
		   $.dialog({
					id:'processId',
					content:'url:'+url,
					title:'成果详情',
					opacity:0.4,
					width:900,
					height:400,
					max:false,
					min:false,
					lock:true
		    });
   	}
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
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
	<div class="myworkingbox">
		<div class="myworkingboxttitle">
			<h2>
				${funName}：
				<span><s:text name="look.check"/></span>
			</h2>
		</div>
		<form action="" method="post" name="labScienceFrom"
			id="labScienceFrom">
				<input type="hidden" name="messageInfo" value="${messageInfo }" />
				<input type="hidden" name="labSciScienceVo.id"
								value="${labSciScienceVo.id}" />
				<input type="hidden" name="labSciScienceVo.code"
					value="${labSciScienceVo.code}" />
				<input type="hidden" name="labSciScienceVo.processInsId"
					value="${labSciScienceVo.processInsId}" />
					<input type="hidden" name="labSciScienceVo.isKnot"
					value="${labSciScienceVo.isKnot}" />
					<input type="hidden" name="labSciScienceVo.isSeized"
					value="${labSciScienceVo.isSeized}" />
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
												uri="science/labScience/updateLabScience.action"
												value="post.commit" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="TabTable">
					<div class="TabTableNAV">
						<ul>
							<li id="li01" class="">
								<a href="javascript:;" onclick="submitvalue('/science/labScience/preUpdateLabScience.action?labSciScienceVo.auditResult=0');"><span><s:text name="base.info"/></span> </a>
							</li>
							<li id="li02" class="">
								<a href="javascript:;" onclick="submitvalue('/science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab2');"><span><s:text name="charge.details"/></span>
								</a>
							</li>
							<li id="li03" class="currenttab">
								<a href="javascript:;" ><span><s:text name="prj.result"/></span>
								</a>
							</li>
							<li id="li03" class="">
								<a href="javascript:;" onclick="goAction('science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab4');"><span><s:text name="procedure.detail"/></span>
								</a>
							</li>
						</ul>
					</div>
				<div class="TabTableBOX01 b" id="Tab01">
					<div class="FUNCIONBARNEW">
							<table>
								<tr>
									<td class="blockTd"
										style="padding: 6px 10px; vertical-align: center;">
										<table cellspacing="0" cellpadding="0" border="0">
											<tr>
												<td>
													<label>
														<s:text name="result.type"/>：
													</label>
												</td>
												<td>
													<s:select list="#request.codeList" listKey="code"
														listValue="name" headerKey="" headerValue="-全部-"
														name="labSciAchievementVo.type" id="type" theme="simple"
														>
													</s:select>
												</td>
												<td>
													<l:a uri="${SessionContainer.lastUrl}"
														onclick="goAction('listLabSciAchievement4Select.action');" value="fun.query" />
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
										<img src="<%=basePath%>img/icon_drag.gif" />
									</th>
									<th width="18%" class="c"  property="name">
										<s:text name="config.name"/>
									</th>
									<th width="18%" class="c" property="money">
										<s:text name="author"/>
									</th>
									<th width="18%" class="c" property="type">
										<s:text name="time"/>
									</th>
									<th width="18%" class="c" property="remark">
										<s:text name="result.type"/>
									</th>
									<th width="18%" class="c">
										<s:text name="lab.code.ops"/>
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
													${pageSizex* currenPagex+st.index+1}
												</td>
												<td class="c">${name}</td>
                                                 <td class="c">${fristAuthorName }</td>
                                                 <td class="c">${date }</td>
                                                 <td class="c">
                                                 	<s:if test="${type == 'PAPER' }">
                                                 		<s:text name="paper"/>   
                                                 	</s:if>
                                                 	<s:if test="${type == 'BOOK' }">
                                                		<s:text name="writting"/>
                                                 	</s:if>
                                                 	<s:if test="${type == 'PATENT' }">
                                                 		<s:text name="inv.pat"/>
                                                 	</s:if>
                                                 	<s:if test="${type == 'AWARD' }">
                                                 		<s:text name="awarding"/>
                                                 	</s:if>
                                                 </td>
												<td class="c">
													<l:a href="#" uri="science/labScience/showLabSciAchievement4Select.action" onclick="showLabSciAchievement('${id}');" value="详情"/>	
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
						<table style="padding-top: 230px;" width="100%" >
							<tr>
								<td align="center" width="300px;"><jsp:include
										page="/jsp/include/page.jsp?formName=labScienceFrom"
										flush="true" /></td>
							</tr>
						</table>
				</div>
			</div>
		</form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
