package cn.labsoft.labos.business.sam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * @title 样品表
 * 
 */
@Entity
public class LabSam extends AbstractBasePo {

	private static final long serialVersionUID = 1L;
	private LabSamMain main;
	private String sampNo;//来样编号
	private LabSamType labSamType; // 样品类型
	private String name;//样品名称
	private String ename;//英文名称
	private String shape;//样品性状
	private String saveMode;//保存方式
	private String position;//存放位置
	private String saveOrg;// 保管科室
	private String saveUser;// 保管人
	private int safeDate;   // 样品有效期
	private String packManner;//包装方式
	private String total;     //样品数量
	private String remark;     //备注
	private String specifications;//规格
	private String grade;   //等级
	private String sampDate;//采样日期
	private String sampAddr;//采样地点
	private String sampCode;//样品编号
	private String oldNo;   //原编号
	private String seqNum;  //序号

	private String isDestory;  //用途
	private String isDiv;
	private String drawPerson; //领样人
	private String drawTime;   //领样时间
	private String isDraw;     //是否领取
	private String itemId;       //分析项目
	private String itemName;     //分析项目
	

	public String getDrawTime() {
		return drawTime;
	}
	public void setDrawTime(String drawTime) {
		this.drawTime = drawTime;
	}
	@ManyToOne
	@JoinColumn(name="type_id", nullable=false)	
	public LabSamType getLabSamType() {
		return labSamType;
	}


	public void setLabSamType(LabSamType labSamType) {
		this.labSamType = labSamType;
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

	@ManyToOne
	@JoinColumn(name="main_id", nullable=false)	
	public LabSamMain getMain() {
		return main;
	}


	public void setMain(LabSamMain main) {
		this.main = main;
	}


	public String getIsDestory() {
		return isDestory;
	}


	public String getIsDiv() {
		return isDiv;
	}


	public void setIsDiv(String isDiv) {
		this.isDiv = isDiv;
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


	public String getIsDraw() {
		return isDraw;
	}


	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Transient
	@Override
	public String getModelName() {
		return "业务管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "样品信息";
	}

}
