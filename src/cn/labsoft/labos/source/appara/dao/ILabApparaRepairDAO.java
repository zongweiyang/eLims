package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaRepair;

public interface ILabApparaRepairDAO extends IBaseDAO{

	public LabApparaRepair addLabApparaRepair(LabApparaRepair po) throws GlobalException;

	public LabApparaRepair updateLabApparaRepair(LabApparaRepair po) throws GlobalException;
	
	public LabApparaRepair getLabApparaRepairById(String id) throws GlobalException;
	
	

}
