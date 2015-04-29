package cn.labsoft.labos.business.science.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSciAuthorVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "人员顺序")
	private String no;//顺序
	@ExcelAnnotation(exportName = "人员类型")
	private String type;//类型    
	
	@ExcelAnnotation(exportName = "项目ID")
	private String labSciScienceId;
	
	@ExcelAnnotation(exportName = "id")
	private String id;
	@ExcelAnnotation(exportName = "人员名称")
	private String userName;
	@ExcelAnnotation(exportName = "人员id")
	private String userId;
	@ExcelAnnotation(exportName = "职责")
	private String duty;
	@ExcelAnnotation(exportName = "职称")
	private String techTitle;
	
	@ExcelAnnotation(exportName = "研究领域")
	private String learn;
	@ExcelAnnotation(exportName = "贡献率")
	private String contributionRate;//贡献率
	@ExcelAnnotation(exportName = "备注")
	private String remark;//备注
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;//备注
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabSciScienceId() {
		return labSciScienceId;
	}
	public void setLabSciScienceId(String labSciScienceId) {
		this.labSciScienceId = labSciScienceId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getTechTitle() {
		return techTitle;
	}
	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}
	public String getLearn() {
		return learn;
	}
	public void setLearn(String learn) {
		this.learn = learn;
	}
	public String getContributionRate() {
		return contributionRate;
	}
	public void setContributionRate(String contributionRate) {
		this.contributionRate = contributionRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
}
