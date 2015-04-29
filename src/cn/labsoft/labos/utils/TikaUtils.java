package cn.labsoft.labos.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import cn.labsoft.labos.framework.common.exception.GlobalException;


public class TikaUtils {
	public static String parseFile(File file) throws GlobalException {
		Parser parser = new AutoDetectParser(); //自动检测文档类型，自动创建相应的解析器
		InputStream input = null;
		if (input != null){
			try {
				input.close();
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		try {
			Metadata metadata = new Metadata();
			metadata.set(Metadata.CONTENT_ENCODING, "utf-8"); //重新设置文档的媒体内容  
			metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
			input = new FileInputStream(file);
			ContentHandler handler = new BodyContentHandler(1024*1024*10);//当文件大于100000时，new BodyContentHandler(1024*1024*10);   
			ParseContext context = new ParseContext();
			context.set(Parser.class, parser);
			parser.parse(input, handler, metadata, context);
			//            for(String name:metadata.names()) {  
			//                System.out.println(name+":"+metadata.get(name));  
			//            }  
//			System.out.println(handler.toString());  
			return handler.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}

	}

	public static void main(String argt0[]) throws Exception {
		System.out.println(parseFile(new File("E:\\myworkspace\\CORE8\\WebRoot\\/uploadDoc/doc/1406534188291.doc")));
	}
}
