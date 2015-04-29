<%String doc = request.getParameter("doc");
String callback = request.getParameter("callback");
if(callback == null){callback = "";}
cn.labsoft.labos.utils.onlinepdf.swfsizequery ss = new cn.labsoft.labos.utils.onlinepdf.swfsizequery();
response.setContentType("application/json");
String outs = "({\"height\":" + ss.getSize(doc,request.getParameter("page"),"height");
outs += ",\"width\":" + ss.getSize(doc,request.getParameter("page"),"width");
%>
<%=callback + outs + ")}" %>