package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabReferenceDAO;
import cn.labsoft.labos.source.reference.entity.LabReference;

@Repository(value="labReferenceDAO")
public class LabReferenceDAOImpl extends BaseDAO implements ILabReferenceDAO {


	@Override
	public LabReference addLabReference(LabReference po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabReferenceDAOImpl add error...."
							+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabReference updateLabReference(LabReference labReference) throws GlobalException {
		try {
			super.update(labReference);
		} catch (Exception ex) {
			Log4J.error("LabReferenceDAOImpl update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReference;
	}

}
