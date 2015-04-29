package cn.labsoft.labos.common.query.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.query.dao.ILabParameterDAO;
import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labParameterDAO")
public class LabParameterDAOImpl extends BaseDAO implements ILabParameterDAO {

	@Override
	public LabParameter getLabParameter(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabParameter labParameter=(LabParameter) super.findById(LabParameter.class, id);
		return labParameter;
	}

	@Override
	public LabParameter addLabParameter(LabParameter labParameter) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			labParameter.setIsDel(Constants_Base.N);
			labParameter.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labParameter);
		} catch (Exception ex) {
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labParameter;
	}
	
	
}
