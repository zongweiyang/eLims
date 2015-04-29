package cn.labsoft.labos.source.ambient.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabAmbientInfo extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private double value;//参数值
	private String address;//地址
	private String room;//房间
	private String time;//环境时间
	private String samBeatchId;//
	private LabAmbient labAmbient;
	private String demo;//备注
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	@ManyToOne
	@JoinColumn(name="labAmbient_id")
	public LabAmbient getLabAmbient() {
		return labAmbient;
	}
	public void setLabAmbient(LabAmbient labAmbient) {
		this.labAmbient = labAmbient;
	}
	
	@Transient
	@Override
	public String getTableName() {
		return "环境管理";
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
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
	public String getSamBeatchId() {
		return samBeatchId;
	}
	public void setSamBeatchId(String samBeatchId) {
		this.samBeatchId = samBeatchId;
	}

}
