package cn.labsoft.labos.source.consumables.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.user.dao.impl.LabUserDAOImpl;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConPurDetailDAO;
import cn.labsoft.labos.source.consumables.entity.LabConPurDetail;
@Repository(value="labConPurDetailDAO")
public class LabConPurDetailDAOImpl extends BaseDAO implements
		ILabConPurDetailDAO {
	private static Log log = LogFactory.getLog(LabUserDAOImpl.class);

	@Override
	public LabConPurDetail addLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException {
		try {
			labConPurDetail.setIsDel(Constants_Source.N);
			super.save(labConPurDetail);
		} catch (Exception ex) {
			log.error("LabConPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConPurDetail;
	}

	@Override
	public boolean deleteLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException {
		try {
			super.delete(labConPurDetail);
		} catch (Exception ex) {

			log.error("LabConPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabConPurDetail> getLabConPurDetailBySql(String hql)
			throws GlobalException {
		try {
			List<LabConPurDetail> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabConPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabConPurDetail(LabConPurDetail labConPurDetail)
			throws GlobalException {
		try {
			super.update(labConPurDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabConPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
