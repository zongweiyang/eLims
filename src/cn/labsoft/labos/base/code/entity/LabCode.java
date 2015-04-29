package cn.labsoft.labos.base.code.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;

/**
 * @title  公共代码
 * @author Bill
 * @time   2014.02.08
 */
@Entity
@Table(name="lab_code_code")
public class LabCode extends AbstractBasePo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LabType labType;
	@Translator
	private String name;
	private String code;
	private String showType;//公共代码显示位置 Y后台 N前台
	private String remark;
	
	// Constructors

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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	@Transient
	public String getModelName() {
		return "系统管理";
	}

	@Override
	@Transient
	public String getTableName() {
		return "公共代码";
	}
	
	@ManyToOne
	@JoinColumn(name="typeid")
	public LabType getLabType() {
		return labType;
	}

	public void setLabType(LabType labType) {
		this.labType = labType;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}