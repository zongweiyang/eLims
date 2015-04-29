package cn.labsoft.labos.utils;

public class DoubleUtils {
	//格式化double 类型的数字 保留2位小数
	public static Double formatDouble(Double param){
		Double db=0.0;
		String result="0.0";
		String str="";
		if(null!=param){
			str=String.valueOf(param);
		}else{
			str="0.0";
		}
		if(null!=str){
			if(str.length()>0){
				if(str.indexOf('.')>0){
					int l=str.indexOf('.');
					String str_befor=str.substring(0,l);
					int str_befor_=0;
					if(null!=str_befor){
						str_befor_=Integer.valueOf(str_befor);
					}
					String str_after=str.substring(l+1,str.length());
					if(str_after.length()>1){
						char s[]=str_after.toCharArray();
						//String s=String.valueOf(sx[1]);
						if(s.length>2){
							String s0=String.valueOf(s[0]);
							int s0_=Integer.parseInt(s0);
							String s1=String.valueOf(s[1]);
							int s1_=Integer.parseInt(s1);
							String s2=String.valueOf(s[2]);
							int s2_=Integer.parseInt(s2);
							if(null!=s2&&!"".equals(s2)){
								if(s2_>=5){
									s1_=s1_+1;
								}if(s1_==10){
									s0_=s0_+1;
									s1_=0;
								}if(s0_==10){
									str_befor_=str_befor_+1;
									s0_=0;
								}
							}
							result=String.valueOf(str_befor_)+"."+String.valueOf(s0_)+String.valueOf(s1_);
						}else{
							result=str;
						}
					}
				}
			}
		}
		if(null!=result&&!"".equals(result)){
			db=Double.parseDouble(result);
		}
		return db;
	}
}
