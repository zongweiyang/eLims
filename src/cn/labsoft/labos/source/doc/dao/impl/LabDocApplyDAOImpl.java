package cn.labsoft.labos.source.doc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.doc.dao.ILabDocApplyDAO;
import cn.labsoft.labos.source.doc.entity.LabDocApply;
@Repository(value="labDocApplyDAO")
public class LabDocApplyDAOImpl extends BaseDAO implements ILabDocApplyDAO {

	@Override
	public LabDocApply addLabDocApply(LabDocApply labDocApply) throws GlobalException {
		try {
			labDocApply.setIsDel(Constants_Source.N);
			super.save(labDocApply);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return labDocApply;
	}

	@Override
	public void deleteLabDocApply(LabDocApply labDocApply) throws GlobalException {
		try {
			super.delete(labDocApply);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

	}

	@Override
	public LabDocApply getLabDocApplyById(String id) throws GlobalException {
		LabDocApply docApply;
		try {
			docApply = (LabDocApply) super.findById(LabDocApply.class, id);
			return docApply;
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabDocApply> getLabDocApplyListByHql(String hql) throws GlobalException {
		List<LabDocApply> resultList = new ArrayList<LabDocApply>();
		Session session = openSession();
		Query query = session.createQuery(hql);
		resultList = query.list();
		return resultList;
	}

	@Override
	public PageResult getLabDocApplyPageResult(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql,pageResult);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return pageResult;
	}

	@Override
	public LabDocApply updateLabDocApply(LabDocApply labDocApply) throws GlobalException {
		try {
			super.update(labDocApply);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return labDocApply;
	}

}
