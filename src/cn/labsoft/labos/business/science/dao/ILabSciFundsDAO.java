package cn.labsoft.labos.business.science.dao;

import cn.labsoft.labos.business.science.entity.LabSciFunds;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabSciFundsDAO extends IBaseDAO {
	
	public LabSciFunds addLabSciFunds(LabSciFunds labSciFunds)throws GlobalException;
	
	public boolean updateLabSciFunds(LabSciFunds labSciFunds)throws GlobalException;
	
	public boolean deleteLabSciFunds(String id)throws GlobalException;

}
