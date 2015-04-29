package cn.labsoft.labos.base.configs.vo;


import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * <strong>Title : 系统参数配置 </strong>. <br>
 * <strong>Description : 所有系统可变参数配置</strong> <br>
 * <strong>Create on : Feb 19, 2014 2:07:55 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Quinn Qu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

@SuppressWarnings("serial")
public class LabConfigVo extends BaseVo {

	// Fields

	private String id;
	private String name;
	private String code;
	private String initValue;//初始值
	private String value;
	private String isUsed;
	private String desc;
	private String remark;
	private String category;//参数类别


	// Property accessors

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
}