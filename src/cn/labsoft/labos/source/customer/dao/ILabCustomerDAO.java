package cn.labsoft.labos.source.customer.dao;

import java.util.List;

import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabCustomerDAO extends IBaseDAO {

	public LabCustomer addLabCustomer(LabCustomer labCustomer) throws GlobalException;

	public boolean deleteLabCustomer(String ids[]) throws GlobalException;

	public boolean updateLabCustomer(LabCustomer labCustomer) throws GlobalException;

	public LabCustomer getLabCustomer(String id) throws GlobalException;

	public PageResult getLabCustomerPR(String hql, PageResult pageResult) throws GlobalException;

	public List<LabCustomer> getLabCustomerList(String hql) throws GlobalException;

}
