package cn.labsoft.labos.business.samtest.dao;

import java.util.List;

import cn.labsoft.labos.business.samtest.entity.LabSamTest;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabSamTestDAO extends IBaseDAO {
	
	public LabSamTest addLabSamTest(LabSamTest labSamTest) throws GlobalException;
	
	public boolean deleteLabSamTest(String ids[]) throws GlobalException;
	
	public boolean deleteLabSamTest(LabSamTest labSamTest) throws GlobalException;
	
	public boolean updateLabSamTest(LabSamTest labSamTest) throws GlobalException;
	
	public LabSamTest getLabSamTest(String id) throws GlobalException;
	
	public PageResult getLabSamTestPR(String hql, PageResult pageResult) throws GlobalException;
	
	public List<LabSamTest> getLabSamTestList(String hql) throws GlobalException;
	/**
	 * 任务审核通过，样品信息同步到检验管理中
	 * @param busId 业务Id
	 * @return
	 * @throws GlobalException
	 */
	public boolean updateLabSamTest4LabSam(String busId) throws GlobalException;
	
}
