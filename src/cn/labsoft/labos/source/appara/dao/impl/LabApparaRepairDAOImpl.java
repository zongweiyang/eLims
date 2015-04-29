package cn.labsoft.labos.source.appara.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaRepairDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaRepair;
@Repository(value="labApparaRepairDAO")
public class LabApparaRepairDAOImpl extends BaseDAO implements
		ILabApparaRepairDAO {


	@Override
	public LabApparaRepair addLabApparaRepair(
			LabApparaRepair po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaRepairDAOImpl error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaRepair updateLabApparaRepair(LabApparaRepair po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaRepairDAOImpl error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaRepair getLabApparaRepairById(
			String id) throws GlobalException {
		LabApparaRepair labApparaRepair = new LabApparaRepair();
		try {
			labApparaRepair = (LabApparaRepair) super.findById(LabApparaRepair.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaRepairDAOImpl error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaRepair;
	}


}
