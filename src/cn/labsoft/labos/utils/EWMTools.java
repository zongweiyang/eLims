package cn.labsoft.labos.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.swetake.util.Qrcode;

public class EWMTools {

	public static boolean generateEWM(File imageFile, String content, char rc,
			int size, String frontColor, String backColor) {
		try {
			BufferedImage bufImg = null;
			try {
				Qrcode qrcodeHandler = new Qrcode();
				// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
				String str=String.valueOf(rc);
				if(str.equals("")){
					rc = 'M';
				}
				qrcodeHandler.setQrcodeErrorCorrect(rc);
				qrcodeHandler.setQrcodeEncodeMode('B');
				// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
				if(size==0){
					size = 7;
				}
				qrcodeHandler.setQrcodeVersion(size);
				// 获得内容的字节数组，设置编码格式
				byte[] contentBytes = content.getBytes("GB2312");
				// 图片尺寸
				int imgSize = 67 + 12 * (size - 1);
				bufImg = new BufferedImage(imgSize, imgSize,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D gs = bufImg.createGraphics();
				// 设置背景颜色
				if (null != backColor && !"".equals(backColor)) {
					Color color = Color.decode(backColor);
					gs.setBackground(color);
				} else {
					gs.setBackground(Color.WHITE);
				}

				gs.clearRect(0, 0, imgSize, imgSize);

				// 设定图像颜色> BLACK
				if (null != frontColor && !"".equals(frontColor)) {
					Color color = Color.decode(frontColor);
					gs.setColor(color);
				} else {
					gs.setColor(Color.BLACK);
				}
				// 设置偏移量，不设置可能导致解析出错
				int pixoff = 2;
				// 输出内容> 二维码
				if (contentBytes.length > 0 && contentBytes.length <800) {
					boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
					for (int i = 0; i < codeOut.length; i++) {
						for (int j = 0; j < codeOut.length; j++) {
							if (codeOut[j][i]) {
								gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3,
										3);
							}
						}
					}
				} else {
					throw new Exception("QRCode content bytes length = "
							+ contentBytes.length + " not in [0, 800].");
				}
				gs.dispose();
				bufImg.flush();
			} catch (Exception e) {
				//e.printStackTrace();
				return false;
			}
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "png", imageFile);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	public static String decoderEWM(File imageFile) throws GlobalException {
		// QRCode 二维码图片的文件
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new EWMImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
			throw new GlobalException("" + dfe.getMessage());
		}
		return content;
	}

	

	public static void main(String[] args) throws GlobalException {
		EWMTools.generateEWM(new File("d:/1.png"), "席飞", 'M', 20, "", "");
		System.out.println(EWMTools.decoderEWM(new File("d:/1.png")));
	}
}
