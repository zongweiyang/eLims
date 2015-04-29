package cn.labsoft.labos.source.appara.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
public class LabApparaUseVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	private List<LabApparaUseVo> apparatusUseingVoList;
	private String id;
	private String appId;//仪器id
	private String appName;//仪器名称
	private String appNo;//仪器编号
	
	private String wenDu;
	private String shiDu;
	private String beforeUse;
	private String nowUse;
	private String afterUse;
	private String remark;
	private String useUserId;//仪器使用人员ID
	private String useUser;
	private String managerUser;
	private String ext01;
	private String ext02;
	private String ext03;
	private String ext04;
	private String ext05;
	private String busId;//使用仪器有关的业务id
	private String samBeatchId;//数据录入关联
	/**
	 * 仪器预约信息
	 * */
	private String stauts;//状态：预约1,无预约0
	private String beStartDate;//预约起始时间
	private String beEndDate;//预约结束时间
	private String beRemark;//预约备注
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWenDu() {
		return wenDu;
	}
	public void setWenDu(String wenDu) {
		this.wenDu = wenDu;
	}
	public String getShiDu() {
		return shiDu;
	}
	public void setShiDu(String shiDu) {
		this.shiDu = shiDu;
	}
	public String getBeforeUse() {
		return beforeUse;
	}
	public void setBeforeUse(String beforeUse) {
		this.beforeUse = beforeUse;
	}
	public String getNowUse() {
		return nowUse;
	}
	public void setNowUse(String nowUse) {
		this.nowUse = nowUse;
	}
	public String getAfterUse() {
		return afterUse;
	}
	public void setAfterUse(String afterUse) {
		this.afterUse = afterUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getManagerUser() {
		return managerUser;
	}
	public void setManagerUser(String managerUser) {
		this.managerUser = managerUser;
	}
	public String getExt01() {
		return ext01;
	}
	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}
	public String getExt02() {
		return ext02;
	}
	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}
	public String getExt03() {
		return ext03;
	}
	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}
	public String getExt04() {
		return ext04;
	}
	public void setExt04(String ext04) {
		this.ext04 = ext04;
	}
	public String getExt05() {
		return ext05;
	}
	public void setExt05(String ext05) {
		this.ext05 = ext05;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public List<LabApparaUseVo> getApparatusUseingVoList() {
		return apparatusUseingVoList;
	}
	public void setApparatusUseingVoList(
			List<LabApparaUseVo> apparatusUseingVoList) {
		this.apparatusUseingVoList = apparatusUseingVoList;
	}

	public String getUseUserId() {
		return useUserId;
	}
	public void setUseUserId(String useUserId) {
		this.useUserId = useUserId;
	}
	public String getUseUser() {
		return useUser;
	}
	public void setUseUser(String useUser) {
		this.useUser = useUser;
	}
	public String getBeStartDate() {
		return beStartDate;
	}
	public void setBeStartDate(String beStartDate) {
		this.beStartDate = beStartDate;
	}
	public String getBeEndDate() {
		return beEndDate;
	}
	public void setBeEndDate(String beEndDate) {
		this.beEndDate = beEndDate;
	}
	public String getBeRemark() {
		return beRemark;
	}
	public void setBeRemark(String beRemark) {
		this.beRemark = beRemark;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public String getSamBeatchId() {
		return samBeatchId;
	}
	public void setSamBeatchId(String samBeatchId) {
		this.samBeatchId = samBeatchId;
	}

	
}