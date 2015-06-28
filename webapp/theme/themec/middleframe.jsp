<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

		<title>labsoft</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <link  href="<%=basePath%>style/global.css" media="all" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/common.css" media="all" rel="stylesheet" type="text/css" id="theme"/>
</head>

<body class="middleframebody">
    <div class="midbarbtn" id="test">
      <div class="midbarbtnimg" id="change_img" style="cursor:pointer;" ></div>
    </div>
 <script type="text/javascript">
    	  var t=false;
    document.getElementById('test').onclick=function(){
	  var dis=parent.document.getElementById('leftFrame').style.display;
	  if(t){
	     parent.document.getElementById('bottomid').cols='161,5,*';
	     document.getElementById('change_img').className='midbarbtnimg';
	     t=false;
	  }else{
	    parent.document.getElementById('bottomid').cols='0,5,*';
	    document.getElementById('change_img').className='midbarbtnimg1';
	    t=true;}
	}
 </script>
</body>
</html>























