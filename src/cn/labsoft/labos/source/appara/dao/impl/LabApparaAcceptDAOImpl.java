package cn.labsoft.labos.source.appara.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaAcceptDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaAccept;

@Repository(value="labApparaAcceptDAO")
public class LabApparaAcceptDAOImpl extends BaseDAO implements ILabApparaAcceptDAO{

	
	@Override
	public LabApparaAccept addLabApparaAccept(LabApparaAccept po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaAcceptDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaAccept updateLabApparaAccept(LabApparaAccept po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaAcceptDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabApparaAccept getLabApparaAcceptById(String id) throws GlobalException {
		LabApparaAccept labApparaAccept = new LabApparaAccept();
		try {
			labApparaAccept = (LabApparaAccept) super.findById(LabApparaAccept.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaAcceptDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaAccept;
	}
}
