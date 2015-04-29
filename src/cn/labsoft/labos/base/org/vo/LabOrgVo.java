package cn.labsoft.labos.base.org.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
public class LabOrgVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String labOrgLevelId;
	@ExcelAnnotation(exportName = "组织等级名称")
	private String labOrgLevelName;
	@ExcelAnnotation(exportName = "组织等级")
	private Short labOrgLevelRank;
	private String parentId;
	@ExcelAnnotation(exportName = "组织编码")
	private String code;
	@ExcelAnnotation(exportName = "组织名称")
	private String name;
	@ExcelAnnotation(exportName = "地址")
	private String address;
	@ExcelAnnotation(exportName = "联系人")
	private String linkMan;
	@ExcelAnnotation(exportName = "联系电话")
	private String telephone;
	@ExcelAnnotation(exportName = "邮编")
	private String post;
	@ExcelAnnotation(exportName = "传真")
	private String fax;
	@ExcelAnnotation(exportName = "logo")
	private String logo;
	@ExcelAnnotation(exportName = "网站")
	private String website;
	@ExcelAnnotation(exportName = "电子邮箱")
	private String email;
	@ExcelAnnotation(exportName = "是否使用")
	private String isUsed;
	@ExcelAnnotation(exportName = "序号")
	private Integer sort;
	@ExcelAnnotation(exportName = "父组织名称")
	private String parentName;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "组织类型")
	private String type;
	@ExcelAnnotation(exportName = "组织类型Id")
	private String typeId;//组织类别ID
	@ExcelAnnotation(exportName = "组织等级")
	private Integer rank;//等级
	@ExcelAnnotation(exportName = "界面风格")
	private Integer manner;//等级
	private Integer dataStr;//权限
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getLabOrgLevelId() {
		return labOrgLevelId;
	}

	public void setLabOrgLevelId(String labOrgLevelId) {
		this.labOrgLevelId = labOrgLevelId;
	}

	public String getLabOrgLevelName() {
		return labOrgLevelName;
	}

	public void setLabOrgLevelName(String labOrgLevelName) {
		this.labOrgLevelName = labOrgLevelName;
	}

	public Short getLabOrgLevelRank() {
		return labOrgLevelRank;
	}

	public void setLabOrgLevelRank(Short labOrgLevelRank) {
		this.labOrgLevelRank = labOrgLevelRank;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String getTypeId() {
		return typeId;
	}

	@Override
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Integer getManner() {
		return manner;
	}

	public void setManner(Integer manner) {
		this.manner = manner;
	}

	public Integer getDataStr() {
		return dataStr;
	}

	public void setDataStr(Integer dataStr) {
		this.dataStr = dataStr;
	}
	
}
