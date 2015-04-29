package cn.labsoft.labos.source.ambient.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabAmbientInfoVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "参数值")
	private double value;
	@ExcelAnnotation(exportName = "地址")
	private String address;
	@ExcelAnnotation(exportName = "房间")
	private String room;
	@ExcelAnnotation(exportName = "环境时间")
	private String time;
	private String labAmbientId;
	@ExcelAnnotation(exportName = "参数名称")
	private String labAmbientName;
	@ExcelAnnotation(exportName = "参数单位")
	private String startTime;
	private String endTime;
	private String labAmbientUnit;
	private String times;
	private String values;
	private String downValues;
	private String upValues;
	private String samBeatchId;
	private String demo;//备注
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getSamBeatchId() {
		return samBeatchId;
	}
	public void setSamBeatchId(String samBeatchId) {
		this.samBeatchId = samBeatchId;
	}
	public String getLabAmbientId() {
		return labAmbientId;
	}
	public void setLabAmbientId(String labAmbientId) {
		this.labAmbientId = labAmbientId;
	}
	public String getLabAmbientName() {
		return labAmbientName;
	}
	public void setLabAmbientName(String labAmbientName) {
		this.labAmbientName = labAmbientName;
	}
	public String getLabAmbientUnit() {
		return labAmbientUnit;
	}
	public void setLabAmbientUnit(String labAmbientUnit) {
		this.labAmbientUnit = labAmbientUnit;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}



	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getDownValues() {
		return downValues;
	}
	public void setDownValues(String downValues) {
		this.downValues = downValues;
	}
	public String getUpValues() {
		return upValues;
	}
	public void setUpValues(String upValues) {
		this.upValues = upValues;
	}

}
