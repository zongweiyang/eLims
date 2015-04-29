package cn.labsoft.labos.common.encoder.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabEncoderVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	
	public static final String EWM_ENCODER = "ewm";
	public static final String TXM_ENCODER = "txm";
	
	@ExcelAnnotation(exportName = "ID")
	private String id;
	
	@ExcelAnnotation(exportName = "业务Id")
	private String busId;
	@ExcelAnnotation(exportName = "条码类型")
	private String busType;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	
	@ExcelAnnotation(exportName = "二维码容错")
	private String rc4Two;//排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
	@ExcelAnnotation(exportName = "二维码尺寸")
	private String size4Two;//尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
	@ExcelAnnotation(exportName = "二维码前景色")
	private String frontColor4Two;
	@ExcelAnnotation(exportName = "二维码背景色")
	private String backColor4Two;
	@ExcelAnnotation(exportName = "二维码内容")
	private List<BarCodeContent> content4Two;
	
	@ExcelAnnotation(exportName = "条形码尺寸")
	private String size4One;
	@ExcelAnnotation(exportName = "条形码码制")
	private String type4One;
	@ExcelAnnotation(exportName = "条形码编号")
	private String num4One;
	@ExcelAnnotation(exportName = "条形码编号位置")
	private String numLay4One;
	@ExcelAnnotation(exportName = "条形码内容")
	private List<BarCodeContent> content4One;
	@ExcelAnnotation(exportName = "业务名称")
	private String busName;
	@ExcelAnnotation(exportName = "条形码信息")
	private String codeInfo4One;
	@ExcelAnnotation(exportName = "二维码信息")
	private String codeInfo4Two;
	@ExcelAnnotation(exportName = "条形码key")
	private String barcodeKey;
	@ExcelAnnotation(exportName = "条形码value")
	private String barcodeValue;
	@ExcelAnnotation(exportName = "是否显示条码内容")
	private String isContentTxm;
	@ExcelAnnotation(exportName = "是否显示二维码内容")
	private String isContentEwm;
	private List<BarCode> barCodeList;
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<BarCodeContent> getContent4Two() {
		return content4Two;
	}

	public void setContent4Two(List<BarCodeContent> content4Two) {
		this.content4Two = content4Two;
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

	public List<BarCodeContent> getContent4One() {
		return content4One;
	}

	public void setContent4One(List<BarCodeContent> content4One) {
		this.content4One = content4One;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getCodeInfo4One() {
		return codeInfo4One;
	}

	public void setCodeInfo4One(String codeInfo4One) {
		this.codeInfo4One = codeInfo4One;
	}

	public String getCodeInfo4Two() {
		return codeInfo4Two;
	}

	public void setCodeInfo4Two(String codeInfo4Two) {
		this.codeInfo4Two = codeInfo4Two;
	}

	public List<BarCode> getBarCodeList() {
		return barCodeList;
	}

	public void setBarCodeList(List<BarCode> barCodeList) {
		this.barCodeList = barCodeList;
	}

	public String getBarcodeKey() {
		return barcodeKey;
	}

	public void setBarcodeKey(String barcodeKey) {
		this.barcodeKey = barcodeKey;
	}

	public String getBarcodeValue() {
		return barcodeValue;
	}

	public void setBarcodeValue(String barcodeValue) {
		this.barcodeValue = barcodeValue;
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

	
}
