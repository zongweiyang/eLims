package cn.labsoft.labos.framework.common.po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.exportexcel.ExportExcel;

public abstract class AbstractBasePo extends Po implements Serializable{
	
	public abstract String getTableName();
	
	public abstract String getModelName();
	
	@Override
	public String toString(){
		Map<String, String> map = new HashMap<String, String>();
		String poClazz = this.getClass().getName();
		try {
			String classString = poClazz.replace(".entity.", ".vo.")+"Vo";
			map = ExportExcel.getAnnotationInfo(Class.forName(classString));
		} catch (ClassNotFoundException e) {
			Log4J.error("加载aliasMap 失败", e);
			
		}
		return BeanUtils.getValues(this,map);
	}
	
	
}
