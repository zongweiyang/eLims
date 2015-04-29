<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<script type="text/javascript"
			src="<%=basePath%>/js/highcharts/1.7.1-jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/js/highcharts/highcharts.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript">
  $(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '人员发表文章统计'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
               ${labSciScienceVo.goal}
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '文章数(篇)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 篇</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
        <s:set name="arr_str" value="#request.code"/>
        <s:iterator value="#arr_str" id="itStr" status="ss">
        <s:if test="${ss.index}<#request.code.length-1">
        {
           name: '${code[ss.index]}',
           data: [${names[ss.index]}]
        },</s:if><s:else>
         {
           name: '${code[ss.index]}',
           data: [${names[ss.index]}]
        }
        </s:else>
        </s:iterator>
       ]
    });
});				
		</script>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<form action="" method="post" name="mouldForm">
							<div class="Formtable">
							<div id="container" style="min-width: 700px; height: 400px"></div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
