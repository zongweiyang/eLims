package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConOutDetailDAO;
import cn.labsoft.labos.source.consumables.entity.LabConOutDetail;
@Repository(value="labConOutDetailDAO")
public class LabConOutDetailDAOImpl extends BaseDAO implements
		ILabConOutDetailDAO {

	private static Log log = LogFactory.getLog(LabConOutDetailDAOImpl.class);

	@Override
	public void addLabConOutDetail(LabConOutDetail labConOutDetail) throws GlobalException {
		try {
			super.save(labConOutDetail);
		} catch (Exception ex) {
			log.error("LabConInDetaiDAOImpl save...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
