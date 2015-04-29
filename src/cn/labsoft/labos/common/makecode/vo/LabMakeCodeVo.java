package cn.labsoft.labos.common.makecode.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabMakeCodeVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "模块名")
	private String name;
	@ExcelAnnotation(exportName = "模板中文名")
	private String nameChin;
	@ExcelAnnotation(exportName = "类名")
	private String className;
	@ExcelAnnotation(exportName = "类中文名")
	private String classNameChin;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
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
