<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="../../../jsp/include/common.jsp"%>
    <title>My JSP 'InfoCount.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		 var chart;
	    $(function(){
	    	var times=$("#times").val().split(",");
	    	var values=$("#values").val().split(",");
	    	var upValues='${labAmbientInfoVo.upValues}'.split(",");
	    	var downValues='${labAmbientInfoVo.downValues}'.split(",");
	    	var arrayObj = new Array([values.length]);
	    	var downObj=new Array([values.length]);
	    	var upObj=new Array([values.length]);
	    	for(var i=0;i<values.length;i++){
	    		arrayObj[i]=parseFloat(values[i]);
	    		downObj[i]=parseFloat(downValues[i]);
	    		upObj[i]=parseFloat(upValues[i]);
	    	}
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'container',
	                type: 'line',
	                marginRight: 130,
	                marginBottom: 25
	            },
	            title: {
	                text: '数据分析',
	                x: -20 //center
	            },
	            xAxis: {
	                categories: times
	            },
	            yAxis: {
	                title: {
	                    text: '参考值'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b>'+ this.series.name +'</b><br/>'+
	                        this.x +': '+ this.y +'${labAmbientInfoVo.labAmbientUnit}';
	                }
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'top',
	                x: -10,
	                y: 100,
	                borderWidth: 0
	            },
	            series: [{
	                name: '${labAmbientInfoVo.labAmbientName}',
	                data: arrayObj
	            },{
	                name: '下限',
	                data: downObj
	            },{
	                name: '上限',
	                data: upObj
	            }]
	        });
	    });
	</script>
  </head>
  
  <body>
		  <script src="${basePath}js/highcharts/highcharts.js"></script>
		<input type="hidden" id="times" value="${labAmbientInfoVo.times}"/>
		<input type="hidden" id="values" value="${labAmbientInfoVo.values}"/>
		<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
  </body>
</html>
