package cn.labsoft.labos.source.klg.vo;



import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
/**
 * @title 检测项目 LabItemVo
 * @author QUINN
 */

@SuppressWarnings("serial")
public class LabItemVo extends BaseVo {

	//必备字段
	private String id;
	private String parentId; //父类
	private String parentName;
	@ExcelAnnotation(exportName = "项目名称")
	private String name;  
	private String fullName ;  //项目全称
	private String code;  //项目编码
	@ExcelAnnotation(exportName = "项目单位")
	private String unit;  
	private String round; //项目检测周期 默认单位为天
	private String remark;//备注
	private int sort;
	private String isDel;
	private String nameIndex;//索引
	@ExcelAnnotation(exportName = "价格")
	private Double price; 
	private Double priceListory;//历史价格
	private int number;
	//以下字段根据系统需要可选
	private String childExistType;  //子类存在方式 0单选 1多选
	@ExcelAnnotation(exportName = "项目类别")
	private String type; 
	private String typex;   	//非标项目的项目类别
	private String priceType;  //收费方式 0按项目，1按数量
	private String categoryNames; //分类集合
	private String categoryIds; //分类集合
	private String appId;
	@ExcelAnnotation(exportName = "所用仪器")
	private String appName;
	//备用字段
	@ExcelAnnotation(exportName = "标准分值")
	private String demo1; 
	@ExcelAnnotation(exportName = "工时(单位:小时)")
	private String demo2;
	private String demo3; 
	private String demo4;  //所在类别
	private String demo5;
	private boolean hasChildren;
	private String isGeneralItems; //  是否常规项
	private List<LabItemVo> itemList;
	private List<LabStandardVo> standardList;
	private List<LabMethodVo> methodList;
	private String index;
	private String standId;
	private String standName;
	private String methodId;
	private String methodName;
	
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDemo1() {
		return demo1;
	}
	public void setDemo1(String demo1) {
		this.demo1 = demo1;
	}
	public String getDemo2() {
		return demo2;
	}
	public void setDemo2(String demo2) {
		this.demo2 = demo2;
	}
	public String getDemo3() {
		return demo3;
	}
	public void setDemo3(String demo3) {
		this.demo3 = demo3;
	}
	public String getDemo4() {
		return demo4;
	}
	public void setDemo4(String demo4) {
		this.demo4 = demo4;
	}
	public String getDemo5() {
		return demo5;
	}
	public void setDemo5(String demo5) {
		this.demo5 = demo5;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public boolean getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getChildExistType() {
		return childExistType;
	}
	public void setChildExistType(String childExistType) {
		this.childExistType = childExistType;
	}
	public String getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public String getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getPriceListory() {
		return priceListory;
	}
	public void setPriceListory(Double priceListory) {
		this.priceListory = priceListory;
	}
	public String getTypex() {
		return typex;
	}
	public void setTypex(String typex) {
		this.typex = typex;
	}
	public List<LabItemVo> getItemList() {
		return itemList;
	}
	public void setItemList(List<LabItemVo> itemList) {
		this.itemList = itemList;
	}
	public String getIsGeneralItems() {
		return isGeneralItems;
	}
	public void setIsGeneralItems(String isGeneralItems) {
		this.isGeneralItems = isGeneralItems;
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
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public List<LabMethodVo> getMethodList() {
		return methodList;
	}
	public void setMethodList(List<LabMethodVo> methodList) {
		this.methodList = methodList;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public List<LabStandardVo> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<LabStandardVo> standardList) {
		this.standardList = standardList;
	}
	public String getStandId() {
		return standId;
	}
	public void setStandId(String standId) {
		this.standId = standId;
	}
	public String getStandName() {
		return standName;
	}
	public void setStandName(String standName) {
		this.standName = standName;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
}