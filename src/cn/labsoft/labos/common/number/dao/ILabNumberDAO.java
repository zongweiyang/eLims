package cn.labsoft.labos.common.number.dao;

import java.util.List;

import cn.labsoft.labos.common.number.entity.LabNumber;
import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabNumberDAO extends IBaseDAO {
	
	public LabNumber addLabNumber(LabNumber labNumber) throws GlobalException;
	
	public boolean deleteLabNumber(String ids[])throws GlobalException;
	
	public boolean updateLabNumber(LabNumber labNumber)throws GlobalException;
	
	public LabNumber getLabNumber(String id)throws GlobalException;
	
	public PageResult getLabNumberPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabNumber> getLabNumberList(String hql)throws GlobalException;

	public void deleteAll(List<LabNumberPar> listLabNumberPar)throws GlobalException;

	public String getLabNumberByNo(String type, String[] names, String status) throws GlobalException;

	public List<String> getLabNumberBatchNo(int count, String typeCode, String[] name, String status)throws GlobalException;
	
}
