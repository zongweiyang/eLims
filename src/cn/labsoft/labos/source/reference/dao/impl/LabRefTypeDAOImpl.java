package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefTypeDAO;
import cn.labsoft.labos.source.reference.entity.LabRefType;

@Repository(value="labRefTypeDAO")
public class LabRefTypeDAOImpl extends BaseDAO implements ILabRefTypeDAO {


	@Override
	public LabRefType addLabRefType(LabRefType labReferenceType) throws GlobalException {
		try {
			super.save(labReferenceType);
		} catch (Exception ex) {
			Log4J.error("LabRefTypeDAOImpl add error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReferenceType;
	}

	@Override
	public LabRefType updateLabReftype(LabRefType labReferenceType) throws GlobalException {
		try {
			super.update(labReferenceType);
		} catch (Exception ex) {
			Log4J.error("LabRefTypeDAOImpl update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReferenceType;
	}

}
