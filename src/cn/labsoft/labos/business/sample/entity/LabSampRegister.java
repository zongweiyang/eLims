package cn.labsoft.labos.business.sample.entity;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Entity;
/**
 * 登记任务信息持久化对象
 * @author Quinn
 */
@Entity
public class LabSampRegister extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	
	private String sampType;//样品类型
	private String sampTypeId;
	private String sampNo;//来样编号
	private String sampName;//样品名称
	private String sampNum;//样品数量
	private String sampSource;//样品来源
	private String sampPack;//包装方式
	private String sampUser;//送样人员
	private String sampDate;//送样日期
	private String saveUser;//保管人
	private String saveOrg; //存放地点
	private String sampDesc; //处理情况
	
	private String no;//任务编号
	private String title;//任务名称
	private String unit;//受检单位
	private String acceptUser;//受理人
	private String createDate;//登记日期
	private String taskType;//检测类别
	private String itemNames;//检测内容
	private String itemIds;//检测内容Id
	private String testStands;//检测依据
	private String reportType;//报告性质 检测，检验
	private String reportNum;//报告份数
	private String reportMake; //生成报告方式 1按任务 2按样品类型
	private String reportPost;//取报告方式
	private String finishDate;//出报告日期
	private String reportDate;
	private Double charge;     //检测费用
	private String chargeType; //费用类型
	private String status;    //状态
	private String chargeNo;   //收费单号
	private String isCharge;//是否收费
	//客户信息
	private LabSampCustomer labSampCustomer; //客户表关联
	private LabWfProcessIns processIns;
	
	private String otherId;     //其他模块业务数据
	private String otherType;   //业务类型
	
	@ManyToOne
	@JoinColumn(name="process_ins_id")
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}

	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSampType() {
		return this.sampType;
	}

	public void setSampType(String sampType) {
		this.sampType = sampType;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getSampNo() {
		return sampNo;
	}

	public void setSampNo(String sampNo) {
		this.sampNo = sampNo;
	}

	public String getSampName() {
		return sampName;
	}

	public void setSampName(String sampName) {
		this.sampName = sampName;
	}

	public String getSampNum() {
		return sampNum;
	}

	public void setSampNum(String sampNum) {
		this.sampNum = sampNum;
	}

	public String getSampSource() {
		return sampSource;
	}

	public void setSampSource(String sampSource) {
		this.sampSource = sampSource;
	}

	public String getSampPack() {
		return sampPack;
	}

	public void setSampPack(String sampPack) {
		this.sampPack = sampPack;
	}

	public String getSampUser() {
		return sampUser;
	}

	public void setSampUser(String sampUser) {
		this.sampUser = sampUser;
	}

	public String getSampDate() {
		return sampDate;
	}

	public void setSampDate(String sampDate) {
		this.sampDate = sampDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(String acceptUser) {
		this.acceptUser = acceptUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getItemNames() {
		return itemNames;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getTestStands() {
		return testStands;
	}

	public void setTestStands(String testStands) {
		this.testStands = testStands;
	}

	public String getReportNum() {
		return reportNum;
	}

	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}

	public String getReportPost() {
		return reportPost;
	}

	public void setReportPost(String reportPost) {
		this.reportPost = reportPost;
	}
	
	public String getSampTypeId() {
		return sampTypeId;
	}
	public void setSampTypeId(String sampTypeId) {
		this.sampTypeId = sampTypeId;
	}
	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getSaveOrg() {
		return saveOrg;
	}

	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}

	public String getSampDesc() {
		return sampDesc;
	}

	public void setSampDesc(String sampDesc) {
		this.sampDesc = sampDesc;
	}
	
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	public String getChargeNo() {
		return chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}
	
	public String getReportMake() {
		return reportMake;
	}

	public void setReportMake(String reportMake) {
		this.reportMake = reportMake;
	}

	@ManyToOne
	@JoinColumn(nullable=false)	
	public LabSampCustomer getLabSampCustomer() {
		return labSampCustomer;
	}

	public void setLabSampCustomer(LabSampCustomer labSampCustomer) {
		this.labSampCustomer = labSampCustomer;
	}
	
	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public String getOtherType() {
		return otherType;
	}

	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	@Transient
	@Override
	public String getModelName() {
		return "业务检测";
	}
	@Transient
	@Override
	public String getTableName() {
		return "任务登记";
	}
}
