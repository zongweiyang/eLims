package cn.labsoft.labos.source.customer.service;

import java.util.List;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabCustomerService {
	@SuppressWarnings("unchecked")
	public LabCustomerVo addLabCustomer(LabCustomerVo labCustomerVo)
			throws GlobalException;;

	public boolean deleteLabCustomer(String[] ids) throws GlobalException;

	public boolean updateLabCustomer4Del(String[] ids) throws GlobalException;

	public boolean updateLabCustomer(LabCustomerVo labCustomerVo)
			throws GlobalException;

	public LabCustomerVo getLabCustomer(String id) throws GlobalException;

	public List<LabCustomerVo> getLabCustomerList(LabCustomerVo labCustomerVo)
			throws GlobalException;

	public PageResult getLabCustomerPR(LabCustomerVo labCustomerVo,
			PageResult pageResult) throws GlobalException;

	public List<String> getEveryMonthProgrammAmount(String id)throws GlobalException;
}
