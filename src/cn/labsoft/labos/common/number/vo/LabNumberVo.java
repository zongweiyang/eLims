package cn.labsoft.labos.common.number.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabNumberVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "编号规则名称")
	private String name;
	@ExcelAnnotation(exportName = "几部分")
	private String part;
	@ExcelAnnotation(exportName = "规则类型")
	private String type;
	@ExcelAnnotation(exportName = "连接符")
	private String connector;//连接符
	@ExcelAnnotation(exportName = "实例")
	private String example;//实例
	private String expression;
	private Integer sort;
	private String path;//路径
	private List<LabNumberParVo> listLabNumberParVo;
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

	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public List<LabNumberParVo> getListLabNumberParVo() {
		return listLabNumberParVo;
	}
	public void setListLabNumberParVo(List<LabNumberParVo> listLabNumberParVo) {
		this.listLabNumberParVo = listLabNumberParVo;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
