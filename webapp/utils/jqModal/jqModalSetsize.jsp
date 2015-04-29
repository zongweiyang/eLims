<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<script language="javascript" type="text/javascript" src="<%=basePath%>js/jquery-1.3.2.js"></script>
		<link rel="stylesheet" href="<%=basePath%>js/jquery-ui.css" type="text/css" media="all" />
		<script language="JavaScript" src="<%=basePath%>js/jquery-ui-1.7.3.custom.min.js"></script>
	<script type="text/javascript">
		$(function() {
		$("#dialog").dialog("destroy");
		$("#dialog-form").dialog({
				autoOpen: false,
				height: $("#height").val(),
				width: $("#width").val(),
				modal: true,
				buttons: {
					'关闭': function() {
						closeMe();
					}
				}
			});
	
			//$('#create-user').click(function() {
//				alert("work");
				//$('#dialog-form').dialog('open');
			//});
		});
	function showID(param,width,height){
		var my_iframe=document.getElementById('my_iframe');
		setSelectState('dialog-form','hidden');   
		//alert(id);
		my_iframe.src=param;
		my_iframe.width=width;
		my_iframe.height=height;
		$("#width").val(width+"px");
		$("#height").val(height+"px");
		showOpen();
	}
	function closeMe(){
		setSelectState('dialog-form','');
		$('#dialog-form').dialog('close');
	}
	// 设置select的可见状态   
	function setSelectState(divName,state) {   
	 var objl = document.getElementsByTagName('select');   
	 var objd = document.getElementById(divName).getElementsByTagName('select');   
	 for ( var i = 0; i < objl.length; i++) {   
	  objl[i].style.visibility = state;   
	 }   
	 for ( var i = 0; i < objd.length; i++) {   
	  objd[i].style.visibility = '';   
	 }   
	}  
	
	
	function showOpen(){
		$('#dialog-form').dialog('open');
	}
	
	</script>
<script type="text/javascript">
<!--
	$(function (){
		//showDialog();
		//showOpen();
	});
//-->
</script>
    <!-- JQ 模态层 start -->
<div id="dialog-form" style="">
<iframe name="frame_div" id="my_iframe" width="920px;" scrolling="no" frameborder="no" height="400px;" border="0″ style="padding:0;margin:0;" name="xxx">
						
</iframe>
<input type="hidden" id="width" />
<input type="hidden" id="height" />
</div>
<!-- JQ 模态层 end -->
