package cn.labsoft.labos.source.reference.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reference.dao.ILabRefPurMainDAO;
import cn.labsoft.labos.source.reference.entity.LabRefPurMain;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labRefPurMainDAO")
public class LabRefPurMainDAOImpl extends BaseDAO implements ILabRefPurMainDAO {

	@Override
	public LabRefPurMain addLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException {
		try {
			labRefPurMain.setIsDel(Constants_Source.N);
			labRefPurMain.setCreateTime(DateUtils.format(new Date(),
					DateUtils.formatStr_yyyyMMdd));
			super.save(labRefPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabRefPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRefPurMain;
	}

	@Override
	public boolean deleteLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException {
		try {
			super.delete(labRefPurMain);
		} catch (Exception ex) {
			Log4J.error("LabRefPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public List<LabRefPurMain> getLabRefPurBySql(String hql)
			throws GlobalException {
		try {
			List<LabRefPurMain> list = super.find(hql);
			return list;
		} catch (Exception ex) {
			Log4J.error("LabRefPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}

	}

	@Override
	public PageResult getLabRefPurPageResult(String hql, PageResult pageResult)
			throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabRefPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabRefPur(LabRefPurMain labRefPurMain)
			throws GlobalException {
		try {
			super.update(labRefPurMain);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabRefPurMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
