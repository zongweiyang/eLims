package cn.labsoft.labos.common.makecode.vo;

public class Prop {
	String name;
	String nameChinese;
	String showType;//展现方式
	String type;//数据类型
	String length;//数据长度
	String validate;//是否必填
	String validateName;
	String isShowOnList;//是否List显示
	String isSortList;//是否排序
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameChinese() {
		return nameChinese;
	}
	public void setNameChinese(String nameChinese) {
		this.nameChinese = nameChinese;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getIsShowOnList() {
		return isShowOnList;
	}
	public void setIsShowOnList(String isShowOnList) {
		this.isShowOnList = isShowOnList;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getIsSortList() {
		return isSortList;
	}
	public void setIsSortList(String isSortList) {
		this.isSortList = isSortList;
	}
	public String getValidateName() {
		return validateName;
	}
	public void setValidateName(String validateName) {
		this.validateName = validateName;
	}

	
}
