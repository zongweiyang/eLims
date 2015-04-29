package cn.labsoft.labos.business.samtest.vo;

import java.util.List;

import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSamTestVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "样品名称")
	private String name;
	@ExcelAnnotation(exportName = "批内编号")
	private String sampCode;
	private String labSamId;
	@ExcelAnnotation(exportName = "样品名称")
	private String labSamName;
	private String labSamTypeId;
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeName;
	@ExcelAnnotation(exportName = "检测方法Id")
	private String methodId;
	@ExcelAnnotation(exportName = "检测方法名称")
	private String methodName;
	@ExcelAnnotation(exportName = "检测标准Id")
	private String standardId;
	@ExcelAnnotation(exportName = "检测标准名称")
	private String standardName;
	@ExcelAnnotation(exportName = "任务ID")
	private String taskId;//任务ID
	@ExcelAnnotation(exportName = "任务编号")
	private String taskNo;   //任务编号
	@ExcelAnnotation(exportName = "检测类别")
	private String taskType; //检测类别
	@ExcelAnnotation(exportName = "截止日期")
	private String finishDate;//截止日期
	
	@ExcelAnnotation(exportName = "分析项目ID")
	private String itemId;      
	@ExcelAnnotation(exportName = "分析项目名称")
	private String itemName;     
	@ExcelAnnotation(exportName = "分析项目IDs")
	private String itemIds;      
	@ExcelAnnotation(exportName = "分析项目名称s")
	private String itemNames;     
	@ExcelAnnotation(exportName = "检测科室ID")
	private String orgId;
	@ExcelAnnotation(exportName = "检测科室名称")
	private String orgName;
	@ExcelAnnotation(exportName = "样品个数")
	private Integer sampNum;
	private List<LabSampRegisterVo> listLabSampRegisterVo;
	@ExcelAnnotation(exportName = "是否存在")
	private String isHave="0";
	@ExcelAnnotation(exportName = "已分配样品个数")
	private Integer sampNumTask;
	@ExcelAnnotation(exportName = "是否分配")
	private String isTask;
	private String isTest;    //是否检验
	private String isCheck;   //是否校验
	@ExcelAnnotation(exportName = "检测人ID")
	private String testManId;
	@ExcelAnnotation(exportName = "检测人姓名")
	private String testManName;
	@ExcelAnnotation(exportName = "检测日期")
	private String testTime;  //检测日期
	@ExcelAnnotation(exportName = "检验人ID")
	private String checkManId;
	@ExcelAnnotation(exportName = "检验人姓名")
	private String checkManName;
	@ExcelAnnotation(exportName = "任务ids")
	private String taskIds;
	@ExcelAnnotation(exportName = "批次号")
	private String samBeatchNo;
	@ExcelAnnotation(exportName = "备注")
	private String demo;
	@ExcelAnnotation(exportName = "跨行数")
	private Integer spanRow;
	@ExcelAnnotation(exportName = "序列")
	private Integer sort;
	private String isSued;    //是否下达
	private String beatchId;
	/**
	 * 数据录入
	 */
	@ExcelAnnotation(exportName = "标准下对应的标准值描述")
	private String describeWri;
	@ExcelAnnotation(exportName = "标准下标准值范围")
	private String describeFormula;
	@ExcelAnnotation(exportName = "温度")
	private String temperature;
	@ExcelAnnotation(exportName = "湿度")
	private String humidity;
	@ExcelAnnotation(exportName = "检测结果")
	private String value;
	@ExcelAnnotation(exportName = "判定结果")
	private String result;//
	@ExcelAnnotation(exportName = "仪器ID")
	private String apparaId;
	@ExcelAnnotation(exportName = "仪器名称")
	private String apparaName;
	@ExcelAnnotation(exportName = "开始时间")
	private String startTime;
	@ExcelAnnotation(exportName = "结束时间")
	private String endTime;
	private String startDate;//开始时间
	private String endDate;//结束时间
	/**
	 * 数据校验
	 */
	private String isBack;
	private List<LabDemoVo> listLabDemoVo;
	private List<LabDemoVo> listTitle;
	private List<LabSamTestVo> listLabSamTest;
	private List<LabStandardItemVo> listLabStandardItemVo;
	
	public List<LabSamTestVo> getListLabSamTest() {
		return listLabSamTest;
	}
	public void setListLabSamTest(List<LabSamTestVo> listLabSamTest) {
		this.listLabSamTest = listLabSamTest;
	}
	public List<LabDemoVo> getListTitle() {
		return listTitle;
	}
	public void setListTitle(List<LabDemoVo> listTitle) {
		this.listTitle = listTitle;
	}
	public List<LabDemoVo> getListLabDemoVo() {
		return listLabDemoVo;
	}
	public void setListLabDemoVo(List<LabDemoVo> listLabDemoVo) {
		this.listLabDemoVo = listLabDemoVo;
	}
	public String getIsHave() {
		return isHave;
	}
	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}
	
	public Integer getSampNum() {
		return sampNum;
	}
	public void setSampNum(Integer sampNum) {
		this.sampNum = sampNum;
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
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSampCode() {
		return this.sampCode;
	}

	public void setSampCode(String sampCode) {
		this.sampCode = sampCode;
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
	public String getLabSamId() {
		return labSamId;
	}
	public void setLabSamId(String labSamId) {
		this.labSamId = labSamId;
	}
	public String getLabSamName() {
		return labSamName;
	}
	public void setLabSamName(String labSamName) {
		this.labSamName = labSamName;
	}
	public String getLabSamTypeId() {
		return labSamTypeId;
	}
	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}
	public String getLabSamTypeName() {
		return labSamTypeName;
	}
	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}
	public List<LabSampRegisterVo> getListLabSampRegisterVo() {
		return listLabSampRegisterVo;
	}
	public void setListLabSampRegisterVo(List<LabSampRegisterVo> listLabSampRegisterVo) {
		this.listLabSampRegisterVo = listLabSampRegisterVo;
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
	public String getItemIds() {
		return itemIds;
	}
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	
	public Integer getSampNumTask() {
		return sampNumTask;
	}
	public void setSampNumTask(Integer sampNumTask) {
		this.sampNumTask = sampNumTask;
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
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getTaskIds() {
		return taskIds;
	}
	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}
	public String getSamBeatchNo() {
		return samBeatchNo;
	}
	public void setSamBeatchNo(String samBeatchNo) {
		this.samBeatchNo = samBeatchNo;
	}
	public Integer getSpanRow() {
		return spanRow;
	}
	public void setSpanRow(Integer spanRow) {
		this.spanRow = spanRow;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	public List<LabStandardItemVo> getListLabStandardItemVo() {
		return listLabStandardItemVo;
	}
	public void setListLabStandardItemVo(List<LabStandardItemVo> listLabStandardItemVo) {
		this.listLabStandardItemVo = listLabStandardItemVo;
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
	public String getApparaId() {
		return apparaId;
	}
	public void setApparaId(String apparaId) {
		this.apparaId = apparaId;
	}
	public String getApparaName() {
		return apparaName;
	}
	public void setApparaName(String apparaName) {
		this.apparaName = apparaName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIsSued() {
		return isSued;
	}
	public void setIsSued(String isSued) {
		this.isSued = isSued;
	}
	public String getBeatchId() {
		return beatchId;
	}
	public void setBeatchId(String beatchId) {
		this.beatchId = beatchId;
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
	@Override
	public String getStartDate() {
		return startDate;
	}
	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Override
	public String getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
