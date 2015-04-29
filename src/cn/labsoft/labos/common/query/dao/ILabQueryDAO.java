package cn.labsoft.labos.common.query.dao;

import java.util.List;

import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.common.query.entity.LabQuery;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabQueryDAO extends IBaseDAO {
	
	public LabQuery addLabQuery(LabQuery labQuery) throws GlobalException;
	
	public boolean deleteLabQuery(String ids[])throws GlobalException;
	
	public boolean updateLabQuery(LabQuery labQuery)throws GlobalException;
	
	public LabQuery getLabQuery(String id)throws GlobalException;
	
	public PageResult getLabQueryPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabQuery> getLabQueryList(String hql)throws GlobalException;

	public void deleteAll(List<LabParameter> listLabParameter)throws GlobalException;
	
}
