package cn.labsoft.labos.utils.onlinepdf;

import java.io.File;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2Pdf {
	public static boolean convert(String srcFilePath, String descFilePath) throws GlobalException {
		try {
			File srcFile = new File(srcFilePath);
			File descFile = new File(descFilePath);
			if (srcFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				connection.connect();
				DocumentConverter converter = new OpenOfficeDocumentConverter(
						connection);
				converter.convert(srcFile, descFile);
				descFile.createNewFile();
				connection.disconnect();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}
	public static void main(String[] args){
		Thread t1 = new Thread(new ConvertRunnable("C:\\flexpaper\\doc\\1x.doc","C:\\flexpaper\\pdf\\1x.pdf"));
		Thread t2 = new Thread(new ConvertRunnable("C:\\flexpaper\\doc\\2x.xls","C:\\flexpaper\\pdf\\2x.pdf"));
		Thread t3 = new Thread(new ConvertRunnable("C:\\flexpaper\\doc\\3x.ppt","C:\\flexpaper\\pdf\\3x.pdf"));
		t1.start();
		t2.start();
		t3.start();
	}
}

