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
		function showLabSciProcess(id)
  	 	{
	   var url='<%=basePath%>/science/labScience/showLabSciProcess4Select.action?labSciProcessVo.id='+id;
	   $.dialog({
				id:'processId',
				content:'url:'+url,
				title:'过程详情',
				opacity:0.4,
				width:800,
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
								<a href="javascript:;" onclick="submitvalue('/science/labScience/preUpdateLabScience.action?labSciScienceVo.auditResult=0');""><span><s:text name="base.info"/></span> </a>
							</li>
							<li id="li02" class="">
								<a href="javascript:;"
									onclick="submitvalue('/science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab2');"><span><s:text name="prj.result"/></span>
								</a>
							</li>
							<li id="li03" class="">
								<a href="javascript:;"
									onclick="submitvalue('/science/labScience/updateLabScience.action?labSciScienceVo.auditResult=tab3');"><span><s:text name="prj.result"/></span>
								</a>
							</li>
							<li id="li04" class="currenttab">
								<a href="javascript:;"><span><s:text name="procedure.detail"/></span>
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
														编号：
													</label>
												</td>
												<td>
													<input type="text" name="labSciProcessVo.no"
												    id="no" value="${labSciProcessVo.no}" />
												</td>
												<td>
													<l:a uri="${SessionContainer.lastUrl}"
														onclick="goAction('listLabSciProcess4Select.action');" value="fun.query" />
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
									<th property="no">编号</th>
									<th property="name">名称</th>
									<th property="type ">类型</th>
									<th property="contents">内容</th>
									<th property="createTime" >登记时间</th>
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
												<td class="c">${no}</td>
												<td>${name}</td>
												<td>${type }</td>
												<td>${contents}</td>
												<td class="c">${createTime }</td>
												<td class="c">
													<l:a href="#" uri="science/labScience/showLabSciProcess.action" onclick="showLabSciProcess('${id }');" value="详情"/>	
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
