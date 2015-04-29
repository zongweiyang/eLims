package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefCheckMainDAO;
import cn.labsoft.labos.source.reference.entity.LabRefCheckMain;

@Repository(value="labRefCheckMainDAO")
public class LabRefCheckMainDAOImpl extends BaseDAO implements
		ILabRefCheckMainDAO {


	@Override
	public LabRefCheckMain addLabRefCheckMain(LabRefCheckMain labRefCheckMain) throws GlobalException {
		try {
			super.save(labRefCheckMain);
		} catch (Exception ex) {
			Log4J.error("LabRefCheckMainDAOImpl add error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRefCheckMain;
	}

}
