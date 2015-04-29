package cn.labsoft.labos.utils.onlinepdf;



public class swfrender extends Config {

	public String renderPage(String pdfdoc, String swfdoc, String page)
	{
		String command = "";
		try {
			if("true".equals(getConfig("splitmode", ""))){
				command = getConfig("cmd.conversion.rendersplitpage", "");
			}else{
				command = getConfig("cmd.conversion.renderpage", "");
			}
			command = command.replace("{path.swf}", separate(getConfig("path.swf", "")));
			command = command.replace("{swffile}", swfdoc);
			command = command.replace("{pdffile}", pdfdoc);
			command = command.replace("{page}", page);
			if(exec(command)){
				return "[OK]";
			}else{
				return "[Error converting PDF to PNG, please check your configuration]";
			}
		} catch (Exception ex) {
			return ex.toString();
		}
	}
}