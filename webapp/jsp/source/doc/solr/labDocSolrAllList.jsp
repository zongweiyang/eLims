<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.sampleEnterForm;
		 		df.action=actionstr;
		  		df.submit();
		  	}
	function goToNextAction(url){
			$('form').attr('action','${basePath}'+url);
			$('form').submit();
	}
	var flage;
	function changeText(){
		if(true==flage){
			$("#TextArea1").val("");
			flage=false;
		}else{
			$("#TextArea1").select();	
			flage=true;	
		}
	}
</script>

		<style>
.clearc:before,.clearc:after{content:"";display:table;}
.clearc:after{clear:both;}
.clearc{zoom:1;}
.fl{float:left;}
.fr{float:right;}
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
.fifterName{font-size:18px;font-family:"微软雅黑";}
.tdConslh{line-height:22px;text-indent:2em;padding-right:40px;}
</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form theme="simple" action="" method="post" name="listform">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}
												<span></span>
											</h2>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="">
														<a href="javascript:;" onclick="javascript:window.location.href='<%=basePath%>doc/labDoc/listLabDocSolr2db.action';return false;"><span>高级检索</span> </a>
													</li>
													<li id="li02" class="currenttab">
														<a href="javascript:;"><span><s:text name="prosearch"/></span> </a>
													</li>
												</ul>
											</div>

											<!-- pageResule query -->
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="tabtablebox">
													<div class="FUNCIONBARNEW">
														<table>
															<tr>
																<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
																	<table cellspacing="0" cellpadding="0" border="0">
																		<tr>
																			<td style="width: 1000px; text-align: center;">
																				<textarea id="TextArea1" name="labUpload.content" cols="60" rows="3" style="color: #838383;margin-bottom:10px;" onclick="changeText()" />${labUpload.content==null?'请输入您想查询的词汇':labUpload.content}</textarea>
																			</td>
																		</tr>
																		<tr>
																			<td style="padding-left: 45%;">
																				<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="goToNextAction('doc/labDoc/listLabDocSolr.action');return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b>搜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;索</b> </a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</div>
												</div>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<table width="100%" class="tabs_infos">
																<tr>
																	<td width="70%" class="clearc">
																		<p class="fifterName fl"><s:text name="file.name"/>:${name}</p>
																	</td>
																	<td width="8%">
																		<p style="text-align:right;display:inline-block;float:right;font-size:14px;">&nbsp;作者:${creater}</p>
																	</td>
																	<td width="10%" style="text-align:right;">
																		<div class="timedate" style="font-size: 14px;"><s:text name="msg.date"/>:${createTime}</div>
																	</td>
																	<td  style="text-align:center;">
																		<a href="<%=basePath%>utils/upload/down.jsp?fileName=${name}&amp;fileUrl=${path}" style="font-size: 14px; cursor: hand; display：block;margin-top:4px;"><s:text name="filedownload"/></a>
																	</td>
																</tr>
																<tr>
																	<td class="tdConslh">${content}</td>
																	<td></td>
																</tr>
															</table><br />
														</s:iterator>
													</s:if>                                                                                                   
												</s:if>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<!-- pageResule query end -->
					</td>
				</tr>
			</table>
			<jsp:include page="/jsp/include/page.jsp?formName=listform" flush="true" />
		</s:form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
