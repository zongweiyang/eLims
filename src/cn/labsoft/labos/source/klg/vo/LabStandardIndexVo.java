package cn.labsoft.labos.source.klg.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
/**
 * 产品指标()
 */
@SuppressWarnings("serial")
public class LabStandardIndexVo extends BaseVo {
	
	private String id;
	private String standardId;   //标准
	private String standardCode;   //标准号
	private String standardName;   //标准名
	
	private String name;   //指标名称
	private String remark;//备注
	private int sort; //序号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
