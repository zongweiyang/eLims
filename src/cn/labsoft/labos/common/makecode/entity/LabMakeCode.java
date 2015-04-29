package cn.labsoft.labos.common.makecode.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabMakeCode extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	public static final String CODE ="YZGL";
	private String name;//模块名
	private String nameChin;//模板中文名
	private String className;//类名
	private String classNameChin;//类中文名
	@Transient
	@Override
	public String getTableName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getModelName() {
		return "生成代码";
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameChin() {
		return this.nameChin;
	}

	public void setNameChin(String nameChin) {
		this.nameChin = nameChin;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassNameChin() {
		return this.classNameChin;
	}

	public void setClassNameChin(String classNameChin) {
		this.classNameChin = classNameChin;
	}
}
