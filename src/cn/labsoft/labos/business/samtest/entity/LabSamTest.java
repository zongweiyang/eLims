package cn.labsoft.labos.business.samtest.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Entity;

@Entity
public class LabSamTest extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//样品名称
	private String labSamId;
	private String labSamName;
	private String sampCode;
	private String labSamTypeId;
	private String labSamTypeName;
	private String methodId;//检测方法Id
	private String methodName;//检测方法名称
	private String itemId;       //分析项目ID
	private String itemName;     //分析项目名称
	private String standardId;//检测标准Id
	private String standardName;//检测标准名称
	private String describeWri;//标准下对应的标准值描述
	private String describeFormula;//标准下标准值范围
	private String orgId;//检测ID
	private String orgName;//检测室名称
	private String finishDate;//截止日期
	
	private String taskId;   //任务ID
	private String taskNo;   //任务编号
	private String taskType; //检测类别
	
	private String isSued;    //是否下达
	private String isTask;    //是否分配
	private String isTest;    //是否检验
	private String isCheck;   //是否校验
	private String isBack;    //是否退回
	private String testManId;//检测人ID
	private String testManName;//检测人姓名
	private String testTime;  //检测日期
	private String checkManId;//检验人ID
	private String checkManName;//检验人姓名
	
	private String temperature;//温度
	private String humidity;//湿度
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String value;  //检测结果
	private String result;//判定结果
	private LabSamTestBeatch labSamTestBeatch;
	@ManyToOne
	@JoinColumn(name="beatch_id")	
	public LabSamTestBeatch getLabSamTestBeatch() {
		return labSamTestBeatch;
	}

	public void setLabSamTestBeatch(LabSamTestBeatch labSamTestBeatch) {
		this.labSamTestBeatch = labSamTestBeatch;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getStandardId() {
		return this.standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	@Transient
	@Override
	public String getModelName() {
		return "检测管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "检测样品数据";
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="sam_id")
	public String getLabSamId() {
		return labSamId;
	}

	public void setLabSamId(String labSamId) {
		this.labSamId = labSamId;
	}
	@Column(name="sam_name")
	public String getLabSamName() {
		return labSamName;
	}

	public void setLabSamName(String labSamName) {
		this.labSamName = labSamName;
	}

	

	public String getSampCode() {
		return sampCode;
	}

	public void setSampCode(String sampCode) {
		this.sampCode = sampCode;
	}
	@Column(name="sam_type_id")
	public String getLabSamTypeId() {
		return labSamTypeId;
	}

	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}
	@Column(name="sam_type_name")
	public String getLabSamTypeName() {
		return labSamTypeName;
	}

	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getIsTask() {
		return isTask;
	}

	public void setIsTask(String isTask) {
		this.isTask = isTask;
	}

	public String getTestManId() {
		return testManId;
	}

	public void setTestManId(String testManId) {
		this.testManId = testManId;
	}

	public String getTestManName() {
		return testManName;
	}

	public void setTestManName(String testManName) {
		this.testManName = testManName;
	}

	public String getCheckManId() {
		return checkManId;
	}

	public void setCheckManId(String checkManId) {
		this.checkManId = checkManId;
	}

	public String getCheckManName() {
		return checkManName;
	}

	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}

	

	public String getDescribeWri() {
		return describeWri;
	}

	public void setDescribeWri(String describeWri) {
		this.describeWri = describeWri;
	}

	public String getDescribeFormula() {
		return describeFormula;
	}

	public void setDescribeFormula(String describeFormula) {
		this.describeFormula = describeFormula;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsSued() {
		return isSued;
	}

	public void setIsSued(String isSued) {
		this.isSued = isSued;
	}

	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
	
}
