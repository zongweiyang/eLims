package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaTypeDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaType;
@Repository(value="labApparaTypeDAO")
public class LabApparaTypeDAOImpl extends BaseDAO implements ILabApparaTypeDAO{

	@Override
	public LabApparaType addLabApparaType(LabApparaType po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaType updateLabApparaType(LabApparaType po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaType getLabApparaTypeById(String id) throws GlobalException {
		LabApparaType labApparaType = new LabApparaType();
		try {
			labApparaType = (LabApparaType) super.findById(LabApparaType.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaType;
	}

	@Override
	public boolean deleteLabApparaType(LabApparaType po) throws GlobalException {
		try {
			super.delete(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
