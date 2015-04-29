package cn.labsoft.labos.utils.word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 
 * <strong>Title : ExportWord </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Sep 5, 2014 1:08:54 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class ExportWord{
	private static final String ENCODING = "UTF-8";
	private static Configuration configuration = null;
	private static final String BASE_PATH = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getParentFile().getParentFile().getPath();
	static{
		configuration = new Configuration();
		configuration.setDefaultEncoding(ENCODING);
	}
	
	/**
	 * 生成word
	 * @Title:  
	 * @Description: TODO
	 * @param @param dataMap
	 * @param @param path
	 * @param @param tempFile
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public static String exportWord(String tempName,String path,Map<String,Object> dataMap) throws GlobalException{
		Template t=null;
		if(!tempName.startsWith(BASE_PATH)){
			tempName = BASE_PATH +tempName;
		}
		File f = new File(tempName);
		try {
			configuration.setDirectoryForTemplateLoading(f.getParentFile());
			t = configuration.getTemplate(f.getName()); //文件名
		} catch (IOException e) {
			throw new GlobalException("获取word模板出错 "+f.getPath(),e);
		}
		File file = new File(path); 
		if(!file.exists()){
			file.getParentFile().mkdirs();
		}
		File outFile = new File(path);
		if(outFile.exists())
			outFile.mkdirs();
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),ENCODING));
		} catch (Exception e1) {
			throw new GlobalException("生成word出错 "+tempName,e1);
		} 
		 
        try {
			t.process(dataMap, out);
			if(null!=out)
				out.close();
		} catch (Exception e) {
			throw new GlobalException("生成word出错 "+tempName,e);
		} 
		return path;
	}

}