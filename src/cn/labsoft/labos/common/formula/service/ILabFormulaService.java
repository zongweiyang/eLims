package cn.labsoft.labos.common.formula.service;

import java.util.List;
import cn.labsoft.labos.common.formula.vo.LabFormulaVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabFormulaService {
	@SuppressWarnings("unchecked")
	public LabFormulaVo addLabFormula(LabFormulaVo labFormulaVo) throws GlobalException;;
	
	public boolean deleteLabFormula(String[] ids) throws GlobalException;
	
	public boolean updateLabFormula4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabFormula(LabFormulaVo labFormulaVo) throws GlobalException;
	
	public LabFormulaVo getLabFormula(String id) throws GlobalException;
	
	public List<LabFormulaVo> getLabFormulaList(LabFormulaVo labFormulaVo) throws GlobalException;
	
	public PageResult getLabFormulaPR(LabFormulaVo labFormulaVo,PageResult pageResult) throws GlobalException;
	
     }
