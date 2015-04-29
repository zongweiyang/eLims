package cn.labsoft.labos.source.appara.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaEditDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaEdit;
@Repository(value="labApparaEditDAO")
public class LabApparaEditDAOImpl extends BaseDAO implements ILabApparaEditDAO{


	@Override
	public LabApparaEdit addLabApparaEdit(
			LabApparaEdit po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaEditDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaEdit updateLabApparaEdit(
			LabApparaEdit po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaEditDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaEdit getLabApparaEditById(
			String id) throws GlobalException {
		LabApparaEdit labApparaEdit = new LabApparaEdit();
		try {
			labApparaEdit = (LabApparaEdit) super.findById(LabApparaEdit.class, id);
		
		} catch (Exception ex) {
			Log4J.error("LabApparaEditDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaEdit;
	}
}
