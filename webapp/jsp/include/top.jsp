<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;"%>
<%@ page import="cn.labsoft.labos.utils.Menus;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>labsoft</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="<%=basePath%>style/global.css" media="all" rel="stylesheet" type="text/css" />
<link  href="<%=basePath%>skin/default/css/common.css" media="all" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    List stationIdList = null;
	if(sessionContainer != null){
		stationIdList = sessionContainer.getStationIDList();
	}
	Menus menu = new Menus();
	String allmenu=menu.getMenus(stationIdList,"");
%>

<script type="text/javascript" src="<%=basePath%>js/framemenu.js"></script>
<script type="text/javascript">
FrameMenuConfig.FolderImage="../img/leftbtn.png";
	FrameMenuConfig.CssPrefix="#fm_Container";
	FrameMenu.insert(null,"a",'<s:property value="getText('top.index')"/>');
	FrameMenu.insert(null,"b",'<s:property value="getText('top.customer.manage')"/>');
	    FrameMenu.insert("b","b1",'<s:property value="getText('top.customer.register')"/>',"1","body");
		FrameMenu.insert("b","b2",'<s:property value="getText('top.customer.compliant')"/>',"1","body");
		FrameMenu.insert("b","b3",'<s:property value="getText('top.cust.maintance')"/>',"1","body");
		FrameMenu.insert("b","b4",'<s:property value="getText('top.cust.satisfact')"/>' ,"1","body");
		FrameMenu.insert("b","b5",'<s:property value="getText('top.cust.query')"/>',"","body");
	FrameMenu.insert(null,"c",'<s:property value="getText('top.sale.manege')"/>');
	    FrameMenu.insert("c","c1",'<s:property value="getText('top.cust.trace')"/>',"","body");
		FrameMenu.insert("c","c2",'<s:property value="getText('top.decoration')"/>',"","body");
		FrameMenu.insert("c","c3",'<s:property value="getText('top.contract.manage')"/>',"","body");
	FrameMenu.insert(null,"d",'<s:property value="getText('top.cost.manage')"/>');
	    FrameMenu.insert("d","d1",'<s:property value="getText('top.budget.review')"/>' ,"","body");
		FrameMenu.insert("d","d2",'<s:property value="getText('top.subcontract.budget')"/>',"","body");
		FrameMenu.insert("d","d3",'<s:property value="getText('top.process.review')"/>',"","body");
		FrameMenu.insert("d","d4",'<s:property value="getText('top.variable.audit')"/>',"","body");
		FrameMenu.insert("d","d5",'<s:property value="getText('top.budget.manage')"/>',"","body");
		FrameMenu.insert("d","d6",'<s:property value="getText('top.material.price')"/>',"","body");
		FrameMenu.insert("d","d7",'<s:property value="getText('top.accessories.price')"/>',"","body");
	FrameMenu.insert(null,"e",'<s:property value="getText('top.project.manage')"/>');
	    FrameMenu.insert("e","e1",'<s:property value="getText('top.project.assign')"/>',"","body");
		FrameMenu.insert("e","e2",'<s:property value="getText('top.project.construction')"/>',"","body");
		FrameMenu.insert("e","e3",'<s:property value="getText('top.project.query')"/>',"","body");
	FrameMenu.insert(null,"f",'<s:property value="getText('top.purchase.manage')"/>');
	    FrameMenu.insert("f","f1",'<s:property value="getText('top.material.order')"/>',"","body");
		FrameMenu.insert("f","f2",'<s:property value="getText('top.accessories.purchase')"/>' ,"","body");
		FrameMenu.insert("f","f3",'<s:property value="getText('top.supplier.manage')"/>',"","body");
	FrameMenu.insert(null,"g",'<s:property value="getText('top.stock.manage')"/>');
	    FrameMenu.insert("g","g1",'<s:property value="getText('top.material.list')"/>',"","body");
		FrameMenu.insert("g","g2",'<s:property value="getText('top.accessories.list')"/>',"","body");
		FrameMenu.insert("g","g4",'<s:property value="getText('top.accessories.inbound')"/>',"","body");
		FrameMenu.insert("g","g5",'<s:property value="getText('top.accessories.outbound')"/>',"","body");
		FrameMenu.insert("g","g6",'<s:property value="getText('top.accessories.inventory')"/>',"","body");
		FrameMenu.insert("g","g7",'<s:property value="getText('top.stock.query')"/>',"","body");
	FrameMenu.insert(null,"h",'<s:property value="getText('top.finance.manage')"/>');
	    FrameMenu.insert("h","h1",'<s:property value="getText('top.charge.deposit')"/>',"","body");
		FrameMenu.insert("h","h2",'<s:property value="getText('top.charge.engagement')"/>',"","body");
		FrameMenu.insert("h","h3",'<s:property value="getText('top.progress.money')"/>',"","body");
		FrameMenu.insert("h","h4",'<s:property value="getText('top.account.statistics')"/>',"","body");
		FrameMenu.insert("h","h5",'<s:property value="getText('top.profit.analysis')"/>',"","body");
	FrameMenu.insert(null,"i",'<s:property value="getText('top.statistic.analysis')"/>');
	    FrameMenu.insert("i","i1",'<s:property value="getText('top.cust.analysis')"/>',"","body");
		    FrameMenu.insert("i1","i11",'<s:property value="getText('top.cust.distribute')"/>',"","body");
			FrameMenu.insert("i1","i12",'<s:property value="getText('top.cust.quantity')"/>',"","body");
		FrameMenu.insert("i","i2",'<s:property value="getText('top.constract.analysis')"/>' ,"","body");
		    FrameMenu.insert("i2","i21",'<s:property value="getText('top.constract.quantity')"/>',"","body");
			FrameMenu.insert("i2","i22",'<s:property value="getText('top.constract.amount')"/>',"","body");
		FrameMenu.insert("i","i3",'<s:property value="getText('top.material.analysis')"/>' ,"","body");
		    FrameMenu.insert("i3","i31",'<s:property value="getText('top.cust.analysis')"/>',"","body");
			FrameMenu.insert("i3","i32",'<s:property value="getText('top.cust.analysis')"/>',"","body");
		FrameMenu.insert("i","i4",'<s:property value="getText('top.purchaser')"/>',"","body");
		    FrameMenu.insert("i4","i41",'<s:property value="getText('top.order.quantity')"/>',"","body");
			FrameMenu.insert("i4","i42",'<s:property value="getText('top.order.amount')"/>',"","body");
		FrameMenu.insert("i","i5",'<s:property value="getText('top.org.manage')"/>',"","body");
		FrameMenu.insert("i","i6",'<s:property value="getText('top.user.manage')"/>',"","body");
		FrameMenu.insert("i","i7",'<s:property value="getText('top.role.manage')"/>',"","body");
	
</script>
<body topmargin="0" leftmargin="0" oncontextmenu="FrameMenuConfig.showMenu();return false;">


<div id="Header">
    <div class="headerbg">
        <form action="topframe.action" method="post" name="userform">
        <input type="hidden" name="vo.stRoleId" id="stRoleId" value="${vo.stRoleId}"/>
        <table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td class="right" colspan="3">
              <div class="headertool">
                <div class="navLinks">  
                 <a class="navLinks_help" href="#">主页</a>
                 <a class="navLinks_logout" href="<%=basePath%>system/stUser/logout.action" target="_top">退出</a>       
                </div>
              </div>
            </td>
          </tr>
        </table>
        <table style="" cellspacing="0" cellpadding="0" border="0">
          <tr><td class="topmenuleft"></td>
          <td class="topmenumiddle">
        	<div id="fm_MainContainer"></div>
        <script defer>
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
