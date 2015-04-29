package cn.labsoft.labos.common.page.entity;

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
@Table(name = "lab_page_editor")
public class LabPageEditor extends BasePo {
	private static final long serialVersionUID = 5404730786419965690L;
	private String name;// 页面中文名
	private String url;// 页面路径
	private String objName;// 页面对象名
	private String objUrl;// 页面对象路径
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjUrl() {
		return objUrl;
	}

	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}