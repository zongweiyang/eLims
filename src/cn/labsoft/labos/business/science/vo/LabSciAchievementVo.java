package cn.labsoft.labos.business.science.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
public class LabSciAchievementVo extends BaseVo {
	
	@ExcelAnnotation(exportName = "类型")
	private String type;//PAPER：论文管理   BOOK：著作管理      PATENT：发明专利      AWARD：获奖管理
	
	@ExcelAnnotation(exportName = "id")
    private String id;
	@ExcelAnnotation(exportName = "成果类型")
	private String achievementType;//成果类型
	@ExcelAnnotation(exportName = "成果名称")
	private String name;
	@ExcelAnnotation(exportName = "成果编号")
	private String achievementNum;//成果编号
	@ExcelAnnotation(exportName = "认证组织")
    private String organization;//认证组织
	@ExcelAnnotation(exportName = "发表成果名称")
    private String publishedWorks;//发表成果名称
	@ExcelAnnotation(exportName = "研究学科")
    private String researchContents;//研究学科
	@ExcelAnnotation(exportName = "研究内容")
    private String contents;//研究内容
	@ExcelAnnotation(exportName = "字数")
    private double wordNumber;//字数
	@ExcelAnnotation(exportName = "认证时间")
    private String date;//认证时间
	@ExcelAnnotation(exportName = "项目来源类型")
    private String sourceType;//项目来源类型
	
    
	@ExcelAnnotation(exportName = "issn号")//论文收录情况
    private String issnNo;//issn号
	@ExcelAnnotation(exportName = "关键字")
    private String keyWords;//关键字
	@ExcelAnnotation(exportName = "摘要")
    private String tabloid;//摘要
    
	@ExcelAnnotation(exportName = "项目ID")   
	private String labSciScienceId;//项目ID
	@ExcelAnnotation(exportName = "项目名称")
    private String labSciScienceName;//项目名称
	@ExcelAnnotation(exportName = "项目编号")
    private String labSciScienceNum;//项目编号
	
	@ExcelAnnotation(exportName = "第一作者id") 
	private String fristAuthorId;//第一作者id
	@ExcelAnnotation(exportName = "第一作者名称") 
	private String fristAuthorName;//第一作者名称
	@ExcelAnnotation(exportName = "其他作者id") 
	private String otherAuthorId;//其他作者id
	@ExcelAnnotation(exportName = "其他作者名称") 
	private String otherAuthorName;//其他作者名称
	@ExcelAnnotation(exportName = "备注") 
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAchievementType() {
		return achievementType;
	}
	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAchievementNum() {
		return achievementNum;
	}
	public void setAchievementNum(String achievementNum) {
		this.achievementNum = achievementNum;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPublishedWorks() {
		return publishedWorks;
	}
	public void setPublishedWorks(String publishedWorks) {
		this.publishedWorks = publishedWorks;
	}
	public String getResearchContents() {
		return researchContents;
	}
	public void setResearchContents(String researchContents) {
		this.researchContents = researchContents;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public double getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(double wordNumber) {
		this.wordNumber = wordNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getIssnNo() {
		return issnNo;
	}
	public void setIssnNo(String issnNo) {
		this.issnNo = issnNo;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getTabloid() {
		return tabloid;
	}
	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
	}
	public String getLabSciScienceId() {
		return labSciScienceId;
	}
	public void setLabSciScienceId(String labSciScienceId) {
		this.labSciScienceId = labSciScienceId;
	}
	public String getLabSciScienceName() {
		return labSciScienceName;
	}
	public void setLabSciScienceName(String labSciScienceName) {
		this.labSciScienceName = labSciScienceName;
	}
	public String getFristAuthorId() {
		return fristAuthorId;
	}
	public void setFristAuthorId(String fristAuthorId) {
		this.fristAuthorId = fristAuthorId;
	}
	public String getFristAuthorName() {
		return fristAuthorName;
	}
	public void setFristAuthorName(String fristAuthorName) {
		this.fristAuthorName = fristAuthorName;
	}
	public String getOtherAuthorId() {
		return otherAuthorId;
	}
	public void setOtherAuthorId(String otherAuthorId) {
		this.otherAuthorId = otherAuthorId;
	}
	public String getOtherAuthorName() {
		return otherAuthorName;
	}
	public void setOtherAuthorName(String otherAuthorName) {
		this.otherAuthorName = otherAuthorName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLabSciScienceNum() {
		return labSciScienceNum;
	}
	public void setLabSciScienceNum(String labSciScienceNum) {
		this.labSciScienceNum = labSciScienceNum;
	}
    
    
}
