<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
	 <div align="center">
	  <center>
	  <table border="0">
	    <tr>
	    	<td align="center">
			<textarea rows="16" cols="54"  name="textContentStrs"  id="textContentStrs" style="color:#333;font-size:12px;padding:5px;" ></textarea>
			</td>
	    </tr>
	    <tr>
	    	<td align="center">
			<input  type="button" value="确定"name="确定" onclick="submitThis();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input  type="button" value="关闭"name="关闭" onclick="closeMe();"/>
			</td>
	    </tr>
	  </table>
	  </center>
	</div>
	<script type="text/javascript">
	var	contentStr = document.getElementById("textContentStrs");
	contentStr.value=parent.document.getElementById('<%=request.getParameter("textCotentName") %>').value;
	</script>
	<script type="text/javascript">
		function submitThis()
		{   
			var	contentStr = document.getElementById("textContentStrs");
			parent.document.getElementById('<%=request.getParameter("textCotentName") %>').value=contentStr.value;
			self.parent.tb_remove();
		}
		function closeMe()
		{
			self.parent.tb_remove();
		}
	</script>
</body>
</html>
