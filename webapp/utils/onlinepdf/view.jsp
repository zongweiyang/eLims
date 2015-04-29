<%@ page import="cn.labsoft.labos.utils.onlinepdf.*,java.io.*" %>
<%
	BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
	common conf 	= new common();
	String doc 		= request.getParameter("doc");
	String pdfdoc	= doc;
	String pages	= request.getParameter("page");
	String format	= request.getParameter("format");
	String resolution	= request.getParameter("resolution");
	String callback = request.getParameter("callback");

	if(doc == null)return;
	if(!doc.endsWith(".pdf"))	{pdfdoc 	= doc + ".pdf";}
	if(pages == null)			{pages = "";}
	if(format == null)			{format="swf";}
	String swfdoc	= pdfdoc + ".swf";
	if("true".equals(conf.getConfig("splitmode", "")))	{	swfdoc 	= pdfdoc + "_" + pages + ".swf";}
	if(callback == null)		{callback = "";}
	String jsondoc	= pdfdoc + ".js";
	if("true".equals(conf.getConfig("splitmode", "")))	{	jsondoc = pdfdoc + "_" + pages + ".js";	}

	String pngdoc 		= pdfdoc + "_" + pages + ".png";
	String jpgcachedoc = pdfdoc + "_" + pages + "_res_" + resolution + ".jpg";
	String messages 	= "";
	String swfFilePath 	= conf.separate(conf.getConfig("path.swf", "")) + swfdoc;
	String pdfFilePath 	= conf.separate(conf.getConfig("path.pdf", "")) + pdfdoc;
	String pngFilePath 	= conf.separate(conf.getConfig("path.swf", "")) + pngdoc;
	String jpgCachePath 	= conf.separate(conf.getConfig("path.swf", "")) + jpgcachedoc;
	String jsonFilePath 	= conf.separate(conf.getConfig("path.swf", "")) + jsondoc;

	boolean validatedConfig = true;
	String error = "";
	if(!conf.is_dir(conf.getConfig("path.swf", ""))){
		error = "Error:Cannot find SWF output directory, please check your configuration file.";
		validatedConfig = false;
	}

	if(!conf.is_dir(conf.getConfig("path.pdf", ""))){
		error += "Error:Cannot find PDF output directory, please check your configuration file.";
		validatedConfig = false;
	}

	if(!validatedConfig){
		error += "Error:Cannot read directories set up in configuration file, please check your configuration.";
	}else if(!conf.validParams(pdfFilePath, pdfdoc, pages)){
		error += "Error:Incorrect file specified, please check your path.";
	}else{
		if("swf".equals(format) || "jpg".equals(format) || "png".equals(format) || "pdf".equals(format)){
			if(!conf.file_exists(swfFilePath)){
				pdf2swf pdfconv = new pdf2swf(request);
				messages = pdfconv.convert(pdfdoc, pages);
			}
			if("png".equals(format)||"jpg".equals(format)){
				if(conf.validParams(swfFilePath, swfdoc, pages)){
					if(!conf.file_exists(pngFilePath)){
						swfrender pngconv = new swfrender();
						pngconv.renderPage(pdfdoc, swfdoc, pages);
					}

					if("true".equals(conf.getConfig("allowcache", ""))){
						conf.setCacheHeaders(response);
					}

					if(conf.getConfig("allowcache") == null || "false".equals(conf.getConfig("allowcache", "")) || ("true".equals(conf.getConfig("allowcache", "")) && conf.endOrRespond(request, response))){
						if(resolution != null){
							response.setContentType("image/jpeg");
							outs.write(conf.file_get_contents(conf.generateImage(pngFilePath, jpgCachePath, resolution, "jpg")));
						}else if("jpg".equals(format)){
						    response.setContentType("image/jpeg");
							outs.write(conf.file_get_contents(conf.generateImage(pngFilePath, jpgCachePath, null, "jpg")));
						}else{
							response.setContentType("image/png");
							outs.write(conf.file_get_contents(pngFilePath));
						}
					}
				}else{
					if(messages.length() == 0 || "[OK]".equals(messages))
						messages = "[Incorrect file specified, please check your path]";
				}
			} else 	if("pdf".equals(format)){
				response.setContentType("application/pdf");
				outs.write(conf.file_get_contents(pdfFilePath));
			}

			if(conf.file_exists(swfFilePath)){
				if("swf".equals(format)){
					if("true".equals(conf.getConfig("allowcache", ""))){
						conf.setCacheHeaders(response);
					}

					if(conf.getConfig("allowcache") == null || "false".equals(conf.getConfig("allowcache", "")) || ("true".equals(conf.getConfig("allowcache", "")) && conf.endOrRespond(request,response))){
						response.setContentType("application/x-shockwave-flash");
						response.setHeader("Accept-Ranges", "bytes");
						byte[] content = conf.file_get_contents(swfFilePath);
						response.setContentLength(content.length);
						outs.write(content);
					}
				}
			}else{
				if(messages.length() == 0)
					messages = "[Cannot find SWF file. Please check your JSP configuration]";
			}
		}else if("json".equals(format) || "jsonp".equals(format)){
			if(!conf.file_exists(jsonFilePath)){
				pdf2json jsonconv = new pdf2json();
				messages = jsonconv.convert(pdfdoc, jsondoc, pages);
			}
			if(conf.file_exists(jsonFilePath)){
				if("true".equals(conf.getConfig("allowcache", ""))){
					conf.setCacheHeaders(response);
				}

				if(conf.getConfig("allowcache") == null || "false".equals(conf.getConfig("allowcache", "")) || ("true".equals(conf.getConfig("allowcache", "")) && conf.endOrRespond(request,response))){
					response.setContentType("text/javascript");

					if("json".equals(format)){
						outs.write(conf.file_get_contents(jsonFilePath));
					}

					if("jsonp".equals(format)){
						outs.write((callback + "(").getBytes());
						outs.write(conf.file_get_contents(jsonFilePath));
						outs.write((");").getBytes());
					}
				}
			}else{
				if(messages.length() == 0)
					messages = "[Cannot find JSON file. Please check your JSP configuration]";
			}
		}

		if(messages.length() > 0 && !"[OK]".equals(messages) && !"[Converted]".equals(messages) && !"png".equals(format)){
			outs.write(("Error:" + messages.substring(1,messages.length()-2)).getBytes());
		}
	}
	if(error.length() > 0)
		outs.write(error.getBytes());
	outs.flush();
	outs.close();
	out.clear();
	out = pageContext.pushBody();
%>