<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><div><P class="content-head" style="TEXT-ALIGN: center"><SPAN style="FONT-SIZE: 28px">收费单</SPAN></P>
<P class="content-head" style="TEXT-ALIGN: center">&nbsp;</P>
<P class="content-head"><SPAN style="FONT-SIZE: 14px">单号：xxx&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日期：2014-04-15</SPAN></P>
<P class="content-head">&nbsp;</P>
<TABLE style="HEIGHT: 32px; WIDTH: 100%" border="1" cellSpacing="1" cellPadding="1">
<TBODY>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">付款单位：</SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.paymentUnit}" key1="labChargeVo.paymentUnit">付款单位</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">收款单位：</SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.collectionUnit}" key1="labChargeVo.collectionUnit">收款单位</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">收费项目： </SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.payName}" key1="labChargeVo.payName">收款项目</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">应收金额：</SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.totalMoney}" key1="labChargeVo.totalMoney">总金额</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px"><SPAN style="FONT-SIZE: 18px">已收金额： </SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.payMoney}" key1="labChargeVo.payMoney">实收金额</SPAN></TD>
<TD><SPAN style="FONT-SIZE: 18px">付款类型：</SPAN></TD>
<TD><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.chargeType}" key1="labChargeVo.chargeType">付款类型</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 64px"><SPAN style="FONT-SIZE: 18px">款项内容： </SPAN></TD>
<TD style="TEXT-ALIGN: left" rowSpan="1" colSpan="3"><SPAN class="are-page-data are-page-data-css" style="FONT-SIZE: 12px" key="${labChargeVo.payInfo}" key1="labChargeVo.payInfo">款项内容</SPAN></TD></TR></TBODY></TABLE>
<P>&nbsp;</P></div>