package cn.labsoft.labos.utils.onlinepdf;



public class swfsizequery extends common {

	/**
	* Method:search
	*/
	public String getSize(String doc, String page, String mode)
	{
		String ret = "";

		try {
			if(page == null)
				page = "";
			String swfdoc = doc + ".swf";
			String command = "";
			if("true".equals(getConfig("splitmode", ""))){
				swfdoc 	= doc + "_" + page + ".swf";
			}
			String swfFilePath = separate(getConfig("path.swf", "")) + swfdoc;
			
			if(	!validParams(swfFilePath, swfdoc, page) )
				return "";

			if("width".equals(mode)){
				command = getConfig("cmd.query.swfwidth", "");
			}
			
			if("height".equals(mode)){
				command = getConfig("cmd.query.swfheight", "");			
			}
			
			command = command.replace("{path.swf}", separate(getConfig("path.swf", "")));
			command = command.replace("{swffile}", swfFilePath);

			ret = execs(command);
			if(ret != null){
				return strip_non_numerics(ret);
			}else{
				return "[Error Extracting]";
			}
		} catch (Exception ex) {
			return ex.toString();
		}
	}
}