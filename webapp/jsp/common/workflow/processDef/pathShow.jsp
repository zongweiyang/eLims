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
            border:1px solid #ccc;
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
    </style>
</head>
<body style="overflow-x:hidden;width: 95%;">
    <!-- id不能设置为nodeName否则会报错 错误与1777行 原因是跟jquery重名这也是ie直接获取id所导致的问题 Aaron-->
    <div id="nodeNameSet">
        <label>线路名称：</label>
        ${labWfPathVo.name}
    </div>
    <div id="top">
    <label>线路变量：</label>
	</div>
	<table class="FormtableCon_line" style="margin:0px;width:100%;">
		<thead>
			<tr>
				<td>变量</td>
				<td>符号</td>
				<td>值</td>
			</tr>
		</thead>
		<tbody id="pathContent">
			<s:set name="alllist" value="#request.varList" />
				<s:iterator value="#alllist" status="st">
					<tr>
						<td>
							${variableName}
						</td>
						<td>
							${operator}
						</td>
						<td>
							${value}
						</td>
					</tr>
				</s:iterator>
		</tbody>
	</table>
</body>
</html>