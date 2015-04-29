package cn.labsoft.labos.source.klg.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 产品标准关联项目表
 * 
 * @author QUINN
 * @category many to many
 */
public class LabStandardItemVo extends BaseVo {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String remark;
	private String standardId;
	private String standardIds;
	private String standardName;
	private String standIndexId;
	private String standIndexName;
	private String itemId;
	private String itemIds;
	private String itemName;
	private String typex;
	private String itemUnit;
	private String minItemId;
	private String minItemName;
	private String minItemUnit;
	private String scores;
	private String decimalCount;//保留几位小数
	private String minKey; // 判定符号
	private String maxKey; // 判定符号
	private String minValue; // 下限值
	private String maxValue; // 上限值
	private String key; // 判定符号
	private String value; // 限值
	private String desc; // 描述
	private String[] itemsIds;
	private String methodIds;
	private String methodNames;
	private Double price;// 费用
	private String isHasMinItem;// 是否含有小项
	private String flag;
	private List<LabItemVo> itemVoList;
	private List<LabMethodVo> methodList;
	private List<LabStandardItemVo> minSItemList; // 小项指标列表
	private List<LabStandardItemVo> indexSItemList; // 产品标准分类

	private String inspectorIds;// （检测员评级）
	private String inspectorNames;
	private String isGeneralItems; //  是否常规项
	
	private String TestTime; // 描述
	private String demo;
	
	
	private String labSamTypeId;//样品类型
	private String labSamTypeName;
	private String itemType;//项目属性  0  常规项目    1   非常规项目
	private String testType;//检测方式  1   日检   2   周检   3  月检
	
	
	
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

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getInspectorIds() {
		return inspectorIds;
	}

	public void setInspectorIds(String inspectorIds) {
		this.inspectorIds = inspectorIds;
	}

	public String getInspectorNames() {
		return inspectorNames;
	}

	public void setInspectorNames(String inspectorNames) {
		this.inspectorNames = inspectorNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getMinKey() {
		return minKey;
	}

	public void setMinKey(String minKey) {
		this.minKey = minKey;
	}

	public String getMaxKey() {
		return maxKey;
	}

	public void setMaxKey(String maxKey) {
		this.maxKey = maxKey;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getStandIndexId() {
		return standIndexId;
	}

	public void setStandIndexId(String standIndexId) {
		this.standIndexId = standIndexId;
	}

	public String getStandIndexName() {
		return standIndexName;
	}

	public void setStandIndexName(String standIndexName) {
		this.standIndexName = standIndexName;
	}

	public String getIsHasMinItem() {
		return isHasMinItem;
	}

	public void setIsHasMinItem(String isHasMinItem) {
		this.isHasMinItem = isHasMinItem;
	}

	public List<LabItemVo> getItemVoList() {
		return itemVoList;
	}

	public void setItemVoList(List<LabItemVo> itemVoList) {
		this.itemVoList = itemVoList;
	}

	public String getMinItemId() {
		return minItemId;
	}

	public void setMinItemId(String minItemId) {
		this.minItemId = minItemId;
	}

	public String getMinItemName() {
		return minItemName;
	}

	public void setMinItemName(String minItemName) {
		this.minItemName = minItemName;
	}

	public String getMinItemUnit() {
		return minItemUnit;
	}

	public void setMinItemUnit(String minItemUnit) {
		this.minItemUnit = minItemUnit;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<LabStandardItemVo> getMinSItemList() {
		return minSItemList;
	}

	public void setMinSItemList(List<LabStandardItemVo> minSItemList) {
		this.minSItemList = minSItemList;
	}

	public String getMethodIds() {
		return methodIds;
	}

	public void setMethodIds(String methodIds) {
		this.methodIds = methodIds;
	}

	public String getMethodNames() {
		return methodNames;
	}

	public void setMethodNames(String methodNames) {
		this.methodNames = methodNames;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<LabStandardItemVo> getIndexSItemList() {
		return indexSItemList;
	}

	public void setIndexSItemList(List<LabStandardItemVo> indexSItemList) {
		this.indexSItemList = indexSItemList;
	}

	public String getStandardIds() {
		return standardIds;
	}

	public void setStandardIds(String standardIds) {
		this.standardIds = standardIds;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public List<LabMethodVo> getMethodList() {
		return methodList;
	}

	public void setMethodList(List<LabMethodVo> methodList) {
		this.methodList = methodList;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTypex() {
		return typex;
	}

	public void setTypex(String typex) {
		this.typex = typex;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getTestTime() {
		return TestTime;
	}

	public void setTestTime(String testTime) {
		TestTime = testTime;
	}

	public String getIsGeneralItems() {
		return isGeneralItems;
	}

	public void setIsGeneralItems(String isGeneralItems) {
		this.isGeneralItems = isGeneralItems;
	}


	public String[] getItemsIds() {
		return itemsIds;
	}

	public void setItemsIds(String[] itemsIds) {
		this.itemsIds = itemsIds;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getDecimalCount() {
		return decimalCount;
	}

	public void setDecimalCount(String decimalCount) {
		this.decimalCount = decimalCount;
	}

	
}
