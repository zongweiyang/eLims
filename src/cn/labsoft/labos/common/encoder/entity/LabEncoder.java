package cn.labsoft.labos.common.encoder.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
public class LabEncoder extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	
	private String busId;//业务Id
	private String busType;//条码类型
	private String remark;//备注
	
	private String rc4Two;//排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小   (二维码容错)
	private String size4Two;//尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大(二维码尺寸)
	private String frontColor4Two;//二维码前景色
	private String backColor4Two;//二维码背景色
	
	private byte[] content4TwoByte;//二维码内容
	
	private String size4One;//条形码尺寸
	private String type4One;//条形码码制
	private String num4One;//条形码编号
	private String numLay4One;//条形码编号位置
	private byte[] content4OneByte;//条形码内容
	private String isContentTxm;//是否支持条码
	private String isContentEwm;//是否支持二维码



	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRc4Two() {
		return rc4Two;
	}

	public void setRc4Two(String rc4Two) {
		this.rc4Two = rc4Two;
	}

	public String getSize4Two() {
		return size4Two;
	}

	public void setSize4Two(String size4Two) {
		this.size4Two = size4Two;
	}

	public String getFrontColor4Two() {
		return frontColor4Two;
	}

	public void setFrontColor4Two(String frontColor4Two) {
		this.frontColor4Two = frontColor4Two;
	}

	public String getBackColor4Two() {
		return backColor4Two;
	}

	public void setBackColor4Two(String backColor4Two) {
		this.backColor4Two = backColor4Two;
	}
	@Type(type="org.hibernate.type.BinaryType") 
	public byte[] getContent4TwoByte() {
		return content4TwoByte;
	}

	public void setContent4TwoByte(byte[] content4TwoByte) {
		this.content4TwoByte = content4TwoByte;
	}

	public String getSize4One() {
		return size4One;
	}

	public void setSize4One(String size4One) {
		this.size4One = size4One;
	}

	public String getType4One() {
		return type4One;
	}

	public void setType4One(String type4One) {
		this.type4One = type4One;
	}

	public String getNum4One() {
		return num4One;
	}

	public void setNum4One(String num4One) {
		this.num4One = num4One;
	}

	public String getNumLay4One() {
		return numLay4One;
	}

	public void setNumLay4One(String numLay4One) {
		this.numLay4One = numLay4One;
	}
	@Type(type="org.hibernate.type.BinaryType") 
	public byte[] getContent4OneByte() {
		return content4OneByte;
	}

	public void setContent4OneByte(byte[] content4OneByte) {
		this.content4OneByte = content4OneByte;
	}

	public String getIsContentTxm() {
		return isContentTxm;
	}

	public void setIsContentTxm(String isContentTxm) {
		this.isContentTxm = isContentTxm;
	}

	public String getIsContentEwm() {
		return isContentEwm;
	}

	public void setIsContentEwm(String isContentEwm) {
		this.isContentEwm = isContentEwm;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "条码信息";
	}
}
