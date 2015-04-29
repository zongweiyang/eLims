
package cn.labsoft.labos.business.science.dao;

import cn.labsoft.labos.business.science.entity.LabSciLearn;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabSciLearnDAO extends IBaseDAO {
	
	public LabSciLearn addLabSciLearn(LabSciLearn labSciLearn)throws GlobalException;
	
	public boolean updateLabSciLearn(LabSciLearn labSciLearn)throws GlobalException;
	
	public boolean deleteLabSciLearn(String id)throws GlobalException;

}
