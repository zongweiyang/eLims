package cn.labsoft.labos.source.supplier.service;

import java.util.List;
import cn.labsoft.labos.source.supplier.vo.LabSupEvaluateVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabSupEvaluateService {
	@SuppressWarnings("unchecked")
	public LabSupEvaluateVo addLabSupEvaluate(LabSupEvaluateVo labSupEvaluateVo)
			throws GlobalException;;

	public boolean deleteLabSupEvaluate(String[] ids) throws GlobalException;

	public boolean updateLabSupEvaluate4Del(String[] ids)
			throws GlobalException;

	public boolean updateLabSupEvaluate(LabSupEvaluateVo labSupEvaluateVo)
			throws GlobalException;

	public LabSupEvaluateVo getLabSupEvaluate(String id) throws GlobalException;

	public List<LabSupEvaluateVo> getLabSupEvaluateList(
			LabSupEvaluateVo labSupEvaluateVo) throws GlobalException;

	public PageResult getLabSupEvaluatePR(LabSupEvaluateVo labSupEvaluateVo,
			PageResult pageResult) throws GlobalException;

}
