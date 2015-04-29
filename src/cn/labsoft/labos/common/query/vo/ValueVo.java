package cn.labsoft.labos.common.query.vo;

import java.util.List;

public class ValueVo {
	private String name;
	private String demo;
	private List<ValueVo> listValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public List<ValueVo> getListValue() {
		return listValue;
	}
	public void setListValue(List<ValueVo> listValue) {
		this.listValue = listValue;
	}
}
