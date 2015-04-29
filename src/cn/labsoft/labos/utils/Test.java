package cn.labsoft.labos.utils;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;




public class Test {
	public static void main(String[] args) throws GlobalException{
		List<String> list= FileOperater.readFileByLines("E:\\workspace\\LABOS-CORE\\WebRoot\\jsp\\common\\jevel\\jevelMain.jsp");
		for(String line:list){
			if(line.indexOf("id=\"one\"")>-1){
				System.out.println(line);
				System.out.println(line.indexOf("id=\"one\""));
				System.out.println(line.charAt(23));
				System.out.println(line.substring(23,31));
				String name=line.substring(0, line.indexOf("id=\"one\""))+" key=\"stone\" "+line.substring(23, line.length());
				System.out.println("name~~~~~~~~~~~~~~~"+name);
			}
		}
	}
}
