package cn.labsoft.labos.source.quality.vo;



import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabQuaInitAuditPlanVo extends BaseVo{

	private String id;
    private String name;
	private String rank;   //层级结构  1：一级 2： 二级  3：三级
	private String parentId;
	private String key;
	private String searchName;
	private List<LabQuaInitAuditPlanVo> childList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<LabQuaInitAuditPlanVo> getChildList() {
		return childList;
	}
	public void setChildList(List<LabQuaInitAuditPlanVo> childList) {
		this.childList = childList;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
