package cn.labsoft.labos.base.code.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;

/**
 * @title  公共代码类型
 * @author Bill
 * @time   2014.02.08
 */
@Entity
@Table(name="lab_code_type")
public class LabType extends AbstractBasePo {

	private static final long serialVersionUID = -1756044775228746446L;
	// Fields
	@Translator
	private String name;  
	private String code;
	private String showType;//公共代码显示位置 Y后台 N前台
	private String remark;
	private String type;
	/**
	 * 序号
	 */
	
	private Set<LabCode> codes;

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
		return "公共代码类型";
	}
	@OneToMany(mappedBy="labType", fetch = FetchType.LAZY)
	public Set<LabCode> getCodes() {
		return codes;
	}

	public void setCodes(Set<LabCode> codes) {
		this.codes = codes;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}