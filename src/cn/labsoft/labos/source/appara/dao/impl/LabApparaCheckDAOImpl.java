package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaCheckDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaCheck;
@Repository(value="labApparaCheckDAO")
public class LabApparaCheckDAOImpl extends BaseDAO implements ILabApparaCheckDAO{


	@Override
	public LabApparaCheck addLabApparaCheck(
			LabApparaCheck po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaCheck updateLabApparaCheck(
			LabApparaCheck po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabApparaCheck getLabApparaCheckById(
			String id) throws GlobalException {
		LabApparaCheck labApparaCheck = new LabApparaCheck();
		try {
			labApparaCheck = (LabApparaCheck) super.findById(LabApparaCheck.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaCheck;
	}
}
