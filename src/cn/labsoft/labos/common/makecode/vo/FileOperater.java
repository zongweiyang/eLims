package cn.labsoft.labos.common.makecode.vo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class FileOperater {
	public static List<String> readFileByLines(String templateSqlPath) throws GlobalException {
		BufferedReader bufferedReader = null;
		List<String> lineStrList = new ArrayList<String>();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(templateSqlPath)), "UTF-8"));

			String tempString = null;
			int line = 1;
			while ((tempString = bufferedReader.readLine()) != null) {
				lineStrList.add(tempString);
				line++;
			}
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					throw new GlobalException("" + e1.getMessage());
				}
			}
		}
		return lineStrList;
	}

	public static void writeFileByLines(String sqlFilePath,
			List<String> lineStrList) throws GlobalException {
		if(null!=sqlFilePath&&!"".equals(sqlFilePath)&&sqlFilePath.indexOf(".")>-1){
			String dirPath = sqlFilePath.substring(0,sqlFilePath.lastIndexOf(File.separator));
			String[] dirPathArray = dirPath.split("\\"+File.separator);
			if(null!=dirPathArray&&0<dirPathArray.length){
				String dir = dirPathArray[0]+File.separator;
				for(int i=1;i<dirPathArray.length;i++){
					dir+=dirPathArray[i];
					File dirFile = new File(dir);
					if(!dirFile.exists()){
						dirFile.mkdir();
					}
					dir+=File.separator;
				}
			}
		}
		OutputStream sqlOutputStream = null;
		BufferedWriter bufferedWriter = null;
		try {
			sqlOutputStream = new FileOutputStream(new File(sqlFilePath), false);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					sqlOutputStream, "UTF-8"));
			for (String str : lineStrList) {
				bufferedWriter.write(str);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e1) {
					throw new GlobalException("" + e1.getMessage());
				}
			}
		}
	}
}
