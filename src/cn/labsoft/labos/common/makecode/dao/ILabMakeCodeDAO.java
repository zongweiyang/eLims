package cn.labsoft.labos.common.makecode.dao;

import java.util.List;

import cn.labsoft.labos.common.makecode.entity.LabMakeCode;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
public interface ILabMakeCodeDAO extends IBaseDAO {
	/**
	 * 新增生成代码对象信息
	 * @param labMakeCode
	 * @return 新增对象信息
	 */
	public LabMakeCode addLabMakeCode(LabMakeCode labMakeCode) throws GlobalException;
	/**
	 * 真删除生成代码对象信息（单删除/批量删除）
	 * @param ids
	 * @return
	 */
	public boolean deleteLabMakeCode(String ids[])throws GlobalException;
	/**
	 * 修改生成代码
	 * @param labMakeCode
	 * @return true:修改成功，false：修改失败
	 */
	public boolean updateLabMakeCode(LabMakeCode labMakeCode)throws GlobalException;
	/**
	 * 根据Id获取生成代码对象
	 * @param id
	 * @return
	 */
	public LabMakeCode getLabMakeCode(String id)throws GlobalException;
	/**
	 * 获取生成代码分页对象信息
	 * @param hql
	 * @param pageResult
	 * @return
	 */
	public PageResult getLabMakeCodePR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据Hql获取生成代码集合信息
	 * @param hql
	 * @return
	 */
	public List<LabMakeCode> getLabMakeCodeList(String hql)throws GlobalException;
	
}
