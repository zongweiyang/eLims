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
            width:150px;
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
        #userInfo div{
        	float:left;width:90px;margin:3px 0px 3px 0px;
        }
        #roleInfo div{
        	float:left;width:140px;margin:3px 0px 3px 0px;
        }
    </style>
    <script>
    	$(function(){
    		var ids = "${labWfStepVo.ids}".split(',');
    		var type = "${labWfStepVo.operType}";
    		if(type=="user"){
    			$('#userCheck').attr('checked','checked');
    			for(var i = 0; i <=ids.length-1; i++){
    				$('#userInfo input#'+ids[i]).attr('checked','checked');
    			}
			}else if(type=="role"){
				$('#roleCheck').attr('checked','checked');
				$('#userInfo').hide();
				$('#roleInfo').show();
				for(var i = 0; i <=ids.length-1; i++){
    				$('#roleInfo input#'+ids[i]).attr('checked','checked');
    			}
			}
    	});
    </script>
</head>
<body style="overflow-x:hidden;width: 95%;">
    <!-- id不能设置为nodeName否则会报错 错误与1777行 原因是跟jquery重名这也是ie直接获取id所导致的问题 Aaron-->
    <div id="nodeNameSet">
        <label><s:text name="node.name"/>：</label>
        <input type="text" name="labWfStepVo.name" id="stepName" value="${labWfStepVo.name}"/>
    </div>
    <div id="nodeNameSet" style="margin-top:5px;">
        <label><s:text name="over.day"/>：</label>
        <input type="text" name="labWfStepVo.overDate" id="overDate" value="${labWfStepVo.overDate==''?7:labWfStepVo.overDate}" onchange="checkNum(this);"/>
    </div>
    <!-- <div id="urlName" style="margin-top:5px;">
        <label>url：</label>
        <input type="text" name="labWfStepVo.url" id="url" value="${labWfStepVo.url}"/>
    </div> -->
    <div id="top">
		<label><s:text name="ops.man"/>：</label>
	        <input type="radio" id="userCheck" name="operatorType" value="user" onclick="checkType(this);" checked="checked"></input>
	        <label for="userCheck">人</label>
            <input type="radio" id="roleCheck" name="operatorType"  value="role" onclick="checkType(this);"></input>
            <label for="roleCheck"><s:text name="theme.role"/></label>
            <script>
            	function checkType(obj){
            		var vv=$(obj).val();
            		if(vv=='user'){
            			$('#userInfo').removeAttr('style');
            			$('#roleInfo').attr('style','display:none');
            		}else if(vv=='role'){
            			$('#roleInfo').removeAttr('style');
            			$('#userInfo').attr('style','display:none');
            		}
            	}
            	function checkNum(obj){
            		var vv=$(obj).val();
            		var reg = /^\d{1,3}$/;
            		if(!reg.test(vv)){
            			alert("请输入3位以内正整数！");
            			$(obj).val("");
            		}
            	}
            </script>
	</div>
	<div id="content">
	    <!-- roleList userList-->
	   <div id="userInfo" style=""> 
		    <s:set name="alllist" value="#request.userList" />
			<s:iterator value="#alllist" status="st">
				<div>
					<input id="${id}" type="checkbox" value="${id}"/>
		            <label for="${id}">${name}</label>
	            </div>
			</s:iterator>
		</div>
		<div id="roleInfo" style="display:none;"> 
		    <s:set name="alllist" value="#request.roleList" />
			<s:iterator value="#alllist" status="st">
				<div>
					<input type="checkbox" id="${id}" value="${id}"/>
		            <label for="${id}">${name}</label>
	            </div>
			</s:iterator>
		</div>
	</div>
	<div class="FUNCIONBARNEW">
        <table>
            <tr>
             	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
              		<table cellspacing="0" cellpadding="0" border="0">
               			<tr>
                  			<td>
                  				<a id="BtnPreview" class="zPushBtn" href="#" onclick="submit();"><img width="20" height="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="comf.agree"/></b></a>
                  			</td>
                		</tr>
            		</table>
         		</td>
         	</tr>
         </table>
    </div>
	<script>
		function submit(){
			//获取相应list里的选中的值和修改后的名称及超期天数，返回给xml里
			//操作类型
			var type=$('input[name="operatorType"]:checked').val();
			//赋权ids
			var ids="";
			if(type=="user"){
				$('#userInfo input:checked').each(function(n){
					ids+=$(this).val()+",";
				});
			}else if(type=="role"){
				$('#roleInfo input:checked').each(function(n){
					ids+=$(this).val()+",";
				});
			}else{
				alert("选中对象出错！");
			}
			//过期天数
			var overDate=$('#overDate').val();
			//var url=$('#url').val();
			var url="";
			//节点id 
			var stepId=$('#stepId').val();
			var stepName=$('#stepName').val();
			
			//<OBJEXTEND OBJSTATUS="过期天数" OBJTEMPLATE="赋权给" VIEWSTYPE="节点" VIEWSSTR="" LOGO="" TAG=""/>
			parent.callBackNodeFun(stepName,overDate,type,ids,url);
			goback();
		}
		function goback(){
  			//关闭当前窗口
  			$('.x-tool-close', parent.document).click();
		}	
	</script>
</body>
</html>