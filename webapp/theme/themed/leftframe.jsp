<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>LabOS-<s:text name="login.labossystem"/></title>
	    <script type="text/javascript">
	    function updateCurrentUserInfo(){
		    $.ajax({
		   	  url:'<%=basePath%>admin/sysCurrentUser/updateSessionContainerToFile.action',
		   	  type:'post',
			  dataType:'text',
		   	  error:function (data){
		   	  	alert('请求错误.');
		   	  }
		   	});
	   	}
	    window.onload = function(){
    	    //dwr.engine.setActiveReverseAjax(true);  //侦听信息
	    }
		function getMessage(titleAndcontent){
		  
			var tempStr = titleAndcontent.split("^");
			//alert(tempStr);
			if('1'==tempStr[0]){
				if('${session.SessionContainer.userId}'==tempStr[1]){
					$("#sound_element").html("<embed src='<%=request.getContextPath() %>/sound/message.mp3' hidden=true autostart=true loop=false>");
					$.messager.lays(160, 120);
					$.messager.show('<font color=red>'+tempStr[2]+'</font>', '<font color=green style="font-size:14px;font-weight:bold;">'+tempStr[3]+'</font>',5500);
				}
			}else{
				if('2'==tempStr[0]){
					if('${session.SessionContainer.roleName}'==tempStr[1]){
						$("#sound_element").html("<embed src='<%=request.getContextPath() %>/sound/message.mp3' hidden=true autostart=true loop=false>");
						$.messager.lays(160, 120);
						$.messager.show('<font color=red>'+tempStr[2]+'</font>', '<font color=green style="font-size:14px;font-weight:bold;">'+tempStr[3]+'</font>',5500);
					}
				}else{
					if('3'==tempStr[0]){
						if('${session.SessionContainer.orgName}'==tempStr[1]){
							$("#sound_element").html("<embed src='<%=request.getContextPath() %>/sound/message.mp3' hidden=true autostart=true loop=false>");
							$.messager.lays(160, 120);
							$.messager.show('<font color=red>'+tempStr[2]+'</font>', '<font color=green style="font-size:14px;font-weight:bold;">'+tempStr[3]+'</font>',5500);
						}
					}else{
						if('4'==tempStr[0]){
							var tempStrTemp = tempStr[1].split("*");
								if(null!=tempStrTemp[1]&&""!=tempStrTemp[1]){
									if('${session.SessionContainer.orgId}'==tempStrTemp[1]){
										$("#sound_element").html("<embed src='<%=request.getContextPath() %>/sound/message.mp3' hidden=true autostart=true loop=false>");
										$.messager.lays(160, 120);
										$.messager.show('<font color=red>'+tempStr[2]+'</font>', '<font color=green style="font-size:14px;font-weight:bold;">'+tempStr[3]+'</font>',5500);
									}
								}else{
									$("#sound_element").html("<embed src='<%=request.getContextPath() %>/sound/message.mp3' hidden=true autostart=true loop=false>");
									$.messager.lays(160, 120);
									$.messager.show('<font color=red>'+tempStr[2]+'</font>', '<font color=green style="font-size:14px;font-weight:bold;">'+tempStr[3]+'</font>',5500);
								}
						}
					}
				}
			}
			//refreshWaitAuditCount();
		}
		
		//点击左边的待办事项 后 选择菜单的当前状态为当前的点击状态
		function selectCurrentMenu(parentId,currentId){
			/*$.post('<%=basePath%>coreextend/extend/getSubmenu.action',{'funId':parentId,'currentId':currentId},function (temp){
		  		$(window.parent.frames['topFrame'].document.body).find("#links").html(temp); 
		  		var linkNum=0;
		  		$(window.parent.frames['topFrame'].document.body).find("#firstMenu").find("td").each(function(linkNum){
		  			linkNum++;
		  			var la=this.lang;
		  			if(la==parentId){
		  				$(this).attr("class","current");
		  			}else{
		  				$(this).attr("class","");
		  			}
		  		});
				
			},'text');*/
		}
		$(function(){
			/***********************
				* 函数：判断滚轮滚动方向
				* 作者：walkingp
				* 参数：event
				* 返回：滚轮方向 1：向上 -1：向下
				*************************/
				var scrollFunc=function(e){
				    var direct=0;
				    e=e || window.event;
				    if(e.wheelDelta){//IE/Opera/Chrome
				        direct=(parseInt(e.wheelDelta)*-1)/40;
				    }else if(e.detail){//Firefox
				        direct=e.detail;
				    }
				    var marginTop = $("#ul1").css("margin-top");
				    marginTop = parseInt(marginTop.substring(0,marginTop.indexOf("px")));
				    marginTop = marginTop - (direct*10);
				    if(marginTop>0){
				    	marginTop=0;
				    }
				    var min = $("#ul1")[0].offsetHeight > 98?(parseInt($("#ul1")[0].offsetHeight)-98)*-1:0;
				   if(marginTop<min){
				   		marginTop = min;
				   }
				    $("#ul1").css("margin-top",marginTop+"px"); 
				}
				/*注册事件*/
				if($(".eventlistbox")[0].addEventListener){
				    $(".eventlistbox")[0].addEventListener('DOMMouseScroll',scrollFunc,false);
				}//W3C
				$(".eventlistbox")[0].onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome/Safari
		})
		$(function() {
			$('.userinfotitle,.eventlisttitle,.lastcontroltitle').toggle(
				function() {
					$(this).addClass('close');
					$(this).next().hide();
				},
				function() {
					$(this).removeClass('close');
					$(this).next().show();
				}
			);
		});
	</script>
</head>
<body style="background:url(<%=basePath%>theme/${themeType }/sidebar_bg.png) repeat-y right top;">
    <div id="sound_element"></div>
    <div class="sidebar">
      <div class="userinfo">
          <div class="userinfotitle">
          	<s:text name="mainframe.left.logininfo" />
          </div>
          <div class="userinfobox">
            <p class="userdate">日期：${labUserVo.currentDate}</p>
            <p class="userunit">单位：${labUserVo.mainOrgName}</p>
            <p class="userjuse">岗位：${labUserVo.duty}</p>
            <p class="username">用户：${labUserVo.name}</p>
          </div>
      </div>
     <div class="eventlist">
		<div class="eventlisttitle">
			待办事项
		</div>
		<div class="eventlistbox" style="height: 80px;overflow:auto">
			<ul id="ul1" style="margin-top: 0px;">
   				<s:if test="#request.wFFunList!=null">
   					<s:iterator value="#request.wFFunList" status="st" id="i">
		           		<li><a onclick="selectCurrentMenu('${parentId }','${id}');" href="<%=basePath%>${url}" target="workarea">${name}(<span>${count}</span>)</a></li>
		         	</s:iterator>
   				</s:if>
         	</ul>
         </div>
      </div>
      <div class="eventlist">
         <div class="eventlisttitle">
         	<s:text name="mainframe.left.messagestips" />
         </div>
         <div class="eventlistbox_tit"> 
	         <ul>
	         <li>
	         <a  href="<%=basePath %>message/messageMain/preAddLabMsg.action" target="workarea">写&nbsp;&nbsp;消&nbsp;&nbsp;息</a>
	         </li>
	            <li><a href="<%=basePath %>message/messageMain/listLabMsg.action" target="workarea">
	                     收&nbsp;&nbsp;件&nbsp;&nbsp;箱(<span id="unreadmessage"><%=session.getAttribute("unreadcount")%></span>)</a>
	            </li>
	            <li><a href="<%=basePath%>message/messageMain/listLabMsg4CaoGao.action" target="workarea">
	                      草&nbsp;&nbsp;稿&nbsp;&nbsp;箱(<span id="caogaomessage"><%=session.getAttribute("caogaocount")%></span>)</a>
	            </li>
	            <li><a href="<%=basePath%>message/messageMain/listLabMsg4Sended.action" target="workarea">
	                发&nbsp;&nbsp;件&nbsp;&nbsp;箱(<span id="sendmessage"><%=session.getAttribute("sendmessage")%></span>)</a>
	            </li>
	             <li><a href="<%=basePath%>message/messageMain/listLabMsg4IsDelete.action" target="workarea">
	              垃&nbsp;&nbsp;圾&nbsp;&nbsp;箱(<span id="deletemessage"><%=session.getAttribute("deletemessage")%></span>)</a>
	            </li>  
	         </ul>
         </div>
      </div>
      <div class="lastcontrol">
         <div class="lastcontroltitle"><s:text name="mainframe.left.basicsettings" /></div>
         <div class="lastcontrolbox">
         <ul>
              <li>
              	<a href="<%=basePath%>user/labUser/preUpdateLabUser4Center.action?labUserVo.id=${labUserVo.id }" target="workarea">
              		<s:text name="mainframe.left.mpi"/></a>
              </li>
         </ul>
          <p class="">&nbsp;</p>
          </div>
      </div>
      
    </div>

</body>
</html>