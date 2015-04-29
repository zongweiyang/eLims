package cn.labsoft.labos.common.formula.dao;

import java.util.List;

import cn.labsoft.labos.common.formula.entity.LabFormula;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabFormulaDAO extends IBaseDAO {
	
	public LabFormula addLabFormula(LabFormula labFormula) throws GlobalException;
	
	public boolean deleteLabFormula(String ids[])throws GlobalException;
	
	public boolean updateLabFormula(LabFormula labFormula)throws GlobalException;
	
	public LabFormula getLabFormula(String id)throws GlobalException;
	
	public PageResult getLabFormulaPR(String hql, PageResult pageResult)throws GlobalException;
	
	public List<LabFormula> getLabFormulaList(String hql)throws GlobalException;
	
}
