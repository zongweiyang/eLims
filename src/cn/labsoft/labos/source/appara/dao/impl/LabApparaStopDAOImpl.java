package cn.labsoft.labos.source.appara.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaStopDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaStop;
@Repository(value="labApparaStopDAO")
public class LabApparaStopDAOImpl extends BaseDAO implements ILabApparaStopDAO{

	
	@Override
	public LabApparaStop addLabApparaStop(LabApparaStop po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaStopDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaStop updateLabApparaStop(LabApparaStop po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaStopDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabApparaStop getLabApparaStopById(String id) throws GlobalException {
		LabApparaStop labApparaStop = new LabApparaStop();
		try {
			labApparaStop = (LabApparaStop) super.findById(LabApparaStop.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaStopDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaStop;
	}
}
