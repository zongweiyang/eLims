package cn.labsoft.labos.source.ambient.dao;

import java.util.List;

import cn.labsoft.labos.source.ambient.entity.LabAmbient;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabAmbientDAO extends IBaseDAO {
	
	public LabAmbient addLabAmbient(LabAmbient labAmbient) throws GlobalException;
	
	public boolean deleteLabAmbient(String ids[])throws GlobalException;
	
	public boolean updateLabAmbient(LabAmbient labAmbient)throws GlobalException;
	
	public LabAmbient getLabAmbient(String id)throws GlobalException;
	
	public PageResult getLabAmbientPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabAmbient> getLabAmbientList(String hql)throws GlobalException;
	
}
