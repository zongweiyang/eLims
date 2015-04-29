package cn.labsoft.labos.source.charge.entity;



import javax.persistence.Entity;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
public class LabChargeDetail extends AbstractBasePo {

	private static final long serialVersionUID = 1L;
	private String code;			//收款单号
	private String busId;
	private String payName;			//付款项目名称
	private Double price;
	private int num;
	private Double payMoney = 0.0;		//实收金额
	private String payInfo;			//付款信息
	private String remark;		 

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
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

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Transient
	@Override
	public String getModelName() {
		return "收费管理";
	}

	@Transient
	@Override
	public String getTableName() {
		return "收费详情";
	}

	
}
