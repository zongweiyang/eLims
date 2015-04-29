package cn.labsoft.labos.business.samtest.dao;

import cn.labsoft.labos.business.samtest.entity.LabSamTestBeatch;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
public interface ILabSamTestBeatchDAO extends IBaseDAO {
	
	public LabSamTestBeatch addLabLabSamTestBeatch(LabSamTestBeatch labSamTestBeatch) throws GlobalException;
	
	public boolean updateLabLabSamTestBeatch(LabSamTestBeatch labSamTestBeatch) throws GlobalException;
	
}
