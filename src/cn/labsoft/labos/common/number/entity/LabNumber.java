package cn.labsoft.labos.common.number.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabNumber extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	public static final String CODE="BHGZ";
	private String name;//编号规则名称
	private String part;//几部分组成
	private String type;//规则类型
	private String connector;//连接符
	private String expression;
	private String example;//实例
	private String path;//路径
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
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
	
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "编号定义";
	}
}
