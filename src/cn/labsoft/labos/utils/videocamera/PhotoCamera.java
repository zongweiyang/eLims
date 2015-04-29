package cn.labsoft.labos.utils.videocamera;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.PropertiesTools;

/**
 * @Project: VideoCamera
 * @package: cn.labsoft.labos.utils.videocamera
 * @ClassFile: PhotoCamera.java
 * @Description:
 * @Copyright: Copyright (c) 2013-10-14 下午15:12:37
 * @Company: LabSoft.Co.,Ltd.
 * @author Charles(charles@labsoft.cn)
 * @version 1.0
 */
public class PhotoCamera implements IPhotoCamera {

	/**
	 * 由Flex调用该入口方法
	 * 
	 * @param length
	 * @param bitmap_data
	 * @throws Exception
	 */
	public String savePhoto(int length, String bitmap_data) throws Exception {
		byte[] b = new byte[length];
		b = new sun.misc.BASE64Decoder().decodeBuffer(bitmap_data);
		return writeToFile(b);
	}

	/**
	 * 将照片以字节流的形式写入临时文件方法
	 * 
	 * @param b
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws GlobalException 
	 */
	private String writeToFile(byte[] b) throws IOException, GlobalException {
		String photoId = "";
		try {
			String photoPath = PropertiesTools.getPropertiesValueByFileAndKey(
					"resource.properties", "videoimg");
			File f = new File(photoPath);
			if (!f.exists()) {
				f.mkdirs();
			}
			String photoName = UUID.randomUUID().toString().replace("-", "")
					+ ".jpg";
			File file = VideoUtil.getFileFromBytes(b, photoPath
					+ File.separator + photoName);
			photoId = file.getName();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return photoId;
	}
}
