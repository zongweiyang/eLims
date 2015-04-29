package cn.labsoft.labos.base.org.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;
@Entity
@Table(name="lab_sys_org")
public class LabOrg extends AbstractBasePo  {
	private static final long serialVersionUID = 1L;
		public static final String LABORG_KIND ="KIND";
	private LabOrg labOrg;//上级组织
	private String code;//组织编码
	private Integer dataStr;//权限标示
	@Translator
	private String name;//组织名称
	private String address;//组织地址
	private String linkMan;//联系人
	private String telephone;//电话
	private String post;//邮编
	private String fax;//传真
	private String logo;
	private String website;//网站
	private String email;//电子邮箱
	private String isUsed;
	
	private String remark;
	private Integer rank;//等级
	@Translator
	private String type;//组织类别
	private String typeId;//组织类别ID
	private String manner;//界面风格  1.蓝色  2 绿色
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getManner() {
		return manner;
	}
	public void setManner(String manner) {
		this.manner = manner;
	}
	public Integer getDataStr() {
		return dataStr;
	}
	public void setDataStr(Integer dataStr) {
		this.dataStr = dataStr;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "组织管理";
	}
	@ManyToOne
	@JoinColumn(name="pid")
	public LabOrg getLabOrg() {
		return labOrg;
	}
	public void setLabOrg(LabOrg labOrg) {
		this.labOrg = labOrg;
	}

}