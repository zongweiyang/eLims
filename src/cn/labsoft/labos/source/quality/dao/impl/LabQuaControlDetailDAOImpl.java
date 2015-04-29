package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaControlDetailDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaControlDetail;



@Repository(value="labQuaControlDetailDAO")
public class LabQuaControlDetailDAOImpl extends BaseDAO implements ILabQuaControlDetailDAO{

	@Override
	public LabQuaControlDetail getLabQuaControlDetail(String id) throws GlobalException {
		try {
		LabQuaControlDetail labQuaControlDetail=(LabQuaControlDetail)super.findById(LabQuaControlDetail.class,id);
		return labQuaControlDetail;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)throws GlobalException{
		try {
			super.update(labQuaControlDetail);
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaControlDetail addLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)
			throws GlobalException {
		try {
			labQuaControlDetail.setIsDel(Constants_Source.N);
			super.save(labQuaControlDetail);
			return labQuaControlDetail;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)
			throws GlobalException {
		try {
			super.update(labQuaControlDetail);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaControlDetailPR(String hql, PageResult pageResult)throws GlobalException{
		try {
		pageResult=super.getPageResult(hql,pageResult);
		return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaControlDetail> getLabQuaControlDetailByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
