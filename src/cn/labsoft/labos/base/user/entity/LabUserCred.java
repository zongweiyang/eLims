package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 用户证书信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_user_cred")
public class LabUserCred extends AbstractBasePo {
	
	private static final long serialVersionUID = 1L;
	private String name;//名称
	private String code;//编号
	private String startDate;//起始日期
	private String endDate;//结束日期
	private String userId;//用户Id
	private String userName;//用户名称
	private String unit;//单位
	private String type;//类型
	private String remark;//备注


	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	@Override
	public String getTableName() {
		return "用户资质关系";
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
}
