<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		 <script type="text/javascript" src="<%=basePath%>/js/highcharts/1.7.1-jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/highcharts/highcharts.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/highcharts/modules/exporting.js"></script>
		<script type="text/javascript">
        $(function(){
        $('#container').highcharts({
           chart: {
            type: 'column'
              },
            title: {
                text: '${labSciScienceVo.name}---科研成果统计',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: [${names[0]}]
            },
            yAxis: {
                title: {
                    text: '成果数(个)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '个'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: '数目',
                data: [${names[1]}]
            }]
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
								<div id="container" style="min-width:700px;height:400px"></div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
