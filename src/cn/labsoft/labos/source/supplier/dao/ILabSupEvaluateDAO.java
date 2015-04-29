package cn.labsoft.labos.source.supplier.dao;

import java.util.List;

import cn.labsoft.labos.source.supplier.entity.LabSupEvaluate;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabSupEvaluateDAO extends IBaseDAO {

	public LabSupEvaluate addLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException;

	public boolean deleteLabSupEvaluate(String ids[]) throws GlobalException;

	public boolean deleteLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException;

	public boolean updateLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException;

	public LabSupEvaluate getLabSupEvaluate(String id) throws GlobalException;

	public PageResult getLabSupEvaluatePR(String hql, PageResult pageResult) throws GlobalException;

	public List<LabSupEvaluate> getLabSupEvaluateList(String hql) throws GlobalException;

}
