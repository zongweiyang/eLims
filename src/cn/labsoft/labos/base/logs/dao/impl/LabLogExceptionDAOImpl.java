package cn.labsoft.labos.base.logs.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.logs.dao.ILabLogExceptionDAO;
import cn.labsoft.labos.base.logs.entity.LabLogException;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

@Repository(value="labLogExceptionDAO")
public class LabLogExceptionDAOImpl extends BaseDAO implements
		ILabLogExceptionDAO {

	public LabLogException addLabLogException(LabLogException labLogException) throws GlobalException {
		try {
			super.save(labLogException);
		} catch (Exception ex) {
			Log4J.error("LabLogExceptionDAOImpl error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labLogException;
	}

	public LabLogException getLabLogException(LabLogException labLogException) throws GlobalException {
		try {
			labLogException = (LabLogException) super.findById(
					LabLogException.class, labLogException.getId());
			return labLogException;
		} catch (Exception ex) {
			Log4J.error("LabLogExceptionDAOImpl error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}