package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaTestDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaTest;
@Repository(value="labApparaTestDAO")
public class LabApparaTestDAOImpl extends BaseDAO implements ILabApparaTestDAO{

	@Override
	public LabApparaTest addLabApparaTest(LabApparaTest po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaTest updateLabApparaTest(LabApparaTest po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaTest getLabApparaTestById(String id) throws GlobalException {
		LabApparaTest labApparaTest = new LabApparaTest();
		try {
			labApparaTest = (LabApparaTest) super.findById(LabApparaTest.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaTest;
	}

	@Override
	public boolean deleteLabApparaTest(LabApparaTest po) throws GlobalException {
		try {
			super.delete(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaTestDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
}
