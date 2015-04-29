package cn.labsoft.labos.utils.exportexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.FileUtils;

public class CopyExcelSheet {

	public static List<Font> fontList=null;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException,
			IOException, GlobalException {
		String fromPath = "E:\\report\\jx.xls";// excel存放路径
		String toPath = "E:\\report\\11.xls";// 保存新EXCEL路径
		copyExcelSheet(fromPath, toPath);
	}

	/**
	 * @Title: 根据文件路径复制sheet
	 * @Description: 将fromPath文件中的sheet全部复制到toPath中
	 * @param
	 * @param fromPath
	 *            源文件全路径
	 * @param
	 * @param toPath
	 *            新文件全路径
	 * @param
	 * @throws IOException
	 * @return 返回类型
	 * @throws GlobalException 
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static void copyExcelSheet(String fromPath, String toPath)
			throws IOException, GlobalException {
		// 创建新的excel
		File toFile = new File(toPath);
		HSSFWorkbook wbCreat = null;
//		if(!toFile.exists()){
//			toFile.createNewFile();
//		}
		if (!toFile.isFile()) {
			wbCreat = new HSSFWorkbook();// 建立新HSSFWorkbook对象
		} else{
			InputStream out = new FileInputStream(toFile);
			wbCreat = new HSSFWorkbook(out);
		}
		List<List<ExcelImage>> excelImageList=new ArrayList<List<ExcelImage>>();
		File fromFile = new File(fromPath);
		if (fromFile.exists()) {
			try {
				InputStream in = new FileInputStream(fromFile);
				HSSFWorkbook wb = new HSSFWorkbook(in);
				for (int ii = 0; ii < wb.getNumberOfSheets(); ii++) {
					HSSFSheet sheet = wb.getSheetAt(ii);
					String sheetName = sheet.getSheetName();
					fontList=new ArrayList<Font>();
					int x = 1;
					while (wbCreat.getSheetIndex(sheetName) >= 0) {
						sheetName += "(" + x + ")";
					}
					HSSFSheet sheetCreat = wbCreat.createSheet(sheetName);
					
					sheetCreat.setMargin(Sheet.TopMargin,sheet.getMargin(Sheet.TopMargin));// 页边距（上）      
					sheetCreat.setMargin(Sheet.BottomMargin,sheet.getMargin(Sheet.BottomMargin));// 页边距（下）      
					sheetCreat.setMargin(Sheet.LeftMargin,sheet.getMargin(Sheet.LeftMargin));// 页边距（左）      
					sheetCreat.setMargin(Sheet.RightMargin,sheet.getMargin(Sheet.RightMargin));// 页边距（右   
					sheetCreat.setMargin(Sheet.HeaderMargin,sheet.getMargin(Sheet.HeaderMargin));// 页边距（右   
					sheetCreat.setMargin(Sheet.FooterMargin,sheet.getMargin(Sheet.FooterMargin));// 页边距（右   
			        
					List<MyPictureData> picList=getAllPictures(wb);
					List<ExcelImage> tempExcelImageList = new ArrayList<ExcelImage>();
					for (MyPictureData myPictureData : picList) {
						ExcelImage image=new ExcelImage();
						image.setStartCol(myPictureData.getCol1());
						image.setWidthCol((myPictureData.getCol2()-myPictureData.getCol1())+1);
						image.setStartRow(myPictureData.getRow1());
						image.setHeightRow((myPictureData.getRow2()-myPictureData.getRow1())+1);
						File picTemp = FileUtils.getFileFromBytes(myPictureData.getData(),toPath.substring(0,toPath.lastIndexOf(File.separator))+File.separator+UUID.randomUUID().toString()+".png");
						image.setImgFilePath(picTemp.getAbsolutePath());
						tempExcelImageList.add(image);
						
					}
					excelImageList.add(tempExcelImageList);
					// 复制源表中的合并单元格
					MergerRegion(sheetCreat, sheet);
					int firstRow = sheet.getFirstRowNum();
					int lastRow = sheet.getLastRowNum();
					for (int i = firstRow; i <= lastRow; i++) {
						// 创建新建excel Sheet的行
						HSSFRow rowCreat = sheetCreat.createRow(i);
						// 取得源有excel Sheet的行
						HSSFRow row = sheet.getRow(i);
						if (row == null)
							continue;
						
						
						// 单元格式样
						rowCreat.setZeroHeight(row.getZeroHeight());
						int[] cells=getStartAndEndRowAndCellNum(sheet);
						int firstCell =cells[0];
						int lastCell =cells[1];
						if (i == firstRow) {
							for (int j = firstCell; j < lastCell; j++) {
								short width = sheet.getColumnWidth((short) j);
								sheetCreat.setColumnWidth((short) j,
										(width));
								boolean isHidden = sheet.isColumnHidden(j);
								sheetCreat.setColumnHidden(j, isHidden);
							}
						}
						for (int j = firstCell; j < lastCell; j++) {
							if (row.getCell(j) == null)
								continue;
							rowCreat.createCell(j);
							// 设置高度
							rowCreat.getCell(j).getRow().setHeight(
									row.getCell(j).getRow().getHeight());
							// 复制样式
							HSSFCellStyle newStyle = wbCreat.createCellStyle();
							HSSFCellStyle oldStely = row.getCell(j)
									.getCellStyle();
							newStyle = copyStyle(newStyle, wbCreat, oldStely,
									wb);
							rowCreat.getCell(j).setCellStyle(newStyle);
							// 复制内容
							// 复制内容
							switch (row.getCell(j).getCellType()) {
							case Cell.CELL_TYPE_STRING:
								rowCreat.getCell(j).setCellValue(
										copyfont(wbCreat, wb, row.getCell(j)));
								break;
							case Cell.CELL_TYPE_NUMERIC:
								rowCreat.getCell(j).setCellValue(
										row.getCell(j).getNumericCellValue());
								break;
							case Cell.CELL_TYPE_FORMULA:
								try {
									rowCreat.getCell(j).setCellValue(
											String.valueOf(row.getCell(j)
													.getNumericCellValue()));
								} catch (IllegalStateException e) {
									try {
										rowCreat
												.getCell(j)
												.setCellValue(
														String
																.valueOf(row
																		.getCell(
																				j)
																		.getRichStringCellValue()));
										throw new GlobalException("" + e.getMessage());
									} catch (Exception ex) {
										rowCreat.getCell(j)
												.setCellValue("公式出错");
										throw new GlobalException("" + e.getMessage());
									}
								}
								break;
							}
						}
					}
					int[] rowBreaks = sheet.getRowBreaks();  
			        for (int rowBreaksIndex = 0; rowBreaksIndex < rowBreaks.length; rowBreaksIndex++) {  
			        	sheetCreat.setRowBreak(sheetCreat.getLastRowNum()+1+rowBreaks[rowBreaksIndex]);
			        }
				}
				FileOutputStream fileOut = new FileOutputStream(toFile);
				wbCreat.write(fileOut);
				fileOut.flush();
				fileOut.close();
				in.close();
				InputStream toFileIn = new FileInputStream(toFile);
				HSSFWorkbook wbToFileIn = new HSSFWorkbook(toFileIn);
				int x=0;
				for (int ii = wbToFileIn.getNumberOfSheets()-1; ii >=0 ; ii--) {
					ExcelPoiUtils.insertImg(toPath, ii, excelImageList.get(excelImageList.size()-1-x));
					x++;
				}
				if(excelImageList!=null){
					for(List<ExcelImage> ss: excelImageList){
						if(ss!=null){
							for (ExcelImage excelImage : ss) {
								FileUtils.delAllFile(excelImage.getImgFilePath());
							}
						}
					}
				}
			} catch (RuntimeException e) {
				////e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		
	};
	/**
	 * @Title:  获取起始结束行和起始结束列
	 * @Description: TODO
	 * @return string[0] 开始列,string[1] 结束列
	 * @throws 
	 */
	private static int[] getStartAndEndRowAndCellNum(HSSFSheet sheet){
		int[] cellNum={100,0};
		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getLastRowNum();
		for (int i = firstRow; i <= lastRow; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null) continue;
			int firstCell = row.getFirstCellNum();
			if(firstCell<cellNum[0]){
				cellNum[0]=firstCell;
			}
			int lastCell = row.getLastCellNum();
			if(lastCell>cellNum[1]){
				cellNum[1]=lastCell;
			}
		}
		return cellNum;
	} 
	private static HSSFRichTextString copyfont(HSSFWorkbook wbCreat,
			HSSFWorkbook wb, HSSFCell oldCell) throws GlobalException {
		int fromIndex = 0;
		int toIndex = 0;
		String value = "";
		// 获取单元格中的数据

		HSSFRichTextString rts = oldCell
				.getRichStringCellValue();
		// 获取每个单元格数据的style属性
		HSSFFont fontTemp = oldCell.getCellStyle().getFont(wb);
		// 处理上下标
		if (rts.numFormattingRuns() > 0) {
			for (int k = 0; k < rts.numFormattingRuns(); k++) {
				toIndex = rts.getIndexOfFormattingRun(k);
				String temp = rts.toString().substring(fromIndex, toIndex);
				
				if (fontTemp.getTypeOffset() == Font.SS_SUB) {// 判断下标
					Font font1 = wbCreat.createFont();
					font1.setTypeOffset(Font.SS_SUB);
					rts.applyFont(fromIndex, toIndex, font1);
				}else if (fontTemp.getTypeOffset() == Font.SS_SUPER) {
					Font font2 = wbCreat.createFont();
					font2.setTypeOffset(Font.SS_SUPER);
					rts.applyFont(fromIndex, toIndex, font2);
				}
				else{
					Font font =isHasThisFont(fontTemp.getFontName(),fontTemp.getFontHeightInPoints(),fontTemp.getColor(),fontTemp.getBoldweight());
					if(font==null){
						font = wbCreat.createFont();
						font.setCharSet(fontTemp.getCharSet());
						font.setBoldweight(fontTemp.getBoldweight());
						font.setTypeOffset(fontTemp.getTypeOffset());
						font.setColor(fontTemp.getColor());
						font.setFontHeight(fontTemp.getFontHeight());
						font.setFontHeightInPoints(fontTemp.getFontHeightInPoints());
						font.setFontName(fontTemp.getFontName());
						font.setItalic(fontTemp.getItalic());
						font.setStrikeout(fontTemp.getStrikeout());
						font.setUnderline(fontTemp.getUnderline());
						fontList.add(font);
					}
					rts.applyFont(fromIndex, toIndex, font);
				}
				value += temp;
				if (!value.equals("")) {
					try {
						fontTemp = wb.getFontAt(rts.getFontOfFormattingRun(k));
					} catch (RuntimeException e) {
						////e.printStackTrace();
						throw new GlobalException("" + e.getMessage());
					}
				}
				fromIndex = toIndex;
			}
			toIndex = rts.length();
			String temp1 = rts.toString().substring(fromIndex, toIndex);
			if (fontTemp.getTypeOffset() == Font.SS_SUB) {
				Font font1 = wbCreat.createFont();
				font1.setTypeOffset(Font.SS_SUB);
				rts.applyFont(fromIndex, toIndex, font1);
			}else if (fontTemp.getTypeOffset() == Font.SS_SUPER) {
				Font font2 = wbCreat.createFont();
				font2.setTypeOffset(Font.SS_SUPER);
				rts.applyFont(fromIndex, toIndex, font2);
			}else{
				Font font =isHasThisFont(fontTemp.getFontName(),fontTemp.getFontHeightInPoints(),fontTemp.getColor(),fontTemp.getBoldweight());
				if(font==null){
					font = wbCreat.createFont();
					font.setCharSet(fontTemp.getCharSet());
					font.setBoldweight(fontTemp.getBoldweight());
					font.setTypeOffset(fontTemp.getTypeOffset());
					font.setColor(fontTemp.getColor());
					font.setFontHeight(fontTemp.getFontHeight());
					font.setFontHeightInPoints(fontTemp.getFontHeightInPoints());
					font.setFontName(fontTemp.getFontName());
					font.setItalic(fontTemp.getItalic());
					font.setStrikeout(fontTemp.getStrikeout());
					font.setUnderline(fontTemp.getUnderline());
					fontList.add(font);
				}
				rts.applyFont(fromIndex, toIndex, font);
			}
			value += temp1;
		}
		return rts;
	}

	private static HSSFCellStyle copyStyle(HSSFCellStyle newStyle,
			HSSFWorkbook wbCreat, HSSFCellStyle oldStely, HSSFWorkbook wb) {
		newStyle.setAlignment(oldStely.getAlignment());
		newStyle.setBorderTop(oldStely.getBorderTop());
		newStyle.setBorderBottom(oldStely.getBorderBottom());
		newStyle.setBorderLeft(oldStely.getBorderLeft());
		newStyle.setBorderRight(oldStely.getBorderRight());
		newStyle.setBottomBorderColor(oldStely.getBottomBorderColor());
		newStyle.setFillBackgroundColor(oldStely.getFillBackgroundColor());
		newStyle.setDataFormat(oldStely.getDataFormat());
		newStyle.setFillForegroundColor(oldStely.getFillForegroundColor());
		newStyle.setFillPattern(oldStely.getFillPattern());
		newStyle.setHidden(oldStely.getHidden());
		newStyle.setIndention(oldStely.getIndention());
		newStyle.setLeftBorderColor(oldStely.getLeftBorderColor());
		newStyle.setLocked(oldStely.getLocked());
		newStyle.setRightBorderColor(oldStely.getRightBorderColor());
		newStyle.setRotation(oldStely.getRotation());
		newStyle.setTopBorderColor(oldStely.getTopBorderColor());
		newStyle.setVerticalAlignment(oldStely.getVerticalAlignment());
		newStyle.setWrapText(oldStely.getWrapText());
		// 设置字体
		
		Font font = oldStely.getFont(wb);
		Font newfont =isHasThisFont(font.getFontName(),font.getFontHeightInPoints(),font.getColor(),font.getBoldweight());
		if(newfont==null){
			newfont = wbCreat.createFont();
			newfont.setFontHeightInPoints(font.getFontHeightInPoints());// 设置字号
			newfont.setFontName(font.getFontName());// 设置字体
			newfont.setItalic(font.getItalic());// 设置是否斜体
			newfont.setStrikeout(font.getStrikeout());// 设置是否强调
			newfont.setFontHeight(font.getFontHeight());
			newfont.setCharSet(font.getCharSet());
			newfont.setColor(font.getColor());
			newfont.setTypeOffset(font.getTypeOffset());
			newfont.setUnderline(font.getUnderline());
			if (font.getBoldweight() == Font.BOLDWEIGHT_BOLD) {
				newfont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			}
			fontList.add(newfont);
		}
		newStyle.setFont(newfont);
		return newStyle;
	}
	public static Font isHasThisFont(String fontName,short fontSize,short fontColor,short bold){
		Font retfont=null;
		for (Font thisFont:fontList) {
			if(thisFont.getFontName().equals(fontName)
					&&thisFont.getBoldweight()==bold
					&&thisFont.getColor()==fontColor
					&&thisFont.getFontHeightInPoints()==fontSize){
				retfont=thisFont;
				break;
			}
			
		}
		return retfont;
	}
	/**
	 * 复制原有sheet的合并单元格到新创建的sheet
	 * 
	 * @param sheetCreat
	 *            新创建sheet
	 * @param sheet
	 *            原有的sheet
	 */
	@SuppressWarnings("deprecation")
	private static void MergerRegion(HSSFSheet sheetCreat, HSSFSheet sheet) {
		int sheetMergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergerCount; i++) {
			org.apache.poi.hssf.util.Region mergedRegionAt = sheet.getMergedRegionAt(i);
			sheetCreat.addMergedRegion(mergedRegionAt);
		}
	}
	public static List<MyPictureData> getAllPictures(HSSFWorkbook workbook) {  
        List<MyPictureData> list = new ArrayList<MyPictureData>();  
  
        List<HSSFPictureData> pictureList = workbook.getAllPictures();  
        List<ClientAnchorInfo> clientAnchorRecords = getClientAnchorRecords(workbook);  
          
//        if (pictureList.size() != clientAnchorRecords.size()) {  
//            throw new RuntimeException("解析文件中的图片信息出错，找到的图片数量和图片位置信息数量不匹配");  
//        }  
          
        for (int i = 0; i < (pictureList.size()>clientAnchorRecords.size()?clientAnchorRecords.size():clientAnchorRecords.size()); i++) {  
            HSSFPictureData pictureData = pictureList.get(i);  
            ClientAnchorInfo anchor = clientAnchorRecords.get(i);  
            HSSFSheet sheet = anchor.sheet;  
            EscherClientAnchorRecord clientAnchorRecord = anchor.clientAnchorRecord;  
            list.add(new MyPictureData(workbook, sheet, pictureData, clientAnchorRecord));  
        }  
          
        return list ;  
    } 
	private static List<ClientAnchorInfo> getClientAnchorRecords(HSSFWorkbook workbook) {  
        List<ClientAnchorInfo> list = new ArrayList<ClientAnchorInfo>();  
          
        EscherAggregate drawingAggregate = null;  
        HSSFSheet sheet = null;  
        List<EscherRecord> recordList = null;  
        Iterator<EscherRecord> recordIter = null;  
        int numSheets = workbook.getNumberOfSheets();  
        for(int i = 0; i < numSheets; i++) {  
            sheet = workbook.getSheetAt(i);  
            drawingAggregate = sheet.getDrawingEscherAggregate();  
            if(drawingAggregate != null) {  
                recordList = drawingAggregate.getEscherRecords();  
                recordIter = recordList.iterator();  
                while(recordIter.hasNext()) {  
                    getClientAnchorRecords(sheet, recordIter.next(), 1, list);  
                }  
            }  
        }  
          
        return list;  
    }  
  
    private static void getClientAnchorRecords(HSSFSheet sheet, EscherRecord escherRecord, int level, List<ClientAnchorInfo> list) {  
        List<EscherRecord> recordList = null;  
        Iterator<EscherRecord> recordIter = null;  
        EscherRecord childRecord = null;  
        recordList = escherRecord.getChildRecords();  
        recordIter = recordList.iterator();  
        while(recordIter.hasNext()) {  
            childRecord = recordIter.next();  
            if(childRecord instanceof EscherClientAnchorRecord) {  
                ClientAnchorInfo e = new ClientAnchorInfo(sheet, (EscherClientAnchorRecord) childRecord);  
                list.add(e);  
            }  
            if(childRecord.getChildRecords().size() > 0) {  
                getClientAnchorRecords(sheet, childRecord, level+1, list);  
            }  
        }  
    } 
//	public static void dropDownList42003(String dataSource) throws Exception {
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet realSheet = workbook.createSheet("下拉列表测试");
//		String[] datas = dataSource.split("\\,");
//		DVConstraint dvConstraint = DVConstraint
//				.createExplicitListConstraint(datas);
//		CellRangeAddressList addressList = null;
//		HSSFDataValidation validation = null;
//		for (int i = 0; i < 100; i++) {
//			addressList = new CellRangeAddressList(i, i, 0, 0);
//			validation = new HSSFDataValidation(addressList, dvConstraint);
//			// 03 默认setSuppressDropDownArrow(false)  
//			// validation.setSuppressDropDownArrow(false);  
//			// validation.setShowErrorBox(true);  
//			validation.setShowErrorBox(false);// 取消弹出错误框  
//			realSheet.addValidationData(validation);
//		}
//		addressList = null;
//		validation = null;
//	}
	private static class ClientAnchorInfo {  
        public HSSFSheet sheet;  
        public EscherClientAnchorRecord clientAnchorRecord;  
          
        public ClientAnchorInfo(HSSFSheet sheet, EscherClientAnchorRecord clientAnchorRecord) {  
            super();  
            this.sheet = sheet;  
            this.clientAnchorRecord = clientAnchorRecord;  
        }  
    }  
}
