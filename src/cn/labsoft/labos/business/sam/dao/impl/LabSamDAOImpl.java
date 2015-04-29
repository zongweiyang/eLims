package cn.labsoft.labos.business.sam.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.sam.dao.ILabSamDAO;
import cn.labsoft.labos.business.sam.entity.LabSam;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamDAO")
public class LabSamDAOImpl extends BaseDAO implements ILabSamDAO {

	@Override
	public LabSam addLabSam(LabSam labSam) throws GlobalException {
		try {
			labSam.setIsDel(Constants_Business.N);
			labSam.setCreateTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMddHHmm));
			labSam.setCreateUserId(SessionContainer.getSessionContainer().getUserId());
			labSam.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSam);
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSam;
	}

	@Override
	public boolean deleteLabSam(LabSam labSam) throws GlobalException {
		try {
			labSam.setIsDel(Constants_Business.Y);
			super.update(labSam);
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSam getLabSam(String samId) throws GlobalException {
		try {
			LabSam labSam = (LabSam) super.findById(LabSam.class, samId);
			return labSam;
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSam> getLabSamList(String hql) throws GlobalException {
		try {
			 List<LabSam> labSamList = super.find(hql);
			 return labSamList;
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabSamPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql,pageResult.getCurrentPage(),pageResult.getPagerMethod(),pageResult.getPageSize());
			 return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSam(LabSam labSam) throws GlobalException {
		try {
			super.update(labSam);
		} catch (Exception ex) {
			Log4J.error("LabSamDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}


}
