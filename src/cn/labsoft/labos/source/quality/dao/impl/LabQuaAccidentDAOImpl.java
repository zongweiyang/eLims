package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaAccidentDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAccident;

@Repository(value="labQuaAccidentDAO")
public class LabQuaAccidentDAOImpl extends BaseDAO implements ILabQuaAccidentDAO{

	@Override
	public LabQuaAccident getLabQuaAccident(String id) throws GlobalException {
		try {
			LabQuaAccident labQuaAccident=(LabQuaAccident)super.findById(LabQuaAccident.class,id);
			return labQuaAccident;
		} catch (Exception ex) {
			Log4J.error("LabQuaAccidentDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaAccident(LabQuaAccident labQuaAccident)throws GlobalException{
		try {
			super.update(labQuaAccident);
		} catch (Exception ex) {
			Log4J.error("LabQuaAccidentDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaAccident addLabQuaAccident(LabQuaAccident labQuaAccident)
			throws GlobalException {
		try {
			labQuaAccident.setIsDel(Constants_Source.N);
			super.save(labQuaAccident);
			return labQuaAccident;
		} catch (Exception ex) {
			Log4J.error("LabQuaAccidentDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaAccident(LabQuaAccident labQuaAccident)
			throws GlobalException {
		try {
			super.update(labQuaAccident);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaAccidentDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaAccidentPR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaAccidentDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaAccident> getLabQuaAccidentByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
