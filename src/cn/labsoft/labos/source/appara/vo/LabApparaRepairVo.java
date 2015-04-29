package cn.labsoft.labos.source.appara.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabApparaRepairVo extends BaseVo{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String appInfoId;
	private String appInfoName;
	private String faultTime;
	private String faultMan;
	private String faultInfo;
	private String repairTime;
	private String repairDep;
	private String repairMan;
	private String repairInfo;
	private String checkTime;
	private String checkMan;
	private String checkInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFaultMan() {
		return faultMan;
	}

	public void setFaultMan(String faultMan) {
		this.faultMan = faultMan;
	}

	public String getFaultInfo() {
		return faultInfo;
	}

	public void setFaultInfo(String faultInfo) {
		this.faultInfo = faultInfo;
	}

	public String getRepairDep() {
		return repairDep;
	}

	public void setRepairDep(String repairDep) {
		this.repairDep = repairDep;
	}

	public String getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	public String getRepairInfo() {
		return repairInfo;
	}

	public void setRepairInfo(String repairInfo) {
		this.repairInfo = repairInfo;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public String getCheckInfo() {
		return checkInfo;
	}

	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}

	public String getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}

	public String getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime; 
	}

	public String getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
	}

	public String getAppInfoName() {
		return appInfoName;
	}

	public void setAppInfoName(String appInfoName) {
		this.appInfoName = appInfoName;
	}
	

}
