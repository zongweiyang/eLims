package cn.labsoft.labos.common.encoder.vo;

public class BarCode {
	private String id;//标识

	private BarCodeContent[] bccArray;
	
	public BarCodeContent[] getBccArray() {
		return bccArray;
	}

	public void setBccArray(BarCodeContent[] bccArray) {
		this.bccArray = bccArray;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
