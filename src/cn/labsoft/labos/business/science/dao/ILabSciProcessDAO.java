package cn.labsoft.labos.business.science.dao;

import java.util.List;

import cn.labsoft.labos.business.science.entity.LabSciProcess;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabSciProcessDAO extends IBaseDAO {
	
	public LabSciProcess addLabSciProcess(LabSciProcess labSciProcess) throws GlobalException;
	
	public boolean deleteLabSciProcess(String ids[]) throws GlobalException;
	
	public boolean deleteLabSciProcess(LabSciProcess labSciProcess) throws GlobalException;
	
	public boolean updateLabSciProcess(LabSciProcess labSciProcess) throws GlobalException;
	
	public LabSciProcess getLabSciProcess(String id) throws GlobalException;
	
	public PageResult getLabSciProcessPR(String hql, PageResult pageResult) throws GlobalException;
	
	public List<LabSciProcess> getLabSciProcessList(String hql) throws GlobalException;
	
}
