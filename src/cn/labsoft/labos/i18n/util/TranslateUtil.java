package cn.labsoft.labos.i18n.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class TranslateUtil {
	private static final String PATH = "/resources/i18n.properties";
	private static Properties pros =  new Properties();
	
	static {
		try {
			
			InputStream in = TranslateUtil.class.getResourceAsStream(PATH);
			if(in != null )
				pros.load(new InputStreamReader(in,"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static String get(String key){
		if(key == null)return null;
		return pros.getProperty(key.trim());
	}
}
