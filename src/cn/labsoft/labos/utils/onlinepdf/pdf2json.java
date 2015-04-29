package cn.labsoft.labos.utils.onlinepdf;

import cn.labsoft.labos.framework.common.exception.GlobalException;



public class pdf2json extends common {

	/**
	* Method:render page as image
	*/
	public String convert(String pdfdoc, String jsondoc, String page)
	{
		try {
			String command = "";
		    if("true".equals(getConfig("splitmode", ""))){
			    command = getConfig("cmd.conversion.splitjsonfile");
			}else{
			    command = getConfig("cmd.conversion.jsonfile");
			}
			command = command.replace("{path.pdf}", separate(getConfig("path.pdf", "")));
			command = command.replace("{path.swf}", separate(getConfig("path.swf", "")));			
			command = command.replace("{pdffile}", pdfdoc);
			command = command.replace("{jsonfile}", jsondoc);

			if(exec(command)){
				return "[OK]";
			}else{
				return "[Error converting PDF to JSON, please check your directory permissions and configuration]";
			}
		} catch (Exception ex) {
			return "[" + ex.toString() + "]";
		}
	}
}