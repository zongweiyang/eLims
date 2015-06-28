<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			var id='';
			function closeMe(){
			  	api.close();
		 	}
		 	function goBack(){
		 		var info=$("#info").val();
		 		var value="";
		 		var html='<select name="labQueryVo.listQuery['+id+'].value" id="select'+id+'">';
		 		if(info==''){
		 			alert('<s:property value="getText('plseexpingo')"/>');
		 		}else{
					$("input").each(function(){
						value+=$(this).val();
						value+=',';
					}); 
					html+='<option value="">'+'<s:property value="getText('selected.pls')"/>'+'</option>';
					if(value!=''&&value.length>2){
						for(var i=0;i<value.split(',').length;i=i+2){
							if(value.split(',')[i]!=''&&value.split(',')[i]!='undefined'){
								html+='<option value="'+value.split(',')[i+1]+'">'+value.split(',')[i]+'</option>';
							}
						}
						html+='</select>';
					}	
					html+="<script type=\"text/javascript\">$(\"#select"+id+"\").val('')<\/script>";
					$('#comboxValue'+id,D).val(html);
					$('#combox'+id,D).val(value);
					closeMe();
		 		}
		 	}
		 	$(function(){
		 		id=$('#comboBoxId',D).val();
	 			var valueD=$('#combox'+id,D).val();
	 			if(valueD!=''){
 					var html='';
					for(var i=0;i<valueD.split(',').length;i=i+2){
						if(valueD.split(',')[i]!=''&&valueD.split(',')[i]!='undefined'){
							html+='<tr>';
							html+='<td><label>显示值</label> </td>';
							html+='<td><input type="text" value="'+valueD.split(',')[i]+'" /></td>';
							html+='<td><label>传入值</label> </td>';
							html+='<td><input type="text" value="'+valueD.split(',')[i+1]+'" /></td>';
							html+='</tr>';
						}
					}
					$("table tr:gt(0)").remove();
					$("table").append(html);
	 			}
		 	});
		 	function addRow(){
				var row=$("table tr:last").clone();
				row.find("input").each(function(){
					$(this).val('');
				});
				$("table").append(row);
		 	}
		 	function deleteRow(){
		 		$("table tr:gt(1):last").remove();
		 	}
		 	function showSelect(){
		 		$("table tr").remove();
		 		var html='<tr><td colspan="4"><l:a uri="query/labQuery/listLabQuery.action" onclick="showSelectList();" value="page.confirm" /></td>';
		 			html+='<tr><td><label>ajax路径</label></td><td colspan="3"><input type="text" id="selectId"/></td></tr>';
		 		$("table").append(html);
		 	}
		 	function showSelectList(){
		 		var url=$('#selectId').val();
		 		if(url==null||url==''){
		 			validate.tip('请输入ajax地址',$('#selectId'));
		 			return false;
		 		}
		 		$.ajax({
					url:'<%=basePath%>'+url,
					type:'post',
					dataType:'json',
					success:function (data){
						var html='<tr><td colspan="4"><l:a uri="query/labQuery/listLabQuery.action" onclick="goBack();" value="page.confirm" /><l:a uri="query/labQuery/addLabQuery.action" onclick="addRow();" value="added" /><l:a uri="query/labQuery/deleteLabQuery.action" onclick="deleteRow();" value="lab.code.del" /><l:a uri="query/labQuery/list.action" onclick="showSelect();" value="autogets" /></td></tr>';	
						$.each(data, function(){   
						    html+='<tr>';
							html+='<td><label>显示值</label> </td>';
							html+='<td><input type="text" value="'+this.value+'" /></td>';
							html+='<td><label>传入值</label> </td>';
							html+='<td><input type="text" value="'+this.name+'" /></td>';
							html+='</tr>';   
						 });
						 $("table tr").remove();
						 $("table").append(html);
				   	  }
				  });
		 	}
		</script>

		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			
			html {
				_overflow-x: hidden;
			}
			.Formtable{
				width:95%
			}
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 98%">
		<div class="Formtable">
			<div class="Formtabletitle">
				<span><s:text name="expresion"/></span>
			</div>
			<table class="FormtableCon">
				<tr>
					<td colspan="4">
						<l:a uri="query/labQuery/listLabQuery.action" onclick="goBack();" value="page.confirm" />
						<l:a uri="query/labQuery/addLabQuery.action" onclick="addRow();" value="added" />
						<l:a uri="query/labQuery/deleteLabQuery.action" onclick="deleteRow();" value="lab.code.del" />
						<l:a uri="query/labQuery/list.action" onclick="showSelect();" value="autogets" />
					</td>
				</tr>
				<tr>
					<td>
						<label><s:text name="show.value"/></label>
					</td>
					<td>
						<input type="text"/>
					</td>
					<td>
						<label><s:text name="inputvalue"/></label>
					</td>
					<td>
						<input type="text"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
