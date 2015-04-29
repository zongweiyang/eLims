package cn.labsoft.labos.business.science.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name="lab_sci_funds")
public class LabSciFunds extends AbstractBasePo{
	private static final long serialVersionUID = 1L;
	private LabSciScience labSciScience;
	private String name;//资金名称
	private double money;//费用数量
	private String user;//使用人员
	private String useDate;//使用时间
	private String type;//类型
	private String remark;//备注
	
	@ManyToOne
	@JoinColumn(name="science_id")
	public LabSciScience getLabSciScience() {
		return labSciScience;
	}

	public void setLabSciScience(LabSciScience labSciScience) {
		this.labSciScience = labSciScience;
	}

	@Column(length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 11)
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Column(length = 32)
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(length = 32)
	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	@Column(length = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "科研项目经费";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "科研经费表";
	}

}
