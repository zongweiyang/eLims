<%@ page import="java.io.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
try{

	String hostPath = request.getContextPath();
	
	{
		InputStream is = null;
		OutputStream os = null;
		java.io.File file = null;
		try {
			String filename = new String(request.getParameter("fileName").toString());
			String url = new String(request.getParameter("fileUrl").toString());
			url=request.getRealPath(url);
			file = new File(url);
			if (file != null && file.exists() && file.isFile()) {
				long filelength = file.length();
				is = new FileInputStream(file);
				os = response.getOutputStream();
				//设置输出的格式
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setContentLength((int) filelength);
				response.addHeader("Content-Disposition",
						"attachment; filename=\""
						+ new String(filename
						.getBytes("GB2312"),
						"iso8859-1") + "\"");
				//循环取出流中的数据
				byte[] b = new byte[4096];
				int len;
				while ((len = is.read(b)) > 0) {
					os.write(b, 0, len);
				}
			} else {
				out.println("<script>");
				out.println(" alert('The file is not exist!');");
				out.println(" history.go(-1);");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}
}catch(Exception e){
	e.printStackTrace();
}
%>
