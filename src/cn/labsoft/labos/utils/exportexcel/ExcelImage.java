package cn.labsoft.labos.utils.exportexcel;
/**
 * 
 * <strong>Title : ExcelImage Excel导出图像类</strong>. <br>
 * <strong>Description : Excel导出图像类�</strong>. <br>
 * <strong>Create on : Jul 23, 2012 9:15:17 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>LabOS v1.1.1</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class ExcelImage {
	private int startCol;
	private int startRow;
	private int widthCol;
	private int heightRow;

	private String imgFilePath;
	private String imgStr;

	public ExcelImage(){
		
	}
	
	public ExcelImage(int startCol, int startRow, int widthCol, int heightRow,
			String imgFilePath,String imgStr) {
		super();
		this.startCol = startCol;
		this.startRow = startRow;
		this.widthCol = widthCol;
		this.heightRow = heightRow;
		this.imgFilePath = imgFilePath;
		this.imgStr = imgStr;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getWidthCol() {
		return widthCol;
	}

	public void setWidthCol(int widthCol) {
		this.widthCol = widthCol;
	}

	public int getHeightRow() {
		return heightRow;
	}

	public void setHeightRow(int heightRow) {
		this.heightRow = heightRow;
	}

	public String getImgFilePath() {
		return imgFilePath;
	}

	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}

	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}

	
	

}
