package cn.labsoft.labos.source.klg.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;


@Entity
@Table(name="lab_klg_item")
public class LabItem extends AbstractBasePo {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -6783001019112829859L;
	//必备字段
	private String name;  //项目名称
	private String fullName ;  //项目全称
	private String code;  //项目编码
	private String unit;  //单位
	private String round; //项目检测周期 默认单位为天
	private String remark;//备注
	private String nameIndex;//索引
	private Double price; //单价
	private String appId;
	private String appName;//所用仪器
	private String categoryNames; //分类集合
	private String categoryIds; //分类集合
	//以下字段根据系统需要可选
	private String type;   		//1标准项目和0非标项目
	private String typex;   	//非标项目的项目类别
	private String priceType;  //收费方式 0按项目，1按数量
	private String isGeneralItems; //  是否常规项
	
	private String demo1;
	private String demo2;
	private String demo3;    
	private String demo4;
	private String demo5;
	
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
	@Column(name="item_type")
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
	@Transient
	@Override
	public String getModelName() {
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "检测项目";
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
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
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	

}