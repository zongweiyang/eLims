package cn.labsoft.labos.business.sample.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSampRegisterVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	
	@ExcelAnnotation(exportName = "样品类型")
	private String sampType;
	private String sampTypeId;
	@ExcelAnnotation(exportName = "来样编号")
	private String sampNo;
	@ExcelAnnotation(exportName = "样品名称")
	private String sampName;
	@ExcelAnnotation(exportName = "样品数量")
	private String sampNum;
	@ExcelAnnotation(exportName = "样品来源")
	private String sampSource;
	@ExcelAnnotation(exportName = "包装方式")
	private String sampPack;
	@ExcelAnnotation(exportName = "送样人员")
	private String sampUser;
	@ExcelAnnotation(exportName = "送样日期")
	private String sampDate;
	@ExcelAnnotation(exportName = "保管人")
	private String saveUser; 
	@ExcelAnnotation(exportName = "存放地点")
	private String saveOrg;  
	@ExcelAnnotation(exportName = "处理情况")
	private String sampDesc; 
	
	@ExcelAnnotation(exportName = "任务编号")
	private String no;
	@ExcelAnnotation(exportName = "任务名称")
	private String title;
	@ExcelAnnotation(exportName = "受检单位")
	private String unit;
	@ExcelAnnotation(exportName = "受理人")
	private String acceptUser;
	@ExcelAnnotation(exportName = "登记日期")
	private String createDate;
	@ExcelAnnotation(exportName = "检测类别")
	private String taskType;
	@ExcelAnnotation(exportName = "检测内容")
	private String itemNames;
	@ExcelAnnotation(exportName = "检测内容Id")
	private String itemIds;
	@ExcelAnnotation(exportName = "检测依据")
	private String testStands;
	@ExcelAnnotation(exportName = "报告类型")
	private String reportType;
	@ExcelAnnotation(exportName = "报告份数")
	private String reportNum;
	@ExcelAnnotation(exportName = "取报告方式")
	private String reportPost;
	@ExcelAnnotation(exportName = "出报告日期")
	private String finishDate;//出报告日期
	@ExcelAnnotation(exportName = "检测费用")
	private Double charge;
	
	private String chargeType; //费用类型
	private String chargeNo;   //收费单号
	private String reportDate;
	@ExcelAnnotation(exportName = "生成报告方式")
	private String reportMake; //生成报告方式 1按任务 2按样品类型
	private String isBack;
	private String isCharge;//是否收费
	@ExcelAnnotation(exportName = "客户关联信息")
	private LabSampCustomerVo labSampCustomerVo;
	
	private String otherId;     //其他模块业务数据
	private String otherType;   //业务类型
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
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
	public LabSampCustomerVo getLabSampCustomerVo() {
		return labSampCustomerVo;
	}
	public void setLabSampCustomerVo(LabSampCustomerVo labSampCustomerVo) {
		this.labSampCustomerVo = labSampCustomerVo;
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
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	public String getReportMake() {
		return reportMake;
	}
	public void setReportMake(String reportMake) {
		this.reportMake = reportMake;
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
	
}
