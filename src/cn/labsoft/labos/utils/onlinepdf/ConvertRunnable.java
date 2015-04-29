package cn.labsoft.labos.utils.onlinepdf;

import java.io.File;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class ConvertRunnable implements Runnable {
	private String srcFilePath;
	private String descFilePath;
	public ConvertRunnable(String srcFilePath, String descFilePath) {
		this.srcFilePath = srcFilePath;
		this.descFilePath = descFilePath;
	}
	public boolean convert(String srcFilePath, String descFilePath) {
		try {
			
			File srcFile = new File(srcFilePath);
			File descFile = new File(descFilePath);
			if (srcFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(
						8100);
				connection.connect();
				DocumentConverter converter = new OpenOfficeDocumentConverter(
						connection);
				converter.convert(srcFile, descFile);
				descFile.createNewFile();
				connection.disconnect();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void run() {
		convert(this.srcFilePath, this.descFilePath);
	}
}
