<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String url = request.getParameter("url"); 
%>

<html>
<head>
	<script type="text/javascript">
		function loadDataFDB(){
			window.location.href = <%=url%>;
		}
		
	</script>
</head>
<body>
<div id="progressbar" align="center" style="margin-top:200px">
<table border = "0">
	<tr>
		<td align="center">
			<font size="1"><s:text name="data.loading"/></font>
		</td>
	</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="1" rowspan="2"></td>
      <td width="1" height="1"></td>
      <td height="1" bgcolor="#cccccc"></td>
      <td width="1"></td>
      <td width="1" rowspan="2"></td>
    </tr>
    <tr>
      <td width="1" height="1" bgcolor="#cccccc"></td>
      <td height="1"></td>
      <td width="1" bgcolor="#cccccc"></td>
    </tr>
    <tr>
      <td width="1" bgcolor="#cccccc"></td>
      <td width="1" height="1"></td>
      <td width="180" height="0"><marquee direction="right" scrollamount="7">
        <table id="marq" cellpadding="0" cellspacing="1" style="FONT-SIZE:8px;WIDTH:50px;HEIGHT:10px;empty-cells:show;">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </marquee>
      </td>
      <td width="1" height="1"></td>
      <td width="1" bgcolor="#cccccc"></td>
    </tr>
    <tr>
      <td rowspan="2"></td>
      <td height="1" bgcolor="#cccccc"></td>
      <td height="1"></td>
      <td bgcolor="#cccccc"></td>
      <td rowspan="2"></td>
    </tr>
    <tr>
      <td height="1"></td>
      <td height="1" bgcolor="#cccccc"></td>
      <td></td>
    </tr>
  </table>
  <br>
  <br>
  <br>
 </div> 
  </body>
  <script languge="JavaScript">
 var lightRGB = new Array(255,255,255);
 initColor("#0000FF");
 function initColor(Color){
  var opArray=new Array(.10,.25,.50,.85,1.0);
  var baseColor = longHexToDec(Color.substring(1,7));
  var nColor,nHex;
  for(var i=0;i<5;i++) {
   nColor=setColorHue(baseColor,opArray[i],lightRGB);
   nHex = toHex(nColor[0])+toHex(nColor[1])+toHex(nColor[2]);
   document.all.item("marq").firstChild.firstChild.children(i).bgColor="#"+nHex;
  }
 }
 
 function setColorHue(originColor,opacityPercent,maskRGB) {
  returnColor=new Array();
  for(w=0;w<originColor.length;w++) returnColor[w] = Math.round(originColor[w]*opacityPercent) + Math.round(maskRGB[w]*(1.0-opacityPercent));
  return returnColor;
 }
 
 function longHexToDec(longHex) {
  r=toDec(longHex.substring(0,2));
  g=toDec(longHex.substring(2,4));
  b=toDec(longHex.substring(4,6));
  return new Array(r,g,b);
 }
 
 function toHex(dec) {
  hex=dec.toString(16);
  if(hex.length==1)hex="0"+hex;
  if(hex==100)hex="FF";
  return hex.toUpperCase();
 }
 
 function toDec(hex) {
  return parseInt(hex,16);
 }
  </script>
