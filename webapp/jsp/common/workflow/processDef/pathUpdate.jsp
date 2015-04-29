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
    <script>
    	$(function(){
    		var idStr="";
    		<s:iterator value="labWfPathVo.ids" status="st" id="ids">
    		idStr+="${ids}";
    		</s:iterator>
    		if(idStr.length>0){
    			var ids =idStr.split(',');
	    		for(var i = 0; i <=ids.length-1; i++){
	    			if(i>0){
	    				addRow();
	    			}
	    			var _v = ids[i].split('*');
	    			for(var j = 0; j < _v.length; j++){
	    				var obj = $('#pathContent').children().eq(i);
	    				if(j == 0) {
	    					obj.find('select option').each(function(){
	    						var vv=$(this).html();
	    						if(vv==_v[0]){
	    							$(this).attr('selected','selected');
	    							var val=$(this).val();
		    						if(val.indexOf("|")>=0){
			    						var vx=val.split("|");
			    						obj.children('td').eq(0).find('input').val(vx[0]);
			    						obj.children('td').eq(2).find('input').val(vx[1]);
		    						}
	    						}
	    					});
	    				}
	    				else {
	    					obj.children('td').eq(j).children().val(_v[j]);
	    				}
	    			}
	    		}
    		}
    	});
    </script>
</head>
<body style="overflow-x:hidden;width: 95%;">
    <!-- id不能设置为nodeName否则会报错 错误与1777行 原因是跟jquery重名这也是ie直接获取id所导致的问题 Aaron-->
    <div id="nodeNameSet">
        <label>迁移名称：</label>
        <input type="text" name="labWfPathVo.name" id="pathName" value="${labWfPathVo.name}"/>
    </div>
    <div id="top">
		<label>变量：</label>
		<a href="javascript:;" onclick="addRow()">增加一行</a>
	</div>
	<table class="FormtableCon_line" style="margin:0px;width:100%;">
		<thead>
			<tr>
				<td>变量</td>
				<td width="80">符号</td>
				<td width="80">值</td>
				<td width="20"></td>
			</tr>
		</thead>
		<tbody id="pathContent">
			<tr>
				<td>
					<s:select list="#request.varList" listKey="name+'|'+value"  listValue="name" onchange="toValue(this);" name="variableId" id="variableId" headerKey="" headerValue="-请选择-" theme="simple" cssStyle="width:150px;"></s:select>
					<input size="15" type="hidden" value="" id=""/>
				</td>
				<td>
					<s:select list="#{'1':'=','2':'>','3':'>=','4':'<','5':'<=','6':'!='}"  theme="simple" name="operator" id="operator" cssStyle="width:50px;"></s:select>
				</td>
				<td>
					<input size="15" type="text" name="value" value="" id="value"/>
				</td>
				<td>
					
				</td>
			</tr>
		</tbody>
	</table>
	<div class="FUNCIONBARNEW">
        <table>
            <tr>
             	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
              		<table cellspacing="0" cellpadding="0" border="0">
               			<tr>
                  			<td>
                  				<a id="BtnPreview" class="zPushBtn" href="#" onclick="submit();"><img width="20" height="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="comf.agree"/></b></a>
                  			</td>
                  			<td>                                                  
                  				<a id="BtnPreview" class="zPushBtn" href="#" onclick="goback();"><img width="20" height="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b></a>
                  			</td>
                		</tr>
            		</table>
         		</td>
         	</tr>
         </table>
    </div>
	<script>
		function submit(){
			//节点id 
			var pathName=$('#pathName').val();
			var pathstr="";
			var flag="0";
			$('#pathContent').children().each(function(n){
				var tv=$(this).children().eq(0).find('input').val();
				var iv1=$(this).children().eq(1).find('select').val();
				var tvs=$(this).children().eq(2).find('input').val();
				if(tv.length>0&iv1.length>0){
					pathstr+=tv+"*"+iv1+"*"+tvs;
				}else{
					flag="1";
					return ;
				}
				pathstr+=",";
			});
			if(pathstr.length>1){
				pathstr=pathstr.substring(0,pathstr.length-1);
			}
			if(flag!="1"){
				parent.callBackLineFun(pathName,pathstr);
				//将数据反回去
				goback();
			}else{
				alert("数据未填写完整！");
			}
		}
		function goback(){
  			//关闭当前窗口
  			$('.x-tool-close', parent.document).click();
		}
		function addRow(){
			var _path = $('#pathContent');
			var _clone = _path.children().eq(0).clone();
			_clone.find('input').val('');
			_clone.children().eq(3).html('<a href="javascript:void(0);" onclick="deleteThis(this)"><img src="<%=basePath%>img/zhongzhi.gif" /></a>');
			_path.append(_clone);
		
		}
		function deleteThis(obj){
			$(obj).parent().parent().remove();
	    }
	    function toValue(obj){
	    	var vv=$(obj).val();
	    	var vs=vv.split('|');
	    	if(vs.length==2){
	    		$(obj).next().val(vs[0]);
	    		$(obj).parent().next().next().find('input').val(vs[1]);
	    	}
	    }
	</script>
</body>
</html>