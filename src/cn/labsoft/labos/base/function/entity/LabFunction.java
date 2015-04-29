package cn.labsoft.labos.base.function.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;

/**
 * 系统功能Entity实体
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Entity
@Table(name = "lab_sys_function")
public class LabFunction extends AbstractBasePo {
	private static final long serialVersionUID = -5291513164419385528L;
	@Translator
	private String name;// 功能名称
	private String code; // 功能编码
	private String wfcode; //流程编码
	private String url;// 功能路径
	private String imageUrl;
	private LabFunction parentFunction;// 父功能
	private String type;// 功能类型
	private Set<LabFunction> subFunctions;// 子功能集合
	private String isDisplay;// 是否显示
	
	private String isProcess;// 是否是流程
	private String isReport; // 是否关联报告模版
	private String isBarCode; // 是否支持条码
	private String isQuery;//是否为查询定义
	private String isQuickFun;//是否快捷功能
	private String isTemplate;//是否关联导出模板
	private String remark;// 备注
	private String isFront;//是否前端显示
	private String isBack;//是否后台显示
	private String dataStr;//org部门级 user用户级
	private String valStr; //若为role时的roleId

	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public String getIsFront() {
		return isFront;
	}

	public void setIsFront(String isFront) {
		this.isFront = isFront;
	}

	public String getIsBarCode() {
		return isBarCode;
	}

	public void setIsBarCode(String isBarCode) {
		this.isBarCode = isBarCode;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	public LabFunction getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(LabFunction parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "parentFunction", fetch = FetchType.LAZY)
	@Where(clause = "is_del ='N'")
	public Set<LabFunction> getSubFunctions() {
		return subFunctions;
	}

	public void setSubFunctions(Set<LabFunction> subFunctions) {
		this.subFunctions = subFunctions;
	}

	

	public String getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(String isProcess) {
		this.isProcess = isProcess;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getWfcode() {
		return wfcode;
	}

	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	@Column(name = "is_Query")
	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsQuickFun() {
		return isQuickFun;
	}

	public void setIsQuickFun(String isQuickFun) {
		this.isQuickFun = isQuickFun;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	public String getValStr() {
		return valStr;
	}

	public void setValStr(String valStr) {
		this.valStr = valStr;
	}

	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}

	@Transient
	@Override
	public String getTableName() {
		return "功能维护";
	}
}