package cn.labsoft.labos.utils.onlinepdf;



public class swfextract extends common {

	/**
	* Method:extractText
	*/
	public String findText(String doc, int page, String searchterm, int numPages) {
		if(searchterm.length()==0){return "[{\"page\":-1, \"position\":-1}]";}

		try {
			String pdfFilePath = separate(getConfig("path.pdf", "")) + doc;
			int pagecount = numPages;
			if(numPages == -1){
				pagecount = glob(getConfig("path.swf", ""),doc + "*").size();
			}

			if(	!validParams( pdfFilePath, doc, String.valueOf(page)))
				return null;

			String command = getConfig("cmd.searching.extracttext", "");
			command = command.replace("{swffile}", separate(getConfig("path.swf", "")) + doc + "_" + page + ".swf");

			String output = execs(command);
			int pos = -1;
			if(output != null)
				pos = output.toLowerCase().indexOf(searchterm.toLowerCase());
            if(pos > 0){
                return "[{\"page\":" + page + ", \"position\":" + pos + "}]";
            }else{
                if(page < pagecount){
                    page++;
                    return findText(doc, page, searchterm, pagecount);
                }else{
                    return "[{\"page\":-1, \"position\":-1}]";
                }
            }
		} catch (Exception ex) {
			return ex.toString();
			
		}
	}
}