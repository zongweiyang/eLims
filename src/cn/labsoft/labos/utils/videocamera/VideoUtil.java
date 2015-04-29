package cn.labsoft.labos.utils.videocamera;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * @Project: VideoCamera
 * @package: com.dreamsun.flex.video
 * @ClassFile: BeanUtil.java
 * @Description:
 * @Copyright: Copyright (c) 2009-11-11 下午09:25:39
 * @Company: DreamSun.Co.,Ltd.
 * @author LiKaijin(BillLee2008@hotmail.com)
 * @version 1.0
 */
public class VideoUtil {

	/**
	 * 时间流水号
	 * @return
	 */
	public static String getSequence() {
		//Date d = new Date();
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd(HH:mm:ss)");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisecond = calendar.get(Calendar.MILLISECOND);
		return "" + year + (month < 10 ? "0" + month : month)
				+ (day < 10 ? "0" + day : day)
				+ (hour < 10 ? "0" + hour : hour)
				+ (minute < 10 ? "0" + minute : minute)
				+ (second < 10 ? "0" + second : second)
				+ (millisecond < 10 ? "0" + millisecond : millisecond);
	}
	
	
	/**
	 * 产生随机数
	 * @param seed
	 * @return
	 */
	public static long getRandom(int seed){
		Random random = new Random();
		return random.nextInt(seed);
	}
	
	
	/**
	 * 把字节数组保存为一个文件
	 * @throws GlobalException 
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) throws GlobalException {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fos = new FileOutputStream(file);
			stream = new BufferedOutputStream(fos);
			stream.write(b);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					throw new GlobalException("" + ex.getMessage());
				}
			}
		}
		return file;
	}
	
	
	/**
	 * 文件转化为字节数组
	 * @throws GlobalException 
	 */
	public static byte[] getBytesFromFile(File file) throws GlobalException {
		if (file == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			throw new GlobalException("" + e.getMessage());
		}
	}
	
	
	/**
	 * 从字节数组获取对象
	 */
	public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
		if (objBytes == null || objBytes.length == 0) {
			return null;
		}
		ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
	}

	
	/**
	 * 从对象获取一个字节数组
	 */
	public static byte[] getBytesFromObject(Serializable obj) throws Exception {
		if (obj == null) {
			return null;
		}
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		return bo.toByteArray();
	}
	
	
	
	public static void main(String[] args) {
	  System.out.println("===========" + getSequence()+getRandom(1000));
		for (int i = 0; i < 10; i++) {
			 System.out.println("===========" + getRandom(1000));
		}

	}

}
