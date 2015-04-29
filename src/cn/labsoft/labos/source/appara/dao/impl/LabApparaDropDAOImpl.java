package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaDropDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaDrop;
@Repository(value="labApparaDropDAO")
public class LabApparaDropDAOImpl extends BaseDAO implements ILabApparaDropDAO{

	
	@Override
	public LabApparaDrop addLabApparaDrop(LabApparaDrop po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaDropDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaDrop updateLabApparaDrop(LabApparaDrop po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaDropDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaDrop getLabApparaDropById(String id) throws GlobalException {
		LabApparaDrop labApparaDrop = new LabApparaDrop();
		try {
			labApparaDrop = (LabApparaDrop) super.findById(LabApparaDrop.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaDropDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaDrop;
	}
}
