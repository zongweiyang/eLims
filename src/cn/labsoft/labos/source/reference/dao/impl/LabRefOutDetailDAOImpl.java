package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefOutDetailDAO;
import cn.labsoft.labos.source.reference.entity.LabRefOutDetail;

@Repository(value="labRefOutDetailDAO")
public class LabRefOutDetailDAOImpl extends BaseDAO implements
		ILabRefOutDetailDAO {


	@Override
	public void addLabRefOutDetail(LabRefOutDetail labRefOutDetail) {
		try {
			super.save(labRefOutDetail);
		} catch (Exception ex) {
			Log4J.error("LabRefOutDetailDAOImpl save...." + ex.getMessage(), ex);
		}
	}

}
