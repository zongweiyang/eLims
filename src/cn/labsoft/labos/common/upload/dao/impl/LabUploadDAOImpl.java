package cn.labsoft.labos.common.upload.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;


@Repository(value="labUploadDAO")
public class LabUploadDAOImpl extends BaseDAO implements ILabUploadDAO {

	@Override
	public LabUpload addLabUpload(LabUpload labUpload)
			throws GlobalException {
		try {
			super.save(labUpload);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return labUpload;
	}

	@Override
	public LabUpload findById(String id) throws GlobalException {
		return (LabUpload)super.findById(LabUpload.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<LabUpload> getLabUploadList(String busId,String type)
			throws GlobalException {
		String hql = "FROM LabUpload where 1=1 AND isDel='N'";
		if(null!=busId&&!"".equals(busId)){
			hql +=" AND busId = '"+busId+"'";
		}else{
			hql +=" AND busId = '1'";
		}
		if(null!=type&&!"".equals(type)){
			hql +=" AND busType = '"+type+"'";
		}
		hql +=" ORDER BY createTime DESC";
		List<LabUpload> list=super.find(hql);
		return list;
	}

	@Override
	public boolean deleteLabUpload(LabUpload labUpload)
			throws GlobalException {
		try {
			super.delete(labUpload);
			return true;
		} catch (RuntimeException e) {
			Log4J.error("LabUploadDAOImpl..."+e.getMessage());
		}
		return false;
	}

	@Override
	public LabUpload updateLabUpload(LabUpload labUpload)
			throws GlobalException {
		try {
			super.update(labUpload);
		} catch (RuntimeException e) {
			Log4J.error("LabUploadDAOImpl..."+e.getMessage());
		}
		return labUpload;
	}
}
