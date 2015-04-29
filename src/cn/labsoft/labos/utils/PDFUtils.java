package cn.labsoft.labos.utils;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PDFUtils {
	public static void main(String[] args) {
		String pdfFilePath = "c:\\6.pdf";
		String imgFolderPath = "c:\\3\\img1";
		PDFUtils.getPdfImageByPage(pdfFilePath, imgFolderPath,11);
	}

	public static String getPdfImageByPage(String pdfFilePath,
			String imgFolderPath, int page) {
		try {
			File imgFolder = new File(imgFolderPath + File.separator);
			if (!imgFolder.exists()) {
				imgFolder.mkdir();
			}

			String imgFilePrefixOne = pdfFilePath.substring(pdfFilePath
					.lastIndexOf(File.separator) + 1, pdfFilePath.length());
			String imgFilePrefixTwo = imgFilePrefixOne.substring(0,
					imgFilePrefixOne.lastIndexOf("."));

			PDFUtils.ViewPage(pdfFilePath, page, imgFolderPath + File.separator
					+ imgFilePrefixTwo + "_" + page + ".jpg");
		} catch (Exception e) {
			//e.printStackTrace();
			return "0";
		}
		return "1";
	}

	public static void changePdfToImage(String pdfFilePath,
			String imgFolderPath) throws GlobalException {
		File imgFolder = new File(imgFolderPath + File.separator);
		if (!imgFolder.exists()) {
			imgFolder.mkdir();
		}

		String imgFilePrefixOne = pdfFilePath.substring(pdfFilePath
				.lastIndexOf(File.separator) + 1, pdfFilePath.length());
		String imgFilePrefixTwo = imgFilePrefixOne.substring(0,
				imgFilePrefixOne.lastIndexOf("."));
		PDFFile pdfFile = PDFUtils.getPdfFile(pdfFilePath);
		int totalPage = pdfFile.getNumPages();
		for (int i = 0; i < totalPage; i++) {
			PDFUtils.ViewPage(pdfFilePath, i, imgFilePrefixTwo + "_" + i
					+ ".jpg");
		}
	}

	/**
	 * 
	 * @param fileName
	 *            pdf路径
	 * @param page
	 *            截图的页面
	 * @param outFile
	 *            生成的图片路径
	 * @throws GlobalException 
	 */
	private static void ViewPage(String fileName, int page, String outFile) throws GlobalException {
		if (fileName == null || fileName.length() <= 0)
			return;
		if (outFile == null || outFile.lastIndexOf('.') < 0)
			return;
		try {
			String type = outFile.substring(outFile.lastIndexOf('.') + 1);
			if (type == null) {
				return;
			}
			PDFFile pdfFile = getPdfFile(fileName);
			if (pdfFile == null)
				return;
			PDFPage pdfPage = pdfFile.getPage(page);
			if (pdfPage == null)
				return;
			Rectangle rect = new Rectangle(0, 0, (int) (pdfPage.getBBox()
					.getWidth()), (int) pdfPage.getBBox().getHeight());
			Image img = pdfPage.getImage(rect.width, rect.height, rect, null,
					true, true);
			BufferedImage tag = new BufferedImage(rect.width, rect.height,
					BufferedImage.TYPE_INT_BGR);
			tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
					null);
			FileOutputStream out = new FileOutputStream(outFile); // 输出到文件流
			System.out.println("成功保存图片到 ：" + outFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag); // JPEG编码
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
	}

	/**
	 * 建立PDF文档读取类
	 * 
	 * @param filePath
	 *            PDF文件的路径
	 * @return null 或者PDFFile instance
	 * @throws GlobalException 
	 */
	private static PDFFile getPdfFile(String filePath) throws GlobalException {
		try {
			File file = new File(filePath);
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			FileChannel channel = raf.getChannel();
			ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
					channel.size());
			PDFFile pdffile = new PDFFile(buf);
			return pdffile;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GlobalException("" + ex.getMessage());
		}
	}
}
