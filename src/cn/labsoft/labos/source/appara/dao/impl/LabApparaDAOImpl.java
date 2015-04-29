package cn.labsoft.labos.source.appara.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaDAO;
import cn.labsoft.labos.source.appara.entity.LabAppara;
@Repository(value="labApparaDAO")
public class LabApparaDAOImpl extends BaseDAO implements ILabApparaDAO{

	
	@Override
	public LabAppara addLabAppara(LabAppara po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabAppara updateLabAppara(LabAppara po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabAppara getLabApparaById(String id) throws GlobalException {
		LabAppara labAppara = new LabAppara();
		try {
			labAppara = (LabAppara) super.findById(LabAppara.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labAppara;
	}
}
