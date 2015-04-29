<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<html>
		<head>
			<%@ include file="../jsp/include/common.jsp"%>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
			<title></title>



			<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		</head>
		<script language=javascript> 

	function submitvalueforlist(url)
	{	
			document.Form1.action=url;			
			document.Form1.submit();
				
	}
			
</script>
		<body>



			<div class="myworkingbox">
				<div class="myworkingboxttitle">
					<h2>
						<s:text name="theme.own.info"/>：
						<span><s:text name="theme.modify"></s:text></span>
					</h2>
				</div>
				<form action="updateUserSysDesktopstyle.action" method="post" name="Form1">
					<div class="TabTable">
						<div class="buttonbar">
							<div class="button01">
								<div class="buttonl"></div>
								<div class="buttonm">
									<a href="javascript:void();" onclick="submitvalueforlist('updateUserSysDesktopstyle.action');return false;"><s:text name="theme.save"></s:text></a>
								</div>
								<div class="buttonr"></div>
							</div>
						</div>
						<div class="TabTableBOX01 b" id="Tab01">
							<div class="tabtablebox">
								<div class="tabtableboxtitle">
									<span><s:text name="theme.change.skin"></s:text></span>
								</div>
								<table class="tabtableboxtable">
								 
									<tr> <s:set name="vo" value="sysDesktopstyleVo.list" />
                                       <s:iterator value="#vo">
										<td class="w150">
											<label>
												<img src="<%=basePath%>img/<s:property value="picurl" />" alt="<s:property value="descc" />"
													width="150px" height="100px" />
											</label>
										</td>
										</s:iterator>  
									</tr>
									<tr>  <s:set name="sysDesktopstyleVo" value="sysDesktopstyleVo.list" />
                                       <s:iterator value="#sysDesktopstyleVo">
                                       <s:if test='${session.SessionContainer.styleName==name}'>
                                       		<td class="w150">
											<label>
												<input   type="radio" name="sysDesktopstyleVo.id" value="<s:property value="id" />" checked="checked"/><s:property value="descc" />
											</label>
										</td>
                                       </s:if>
                                       
                                       <s:else>
                                       <td class="w150">
											<label>
												<input  type="radio" name="sysDesktopstyleVo.id" value="<s:property value="id" />"/><s:property value="descc" />
											</label>
										</td>
                                       </s:else>
										
										</s:iterator>  
									</tr>
                                    <input type="hidden" value="<s:property value="sysDesktopstyleVo.userid" />" name="sysDesktopstyleVo.userid"/>
								</table>
							</div>
						</div>

						</td>
						</tr>
						</table>
						</td>
						</tr>
						</table>
				</form>
			</div>
			<div class="buttonbar">
				<div class="button01">
					<div class="buttonl"></div>
					<div class="buttonm">
						<a href="javascript:void();" onclick="submitvalueforlist('updateUserSysDesktopstyle.action');return false;"><s:text name="theme.save"></s:text></a>
					</div>
					<div class="buttonr"></div>
				</div>

			</div>
		</body>
		<script language="javascript" type="text/javascript">

</script>
	</html>