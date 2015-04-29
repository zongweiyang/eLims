package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaProvDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaProv;
@Repository(value="labApparaProvDAO")
public class LabApparaProvDAOImpl extends BaseDAO implements ILabApparaProvDAO{


	@Override
	public LabApparaProv addLabApparaProv(
			LabApparaProv po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaProvDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaProv updateLabApparaProv(
			LabApparaProv po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaProvDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaProv getLabApparaProvById(
			String id) throws GlobalException {
		LabApparaProv labApparaProv = new LabApparaProv();
		try {
			labApparaProv = (LabApparaProv) super.findById(LabApparaProv.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaProvDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaProv;
	}
}
