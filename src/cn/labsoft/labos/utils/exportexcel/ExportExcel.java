package cn.labsoft.labos.utils.exportexcel;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.FileUtils;
import cn.labsoft.labos.utils.StrUtils;
/**
 * 
 * <strong>Title : ExportExcel Excel导出类</strong>. <br>
 * <strong>Description : Excel导出类�</strong>. <br>
 * <strong>Create on : Jul 23, 2012 9:17:38 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>LabOS v1.1.1</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class ExportExcel {
	
	@SuppressWarnings("unused")
	private static final String EXCEL_TITLE = "excelTitle"; // Excel表头
	@SuppressWarnings("unused")
	private static final String EXCEL_RESULT = "excelResult"; // Excel数据
	@SuppressWarnings("unchecked")
	public static Map<String, String> getAnnotationInfo(Class cls){
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 获取对象内定义的fields
	    Field[] files = cls.getDeclaredFields();
	    for (int i = 0; i < files.length; i++) {  
            Field f = files[i]; 
            // 获取field对应的annotation
            ExcelAnnotation exa = f.getAnnotation(ExcelAnnotation.class);  
            // 如果设置了annottion  
            if (exa != null) {  
            	map.put(f.getName(), exa.exportName());
            }  
        } 
	    return map;
	}
	
	@SuppressWarnings("unchecked")
	private static List getSelectColumnValue(List valueList, List<String> selectedColumn) throws NoSuchFieldException, IllegalAccessException {
		List result = new ArrayList();
		for (Object in : valueList) {
			List tempList = new ArrayList();
			Class cl = in.getClass();
			for (String name : selectedColumn) {
				Field f = cl.getDeclaredField(name);
				f.setAccessible(true);
				tempList.add(f.get(in));
			}
			result.add(tempList);
		}
		return result;
	}
	
   /**
    * 设置Excel表头
    * @param selectedColumnList 页面选中的要导出的列
    * @param checkboxList 根据要导出对象读取的对应关系
    * @return  Excel表头
    * @return 返回类型 List<String>
    */
	private static List<String> setExcelTitle(List<String> selectedColumnList, Map<String, String> checkboxList){
		List<String> result = new ArrayList<String>();
		for(String name : selectedColumnList){
			result.add(checkboxList.get(name));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static String[][] prepareExcelData(List<String> excelTitle, List excelValue){
		String[][] excelData = null;
		if(excelValue == null || excelValue.size() <= 0 || excelTitle == null || excelTitle.size() <= 0){
			return excelData;
		}
		excelData = new String[excelValue.size()+1][excelTitle.size()];
		for(int i=1;i<=excelTitle.size();i++){
			excelData[0][i-1]=excelTitle.get(i-1);
		}
		for (int i = 0; i < excelValue.size(); i++) {
			List tempList = (List) excelValue.get(i);
			Object[] dataStr =  tempList.toArray();
			String tempStr = "";
			for (int j = 1; j <= dataStr.length; j++) {
				tempStr = getValue(dataStr[j-1]);
				if (0 < tempStr.indexOf("-") && 0 < tempStr.indexOf(":")) {
					tempStr = StrUtils.replace(tempStr, "00:00:00.0", "");
				}
				excelData[i + 1][j - 1] = tempStr;
			}
		}
		return excelData;
	}
	
	private static String getValue(Object value){
		String textValue = "";  
        if (value == null)  
            return textValue;  
        if (value instanceof Boolean) {  
            boolean bValue = (Boolean) value;  
            textValue = "是";  
            if (!bValue) {  
                textValue = "否";  
            }  
        } else if (value instanceof Date) { 
        	//格式化日期  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) value;  
            textValue = sdf.format(date);  
        } else {
        	textValue = value.toString();
        }
        return textValue; 
	}
	
	@SuppressWarnings("unchecked")
	public static void exportExcel(String title, String excelName,
			List valueList, Map<String, String> checkboxList,
			List<String> selectedColumnList, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List excelValue = new ArrayList<String>();
		// 根据选择的列来获取要导出的数据
		excelValue = ExportExcel.getSelectColumnValue(valueList, selectedColumnList);
		List<String> excelTitle = new ArrayList<String>();
		// 根据选择的列来设置Excel表头
		excelTitle = ExportExcel.setExcelTitle(selectedColumnList, checkboxList);
		ExcelUtils.exportExcelData(title, prepareExcelData(excelTitle, excelValue), excelName, request, response);
	}
	
	public static String exportExcel(String source,String target,Map<String, Object> beansMaps) throws GlobalException {
		File file = new File(target);
		if(file.exists()||file.getParentFile().exists()){
			FileUtils.delAllFile(file.getParentFile().getPath());
			//file = new File(targetPath);
		}else {
			file.getParentFile().mkdirs();
		}
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(source, beansMaps, target);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return target;
	}
}
