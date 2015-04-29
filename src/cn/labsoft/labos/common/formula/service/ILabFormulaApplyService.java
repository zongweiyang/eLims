package cn.labsoft.labos.common.formula.service;

import java.util.List;

import cn.labsoft.labos.common.formula.vo.LabFormulaApplyVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabFormulaApplyService {
	@SuppressWarnings("unchecked")
	public LabFormulaApplyVo addLabFormulaApply(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException;;
	
	public boolean deleteLabFormulaApply(String[] ids);
	
	public boolean updateLabFormulaApply4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabFormulaApply(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException;
	
	public LabFormulaApplyVo getLabFormulaApply(String id) throws GlobalException;
	
	public List<LabFormulaApplyVo> getLabFormulaApplyList(LabFormulaApplyVo labFormulaApplyVo) throws GlobalException;
	
	public PageResult getLabFormulaApplyPR(LabFormulaApplyVo labFormulaApplyVo,PageResult pageResult) throws GlobalException;
	
	public String getResult(String result,String forApplyId)throws GlobalException;
     }
