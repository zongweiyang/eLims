<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>LabOS-<s:text name="login.labossystem" />
		</title>
		<script type="text/javascript">
		
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
	</script>
	</head>
	<body
		style="background: url(<%=basePath%>img/sidebar_bg.png) repeat-y right top;">
		<div id="sound_element"></div>
		<div class="sidebar">
			<div class="userinfo">
				<div class="userinfotitle">
					<s:text name="mainframe.left.logininfo" />
				</div>
				<div class="userinfobox">
					<p class="userdate">
						<s:text name="theme.date"></s:text>：${labUserVo.currentDate}
					</p>
					<p class="userunit">
						<s:text name="theme.depart"></s:text>：${labUserVo.orgName}
					</p>
					<p class="userjuse">
						<s:text name="theme.duty"/>：${labUserVo.duty}
					</p>
					<p class="username">
						<s:text name="user"></s:text>：${labUserVo.name}
					</p>

				</div>
			</div>
			<div class="eventlist">
				<div class="eventlisttitle">
					<s:text name="theme.todo"></s:text>
				</div>
				<div class="eventlistbox" style="height: 80px;overflow:auto">
					<ul id="ul1" style="margin-top: 0px;">
						<s:if test="#request.wFFunList!=null">
							<s:iterator value="#request.wFFunList" status="st" id="i">
								<li>
									<a onclick="selectCurrentMenu('${parentId }','${id}');"
										href="<%=basePath%>${url}" target="workarea">${name}(<span>${count}</span>)</a>
								</li>
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
							<a href="<%=basePath %>message/messageMain/preAddLabMsg.action"
								target="workarea"><s:text name="theme.write.msg"></s:text></a>
						</li>
						<li>
							<a
								href="<%=basePath %>message/messageMain/listLabMsg4Recive.action"
								target="workarea"> <s:text name="theme.receive.box"></s:text>
								<s:if test="${unreadcount>'0'}">
									(<span id="unreadmessage">${unreadcount}</span>)
								</s:if>
							</a>
						</li>
						<li>
							<a
								href="<%=basePath%>message/messageMain/listLabMsg4CaoGao.action"
								target="workarea"> <s:text name="theme.draft.box"></s:text>
								<s:if test="${caogaocount>'0'}">
									(<span id="caogaomessage">${caogaocount}</span>)
								</s:if>
							</a>
						</li>
						<li>
							<a
								href="<%=basePath%>message/messageMain/listLabMsg4Sended.action"
								target="workarea"> <s:text name="theme.send.box"></s:text>
								<s:if test="${sendmessage>'0'}">
									(<span id="sendmessage">${sendmessage}</span>)
								</s:if>
							</a>
						</li>
						<li>
							<a
								href="<%=basePath%>message/messageMain/listLabMsg4IsDelete.action"
								target="workarea"> <s:text name="theme.junk.box"></s:text>
								<s:if test="${deletemessage>'0'}">
									(<span id="deletemessage">${deletemessage}</span>)
								</s:if>
								</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="lastcontrol">
				<div class="lastcontroltitle">
					<s:text name="mainframe.left.basicsettings" />
				</div>
				<div class="lastcontrolbox">
					<ul>
						<li>
							<a
								href="<%=basePath%>user/labUser/preUpdateLabUser4Center.action?labUserVo.id=${labUserVo.id }"
								target="workarea"> <s:text name="mainframe.left.mpi" />
							</a>
						</li>
					</ul>
					<p class="">
						&nbsp;
					</p>
				</div>
			</div>

		</div>

	</body>
</html>