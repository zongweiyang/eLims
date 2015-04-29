package cn.labsoft.labos.source.reagent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name="lab_rea_check")
public class LabReaCheckMain extends AbstractBasePo {

	private static final long serialVersionUID = 1L;
	private String checkno;
	private String name;
	private String checker;
	private Date checktime;
	private String remark;

	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	@Column(name="check_time")
	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	@Override
	@Transient
	public String getModelName() {
		return "试剂盘点";
	}

	@Override
	@Transient
	public String getTableName() {
		return "试剂盘点详情";
	}

}