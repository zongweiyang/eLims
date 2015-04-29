<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript">
        function submitvalue(actionstr){
            var df = document.forms[0];
            df.action = actionstr;
            df.submit();
        }
		function doUrl(url){
		  window.location.href=url;	
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
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName }：
								<span><s:text name="look.check"/></span>
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
														<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/appara/listLabAppara.action');return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
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
											<a href="#" onclick="doUrl('<%=basePath%>appara/showLabAppara.action?labApparaVo.typeId=${labApparaEditVo.typeId}&labApparaVo.id=${labApparaEditVo.appId}');return false;"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li11" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/accept/listLabAppAccept4View.action?labApparaAcceptVo.typeId=${labApparaEditVo.typeId}&labApparaAcceptVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="checking.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/prov/listApparaProv4View.action?labApparaProvVo.typeId=${labApparaEditVo.typeId}&labApparaProvVo.labAppId=${labApparaEditVo.appId}');return false;"><span><s:text name="accid.recd"/></span> </a>
										</li>
										<li id="li12" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/test/listLabApparaTest4View.action?labApparaTestVo.typeId=${labApparaEditVo.typeId}&labApparaTestVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="check.recod"/></span> </a>
										</li>
										<li id="li04" class="currenttab">
											<a href="#" ><span><s:text name="repair.recd"/></span> </a>
										</li>
										<li id="li05" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/useing/listLabAppUse4View.action?labApparaUseVo.typeId=${labApparaEditVo.typeId}&labApparaUseVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="use.record"/></span> </a>
										</li>
										<li id="li06" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/open/listLabApparaOpen4View.action?labApparaOpenVo.typeId=${labApparaEditVo.typeId}&labApparaOpenVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="start.record"/></span> </a>
										</li>
										<li id="li07" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/stop/listLabLabApparaStop4View.action?labApparaStopVo.typeId=${labApparaEditVo.typeId}&labApparaStopVo.appId=${labApparaEditVo.appId}');return false;><span><s:text name="stop.record"/></span> </a>
										</li>
										<li id="li08" class="" >
											<a href="#" onclick="doUrl('<%=basePath%>appara/borrow/listLabApparaBor4View.action?labApparaBorVo.typeId=${labApparaEditVo.typeId}&labApparaBorVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="borrow.record"/></span> </a>
										</li>
										<li id="li09" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/drop/listLabApparaDrop4View.action?labApparaDropVo.typeId=${labApparaEditVo.typeId}&labApparaDropVo.appId=${labApparaEditVo.appId}');return false;"><span><s:text name="drop.record"/></span> </a>
										</li>
									</ul>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
								<input type="hidden" name="labApparaVo.typeId" value="${labApparaEditVo.typeId}"/>
									<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
										<thead>
											<tr>
												<th>
													<s:text name="lab.sequence"/>
												</th>
												<th property="appName">
													<s:text name="app.sys.name"/>
												</th>
												<th>
													<s:text name="saving.re.method"/>
												</th>
												<th>
													<s:text name="main.man"/>
												</th>
												<th>
													<s:text name="saved.repair.man"/>
												</th>
												<th property="verDate">
													<s:text name="msg.date"/>
												</th>
												<th>
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
																<s:property value="#st.index+1" />
															</td>
															<td class="c">
																<s:property value="appName" />
															</td>
															<td class="l">
																<s:property value="ext05" />
															</td>
															<td class="c">
																<s:property value="auditName" />
															</td>
															<td class="c">
																<s:property value="checkName" />
															</td>
															<td class="c">
																<s:property value="verDate" />
															</td>
															<td class="c">
																<l:a href="#" uri="appara/edit/showLabApparaEdit.action?labApparaEditVo.id=${id}&labApparaEditVo.typeId=${labApparaEditVo.typeId}" value="look.check" />
															</td>
														</tr>
													</s:iterator>
												</s:if>
											</s:if>
										</tbody>
									</table>
								</div>
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
