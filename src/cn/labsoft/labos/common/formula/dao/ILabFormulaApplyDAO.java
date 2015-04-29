package cn.labsoft.labos.common.formula.dao;

import java.util.List;

import cn.labsoft.labos.common.formula.entity.LabFormulaApply;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabFormulaApplyDAO extends IBaseDAO {
	
	public LabFormulaApply addLabFormulaApply(LabFormulaApply labFormulaApply) throws GlobalException;
	
	public boolean deleteLabFormulaApply(String ids[])throws GlobalException;
	
	public boolean updateLabFormulaApply(LabFormulaApply labFormulaApply)throws GlobalException;
	
	public LabFormulaApply getLabFormulaApply(String id)throws GlobalException;
	
	public PageResult getLabFormulaApplyPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabFormulaApply> getLabFormulaApplyList(String hql)throws GlobalException;
	
}
