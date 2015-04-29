package cn.labsoft.labos.business.sample.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import javax.persistence.Transient;
import javax.persistence.Entity;
/**
 * 检测项目信息持久化对象
 * @author Quinn
 */
@Entity
public class LabSampItems extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String busId;        //业务id
	private String labSamId;
	private String labSamName;
	private String samCode;
	private String labSamTypeId;
	private String labSamTypeName;
	private String itemId;      //项目Id
	private String itemName;    //项目名称
	private Double price;      //费用
	private String standardId; //检测标准
	private String standardName;//检测标准
	private String methodId;    //检测方法
	private String methodName;  //检测方法

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStandardId() {
		return this.standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
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

	public String getLabSamId() {
		return labSamId;
	}

	public void setLabSamId(String labSamId) {
		this.labSamId = labSamId;
	}

	public String getLabSamName() {
		return labSamName;
	}

	public void setLabSamName(String labSamName) {
		this.labSamName = labSamName;
	}
	

	public String getSamCode() {
		return samCode;
	}

	public void setSamCode(String samCode) {
		this.samCode = samCode;
	}

	@Transient
	@Override
	public String getModelName() {
		return "业务检测";
	}
	@Transient
	@Override
	public String getTableName() {
		return "项目登记";
	}
}
