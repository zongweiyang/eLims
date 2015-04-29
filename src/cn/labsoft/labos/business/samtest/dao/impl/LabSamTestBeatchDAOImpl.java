package cn.labsoft.labos.business.samtest.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.samtest.dao.ILabSamTestBeatchDAO;
import cn.labsoft.labos.business.samtest.entity.LabSamTestBeatch;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamTestBeatchDAO")
public class LabSamTestBeatchDAOImpl extends BaseDAO implements ILabSamTestBeatchDAO {

	@Override
	public LabSamTestBeatch addLabLabSamTestBeatch(LabSamTestBeatch labSamTestBeatch) throws GlobalException {
		// TODO Auto-generated method stub
		try{
			labSamTestBeatch.setIsDel(Constants_Business.N);
			labSamTestBeatch.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSamTestBeatch);
		}catch(Exception ex){
			Log4J.error("LabSamTestBeatchDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamTestBeatch;
	}

	@Override
	public boolean updateLabLabSamTestBeatch(LabSamTestBeatch labSamTestBeatch) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		try{
			super.update(labSamTestBeatch);
		}catch(Exception ex){
			Log4J.error("LabSamTestBeatchDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return key;
	}
	
	
}
