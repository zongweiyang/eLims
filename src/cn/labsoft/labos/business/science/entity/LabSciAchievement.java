package cn.labsoft.labos.business.science.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name="lab_sci_achievement")
public class LabSciAchievement extends AbstractBasePo{
	private static final long serialVersionUID = 1L;
	
	private String type;//成果类型

	private String name;
	private String achievementType;
	private String achievementNum;//成果编号
    private String organization;//认证组织
    private String publishedWorks;//发表成果名称
    private String researchContents;//研究学科
    private String contents;//研究内容
    private double wordNumber;//字数
	private String fristAuthorId;//第一作者id
	private String fristAuthorName;//第一作者名称
	private String otherAuthorId;//其他作者id
	private String otherAuthorName;//其他作者名称
    private String date;//认证时间
    private String sourceType;//项目来源类型
    
    //论文收录情况
    private String issnNo;//issn号
    private String keyWords;//关键字
    private String tabloid;//摘要
    
    private LabSciScience labSciScience;//弱关联项目
    private String remark;
    
	@ManyToOne
	@JoinColumn(name="science_id")
	public LabSciScience getLabSciScience() {
		return labSciScience;
	}

	public void setLabSciScience(LabSciScience labSciScience) {
		this.labSciScience = labSciScience;
	}

    @Column(length=64)
	public String getAchievementType(){
		return achievementType;
	}

	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}
	

	@Column(length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=64)
	public String getType() {
		return type;
	}

	@Column(length=32)
	public String getFristAuthorId() {
		return fristAuthorId;
	}

	public void setFristAuthorId(String fristAuthorId) {
		this.fristAuthorId = fristAuthorId;
	}

	@Column(length=32)
	public String getFristAuthorName() {
		return fristAuthorName;
	}

	public void setFristAuthorName(String fristAuthorName) {
		this.fristAuthorName = fristAuthorName;
	}

	@Column(length=128)
	public String getOtherAuthorId() {
		return otherAuthorId;
	}

	public void setOtherAuthorId(String otherAuthorId) {
		this.otherAuthorId = otherAuthorId;
	}

	@Column(length=128)
	public String getOtherAuthorName() {
		return otherAuthorName;
	}

	public void setOtherAuthorName(String otherAuthorName) {
		this.otherAuthorName = otherAuthorName;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(length=64)
	public String getAchievementNum() {
		return achievementNum;
	}

	public void setAchievementNum(String achievementNum) {
		this.achievementNum = achievementNum;
	}

	@Column(length=64)
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(length=32)
	public String getPublishedWorks() {
		return publishedWorks;
	}

	public void setPublishedWorks(String publishedWorks) {
		this.publishedWorks = publishedWorks;
	}

	@Column(length=128)
	public String getResearchContents() {
		return researchContents;
	}

	public void setResearchContents(String researchContents) {
		this.researchContents = researchContents;
	}

	@Column(length=512)
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Column(length=11)
	public double getWordNumber() {
		return wordNumber;
	}

	public void setWordNumber(double wordNumber) {
		this.wordNumber = wordNumber;
	}

	@Column(length=32)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(length=32)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(length=32)
	public String getIssnNo() {
		return issnNo;
	}

	public void setIssnNo(String issnNo) {
		this.issnNo = issnNo;
	}

	@Column(length=32)
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@Column(length=32)
	public String getTabloid() {
		return tabloid;
	}

	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
	}
	
	@Column(length=512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "科研项目";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "科研成果";
	}



}
