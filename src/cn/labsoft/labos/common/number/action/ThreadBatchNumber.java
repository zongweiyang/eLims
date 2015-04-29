package cn.labsoft.labos.common.number.action;

import java.util.List;
import java.util.concurrent.Callable;

import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.number.service.ILabNumberService;

public class ThreadBatchNumber implements  Callable<List<String>> {
	private ILabNumberDAO labNumberDAO;
	private ILabNumberService labNumberService;
	private Integer count;
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


	public ThreadBatchNumber(ILabNumberService labNumberService,ILabNumberDAO labNumberDAO,String type,String[] name,String status,int count){
		this.labNumberService=labNumberService;
		this.labNumberDAO=labNumberDAO;
		this.type=type;
		this.name=name;
		this.status=status;
		this.count=count;
	}

	public ILabNumberDAO getLabNumberDAO() {
		return labNumberDAO;
	}


	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	@Override
	public List<String> call() throws Exception {
		// TODO Auto-generated method stub
		if(labNumberDAO!=null)
			return labNumberDAO.getLabNumberBatchNo(count, type, name, status);
		else
			return labNumberService.getLabNumberBatchNo(count, type, name, status);
	}


	public ILabNumberService getLabNumberService() {
		return labNumberService;
	}


	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	
	

}
