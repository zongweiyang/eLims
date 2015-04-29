package cn.labsoft.labos.source.doc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.doc.dao.ILabDocDAO;
import cn.labsoft.labos.source.doc.entity.LabDoc;
@Repository(value="labDocDAO")
public class LabDocDAOImpl extends BaseDAO implements ILabDocDAO {

	@Override
	public LabDoc addLabDoc(LabDoc labDoc) throws GlobalException {
		try {
			labDoc.setIsDel(Constants_Source.N);
			super.save(labDoc);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return labDoc;
	}

	@Override
	public Boolean addLabDocBoolean(LabDoc labDoc) throws GlobalException {
		try {
			super.save(labDoc);
		} catch (RuntimeException e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public void deleteLabDoc(LabDoc labDoc) throws GlobalException {
		super.delete(labDoc);
	}

	@Override
	public Boolean deleteLabDocBoolean(LabDoc labDoc) throws GlobalException {
		try {
			super.delete(labDoc);
		} catch (RuntimeException e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public LabDoc getLabDocById(String id) throws GlobalException {
		LabDoc labDoc = (LabDoc) super.findById(LabDoc.class, id);
		return labDoc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabDoc> getLabDocList(String hql) throws GlobalException {
		List<LabDoc> resultList = new ArrayList<LabDoc>();
		try {
			resultList = super.find(hql);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return resultList;
	}

	@Override
	public PageResult getLabDocPageResult(String hql, PageResult pageResult) throws GlobalException {
		 pageResult = super.getPageResult(hql,pageResult);
		return pageResult;
	}

	@Override
	public LabDoc updateLabDoc(LabDoc labDoc) throws GlobalException {
		super.update(labDoc);
		return labDoc;
	}

	@Override
	public Boolean updateLabDocBoolean(LabDoc labDoc) throws GlobalException {
		try {
			super.update(labDoc);
		} catch (RuntimeException e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

}
