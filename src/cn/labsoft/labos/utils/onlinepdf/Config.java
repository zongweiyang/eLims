package cn.labsoft.labos.utils.onlinepdf;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

import com.sun.org.apache.regexp.internal.RE;

public class Config {
	protected static JArray config = null;
	protected static String ROOT = null;
	protected static String OS = System.getProperty("os.name");
	protected static String DIRECTORY_SEPARATOR = null;
	protected static int len = 0;
	protected static String doc_url = "";
	public Config() {
		if(ROOT==null){
			String path = Config.class.getResource("/").getPath();
			path = path.substring(1,path.length());
			ROOT = path;
		}
		if(DIRECTORY_SEPARATOR == null)
			DIRECTORY_SEPARATOR	= System.getProperty("file.separator");
		if(isChange()) {
			config = parse_ini_file(getConfigFilename());
		}
	}

	public boolean isChange(){
		File f = new File(getConfigFilename());
		if(!f.isFile() || !f.canRead()){
			config = null;
			return false;
		}
		if(f.length() == len)
			return false;
		len = (int) f.length();
		return true;
	}

	public boolean isWin(){
		if(OS.contains("Win"))
			return true;
		return false;
	}

	public String getConfig(String key) {
		key = key.trim();
		if(key == null || key.length() <= 0 || config == null)
			return null;
		return config.get(key);
    }

	public String getConfig(String key, String def) {
		key = key.trim();
		if(key == null || key.length() <= 0 || config == null)
			return def;
		return config.get(key, def);
    }

	public JArray getConfigs(){
		if(config == null)return null;
		return (JArray) config.clone();
	}

	public String getDocUrl(){
		return "<br/><br/>Click <a href='" + doc_url + "'>here</a> for more information on configuring FlexPaper with JSP";
	}

	public String getConfigFilename(){
		String configPath = ROOT + DIRECTORY_SEPARATOR;
		File f = new File(configPath);
		if(!f.isDirectory())
			f.mkdirs();
		String path=configPath +File.separator+"resources"+File.separator+ "onlinepdfconfig.ini";
		if(!isWin()){
			path="/"+path;
		}
		return path;
	}

	public boolean saveConfig(JArray ht) throws GlobalException{
		if(!write_ini(ht, getConfigFilename()))
			return false;
		config = ht;
		return true;
	}

	public int is_writable(String dir){
		File f = new File(dir.trim());
		if(!f.isDirectory() || !f.canWrite())
			return 0;
		return 1;
	}

	public void mkdir(String dir){
		dir = dir.trim();
		File f = new File(dir);
		if(!f.isDirectory())
			f.mkdirs();
	}
	public boolean write_ini(JArray ht, String fname) throws GlobalException {
		BufferedWriter bufferedWriter = null;
		FileWriter f = null;
		int tab = 30;
        try {
        	f = new FileWriter(fname);
            bufferedWriter = new BufferedWriter(f);
            for(int i = 0; i < ht.len(); i++){
            	String index = ht.getIndex(i);
            	bufferedWriter.write(index);
            	String data = ht.get(i).trim();
            	if(data.length() > 0){
            		for(int j = 0; j < tab - index.length(); j++)
            			bufferedWriter.write(" ");
        			bufferedWriter.write("= ");
	            	bufferedWriter.write(data);
            	}
            	bufferedWriter.write("\r\n");
            }
        	bufferedWriter.close();
        	f.close();
		} catch (Exception e) {
			try {
				if(f != null)
					f.close();
				if(bufferedWriter != null)
					bufferedWriter.close();
				throw new GlobalException("" + e.getMessage());
			} catch (IOException e1) {
				throw new GlobalException("" + e1.getMessage());
			}
		}
        return true;
	}

	public JArray newConfig(String fname) throws GlobalException{
		JArray con = new JArray();
		con.add("[admin]",				"");
		con.add("username",				"ok");
		con.add("password",				"ok");
		con.add("[requirements]",		"");
		con.add("test_pdf2swf",			"true");
		con.add("test_pdf2json",		"true");
		con.add("licensekey",			"");
		con.add("[general]",			"");
		con.add("allowcache",			"true");
		con.add("splitmode",			"false");
		con.add("path.pdf",				"C:\\pdf\\");
		con.add("path.swf",				"C:\\docs\\");
		con.add("renderingorder.primary", 			"flash");
		con.add("renderingorder.secondary", 		"html");
		con.add("[external commands]", 				"");
		con.add("cmd.conversion.singledoc", 		"\"pdf2swf.exe\" \"{path.pdf}{pdffile}\" -o \"{path.swf}{pdffile}.swf\" -f -T 9 -t -s storeallcharacters -s linknameurl");
		con.add("cmd.conversion.splitpages", 		"\"pdf2swf.exe\" \"{path.pdf}{pdffile}\" -o \"{path.swf}{pdffile}_%.swf\" -f -T 9 -t -s storeallcharacters -s linknameurl");
		con.add("cmd.conversion.renderpage", 		"\"swfrender.exe\" \"{path.swf}{swffile}\" -p {page} -o \"{path.swf}{pdffile}_{page}.png\" -X 1024 -s keepaspectratio");
		con.add("cmd.conversion.rendersplitpage", 	"\"swfrender.exe\" \"{path.swf}{swffile}\" -o \"{path.swf}{pdffile}_{page}.png\" -X 1024 -s keepaspectratio");
		con.add("cmd.conversion.jsonfile", 			"\"pdf2json.exe\" \"{path.pdf}{pdffile}\" -enc UTF-8 -compress \"{path.swf}{pdffile}.js\"");
		con.add("cmd.conversion.splitjsonfile", 	"\"pdf2json.exe\" \"{path.pdf}{pdffile}\" -enc UTF-8 -compress -split 10 \"{path.swf}{pdffile}_%.js\"");
		con.add("cmd.searching.extracttext", 		"\"swfstrings.exe\" \"{swffile}\"");
		con.add("cmd.query.swfwidth", 				"\"swfdump.exe\" \"{swffile}\" -X");
		con.add("cmd.query.swfheight", 				"\"swfdump.exe\" \"{swffile}\" -Y");
		if(!write_ini(con, fname))
			return null;
		return con;
	}

	public JArray parse_ini_file(String fname){
		File f = new File(fname);
		if(!f.isFile() || !f.canRead())
			return null;
		JArray ret = new JArray();
		try {
			FileInputStream fstream = new FileInputStream(fname);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] ri = strLine.split("=");
				String data = ri[0].trim();
				String value = null;
				if(ri.length > 1) {
					value = ri[1].trim();
				}
				ret.add(data, value);
			}
			in.close();
			fstream.close();
		} catch (Exception e) {
			Log4J.error("", e);
		}
		return ret;
	}

	public boolean exec(String execString) throws GlobalException {
		boolean result = true;
		try {
			System.out.println("Executing: " + execString);

			ArrayList commands = commandLineAsList(execString);

			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			BufferedReader is = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = is.readLine()) != null) {
				if (line.toLowerCase().startsWith("warning")) {
					System.err.println("\tWARNING: " + line);
				} else if (line.toLowerCase().startsWith("error")) {
					System.err.println("\tERROR: " + line);
					result = false;
				} else if (line.toLowerCase().startsWith("fatal")) {
					System.err.println("\tFATAL ERROR: " + line);
					result = false;
				} else {
					System.out.println("\t" + line);
				}
			}
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				throw new GlobalException("" + e.getMessage());
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return result;
	}

	public String separate(String path){
		if(isWin())
			return (path.trim() + "\\").replace("\\\\", "\\");
		else
			return (path.trim() + "/").replace("//", "/");
	}

	public String execs(String execString) {
		String ret = "";
		try {
			ArrayList commands = commandLineAsList(execString);

			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.redirectErrorStream(true);
			Process p = pb.start();

			BufferedReader is = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = is.readLine()) != null) {
				ret += "	" + line;
			}
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				return null;
			}
		} catch (IOException e) {
			//e.printStackTrace();
			return null;
		}
		return ret;
	}
	
	public static ArrayList commandLineAsList(String commandLine) {
		ArrayList commands = new ArrayList();
		String elt = "";
		boolean insideString = false;

		for (int i = 0; i < commandLine.length(); i++) {
			char c = commandLine.charAt(i);

			if (!insideString && (c == ' ' || c == '\t')) {
				if (elt.length() > 0) {
					commands.add(elt);
					elt = "";
				}
				continue;
			} else if (c == '"') {
				insideString = !insideString;
			}

			elt += c;
		}
		if (elt.length() > 0) {
			commands.add(elt);
		}

		return commands;
	}

	public void DeleteFiles(String dir){
		dir = separate(dir);
		File file = new File(dir);
		if(!file.isDirectory())
			return;
		String[] files = file.list();
		for (int i=0; i < files.length; i++){
			File f = new File(dir + files[i]);
			f.delete();
		}
	}

	public ArrayList<String> DirectoryFiles(String dir){
		ArrayList<String> arr = new ArrayList<String>();
		dir = separate(dir);
		File d = new File(dir);
		if(!d.isDirectory() && !d.mkdirs()){
			return null;
		}
		String[] files = d.list();
		for (int i=0; i < files.length; i++){
			File f = new File(dir + files[i]);
			if(f.isFile()){
				arr.add(files[i]);
			}
		}
		return arr;
	}

	public String getSizeString(long size){
		String ret = " byte";
		if(size > 1023){
			ret = " KB";
			size = size / 1024;
			if(size > 1023){
				ret = " MB";
				size = size / 1024;
				if(size > 1023){
					size = size / 1024;
					ret = " GB";
				}
			}
		}
		return size + ret;
	}

	public long FileSize(String dir, String file){
		dir = separate(dir);
		File f = new File(dir + file);
		if(!f.isFile())
			return 0;
		return f.length();
	}

	public long FileSize(String file){
		File f = new File(file);
		if(!f.isFile())
			return 0;
		return f.length();
	}

	public void DeleteFiles(String[] files, String path){
		path = separate(path);
		for (int i = 0; i < files.length; i++) {
			File f = new File(path + files[i]);
			if(f.exists()){
				f.delete();
			}
		}
	}

	public void setConfig(String key, String data){
		if(key == null || key.trim().length() == 0)
			return;
		config.set(key, data);
	}

	public boolean is_dir(String dir) {
		if(dir == null || dir.length() == 0)
			return false;
		File f = new File(dir);
		if(f.isDirectory())
			return true;
		return false;
	}

	public boolean file_exists(String path) {
		if(path == null || path.length() == 0)
			return false;
		File f = new File(path);
		if(f.exists())
			return true;
		return false;
	}

	public byte[] file_get_contents(String file) throws GlobalException {
		byte[] con = {0};
		if(file == null || file == "")
			return con;
		try {
			File f = new File(file);
			if(!f.isFile() || !f.canRead())
				return con;
			FileInputStream fstream = new FileInputStream(file);
			con = new byte[(int) f.length()];
			fstream.read(con);
			fstream.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return con;
	}

	public String file_get_content(String file) throws GlobalException {
		String con = "";
		if(file == null || file == "")
			return null;
		try {
			File f = new File(file);
			if(!f.isFile() || !f.canRead())
				return null;
			FileInputStream fstream = new FileInputStream(file);
			byte[] cont = new byte[(int) f.length()];
			fstream.read(cont);
			fstream.close();
			con = new String(cont,"iso8859-1");
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return con;
	}

	public boolean file_write_contents(String file, String content) throws GlobalException {
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			byte[] cont = content.getBytes("iso8859-1");
			fstream.write(cont);
			fstream.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	public int getTotalPage(String file) throws GlobalException{
		String content = "";
		try {
			content = new String(file_get_contents(file), "iso8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			throw new GlobalException("" + e1.getMessage());
		}
		if(content.length() == 0)
			return 0;
		int page = 0;
		try {
			String pat_one = "/N\\s+([0-9]+)";
			Pattern pattern = Pattern.compile(pat_one);
			Matcher match = pattern.matcher(content);
			if(match.find()){
				String count = match.group(1).trim();
				try{
					page = Integer.parseInt(count);
				}catch(Exception e){
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}

			String pat_two = "/Count\\s+([0-9]+)";
			Pattern pattern2 = Pattern.compile(pat_two);
			Matcher match2 = pattern2.matcher(content);
			while(match2.find()){
				String count = match2.group(1).trim();
				try{
					int kk = Integer.parseInt(count);
					if(kk > page)
						page = kk;
				}catch(Exception e){
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return page;
	}

	public ArrayList<String> glob(String dir, String pattern){
		dir = separate(dir);
		ArrayList<String> ret = new ArrayList<String>();
		File d = new File(dir);
		if(!d.isDirectory())
			return ret;
		String[] files = d.list();
		pattern = pattern.replace(" ", "*");
		pattern = pattern.replace(".", "[.]");
		pattern = pattern.replace("*", "(.*)");
		Pattern r = Pattern.compile(pattern);
		for(int i = 0; i < files.length; i++){
			Matcher m = r.matcher(files[i]);
			if (m.matches( )) {
				ret.add(dir + files[i]);
			}
		}
		return ret;
	}

	public String strip_non_numerics(String string) {
		echo ("***Config 	strip_non_numerices  string = " + string);
		String pattern = "[\\D]";
		RE r = new RE(pattern);
		string = r.subst(string, "");
		return string;
	}

	public void echo(Object echo){
		System.out.println(Config.class.getResource("/").getPath());
	}

	public static void main(String[] args) {
		String path = Config.class.getResource("/").getPath();
		path = path.substring(1,path.length());
		System.out.println(path);
		//user.dir=C:\Program Files\Apache Software Foundation\Tomcat 7.0,
		//java.runtime.version=1.7.0_11-b21,
		//os.name=Windows XP,
		//user.name=SYSTEM,
		//file.separator=\,
		//sun.desktop=windows
	}
}