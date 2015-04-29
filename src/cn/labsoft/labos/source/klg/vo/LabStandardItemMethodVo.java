package cn.labsoft.labos.source.klg.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 产品标准与项目关联表-方法 关系表
 * @author QUINN
 * @category many to many
 */
public class LabStandardItemMethodVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -738592141654027066L;
	private String id;
	private String remark;
	private String standardId;
	private String standardName;
	private String itemId;
	private String itemName;
	private String methodId;
	private String methodName;
	private String methodIds;
	private String flag;
	private String type;
	private String demo1;
	private String demo2;
	private String demo3;
	private String demo4;
	private String demo5;
	private String demo6;
	private String demo7;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
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
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public String getMethodIds() {
		return methodIds;
	}
	public void setMethodIds(String methodIds) {
		this.methodIds = methodIds;
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
	public String getDemo6() {
		return demo6;
	}
	public void setDemo6(String demo6) {
		this.demo6 = demo6;
	}
	public String getDemo7() {
		return demo7;
	}
	public void setDemo7(String demo7) {
		this.demo7 = demo7;
	}
	
}
