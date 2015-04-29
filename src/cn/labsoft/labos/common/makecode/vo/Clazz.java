package cn.labsoft.labos.common.makecode.vo;

import java.util.List;

public class Clazz {
	public String clazzCommon;//中文名
	public String  clazzName;//英文名
	public int addColEnterNum;//几行一跨
	public String serMethodNames;//方法
	public String jspName;//页面名称
	List<Prop> prop;

	public String getClazzCommon() {
		return clazzCommon;
	}

	public void setClazzCommon(String clazzCommon) {
		this.clazzCommon = clazzCommon;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName.trim();
	}

	public List<Prop> getProp() {
		return prop;
	}

	public void setProp(List<Prop> prop) {
		this.prop = prop;
	}

	public int getAddColEnterNum() {
		return addColEnterNum;
	}

	public void setAddColEnterNum(int addColEnterNum) {
		this.addColEnterNum = addColEnterNum;
	}

	public String getSerMethodNames() {
		return serMethodNames;
	}

	public void setSerMethodNames(String serMethodNames) {
		this.serMethodNames = serMethodNames;
	}

	public String getJspName() {
		return jspName;
	}

	public void setJspName(String jspName) {
		this.jspName = jspName;
	}

	
}
