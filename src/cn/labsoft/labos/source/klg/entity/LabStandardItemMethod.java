package cn.labsoft.labos.source.klg.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@SuppressWarnings("serial")
@Entity
@Table(name="lab_klg_standard_item_method")
public class LabStandardItemMethod extends AbstractBasePo{

	private String remark;
	
	private LabStandard standard;
	private String standardName;
	private LabItem item;
	private LabMethod method;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@ManyToOne
	@JoinColumn(name="standard_id", nullable=false)
	public LabStandard getStandard() {
		return standard;
	}
	public void setStandard(LabStandard standard) {
		this.standard = standard;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	public LabItem getItem() {
		return item;
	}
	public void setItem(LabItem item) {
		this.item = item;
	}
	@ManyToOne
	@JoinColumn(name="method_id", nullable=false)
	public LabMethod getMethod() {
		return method;
	}
	public void setMethod(LabMethod method) {
		this.method = method;
	}
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "标准项目方法关系";
	}
	
	
}
