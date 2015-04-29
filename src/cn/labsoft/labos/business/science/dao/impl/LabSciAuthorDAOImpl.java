package cn.labsoft.labos.business.science.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.science.dao.ILabSciAuthorDAO;
import cn.labsoft.labos.business.science.entity.LabSciAuthor;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
@Repository(value="labSciAuthorDAO")
public class LabSciAuthorDAOImpl extends BaseDAO implements ILabSciAuthorDAO {

	@Override
	public LabSciAuthor addLabSciAuthor(LabSciAuthor labSciAuthor)
			throws GlobalException {
		try {
			super.save(labSciAuthor);
			return labSciAuthor;
		} catch (Exception ex) {
			Log4J.error("LabSciAuthorDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabSciAuthor(String id) throws GlobalException {
		try {
			LabSciAuthor labSciAuthor=(LabSciAuthor) super.findById(LabSciAuthor.class,id);
			labSciAuthor.setIsDel(Constants_Business.Y);
			super.update(labSciAuthor);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciAuthorDAOImpl delete error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciAuthor(LabSciAuthor labSciAuthor)
			throws GlobalException {
		try {
			super.update(labSciAuthor);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciAuthorDAOImpl update error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
