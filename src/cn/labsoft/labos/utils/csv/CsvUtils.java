package cn.labsoft.labos.utils.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : CsvUtils </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Sep 5, 2014 9:45:04 AM  </strong>. <br>
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
public class CsvUtils {  
	private static final String CHARSET="UTF-8"; 
	/**
	 * 读取CSV文件
	 * @Title:  
	 * @Description: TODO
	 * @param   
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
     public static List<String[]>  readeCsv(String csvFilePath) throws GlobalException{
        List<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
        CsvReader reader;
		try {
			 reader = new CsvReader(csvFilePath,',',Charset.forName(CHARSET));
			 //reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
			 while(reader.readRecord()){ //逐行读入除表头的数据    
	              csvList.add(reader.getValues());
	         }            
	         reader.close();
		} catch (Exception e) {
			throw new GlobalException("解析CSV出错: "+csvFilePath,e);
		}   
         return csvList;
     }
     
    /**
     * 写入CSV文件
     * @Title:  
     * @Description: TODO
     * @param @throws GlobalException  
     * @return 返回类型 
     * @throws
     */
     public static String writeCsv(String csvFilePath,String[] contents,boolean append) throws GlobalException{
         try {
             CsvWriter wr = new CsvWriter(csvFilePath.replace(File.separator, "/"),',',Charset.forName(CHARSET),append);
             wr.writeRecord(contents);
             wr.close();
          } catch (IOException e) {
        	  throw new GlobalException("写CSV出错: "+csvFilePath,e);
          }
          return csvFilePath;
     }
     
     public static void main(String[] args) {
		try {
			String csvFilePath = "E:\\workspace\\CORE8\\WebRoot\\export\\temp\\0\\csv\\系统日志-20140905153901924.csv";
			boolean append = true;
			new CsvUtils().writeCsv(csvFilePath,new String []{"1","2","3","4","5","6"},append);
			List<String[]> csvList = new CsvUtils().readeCsv(csvFilePath);
			 for(int row=0;row<csvList.size();row++){
	              String[]  cell = csvList.get(row); //取得第row行第0列的数据
	              System.out.println(cell);
	         }
		} catch (GlobalException e) {
			e.printStackTrace();
		}
	}
}
