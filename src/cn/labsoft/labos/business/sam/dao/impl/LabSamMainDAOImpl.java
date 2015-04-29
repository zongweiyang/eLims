package cn.labsoft.labos.business.sam.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.sam.dao.ILabSamMainDAO;
import cn.labsoft.labos.business.sam.entity.LabSamMain;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamMainDAO")
public class LabSamMainDAOImpl extends BaseDAO implements ILabSamMainDAO {

	@Override
	public LabSamMain addLabSamMain(LabSamMain labSamMain) throws GlobalException {
		try {
			labSamMain.setIsDel(Constants_Business.N);
			labSamMain.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMddHHmm));
			labSamMain.setCreateUserId(SessionContainer.getSessionContainer().getUserId());
			super.save(labSamMain);
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamMain;
	}

	@Override
	public boolean deleteLabSamMain(LabSamMain labSamMain) throws GlobalException {
		try {
			super.update(labSamMain);
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSamMain getLabSamMain(String samMainId) throws GlobalException {
		try {
			LabSamMain labSamMain = (LabSamMain) super.findById(LabSamMain.class, samMainId);
			return labSamMain;
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamMain> getLabSamMainList(String hql) throws GlobalException {
		try {
			 List<LabSamMain> labSamMainList = super.find(hql);
			 return labSamMainList;
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabSamMainPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql,pageResult.getCurrentPage(),pageResult.getPagerMethod(),pageResult.getPageSize());
			 return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSamMain(LabSamMain labSamMain) throws GlobalException {
		try {
			super.update(labSamMain);
		} catch (Exception ex) {
			Log4J.error("LabSamMainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}


}
