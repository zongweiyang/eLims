<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.labsoft.labos.framework.common.sesseionutils.SessionContainer"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/jquery.jsp"%>
		<link rel=stylesheet href="<%=basePath%>theme/${themeType }/skin/<s:property value="#session.SessionContainer.styleName"/>/css/common.css" type="text/css">
		<link rel=stylesheet href="<%=basePath%>style/global.css" type="text/css">
		<link rel="stylesheet" href="<%=basePath%>js/jquery/ui/css/jquery-ui.css" type="text/css" media="all" />
		<script language="JavaScript" src="<%=basePath%>js/jquery/ui/jquery-ui-1.10.1.custom.min.js"></script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

.bold {
	font-weight: bold;
	font-size: 25px;
}
</style>
		<script type="text/javascript">		
	jQuery.cookie = function(name, value, options) {
	if (typeof value != 'undefined') {
	   options = options || {};
	   if (value === null) {
	    value = '';
	    options = $.extend({}, options);
	    options.expires = -1;
	   }
	   var expires = '';
	   if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
	    var date;
	    if (typeof options.expires == 'number') {
	     date = new Date();
	     date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
	    } else {
	     date = options.expires;
	    }
	    expires = '; expires=' + date.toUTCString();
	   }
	   var path = options.path ? '; path=' + (options.path) : '';
	   var domain = options.domain ? '; domain=' + (options.domain) : '';
	   var secure = options.secure ? '; secure' : '';
	   document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	} else {
	   var cookieValue = null;
	   if (document.cookie && document.cookie != '') {
	    var cookies = document.cookie.split(';');
	    for (var i = 0; i < cookies.length; i++) {
	     var cookie = jQuery.trim(cookies[i]);
	     if (cookie.substring(0, name.length + 1) == (name + '=')) {
	      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	      break;
	     }
	    }
	   }
	   return cookieValue;
	}
	};
	
	function sortTable(){
		$(".column").sortable({
			connectWith: '.column',
			stop: saveLayout
		}); 
		$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
			.find(".portlet-header").addClass("ui-widget-header ui-corner-all")
				.prepend('<span class="ui-icon ui-icon-minusthick"></span>')
				.end().find(".portlet-content");
		$(".portlet-header .ui-icon").click(function() {
			$(this).toggleClass("ui-icon-minusthick").toggleClass("ui-icon-plusthick");
			$(this).parents(".portlet:first").find(".portlet-content").toggle();
		});
		$(".column").disableSelection();
	}
	
	function saveLayout(){
		var list = "";
		$.each($(".column"), function(m){
			list += $(this).attr('id') + ":";
			$.each($(this).children(".portlet"), function(d){
				list += $(this).attr('id') + "@";
			})
			list += "|";
		})
		buttonControl(list);
		$('#portletOrder').val(list);
		updateUserPortlet();
		var h=25;
		if($('#column-0').height()>$('#column-1').height()){
			h+=$('#column-0').height();
		}
		else {
			h+=$('#column-1').height();
		}
		$('.myworkingbox').height(h);
	}
	
	function buttonControl(value){
		var tempValue=$('#portletOrder').val();
	}
	
	function initColmuns(){
		var list=$('#portletOrder').val();
		var arrColumn=list.split('|');
		$("#columnChange").val(arrColumn.length-1);
		drawColumns();
		sortTable();
	}
	
	function readLayout(){
		var list="column-1:portlet1@|column-2:portlet2|column-3:portlet3|column-4:portlet4@manyi2@maoli@|";
		var arrColumn=list.split('|');
		var columnsList=new Array();
		var contentsList=new Array();
		for(var i=0;i<arrColumn.length;i++){
			var column=arrColumn[i].split(":")[0];
			var content=arrColumn[i].split(":").length>=2?arrColumn[i].split(":")[1]:"";
			columnsList.push(column);
			contentsList.push(content);
		}
		var y=0;
		for(var i=0;i<contentsList.length;i++){
			var temp=contentsList[i].split("@");
			var length=temp.length-1;
			if(length>y){
				y=length;
			}
		}
		var x=columnsList.length-1;
		var xy=new Array();
		for(var i=0;i<y;i++){
			for(var j=0;j<x;j++){
				var tempContent = contentsList[j].split("@");
				if(tempContent.length <= i){
					xy.push("");
				}else{
					xy.push(tempContent[i]);
				}
			}
		}
		var cols=$(".column");
		var m = cols.length;
		for(var i=0;i<xy.length;i++){
			var containerID=cols.eq(i%m).attr("id");
			var portletID=xy[i];
			if (portletID==null||portletID=="") {//排除空值
				continue;
			}
			$("#" + containerID).append($("#" + portletID).attr('id', portletID));//把序列填加进容器
		}
		var h=25;
		if($('#column-0').height()>$('#column-1').height()){
			h+=$('#column-0').height();
		}
		else {
			h+=$('#column-1').height();
		}
		$('.myworkingbox').height(h);	
	}
	
	function drawColumns(){
		var value=$("#columnChange").val();
		$(".portlet").each(function(){
			$("#portlet").append($("#" + $(this).attr("id")).attr("id", $(this).attr("id")));
		})
		$("#columns").children().remove();
		for(var i=0;i<value;i++){
			$("#columns").append("<div class=\"column\" id=\"column-"+i+"\"></div>");
		}
	}
	
	//设置和获取cookie
	function makeCookie(){
		var userId='${sessionScope.SessionContainer.userId}';
		var cookieUserId=$.cookie('userId');
		if(cookieUserId==null||typeof(cookieUserId)=='undefined'||cookieUserId.length==0){
			$.cookie('userId',userId);
		}else{
			alert($.cookie('userId'));
		}
		
	}
	$(function(){
		getUserPortlet();
		initColmuns();
		readLayout();
		$('#portlet').hide();
	});
	function check(name){
		var el = document.getElementsByTagName('input');     
		var len = el.length; 
		var m = 0;    
		for(var i=0; i<len; i++)     
		{         
			if((el[i].type=="checkbox") && (el[i].name==name))         
			{             
				if(el[i].checked == true){
			    	m = m + 1;
			 	}      
			}     
		}  
		if(m<1){
			alert('<s:property value="getText('theme.del.record')"/>');
			return false;
		}else{
			return true;
		}
	}

	function submitvalue(actionstr) {
		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
	}
	
	function deleteMessage(actionstr) {
		var df = document.messageForm;
		df.action = actionstr;
		df.submit();
	}
	function getUserPortlet(){
		var userId = '${session.SessionContainer.userId}';
					var callBackDate = 'null';
					if(null==callBackDate||callBackDate=='null'||callBackDate){
						callBackDate = 'column-0:portlet1@|column-1:portlet2@|';
						$('#portletOrder').val(callBackDate);
					}else{
						$('#portletOrder').val(callBackDate);
					}
	}
	
	function updateUserPortlet(){
		var userId = '${session.SessionContainer.userId}';
		var order = $('#portletOrder').val();
	}
	
	</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<input type="hidden" id="columnChange" />
		<input type="hidden" id="portletOrder" />
		<s:form theme="simple" action="" method="post" name="sysOrgForm">
			<input type="hidden" name="sysOrgVo.parentId" id="parentId" value="${sysOrgVo.parentId }" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												<s:text name="theme.home"/>
											</h2>
										</div>
										<div id="columns"></div>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</s:form>
		<div id="portlet">
			<div class="portlet" id="portlet1" style="margin-left: 10px;">
				<div class="portlet-header">
					<s:text name="theme.unread.msg"></s:text>
				</div>
				<div class="portlet-content" style="height: 100px; overflow: auto;">
					<jsp:include page="/jsp/portlets/unreadMessage.jsp" flush="true" />
				</div>
			</div>
			<div class="portlet" id="portlet2">
				<div class="portlet-header">
					<s:text name="theme.alarm.msg"></s:text>
				</div>
				<div class="portlet-content" style="height: 100px; overflow: auto;">
					<jsp:include page="/jsp/portlets/alarmMessage.jsp" flush="true" />
				</div>
			</div>
			<!-- <div class="portlet" id="portlet3" style="margin-left: 10px;">
				<div class="portlet-header">
					样品统计指标
				</div>
				<div class="portlet-content" style="height: 100px;overflow:auto;">
					<jsp:include page="/jsp/portlets/sampleKPI.jsp" flush="true" />
				</div>
			</div>
			<div class="portlet" id="portlet4">
				<div class="portlet-header">
					试剂耗材指标
				</div>
				<div class="portlet-content" style="height: 100px;overflow:auto;">
					<jsp:include page="/jsp/portlets/reagentKPI.jsp" flush="true" />
				</div>
			</div> -->
		</div>
	</body>
</html>