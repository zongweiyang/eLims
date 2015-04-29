package cn.labsoft.labos.business.science.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name = "lab_sci_author")
public class LabSciAuthor extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String no;//顺序
	private String type;//类型
	private String userId;
    private String userName;
	private LabSciScience labSciScience;
	private String duty;
	private String techTitle;
	private String learn;//研究领域
	private String contributionRate;//贡献率
	private String remark;//备注

	@Column(length = 11)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(length = 64)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 11)
	public String getContributionRate() {
		return contributionRate;
	}

	public void setContributionRate(String contributionRate) {
		this.contributionRate = contributionRate;
	}

	@Column(length = 256)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(length = 256)
	public String getLearn() {
		return learn;
	}

	public void setLearn(String learn) {
		this.learn = learn;
	}
	
	
	@Column(length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(length = 64)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ManyToOne
	@JoinColumn(name="science_id")
	public LabSciScience getLabSciScience() {
		return labSciScience;
	}

	public void setLabSciScience(LabSciScience labSciScience) {
		this.labSciScience = labSciScience;
	}

	@Column(length = 64)
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(length = 64)
	public String getTechTitle() {
		return techTitle;
	}

	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}

	@Override
	@Transient
	public String getModelName() {
		return "科研成果";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "成果作者";
	}

}
