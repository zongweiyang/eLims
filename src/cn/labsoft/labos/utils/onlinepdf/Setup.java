package cn.labsoft.labos.utils.onlinepdf;

import java.util.ArrayList;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class Setup extends common{

	public String table_data = "", warning_msg = "", fatal_msg = "";
	public int fatals = 0, warnings = 0;

	public void setTable_data(String data){
		table_data = data;
	}

	public String getTable_data(){
		return table_data;
	}

	public void setWarning_msg(String data){
		warning_msg = data;
	}

	public String getWarning_msg(){
		return warning_msg;
	}

	public void setFatal_msg(String data){
		fatal_msg = data;
	}

	public String getFatal_msg(){
		return fatal_msg;
	}

	public void setFatals(int data){
		fatals = data;
	}

	public int getFatals(){
		return fatals;
	}

	public void setWarnings(int data){
		warnings = data;
	}

	public int getWarnings(){
		return warnings;
	}

	public boolean pdf2swfEnabled(String path_to_pdf2swf) throws GlobalException{
		if ("/".equals(DIRECTORY_SEPARATOR) || ("\\".equals(DIRECTORY_SEPARATOR) && !"pdf2swf".equals(path_to_pdf2swf))) {
			boolean out = false;
			if(isWin()){
				out = exec('"' + path_to_pdf2swf + '"' + " --version 2>&1");
			}else{	
				out = exec(path_to_pdf2swf + " --version 2>&1");
			}
			if (out) {
				return true;
			}
		}
		return false;
	}

	public boolean pdf2jsonEnabled(String path_to_pdf2json) throws GlobalException{
		if ("/".equals(DIRECTORY_SEPARATOR) || ("\\".equals(DIRECTORY_SEPARATOR) && !"pdf2json".equals(path_to_pdf2json))) {
			boolean result = false;
			if(isWin()){
				result = exec('"' + path_to_pdf2json + '"' + " -help 2>&1");
			}else{
				result = exec(path_to_pdf2json + " -help 2>&1");
			}
			if (result) {
					return true;
			}
		}
		return false;
	}

	public void exec_tests(ArrayList<JArray> tests) {
		for(int i = 0; i < tests.size(); i++){
			JArray sub = tests.get(i);
			String tr = "<tr><td class=\"title\">" + sub.get("desc") + "</td>";
			if("true".equals(sub.get("test"))){
				if(sub.get("msg") == null)
					sub.add("msg", "Yes");
				tr += "<td class=\"pass\">" + sub.get("msg") + "</td>";
			} else {
				if(sub.get("msg") == null){
					sub.set("nomsg", "No");
				}
				tr += "<td class=\"fail\">" + sub.get("nomsg","") + "</td>";
				fatals ++;
				fatal_msg += "<li>" + sub.get("failmsg") + "</li>";
			}
			tr += "</tr>";
			table_data += tr;
		}
	}

	public static void main(String args[]) {
    }
}
