package cn.labsoft.labos.base.role.vo;

import java.util.List;

import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabRoleVo extends BaseVo {

	private String id;
	private String[] ids;
	@ExcelAnnotation(exportName = "角色名称")
	private String name;
	private String isUsed;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "系统默认角色")
	private String isDefault;
	@ExcelAnnotation(exportName = "显示状态")
	private String show; //显示 0前台,1后台
	private String userNames;
	private List<LabUserVo> userList;
	private String index;
	@ExcelAnnotation(exportName = "序号")
	private Integer sort;
	private String funIds;
	private String funNames;
	private List<LabFunctionVo> funList;
	private String isSynchro;//是否同步
	private String portlet;
	public String getPortlet() {
		return portlet;
	}

	public void setPortlet(String portlet) {
		this.portlet = portlet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String[] getIds() {
		return ids;
	}

	@Override
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getFunIds() {
		return funIds;
	}

	public void setFunIds(String funIds) {
		this.funIds = funIds;
	}

	public String getFunNames() {
		return funNames;
	}

	public void setFunNames(String funNames) {
		this.funNames = funNames;
	}

	public List<LabFunctionVo> getFunList() {
		return funList;
	}

	public void setFunList(List<LabFunctionVo> funList) {
		this.funList = funList;
	}

	public List<LabUserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<LabUserVo> userList) {
		this.userList = userList;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getIsSynchro() {
		return isSynchro;
	}

	public void setIsSynchro(String isSynchro) {
		this.isSynchro = isSynchro;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
}
