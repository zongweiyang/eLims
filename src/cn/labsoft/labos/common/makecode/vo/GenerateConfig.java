package cn.labsoft.labos.common.makecode.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;


public class GenerateConfig {

	/** 
	 * @Title:  
	 * @Description: TODO
	 * @param @param args  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws 
	 */
	public static void createConfig(Packagee packagee,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		String configDaoPath=path+"\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\config\\applicationContext_DAO.xml";
		String configServicePath=path+"\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\config\\applicationContext_Service.xml";
		File fileDAO=new File(configDaoPath);
		if(fileDAO.exists()){
			List<String> listRead=FileOperater.readFileByLines(configDaoPath);
			List<String> listWrite=new ArrayList<String>();
			List<String> lineWrite=new ArrayList<String>();
			String className=packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length());;
			String classSrc="cn.labsoft.labos."+packagee.getName()+".dao.impl."+packagee.getClazz().getClazzName()+"DAOImpl";
			listWrite.add("	<bean id=\""+className+"DAO\" class=\""+classSrc+"\">");
			listWrite.add(" 		<property name=\"sessionFactory\">");
			listWrite.add("			<ref bean=\"sessionFactory\"/>");
			listWrite.add("		</property>");
			listWrite.add("	</bean>");
			if(listRead!=null&&listRead.size()>0){
				for(int i=0;i<listRead.size();i++){
					if(listRead.get(i).trim().equals("</beans>")){
						lineWrite.add("	<!--"+packagee.getClazz().getClazzCommon()+"-->");
						lineWrite.addAll(listWrite);
					}
					lineWrite.add(listRead.get(i));
				}
				FileOperater.writeFileByLines(configDaoPath, lineWrite);
			}
		}else{
			List<String> copyDaoList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\applicationContext_DAO.xml");
			String writePath=path+"\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath();
			if (null != copyDaoList && 0 < copyDaoList.size()) {
				String daoImpl="cn.labsoft.labos."+packagee.getName()+".dao.impl."+packagee.getClazz().getClazzName()+"DAOImpl";
				for (String lineStr : copyDaoList) {
					lineStr=lineStr.replace("daoImpleSrc",daoImpl);
					lineStr = lineStr.replace("mould",packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()));
					list.add(lineStr);
				}
				String daoPath=(writePath+"\\config\\applicationContext_DAO.xml");
				FileOperater.writeFileByLines(daoPath, list);
			}
			
		}
		File fileService=new File(configServicePath);
		if(!fileService.exists()){
			List<String> copyServiceList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\applicationContext_Service.xml");
			if (null != copyServiceList && 0 < copyServiceList.size()) {
				list=new ArrayList<String>();
				String serviceImpl="cn.labsoft.labos."+packagee.getName()+".service.impl."+packagee.getClazz().getClazzName()+"ServiceImpl";
				for (String lineStr : copyServiceList) {
					lineStr=lineStr.replace("serviceImplSrc",serviceImpl);
					lineStr = lineStr.replace("mould",packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()));
					list.add(lineStr);
				}
					String writePath=path+"\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath();
				String daoPath=(writePath+"\\config\\applicationContext_Service.xml");
				FileOperater.writeFileByLines(daoPath, list);
			}
		}else{
			List<String> listRead=FileOperater.readFileByLines(configServicePath);
			List<String> listWrite=new ArrayList<String>();
			List<String> lineWrite=new ArrayList<String>();
			String className=packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length());
			String classSrc="cn.labsoft.labos."+packagee.getName()+".service.impl."+packagee.getClazz().getClazzName()+"ServiceImpl";
			listWrite.add("	<bean id=\""+className+"Service\" class=\""+classSrc+"\">");
			listWrite.add(" 		<property name=\""+className+"DAO\">");
			listWrite.add("			<ref bean=\""+className+"DAO\"/>");
			listWrite.add("		</property>");
			listWrite.add("	</bean>");
			if(listRead!=null&&listRead.size()>0){
				for(int i=0;i<listRead.size();i++){
					if(listRead.get(i).trim().equals("</beans>")){
						lineWrite.add("	<!--"+packagee.getClazz().getClazzCommon()+"-->");
						lineWrite.addAll(listWrite);
					}
					lineWrite.add(listRead.get(i));
				}
				FileOperater.writeFileByLines(configServicePath, lineWrite);
			}
		}
		
	}
	public static void main(String[] args) throws GlobalException {
		Packagee packagee=new Packagee();
		packagee.setName("apparatus");
		packagee.setNameChinese("仪器管理");
		Clazz clazz=new Clazz();
		clazz.setClazzName("Test");
		clazz.setClazzCommon("测试信息");
		packagee.setClazz(clazz);
		GenerateConfig.createConfig(packagee, "E:\\workspace\\LABOS-CORE");
	}
}
