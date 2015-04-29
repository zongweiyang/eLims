package cn.labsoft.labos.source.appara.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.appara.dao.ILabApparaUseDAO;
import cn.labsoft.labos.source.appara.entity.LabApparaUse;
@Repository(value="labApparaUseDAO")
public class LabApparaUseDAOImpl extends BaseDAO implements ILabApparaUseDAO{

	
	@Override
	public LabApparaUse addLabApparaUse(LabApparaUse po) throws GlobalException {
		try {
			po.setIsDel(Constants_Source.N);
			super.save(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaUseDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaUse updateLabApparaUse(LabApparaUse po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabApparaUseDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabApparaUse getLabApparaUseById(String id) throws GlobalException {
		LabApparaUse labApparaUse = new LabApparaUse();
		try {
			labApparaUse = (LabApparaUse) super.findById(LabApparaUse.class, id);
		} catch (Exception ex) {
			Log4J.error("LabApparaUseDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labApparaUse;
	}

	@Override
	public boolean deleteLabApparaUseById(String[] ids) throws GlobalException {
		try {
			for (String id : ids) {
				super.delete(this.getLabApparaUseById(id));
			}
		} catch (Exception ex) {
			Log4J.error("labAppbespekDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
		
	}

	@Override
	public boolean deleteAllLabApparaUse(List<LabApparaUse> listLabApparaUse) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		try {
			super.deleteAll(listLabApparaUse);
		} catch (Exception ex) {
			Log4J.error("labAppbespekDAOImpl error...." + ex.getMessage(), ex);
			key=false;
			throw new GlobalException("" + ex.getMessage());
			// TODO: handle exception
		}
		return key;
	}
}
