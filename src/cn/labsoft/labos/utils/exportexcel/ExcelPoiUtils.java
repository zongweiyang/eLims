package cn.labsoft.labos.utils.exportexcel;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.ImageUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ExcelPoiUtils {
	private static HSSFWorkbook wb = null;
	private static HSSFSheet sheet = null;
	private static HSSFPatriarch patriarch = null;

	public ExcelPoiUtils() {
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
	 * @throws IOException
	 * @throws GlobalException 
	 */
	@SuppressWarnings("deprecation")
	public static void insertImg(String xlsFilePath, int index,
			List<ExcelImage> excelImageList) throws IOException, GlobalException {
		InputStream fileInput = null;
		FileOutputStream fileOut = null;
		try {
			fileInput = new FileInputStream(xlsFilePath);
			wb = new HSSFWorkbook(fileInput);
			sheet = wb.getSheetAt(index);
			sheet.setDefaultColumnWidth((short) 2);
			patriarch = sheet.createDrawingPatriarch();

			if (null != excelImageList && excelImageList.size() > 0) {
				for (ExcelImage img : excelImageList) {
					String strImagePath = "";
					if (null != img && null != img.getImgStr()
							&& !"".equals(img.getImgStr

							())) {
						strImagePath =

						ImageUtils.generateImage(img.getImgFilePath(),

						img.getImgStr());
						img.setImgFilePath(strImagePath);
					}
					setImageToCell((short) img.getStartCol(),
							img.getStartRow(), (short)

							(img.getStartCol() + img.getWidthCol

							()), img.getStartRow() +

							img.getHeightRow(), img.getImgFilePath());
				}
			}
			fileOut = new FileOutputStream(xlsFilePath);
			wb.write(fileOut);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (null != fileInput)
				fileInput.close();
			if (null != fileOut)
				fileOut.close();
		}
	}

	private static void setImageToCell(short col1, int row1, short col2,
			int row2, String imagePath) throws IOException, GlobalException {
		BufferedImage bufferImg = null;
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		try {
			bufferImg = ImageIO.read(new File(imagePath));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			bufferImg = op.filter(bufferImg, null);
			ImageIO.write(bufferImg, "png", byteArrayOut);
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255,
					col1, row1, col2, row2);
			anchor.setAnchorType(2);
			// 不拉伸
			// patriarch.createPicture(anchor, wb.addPicture(byteArrayOut
			// .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
			// 拉伸
			patriarch.createPicture(
					anchor,
					wb.addPicture(byteArrayOut.toByteArray(),
							HSSFWorkbook.PICTURE_TYPE_JPEG)).resize();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (null != byteArrayOut)
				byteArrayOut.close();
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
	@SuppressWarnings("deprecation")
	public static void modifyExcel(String xlsFilePath, int index,
			List<ExcelString> excelStringList) throws GlobalException {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					xlsFilePath));

			HSSFWorkbook wb = new HSSFWorkbook(fs);

			HSSFSheet sheet = wb.getSheetAt(index);

			if (null != excelStringList && 0 < excelStringList.size()) {
				for (ExcelString es : excelStringList) {
					HSSFRow row = sheet.getRow(es.getRow());
					HSSFCell cell = row.getCell((short)

					es.getCol());

					HSSFFont font = wb.createFont();
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					font.setFontHeightInPoints((short) 20);
					HSSFCellStyle style = wb.createCellStyle();

					// 样式对象
					style.setVerticalAlignment

					(HSSFCellStyle.VERTICAL_CENTER);// 垂直
					style.setAlignment

					(HSSFCellStyle.ALIGN_CENTER);// 水平
					style.setFont(font);
					cell.setCellStyle(style);
					cell.setCellValue(es.getValue());
				}
			}
			FileOutputStream fileOut = new FileOutputStream(xlsFilePath);

			wb.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static void main(String[] args) throws GlobalException {

		try {
			List<ExcelImage> excelImageList = new ArrayList<ExcelImage>

			();
			ExcelImage excelImage = new ExcelImage(14, 15, 4, 1,
					"D:\\TEST\\1.gif", "");
			excelImageList.add(excelImage);
			excelImage = new ExcelImage(14, 16, 4, 1, "D:\\TEST\\2.gif",

			"");
			excelImageList.add(excelImage);
			excelImage = new ExcelImage(14, 17, 4, 1, "D:\\TEST\\3.gif",

			"");
			excelImageList.add(excelImage);
			ExcelPoiUtils.insertImg("D:\\TEST\\1.xls", 0,

			excelImageList);
		} catch (Exception e) { // TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		/*
		 * try { List<ExcelString> exceclStringList = new
		 * 
		 * ArrayList<ExcelString>(); ExcelString excelString = new
		 * ExcelString("宋体", 14, false,
		 * 
		 * false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK,
		 * ScriptStyle.SUPERSCRIPT, Alignment.LEFT, VerticalAlignment.TOP, 0, 0, "
		 * 编号：00
		 * 
		 * -2014-033"); exceclStringList.add(excelString);
		 * ExcelPoiUtils.modifyExcel("D:\\TEST\\1.xls", 0,
		 * 
		 * exceclStringList); } catch (Exception e) { //e.printStackTrace(); }
		 */
	}

}
