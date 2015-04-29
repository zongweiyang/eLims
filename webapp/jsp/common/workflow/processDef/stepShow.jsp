<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/jsp/include/common.jsp"%>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <style>
        html,body,ul,li,h5{
            margin:0px;padding:0px;background:#fff;
        }
        body{
            padding:5px 10px;
        }
         #top label{
            font-size:13px;font-weight:bold;
            color:#0A2958;
        }
        #nodeNameSet label{
            font-size:13px;font-weight:bold;
        }
        #nodeNameSet label,h5{
            color:#0A2958;
        }
        #nodeNameSet input{
            border:0px;border-bottom:2px dotted #333;text-align:center;
            width:120px;
        }
         #urlName label{
            font-size:13px;font-weight:bold;
        }
        #urlName label,h5{
            color:#0A2958;
        }
        #urlName input{
            border:0px;border-bottom:2px dotted #333;text-align:left;
            width:200px;
        }
        #top,#content{
            width:100%;overflow:hidden;margin-top:10px;
        }
        ul{
            list-style:none;
        }
        #top ul{
            margin-top:5px;
        }
        input{
            vertical-align: middle;font-family: "微软雅黑", "宋体"
        }
        label{
            font-size:12px;margin:0px;
        }
        #top ul li{
            float:left;margin-left:5px;margin-right:5px;
        }
        #content{
            border:1px solid #ccc;padding-bottom:5px;
            padding-top:3px;
        }
        #content ul li{
            padding-left:10px;
        }
        .shandow{
            position:absolute;
            background:url(${basePath}img/loadingAnimation.gif) no-repeat;
            width:32px;height:32px;
            left:50%;margin-left:-16px;
            margin-top:5px;display:none;z-index:1000;
        }
        .shandow-bg{
            position:absolute;background:#ccc;
            filter:alpha(opacity=60);-moz-opacity:0.6;opacity:0.6;
        }
        #userInfo div,#roleInfo div{
        	float:left;width:100px;margin:3px 0px 3px 10px;
        }
    </style>
</head>
<body style="overflow-x:hidden;width: 95%;">
    <!-- id不能设置为nodeName否则会报错 错误与1777行 原因是跟jquery重名这也是ie直接获取id所导致的问题 Aaron-->
    <div id="nodeNameSet">
        <label><s:text name="node.name"/>：</label>
        ${labWfStepVo.name}
    </div>
   <!--  <div id="nodeNameSet" style="margin-top:5px;">
        <label><s:text name="over.day"/>：</label>
        ${labWfStepVo.overDate}
    </div> -->
    <!-- <div id="urlName" style="margin-top:5px;">
        <label>url：</label>
        ${stepDefVo.url}
    </div> -->
    <!-- <div id="top">
		<label>操&nbsp;&nbsp;作&nbsp;&nbsp;者：</label>
			<s:if test="${labWfStepVo.operType=='user'}">
				人
			</s:if>
			<s:elseif test="${labWfStepVo.operType=='role'}">
				角色
			</s:elseif>
			<s:else>
			</s:else>
	</div>
	<div id="content">
		<s:if test="${labWfStepVo.operType=='user'}">
			<div id="userInfo" style=""> 
			    <s:set name="alllist" value="#request.userList" />
				<s:iterator value="#alllist" status="st">
					<div>
			            <label for="${id}">${username}</label>
		            </div>
				</s:iterator>
			</div>
		</s:if>
		<s:elseif test="${labWfStepVo.operType=='role'}">
			<div id="roleInfo"> 
			    <s:set name="alllist" value="#request.roleList" />
				<s:iterator value="#alllist" status="st">
					<div>
			            <label for="${id}">${rolename}</label>
		            </div>
				</s:iterator>
			</div>
		</s:elseif>
	</div> -->
</body>
</html>