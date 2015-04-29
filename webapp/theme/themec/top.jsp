<%@ page language="java"  pageEncoding="gb2312"%>
<%@ page import="cn.labsoft.labos.framework.common.sesseionutils.SessionContainer"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.labsoft.labos.utils.Menus"%>
<%@ page import="cn.labsoft.labos.base.admin.vo.SysFunctionVo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>labsoft</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
    List<SysFunctionVo> list = null;
    SessionContainer sessionContainer=(SessionContainer)session.getAttribute(SessionContainer.Session_Container);
    list=sessionContainer.getFunlist();
	String sys=Menus.outJS(list,path);
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link  href="<%=basePath%>style/global.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>skin/<s:property value="#session.SessionContainer.styleName"/>/css/common.css" media="all" rel="stylesheet" type="text/css" id="theme"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>

<script language="javascript" src="<%=basePath%>js/framemenu.js"  charset="gb2312"></script>
<script type="text/javascript" charset="gb2312">

	FrameMenuConfig.FolderImage="leftbtn.gif";
	FrameMenuConfig.CssPrefix="#fm_Container";
    <%out.println(sys);%>
		
</script>
<body topmargin="0" leftmargin="0" >


<div id="Header">
    <div class="headerbg">
        <form action="topframe.action" method="post" name="userform">
        <input type="hidden" name="vo.stRoleId" id="stRoleId" value="${vo.stRoleId}"/>
        <table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td class="right" colspan="3">
              <div class="headertool">
                <div class="headerlogo"><img src="<%=basePath%>img/logo_green.gif"/></div>
                <div class="navLinks">          
                 <a class="navLinks_help" href="#"><s:text name="theme.help"></s:text></a>
                 <a class="navLinks_logout" href="<%=basePath%>coreextend/extend/logout.action" target="_top"><s:text name="theme.logout"></s:text></a>       
                </div>
              </div>
            </td>
          </tr>
        </table>
        <table style="" cellspacing="0" cellpadding="0" border="0">
          <tr><td class="topmenuleft"><div class="todaydata"><%=session.getAttribute("currenttime")%></div></td>
          <td class="topmenumiddle">
        	<div id="fm_MainContainer"></div>
        <script>
        	FrameMenuConfig.createFrameMenu("fm_MainContainer",false);
        </script>
          </td>
          <td class="topmenuright"></td></tr>
        </table>
        </form>   
    </div>
</div>

</body>
</html>
