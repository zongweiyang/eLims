<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><div><P style="TEXT-ALIGN: center" class="content-head"><SPAN style="FONT-SIZE: 28px">收费单</SPAN></P>
<DIV style="MIN-WIDTH: 110px; FLOAT: left; MARGIN-LEFT: 10px">
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; FLOAT: left; FONT-SIZE: 15px">收费单：</DIV>
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: left; WIDTH: 188px; FLOAT: left; HEIGHT: 17px; FONT-SIZE: 15px"><SPAN style="WIDTH: 63.81%; HEIGHT: 15px; FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.code}" key1="labChargeVo.code">FYBH-20140620-0107</SPAN></DIV></DIV>
<DIV style="MIN-WIDTH: 110px; FLOAT: right; MARGIN-RIGHT: 10px">
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; WIDTH: 79px; FLOAT: left; HEIGHT: 17px; FONT-SIZE: 15px">收费日期：</DIV>
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; WIDTH: 93px; FLOAT: left; HEIGHT: 17px; FONT-SIZE: 15px"><SPAN style="WIDTH: 62.58%; HEIGHT: 15px; FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.collectionTime}" key1="labChargeVo.collectionTime">2014-06-20</SPAN></DIV></DIV>
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; FLOAT: right; FONT-SIZE: 15px; MARGIN-RIGHT: 24px"></DIV>
<TABLE style="WIDTH: 100%; HEIGHT: 32px" border="1" cellSpacing="1" cellPadding="1">
<TBODY>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">付款单位：</SPAN></TD>
<TD style="TEXT-ALIGN: left"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.paymentUnit}" key1="labChargeVo.paymentUnit">西安纤维检验所</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">收款单位：</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.collectionUnit}" key1="labChargeVo.collectionUnit">西安瑞铂软件</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">收费项目： </SPAN></TD>
<TD><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.payName}" key1="labChargeVo.payName">MN-20140620-0002</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">应收金额：</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.totalMoney}" key1="labChargeVo.totalMoney">10605.0</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">已收金额： </SPAN></TD>
<TD style="TEXT-ALIGN: left"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.payMoney}" key1="labChargeVo.payMoney">9421.5</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">付款类型：</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.payType}" key1="labChargeVo.payType">现金</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 64px"><SPAN style="FONT-SIZE: 18px">款项内容： </SPAN></TD>
<TD style="TEXT-ALIGN: left" rowSpan="1" colSpan="3"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labChargeVo.payInfo}" key1="labChargeVo.payInfo">Au,H,K,pH值,含碳量,纯净水,材料费,加急费</SPAN></TD></TR></TBODY></TABLE>
<P>&nbsp;</P></div>