package cn.labsoft.labos.common.template.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.labsoft.labos.framework.common.po.BasePo;

/**
 * 页面管理Entity实体
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Entity
@Table(name = "lab_template")
public class LabTemplate extends BasePo {
	private static final long serialVersionUID = -7955166761251127245L;
	private String type;//类型
	private String name;//模板名称
	private String remark;//备注
	private String path; //文件路径
	private String busId;//关联业务的ID
	private String objUrl;//对象的路径(cn.labsoft.....)

	public String getObjUrl() {
		return objUrl;
	}

	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
