package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReagentDAO;
import cn.labsoft.labos.source.reagent.entity.LabReagent;
@Repository(value="labReagentDAO")
public class LabReagentDAOImpl extends BaseDAO implements ILabReagentDAO {

	private static Log log = LogFactory.getLog(LabReagentDAOImpl.class);

	public LabReagent addLabReagent(LabReagent labReagent) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.save(labReagent);
		} catch (Exception ex) {
			log.error("LabReagentDAOImpl add error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReagent;
	}

	public boolean deleteLabRegent(LabReagent labReagent) throws GlobalException {
		try {
			super.update(labReagent);
		} catch (Exception ex) {
			log.error("LabReagentDAOImpl delete error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	public boolean updateLabReagent(LabReagent labReagent) throws GlobalException {
		try {
			super.update(labReagent);
		} catch (Exception ex) {
			log.error("LabReagentDAOImpl update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	public LabReagent getLabReagent(String id) throws GlobalException {
		try {
			LabReagent labReagent = (LabReagent) super.findById(
					LabReagent.class, id);
			return labReagent;
		} catch (Exception ex) {
			log.error("LabReagentDAOImpl find...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
