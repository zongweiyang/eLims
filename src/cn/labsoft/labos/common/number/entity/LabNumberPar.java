package cn.labsoft.labos.common.number.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabNumberPar extends AbstractBasePo{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
	private String demo;
	private LabNumber labNumber;
	private String type;//
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@ManyToOne
	@JoinColumn(name="labNumber_id")
	public LabNumber getLabNumber() {
		return labNumber;
	}
	public void setLabNumber(LabNumber labNumber) {
		this.labNumber = labNumber;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
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
		return "编号管理";
	}
	
}
