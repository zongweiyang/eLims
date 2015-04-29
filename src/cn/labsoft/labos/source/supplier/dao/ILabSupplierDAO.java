package cn.labsoft.labos.source.supplier.dao;

import java.util.List;

import cn.labsoft.labos.source.supplier.entity.LabSupplier;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabSupplierDAO extends IBaseDAO {
	
	public LabSupplier addLabSupplier(LabSupplier labSupplier) throws GlobalException;
	
	public boolean deleteLabSupplier(String ids[]) throws GlobalException;
	
	public boolean deleteLabSupplier(LabSupplier labSupplier) throws GlobalException;
	
	public boolean updateLabSupplier(LabSupplier labSupplier) throws GlobalException;
	
	public LabSupplier getLabSupplier(String id) throws GlobalException;
	
	public PageResult getLabSupplierPR(String hql, PageResult pageResult) throws GlobalException;
	
	public List<LabSupplier> getLabSupplierList(String hql) throws GlobalException;
	
}
