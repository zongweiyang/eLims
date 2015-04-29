package cn.labsoft.labos.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class FileUtils {
	/**
	 * 获得文本文件内容
	 * 
	 * @param fileName
	 *            类路径下的文件名
	 * @return 返回类型 String 文本文件内容
	 * 
	 */
	public static boolean creatFolders(String foldersPath) {
		try {
			System.out.println(System.getProperty("os.name"));
			String splitStr = "";
			if(System.getProperty("os.name").toLowerCase().indexOf("win")>-1){
				splitStr = "\\"+File.separator;
			}else{
				splitStr = File.separator;
			}
			String[] dirs = foldersPath.split(splitStr);
			String haveBeenBuild = "";
			if (null != dirs && 0 < dirs.length) {
				haveBeenBuild = dirs[0];
				for (int i = 1; i < dirs.length; i++) {
					haveBeenBuild = haveBeenBuild + File.separator + dirs[i];
					File newDir = new File(haveBeenBuild);
					if (!newDir.exists()) {
						newDir.mkdir();
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getFilecontent(String fileName) {
		String fileContent = "";
		FileReader fileReader;
		try {
			String path = this.getClass().getClassLoader()
					.getResource(fileName).getPath();
			File f = new File(path);
			fileReader = new FileReader(f);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line;
			}
			reader.close();
			return fileContent;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}

	}

	public static String fileSize(float size) {
		DecimalFormat df = new DecimalFormat("###.##");
		float f;
		if (size < 1024) {
			return (df.format(new Float(size).doubleValue()) + "B");
		} else if (size < 1024 * 1024) {
			f = (size / 1024);
			return (df.format(new Float(f).doubleValue()) + "KB");
		} else {
			f = (size / (1024 * 1024));
			return (df.format(new Float(f).doubleValue()) + "MB");
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹路径及名称 如c:/fqf
	 * @throws GlobalException 
	 * 
	 */
	public static void delFolder(String folderPath) throws GlobalException {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            文件夹路径 如 c:/fqf
	 * @throws GlobalException 
	 * 
	 */
	public static void delAllFile(String path) throws GlobalException {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 
	 * 在文件名称后面加一个“.”号
	 * 
	 * @param fileName
	 *            文件名
	 * @return 返回类型 String 返回以"."结尾的文件名
	 */
	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 
	 * 将文件名按拆分符号拆分
	 * 
	 * @param fileName
	 *            文件名
	 * @param splitChar
	 *            拆分符号
	 * @return 返回类型
	 */
	public static String getLastStr(String fileName, String splitChar) {
		int pos = fileName.lastIndexOf(splitChar);
		return fileName.substring(pos);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		File file=new File("E:\\workspace\\Core7\\WebRoot\\WEB-INF\\lib"); 
		String test[] = file.list();  
		for(int i=0;i<test.length;i++)  {
			System.out.println(test[i]);  
		}
		
		
	}

	public static File getFileFromBytes(byte[] b, String outputFile) throws GlobalException {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					throw new GlobalException("" + e1.getMessage());
				}
			}
		}
		return file;
	}

	public static void writeObjectToFile(Object object, String filePath) throws GlobalException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(object);
			oos.flush();
			oos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("文件写入错误!");
			System.exit(-1);
			throw new GlobalException("" + e1.getMessage());
		} finally {
			if (null != oos) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
	}

	public static Object readObjectFromFile(String filePath) throws GlobalException {
		Object object = null;

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath));
			object = ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件!");
			throw new GlobalException("" + e.getMessage());
		} catch (IOException e) {
			System.out.println("文件读取错误!");
			throw new GlobalException("" + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		}
		return object;

	}

}

class Student implements Serializable {

	private static final long serialVersionUID = -4096979979003245189L;
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
