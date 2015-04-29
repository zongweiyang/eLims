package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaBorDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaBor;
@Repository(value="labApparaBorDAO")
public class LabApparaBorDAOImpl extends BaseDAO implements ILabApparaBorDAO{

	
	@Override
	public LabApparaBor addLabApparaBor(LabApparaBor po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaBorDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaBor updateLabApparaBor(LabApparaBor po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaBorDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabApparaBor getLabApparaBorById(String id) throws GlobalException {
		LabApparaBor labApparaBor = new LabApparaBor();
		try {
			labApparaBor = (LabApparaBor) super.findById(LabApparaBor.class, id);
		
		} catch (Exception ex) {
			Log4J.error("LabApparaBorDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaBor;
	}
}
