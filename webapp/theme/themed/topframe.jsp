<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/jsp/include/common.jsp"  %>
		<title><s:text name="login.labossystem"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   		<script language="javascript" type="text/javascript" src="<%=basePath%>js/jquery-1.3.2.js"></script>
   		<script type="text/javascript" src="<%=basePath%>js/ccit/CCITCertCtrl.js"></script>
</head>
<body>

<script type="text/javascript">
function hideAndShow(){
    var size = $('#left', parent.document).html();
    alert($('#left', parent.document).attr(""));
	if(size == '190,*'){
		$('#left', parent.document)[0].cols = '0,*';
	}else{
		$('#left', parent.document)[0].cols = '190,*';
	    
	}
}

//绑定左侧隐藏和显示
/*$('.sfbtn').click(
	function() {
		if ( $('#left', parent.document).is(":hidden") ) {
			$('#workarea', parent.document).width($('#work', parent.document).width() - $('#left', parent.document).width());
			$('#left', parent.document).show();
		}
		else {
			$('#workarea', parent.document).width($('#work', parent.document).width());
			$('#left', parent.document).hide();
		}
	}
);*/
function changeStyle(t,count,id) {
	var tem = eval(t);
	for(var i=1;i<=count;i++){
		var temTab = "Tab"+i;
	    if(i != tem){ 
		   	document.getElementById(temTab).className = "";
		}else{
			document.getElementById(temTab).className = "current";
		}
		
	}
	$setMenuClass(t,count);
	menu($("#MENUA_HREF_0_"+id));
}

$setMenuClass=function(t,count){
	for(var index =0;index<count;index++){
		if(t==index){
			$("#sub_"+t).css("display","block"); 
		}else{
			$("#sub_"+index).css("display","none"); 
		}
	}
}

function loginOut(){
	var usKey ='';
	try{
		usKey = readUKSN();
	}catch(err){
	}
	window.parent.parent.location.href="/coreextend/extend/logout.action";
	
}


</script>
<div id="Header">
<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
  <tr>
    <td class="left"><img src="<%=basePath%>${session.SessionContainer.logoUrl}" style="position:absolute;top:0;left:0;"/></td>
    <td class="right">
      <div class="headertool">
        <div class="navLinks">
         <form action="#" target="workarea" method="POST">
	         <a class="navLinks_logout" href="/coreextend/extend/logout.action" target="_top"><s:text name="mainframe.top.loginout"/></a>
			 <div style="text-align:left;"> </div>
         </form>
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
            <tr id="firstMenu">
				<s:iterator value="labUserVo.FunVoList" status="st">
				   <s:if test="id==currentMainmenuId">
					    <s:if test="${funname=='Home'}">
					      	<td nowrap="nowrap" lang="${id}" id="Tab${st.index+1 }" class="current"><div><a title="${name }选项卡" href="/portlets/showPortlets.action" target="workarea" onclick="changeStyle('${st.index+1 }','${labUserVo.funCount}');">${name }</a></div></td>
					    </s:if>
					    <s:else>
					   		<td nowrap="nowrap" lang="${id}" id="Tab${st.index+1 }" class="current"><div><a title="${name }选项卡" href="${url}" target="workarea"  onclick="changeStyle('${st.index+1 }','${labUserVo.funCount}','${id }');">${name }</a></div></td>
					    </s:else>
				   </s:if>
				   <s:else>
				   		<td nowrap="nowrap" lang="${id}" id="Tab${st.index+1 }"><div><a title="${name }选项卡" href="${url}" target="workarea" onclick="changeStyle('${st.index+1 }','${labUserVo.funCount}','${id }');">${name }</a></div></td>
				   </s:else>
				</s:iterator>             
            </tr>
          </tbody>
        </table>
      </div>
    </td>
  </tr>
</table>
<script language="JavaScript">
	function menu(obj){
		$("a[id*='MENUA_HREF']").each(function (linkNum){
			$(this).removeAttr("class","current");
		});
		$(obj).addClass("current");
	}
  </script>
<div id="lineid" class="sh st2" >
				<div id="links" class="hsubmenu"> 
					<s:iterator value="labUserVo.FunVoList" status="st1" id="s1">
	              		<span id="sub_${st1.index+1}" style="display: none">
	              		<s:iterator value="#s1.subFunctionList" status="st2" id="s2">
	              			<a href="${url}" id="MENUA_HREF_${st2.index}_${s1.id}" onclick="menu(this);" class="" target="workarea">${s2.name}</a>
	              		</s:iterator>
	              		</span>
              		</s:iterator>
				</div>
</div>
<script language="JavaScript">
	menu();
</script>
<input class="sfbtn" type="button" onclick="hideAndShow();"/>
</div>
</body>
</html>