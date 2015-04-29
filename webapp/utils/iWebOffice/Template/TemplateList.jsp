<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*,DBstep.iDBManager2000.*,cn.labsoft.labos.framework.common.sesseionutils.*,org.springframework.jdbc.datasource.DataSourceUtils"%>
<%@page import="cn.labsoft.labos.utils.SysOut"%>
<%@ include file="../../jsp/include/common.jsp"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<link href="<%=basePath%>css/theme8.css" media="all" rel="stylesheet" type="text/css" id="theme" />
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
		<script language="JavaScript">
	function ConfirmDel(FileUrl){
	if (confirm('是否确定删除该模板！')){
		location.href=FileUrl;
	}
}		
	function submitvalue(actionstr){
		       	var df = document.customerForm;
		 		df.action=actionstr;
		  		df.submit();
		  	}
	function goToNextAction(url){
		$('form').attr('${basePath}'+url);
		$('form').submit();
	}		
</script>

	</head>
	<%
		int thisPage = 0;
		int pageSize = 15;
		int count = 0;
		int countPage = 0;
		String searchWords = "";
		String tempType = "";
		SessionContainer sessionContainer = (SessionContainer) session.getAttribute(SessionContainer.Session_Container);
		String currUserName = sessionContainer.getUsername();
		try {
			thisPage = Integer.valueOf(request.getParameter("thisPage"));
			if (thisPage < 0)
				thisPage = 1;
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
			searchWords = String.valueOf(request.getParameter("searchWords"));
			tempType = String.valueOf(request.getParameter("TempType"));
			if ("null" == searchWords)
				searchWords = "";
			if ("null" == tempType)
				tempType = "";
			request.setAttribute("thisPage", thisPage);
			request.setAttribute("pageSize", pageSize);
		} catch (Exception e) {

		}
		DBstep.iDBManager2000 DbaObj = new DBstep.iDBManager2000();
	%>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form theme="simple" action="" method="post" name="sysUserForm" id="sysUserForm">
			<input type="hidden" name="sysUserVo.sysOrgId" id="sysOrgId" value="${sysUserVo.sysOrgId}" />
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
												模板管理：
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<form action="TemplateList.jsp?thisPage=<%=thisPage%>&tempType=<%=tempType%>&pageSize=<%=pageSize%>" method="post">
											<div class="FUNCIONBARNEW">
												<table>
													<tr>
														<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
															<table cellspacing="0" cellpadding="0" border="0">
																<tr>
																	<td>
																		<label>
																			模板类别:
																		</label>
																		<select name="TempType">
																			<option value="">
																			</option>
																			<%
																				try {
																					String hql = "select name,code from com_code where TYPEID = (select id from com_type where CODE = 'TEMP_TYPE') order by sort asc ";
																					ResultSet result2 = DbaObj.ExecuteQuery1(hql);
																					while (result2.next()) {
																						String name = result2.getString("name");
																						String code = result2.getString("code");
																			%>
																			<%
																				if (code.equals(tempType)) {
																			%>
																			<option value="<%=code%>" selected="selected"><%=name%>
																			</option>
																			<%
																				} else {
																			%>
																			<option value="<%=code%>"><%=name%>
																			</option>
																			<%
																				}
																					}
																					result2.close();
																				} catch (Exception e) {
																			%>

																			<%
																				}
																			%>
																		</select>
																	</td>
																	<td>
																		<label>
																			模板名称：
																		</label>
																	</td>
																	<td>
																		<input name="searchWords" value="<%=searchWords%>" />
																	</td>
																	<td>

																		<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="goToNextAction('TemplateList.jsp?thisPage=<%=thisPage%>&tempType=<%=tempType%>&pageSize=<%=pageSize%>'); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b>查询</b>
																		</a>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
										</form>
										<!-- 按钮条 结束 -->

										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.doc&UserName=<%=currUserName%>';"><img height="20" width="20" src="${basePath}img/icon_word.gif" /><b>新建Word模板</b>
																	</a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="javascript:location.href='TemplateEdit.jsp?FileType=.xls&UserName=<%=currUserName%>';"><img height="20" width="20" src="${basePath}img/icon_excel.gif" /><b>新建Excel模板</b>
																	</a>
																</td>

															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>

										<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
											<thead>
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														模板名称
													</th>
													<th>
														模板类别
													</th>
													<th>
														模板说明
													</th>
													<th>
														维护人
													</th>
													<th>
														最近维护时间
													</th>
													<th>
														操作
													</th>
											</thead>
											<tbody>

												<%
													if (null != DbaObj.Conn) {
														try {
															String hql = "Select RecordID,FileName,FileType,Descript,TempType,FileDate,UserName From Template_File where 1=1";
															if (null != searchWords && !"".equals(searchWords)) {
																hql += " AND FileName like '%" + searchWords + "%'";
															}
															if (null != tempType && !"".equals(tempType)) {
																hql += " AND TempType like '%" + tempType + "%'";
															}
															hql += " order by FileName desc";
															//System.out.println(hql);
															ResultSet result = DbaObj.ExecuteQuery(hql + " limit " + thisPage * pageSize + "," + pageSize);
															ResultSet result1 = DbaObj.ExecuteQuery(hql);
															//System.out.println(hql+" limit "+thisPage*pageSize+","+pageSize);    
															while (result1.next()) {
																count++;
															}
															result1.close();
															countPage = count / pageSize;
															if (0 != count % pageSize)
																countPage = countPage + 1;
															int index = 0;
															while (result.next()) {
																String mRecordID = result.getString("RecordID");
																String mFileName = result.getString("FileName");
																String mFileType = result.getString("FileType");
																String mDescript = result.getString("Descript");
																String mTempType = result.getString("TempType");
																String hql2 = "select name from com_code where code = '" + mTempType + "'";
																ResultSet result2 = DbaObj.ExecuteQuery1(hql2);
																while (result2.next()) {
																	mTempType = result2.getString("name");
																}
																String mFileDate = result.getString("FileDate");
																if (null != mFileDate)
																	mFileDate = mFileDate.substring(0, 10);
																String mUserName = result.getString("UserName");
																index += 1;
												%>
												<tr>
													<td class="1"><%=pageSize * thisPage + index%>
													</td>
													<!-- <td ><%=mRecordID%>&nbsp;</td> -->
													<td class="TDStyle"><%=mFileName%>
													</td>
													<!-- <td ><%=mFileType%>&nbsp;</td> -->
													<td><%=mTempType%>
													</td>
													<td>
														<%=mDescript%>

													</td>
													<td><%=mUserName%>
													</td>
													<td class="c"><%=mFileDate%>
													</td>
													<td class="c">
														<input type=button onclick="javascript:location.href='TemplateEdit.jsp?RecordID=<%=mRecordID%>&FileType=<%=mFileType%>&UserName=<%=currUserName%>';" name="Edit" value=" 修 改 ">
													</td>
												</tr>
												<%
													}
															result.close();
														} catch (Exception e) {
															System.out.println(e.toString());
														}finally {
															DataSourceUtils.releaseConnection(DbaObj.Conn, DbaObj.dataSource);
														}
												%>
												<tr>

												</tr>
												<%
													} else {
														out.println("OpenDatabase Error");
													}
												%>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
							<tr>
								<td class="FormtableCon" colspan="6" align="center">

									第
									<%=thisPage + 1%>
									页/ 共
									<%=countPage%>
									页 记录总数:
									<%=count%>
									条 (每页<%=pageSize%>条)
									<a href="TemplateList.jsp?thisPage=0&pageSize=<%=pageSize%>&searchWords=<%=searchWords%>&tempType=<%=tempType%>">首页</a>
									<%
										if (0 == thisPage) {
									%>
									<a href="javascript:void(0)" onclick="javascript:alert('已经到首页!!');return false;">上一页</a>
									<%
										} else {
									%>
									<a href="TemplateList.jsp?thisPage=<%=thisPage - 1%>&pageSize=<%=pageSize%>&searchWords=<%=searchWords%>&tempType=<%=tempType%>">上一页</a>
									<%
										}
									%>
									<%
										if (countPage == thisPage + 1) {
									%>
									<a href="javascript:void(0)" onclick="javascript:alert('已经到尾页!!');return false;">下一页</a>
									<%
										} else {
									%>
									<a href="TemplateList.jsp?thisPage=<%=thisPage + 1%>&pageSize=<%=pageSize%>&searchWords=<%=searchWords%>&tempType=<%=tempType%>">下一页</a>
									<%
										}
									%>
									<a href="TemplateList.jsp?thisPage=<%=countPage - 1%>&pageSize=<%=pageSize%>&searchWords=<%=searchWords%>&tempType=<%=tempType%>">尾页</a>
								</td>
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
		</s:form>
		<%@ include file="../../jsp/include/foot.jsp"%>
	</body>
</html>
