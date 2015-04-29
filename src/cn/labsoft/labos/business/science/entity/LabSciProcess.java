package cn.labsoft.labos.business.science.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javax.persistence.Entity;

@Entity
public class LabSciProcess extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String no;//编号
	private String contents;//内容
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String original;//原件
	private String hardCopy ;//复印件
	private String name;
	private String writeUser;
	private String remark;
	private String type;
	private LabSciScience labSciScience;
	
	@ManyToOne
	@JoinColumn(name="science_id")
	public LabSciScience getLabSciScience() {
		return labSciScience;
	}

	public void setLabSciScience(LabSciScience labSciScience) {
		this.labSciScience = labSciScience;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOriginal() {
		return this.original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getHardCopy () {
		return this.hardCopy ;
	}

	public void setHardCopy (String hardCopy ) {
		this.hardCopy  = hardCopy ;
	}
	@Column(name="[name]")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriteUser() {
		return writeUser;
	}

	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	@Override
	public String getModelName() {
		return "科研管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "过程管理";
	}
	@Column(name="[type]")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
