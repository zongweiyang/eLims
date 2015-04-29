package cn.labsoft.labos.common.report.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 报告模版-标签
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabReport extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String type;//类型
	private String title;//报告名称
	private String busId;//业务id
	private String busName;//业务
	private String busType;//业务类型
	private String remark;//备注
	private String path; //文件路径
	private String busCode;//业务编码
	private byte[] xslFile;
	private byte[] htmlFile;

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public byte[] getXslFile() {
		return xslFile;
	}

	public void setXslFile(byte[] xslFile) {
		this.xslFile = xslFile;
	}

	public byte[] getHtmlFile() {
		return htmlFile;
	}

	public void setHtmlFile(byte[] htmlFile) {
		this.htmlFile = htmlFile;
	}

	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}

	@Transient
	@Override
	public String getTableName() {
		return "报告信息";
	}
}
