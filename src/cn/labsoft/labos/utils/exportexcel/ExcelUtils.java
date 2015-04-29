package cn.labsoft.labos.utils.exportexcel;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.CellType;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.ImageUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * <strong>Title : ExcelUtils Excel导出工具类</strong>. <br>
 * <strong>Description : Excel导出工具类</strong>. <br>
 * <strong>Create on : Jul 23, 2012 9:17:14 AM </strong>. <br>
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
@SuppressWarnings( { "unchecked", "serial", "unused", "static-access" })
public final class ExcelUtils {

	public ExcelUtils() {
	}

	/**
	 * 
	 * 导出excel对外接口
	 * 
	 * @param title
	 *            标题 如：2005年人力资源统计报表
	 * @param tableData
	 *            数据表数据 如：new String[][]{{"表头1","表头2"},{"aaa","bbb"}}
	 * @param exportFileName
	 *            导出后的文件名 如：hr.xls
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws GlobalException 
	 */
	public static void exportExcelData(String title, String[][] tableData,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException, GlobalException {
		String fileName = "attachment;filename=\""
				+ new String(exportFileName.getBytes(), "ISO8859_1") + "\";";
		response.reset();
		response.setHeader("Content-disposition", fileName);
		response.setContentType("application/vnd.ms-excel");
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			exportExcel(title, tableData, outputStream, createWorkbook());
			// getTemplateFromResource("templete.xls"));

		} catch (Exception e) {
			// log.debug(e.getMessage(), e);
			// log. debug(e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		// return null;
	}

	public static void exportExcelData(String title, String[][][] tableData,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response, List sblxList, int sheetNum) throws GlobalException {
		String fileName = "attachment;filename=\"" + exportFileName + "\";";
		response.setHeader("Content-disposition", fileName);
		response.setContentType("xls");
		try {
			exportExcel(title, tableData, response.getOutputStream(),
					createWorkbook(sheetNum, sblxList), sheetNum);
			// getTemplateFromResource("templete.xls"));
			response.getOutputStream().close();
		} catch (Exception e) {
			// log.debug(e.getMessage(), e);
			// log. debug(e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		// return null;
	}

	@SuppressWarnings("deprecation")
	public static void exportExcel(String title, String[][] tableData,
			OutputStream output, HSSFWorkbook workbook) throws Exception {
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.setDefaultColumnWidth((short) 15);
		// workbook.setSheetName(0,title,HSSFWorkbook.ENCODING_UTF_16); POI3.0
		workbook.setSheetName(0, title); // POI3.2
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillBackgroundColor((short) 55);
		HSSFFont hsFont = workbook.createFont();
		hsFont.setBoldweight((short) 700);
		String titles[] = tableData[0];
		titleStyle.setFont(hsFont);
		titleStyle.setAlignment((short) 2);

		/*
		 * titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 * titleStyle.setTopBorderColor(HSSFColor.BLACK.index);
		 * titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 * titleStyle.setTopBorderColor(HSSFColor.BLACK.index);
		 * titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 * titleStyle.setTopBorderColor(HSSFColor.BLACK.index);
		 * titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 * titleStyle.setTopBorderColor(HSSFColor.BLACK.index);
		 */

		HSSFRow row = sheet.createRow(0);

		row.setHeight((short) 300);
		// HSSFCell cell = createCell(row, (short) 0, title, titleStyle);

		// row = sheet.createRow(3);
		row.setHeight((short) 250);

		short i = 0;

		HSSFDataFormat format = workbook.createDataFormat();
		titleStyle.setDataFormat(format.getFormat("@"));

		/**
		 * 1.0, "General" 2.1, "0" 3.2, "0.00" 4.3, "#,##0" 5.4, "#,##0.00" 6.5,
		 * "($#,##0_);($#,##0)" 7.6, "($#,##0_);[Red]($#,##0)" 8.7,
		 * "($#,##0.00);($#,##0.00)" 9.8, "($#,##0.00_);[Red]($#,##0.00)" 10.9,
		 * "0%" 11.0xa, "0.00%" 12.0xb, "0.00E+00" 13.0xc, "# ?/?" 14.0xd, "#
		 * ??/??" 15.0xe, "m/d/yy" 16.0xf, "d-mmm-yy" 17.0x10, "d-mmm" 18.0x11,
		 * "mmm-yy" 19.0x12, "h:mm AM/PM" 20.0x13, "h:mm:ss AM/PM" 21.0x14,
		 * "h:mm" 22.0x15, "h:mm:ss" 23.0x16, "m/d/yy h:mm" 24.// 0x17 - 0x24
		 * reserved for international and undocumented 0x25, "(#,##0_);(#,##0)"
		 * 25. 26.0x26, "(#,##0_);[Red](#,##0)" 27. 28.0x27,
		 * "(#,##0.00_);(#,##0.00)" 29. 30.0x28, "(#,##0.00_);[Red](#,##0.00)"
		 * 31. 32.0x29, "_(*#,##0_);_(*(#,##0);_(* \"-\"_);_(@_)" 33. 34.0x2a,
		 * "_($*#,##0_);_($*(#,##0);_($* \"-\"_);_(@_)" 35. 36.0x2b,
		 * "_(*#,##0.00_);_(*(#,##0.00);_(*\"-\"??_);_(@_)" 37. 38.0x2c,
		 * "_($*#,##0.00_);_($*(#,##0.00);_($*\"-\"??_);_(@_)" 39. 40.0x2d,
		 * "mm:ss" 41. 42.0x2e, "[h]:mm:ss" 43. 44.0x2f, "mm:ss.0" 45. 46.0x30,
		 * "##0.0E+0" 47. 48.0x31, "@" - This is text format. 49. 50.0x31 "text" -
		 * Alias for "@"
		 */
		/**/
		for (int forI = 0; forI < titles.length; forI++) {
			createCell(row, i, titles[forI], titleStyle);
			i++;
		}
		int rowCount = 1;

		/*
		 * HSSFCellStyle cellStyleTop = workbook.createCellStyle();
		 * HSSFCellStyle cellStyleBottom = workbook.createCellStyle();
		 * HSSFCellStyle cellStyleLeft = workbook.createCellStyle();
		 * HSSFCellStyle cellStyleRight = workbook.createCellStyle();
		 * 
		 * cellStyleTop.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 * cellStyleTop.setTopBorderColor(HSSFColor.BLACK.index);
		 * 
		 * cellStyleBottom.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 * cellStyleBottom.setTopBorderColor(HSSFColor.BLACK.index);
		 * 
		 * cellStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 * cellStyleLeft.setTopBorderColor(HSSFColor.BLACK.index);
		 * 
		 * cellStyleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 * cellStyleRight.setTopBorderColor(HSSFColor.BLACK.index);
		 */
		HSSFCellStyle titleStyle1 = workbook.createCellStyle();
		titleStyle1.setDataFormat(format.getFormat("@"));
		for (int rowPos = 1; rowPos < tableData.length; rowPos++) {
			row = sheet.createRow(rowCount++);

			i = 0;
			for (int colPos = 0; colPos < tableData[rowPos].length; colPos++) {
				createCell(row, i, tableData[rowPos][colPos], titleStyle1);
				i++;
			}
		}

		// workbook.setPrintArea(0, "$A$4:$H$8");

		try {
			workbook.write(output);

		} catch (Exception e) {
			// throw new Exception(e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	protected static void exportExcel(String title, String[][][] tableData,
			OutputStream output, HSSFWorkbook workbook, int sheetNum)
			throws Exception {
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillBackgroundColor((short) 55);
		HSSFFont hsFont = workbook.createFont();
		hsFont.setBoldweight((short) 700);
		titleStyle.setFont(hsFont);
		titleStyle.setAlignment((short) 2);

		for (int k = 0; k < sheetNum; k++) {
			HSSFSheet sheetk = workbook.getSheetAt(k);
			String titles[] = tableData[k][0];
			HSSFRow row = sheetk.createRow(1);
			row.setHeight((short) 300);
			HSSFCell cell1 = createCell(row, (short) 0, title, titleStyle);
			// HSSFCell cell2 = createCell(row, (short)1, title, titleStyle);

			// sheetk.addMergedRegion(new Region(1,(short)0,1,(short)10));

			row = sheetk.createRow(3);
			row.setHeight((short) 250);
			short i = 0;
			for (int forI = 0; forI < titles.length; forI++) {
				createCell(row, i, titles[forI], titleStyle);
				i++;
			}

			int rowCount = 4;
			for (int rowPos = 1; rowPos < tableData[k].length; rowPos++) {
				row = sheetk.createRow(rowCount++);
				i = 0;
				for (int colPos = 0; colPos < tableData[k][rowPos].length; colPos++) {
					createCell(row, i, tableData[k][rowPos][colPos], null);
					i++;
				}
			}

		}
		try {
			workbook.write(output);
		} catch (Exception e) {
			System.err.println("e====" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	protected static HSSFCell createCell(HSSFRow row, short cellCount,
			String value, HSSFCellStyle titleStyle) {
		HSSFCell cell = row.createCell(cellCount);
		// cell.setEncoding((short) 1); POI3.0
		if (titleStyle != null)
			cell.setCellStyle(titleStyle);
		cell.setCellValue(value);
		return cell;
	}

	public static HSSFWorkbook createWorkbook() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		return wb;
	}

	public static HSSFWorkbook createWorkbook(int sheetCount) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		for (int i = 0; i < sheetCount; i++) {
			HSSFSheet si = wb.createSheet();
		}

		return wb;
	}

	public static HSSFWorkbook createWorkbook(int sheetCount, List sblxList)
			throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		for (int i = 0; i < sheetCount; i++) {
			HSSFSheet si = wb.createSheet((String) sblxList.get(i));
			// wb.setSheetName(i, (String) sblxList.get(i), (short) 1); POI3.0
			wb.setSheetName(i, (String) sblxList.get(i));
		}

		return wb;
	}

	/**
	 * 根据path获取EXCEL中的所有sheetName集合
	 */
	public static List<String> getExcelSheetNameByFileUrl(String path)
			throws Exception {
		List<String> resultList = new ArrayList<String>();
		if (null == path || "".equals(path))
			return resultList;

		InputStream is = new FileInputStream(path);
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(is));
		int numOfSheet = wb.getNumberOfSheets();
		// 判断工作表的有效性
		if (numOfSheet <= 0)
			throw new Exception("没有此工作表");
		for (Integer i = 0; i < numOfSheet; i++) {
			String sheetName = wb.getSheetName(i);
			resultList.add(sheetName);
		}
		if (null != is) {
			is.close();
		}
		return resultList;
	}

	/**
	 * 删除Excel中sheet
	 * 
	 * @param path
	 *            Excel 路径
	 * @param startIndex
	 *            要删除的起始Index 从0开始
	 * @return
	 * @throws GlobalException 
	 * @throws Exception
	 */
	public static boolean removeExcelSheetBysStartIndex(String path,
			int startIndex) throws GlobalException {
		try {
			long sTime = System.currentTimeMillis();
			path = "D:\\1.xls";
			File file = new File(path);
			Workbook book = Workbook.getWorkbook(file);
			WritableWorkbook wBook = Workbook.createWorkbook(file, book);
			int numOfSheets = wBook.getNumberOfSheets();
			if (numOfSheets > 0) {
				for (int i = startIndex; i < numOfSheets; i++) {
					wBook.removeSheet(i);
				}
			}
			wBook.write();
			wBook.close();
			System.out.println(path + "~~~~~~~"
					+ (System.currentTimeMillis() - sTime) + "~~~~~~~~~~"
					+ startIndex);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	public static int getNumberOfSheets(String path) throws GlobalException {
		try {
			File file = new File(path);
			Workbook book = Workbook.getWorkbook(file);
			WritableWorkbook wBook = Workbook.createWorkbook(file, book);
			int numOfSheets = wBook.getNumberOfSheets();
			return numOfSheets;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	/**
	 * 在Excel中插入图片
	 * 
	 * @param xlsFilePath
	 *            Excel文件的路径
	 * @param index
	 *            第？Sheet页,从0开始计算
	 * @param excelImageList
	 *            要插入的图片的对象List
	 * @throws GlobalException 
	 */
	public static void insertImg(String xlsFilePath, int index,
			List<ExcelImage> excelImageList) throws GlobalException {
		try {
			File sourceFile = new File(xlsFilePath);
			File descFile = new File(xlsFilePath.substring(0, xlsFilePath
					.lastIndexOf("."))
					+ "_temp.xls");
			Workbook workbook = Workbook.getWorkbook(sourceFile);
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(
					descFile, workbook);
			WritableSheet writableSheet = writableWorkbook.getSheet(index);
			List<String> strImagePathList = new ArrayList<String>();
			if (null != excelImageList && 0 < excelImageList.size()) {
				for (ExcelImage excelImage : excelImageList) {
					String strImagePath = "";
					if (null != excelImage && null != excelImage.getImgStr()
							&& !"".equals(excelImage.getImgStr())) {
						strImagePath = ImageUtils.generateImage(excelImage
								.getImgFilePath(), excelImage.getImgStr());
						excelImage.setImgFilePath(strImagePath);
						strImagePathList.add(strImagePath);
					}
					WritableImage writableImage = new WritableImage(excelImage
							.getStartCol(), excelImage.getStartRow(),
							excelImage.getWidthCol(),
							excelImage.getHeightRow(), new File(excelImage
									.getImgFilePath()));
					writableSheet.addImage(writableImage);
				}
			}
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			workbook = Workbook.getWorkbook(descFile);
			writableWorkbook = Workbook.createWorkbook(sourceFile, workbook);
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			if (descFile.exists()) {
				descFile.delete();
			}
			if (null != strImagePathList && 0 < strImagePathList.size()) {
				for (String strImagePath : strImagePathList) {
					File strImageFile = new File(strImagePath);
					if (null != strImageFile && strImageFile.exists()) {
						strImageFile.delete();
					}
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (BiffException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (WriteException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	public static void removeImg(String xlsFilePath, int index, int[] indexImg) throws GlobalException {
		try {
			File sourceFile = new File(xlsFilePath);
			File descFile = new File(xlsFilePath.substring(0, xlsFilePath
					.lastIndexOf("."))
					+ "_temp.xls");
			Workbook workbook = Workbook.getWorkbook(sourceFile);
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(
					descFile, workbook);
			WritableSheet writableSheet = writableWorkbook.getSheet(index);
			int totalImgs = writableSheet.getNumberOfImages();

			int maxIndex = 0;
			for (int i = 0; i < indexImg.length; i++) {
				maxIndex = indexImg[i] > totalImgs ? totalImgs : indexImg[i];
				WritableImage writableImage = writableSheet.getImage(maxIndex);
				writableSheet.removeImage(writableImage);
			}
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			workbook = Workbook.getWorkbook(descFile);
			writableWorkbook = Workbook.createWorkbook(sourceFile, workbook);
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			if (descFile.exists()) {
				descFile.delete();
			}

		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (BiffException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (WriteException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	public static void removeAllImg(String xlsFilePath, int index) throws GlobalException {
		try {
			File sourceFile = new File(xlsFilePath);
			File descFile = new File(xlsFilePath.substring(0, xlsFilePath
					.lastIndexOf("."))
					+ "_temp.xls");
			Workbook workbook = Workbook.getWorkbook(sourceFile);
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(
					descFile, workbook);
			WritableSheet writableSheet = writableWorkbook.getSheet(index);

			while (true) {
				int totalImgs = writableSheet.getNumberOfImages();
				if (totalImgs == 0) {
					break;
				}
				WritableImage writableImage = writableSheet.getImage(0);
				writableSheet.removeImage(writableImage);
			}
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			workbook = Workbook.getWorkbook(descFile);
			writableWorkbook = Workbook.createWorkbook(sourceFile, workbook);
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			if (descFile.exists()) {
				descFile.delete();
			}

		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (BiffException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (WriteException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	/**
	 * 修改Excel某个Cell的文字
	 * 
	 * @param xlsFilePath
	 *            Excel文件的路径
	 * @param index
	 *            第？Sheet页,从0开始计算
	 * @param excelImageList
	 *            要插入的图片的对象List
	 * @throws GlobalException 
	 */
	public static void modifyExcel(String xlsFilePath, int index,
			List<ExcelString> excelStringList) throws GlobalException {
		try {
			File sourceFile = new File(xlsFilePath);
			File descFile = new File(xlsFilePath.substring(0, xlsFilePath
					.lastIndexOf("."))
					+ "_temp.xls");
			Workbook workbook = Workbook.getWorkbook(sourceFile);
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(
					descFile, workbook);
			WritableSheet writableSheet = writableWorkbook.getSheet(index);

			if (null != excelStringList && 0 < excelStringList.size()) {
				for (ExcelString excelString : excelStringList) {
					WritableCell wc = writableSheet.getWritableCell(excelString
							.getCol(), excelString.getRow());
					// 判断单元格的类型,做出相应的转换
					WritableFont witableFont = new WritableFont(WritableFont
							.createFont(excelString.getFontName()),// 字体
							excelString.getFontSize(),// WritableFont.DEFAULT_POINT_SIZE,
							// // 字号
							excelString.isBoldStyle() ? WritableFont.BOLD
									: WritableFont.NO_BOLD, // 粗体
							excelString.isItalic(), // 斜体
							excelString.getUnderLineStyle(), // 下划线
							excelString.getFontColour(), // 字体颜色
							excelString.getScriptStyle());
					WritableCellFormat writableCellFormat = new WritableCellFormat(
							witableFont);
					writableCellFormat.setAlignment(excelString.getAlignment()); // 设置对齐方式
					writableCellFormat.setVerticalAlignment(excelString
							.getVerticalAlignment());
					wc.setCellFormat(writableCellFormat);
					if (wc.getType() == CellType.LABEL) {
						Label label = (Label) wc;
						label.setString(excelString.getValue());
					} else if (wc.getType() == CellType.NUMBER) {
						Number number = (Number) wc;
						number
								.setValue(Integer.valueOf(excelString
										.getValue()));
					} else if (wc.getType() == CellType.EMPTY) {
						Blank blank = (Blank) wc;
						System.out.println("\"" + excelString.getValue()
								+ "\" 需要修改的Cell(" + excelString.getCol() + ","
								+ excelString.getRow()
								+ ")中原来没有值，请保证要修改的Cell中有值！");
					}
				}
			}
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			workbook = Workbook.getWorkbook(descFile);
			writableWorkbook = Workbook.createWorkbook(sourceFile, workbook);
			writableWorkbook.write();
			writableWorkbook.close();
			workbook.close();

			if (descFile.exists()) {
				descFile.delete();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static void composePic(String filesrc, String logosrc,
			String outsrc, int x, int y) throws GlobalException {
		try {
			double ratio = 0.9;// 压缩比例
			File bgfile = new File(filesrc);
			Image bg_src = javax.imageio.ImageIO.read(bgfile);
			File logofile = new File(logosrc);
			Image logo_src = javax.imageio.ImageIO.read(logofile);

			int bg_width = bg_src.getWidth(null);
			int bg_height = bg_src.getHeight(null);
			// int logo_width = logo_src.getWidth(null);
			// int logo_height = logo_src.getHeight(null);
			int logo_width = (int) (logo_src.getWidth(null) * ratio);
			int logo_height = (int) (logo_src.getHeight(null) * ratio);
			BufferedImage tag = new BufferedImage(bg_width, bg_height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D g2d = tag.createGraphics();
			g2d.drawImage(bg_src, 0, 0, bg_width, bg_height, null);

			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_ATOP, 1.0f)); // 透明度设置开始
			g2d.drawImage(logo_src, x, y, logo_width, logo_height, null);
			g2d.setComposite(AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER)); // 透明度设置 结束

			FileOutputStream out = new FileOutputStream(outsrc);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static void main(String[] args) throws GlobalException {
		try {
			List<ExcelImage> excelImageList = new ArrayList<ExcelImage>();
			ExcelImage excelImage = new ExcelImage(3, 18, 2, 2,
					"D:\\TEST\\src.png", "");
			excelImageList.add(excelImage);

			ExcelUtils.insertImg("D:\\TEST\\1.xls", 0, excelImageList);
			// removeAllImg("D:\\TESTEXCEL\\1.xls", 0);

			/*
			 * List<ExcelString> exceclStringList = new ArrayList<ExcelString>();
			 * ExcelString excelString = new ExcelString("宋体",14, true, false,
			 * UnderlineStyle.NO_UNDERLINE, Colour.BLACK,
			 * ScriptStyle.SUPERSCRIPT, Alignment.CENTRE,
			 * VerticalAlignment.CENTRE,2,21,"2013");
			 * exceclStringList.add(excelString);
			 * 
			 * excelString = new ExcelString("宋体",14, false, false,
			 * UnderlineStyle.NO_UNDERLINE, Colour.BLACK,
			 * ScriptStyle.SUPERSCRIPT, Alignment.CENTRE,
			 * VerticalAlignment.CENTRE,4,21,"6");
			 * exceclStringList.add(excelString);
			 * 
			 * excelString = new ExcelString("宋体",14, false, false,
			 * UnderlineStyle.NO_UNDERLINE, Colour.BLACK,
			 * ScriptStyle.SUPERSCRIPT, Alignment.CENTRE,
			 * VerticalAlignment.CENTRE,6,21,"28");
			 * exceclStringList.add(excelString);
			 * 
			 * modifyExcel("D:\\TESTEXCEL\\1.xls",0,exceclStringList);
			 */
			/*
			 * long sTime = System.currentTimeMillis();
			 * removeExcelSheetBysStartIndex("D:\\1.xls",18);
			 * System.out.println(System.currentTimeMillis() - sTime);
			 */

			/*
			 * Long star = System.currentTimeMillis();
			 * composePic("D:\\TEST\\dt.gif", "D:\\TEST\\src.gif",
			 * "D:\\TEST\\out_pic.gif", 15, 15); Long end =
			 * System.currentTimeMillis(); System.out.print("time====:" + (end -
			 * star));
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	

}
