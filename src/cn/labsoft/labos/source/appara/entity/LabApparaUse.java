package cn.labsoft.labos.source.appara.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name="lab_appara_use")
public class LabApparaUse extends AbstractBasePo {

	
	private LabAppara labAppara;
	private String wenDu;
	private String shiDu;
	private String startDate;//使用起始时间
	private String endDate;//使用结束时间
	private String beforeUse;
	private String nowUse;
	private String afterUse;
	private String remark;//使用备注
	private String useUserId;//仪器使用人员ID
	private String useUser;//仪器使用人员
	private String managerUser;//仪器管理员
	private String ext01;
	private String ext02;
	private String ext03;
	private String ext04;
	private String ext05;
	private String busId;//使用仪器有关的业务id
	private String uuid; //用于检测是否一批处理
	private String samBeatchId;//数据录入关联
	/**
	 * 仪器预约信息
	 * */
	private String stauts;//状态：预约1,无预约0
	private String beStartDate;//预约起始时间
	private String beEndDate;//预约结束时间
	private String beRemark;//预约备注
	@ManyToOne
	@JoinColumn(name="lab_appara_id")
	public LabAppara getLabAppara() {
		return labAppara;
	}
	public void setLabAppara(LabAppara labAppara) {
		this.labAppara = labAppara;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
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
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器使用信息";
	}
	public String getSamBeatchId() {
		return samBeatchId;
	}
	public void setSamBeatchId(String samBeatchId) {
		this.samBeatchId = samBeatchId;
	}

}