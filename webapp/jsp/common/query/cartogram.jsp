<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		  <script type="text/javascript" src="<%=basePath%>/js/highcharts/highcharts.js"></script>
		  <script type="text/javascript" src="<%=basePath%>/js/highcharts/exporting.js"></script>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
		<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 
		<META HTTP-EQUIV="expires" CONTENT="0"> 
		<style>
</style>
		<script>
			$(function () {
					
			    $('#container').highcharts({
			        chart: {
			            type: 'spline'
			        },
			        title: {
			            text: '${funName}查询'
			        },
			        xAxis: {
			            type: 'datetime',
			            dateTimeLabelFormats: { // don't display the dummy year
			              	day: '%Y-%m-%d', 
                            week: '%Y-%m-%d', 
                            month: '%Y-%m-%d', 
                            year: '%Y-%m-%d' 

			            }
			        },
			        yAxis: {
			            title: {
			                text: '总金额（元）'
			            },
			            min: 0
			        },
			        tooltip: {
			            formatter: function() {
			                    return '<b>'+ this.series.name +'</b><br/>'+
			                    Highcharts.dateFormat('%y.%m.%d', this.x) +': '+ this.y +'个';
			            }
			        },
			        
			        series: [{
			            name: '${funName}数量',
			            // Define the data points. All series have a dummy year
			            // of 2014/71 in order to be compared on the same x axis. Note
			            // that in JavaScript, months start at 0 for January, 1 for February etc.
			            data: [
			            	<s:iterator value="#request.labQueryVo.listCartogram" status="st">
			             		[Date.UTC(${name}), ${value}],
			     			 </s:iterator>
			            ]
			        }]
			    });
			});				
		
		</script>
</html>
