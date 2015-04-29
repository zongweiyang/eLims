<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.labsoft.labos.framework.common.sesseionutils.SessionContainer"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld" %>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
	<%@ include file="/jsp/include/common.jsp"%>
	<link rel=stylesheet href="<%=basePath%>skin/<s:property value="#session.SessionContainer.styleName"/>/css/common.css" type="text/css">
	<link rel=stylesheet href="<%=basePath%>style/global.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>js/jquery/ui/css/jquery-ui.css" type="text/css" media="all" />
	<script language="JavaScript" src="<%=basePath%>js/jquery/ui/jquery-ui-1.8.16.custom.js"></script>
	<script language="JavaScript" src="<%=basePath%>js/portal/commonutil.js"></script>
	<style>
	.bodytable .oRight {
		padding-left: 0;
	}

	html {
		_overflow-x: hidden;
	}
    .bold {
        font-weight:bold;
        font-size:25px;    
     }    
    </style>
	<script type="text/javascript">		
		function fmoney(s, n) { 
			n = n > 0 && n <= 20 ? n : 2; 
			s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
			var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1]; 
			t = ""; 
			for (i = 0; i < l.length; i++) { 
				t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
				} 
				return t.split("").reverse().join("") + "." + r; 
		} 

		
		
		function showInfo(url,title){
			$.dialog({
				id:'showProgressInfo',
				content:'url:'+url,
				title:title,
				opacity:0.4,
				width:800,
				//height:550,
				lock:true
			 });
		}
		
		function showContext(uri,id){
			 $.ajax({
				   url:"<%=basePath%>coreextend/extend/ajaxDeleteTrayMessage.action",
				   type:"post",
				   data:{'id':id},
				   dataType:'text',
				   success:function(data){
				   		
				   }
			});
			window.location.href = basePath+uri;
		}
		
		$(function(){
			/*
			getSampInfo();
			getCostInfo();
			getFinanceInfo();
			getSampDelayed();
			getPerformance();
			getTCostInfo();
			*/
		});
		
		//本年收费统计
		function getCostInfo(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'cost'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = eval('('+data+')').data;
				   		arrayData = arrayData[0];
				   		for(var index = 0;index < arrayData.length;index++){
				   			$("#cost_"+index).html(fmoney(arrayData[index],2));
				   		}
				   }
			});
		}
		
		//本年样品统计
		function getSampInfo(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'samp'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = eval('('+data+')').data;
				   		
				   		for(var index = 0;index < arrayData.length;index++){
				   			$("#samp_"+index).html(arrayData[index][1]);
				   		}
				   }
			});
		} 
		
		//超期
		function getSampDelayed(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'delayed'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = eval('('+data+')');
				   		$("#delayed").html(arrayData[0]);
				   }
			});
		} 
		
		//绩效
		function getPerformance(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'performance'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = data.split('_');
				   		$("#performance0").html(arrayData[0]);
				   		$("#performance1").html(arrayData[1]);
				   }
			});
		} 
		
		//采购支出
		function getFinanceInfo(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'financeInfo'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = data.split('_');
				   		$("#finance0").html(arrayData[0]);
				   		$("#finance1").html(arrayData[1]);
				   }
			});
		} 
		
		//费用支出统计
		function getTCostInfo(){
			 $.ajax({
				   url:"<%=basePath%>/samp/statistics/ajaxCount.action",
				   type:"post",
				   data:{'type':'tCostInfo'},
				   dataType:'text',
				   success:function(data){
				   		var arrayData = data.split('_');
				   		$("#tCost0").html(arrayData[0]);
				   		$("#tCost1").html(arrayData[1]);
				   }
			});
		} 
	</script>
	</head>
	<body class="" id="mainid" style="overflow-y: hidden; height: 100%">
	<input type="hidden" id="columnChange"/>
	<input type="hidden" id="portletOrder"/>
		<s:form theme="simple" action="" method="post" name="sysOrgForm">
			<input type="hidden" name="sysOrgVo.parentId" id="parentId" value="${sysOrgVo.parentId }" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr>
		           <td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2><s:text name="theme.home"></s:text>
												<s:if test="${key}">
													<a href="${basePath}console/showConsole.action" onclick="window.parent.document.getElementById('bottomid').cols = '0,*';"><s:text name="theme.change"></s:text></a>
												</s:if>
											</h2>
										</div>
											<table cellspacing="0" cellpadding="0" class="welcome_table">
											<tr>
												<td class="bujutab" rowspan="2">
												    <table cellspacing="0" cellpadding="0" class="listTable">
												    <tr>
												    	<th colspan="3"><s:text name="theme.my.foucs"></s:text></th>
											    	</tr>
											    	<s:if test="${fn:indexOf(show, 'BNYPTJ')>-1}">
												    <tr>	
												   		<td width="120" rowspan="5" class="titletd"> 
												   			<a href="javascript:;" onclick="showInfo('${basePath}samp/statistics/listSampType.action','<s:property value="getText('theme.sam.count')"/>');return false;"><img src="<%=basePath%>img/bargraph32.png"/><br /><s:text name="theme.sam.count"></s:text></a>
											   			</td>
                           								<td width="120" class="labeltd"><s:text name="theme.contrct.check"></s:text>：</td>
                           								<td class="valuetd"><span id="samp_0">0</span><s:text name="theme.slice"></s:text></td>
                         						   </tr>
												   <tr>
												   		<td class="labeltd"><s:text name="theme.random.contrct"></s:text>：</td>
												   		<td class="valuetd"><span id="samp_1">0</span><s:text name="theme.slice"/></td>
											   		</tr>
												   <tr>
												   		<td class="labeltd"><s:text name="theme.teck.ask"></s:text>：</td>
												   		<td class="valuetd"><span id="samp_2">0</span><s:text name="theme.slice"/></td>
											   		</tr>
												   <tr>
												   		<td class="labeltd"><s:text name="theme.train.move"></s:text>：</td>
												   		<td class="valuetd"><span id="samp_3">0</span><s:text name="theme.slice"/></td>
											   		</tr>
											   		<tr>
												   		<td class="labeltd"><s:text name="theme.plane.oil"></s:text>：</td>
												   		<td class="valuetd"><span id="samp_4">0</span><s:text name="theme.slice"/></td>
											   		</tr>
											   		</s:if>
											   		<s:if test="${fn:indexOf(show, 'BNSFTJ')>-1}">
                           							<tr>	
                           								<td width="120" rowspan="3" class="titletd"><a href="javascript:;" onclick="showInfo('${basePath}samp/statistics/listSampCost.action','<s:property value="getText('theme.yearmoney')"/>');return false;"><img src="<%=basePath%>img/risegraph32.png"/><br /><s:text name="theme.yearmoney"></s:text></a></td>
                           								<td width="100" class="labeltd"><s:text name="theme.charge.money"></s:text>：</td>
                           								<td class="valuetd"><span id="cost_0">0</span><s:text name="theme.yuan"></s:text></td>
                         							</tr>
												   <tr>
												   		<td class="labeltd"><s:text name="theme.charged.money"></s:text>：</td>
												   		<td class="valuetd"><span id="cost_1">0</span><s:text name="theme.yuan"></s:text></td>
											   		</tr>
												   <tr>
												   		<td class="labeltd"><s:text name="theme.unpayed.money"></s:text>：</td>
												   		<td class="valuetd"><span id="cost_2">0</span><s:text name="theme.yuan"></s:text></td>
											   		</tr>
											   		</s:if>
											   		<s:if test="${fn:indexOf(show, 'CQYPTJ')>-1}">
													<tr>
												    	<td width="120" class="titletd"><a href="javascript:;" onclick="showInfo('${basePath}samp/statistics/listSampDelayed.action','<s:property value="getText('theme.over.sam')"/>');return false;"><s:text name="theme.over.sam"></s:text></a></td>
												    	<td width="100" class="labeltd"><s:text name="theme.over.samamout"></s:text>：</td>
                              							<td class="valuetd"><span id="delayed">0</span><s:text name="theme.slice"></s:text></td>
                              						</tr>
                              						</s:if>
											   		<s:if test="${fn:indexOf(show, 'JKBXTJ')>-1}">
                              						<tr>
												    	<td width="120" rowspan="2" class="titletd"><a href="javascript:;" onclick="showInfo('${basePath}samp/statistics/listQueryCostApply.action','<s:property value="getText('theme.loan.count')"/>');return false;"><s:text name="theme.loan.count"></s:text></a></td>
												    	<td width="100" class="labeltd"><s:text name="theme.money.out"></s:text>：</td>
                              							<td class="valuetd"><span id="tCost0">0</span><s:text name="theme.yuan"></s:text></td>
                              						</tr>
                              						<tr>
													   	<td class="labeltd"><s:text name="theme.money.in"/>：</td>
													   	<td class="valuetd"><span id="tCost1">0</span><s:text name="theme.yuan"></s:text></td>
												   	</tr>
												   	</s:if>
											   		<s:if test="${fn:indexOf(show, 'CGFYTJ')>-1}">
												   	<tr>
												    	<td width="120" rowspan="2" class="titletd"><a href="javascript:;" onclick="showInfo('${basePath}purchase/finance/getStatisticsFinanceList.action','<s:property value="getText('theme.buy.money')"/>');return false;"><s:text name="theme.buy.money"></s:text></a></td>
												    	<td width="100" class="labeltd"><s:text name="theme.buy.depart"></s:text>：</td>
                              							<td class="valuetd"><span id="finance0">0</span></td>
                              						</tr>
                              						<tr>
													   	<td class="labeltd"><s:text name="theme.fee.count"></s:text>：</td>
													   	<td class="valuetd"><span id="finance1">0</span></td>
												   	</tr>
												   	</s:if>
											   		<s:if test="${fn:indexOf(show, 'DYJXTJ')>-1}">
                              						<tr>
												    	<td width="120" rowspan="2" class="titletd"><a href="javascript:;" onclick="showInfo('${basePath}samp/statistics/listPerformance.action','<s:property value="getText('theme.month.active')"/>');return false;"><s:text name="theme.month.active"></s:text></a></td>
												    	<td width="100" class="labeltd"><s:text name="theme.check.count"></s:text>：</td>
                              							<td class="valuetd"><span id="performance0">0</span></td>
                              						</tr>
                              						<tr>
													   	<td class="labeltd"><s:text name="theme.check.score"></s:text>：</td>
													   	<td class="valuetd"><span id="performance1">0</span></td>
												   	</tr>
												   	</s:if>
												    </table>
												</td>
												<td class="bujutab" rowspan="2">
												    <table cellspacing="0" cellpadding="0" class="listTable" style="margin-bottom:10px;">
												    <tr><th colspan="3"><s:text name="theme.annce.notify"></s:text></th></tr>
												    <s:iterator value="unReadMessageList">
												    	<tr>
												    		<td width="150">${fn:substring(demo2,0,10)}【<s:text name="theme.announcement"></s:text>】</td>
												    		<td width="100">${sysUserBySender.username}</td>
												    		<td><font color='red'>
												    			<!-- 
												    			<a href="<%=basePath%>message/messageMain/showMessage.action?messageDetailVo.id=${id}">${demo1 }</a>
												    			 -->
												    			 ${demo1 }
												    			</font>
											    			</td>
											    		</tr>
												    </s:iterator>
												    <tr>
												    	<td width="150">2013-09-01【<s:text name="theme.notify"></s:text>】</td>
												    	<td colspan="2"><s:text name="theme.sales.plan"></s:text></td>
											    	</tr>
											    	<s:iterator value="#request.trayList">
												    	<tr>
												    		<td width="150">${fn:substring(time,0,10)}【消息】</td>
												    		<td width="100">${userName}</td>
												    		<td><a href="javascript:;" onclick="showContext('${uri}','${id}');"> ${title }</a></td>
											    		</tr>
												    </s:iterator>
												    </table>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					    </td>
				  </tr>
			</table>
		</s:form>
	</body>
</html>