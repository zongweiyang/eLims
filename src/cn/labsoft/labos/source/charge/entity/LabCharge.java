package cn.labsoft.labos.source.charge.entity;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_charge")
public class LabCharge extends AbstractBasePo {

	private static final long serialVersionUID = 1L;
	private String code;			//收款单号
	private String busId;
	private String busType;
	
	private String paymentUser;		//付款人
	private String paymentUserTel;	//付款人电话
	private String paymentUnit;		//付款单位
	private String paymentUnitUrl;	//付款单位地址
	
	private String payName;			//付款项目名称
	private String payInfo;			//付款信息
	private Double payMoney = 0.0;		//实收金额
	private Double yiMoney = 0.0;//付款金额
	private Double weiMoney = 0.0;//未付款金额
	private Double taxMoney = 0.0;//税务收费
	private Double totalMoney;//总金额
	private String payType;			//付款类型
	private String chargeType;//收费项目
	
	private Double discount;//折扣
	private Double preferential;//优惠
	private Double tax;//税务
	
	private String collectionName;			//收款人
	private String collectionUnit;	//收款单位
	private String collectionUnitUrl;
	private String collectionTel;	//收款电话
	private String collectionTime;	//收款时间
	
	private String updateDate;		//修改时间
	
	private String invoiceNo;	//票号
	private String fastName;	//快递名称
	private String sendTime;	//发送时间
	private String costDate;		//到费时间
	private String fosterDate;			//寄养发票
	private String qualifiyDate;			//合格报告寄出
	private String unqualifiyDate;		//不合格报告寄出
	private String isEnd;//是否完结

	
	private String reportTempId;
	private String reportPath;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPreferential() {
		return preferential;
	}

	public void setPreferential(Double preferential) {
		this.preferential = preferential;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getPaymentUser() {
		return paymentUser;
	}

	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}

	public String getPaymentUserTel() {
		return paymentUserTel;
	}

	public void setPaymentUserTel(String paymentUserTel) {
		this.paymentUserTel = paymentUserTel;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public String getPaymentUnitUrl() {
		return paymentUnitUrl;
	}

	public void setPaymentUnitUrl(String paymentUnitUrl) {
		this.paymentUnitUrl = paymentUnitUrl;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionUnit() {
		return collectionUnit;
	}

	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}

	public String getCollectionUnitUrl() {
		return collectionUnitUrl;
	}

	public void setCollectionUnitUrl(String collectionUnitUrl) {
		this.collectionUnitUrl = collectionUnitUrl;
	}

	public String getCollectionTel() {
		return collectionTel;
	}

	public void setCollectionTel(String collectionTel) {
		this.collectionTel = collectionTel;
	}
	
	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getFastName() {
		return fastName;
	}

	public void setFastName(String fastName) {
		this.fastName = fastName;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getCostDate() {
		return costDate;
	}

	public void setCostDate(String costDate) {
		this.costDate = costDate;
	}

	public String getFosterDate() {
		return fosterDate;
	}

	public void setFosterDate(String fosterDate) {
		this.fosterDate = fosterDate;
	}


	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Double getYiMoney() {
		return yiMoney;
	}

	public void setYiMoney(Double yiMoney) {
		this.yiMoney = yiMoney;
	}

	public Double getWeiMoney() {
		return weiMoney;
	}

	public void setWeiMoney(Double weiMoney) {
		this.weiMoney = weiMoney;
	}

	public String getQualifiyDate() {
		return qualifiyDate;
	}

	public void setQualifiyDate(String qualifiyDate) {
		this.qualifiyDate = qualifiyDate;
	}

	public String getUnqualifiyDate() {
		return unqualifiyDate;
	}

	public void setUnqualifiyDate(String unqualifiyDate) {
		this.unqualifiyDate = unqualifiyDate;
	}

	@Transient
	@Override
	public String getModelName() {
		return "收费管理";
	}

	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "收费管理";
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Double getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(Double taxMoney) {
		this.taxMoney = taxMoney;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getReportTempId() {
		return reportTempId;
	}

	public void setReportTempId(String reportTempId) {
		this.reportTempId = reportTempId;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	

}
