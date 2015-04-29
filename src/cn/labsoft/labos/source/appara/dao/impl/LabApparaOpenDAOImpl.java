package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaOpenDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaOpen;
@Repository(value="labApparaOpenDAO")
public class LabApparaOpenDAOImpl extends BaseDAO implements ILabApparaOpenDAO{

	
	@Override
	public LabApparaOpen addLabApparaOpen(LabApparaOpen po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaOpenDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaOpen updateLabApparaOpen(LabApparaOpen po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaOpenDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaOpen getLabApparaOpenById(String id) throws GlobalException {
		LabApparaOpen labApparaOpen = new LabApparaOpen();
		try {
			labApparaOpen = (LabApparaOpen) super.findById(LabApparaOpen.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaOpenDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaOpen;
	}
}
