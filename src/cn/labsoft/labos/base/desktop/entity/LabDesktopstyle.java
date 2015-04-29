package cn.labsoft.labos.base.desktop.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import cn.labsoft.labos.i18n.annotation.Translator;

/**
 * LabDesktopstyle entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name="lab_desktop_style")
public class LabDesktopstyle{

	// Fields

	private String id;
	private String code;
	private String name;
	@Translator
	private String descc;
	private String picurl;
	private String isdefault;
	private String isDel;

	// Constructors

	/** default constructor */
	public LabDesktopstyle() {
	}

	/** full constructor */
	@SuppressWarnings("unchecked")
	public LabDesktopstyle(String code, String name, String descc,
			String picurl, String isdefault, Set sysUsers) {
		this.code = code;
		this.name = name;
		this.descc = descc;
		this.picurl = picurl;
		this.isdefault = isdefault;
	}

	// Property accessors

	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
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

	
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	@Transient
	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(null);
		if (this.getCode() != null) {
			builder.append("编码", this.getCode());
		}
		if (this.getName() != null) {
			builder.append("名称", this.getName());
		}
		String str = builder.toString();
		return str;
	}
//	@Transient
//	@Override
//	public String getModelName() {
//		// TODO Auto-generated method stub
//		return "系统管理";
//	}
//	@Transient
//	@Override
//	public String getTableName() {
//		// TODO Auto-generated method stub
//		return "用户桌面";
//	}
}