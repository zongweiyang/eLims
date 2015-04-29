<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<link href="<%=basePath %>css/global.css" media="all" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/common.css" media="all" rel="stylesheet" type="text/css" />
<meta http-equiv="refresh" content="5;url=http://localhost:8080/LabOSCore1.0">
</head>
<script type='text/javascript'>
　　var i=5;
　　function getTime(){
　　document.getElementById('num').innerHTML="<font color='red'>"+i+"</font>";
　　i-=1;
　　var x=setTimeout('getTime()',1000)
　　if(i<=0){
　　clearTimeout(x);
　　}
　　}
　　window.onload=getTime;
</script>
　<body>
      <s:fielderror cssStyle="color: red"></s:fielderror>
        <s:if test="hasFieldErrors()">  
            <s:iterator value="fieldErrors">  
                <s:iterator value="value" status="statu">  
                    <!--                //关键代码  -->  
                    <s:set name="msg" value="((#msg==null || #msg=='')?'':#msg+'\\\n')" />  
                    <s:set name="msg" value="#msg+value.get(#statu.getIndex()).toString()" />  
                </s:iterator>  
            </s:iterator>  
        </s:if>  
         <s:if test="#msg.length()>0">  
            <script language="JavaScript">      
            alert("<s:property escape="false" value="#msg"/>")      
        </script>  
        </s:if>  
     
　　<h2 align="center">注意!!页面将在<div id='num' style='display=inline;'>5</div>秒后回到上一个页面</h2>
	
</body>