<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		  <script type="text/javascript" src="<%=basePath%>jsp/common/query/js/amcharts.js"></script>
		  <script type="text/javascript" src="<%=basePath%>jsp/common/query/js/serial.js"></script>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
		<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 
		<META HTTP-EQUIV="expires" CONTENT="0"> 
		<style>
</style>
		  <script type="text/javascript">
            var chart;
            var kk;
			var chartData = [
	                <s:iterator value="#request.labQueryVo.listCartogram" status="st">
	                		{
			                   "country":'${name}',
			                   "litres":${value},
			                   "short":'${name}'
			               	<s:if test="${st.last}">
			               		}
							</s:if>
							<s:else>
								},
							</s:else>
			         </s:iterator>
           		];

            AmCharts.ready(function () {
                // SERIAL CHART
                var chart = new AmCharts.AmSerialChart();
                chart.dataProvider =chartData;
                chart.categoryField = "country";
                chart.startDuration = 2;
                // change balloon text color
                chart.balloon.color = "#000000";

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridAlpha = 0;
                categoryAxis.axisAlpha = 0;
                categoryAxis.labelsEnabled = false;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.gridAlpha = 0;
                valueAxis.axisAlpha = 0;
                valueAxis.labelsEnabled = false;
                valueAxis.minimum = 0;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.balloonText = "[[category]]: [[value]]";
                graph.valueField = "litres";
                graph.descriptionField = "short";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.fillColors = ["#ffe78e", "#bf1c25"];
                graph.labelText = "[[description]]";
                graph.balloonText = "[[category]]: [[value]] Litres";
                chart.addGraph(graph);

                chart.creditsPosition = "top-right";

                // WRITE
                chart.write("chartdiv");
            });
        </script>
</html>
