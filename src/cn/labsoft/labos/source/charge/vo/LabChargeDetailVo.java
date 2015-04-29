package cn.labsoft.labos.source.charge.vo;



import javax.persistence.Entity;
import javax.persistence.Table;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@Entity
@Table(name="lab_charge")
public class LabChargeDetailVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "收款单号")
	private String code;			
	private String busId;
	@ExcelAnnotation(exportName = "付款项目")
	private String payName;			
	@ExcelAnnotation(exportName = "付款信息")
	private String payInfo;			
	@ExcelAnnotation(exportName = "付款金额")
	private Double payMoney = 0.0;
	@ExcelAnnotation(exportName = "单价")
	private Double price;
	@ExcelAnnotation(exportName = "数量")
	private int num;
	@ExcelAnnotation(exportName = "备注")
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
	
}
