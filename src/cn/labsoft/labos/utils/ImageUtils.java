package cn.labsoft.labos.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * <strong>Title : ImageUtils Image工具类</strong>. <br>
 * <strong>Description : Image工具类</strong>. <br>
 * <strong>Create on : Jul 23, 2012 10:43:45 AM </strong>. <br>
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
public class ImageUtils {

	
	public static String generateImage(String tempPath, String str) throws GlobalException {
		// 在内存中创建图象

		String uuidStr = UUID.randomUUID().toString().replace("-", "");
		String returnImagePath = tempPath + File.separator + uuidStr + ".png";
		try {
			BufferedImage bufferedImage = new BufferedImage(130, 60,
					BufferedImage.TYPE_INT_RGB);
			// 获取图形上下文
			Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 130, 60);
			g.setFont(new Font("宋体", Font.BOLD, 22));
			g.setColor(Color.BLACK);
			if (null != str && str.length() == 4) {
				g.drawString(str, 45, 40);
			} else {
				g.drawString(str, 50, 40);
			}
			FileOutputStream fos = new FileOutputStream(returnImagePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(bufferedImage);
			if (null != fos) {
				fos.close();
			}
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return returnImagePath;
	}

}
