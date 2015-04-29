package cn.labsoft.labos.business.science.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.business.science.entity.LabSciScience;

public interface ILabSciScienceDAO extends IBaseDAO{
	
	public LabSciScience addLabSciScience(LabSciScience labSciScience)throws GlobalException;
	
	public boolean updateLabSciScience(LabSciScience labSciScience)throws GlobalException;
	
	public boolean deleteLabSciScience(String id)throws GlobalException;

}
