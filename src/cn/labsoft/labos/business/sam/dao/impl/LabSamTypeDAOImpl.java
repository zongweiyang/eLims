package cn.labsoft.labos.business.sam.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.sam.dao.ILabSamTypeDAO;
import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamTypeDAO")
public class LabSamTypeDAOImpl extends BaseDAO implements ILabSamTypeDAO {
	@Override
	public LabSamType addLabSamType(LabSamType labSamType) throws GlobalException {
		labSamType.setIsDel(Constants_Business.N);
		labSamType.setCreateTime(DateUtils.getCurrDateTimeStr());
		try {
			super.save(labSamType);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabSamTypeDAOImpl error......" + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamType;
	}

	@Override
	public boolean deleteLabSamType(String id) throws GlobalException {
		try {
				super.delete(this.getLabSamType(id));
		} catch (Exception ex) {
			Log4J.error("LabSamTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSamType getLabSamType(String samTypeId) throws GlobalException {
		try {
			LabSamType samType=(LabSamType) super.findById(LabSamType.class, samTypeId);
			return samType;
			} catch (Exception ex) {
			Log4J.error("LabSamTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamType> getLabSamTypeList(String hql) throws GlobalException {
		try {
			List<LabSamType> samTypeList = super.find(hql);
			return samTypeList;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabSamTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabSamTypePR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabSamTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSamType(LabSamType labSamType) throws GlobalException {
		try {
			super.update(labSamType);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabSamTypeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public int getLevel(LabSamType labSamType, Integer node)
			throws GlobalException {
		if(null==node){
			node=new Integer(1);
		}
		if(null!=labSamType&&null!=labSamType.getLabSamType()){
			node++;
			node = getLevel(labSamType.getLabSamType(), node);
		}
		return node;
	}

}
