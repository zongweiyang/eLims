package cn.labsoft.labos.common.number.dao;

import java.util.List;

import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabNumberParDAO extends IBaseDAO {
	
	public LabNumberPar addLabNumberPar(LabNumberPar labNumberPar)throws GlobalException;
	
	public boolean deleteLabNumberPar(String ids[]) throws GlobalException;
	
	public boolean updateLabNumberPar(LabNumberPar labNumberPar)throws GlobalException;
	
	public LabNumberPar getLabNumberPar(String id)throws GlobalException;
	
	public PageResult getLabNumberParPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabNumberPar> getLabNumberParList(String hql)throws GlobalException;
	
}
