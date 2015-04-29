package cn.labsoft.labos.source.appara.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_appara_repair")
public class LabApparaRepair extends AbstractBasePo {
	private String faultCause;
	private String faultInfo;
	private String mantance;
	private String mantResult;
	private Date mantData;
	private String mantMan;
	private String comment;
	private String ext01;
	private String ext02;
	private String ext03;
	private String ext04;
	private String ext05;

	public String getFaultCause() {
		return this.faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public String getFaultInfo() {
		return this.faultInfo;
	}

	public void setFaultInfo(String faultInfo) {
		this.faultInfo = faultInfo;
	}

	public String getMantance() {
		return this.mantance;
	}

	public void setMantance(String mantance) {
		this.mantance = mantance;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExt01() {
		return this.ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}

	public String getExt02() {
		return this.ext02;
	}

	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}

	public String getExt03() {
		return this.ext03;
	}

	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}

	public String getExt04() {
		return this.ext04;
	}

	public void setExt04(String ext04) {
		this.ext04 = ext04;
	}

	public String getExt05() {
		return this.ext05;
	}

	public void setExt05(String ext05) {
		this.ext05 = ext05;
	}
	
	public String getMantResult() {
		return mantResult;
	}

	public void setMantResult(String mantResult) {
		this.mantResult = mantResult;
	}

	public Date getMantData() {
		return mantData;
	}

	public void setMantData(Date mantData) {
		this.mantData = mantData;
	}

	public String getMantMan() {
		return mantMan;
	}

	public void setMantMan(String mantMan) {
		this.mantMan = mantMan;
	}

	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器xx信息";
	}
}