package cn.labsoft.labos.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipFile;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class ZipUtils {
	static final int BUFFER = 2048;

    @SuppressWarnings("unchecked")
	public static void unZIP(String zipPath,String unZipPath) throws GlobalException {
        try {
            String fileName =zipPath;
            String filePath =unZipPath+"\\";
            ZipFile zipFile = new ZipFile(fileName);
            Enumeration emu = zipFile.getEntries();
            while(emu.hasMoreElements()){
            	org.apache.tools.zip.ZipEntry entry = (org.apache.tools.zip.ZipEntry)emu.nextElement();
                //会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
                if (entry.isDirectory())
                {
                    new File(filePath + entry.getName()).mkdirs();
                    continue;
                }
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(filePath + entry.getName());
                //加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
                //而这个文件所在的目录还没有出现过，所以要建出目录来。
                File parent = file.getParentFile();
                if(parent != null && (!parent.exists())){
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER); 
                
                int count;
                byte data[] = new byte[BUFFER];
                while ((count = bis.read(data, 0, BUFFER)) != -1)
                {
                    bos.write(data, 0, count);
                }
                bos.flush();
                bos.close();
                bis.close();
            }
            zipFile.close();
        } catch (Exception e) {
            //e.printStackTrace();
        	throw new GlobalException("" + e.getMessage());
        }
    }
    public static void zip(String zipPath,String unZipPath) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipPath);
            org.apache.tools.zip.ZipOutputStream out = new org.apache.tools.zip.ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];
            File f = new File(unZipPath);
            File files[] = f.listFiles();
            if(new File(unZipPath).isFile()){
            	FileInputStream fi = new FileInputStream(new File(unZipPath));
				origin = new BufferedInputStream(fi, BUFFER);
				org.apache.tools.zip.ZipEntry entry = new org.apache.tools.zip.ZipEntry(
						new File(unZipPath).getName());
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
            }else{
	            for (int i = 0; i < files.length; i++) {
					FileInputStream fi = new FileInputStream(files[i]);
					origin = new BufferedInputStream(fi, BUFFER);
					org.apache.tools.zip.ZipEntry entry = new org.apache.tools.zip.ZipEntry(
							files[i].getName());
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
				}
			}
            out.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws GlobalException{
    	String zipPath = "D:\\动态参数.zip";
    	String unZipPath = "D:\\动态参数";
    	ZipUtils.unZIP(zipPath, unZipPath);
    }
}
