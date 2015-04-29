package cn.labsoft.labos.source.supplier.service;

import java.util.List;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabSupplierService {
	@SuppressWarnings("unchecked")
	public LabSupplierVo addLabSupplier(LabSupplierVo labSupplierVo)
			throws GlobalException;;

	public boolean deleteLabSupplier(String[] ids) throws GlobalException;

	public boolean updateLabSupplier4Del(String[] ids) throws GlobalException;

	public boolean updateLabSupplier(LabSupplierVo labSupplierVo)
			throws GlobalException;

	public LabSupplierVo getLabSupplier(String id) throws GlobalException;

	public List<LabSupplierVo> getLabSupplierList(LabSupplierVo labSupplierVo)
			throws GlobalException;

	public PageResult getLabSupplierPR(LabSupplierVo labSupplierVo,
			PageResult pageResult) throws GlobalException;

	/**
	 * 得到评价供货时间list
	 * 
	 * @return
	 * @throws GlobalException
	 */
	public List<LabCodeVo> getLabSupEvaluateList(List<LabCodeVo> list,
			String string, String id) throws GlobalException;

}
