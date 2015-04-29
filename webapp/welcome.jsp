<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="jsp/include/common.jsp"%>
<link rel="shortcut icon" href="favicon.ico"> 
<title>SaaS For LIMS - LabSoft</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
</head>
<body>
<style type="text/css">
body {
  background-color: #ffffff;
  text-align:center;
}

body,td,div,p,a,font,span {
  font-family: verdana,"simsun";
  color:#333;
}
p {margin:5px 0;}
ul {margin:10px 0 10px 0px;*margin:10px 0 10px 30px;}
li {
  margin-bottom:0px;
  line-height:18px;
}
.wrapper {margin:0 auto;width:1003px;text-align:center;}
.footer {font-size:12px;color:#999;border-top:1px solid #999;padding-top:10px;margin-top:200px;}
.footer span {margin:0 10px;}
/*
 Used by some images.
*/
.c {
  width: 4;
  height: 4
}

#gaia_loginform {
  margin:0px;
}


#gaia_loginbox_td {
    padding-left: 40px
}

.form-noindent {
  border: #1675B1 1px solid;
  background-color: #62C6E9;
}

.bubble {
  background-color:#C3D9FF
}

.tl {
  font-size: 1px;
  padding: 0;
  width: 4;
  text-align: left;
  vertical-align: top
}

.tr {
  font-size: 1px;
  padding: 0;
  width: 4;
  text-align: right;
  vertical-align: top
}

.bl {
  font-size: 1px;
  padding: 0;
  width: 4;
  text-align: left;
  vertical-align: bottom
}

.br {
  font-size: 1px;
  padding: 0;
  width: 4;
  text-align: right;
  vertical-align: bottom
}

.loginBox {
  padding: 15px 5px 40px;
  margin: 0 0 0 0px;
  text-align: center;
}

.loginBox td {
padding-bottom: 5px;
}

.loginBox td.smallfont {
font-size: 80%;
}

.loginBox h2 {
  margin:0 0 0 0;
  font-weight: bold;
  font-size: 120%;
}

.loginBox table {
  margin: 0px;
  text-align: left;
}

.loginBox p {
  text-align: left;
}

#alBoxWrap {
  margin-top: 10px;
}

.alBox {
  padding: 5px 10px;
  margin: 0px;
}

.alBox h4 {
  margin: 0px;
  font-size: 80%;
}

.errormsg {
  color: #cc0000
}

.alert {
  color: #FF0000
}
.sites-teaser {
  margin-top: 0;
  padding: 6px;
  background-color: #ffffee;
  border: 1px solid #eeee00;
}

</style>

</head>
<body>
<div class="wrapper">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
  <tbody><tr valign="top">
  <td width="1%">
  <img src="<%=basePath%>img/logo/${session.SessionContainer.logoUrl}" alt="" align="left" border="0" vspace="10" width="273" height="36">
  </td>
  <td bgcolor="#ffffff" valign="top" width="99%">
  <table cellpadding="2" width="100%">
  <tbody><tr valign="bottom">
  <td>
  <div align="right">&nbsp;
  </div>
  </td>
  </tr>
  <tr>
  <td nowrap="nowrap">
  <table style="margin-bottom: 5px;" dir="ltr" align="center" bgcolor="#c3d9ff" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr>
  <td class="bubble tl" align="left" valign="top">
  <img src="<%=basePath%>images/corner_tl.gif" class="c" alt="">
  </td>
  <td class="bubble" rowspan="2" style="font-weight: bold;text-align:left;" dir="ltr">
  <b>
  基于SaaS模式的实验室运营管理系统
  </b>
  </td>
  <td class="bubble tr" align="right" valign="top">
  <img src="<%=basePath%>images/corner_tr.gif" class="c" alt="">
  </td>
  </tr>
  <tr>
  <td class="bubble bl" align="left" valign="bottom">
  <img src="<%=basePath%>images/corner_bl.gif" class="c" alt="">
  </td>
  <td class="bubble br" align="right" valign="bottom">
  <img src="<%=basePath%>images/corner_br.gif" class="c" alt="">
  </td>
  </tr>
  </tbody></table>
  </td>
  </tr>
  </tbody></table>
  </td>
  </tr>
</tbody></table>
<table border="0" cellpadding="1" cellspacing="1" width="260" style="margin:0 auto;margin-top:100px;">
  <tbody><tr>
  <td id="gaia_loginbox_td" align="center" valign="top">
<script><!--

function gaia_onLoginSubmit() {

  
  if (window.gaiacb_onLoginSubmit) {
    return gaiacb_onLoginSubmit();
  } else {
    return true;
  }

}


function gaia_setFocus() {
  var f = null;
  if (document.getElementById) { 
    f = document.getElementById("gaia_loginform");
  } else if (window.gaia_loginform) { 
    f = window.gaia_loginform;
  } 
  if (f) {
    if (f.Email && (f.Email.value == null || f.Email.value == "")) {
      f.Email.focus();
    } else if (f.Passwd) {
      f.Passwd.focus();
    } 
  }
}
--></script>

<style type="text/css"><!--
.gaia.le.lbl { font-family: Arial, Helvetica, sans-serif; font-size: smaller; }
.gaia.le.fpwd { font-family: Arial, Helvetica, sans-serif; font-size: 70%; }
.gaia.le.chusr { font-family: Arial, Helvetica, sans-serif; font-size: 70%; }
.gaia.le.val { font-family: Arial, Helvetica, sans-serif; font-size: smaller; }
.gaia.le.button { font-family: Arial, Helvetica, sans-serif; font-size: smaller; }
.gaia.le.rem { font-family: Arial, Helvetica, sans-serif; font-size: smaller; }

.gaia.captchahtml.desc { font-family: arial, sans-serif; font-size: smaller; } 
.gaia.captchahtml.cmt { font-family: arial, sans-serif; font-size: smaller; font-style: italic; }
  
--></style>
		<a href="#" onclick="window.location.href='<%=basePath %>servlet/loginFromSession?'">欢迎进入！请点击！</a>

  </td>

  </tr>
</tbody></table>
<div class="footer" style="">
<span>系统维护单位：西安瑞铂软件科技有限公司</span><span>电话：029-88440668</span><span><a href="http://www.labsoft.cn">www.labsoft.cn</a></span>
</div>
</div>
<br><br>


</body>
























