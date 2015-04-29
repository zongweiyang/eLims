package cn.labsoft.labos.source.appara.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * LabAppara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lab_appara")
public class LabAppara extends AbstractBasePo {

	
	private LabApparaType apparaType; //仪器类型
	private String name;//仪器名称
	private String no;//仪器编号
	private String code;//出厂编号
	private String mcountry;//国家
	private String supplier;//供应商
	private String model;//模型
	private String spec;//规格/型号
	private String producer;//制造厂商
	private Double price;//价格
	private String purTime;//购置时间
	private String verPeriod;
	private String verPeriodStr;
	private String verLastTime;
	private String verNextTime;
	private String runPeriod;
	private String runPeriodStr;
	private String runLastTime;
	private String runNextTime;
	private String typeId;
	private String status;//0 仪器正常 1报修 2 停用 7报废 10正在使用中
	private String effectDate;//有效期
	private String keeperId;//保管人ID
	private String keeper;//仪器保管人
	private String cost;//使用费用
	private String comment;
	private String ext01;
	private String ext03;
	private String ext04; //仪器授权使用人
	private String isCheck;//是否验收
	private String testProperty;
	
	@Column(name="[name]") 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="[no]") 
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	@Column(name="[code]") 
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMcountry() {
		return this.mcountry;
	}

	public void setMcountry(String mcountry) {
		this.mcountry = mcountry;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExt01() {
		return this.ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}


	public String getExt03() {
		return this.ext03;
	}

	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}

	public String getExt04() {
		return this.ext04;
	}

	public void setExt04(String ext04) {
		this.ext04 = ext04;
	}


	public String getTestProperty() {
		return testProperty;
	}
	public void setTestProperty(String testProperty) {
		this.testProperty = testProperty;
	}
	
	public String getPurTime() {
		return purTime;
	}

	public void setPurTime(String purTime) {
		this.purTime = purTime;
	}

	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	@ManyToOne
	public LabApparaType getApparaType() {
		return apparaType;
	}
	public void setApparaType(LabApparaType apparaType) {
		this.apparaType = apparaType;
	}
	
	public String getVerPeriod() {
		return verPeriod;
	}

	public void setVerPeriod(String verPeriod) {
		this.verPeriod = verPeriod;
	}

	public String getVerPeriodStr() {
		return verPeriodStr;
	}

	public void setVerPeriodStr(String verPeriodStr) {
		this.verPeriodStr = verPeriodStr;
	}

	public String getVerLastTime() {
		return verLastTime;
	}

	public void setVerLastTime(String verLastTime) {
		this.verLastTime = verLastTime;
	}

	public String getVerNextTime() {
		return verNextTime;
	}

	public void setVerNextTime(String verNextTime) {
		this.verNextTime = verNextTime;
	}

	public String getRunPeriod() {
		return runPeriod;
	}

	public void setRunPeriod(String runPeriod) {
		this.runPeriod = runPeriod;
	}

	public String getRunPeriodStr() {
		return runPeriodStr;
	}

	public void setRunPeriodStr(String runPeriodStr) {
		this.runPeriodStr = runPeriodStr;
	}

	public String getRunLastTime() {
		return runLastTime;
	}

	public void setRunLastTime(String runLastTime) {
		this.runLastTime = runLastTime;
	}

	public String getRunNextTime() {
		return runNextTime;
	}

	public void setRunNextTime(String runNextTime) {
		this.runNextTime = runNextTime;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Column(name="[status]") 
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getKeeperId() {
		return keeperId;
	}

	public void setKeeperId(String keeperId) {
		this.keeperId = keeperId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器信息";
	}
//	@Override
//	public String toString() {
//		return "仪器信息";
//	}
}