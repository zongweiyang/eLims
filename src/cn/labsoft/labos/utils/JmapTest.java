package cn.labsoft.labos.utils;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.utils.FileOperater;

public class JmapTest extends Thread{
	
	public static void test() throws Exception {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("E:\\jmaptest\\11.bat"); 
		
		List<String> contentList = FileOperater.readFileByLines("e:\\jmaptest\\jmap.txt");
		List<String> tempList = new ArrayList<String>();
		String srcPath = "cn.labsoft";
		for(String str :contentList){
			if(str.contains(srcPath)){
				System.out.println(str);
			}
				
		} 
		String time = DateUtils.format(DateUtils.getCurrDate(), DateUtils.HH_MM_SS).replace(":", "");
		System.out.println("~~~~~~~~~~~"+time);
		FileOperater.writeFileByLines("e:\\jmaptest\\jmap_"+time+".txt", tempList);
	}
	
	public static void main(String[] args) {
		try {
			JmapTest testThread = new JmapTest();  
			testThread.start(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(true){
				test();
				sleep(1000*30);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
