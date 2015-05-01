<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		
		<link rel='stylesheet' href='<%=basePath%>js/appbespeskJs/cupertino/theme.css' />
		<link href='<%=basePath%>js/appbespeskJs/fullcalendar/fullcalendar.css' rel='stylesheet' />
		<link href='<%=basePath%>js/appbespeskJs/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
		<script src='<%=basePath%>js/appbespeskJs/jquery-ui-1.10.2.custom.min.js'></script>
		<script src='<%=basePath%>js/appbespeskJs/fullcalendar/fullcalendar.min.js'></script>
		
	</head>
	<style>
.fc-grid .fc-day-content {
 /*clear: both;*/
 padding: 2px 2px 1px; /* distance between events and day edges */
 height:10px;
 }	
 
 </style>
<script> 
	$(document).ready(function() {
		var labAppUsebespekList =${labApparaUseListJSON};//接收json
		$('#calendar').fullCalendar({
			theme: true,
			header: {
				right: 'prev,next today',//右侧显示
	            center: 'title',//内容
	            left: 'month,agendaWeek,agendaDay'//左侧显示
			},
			 
			 height: 500,
			//内容高度，不包括表头contentHeight: 600
			//单元格宽与高度的比值
			//此属性不能与日历高度同时存在
			//aspectRatio: 1.35
			 dayNames:['星期日', '星期一', '星期二', '星期三','星期四', '星期五', '星期六'],
 			 dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
 			 firstDay: 1, //每周第一天从星期一开始 , 不写,0代表从星期日开始
			 editable: true,//事件是否可编辑,false不可编辑,不可拖拽
			 timeFormat: 'h(:mm)tt',//最右侧显示时间HH:mm{ - HH:mm}
			 events: function(start, end, callback) {//生成日程
			          var events = [];
			                 $(labAppUsebespekList).each(function(i){
			                 	events.push({
			                        title: labAppUsebespekList[i].appName,
			                        start: labAppUsebespekList[i].beStartDate,
			                        end: labAppUsebespekList[i].beEndDate,
			                        id:labAppUsebespekList[i].id,
			                        appId:labAppUsebespekList[i].appId,
			                        background:'#ff0000',
            						allDay : false 
            						//url：'',点击是会连接到该地址
            						//可自行写入其他参数,如上ID
			                    });    
			                 });
			          callback(events); //返回日程对象,生成日程
			         // $('span.fc-button-prev').before('<a id="BtnPreview" class="zPushBtn" onclick="backList();"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>');
			  		//$('span.fc-button-month').before('<a id="BtnPreview" class="zPushBtn" href="<%=basePath%>appara/useing/listLabAppUse.action"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>');
			  },
			dayClick: function(date, allDay, jsEvent, view) {
				//用于使用弹层新增预约仪器
				//参数说明：
				//date:获取点击时间;allDay:是否是全天日程;jsEvent:坐标参数;view:获取记录;
				var d = new Date();
				var year = date.getFullYear();
				var month = date.getMonth()+1; //js从0开始取
				var date1 = date.getDate();
				var hour = d.getHours();
				var minutes = d.getMinutes();
				var second = d.getSeconds();
				if(date1<10){
					date1 = "0" + date1;
				}
				if(month<10){
					month = "0" + month;
				}
				if(hour <10){
					hour = "0" + hour;
				}
				if(minutes <10){
					minutes = "0" + minutes;
				}
				if(second <10){
					second = "0" + second ;
				}
				var startDate= year+"-"+month+"-"+date1+" "+hour+":"+minutes +":"+second;
				var url='<%=basePath%>/appara/useing/preAddLabAppUse4bespek.action?labApparaUseVo.beStartDate='+startDate;
				$.dialog({
					id:'power',
					content:'url:'+url,
					title:'<s:property value="getText('addedpre')"/>',
					maxBtn:false,
					rang: true,
					drag: true,
					resize: false,
					bgcolor:'#000',
					opacity:0.4,
					width:600, 
					iconTitle:false,
					btnBar:false,
					height:400,
					max:false,
					min:false,
					lock:true,
					autoPos:{left:'center',top:'center'}
				 });
		    },
		    eventClick: function(calEvent, jsEvent, view) {
		    	//用于显示详情,移除,修改
		    	var url='<%=basePath%>/appara/useing/preUpdateLabAppUse4bespek.action?labApparaUseVo.id='+calEvent.id;
				$.dialog({
					id:'power',
					content:'url:'+url,
					title:'<s:property value="getText('todayreserve')"/>',
					maxBtn:false,
					rang: true,
					drag: true,
					resize: false,
					bgcolor:'#000',
					opacity:0.4,
					width:600, 
					iconTitle:false,
					btnBar:false,
					height:400,
					max:false,
					min:false,
					lock:true,
					autoPos:{left:'center',top:'center'}
				 });
		    },
		    eventDrop:function( event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view ) {
		    	//日程移动事件
		    	var startDate = event.start;
		    	var year = startDate.getFullYear();
				var month = startDate.getMonth()+1; //js从0开始取
				var date = startDate.getDate();
				var hour = startDate.getHours();
				var minutes = startDate.getMinutes();
				var second = startDate.getSeconds();
				if(date<10){
					date = "0" + date;
				}
				if(month<10){
					month = "0" + month;
				}
				if(hour <10){
					hour = "0" + hour;
				}
				if(minutes <10){
					minutes = "0" + minutes;
				}
				if(second <10){
					second = "0" + second ;
				}
				var bespeakStartDate= year+"-"+month+"-"+date+" "+hour+":"+minutes +":"+second;
		    	var endDate = event.end;
		    	if(endDate!=null){
			    	year = endDate.getFullYear();
					month = endDate.getMonth()+1; //js从0开始取
					date = endDate.getDate();
					hour = endDate.getHours();
					minutes = endDate.getMinutes();
					second = endDate.getSeconds();
					if(date<10){
						date = "0" + date;
					}
					if(month<10){
						month = "0" + month;
					}
					if(hour <10){
						hour = "0" + hour;
					}
					if(minutes <10){
						minutes = "0" + minutes;
					}
					if(second <10){
						second = "0" + second ;
					}
					var bespeakEndDate= year+"-"+month+"-"+date+" "+hour+":"+minutes +":"+second;
		    	}else{
		    		alert('<s:property value="getText('preendtiem')"/>');
		    		revertFunc();
		    		return false;
		    	}
		    	if(!confirm('<s:property value="getText('confrimmodiriedtime')"/>')){
		    		revertFunc(); //恢复
		    	}else{
		    		var url = '<%=basePath%>appara/useing/ajaxLabAppUsebes4update.action?labApparaUseVo.id='+event.id+'&labApparaUseVo.beStartDate='+bespeakStartDate+'&labApparaUseVo.beEndDate='+bespeakEndDate+'&labApparaUseVo.appId='+event.appId; 
					$.ajax({
							url:url,
							type:'POST',
							dataType:'text',
							success:function(data){
								if('false'==data){
									alert('<s:property value="getText('tiemconsionfailu')"/>');
									revertFunc();
								}else{
									alert('<s:property value="getText('modify.success')"/>');
								}
							},
							error:function(){
								alert('<s:property value="getText('network.error')"/>');
							}
						});
		    	}
			}
		});
		$('span.fc-button-month').before('<a id="BtnPreview" class="zPushBtn" href="<%=basePath%>appara/useing/listLabAppUse.action"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>');
	});
    
</script>
<style>
#calendar {
	width: 100%;
	height: 90%;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto;">
		<form action="" method="post">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td id="bodyCell" class="oRight">
						<div class="myworkingbox">
							<div class="myworkingboxttitle">
								<h2>
									${funName }：
									<span><s:text name="apppre"/></span>
								</h2>
							</div>
							<div class="TabTableBOX01 b" id="Tab01">
								<div class="tabtablebox">
									<table width="100%">
										<tr>
											<td>
												<div id='calendar'></div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
