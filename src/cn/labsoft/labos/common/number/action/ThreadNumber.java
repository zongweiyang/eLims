package cn.labsoft.labos.common.number.action;

import java.util.concurrent.Callable;

import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.number.service.ILabNumberService;

@SuppressWarnings("unchecked")
public class ThreadNumber implements  Callable {
	private ILabNumberDAO labNumberDAO;
	private ILabNumberService labNumberService;
	private String type;
	private String[] name;
	private String status;// 0  不确定编号 1：已确定编号

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String[] getName() {
		return name;
	}


	public void setName(String[] name) {
		this.name = name;
	}

//type ：编号种类 如：RWBH  name[]动态字符 status 0预编号 1持久编号
	public ThreadNumber(ILabNumberService labNumberService,ILabNumberDAO labNumberDAO,String type,String[] name,String status){
		this.labNumberDAO=labNumberDAO;
		this.labNumberService=labNumberService;
		this.type=type;
		this.name=name;
		this.status=status;
	}


	
	public ILabNumberDAO getLabNumberDAO() {
		return labNumberDAO;
	}


	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}


	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		if(labNumberDAO!=null){
			return	labNumberDAO.getLabNumberByNo(type,name,status);
		}else
		return labNumberService.getLabNumberByNo(type,name,status);
	}


	public ILabNumberService getLabNumberService() {
		return labNumberService;
	}


	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	

}
