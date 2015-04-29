package cn.labsoft.labos.base.desktop.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * LabDesktopstyle entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class LabDesktopstyleVo extends BaseVo {

	// Fields

	private String id;
	private String code;
	private String name;
	private String descc;
	private String picurl;
	private String isdefault;

	private List<LabDesktopstyleVo> list;

	private String userid;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescc() {
		return this.descc;
	}

	public void setDescc(String descc) {
		this.descc = descc;
	}

	public String getPicurl() {
		return this.picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getIsdefault() {
		return this.isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}