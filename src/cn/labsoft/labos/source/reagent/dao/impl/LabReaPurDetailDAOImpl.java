package cn.labsoft.labos.source.reagent.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaPurDetailDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaPurDetail;
@Repository(value="labReaPurDetailDAO")
public class LabReaPurDetailDAOImpl extends BaseDAO implements
		ILabReaPurDetailDAO {
	private static Log log = LogFactory.getLog(LabReaPurDetailDAOImpl.class);

	@Override
	public LabReaPurDetail addLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException {
		try {
			labReaPurDetail.setIsDel(Constants_Source.N);
			super.save(labReaPurDetail);
		} catch (Exception ex) {
			log.error("LabReaPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReaPurDetail;
	}

	@Override
	public boolean deleteLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException {
		try {
			super.delete(labReaPurDetail);
		} catch (Exception ex) {

			log.error("LabReaPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabReaPurDetail> getLabReaPurDetailBySql(String hql)
			throws GlobalException {
		try {
			List<LabReaPurDetail> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabReaPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabReaPurDetail(LabReaPurDetail labReaPurDetail)
			throws GlobalException {
		try {
			super.update(labReaPurDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("LabReaPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
