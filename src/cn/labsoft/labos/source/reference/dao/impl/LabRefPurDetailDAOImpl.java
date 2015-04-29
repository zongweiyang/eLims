package cn.labsoft.labos.source.reference.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefPurDetailDAO;
import cn.labsoft.labos.source.reference.entity.LabRefPurDetail;

@Repository(value="labRefPurDetailDAO")
public class LabRefPurDetailDAOImpl extends BaseDAO implements
		ILabRefPurDetailDAO {

	@Override
	public LabRefPurDetail addLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException {
		try {
			labRefPurDetail.setIsDel(Constants_Source.N);
			super.save(labRefPurDetail);
		} catch (Exception ex) {
			Log4J.error("LabRefPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRefPurDetail;
	}

	@Override
	public boolean deleteLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException {
		try {
			super.delete(labRefPurDetail);
		} catch (Exception ex) {

			Log4J.error("LabRefPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabRefPurDetail> getLabRefPurDetailBySql(String hql)
			throws GlobalException {
		try {
			List<LabRefPurDetail> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabRefPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabRefPurDetail(LabRefPurDetail labRefPurDetail)
			throws GlobalException {
		try {
			super.update(labRefPurDetail);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabRefPurDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
