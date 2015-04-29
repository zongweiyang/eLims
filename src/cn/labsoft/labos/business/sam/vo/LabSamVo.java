package cn.labsoft.labos.business.sam.vo;


import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * @title 样品表
 * 
 */
public class LabSamVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeId;  
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeName; 
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "来样编号")
	private String sampNo;//来样编号
	@ExcelAnnotation(exportName = "样品名称")
	private String name;//样品名称
	private String ename;//英文名称
	@ExcelAnnotation(exportName = "样品性状")
	private String shape;//样品性状
	@ExcelAnnotation(exportName = "保存方式")
	private String saveMode;//保存方式
	@ExcelAnnotation(exportName = "存放位置")
	private String position;//存放位置
	private String saveOrg;// 保管科室
	private String saveOrgName;// 保管科室
	@ExcelAnnotation(exportName = "保管人")
	private String saveUser;// 保管人
	private String saveUserName;
	@ExcelAnnotation(exportName = "样品有效期")
	private int safeDate; // 样品有效期
	@ExcelAnnotation(exportName = "包装方式")
	private String packManner;//包装方式
	@ExcelAnnotation(exportName = "样品数量")
	private String total;//样品数量
	@ExcelAnnotation(exportName = "备注")
	private String remark;//备注
	@ExcelAnnotation(exportName = "样品类型")
	private String samTypeId; // 样品类型
	@ExcelAnnotation(exportName = "样品类型名称")
	private String samTypeName; // 样品类型
	@ExcelAnnotation(exportName = "样品规格")
	private String specifications;//规格
	@ExcelAnnotation(exportName = "样品等级")
	private String grade;//等级
	@ExcelAnnotation(exportName = "采样日期")
	private String sampDate;//采样日期
	@ExcelAnnotation(exportName = "采样地点")
	private String sampAddr;//采样地点
	private String taskCode;//任务编号
	@ExcelAnnotation(exportName = "样品编号")
	private String sampCode;//样品编号
	@ExcelAnnotation(exportName = "原编号")
	private String oldNo; 
	@ExcelAnnotation(exportName = "序号")
	private String seqNum;//序号
	private List<LabSamVo> labSamVoList;
	@ExcelAnnotation(exportName = "项目名称")
	private String itemName;
	private String itemId;
	@ExcelAnnotation(exportName = "样品用途")
	private String isDestory;
	@ExcelAnnotation(exportName = "领样人")
	private String drawPerson;//领样人
	@ExcelAnnotation(exportName = "领样时间")
	private String drawTime;   //领样时间
	private String isDraw;//是否领取
	private String isDiv;//是否已分样

	public String getIsDiv() {
		return isDiv;
	}


	public void setIsDiv(String isDiv) {
		this.isDiv = isDiv;
	}


	public String getIsDraw() {
		return isDraw;
	}


	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public String getShape() {
		return shape;
	}


	public void setShape(String shape) {
		this.shape = shape;
	}


	public String getSaveMode() {
		return saveMode;
	}


	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getSaveOrg() {
		return saveOrg;
	}


	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}


	public String getSaveUser() {
		return saveUser;
	}


	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}


	public int getSafeDate() {
		return safeDate;
	}


	public void setSafeDate(int safeDate) {
		this.safeDate = safeDate;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getPackManner() {
		return packManner;
	}


	public void setPackManner(String packManner) {
		this.packManner = packManner;
	}


	public String getSamTypeId() {
		return samTypeId;
	}


	public void setSamTypeId(String samTypeId) {
		this.samTypeId = samTypeId;
	}


	public String getSamTypeName() {
		return samTypeName;
	}


	public void setSamTypeName(String samTypeName) {
		this.samTypeName = samTypeName;
	}


	public String getSaveOrgName() {
		return saveOrgName;
	}


	public void setSaveOrgName(String saveOrgName) {
		this.saveOrgName = saveOrgName;
	}


	public List<LabSamVo> getLabSamVoList() {
		return labSamVoList;
	}


	public void setLabSamVoList(List<LabSamVo> labSamVoList) {
		this.labSamVoList = labSamVoList;
	}


	public String getSaveUserName() {
		return saveUserName;
	}


	public void setSaveUserName(String saveUserName) {
		this.saveUserName = saveUserName;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getSpecifications() {
		return specifications;
	}


	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSampDate() {
		return sampDate;
	}


	public void setSampDate(String sampDate) {
		this.sampDate = sampDate;
	}


	public String getSampAddr() {
		return sampAddr;
	}


	public void setSampAddr(String sampAddr) {
		this.sampAddr = sampAddr;
	}


	public String getTaskCode() {
		return taskCode;
	}


	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}


	public String getSampCode() {
		return sampCode;
	}


	public void setSampCode(String sampCode) {
		this.sampCode = sampCode;
	}


	public String getSeqNum() {
		return seqNum;
	}


	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}


	public String getIsDestory() {
		return isDestory;
	}


	public void setIsDestory(String isDestory) {
		this.isDestory = isDestory;
	}


	public String getDrawPerson() {
		return drawPerson;
	}


	public void setDrawPerson(String drawPerson) {
		this.drawPerson = drawPerson;
	}


	public String getDrawTime() {
		return drawTime;
	}


	public void setDrawTime(String drawTime) {
		this.drawTime = drawTime;
	}


	public String getOldNo() {
		return oldNo;
	}


	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}


	public String getSampNo() {
		return sampNo;
	}


	public void setSampNo(String sampNo) {
		this.sampNo = sampNo;
	}


	public String getLabSamTypeId() {
		return labSamTypeId;
	}


	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}


	public String getLabSamTypeName() {
		return labSamTypeName;
	}


	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}
	
	
}
