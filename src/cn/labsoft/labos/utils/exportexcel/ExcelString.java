package cn.labsoft.labos.utils.exportexcel;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
/**
 * 
 * <strong>Title : ExcelString Excel导出字符串类</strong>. <br>
 * <strong>Description : Excel导出字符串类�</strong>. <br>
 * <strong>Create on : Jul 23, 2012 9:16:22 AM  </strong>. <br>
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
public class ExcelString {

	private String fontName;// 字体
	private int fontSize;// 字号 WritableFont.DEFAULT_POINT_SIZE,
	private boolean isBoldStyle;// 是否粗体
	private boolean isItalic;// 是否斜体
	private UnderlineStyle underLineStyle;// 下划线
	private Colour fontColour;// 字体颜色
	private ScriptStyle scriptStyle;
	private Alignment alignment;
	private VerticalAlignment verticalAlignment;

	private int col;//位置列
	private int row;//位置行

	private String value;

	
	
	public ExcelString() {
		super();
	}

	public ExcelString(String fontName, int fontSize, boolean isBoldStyle,
			boolean isItalic, UnderlineStyle underLineStyle, Colour fontColour,
			ScriptStyle scriptStyle, Alignment alignment,
			VerticalAlignment verticalAlignment, int col, int row, String value) {
		super();
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.isBoldStyle = isBoldStyle;
		this.isItalic = isItalic;
		this.underLineStyle = underLineStyle;
		this.fontColour = fontColour;
		this.scriptStyle = scriptStyle;
		this.alignment = alignment;
		this.verticalAlignment = verticalAlignment;
		this.col = col;
		this.row = row;
		this.value = value;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	

	public boolean isBoldStyle() {
		return isBoldStyle;
	}

	public void setBoldStyle(boolean isBoldStyle) {
		this.isBoldStyle = isBoldStyle;
	}

	public boolean isItalic() {
		return isItalic;
	}

	public void setItalic(boolean isItalic) {
		this.isItalic = isItalic;
	}

	public UnderlineStyle getUnderLineStyle() {
		return underLineStyle;
	}

	public void setUnderLineStyle(UnderlineStyle underLineStyle) {
		this.underLineStyle = underLineStyle;
	}

	public Colour getFontColour() {
		return fontColour;
	}

	public void setFontColour(Colour fontColour) {
		this.fontColour = fontColour;
	}

	public ScriptStyle getScriptStyle() {
		return scriptStyle;
	}

	public void setScriptStyle(ScriptStyle scriptStyle) {
		this.scriptStyle = scriptStyle;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
