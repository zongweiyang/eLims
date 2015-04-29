<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="../jsp/include/common.jsp"  %>
		<title>LabOS-<s:text name="login.labossystem"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
   
</head>
<body>
<script type="text/javascript">
function exit(){
  // window.parent.location.href="";
}
function hideAndShow(){
    var size = window.parent.document.getElementById("bottomid").cols;
	if(size == '190,*'){
		window.parent.document.getElementById("bottomid").cols = '0,*';
	}else{
	    window.parent.document.getElementById("bottomid").cols = '190,*';
	}
}
function changeStyle(t,count) {

	var tem = eval(t); 
	for(var i=1;i<count+1;i++){
		var temTab = "Herf"+i;
	    if(i != tem){ 
		   document.getElementById(temTab).className = "";
		}else{
			document.getElementById(temTab).className = "current";
		}
		
	}
}
function changeMainmenuStyle(t,count) {
	$("#submenushow").html("")
	var tem = eval(t); 
	for(var i=1;i<count+1;i++){
		var temTab = "Tab"+i;
	    if(i != tem){ 
		   document.getElementById(temTab).className = "";
		}else{
			document.getElementById(temTab).className = "current";
		}
		
	}


}

function loadFirstPage(){
	
	//window.location.target="workarea";
	//window.location.href="<%=basePath%>coreextend/extend/login.action";
	//alert("abcd");
}
//单点登录时用来关闭窗口的函数
function closeWindow() {      
    parent.window.close();   
}
</script>
<div id="Header">

<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
  <tr>
    <td class="left"><img src="<%=basePath%>img/logo/${session.SessionContainer.logoUrl}" style="position:absolute;top:0;left:0;"/></td>
    <td class="right">
      <div class="headertool">
        <div class="navLinks">
  
        <a class="navLinks_back" href="#"><s:text name="mainframe.top.backhome"/></a>
         <a class="navLinks_help" href="#"><s:text name="mainframe.top.help"/></a>
          <a class="navLinks_logout" href="<%=basePath%>coreextend/extend/logout.action" target="_top" onclick="closeWindow()"><s:text name="mainframe.top.loginout"/></a>
        </div>
      </div>
    </td>
  </tr>
</table>

<table class="Headertabsbar" cellspacing="0" cellpadding="0" border="0">
  <tr>
    <td style="padding-left:5px;">
      <div class="tabNavigation">
        <table cellspacing="0" cellpadding="0" border="0" id="tabBar" class="tab">
          <tbody>
            <tr>
<s:iterator value="labUserVo.sysFunctionList" status="st">
   <s:if test="id==currentMainmenuId">
   		<s:if test="${funtype=='0'}">
   			<td nowrap="nowrap" id="Tab<s:property value="#st.index+1" />" class="current"><div><a title="<s:property value="funname" />选项卡" href="<%=path %>/coreextend/extend/getSubmenu.action?funId=<s:property value="id" />&styleName=<s:property value="#st.index+1" />" ><s:property value="funname" /></a></div></td>
   		</s:if>
   		<s:else>
   			<td nowrap="nowrap" id="Tab<s:property value="#st.index+1" />" class="current"><div><a title="<s:property value="funname" />选项卡" href="<%=path %><s:property value="funurl"/>?funId=<s:property value="id" />&styleName=<s:property value="#st.index+1" />"   target="workarea" onclick="changeMainmenuStyle('<s:property value="#st.index+1" />','${session.MainMenuFunCount}');"><s:property value="funname"/></a></div></td>
   		</s:else>
   </s:if>
   <s:else>
	   <s:if test="${funtype=='0'}">
	   		<td nowrap="nowrap" id="Tab<s:property value="#st.index+1" />"><div><a title="<s:property value="funname" />选项卡" href="<%=path %>/coreextend/extend/getSubmenu.action?funId=<s:property value="id" />&styleName=<s:property value="#st.index+1" />" ><s:property value="funname" /></a></div></td>
	   </s:if>
	   <s:else>
	   		<td nowrap="nowrap" id="Tab<s:property value="#st.index+1" />"><div><a title="<s:property value="funname" />选项卡" href="<%=path %><s:property value="funurl"/>?funId=<s:property value="id" />&styleName=<s:property value="#st.index+1" />"  onclick="changeMainmenuStyle('<s:property value="#st.index+1" />','${session.MainMenuFunCount}');" target="workarea"><s:property value="funname" /></a></div></td>
	   </s:else>
   </s:else>
</s:iterator>             
            </tr>
          </tbody>
        </table>
      </div>
    </td>
  </tr>
</table>
<div id="lineid" class="sh st2" >
				<div class="hsubmenu" id="submenushow"> 
              		<s:iterator value="submenuList" status="st">
              			<s:if test="#st.index==0">
              				<a href="<%=path %><s:property value="funurl"/>" id="Herf<s:property value="#st.index+1" />" class="current" target="workarea" onclick="changeStyle('<s:property value="#st.index+1" />','${session.FunCount}');"><s:property value="funname"/></a>
              			</s:if>
              			<s:else>
              				<a href="<%=path %><s:property value="funurl"/>" id="Herf<s:property value="#st.index+1" />" target="workarea" onclick="changeStyle('<s:property value="#st.index+1" />','${session.FunCount}');"><s:property value="funname"/></a>
              			</s:else>
              		</s:iterator>
				</div>
</div>
<input class="sfbtn" type="button" onclick="hideAndShow();" />
</div>

</body>
</html>