package cn.labsoft.labos.source.quality.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaControlDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaControl;



@Repository(value="labQuaControlDAO")
public class LabQuaControlDAOImpl extends BaseDAO implements ILabQuaControlDAO{

	@Override
	public LabQuaControl getLabQuaControl(String id) throws GlobalException {
		try {
		LabQuaControl labQuaControl=(LabQuaControl)super.findById(LabQuaControl.class,id);
		return labQuaControl;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaControl(LabQuaControl labQuaControl)throws GlobalException{
		try {
			super.update(labQuaControl);
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaControl addLabQuaControl(LabQuaControl labQuaControl)
			throws GlobalException {
		try {
			labQuaControl.setIsDel(Constants_Source.N);
			super.save(labQuaControl);
			return labQuaControl;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaControl(LabQuaControl labQuaControl)
			throws GlobalException {
		try {
			super.update(labQuaControl);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaControlPR(String hql, PageResult pageResult)throws GlobalException{
		try {
		pageResult=super.getPageResult(hql,pageResult);
		return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaControlDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaControl> getLabQuaControlByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
	
}
