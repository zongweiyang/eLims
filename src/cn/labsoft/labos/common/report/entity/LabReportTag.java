package cn.labsoft.labos.common.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 报告模版-标签
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabReportTag extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String index;//序号
	private String title;//标签名称
	private String code;//表达式
	private String value;//值
	private String type;//类型
	private String remark;//备注
	private LabReportTag parentTag; //循环
	private LabReport labReport;    //模版
	
	@Column(name="[index]")
	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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
	@ManyToOne
	@JoinColumn(name="parent_id")
	public LabReportTag getParentTag() {
		return parentTag;
	}
	public void setParentTag(LabReportTag parentTag) {
		this.parentTag = parentTag;
	}
	@ManyToOne
	@JoinColumn(name="report_id", nullable=false)
	public LabReport getLabReport() {
		return labReport;
	}
	public void setLabReport(LabReport labReport) {
		this.labReport = labReport;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "报告标签信息";
	}
}
