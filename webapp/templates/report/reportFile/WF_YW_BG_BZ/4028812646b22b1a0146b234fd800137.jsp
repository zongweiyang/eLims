<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><div><P style="TEXT-ALIGN: center" class="content-head">&nbsp;</P>
<P style="TEXT-ALIGN: center" class="content-title">&nbsp;</P>
<P style="TEXT-ALIGN: center" class="content-title"><SPAN style="FONT-SIZE: 28px"><STRONG>检&nbsp;&nbsp;验&nbsp;&nbsp;报&nbsp;&nbsp;告</STRONG> </SPAN></P>
<P style="TEXT-ALIGN: center" class="content-title">&nbsp;</P>
<DIV style="MIN-WIDTH: 110px; FLOAT: left; MARGIN-LEFT: 10px">
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; FLOAT: left; FONT-SIZE: 15px">编号：</DIV>
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: left; WIDTH: 120px; FLOAT: left; HEIGHT: 17px; FONT-SIZE: 15px"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.reportNo}" key1="labSamReportVo.reportNo">LAB-2014-001</SPAN></DIV></DIV>
<DIV style="MIN-WIDTH: 110px; FLOAT: right; MARGIN-RIGHT: 10px">
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: right; WIDTH: 57px; FLOAT: left; HEIGHT: 17px; FONT-SIZE: 15px">日期：</DIV>
<DIV style="MIN-WIDTH: 50px; TEXT-ALIGN: left; FLOAT: left; FONT-SIZE: 15px"><SPAN style="WIDTH: 103.32%; HEIGHT: 15px; FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.finishDate}" key1="labSamReportVo.finishDate">2014-07-19</SPAN></DIV></DIV>
<P>&nbsp;</P>
<TABLE style="HEIGHT: 32px" class="    cke_show_border">
<TBODY>
<TR>
<TD style="WIDTH: 100px; HEIGHT: 32px">客户名称</TD>
<TD style="WIDTH: 120px"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.labCustomerName}" key1="labSamReportVo.labCustomerName">西安纤维检验所</SPAN></TD>
<TD style="WIDTH: 100px" colSpan="2">联 系 人</TD>
<TD style="WIDTH: 120px" colSpan="2"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.user}" key1="labSamReportVo.user">张三</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px">邮&nbsp;&nbsp; 箱</TD>
<TD><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.email}" key1="labSamReportVo.email">info@labsoftpl.com</SPAN></TD>
<TD colSpan="2">联系电话</TD>
<TD colSpan="2"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.telephone}" key1="labSamReportVo.telephone">13811111111</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px">地&nbsp;&nbsp;&nbsp; 址</TD>
<TD style="TEXT-ALIGN: left; HEIGHT: 32px" colSpan="5"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.address}" key1="labSamReportVo.address">锦业路1号</SPAN></TD></TR>
<TR>
<TD style="HEIGHT: 32px">受检单位</TD>
<TD style="HEIGHT: 32px"></TD>
<TD style="HEIGHT: 32px" colSpan="2">受 理 人</TD>
<TD style="HEIGHT: 32px" colSpan="2"></TD></TR>
<TR>
<TD style="HEIGHT: 32px">受理日期</TD>
<TD style="HEIGHT: 32px"></TD>
<TD style="HEIGHT: 32px" colSpan="2">检测类别</TD>
<TD style="HEIGHT: 32px" colSpan="2"></TD></TR>
<TR>
<TD style="HEIGHT: 32px">送样人员</TD>
<TD style="HEIGHT: 32px"></TD>
<TD style="HEIGHT: 32px" colSpan="2">送样日期</TD>
<TD style="HEIGHT: 32px" colSpan="2"></TD></TR>
<TR>
<TD style="HEIGHT: 32px">样品数量</TD>
<TD style="HEIGHT: 32px"></TD>
<TD style="HEIGHT: 32px" colSpan="2">来样编号</TD>
<TD style="HEIGHT: 32px" colSpan="2"></TD></TR>
<TR>
<TD style="HEIGHT: 32px">样品来源</TD>
<TD style="HEIGHT: 32px"></TD>
<TD style="HEIGHT: 32px" colSpan="2">报告日期</TD>
<TD style="HEIGHT: 32px" colSpan="2"></TD></TR>
<TR>
<TD style="HEIGHT: 32px">检验项目</TD>
<TD style="HEIGHT: 32px" colSpan="5">
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P></TD></TR>
<TR>
<TD style="HEIGHT: 32px">检验依据</TD>
<TD colSpan="5">
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P></TD></TR>
<TR>
<TD>
<P style="TEXT-ALIGN: center">检</P>
<P style="TEXT-ALIGN: center">验</P>
<P style="TEXT-ALIGN: center">结</P>
<P style="TEXT-ALIGN: center">论</P></TD>
<TD colSpan="5">
<P style="LINE-HEIGHT: 40px" class="table-p">&nbsp;</P>
<P style="LINE-HEIGHT: 40px" class="table-p">&nbsp;</P>
<P style="LINE-HEIGHT: 40px" class="table-p"><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.testResult}" key1="labSamReportVo.testResult">根据《国家xxx号标准》检验，结果符合规定</SPAN></P>
<P style="LINE-HEIGHT: 40px" class="table-p">&nbsp;</P>
<P style="TEXT-ALIGN: right" class="table-p">签发日期：&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</P></TD></TR>
<TR>
<TD style="HEIGHT: 90px">备注</TD>
<TD colSpan="5">
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P><SPAN style="FONT-SIZE: 12px" class="are-page-data" key="${labSamReportVo.remark}" key1="labSamReportVo.remark">本次检验只供参考</SPAN></P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P></TD></TR></TBODY></TABLE></div>