package cn.labsoft.labos.utils.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.FileUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;
/**
 * 
 * <strong>Title : ExportCsv </strong>. <br>
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
public class ExportCSV{
	
	/**
	 * 生成Csv
	 * @Title:  
	 * @Description: TODO
	 * @param @param dataMap
	 * @param @param path
	 * @param @param tempFile
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws ClassNotFoundException 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static String exportCSV(String source,String path,Map<String,Object> dataMap) throws GlobalException{
		List<Object> l = (List<Object>)dataMap.get("data");
		String property = (String)dataMap.get("property");
		List<String[]> tempList = new ArrayList<String[]>();
		List<String> thList = null;
		List<String> trList = null;
		//指定表头
		if(null!=property&&property.indexOf(":")>0){
			List<String> list = StrUtils.splitList(property, ',');
			//表头信息
			thList = new  ArrayList<String>();
			for(int i=0;i<list.size();i++){
				String[] arrays = StrUtils.split(list.get(i), ':');
				thList.add(arrays[0]);
			}
			tempList.add(thList.toArray(new String[]{}));
			for(Object obj:l){
				Map<String,String> m = BeanUtils.getValueMaps(obj);
				trList = new  ArrayList<String>();
				for(int i=0;i<list.size();i++){
					String[] arrays = StrUtils.split(list.get(i), ':');
					trList.add(m.get(arrays[1]));
				}
				tempList.add(trList.toArray(new String[]{}));
			}
		}else{
			int temp = 0;
			Map<String,String> aliasMap = null;
			//表头
			for(Object obj:l){
				Map<String,String> m = BeanUtils.getValueMaps(obj);
				if(0==temp){
					try {
						aliasMap = ExportExcel.getAnnotationInfo(Class.forName(obj.getClass().getName()));
					} catch (ClassNotFoundException e) {
						Log4J.error("别名获取错误", e);
					}
					thList = new ArrayList<String>();
				}
					
				trList = new ArrayList<String>();
				for(String key:m.keySet()){
					if(0==temp){
						thList.add(aliasMap.get(key));
					}else{
						trList.add(m.get(key));
					}
				}
				if(0==temp){
					tempList.add(thList.toArray(new String[]{}));
				}
				temp++;
				tempList.add(trList.toArray(new String[]{}));
			}
		}
		
		File f= new File(path);
		if(!f.exists()){
			f.getParentFile().mkdirs();
		}else{
			FileUtils.delAllFile(f.getParentFile().getPath());
		}
		
		if(null!=tempList&&tempList.size()>0){
			for(String[] str:tempList){
				CsvUtils.writeCsv(path, str, true);
			}
		}
		return path;
	}

}