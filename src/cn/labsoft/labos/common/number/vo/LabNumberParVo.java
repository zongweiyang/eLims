package cn.labsoft.labos.common.number.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabNumberParVo extends BaseVo{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String expression;
	private String demo;
	private String labNumberId;
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

	public String getLabNumberId() {
		return labNumberId;
	}
	public void setLabNumberId(String labNumberId) {
		this.labNumberId = labNumberId;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
