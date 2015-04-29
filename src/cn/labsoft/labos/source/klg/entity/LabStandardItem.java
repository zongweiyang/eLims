package cn.labsoft.labos.source.klg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@SuppressWarnings("serial")
@Entity
@Table(name="lab_klg_standard_item")
public class LabStandardItem extends AbstractBasePo{

	private String remark;
	private LabStandard standard;
	private String standIndexName;
	private LabItem item;
	private LabItem minItem;
	private String scores;//分值
	private String decimalCount;//保留几位小数
	private String minKey; //判定符号
	private String maxKey; //判定符号
	private String minValue; //下限值
	private String maxValue; //上限值
	private String desc; //描述
	private String testTime;//预计需要的检测时间
	private String isGeneralItems; //  是否常规项
	private String sort; //序号
	
	private LabSamType labSamType;//样品类型
	private String itemType;//项目属性  0  常规项目    1   非常规项目
	private String testType;//检测方式  1   日检   2   周检   3  月检
	
	
	public static String[] getStandValues(LabStandardItem labStandardItem){
		 String[] array={"",""};
		if(null!=labStandardItem.getMinValue()
				&&null!=labStandardItem.getMaxValue()
					&&!"".equals(labStandardItem.getMinValue())
							&&!"".equals(labStandardItem.getMaxValue())){
			array[0]=null;
			array[1]=labStandardItem.getMinValue()+"-"+labStandardItem.getMaxValue();
		}else if(null!=labStandardItem.getMaxValue()&&!"".equals(labStandardItem.getMaxValue())){
			array[0]="不大于";
			array[1]=labStandardItem.getMaxValue();
		}else if(null!=labStandardItem.getMinValue()&&!"".equals(labStandardItem.getMinValue())){
			array[0]="不小于";
			array[1]=labStandardItem.getMinValue();
		}else{
			array[0]=" ";
			array[1]=labStandardItem.getDesc();
		}
		return array;
	}
	
	@ManyToOne
	@JoinColumn(name="samtype_id")
	public LabSamType getLabSamType() {
		return labSamType;
	}


	public void setLabSamType(LabSamType labSamType) {
		this.labSamType = labSamType;
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


	public static void main(String[] args){
		LabStandardItem a=new LabStandardItem();
		a.setMinKey("<=");
		
		a.setMinKey(null);
		a.setMaxKey(">=");
	}
	
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	@ManyToOne
	@JoinColumn(name="standard_id", nullable=false)
	public LabStandard getStandard() {
		return standard;
	}
	public void setStandard(LabStandard standard) {
		this.standard = standard;
	}
	@ManyToOne
	@JoinColumn(name="item_id")
	public LabItem getItem() {
		return item;
	}
	public void setItem(LabItem item) {
		this.item = item;
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
	@Column(name="standard_item_desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getStandIndexName() {
		return standIndexName;
	}
	public void setStandIndexName(String standIndexName) {
		this.standIndexName = standIndexName;
	}
	@ManyToOne
	@JoinColumn(name="min_item_id")
	public LabItem getMinItem() {
		return minItem;
	}
	public void setMinItem(LabItem minItem) {
		this.minItem = minItem;
	}
	public String getScores() {
		return scores;
	}
	public void setScores(String scores) {
		this.scores = scores;
	}
	public String getIsGeneralItems() {
		return isGeneralItems;
	}
	public void setIsGeneralItems(String isGeneralItems) {
		this.isGeneralItems = isGeneralItems;
	}
	@Transient
	@Override
	public String getModelName() {
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "标准量化";
	}
	public String getDecimalCount() {
		return decimalCount;
	}
	public void setDecimalCount(String decimalCount) {
		this.decimalCount = decimalCount;
	}
	
	
}
